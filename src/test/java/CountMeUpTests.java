import Model.User;
import Model.UserOther;
import Model.Vote;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class CountMeUpTests {

    public List<Vote> generateVotesWithValidVotes(Map<String,Integer> voteAmount) {
        List<Vote> votes = new ArrayList<Vote>();
        voteAmount.forEach((k,v) -> {
            for(int i=0; i < voteAmount.get(k); i++) {
                votes.add(new Vote(k,new User(k+Integer.toString(i))));
            }
        });
        return votes;
    }

    private class VoteTestObject{
        private List<UserOther> unsortedVotes;
        private Map<String,Integer> results;
        public VoteTestObject(List<UserOther> unsortedVotes, Map<String,Integer> results) {
            this.unsortedVotes = unsortedVotes;
            this.results = results;
        }

        public List<UserOther> getUnsortedVotes() {
            return unsortedVotes;
        }

        public Map<String, Integer> getResults() {
            return results;
        }
    }
    public VoteTestObject generateUsers() {
        int voteAmount;
        List<UserOther> votes = new ArrayList<>();
        Map<String,Integer> validVotes = new HashMap<>();
        int j = 10000000;
//        int j = 15;
        UserOther user;
        for(int i=0 ; i<=j; i++) {
            user = new UserOther();
            //Randomise how many tims a user votes, currently up to 4 to allow invalid votes
            voteAmount = (int) (1 +Math.random() * 4);
            for(int k =0; k<voteAmount;k++) {
                //randomise who they vote for
                String candidate =Integer.toString((int) (1+ Math.random() * 5));
                user.vote(candidate);
                //Store the correct value
                if(k<4) {
                    validVotes.merge(candidate, 1, Integer::sum);
                }
            }
            votes.add(user);
           i=i+(voteAmount-1);
        }
        return new VoteTestObject(votes,validVotes);
    }

    @Test
    public void countMeUpReturnsAccurateVotesForSimpleSetSampleForUsers() {
      //   = new HashMap<>();
        VoteTestObject voteTestObject= generateUsers();
        List<UserOther> users = voteTestObject.getUnsortedVotes();
        Map<String, Integer> castVotes = voteTestObject.getResults();
        CountMeUp countMeUp = new CountMeUp();
        long startTime = System.nanoTime();
        Map<String, Integer> results = countMeUp.countForUser(users);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000);
        castVotes.keySet().forEach(k -> assertEquals("Values different for candidate " + k, castVotes.get(k), results.get(k)));
    }

//    public List<Vote> generateVotesWithValidVotes(Map<String,Integer> voteAmount) {
//        List<Vote> votes = new ArrayList<Vote>();
//        int j =0;
//        voteAmount.forEach((k,v) -> {
//            for(int i=0; i < voteAmount.get(k); i++) {
//                votes.add(new Vote(k,k+Integer.toString(i)));
////                        j++;
//            }
//        });
//        return votes;
//    }

    @Test
    public void countMeUpReturnsAccurateVotesForSimpleSetSample() {
        Map<String, Integer> castVotes = new HashMap<>();
//        castVotes.put("1", 5);
//        castVotes.put("2", 10);
//        castVotes.put("3", 20);
//        castVotes.put("4", 25);
//        castVotes.put("5", 40);
        castVotes.put("1", 500000);
        castVotes.put("2", 1000000);
        castVotes.put("3", 2000000);
        castVotes.put("4", 2500000);
        castVotes.put("5", 4000000);
        List<Vote> votes=   generateVotesWithValidVotes(castVotes);
//        votes.add(new Vote("1",new User("11")));
        List<String> candidates = new ArrayList<>();
        candidates.add("1");
        candidates.add("2");
        candidates.add("3");
        candidates.add("4");
        candidates.add("5");
        CountMeUp countMeUp = new CountMeUp(candidates);
        long startTime = System.nanoTime();
        Map<String, Integer> results = countMeUp.count(votes);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime)/1000000);
        castVotes.keySet().forEach(k -> assertEquals("Values different for candidate " + k, castVotes.get(k), results.get(k)));
    }

    @Test
    public void countMeUpReturnsAccurateVotesForSimpleRandomSample() {
        Map<String, Integer> castVotes = new HashMap<>();
        castVotes.put("1", (int) (Math.random()*1000));
        castVotes.put("2", (int) (Math.random()*1000));
        castVotes.put("3", (int) (Math.random()*1000));
        castVotes.put("4", (int) (Math.random()*1000));
        castVotes.put("5", (int) (Math.random()*1000));
        List<Vote> votes=   generateVotesWithValidVotes(castVotes);
        List<String> candidates = new ArrayList<String>();
        candidates.add("1");
        candidates.add("2");
        candidates.add("3");
        candidates.add("4");
        candidates.add("5");
        CountMeUp countMeUp = new CountMeUp(candidates);
        long startTime = System.nanoTime();
        Map<String, Integer> results = countMeUp.count(votes);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime)/1000000);
        castVotes.keySet().forEach(k -> assertEquals("Values different for candidate " + k, castVotes.get(k), results.get(k)));
    }



}
