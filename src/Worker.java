
public class Worker extends Client {
    private WorkerProperty property;

    public Worker(String firstName, String lastName, String userName, String password, boolean isMember, boolean isWorker, WorkerProperty property) {
        super(firstName, lastName, userName, password, isMember, isWorker);
        this.property = property;
    }

    public Worker(Client other, WorkerProperty property) {
        super(other.getFirstName(), other.getLastName(), other.getUserName(), other.getPassword(), other.isMember(), other.isWorker());
        this.property = property;
    }

    public void setProperty(WorkerProperty property) {
        this.property = property;
    }

    public WorkerProperty getProperty() {
        return property;
    }
}