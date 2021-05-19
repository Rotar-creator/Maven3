package ru.sberbank.course.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.course.bank.entity.Account;
import ru.sberbank.course.bank.entity.Client;
import ru.sberbank.course.bank.service.AccountService;
import ru.sberbank.course.bank.service.ClientService;

import java.util.List;
import java.util.UUID;

/**
 * Контролер для управления счетами
 */
@RestController
public class AccountController {
    private final AccountService accountService;
    private final ClientService clientService;

    @Autowired
    public AccountController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    /**
     * Rest сервис выгрузки информации по счету
     * @param id - ID счета
     * @return json ответ с реквизитами счета
     */
    @GetMapping(value = "/account/{id}")
    public ResponseEntity<Account> read(@PathVariable(name = "id") UUID id) {
        final Account account = accountService.findById(id);

        return account != null
                ? new ResponseEntity<>(account, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Форма создания нового счета
     * @param client_id - ID Клиент
     * @return html окно подтверждения создания клиента
     */
    @GetMapping("/account_create/{client_id}")
    public String getAccountCreate(@PathVariable(name = "client_id") UUID client_id) {

        Client client = clientService.findById(client_id);
        Account account = accountService.create();
        client.getAccounts().add(account);

        StringBuilder html = new StringBuilder();
        html.append("<label>созданли новый счет для клиента с id=" + client.toString() + "</label>");
        html.append("<br><label> id счета =" + account.toString() + "</label>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/account_list/").append(client.getId()).append("';\">Вернуться назад к списку счетов</button><br><br>");
        return html.toString();

    }

    /**
     * Вывод списка счетов
     * @param clientId- ID Клиент
     * @return html старница вывода списка счетов
     */
    @GetMapping("/account_list/{client_id}")
    public String getAccountList(@PathVariable(name = "client_id") UUID clientId) {
        Client client = clientService.findById(clientId);
        int numAccount = 0;
        StringBuilder html = new StringBuilder();

        float sumDebit = 100;

        if (client != null) {
            List<Account> accountList = client.getAccounts();

            //html.append("<br><br>").append("<a href=/client_create").append(">Создать нового клиента</a><br><br>");
            html.append("<br><br>").append("<button onclick=\"window.location.href = '/account_create/").append(client.getId()).append("';\">Открыть новый счет</button><br><br>");

            for (Account itemAccount : accountList) {
                numAccount += 1;
                html.append("<br> Счет ").append(numAccount).append(") № [").append(itemAccount.getNumber()).append("]");
                html.append("--- Остаток: |").append(itemAccount.getAmount()).append("|");
                html.append("<button onclick=\"window.location.href = '/account/").append(itemAccount.getId()).append("';\">просмотр</button>");
                html.append("<button onclick=\"window.location.href = '/transaction_list/").append(client.getId()).append("/").append(itemAccount.getId()).append("';\">Операции по счету (" + (itemAccount.getTransactions() == null ? 0 : itemAccount.getTransactions().size()) + ")</button>");
                html.append("<button onclick=\"window.location.href = '/account_sum/").append(client.getId()).append("/").append(itemAccount.getId()).append("/").append(1).append("/").append(sumDebit).append("';\">Выполнить зачисление</button>");
                html.append("<button onclick=\"window.location.href = '/account_sum/").append(client.getId()).append("/").append(itemAccount.getId()).append("/").append(2).append("/").append(sumDebit).append("';\">Выполнить списание</button>");
            }
            html.append("<br>");
        }
        //html.append("<br><br>").append("<a href=/").append(">Вернуться назад на главную</a><br><br>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/client_list/';\">Вернуться назад к списку клиентов</button><br><br>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/';\">Вернуться назад на главную</button><br><br>");
        return html.toString();
    }

}
