package Model;

public class Vote {
    private String candidateId;
    private String userId;

    public Vote(String candidateId, String userId) {
        this.candidateId = candidateId;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }
}
