import java.util.LinkedList;

public class Client extends User{
    private boolean isMember;

    public Client(String firstName, String lastName, String userName, String password, boolean isMember) {
        super(firstName, lastName, userName, password);
        this.isMember = isMember;
    }
}
