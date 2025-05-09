package com.huawei.watchface.mvp.model.latona.provider;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceThemeProviders {

    @SerializedName("provider")
    private List<WatchFaceThemeProvider> mWatchFaceThemeProviders;

    public List<WatchFaceThemeProvider> getWatchFaceThemeProviders() {
        return this.mWatchFaceThemeProviders;
    }

    public void setWatchFaceThemeProviders(List<WatchFaceThemeProvider> list) {
        this.mWatchFaceThemeProviders = list;
    }

    public String toString() {
        return " mWatchFaceThemeProviders { mWatchFaceThemeProviders=" + this.mWatchFaceThemeProviders + " } ";
    }
}
