

import java.util.LinkedList;
import java.util.Scanner;

public class Store {

    private LinkedList<Client> users;
    private LinkedList<Product> products;


    public Store() {
        this.users = new LinkedList<>();
        this.products =new LinkedList<>();
        //for test
        users.add(new Worker("Wasim", "shhab", "was", "123456", true, true, WorkerProperty.RegularWorker));
        Client seham = new Client("seham", "shhab", "se", "123456", false, false);
        Client abrar = new Client("abrar", "shhab", "abraer", "123456", true, false);
        users.add(seham);
        users.add(abrar);
        // add product to seham cart
        seham.addProductToCart("soda", 3.5);
        abrar.addProductToCart("coca- cola", 6);


    }



    public void createUser () {
        Scanner scanner = new Scanner(System.in);
        Scanner in =new Scanner (System.in);
        System.out.println("What type of user you are?");
        int type;
        do {
            System.out.println("1 - for a Client \n2 - for a Worker");
            type = in.nextInt();
        } while (type != Deff.CLIENT && type!=Deff.WORKER);

        switch (type) {

            case Deff.CLIENT:
                Client newClient = createUserGeneral();
                newClient.setWorker(false);
                this.users.add(newClient);
                System.out.println("account has been created successfully");
                break;

            case Deff.WORKER:
                Client newClientWorker =  createUserGeneral();
                newClientWorker.setWorker(true);
                int workerDegree;
                WorkerProperty workerProperty = WorkerProperty.None;
                do{
                    System.out.println("What is your degree?");
                    System.out.println("1-regular worker \n2-management \n3-member of the management team");
                    workerDegree = in.nextInt();
                    switch (workerDegree){
                        case Deff.REGULAR_WORKER:
                            workerProperty=WorkerProperty.RegularWorker;
                            break;
                        case Deff.MANAGEMENT:
                            workerProperty=WorkerProperty.Management;
                            break;
                        case Deff.MEMBER_OF_MANAGEMENT_TEAM:
                            workerProperty=WorkerProperty.MemberOfTheManagementTeam;
                            break;}

                } while (workerDegree != Deff.REGULAR_WORKER && workerDegree != Deff.MANAGEMENT && workerDegree != Deff.MEMBER_OF_MANAGEMENT_TEAM);
                Worker newWorker = new Worker(newClientWorker, workerProperty);
//                Worker newWorker = new Worker(newClientWorker.getFirstName(), newClientWorker.getLastName(), newClientWorker.getUserName(), newClientWorker.getPassword(), newClientWorker.isMember(),newClientWorker.isWorker() ,workerProperty);
                this.users.add(newWorker);
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
        Client currentAccountLogin = doseAccountExist();

        if (currentAccountLogin != null) {
            switch (clientOrWorkerAccount) {
                case Deff.CLIENT:
                    System.out.println(currentAccountLogin.introduce());
                    buyingInProcess(currentAccountLogin);
                    break;

                case Deff.WORKER:
                    System.out.println(currentAccountLogin.introduce());
                    int workerChoice = 5;
                    boolean backToMainMenu = false;
//                    do {
                        workersMenu();
//                        workerChoice = scanner.nextInt();

                        switch (workerChoice) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                printingSpecificClients(workerChoice);
                                break;
                            case 5:
                                addProductToTheStore();
                                for (Product currentProduct : products) {
                                    System.out.println(currentProduct.toString());
                                }
                                break;
                            case 6:


                                break;
                            case 7:
                            case 8:
                                backToMainMenu = true;
                                break;
                            default:
                                System.out.println("invalid choice");
                                break;
                        }

//                    } while (!backToMainMenu);
                    break;

            }


        } else {
            System.out.println("no such account!!\n\n\n");
        }
    }

    private void addProductToTheStore() {
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);

        System.out.println("\t fill Product Details");
        System.out.println("Product Name:");
        String productName = scanner.nextLine();


