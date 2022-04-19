public class LetterInventory {
    // size of the inventory array
    private final int SIZE = 26;
    // first character accepted by inventory
    private final char FIRST_CHAR = 'a';

    // the inventory containing the counts of the letters
    private int[] inventory;
    // the total number of letters accounted for in the inventory
    private int inventorySize;

    // constructs empty inventory
    public LetterInventory() {
        inventory = new int[SIZE];
        inventorySize = 0;
    }

    // constructs an inventory with data - upper and lower case are treated same
    public LetterInventory(String data) {
        inventory = new int[SIZE];
        inventorySize = 0;
        for (int i = 0; i < data.length(); i++) {
            char currentChar = Character.toLowerCase(data.charAt(i));
            int charIndex = currentChar - FIRST_CHAR;
            if (charIndex < SIZE && charIndex >= 0) {
                inventory[charIndex]++;
                inventorySize++;
            }
        }
    }

    // returns sum of all counts
    public int size() {
        return inventorySize;
    }

    // returns if inventory is empty
    public boolean isEmpty() {
        return inventorySize == 0;
    }

    // gets the number of the given letter currently in the inventory
    public int get(char letter) {
        return inventory[convertLetterToCharDiff(letter)];
    }

    // converts inventory to a string
    public String toString() {
        String outputString = "[";
        for (int i = 0; i <	SIZE; i++) {
            String result = Character.toString((char) (FIRST_CHAR + i));
            for (int j = 0; j < inventory[i]; j++) {
                outputString += (result);
            }
        }
        outputString += "]";
        return outputString;
    }

    // sets count for letter to given value
    public void set(char letter, int value) {
        if (convertLetterToCharDiff(letter) >= 0 &&
                convertLetterToCharDiff(letter) < SIZE && value >= 0){
            inventorySize += (value - inventory[convertLetterToCharDiff(letter)]);
            inventory[convertLetterToCharDiff(letter)] = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    // constructs and returns new LetterInventory that represents sum of all inventories
    public LetterInventory add(LetterInventory other) {
        LetterInventory newInventory = new LetterInventory();
        for (char i = FIRST_CHAR; i < FIRST_CHAR + SIZE; i++) {
            newInventory.set(i, this.get(i) + other.get(i));
        }
        return newInventory;
    }

    // constructs and returns new LetterInventory that represents difference
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory newInventory = new LetterInventory();
        for (char c = FIRST_CHAR; c < FIRST_CHAR + SIZE; c++) {
            int newValue = this.get(c) - other.get(c);
            if (newValue >= 0) {
                newInventory.set(c, newValue);
            } else {
                return null;
            }
        }
        return newInventory;
    }

    // returns percentage of letters in inventory that are given letter
    public double getLetterPercentage(char letter) {
        if (convertLetterToCharDiff(letter) >= 0 &&
                convertLetterToCharDiff(letter) < SIZE){
            return (double) this.get(letter) / this.size();
        } else {
            throw new IllegalArgumentException();
        }
    }

    // converts letter to char difference
    private int convertLetterToCharDiff(char letter) {
        return Character.toLowerCase(letter) - FIRST_CHAR;
    }
}
