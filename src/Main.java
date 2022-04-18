import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        System.out.println("hi wasim");
        int userChoice;
        do{
            System.out.println("Choose  your action");
            System.out.println("1 - Sign up");
            System.out.println("2 - Sign in");
            System.out.println("3 - Exit");
            userChoice=scanner.nextInt();
            switch (userChoice) {
                case 1:
                    store.createUser();
                break;
                case 2:
                    break;
                case 3:
                    break;
            }
        } while (userChoice !=3);

    }
}