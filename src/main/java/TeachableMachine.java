import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TeachableMachine {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://teachablemachine.withgoogle.com/train/tiny_image");

        System.out.println("여기서 부터?");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.tagName("button")).findElement(By.xpath("//*[@title=\"샘플 추가: 업로드\"]/button[2]")).click();

        String SW = "고양이";
        //List<WebElement> m = driver.findElements(By.cssSelector(".rg_i.Q4LuWd"));


        //String imgUri = driver.findElement(By.xpath("//*[@id=\"Sva75c\"]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[3]/div/a/img")).getAttribute("src");


        driver.close();
    }

}
