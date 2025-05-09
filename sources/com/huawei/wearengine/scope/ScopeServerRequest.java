package com.huawei.wearengine.scope;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public final class ScopeServerRequest {
    private static final int CARE_TYPE = 1194;
    private final String appid;
    private final String nspSvc = "nsp.scope.app.get";
    private final String nspFmt = "json";
    private final int type = CARE_TYPE;

    public ScopeServerRequest(String str) {
        this.appid = str;
    }

    public String getUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str + toQueryString();
    }

    public String getAppId() {
        return this.appid;
    }

    private String toQueryString() {
        return "?nsp_svc=" + this.nspSvc + "&nsp_fmt=" + this.nspFmt + "&appid=" + this.appid + "&type=" + this.type;
    }

    public String toString() {
        return "nsp_svc=" + this.nspSvc + ",nsp_fmt=" + this.nspFmt + ",appid=" + this.appid + ",type=" + this.type;
    }
}
