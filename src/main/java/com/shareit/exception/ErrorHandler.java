package com.shareit.exception;


import com.shareit.item.service.ItemService;
import com.shareit.item.service.ItemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({Throwable.class, AppException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse objectNotFound(Throwable e){
        return new ErrorResponse("Произошла непредвиденная ошибка!");
    }


}
