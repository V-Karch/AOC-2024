import java.util.ArrayList;

public class Rule {
    private int first, second;

    public Rule(int first, int second) {
        this.first = first; // Must come first
        this.second = second; // Must come second
    }

    public boolean validateRule(String input) {
        ArrayList<Integer> actualValues = new ArrayList<>();

        for (String value: input.split(",")) {
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
            return true; // If either are not found returnt rue
        }

        if (firstIndex < secondIndex) {
            return true; // Return true if the first is before the second
        }

        return false; // otherwise return false
    } 

    @Override
    public String toString() {
        return "Rule(" + this.first + " < " + this.second + ")";
    }
}
