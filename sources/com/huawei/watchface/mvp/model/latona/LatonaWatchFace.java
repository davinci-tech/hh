package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hms.network.inner.api.NetworkService;
import java.util.List;

/* loaded from: classes9.dex */
public class LatonaWatchFace {

    @SerializedName(NetworkService.Constants.CONFIG_SERVICE)
    private LatonaConfig mLatonaConfig;

    @SerializedName(FunctionSetBeanReader.BI_ELEMENT)
    private List<LatonaElement> mLatonaElements;

    public List<LatonaElement> getLatonaElements() {
        return this.mLatonaElements;
    }

    public void setLatonaElements(List<LatonaElement> list) {
        this.mLatonaElements = list;
    }

    public LatonaConfig getLatonaConfig() {
        return this.mLatonaConfig;
    }

    public void setLatonaConfig(LatonaConfig latonaConfig) {
        this.mLatonaConfig = latonaConfig;
    }

    public String toString() {
        return "LatonaWatchFace{mLatonaElements=" + this.mLatonaElements + ", mLatonaConfig=" + this.mLatonaConfig + '}';
    }
}
