package no.hvl.dat250.springjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import no.hvl.dat250.springjpa.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@Transactional
public class PopulateData implements CommandLineRunner {

    public static final Account ACCOUNT1_DATA = new Account("123456789", BigDecimal.valueOf(6_000L));
    public static final Account ACCOUNT2_DATA = new Account("987654321", BigDecimal.valueOf(3_000L));
    public static final Account ACCOUNT3_DATA = new Account("133769001", BigDecimal.valueOf(12_000L));
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Account a1 = ACCOUNT1_DATA;
        Account a2 = ACCOUNT2_DATA;
        Account a3 = ACCOUNT3_DATA;
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.getTransaction().commit();
    }



}
