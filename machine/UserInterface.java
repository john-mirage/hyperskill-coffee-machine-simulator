package machine;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static String askForCommand(String prompt) {
        while (true) {
            System.out.println(prompt);
            String command = scanner.nextLine();
            if (!command.isEmpty()) {
                return command;
            } else {
                System.out.println("Please enter a command:");
            }
        }
    }
}
