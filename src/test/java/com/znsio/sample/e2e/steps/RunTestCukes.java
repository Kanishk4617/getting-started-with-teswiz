package com.znsio.sample.e2e.steps;

import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.entities.APPLITOOLS;
import com.znsio.e2e.entities.TEST_CONTEXT;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.steps.Hooks;
import com.znsio.sample.e2e.entities.SAMPLE_TEST_CONTEXT;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import java.util.Map;

public class RunTestCukes
        extends AbstractTestNGCucumberTests {
    private static final Logger LOGGER = Logger.getLogger(RunTestCukes.class.getName());
    private final TestExecutionContext context;

    public RunTestCukes() {
        long threadId = Thread.currentThread()
                              .getId();
        LOGGER.info("RunTestCukes constructor: ThreadId: " + threadId);
        context = SessionContext.getTestExecutionContext(threadId);
        System.setProperty(TEST_CONTEXT.TAGS_TO_EXCLUDE_FROM_CUCUMBER_REPORT, "@android,@web,@prod,@sit,@eat,@uat,@qa");
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        LOGGER.info(String.format("ThreadID: %d: in overridden scenarios%n", Thread.currentThread()
                                                                                   .getId()));
        Object[][] scenarios = super.scenarios();
        LOGGER.info(scenarios);
        return scenarios;
    }

    @Before
    public void beforeTestScenario(Scenario scenario) {
        LOGGER.info(String.format("ThreadID: %d: in overridden beforeTestScenario%n", Thread.currentThread()
                                                                                            .getId()));
        new Hooks().beforeScenario(scenario);
        Configuration ufgConfig = new Configuration();
        ufgConfig.addBrowser(1024, 1024, BrowserType.CHROME);
        ufgConfig.addBrowser(1024, 1024, BrowserType.FIREFOX);
        ufgConfig.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
        ufgConfig.addDeviceEmulation(DeviceName.OnePlus_7T_Pro, ScreenOrientation.LANDSCAPE);
        context.addTestState(APPLITOOLS.UFG_CONFIG, ufgConfig);
        initialDataSetup();
    }

    @After
    public void afterTestScenario(Scenario scenario) {
        LOGGER.info(String.format("ThreadID: %d: in overridden afterTestScenario%n", Thread.currentThread()
                                                                                           .getId()));
        new Hooks().afterScenario(scenario);
    }

    private void initialDataSetup() {
        Map<String,String> testData = Runner.getTestDataAsMap(System.getProperty("user.name"));
        context.addTestState(SAMPLE_TEST_CONTEXT.USER_DETAILS, testData);
    }
}
