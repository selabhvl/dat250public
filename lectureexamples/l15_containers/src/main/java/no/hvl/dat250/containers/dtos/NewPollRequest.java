package no.hvl.dat250.containers.dtos;

import java.util.List;

public record NewPollRequest(
        String question,
        Long createdBy,
        List<String> options
) {
}
