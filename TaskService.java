//This file manages and hold created tasks in the listview so that it stays visible when the homescreen refreshes


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskService {
    private static ObservableList<String> tasks = FXCollections.observableArrayList();

    public static ObservableList<String> getTasks() {
        return tasks;
    }

    public static void addTask(String task) {
        tasks.add(task);
    }
}
