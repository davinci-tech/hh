package com.huawei.operation.beans;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class EcgFilterAccSignal {

    @SerializedName("accX")
    private int accX;

    @SerializedName("accY")
    private int accY;

    @SerializedName("accZ")
    private int accZ;

    public int getAccX() {
        return this.accX;
    }

    public void setAccX(int i) {
        this.accX = i;
    }

    public int getAccY() {
        return this.accY;
    }

    public void setAccY(int i) {
        this.accY = i;
    }

    public int getAccZ() {
        return this.accZ;
    }

    public void setAccZ(int i) {
        this.accZ = i;
    }

    public String toString() {
        return "{accX:" + this.accX + ", accY:" + this.accY + ", accZ:" + this.accZ + '}';
    }
}
