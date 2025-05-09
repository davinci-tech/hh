package com.huawei.health.h5pro.webkit;

import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class InstalledMeta {
    public long c;
    public EnvironmentHelper.BuildType e;

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("installTime", this.c);
            jSONObject.put("buildType", this.e);
        } catch (JSONException e) {
            LogUtil.e("H5PRO_InstalledMeta", "toJSON err: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    public long getInstallTime() {
        return this.c;
    }

    public EnvironmentHelper.BuildType getBuildType() {
        return this.e;
    }

    public InstalledMeta(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.c = jSONObject.optLong("installTime");
            String optString = jSONObject.optString("buildType");
            for (EnvironmentHelper.BuildType buildType : EnvironmentHelper.BuildType.values()) {
                if (buildType.name().equals(optString)) {
                    this.e = buildType;
                }
            }
        } catch (JSONException e) {
            LogUtil.e("H5PRO_InstalledMeta", "parse err: " + e.getMessage());
        }
    }

    public InstalledMeta(long j, EnvironmentHelper.BuildType buildType) {
        this.c = j;
        this.e = buildType;
    }
}
