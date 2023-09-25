package Product;


import Cart.CartProduct;
import Order.Order;
import User.User;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;
    private Order order;

    public ProductService(){
        this.productRepository = new ProductRepository();
        this.order = new Order();
    }

    public void addProductToMenu(String productName, String description, int price, int quantity){
        if(!productName.isEmpty() && !description.isEmpty() && quantity != 0){
            Product product = new Product(productName, description, price, quantity);
            productRepository.addProduct(product);
            System.out.println("Product added. Name: "+ product.getName());
        }else {
            System.out.println("Every field is necessary.");
        }
    }

    public void addProductToCart(String productName, int quantity, User user){
        for (Product product : productRepository.getProductList()){
            if(product.getName().equals(productName)){
                if (product.getQuantity()<quantity){
                    int productPurchased = product.getQuantity();
                    System.out.println("Insufficient quantity of the product. Adding the remaining product.");
                    System.out.println("Product purchased: " + productName);
                    System.out.println("Quantity of the product purchased: " + productPurchased);
                    CartProduct cartProduct = new CartProduct(product, productPurchased);
                    user.addToCart(cartProduct);
                    product.setQuantity(0);
                    productRepository.removeProduct(product);
                    return;
                }else {
                    int newQuantity = product.getQuantity() - quantity;
                    System.out.println("Product purchased: " + productName);
                    System.out.println("Quantity purchased: " + quantity);
                    CartProduct cartProduct = new CartProduct(product, quantity);
                    user.addToCart(cartProduct);
                    product.setQuantity(newQuantity);
                    return;
                }
            }
        }
        System.out.println("No product found with the name: " + productName);
    }

    public void showProducts(){
        for (Product product : productRepository.getProductList()){
            System.out.println("Product Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Quantity: " + product.getQuantity());
        }
    }

    public int showCart(User user){
        System.out.println("------CART-----");
        if(user.getCart().isEmpty()){
            System.out.println("Cart is empty.");
            return 0;
        }
        int totalPrice = 0;
        for (CartProduct cartProduct : user.getCart()){
            System.out.println("Product name: " + cartProduct.getProduct().getName());
            System.out.println("Quantity: " + cartProduct.getQuantity());
            int price = cartProduct.getProduct().getPrice() * cartProduct.getQuantity();
            System.out.println("Price: " + price);
            totalPrice = totalPrice + price;
        }
        System.out.println("--------------------");
        System.out.println("Total Price: " + totalPrice);
        System.out.println("--------------------");
        return totalPrice;
    }

    public void checkout(User user, String cardNumber, String cvv, int amount) throws InterruptedException {
        System.out.println("Processing payment...");
        Thread.sleep(3000);
        createOrder(user.getUsername(), user.getCart());
        user.setCart();
        System.out.println("Order Placed!");
    }

    public void createOrder(String username, List<CartProduct> cart){
        order.getOrderMap().put(username, cart);
    }

}
