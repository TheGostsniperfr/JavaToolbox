package fr.thegostsniperfr.java_toolbox.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveJson {
    public static void saveJsonAt(JsonObject jsonObject, Path filePath, boolean withIndent){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fileWriter = new FileWriter(filePath.toFile())) {
            if(!Files.exists(filePath.getParent())){
                Files.createDirectories(filePath.getParent());
            }

            JsonWriter jsonWriter = new JsonWriter(fileWriter);

            if(withIndent) {
                jsonWriter.setIndent("\t");
            }

            gson.toJson(jsonObject, jsonWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
