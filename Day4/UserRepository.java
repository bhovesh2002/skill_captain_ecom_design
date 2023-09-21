import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    List<User> userList;

    public UserRepository(){
        this.userList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return userList;
    }


    public User addUser(User user){
        userList.add(user);
        return user;
    }



}
