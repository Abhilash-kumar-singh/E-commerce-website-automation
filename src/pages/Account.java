package pages;

import Screenshots.screenshot;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class Account extends PageInitilisation{


    public Account(WebDriver driver) {
        super(driver);
    }
    @FindBy(css=".login")
    WebElement signupButton;
    @FindBy(id="email_create")
    WebElement loginId;
    @FindBy(id="SubmitCreate")
    WebElement submitCreate;
    @FindBy(id="email")
    WebElement emailId;
    @FindBy(id="passwd")
    WebElement passcode;
    @FindBy(id="SubmitLogin")
    WebElement submitLogin;




    public void signupButtonClick(){
        signupButton.click();
    }
    public Boolean isAccountAlreadyCreated(String email,WebDriver driver){
        loginId.sendKeys(email);
        submitCreate.click();
        WebDriverWait wait=new WebDriverWait(driver,5);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_account_error")));
            screenshot accountExistShot =new screenshot(driver);
            String name=accountExistShot.takeScreenshot(driver);
            System.out.println("Reference proof "+name);
            return true;

        }catch(Exception e){
            return false;
        }
    }

    public void createAccount(JSONObject user_data,WebDriver driver) throws IOException {

        //loginId.sendKeys((String)user_data.get("email"));
        //submitCreate.click();
        if (((String)user_data.get("Designation")).equals("Mr")) {
            driver.findElement(By.id("id_gender1")).click();
        }else{
            driver.findElement(By.id("id_gender2")).click();
        }
        JavascriptExecutor js=(JavascriptExecutor)driver;
        driver.findElement(By.id("customer_firstname")).sendKeys((String) user_data.get("Firstname"));
        js.executeScript("window.scroll(0,30)");
        driver.findElement(By.id("customer_lastname")).sendKeys((String)user_data.get("Lastname"));
        driver.findElement(By.id("passwd")).sendKeys((String)user_data.get("password"));
        String DOB=(String)user_data.get("DOB");
        new Select(driver.findElement(By.id("days"))).selectByValue(DOB.split("-")[0]);
        new Select (driver.findElement(By.id("months"))).selectByValue(DOB.split("-")[1]);
        new Select (driver.findElement(By.id("years"))).selectByValue(DOB.split("-")[2]);
        js.executeScript("window.scroll(0,60)");
        driver.findElement(By.id("company")).sendKeys((String)user_data.get("company"));
        driver.findElement(By.id("address1")).sendKeys((String)user_data.get("Address"));
        driver.findElement(By.id("city")).sendKeys((String)user_data.get("city"));
        js.executeScript("window.scroll(0,10)");
        new Select (driver.findElement(By.id("id_state"))).selectByVisibleText((String)user_data.get("state"));
        driver.findElement(By.id("postcode")).sendKeys((String)user_data.get("pin"));
        driver.findElement(By.id("phone_mobile")).sendKeys((String)user_data.get("mobile"));
        driver.findElement(By.id("alias")).sendKeys((String)user_data.get("address_reference"));
        driver.findElement(By.id("submitAccount")).click();
        System.out.println("Account created successfully");
        screenshot accountScreenshot=new screenshot(driver);
        String name=accountScreenshot.takeScreenshot(driver);
        System.out.println("Check screenshot "+name +" and see if you landed on dashboard");
    }


    public Boolean login(WebDriver driver,String email, String password){
        emailId.sendKeys(email);
        passcode.sendKeys(password);
        submitLogin.click();
        WebDriverWait wait=new WebDriverWait(driver,15);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='center_column']/div[1]")));
            return false;

        }catch(Exception e){
            return true;
        }

    }

}
