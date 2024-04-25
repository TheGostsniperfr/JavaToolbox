package testsuite;

import fr.thegostsniperfr.java_toolbox.distribution.ArchType;
import fr.thegostsniperfr.java_toolbox.distribution.OsType;
import fr.thegostsniperfr.java_toolbox.distribution.OsUtils;

public class OsUtilsTestSuite implements ITestSuite{
    @Override
    public void runTests() {
        System.out.println("Appdata dir path: " + OsUtils.getAppdataDirPath());
        System.out.println("OsType: " + OsType.getCurrentOsType());
        System.out.println("ArchType: " + ArchType.getCurrentArchitecture());
    }

}
