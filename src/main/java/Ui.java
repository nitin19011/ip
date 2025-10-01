import java.util.List;

public class Ui {
    public void showWelcome() {
        String logo =
                """
                          ____             _      ____  
                         / ___|  ___   ___| | __ / ___| 
                         \\___ \\ / _ \\ / __| |/ / \\___ \\ 
                          ___) | (_) | (__|   <   ___) |
                         |____/ \\___/ \\___|_|\\_\\ |____/ 
                        """;
        System.out.println("Hello from\n" + logo + "Your trusty life tracker! What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("ByeBye! Stay productive and hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Error: Could not load previous tasks. Starting with an empty list.");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}