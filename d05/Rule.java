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
        
        return false; // Placeholder
    } 

    @Override
    public String toString() {
        return "Rule(" + this.first + " < " + this.second + ")";
    }
}
