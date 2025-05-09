package com.huawei.openalliance.ad.beans.feedback;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes5.dex */
public class ComplainAddInfo {

    @a
    private String appId = Constants.COMPLAIN_APP_ID;
    private String sceneId = "2";
    private String subSceneId = "6";

    @a
    private String deviceId = "";
    private boolean disableUserUpload = true;
    private AdditionalContext additionalContext = new AdditionalContext();

    public void a(String str) {
        this.deviceId = str;
    }

    public void a(AdditionalContext additionalContext) {
        this.additionalContext = additionalContext;
    }

    public AdditionalContext a() {
        return this.additionalContext;
    }
}
