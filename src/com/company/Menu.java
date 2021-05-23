package com.company;

import java.io.*;

public class Menu {
    public static String l = "LOGIC";
    public static String phrase = "I love to work in global logic !";
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void runProgram() throws IOException {
        while (true) {
            showMenu();

            String i = reader.readLine();
            String temp = null;
            switch (i) {
                case "1":
                    System.out.println("Enter new word: ");
                    temp = reader.readLine();
                    l = checkIfNotEmptyForWord(temp);
                    break;
                case "2":
                    System.out.println("Enter new phrase: ");
                    temp = reader.readLine();
                    phrase = checkIfNotEmptyForPhrase(temp);
                    break;
                case "3":
                    Algorithm.executeAlgorithm(phrase, l);
                    break;
                case "4":
                    System.out.println("Goodbye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("I don't understand!");
                    break;
            }
        }
    }

    private static String checkIfNotEmptyForWord(String i) throws IOException {
        boolean flag = false;
        i = i.replaceAll(" +", "");
        i = i.replaceAll("[^a-zA-Z0-9]", "");
        if (i.equals(""))
            System.out.println("Input can't be empty, null or including special characters!");
        else
            flag = true;

        while (!flag) {
            System.out.println("Enter proper word: ");
            i = reader.readLine();
            i = i.trim().replaceAll(" +", "");
            i = i.replaceAll("[^a-zA-Z0-9]", "");
            if (i.equals(""))
                System.out.println("Input can't be empty, null or including special characters!");
            else
                flag = true;
        }
        return i;
    }

    private static String checkIfNotEmptyForPhrase(String i) throws IOException {
        boolean flag = false;
        i = i.replaceAll("[^a-zA-Z0-9]", " ");
        if (i.equals(""))
            System.out.println("Input can't be empty, null or including special characters!");
        else
            flag = true;

        while (!flag) {
            System.out.println("Enter proper word: ");
            i = reader.readLine();
            i = i.replaceAll("[^a-zA-Z0-9]", " ");
            if (i.equals(""))
                System.out.println("Input can't be empty, null or including special characters!");
            else
                flag = true;
        }
        return i;
    }

    public static void showMenu() {
        System.out.println();
        System.out.println("=== MAIN MENU ===");
        System.out.println("Enter the number and confirm (Enter)");
        System.out.println("1 - Change the word that will be searched in phrase (Now set \"" + l + "\").");
        System.out.println("2 - Change the phrase (Now set \"" + phrase + "\").");
        System.out.println("3 - Run algorithm.");
        System.out.println("4 - Exit the program.");
        System.out.println("==================");
        System.out.println();
    }

}
