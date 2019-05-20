package ru.bellintegrator.model.types;

/**
 * Описание id для разных типов Person
 */
public enum AddressType {
    /**
     * id=1 юридический адрес
     */
    Legal(1),
    /**
     * id=2 адрес для рекламной продукции
     */
    Advertising(2);

    private final int type;

    AddressType(int type) {
        this.type = type;
    }

    public int getValue() {
        return type;
    }
}
