import java.util.Scanner;

public class Socks {
    public static void main(String[] args) {
        String logo =
                """
                          ____             _      ____  \s
                         / ___|  ___   ___| | __ / ___| \s
                         \\___ \\ / _ \\ / __| |/ / \\___ \\ \s
                          ___) | (_) | (__|   <   ___) |\s
                         |____/ \\___/ \\___|_|\\_\\ |____/ \s
                        """;

        System.out.println("Hello from\n" + logo + "Your trusty life tracker! What can I do for you?");

        // echo user command
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    ByeBye, Stay productive and hope to see you again soon!");
                break;
            }
            System.out.println("    "+userInput);
        }
        sc.close();
    }
}