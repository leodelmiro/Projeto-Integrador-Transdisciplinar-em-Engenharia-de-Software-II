package com.leodelmiro.cupcakes.controllers.exceptionhandler

import com.leodelmiro.cupcakes.services.exceptions.DatabaseException
import com.leodelmiro.cupcakes.services.exceptions.ForbiddenException
import com.leodelmiro.cupcakes.services.exceptions.RecursoNotFoundException
import com.leodelmiro.cupcakes.services.exceptions.UnauthorizedException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    protected fun handleBadRequest(ex: RuntimeException, request: HttpServletRequest): ResponseEntity<ErrorResponse> =
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ErrorResponse(
                            status = HttpStatus.BAD_REQUEST.value(),
                            error = HttpStatus.BAD_REQUEST.name,
                            message = ex.message ?: HttpStatus.BAD_REQUEST.reasonPhrase,
                            path = request.requestURI
                    )
            )

    @ExceptionHandler(value = [RecursoNotFoundException::class])
    protected fun handleRecursoNotFoundException(ex: RecursoNotFoundException, request: HttpServletRequest)
            : ResponseEntity<ErrorResponse> =
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ErrorResponse(
                            status = HttpStatus.NOT_FOUND.value(),
                            error = HttpStatus.NOT_FOUND.name,
                            message = ex.message ?: HttpStatus.NOT_FOUND.reasonPhrase,
                            path = request.requestURI
                    )
            )

    @ExceptionHandler(value = [DatabaseException::class])
    protected fun handleDatabaseException(ex: DatabaseException, request: HttpServletRequest):
            ResponseEntity<ErrorResponse> =
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    ErrorResponse(
                            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
                            error = HttpStatus.UNPROCESSABLE_ENTITY.name,
                            message = ex.message ?: HttpStatus.UNPROCESSABLE_ENTITY.reasonPhrase,
                            path = request.requestURI
                    )
            )

    @ExceptionHandler(value = [ForbiddenException::class])
    protected fun handleForbiddenException(ex: ForbiddenException, request: HttpServletRequest):
            ResponseEntity<OAuthCustomError> =
            ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    OAuthCustomError(
                            error = HttpStatus.FORBIDDEN.name,
                            errorDescription = ex.message ?: HttpStatus.FORBIDDEN.reasonPhrase
                    )
            )

    @ExceptionHandler(value = [UnauthorizedException::class])
    protected fun handleUnauthorizedException(ex: UnauthorizedException, request: HttpServletRequest):
            ResponseEntity<OAuthCustomError> =
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    OAuthCustomError(
                            error = HttpStatus.UNAUTHORIZED.name,
                            errorDescription = ex.message ?: HttpStatus.UNAUTHORIZED.reasonPhrase
                    )
            )

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> =
            ValidacaoErrorResponse(
                    errors = ex.bindingResult.fieldErrors.map { error ->
                        CampoErrorResponse(error.field, error.defaultMessage ?: "Sem mensagem de erro")
                    },
                    message = "Erro de Validação",
                    path = (request as ServletWebRequest).request.requestURI
            ).run {
                ResponseEntity.status(status).body(this)
            }
}