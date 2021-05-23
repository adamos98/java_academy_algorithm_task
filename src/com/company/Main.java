package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

class lengthComparator implements Comparator<KeyWord> {
    @Override
    public int compare(KeyWord o1, KeyWord o2) {
        return Double.compare(o1.length, o2.length);
    }
}

class frequencyComparator implements Comparator<KeyWord> {
    @Override
    public int compare(KeyWord o1, KeyWord o2) {
        return Double.compare(o1.frequency, o2.frequency);
    }
}

class logicComparator extends Main implements Comparator<KeyWord> {
    @Override
    public int compare(KeyWord o1, KeyWord o2) {
        final String word = getL().toLowerCase(Locale.ROOT);
        Character[] co1 = new Character[o1.length];
        Character[] co2 = new Character[o2.length];
        co1 = o2.characters.toArray(co1);
        co2 = o1.characters.toArray(co2);
        return word.indexOf(co1[0]) - word.indexOf(co2[0]);
    }
}

class KeyWord{

    int countOfAppear;
    double frequency;
    LinkedHashSet<Character> characters;
    int length;

    public KeyWord(int countOfAppear, double frequency, LinkedHashSet<Character> characters,int length) {
        this.countOfAppear = countOfAppear;
        this.frequency = frequency;
        this.characters = characters;
        this.length = length;
    }

}


public class Main {
    public static String l = "LOGIC";

    public String getL() {
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String phrase = "I love to work in global logic !";

        /**
         * Main menu
         */
        while(true){

            System.out.println();
            System.out.println("=== MAIN MENU ===");
            System.out.println("Enter the number and confirm (Enter)");
            System.out.println("1 - Change the word that will be searched in phrase. (Now set \""+ l + "\")");
            System.out.println("2 - Change the phrase. (Now set \"" + phrase + "\")");
            System.out.println("3 - Run algorithm.");
            System.out.println("4 - Exit the program.");
            System.out.println("==================");
            System.out.println();
            String i = reader.readLine();
            switch (i){
                case "1":
                    System.out.println("Enter new word: ");
                    l = reader.readLine();
                    break;
                case "2":
                    System.out.println("Enter new phrase: ");
                    phrase = reader.readLine();
                    break;
                case "3":
                    algorithm(phrase,l);
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

    public static void algorithm(String phrase, String word){
        DecimalFormat df = new DecimalFormat("#.##");
        ArrayList<Character> listWordSearched = new ArrayList<>();

        /**
         * Slice word to characters
         */
        for (int i = 0; i < word.length(); i++) {
            listWordSearched.add(word.toLowerCase().charAt(i));
        }

        List<KeyWord> listOfKeyWords = new ArrayList<>();
        ArrayList<Character> listFor = new ArrayList<>();
        List<String> listOfWords;

        int lengthWithoutSpecialChars= phrase.replaceAll("[^a-zA-Z0-9]","").length();
        phrase = phrase.toLowerCase().replaceAll("[^a-zA-Z0-9]"," ");
        phrase = phrase.trim().replaceAll(" +"," ");
        int searchedCharAppears = 0;

        /**
         * Count appears of searched chars in phrase
         */
        for(Character c : listWordSearched){
            for (int i = 0; i < phrase.length(); i++) {
                if(phrase.charAt(i) == c)
                    searchedCharAppears++;
            }
        }

        listOfWords = Arrays.asList(phrase.split(" "));

        /**
         * Counting frequency
         */
        for (String s : listOfWords) {
            int countOfAppear = 0;
            for(Character c : listWordSearched) {
                for (int i = 0; i < s.length(); i++) {
                    if(s.charAt(i) == c){
                        countOfAppear++;
                        listFor.add(c);
                    }
                }
            }
            double frequency = countOfAppear/ (double) searchedCharAppears;
            LinkedHashSet<Character> set = new LinkedHashSet<>(listFor);
            KeyWord kw = new KeyWord(countOfAppear,Double.parseDouble(df.format(frequency)),set,s.length());
            listOfKeyWords.add(kw);
            listFor.clear();
        }



        /**
         * Check the same group of words
         */
        List<KeyWord> found = new ArrayList<>();
        for (int i = 0; i < listOfKeyWords.size()-1; i++) {
            for (int k = i + 1; k < listOfKeyWords.size(); k++) {
                if(listOfKeyWords.get(i).length == listOfKeyWords.get(k).length &&
                        listOfKeyWords.get(i).characters.equals(listOfKeyWords.get(k).characters))
                {
                    listOfKeyWords.get(i).countOfAppear += listOfKeyWords.get(k).countOfAppear;
                    found.add(listOfKeyWords.get(k));
                }
            }
        }

        listOfKeyWords.removeAll(found);

        /**
         * Sorting listOfKeyWords
         */
        listOfKeyWords.sort(new logicComparator());
        listOfKeyWords.sort(new lengthComparator());
        listOfKeyWords.sort(new frequencyComparator());



        for(KeyWord kw : listOfKeyWords){
            System.out.println("{" + kw.characters.toString().replace("[","(").replace("]",")") + ", "
                    + kw.length + "} = "
                    + String.format("%.2f",kw.frequency) + " (" + kw.countOfAppear + "/"
                    + String.format("%.0f", (double) searchedCharAppears) + ")");
        }

        System.out.println("TOTAL Frequency: "+ String.format("%.2f",searchedCharAppears/ (double) lengthWithoutSpecialChars)
                + " (" + String.format("%.0f",(double) searchedCharAppears)
                + "/" + lengthWithoutSpecialChars + ")");
    }

}
