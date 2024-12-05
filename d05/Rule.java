import java.util.ArrayList;

public class Rule {
    private int first, second;

    public Rule(int first, int second) {
        this.first = first; // Must come first
        this.second = second; // Must come second
    }

    public String fixInput(String input) {
        ArrayList<Integer> actualValues = new ArrayList<>();
        for (String value : input.split(",")) {
            actualValues.add(Integer.valueOf(value));
        } // Add to arraylist

        // Iteratively enforce the rule until the input satisfies the constraints
        boolean rulesSatisfied;
        do {
            rulesSatisfied = true; // Assume all rules are satisfied initially
            for (int i = 0; i < actualValues.size(); i++) {
                for (int j = i + 1; j < actualValues.size(); j++) {
                    int current = actualValues.get(i);
                    int next = actualValues.get(j);

                    // Check if the current rule is violated
                    if (current == this.second && next == this.first) {
                        // Swap the elements to fix the order
                        actualValues.remove(j);
                        actualValues.add(i, next); // Place "second" behind "first"
                        rulesSatisfied = false; // Rules need to be rechecked
                    }
                }
            }
        } while (!rulesSatisfied);

        // Convert the list back to a comma-separated string
        StringBuilder result = new StringBuilder();
        for (int value : actualValues) {
            result.append(value).append(",");
        }

        return result.substring(0, result.length() - 1);
    }

    public boolean validateRule(String input) {
        ArrayList<Integer> actualValues = new ArrayList<>();

        for (String value : input.split(",")) {
            actualValues.add(Integer.valueOf(value));
        } // Add to arraylist

        int firstIndex = -1;
        int secondIndex = -1;

        for (int i = 0; i < actualValues.size(); i++) {
            if (actualValues.get(i) == this.first) {
                firstIndex = i;
            }

            if (actualValues.get(i) == this.second) {
                secondIndex = i;
            }
        }

        if (firstIndex == -1 || secondIndex == -1) {
            return true; // If either is not found, return true
        }

        return firstIndex < secondIndex; // Return true if the first is before the second
    }

    @Override
    public String toString() {
        return "Rule(" + this.first + " < " + this.second + ")";
    }
}
