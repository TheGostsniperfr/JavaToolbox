package testsuite;

import fr.thegostsniperfr.java_toolbox.distribution.OsUtils;

public class OsUtilsTestSuite implements ITestSuite{
    @Override
    public void runTests() {
        System.out.println("Appdata dir path: " + OsUtils.getAppdataDirPath());
    }
}
