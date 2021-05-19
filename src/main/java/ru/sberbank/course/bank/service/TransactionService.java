package ru.sberbank.course.bank.service;

import org.springframework.stereotype.Service;
import ru.sberbank.course.bank.entity.Transaction;
import ru.sberbank.course.bank.entity.TransactionType;

import java.util.List;

/**
 * Сервис работы с траназкциями по счетам и хранение их в пямяти (кеше)
 *
 */

@Service
public interface TransactionService {
    String NAME = "minibank_TransactionService";

    /**
     * Получить список всех операций из памяти
     *
     * @return список счетов
     */
    List<Transaction> getAllList();

    /**
     * Создает новую операцию с сохранением его в памяти
     *
     * @param type - Тип операции
     * @return операция
     */
    Transaction create(TransactionType type);

    /**
     * Сохранение полученной транзакции в памяти
     *
     * @param transaction операция
     * @return операция
     */
    Transaction create(Transaction transaction);

    /**
     * Получить список всех операций из памяти
     *
     * @return список операций
     */
    int getCountList();

}