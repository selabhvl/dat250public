package no.hvl.dat250.l04.examples.domains;


import com.google.common.collect.Lists;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public class Polls {

    public static class User {

        private String username;
        private String email;

        private List<Poll> createdPolls;

        public User() {
        }

        public User(String username, String email) {
            this.username = username;
            this.email = email;
            this.createdPolls = Lists.newArrayList();
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<Poll> getCreatedPolls() {
            return createdPolls;
        }

        public void setCreatedPolls(List<Poll> createdPolls) {
            this.createdPolls = createdPolls;
        }
    }



    public static class Poll {

        private String question;
        private Set<VoteOption> options;

        private User creator;

        private Instant closesAt;



        public Poll(String question, Set<VoteOption> options, User creator) {
            this.question = question;
            this.options = options;
            this.creator = creator;
            //creator.getCreatedPolls().add(this);  // this line is dangerous...
        }

        public Instant getClosesAt() {
            return closesAt;
        }

        public void setClosesAt(Instant closesAt) {
            this.closesAt = closesAt;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public Set<VoteOption> getOptions() {
            return options;
        }

        public void setOptions(Set<VoteOption> options) {
            this.options = options;
        }

        public User getCreator() {
            return creator;
        }

        public void setCreator(User creator) {
            this.creator = creator;
        }
    }

    public static class VoteOption {

        private int order;


        private String caption;

        public VoteOption(int order, String caption) {
            this.order = order;
            this.caption = caption;
        }

        public VoteOption() {
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }
    }
}
