import java.sql.Array;
import java.util.Scanner;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
//    private LinkedList<ShoppingCart> shoppingCartLinkedLis;
//    private String date;

    public User(String firstName, String lastName,String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;


    }
    public User (String userName){

        this.userName = userName;
    }
    public String nameIsValid (){
        Scanner scanner = new Scanner(System.in);
        String [] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        boolean contains = false;
        String nameToCheck=  null;
        do{
             nameToCheck = scanner.nextLine();
            for (int i=0; i< array.length;i++){
                if (nameToCheck.contains(array[i])){
                    System.out.println("You need to enter name without numbers!");
                    contains=true;
                    break;
                }
            }
        } while (!contains);

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


}
