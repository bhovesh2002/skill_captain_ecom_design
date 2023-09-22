package Product;


import Order.Order;
import User.User;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(){
        this.productRepository = new ProductRepository();
    }

    public void addProduct(String productName, String description, int price, int quantity){
        if(!productName.isEmpty() && !description.isEmpty() && quantity != 0){
            Product product = new Product(productName, description, price, quantity);
            productRepository.addProduct(product);
            System.out.println("Product added. Name: "+ product.getName());
        }else {
            System.out.println("Every field is necessary.");
        }
    }

    public void buyProduct(String productName, int quantity, User user){
        for (Product product : productRepository.getProductList()){
            if(product.getName().equals(productName)){
                if (product.getQuantity()<quantity){
                    int productPurchased = product.getQuantity();
                    System.out.println("Insufficient quantity of the product. Adding the remaining product.");
                    System.out.println("Product purchased: " + productName);
                    System.out.println("Quantity of the product purchased: " + productPurchased);
                    Order order = new Order(product, productPurchased);
                    user.orderProduct(order);
                    product.setQuantity(0);
                    productRepository.removeProduct(product);
                    return;
                }else {
                    int newQuantity = product.getQuantity() - quantity;
                    System.out.println("Product purchased: " + productName);
                    System.out.println("Quantity purchased: " + quantity);
                    Order order = new Order(product, quantity);
                    user.orderProduct(order);
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

    public void showCart(User user){
        System.out.println("------CART-----");
        if(user.getOrderList().isEmpty()){
            System.out.println("Cart is empty.");
            return;
        }
        int totalPrice = 0;
        for (Order order : user.getOrderList()){
            System.out.println("Product name: " + order.getProduct().getName());
            System.out.println("Quantity: " + order.getQuantity());
            int price = order.getProduct().getPrice() * order.getQuantity();
            System.out.println("Price: " + price);
            totalPrice = totalPrice + price;
        }
        System.out.println("--------------------");
        System.out.println("Total Price: " + totalPrice);
        System.out.println("--------------------");
    }

}
