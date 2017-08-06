package Model;

import java.util.ArrayList;
import java.util.List;

public class UserOther {

public List<String> votes;
public UserOther() {
    votes = new ArrayList<>();
}
public void vote(String vote) {
    votes.add(vote);
}
}
