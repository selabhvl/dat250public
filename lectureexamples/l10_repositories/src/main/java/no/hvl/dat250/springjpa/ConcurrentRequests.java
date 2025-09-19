package no.hvl.dat250.springjpa;


import no.hvl.dat250.springjpa.entities.Account;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class ConcurrentRequests {


    public static final String ACCOUNT1_URL = "http://localhost:8080/accounts/123456789";
    public static final String ACCOUNT2_URL = "http://localhost:8080/accounts/987654321";
    public static final String ACCOUNT3_URL = "http://localhost:8080/accounts/133769001";

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            RestTemplate rt = new RestTemplate();
            try {
                rt.postForEntity("http://localhost:8080/transfers?fromAccount=123456789&toAccount=987654321&amount=4000", HttpEntity.EMPTY, Void.class);
                System.out.println("Transferred 4000 from 123456789 to 987654321");
            } catch (Throwable t) {
                System.out.println("Transfer 1 failed");
            }

        });
        Thread t2 = new Thread(() -> {
            RestTemplate rt = new RestTemplate();
            try {
                rt.postForEntity("http://localhost:8080/transfers?fromAccount=123456789&toAccount=133769001&amount=4000", HttpEntity.EMPTY, Void.class);
                System.out.println("Transferred 4000 from 123456789 to 133769001");
            } catch (Throwable t) {
                System.out.println("Transfer 2 failed");
            }


        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();

        RestTemplate rt = new RestTemplate();
        Account a1 = rt.getForObject(ACCOUNT1_URL, Account.class);
        Account a2 = rt.getForObject(ACCOUNT2_URL, Account.class);
        Account a3 = rt.getForObject(ACCOUNT3_URL, Account.class);
        System.out.printf("Balance on %s is: %s\n", a1.getAccountNo(), a1.getBalance().toPlainString());
        System.out.printf("Balance on %s is: %s\n", a2.getAccountNo(), a2.getBalance().toPlainString());
        System.out.printf("Balance on %s is: %s\n", a3.getAccountNo(), a3.getBalance().toPlainString());
    }

}
