

import java.util.LinkedList;
import java.util.Scanner;

public class Store {

    private LinkedList<Client> users;
  //  private LinkedList<Worker> workers;
    private LinkedList<Product> products;

    public Store() {

        this.users = new LinkedList<>();
       // this.workers =new LinkedList<>();
        this.products =new LinkedList<>();
    }

    public Client createUserGeneral() {
        Scanner scanner = new Scanner(System.in);
        Client newClient = new Client();
        System.out.println("Enter your firstName");
        newClient.setFirstName(newClient.nameIsValid());
        System.out.println("Enter your lastName");
        newClient.setLastName(newClient.nameIsValid());
        boolean usernameTaken = false;
        String username = null;
            do {
                System.out.println("Enter username:");
                username = scanner.nextLine();
                usernameTaken = this.doesUserNameExist(username);
            } while (usernameTaken);
            newClient.setUserName(username);
            boolean strongPassword = false;
            String password = null;
            do {
                System.out.println("Enter a strong password: ");
                password = scanner.nextLine();
                strongPassword = this.checkIfStrongPassword(password);
            } while (!strongPassword);
            newClient.setPassword(password);
        System.out.println("Are you a member?");
        String answer = scanner.nextLine();
        boolean member = false;
        if (answer.equals("Yes")) {
            member = true;
        }
        newClient.setMember(member);
            return newClient;
        }

        public void createUser () {
            Scanner scanner = new Scanner(System.in);
            Scanner in =new Scanner (System.in);
            System.out.println("What type of user are you?");
            int type;
            do {
                System.out.println("1 - for a Client \n2 - for a Worker");
                type = in.nextInt();
            } while (type != 2 && type!=1);
            switch (type) {
                case 1:
                    Client newClient = createUserGeneral();
                    newClient.setWorker(false);
                    this.users.add(newClient);

                    System.out.println("Client was added!");
                    break;
                case 2:
                    Client newClientWorker =  createUserGeneral();
                    newClientWorker.setWorker(true);
                   // boolean setWorker = true;
                    int workerDegree;
                    WorkerProperty workerProperty = WorkerProperty.None;
                    do{
                        System.out.println("What is your degree?");
                        System.out.println("1-regular worker \n2-management \n3-member of the management team");
                        workerDegree = in.nextInt();

                            switch (workerDegree){
                                case 1:
                                    workerProperty=WorkerProperty.RegularWorker;
                                    break;
                                case 2:
                                    workerProperty=WorkerProperty.Management;
                                    break;
                                case 3:
                                    workerProperty=WorkerProperty.MemberOfTheManagementTeam;
                                    break;}


                    } while (workerDegree != 1 && workerDegree != 2 && workerDegree != 3);
            Worker newWorker = new Worker(newClientWorker.getFirstName(), newClientWorker.getLastName(), newClientWorker.getUserName(), newClientWorker.getPassword(), newClientWorker.isMember(),newClientWorker.isWorker() ,workerProperty);
            users.add(newWorker);
            }
        }


        private boolean doesUserNameExist (String usernameToCheck){
            boolean exits = false;
            for (Client currentUser : this.users) {
                if (currentUser.getUserName().equals(usernameToCheck)) {
                    exits = true;
                    break;
                }
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
        private int amountOfProduct(int numOfProduct)  {
        boolean exist =false;
        for (Product currentProduct:this.products)    {
            if(currentProduct.getNumOfProduct()==numOfProduct) {
                exist=true;
                break;
            }
        }
            // return (exist? );
        }
        public Client login () {
            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(System.in);
            Client found = null;
            System.out.println("Which account do you want to login?");
            System.out.println("1 - for a Client \n2 - for a Worker");
            int userChoice=in.nextInt();
            System.out.println("Enter your username");
            String username = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            for (Client currentUser : this.users) {
                if (currentUser.getUserName().equals(username) && currentUser.getPassword().equals(password)) {
                    found = currentUser;
                    switch (userChoice){
                        case 1:
                            System.out.println(currentUser.toString());
                            System.out.println("The products in the store are in stock:");
                            for (Product currentProduct : this.products){
                                if (currentProduct.isExist()){
                                    System.out.println("Product number "+currentProduct.getNumOfProduct()+" : "+currentProduct.getProductName());
                                }
                            }
                            do{
                                System.out.println("Which product do you want, If you want to complete the purchase, enter -1 ");
                                int userChoiceOfProduct=in.nextInt();

                                int amountProduct;
                                do{
                                    System.out.println("How much products do you want?");
                                     amountProduct=in.nextInt();
                                } while (amountProduct>0 && amountProduct<= || userChoiceOfProduct==-1);

                            } while (!products.contains(userChoice));

                            break;
                        case 2:
                            break;
                    }
                    break;
                }
            }
            return found;
        }
    }
