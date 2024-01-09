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
                //displayAllOrder();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
        main(args);
    }

    private static void placeOrder() {

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
        for (Product p:productList)
        {
            System.out.println(p.getProductId()+"  "+p.getProductName()+"   "+p.getProductQty()+"   "+p.getProductPrice());
        }
    }


}
