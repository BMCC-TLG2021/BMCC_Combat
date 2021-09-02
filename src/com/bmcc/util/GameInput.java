package com.bmcc.util;

import java.util.*;

import com.bmcc.model.character.Character;

public class GameInput {
    private static String userInput;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] validCommands = {"ATTACK ENEMY", "USE MAGIC", "END GAME", "SAVE GAME"};
    private static final String[] validSeeVendorCommands = {"GO BATTLE", "SEE VENDOR"};


    // Ingest user input
    public static String getUserInput(String prompt) throws NoSuchElementException, IllegalStateException {
        try {
            System.out.println(ConsoleColors.YELLOW_BOLD + prompt + ConsoleColors.RESET);
            userInput = scanner.nextLine();
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
            if (input.equalsIgnoreCase(validCommand)) {
                isValid = true;
            }
        }
        return isValid;
    }


    // looping to check for valid commands
    public static String getCommand() {
        String userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please enter a command from below list:\n"
                +ConsoleColors.RESET + Arrays.toString(validCommands).toUpperCase());
        while (!parseUserInput(userInput)) {
            System.out.println("Valid commands: " + Arrays.toString(validCommands));
            userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please enter a valid command" + ConsoleColors.RESET);
        }
        return userInput;
    }

    public static String getSeeVendorCommand(){
        String userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Do you want to go directly to next battle, " +
                "or see the vendor to update your equipment? \n"
                +ConsoleColors.RESET + Arrays.toString(validSeeVendorCommands).toUpperCase());
        while (!parseUserInput(userInput)) {
            System.out.println("Valid commands: " + Arrays.toString(validSeeVendorCommands));
            userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please enter a valid command" + ConsoleColors.RESET);
        }
        return userInput;
    }
}