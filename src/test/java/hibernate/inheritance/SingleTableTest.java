package hibernate.inheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task9.hibernate.HibernateUtils;
import ru.aston.mineev_ia.task9.hibernate.inheritance.single_table.models.BankAccount;
import ru.aston.mineev_ia.task9.hibernate.inheritance.single_table.models.CreditCard;
import ru.aston.mineev_ia.task9.hibernate.inheritance.single_table.models.BillingDetails;

import java.util.List;

public class SingleTableTest {
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

            CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber(44411111);
            creditCard.setExpMonth("Jan");
            creditCard.setExpYear("2017");
            creditCard.setOwner("Bill Gates");

            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccount(111222333);
            bankAccount.setBankName("Goldman Sachs");
            bankAccount.setSwift("GOLDUS33");
            bankAccount.setOwner("Donald Trump");

            session.persist(creditCard);
            session.persist(bankAccount);

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
            List<BillingDetails> billingDetails = session.createQuery("select bd from ru.aston.mineev_ia.task9.hibernate.inheritance.single_table.models.BillingDetails bd", BillingDetails.class).list();
            for (Object billingDetail : billingDetails) {
                System.out.println(billingDetail);
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
