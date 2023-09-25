package Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductRepository {

    List<Product> productList;

    public ProductRepository(){
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public void removeProduct(Product product){
        productList.remove(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
