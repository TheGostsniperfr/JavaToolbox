package fr.thegostsniperfr.java_toolbox.distribution;

public enum OsType {
    NOT_SUPPORTED("not_supported"),
    WINDOWS("windows"),
    MACOS("macos"),
    LINUX("linux"),
    SOLARIS("solaris");

    private final String osType;

    OsType(String osType) {
        this.osType = osType;
    }

    public String getType() {
        return osType;
    }

    public static OsType getCurrentOsType() {
        String osName = System.getProperty("os.name").toLowerCase();
        OsType currentOsType = OsType.NOT_SUPPORTED;

        if(osName.contains("win")) {
            currentOsType = WINDOWS;
        }

        if(osName.contains("mac")) {
            currentOsType = MACOS;
        }

        if(osName.contains("nix")
            || osName.contains("nux")
            || osName.contains("aix"))
        {
            currentOsType = LINUX;
        }

        if(osName.contains("sunos")) {
            currentOsType = SOLARIS;
        }

        return currentOsType;
    }
}
