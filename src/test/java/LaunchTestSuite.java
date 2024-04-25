import testsuite.ITestSuite;
import testsuite.LoggerTestSuite;
import testsuite.OsUtilsTestSuite;

import java.util.ArrayList;
import java.util.List;

public class LaunchTestSuite {
    public static void main(String[] args) {
        List<ITestSuite> testSuiteList = new ArrayList<>();
        testSuiteList.add(new LoggerTestSuite());
        testSuiteList.add(new OsUtilsTestSuite());

        testSuiteList.forEach(ITestSuite::runTests);
    }
}
