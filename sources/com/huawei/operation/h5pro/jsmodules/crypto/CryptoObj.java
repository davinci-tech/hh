package com.huawei.operation.h5pro.jsmodules.crypto;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.MedalConstants;

/* loaded from: classes.dex */
class CryptoObj {

    @SerializedName("content")
    private String content;

    @SerializedName(MedalConstants.EVENT_KEY)
    private String key;

    @SerializedName("opMode")
    private String opMode;

    @SerializedName("transform")
    private String transform;

    CryptoObj() {
    }

    public String getTransform() {
        return this.transform;
    }

    public String getKey() {
        return this.key;
    }

    public String getContent() {
        return this.content;
    }

    public String getOpMode() {
        return this.opMode;
    }
}
