import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));

        int part1Result = part1(lines);
        System.out.println("Part 1: " + part1Result);

        int part2Result = part2(lines);
        System.out.println("Part 2: " + part2Result);
    }

    public static int part2(List<String> lines) {
        ArrayList<Rule> rules = new ArrayList<>();
        ArrayList<String> invalidLines = new ArrayList<>(); 

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

                if (!validRule) {
                    invalidLines.add(line);
                }
            }
        }

        int total = 0;

        for (String invalidLine: invalidLines) {
            int timesChecked = 0;
            while (!checkAllRules(rules, invalidLine)) {
                invalidLine = randomShuffle(invalidLine);
                timesChecked++;
                System.out.print("\rState: " + invalidLine + " | Checked: " + timesChecked);
            }

            total += Integer.valueOf(findMiddle(invalidLine));
            System.out.println();
        }

        return total;
    }

    public static boolean checkAllRules(ArrayList<Rule> rules, String input) {
        boolean isValid = true;

        for (Rule rule: rules) {
            if (!rule.validateRule(input)) {
                isValid = false;
            }
        }

        return isValid;
    }

    public static String randomShuffle(String invalidLine) {
        List<String> splitLine = Arrays.asList(invalidLine.split(","));
        Collections.shuffle(splitLine);

        String result = "";
        for (String shuffledValue: splitLine) {
            result += shuffledValue + ",";
        }
        
        return result.substring(0, result.length() - 1);
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