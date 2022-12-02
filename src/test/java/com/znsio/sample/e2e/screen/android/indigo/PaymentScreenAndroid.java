package com.znsio.sample.e2e.screen.android.indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.businessLayer.jiomeet.AuthBL;
import com.znsio.sample.e2e.screen.web.indigo.PaymentScreen;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class PaymentScreenAndroid extends PaymentScreen {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(AuthBL.class.getName());
    private final Driver driver;
    private final Visual visually;
    private final String SCREEN_NAME = PaymentScreenAndroid.class.getSimpleName();
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";

    public PaymentScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public String getPaymentAmount() {
        LOGGER.info("Payment Page is opened");
        throw new NotImplementedException(SCREEN_NAME + ":" + new Throwable().getStackTrace()[0].getMethodName() + NOT_YET_IMPLEMENTED);
    }

}
