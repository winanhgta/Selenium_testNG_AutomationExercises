package WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
public class WebDriver {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:/Download/edgedriver_win64/msedgedriver.exe");
        org.openqa.selenium.WebDriver driver = new EdgeDriver();
//        driver.get("https://automationexercise.com/");

        try {
            // Navigate to the desired website (GeeksforGeeks in this example)
            driver.get("https://www.geeksforgeeks.org/");

            // Get and print the page title
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);

            // Wait for a few seconds (for demonstration purposes only)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

