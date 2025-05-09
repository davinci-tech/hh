package com.github.mikephil.charting.formatter;

import com.amap.api.col.p0003sl.it;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.scankit.b;
import java.text.DecimalFormat;

/* loaded from: classes8.dex */
public class LargeValueFormatter implements IValueFormatter, IAxisValueFormatter {
    private DecimalFormat mFormat;
    private int mMaxLength;
    private String[] mSuffix;
    private String mText;

    public int getDecimalDigits() {
        return 0;
    }

    public LargeValueFormatter() {
        this.mSuffix = new String[]{"", it.k, FitRunPlayAudio.OPPORTUNITY_M, b.H, FitRunPlayAudio.PLAY_TYPE_T};
        this.mMaxLength = 5;
        this.mText = "";
        this.mFormat = new DecimalFormat("###E00");
    }

    public LargeValueFormatter(String str) {
        this();
        this.mText = str;
    }

    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return makePretty(f) + this.mText;
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return makePretty(f) + this.mText;
    }

    public void setAppendix(String str) {
        this.mText = str;
    }

    public void setSuffix(String[] strArr) {
        this.mSuffix = strArr;
    }

    public void setMaxLength(int i) {
        this.mMaxLength = i;
    }

    private String makePretty(double d) {
        String format = this.mFormat.format(d);
        int numericValue = Character.getNumericValue(format.charAt(format.length() - 1));
        String replaceAll = format.replaceAll("E[0-9][0-9]", this.mSuffix[Integer.valueOf(Character.getNumericValue(format.charAt(format.length() - 2)) + "" + numericValue).intValue() / 3]);
        while (true) {
            if (replaceAll.length() <= this.mMaxLength && !replaceAll.matches("[0-9]+\\.[a-z]")) {
                return replaceAll;
            }
            replaceAll = replaceAll.substring(0, replaceAll.length() - 2) + replaceAll.substring(replaceAll.length() - 1);
        }
    }
}
