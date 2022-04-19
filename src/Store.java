

import java.util.LinkedList;
import java.util.Scanner;

public class Store {

    private LinkedList<Client> users;
    private LinkedList<Product> products;


    public Store() {
        this.users = new LinkedList<>();
        this.products =new LinkedList<>();
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

        if (answer.toLowerCase().equals("yes")) {
            member = true;
        }
        newClient.setMember(member);
            return newClient;
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
        boolean enoughAmount =false;
        int amount = 0;
        for (Product currentProduct :this.products)    {
            if(currentProduct.getNumOfProduct() == numOfProduct) {
                enoughAmount = true;
                amount = currentProduct.getAmount();
                break;
            }
        }
        return enoughAmount ? amount : 0;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        int clientOrWorkerAccount = loginToClientOrWorker();
        Client currentClientLogin = doseAccountExist();

        if (currentClientLogin != null) {
            switch (clientOrWorkerAccount) {
                case Deff.CLIENT:
                    System.out.println(currentClientLogin.toString());
                    buyingInProcess();
                    break;














                case Deff.WORKER:
                    break;

            }


        } else {
            System.out.println("no such account!!\n\n\n");
        }




    }

    private void buyingInProcess() {
        Scanner scanner = new Scanner(System.in);
        boolean buyingInProcess = true;
        while (buyingInProcess) {
            System.out.println("Enter product number you would buy\nIf you want to complete the purchase enter -1");
            showProducts();
            int productSelected = scanner.nextInt();
            buyingInProcess = productSelected != -1;
            if (buyingInProcess) {
                int quantityUserNeeds = 0;
                do {
                    System.out.println("Select quantity: ");
                    quantityUserNeeds = scanner.nextInt();
                    if (quantityUserNeeds > amountOfProduct(quantityUserNeeds)) {
                        System.out.println("The quantity you selected is not in stock!");
                        System.out.println("Currently there are" + amountOfProduct(quantityUserNeeds) + " in stock of the product you have selected");
                    }
                } while (quantityUserNeeds < 0 && quantityUserNeeds > amountOfProduct(quantityUserNeeds));

            } else {
                //purchase completed. nice to meet u
                System.out.println("purchase Completed.....................bye bye . :)");

            }



        }

    }


    private  int loginToClientOrWorker() {
        Scanner in = new Scanner(System.in);
        int accountType;
        boolean correctSelect;
        System.out.println("Which account do you want to login?.");
        do {
            System.out.println("1 - Client \n2 - Worker.");
            System.out.println("->");
            accountType = in.nextInt();
            correctSelect = accountType > 0 && accountType <= 2;
        } while (!correctSelect);

        return accountType;
    }



    private Client doseAccountExist() {
        Client clientLogin = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("FILL DETAILS");
        System.out.println("userName:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        for (Client currentAccount : this.users) {
            if (username.equals(currentAccount.getUserName()) && password.equals(currentAccount.getPassword())) {
                clientLogin = currentAccount;
                break;
            }
        }
        return clientLogin;
    }


    public Client login2 () {
            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(System.in);
            Client found = null;
            System.out.println("Which account do you want to login?");
            System.out.println("1 - for a Client \n2 - for a Worker");
            int clientOrWorker=in.nextInt();
            System.out.println("Enter your username");
            String username = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            for (Client currentUser : this.users) {
                if (currentUser.getUserName().equals(username) && currentUser.getPassword().equals(password)) {
                    found = currentUser;
                    switch (clientOrWorker){
                        case Deff.CLIENT:
                            System.out.println(found.toString());
                            showProducts();
                            int userChoice;
                            do {
                                System.out.println("Which product do you want, If you want to complete the purchase, enter -1 ");
                                userChoice = in.nextInt();
                                int amountProduct;
                                do {
                                    System.out.println("How much products do you want?");
                                    amountProduct = in.nextInt();
                                } while (amountProduct <= 0 && amountProduct >= amountOfProduct(amountProduct));

                            } while (doseProductExist(userChoice) || userChoice == -1);
                            break;

                        case Deff.WORKER:
                            System.out.println(found.toString());

                            break;
                    }


                } else {
                    System.out.println("account not fount");
                }
            }





            return found;
        }


    private void showProducts() {
        System.out.println("Products:");
        for (Product currentProduct : this.products){
            if (currentProduct.isExist()){
                System.out.println("Product number: "+currentProduct.getNumOfProduct()+"\tProduct Name: "+currentProduct.getProductName());
            }
        }
    }

    private boolean doseProductExist(int productNumber) {
        boolean productExist = false;
        for (Product currentProduct : this.products) {
            if (currentProduct.getNumOfProduct() == productNumber) {
                productExist = true;
                break;
            }
        }
        return productExist;
    }
}
