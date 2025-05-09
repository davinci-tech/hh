package com.huawei.watchface.mvp.model.latona.provider;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.watchface.mvp.model.latona.WatchFaceThemeElement;
import com.huawei.watchface.mvp.model.latona.WatchFaceThemeElementConfig;
import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceThemeProvider {

    @SerializedName("@dpi")
    private String mDpi;

    @SerializedName(NetworkService.Constants.CONFIG_SERVICE)
    private WatchFaceThemeElementConfig mWatchFaceThemeElementConfig;

    @SerializedName(FunctionSetBeanReader.BI_ELEMENT)
    private List<WatchFaceThemeElement> mWatchFaceThemeElements;

    public List<WatchFaceThemeElement> getWatchFaceThemeElements() {
        return this.mWatchFaceThemeElements;
    }

    public String getDpi() {
        return this.mDpi;
    }

    public void setDpi(String str) {
        this.mDpi = str;
    }

    public void setWatchFaceThemeElements(List<WatchFaceThemeElement> list) {
        this.mWatchFaceThemeElements = list;
    }

    public WatchFaceThemeElementConfig getWatchFaceThemeElementConfig() {
        return this.mWatchFaceThemeElementConfig;
    }

    public void setWatchFaceThemeElementConfig(WatchFaceThemeElementConfig watchFaceThemeElementConfig) {
        this.mWatchFaceThemeElementConfig = watchFaceThemeElementConfig;
    }

    public String toString() {
        return "WatchFaceThemeProvider{mDpi='" + this.mDpi + "', mWatchFaceThemeElements=" + this.mWatchFaceThemeElements + ", mWatchFaceThemeElementConfig=" + this.mWatchFaceThemeElementConfig + '}';
    }
}
