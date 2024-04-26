package fr.thegostsniperfr.java_toolbox.file;

import fr.thegostsniperfr.java_toolbox.hash.HashUtils;
import fr.thegostsniperfr.java_toolbox.hash.HashType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtils {
    /**
     * Check if a file is the same by checking its hash
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

    /**
     * Create a file if it doesn't exist
     *
     * @param filePath File to create
     * @throws IOException
     */
    public static void createFileIfNotExist(Path filePath) throws IOException {
        if(Files.notExists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }
    }

    /**
     * Get all file recursively from a directory
     *
     * @param targetDirPath Target path
     * @return list of files found
     */
    public static List<File> getRecursiveFilesFromDirPath(Path targetDirPath){
        File targetDir = new File(targetDirPath.toUri());
        if(!targetDir.exists() | !targetDir.isDirectory()){
            throw new RuntimeException("Invalid path: " + targetDirPath);
        }

        List<File> listedFile = new ArrayList<>();

        for(File file : Objects.requireNonNull(targetDir.listFiles())){
            if(file.isFile()){
                listedFile.add(file);
                continue;
            }
            listedFile.addAll(getRecursiveFilesFromDirPath(file.toPath().toAbsolutePath()));
        }

        return listedFile;
    }
}
