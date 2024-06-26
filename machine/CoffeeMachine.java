package machine;

public class CoffeeMachine {
    private State currentState;
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int cash;
    private boolean hasUser;

    public CoffeeMachine(int water, int milk, int beans, int cups, int cash) throws IllegalArgumentException {
        if (water < 0 || milk < 0 || beans < 0 || cups < 0 || cash < 0) {
            throw new IllegalArgumentException("Water, milk, beans, cups and cash must not be negative");
        }
        this.currentState = State.CHOOSING_AN_ACTION;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.cash = cash;
        this.hasUser = true;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public boolean hasUser() {
        return this.hasUser;
    }

    private void checkCommand(String command) throws IllegalArgumentException {
        if (command == null || command.isEmpty()) {
            throw new IllegalArgumentException("Command cannot be null or empty");
        }
    }

    private void chooseAction(String command) throws IllegalArgumentException {
        this.checkCommand(command);
        switch (command) {
            case "buy":
                this.currentState = State.CHOOSING_A_TYPE_OF_COFFEE;
                break;
            case "fill":
                this.currentState = State.FILLING_WITH_WATER;
                break;
            case "take":
                System.out.printf("I gave you $%d%n", this.cash);
                this.cash = 0;
                break;
            case "remaining":
                System.out.println(this);
                break;
            case "exit":
                this.hasUser = false;
                break;
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }

    private void makeCoffee(Coffee coffee) {
        if (
            this.water >= coffee.getWater() &&
            this.milk >= coffee.getMilk() &&
            this.beans >= coffee.getBeans() &&
            this.cups >= 1
        ) {
            this.water -= coffee.getWater();
            this.milk -= coffee.getMilk();
            this.beans -= coffee.getBeans();
            this.cups -= 1;
            this.cash += coffee.getPrice();
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            System.out.println("Sorry, not enough components!");
        }
        currentState = State.CHOOSING_AN_ACTION;
    }

    private void chooseATypeOfCoffee(String command) throws IllegalArgumentException {
        this.checkCommand(command);
        switch (command) {
            case "1":
                this.makeCoffee(Coffee.ESPRESSO);
                break;
            case "2":
                this.makeCoffee(Coffee.LATTE);
                break;
            case "3":
                this.makeCoffee(Coffee.CAPPUCCINO);
                break;
            case "back":
                this.currentState = State.CHOOSING_AN_ACTION;
                break;
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }

    private int getAmountFromCommand(String command) throws IllegalArgumentException {
        this.checkCommand(command);
        try {
            int number = Integer.parseInt(command);
            if (number >= 0) {
                return number;
            } else {
                throw new IllegalArgumentException("Command is a negative number");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Command is not a number");
        }
    }

    private void fillWithWater(String command) {
        this.water += this.getAmountFromCommand(command);
        this.currentState = State.FILLING_WITH_MILK;
    }

    private void fillWithMilk(String command) {
        this.milk += this.getAmountFromCommand(command);
        this.currentState = State.FILLING_WITH_BEANS;
    }

    private void fillWithBeans(String command) {
        this.beans += this.getAmountFromCommand(command);
        this.currentState = State.FILLING_WITH_CUPS;
    }

    private void fillWithCups(String command) {
        this.cups += this.getAmountFromCommand(command);
        this.currentState = State.CHOOSING_AN_ACTION;
    }

    public void execute(String command) {
        this.checkCommand(command);
        switch (currentState) {
            case CHOOSING_AN_ACTION:
                this.chooseAction(command);
                break;
            case CHOOSING_A_TYPE_OF_COFFEE:
                this.chooseATypeOfCoffee(command);
                break;
            case FILLING_WITH_WATER:
                this.fillWithWater(command);
                break;
            case FILLING_WITH_MILK:
                this.fillWithMilk(command);
                break;
            case FILLING_WITH_BEANS:
                this.fillWithBeans(command);
                break;
            case FILLING_WITH_CUPS:
                this.fillWithCups(command);
                break;
            default:
                throw new IllegalArgumentException("Invalid state");
        }
    }

    @Override
    public String toString() {
        return """
            The coffee machine has:
            %d ml of water
            %d ml of milk
            %d g of coffee beans
            %d disposable cups
            $%d of money
            """.formatted(this.water, this.milk, this.beans, this.cups, this.cash);
    }
}
