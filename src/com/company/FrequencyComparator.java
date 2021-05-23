package com.company;

import java.util.Comparator;

class FrequencyComparator implements Comparator<KeyWord> {
    @Override
    public int compare(KeyWord o1, KeyWord o2) {
        return Double.compare(o1.frequency, o2.frequency);
    }
}
