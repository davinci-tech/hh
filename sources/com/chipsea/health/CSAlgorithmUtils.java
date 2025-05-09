package com.chipsea.health;

/* loaded from: classes2.dex */
public class CSAlgorithmUtils {
    public native float getBFR(float f, byte b, float f2, int i, int i2, int i3);

    public native float getBFRS(float f, byte b, float f2, int i, int i2, int i3);

    public native float getBMR(float f, byte b, float f2, int i, int i2, int i3);

    public native float getBMRS(float f, byte b, float f2, int i, int i2, int i3);

    public native int getBodyAge(float f, byte b, float f2, int i, int i2, int i3);

    public native int getBodyAgeS(float f, byte b, float f2, int i, int i2, int i3);

    public native float getMSW(float f, byte b, float f2, int i, int i2, int i3);

    public native float getMSWS(float f, byte b, float f2, int i, int i2, int i3);

    public native float getPM(float f, byte b, float f2, int i, int i2, int i3);

    public native float getPMS(float f, byte b, float f2, int i, int i2, int i3);

    public native int getResistance(float f, byte b, float f2, int i, float f3);

    public native float getSLM(float f, byte b, float f2, int i, int i2, int i3);

    public native float getSLMS(float f, byte b, float f2, int i, int i2, int i3);

    public native float getSMM(float f, byte b, float f2, int i, int i2, int i3);

    public native float getSMMS(float f, byte b, float f2, int i, int i2, int i3);

    public native int getScore(float f, byte b, float f2, int i, int i2, int i3);

    public native int getScoreS(float f, byte b, float f2, int i, int i2, int i3);

    public native float getTFR(float f, byte b, float f2, int i, int i2, int i3);

    public native float getTFRS(float f, byte b, float f2, int i, int i2, int i3);

    public native float getVFR(float f, byte b, float f2, int i, int i2, int i3);

    public native float getVFRS(float f, byte b, float f2, int i, int i2, int i3);

    static {
        System.loadLibrary("csalgorithm");
    }
}
