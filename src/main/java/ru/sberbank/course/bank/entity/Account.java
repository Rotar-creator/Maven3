package ru.sberbank.course.bank.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс - счет клиента
 */
public class Account extends BaseEntity {

    private String number;

    private float amount;

    private Date dateCreate;

    private Date dateUpdate;

    private Date dateClose;

    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> debits) {
        this.transactions = debits;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public Account() {
        super();

        this.dateCreate = new Date();
        this.dateUpdate = new Date();
        this.transactions = new ArrayList<>();

    }

    public void close() {
        this.dateClose = new Date();
    }

}