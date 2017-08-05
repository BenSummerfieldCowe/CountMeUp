import Model.Vote;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountMeUpTests {

    public List<Vote> generateVotesWithValidVotes(Map<String,Integer> voteAmount) {
        List<Vote> votes = new ArrayList<Vote>();
        int j =0;
        voteAmount.forEach((k,v) -> {
            for(int i=0; i < voteAmount.get(k); i++) {
                votes.add(new Vote(k,k+Integer.toString(i)));
//                        j++;
            }
        });
        return votes;
    }

    @Test
    public void countMeUpReturnsAccurateVotesForSimpleSample() {
        Map<String, Integer> castVotes = new HashMap<String, Integer>();
        castVotes.put("1", 5);
        castVotes.put("2", 10);
        castVotes.put("3", 20);
        castVotes.put("4", 25);
        castVotes.put("5", 40);
//        castVotes.put("1", 500000);
//        castVotes.put("2", 1000000);
//        castVotes.put("3", 2000000);
//        castVotes.put("4", 2500000);
//        castVotes.put("5", 4000000);
        List<Vote> votes=   generateVotesWithValidVotes(castVotes);
        List<String> candidates = new ArrayList<String>();
        candidates.add("1");
        candidates.add("2");
        CountMeUp countMeUp = new CountMeUp(candidates);
        long startTime = System.nanoTime();
        Map<String, Integer> results = countMeUp.count(votes);
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime)/1000000);
        results.keySet().forEach(k -> assertEquals("Values different for candidate " + k, castVotes.get(k), results.get(k)));
    }

//    @Test
//    public void countMeUpreturnsAccurateVotes

}
