package defpackage;

import android.text.TextPaint;

/* loaded from: classes9.dex */
public class njy {
    public static float d(String str, float f) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(f);
        return textPaint.measureText(str);
    }
}
