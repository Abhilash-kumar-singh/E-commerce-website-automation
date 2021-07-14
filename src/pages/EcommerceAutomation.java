package pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.*;
import org.json.simple.*;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class EcommerceAutomation {
    public static WebDriver driver;
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scan=new Scanner(System.in);

        JSONParser jsonparser=new JSONParser();
        JSONObject user_data = null;
        try(FileReader reader=new FileReader("/Users/abhilashsingh/IdeaProjects/E-commerce website automation/src/pages/userdata.json")){
            Object obj=jsonparser.parse(reader);
            user_data=(JSONObject) obj;
        }catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}



        WebDriverManager.chromedriver().setup();
        /*
        ChromeOptions options=new ChromeOptions();
        options.setHeadless(true);
         */
        System.out.println("Press 1 to check if email is already registered");
        System.out.println("Press 2 to create an account");
        System.out.println("Press 3 to login");
        System.out.println("Press anything to order dress");
        String choice=scan.nextLine();
        //WebDriver driver= new ChromeDriver(options);
        WebDriver driver= new ChromeDriver();


        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        if(choice.equals("1")){

            Account checkAccount=new Account(driver);
            checkAccount.signupButtonClick();
            if(checkAccount.isAccountAlreadyCreated((String)user_data.get("email"), driver)) {
                System.out.println("Account already exist");
                driver.close();
            }else{


            }
        }else if(choice.equals("2")){
            Account createAccount=new Account(driver);
            createAccount.signupButtonClick();
            Boolean result=createAccount.isAccountAlreadyCreated((String)user_data.get("email"),driver);
            if(!result) {
                createAccount.createAccount(user_data, driver);
                driver.close();
            }else{
                driver.quit();
                System.out.println("Account already exist");
            }
        }else if(choice.equals("3")){

            Account loginAccount=new Account(driver);
            loginAccount.signupButtonClick();
            Boolean result=loginAccount.login(driver,(String)user_data.get("email"),(String)user_data.get("password"));
            if(result){
                System.out.println("Login successful");
            }else{
                System.out.println("Wrong id or password");
            }

        }else{
            PlaceOrder order=new PlaceOrder(driver);
            order.orderDress(driver,user_data);
        }




    }
}
