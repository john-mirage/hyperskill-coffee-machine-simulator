package machine;

public class CoffeeMachine {
    private enum State {
        CHOOSING_AN_ACTION,
        CHOOSING_A_TYPE_OF_COFFEE,
        FILLING_WITH_WATER,
        FILLING_WITH_MILK,
        FILLING_WITH_BEANS,
        FILLING_WITH_CUPS
    }

    private static State state = State.CHOOSING_AN_ACTION;
    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int cups = 9;
    private static int cash = 550;

    public static void process(String input) {
        switch (state) {
            case CHOOSING_AN_ACTION: {
                switch (input) {
                    case "buy": {
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        state = State.CHOOSING_A_TYPE_OF_COFFEE;
                        break;
                    }
                    case "fill": {
                        System.out.println("Write how many ml of water you want to add:");
                        state = State.FILLING_WITH_WATER;
                        break;
                    }
                    case "take": {
                        System.out.printf("I gave you $%d%n", cash);
                        cash = 0;
                        break;
                    }
                    case "remaining": {
                        System.out.printf("""
                                The coffee machine has:
                                %d ml of water
                                %d ml of milk
                                %d g of coffee beans
                                %d disposable cups
                                $%d of money
                                """, water, milk, beans, cups, cash);
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice");
                    }
                }
                break;
            }
            case CHOOSING_A_TYPE_OF_COFFEE: {
                switch (input) {
                    case "1": {
                        if (water >= 250 && beans >= 16 && cups >= 1) {
                            water -= 250;
                            beans -= 16;
                            cups -= 1;
                            cash += 4;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            System.out.println("Sorry, not enough components!");
                        }
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        state = State.CHOOSING_AN_ACTION;
                        break;
                    }
                    case "2": {
                        if (water >= 350 && milk >= 75 && beans >= 20 && cups >= 1) {
                            water -= 350;
                            milk -= 75;
                            beans -= 20;
                            cups -= 1;
                            cash += 7;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            System.out.println("Sorry, not enough components!");
                        }
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        state = State.CHOOSING_AN_ACTION;
                        break;
                    }
                    case "3": {
                        if (water >= 200 && milk >= 100 && beans >= 12 && cups >= 1) {
                            water -= 200;
                            milk -= 100;
                            beans -= 12;
                            cups -= 1;
                            cash += 6;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            System.out.println("Sorry, not enough components!");
                        }
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        state = State.CHOOSING_AN_ACTION;
                        break;
                    }
                    case "back": {
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                        state = State.CHOOSING_AN_ACTION;
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice");
                    }
                }
                break;
            }
            case FILLING_WITH_WATER: {
                water += Integer.parseInt(input);
                System.out.println("Write how many ml of milk you want to add:");
                state = State.FILLING_WITH_MILK;
                break;
            }
            case FILLING_WITH_MILK: {
                milk += Integer.parseInt(input);
                System.out.println("Write how many grams of coffee beans you want to add:");
                state = State.FILLING_WITH_BEANS;
                break;
            }
            case FILLING_WITH_BEANS: {
                beans += Integer.parseInt(input);
                System.out.println("Write how many disposable cups you want to add:");
                state = State.FILLING_WITH_CUPS;
                break;
            }
            case FILLING_WITH_CUPS: {
                cups += Integer.parseInt(input);
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                state = State.CHOOSING_AN_ACTION;
                break;
            }
            default: {
                System.out.println("Invalid state");
            }
        }
    }
}
