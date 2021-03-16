import lombok.SneakyThrows;
import org.jsoup.Jsoup;


import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Character.isUpperCase;

public class LegacyApplication {
    public static String middleToken = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\middle.txt";
    public static String outputToken = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\tokens.txt";

    public static boolean hasRussian(String token) {
        for (int i = 0; i < token.length(); i++) {
            if(Character.UnicodeBlock.of(token.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return true;
            }
        }
        return false;
    }
    //TODO add spacings in between words
    public static String getRussianChars(String token) {
        String newToken = "";
        for (int i = 0; i < token.length(); i++) {
            if (Character.UnicodeBlock.of(token.charAt(i)).equals(Character.UnicodeBlock.GENERAL_PUNCTUATION)) {
                newToken.concat(" ");
            } else if (!Character.UnicodeBlock.of(token.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                newToken.concat(" ");
            } else if (Character.UnicodeBlock.of(token.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                newToken += token.charAt(i);
            }
        }
        return newToken;
    }

    public static String removeHtmlTags(String token) {
        return Jsoup.parse(token).text();
    }
    @SneakyThrows
    public static void getAllTokens(BufferedReader reader, BufferedWriter writer) {
        String token;
        String line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                token = removeHtmlTags(token);
                if (hasRussian(token)) {
                    token = getRussianChars(token);
                    addToken(writer, token);
                }
            }
            line = reader.readLine();
        }
//        reader.close();
    }

    @SneakyThrows
    public static void addToken(BufferedWriter writer, String token) {
        writer.write(token);
        writer.newLine();
        writer.flush();
    }
    @SneakyThrows
    public static void separateTokens(BufferedReader reader, BufferedWriter writer) {
        String line = reader.readLine();
        String[] tokens;
        while (line != null) {
            tokens = line.split("(?=\\p{Upper})");
            for (int i = 0; i < tokens.length; i++) {
                writer.write(tokens[i]);
            }

        }
    }
    @SneakyThrows
    public static void main(String[] args) {
        String source = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\Прозоров, Лев Рудольфович — Википедия.html";
        BufferedReader middleReader = new BufferedReader(new FileReader(source));
        BufferedWriter middleWriter = new BufferedWriter(new FileWriter(middleToken));
        BufferedReader tokenReader = new BufferedReader(new FileReader(middleToken));
        BufferedWriter tokenWriter = new BufferedWriter(new FileWriter(outputToken));
        getAllTokens(middleReader, middleWriter);
        separateTokens(tokenReader, tokenWriter);
    }
}
