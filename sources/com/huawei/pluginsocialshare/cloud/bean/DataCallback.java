package com.huawei.pluginsocialshare.cloud.bean;

import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class DataCallback {
    public abstract void onFailure(int i, String str);

    public void onProgress(long j, long j2, boolean z) {
    }

    public abstract void onSuccess(JSONObject jSONObject);
}
