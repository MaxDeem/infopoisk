import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Application {
    @SneakyThrows
    public static void main(String[] args) {
        String file = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\test.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                System.out.println(tokenizer.nextToken());
            }
            line = reader.readLine();
        }
        reader.close();

    }
}
