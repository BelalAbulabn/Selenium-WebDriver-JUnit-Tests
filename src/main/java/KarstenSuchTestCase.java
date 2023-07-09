
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KarstenSuchTestCase {
    public WebDriver driver ;
    private String eachKarosseRow= "//div[@class='skw-card-header-text']";
    private String FirstFilter = "//label[contains(@class,skw-search-facet)][1]";
    private String SecondFilter = "//label[contains(@class,skw-search-facet)][2]";
    private String Removebutton ="//button[descendant::i/@class='fa fa-times-circle']";
    private String Randomfilter(){

        Random rand = new Random();
        int rand_int = rand.nextInt(100);
        String RandomlFiter = "//label[contains(@class,skw-search-facet)]["+Integer.toString(10)+"]";
        return RandomlFiter;
    }
    private String sidebarLink(String name) {
        return String.format("//skw-sidebar//a[normalize-space()=\"%s\"]", name);
    }
    private String ButtonClickSkw(String name) {

        return String.format("//skw-button[.=\"%s\"]", name);
    }
    private List<String> ReadAllRow(String RowKind) {
        List<WebElement> ListOfKarrossenattributeAfterSorting = driver.findElements(By.xpath(RowKind));
        List<String> StringArrayListafterSorting = new ArrayList<String>();
        for (WebElement eachRow : ListOfKarrossenattributeAfterSorting) {

            StringArrayListafterSorting.add(eachRow.getAttribute("textContent"));
        }
        return StringArrayListafterSorting;
    }
    private void login() throws InterruptedException {
        driver.findElement(By.id("mat-input-0")).sendKeys("admin");
        driver.findElement(By.id("mat-input-1")).sendKeys("admin");
        driver.findElement(By.xpath(ButtonClickSkw(" Anmelden "))).click();
        Thread.sleep(50);

    }
    private int NumberOfCilckLoadMoreButton() {
        return ReadAllRow(eachKarosseRow).size()/10;
    }

    private int NumberOfKaroossen() {
        return ReadAllRow(eachKarosseRow).size();
    }

    private boolean LoadMoref() throws InterruptedException {
        if ((ReadAllRow(eachKarosseRow).size())  == 10 || (ReadAllRow(eachKarosseRow).size())  == 20|| (ReadAllRow(eachKarosseRow).size())  == 30 || (ReadAllRow(eachKarosseRow).size())  == 40)
        {
            Thread.sleep(50);
            driver.findElement(By.xpath(ButtonClickSkw("Weitere Treffer laden"))).click();
            return true;
        }
        else
        {
            return false;
        }
    }
    private void LoadAllKarosse() throws InterruptedException {
        while (LoadMoref()==true)
        {
            Thread.sleep(50);
        }
    }

    public  void loge_in_GO_to_Karossensuch () throws InterruptedException {
        login();
        String Go_to= "Karossensuche";
        driver.findElement(By.xpath(sidebarLink(Go_to))).click();
        Thread.sleep(50);
    }
    @Before
    public  void setupDriver()
    {
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
    }
    @Test
    public  void LogeInGOToKarossensuche () throws InterruptedException {

        loge_in_GO_to_Karossensuch();
        Thread.sleep(50);
        Assert.assertEquals(driver.findElement(By.xpath("//*[text()=' Karossensuche 'and @class ='skw-page-heading-content' ] ")).getText(),"Karossensuche");
    }
    @Test
    public  void  LoadMore() throws InterruptedException {
        boolean checkButton= false;
        loge_in_GO_to_Karossensuch();
        Thread.sleep(50);
        LoadAllKarosse();

        if((NumberOfCilckLoadMoreButton())>0)
        {
            checkButton=true;
        }
        Assert.assertEquals(true,checkButton);
    }
    @Test
    public  void SucheFilter () throws InterruptedException {
        loge_in_GO_to_Karossensuch();
        boolean checkButton= false;
        int CheckvalueBeforFilter=0;
        int CheckvalueAfterFilter=0;
        Thread.sleep(50);
        LoadAllKarosse();
        CheckvalueBeforFilter=NumberOfKaroossen();
        System.out.println(CheckvalueBeforFilter);
        Thread.sleep(500);
        driver.findElement(By.xpath(FirstFilter)).click();
        LoadAllKarosse();
        Thread.sleep(50);
        CheckvalueAfterFilter=NumberOfKaroossen();
        System.out.println(CheckvalueAfterFilter);
        if( CheckvalueAfterFilter<CheckvalueBeforFilter)
        {
            checkButton=true;
        }
        Assert.assertEquals(true,checkButton);

    }
    @Test
    public  void SucheFilter2 () throws InterruptedException {
        loge_in_GO_to_Karossensuch();
        boolean checkButton= false;
        int CheckvalueBeforFilter=0;
        int CheckvalueAfterFilter=0;
        Thread.sleep(50);
        LoadAllKarosse();
        CheckvalueBeforFilter=NumberOfKaroossen();
        System.out.println(CheckvalueBeforFilter);
        Thread.sleep(50);
        driver.findElement(By.xpath(SecondFilter)).click();
        LoadAllKarosse();
        Thread.sleep(50);
        CheckvalueAfterFilter=NumberOfKaroossen();
        System.out.println(CheckvalueAfterFilter);
        if( CheckvalueAfterFilter<CheckvalueBeforFilter)
        {
            checkButton=true;
        }
        Assert.assertEquals(true,checkButton);


    }
    @Test
    public  void RandomFilterSuche () throws InterruptedException {

        loge_in_GO_to_Karossensuch();
        boolean checkButton= false;
        int CheckvalueBeforFilter=0;
        int CheckvalueAfterFilter=0;
        Thread.sleep(50);
        LoadAllKarosse();
        CheckvalueBeforFilter=NumberOfKaroossen();
        Thread.sleep(500);
        driver.findElement(By.xpath(Randomfilter())).click();
        Thread.sleep(500);
        LoadAllKarosse();
        Thread.sleep(500);
        CheckvalueAfterFilter=NumberOfKaroossen();
        if( CheckvalueAfterFilter<CheckvalueBeforFilter)
        {
            checkButton=true;
        }
        Assert.assertEquals(true,checkButton);


    }
    @Test
    public void setRemovebutton() throws InterruptedException {

        loge_in_GO_to_Karossensuch();
        boolean checkButton= false;
        int CheckvalueBeforFilter=0;
        int CheckvalueAfterFilter=0;
        int CheckvalueAfterRemoveFilter=0;
        Thread.sleep(50);
        LoadAllKarosse();
        CheckvalueBeforFilter=NumberOfKaroossen();
        Thread.sleep(50);
        driver.findElement(By.xpath(FirstFilter)).click();
        Thread.sleep(50);
        LoadAllKarosse();
        Thread.sleep(50);
        CheckvalueAfterFilter=NumberOfKaroossen();
        Thread.sleep(50);
        driver.findElement(By.xpath(Removebutton)).click();
        Thread.sleep(500);
        LoadAllKarosse();
        CheckvalueAfterRemoveFilter=NumberOfKaroossen();
        Thread.sleep(50);
        if( CheckvalueAfterFilter<CheckvalueBeforFilter && CheckvalueBeforFilter==CheckvalueAfterRemoveFilter)
        {
            checkButton=true;
        }

        Assert.assertEquals(true,checkButton);

    }
    @Test
    public void setTwoFilterRemoveButton() throws InterruptedException {
        loge_in_GO_to_Karossensuch();
        boolean checkButton= false;
        int CheckvalueBeforFilter=0;
        int CheckvalueAfterFilter=0;
        int CheckvalueAfterRemoveFilter=0;
        Thread.sleep(50);
        LoadAllKarosse();
        CheckvalueBeforFilter=NumberOfKaroossen();
        Thread.sleep(50);
        driver.findElement(By.xpath(FirstFilter)).click();
        Thread.sleep(50);
        driver.findElement(By.xpath(SecondFilter)).click();
        Thread.sleep(50);
        LoadAllKarosse();
        Thread.sleep(50);
        CheckvalueAfterFilter=NumberOfKaroossen();
        Thread.sleep(50);
        driver.findElement(By.xpath(Removebutton)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(Removebutton)).click();
        Thread.sleep(500);
        LoadAllKarosse();
        CheckvalueAfterRemoveFilter=NumberOfKaroossen();
        Thread.sleep(50);
        if( CheckvalueAfterFilter<CheckvalueBeforFilter && CheckvalueBeforFilter==CheckvalueAfterRemoveFilter)
        {
            checkButton=true;
        }

        Assert.assertEquals(true,checkButton);

    }
    @Test
    public void TwoClickOnOneFilter() throws InterruptedException {

        loge_in_GO_to_Karossensuch();
        boolean checkButton= false;
        int CheckvalueBeforFilter=0;
        int CheckvalueAfterFilter=0;
        int CheckvalueAfterRemoveFilter=0;
        Thread.sleep(50);
        LoadAllKarosse();
        CheckvalueBeforFilter=NumberOfKaroossen();
        Thread.sleep(50);
        driver.findElement(By.xpath(FirstFilter)).click();
        Thread.sleep(50);
        LoadAllKarosse();
        Thread.sleep(50);
        CheckvalueAfterFilter=NumberOfKaroossen();
        Thread.sleep(50);
        driver.findElement(By.xpath(FirstFilter)).click();
        Thread.sleep(500);
        LoadAllKarosse();
        CheckvalueAfterRemoveFilter=NumberOfKaroossen();
        Thread.sleep(50);
        if( CheckvalueAfterFilter<CheckvalueBeforFilter && CheckvalueBeforFilter==CheckvalueAfterRemoveFilter)
        {
            checkButton=true;
        }

        Assert.assertEquals(true,checkButton);

    }
    @After
    public  void close()
    {
        driver.close();

    }
}