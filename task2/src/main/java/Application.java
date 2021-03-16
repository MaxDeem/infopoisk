import lombok.SneakyThrows;
import org.apache.lucene.morphology.russian.RussianAnalyzer;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Application {
    public static String inputFile = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\Прозоров, Лев Рудольфович — Википедия.html";
    public static String outputFile = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\tokens.txt";
    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        String token;
        String line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                if (hasRussian(token)) {
                    token = removeHtml(token);
                    if (!token.isEmpty()) {
                        ifSeveral(writer, token);
                    }
                }
            }
            line = reader.readLine();
        }

        Map<String, String> lemmas = new HashMap<>();
        

    }

    public static String removeHtml(String html) {
        return Jsoup.parse(html).text();
    }
    @SneakyThrows
    public static void addToken(BufferedWriter writer, String token) {
        writer.write(token);
        writer.newLine();
        writer.flush();
    }
    public static boolean hasRussian(String token) {
        for (int i = 0; i < token.length(); i++) {
            if (Character.UnicodeBlock.of(token.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return true;
            }
        }
        return false;
    }
    public static boolean hasEnglish(String token) {
        for (int i = 0; i < token.length(); i++) {
            if (Character.UnicodeBlock.of(token.charAt(i)).equals(Character.UnicodeBlock.BASIC_LATIN)) {
                return true;
            }
        }
        return false;
    }
    public static void ifSeveral(BufferedWriter writer, String token) {
        String[] tokens = token.split(",");
        for (String s : tokens) {
            //if contains english chars we can remove it, because most probably it's metadata
            if (!hasEnglish(s)) {
                //on this stage only "«" and "»" are left, so we remove them
                s = replaceLeftovers(s);
                addToken(writer, s);
            }
        }
    }
    public static String replaceLeftovers(String token) {
        token = token.replace("«", "");
        token = token.replace("»", "");

        return token;
    }

}
