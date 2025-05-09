package com.huawei.health.h5pro.jsbridge.system.share;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.operation.utils.Constants;
import java.util.Map;

/* loaded from: classes8.dex */
public class ShareObj {

    @SerializedName("sharePlatform")
    public int[] f;

    @SerializedName(KnitFragment.KEY_EXTRA)
    public Map<String, Object> h;

    @SerializedName(Constants.BI_MODULE_ID)
    public String i = "";

    @SerializedName("shareBiMap")
    public Map<String, Object> j;

    public void setSharePlatform(int[] iArr) {
        this.f = iArr;
    }

    public void setShareBiMap(Map<String, Object> map) {
        this.j = map;
    }

    public void setModuleId(String str) {
        this.i = str;
    }

    public void setExtra(Map<String, Object> map) {
        this.h = map;
    }

    public int[] getSharePlatform() {
        return this.f;
    }

    public Map<String, Object> getShareBiMap() {
        return this.j;
    }

    public String getModuleId() {
        return this.i;
    }

    public Map<String, Object> getExtra() {
        return this.h;
    }
}
