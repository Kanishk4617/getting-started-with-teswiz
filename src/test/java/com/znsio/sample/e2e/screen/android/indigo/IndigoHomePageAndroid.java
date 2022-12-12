package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.web.indigo.IndigoHomeScreen;
import com.znsio.sample.e2e.screen.windows.web.PaymentScreenWeb;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.znsio.e2e.tools.Wait.waitFor;

public class IndigoHomePageAndroid extends IndigoHomeScreen {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(PaymentScreenWeb.class.getName());

    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = IndigoHomePageAndroid.class.getSimpleName();

    private static final By byContinueAsGuestId = By.id("in.goindigo.android:id/button_as_guest");
    private static final By byGiftVoucherXpath = By.xpath("//android.widget.TextView[contains(@text, 'Gift voucher')]/..");
    private static final By byGetVoucherPageHeaderXpath = By.xpath("//android.view.View[contains(@text, 'Gift Voucher Denomination')]");



    public IndigoHomePageAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String gotoVoucherPage() {
        driver.waitTillElementIsPresent(byContinueAsGuestId).click();;
        driver.waitTillElementIsPresent(byGiftVoucherXpath).click();
        LOGGER.info("Voucher Page is opened");
        String voucherPageHeader = driver.waitTillElementIsPresent(byGetVoucherPageHeaderXpath).getText();
        return voucherPageHeader;
    }
}
