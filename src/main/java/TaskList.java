import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> tasks;

    public TaskList() {
        tasks = new ArrayList<>(); //constructor
    }

    public void addTask(String task) {
        tasks.add(task); // add to list method
    }

    public void listTasks() { // show the list method
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks to do! HOORAY!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + ": " + tasks.get(i));
            }
        }
    }
}
