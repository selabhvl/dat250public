package no.hvl.dat250.springjpa;

import org.springframework.web.client.RestTemplate;

public class ResetData {

    public static void main(String[] args) {
        RestTemplate req = new RestTemplate();

        req.put(ConcurrentRequests.ACCOUNT1_URL, PopulateData.ACCOUNT1_DATA);
        req.put(ConcurrentRequests.ACCOUNT2_URL, PopulateData.ACCOUNT2_DATA);
        req.put(ConcurrentRequests.ACCOUNT3_URL, PopulateData.ACCOUNT3_DATA);
    }
}
