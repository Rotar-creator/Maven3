package ru.sberbank.course.bank.service;

import org.springframework.stereotype.Service;
import ru.sberbank.course.bank.entity.Account;
import ru.sberbank.course.bank.entity.Client;
import ru.sberbank.course.bank.entity.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * Сервис работы со счетами и хранение их в пямяти (кеше)
 */
@Service
public interface AccountService {
    String NAME = "minibank_AccountService";

    /**
     * Создает новый счет с сохранением его в памяти
     *
     * @return счет
     */
    Account create();

    /**
     * Сохранение полученного счета в памяти
     *
     * @param account счет
     * @return счет
     */
    Account create(Account account);

    /**
     * Получить список всех счетов из памяти
     *
     * @return список счетов
     */
    List<Account> getAllList();

    /**
     * Операция закрытия счета
     *
     * @param account счет
     * @return счет
     */
    Account close(Account account);

    /**
     * Удаление счета из памяти
     *
     * @param account счет
     * @return true если данные были удалены, иначе false
     */
    boolean removeList(Account account);

    /**
     * Обновление счета в памяти по своему id
     *
     * @param account счет
     * @return true если данные были обновлены, иначе false
     */
    boolean updateList(Account account);

    /**
     * Подсчет количества счетов в памяти
     *
     * @return количество
     */
    int getCountList();

    /**
     * Поиск счета в памяти по его id
     *
     * @param id ID счета
     * @return счет
     */
    Account findById(UUID id);

    /**
     * Поиск счета по его номеру
     *
     * @param accountList список счетов
     * @param number номер счета
     * @return счет
     */

    Account findAccount(List<Account> accountList, String number);

    /**
     * Получить список счетов клиента
     *
     * @param client клиент
     * @return список счетов
     */
    List<Account> getAccountList(Client client);

    /**
     * Получить сумму остатка по счету
     *
     * @param account счет
     * @return остаток на счете
     */

    float getAmount(Account account);

    /**
     * Операция зачисления суммы на счет
     *
     * @param account счет
     * @param summa сумма
     * @return новая транзакиця
     */
    Transaction creditAmount(Account account, float summa);

    /**
     * Операция Списание суммы со счета
     *
     * @param account счет
     * @param summa сумма
     * @return новая транзакиця
     */
    Transaction debitAmount(Account account, float summa);

    /**
     * Операция генерируем новый случайный счет 40817
     * В данной реализации нет процесса проверки на уникальность счетов или базы заранее зарезервированных
     *
     * @return Номер счета
     */
    String genRandomNumber();

}