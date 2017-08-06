package Model;


public class User {
    private int timesVote;

    public int getTimesVote() {
        return timesVote;
    }
    public void vote() {
        this.timesVote++;
    }

    public void setTimesVote(int timesVote) {
        this.timesVote = timesVote;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;

    public boolean canVote() {
        return timesVote<=3;
    }

    public User(String userID) {
        this.userID = userID;
        this.timesVote=0;
    }

}
