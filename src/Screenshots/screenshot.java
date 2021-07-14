package Screenshots;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.PageInitilisation;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class screenshot extends PageInitilisation {
    public screenshot(WebDriver driver) {
        super(driver);
    }
    public String takeScreenshot(WebDriver driver) throws IOException {
        int filename=0;
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Random rand =new Random();
        filename=rand.nextInt(2000);
        //Screenshots are stored in database folder inside screenshot package

        FileUtils.copyFile(file, new File("/Users/abhilashsingh/IdeaProjects/E-commerce website automation/src/Screenshots/database/"+filename+".jpg"));
        return (filename+".jpg");
    }
}
