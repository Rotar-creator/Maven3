package ru.sberbank.course.bank.service;

import org.springframework.stereotype.Service;
import ru.sberbank.course.bank.entity.BaseEntity;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для обработки объектов-сущность на базовом уровне и быть наследником
 * и хранение в кеше
 * в данное реализации не используется  (не успел сделать рефакторинг), обобщенные методы пока реализованы в прикладных сервисах
 */
@Service
public interface BaseEntityService {
    String NAME = "minibank_BaseEntityService";

    /**
     * Создает новый базовый объект - сущность и сохраняет в памяти
     * @return базовый объект
     */
    BaseEntity create();

    /**
     * Возвращает объект по его ID
     *
     * @param id - ID объекта
     * @return - объект с заданным ID
     */
    BaseEntity read(UUID id);

    /**
     * Обновляет объект с заданным ID,
     * в соответствии с переданным объектом
     *
     * @param entity - объект в соответсвии с которым нужно обновить данные
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(BaseEntity entity);

    /**
     * Удаляет сущность с заданным ID
     *
     * @param id - id сущности, которую нужно удалить
     * @return - true если сущность была удалена, иначе false
     */
    boolean delete(UUID id);

    /**
     * Добавляет сущность в память
     *
     * @param entity - сущность
     */
    void putList(BaseEntity entity);

    /**
     * получить объект из памяти по ID
     * @param entityId - ID сущности
     * @return - Объект
     */
    BaseEntity getList(UUID entityId);

    /**
     * Получить список всех объектов из памяти
     *
     * @return список объектов
     */
    List<BaseEntity> getAllList();

    /**
     * Удаляет объект в памяти
     * @param entityId - ID объекта
     * @return - true если сущность была удалена, иначе false
     */
    boolean removeList(UUID entityId);

    /**
     * Обновляем объект в памяти
     * @param entity - объект
     * @return - true если сущность была удалена, иначе false
     */
    boolean updateList(BaseEntity entity);

    /**
     * Получить список объектов в памяти
     * @return количество записей
     */
    int getCountList();


}
