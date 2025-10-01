public class Parser {
    public boolean handle(String input, TaskList tasks, Ui ui, Storage storage) throws SocksException {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        String[] parts = trimmed.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (cmd) {
        case "bye":
            ui.showGoodbye();
            return true;

        case "list":
            ui.showList(tasks.getAll());
            return false;

        case "mark": {
            int idx = parseIndex(args);
            ui.showMarked(tasks.mark(idx));
            storage.save(new java.util.ArrayList<>(tasks.getAll()));
            return false;
        }

        case "unmark": {
            int idx = parseIndex(args);
            ui.showUnmarked(tasks.unmark(idx));
            storage.save(new java.util.ArrayList<>(tasks.getAll()));
            return false;
        }

        case "delete": {
            int idx = parseIndex(args);
            Task deleted = tasks.delete(idx);
            ui.showDeleted(deleted, tasks.size());
            storage.save(new java.util.ArrayList<>(tasks.getAll()));
            return false;
        }

        case "todo": {
            Task t = tasks.addTodo(args);
            ui.showAdded(t, tasks.size());
            storage.save(new java.util.ArrayList<>(tasks.getAll()));
            return false;
        }

        case "deadline": {
            String[] segs = splitOnce(args, " /by ");
            Task t = tasks.addDeadline(segs[0], segs[1]);
            ui.showAdded(t, tasks.size());
            storage.save(new java.util.ArrayList<>(tasks.getAll()));
            return false;
        }

        case "event": {
            String[] segsFrom = splitOnce(args, " /from ");
            String desc = segsFrom[0];
            String[] segsTo = splitOnce(segsFrom[1], " /to ");
            Task t = tasks.addEvent(desc, segsTo[0], segsTo[1]);
            ui.showAdded(t, tasks.size());
            storage.save(new java.util.ArrayList<>(tasks.getAll()));
            return false;
        }

        default:
            throw new SocksException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private int parseIndex(String s) throws SocksException {
        if (s == null || s.trim().isEmpty()) {
            throw new SocksException("Please provide an index.");
        }
        try {
            int idx = Integer.parseInt(s.trim());
            if (idx <= 0) throw new NumberFormatException();
            return idx;
        } catch (NumberFormatException e) {
            throw new SocksException("Index must be a positive number.");
        }
    }

    private String[] splitOnce(String text, String delimiter) throws SocksException {
        int pos = text.indexOf(delimiter);
        if (pos < 0) {
            throw new SocksException("Missing '" + delimiter.trim() + "' part.");
        }
        String left = text.substring(0, pos).trim();
        String right = text.substring(pos + delimiter.length()).trim();
        if (left.isEmpty() || right.isEmpty()) {
            throw new SocksException("Incomplete command: " + delimiter.trim());
        }
        return new String[] { left, right };
    }
}