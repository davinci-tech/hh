package com.huawei.hwcommonmodel.datatypes;

import com.google.gson.annotations.SerializedName;
import defpackage.jcu;

/* loaded from: classes5.dex */
public class HealthSupportModel {

    @SerializedName("isSupportLeo")
    private boolean mIsSupportLeo = false;

    @SerializedName("watch2MiniVersion")
    private String mWatch2MiniVersion = "";

    @SerializedName("isSupportPro")
    private boolean mIsSupportPro = false;

    @SerializedName("proscheDesignMiniVersion")
    private String mProscheDesignMiniVersion = "";

    @SerializedName("isSupportMetis")
    private boolean mIsSupportMetis = true;

    @SerializedName("isSupportB3Lite")
    private boolean mIsSupportB3Lite = true;

    @SerializedName("isSupportEris")
    private boolean mIsSupportEris = true;

    @SerializedName("isSupportNyx")
    private boolean mIsSupportNyx = jcu.g;

    @SerializedName("isSupportA2")
    private boolean mIsSupportA2 = true;

    @SerializedName("isSupportB3")
    private boolean mIsSupportB3 = true;

    @SerializedName("isSupportB2")
    private boolean mIsSupportB2 = false;

    @SerializedName("isSupportB1")
    private boolean mIsSupportB1 = false;

    @SerializedName("isSupportB0")
    private boolean mIsSupportB0 = false;

    @SerializedName("isSupportW1")
    private boolean mIsSupportW1 = false;

    @SerializedName("isSupportAF500")
    private boolean mIsSupportAf500 = false;

    @SerializedName("isSupportR1")
    private boolean mIsSupportR1 = true;

    public boolean isSupportLeo() {
        return this.mIsSupportLeo;
    }

    public void setSupportLeo(boolean z) {
        this.mIsSupportLeo = z;
    }

    public boolean isSupportPro() {
        return this.mIsSupportPro;
    }

    public void setSupportPro(boolean z) {
        this.mIsSupportPro = z;
    }

    public boolean isSupportMetis() {
        return this.mIsSupportMetis;
    }

    public void setSupportMetis(boolean z) {
        this.mIsSupportMetis = z;
    }

    public boolean isSupportB3Lite() {
        return this.mIsSupportB3Lite;
    }

    public void setSupportB3Lite(boolean z) {
        this.mIsSupportB3Lite = z;
    }

    public boolean isSupportEris() {
        return this.mIsSupportEris;
    }

    public void setSupportEris(boolean z) {
        this.mIsSupportEris = z;
    }

    public boolean isSupportNyx() {
        return this.mIsSupportNyx;
    }

    public void setSupportNyx(boolean z) {
        this.mIsSupportNyx = z;
    }

    public boolean isSupportA2() {
        return this.mIsSupportA2;
    }

    public void setSupportA2(boolean z) {
        this.mIsSupportA2 = z;
    }

    public boolean isSupportB3() {
        return this.mIsSupportB3;
    }

    public void setSupportB3(boolean z) {
        this.mIsSupportB3 = z;
    }

    public boolean isSupportR1() {
        return this.mIsSupportR1;
    }

    public void setSupportR1(boolean z) {
        this.mIsSupportR1 = z;
    }

    public boolean isSupportB2() {
        return this.mIsSupportB2;
    }

    public void setSupportB2(boolean z) {
        this.mIsSupportB2 = z;
    }

    public boolean isSupportB1() {
        return this.mIsSupportB1;
    }

    public void setSupportB1(boolean z) {
        this.mIsSupportB1 = z;
    }

    public boolean isSupportB0() {
        return this.mIsSupportB0;
    }

    public void setSupportB0(boolean z) {
        this.mIsSupportB0 = z;
    }

    public boolean isSupportW1() {
        return this.mIsSupportW1;
    }

    public void setSupportW1(boolean z) {
        this.mIsSupportW1 = z;
    }

    public boolean isSupportAf500() {
        return this.mIsSupportAf500;
    }

    public void setSupportAf500(boolean z) {
        this.mIsSupportAf500 = z;
    }

    public String getWatch2MiniVersion() {
        return this.mWatch2MiniVersion;
    }

    public void setWatch2MiniVersion(String str) {
        this.mWatch2MiniVersion = str;
    }

    public String getProscheDesignMiniVersion() {
        return this.mProscheDesignMiniVersion;
    }

    public void setProscheDesignMiniVersion(String str) {
        this.mProscheDesignMiniVersion = str;
    }

    public String toString() {
        return "HealthSupportModel{isSupportLeo=" + this.mIsSupportLeo + ", watch2MiniVersion='" + this.mWatch2MiniVersion + "', isSupportPro=" + this.mIsSupportPro + ", proscheDesignMiniVersion='" + this.mProscheDesignMiniVersion + "', isSupportMetis=" + this.mIsSupportMetis + ", isSupportB3Lite=" + this.mIsSupportB3Lite + ", isSupportEris=" + this.mIsSupportEris + ", isSupportNyx=" + this.mIsSupportNyx + ", isSupportA2=" + this.mIsSupportA2 + ", isSupportB3=" + this.mIsSupportB3 + ", isSupportB2=" + this.mIsSupportB2 + ", isSupportB1=" + this.mIsSupportB1 + ", isSupportB0=" + this.mIsSupportB0 + ", isSupportW1=" + this.mIsSupportW1 + ", isSupportAF500=" + this.mIsSupportAf500 + ", isSupportR1=" + this.mIsSupportR1 + '}';
    }
}
