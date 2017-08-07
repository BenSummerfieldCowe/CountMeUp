import Model.UserLog;
import Model.UserOther;
import Model.Vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

public class CountMeUp {

    private List<String> candidates;

    public CountMeUp(List<String> candidates) {
        this.candidates = candidates;
    }

    public CountMeUp() {

    }


    //This method takes about 2.2 second
    public Map<String, Integer> countForUser(List<UserOther> users) throws ExecutionException, InterruptedException {
        Map<String, Integer> results = new ConcurrentHashMap<>();
        ForkJoinPool myPool = new ForkJoinPool(8);
        myPool.submit(() -> users.stream().parallel()
                .forEach(user -> user.votes.stream().limit(3).forEach(vote -> results.merge(vote, 1, Integer::sum)))
        ).get();
        return  results;
    }


    //This method takes about 1.4 seconds
    public Map<String, AtomicInteger> countForUserAtomic(List<UserOther> users) throws ExecutionException, InterruptedException {
        Map<String, AtomicInteger> results2 = new HashMap<>();
        candidates.forEach(candidate -> results2.put(candidate,new AtomicInteger()));
        ForkJoinPool myPool = new ForkJoinPool(8);
              myPool.submit(() -> users.stream().parallel()
                   .forEach(user -> user.votes.stream().limit(3).forEach(vote -> {
                 results2.get(vote).getAndIncrement();
                     })
                   )
          ).get();
            return results2;
    }


        public Map<String, Integer> count(List<Vote> castVotes) {
//            Map<String, Integer> results = new ConcurrentHashMap<>();
            Map<String, Integer> results = new HashMap<>();
            UserLog userLog = new UserLog();
            //new ConcurrentHashMap<>();
            //new HashMap<>();
//            List<Vote> validVotes = new ArrayList<Vote>();
//            castVotes.forEach(votes -> {
//                votes.getUser().vote();
//                if (votes.getUser().canVote()) {
//                    validVotes.add(votes);
//                }
//            });
              Map<String,Integer> users = new HashMap<>();
//                Map<String,Integer> users = new ConcurrentHashMap<>();
               castVotes.forEach(votes -> {
//            UserLog.vote(userid);
            if (UserLog.vote(votes.getUserID())<3) {
            //   if (users.merge(votes.getUserID(),1,Integer::sum)<2) {
                results.merge(votes.getCandidateId(),1,Integer::sum);
            //  validVotes.add(votes);
                    }
                });
          //  castVotes.forEach(vote -> );
//            candidates.forEach(candidate ->
//                    results.put(candidate, Math.toIntExact(validVotes.stream()
//                            .filter(vote -> vote.getCandidateId().equals(candidate))
//                            .count()))
//            );

//            Map<String, AtomicInteger> results2 = new HashMap<>();
//            candidates.forEach(candidate -> results2.put(candidate,new AtomicInteger()));
//            ForkJoinPool myPool = new ForkJoinPool(8);
//            myPool.submit(() -> users.stream().parallel()
//                            .forEach(user -> user.votes.stream().limit(3).forEach(vote -> {
////                    results.merge(vote, 1, Integer::sum);
//                                        results2.get(vote).getAndIncrement();
//                                    })
//                            )
//            ).get();
//            return results2;
//            long startTime = System.nanoTime();
//            validVotes.stream().parallel().forEach(vote -> results.merge(vote.getCandidateId(), 1, Integer::sum));
//            long endTime = System.nanoTime();
//            System.out.println((endTime - startTime) / 1000000);
            return results;
        }

    }
