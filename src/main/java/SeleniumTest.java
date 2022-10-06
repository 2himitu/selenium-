import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

public class SeleniumTest {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.google.co.kr/imghp?hl=ko&tab=ri&ogbl");

        WebElement element = driver.findElement(By.name("q"));
        String SW = "고양이";
        element.sendKeys(SW);
        element.sendKeys(Keys.RETURN);

        int time = 2000;

        Object last_height = driver.executeScript("return document.body.scrollHeight");
        while (true){
            driver.executeScript("window.scrollBy(0,document.body.scrollHeight);");
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Object new_height = driver.executeScript("return document.body.scrollHeight");
            if( last_height.equals(new_height) ){
                try{
                    driver.findElement(By.cssSelector(".mye4qd")).click();
                }catch (Exception e){
                    break;
                }
            }
            last_height = new_height;
        }
        List<WebElement> m = driver.findElements(By.cssSelector(".rg_i.Q4LuWd"));
        System.out.println(m.size());
        int count = 1;
        for(WebElement el : m ){
            el.click();
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String imgUri = driver.findElement(By.xpath("//*[@id=\"Sva75c\"]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[3]/div/a/img")).getAttribute("src");



            try{
                URL url = new URL(imgUri);
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                File tFolder = new File("C:/marpple/" + SW);
                if(!tFolder.exists()) {
                    tFolder.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream("C:\\marpple\\"+SW+"\\"+count+".jpg");
                fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            count += 1;
            if(count==100){
                driver.close();
                break;
            }
        }
        driver.close();
    }

}
