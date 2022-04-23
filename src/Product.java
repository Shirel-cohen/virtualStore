public class Product extends Purchase{


    private int ProductNumber;
    private boolean isExist;


    public Product(String productName, double price, int amountOfProduct, int discount, int productNumber, boolean isExist) {
        super(productName, price, amountOfProduct, discount);
        ProductNumber = productNumber;
        this.isExist = isExist;
    }

    public int getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(int productNumber) {
        ProductNumber = productNumber;
    }



    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    @Override
    public String toString() {

        return  super.toString() +"\t\t{" +
                "Product Number=" + ProductNumber +
                ", Available in stock=" + isExist +
                '}';
    }
}