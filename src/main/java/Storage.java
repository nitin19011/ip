import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {
    private final Path dataPath;

    public Storage(String filePath) {
        this.dataPath = Paths.get(filePath);
    }

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