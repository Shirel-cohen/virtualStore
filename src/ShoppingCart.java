import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<String> productNames;
    private double toPay;


    public ShoppingCart(ArrayList<String> productNames, double toPay) {
        this.productNames = productNames;
        this.toPay = toPay;
    }
}
