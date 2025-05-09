package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes9.dex */
public class WatchFaceAarAbility {
    private boolean enableAnrCollect;
    private boolean enableReloadOnConfigChange = true;
    private boolean enableWebViewUrlCache;

    public boolean getEnableAnrCollect() {
        return this.enableAnrCollect;
    }

    public void setEnableAnrCollect(boolean z) {
        this.enableAnrCollect = z;
    }

    public boolean isEnableWebViewUrlCache() {
        return this.enableWebViewUrlCache;
    }

    public void setEnableWebViewUrlCache(boolean z) {
        this.enableWebViewUrlCache = z;
    }

    public boolean isEnableReloadOnConfigChange() {
        return this.enableReloadOnConfigChange;
    }

    public void setEnableReloadOnConfigChange(boolean z) {
        this.enableReloadOnConfigChange = z;
    }
}
