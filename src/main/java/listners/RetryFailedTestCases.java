package listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {

    private int retryCnt = 0;

    /**
     * You can mention maxRetryCnt (Maximium Retry Count) as per your requirement.
     * This example uses 2 which means that for any failed testcases it retires the run twice.
     */

    private int maxRetryCnt = 2;

    /**
     * This method will be called everytime a test fails.
     * It will return TRUE if a test fails and need to be retried, else it returns FALSE
     */

    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {                      //Check if test not succeed
            if (retryCnt < maxRetryCnt) {                            //Check if maxtry count is reached
                retryCnt++;                                     //Increase the maxTry count by 1
                result.setStatus(ITestResult.FAILURE);  //Mark test as failed
                return true;                                 //Tells TestNG to re-run the test
            } else {
                result.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
}
