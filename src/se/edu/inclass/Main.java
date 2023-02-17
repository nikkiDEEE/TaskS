package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

//        System.out.println();
//        System.out.println("Printing deadlines");
//
//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
//        printDataUsingIterations(tasksData);
//        printDataUsingStreams(tasksData);
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
                .forEach(System.out::println);
    }
}
