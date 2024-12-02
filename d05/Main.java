import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        System.out.println(lines);
    }
}