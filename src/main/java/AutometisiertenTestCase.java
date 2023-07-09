import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutometisiertenTestCase {
    public WebDriver driver;

    // Server URL set as environment variable for security
    String serverURL = System.getenv("SERVER_URL");

    String XpathInput ="//skw-form-input//*[contains(@class, skw-input-label) and .='Attribut-Name:']";
    String XpathInputQuellfeld ="//*[@id='mat-select-1']";
    String XpathSotierungButton ="//skw-simple-table//*[contains(@class,skw-th) and .='Attribut-Name']";
    String XpathAddButton ="//skw-button[descendant::i/@class='fa fa-plus']";
    String XpathChangeLanguage ="//skw-language//*[contains(@class,skw-language-select-icon)]";

    public static String ButtonClickSkw(String name) {
        return String.format("//skw-button[.=\"%s\"]", name);
    }

    private String ButtonClick(String name) {
        return String.format("//button[.=\"%s\"]", name);
    }

    private String sidebarLink(String name) {
        return String.format("//skw-sidebar//a[normalize-space()=\"%s\"]", name);
    }

    private String SelectAll()
    {
        String s = Keys.chord(Keys.CONTROL, "a");
        return s;
    }
    private List<String> Readall()
    {
        List <WebElement> ListOfKarrossenattributeAfterSorting =driver.findElements(By.xpath("//div[@class='skw-tr selectable ng-star-inserted']"));
        List<String> StringArrayListafterSorting = new ArrayList<String>();
        for (WebElement eachLink :ListOfKarrossenattributeAfterSorting){

            StringArrayListafterSorting.add(eachLink.getAttribute("textContent"));
        }
        return StringArrayListafterSorting;
    }
    private String RandomAttributName()
    {
        Random rand = new Random();
        int rand_int = rand.nextInt(100);
        String RandomName = "Test"+Integer.toString(rand_int);
        return RandomName;
    }
    private String RandomQuelLFeld()
    {
        Random rand2 = new Random();
        int rand2_int2 = rand2.nextInt(24)+1;

        String RandomQuellfeld= "//*[@class='mat-option ng-star-inserted' and @id ='mat-option-"+Integer.toString(rand2_int2)+"']";
        return RandomQuellfeld;
    }
    private String RandomAttributNameSuche() throws InterruptedException {
        Random rand = new Random();
        int randomsearch = rand.nextInt(Readall().size());
        Thread.sleep(500);
        System.out.println( Readall().get(randomsearch));
        String ErsteDreibuchstabeAttributName=Readall().get(randomsearch).charAt(0)+ ""+ Readall().get(randomsearch).charAt(1)+Readall().get(randomsearch).charAt(2);
        return ErsteDreibuchstabeAttributName;
    }
    private void login() throws InterruptedException {
        driver.findElement(By.id("mat-input-0")).sendKeys("admin");
        driver.findElement(By.id("mat-input-1")).sendKeys("admin");
        driver.findElement(By.xpath(ButtonClickSkw(" Anmelden "))).click();
        Thread.sleep(50);


    }
    @BeforeAll
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    // TearDown method to close the driver after all tests
    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login() throws InterruptedException {
        driver.findElement(By.id("mat-input-0")).sendKeys("admin");
        driver.findElement(By.id("mat-input-1")).sendKeys("admin");
        driver.findElement(By.xpath(ButtonClickSkw(" Anmelden "))).click();
        Thread.sleep(50);
    }

    // Test methods
    // ... (Same as your code)

    // For example
    @Test
    public void logIn() throws InterruptedException {
        driver.get(serverURL + "/#/login");
        login();
        Assertions.assertEquals("start", driver.getTitle());
    }

    @Test
    public  void KarossenattributeButton() throws InterruptedException {
        login();
        String goTo= "Karossenattribute";

        driver.findElement(By.xpath(sidebarLink(goTo))).click();
        Thread.sleep(50);
        String getHeading =driver.findElement(By.xpath("//*[text()=' Karossenattribute 'and @class ='skw-page-heading-content']")).getText();
        Assertions.assertEquals(getHeading,goTo);

    }
    @Test
    public  void KarossensucheButton () throws InterruptedException {

        login();
        String goTo= "Karossensuche";

        driver.findElement(By.xpath(sidebarLink(goTo))).click();
        Thread.sleep(50);
        String getHeading =driver.findElement(By.xpath("//*[text()=' Karossensuche 'and @class ='skw-page-heading-content' ] ")).getText();
        Assertions.assertEquals(getHeading,goTo);

    }
    @Test
    public  void DashboardButton  () throws InterruptedException {

        login();
        String goTo= "Dashboard";

        driver.findElement(By.xpath(sidebarLink(goTo))).click();
        Thread.sleep(50);
        String getHeading =driver.findElement(By.xpath("//*[text()=' Willkommen Administrator 'and @class ='skw-page-heading-content' ] ")).getText();
        Assertions.assertEquals(getHeading,"Willkommen Administrator\n"+"Aktualisieren");

    }
    @Test
    public  void ChangeLanguage() throws InterruptedException {
        login();
        String Go_to= "Karossenattribute";
        Thread.sleep(50);
        driver.findElement(By.xpath(sidebarLink(Go_to))).click();
        Thread.sleep(50);
        driver.findElement(By.xpath(XpathChangeLanguage)).click();
        Thread.sleep(50);
        driver.findElement(By.xpath(ButtonClick("English"))).click();

        Thread.sleep(50);
        String getHeading =driver.findElement(By.xpath("//*[text()=' Carbody attributes 'and @class ='skw-page-heading-content']")).getText();
        Assertions.assertEquals(getHeading,"Carbody attributes");

    }



    @Test
    public void AttributHinzufügen() throws InterruptedException {
        int Checkvalue=0;
		/*
		Login part
		 */
        login();
        Thread.sleep(50);

        driver.findElement(By.xpath(sidebarLink("Karossenattribute"))).click();
        Thread.sleep(50);
		/*
		  add new Karossanttribute

		 */
        driver.findElement(By.xpath(XpathAddButton)).click();
		/*
			 create Random Karossanttribute
		 */
        String AttributName =RandomAttributName();
        driver.findElement(By.cssSelector(".skw-string-input")).sendKeys(AttributName);
        driver.findElement(By.xpath(XpathInput)).click();
        Thread.sleep(50);
        driver.findElement(By.xpath(XpathInputQuellfeld)).click();
        Thread.sleep(50);

        driver.findElement(By.xpath(RandomQuelLFeld())).click();
        Thread.sleep(50);
        driver.findElement(By.xpath(ButtonClickSkw("Speichern"))).click();
        Thread.sleep(50);
		/*
			read all Karossenattribute in List Array
			covert Attribut_name to  list of String
 		*/

        Thread.sleep(500);
        for (String str :Readall())
        {
            if (str.matches(AttributName+"(.*)")== true)
            {
                Checkvalue=1;
            }

        }

        Assertions.assertEquals(1,Checkvalue);

    }
    @Test
    public void AtrributHinzufgenUndLöschen() throws InterruptedException {
        int Checkvalue=0;

		/*
		Login part
		 */
        login();
        Thread.sleep(50);

        driver.findElement(By.xpath(sidebarLink("Karossenattribute"))).click();
        Thread.sleep(50);
		/*
		  add new Karossanttribute

		 */

        driver.findElement(By.xpath(XpathAddButton)).click();

		/*
			 create Random Karossanttribute
		 */

        String AttrubitName = RandomAttributName();
        driver.findElement(By.cssSelector(".skw-string-input")).sendKeys(AttrubitName);
        driver.findElement(By.xpath(XpathInput)).click();
        Thread.sleep(50);
        driver.findElement(By.xpath(XpathInputQuellfeld)).click();
        Thread.sleep(50);
        driver.findElement(By.xpath(RandomQuelLFeld())).click();
        Thread.sleep(50);
        Thread.sleep(50);
		/*
			read all Karossenattribute in List Array
			covert Attribut_name to  list of String
 		*/

        Thread.sleep(500);


        for (String str :Readall())
        {
            if (str.matches(AttrubitName+"(.*)")== true)
            {
                Checkvalue=2;
            }

        }
        if (Checkvalue==2){
            Thread.sleep(50);
            driver.findElement(By.xpath("//*[text()='"+AttrubitName+"' and @class ='ng-star-inserted' ]/..")).click();
            Thread.sleep(500);

            driver.findElement(By.xpath(ButtonClickSkw("Löschen"))).click();
            Thread.sleep(500);

            driver.findElement(By.xpath(ButtonClickSkw("Bestätigen"))).click();
            Thread.sleep(500);
            for (String str :Readall())
            {
                if (str.matches(AttrubitName+"(.*)")== true)
                {
                    Checkvalue=0;
                }
                else {
                    Checkvalue=1;
                }
            }

        }


        Assertions.assertEquals(1,Checkvalue);

    }
    @Test
    public void RheinfolgeAttributName() throws InterruptedException {
		/*
		Login part
		 */
        login();
		/*
		 Go to Karossenattribute
		 */
        String goTo= "Karossenattribute";
        driver.findElement(By.xpath(sidebarLink(goTo))).click();
        Thread.sleep(500);
		/*
			read all Karossenattribute in List Array
			covert Attribut_name to  list of String
 		*/

        List<String> ListBevorSorting =Readall();
		/*
			Sorting of Array
		 */


        Collections.sort(ListBevorSorting);

        System.out.println(ListBevorSorting);
		/*
			Click on the sort button
		 */
        Thread.sleep(500);
        driver.findElement(By.xpath(XpathSotierungButton)).click();
        Thread.sleep(500);
		/*
			read all Karossenattribute in List Array again
			covert Attribut_name to  list of String
		 */

        List<String> ListafterSorting = Readall();
		/*
			Comparing  the after sorting  to check if the sorting button works
		*/
        Assertions.assertEquals(ListafterSorting,ListBevorSorting);

    }

    @Test
    public void RheinfolgeAttribut() throws InterruptedException {
		/*
		Login part
		 */
        login();
		/*
		 Go to Karossenattribute
		 */
        String goTo= "Karossenattribute";
        driver.findElement(By.xpath(sidebarLink(goTo))).click();
        Thread.sleep(500);
		/*
			read all Karossenattribute in List Array
			covert Attribut_name to  list of String
 		*/

        List<String> ListBevorSorting =Readall();

		/*
			Click on the sort button
		 */
        Thread.sleep(500);
        driver.findElement(By.xpath(XpathSotierungButton)).click();
        Thread.sleep(500);
		/*
			read all Karossenattribute in List Array again
			covert Attribut_name to  list of String
		 */

        List<String> ListafterSorting = Readall();
		/*
			Comparing  the after sorting  to check if the sorting button works
		*/
        Assertions.assertNotEquals(ListafterSorting,ListBevorSorting);

    }
    @Test
    public  void SucheRandomAtrubittName() throws InterruptedException {

        String SearchInput = ".search-input";
        login();
        String Go_to= "Karossenattribute";
        driver.findElement(By.xpath(sidebarLink(Go_to))).click();
        Thread.sleep(50);
        driver.findElement(By.cssSelector(SearchInput)).click();
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(Keys.ENTER);
        // enter text then ctrl+a with Keys.chord()
        WebElement l = driver.findElement(By.cssSelector(SearchInput));
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(SelectAll());
        driver.findElement(By.cssSelector(".search-input")).sendKeys(Keys.DELETE);
        Thread.sleep(500);

        driver.findElement(By.cssSelector(SearchInput)).sendKeys(RandomAttributNameSuche()+"");
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(Keys.ENTER);
        Thread.sleep(500);
        int CheckValue =Readall().size() ;

        Assertions.assertNotEquals(0,CheckValue);

    }
    @Test
    public  void SucheNichtExsitierenderName () throws InterruptedException {
        String SearchInput = ".search-input";
        login();
        String goTo= "Karossenattribute";
        driver.findElement(By.xpath(sidebarLink(goTo))).click();
        Thread.sleep(50);
        driver.findElement(By.cssSelector(SearchInput)).click();
        Thread.sleep(50);
        driver.findElement(By.cssSelector(SearchInput)).sendKeys("*\'*");
        driver.findElement(By.cssSelector(SearchInput)).sendKeys("*\'*");
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(Keys.ENTER);
        Thread.sleep(500);
        int CheckValue=Readall().size();
        Assertions.assertEquals(0,CheckValue);

    }
    @Test
    public  void SucheSchreibenUndLöschen() throws InterruptedException {
        int x,y;

        String SearchInput = ".search-input";
        login();
        Thread.sleep(500);
        String goTo= "Karossenattribute";
        driver.findElement(By.xpath(sidebarLink(goTo))).click();
        Thread.sleep(500);
        x=Readall().size();
        //driver.findElement(By.cssSelector(".search-input")).sendKeys(Keys.CLEAR);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(Keys.DELETE);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(SearchInput)).sendKeys("Test");
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(Keys.CLEAR);
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(Keys.ENTER);
        // enter text then ctrl+a with Keys.chord()
        WebElement l = driver.findElement(By.cssSelector(SearchInput));
        driver.findElement(By.cssSelector(SearchInput)).sendKeys(SelectAll());
        driver.findElement(By.cssSelector(".search-input")).sendKeys(Keys.DELETE);
        Thread.sleep(500);
        y=Readall().size();
        Thread.sleep(500);
        Assertions .assertEquals(x,y);

    }
}