        int productNumber = 0;
        boolean allowBarcode;
        do {
            System.out.println("barcode/Product number (maximum - 2 Digits):");
            productNumber = in.nextInt();
            allowBarcode = 0 < productNumber && productNumber <= 99;
            if (allowBarcode) {
                if (doseBarcodeCanBeUsed(productNumber)) {
                    allowBarcode = false;
                    System.out.println("Choose a different barcode:");
                }
            } else {

            }
        } while (!allowBarcode);


        System.out.println("quantity:");
        int amount = -1;
        boolean normalQuantity;
        do {
            amount = in.nextInt();
            normalQuantity = amount > 0;
            if (!normalQuantity)
                System.out.println("amount cannot be negative! , enter a positive amount:");
        } while (!normalQuantity);

        System.out.println("Product price");
        double productPrice = 0;
        boolean normalPrice;
        do {
            productPrice = in.nextDouble();
            normalPrice = productPrice > 0;
            if (!normalPrice)
                System.out.println("price cannot be negative! , enter a positive price:");
        } while (!normalPrice);


        System.out.println("Percentage discount that club members will receive (discount can be between 0 - 70)");
        int discount = 0;
        boolean normalDiscount;
        do {
            discount = in.nextInt();
            normalDiscount = 0 <= discount && discount <= 70;
            if (!normalDiscount)
                System.out.println("Invalid discount!!\ndiscount can be between 0 & 70 ");

        } while (!normalDiscount);
        products.add(new Product(amount, productPrice, true, productNumber, productName));
    }

    private boolean doseBarcodeCanBeUsed(int barcodeToCheck) {
        boolean barcodeTaken = false;
        for (Product currentProduct : products) {
            if (currentProduct.getNumOfProduct() == barcodeToCheck) {
                barcodeTaken = true;
                System.out.println(currentProduct.getProductName() + " have the same barcode !!");
                break;
            }
        }
        return barcodeTaken;
    }
    private  void printingSpecificClients(int specificCategory) {
        switch (specificCategory) {
            case 1:
                System.out.println("\t\t[Clients]");
                for (Client currentClient : users)
                    System.out.println(currentClient.toString());
                break;
            case 2:
                System.out.println("\t\t[Club members]");
                for (Client currentClient : users)
                    if (currentClient.isMember())
                        System.out.println(currentClient.toString());
                break;
            case 3:
                System.out.println("\t\t[Client have made purchases]");
                boolean clientHaveMadePurchase = false;
                for (Client currentClient : users) {
                    if (currentClient.getPurchases().size() > 0) {
                        clientHaveMadePurchase = true;
                        System.out.println(currentClient.toString());
                    }
                }

                if (!clientHaveMadePurchase) {
                    System.out.println("no one have made purchase");

                }
                break;
            case 4:
                System.out.println("\t\t[Beloved Client <3]");
                System.out.println(users.get(belovedClient()).toString());
                break;
        }
    }

    private int belovedClient() {
        double extremelyHighAmount = 0;
        int belovedClientIndex = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getFinalCostOfPurchases() > extremelyHighAmount) {
                belovedClientIndex = i;
                extremelyHighAmount = users.get(belovedClientIndex).getFinalCostOfPurchases();
            }
        }
        return belovedClientIndex;
    }

    private void workersMenu() {
        System.out.println("____________________________________________________________________");
        System.out.println("\t\tactions:");
        System.out.println("1 - show Clients\n" + "" +
                "2 - show Club member Clients\n" + "" +
                "3 - show Client have made at least one purchase\n" + "" +
                "4 - show Client whose Purchases amount is the highest\n" + "" +
                "5 - add Product to the Store\n" + "" +
                "6 - update inventory status for a product\n" + "" +
                "7 - property" + "\n" +
                "8 - Exit");
        System.out.println("____________________________________________________________________\n\n");
    }

    private void buyingInProcess(Client currentClientLogin) {
        Scanner scanner = new Scanner(System.in);
        boolean buyingInProcess = true;
        while (buyingInProcess) {
            System.out.println("Enter product number you would buy\nIf you want to complete the purchase enter -1");
            showProducts();
            int selectedProductNumber = scanner.nextInt();
            buyingInProcess = selectedProductNumber != -1;
            if (buyingInProcess) {
                Product userProduct = doseProductExist(selectedProductNumber);
                if (userProduct!=null) {
//                    should know what is the product index, to take the name and the price and add it to the client cart
//                    if we have amount in the stock
                    int quantityUserNeeds = 0;

                    boolean normalQuantityOfProduct;
                    do {
                        System.out.println("Select quantity: ");
                        quantityUserNeeds = scanner.nextInt();
                        normalQuantityOfProduct = 0 < quantityUserNeeds && quantityUserNeeds <= userProduct.getAmount();

                        if (quantityUserNeeds > userProduct.getAmount()) {
                            System.out.println("Currently there are [" + userProduct.getAmount() + "] in stock of the product you have selected");
                        } else if (quantityUserNeeds < 0)
                            System.out.println("!!invalid Quantity ");

                    } while (!normalQuantityOfProduct);
                    // here we know that we have enough amount so we will add the product and the price
                    currentClientLogin.addProductToCart(userProduct.getProductName() , userProduct.getPrice());
                    // show the client his current shopping cart status, and the price he should pay until now.
                    currentClientLogin.shoppingCartStatus();


                    // after each purchase maybe**   the shopping cart should be reset for the next shopping



                } else {
                    // for shirel if you wanna try to imagine that we have products our store just put this else and his


                    //invalid productNumber
                    System.out.println("no such product");
                }
            } else {
                //purchase completed. nice to meet u
                try {
                    // This function collapses the program as long as there are no products
                    currentClientLogin.finalCostOfPurchases();

                } catch (Exception e) {
                    e.printStackTrace();

                }
                System.out.println("purchase Completed.....................bye bye :)");

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

            //the old login Function
//    public Client login2 () {
//            Scanner scanner = new Scanner(System.in);
//            Scanner in = new Scanner(System.in);
//            Client found = null;
//            System.out.println("Which account do you want to login?");
//            System.out.println("1 - for a Client \n2 - for a Worker");
//            int clientOrWorker=in.nextInt();
//            System.out.println("Enter your username");
//            String username = scanner.nextLine();
//            System.out.println("Enter your password");
//            String password = scanner.nextLine();
//            for (Client currentUser : this.users) {
//                if (currentUser.getUserName().equals(username) && currentUser.getPassword().equals(password)) {
//                    found = currentUser;
//                    switch (clientOrWorker){
//                        case Deff.CLIENT:
//                            System.out.println(found.toString());
//                            showProducts();
//                            int userChoice;
//                            do {
//                                System.out.println("Which product do you want, If you want to complete the purchase, enter -1 ");
//                                userChoice = in.nextInt();
//                                int amountProduct;
//                                do {
//                                    System.out.println("How much products do you want?");
//                                    amountProduct = in.nextInt();
//                                } while (amountProduct <= 0 && amountProduct >= amountOfProduct(amountProduct));
//
//                            } while (doseProductExist(userChoice) || userChoice == -1);
//                            break;
//
//                        case Deff.WORKER:
//                            System.out.println(found.toString());
//
//                            break;
//                    }
//
//
//                } else {
//                    System.out.println("account not fount");
//                }
//            }
//
//
//
//
//
//            return found;
//        }

    private void showProducts() {
        System.out.println("Products:");
        for (Product currentProduct : this.products){
            if (currentProduct.isExist()){
                System.out.println("Product number: "+currentProduct.getNumOfProduct()+"\tProduct Name: "+currentProduct.getProductName());
            }
        }
    }

    private Product doseProductExist(int productNumber) {
        boolean productExist = false;
        Product productForUser = null;
        for (Product currentProduct : this.products) {
            if (currentProduct.getNumOfProduct() == productNumber) {
                productForUser = currentProduct;
                break;
            }
        }
        return productForUser;
    }

}
