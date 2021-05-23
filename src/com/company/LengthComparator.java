package com.company;

import java.util.Comparator;

class LengthComparator implements Comparator<KeyWord> {
    @Override
    public int compare(KeyWord o1, KeyWord o2) {
        return Double.compare(o1.length, o2.length);
    }
}
