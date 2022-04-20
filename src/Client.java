
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Introduce {


    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean isMember;
    private boolean isWorker;
    private double finalCostOfPurchases;
    private ArrayList<Purchase> purchases;
//    private LinkedList<Product> shoppingCartLinkedLis;
//    private String date; // תודה של על הערה


    public Client(String firstName, String lastName, String userName, String password, boolean isMember, boolean isWorker) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.isMember = isMember;
        this.isWorker = isWorker;
        this.purchases = new ArrayList<>();
    }

    public Client() {

    }

    public void setFinalCostOfPurchases(double finalCostOfPurchases) {
        this.finalCostOfPurchases = finalCostOfPurchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String introduce() {
        String introduce;
        if (isMember) {
            introduce = "Hello " + firstName + " " + lastName + " [Vip]";
        }else
        introduce = "Hello " + firstName + " " + lastName;
        return introduce;
    }

    public String toString() {

        if (isMember) {
            return "**************************************\n" +
                    "Client Details: " + firstName + " " + lastName + " | CLUB MEMBER | " +
                    "Amount of purchases = " + purchases.size() + " | " +
                    "Total cost of purchases = $" + finalCostOfPurchases + "\n";

        }
        return "**************************************\n" +
                "Client Details: " + firstName + " " + lastName + " | " +
                "Amount of purchases = " + purchases.size() + " | " +
                "Total cost of purchases = $" + finalCostOfPurchases + "\n";

    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void addProductToCart(String productName, double price , int amount) {
        this.purchases.add(new Purchase(productName, price, amount));
        this.finalCostOfPurchases += price;
    }

    public double getFinalCostOfPurchases() {
        return finalCostOfPurchases;
    }

    public void shoppingCartStatus() {
        double payment = 0;
        for (Purchase currentPurchase : this.purchases) {
            payment += currentPurchase.getPrice() * currentPurchase.getAmountOfProduct() ;
            System.out.println(currentPurchase.getProductName() + "-  $" + currentPurchase.getPrice());
        }
        this.finalCostOfPurchases = payment;
        System.out.println("[PAYMENT: $" + finalCostOfPurchases+"]");
    }



    public String nameIsValid() {
        Scanner scanner = new Scanner(System.in);
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        boolean contains = false;
        String nameToCheck = null;
        do {
            contains = false;
            System.out.println("> ");
            nameToCheck = scanner.nextLine();
            for (int i = 0; i < array.length; i++) {
                if (nameToCheck.contains(array[i])) {
                    System.out.println("You need to enter name without numbers!");
                    contains = true;
                    nameToCheck = null;
                    break;
                }
            }
        } while (contains);

        return nameToCheck;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

}




