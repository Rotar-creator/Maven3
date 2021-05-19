package ru.sberbank.course.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BankApplication {


    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);


        // Подключение к базе данных и заполнение данных в базу доделать сегодня
        // DBH2Manager dBH2Manager = new DBH2Manager();

       // dBH2Manager.initBase();
       // client.setMiddleName("Успех");
    }


}
