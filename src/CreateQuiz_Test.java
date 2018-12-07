import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;     // for Firefox
import org.openqa.selenium.chrome.ChromeDriver;       // for chrome
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
/**
 * Cynthia Cabrera
 * Input space test for CreateQuiz
 *
 *
 * Note:
 *   Test environment: Chrome Version 68, selenium 3.14.0, Java 8, ChromeDriver 2.42
 */

public class CreateQuiz_Test
{
    private WebDriver driver;
    //private String login_url = "http://pegasus.cs.virginia.edu/quiztool";
    private String url; // = "http://pegasus.cs.virginia.edu/quiztool/createQuiz/3/";

    @Before
    public void setUp()
    {
        driver = new ChromeDriver();    // create an instance of the web browser and open it
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        //Login into quiztool app
        url = "http://pegasus.cs.virginia.edu/quiztool/login";
        driver.get(url);                // open the given url
        driver.findElement(By.name("username")).sendKeys("professorM");
        driver.findElement(By.name("password")).sendKeys("swtesting1");
        WebElement element = driver.findElement(By.xpath("(/html/body/div/div/form/div/button[@type='submit'])[1]"));
        element.click();

        // Go to createQuiz
        url = "http://pegasus.cs.virginia.edu/quiztool/createQuiz/1/";
        driver.get(url);                // open the given url
    }

    @After
    public void teardown()
    {
        driver.quit();                  // close the browser
    }

    @Test
    public void test_openURL()
    {
        assertTrue(driver.getPageSource().contains("Create a Quiz"));	// check if we are on the right page
    }

    @Test
    public void test_Base()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("1");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Check result
        assertTrue(driver.getPageSource().contains("Question text"));

    }

    @Test
    public void test_case2()
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("1");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        driver.findElement(By.linkText("Cancel")).click();
        //element.click();

        //try { Thread.sleep(1000); } catch (InterruptedException e) { }// TODO Auto
        assertTrue(driver.getPageSource().contains("CS 4501"));
    }

    @Test
    public void test_case3()
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("12");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("12");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("1");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Click cancel
        try { Thread.sleep(1000); } catch (InterruptedException e) { }// TODO Auto
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Check result
        // Page should give error message "Invalid date"
        assertTrue(driver.getPageSource().contains("Invalid date"));
        // But instead breaks down
        //assertTrue(driver.getPageSource().contains("TypeError"));
    }

    @Test
    public void test_case4()
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("0");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit
        try { Thread.sleep(1000); } catch (InterruptedException e) { }// TODO Auto
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Check result
        // Page should give error message "Invalid duration"
        assertTrue(driver.getPageSource().contains("Invalid duration"));
        // But instead it accepts value
        //assertTrue(driver.getPageSource().contains("Question text"));
    }

    @Test
    public void test_case5()
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("-1");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit
        try { Thread.sleep(1000); } catch (InterruptedException e) { }// TODO Auto
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Check result
        // Page should give error message "Invalid duration"
        assertTrue(driver.getPageSource().contains("Invalid duration"));
        // But instead it accepts value
        //assertTrue(driver.getPageSource().contains("Question text"));
    }

    @Test   // (expected = NullPointerException.class)
    public void test_case6()
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("a");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit
        try { Thread.sleep(1000); } catch (InterruptedException e) { }// TODO Auto
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Check result
        // Page should give error message "Invalid duration"
        assertTrue(driver.getPageSource().contains("Invalid duration"));
        // But instead it doesn't give error message but it won't submit which is a good behavior
        //assertTrue(driver.getPageSource().contains("Create a Quiz"));
    }

    @Test   // (expected = NullPointerException.class)
    public void test_case7()
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("1");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit
        try { Thread.sleep(1000); } catch (InterruptedException e) { }// TODO Auto
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Check result
        // Page should give error message "Please fill out this field."
        assertTrue(driver.getPageSource().contains("Please fill out this field"));
        // But instead it accepts value
        //assertTrue(driver.getPageSource().contains("Create a Quiz"));
    }


    @Test
    public void test_case8()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("1");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Browser feature
        driver.navigate().back();

        // Check result (should go back to creat quiz page and quiz values should be display)
        assertTrue(driver.getPageSource().contains("Create a Quiz"));
        assertEquals("a", driver.findElement(By.name("quiz_name")).getAttribute("value"));
        assertEquals("1", driver.findElement(By.name("duration")).getAttribute("value"));
        assertEquals("11/14/2018 00:00", driver.findElement(By.name("open_date")).getAttribute("value"));
        assertEquals("11/14/2018 00:00", driver.findElement(By.name("due_date")).getAttribute("value"));
        assertEquals("1", driver.findElement(By.name("number_of_allowed_submissions")).getAttribute("value"));
    }

    @Test
    public void test_case9()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("1");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Browser feature
        driver.navigate().forward();

        // Check result (should stay in create questions for quiz "a")
        assertTrue(driver.getPageSource().contains("Create Questions for (a)"));

    }

    @Test
    public void test_case10()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("quiz_name")).clear();
        driver.findElement(By.name("quiz_name")).sendKeys("a");
        driver.findElement(By.name("duration")).clear();
        driver.findElement(By.name("duration")).sendKeys("1");
        driver.findElement(By.name("open_date")).clear();
        driver.findElement(By.name("open_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.name("due_date")).clear();
        driver.findElement(By.name("due_date")).sendKeys("11/14/2018 00:00");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the datepicker
        driver.findElement(By.name("number_of_allowed_submissions")).clear();
        driver.findElement(By.name("number_of_allowed_submissions")).sendKeys("1");
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div/button[@type='submit'])[1]"));
        element.click();

        // Browser feature
        driver.navigate().refresh();

        // Check result (should stay in create questions for quiz "a")
        assertTrue(driver.getPageSource().contains("Create Questions for (a)"));

    }


}


