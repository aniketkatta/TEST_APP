package test_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    static Service service=new ServiceImpl();

    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Select Your Operation");
        System.out.println("1. Display All Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Update Product");
        System.out.println("4. Place Order");
        System.out.println("5. Display All Order");

        int ch=sc.nextInt();

        switch (ch)
        {
            case 1:
                displayAllProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                placeOrder();
                break;
            case 5:
                displayAllOrder();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
        main(args);
    }

    private static void displayAllOrder() {
        System.out.println("Enter Customer Name");
        String customer_name=sc.next();

        List<Order> orderlist=service.orderList();
        System.out.println("CustomerName \t\t\t OrderQTY \t\t\t TotalAmount");
        for (Order o:orderlist)
        {
            System.out.println(o.getCustName()+"\t\t\t\t\t"+o.getOrder_qty()+"\t\t\t\t\t"+o.getTotal_amt()+"");
        }
    }

    private static void placeOrder() {

        System.out.println("Enter Name");
        String name = sc.next();
        System.out.println("Enter product Id");
        int proId = sc.nextInt();
        System.out.println("Enter Order Quantity");
        int orderQty = sc.nextInt();
        Order order = new Order(name, proId, orderQty);
        boolean sts = service.placeOrder(order);
        if (sts) {
            System.out.println("Order placed");
        } else {
            System.out.println("Order Not Placed");
        }
    }
    private static void updateProduct() {
        System.out.println("Enter Product Name");
        String proName=sc.next();

        System.out.println("Enter Product Qty");
        int proQty=sc.nextInt();

        System.out.println("Enter Product Price");
        double proPrice=sc.nextDouble();

        int n=service.updateProduct(proName,proQty,proPrice);
        System.out.println(n+ "row update");
    }

    private static void removeProduct() {
        System.out.println("Enter Product Id");
        int proId=sc.nextInt();

        int n=service.removeProduct(proId);
        System.out.println(n +" Product Remove");
    }

    private static void displayAllProduct() {

        List<Product> productList=service.displayAllProduct();
        System.out.println("ProductId \t\t\t ProductName \t\t\t  ProductQty \t\t\t ProductPrice");
        for (Product p:productList)
        {
            System.out.println(p.getProductId()+"\t\t\t\t\t"+p.getProductName()+"\t\t\t\t\t"+p.getProductQty()+"\t\t\t\t\t"+p.getProductPrice());
        }
    }


}
