/**
 * Represents a Todo task, which is a basic task without a specific time or deadline.
 * Extends the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return a formatted string including the task type, status, and description
     */
    @Override
    public String toString() {
        return ("[T]" + "[" + this.taskStatus + "] " + description);
    }
}
