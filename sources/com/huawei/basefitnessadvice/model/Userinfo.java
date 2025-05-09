package com.huawei.basefitnessadvice.model;

/* loaded from: classes8.dex */
public class Userinfo {
    private static final int DEFAULT_USER_WEIGHT = 60;
    private int mAge;
    private int mGender;
    private int mHeight;
    private float mWeight;

    public static int getDefaultUserWeight() {
        return 60;
    }

    public Userinfo(int i, int i2, int i3, float f) {
        this.mAge = i;
        this.mGender = i2;
        this.mHeight = i3;
        this.mWeight = f;
    }

    public int getAge() {
        return this.mAge;
    }

    public void setAge(int i) {
        this.mAge = i;
    }

    public int getGender() {
        return this.mGender;
    }

    public void setGender(int i) {
        this.mGender = i;
    }

    public int getHeigth() {
        return this.mHeight;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public float acquireWeight() {
        return this.mWeight;
    }

    public void setWeight(float f) {
        this.mWeight = f;
    }
}
