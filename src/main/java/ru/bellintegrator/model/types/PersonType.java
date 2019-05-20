package ru.bellintegrator.model.types;

/**
 * Описание id для разных типов Person
 */
public enum PersonType {
    /**
     * id=1 Person из списка контактов
     */
    Contact(1),
    /**
     * id=2 Person - ответственный
     */
    Responsible(2);

    private final int type;

    PersonType(int type) {
        this.type = type;
    }

    public int getValue() {
        return type;
    }
}
