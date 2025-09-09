import java.util.Scanner;

public class Socks {
    private static boolean programRunning = true; // used to keep track of pgm's run status

    public static void main(String[] args) {
        printStartMessage();
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager(); // create a task manager to handle all the tasks that will be created

        while (programRunning) {
            String input = sc.nextLine();
            performUserCommands(input, manager);
        }

        sc.close();
    }

    public static void performUserCommands(String input, TaskManager manager) {
        String[] parts = input.split(" "); // split the user input
        String command = parts[0].toLowerCase(); // store first word of user input to decide what action to execute
        String rest = input.substring(command.length()).trim(); // store the remaining section of user input

        switch (command) {
        case ("bye"):
            printExitMessage();
            programRunning = false; // terminate the while loops by making programRunning false
            break;

        case ("list"):
            manager.listTasks(); // list all the tasks
            break;

        case ("mark"):
            manager.markDone(Integer.parseInt(parts[1]) - 1); // pass the task number to be marked as (task number - 1)
            break;

        case ("unmark"):
            manager.markUndone(Integer.parseInt(parts[1]) - 1); // pass the task number to be unmarked as (task number - 1)
            break;

        case ("todo"):
            manager.addTask(new Todo(rest)); // add a todo task
            break;

        case ("deadline"):
            String[] split = rest.split("/by"); // split the "rest" substring by "/by"
            manager.addTask(new Deadline(split[0].trim(), split[1].trim())); // pass the 2 section of the split as description and deadline arguments)
            break;

        case ("event"):
            String[] fromSplit = rest.split("/from"); // split the "rest" substring by "/from"
            String[] toSplit = fromSplit[1].split("/to"); // split the "fromSplit" substring by "/to"
            manager.addTask(new Event(fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim())); // pass the 3 section of the split as description, start, end arguments)
            break;

        default:
            System.out.println("Please enter a valid command.");
        }
    }

    private static void printStartMessage() {
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

    private static void printExitMessage() {
        System.out.println("ByeBye! Stay productive and hope to see you again soon!");
    }
}