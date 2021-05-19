package ru.sberbank.course.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.course.bank.entity.Client;
import ru.sberbank.course.bank.service.ClientService;

import java.util.List;
import java.util.UUID;

/**
 * Контролер для управления клиентами
 */

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     *  Форма обновления реквизитов Клиента
     * @param id- ID Клиента
     * @return html формы
     */
    @GetMapping("/client_update/{id}")
    public String getClientUpdate(@PathVariable(name = "id") UUID id) {
        Client client = clientService.findById(id);

        StringBuilder html = new StringBuilder();
        html.append("<label>Редактирование клиента с id=").append(client.toString()).append("</label>");
        html.append("<form method=\"post\" action=\"/client_save\">");
        html.append("<br><input name=\"id\" value=").append(client != null ? client.getId().toString() : "-").append(" type=\"hidden\">\n");
        html.append("<br><label>Имя</label>");
        html.append("<br><input name=\"firstName\" value=").append(client != null ? client.getFirstName() : "-").append(" type=\"text\">\n");
        html.append("<br><label>Фамилия</label>");
        html.append("<br><input name=\"lastName\" value=").append(client != null ? client.getLastName() : "-").append(" type=\"text\">\n");
        html.append("<br><label>Отчетсво</label>");
        html.append("<br><input name=\"middleName\" value=").append(client != null ? client.getMiddleName() : "0").append(" type=\"text\">\n");
        html.append("<br><label>ИНН</label>");
        html.append("<br><input name=\"inn\" value=").append(client != null ? client.getInn() : "").append(" type=\"text\">\n");
        html.append("<br><input type=\"submit\"></input>\n");
        html.append("</form>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/client_list/';\">Вернуться назад к списку клиентов (без сохранения)</button><br><br>");
        return html.toString();
    }

    /**
     * Форма создания нового клиента Клиента
     * @return html формы
     */
    @GetMapping("/client_create")
    public String getClientCreate() {

        String html = "<label>Создание нового клиента</label>" +
                "<form method=\"post\" action=\"/client_save_new\">" +
                "<br><label>Имя</label>" +
                "<br><input name=\"firstName\" type=\"text\">\n" +
                "<br><label>Фамилия</label>" +
                "<br><input name=\"lastName\"  type=\"text\">\n" +
                "<br><label>Отчетсво</label>" +
                "<br><input name=\"middleName\"  type=\"text\">\n" +
                "<br><label>ИНН</label>" +
                "<br><input name=\"inn\"  type=\"text\">\n" +
                "<br><input type=\"submit\"></input>\n" +
                "</form>" +
                "<br><br>" + "<button onclick=\"window.location.href = '/client_list/';\">Вернуться назад к списку клиентов (без создания)</button><br><br>";
        return html;
    }

    /**
     * Окно сохранение изменений карточки клиента
     * @param id    - id Клиента
     * @param firstName - Имя клиента
     * @param lastName - фамилия клиента
     * @param middleName - отчетсов клиента
     * @param inn - ИНН
     * @return html форма подтверждения
     */
    @PostMapping("/client_save")
    public String setClientSave(@RequestParam(name = "id") UUID id,
                                @RequestParam(name = "firstName") String firstName,
                                @RequestParam(name = "lastName") String lastName,
                                @RequestParam(name = "middleName") String middleName,
                                @RequestParam(name = "inn") Integer inn
    ) {
        Client client = clientService.findById(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setMiddleName(middleName);
        client.setInn(inn);

        StringBuilder html = new StringBuilder();
        html.append("<label>Сохранили клиента с id=" + client.toString() + "</label>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/client_list/';\">Вернуться назад к списку клиентов</button><br><br>");
        return html.toString();
    }

    /**
     * Окно сохранение новой карточки клиента
     * @param firstName - Имя клиента
     * @param lastName - фамилия клиента
     * @param middleName - отчетсов клиента
     * @param inn - ИНН
     * @return html форма подтверждения
     */
    @PostMapping("/client_save_new")
    public String setClientSave(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "middleName") String middleName,
            @RequestParam(name = "inn") Integer inn
    ) {
        Client client = clientService.create();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setMiddleName(middleName);
        client.setInn(inn);

        StringBuilder html = new StringBuilder();
        html.append("<label>Сохранили клиента с id=" + client.toString() + "</label>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/client_list/';\">Вернуться назад к списку клиентов</button><br><br>");
        return html.toString();
    }

    /**
     * Окно вывода списка клиентов
     * @return html список клиентов
     */
    @GetMapping("/client_list")
    public String getClientList() {
        List<Client> clientList = clientService.getAllList();
        int numClient = 0;
        StringBuilder html = new StringBuilder();

        //html.append("<br><br>").append("<a href=/client_create").append(">Создать нового клиента</a><br><br>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/client_create';\">Создать нового клиента</button><br><br>");

        for (Client itemClient : clientList) {
            numClient += 1;
            html.append("<br> Клиент №").append(numClient).append(" [").append(itemClient.getFio()).append("] ");
            html.append("<button onclick=\"window.location.href = '/client_update/").append(itemClient.getId()).append("';\">редактировать</button>");
            html.append("<button onclick=\"window.location.href = '/account_list/").append(itemClient.getId()).append("';\">Счета клиента (" + (itemClient.getAccounts() == null ? 0 : itemClient.getAccounts().size()) + ")</button>");
        }
        html.append("<br>");
        html.append("<br><br>").append("<a href=/").append(">Вернуться назад на главную</a><br><br>");
        html.append("<br><br>").append("<button onclick=\"window.location.href = '/';\">Вернуться назад на главную</button><br><br>");
        return html.toString();
    }

    /**
     * Rest сервис выгрузки информации по клиенту
     * @param id - ID клиента
     * @return json ответ с реквизитами клиента
     */
    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") UUID id) {
        final Client client = (Client) clientService.findById(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}