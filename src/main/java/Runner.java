import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class Runner {

    @Test
    @TestData(
            testId = "TC1",
            tags = {"smoke", "authentication"}
    )
    public void testName()
    {

        Reporter.getCurrentTestResult().setAttribute("TestID",getTestId());
    }

    public String getTestId(){
        return  getClass().getMethods()[0].getAnnotation(TestData.class).testId();
    }


    @AfterMethod
    public void baseTearDown() throws Exception
    {
        //Good place to record test results
        Method testMethod = getClass().getMethod("testName");
        if(testMethod.isAnnotationPresent(TestData.class))
        {
            TestData testData = testMethod.getAnnotation(TestData.class);
            //Do something with testData.testId();
            System.out.println("Test ID = " + testData.testId());
        }

    }
}

