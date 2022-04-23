import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        double cost;
//        int discount;
//        int hundred = 100;
//        do {
//            System.out.println("cost:");
//            cost = scanner.nextInt();
//            System.out.println("discount");
//            discount = scanner.nextInt();
//            System.out.println(cost / hundred);
//            double calc = cost / hundred;
//            double result = calc * discount;
//            System.out.println("Payment =: "+ (cost-result) +" You saved " + result + " because you are a Club Member" );
//        } while (cost != -1);


        Store store = new Store();
        int userChoice;
        do {
            System.out.println("OPTIONS");
            System.out.println("1 - Sign up");
            System.out.println("2 - Sign in");
            System.out.println("3 - Exit");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case Deff.SIGN_UP:
                    store.createUser();
                    break;
                case Deff.SIGN_IN:
                    store.login();
                    break;
                case Deff.EXIT:
                    break;
            }
        } while (userChoice != Deff.EXIT);

    }
}