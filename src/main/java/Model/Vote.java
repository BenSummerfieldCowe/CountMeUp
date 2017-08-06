package Model;

public class Vote {
    private String candidateId;
    private User user;
    private String userID;

    public Vote(String candidateId, User user) {
        this.candidateId = candidateId;
        this.user = user;
    }

    public Vote(String candidateId, String userID) {
        this.candidateId = candidateId;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }
}
