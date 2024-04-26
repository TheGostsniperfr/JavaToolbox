package fr.thegostsniperfr.java_toolbox.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirUtils {
    /**
     * Create a directories if it doesn't exist
     *
     * @param path Directory path
     */
    public static void createDirIfNotExist(Path path){
        if(!Files.exists(path)){
            try {
                if(Files.isDirectory(path)){
                    Files.createDirectories(path);
                } else {
                    Files.createDirectories(path.getParent());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Check if a directory exist
     *
     * @param targetDirPath Dir path to check
     * @return true if exist
     */
    public static Boolean isValidTargetDirPath(Path targetDirPath){
        return Files.exists(targetDirPath) && Files.isDirectory(targetDirPath);
    }

    /**
     * Check if a directory exist
     *
     * @param targetDirStrPath Dir path to check
     * @return true if exist
     */
    public static Boolean isValidTargetDirPath(String targetDirStrPath){
        return isValidTargetDirPath(Paths.get(targetDirStrPath));
    }
}
