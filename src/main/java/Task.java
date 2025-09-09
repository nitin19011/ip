public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskStatus;

    public Task(String description) {
        this.description = description;
        isDone = false;
        taskStatus = " ";
    }
}
