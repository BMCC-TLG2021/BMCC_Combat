package com.bmcc.util;

import java.util.*;

import com.bmcc.controller.Game;
import com.bmcc.model.character.Character;

public class GameInput {
    private static String userInput;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] validCommands = {"ATTACK ENEMY", "USE MAGIC", "END GAME", "SAVE GAME"};
    private static final List<String> validSeeVendorCommands = new ArrayList<>(Arrays.asList("GO BATTLE", "SEE VENDOR", "SAVE GAME"));
    private static final String[] validVendorCommands = {"SELL", "BUY", "EQUIP", "EXIT STORE"};
    private static final String[] equipmentType = {"WEAPON", "ARMOR", "GO BACK"};


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
    public static boolean parseUserInput(String userInput, String[] validCommands) {
        boolean isValidInput = false;
        String parsedInput = userInput.trim().toUpperCase();
        if (isValidCommand(parsedInput, validCommands)) {
            isValidInput = true;
        }
        return isValidInput;
    }

    // check if inputs are valid
    private static boolean isValidCommand(String input, String[] validCommands) {
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
        while (!parseUserInput(userInput, validCommands)) {
            System.out.println("Valid commands: " + Arrays.toString(validCommands));
            userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please enter a valid command" + ConsoleColors.RESET);
        }
        return userInput;
    }

    public static String getSeeVendorCommand() {
        String userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Do you want to go directly to next battle, " +
                "or see the vendor to update your equipment? \n"
                + ConsoleColors.RESET + validSeeVendorCommands).toUpperCase();
        while (!validSeeVendorCommands.contains(userInput)) {
            System.out.println("Valid commands: " + validSeeVendorCommands);
            userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please enter a valid command" + ConsoleColors.RESET);
        }
        return userInput;
    }
    public static String getVendorCommand() {
        String userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please enter a command from below list:\n"
                +ConsoleColors.RESET + Arrays.toString(validVendorCommands).toUpperCase());
        while (!parseUserInput(userInput, validVendorCommands)) {
            System.out.println("Valid commands: " + Arrays.toString(validVendorCommands));
            userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please enter a valid command" + ConsoleColors.RESET);
        }
        return userInput;
    }

    public static String getEquipmentType() {
        String userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please Choose a equipment type from below list or go back to vendor:\n"
                +ConsoleColors.RESET + Arrays.toString(equipmentType).toUpperCase());
        while (!parseUserInput(userInput, equipmentType)) {
            System.out.println("Valid commands: " + Arrays.toString(equipmentType));
            userInput = getUserInput(ConsoleColors.YELLOW_BOLD + "Please enter a valid command" + ConsoleColors.RESET);

        }
         return userInput;
    }

    public static int getUserEquipmentChoice(int listSize) {
        int userChoice = 0;
        while (userChoice < 1 || userChoice > listSize) {
            try {
                String message = String.format("Please choose equipment from the list. (input number only)");
                userChoice = Integer.parseInt(GameInput.getUserInput(message));
                return userChoice;
            } catch (Exception ignored) {

            }
        }
        return userChoice;
    }
}