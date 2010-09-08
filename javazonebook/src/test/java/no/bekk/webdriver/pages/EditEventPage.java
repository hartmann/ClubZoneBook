package no.bekk.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EditEventPage {
    private WebDriver driver;

    public EditEventPage(WebDriver driver) {
        this.driver = driver;
    }

    public EventListPage addEvent(String name, String location, String organizer) {
        setEventName(name);
        selectLocation(location);
        selectOrganizer(organizer);
        save();
        return new EventListPage(driver);
    }

    private void setEventName(String name) {
        driver.findElement(By.name("description")).sendKeys(name);
    }

    public EventListPage updateLocation(String location) {
        selectLocation(location);
        save();
        return new EventListPage(driver);
    }

    private void selectLocation(String location) {
        selectOptionFromSelectList(location, "location");
    }

    private void selectOrganizer(String organizer) {
        selectOptionFromSelectList(organizer, "organizer");
    }

    private void selectOptionFromSelectList(String optionValue, String selectList) {
        WebElement locationSelectList = driver.findElement(By.name(selectList));
        List<WebElement> options = locationSelectList.findElements(By.tagName("option"));
        boolean found = false;
        for (WebElement option : options) {
            if (option.getText().equals(optionValue)) {
                found = true;
                option.setSelected();
                continue;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Could not find the specified option: " + optionValue);
        }
    }

    private void save() {
        driver.findElement(By.name("save")).click();
    }
}
