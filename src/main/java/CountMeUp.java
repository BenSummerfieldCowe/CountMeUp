import Model.UserLog;
import Model.UserOther;
import Model.Vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CountMeUp {

    private List<String> candidates;

    public CountMeUp(List<String> candidates) {
        this.candidates = candidates;
    }
    public CountMeUp(){

    }


    public Map<String, Integer> countForUser(List<UserOther> users) {
        Map<String, Integer> results =  new ConcurrentHashMap<>();
        users.stream().parallel().forEach(user -> user.votes.stream().limit(4).forEach(vote -> results.merge(vote, 1, Integer::sum)));
        return  results;
    }


    public Map<String, Integer> count(List<Vote> castVotes) {
        Map<String, Integer> results = new HashMap<>();
        //new ConcurrentHashMap<>();
        //new HashMap<>();
        List<Vote> validVotes = new ArrayList<Vote>();
        castVotes.forEach(votes -> {
            votes.getUser().vote();
            if (votes.getUser().canVote()) {
                validVotes.add(votes);
            }
        });
      //  Map<String,Integer> users = new HashMap<>();
    //    Map<String,Integer> users = new ConcurrentHashMap<>();
     //   castVotes.forEach(votes -> {
//            UserLog.vote(userid);
//            if (UserLog.vote(userid)<2) {
             //   if (users.merge(votes.getUserID(),1,Integer::sum)<2) {
            //    results.merge(votes.getCandidateId(),1,Integer::sum);
              //  validVotes.add(votes);
    //        }
    //    });
        candidates.forEach(candidate ->
                results.put(candidate, Math.toIntExact(validVotes.stream()
                        .filter(vote -> vote.getCandidateId().equals(candidate))
                       .count()))
        );
        return results;
    }

    class getthem implements Runnable {
        public void run() {

        }
    }

}
