package com.company;

import java.util.LinkedHashSet;

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
