
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Introduce {


    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean isMember;
    private boolean isWorker;
    private double currentPropertyCost;
    private ArrayList<Purchase> purchases;
    private int sumOfPurchases;
    private double totalCostOfAllPurchase;
    private LocalDate dateOfLastPurchase;


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
        this.purchases = new ArrayList<>(); // for the client we create them in process they should have Porches cart
    }

    public String nameIsValid() {
        Scanner scanner = new Scanner(System.in);
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        boolean contains;
        String nameToCheck;
        do {
            contains = false;
            System.out.println("> ");
            nameToCheck = scanner.nextLine();
            for (String num : array) {
                if (nameToCheck.contains(num)) {
                    System.out.println("You need to enter name without numbers!");
                    contains = true;
                    nameToCheck = null;
                    break;
                }
            }
        } while (contains);

        return nameToCheck;
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
                    "Client Details: Name = " + firstName + " " + lastName + " | CLUB MEMBER | " +
                    "Amount of purchases = " + sumOfPurchases + " | " +
                    "Total cost of purchases = $" + totalCostOfAllPurchase + " | Last purchase date = " + dateOfLastPurchase;
        }
        return "**************************************\n" +
                "Client Details: Name = " + firstName + " " + lastName + " | " +
                "Amount of purchases = " + sumOfPurchases + " | " +
                "Total cost of purchases = $" + totalCostOfAllPurchase + " | Last purchase date = " + dateOfLastPurchase;



    }

    public void shoppingCartStatus() {
        System.out.println("\t\t{Currently shopping cart}:");
        double payment = 0;
        if (!isMember && !isWorker) {
            // not member(false) && notWorker(false) == false
            for (Purchase currentPurchase : this.purchases) {
                payment += currentPurchase.getPrice() * currentPurchase.getAmountOfProduct();
                System.out.println("\t\t\t" + currentPurchase.getAmountOfProduct() + "-" + currentPurchase.getProductName() + " Cost per unit: $" + currentPurchase.getPrice());
            }
        } else {
            int discount;
            double cost;
            double saved;
            for (Purchase currentPurchase : this.purchases) {
                discount = currentPurchase.getDiscount();
                cost = currentPurchase.getPrice();
                saved = (cost / Deff.HUNDRED) * discount;
                payment += (cost - saved) * currentPurchase.getAmountOfProduct();
                System.out.println("\t\t\t" + currentPurchase.getAmountOfProduct() + "-" + currentPurchase.getProductName() + " Cost per unit: $" + currentPurchase.getPrice());
            }




        }
        currentPropertyCost = payment;
//        totalCostOfAllPurchase += currentPropertyCost; 426 in store Class here we Update the total cost of purchases
        System.out.println("\t\t\t\t\t[PAYMENT: $" + (float)currentPropertyCost+"]\n________________________________________________________________________");
    }



    public void addProductToCart(String productName, double price , int amount , int discount) {
        this.purchases.add(new Purchase(productName, price, amount, discount));
//        this.finalCostOfPurchases += price; // that's why we have wrong final amount to pay when the client complete the purchase.
    }

    public void setTotalCostOfAllPurchase(double totalCostOfAllPurchase) {
        this.totalCostOfAllPurchase = totalCostOfAllPurchase;
    }

    public void setCurrentPropertyCost(double currentPropertyCost) {
        this.currentPropertyCost = currentPropertyCost;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setSumOfPurchases(int sumOfPurchases) {
        this.sumOfPurchases = sumOfPurchases;
    }

    public void setDateOfLastPurchase(LocalDate dateOfLastPurchase) {
        this.dateOfLastPurchase = dateOfLastPurchase;
    }

    public int getSumOfPurchases() {
        return sumOfPurchases;
    }

    public double getTotalCostOfAllPurchase() {
        return totalCostOfAllPurchase;
    }



    public double getCurrentPropertyCost() {
        return currentPropertyCost;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
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




