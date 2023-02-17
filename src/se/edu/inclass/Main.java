package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task Manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println();
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Printing deadlines before sorting");

        System.out.println("Printing deadlines after sorting");
        printDeadlinesUsingStreams(tasksData);

        ArrayList<Task> filteredList = filterTasksUsingStreams(tasksData, "11");
        printDeadlinesUsingStreams(tasksData);
        System.out.println("Total count of deadlines: " + countDeadlinesUsingStream(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks) {
        int count = (int)tasks.stream()
                .filter(task -> task instanceof Deadline)
                .count();
        return count;
    }

    public static void printDataUsingIterations(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iterations.");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data using streams.");
        tasks.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlinesUsingIterations(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines using iterations.");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing deadlines using streams.");
        tasks.stream()
                .filter(task -> task instanceof Deadline)
                .sorted((a, b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTasksUsingStreams(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter(task -> task.getDescription().contains(filterString))
                .collect(toList());
        return filteredList;
    }
}
