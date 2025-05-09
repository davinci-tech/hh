package com.huawei.health.suggestion.h5pro.h5params;

import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;

/* loaded from: classes.dex */
public class PlayOrPauseParam {

    @SerializedName(WatchFaceProvider.BACKGROUND_LABEL)
    private boolean isBackgroundPlay;

    @SerializedName("counter")
    private boolean isCounterPlay;

    @SerializedName("guider")
    private boolean isGuiderPlay;

    public boolean isCounterPlay() {
        return this.isCounterPlay;
    }

    public void setCounterPlay(boolean z) {
        this.isCounterPlay = z;
    }

    public boolean isGuiderPlay() {
        return this.isGuiderPlay;
    }

    public void setGuiderPlay(boolean z) {
        this.isGuiderPlay = z;
    }

    public boolean isBackgroundPlay() {
        return this.isBackgroundPlay;
    }

    public void setBackgroundPlay(boolean z) {
        this.isBackgroundPlay = z;
    }
}
