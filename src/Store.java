import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Store {

    private  LinkedList<Client> users;
    private  LinkedList<Product> products;

    public Store() {
        this.users = new LinkedList<>();
        this.products =new LinkedList<>();
        users.add(new Worker("wasim", "shhab", "was", "123456", true, true, WorkerDegree.Management));
    }

    public void createUser () {
        Scanner in =new Scanner (System.in);
        System.out.println("What type of user you are?");
        int type;
        do {
            System.out.println("1 - Client \n2 - Worker");
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
                int userWorkerDegree;
                WorkerDegree workerDegree = WorkerDegree.None;
                do{
                    System.out.println("What is your degree?");
                    System.out.println("1-regular worker \n2-management \n3-member of the management team");
                    userWorkerDegree = in.nextInt();
                    switch (userWorkerDegree){
                        case Deff.REGULAR_WORKER:
                            workerDegree=WorkerDegree.RegularWorker;
                            break;
                        case Deff.MANAGEMENT:
                            workerDegree=WorkerDegree.Management;
                            break;
                        case Deff.MEMBER_OF_MANAGEMENT_TEAM:
                            workerDegree=WorkerDegree.MemberOfTheManagementTeam;
                            break;}

                } while (userWorkerDegree != Deff.REGULAR_WORKER && userWorkerDegree != Deff.MANAGEMENT && userWorkerDegree != Deff.MEMBER_OF_MANAGEMENT_TEAM);
                Worker newWorker = new Worker(newClientWorker, workerDegree);
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
        boolean usernameTaken;
        String username;
        do {
            System.out.println("Enter username:");
            username = scanner.nextLine();
            usernameTaken = this.doesUserNameExist(username);
            if (usernameTaken)
                System.out.println("username taken, choose different one");
        } while (usernameTaken);
        newClient.setUserName(username);
        boolean strongPassword;
        String password;
        do {
            System.out.println("Enter a strong password: ");
            password = scanner.nextLine();
            strongPassword = this.checkIfStrongPassword(password);
            if (!strongPassword)
                System.out.println("Password should be bigger than 5 characters");
        } while (!strongPassword);
        newClient.setPassword(password);
        System.out.println("Are you a member?\t\t[YES/NO]");
        String answer = scanner.nextLine();
        newClient.setMember(answer.equalsIgnoreCase("yes"));
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
        return password.length() >= 6;
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
                    boolean backToMainMenu = false;
                    do {
                        workersMenu();
                        int workerChoice = scanner.nextInt();

                        switch (workerChoice) {
                            case Deff.PRINT_COSTUMERS:
                            case Deff.PRINT_VIP_COSTUMERS:
                            case Deff.PRINT_COSTUMERS_WHO_MADE_PURCHASE:
                            case Deff.PRINT_COSTUMER_WITH_HIGHEST_PURCHASE:
                                printingSpecificClients(workerChoice);
                                break;
                            case Deff.ADD_NEW_PRODUCT:
                                addProductToTheStore();
                                for (Product currentProduct : products) {
                                    System.out.println(currentProduct.toString());
                                }
                                break;
                            case Deff.CHANGING_PRODUCT_STATUS:
                                changeStatusOfProduct();
                                break;

                            case Deff.MAKE_PURCHASE:
                                buyingInProcess(currentAccountLogin);
                                break;
                            case Deff.LOG_OUT:
                                backToMainMenu = true;
                                break;
                            default:
                                System.out.println("invalid choice");
                                break;
                        }

                    } while (!backToMainMenu);
                    break;

            }


        } else {
            System.out.println("no such account!!\n\n\n");
        }
    }
    private void changeStatusOfProduct(){
        Scanner in = new Scanner(System.in);
        int quantityToUpdate,productNum;
        boolean finishUpdate = false;

        do {
            showAllProducts();
            System.out.println("Select product Number you want to update?\n[-1] to finish updates");
            productNum = in.nextInt();
            if (productNum == -1) {
                finishUpdate = true;
            } else {
                Product productToUpdate = doseProductExist(productNum);
                if (productToUpdate != null) {
                    System.out.println("What quantity do you want to update for the product?");
                    quantityToUpdate = in.nextInt();
                    boolean normalAmount = quantityToUpdate >= 0;
                    while (!normalAmount) {
                        System.out.println("invalid amount!\n amount should be positive");
                        quantityToUpdate = in.nextInt();
                        normalAmount = quantityToUpdate >= 0;
                    }
                    productToUpdate.setAmountOfProduct(quantityToUpdate);  //it's the same logic of the if-else in line 204
                    productToUpdate.setExist(quantityToUpdate > 0);
                } else {System.out.println("no such product");}
            }
        } while (!finishUpdate);

    }

    private void showAllProducts() {
        System.out.println("Products:");
        for (Product currentProduct : this.products){
            System.out.println(currentProduct.toString());
        }
    }

    private Product productNameExist(String nameToCheck) {
        Product product = null;

        for (Product currentProduct : this.products) {
            if (currentProduct.getProductName().equals(nameToCheck)) {
                System.out.println("There is a product with that name.");
                product = currentProduct;
                break;
            }
        }
        return product;
    }

    private void addProductToTheStore() {
        boolean continueToUpdate = true;
        boolean exit;
        do {
            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(System.in);
            int updateORCreate = Deff.CREATE;
            System.out.println("\t fill Product Details\t\t[1]-Continue | [-1]-Exit");
            exit = in.nextInt() == -1;
            if (!exit) {
                System.out.println("Product Name:");
                String productName = scanner.nextLine();
                Product productSelected = productNameExist(productName);
                if (productSelected != null) {
                    updateORCreate = Deff.UPDATE;
                }
                if (updateORCreate == Deff.UPDATE) {
                    System.out.println("u can update it :\n\n\t[Product Status]\n" + productSelected + "\t\t[2]-update | [-2]-Do not update");
                    continueToUpdate = in.nextInt() == 2;
                }
                if (continueToUpdate) {

                    int productNumber;
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
                        }
                    } while (!allowBarcode);
                    System.out.println("Product price");
                    double productPrice;
                    boolean normalPrice;
                    do {
                        productPrice = in.nextDouble();
                        normalPrice = productPrice > 0;
                        if (!normalPrice)
                            System.out.println("price cannot be negative! , enter a positive price:");
                    } while (!normalPrice);
                    System.out.println("Percentage discount that club members will receive (discount can be between 0 - 70)");
                    int discount;
                    boolean normalDiscount;
                    do {
                        discount = in.nextInt();
                        normalDiscount = 0 <= discount && discount <= 70;
                        if (!normalDiscount)
                            System.out.println("Invalid discount!!\ndiscount can be between 0 & 70 ");


                    } while (!normalDiscount);
                    int quantityToUpdate = 0;
                    if (productSelected != null) {
                        System.out.println("Amount:");
                        quantityToUpdate = in.nextInt();
                        boolean normalAmount = quantityToUpdate >= 0;
                        while (!normalAmount) {
                            System.out.println("invalid amount!\n amount should be positive");
                            quantityToUpdate = in.nextInt();
                            normalAmount = quantityToUpdate >= 0;
                        }
                        productSelected.setProductName(productName);
                        productSelected.setProductNumber(productNumber);
                        productSelected.setPrice(productPrice);
                        productSelected.setAmountOfProduct(quantityToUpdate);
                        productSelected.setExist(quantityToUpdate > 0);
                    } else
                        products.add(new Product(productName, productPrice, quantityToUpdate, discount, productNumber, false));
//        we just added new product with no amount, so it's notExist in the stock Until some workers update this. in case 6
                }
                continueToUpdate = true;
            }


        } while (!exit);

    }

    private boolean doseBarcodeCanBeUsed(int barcodeToCheck) {
        boolean barcodeTaken = false;
        for (Product currentProduct : products) {
            if (currentProduct.getProductNumber() == barcodeToCheck) {
                barcodeTaken = true;
                System.out.println(currentProduct.getProductName() + " have the same barcode !!");
                break;
            }
        }
        return barcodeTaken;
    }

    private  void printingSpecificClients(int specificCategory) {
        switch (specificCategory) {
            case Deff.PRINT_COSTUMERS:
                System.out.println("\t\t[Clients]");
                for (Client currentClient : users)
                    System.out.println(currentClient.toString());
                break;

            case Deff.PRINT_VIP_COSTUMERS:
                System.out.println("\t\t[Club members]");
                for (Client currentClient : users)
                    if (currentClient.isMember())
                        System.out.println(currentClient);
                break;

            case Deff.PRINT_COSTUMERS_WHO_MADE_PURCHASE:
                System.out.println("\t\t[Client have made purchases]");
                boolean clientHaveMadePurchase = false;
                for (Client currentClient : users) {
                    if (currentClient.getSumOfPurchases() > 0) {
                        clientHaveMadePurchase = true;
                        System.out.println(currentClient);
                    }
                }

                if (!clientHaveMadePurchase)
                    System.out.println("no one have made purchase");
                break;

            case Deff.PRINT_COSTUMER_WITH_HIGHEST_PURCHASE:
                System.out.println("\t\t[Beloved Client <3]");
                if (belovedClientIndex()!= -1)
                System.out.println(users.get(belovedClientIndex()).toString());
                else
                    System.out.println("There is no BelovedClient");
                break;
        }
    }

    private int belovedClientIndex() {
        double extremelyHighAmount = 0;
        int belovedClientIndex = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getTotalCostOfAllPurchase() > extremelyHighAmount) {
                belovedClientIndex = i;
                extremelyHighAmount = users.get(belovedClientIndex).getTotalCostOfAllPurchase();
            }
        }
        return belovedClientIndex ;
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
            System.out.println("Enter product number you would buy\t\t[-1] to complete the purchase");
            showProductsInStockToTheClients();
            int selectedProductNumber = scanner.nextInt();
            buyingInProcess = selectedProductNumber != -1;
            if (buyingInProcess) {
                Product userProduct = doseProductExist(selectedProductNumber);
                if (userProduct!=null && userProduct.isExist()) {
//                    should know what is the product index, to take the name and the price and add it to the client cart
//                    if amounted in the stock
                    int quantityUserNeeds;
                    boolean normalQuantityOfProduct;
                    do {
                        System.out.println("Select quantity: ");
                        quantityUserNeeds = scanner.nextInt();
                        normalQuantityOfProduct = 0 < quantityUserNeeds && quantityUserNeeds <= userProduct.getAmountOfProduct();

                        if (quantityUserNeeds > userProduct.getAmountOfProduct()) {
                            System.out.println("Currently there are [" + userProduct.getAmountOfProduct() + "] in stock of the product you have selected");
                        } else if (quantityUserNeeds < 0)
                            System.out.println("!!invalid Quantity ");

                    } while (!normalQuantityOfProduct);
                    // here we know that we have enough amount, so we will add the product and the price

                    currentClientLogin.addProductToCart(userProduct.getProductName() , userProduct.getPrice() , quantityUserNeeds,  userProduct.getDiscount());
                    if (userProduct.getAmountOfProduct() - quantityUserNeeds == 0) {
                        userProduct.setExist(false);
                        userProduct.setAmountOfProduct(0);
                    } else {
                        userProduct.setAmountOfProduct(userProduct.getAmountOfProduct() - quantityUserNeeds);
                    }
                    // show the client his current shopping cart status, and the price he should pay until now.
                    currentClientLogin.shoppingCartStatus();
                    // after each purchase maybe**   the shopping cart should be reset for the next shopping
                } else {
                    if (userProduct != null) {
                        System.out.println("this product Number for " + userProduct.getProductName() + " and we We provide it, at the moment it's Out of stock");

                    }else
                    System.out.println("no such product");
                }
            } else {
                boolean AtLeastOneProductWasAddedToTheClientCart = currentClientLogin.getPurchases().size() > 0;
                if (AtLeastOneProductWasAddedToTheClientCart) { // if the clint add at lest one thing to cart so add the sum of his purchase if he just leaves without buy do nothing
                    System.out.println("The final cost of the purchase -  $" + currentClientLogin.getCurrentPropertyCost());
                    System.out.println("purchase Completed.....................bye bye :)");
                    currentClientLogin.setSumOfPurchases(currentClientLogin.getSumOfPurchases() + 1); // sum purchase
                    currentClientLogin.setTotalCostOfAllPurchase(currentClientLogin.getTotalCostOfAllPurchase() + currentClientLogin.getCurrentPropertyCost()); // this is t
                    currentClientLogin.setDateOfLastPurchase(new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(new Date()));
                    currentClientLogin.setCurrentPropertyCost(0);
                    currentClientLogin.setPurchases(new ArrayList<>());
                } else {
                    System.out.println("Soon there will be new products we hope you join us again");
                }

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

    private void showProductsInStockToTheClients() {
        System.out.println("\t\tProducts:");
        for (Product currentProduct : this.products){
            if (currentProduct.isExist()){
                System.out.println("Product {Product number=" + currentProduct.getProductNumber() + ", Product Name=" + currentProduct.getProductName()+"}");
            }
        }
    }

    private Product doseProductExist(int productNumber) {
//        boolean productExist = false;
        Product productForUser = null;
        for (Product currentProduct : this.products) {
            if (currentProduct.getProductNumber() == productNumber) {
                productForUser = currentProduct;
                break;
            }
        }
        return productForUser;
    }

}