package ru.sberbank.course.bank.service;

import org.springframework.stereotype.Service;
import ru.sberbank.course.bank.entity.BaseEntity;
import ru.sberbank.course.bank.entity.Client;

import java.util.*;

/**
 * Реализация сервиса работы с клиентами и хранение их в пямяти (кеше)
 */

@Service(ClientService.NAME)
public class ClientServiceBean implements ClientService {

    // Хранилище объектов
    private static final Map<UUID, Client> ENTITY_REPOSITORY_MAP = new HashMap<>();

    public void putList(Client entity) {
        ENTITY_REPOSITORY_MAP.put(entity.getId(), entity);
    }

    public Client getList(UUID entityId) {
        return ENTITY_REPOSITORY_MAP.get(entityId);
    }

    @Override
    public List<Client> getAllList() {

        List<Client> list = new ArrayList<>();
        for (Map.Entry<UUID, Client> entry : ENTITY_REPOSITORY_MAP.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * Создает нового клиента с сохранением его в памяти
     *
     * @return счет
     */
    @Override
    public Client create() {
        Client client = new Client();
        putList(client);
        return client;
    }

    /**
     * Сохранение полученного клиента в памяти
     *
     * @param client клиент
     * @return клиент
     */
    @Override
    public Client create(Client client) {
        putList(client);
        return client;
    }

    /**
     * Подсчет количества клиентов в памяти
     *
     * @return количество
     */
    @Override
    public int getCountList() {
        return ENTITY_REPOSITORY_MAP.size();
    }

    /**
     * Удаление клиента в памяти по своему id
     *
     * @param client клиент
     * @return true если данные были удалены, иначе false
     */
    @Override
    public boolean removeList(Client client) {
        return ENTITY_REPOSITORY_MAP.remove(client.getId()) != null;
    }

    /**
     * Обновление клиента в памяти по своему id
     *
     * @param client клиент
     * @return true если данные были обновлены, иначе false
     */
    @Override
    public boolean updateList(Client client) {
        if (ENTITY_REPOSITORY_MAP.containsKey(client.getId())) {
            ENTITY_REPOSITORY_MAP.put(client.getId(), client);
            return true;
        }
        return false;
    }


    /**
     * Операция закрытия клиента
     *
     * @param client клиента
     * @return клиент
     */
    @Override
    public Client closeClient(Client client) {

        client.close();

        return client;
    }

    /**
     * Поиск клиента в памяти по его ИНН
     *
     * @param inn ИНН клиента
     * @return клиент
     */
    @Override
    public Client findClientByInn(Integer inn) {

        Client client = null;

        if (inn != null) {
            List<Client> clientList = getAllList();
            if (clientList != null && clientList.size() > 0) {
                for (Client iterClient : clientList) {
                    if (iterClient != null) {
                        if (inn.equals(iterClient.getInn())) {
                            client = iterClient;
                            break;
                        }
                    }
                }
            }
        }

        return client;
    }

    /**
     * Поиск клиента в памяти по его id
     *
     * @param id ID клиента
     * @return клиент
     */

    @Override
    public Client findById(UUID id) {

        Client client = null;

        if (id != null) {
            client = ENTITY_REPOSITORY_MAP.get(id);
        }

        return client;
    }

}