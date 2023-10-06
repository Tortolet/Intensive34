package ru.aston.mineev_ia.task9.hibernate.relations.convertors;

import jakarta.persistence.AttributeConverter;
import ru.aston.mineev_ia.task9.hibernate.relations.Birthday;

import java.sql.Date;
import java.util.Optional;

public class BirthdayConvertor implements AttributeConverter<Birthday, Date> {
    @Override
    public Date convertToDatabaseColumn(Birthday attribute) {
        return Optional.ofNullable(attribute)
                .map(Birthday::getDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date dbData) {
        return Optional.ofNullable(dbData)
                .map(Date::toLocalDate)
                .map(Birthday::new)
                .orElse(null);
    }
}
