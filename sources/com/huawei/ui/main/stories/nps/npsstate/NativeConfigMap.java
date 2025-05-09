package com.huawei.ui.main.stories.nps.npsstate;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes7.dex */
public class NativeConfigMap {

    @SerializedName("newUserConfig")
    private Map<Integer, NativeConfigBean> mNewUserConfig;

    public Map<Integer, NativeConfigBean> getNewUserConfig() {
        return this.mNewUserConfig;
    }

    public void setNewUserConfig(Map<Integer, NativeConfigBean> map) {
        this.mNewUserConfig = map;
    }
}
