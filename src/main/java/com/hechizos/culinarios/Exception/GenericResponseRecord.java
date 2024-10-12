package com.hechizos.culinarios.Exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GenericResponseRecord<T>(
        int status,
        String message,
        List<T> data) {
}
