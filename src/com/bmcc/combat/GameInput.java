package com.bmcc.combat;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class GameInput {
    private static String userInput;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] validCommands = {"ATTACK ENEMY", "USE MAGIC", "END GAME"};

    // Ingest user input
    public static String getUserInput(String prompt) throws NoSuchElementException, IllegalStateException {
        try {
            System.out.println(prompt);
            userInput = scanner.nextLine().toUpperCase();
        } catch (NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }
        return userInput;
    }

     // parse user input
    public static boolean parseUserInput(String userInput) {
        boolean isValidInput = false;
        String parsedInput = userInput.trim().toUpperCase();
        if (isValidCommand(parsedInput)) {
            isValidInput = true;
        }
        return isValidInput;
      }

      // check if inputs are valid
      private static boolean isValidCommand(String input) {
        boolean isValid = false;
        for (String validCommand : validCommands) {
            if (input.equals(validCommand)) {
                isValid = true;
            }
        }
        return isValid;
      }


      // looping to check for valid commands
      public static String getCommand() {
        String userInput = getUserInput("Please enter a command from below list:\n" + Arrays.toString(validCommands));
        while (!parseUserInput(userInput)) {
            System.out.println("Valid commands: " + Arrays.toString(validCommands));
            userInput = getUserInput("Please put valid command");
        }
         return userInput;
      }
}