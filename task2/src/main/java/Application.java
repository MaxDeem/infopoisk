import lombok.SneakyThrows;

import java.io.*;
import java.util.StringTokenizer;

public class Application {
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
            if(Character.UnicodeBlock.of(token.charAt(i)).equals(Character.UnicodeBlock.GENERAL_PUNCTUATION)) {
                newToken = newToken.concat(" ");
            } else if (Character.UnicodeBlock.of(token.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                newToken += token.charAt(i);
            }
        }
        return newToken;
    }
    @SneakyThrows
    public static void getAllTokens(BufferedReader reader, BufferedWriter writer) {
        String token;
        String line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                if (hasRussian(token)) {
                    token = getRussianChars(token);
                    addToken(writer, token);
                }
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
        String file = "E:\\studies\\infopoisk\\infopoisk\\task2\\src\\main\\resources\\Прозоров, Лев Рудольфович — Википедия.html";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputToken));

        getAllTokens(reader, writer);
    }
}
