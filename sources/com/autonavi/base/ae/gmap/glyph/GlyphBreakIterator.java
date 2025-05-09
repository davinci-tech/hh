package com.autonavi.base.ae.gmap.glyph;

import java.text.BreakIterator;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class GlyphBreakIterator {
    public static final int BREAK_BY_CHARACTER_SEQUENCES = 2;
    public static final int BREAK_BY_WORDS = 1;
    public ArrayList<Integer> breakResult = new ArrayList<>();
    public int mBreakMode;

    public GlyphBreakIterator(int i) {
        this.mBreakMode = i;
    }

    public int[] setText(String str) {
        BreakIterator breakIterator = null;
        if (str.isEmpty()) {
            return null;
        }
        if (!this.breakResult.isEmpty()) {
            this.breakResult.clear();
        }
        int i = this.mBreakMode;
        if (i == 1) {
            breakIterator = BreakIterator.getWordInstance();
        } else if (i == 2) {
            breakIterator = BreakIterator.getCharacterInstance();
        }
        breakIterator.setText(str);
        int first = breakIterator.first();
        while (true) {
            int next = breakIterator.next();
            if (next == -1) {
                break;
            }
            this.breakResult.add(Integer.valueOf(first));
            first = next;
        }
        this.breakResult.add(Integer.valueOf(str.length()));
        int size = this.breakResult.size();
        Integer[] numArr = new Integer[size];
        this.breakResult.toArray(numArr);
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }
}
