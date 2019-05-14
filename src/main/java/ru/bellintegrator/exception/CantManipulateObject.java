package ru.bellintegrator.exception;

/**
 * Выбрасывается, если нельзя обновить поля объекта
 */
public class CantManipulateObject extends RuntimeException {
    private String message;

    private Exception ex;

    public CantManipulateObject() {
    }

    public CantManipulateObject(String message, Exception ex) {
        this.message = message;
        this.ex = ex;
    }

    public CantManipulateObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }
}
