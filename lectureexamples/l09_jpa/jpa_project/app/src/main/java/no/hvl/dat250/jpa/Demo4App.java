package no.hvl.dat250.jpa;

import jakarta.persistence.*;
import no.hvl.dat250.jpa.entities.BankAccount;
import no.hvl.dat250.jpa.entities.Post;
import no.hvl.dat250.jpa.entities.User;
import org.hibernate.cfg.JdbcSettings;

import java.math.BigDecimal;
import java.util.List;

public class Demo4App {

    public static void main(String[] args) throws InterruptedException {
        EntityManagerFactory emf = new PersistenceConfiguration("test2")
                .managedClass(BankAccount.class)
                // corresponds to 'jakarta.persistence.jdbc.url' in the persistence.xml
                .property(PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:bank")
                // other properties accordingly
                .property(PersistenceConfiguration.JDBC_USER, "sa")
                .property(PersistenceConfiguration.JDBC_PASSWORD, "")
                .property(PersistenceConfiguration.SCHEMAGEN_DATABASE_ACTION, "drop-and-create") // Recreate the database fresh; DO NOT USE IN PRODUCTION
                // Hibernate specific properties are found in the JdbcSettings class
                .property(JdbcSettings.SHOW_SQL, true)
                .property(JdbcSettings.FORMAT_SQL, true)
                .property(JdbcSettings.HIGHLIGHT_SQL, true)
                .property("hibernate.transaction.jta.platform", "jta")
                .createEntityManagerFactory();
        emf.runInTransaction(entityManager -> {
            BankAccount b1 = new BankAccount("1234 56 789", BigDecimal.valueOf(5_000));
            BankAccount b2 = new BankAccount("9876 54 321", BigDecimal.valueOf(3_000));
            BankAccount b3 = new BankAccount("1337 69 007", BigDecimal.valueOf(10_000));
            entityManager.persist(b1);
            entityManager.persist(b2);
            entityManager.persist(b3);
        });

        Thread t1 = new Thread(() -> {
            EntityManager entityManager = emf.createEntityManager();
            EntityTransaction tx = entityManager.getTransaction();
            try {
                tx.begin();
                BankAccount b1 = entityManager.find(BankAccount.class, "1234 56 789");
                BankAccount b2 = entityManager.find(BankAccount.class, "9876 54 321");
                b1.transfer(b2, BigDecimal.valueOf(4_000));
                tx.commit();
            } catch (Throwable e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                System.out.println("Error finishing transfer 1: " + e.getMessage());
            }
        });
        Thread t2 = new Thread(() -> {
            EntityManager entityManager = emf.createEntityManager();
            EntityTransaction tx = entityManager.getTransaction();
            try {
            tx.begin();
            BankAccount b1 = entityManager.find(BankAccount.class, "1234 56 789");
            BankAccount b3 = entityManager.find(BankAccount.class, "1337 69 007");
            b1.transfer(b3, BigDecimal.valueOf(4_000));
            tx.commit();
        } catch (Throwable e) {
            if (tx.isActive()) {
                tx.rollback();
            }
                System.out.println("Error finishing transfer 2: " + e.getMessage());
        }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        emf.runInTransaction(entityManager -> {
            List<BankAccount> allAccounts = entityManager.createQuery("select b from BankAccount b", BankAccount.class).getResultList();
            for (BankAccount a : allAccounts) {
                System.out.printf("Account %s balance: %s\n", a.getAccountNo(), a.getBalance().toPlainString());
            }
        });
    }
}
