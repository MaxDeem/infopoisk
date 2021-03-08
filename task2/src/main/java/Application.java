import lombok.SneakyThrows;

import java.io.*;
import java.util.StringTokenizer;

public class Application {
    public static String outputToken = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\tokens.txt";
    @SneakyThrows
    public static void getAllTokens(BufferedReader reader, BufferedWriter writer) {
        String line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                addToken(writer, tokenizer.nextToken());
            }
            line = reader.readLine();
        }
        reader.close();
    }

    @SneakyThrows
    public static void addToken(BufferedWriter writer, String token) {
        writer.write(token);
        writer.newLine();
        writer.flush();
    }

    @SneakyThrows
    public static void main(String[] args) {
        String file = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\test.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputToken));

        getAllTokens(reader, writer);
    }
}
