package ru.sberbank.course.bank.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс - клиент
 */
public class Client extends BaseEntity {

    private Date dateCreate;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date dateBorn;

    private Integer inn;

    private String mobile;

    private String email;

    private List<Account> accounts;

    private Date dateClose;

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public Integer getInn() {
        return inn;
    }

    public void setInn(Integer inn) {
        this.inn = inn;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFio() {
        return lastName + " " + firstName + " " + middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Client() {
        super();
        this.dateCreate = new Date();
        this.accounts = new ArrayList<>();
    }

    public Client(Integer inn) {
        super();

        this.dateCreate = new Date();
        this.inn = inn;
    }

    public void close() {
        this.dateClose = new Date();
    }

}