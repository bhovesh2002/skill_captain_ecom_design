package User;

import Cart.CartProduct;

import java.util.ArrayList;
import java.util.List;

//an empty cart list would be created for every user.
public class User {

    private String username;
    private String password;
    private List<CartProduct> cart;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        cart = new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CartProduct> getCart() {
        return cart;
    }

    public void setCart() {
        this.cart = new ArrayList<>();
    }

    public void addToCart(CartProduct cartProduct){
        cart.add(cartProduct);
    }

}
