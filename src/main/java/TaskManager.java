import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> allTasks = new ArrayList<>(); // create a dynamic array to store Task objects

    public void addTask(Task task) {
        allTasks.add(task);
        System.out.println("Got it. I've added this task:\n   " + task);
        System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
    }

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

    public void markDone(int index) {
        Task t = allTasks.get(index);
        t.isDone = true;
        t.taskStatus = "X";
        System.out.println(" Nice! I've marked this task as done:\n    " + t);
    }

    public void markUndone(int index) {
        Task t = allTasks.get(index);
        t.isDone = false;
        t.taskStatus = " ";
        System.out.println(" Sure, I've unmarked this task:\n    " + t);
    }

    public void deleteTask(int index) {
        Task t = allTasks.get(index);
        allTasks.remove(index);
        System.out.println(" Sure, I've deleted this task:\n    " + t);
    }
}
