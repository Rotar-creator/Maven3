package ru.sberbank.course.bank.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для хранения объектов в БД
 * В текущей реализации еще не используется, не успел
 */
public class DBH2Manager {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    static final String USER = "sa";
    static final String PASS = "";

    /**
     * Операция для выполнения скрипта в БД
     * @param sql скрипт
     */
    public void executer(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 1: Регистрируем JDBC driver
            Class.forName(JDBC_DRIVER);

            // 2: Открываем соединение
            System.out.println("Подключение к БД...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Успешное подключение к БД");

            // 3: Выполняем запрос
            stmt = conn.createStatement();

            System.out.println("Выполняем запрос: [" + sql + "]");

            stmt.executeUpdate(sql);
            System.out.println("Запрос выполнен успешно");

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Ошибка подключения JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // ошибка Class.forName
            e.printStackTrace();
        } finally {
            // всеравно закрываемся
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Выход !");
    }

    /**
     * Инициализация объектов в БД
     */
    public void initBase() {

        String sqlCreateTableClient =   "CREATE TABLE CLIENT " +
                                        "(id UUID primary key, " +
                                        " firstName VARCHAR(255), " +
                                        " lastName VARCHAR(255), " +
                                        " middleName VARCHAR(255), " +
                                        " dateBorn DATE, " +
                                        " dateCreate DATE, " +
                                        " inn INTEGER, " +
                                        " email VARCHAR(255), " +
                                        " mobile VARCHAR(255), " +
                                        " dateClose DATE " +
                                        " )";
        executer(sqlCreateTableClient);
    }

}
