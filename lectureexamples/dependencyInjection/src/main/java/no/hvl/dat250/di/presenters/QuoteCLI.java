package no.hvl.dat250.di.presenters;

import no.hvl.dat250.di.domain.Quote;
import no.hvl.dat250.di.services.FamousQuoteService;
import no.hvl.dat250.di.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class QuoteCLI {

    private QuoteService service;

    public QuoteCLI(@Autowired QuoteService service) {
        this.service = service;
    }

    public void run() {
        int choice = -1;
        boolean validInput = false;
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (choice != 4) {
                System.out.println("Quote Manager Deluxe v.6.9");
                System.out.println("*********  Menu  *********");
                System.out.println("* 1. List quotes         *");
                System.out.println("* 2. Enter new quote     *");
                System.out.println("* 3. Upvote quote        *");
                System.out.println("* 4. Quit                *");
                System.out.println("**************************");
                System.out.println();
                System.out.print("Your choice> ");
                while (!validInput) {
                    String input = inputReader.readLine();
                    try {
                        choice = Integer.parseInt(input);
                        if (choice > 0 && choice < 5) {
                            validInput = true;
                        } else {
                            System.out.println("Invalid choice: " + choice);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input: " + input);
                    }
                }
                validInput = false;
                switch (choice) {
                    case 1:
                        renderQuotes();
                        break;
                    case 2:
                        newQuote(inputReader);
                        break;
                    case 3:
                        upvoteQuote(inputReader);
                        break;
                    default:
                        break;
                }

            }
        } catch (IOException e) {
            System.out.println("Unexpected IO Exception! Quitting now...");
        }

    }

    private void upvoteQuote(BufferedReader inputReader) throws IOException {
        List<Quote> quotes = service.listQuotes();
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter index of quote to upvote (1-" + quotes.size() +"):" );
            String input = inputReader.readLine();
            try {
                int idx = Integer.parseInt(input);
                if (idx > 0 && idx <= quotes.size()) {
                    Quote toUpvote = quotes.get(idx - 1);
                    service.upvoteQuote(toUpvote);
                    validInput = true;
                } else {
                    System.out.println("Error! Out out range!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error! Not a number!");
            }
        }
    }

    private void newQuote(BufferedReader inputReader) throws IOException {
        System.out.print("Enter quote (finished by Enter):");
        String quote = inputReader.readLine();
        System.out.print("Who said it?:");
        String who = inputReader.readLine();
        service.addQuote(quote, who);

    }

    private void renderQuotes() {
        List<Quote> quotes = service.listQuotes();
        System.out.println("\nCollected Quotes:");
        for (int i = 0; i < quotes.size(); i++) {
            System.out.println("" + (i+1) + ": \"" + quotes.get(i).getText() + "\" -- " + quotes.get(i).getWhoSaidIt() + " (" + quotes.get(i).getVotes() + " likes)");
        }
        System.out.println();
    }

}
