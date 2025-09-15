package no.hvl.dat250.jpa;

import jakarta.persistence.*;
import no.hvl.dat250.jpa.entities.User;
import org.hibernate.cfg.JdbcSettings;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Demo1App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        emf.runInTransaction(entityManager -> {

            // Executing native SQL query to check whether connection works
            String pgVersion = (String) entityManager.createNativeQuery("select version()", String.class).getSingleResult();
            System.out.printf("Successfully connected to Postgres running: %s\n", pgVersion);

            // Executing another native SQL queries, this time reading the courses
            var resultList = entityManager.createNativeQuery("select id, code, semester, year from courses", Tuple.class).getResultList();
            System.out.println("Courses in the Database:");
            for (Object o : resultList) {
                Tuple t = (Tuple) o;
                System.out.printf("id: %s code: %s semester: %s-%s \n", t.get("id", String.class), t.get("code", String.class), t.get("year", BigDecimal.class).toPlainString(), t.get("semester", Character.class));

            }
        });
    }
}
