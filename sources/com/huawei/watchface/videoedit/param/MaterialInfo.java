package com.huawei.watchface.videoedit.param;

import androidx.constraintlayout.motion.widget.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes9.dex */
public class MaterialInfo {

    @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("duration")
    @Expose
    private int duration;

    @SerializedName("height")
    @Expose
    private int height;
    private String montagePath;

    @SerializedName(BleConstants.KEY_PATH)
    @Expose
    private String path;

    @SerializedName(Key.ROTATION)
    @Expose
    private float rotation;

    @SerializedName("startTime")
    @Expose
    private long startTime;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("width")
    @Expose
    private int width;

    public void setType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setRotation(float f) {
        this.rotation = f;
    }

    public float getRotation() {
        return this.rotation;
    }

    public void setMontagePath(String str) {
        this.montagePath = str;
    }

    public String getMontagePath() {
        return this.montagePath;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getStartTime() {
        return this.startTime;
    }
}
