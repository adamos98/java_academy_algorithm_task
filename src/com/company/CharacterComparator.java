package com.company;

import java.util.Comparator;

class CharacterComparator implements Comparator<KeyWord> {
    @Override
    public int compare(KeyWord o1, KeyWord o2) {
        final String word = Menu.l.toLowerCase();
        Character[] co1 = new Character[o1.length];
        Character[] co2 = new Character[o2.length];
        co1 = o2.characters.toArray(co1);
        co2 = o1.characters.toArray(co2);
        return word.indexOf(co1[0]) - word.indexOf(co2[0]);
    }
}
