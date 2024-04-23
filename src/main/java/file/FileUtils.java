package file;

import hash.HashUtils;
import hash.HashType;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    /**
     * Check if a file is still the same by checking his hash
     *
     * @param absFilePath absolute path to the file
     * @param hash current file hash
     * @param hashType current file algo type
     * @return true is the file is the same, else false
     */
    public static Boolean isSameFileHash(Path absFilePath, String hash, HashType hashType){
        if(!Files.exists(absFilePath) | Files.isDirectory(absFilePath)){
            return false;
        }

        return HashUtils.getHashFromFilePath(absFilePath, hashType).equals(hash);
    }
}
