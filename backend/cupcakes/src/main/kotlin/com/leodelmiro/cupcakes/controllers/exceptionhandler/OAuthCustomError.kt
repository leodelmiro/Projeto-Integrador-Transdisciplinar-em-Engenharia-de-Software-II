package com.leodelmiro.cupcakes.controllers.exceptionhandler

import com.fasterxml.jackson.annotation.JsonProperty

data class OAuthCustomError(
        private val error: String,
        @JsonProperty("error_description")
        private val errorDescription: String
)