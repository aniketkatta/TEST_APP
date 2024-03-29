package test_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service{

    private static Connection conn;

    static {
        String url="jdbc:mysql://localhost:3306/testdb";
        String username="root";
        String password="Kattasql@358";

        try {
            conn= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Override

    public List<Product> displayAllProduct() {
        String displayQuery="select * from product_info";
        List<Product> productList=new ArrayList<>();

        try {
            Statement stmt= conn.createStatement();
            ResultSet rs= stmt.executeQuery(displayQuery);

            while (rs.next())
            {
                int productId=rs.getInt("product_id");
                String productName=rs.getString("product_name");
                int productQty=rs.getInt("product_qty");
                double productPrice=rs.getDouble("product_price");
                Product pro=new Product(productId,productName,productQty,productPrice);

                productList.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productList;
    }

    @Override
    public int removeProduct(int proId) {
        String delete="delete from product_info where product_id=?";
        int n=0;
        try {
            PreparedStatement pstmt= conn.prepareStatement(delete);
            pstmt.setInt(1,proId);

            n= pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    @Override
    public int updateProduct(String proName, int proQty, double proPrice) {
        String update="update product_info set product_qty=?,product_price=? where product_name=?";
        int n=0;
        try {
            PreparedStatement pstmt= conn.prepareStatement(update);
            pstmt.setInt(1,proQty);
            pstmt.setDouble(2,proPrice);
            pstmt.setString(3,proName);
            n= pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    @Override
    public boolean placeOrder(Order order) {
        boolean n = false;
        try {
            CallableStatement cstmt = conn.prepareCall("{call placeOrder(?,?,?)}");
            cstmt.setString(1,order.getCustName());
            cstmt.setInt(2,order.getProduct_id());
            cstmt.setInt(3,order.getOrder_qty());
            n=cstmt.execute();
            if(n)
            {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    @Override
    public List<Order> orderList() {

        String orderDisplay="select customer_name,order_qty,total_amt from order_info";
        List<Order> orderList=new ArrayList<>();

        try {
            Statement stmt= conn.createStatement();
            ResultSet rs= stmt.executeQuery(orderDisplay);

            while (rs.next())
            {
                String customerName=rs.getString("customer_name");
                int orderQty=rs.getInt("order_qty");
                double total_amt=rs.getDouble("total_amt");

                Order order=new Order(customerName,orderQty,total_amt);
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return orderList;
    }


}
