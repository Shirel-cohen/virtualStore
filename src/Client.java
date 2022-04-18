
import java.util.Scanner;

public class Client {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean isMember;
    private boolean isWorker;
//    private LinkedList<ShoppingCart> shoppingCartLinkedLis;
//    private String date;


    public Client(String firstName, String lastName, String userName, String password, boolean isMember, boolean isWorker) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.isMember = isMember;
        this.isWorker=isWorker;
    }
    public Client(){

    }


    public String toString(){
        if(this.isMember){
            return "Hello " + this.firstName + " " + this.lastName +"(vip)!";
        }
            return "Hello " + this.firstName + " " + this.lastName +"!";
    }

    public String nameIsValid (){
        Scanner scanner = new Scanner(System.in);
        String [] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        boolean contains = false;
        String nameToCheck =  null;
        do{
            contains = false;
            System.out.println("> ");
            nameToCheck = scanner.nextLine();
            for (int i=0; i< array.length;i++){
                if (nameToCheck.contains(array[i])){
                    System.out.println("You need to enter name without numbers!");
                    contains=true;
                    nameToCheck =  null;
                    break;
                }
            }
        } while (contains);

        return nameToCheck;
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




