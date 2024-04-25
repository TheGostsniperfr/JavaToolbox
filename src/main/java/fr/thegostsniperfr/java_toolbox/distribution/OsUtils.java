package fr.thegostsniperfr.java_toolbox.distribution;

import java.nio.file.Path;
import java.nio.file.Paths;

public class OsUtils {
    public static Path getAppdataDirPath() {
        String appDataPath = System.getenv("APPDATA");
        if (appDataPath == null) {
            String userHome = System.getProperty("user.home");
            appDataPath = userHome + "/.appdata";
        }

        return Paths.get(appDataPath);
    }
}
