package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageInitilisation {
    public PageInitilisation(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
