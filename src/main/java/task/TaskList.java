package task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manage them.
 * Supports adding, deleting, marking, and unmarking tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param existing an ArrayList of tasks to initialize the TaskList; if null, an empty list is created
     */
    public TaskList(ArrayList<Task> existing) {
        this.tasks = (existing == null) ? new ArrayList<>() : existing;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the list.
     *
     * @return a List of all Task objects
     */
    public List<Task> getAll() {
        return tasks;
    }

    /**
     * Retrieves a task by its 1-based index.
     *
     * @param oneBasedIndex the 1-based index of the task
     * @return the Task at the specified index
     * @throws SocksException if the index is out of range
     */
    public Task get(int oneBasedIndex) throws SocksException {
        int idx = oneBasedIndex - 1;
        if (oneBasedIndex <= 0 || idx >= tasks.size()) {
            throw new SocksException("Index out of range: " + oneBasedIndex);
        }
        return tasks.get(idx);
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param description the description of the todo
     * @return the added Todo task
     * @throws SocksException if the description is null or empty
     */
    public Task addTodo(String description) throws SocksException {
        if (description == null || description.trim().isEmpty()) {
            throw new SocksException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(description.trim());
        tasks.add(t);
        return t;
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param description the description of the deadline
     * @param by          the deadline time
     * @return the added Deadline task
     * @throws SocksException if the description or deadline is null or empty
     */
    public Task addDeadline(String description, String by) throws SocksException {
        if (description == null || description.trim().isEmpty()) {
            throw new SocksException("The description of a deadline cannot be empty.");
        }
        if (by == null || by.trim().isEmpty()) {
            throw new SocksException("Deadline requires a /by time.");
        }
        Task t = new Deadline(description.trim(), by.trim());
        tasks.add(t);
        return t;
    }

    /**
     * Adds an Event task to the list.
     *
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     * @return the added Event task
     * @throws SocksException if description, from, or to is null or empty
     */
    public Task addEvent(String description, String from, String to) throws SocksException {
        if (description == null || description.trim().isEmpty()) {
            throw new SocksException("The description of an event cannot be empty.");
        }
        if (from == null || from.trim().isEmpty() || to == null || to.trim().isEmpty()) {
            throw new SocksException("Event requires both /from and /to.");
        }
        Task t = new Event(description.trim(), from.trim(), to.trim());
        tasks.add(t);
        return t;
    }

    /**
     * Deletes a task by its 1-based index.
     *
     * @param oneBasedIndex the 1-based index of the task to delete
     * @return the deleted Task
     * @throws SocksException if the index is out of range
     */
    public Task delete(int oneBasedIndex) throws SocksException {
        Task t = get(oneBasedIndex);
        tasks.remove(oneBasedIndex - 1);
        return t;
    }

    /**
     * Marks a task as done by its 1-based index.
     *
     * @param oneBasedIndex the 1-based index of the task to mark
     * @return the marked Task
     * @throws SocksException if the index is out of range
     */
    public Task mark(int oneBasedIndex) throws SocksException {
        Task t = get(oneBasedIndex);
        t.isDone = true;
        t.taskStatus = "X";
        return t;
    }

    /**
     * Unmarks a task (marks it as not done) by its 1-based index.
     *
     * @param oneBasedIndex the 1-based index of the task to unmark
     * @return the unmarked Task
     * @throws SocksException if the index is out of range
     */
    public Task unmark(int oneBasedIndex) throws SocksException {
        Task t = get(oneBasedIndex);
        t.isDone = false;
        t.taskStatus = " ";
        return t;
    }
}