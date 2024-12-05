import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));

        int part1Result = part1(lines);
        System.out.println("Part 1: " + part1Result);
    }

    public static int part1(List<String> lines) {
        ArrayList<Rule> rules = new ArrayList<>();
        ArrayList<String> validLines = new ArrayList<>(); 

        for (String line: lines) {
            if (line.contains("|")) {
                String[] split_line = line.strip().split("\\|");
                int first = Integer.valueOf(split_line[0]);
                int second = Integer.valueOf(split_line[1]);

                Rule newRule = new Rule(first, second);
                rules.add(newRule);
            }

            if (line.contains(",")) {
                boolean validRule = true;
                for (Rule rule: rules) {
                    if (!rule.validateRule(line)) {
                        validRule = false;
                    }
                }

                if (validRule) {
                    validLines.add(line);
                }
            }
        }

        int total = 0;

        for (String validLine: validLines) {
            total += Integer.valueOf(findMiddle(validLine));
        }

        return total;
    }

    public static String findMiddle(String input) {
        String[] splitString = input.split(",");
        return splitString[splitString.length / 2];
    }
}