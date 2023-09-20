import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    private final static int REGISTER = 1;
    private final static int LOGIN = 2;
    private final static int CHANGE_PASSWORD = 3;
    private final static int EXIT = 4;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("To register press 1");
        System.out.println("To login press 2");
        System.out.println("To change password press 3");
        System.out.println("To exit press 4");

        int i = 0;

        while (i != EXIT){
            System.out.println("Enter your choice: ");
            i = sc.nextInt();
            sc.nextLine();

            switch (i){
                case REGISTER -> {
                    userService.Signin(sc);
                }
                case LOGIN -> {
                    userService.Login(sc);
                }
                case CHANGE_PASSWORD -> {
                    userService.forgetPassword(sc);
                }
                case EXIT -> {
                    System.out.println("Exiting");
                }
                default -> {
                    System.out.println("Wrong choice entered.");
                }
            }
        }
        sc.close();

    }

}
