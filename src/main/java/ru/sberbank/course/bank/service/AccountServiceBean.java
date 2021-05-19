package ru.sberbank.course.bank.service;

import org.springframework.stereotype.Service;
import ru.sberbank.course.bank.entity.Account;
import ru.sberbank.course.bank.entity.Client;
import ru.sberbank.course.bank.entity.Transaction;
import ru.sberbank.course.bank.entity.TransactionType;

import java.util.*;

/**
 * Реализация сервиса работы со счетами и хранение их в пямяти (кеше)
 *
 */

@Service(AccountService.NAME)
public class AccountServiceBean implements AccountService {

    // Хранилище объектов
    private static final Map<UUID, Account> ENTITY_REPOSITORY_MAP = new HashMap<>();

    /**
     * положить счет в кеш
     * @param entity счет
     */
    public void putList(Account entity) {
        ENTITY_REPOSITORY_MAP.put(entity.getId(), entity);
    }

    /**
     * получить счет из кеша
     * @param entityId ID счета
     * @return счет
     */
    public Account getList(UUID entityId) {
        return ENTITY_REPOSITORY_MAP.get(entityId);
    }

    /**
     * Получить список всех счетов из памяти
     *
     * @return список счетов
     */
    @Override
    public List<Account> getAllList() {

        List<Account> list = new ArrayList<>();
        for (Map.Entry<UUID, Account> entry : ENTITY_REPOSITORY_MAP.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * Создает новый счет с сохранением его в памяти
     *
     * @return счет
     */
    @Override
    public Account create() {
        Account account = new Account();
        account.setNumber(genRandomNumber());
        putList(account);
        return account;
    }

    /**
     * Сохранение полученного счета в памяти
     *
     * @param account счет
     * @return счет
     */
    @Override
    public Account create(Account account) {
        account.setNumber(genRandomNumber());
        putList(account);
        return account;
    }

    /**
     * Получить список всех счетов из памяти
     *
     * @return список счетов
     */
    @Override
    public int getCountList() {
        return ENTITY_REPOSITORY_MAP.size();
    }

    /**
     * Удаление счета из памяти
     *
     * @param account счет
     * @return Признак упеха
     */
    @Override
    public boolean removeList(Account account) {
        return ENTITY_REPOSITORY_MAP.remove(account.getId()) != null;
    }

    /**
     * Обновление счета в памяти по своему id
     *
     * @param account счет
     * @return Признак упеха
     */
    @Override
    public boolean updateList(Account account) {
        if (ENTITY_REPOSITORY_MAP.containsKey(account.getId())) {
            ENTITY_REPOSITORY_MAP.put(account.getId(), account);
            return true;
        }
        return false;
    }


    /**
     * Операция закрытия счета
     *
     * @param account счет
     * @return счет
     */
    @Override
    public Account close(Account account) {

        account.close();

        return account;
    }

    /**
     * Поиск счета в памяти по его id
     *
     * @param id ID счета
     * @return счет
     */
    @Override
    public Account findById(UUID id) {

        Account account = null;

        if (id != null) {
            account = ENTITY_REPOSITORY_MAP.get(id);
        }

        return account;
    }


    /**
     * Поиск счета по его номеру
     *
     * @param accountList список счетов
     * @param number номер счета
     * @return счет
     */
    public Account findAccount(List<Account> accountList, String number) {
        Account account = null;

        if (number != null) {
            if (accountList != null && accountList.size() > 0) {
                for (Account iterAccount : accountList) {
                    if (iterAccount != null) {
                        if (number.equals(iterAccount.getNumber())) {
                            account = iterAccount;
                            break;
                        }
                    }
                }
            }
        }

        return account;
    }

    /**
     * Получить список счетов клиента
     *
     * @param client клиент
     * @return список счетов
     */
    public List<Account> getAccountList(Client client) {
        List<Account> accountList = new ArrayList<Account>();

        if (client != null) {
            if (client.getAccounts() != null && client.getAccounts().size() > 0) {
                accountList.addAll(client.getAccounts());
            }
        }

        return accountList;
    }

    /**
     * Получить сумму остатка по счету
     *
     * @param account счет
     * @return остаток на счете
     */
    public float getAmount(Account account) {
        float amount = 0;

        if (account != null) {
            amount = account.getAmount();
        }

        return amount;
    }

    /**
     * Операция зачисления суммы на счет
     *
     * @param account счет
     * @param summa сумма
     * @return новая транзакиця
     */
    public Transaction creditAmount(Account account, float summa) {
        if (account != null) {

            Transaction transaction = new Transaction(TransactionType.CREDIT);
            transaction.setSumma(summa);
            account.setAmount(account.getAmount() + summa);
            if (account.getTransactions() == null) {
                account.setTransactions(new ArrayList<>());
            }
            account.getTransactions().add(transaction);
            return transaction;
        } else {
            throw new RuntimeException("Не указан счет для зачисления");
        }
    }

    /**
     * Операция Списание суммы со счета
     *
     * @param account счет
     * @param summa сумма
     * @return новая транзакиця
     */
    public Transaction debitAmount(Account account, float summa) {
        if (account != null) {
            if (account.getAmount() >= summa) {
                Transaction transaction = new Transaction(TransactionType.DEBIT);
                transaction.setSumma(summa);
                account.setAmount(account.getAmount() - summa);
                if (account.getTransactions() == null) {
                    account.setTransactions(new ArrayList<>());
                }
                account.getTransactions().add(transaction);
                return transaction;
            } else {
                throw new RuntimeException("Сумма списания " + summa + " превышает сумму остатка " + account.getAmount() + " на счете");
            }
        } else {
            throw new RuntimeException("Не указан счет для списания");
        }
    }

    /**
     * Операция генерируем новый случайный счет 40817
     * В данной реализации нет процесса проверки на уникальность счетов или базы заранее зарезервированных
     *
     * @return Номер счета
     */
    @Override
    public String genRandomNumber() {
        String newNumber = "40817 810";
        Random value = new Random();

        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        newNumber += " " + Integer.toString(r1) + " " + Integer.toString(r2);

        int count = 0;
        int n = 0;
        for (int i = 0; i < 10; i++) {
            //if (count == 1) {
            //    newNumber += " ";
            //    count = 0;
            //} else
            n = value.nextInt(10);
            newNumber += Integer.toString(n);
            count++;

        }
        return newNumber;
    }

}