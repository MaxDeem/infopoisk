import lombok.SneakyThrows;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static String input = "src/main/resources/British Muslim Heritage Centre - Wikipedia.html";
    public static String tokenFile = "src/main/resources/tokens.txt";

    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tokenFile));

        String token;
        String line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                token = removeHtml(token);
                if (!token.isEmpty()) {
                    splitIfSeveral(writer, token);
                }
            }
            line = reader.readLine();
        }
    }

    public static String removeHtml(String token) {
        return Jsoup.parse(token).text();
    }
    @SneakyThrows
    public static void addToken(BufferedWriter writer, String token) {
        writer.write(token);
        writer.newLine();
        writer.flush();
    }

    public static void splitIfSeveral(BufferedWriter writer, String token) {
        String[] cleanTokens = token.split(",");
        for (String cleanToken : cleanTokens) {
            if(isClean(cleanToken))
                addToken(writer, cleanToken);
        }
    }
    public static boolean isClean(String token) {
        Pattern pattern = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(token);
        if (matcher.find()) {
            return false;
        }
        return true;
    }
}
