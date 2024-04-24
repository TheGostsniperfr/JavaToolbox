import fr.thegostsniperfr.java_toolbox.logger.Logger;

import java.io.File;

public class LoggerTestSuite {
    public static void main(String[] args) {
        Logger logger = new Logger(new File("C:\\Users\\brian\\Desktop\\installJava\\The_Gost_Logger_logs.txt").toPath(), "The_Gost_Logger");
        logger.info("This is an info message.");
        logger.debug("This is an debug message.");
        logger.warn("This is an warn message.");
        logger.err("This is an error message.");

        try {
            int failed = 1/0;
            logger.err("This is ca not be written");

        } catch (Exception e) {
            logger.err(e.getMessage());
        }

        logger.info("This message need to be write.");

    }
}
