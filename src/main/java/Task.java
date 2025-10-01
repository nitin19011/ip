/**
 * Represents a generic task with a description and completion status.
 * This is the base class for different types of tasks such as Todo, Deadline, and Event.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskStatus;

    /**
     * Constructs a Task with the specified description.
     * By default, a task is not done and its taskStatus is set to a blank space.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        taskStatus = " ";
    }
}
