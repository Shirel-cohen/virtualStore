public class Purchase {
    private String productName;
    private double price;
    private int amountOfProduct;
    private int discount;

    public Purchase(String productName, double price, int amountOfProduct , int discount) {
        this.productName = productName;
        this.price = price;
        this.amountOfProduct = amountOfProduct;
        this.discount = discount;
    }

    public void setAmountOfProduct(int amountOfProduct) {
        this.amountOfProduct = amountOfProduct;
    }

    public int getDiscount() {
        return discount;
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

    @Override
    public String toString() {
        return "Product{" +
                "product Name='" + productName + '\'' +
                ", price=" + price +
                ", Amount=" + amountOfProduct +
                ", Discount for product=" + discount +
                '}';
    }
}
