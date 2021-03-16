import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class TestApplication {
    @SneakyThrows
    public static void main(String[] args) {
        String outputFirst = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\testInput.txt";
        String outputSecond = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\testOutput.txt";

        String input = "input";
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFirst));
        writer.write(input);
        writer.flush();
        writer = new BufferedWriter(new FileWriter(outputSecond));
        writer.write(input);
        writer.flush();
    }
}
