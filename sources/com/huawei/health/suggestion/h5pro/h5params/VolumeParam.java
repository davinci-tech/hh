package com.huawei.health.suggestion.h5pro.h5params;

import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;

/* loaded from: classes.dex */
public class VolumeParam {

    @SerializedName(WatchFaceProvider.BACKGROUND_LABEL)
    private float background;

    @SerializedName("counter")
    private float counter;

    @SerializedName("guider")
    private float guider;

    public float getCounter() {
        return this.counter;
    }

    public void setCounter(float f) {
        this.counter = f;
    }

    public float getGuider() {
        return this.guider;
    }

    public void setGuider(float f) {
        this.guider = f;
    }

    public float getBackground() {
        return this.background;
    }

    public void setBackground(float f) {
        this.background = f;
    }
}
