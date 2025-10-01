import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> existing) {
        this.tasks = (existing == null) ? new ArrayList<>() : existing;
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getAll() {
        return tasks;
    }

    public Task get(int oneBasedIndex) throws SocksException {
        int idx = oneBasedIndex - 1;
        if (oneBasedIndex <= 0 || idx >= tasks.size()) {
            throw new SocksException("Index out of range: " + oneBasedIndex);
        }
        return tasks.get(idx);
    }

    public Task addTodo(String description) throws SocksException {
        if (description == null || description.trim().isEmpty()) {
            throw new SocksException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(description.trim());
        tasks.add(t);
        return t;
    }

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

    public Task delete(int oneBasedIndex) throws SocksException {
        Task t = get(oneBasedIndex);
        tasks.remove(oneBasedIndex - 1);
        return t;
    }

    public Task mark(int oneBasedIndex) throws SocksException {
        Task t = get(oneBasedIndex);
        // Adjust these two lines if your Task API differs.
        t.isDone = true;
        t.taskStatus = "X";
        return t;
    }

    public Task unmark(int oneBasedIndex) throws SocksException {
        Task t = get(oneBasedIndex);
        // Adjust these two lines if your Task API differs.
        t.isDone = false;
        t.taskStatus = " ";
        return t;
    }
}