public class Rule {
    private int first, second;

    public Rule(int first, int second) {
        this.first = first; // Must come first
        this.second = second; // Must come second
    }

    public boolean validateRule(String input) {
        // Remember to implement this
        return false; // Placeholder
    } 

    @Override
    public String toString() {
        return "Rule(" + this.first + " < " + this.second + ")";
    }
}
