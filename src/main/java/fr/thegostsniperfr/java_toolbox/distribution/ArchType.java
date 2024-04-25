package fr.thegostsniperfr.java_toolbox.distribution;

public enum ArchType {
    NOT_SUPPORTED("not_supported"),
    X86("x86"),
    X64("x64"),
    AMD64("amd64"),
    ARM("arm"),
    AARCH64("aarch64"),
    AARCH32("aarch32");

    private final String archType;

    ArchType(String archType) {
        this.archType = archType;
    }

    public String getType() {
        return archType;
    }

    public static ArchType fromString(String archTypeStr) {
        for (ArchType archType : ArchType.values()) {
            if (archType.archType.equalsIgnoreCase(archTypeStr)) {
                return archType;
            }
        }

        throw new IllegalArgumentException("Unknown architecture type: " + archTypeStr);
    }

    public static ArchType getCurrentArchitecture() {
        String osArch = System.getProperty("os.arch").toLowerCase();
        ArchType architecture = ArchType.NOT_SUPPORTED;

        if (osArch.contains("x86")) {
            architecture = X86;
        }
        if (osArch.contains("x64") || osArch.contains("amd64")) {
            architecture = X64;
        }
        if (osArch.contains("arm")) {
            architecture = ARM;
        }
        if (osArch.contains("aarch64")) {
            architecture = AARCH64;
        }
        if (osArch.contains("aarch32")) {
            architecture = AARCH32;
        }

        return architecture;
    }
}
