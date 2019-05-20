package ru.bellintegrator.exception;

/**
 * Выбрасывается при действиях с объектами приводящих к ошибкам
 */
public class CantManipulateObject extends RuntimeException {
    /**
     * Текст сообщения об ошибке
     */
    private String message;
    /**
     * Сработавшее исключение
     */
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
