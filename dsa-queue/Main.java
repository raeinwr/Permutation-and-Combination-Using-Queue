
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Queue implementation
    static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    addElements();
                    break;

                case 2:
                    displayQueue();
                    break;

                case 3:
                    generatePermutations();
                    break;

                case 4:
                    generateCombinations();
                    break;

                case 5:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // TODO 1: Accept elements and add them to the Queue
    static void addElements() {
        System.out.print("Enter number of elements to add: ");
        int n = input.nextInt();

        if (n <= 0) {
            System.out.println("Number of elements must be at least 1.");
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter element " + i + ": ");
            queue.add(input.next()); 
        }

        displayQueue();

        if (queue.size() >= 2) {
            System.out.print("\nDequeue two elements? (y/n): ");
            String answer = input.next();
            if (answer.equalsIgnoreCase("y")) {
                for (int i = 0; i < 2 && !queue.isEmpty(); i++) {
                    System.out.println("Dequeued: " + queue.poll()); 
                }
                System.out.println("Front of queue now: " + queue.peek());
                displayQueue();
            }
        }
    }

    // TODO 2: Display all elements in the Queue
    static void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty. Add elements first.");
            return;
        }
        System.out.println("Queue: " + queue);
    }

    static List<String> snapshot() {
        return new ArrayList<>(queue);
    }

    static String build(List<String> items, int[] indexes) {
        StringBuilder sb = new StringBuilder();
        for (int idx : indexes) {
            sb.append(items.get(idx));
        }
        return sb.toString();
    }

    // TODO 3: Generate all possible permutations
    static void generatePermutations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty. Add elements first.");
            return;
        }
        displayQueue();

        System.out.print("\nEnter number of elements to arrange: ");
        int r = input.nextInt();

        List<String> items = snapshot();
        int n = items.size();

        if (r < 1 || r > n) {
            System.out.println("Please enter a value from 1 to " + n + ".");
            return;
        }

        System.out.println();

        Queue<int[]> work = new LinkedList<>();
        work.add(new int[0]);

        int count = 0;
        while (!work.isEmpty()) {
            int[] current = work.poll();

            if (current.length == r) { 
                System.out.println(build(items, current));
                count++;
                continue;
            }

            for (int i = 0; i < n; i++) {
                boolean used = false;
                for (int idx : current) {
                    if (idx == i) {
                        used = true;
                        break;
                    }
                }
                if (!used) {
                    int[] next = new int[current.length + 1];
                    System.arraycopy(current, 0, next, 0, current.length);
                    next[current.length] = i;
                    work.add(next);
                }
            }
        }

        System.out.println("\nTotal Permutations: " + count);
    }

    // TODO 4: Generate all possible combinations
    static void generateCombinations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty. Add elements first.");
            return;
        }
        displayQueue();

        System.out.print("\nEnter number of elements to select: ");
        int r = input.nextInt();

        List<String> items = snapshot();
        int n = items.size();

        if (r < 1 || r > n) {
            System.out.println("Please enter a value from 1 to " + n + ".");
            return;
        }

        System.out.println();

        Queue<int[]> work = new LinkedList<>();
        work.add(new int[0]);

        int count = 0;
        while (!work.isEmpty()) {
            int[] current = work.poll();

            if (current.length == r) {       
                System.out.println(build(items, current));
                count++;
                continue;
            }

            int start = current.length == 0 ? 0 : current[current.length - 1] + 1;
            for (int i = start; i < n; i++) {
                int[] next = new int[current.length + 1];
                System.arraycopy(current, 0, next, 0, current.length);
                next[current.length] = i;
                work.add(next);
            }
        }

        System.out.println("\nTotal Combinations: " + count);
    }
}
