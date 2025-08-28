import java.util.Scanner;

public class Socks {
    public static void main(String[] args) {

        // Start message
        String logo =
                """
                          ____             _      ____  \s
                         / ___|  ___   ___| | __ / ___| \s
                         \\___ \\ / _ \\ / __| |/ / \\___ \\ \s
                          ___) | (_) | (__|   <   ___) |\s
                         |____/ \\___/ \\___|_|\\_\\ |____/ \s
                        """;
        System.out.println("Hello from\n" + logo + "Your trusty life tracker! What can I do for you?");

        //Create a new task list and store user's task
        TaskList toDoList = new TaskList();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")) { // handle exit command
                System.out.println("    ByeBye, Stay productive and hope to see you again soon!");
                break;
            }
            if (userInput.equalsIgnoreCase("list")) { // handle listing of the list including when its empty
                toDoList.listTasks();
            } else {
                toDoList.addTask(userInput); // add the user's input to list and show what was added to user
                System.out.println("    Added: " + userInput);
            }
        }
        sc.close();
    }
}