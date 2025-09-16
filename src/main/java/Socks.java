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
        try {
            if (input.trim().isEmpty()) {
                throw new SocksException("Input cannot be empty.");
            }

            String[] parts = input.split(" ");
            String command = parts[0].toLowerCase();
            String rest = input.substring(command.length()).trim();

            switch (command) {
            case "bye":
                printExitMessage();
                programRunning = false;
                break;

            case "list":
                manager.listTasks();
                break;

            case "mark":
                if (parts.length < 2) throw new SocksException("Please specify a task number to mark.");
                manager.markDone(Integer.parseInt(parts[1]) - 1);
                break;

            case "unmark":
                if (parts.length < 2) throw new SocksException("Please specify a task number to unmark.");
                manager.markUndone(Integer.parseInt(parts[1]) - 1);
                break;

            case "todo":
                if (rest.isEmpty()) throw new SocksException("The description of a todo cannot be empty.");
                manager.addTask(new Todo(rest));
                break;

            case "deadline":
                if (!rest.contains("/by")) throw new SocksException("Deadline must include '/by'.");
                String[] split = rest.split("/by");
                manager.addTask(new Deadline(split[0].trim(), split[1].trim()));
                break;

            case "event":
                if (!rest.contains("/from") || !rest.contains("/to")) throw new SocksException("Event must include '/from' and '/to'.");
                String[] fromSplit = rest.split("/from");
                String[] toSplit = fromSplit[1].split("/to");
                manager.addTask(new Event(fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim()));
                break;

            default:
                throw new SocksException("Sorry, I don't recognize that command.");
            }

        } catch (SocksException e) {
            printErrorMessage(e.getMessage());
        } catch (Exception e) {
            printErrorMessage("Something went wrong: " + e.getMessage());
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

    private static void printErrorMessage(String message) {
        System.out.println("OOPS!!! " + message);
    }
}



