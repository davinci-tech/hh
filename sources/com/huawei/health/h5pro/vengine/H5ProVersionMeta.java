package com.huawei.health.h5pro.vengine;

import com.huawei.health.h5pro.utils.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class H5ProVersionMeta {
    public String b;
    public String d;

    public String getMinAppVersion() {
        return this.d;
    }

    public String getLatestVersion() {
        return this.b;
    }

    public H5ProVersionMeta(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.b = jSONObject.optString("latest");
            this.d = jSONObject.optString("adMinAppVersion");
        } catch (JSONException e) {
            LogUtil.e("H5PRO_VersionMeta", "parse err: " + e.getMessage());
        }
    }
}
