import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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