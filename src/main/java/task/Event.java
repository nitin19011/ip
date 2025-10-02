package task;

/**
 * Represents an event task that has a start time and an end time.
 * Extends the Task class and adds specific start and end time attributes.
 */
public class Event extends Task {
    String startTime;
    private String endTime;

    /**
     * Constructs an Event object with a description, start time, and end time.
     *
     * @param description the description of the event
     * @param startTime   the start time of the event
     * @param endTime     the end time of the event
     */
    public Event(String description, String startTime, String endTime) {
        super(description + " (from: " + startTime + " to: " + endTime + ")");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of the event task. 
     *
     * @return a formatted string including task type, status, and description
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.taskStatus + "] " + description;
    }
}
