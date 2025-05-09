package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.https.annotation.Result;
import com.tencent.connect.common.Constants;

/* loaded from: classes2.dex */
public class an {

    @Result(Constants.PARAM_ACCESS_TOKEN)
    private String accessToken;

    @Result(Constants.PARAM_EXPIRES_IN)
    private long expiresIn;

    @Result("ret")
    private ao ret;

    public ao getRet() {
        return this.ret;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public String getAccessToken() {
        return this.accessToken;
    }
}
