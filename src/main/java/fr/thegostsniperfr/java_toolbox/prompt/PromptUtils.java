package fr.thegostsniperfr.java_toolbox.prompt;

import java.util.Scanner;

public class PromptUtils {
    /**
     * Get bool response from prompt question
     *
     * @param ask Question message to print in prompt
     * @return True on yes or y
     */
    public static Boolean getUserChoice(String ask){
        String rep = getUserRep(ask + " [yes/no]").trim().toLowerCase();
        return rep.equals("yes") || rep.equals("y");
    }

    /**
     * Get response from prompt question
     *
     * @param ask Question message to print in prompt
     * @return Response
     */
    public static String getUserRep(String ask){
        System.out.println(ask);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
