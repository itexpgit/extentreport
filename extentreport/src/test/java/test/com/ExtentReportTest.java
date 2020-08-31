/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author Uma
 */
public class ExtentReportTest {
    ExtentReports extent;
    ExtentTest logger;
    public ExtentReportTest() {
    }
    
    @BeforeTest
    public void startReport(){
    extent = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
    extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));    
    
    }
    
    @Test
    public void passTest(){
    logger = extent.startTest("passTest");
    Assert.assertTrue(true);
    //To generate the log when the test case is passed
    logger.log(LogStatus.PASS, "Test Case Passed is passTest");
    }

    @Test
    public void failTest(){
    logger = extent.startTest("failTest");
    Assert.assertTrue(true);
    logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
    }
    
    @Test
    public void skipTest(){
    logger = extent.startTest("skipTest");
    throw new SkipException("Skipping - This is not ready for testing ");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @AfterMethod
    public void getResult(ITestResult result){
    if(result.getStatus() == ITestResult.FAILURE){
    logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
    logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
    }else if(result.getStatus() == ITestResult.SKIP){
    logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
    }
    extent.endTest(logger);
    }
    
    @AfterTest
    public void endReport(){
                extent.flush();
                extent.close();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    }
