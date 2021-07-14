package pages;

import Screenshots.screenshot;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class PlaceOrder extends PageInitilisation{

    public PlaceOrder(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//*[@id=\"block_top_menu\"]/ul/li[1]/a")
    WebElement category;
    @FindBy(xpath="//*[@id=\"list\"]/a")
    WebElement gridView;
    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[3]/div/div/div[3]/div/div[2]/a[2]")
    WebElement dress;
    @FindBy(id="group_1")
    Select size;
    @FindBy(xpath="//*[@id=\"add_to_cart\"]/button")
    WebElement addToCart;
    @FindBy(xpath="//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
    WebElement checkOut;
    public void orderDress( WebDriver driver, JSONObject data) throws IOException {
        Account account=new Account(driver);
        account.signupButtonClick();
        account.login(driver,(String)data.get("email"),(String)data.get("password"));
        //Select the category, in this case we are selecting women
        category.click();
        //switch to grid view
        gridView.click();
        // select the dress, in this case we are selecting 3rd dress
        dress.click();
        //Add to cart
        addToCart.click();
        //checkout
        checkOut.click();
        //Taking screenshot of checkout
        screenshot shot=new screenshot(driver);
        String name=shot.takeScreenshot(driver);
        System.out.println("Check file "+name+ " in database folder for reference");
        System.out.println("Item successfully added to checklist");
        driver.quit();
    }


}
