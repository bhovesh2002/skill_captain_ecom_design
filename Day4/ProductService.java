package Product;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(){
        this.productRepository = new ProductRepository();
    }

    public void addProduct(String productName, String description, int quantity){
        if(!productName.isEmpty() && !description.isEmpty() && quantity != 0){
            Product product = new Product(productName, description, quantity);
            productRepository.addProduct(product);
            System.out.println("Product added. Name: "+ product.getName());
        }else {
            System.out.println("Every field is necessary.");
        }
    }

    public void buyProduct(String productName, int quantity){
        for (Product product : productRepository.getProductList()){
            if(product.getName().equals(productName)){
                if (product.getQuantity()<quantity){
                    int productPurchased = product.getQuantity();
                    System.out.println("Insufficient quantity of the product. Adding the remaining product.");
                    System.out.println("Product purchased: " + productName);
                    System.out.println("Quantity of the product purchased: " + productPurchased);
                    product.setQuantity(0);
                    productRepository.removeProduct(product);
                    return;
                }else {
                    int newQuantity = product.getQuantity() - quantity;
                    System.out.println("Product purchased: " + productName);
                    System.out.println("Quantity purchased: " + quantity);
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

}
