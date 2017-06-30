package com.jonathan.handler;

import com.jonathan.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BadRequestException.class})
    public Map<String, String> handle400(Throwable throwable) {
        return this.constructResponse(HttpStatus.BAD_REQUEST, throwable);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Throwable.class})
    public Map<String, String> handle500(Throwable throwable) {
        return this.constructResponse(HttpStatus.INTERNAL_SERVER_ERROR, throwable);
    }

    private Map<String, String> constructResponse(HttpStatus status, Throwable throwable) {
        return Collections.unmodifiableMap(
                Stream
                        .of(
                                new AbstractMap.SimpleEntry<>("status", status.getReasonPhrase()),
                                new AbstractMap.SimpleEntry<>("message", throwable.getMessage())
                        )
                        .collect(
                                Collectors.toMap(
                                        AbstractMap.SimpleEntry::getKey,
                                        AbstractMap.SimpleEntry::getValue
                                )
                        )
        );
    }
}
