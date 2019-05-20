package ru.bellintegrator.view;

/**
 * DTO сообщения об ошибке
 */
public class Error {
    /**
     * Текст сообщения
     */
    private String message;

    public Error() {
    }

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
