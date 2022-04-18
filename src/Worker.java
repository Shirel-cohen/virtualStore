import java.util.LinkedList;

public class Worker extends User{
    private WorkerProperty property;

    public Worker(String firstName, String lastName, String userName, String password, WorkerProperty property) {
        super(firstName, lastName, userName, password);
        this.property=property;
    }

    public WorkerProperty getProperty() {
        return property;
    }
}