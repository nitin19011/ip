package ui;

import task.Task;
import java.util.List;

/**
 * Handles all user interactions for the Socks application.
 * Responsible for displaying messages, task lists, errors, and confirmations.
 */
public class Ui {

    /**
     * Displays a welcome message with the app logo.
     */
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

    /**
     * Prints a single blank line (for spacing between commands).
     */
    public void spacer() {
        System.out.println();
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        System.out.println("ByeBye! Stay productive and hope to see you again soon!");
    }

    /**
     * Displays a message indicating that previous tasks could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Error: Could not load previous tasks. Starting with an empty list.");
    }

    /**
     * Displays a generic error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task the task that was added
     * @param size the total number of tasks after addition
     */
    public void showAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task the task that was deleted
     * @param size the total number of tasks after deletion
     */
    public void showDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task the task that was marked
     */
    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message confirming that a task has been unmarked (not done).
     *
     * @param task the task that was unmarked
     */
    public void showUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays the list of tasks with their index numbers.
     * Shows a special message if the list is empty.
     *
     * @param tasks the list of tasks to display
     */
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