package no.bekk.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class EventListPage {

    private WebDriver driver;

    public EventListPage(WebDriver driver) {
        this.driver = driver;
    }

    public EditEventPage chooseAddEvent() {
        WebElement addEventLink = driver.findElement(By.name("add-event"));
        addEventLink.click();
        return new EditEventPage(driver);
    }

    public EditEventPage chooseEditEventWithName(String eventName) {
        WebElement row = getRowWithEventName(eventName);
        if (null != row) {
            WebElement editBtn = getEditButtonOfRow(row);
            editBtn.click();
            return new EditEventPage(driver);
        }
        return null;
    }

    public void chooseDeleteEventWithName(String eventName) {
        WebElement row = getRowWithEventName(eventName);
        if (null != row) {
            WebElement editBtn = getDeleteButtonOfRow(row);
            editBtn.click();
        }
    }

    public boolean isEventWithNameListed(String eventName) {
        try {
            getRowWithEventName(eventName);
        } catch (NoSuchElementException nsee) {
            return false;
        }
        return true;

    }

    public boolean eventWithNameHasLocation(String eventName, String location) {
        WebElement row = getRowWithEventName(eventName);
        return rowContainsElementWithText(row, location);
    }

    private WebElement getRowWithEventName(String eventName) {
        List<WebElement> rows = getRowsOfEventList();
        for (WebElement row : rows) {
            if (rowContainsElementWithText(row, eventName)) {
                return row;
            }
        }
        throw new NoSuchElementException("There was no row with event named " + eventName);
    }

    private boolean rowContainsElementWithText(WebElement row, String text) {
        List<WebElement> textElements = row.findElements(By.tagName("span"));
        for (WebElement span : textElements) {
            if (text.equals(span.getText())) {
                return true;
            }
        }
        return false;
    }

    private List<WebElement> getRowsOfEventList() {
        WebElement eventTable = driver.findElement(By.id("eventList"));
        return eventTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
    }

    private WebElement getEditButtonOfRow(WebElement row) {
        return getButtonOfRow("Edit", row);
    }

    private WebElement getDeleteButtonOfRow(WebElement row) {
        return getButtonOfRow("Delete", row);
    }

    private WebElement getButtonOfRow(String buttonSelector, WebElement row) {
        List<WebElement> inputElements = row.findElements(By.tagName("input"));
        for (WebElement input : inputElements) {
            if (input.getValue().startsWith(buttonSelector)) {
                return input;
            }
        }
        return null;
    }
}
