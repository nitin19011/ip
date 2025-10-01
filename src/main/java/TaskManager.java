import java.util.ArrayList;

/**
 * Manages a collection of tasks in memory and persists changes to storage.
 * Provides operations to add, list, mark, unmark, and delete tasks.
 */
public class TaskManager {

    private ArrayList<Task> allTasks = new ArrayList<>();
    private final Storage storage;

    /**
     * Constructs a TaskManager with a given Storage object.
     * Loads existing tasks from storage into memory.
     *
     * @param storage the Storage object used for loading and saving tasks
     */
    public TaskManager(Storage storage) {
        this.storage = storage;
        this.allTasks = storage.load();
    }

    /**
     * Adds a task to the in-memory list and saves the updated list to storage.
     * Prints a confirmation message to the user.
     *
     * @param task the Task to add
     */
    public void addTask(Task task) {
        allTasks.add(task);
        System.out.println("Got it. I've added this task:\n   " + task);
        System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
        storage.save(allTasks);
    }

    /**
     * Lists all tasks in memory with their index numbers.
     * Prints a special message if there are no tasks.
     */
    public void listTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("You have no tasks to do! HOORAY!");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < allTasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + allTasks.get(i));
            }
        }
    }

    /**
     * Marks a task as done by its 0-based index in the list.
     * Saves the updated list to storage and prints a confirmation.
     *
     * @param index the 0-based index of the task to mark as done
     */
    public void markDone(int index) {
        Task t = allTasks.get(index);
        t.isDone = true;
        t.taskStatus = "X";
        System.out.println(" Nice! I've marked this task as done:\n    " + t);
        storage.save(allTasks);
    }

    /**
     * Marks a task as not done (unmarks it) by its 0-based index.
     * Saves the updated list to storage and prints a confirmation.
     *
     * @param index the 0-based index of the task to unmark
     */
    public void markUndone(int index) {
        Task t = allTasks.get(index);
        t.isDone = false;
        t.taskStatus = " ";
        System.out.println(" Sure, I've unmarked this task:\n    " + t);
        storage.save(allTasks);
    }

    /**
     * Deletes a task by its 0-based index in the list.
     * Prints a confirmation message.
     *
     * @param index the 0-based index of the task to delete
     */
    public void deleteTask(int index) {
        Task t = allTasks.get(index);
        allTasks.remove(index);
        System.out.println(" Sure, I've deleted this task:\n    " + t);
    }
}
