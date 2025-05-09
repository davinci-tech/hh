package com.huawei.health.knit.bff.impl;

import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface IResourceRequestCallback {
    void onFailure(int i, String str);

    void onSuccess(JSONObject jSONObject);
}
