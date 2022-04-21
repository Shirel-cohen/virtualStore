
public class Worker extends Client implements Introduce {
    private WorkerDegree degree;

    public Worker(String firstName, String lastName, String userName, String password, boolean isMember, boolean isWorker, WorkerDegree degree) {
        super(firstName, lastName, userName, password, isMember, isWorker);
        this.degree = degree;
    }

    public Worker(Client other, WorkerDegree property) {
        super(other.getFirstName(), other.getLastName(), other.getUserName(), other.getPassword(), other.isMember(), other.isWorker());
        this.degree = property;
    }


    public void setDegree(WorkerDegree degree) {
        this.degree = degree;
    }

    public WorkerDegree getDegree() {
        return degree;
    }

    /*public String toString() {
        return getFirstName() + " " + getLastName() + '(' + property + ')';
    }*/

    @Override
    public String introduce() {
        return "Hello " + this.getFirstName() + " " + this.getLastName() + " [" + this.degree + ']';
    }
}