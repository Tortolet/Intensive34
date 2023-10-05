package ru.aston.mineev_ia.task9.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Создание реестра сервисов Hibernate
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            // Создание метаданных на основе аннотаций и конфигурации
            Metadata metadata = new MetadataSources(standardRegistry)
                    .getMetadataBuilder()
                    .build();

            // Создание фабрики сессий Hibernate
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError("Инициализация Hibernate не удалась: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
