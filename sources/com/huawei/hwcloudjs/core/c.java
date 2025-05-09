package com.huawei.hwcloudjs.core;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface c {
    String getApi();

    void onCompletedConfig(Context context, String str, String str2, String str3, JSONObject jSONObject);

    void onDestroy(String str);
}
