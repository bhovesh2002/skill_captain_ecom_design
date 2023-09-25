package User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    Map<String,User> userMap;

    public UserRepository(){
        this.userMap = new HashMap<>();
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }


    public User addUser(User user){
        userMap.put(user.getUsername(), user);
        return user;
    }



}
