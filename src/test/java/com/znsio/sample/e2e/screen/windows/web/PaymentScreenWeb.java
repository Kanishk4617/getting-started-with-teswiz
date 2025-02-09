package com.znsio.sample.e2e.screen.windows.web;

import Utils.ReusableMethods;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.web.indigo.PaymentScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PaymentScreenWeb extends PaymentScreen {
    private static final Logger LOGGER = Logger.getLogger(PaymentScreenWeb.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = PaymentScreenWeb.class.getSimpleName();
    private static final By actualPayment = By.xpath("//div[@class = 'resp-tab-content resp-tab-content-active']//child::div[@id = 'creditcard_form']//child::div[@id = 'amount']//strong");

    private ReusableMethods utils;
    public PaymentScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getPaymentAmount() {
        visually.takeScreenshot(SCREEN_NAME, "Payment Screen");
        String payment = driver.findElement(actualPayment).getText();
        LOGGER.info("Amount on payment page = "+ payment);
        String amount[] = payment.split(" ");
        return amount[1];
    }


}
