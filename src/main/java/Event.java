public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description + " (from: " + startTime + " to: " + endTime + ")");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.taskStatus + "] " + description;
    }
}