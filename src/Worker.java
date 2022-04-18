import java.util.LinkedList;

public class Worker extends Client {
    private WorkerProperty property;

    public Worker(String firstName, String lastName, String userName, String password, boolean isMember, WorkerProperty property) {
        super(firstName, lastName, userName, password, isMember);
        this.property = property;
    }

    public void setProperty(WorkerProperty property) {
        this.property = property;
    }

    public WorkerProperty getProperty() {
        return property;
    }
}