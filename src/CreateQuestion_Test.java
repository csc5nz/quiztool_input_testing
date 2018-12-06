import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;

import org.openqa.selenium.firefox.FirefoxDriver;     // for Firefox
import org.openqa.selenium.chrome.ChromeDriver;       // for chrome
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
/**
 * Cynthia Cabrera
 * Input space test for CreateQuestion
 *
 *
 * Note:
 *   Test environment: Chrome Version 68, selenium 3.14.0, Java 8, ChromeDriver 2.42
 */

public class CreateQuestion_Test
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
        url = "http://pegasus.cs.virginia.edu/quiztool/create_additional_questions/126/16";
        driver.get(url);                // open the given url
    }

    @After
    public void teardown()
    {
        // Delete question if there is a question available to delete
        try {
            try { Thread.sleep(1000); } catch (InterruptedException e) { }
            driver.findElement(By.linkText("Delete")).click();
            try { Thread.sleep(1000); } catch (InterruptedException e) { }
            driver.switchTo().alert().accept();
            try { Thread.sleep(1000); } catch (InterruptedException e) { }
        } catch (NoSuchElementException e) { }
        driver.quit();                  // close the browser
    }

    @Test
    public void test_openURL()
    {
        assertTrue(driver.getPageSource().contains("Add Questions"));	// check if we are on the right page
    }

    @Test
    public void test_Base()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("1");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Add question)
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        element.click();

        // Accept alert popup
        driver.switchTo().alert().accept();

        // Check result (Stays in same page, and new question is displayed on quiz preview)
        assertTrue(driver.getPageSource().contains("Q: a"));

    }

    @Test
    public void test_case2()
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("1");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Finish)
        driver.findElement(By.linkText("Finish adding Questions")).click();
        //WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        //element.click();

        // Accept alert popup
        driver.switchTo().alert().accept();

        // Check result (Goes to editing page)
        assertTrue(driver.getPageSource().contains("Editing"));
    }

    @Test
    public void test_case3()
    {
    // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("1");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (clear answer)
        driver.findElement(By.id("clear_answer_0")).click();

        // Check result (answer should be empty)
        assertEquals("", driver.findElement(By.name("answer_0")).getAttribute("value"));
    }

    @Test
    public void test_case4()
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("1");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (clear answer)
        driver.findElement(By.id("add_answer")).click();

        // Check result (answer_1 should have been created)
        WebElement element = null;
        try { element = driver.findElement(By.name("answer_1")); } catch (NoSuchElementException e) { }
        assertNotEquals(null, element);
    }

    @Test
    public void test_case5()
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("1");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Short Answer");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Add question)
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        element.click();

        // Accept alert popup
        driver.switchTo().alert().accept();

        // Check result (Stays in same page, and new question is displayed on quiz preview)
        assertTrue(driver.getPageSource().contains("Q: a"));

    }

    @Test
    public void test_case6()
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("1");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Choose All");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Add question)
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        element.click();

        // Accept alert popup
        driver.switchTo().alert().accept();

        // Check result (Stays in same page, and new question is displayed on quiz preview)
        assertTrue(driver.getPageSource().contains("Q: a"));

    }

    @Test
    public void test_case7()
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("1");

        // Checkbox
        if ( driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Add question)
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        element.click();

        // Accept alert popup
        driver.switchTo().alert().accept();

        // Check result (Stays in same page, and new question is displayed on quiz preview)
        assertTrue(driver.getPageSource().contains("Q: a"));

    }

    @Test
    public void test_case8()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("0");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("0");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("0");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Add question)
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        element.click();

        // Accept alert popup
        try { driver.switchTo().alert().accept(); } catch (NoAlertPresentException e) { }
        //driver.switchTo().alert().accept();

        // Check result (Stays in same page, and new question is displayed on quiz preview)
        assertTrue(driver.getPageSource().contains("Q: a"));

    }

    @Test
    public void test_case9()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("-1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("-1");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("-1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Add question)
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        element.click();

        // Accept alert popup
        try { driver.switchTo().alert().accept(); } catch (NoAlertPresentException e) { }
        //driver.switchTo().alert().accept();

        // Check result (Stays in same page, and question is not created)
        assertFalse(driver.getPageSource().contains("Q: a"));

    }

    @Test
    public void test_case10()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("a");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("a");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("a");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("a");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("a");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Add question)
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        element.click();

        // Accept alert popup
        try { driver.switchTo().alert().accept(); } catch (NoAlertPresentException e) { }
        //driver.switchTo().alert().accept();

        // Check result (Stays in same page, and question is not created)
        assertFalse(driver.getPageSource().contains("Q: a"));
    }

    @Test
    public void test_case11()     // test case #1
    {
        // Fill input fields
        driver.findElement(By.name("question_text")).clear();
        driver.findElement(By.name("question_text")).sendKeys("");
        driver.findElement(By.name("question_points")).clear();
        driver.findElement(By.name("question_points")).sendKeys("1");
        driver.findElement(By.name("question_starting_points")).clear();
        driver.findElement(By.name("question_starting_points")).sendKeys("1");

        // Checkbox
        if ( !driver.findElement(By.name("randomize_answers")).isSelected() )
        {
            driver.findElement(By.name("randomize_answers")).click();
        }

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("question_type")));
        dropdown.selectByVisibleText("Multiple Choice");

        // Fill input fields
        driver.findElement(By.name("answer_0")).clear();
        driver.findElement(By.name("answer_0")).sendKeys("");
        driver.findElement(By.name("answer_weight_0")).clear();
        driver.findElement(By.name("answer_weight_0")).sendKeys("1");

        // Click background to make sure all java scripts finish running
        driver.findElement(By.cssSelector("body")).click();     // Click on the background to deselect the input field

        // Submit (Add question)
        WebElement element = driver.findElement(By.xpath("(/html/body/div[1]/div/div[2]/form/div[2]/button[@type='submit'])[1]"));
        element.click();

        // Accept alert popup
        try { driver.switchTo().alert().accept(); } catch (NoAlertPresentException e) { }
        //driver.switchTo().alert().accept();

        // Check result (Stays in same page, and question is not created)
        assertFalse(driver.getPageSource().contains("Q: a"));

    }

}


