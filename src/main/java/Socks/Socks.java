package Socks;

import task.Task;
import task.TaskList;
import task.Storage;
import task.SocksException;

import ui.Ui;

import parser.Parser;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Socks class is the main application class for the task management system.
 * It handles the initialization of the UI, storage, task list, and parser,
 * and contains the main run loop that reads user input and executes commands.
 */
public class Socks {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Entry point of the application.
     * Creates the app with the default data file path and starts the run loop.
     *
     * @param args optional first argument overrides the default data file path
     */
    public static void main(String[] args) {
        Socks socks = new Socks("data/socks.txt");
        socks.run();
    }

    /**
     * Constructs a Socks application instance.
     * Initializes the UI, storage, parser, and loads tasks from disk.
     * If loading fails, starts with an empty task list.
     *
     * @param filePath the path to the data file where tasks are saved/loaded
     */
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

    /**
     * Starts the main application loop.
     * Displays the welcome message, continuously reads user input,
     * and passes it to the parser for handling.
     * The loop terminates when the user issues the "bye" command or input ends.
     */
    public void run() {
        ui.showWelcome();
        System.out.println();

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
            if (!exit) {
                System.out.println();
            }
        }
        sc.close();
    }
}
