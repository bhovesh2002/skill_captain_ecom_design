import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {

    private UserRepository userRepository;
    MessageDigest messageDigest;

    public UserService() throws NoSuchAlgorithmException {
        this.userRepository = new UserRepository();
        this.messageDigest = MessageDigest.getInstance("MD5");
    }

    public void Signin(Scanner sc){
        try{
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            System.out.println("Enter password: ");
            String password = sc.nextLine();
            if(!username.isEmpty() && !password.isEmpty()){
                String encryptedPassword = encrypt(password);
                User user = new User(username, encryptedPassword);
                userRepository.addUser(user);
                System.out.println("User registered. Username: "+ username);
            }else {
                System.out.println("Every field is necessary.");
            }

        }catch (InputMismatchException ime){
            System.out.println("Wrong type of input entered.");
        }

    }

    public User Login(Scanner sc){
        try{
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            System.out.println("Enter password: ");
            String password = sc.nextLine();
            if(!username.isEmpty() && !password.isEmpty()){
                String encryptedPassword = encrypt(password);
                for (User user: userRepository.getUserList()){
                    if(user.getUsername().equals(username) && user.getPassword().equals(encryptedPassword)){
                        System.out.println("Logged in");
                        return user;
                    }
                }
            }
            System.out.println("No user found");
            return null;

        }catch (InputMismatchException ime){
            System.out.println("Wrong input type entered");
            return null;
        }

    }

    public void forgetPassword(Scanner sc){
        try {
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            for (User user: userRepository.getUserList()){
                if(user.getUsername().equals(username)){
                    System.out.println("Enter new password: ");
                    String password = sc.nextLine();
                    user.setPassword(password);
                    return;
                }
            }
            System.out.println("No user with username "+ username + " found.");
        }catch (InputMismatchException ime){
            System.out.println("Wrong input type entered.");
        }
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
