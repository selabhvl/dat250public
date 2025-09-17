package no.hvl.data250.springjpa;


import no.hvl.data250.springjpa.entities.Account;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class ConcurrentRequests {


    public static final String ACCOUNT1_URL = "http://localhost:8080/accounts/123456789";
    public static final String ACCOUNT2_URL = "http://localhost:8080/accounts/987654321";
    public static final String ACCOUNT3_URL = "http://localhost:8080/accounts/133769001";

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            RestTemplate r1 = new RestTemplate();

            Account a1 = r1.getForObject(ACCOUNT1_URL, Account.class);
            Account a2 = r1.getForObject(ACCOUNT2_URL, Account.class);
            // Transfer from a1 to a2
            BigDecimal amountToTransfer = BigDecimal.valueOf(4_000L);
            a1.setAmount(a1.getAmount().subtract(amountToTransfer));
            a2.setAmount(a2.getAmount().add(amountToTransfer));

            r1.put(ACCOUNT1_URL, a1);
            r1.put(ACCOUNT2_URL, a2);
        });
        Thread t2 = new Thread(() -> {
            RestTemplate r1 = new RestTemplate();

            Account a1 = r1.getForObject(ACCOUNT1_URL, Account.class);
            Account a3 = r1.getForObject(ACCOUNT3_URL, Account.class);

            // Transfer from a1 to a3
            BigDecimal amountToTransfer = BigDecimal.valueOf(4_000L);
            a1.setAmount(a1.getAmount().subtract(amountToTransfer));
            a3.setAmount(a3.getAmount().add(amountToTransfer));

            r1.put(ACCOUNT1_URL, a1);
            r1.put(ACCOUNT2_URL, a3);
        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();

        RestTemplate r1 = new RestTemplate();
        Account a1 = r1.getForObject(ACCOUNT1_URL, Account.class);
        Account a2 = r1.getForObject(ACCOUNT2_URL, Account.class);
        Account a3 = r1.getForObject(ACCOUNT3_URL, Account.class);
        System.out.printf("Balance on %s is: %s\n", a1.getAccountNo(), a1.getAmount().toPlainString());
        System.out.printf("Balance on %s is: %s\n", a2.getAccountNo(), a2.getAmount().toPlainString());
        System.out.printf("Balance on %s is: %s\n", a3.getAccountNo(), a3.getAmount().toPlainString());
    }

}
