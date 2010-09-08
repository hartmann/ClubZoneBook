package no.bekk.webdriver;

import no.bekk.webdriver.pages.EditEventPage;
import no.bekk.webdriver.pages.EventListPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class EventWebTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new HtmlUnitDriver();
        
//        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        driver.get("http://localhost:8080/javazonebook/events");
    }

    @Test
    public void skalLeggeTilEventOgInkludereDenIEventListen() {
        String eventName = "Vorspiel";
        EventListPage eventListPage = new EventListPage(driver);
        EditEventPage editEventPage = eventListPage.chooseAddEvent();
        eventListPage = editEventPage.addEvent(eventName, "Bekk Stand", "Bekk");
        assertTrue(eventListPage.isEventWithNameListed(eventName));
    }

    @Test
    public void skalOppdatereEventMedSted() {
        String eventName = "Stand-up festival";
        String location = "Cafe con Bar";
        EventListPage eventListPage = new EventListPage(driver);
        EditEventPage editEventPage = eventListPage.chooseEditEventWithName(eventName);
        eventListPage = editEventPage.updateLocation(location);
        assertTrue(eventListPage.eventWithNameHasLocation(eventName, location));
    }

    @Test
    public void skalSletteEvent() {
        String eventName = "DJ Mario";
        EventListPage eventListPage = new EventListPage(driver);
        eventListPage.chooseDeleteEventWithName(eventName);
        assertFalse(eventListPage.isEventWithNameListed(eventName));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
