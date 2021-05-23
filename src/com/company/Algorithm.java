package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class Algorithm {

    public static void executeAlgorithm(String phrase, String word){
        DecimalFormat df = new DecimalFormat("#.##");
        ArrayList<Character> listOfWordCharacters = new ArrayList<>();
        List<KeyWord> listOfKeyWords = new ArrayList<>();
        List<String> listOfWords;
        int searchedCharAppears = 0;

        sliceWordToCharacters(word,listOfWordCharacters);

        int lengthWithoutSpecialChars = phrase.replaceAll("[^a-zA-Z0-9]","").length();

        phrase = phrase.toLowerCase().replaceAll("[^a-zA-Z0-9]"," ");
        phrase = phrase.trim().replaceAll(" +"," ");

        listOfWords = Arrays.asList(phrase.split(" "));

        for(Character c : listOfWordCharacters){
            for (int i = 0; i < phrase.length(); i++) {
                if(phrase.charAt(i) == c)
                    searchedCharAppears++;
            }
        }

        createNewKeyWord(listOfWords,listOfWordCharacters,listOfKeyWords,searchedCharAppears,df);

        removeDuplicatedKeyWords(listOfKeyWords);

        sortKeyWords(listOfKeyWords);

        showResults(listOfKeyWords,searchedCharAppears);

        showTotalFrequency(searchedCharAppears,lengthWithoutSpecialChars);

    }

    public static void createNewKeyWord(List<String> listOfWords, ArrayList<Character> listOfWordCharacters,
                                        List<KeyWord> listOfKeyWords, int searchedCharAppears,DecimalFormat df){
        ArrayList<Character> listForCountingFrequency = new ArrayList<>();
        for (String s : listOfWords) {
            int countOfAppear = 0;
            for(Character c : listOfWordCharacters) {
                for (int i = 0; i < s.length(); i++) {
                    if(s.charAt(i) == c){
                        countOfAppear++;
                        listForCountingFrequency.add(c);
                    }
                }
            }
            double frequency = countOfAppear/ (double) searchedCharAppears;
            LinkedHashSet<Character> set = new LinkedHashSet<>(listForCountingFrequency);
            KeyWord kw = new KeyWord(countOfAppear,Double.parseDouble(df.format(frequency)),set,s.length());
            listOfKeyWords.add(kw);
            listForCountingFrequency.clear();
        }
    }

    public static void removeDuplicatedKeyWords(List<KeyWord> listOfKeyWords){
        List<KeyWord> foundDuplicatedKeyWords = new ArrayList<>();
        for (int i = 0; i < listOfKeyWords.size()-1; i++) {
            for (int k = i + 1; k < listOfKeyWords.size(); k++) {
                if(listOfKeyWords.get(i).length == listOfKeyWords.get(k).length &&
                        listOfKeyWords.get(i).characters.equals(listOfKeyWords.get(k).characters))
                {
                    listOfKeyWords.get(i).countOfAppear += listOfKeyWords.get(k).countOfAppear;
                    foundDuplicatedKeyWords.add(listOfKeyWords.get(k));
                }
            }
        }
        listOfKeyWords.removeAll(foundDuplicatedKeyWords);
    }

    public static void showResults(List<KeyWord> listOfKeyWords, int searchedCharAppears) {
        for(KeyWord kw : listOfKeyWords){
            System.out.println("{" + kw.characters.toString().replace("[","(").replace("]",")") + ", "
                    + kw.length + "} = "
                    + String.format("%.2f",kw.frequency) + " (" + kw.countOfAppear + "/"
                    + String.format("%.0f", (double) searchedCharAppears) + ")");
        }
    }

    public static void showTotalFrequency(int searchedCharAppears, int lengthWithoutSpecialChars){
        System.out.println("TOTAL Frequency: "+ String.format("%.2f",searchedCharAppears/ (double) lengthWithoutSpecialChars)
                + " (" + String.format("%.0f",(double) searchedCharAppears)
                + "/" + lengthWithoutSpecialChars + ")");
    }

    public static void sortKeyWords(List<KeyWord> listOfKeyWords){
        listOfKeyWords.sort(new LengthComparator());
        listOfKeyWords.sort(new CharacterComparator());
        listOfKeyWords.sort(new FrequencyComparator());
    }

    public static void sliceWordToCharacters(String word, ArrayList<Character> listOfWordCharacters){
        for (int i = 0; i < word.length(); i++) {
            listOfWordCharacters.add(word.toLowerCase().charAt(i));
        }
    }
}
