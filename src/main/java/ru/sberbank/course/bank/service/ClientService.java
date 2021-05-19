package ru.sberbank.course.bank.service;

import org.springframework.stereotype.Service;
import ru.sberbank.course.bank.entity.BaseEntity;
import ru.sberbank.course.bank.entity.Client;

import java.util.List;
import java.util.UUID;

@Service
public interface ClientService {
    String NAME = "minibank_ClientService";

    /**
     * Создает нового клиента с сохранением его в памяти
     *
     * @return счет
     */
    Client create();

    /**
     * Сохранение полученного клиента в памяти
     *
     * @param client клиент
     * @return клиент
     */
    Client create(Client client);

    /**
     * Получить список всех клиентов из памяти
     *
     * @return список клиентов
     */
    List<Client> getAllList();

    /**
     * Операция закрытия клиента
     *
     * @param client клиента
     * @return клиент
     */
    Client closeClient(Client client);

    /**
     * Поиск клиента в памяти по его ИНН
     *
     * @param inn ИНН клиента
     * @return клиент
     */
    Client findClientByInn(Integer inn);

    /**
     * Удаление клиента в памяти по своему id
     *
     * @param client клиент
     * @return true если данные были удалены, иначе false
     */
    boolean removeList(Client client);

    /**
     * Обновление клиента в памяти по своему id
     *
     * @param client клиент
     * @return true если данные были обновлены, иначе false
     */
    boolean updateList(Client client);

    /**
     * Подсчет количества клиентов в памяти
     *
     * @return количество
     */
    int getCountList();

    /**
     * Поиск клиента в памяти по его id
     *
     * @param id ID клиента
     * @return клиент
     */
    Client findById(UUID id);


}