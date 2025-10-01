import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * The Storage class handles reading and writing tasks to a file on disk.
 * It provides methods to load tasks from a file and save tasks back to the file.
 */
public class Storage {
    private final Path dataPath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.dataPath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file.
     * If the file or its directories do not exist, they will be created.
     * Corrupted lines are skipped with a warning.
     *
     * @return an ArrayList of Task objects loaded from the file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(dataPath.getParent())) {
                Files.createDirectories(dataPath.getParent());
            }
            if (!Files.exists(dataPath)) {
                Files.createFile(dataPath);
            }
            BufferedReader reader = new BufferedReader(new FileReader(dataPath.toFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    // Corrupted line: skip
                    System.out.println("Warning: Skipping invalid line: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     * If the parent directories do not exist, they will be created.
     *
     * @param tasks the ArrayList of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try {
            if (!Files.exists(dataPath.getParent())) {
                Files.createDirectories(dataPath.getParent());
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath.toFile()));
            for (Task task : tasks) {
                writer.write(formatTask(task));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     * This method is private because it is only used internally by load().
     *
     * @param line a line from the storage file
     * @return the Task object represented by the line
     * @throws Exception if the line is invalid or the task type is unknown
     */
    private Task parseLine(String line) throws Exception {
        String[] fields = line.split("\\|");
        String type = fields[0].trim();
        boolean isDone = fields[1].trim().equals("1");
        String desc = fields[2].trim();
        switch (type) {
        case "T":
            Todo todo = new Todo(desc);
            todo.isDone = isDone;
            todo.taskStatus = isDone ? "X" : " ";
            return todo;
        case "D":
            String by = fields[3].trim();
            Deadline deadline = new Deadline(desc, by);
            deadline.isDone = isDone;
            deadline.taskStatus = isDone ? "X" : " ";
            return deadline;
        case "E":
            String at = fields[3].trim();
            Event event = new Event(desc, at, "");
            event.isDone = isDone;
            event.taskStatus = isDone ? "X" : " ";
            return event;
        default:
            throw new Exception("Unknown task type");
        }
    }

    /**
     * Formats a Task object into a string suitable for storage in a file.
     * This method is private because it is only used internally by save().
     *
     * @param task the Task to format
     * @return a string representing the task for storage
     */
    private String formatTask(Task task) {
        String type = "T";
        String extra = "";
        if (task instanceof Deadline) {
            type = "D";
            extra = " | " + ((Deadline) task).getDeadline();
        } else if (task instanceof Event) {
            type = "E";
            extra = " | " + ((Event) task).startTime;
        }
        return String.format("%s | %d | %s%s", type, task.isDone ? 1 : 0, task.description, extra);
    }
}
