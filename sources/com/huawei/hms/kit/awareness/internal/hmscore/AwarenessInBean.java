package com.huawei.hms.kit.awareness.internal.hmscore;

import com.huawei.hms.kit.awareness.barrier.internal.f.c;
import com.huawei.hms.kit.awareness.quickapp.SubAppInfo;
import com.huawei.hms.kit.awareness.sdk.HHA;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AwarenessInBean {
    private static final String DEFAULT_STRING = "{}";
    private static final String SUB_APPID = "subAppId";
    private static final String SUB_APP_PACKAGE_NAME = "subAppPackageName";
    private static final String TAG = "AwarenessInBean";
    private static final String URL = "urls";
    private static final String VERSION_NAME = "versionName";
    private JSONObject mInBeanJson = new JSONObject();

    public String toJsonString() {
        String jSONObject = this.mInBeanJson.toString();
        return c.a(jSONObject) ? "{}" : jSONObject;
    }

    public void setInBeanJsonString(SubAppInfo subAppInfo) {
        try {
            this.mInBeanJson.put(SUB_APPID, subAppInfo.getSubAppId());
            this.mInBeanJson.put(SUB_APP_PACKAGE_NAME, subAppInfo.getSubAppPackageName());
            this.mInBeanJson.put("urls", subAppInfo.getUrls());
            this.mInBeanJson.put(VERSION_NAME, HHA.d);
        } catch (JSONException unused) {
            com.huawei.hms.kit.awareness.b.a.c.d(TAG, "json convert failed", new Object[0]);
        }
    }
}
