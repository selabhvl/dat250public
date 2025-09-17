package no.hvl.data250.springjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import no.hvl.data250.springjpa.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@Transactional
public class PopulateData implements CommandLineRunner {

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Account a1 = new Account("123456789", BigDecimal.valueOf(6_000L));
        Account a2 = new Account("987654321", BigDecimal.valueOf(3_000L));
        Account a3 = new Account("133769001", BigDecimal.valueOf(12_000L));
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.getTransaction().commit();
    }
}
