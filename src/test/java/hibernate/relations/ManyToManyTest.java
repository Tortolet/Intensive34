package hibernate.relations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task9.hibernate.HibernateUtils;
import ru.aston.mineev_ia.task9.hibernate.relations.Birthday;
import ru.aston.mineev_ia.task9.hibernate.relations.enums.Roles;
import ru.aston.mineev_ia.task9.hibernate.relations.many_to_many.Employee;
import ru.aston.mineev_ia.task9.hibernate.relations.many_to_many.Position;

import java.time.LocalDate;
import java.util.List;

public class ManyToManyTest {
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

            Position position1 = new Position();
            position1.setName("Новичок");
            position1.setRoles(Roles.ROLE_NEWBIE);

            Position position2 = new Position();
            position2.setName("Опытный");
            position2.setRoles(Roles.ROLE_EXPERIENCED);

            Position position3 = new Position();
            position3.setName("Бывалый");
            position3.setRoles(Roles.ROLE_BIG_BRAIN);

            Employee employee1 = new Employee();
            employee1.setName("Иван");
            employee1.setBirthday(new Birthday(LocalDate.of(2003, 7, 14)));
            employee1.addPosition(position1);
            employee1.addPosition(position2);

            session.persist(position1);
            session.persist(position2);
            session.persist(position3);
            session.persist(employee1);

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
                System.out.println(employee.getPositions());
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
