package ru.sberbank.course.bank.entity;


public enum TransactionType {

    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    private final String id;

    TransactionType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    public static TransactionType fromId(String id) {
        for (TransactionType at : TransactionType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}