package no.hvl.dat250.l07.graphql;

public class Vote {

    private User user;
    private VoteOption option;

    public Vote(User user, VoteOption option) {
        this.user = user;
        this.option = option;
        this.user.getAllVotes().add(this);
        this.option.getVotes().add(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VoteOption getOption() {
        return option;
    }

    public void setOption(VoteOption option) {
        this.option = option;
    }
}
