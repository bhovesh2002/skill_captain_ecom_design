package User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    private UserRepository userRepository;
    MessageDigest messageDigest;

    public UserService() throws NoSuchAlgorithmException {
        this.userRepository = new UserRepository();
        this.messageDigest = MessageDigest.getInstance("MD5");
    }

    public void signUp(String username, String password){
        if(!username.isEmpty() && !password.isEmpty()){
            String encryptedPassword = encrypt(password);
            User user = new User(username, encryptedPassword);
            userRepository.addUser(user);
            System.out.println("User.User registered. Username: "+ username);
        }else {
            System.out.println("Every field is necessary.");
        }
    }

    public User login(String username, String password){
        if(!username.isEmpty() && !password.isEmpty()){
            String encryptedPassword = encrypt(password);
            if (userRepository.getUserMap().containsKey(username)){
                if(userRepository.getUserMap().get(username).getPassword().equals(encryptedPassword)){
                    System.out.println("Logged in");
                    return userRepository.getUserMap().get(username);
                }else {
                    System.out.println("Wrong password entered");
                    return null;
                }
            }

//            for (User user: userRepository.getUserList()){
//                if(user.getUsername().equals(username) && user.getPassword().equals(encryptedPassword)){
//                    System.out.println("Logged in");
//                    return user;
//                }
//            }
        }
        System.out.println("No user found");
        return null;
    }

    public void forgetPassword(String username, String password){
        if(userRepository.getUserMap().containsKey(username)){
            String encryptedPassword = encrypt(password);
            userRepository.getUserMap().get(username).setPassword(encryptedPassword);
            return;
        }
//        for (User user: userRepository.getUserList()){
//            if(user.getUsername().equals(username)){
//                user.setPassword(password);
//                return;
//            }
//        }
        System.out.println("No user with username "+ username + " found.");

    }


    public String encrypt(String password){
        messageDigest.update(password.getBytes());
        byte[] bytes = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i< bytes.length; i++){
            stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return stringBuilder.toString();
    }

}
