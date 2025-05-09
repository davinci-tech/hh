package com.huawei.pluginhealthzone.cloud;

import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class HttpDataCallback {
    public abstract void onFailure(int i, String str);

    public abstract void onSuccess(JSONObject jSONObject);
}
