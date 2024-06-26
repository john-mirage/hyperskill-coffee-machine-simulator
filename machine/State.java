package machine;

public enum State {
    CHOOSING_AN_ACTION("Write action (buy, fill, take, remaining, exit):"),
    CHOOSING_A_TYPE_OF_COFFEE("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"),
    FILLING_WITH_WATER("Write how many ml of water you want to add:"),
    FILLING_WITH_MILK("Write how many ml of milk you want to add:"),
    FILLING_WITH_BEANS("Write how many grams of coffee beans you want to add:"),
    FILLING_WITH_CUPS("Write how many disposable cups you want to add:");

    private final String message;

    State(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
