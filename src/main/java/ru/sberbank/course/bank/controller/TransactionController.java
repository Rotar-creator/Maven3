package ru.sberbank.course.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.course.bank.entity.Account;
import ru.sberbank.course.bank.entity.Transaction;
import ru.sberbank.course.bank.service.AccountService;
import ru.sberbank.course.bank.service.ClientService;
import ru.sberbank.course.bank.service.TransactionService;

import java.util.List;
import java.util.UUID;

/**
 * Контролер для управления транзакциями по счетам клиента
 */
@RestController
public class TransactionController {
    private final AccountService accountService;
    private final ClientService clientService;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(AccountService accountService, ClientService clientService, TransactionService transactionService) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    /**
     * Отображение в html списка операций по счету
     * @param client_id - ID Клиента
     * @param account_id - ID счета
     * @return html результата
     */
    @GetMapping("/transaction_list/{client_id}/{account_id}")
    public String getAccountList(@PathVariable(name = "client_id") UUID client_id
            , @PathVariable(name = "account_id") UUID account_id
    ) {
        Account account = accountService.findById(account_id);
        int numTransaction = 0;
        StringBuilder html = new StringBuilder();

        if (account != null) {
            List<Transaction> transactiontList = account.getTransactions();

            for (Transaction itemTransaction : transactiontList) {
                numTransaction += 1;
                String operName = "Списание";
                if ("CREDIT".equals(itemTransaction.getType().getId())){
                    operName = "Зачисление";
                }

                html.append("<br> Операция ").append(numTransaction).append(") Дата: [").append(itemTransaction.getDateOper()).append("] ");
                html.append("---- [" + operName + "] ----- Сумма =" + itemTransaction.getSumma() + "");

            }
            html.append("<br>");
        }


        //html.append("<br><br>").append("<a href=/").append(">Вернуться назад на главную</a><br><br>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/account_list/" + client_id + "';\">Вернуться назад к списку счетов</button><br><br>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/';\">Вернуться назад на главную</button><br><br>");
        return html.toString();
    }

    /**
     * Операция создания транзакции по счету на указанную сумму
     * @param clientId- ID Клиента
     * @param accountId - ID счета
     * @param typeId - Тип операции 1-зачисление /2-списание
     * @param summa - сумма операции
     * @return html результата
     */
    @GetMapping("/account_sum/{clientId}/{accountId}/{typeId}/{summa}")
    public String setAccountSum(
            @PathVariable(name = "clientId") UUID clientId
           , @PathVariable(name = "accountId") UUID accountId
            ,@PathVariable(name = "typeId") Integer typeId
            ,@PathVariable(name = "summa") float summa
    ) {

        Account account = accountService.findById(accountId);

        Transaction transaction = null;
        StringBuilder html = new StringBuilder();

        try {
            if (typeId == 1) {
                transaction = accountService.creditAmount(account, summa);
            } else {
                transaction = accountService.debitAmount(account, summa);
            }
            transactionService.create(transaction);

            html.append("<label>Операция "+ (typeId == 1?"Зачисления":"Списания")+" на сумму " +summa+ " успешно выполнена</label>");

        } catch (RuntimeException e) {
            html.append("<br><label>Ошибка выполнения операции:" + e.toString() + "</label>");

        }

        html.append("<br><br>").append("<button onclick=\"window.location.href = '/account_list/").append(clientId).append("';\">Вернуться назад к списку счетов</button><br><br>");
        return html.toString();

    }

}
