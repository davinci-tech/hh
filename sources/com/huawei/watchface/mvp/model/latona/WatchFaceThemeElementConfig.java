package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceThemeElementConfig {

    @SerializedName("position")
    private List<WatchFaceThemePosition> mWatchFaceThemePositionList;

    public List<WatchFaceThemePosition> getWatchFaceThemePositionList() {
        return this.mWatchFaceThemePositionList;
    }

    public void setWatchFaceThemePositionList(List<WatchFaceThemePosition> list) {
        this.mWatchFaceThemePositionList = list;
    }

    public String toString() {
        return "WatchFaceThemeElementConfig{mWatchFaceThemePositionList=" + this.mWatchFaceThemePositionList + '}';
    }
}
