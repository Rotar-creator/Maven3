package ru.sberbank.course.bank.entity;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Базовый класс прикладных сущностей
 */
public class BaseEntity {


    protected UUID id;

    /**
     * @return новый UUID
     */
    public static UUID createUuid() {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            return new UUID(random.nextLong(), random.nextLong());
    }

    public BaseEntity() {
        id = createUuid();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return getClass().getName() + "-" + getId() + "";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (other == null || getClass() != other.getClass())
            return false;

        return Objects.equals(getId(), ((BaseEntity) other).getId());
    }


}