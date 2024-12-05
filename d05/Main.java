import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        ArrayList<Rule> rules = new ArrayList<>();

        for (String line: lines) {
            if (line.contains("|")) {
                String[] split_line = line.strip().split("\\|");
                int first = Integer.valueOf(split_line[0]);
                int second = Integer.valueOf(split_line[1]);

                Rule newRule = new Rule(first, second);
                rules.add(newRule);
            }
        }

        System.out.println(rules);
    }
}