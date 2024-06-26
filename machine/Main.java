package machine;

public class Main {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        while (coffeeMachine.hasUser()) {
            String message = coffeeMachine.getCurrentState().getMessage();
            String command = UserInterface.askForCommand(message);
            coffeeMachine.execute(command);
        }
    }
}
