package fr.thegostsniperfr.java_toolbox.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoadJson {
    private static final int BUFFER_SIZE = 4096;

    public static JsonObject loadJsonFromStream(InputStream inputStream) {
        try {
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Gson gson = new Gson();
                return gson.fromJson(response.toString(), JsonObject.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonObject loadJsonFromFilePath(Path filePath){
        try {
            return loadJsonFromStream(Files.newInputStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonObject loadJsonFromUrl(URL url)  {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return loadJsonFromStream(conn.getInputStream());
            } else {
                throw new RuntimeException("Failed to load json from URL. Response code: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonObject loadJsonFromUrl(String url){
        try{
            return loadJsonFromUrl(new URI(url).toURL());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
