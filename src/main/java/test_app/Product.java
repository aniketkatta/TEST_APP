package test_app;

public class Product {
    private int productId;
    private String productName;
    private int productQty;
    private double productPrice;

    public Product() {
    }

    public Product(int productId, String productName, int productQty, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Product(String productName, int productQty, double productPrice) {
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
    }

    public int getProductQty() {
        return productQty;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
