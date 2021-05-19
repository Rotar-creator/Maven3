package ru.sberbank.course.bank.service;

import org.springframework.stereotype.Service;
import ru.sberbank.course.bank.entity.Transaction;
import ru.sberbank.course.bank.entity.TransactionType;

import java.util.*;

/**
 * Реализация сервиса работы с траназкциями по счетам и хранение их в пямяти (кеше)
 *
 */

@Service(TransactionService.NAME)
public class TransactionServiceBean implements TransactionService {
    // Хранилище объектов
    private static final Map<UUID, Transaction> ENTITY_REPOSITORY_MAP = new HashMap<>();

    public void putList(Transaction entity) {
        ENTITY_REPOSITORY_MAP.put(entity.getId(), entity);
    }

    public Transaction getList(UUID entityId) {
        return ENTITY_REPOSITORY_MAP.get(entityId);
    }

    /**
     * Получить список всех операций из памяти
     *
     * @return список счетов
     */
    @Override
    public List<Transaction> getAllList() {

        List<Transaction> list = new ArrayList<>();
        for (Map.Entry<UUID, Transaction> entry : ENTITY_REPOSITORY_MAP.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * Создает новую операцию с сохранением его в памяти
     *
     * @param type - Тип операции
     * @return операция
     */

    @Override
    public Transaction create(TransactionType type) {
        Transaction transaction = new Transaction(type);
        putList(transaction);
        return transaction;
    }

    /**
     * Сохранение полученной транзакции в памяти
     *
     * @param transaction операция
     * @return операция
     */
    @Override
    public Transaction create(Transaction transaction) {
        putList(transaction);
        return transaction;
    }

    /**
     * Получить список всех операций из памяти
     *
     * @return список операций
     */
    @Override
    public int getCountList() {
        return ENTITY_REPOSITORY_MAP.size();
    }

}