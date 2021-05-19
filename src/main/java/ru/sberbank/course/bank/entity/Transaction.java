package ru.sberbank.course.bank.entity;

import java.util.Date;

/**
 * Класс - транзакции по счету (операции)
 */
public class Transaction extends BaseEntity {
    private TransactionType type;

    private Long code;

    private Date dateOper;

    private Float summa;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getDateOper() {
        return dateOper;
    }

    public void setDateOper(Date dateOper) {
        this.dateOper = dateOper;
    }

    public Float getSumma() {
        return summa;
    }

    public void setSumma(Float summa) {
        this.summa = summa;
    }

    public Transaction() {
        super();

        this.dateOper = new Date();
    }

    public Transaction(TransactionType type) {
        super();

        Date date = new Date();
        this.dateOper = new Date();

        this.setType(type);

    }

}