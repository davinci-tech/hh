package com.autonavi.base.ae.gmap.glyph;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import com.autonavi.base.amap.mapcore.tools.GLConvertUtil;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class GlyphLoader {
    private static Map<String, Typeface> FontFaceMap = new HashMap();

    private static native long nativeCreateGlyphLoader();

    private static native void nativeDestroyGlyphLoader(long j);

    private static GlyphMetrics loadGlyphMetrics(String str, FontStyle fontStyle, float f, String str2, boolean z, float f2, boolean z2, boolean z3) {
        GlyphMetrics glyphMetrics = new GlyphMetrics();
        if (fontStyle == null || TextUtils.isEmpty(str)) {
            return glyphMetrics;
        }
        try {
            if (!z2) {
                TextPaint newTextPaint = newTextPaint(fontStyle, f, str2, z, f2);
                Rect rect = new Rect();
                newTextPaint.getTextBounds(str, 0, str.length(), rect);
                if (rect.width() == 0 && rect.height() == 0) {
                    float measureText = newTextPaint.measureText(" ", 0, 1);
                    float abs = Math.abs(newTextPaint.getFontMetrics().ascent);
                    float abs2 = Math.abs(newTextPaint.getFontMetrics().descent);
                    rect.top = 0;
                    rect.left = 0;
                    rect.right = (int) measureText;
                    rect.bottom = (int) (abs + abs2);
                }
                if (z && f2 > 0.0f) {
                    float f3 = f2 / 2.0f;
                    rect.top = (int) (rect.top - f3);
                    rect.left = (int) (rect.left - f3);
                    rect.right = (int) (rect.right + f3);
                    rect.bottom = (int) (rect.bottom + f3);
                }
                glyphMetrics.bSuccess = true;
                glyphMetrics.fLeft = rect.left;
                glyphMetrics.fTop = Math.abs(newTextPaint.getFontMetrics().ascent) - Math.abs(rect.top);
                glyphMetrics.nWidth = rect.width();
                glyphMetrics.nHeight = rect.height();
                glyphMetrics.fAdvance = newTextPaint.measureText(str);
                newTextPaint.setTypeface(null);
            } else {
                glyphMetrics.bSuccess = true;
                glyphMetrics.fLeft = 0.0f;
                glyphMetrics.fTop = 0.0f;
                int i = (int) f;
                glyphMetrics.nWidth = i;
                glyphMetrics.nHeight = i;
                glyphMetrics.fAdvance = f;
            }
        } catch (Exception unused) {
            glyphMetrics.bSuccess = false;
        }
        return glyphMetrics;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:8|9|10|(9:12|14|15|(1:19)|(1:23)|24|(9:26|(7:30|(1:32)(1:50)|33|(4:35|(4:37|(2:40|38)|41|42)|43|44)|45|(1:47)(1:49)|48)|51|(0)(0)|33|(0)|45|(0)(0)|48)|52|53)|56|14|15|(2:17|19)|(2:21|23)|24|(0)|52|53) */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0139, code lost:
    
        r0.bSuccess = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b7 A[Catch: Exception -> 0x0139, TryCatch #1 {Exception -> 0x0139, blocks: (B:15:0x0049, B:17:0x0060, B:19:0x0066, B:23:0x0092, B:24:0x00b1, B:26:0x00b7, B:33:0x00c8, B:35:0x0105, B:38:0x010b, B:40:0x010f, B:42:0x011c, B:45:0x0120, B:47:0x0126, B:48:0x012b, B:49:0x0129, B:51:0x00c0, B:52:0x0135), top: B:14:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0105 A[Catch: Exception -> 0x0139, TryCatch #1 {Exception -> 0x0139, blocks: (B:15:0x0049, B:17:0x0060, B:19:0x0066, B:23:0x0092, B:24:0x00b1, B:26:0x00b7, B:33:0x00c8, B:35:0x0105, B:38:0x010b, B:40:0x010f, B:42:0x011c, B:45:0x0120, B:47:0x0126, B:48:0x012b, B:49:0x0129, B:51:0x00c0, B:52:0x0135), top: B:14:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0126 A[Catch: Exception -> 0x0139, TryCatch #1 {Exception -> 0x0139, blocks: (B:15:0x0049, B:17:0x0060, B:19:0x0066, B:23:0x0092, B:24:0x00b1, B:26:0x00b7, B:33:0x00c8, B:35:0x0105, B:38:0x010b, B:40:0x010f, B:42:0x011c, B:45:0x0120, B:47:0x0126, B:48:0x012b, B:49:0x0129, B:51:0x00c0, B:52:0x0135), top: B:14:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0129 A[Catch: Exception -> 0x0139, TryCatch #1 {Exception -> 0x0139, blocks: (B:15:0x0049, B:17:0x0060, B:19:0x0066, B:23:0x0092, B:24:0x00b1, B:26:0x00b7, B:33:0x00c8, B:35:0x0105, B:38:0x010b, B:40:0x010f, B:42:0x011c, B:45:0x0120, B:47:0x0126, B:48:0x012b, B:49:0x0129, B:51:0x00c0, B:52:0x0135), top: B:14:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.autonavi.base.ae.gmap.glyph.GlyphRaster loadGlyphRaster(java.lang.String r8, com.autonavi.base.ae.gmap.glyph.FontStyle r9, float r10, java.lang.String r11, boolean r12, float r13, boolean r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.base.ae.gmap.glyph.GlyphLoader.loadGlyphRaster(java.lang.String, com.autonavi.base.ae.gmap.glyph.FontStyle, float, java.lang.String, boolean, float, boolean, boolean):com.autonavi.base.ae.gmap.glyph.GlyphRaster");
    }

    private static GlyphRequestParam genGlyphRequestParam(byte[] bArr) {
        GlyphRequestParam glyphRequestParam = new GlyphRequestParam();
        int i = GLConvertUtil.getInt(bArr, 0);
        glyphRequestParam.strBuffer = new String(bArr, 4, i);
        Font font = new Font();
        font.nFontStyleCode = GLConvertUtil.getInt(bArr, i + 4);
        font.nFontSize = GLConvertUtil.getInt(bArr, i + 8);
        int i2 = GLConvertUtil.getInt(bArr, i + 12);
        int i3 = i + 16;
        font.strName = new String(bArr, i3, i2);
        int i4 = i3 + i2;
        FontMetrics fontMetrics = new FontMetrics();
        fontMetrics.fAscent = GLConvertUtil.getInt(bArr, i4) * 0.001f;
        fontMetrics.fDescent = GLConvertUtil.getInt(bArr, i4 + 4) * 0.001f;
        fontMetrics.fLeading = GLConvertUtil.getInt(bArr, i4 + 8) * 0.001f;
        fontMetrics.fHeight = GLConvertUtil.getInt(bArr, i4 + 12) * 0.001f;
        font.fontMetrics = fontMetrics;
        glyphRequestParam.font = font;
        glyphRequestParam.drawingMode = GLConvertUtil.getInt(bArr, i4 + 16);
        glyphRequestParam.strokeWidth = GLConvertUtil.getInt(bArr, i4 + 20) * 0.001f;
        int i5 = i4 + 28;
        glyphRequestParam.languageArr = new String(bArr, i5, GLConvertUtil.getInt(bArr, i4 + 24));
        glyphRequestParam.isEmoji = GLConvertUtil.getInt(bArr, i5);
        glyphRequestParam.isSDF = GLConvertUtil.getInt(bArr, i4 + 32);
        int i6 = i4 + 40;
        if (1 == GLConvertUtil.getInt(bArr, i4 + 36)) {
            GlyphMetrics glyphMetrics = new GlyphMetrics();
            glyphMetrics.nWidth = GLConvertUtil.getInt(bArr, i6);
            glyphMetrics.nHeight = GLConvertUtil.getInt(bArr, i4 + 44);
            glyphMetrics.fLeft = GLConvertUtil.getInt(bArr, i4 + 48) * 0.001f;
            glyphMetrics.fTop = GLConvertUtil.getInt(bArr, i4 + 52) * 0.001f;
            glyphMetrics.fAdvance = GLConvertUtil.getInt(bArr, i4 + 56) * 0.001f;
            glyphRequestParam.fGlyphMetrics = glyphMetrics;
        }
        return glyphRequestParam;
    }

    private static FontMetricsRequestParam genFontMetricsParam(byte[] bArr) {
        FontMetricsRequestParam fontMetricsRequestParam = new FontMetricsRequestParam();
        fontMetricsRequestParam.fFontSize = GLConvertUtil.getInt(bArr, 0) * 0.001f;
        fontMetricsRequestParam.nFontStyleCode = GLConvertUtil.getInt(bArr, 4);
        int i = 12;
        if (1 == GLConvertUtil.getInt(bArr, 8)) {
            int i2 = GLConvertUtil.getInt(bArr, 12);
            fontMetricsRequestParam.strName = new String(bArr, 16, i2);
            i = 16 + i2;
        }
        fontMetricsRequestParam.languageArr = new String(bArr, i + 4, GLConvertUtil.getInt(bArr, i));
        return fontMetricsRequestParam;
    }

    private static GlyphMetrics getGlyphMetrics(byte[] bArr) {
        GlyphRequestParam genGlyphRequestParam = genGlyphRequestParam(bArr);
        return loadGlyphMetrics(genGlyphRequestParam.strBuffer, new FontStyle(genGlyphRequestParam.font.nFontStyleCode), genGlyphRequestParam.font.nFontSize, genGlyphRequestParam.font.strName, genGlyphRequestParam.drawingMode != 0, genGlyphRequestParam.strokeWidth, genGlyphRequestParam.isEmoji > 0, genGlyphRequestParam.isSDF > 0);
    }

    private static GlyphRaster getGlyphRaster(byte[] bArr) {
        GlyphRequestParam genGlyphRequestParam = genGlyphRequestParam(bArr);
        FontStyle fontStyle = new FontStyle(genGlyphRequestParam.font.nFontStyleCode);
        boolean z = genGlyphRequestParam.drawingMode != 0;
        if (genGlyphRequestParam.drawingMode == 3) {
            return loadPathRaster(genGlyphRequestParam.strBuffer, fontStyle, genGlyphRequestParam.font.nFontSize, genGlyphRequestParam.font.strName, z, 2.0f * genGlyphRequestParam.strokeWidth);
        }
        return loadGlyphRaster(genGlyphRequestParam.strBuffer, fontStyle, genGlyphRequestParam.font.nFontSize, genGlyphRequestParam.font.strName, z, genGlyphRequestParam.strokeWidth, genGlyphRequestParam.isEmoji > 0, genGlyphRequestParam.isSDF > 0);
    }

    public static GlyphRaster loadPathRaster(String str, FontStyle fontStyle, float f, String str2, boolean z, float f2) {
        GlyphRaster glyphRaster = new GlyphRaster();
        if (fontStyle == null || TextUtils.isEmpty(str)) {
            return glyphRaster;
        }
        try {
            TextPaint newTextPaint = newTextPaint(fontStyle, f, str2, false, 0.0f);
            Rect rect = new Rect();
            newTextPaint.getTextBounds(str, 0, str.length(), rect);
            new Canvas(Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ALPHA_8)).drawText(str, 0 - rect.left, 0 - rect.top, newTextPaint);
            TextPaint newTextPaint2 = newTextPaint(fontStyle, f, str2, z, f2);
            Rect rect2 = new Rect();
            newTextPaint2.getTextBounds(str, 0, str.length(), rect2);
            if (z && f2 > 0.0f) {
                float f3 = 0.5f * f2;
                rect2.top = (int) (rect2.top - f3);
                rect2.left = (int) (rect2.left - f3);
                rect2.right = (int) (rect2.right + f3);
                rect2.bottom = (int) (rect2.bottom + f3);
            }
            if (!rect2.isEmpty()) {
                Bitmap createBitmap = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ALPHA_8);
                Canvas canvas = new Canvas(createBitmap);
                float f4 = 0 - rect2.left;
                float f5 = 0 - rect2.top;
                Path path = new Path();
                newTextPaint.getTextPath(str, 0, str.length(), f4, f5, path);
                canvas.drawPath(path, newTextPaint2);
                int width = rect2.width() * rect2.height();
                byte[] bArr = new byte[width];
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                glyphRaster.bitmapWidth = rect2.width();
                glyphRaster.bitmapHeight = rect2.height();
                glyphRaster.bitmapPixelMode = 0;
                glyphRaster.bitmapSize = width;
                createBitmap.copyPixelsToBuffer(wrap);
                createBitmap.recycle();
                glyphRaster.bitmapBuffer = bArr;
                glyphRaster.bSuccess = true;
            }
            newTextPaint.setTypeface(null);
            newTextPaint2.setTypeface(null);
        } catch (Exception unused) {
            glyphRaster.bSuccess = false;
        }
        return glyphRaster;
    }

    private static FontMetrics getFontMetrics(byte[] bArr) {
        FontMetricsRequestParam genFontMetricsParam = genFontMetricsParam(bArr);
        TextPaint newTextPaint = newTextPaint(new FontStyle(genFontMetricsParam.nFontStyleCode), genFontMetricsParam.fFontSize, genFontMetricsParam.strName, false, 0.0f);
        Paint.FontMetrics fontMetrics = newTextPaint.getFontMetrics();
        FontMetrics fontMetrics2 = new FontMetrics();
        fontMetrics2.bSuccess = true;
        fontMetrics2.fAscent = Math.abs(fontMetrics.ascent);
        fontMetrics2.fDescent = Math.abs(fontMetrics.descent);
        fontMetrics2.fLeading = Math.abs(fontMetrics.leading);
        fontMetrics2.fHeight = Math.abs(fontMetrics.ascent) + Math.abs(fontMetrics.descent);
        newTextPaint.setTypeface(null);
        return fontMetrics2;
    }

    private static String decodeUnicode(short s) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append((char) s);
        return stringBuffer.toString();
    }

    private static String decodeUnicode(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0068, code lost:
    
        if (r4 != false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.text.TextPaint newTextPaint(com.autonavi.base.ae.gmap.glyph.FontStyle r3, float r4, java.lang.String r5, boolean r6, float r7) {
        /*
            android.text.TextPaint r0 = new android.text.TextPaint
            r0.<init>()
            if (r3 != 0) goto L8
            return r0
        L8:
            r1 = -1
            r0.setColor(r1)
            r1 = 1
            r0.setAntiAlias(r1)
            r0.setFilterBitmap(r1)
            r0.setTextSize(r4)
            android.graphics.Paint$Align r4 = android.graphics.Paint.Align.LEFT
            r0.setTextAlign(r4)
            if (r6 == 0) goto L26
            android.graphics.Paint$Style r4 = android.graphics.Paint.Style.STROKE
            r0.setStyle(r4)
            r0.setStrokeWidth(r7)
            goto L2b
        L26:
            android.graphics.Paint$Style r4 = android.graphics.Paint.Style.FILL
            r0.setStyle(r4)
        L2b:
            int r4 = r3.getSlant()
            r6 = 2
            r7 = 0
            if (r4 == 0) goto L3a
            if (r4 == r1) goto L38
            if (r4 == r6) goto L38
            goto L3a
        L38:
            r4 = r1
            goto L3b
        L3a:
            r4 = r7
        L3b:
            int r3 = r3.getWeight()
            r2 = 500(0x1f4, float:7.0E-43)
            if (r3 == r2) goto L59
            r2 = 600(0x258, float:8.41E-43)
            if (r3 == r2) goto L59
            r2 = 700(0x2bc, float:9.81E-43)
            if (r3 == r2) goto L59
            r2 = 800(0x320, float:1.121E-42)
            if (r3 == r2) goto L59
            r2 = 900(0x384, float:1.261E-42)
            if (r3 == r2) goto L59
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r3 == r2) goto L59
            r3 = r7
            goto L5a
        L59:
            r3 = r1
        L5a:
            if (r3 == 0) goto L62
            if (r4 == 0) goto L62
            r0.setFakeBoldText(r1)
            goto L6b
        L62:
            if (r3 == 0) goto L68
            r0.setFakeBoldText(r1)
            goto L6b
        L68:
            if (r4 == 0) goto L6b
            goto L6c
        L6b:
            r6 = r7
        L6c:
            boolean r3 = r5.isEmpty()
            if (r3 != 0) goto L94
            java.util.Map<java.lang.String, android.graphics.Typeface> r3 = com.autonavi.base.ae.gmap.glyph.GlyphLoader.FontFaceMap     // Catch: java.lang.Exception -> L8d
            monitor-enter(r3)     // Catch: java.lang.Exception -> L8d
            java.util.Map<java.lang.String, android.graphics.Typeface> r4 = com.autonavi.base.ae.gmap.glyph.GlyphLoader.FontFaceMap     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r4 = r4.get(r5)     // Catch: java.lang.Throwable -> L8a
            android.graphics.Typeface r4 = (android.graphics.Typeface) r4     // Catch: java.lang.Throwable -> L8a
            if (r4 != 0) goto L88
            android.graphics.Typeface r4 = android.graphics.Typeface.createFromFile(r5)     // Catch: java.lang.Throwable -> L8a
            java.util.Map<java.lang.String, android.graphics.Typeface> r7 = com.autonavi.base.ae.gmap.glyph.GlyphLoader.FontFaceMap     // Catch: java.lang.Throwable -> L8a
            r7.put(r5, r4)     // Catch: java.lang.Throwable -> L8a
        L88:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L8a
            goto L9a
        L8a:
            r4 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L8a
            throw r4     // Catch: java.lang.Exception -> L8d
        L8d:
            android.graphics.Typeface r3 = android.graphics.Typeface.DEFAULT
            android.graphics.Typeface r4 = android.graphics.Typeface.create(r3, r6)
            goto L9a
        L94:
            android.graphics.Typeface r3 = android.graphics.Typeface.DEFAULT
            android.graphics.Typeface r4 = android.graphics.Typeface.create(r3, r6)
        L9a:
            r0.setTypeface(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.base.ae.gmap.glyph.GlyphLoader.newTextPaint(com.autonavi.base.ae.gmap.glyph.FontStyle, float, java.lang.String, boolean, float):android.text.TextPaint");
    }

    public static long createGlyphLoader() {
        return nativeCreateGlyphLoader();
    }

    public static void destroyGlyphLoader(long j) {
        nativeDestroyGlyphLoader(j);
    }
}
