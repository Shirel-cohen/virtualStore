
public class Worker extends Client  {
    private WorkerDegree degree;

    public Worker(Client other, WorkerDegree property) {
        super(other.getFirstName(), other.getLastName(), other.getUserName(), other.getPassword(), other.isMember(), other.isWorker());
        this.degree = property;
    }

    public Worker(String firstName, String lastName, String userName, String password, boolean isMember, boolean isWorker, WorkerDegree degree) {
        super(firstName, lastName, userName, password, isMember, isWorker);
        this.degree = degree;
    }

    public void shoppingCartStatus() {
        int discount;
        double cost;
        double saved;

        System.out.println("\t\t{Currently shopping cart}:");
        double payment = 0;
        if (!this.isMember()) {
                                                                                // worker && not member == worker
            System.out.println("i am not member - i am just worker");
            for (Purchase currentPurchase : this.getPurchases()) {
                payment += currentPurchase.getPrice() * currentPurchase.getAmountOfProduct();
                System.out.println("\t\t\t" + currentPurchase.getAmountOfProduct() + "-" + currentPurchase.getProductName() + " Cost per unit: $" + currentPurchase.getPrice());
            }
        } else {
            System.out.println(" super Discount. because i'm member and worker");
            for (Purchase currentPurchase : this.getPurchases()) {
                cost = currentPurchase.getPrice();
                discount = currentPurchase.getDiscount();
                saved = (cost / Deff.HUNDRED) * discount;
                payment += (cost - saved) * currentPurchase.getAmountOfProduct();
                System.out.println("\t\t\t" + currentPurchase.getAmountOfProduct() + "-" + currentPurchase.getProductName() + " Cost per unit: $" + currentPurchase.getPrice());
            }
            // true
        }
        setCurrentPropertyCost(payment);

        switch (this.degree) {
            case RegularWorker:
                setCurrentPropertyCost( getCurrentPropertyCost() - (getCurrentPropertyCost() / Deff.HUNDRED) * 10);
                break;
            case Management:
                setCurrentPropertyCost( getCurrentPropertyCost() - (getCurrentPropertyCost() / Deff.HUNDRED) * 20);
                break;
            case MemberOfTheManagementTeam:
                setCurrentPropertyCost(getCurrentPropertyCost() - (getCurrentPropertyCost() / Deff.HUNDRED) * 30);
                break;
        }
        System.out.println("\t\t\t\t\t[PAYMENT: $" + (float)getCurrentPropertyCost()+"]\n________________________________________________________________________");
    }

    @Override
    public String introduce() {
        return "Hello " + this.getFirstName() + " " + this.getLastName() + " [" + this.degree + ']';
    }
}