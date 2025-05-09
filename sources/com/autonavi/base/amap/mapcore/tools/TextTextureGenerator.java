package com.autonavi.base.amap.mapcore.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.amap.api.col.p0003sl.dv;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class TextTextureGenerator {
    private static final int ALIGNCENTER = 51;
    private static final int ALIGNLEFT = 49;
    private static final int ALIGNRIGHT = 50;
    static final int AN_LABEL_MAXCHARINLINE = 7;
    static final int AN_LABEL_MULITYLINE_SPAN = 2;
    private int TEXT_FONTSIZE = -1;
    private int TEXT_FONTSIZE_TRUE = -1;
    private float base_line = 0.0f;
    private float start_x = 0.0f;
    private Paint text_paint = null;

    public static int GetNearstSize2N(int i) {
        int i2 = 1;
        while (i > i2) {
            i2 *= 2;
        }
        return i2;
    }

    public TextTextureGenerator() {
        createTextParam();
    }

    private void createTextParam() {
        float f;
        int i = this.TEXT_FONTSIZE - 2;
        this.TEXT_FONTSIZE_TRUE = i;
        Paint newPaint = newPaint(null, i, 49);
        this.text_paint = newPaint;
        float f2 = (this.TEXT_FONTSIZE - this.TEXT_FONTSIZE_TRUE) / 2.0f;
        this.start_x = f2;
        float f3 = -27.832031f;
        try {
            Paint.FontMetrics fontMetrics = newPaint.getFontMetrics();
            f = fontMetrics.descent;
            try {
                f3 = fontMetrics.ascent;
                float f4 = fontMetrics.top;
                float f5 = fontMetrics.bottom;
                float f6 = fontMetrics.leading;
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            f = 7.3242188f;
        }
        this.base_line = ((this.TEXT_FONTSIZE_TRUE - (f + f3)) / 2.0f) + f2 + 0.5f;
    }

    public byte[] getTextPixelBuffer(int i, int i2) {
        if (this.TEXT_FONTSIZE != i2) {
            this.TEXT_FONTSIZE = i2;
            createTextParam();
        }
        char c = (char) i;
        try {
            char[] cArr = {c};
            float f = this.base_line;
            int i3 = this.TEXT_FONTSIZE;
            Bitmap createBitmap = Bitmap.createBitmap(i3, i3, Bitmap.Config.ALPHA_8);
            Canvas canvas = new Canvas(createBitmap);
            int i4 = this.TEXT_FONTSIZE;
            byte[] bArr = new byte[i4 * i4];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            float measureText = this.text_paint.measureText(String.valueOf(c));
            char c2 = cArr[0];
            if (c2 > 0 && c2 < 256) {
                f -= 1.5f;
            }
            float f2 = f;
            Paint.Align textAlign = this.text_paint.getTextAlign();
            float textSize = this.text_paint.getTextSize();
            float f3 = measureText - this.TEXT_FONTSIZE_TRUE;
            if (textAlign != Paint.Align.CENTER && f3 >= 4.0f) {
                this.text_paint.setTextAlign(Paint.Align.CENTER);
                this.text_paint.setTextSize(this.TEXT_FONTSIZE_TRUE - f3);
                canvas.drawText(cArr, 0, 1, (this.TEXT_FONTSIZE_TRUE - f3) / 2.0f, f2, this.text_paint);
                this.text_paint.setTextAlign(textAlign);
                this.text_paint.setTextSize(textSize);
            } else {
                canvas.drawText(cArr, 0, 1, this.start_x, f2, this.text_paint);
            }
            createBitmap.copyPixelsToBuffer(wrap);
            dv.a(createBitmap);
            return bArr;
        } catch (Exception | OutOfMemoryError unused) {
            return null;
        }
    }

    public byte[] getCharsWidths(int[] iArr) {
        int length = iArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            Paint paint = this.text_paint;
            new StringBuilder().append((char) iArr[i]);
            bArr[i] = (byte) (paint.measureText(r4.toString()) + (this.TEXT_FONTSIZE - this.TEXT_FONTSIZE_TRUE));
        }
        return bArr;
    }

    private static Paint newPaint(String str, int i, int i2) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(-1);
        textPaint.setTextSize(i);
        textPaint.setAntiAlias(true);
        textPaint.setFilterBitmap(true);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        switch (i2) {
            case 49:
                textPaint.setTextAlign(Paint.Align.LEFT);
                return textPaint;
            case 50:
                textPaint.setTextAlign(Paint.Align.RIGHT);
                return textPaint;
            case 51:
                textPaint.setTextAlign(Paint.Align.CENTER);
                return textPaint;
            default:
                textPaint.setTextAlign(Paint.Align.LEFT);
                return textPaint;
        }
    }

    public static float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }

    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }
}
