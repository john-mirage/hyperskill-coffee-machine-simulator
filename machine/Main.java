package machine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            } else {
                CoffeeMachine.process(input);
            }
        }
    }
}
