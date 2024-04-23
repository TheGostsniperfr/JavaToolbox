package hash;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.zip.CRC32;

public class HashUtils {

    public static String getHashFromFilePath(Path filePath, HashType algoType){
        try {
            byte[] data = Files.readAllBytes(filePath);
            byte[] hash = MessageDigest.getInstance(algoType.getKey()).digest(data);
            return new BigInteger(1, hash).toString(16);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static HashType getAlgoTypeByName(String algoName){
        for(HashType algoType : HashType.values()){
            if(algoType.getKey().equals(algoName)){
                return algoType;
            }
        }

        throw new IllegalArgumentException(algoName);
    }

    public static long getCRC32(Path filePath) {
        CRC32 crc = new CRC32();
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                crc.update(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return crc.getValue();
    }
}
