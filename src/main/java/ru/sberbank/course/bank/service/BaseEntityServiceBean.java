package ru.sberbank.course.bank.service;

import org.springframework.stereotype.Service;
import ru.sberbank.course.bank.entity.BaseEntity;

import java.util.*;

@Service(BaseEntityService.NAME)
public class BaseEntityServiceBean implements BaseEntityService {

    // Хранилище объектов
    private static final Map<UUID, BaseEntity> ENTITY_REPOSITORY_MAP = new HashMap<>();

    /**
     * Добавляет сущность в память
     *
     * @param entity - сущность
     */
    @Override
    public void putList(BaseEntity entity) {
        ENTITY_REPOSITORY_MAP.put(entity.getId(), entity);
    }

    /**
     * получить объект из памяти по ID
     * @param entityId - ID сущности
     * @return - Объект
     */
    @Override
    public BaseEntity getList(UUID entityId) {
        return ENTITY_REPOSITORY_MAP.get(entityId);
    }

    /**
     * Получить список всех объектов из памяти
     *
     * @return список объектов
     */
    @Override
    public List<BaseEntity> getAllList() {

        List<BaseEntity> list = new ArrayList<>();
        Iterator<Map.Entry<UUID, BaseEntity>> iterator = ENTITY_REPOSITORY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, BaseEntity> entry = iterator.next();
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * Получить список объектов в памяти
     * @return количество записей
     */
    @Override
    public int getCountList() {
        return ENTITY_REPOSITORY_MAP.size();
    }

    /**
     * Удаляет объект в памяти
     * @param entityId - ID объекта
     * @return - true если сущность была удалена, иначе false
     */
    @Override
    public boolean removeList(UUID entityId) {
        return ENTITY_REPOSITORY_MAP.remove(entityId) != null;
    }

    /**
     * Обновляем объект в памяти
     * @param entity - объект
     * @return - true если сущность была удалена, иначе false
     */
    @Override
    public boolean updateList(BaseEntity entity) {
        if (ENTITY_REPOSITORY_MAP.containsKey(entity.getId())) {
            ENTITY_REPOSITORY_MAP.put(entity.getId(), entity);
            return true;
        }
        return false;
    }

    /**
     * Создает новый базовый объект - сущность и сохраняет в памяти
     * @return базовый объект
     */
    @Override
    public BaseEntity create() {
        return new BaseEntity();
    }

    /**
     * Возвращает объект по его ID
     *
     * @param id - ID объекта
     * @return - объект с заданным ID
     */
    @Override
    public BaseEntity read(UUID id) {
        return getList(id);
    }

    /**
     * Обновляет объект с заданным ID,
     * в соответствии с переданным объектом
     *
     * @param entity - объект в соответсвии с которым нужно обновить данные
     * @return - true если данные были обновлены, иначе false
     */
    @Override
    public boolean update(BaseEntity entity) {
        return updateList(entity);
    }

    /**
     * Удаляет сущность с заданным ID
     *
     * @param id - id сущности, которую нужно удалить
     * @return - true если сущность была удалена, иначе false
     */
    @Override
    public boolean delete(UUID id) {
        return removeList(id);
    }

}
