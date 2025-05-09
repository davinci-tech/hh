package com.huawei.basefitnessadvice.callback;

import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class DataCallback {
    public abstract void onFailure(int i, String str);

    public void onProgress(long j, long j2, boolean z) {
    }

    public abstract void onSuccess(JSONObject jSONObject);
}
