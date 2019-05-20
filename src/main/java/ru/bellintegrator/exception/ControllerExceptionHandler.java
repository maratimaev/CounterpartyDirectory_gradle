package ru.bellintegrator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.view.Error;

/**
 * Перехватывание исключений
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /** Обработка исключения при работе с объектом в БД
     * @param ex исключение
     * @return Error
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CantManipulateObject.class)
    public Error handlerCantUpdateOffice(CantManipulateObject ex) {
        return new Error(ex.getMessage());
    }

    /** Обработка прочих исключений
     * @param ex исключение
     * @return Error
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Error handlerException(Exception ex) {
        return new Error(String.format("There is an Exception Error: %s", ex.getMessage()));
    }

}
