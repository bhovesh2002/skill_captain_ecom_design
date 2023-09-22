import Product.ProductService;
import User.User;
import User.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private final static int REGISTER = 1;
    private final static int ADD_PRODUCT = 1;
    private final static int LOGIN = 2;
    private final static int SHOW_ALL_PRODUCTS = 2;
    private final static int CHANGE_PASSWORD = 3;
    private final static int BUY_PRODUCT = 3;
    private final static int EXIT = 4;
    private final static int CHECK_CART = 4;
    private final static int LOGOUT = 5;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        ProductService productService = new ProductService();

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
                    signUpMain(sc, userService);
                }
                case LOGIN -> {
                    switchLogin(sc, logInMain(sc, userService), productService);
                }
                case CHANGE_PASSWORD -> {
                    changePassword(sc, userService);
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

    public static void signUpMain(Scanner sc, UserService userService){
        try {
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            System.out.println("Enter password: ");
            String password = sc.nextLine();
            userService.signUp(username, password);
        }catch (InputMismatchException ime){
            System.out.println("Wrong input type entered.");
        }
    }

    public static User logInMain(Scanner sc, UserService userService){
        try{
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            System.out.println("Enter password: ");
            String password = sc.nextLine();
            return userService.Login(username, password);
        }catch (InputMismatchException ime){
            System.out.println("Wrong input type entered.");
            return null;
        }
    }

    public static void changePassword(Scanner sc, UserService userService){
        try {
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            System.out.println("Enter new password: ");
            String password = sc.nextLine();
            userService.forgetPassword(username, password);
        }catch (InputMismatchException ime){
            System.out.println("Wrong input type entered.");
        }
    }


    public static void switchLogin(Scanner sc, User user, ProductService productService){
        if(user != null){
            System.out.println("Welcome " + user.getUsername());
            System.out.println("Press 1 to add new product");
            System.out.println("Press 2 to check all products");
            System.out.println("press 3 to buy a product");
            System.out.println("press 4 to check your cart");
            System.out.println("Press 5 to logout");
            int i = 0;

            while (i != LOGOUT){
                System.out.println("Enter your choice");
                i = sc.nextInt();
                sc.nextLine();

                switch (i){
                    case ADD_PRODUCT -> {
                        addProduct(sc, productService);
                    }
                    case SHOW_ALL_PRODUCTS -> {
                        showAllProducts(productService);
                    }
                    case BUY_PRODUCT -> {
                        buyProduct(sc, productService, user);
                    }
                    case CHECK_CART -> {
                        checkCart(productService, user);
                    }
                    case LOGOUT -> {
                        System.out.println("Logging Out...");
                    }
                    default -> {
                        System.out.println("Wrong choice entered.");
                    }

                }

            }
        }
    }

    public static void addProduct(Scanner sc, ProductService productService){
        try {
            System.out.println("Enter the product name: ");
            String name = sc.nextLine();
            System.out.println("Enter the description for the product: ");
            String desc = sc.nextLine();
            System.out.println("Enter the price of the product: ");
            int price = sc.nextInt();
            System.out.println("Enter the quantity: ");
            int quantity = sc.nextInt();

            productService.addProduct(name.toLowerCase(), desc, price, quantity);
        }catch (InputMismatchException ime){
            System.out.println("Wrong input type entered.");
        }
    }

    public static void showAllProducts(ProductService productService){
        productService.showProducts();
    }

    public static void buyProduct(Scanner sc, ProductService productService, User user){
        try {
            System.out.println("Enter the product name you want to buy: ");
            String name = sc.nextLine();
            System.out.println("Enter the quantity: ");
            int quantity = sc.nextInt();
            productService.buyProduct(name, quantity, user);
        }catch (InputMismatchException ime){
            System.out.println("Wrong input type entered.");
        }
    }

    public static void checkCart(ProductService productService, User user){
        productService.showCart(user);
    }

}
