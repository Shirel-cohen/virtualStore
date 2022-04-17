
import javafx.concurrent.Worker;

import java.util.LinkedList;
import java.util.Scanner;

public class Store {
    private LinkedList<User> users;
    private LinkedList<Client> clients;
    private LinkedList<Worker> workers;
    private LinkedList<Product> products;

    public Store() {
        this.users = new LinkedList<>();
        this.clients = new LinkedList<>();
        this.workers =new LinkedList<>();
        this.products =new LinkedList<>();
    }


    public User createUserGeneral() {
        Scanner scanner = new Scanner(System.in);
            User newUser = null;
            System.out.println("Enter your firstName");
            newUser.setFirstName(newUser.nameIsValid());
            System.out.println("Enter your lastName");
            newUser.setLastName(newUser.nameIsValid());
            boolean usernameTaken = false;
            String username = null;
            do {
                System.out.println("Enter username:");
                username = scanner.nextLine();
                usernameTaken = this.doesUserNameExist(username);
            } while (usernameTaken);
            newUser.setUserName(username);
            boolean strongPassword = false;
            String password = null;
            do {
                System.out.println("Enter a strong password: ");
                password = scanner.nextLine();
                strongPassword = this.checkIfStrongPassword(password);
            } while (!strongPassword);
            newUser.setPassword(password);
            return newUser;
        }

        public void createUser () {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which type of user are you?");
            int type;
            do {
                System.out.println("1 - for a Client \n 2 - for a Worker");
                type = scanner.nextInt();
            } while (type != 2 && type!=1);
            switch (type) {
                case 1:
                    User newUser = this.createUserGeneral();
                    System.out.println("Are you a member?");
                    String answer = scanner.nextLine();
                    boolean member = false;
                    if (answer.equals("Yes")) {
                        member = true;
                    }
                    Client newClient = new Client(newUser.getFirstName(), newUser.getLastName(), newUser.getUserName(), newUser.getPassword(), member);
                    this.clients.add(newClient);
                    System.out.println("Client was added!");
                    break;
                case 2:
                    this.createUserGeneral();
                    boolean setWorker = true;
                    int workerDegree;
                    do {
                        System.out.println("What is your degree?");
                        System.out.println("1-regular worker \n 2-management \n 3-member of the management team");
                        workerDegree = scanner.nextInt();
                    } while (workerDegree != 2);
                    break;
            }

        }


        private boolean doesUserNameExist (String usernameToCheck){
            boolean exits = false;
            for (User currentUser : this.users) {
                if (currentUser.getUserName().equals(usernameToCheck)) {
                    exits = true;
                    break;
                }
                User newUser = new User(usernameToCheck);
                this.users.add(newUser);
            }
            return exits;
        }
        private boolean checkIfStrongPassword (String password){
            boolean strong = false;
            if (password.length() >= 6) {
                strong = true;
            }
            return strong;
        }

        public User login () {
            User found = null;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username");
            String username = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            for (User currentUser : this.users) {
                if (currentUser.getUserName().equals(username) && currentUser.getPassword().equals(password)) {
                    found = currentUser;
                    break;
                }
            }
            return found;
        }
    }
