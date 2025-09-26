public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description + " (by: " + deadline + ")");
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return ("[D]" + "[" + this.taskStatus + "] " + description);
    }
}
