import java.util.Scanner;
import java.util.ArrayList;

public class Socks {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    public Socks(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();

        TaskList loaded;
        try {
            ArrayList<Task> fromDisk = storage.load();
            loaded = new TaskList(fromDisk);
        } catch (Exception e) {
            ui.showLoadingError();
            loaded = new TaskList();
        }
        this.tasks = loaded;
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit && sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                exit = parser.handle(input, tasks, ui, storage);
            } catch (SocksException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Socks socks = new Socks("data/socks.txt");
        socks.run();
    }
}