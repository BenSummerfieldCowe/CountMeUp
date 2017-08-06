package Model;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class UserLog {

    private static Map<String,Integer> user = new HashMap<>();
    public static Integer get(String userid) {
        return  user.get(userid);
    }

    public static Integer vote(String userid) {
        return user.merge(userid,1,Integer::sum);
    }
}
