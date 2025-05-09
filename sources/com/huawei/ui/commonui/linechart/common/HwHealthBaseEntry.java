package com.huawei.ui.commonui.linechart.common;

import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.Entry;
import defpackage.nor;

/* loaded from: classes6.dex */
public class HwHealthBaseEntry extends Entry implements Comparable<HwHealthBaseEntry> {
    public HwHealthBaseEntry(float f, float f2) {
        super(f, f2);
    }

    public HwHealthBaseEntry(float f, float f2, Object obj) {
        super(f, f2, obj);
    }

    public HwHealthBaseEntry(float f, float f2, Drawable drawable) {
        super(f, f2, drawable);
    }

    public HwHealthBaseEntry(float f, float f2, Drawable drawable, Object obj) {
        super(f, f2, drawable, obj);
    }

    @Override // java.lang.Comparable
    public int compareTo(HwHealthBaseEntry hwHealthBaseEntry) {
        if (hwHealthBaseEntry != null && getX() <= hwHealthBaseEntry.getX()) {
            return Math.abs(getX() - hwHealthBaseEntry.getX()) < 1.0E-7f ? 0 : -1;
        }
        return 1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof HwHealthBaseEntry) {
            return nor.a(getX(), ((HwHealthBaseEntry) obj).getX());
        }
        return false;
    }

    public int hashCode() {
        return (int) getX();
    }
}
