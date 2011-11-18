package no.bekk.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Created by IntelliJ IDEA.
 * User: vegard
 * Date: 11/1/11
 * Time: 9:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class SlowDriver extends EventFiringWebDriver {

    public static final long DELAY=1500l;

    public SlowDriver(WebDriver driver) {
        super(driver);
        this.register(new WebDriverEventListener() {
            public void beforeNavigateTo(String s, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void afterNavigateTo(String s, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeNavigateBack(WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void afterNavigateBack(WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeNavigateForward(WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void afterNavigateForward(WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void afterClickOn(WebElement webElement, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void beforeScript(String s, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void afterScript(String s, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void onException(Throwable throwable, WebDriver webDriver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
