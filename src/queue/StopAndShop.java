package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class StopAndShop {

    int numLines, numLineSpots;

    private final List<Queue> checkout;

    public StopAndShop(int numLines, int numLineSpots) {
        this.numLines = numLines;
        this.numLineSpots = numLineSpots;
        checkout = new ArrayList<>(numLines);
        for (int i = 0; i < numLines; i++) checkout.add(new Queue(numLineSpots));
    }

    void addCustomer(long numItems) {
        int line = this.pickLine();
        checkout.get(line).insert(numItems);
    }

    @SuppressWarnings("ManualMinMaxCalculation")
    private int pickLine() {
        int shortestLineLength = checkout.get(0).size();
        int shortestLineIndex = 0;
        for (int i = 0; i < checkout.size(); i++) {
            Queue line = checkout.get(i);
            if (shortestLineLength > line.size()) {
                shortestLineLength = line.size();
                shortestLineIndex = i;
            }
        }
        return shortestLineIndex;
    }

    void timeStep() {
        for (Queue line : checkout) {
            if (line.isEmpty()) continue;
            if (line.peekFront() <= 1) {
                line.remove();
            } else {
                line.decrementFront();
            }
        }
    }

    boolean isFull() {
        for (Queue line : checkout) {
            if (line.isFull()) return true;
        }
        return false;

    }

    //If any line is not empty, the checkout matrix is not empty
    boolean isEmpty() {
        for (Queue line : checkout) {
            if (!line.isEmpty()) return false;
        }
        return true;
    }

    long removeCustomer(int lineNum) {
        return checkout.get(lineNum).remove();
    }

    void printCheckout() {
        for (Queue line : checkout) {
            line.displayQueue();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StopAndShop shop = new StopAndShop(5, 5);
        int choice;
        while (true) {
            System.out.println("1. To Add a Customer");
            System.out.println("2. To Increment Time");
            System.out.println("3. Quit");
            choice = scanner.nextInt();
            Random rand = new Random();
            switch (choice) {
                case 1:
                    shop.addCustomer(rand.nextInt(11-1) + 1);
                    break;
                case 2:
                    shop.timeStep();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid input. Please try again");
            }
            shop.printCheckout();
        }
    }
}
