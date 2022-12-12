package com.znsio.sample.e2e.screen.android.indigo;

import Utils.ReusableMethods;
import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.web.indigo.GiftVoucherScreen;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Map;

import static com.znsio.e2e.tools.Wait.waitFor;

public class GiftVoucherScreenAndroid extends GiftVoucherScreen {
    private final Driver driver;
    private final TestExecutionContext context;

    private final Visual visually;
    private static final String SCREEN_NAME = GiftVoucherScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(GiftVoucherScreenAndroid.class.getName());
    private ReusableMethods utils;

    private static final By bySelectDenominationXpath = By.xpath("//android.view.View[@resource-id = 'SelectedVoucherValue']");
    private static final String byDenominationValue = "//android.widget.CheckedTextView[contains(@text, '%s')]";
    private static final By bySelectQuantityXpath = By.xpath("//android.view.View[@resource-id = 'SelectedVoucherQuantity']");
    private static final String byQuantityValue = "//android.widget.CheckedTextView[contains(@text, '%s')]";
    private static final By byCheckPersonalXpath = By.xpath("//android.widget.CheckBox[@text, 'chkPersonal']");
    private static final By byReceipentNameXpath = By.xpath("//android.widget.EditText[@resource-id = 'Per_Fname']");
    private static final By byMessageXpath = By.xpath("//android.widget.EditText[@resource-id = 'Message']");
    private static final By byPreviewButtonXpath = By.xpath("//android.widget.Button[contains(@text, 'Preview')]");
    private static final By byPromocodeXpath = By.xpath("//android.widget.EditText[@resource-id = 'PromoCode']");
    private static final By byReceiverFNameXpath = By.xpath("//android.widget.EditText[@resource-id = 'Rec_Fname']");
    private static final By byReceiverLNameXpath = By.xpath("//android.widget.EditText[@resource-id = 'Rec_Lname']");
    private static final By byReceiverEmailXpath = By.xpath("//android.widget.EditText[@resource-id = 'Rec_EmailID']");
    private static final By byReceiverPhoneXpath = By.xpath("//android.widget.EditText[@resource-id = 'Rec_Phone']");
    private static final By bySenderFNameXpath = By.xpath("//android.widget.EditText[@resource-id = 'Per_Fname']");
    private static final By bySenderLNameXpath = By.xpath("//android.widget.EditText[@resource-id = 'Per_Lname']");
    private static final By bySenderEmailXpath = By.xpath("//android.widget.EditText[@resource-id = 'Per_EmailID']");
    private static final By bySenderPhoneXpath = By.xpath("//android.widget.EditText[@resource-id = 'Per_Phone']");
    private static final By byErrorMssgXpath = By.xpath("//android.view.View[contains(@text, 'Invalid Promo Code.')]");
    private static final By byApplyPromocodeButtonXpath = By.xpath("//android.widget.Button[contains(@text, 'Apply')]");
    private static final By byTnCXpath = By.xpath("//android.widget.CheckBox[@resource-id = 'chkTnC']");
    private static final By byPayNowXpath = By.xpath("//android.widget.Button[contains(@text, 'Pay Now')]");
    private static final By byProceedButtonXpath = By.xpath("//android.widget.Button[contains(@text, 'Proceed')]");
    private static final By byReceipentNamePreviewXpath = By.xpath("(//android.view.View)[3]");
    private static final By byMessagePreviewXpath = By.xpath("(//android.view.View)[4]");
    private static final By byNewPriceXpath = By.xpath("//android.view.View[@resource-id = 'lblTotal']");


    public GiftVoucherScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public GiftVoucherScreen personaliseVoucher(String quantity, String denomination) {
        Map data = (Map) context.getTestState(SAMPLE_TEST_CONTEXT.USER_DETAILS);
        int voucherAmount = Integer.parseInt(quantity) * Integer.parseInt(denomination);
        context.addTestState(SAMPLE_TEST_CONTEXT.VOUCHER_PRICE, String.valueOf(voucherAmount));
        driver.waitTillElementIsPresent(bySelectDenominationXpath).click();;
        driver.findElement(By.xpath(String.format(byDenominationValue, denomination))).click();
        driver.findElement(bySelectQuantityXpath).click();
        driver.findElement(By.xpath(String.format(byQuantityValue, quantity))).click();
        driver.scrollDownByScreenSize();
        driver.findElement(byCheckPersonalXpath).click();
        driver.scrollDownByScreenSize();
        driver.findElement(byReceipentNameXpath).sendKeys(data.get("personName").toString());
        driver.findElement(byMessageXpath).sendKeys(data.get("message").toString());
        LOGGER.info("Voucher is personalised");
        visually.takeScreenshot(SCREEN_NAME, "Voucher is personalised");
        driver.findElement(byPreviewButtonXpath).click();
        return this;
    }

    @Override
    public String getPreviewVoucherDetails() {
        String receipent = driver.waitTillElementIsPresent(byReceipentNamePreviewXpath).getText().trim();
        String message = driver.findElement(byMessagePreviewXpath).getText().trim();
        driver.scrollDownByScreenSize();
        visually.takeScreenshot(SCREEN_NAME, "Preview Voucher");
        driver.findElement(byProceedButtonXpath).click();
        LOGGER.info("Proceed Button clicked on Preview page");
        return receipent + " " + message;
    }

    @Override
    public GiftVoucherScreen fillDeliveryDetails() {
        LOGGER.info("Entering Delivery Details");
        driver.scrollDownByScreenSize();
        driver.waitTillElementIsPresent(byReceiverFNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(10));
        driver.scrollDownByScreenSize();
        driver.findElement(byReceiverLNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(10));
        driver.findElement(byReceiverEmailXpath).sendKeys(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");
        driver.findElement(byReceiverPhoneXpath).sendKeys(RandomStringUtils.randomNumeric(10));
        driver.scrollDownByScreenSize();
        driver.waitTillElementIsPresent(bySenderFNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(10));
        driver.findElement(bySenderLNameXpath).sendKeys(RandomStringUtils.randomAlphabetic(10));
        driver.findElement(bySenderEmailXpath).sendKeys(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");
        driver.findElement(bySenderPhoneXpath).sendKeys(RandomStringUtils.randomNumeric(10));
        visually.checkWindow(SCREEN_NAME, "Delivery details");
        driver.scrollDownByScreenSize();
        driver.waitTillElementIsPresent(byTnCXpath);
        driver.findElement(byTnCXpath).click();
        driver.findElement(byPayNowXpath).click();
        return this;
    }

    @Override
    public String enterInvalidPromocode() {
        LOGGER.info("Entering invalid promocode");
        driver.scrollDownByScreenSize();
        driver.waitTillElementIsPresent(byPromocodeXpath).sendKeys(RandomStringUtils.randomAlphabetic(10));;
        driver.findElement(byApplyPromocodeButtonXpath).click();
        String errMsg = driver.findElement(byErrorMssgXpath).getText();
        visually.checkWindow(SCREEN_NAME, "Screen with invalid promocode");
        return errMsg;
    }

    @Override
    public String getAmountAfterApplyingPromocode() {
        String amountAfterPromocode = driver.findElement(byNewPriceXpath).getText();
        String amount[] = amountAfterPromocode.split(" ");
        return amount[1];
    }
}
