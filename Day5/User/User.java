package User;

import Order.Order;

import java.util.ArrayList;
import java.util.List;

//an empty cart list would be created for every user.
public class User {

    private String username;
    private String password;
    private List<Order> orderList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        orderList = new ArrayList<>();
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void orderProduct(Order order){
        orderList.add(order);
    }

}
