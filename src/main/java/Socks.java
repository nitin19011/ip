import java.util.Scanner;

public class Socks {
    private static boolean programRunning = true;

    public static void main(String[] args) {
        printStartMessage();
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (programRunning) {
            String input = sc.nextLine();
            performUserCommands(input, manager);
        }

        sc.close();
    }

    public static void performUserCommands(String input, TaskManager manager) {
        String[] parts = input.split(" ");
        String command = parts[0].toLowerCase();
        String rest = input.substring(command.length()).trim();

        switch (command) {
        case ("bye"):
            printExitMessage();
            programRunning = false;

        case ("list"):
            manager.listTasks();
            break;

        case ("mark"):
            manager.markDone(Integer.parseInt(parts[1]) - 1);
            break;

        case ("unmark"):
            manager.markUndone(Integer.parseInt(parts[1]) - 1);
            break;

        case ("todo"):
            manager.addTask(new Todo(rest));
            break;

        case ("deadline"):
            String[] split = rest.split("/by");
            manager.addTask(new Deadline(split[0].trim(), split[1].trim()));
            break;

        case ("event"):
            String[] fromSplit = rest.split("/from");
            String[] toSplit = fromSplit[1].split("/to");
            manager.addTask(new Event(fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim()));
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