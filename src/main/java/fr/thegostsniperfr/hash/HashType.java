package fr.thegostsniperfr.hash;

public enum HashType {
    SHA256("SHA-256"),
    MD5("MD5"),
    SHA1("SHA-1");

    private final String key;

    HashType(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
