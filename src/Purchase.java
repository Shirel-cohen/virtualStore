public class Purchase {
    private String productName;
    private double price;
    private int amountOfProduct;

    public Purchase(String productName, double price, int amountOfProduct) {
        this.productName = productName;
        this.price = price;
        this.amountOfProduct = amountOfProduct;
    }

    public int getAmountOfProduct() {
        return amountOfProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
