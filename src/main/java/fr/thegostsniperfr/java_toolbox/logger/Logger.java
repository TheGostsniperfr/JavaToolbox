package fr.thegostsniperfr.java_toolbox.logger;

import fr.thegostsniperfr.java_toolbox.file.FileUtils;
import fr.thegostsniperfr.java_toolbox.prompt.PromptColors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private final Path logFilePath;
    private final String title;
    private final DateFormat dateFormat;
    private final PrintWriter printWriter;

    public Logger(Path logFilePath, String title) {
        this.logFilePath = logFilePath;
        this.title = title;
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            FileUtils.createFileIfNotExist(this.logFilePath);
            this.printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath.toFile(), false)));
            Runtime.getRuntime().addShutdownHook(new Thread(this.printWriter::close));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void info(String msg) {
        this.writeToLog(msg, "INFO", PromptColors.RESET);
    }

    public void debug(String msg) {
        this.writeToLog(msg, "DEBUG", PromptColors.CYAN);
    }

    public void warn(String msg) {
        this.writeToLog(msg, "WARN", PromptColors.YELLOW);
    }

    public void err(String msg) {
        this.writeToLog(msg, "ERROR", PromptColors.RED);
    }

    private void writeToLog(String msg, String msgType, PromptColors colorCode) {
        String formattedMsg = String.format("[%s] [%s] [%s]: %s",
                this.dateFormat.format(new Date()),
                this.title,
                msgType,
                msg
        );

        System.out.println(colorCode + formattedMsg + PromptColors.RESET);
        this.printWriter.println(formattedMsg);
        this.printWriter.flush();
    }
}
