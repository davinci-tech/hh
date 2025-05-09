package com.huawei.health.h5pro.dfx.bi;

import android.text.TextUtils;
import com.huawei.health.h5pro.utils.AppInfoUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ParamBuilder {

    /* renamed from: a, reason: collision with root package name */
    public final LinkedHashMap<String, String> f2383a = new LinkedHashMap<>();

    public ParamBuilder withUrl(String str, H5ProAppInfo h5ProAppInfo) {
        if (!TextUtils.isEmpty(str)) {
            this.f2383a.put("domain", AppInfoUtil.getInstance().getHostOrPkgNameFroUrl(str, h5ProAppInfo));
            this.f2383a.put("url", str);
        }
        return this;
    }

    public ParamBuilder withInstance(H5ProInstance h5ProInstance) {
        if (h5ProInstance != null) {
            this.f2383a.put("domain", AppInfoUtil.getInstance().getHostOrPkgNameFroUrl(h5ProInstance.getUrl(), h5ProInstance.getAppInfo()));
            this.f2383a.put("url", h5ProInstance.getUrl());
        }
        return this;
    }

    public String toJson() {
        return new JSONObject(build()).toString();
    }

    public ParamBuilder putUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2383a.put("url", str);
        }
        return this;
    }

    public ParamBuilder putDomain(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2383a.put("domain", str);
        }
        return this;
    }

    public ParamBuilder putCustomData(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            this.f2383a.putAll(map);
        }
        return this;
    }

    public ParamBuilder putClickType() {
        this.f2383a.put("click", "1");
        return this;
    }

    public ParamBuilder put(String str, String str2) {
        this.f2383a.put(str, str2);
        return this;
    }

    public LinkedHashMap<String, String> build() {
        this.f2383a.put("platform", "h5pro");
        return this.f2383a;
    }
}
