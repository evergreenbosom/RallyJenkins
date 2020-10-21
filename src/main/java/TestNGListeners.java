import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

public class TestNGListeners implements ITestListener {

    public void onTestStart(ITestResult result) {
    }


    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        try {
            try {
                TestPassUpdateRallyPass();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void onTestFailure(ITestResult result) {
       String testName = result.getName();
        try {
            try {
                TestPassUpdateRallyFail();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    public void TestPassUpdateRallyPass() throws IOException, URISyntaxException, NoSuchMethodException {

        //get Test case ID
        String TCID = Reporter.getCurrentTestResult().getAttribute("TestID").toString();

        RallyRestApi restApi = new RallyRestApi(new URI(
                "https://rally1.rallydev.com"), "_8ohhxYLRSTCUVXVPPeCS2iYdgxWx05DnfJHXZaTas");
        restApi.setApplicationName("QueryExample");
        String workspaceRef = "/workspace/440927704160";//Rally Workspace ID


// Query to get the id from Rally for the test case
        QueryRequest testCaseRequest = new QueryRequest("TestCase");
        testCaseRequest.setFetch(new Fetch("FormattedID","Name"));
        testCaseRequest.setWorkspace(workspaceRef);
        testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", TCID));
        QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);
        String testCaseRef = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();

        //Add a Test Case Result
        JsonObject newTestCaseResult = new JsonObject();
        newTestCaseResult.addProperty("Verdict", "Fail");
        java.util.Date date= new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String timestamp = sdf.format(date);
        newTestCaseResult.addProperty("Date", timestamp);
        newTestCaseResult.addProperty("Build", "Build");
        newTestCaseResult.addProperty("Notes", "Selenium Automated Test Run");
        newTestCaseResult.addProperty("TestCase", testCaseRef);
        CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
        CreateResponse createResponse = restApi.create(createRequest);
        restApi.close();
        }

    public void TestPassUpdateRallyFail() throws IOException, URISyntaxException, NoSuchMethodException {

        //get Test case ID
        String TCID = Reporter.getCurrentTestResult().getAttribute("TestID").toString();

        RallyRestApi restApi = new RallyRestApi(new URI(
                "https://rally1.rallydev.com"), "_8ohhxYLRSTCUVXVPPeCS2iYdgxWx05DnfJHXZaTas");
        restApi.setApplicationName("QueryExample");
        String workspaceRef = "/workspace/440927704160";//Rally Workspace ID


// Query to get the id from Rally for the test case
        QueryRequest testCaseRequest = new QueryRequest("TestCase");
        testCaseRequest.setFetch(new Fetch("FormattedID","Name"));
        testCaseRequest.setWorkspace(workspaceRef);
        testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", TCID));
        QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);
        String testCaseRef = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();


        //Add a Test Case Result
        JsonObject newTestCaseResult = new JsonObject();
        newTestCaseResult.addProperty("Verdict", "Fail");
        java.util.Date date= new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String timestamp = sdf.format(date);
        newTestCaseResult.addProperty("Date", timestamp);
        newTestCaseResult.addProperty("Build", "Build");
        newTestCaseResult.addProperty("Notes", "Selenium Automated Test Run");
        newTestCaseResult.addProperty("TestCase", testCaseRef);
        CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
        CreateResponse createResponse = restApi.create(createRequest);
        restApi.close();
    }



    }




