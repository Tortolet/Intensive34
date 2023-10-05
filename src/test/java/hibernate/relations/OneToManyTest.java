package hibernate.relations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task9.hibernate.HibernateUtils;
import ru.aston.mineev_ia.task9.hibernate.relations.Birthday;
import ru.aston.mineev_ia.task9.hibernate.relations.enums.Roles;
import ru.aston.mineev_ia.task9.hibernate.relations.one_to_many.Employee;
import ru.aston.mineev_ia.task9.hibernate.relations.one_to_many.Position;

import java.time.LocalDate;
import java.util.List;

public class OneToManyTest {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    public static void init() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Test
    void insertValues() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            Employee employee1 = new Employee();
            employee1.setName("Иван");
            employee1.setBirthday(new Birthday(LocalDate.of(2003, 7, 14)));

            Employee employee2 = new Employee();
            employee2.setName("Артем");
            employee2.setBirthday(new Birthday(LocalDate.of(2004, 9, 30)));

            Employee employee3 = new Employee();
            employee3.setName("Максим");
            employee3.setBirthday(new Birthday(LocalDate.of(2004, 5, 29)));

            Position position1 = new Position();
            position1.setName("Новичок");
            position1.setRoles(Roles.ROLE_NEWBIE);

            Position position2 = new Position();
            position2.setName("Опытный");
            position2.setRoles(Roles.ROLE_EXPERIENCED);

            Position position3 = new Position();
            position3.setName("Бывалый");
            position3.setRoles(Roles.ROLE_BIG_BRAIN);

            position1.addEmployeePos(employee1);
            position1.addEmployeePos(employee3);

            position2.addEmployeePos(employee2);

            session.persist(employee1);
            session.persist(employee2);
            session.persist(employee3);
            session.persist(position1);
            session.persist(position2);
            session.persist(position3);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Test
    void selectAll() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Employee> employees = session.createQuery("select e from Employee e", Employee.class).list();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
