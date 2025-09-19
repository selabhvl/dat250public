package no.hvl.dat250.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import no.hvl.dat250.jpa.inheritance.*;
import org.hibernate.cfg.JdbcSettings;

import java.math.BigDecimal;

public class Demo3App {

    public static void main(String[] args) {
        EntityManagerFactory emf = new PersistenceConfiguration("test3")
                .managedClass(Employee.class)
                .managedClass(Role.class)
                .managedClass(EngineerRole.class)
                .managedClass(CustomerSupportRole.class)
                .managedClass(ManagerRole.class)
                .property(PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:employees")
                .property(PersistenceConfiguration.JDBC_USER, "sa")
                .property(PersistenceConfiguration.JDBC_PASSWORD, "")
                .property(JdbcSettings.SHOW_SQL, true)
                .property(JdbcSettings.FORMAT_SQL, true)
                .property(JdbcSettings.HIGHLIGHT_SQL, true)
                .createEntityManagerFactory();

        emf.getSchemaManager().create(true);

        emf.runInTransaction(em -> {
            Employee e1 = new Employee("772-24-2312", "Lyndell Piele", BigDecimal.valueOf(500_000), "Engineering");
            Employee e2 = new Employee("539-90-9492", "Hank Everley", BigDecimal.valueOf(400_000), "Engineering");
            Employee e3 = new Employee("653-54-1370", "Tymon Kidman", BigDecimal.valueOf(700_000), "Engineering");
            e1.addRole(new EngineerRole("Junior Engineer"));
            e1.addRole(new ManagerRole("Engineering Supervisor", "Engineering"));
            e2.addRole(new CustomerSupportRole("Call Center Agent"));
            e3.addRole(new EngineerRole("Senior Engineer"));
            em.persist(e1);
            em.persist(e2);
            em.persist(e3);
        });
        System.out.println("Populated the database");
    }


}
