package Order;

import Cart.CartProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private Map<String, List<CartProduct>> orderMap;

    public Order() {
        this.orderMap = new HashMap<>();
    }

    public Map<String, List<CartProduct>> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, List<CartProduct>> orderMap) {
        this.orderMap = orderMap;
    }
}
