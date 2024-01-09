package test_app;

import java.util.List;

public interface Service {

    List<Product> displayAllProduct();

    int removeProduct(int proId);

    int updateProduct(String proName, int proQty, double proPrice);

    boolean placeOrder(Order order);

    List<Order> orderList();
}
