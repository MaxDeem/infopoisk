import com.google.common.collect.Iterators;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {
    public static String url = "https://en.wikipedia.org/wiki/Special:AllPages/GM";
    public static String output = "src/main/resources/index.txt";

    @SneakyThrows
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3
                , TimeUnit.SECONDS);
        driver.get(url);

        List<WebElement> elements = driver.findElements(By.tagName("a"));;
        List<String> links = new ArrayList<>();
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        int iterator = 1;

        //here we get url from WebElements and add url to List
        for (WebElement elem : elements) {
            String link = elem.getAttribute("href");
            //all links containing "index.php" return us to the starter page, so don't add 'em
            if ((link != null) && (!link.contains("index.php"))) {
                links.add(elem.getAttribute("href"));
            }
        }

        //here we write links from list to file
        for (String link : links) {
            writer.write(iterator + ": " + link + "\n");
            writer.flush();
            iterator++;
        }

        //here we download pages
        for (String link : links) {
            driver.get(link);
            getHtml();
        }
    }
    @SneakyThrows
    public static void getHtml() {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_S);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);
    }
}
