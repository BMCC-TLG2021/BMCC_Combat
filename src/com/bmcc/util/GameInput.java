package com.bmcc.util;

import java.util.*;

import com.bmcc.model.character.Character;

public class GameInput {
    private static String userInput;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] validCommands = {"ATTACK ENEMY", "USE MAGIC", "END GAME"};


    // Ingest user input
    public static String getUserInput(String prompt) throws NoSuchElementException, IllegalStateException {
        try {
            System.out.println(prompt);
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
            if (input.equals(validCommand)) {
                isValid = true;
            }
        }
        return isValid;
    }

    public static boolean isValidCharacter(String input, List<Character> characters) {
        boolean isValidCharacter = false;
        for (Character item : characters) {
            if (item.getName().equals(input)) {
                isValidCharacter = true;
            }
        }
        return isValidCharacter;
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