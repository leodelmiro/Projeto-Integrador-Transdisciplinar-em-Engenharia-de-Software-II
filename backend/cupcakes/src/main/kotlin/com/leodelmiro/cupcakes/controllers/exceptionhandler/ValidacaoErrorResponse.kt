package com.leodelmiro.cupcakes.controllers.exceptionhandler

import org.springframework.http.HttpStatus


class ValidacaoErrorResponse(
        var errors: List<CampoErrorResponse> = mutableListOf(),
        message: String?,
        path: String
) : ErrorResponse(
        status = HttpStatus.BAD_REQUEST.value(),
        error = HttpStatus.BAD_REQUEST.name,
        message = message ?: HttpStatus.BAD_REQUEST.reasonPhrase,
        path = path
)