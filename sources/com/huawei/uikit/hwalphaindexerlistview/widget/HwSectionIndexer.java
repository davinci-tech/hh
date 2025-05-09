package com.huawei.uikit.hwalphaindexerlistview.widget;

import android.widget.SectionIndexer;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class HwSectionIndexer implements SectionIndexer {
    private int[] b;
    private String[] d;
    private int e;

    public HwSectionIndexer(String[] strArr, int[] iArr, boolean z) {
        if (strArr == null || iArr == null || strArr.length != iArr.length) {
            return;
        }
        int length = strArr.length;
        String[] strArr2 = new String[length];
        this.d = strArr2;
        System.arraycopy(strArr, 0, strArr2, 0, length);
        this.b = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            String[] strArr3 = this.d;
            String str = strArr3[i2];
            if (str == null) {
                strArr3[i2] = "";
            } else {
                strArr3[i2] = str.trim();
            }
            this.b[i2] = i;
            i += iArr[i2];
        }
        this.e = i;
    }

    @Override // android.widget.SectionIndexer
    public int getPositionForSection(int i) {
        if (i < 0 || i >= this.d.length) {
            return -1;
        }
        return this.b[i];
    }

    @Override // android.widget.SectionIndexer
    public int getSectionForPosition(int i) {
        if (i < 0 || i >= this.e) {
            return -1;
        }
        int binarySearch = Arrays.binarySearch(this.b, i);
        return binarySearch >= 0 ? binarySearch : (-binarySearch) - 2;
    }

    @Override // android.widget.SectionIndexer
    public Object[] getSections() {
        String[] strArr = this.d;
        if (strArr == null || strArr.length == 0) {
            return new String[0];
        }
        int length = strArr.length;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        return strArr2;
    }

    public HwSectionIndexer(String[] strArr, int[] iArr) {
        this(strArr, iArr, false);
    }
}
