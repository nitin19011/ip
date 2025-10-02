# Socks — User Guide

Socks is a simple, keyboard‑only command‑line task manager. You can add todos, deadlines, events, list them, mark/unmark as done, delete, and find tasks by keyword. Data is saved automatically to `data/socks.txt`.

- Platform: Windows, macOS, Linux
- Java: 17+

---

## Quick Start

1. Install Java 17 or later.
2. Run the app:
    - If you have a JAR (e.g., `socks.jar`):
      ```
      java -jar socks.jar
      ```
    - Run from classes (from the project root after building/compiling):
      ```
      java -cp out socks.Socks
      ```
3. You should see a welcome banner. Type commands and press Enter.

Data file is created at `data/socks.txt` in the working directory.

---

## Commands

- list  
  Show all tasks with 1‑based indices.
  ```
  list
  ```

- todo  
  Add a todo.
  ```
  todo DESCRIPTION
  ```
  Example:
  ```
  todo read book
  ```

- deadline  
  Add a deadline with a due time.
  ```
  deadline DESCRIPTION /by WHEN
  ```
  Example:
  ```
  deadline return book /by June 6th
  ```

- event  
  Add an event with start and end times.
  ```
  event DESCRIPTION /from START /to END
  ```
  Example:
  ```
  event team sync /from Mon 2pm /to Mon 3pm
  ```

- mark  
  Mark a task (1‑based index) as done.
  ```
  mark INDEX
  ```
  Example:
  ```
  mark 1
  ```

- unmark  
  Mark a task as not done.
  ```
  unmark INDEX
  ```

- delete  
  Delete a task.
  ```
  delete INDEX
  ```

- find  
  Show tasks containing the keyword (case‑insensitive).
  ```
  find KEYWORD
  ```
  Example:
  ```
  find book
  ```

- bye  
  Exit the application.
  ```
  bye
  ```

Notes:
- INDEX is 1‑based (the number shown by `list`).
- `find` matches within the task’s display text (e.g., it will match inside “(by: …)” and “(from: … to: …)” too).

---

## Sample Session

```
Hello from
  ____             _      ____
 / ___|  ___   ___| | __ / ___|
 \___ \ / _ \ / __| |/ / \___ \
  ___) | (_) | (__|   <   ___) |
 |____/ \___/ \___|_|\_\ |____/
Your trusty life tracker! What can I do for you?

todo read book
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.

deadline return book /by June 6th
Got it. I've added this task:
  [D][ ] return book (by: June 6th)
Now you have 2 tasks in the list.

event team sync /from Mon 2pm /to Mon 3pm
Got it. I've added this task:
  [E][ ] team sync (from: Mon 2pm to: Mon 3pm)
Now you have 3 tasks in the list.

list
1. [T][ ] read book
2. [D][ ] return book (by: June 6th)
3. [E][ ] team sync (from: Mon 2pm to: Mon 3pm)

mark 1
Nice! I've marked this task as done:
  [T][X] read book
  
unmark 1
OK, I've marked this task as not done yet:
  [T][ ] read book
  
delete 2
Noted. I've removed this task:
  [D][ ] return book (by: June 6th)
Now you have 2 tasks in the list.

list
1. [T][ ] read book
2. [E][ ] team sync (from: Mon 2pm to: Mon 3pm)

find book
 Here are the matching tasks in your list:
 1.[T][ ] read book
 
bye
ByeBye! Stay productive and hope to see you again soon!
```

---

## Command Summary

| Command   | Format                                   | Example                                       |
|----------|-------------------------------------------|-----------------------------------------------|
| list     | `list`                                    | `list`                                        |
| todo     | `todo DESCRIPTION`                        | `todo read book`                              |
| deadline | `deadline DESCRIPTION /by WHEN`           | `deadline return book /by June 6th`           |
| event    | `event DESCRIPTION /from START /to END`   | `event team sync /from Mon 2pm /to Mon 3pm`   |
| mark     | `mark INDEX`                              | `mark 1`                                      |
| unmark   | `unmark INDEX`                            | `unmark 1`                                    |
| delete   | `delete INDEX`                            | `delete 2`                                    |
| find     | `find KEYWORD`                            | `find book`                                   |
| bye      | `bye`                                     | `bye`                                         |

---

## Storage

- File path: `data/socks.txt`
- The app creates the folder and file if missing.
- Tasks are saved automatically after add, delete, mark, and unmark.