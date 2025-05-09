package com.huawei.health.h5pro.jsbridge.system.analyzer;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.dfx.bi.AnalyzerObj;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.LinkedHashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AnalyzerEntry extends JsBaseModule {
    public Analyzer c = new Analyzer();

    @JavascriptInterface
    public void onResume(long j, String str) {
        LogUtil.i(this.TAG, "onResume");
        a(j, str, true);
    }

    @JavascriptInterface
    public void onPause(long j, String str) {
        LogUtil.i(this.TAG, "onPause");
        a(j, str, false);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.c = null;
    }

    @JavascriptInterface
    public void biReportOnPageEnd(long j, String str) {
        String str2;
        LogUtil.i(this.TAG, "biReportOnPageEnd");
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        H5ProAppInfo appInfo = h5ProInstance == null ? null : h5ProInstance.getAppInfo();
        String pkgName = appInfo == null ? "" : appInfo.getPkgName();
        if (TextUtils.isEmpty(pkgName)) {
            str2 = "packageName is empty";
        } else {
            this.c.setAppInfo(appInfo);
            if (this.c.biReportOnPageEnd(pkgName, str)) {
                onSuccessCallback(j);
                return;
            }
            str2 = "no loading record is found";
        }
        onFailureCallback(j, str2);
    }

    @JavascriptInterface
    public void biReport(long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(MedalConstants.EVENT_KEY);
            JSONArray optJSONArray = jSONObject.optJSONArray("msg");
            if (optJSONArray != null) {
                e(optString, a(optJSONArray), j);
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("msg");
            if (optJSONObject != null) {
                e(optString, optJSONObject, j);
            } else {
                onFailureCallback(j, "biReport fail: param invalid");
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "biReport fail: param invalid");
        }
    }

    @JavascriptInterface
    public void aiopsReport(long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("msg");
            if (optJSONArray == null) {
                onFailureCallback(j, "aiopsReport fail: param invalid");
                return;
            }
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                linkedHashMap.put(jSONObject2.getString("name"), jSONObject2.getString("value"));
            }
            if (this.c == null) {
                LogUtil.w(this.TAG, "aiopsReport: analyzer is null");
                return;
            }
            String optString = jSONObject.optString(MedalConstants.EVENT_KEY);
            if (this.mH5ProInstance != null && this.mH5ProInstance.getAppInfo() != null) {
                this.c.setAppInfo(this.mH5ProInstance.getAppInfo());
            }
            this.c.putAiopsEvent(optString, linkedHashMap);
            onSuccessCallback(j, String.format(Locale.ENGLISH, "aiops report success, key = %s, msg = %s", optString, optJSONArray.toString()));
        } catch (JSONException unused) {
            onFailureCallback(j, "aiops report fail:param invalid");
        }
    }

    private void e(String str, JSONObject jSONObject, long j) {
        if (this.c == null) {
            LogUtil.w(this.TAG, "putBiEvent: analyzer is null");
            return;
        }
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance != null && h5ProInstance.getAppInfo() != null) {
            this.c.setAppInfo(this.mH5ProInstance.getAppInfo());
        }
        this.c.putBiEvent(this.mContext, str, jSONObject);
        onSuccessCallback(j, String.format(Locale.ENGLISH, "bi report success, key = %s", str));
    }

    private JSONObject a(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            jSONObject.put(jSONObject2.getString("name"), jSONObject2.getString("value"));
        }
        return jSONObject;
    }

    private void a(long j, String str, boolean z) {
        H5ProInstance h5ProInstance;
        if (this.c == null || (h5ProInstance = this.mH5ProInstance) == null) {
            LogUtil.w(this.TAG, "analyzer or h5ProInstance is null");
            onFailureCallback(j, "report failed");
            return;
        }
        String url = h5ProInstance.getUrl();
        H5ProAppInfo appInfo = this.mH5ProInstance.getAppInfo();
        if (!H5ProTrustListChecker.isTrusted(appInfo, url)) {
            onFailureCallback(j, "authentication failed");
            return;
        }
        AnalyzerObj analyzerObj = (AnalyzerObj) GsonUtil.parseContainsMapJson(str, AnalyzerObj.class);
        if (analyzerObj == null) {
            onFailureCallback(j, "invalid param");
            return;
        }
        if (z) {
            this.c.resume(url, appInfo, analyzerObj);
        } else {
            this.c.pause(url, appInfo, analyzerObj);
        }
        onSuccessCallback(j, 0);
    }
}
