import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.GetRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.request.UpdateRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.GetResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.response.UpdateResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import com.rallydev.rest.util.Ref;

public class UpdateTestResultIntoRally {
    public static void main(String []args) throws IOException, URISyntaxException {
//Connect to the Rally
        RallyRestApi restApi = new RallyRestApi(new URI(
                "https://rally1.rallydev.com"), "_8ohhxYLRSTCUVXVPPeCS2iYdgxWx05DnfJHXZaTas");
        restApi.setApplicationName("QueryExample");
        String workspaceRef = "/workspace/440927704160";


// Query to get the id from Rally for the test case
        QueryRequest testCaseRequest = new QueryRequest("TestCase");
        testCaseRequest.setFetch(new Fetch("FormattedID","Name"));
        testCaseRequest.setWorkspace(workspaceRef);
        testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", "TC2"));
        QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);
        String testCaseRef = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();


       //Add a Test Case Result
        JsonObject newTestCaseResult = new JsonObject();
        newTestCaseResult.addProperty("Verdict", "Pass");
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

      /*  //Update defect
        System.out.println("\nUpdating defect state...");
        JsonObject updatedTestCase = new JsonObject();
        updatedTestCase.addProperty("Verdict", "Fail");
        UpdateRequest updateRequest = new UpdateRequest(testCaseRef, updatedTestCase);
        UpdateResponse updateResponse = restApi.update(updateRequest);
//        obj = updateResponse.getObject();
//        System.out.println(String.format("Updated defect. State = %s", obj.get("State").getAsString()));

        // Query to get the id from Rally for the Defect
        QueryRequest defectRequest = new QueryRequest("Defect");
        defectRequest.setFetch(new Fetch("FormattedID","Name"));
        defectRequest.setWorkspace(workspaceRef);
        defectRequest.setQueryFilter(new QueryFilter("FormattedID", "=", "TC1"));
        QueryResponse defectQueryResponse = restApi.query(defectRequest);
        String defectRef = defectQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();


        //Add a Test Case Result


        JsonObject updatedDefect = new JsonObject();
        updatedDefect.addProperty("State", "Fixed");
        java.util.Date ddate= new java.util.Date();
        SimpleDateFormat dsdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dtimestamp = sdf.format(date);

        updatedDefect.addProperty("Date", dtimestamp);
        updatedDefect.addProperty("Build", "Build");
        updatedDefect.addProperty("Notes", "Selenium Automated Test Run");
        updatedDefect.addProperty("TestCase", testCaseRef);

//        CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
//        CreateResponse createResponse = restApi.create(createRequest);
//        restApi.close();*/
    }
}