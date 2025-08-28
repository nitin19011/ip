import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> tasks;
    private final ArrayList<Boolean> isDone;

    public TaskList() {
        tasks = new ArrayList<>(); //constructor
        isDone = new ArrayList<>();
    }

    public int getSize(){
        return tasks.size();
    }

    public void addTask(String task) {
        tasks.add(task); // add to list method
        isDone.add(false);
    }

    public void markDone(int index) {
        isDone.set(index-1, true);
    }

    public void unmarkDone(int index) {
        isDone.set(index-1, false);
    }

    public void listTask(int index) {
        if (!isDone.get(index - 1)) {
            System.out.println("    " + index + ".[ ] " + tasks.get(index - 1));
        } else {
            System.out.println("    " + index + ".[X] " + tasks.get(index - 1));
        }
    }

    public void listTasks() { // show the list method
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks to do! HOORAY!");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 1; i < tasks.size()+1; i++) {
                listTask(i);
            }
        }
    }
}
