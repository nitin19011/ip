package task;

/**
 * Represents a task that has a deadline.
 * Extends the Task class and adds a specific deadline attribute.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructs a Deadline object with a description and a deadline.
     *
     * @param description the description of the task
     * @param deadline    the deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description + " (by: " + deadline + ")");
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return the deadline as a String
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return a formatted string including task type, status, and description
     */
    @Override
    public String toString() {
        return ("[D]" + "[" + this.taskStatus + "] " + description);
    }
}
