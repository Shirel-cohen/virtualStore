public class Product {

    private int numOfProduct;
    private String productName;
    private int amount;
    private double price;
   private boolean isExist;

    public Product(int amount, double price, boolean isExist,int numOfProduct, String productName) {
        this.amount = amount;
        this.price = price;
        this.isExist = isExist;
        this.numOfProduct= numOfProduct;
        this.productName=productName;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumOfProduct() {
        return numOfProduct;
    }

    public void setNumOfProduct(int numOfProduct) {
        this.numOfProduct = numOfProduct;
    }


}
