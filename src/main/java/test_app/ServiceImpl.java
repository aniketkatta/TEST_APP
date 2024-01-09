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
}
