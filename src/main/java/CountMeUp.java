import Model.Vote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CountMeUp {

    Map<String, Integer> votes = new HashMap<String,Integer>();
    private List<String> candidates;

    public CountMeUp(List<String> candidates) {
        this.candidates = candidates;
    }

    public Map<String,Integer> count(List<Vote> castVotes) {
//        Stream<String> candidateStream
        Map<String,Integer> results = new HashMap<>();
        candidates.forEach(candidate -> {
            results.put(candidate,Math.toIntExact(castVotes.parallelStream().filter(votes -> votes.getCandidateId().equals(candidate)).count()));
        });
        return votes;
    }

}
