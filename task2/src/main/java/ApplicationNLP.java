import lombok.SneakyThrows;
import opennlp.tools.sentdetect.SentenceDetectorME;
import org.jsoup.Jsoup;

import java.io.*;

public class ApplicationNLP {
    public static String input = "src/main/resources/British Muslim Heritage Centre - Wikipedia.html";
    public static String output = "src/main/resources/output.txt";
    public static String modelPath = "src/main/resources/models/en-sent.bin";
    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));

        String pageText = new String();
        String line = reader.readLine();
        while (line != null) {
            pageText = pageText.concat(line);
            line = reader.readLine();
        }
        pageText = Jsoup.parse(pageText).text();


        NLProcessor processor = new NLProcessor(modelPath);
        processor.detectSentences(pageText);
        String[] sentences = processor.getSentences();
        for (String sent : sentences) {
            writer.write(sent);
            writer.newLine();
            writer.flush();
        }
    }
}
