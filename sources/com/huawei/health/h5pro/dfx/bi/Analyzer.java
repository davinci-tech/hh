package com.huawei.health.h5pro.dfx.bi;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.h5pro.utils.AppInfoUtil;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.webkit.H5ProLoadingRecordManager;
import com.huawei.hianalytics.v2.HiAnalytics;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Analyzer {
    public static boolean b = false;
    public static boolean d = false;
    public static AnalyzerProxy h;
    public static volatile Map<String, String> j;
    public H5ProAppInfo f;
    public static final Object c = new Object();
    public static final LinkedHashMap<String, String> e = new LinkedHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    public static final List<String> f2382a = new ArrayList();
    public static final ArrayList<String> g = new ArrayList<>();
    public long n = 0;
    public String k = "";
    public String i = "";

    public void visitH5App(String str, String str2) {
        ParamBuilder put;
        if (c(str)) {
            if (!TextUtils.isEmpty(str)) {
                put = new ParamBuilder().putClickType().put("source", str);
            } else {
                if (TextUtils.isEmpty(str2)) {
                    LogUtil.w("H5PRO_BIAnalytics", "visitH5App: Invalid parameter");
                    return;
                }
                put = new ParamBuilder().putClickType().put("source", AppInfoUtil.getInstance().getHostFromUrl(str2)).put("url", str2);
            }
            putBiEvent("2120006", put.toJson());
        }
    }

    public void startLoading(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.k = CommonUtil.filterUrl(str);
        this.n = System.currentTimeMillis();
    }

    public void setGlobalParams(HashMap<String, String> hashMap) {
        synchronized (this) {
            j = hashMap;
        }
    }

    public void setAppInfo(H5ProAppInfo h5ProAppInfo) {
        this.f = h5ProAppInfo;
    }

    public void resume(String str, H5ProAppInfo h5ProAppInfo, AnalyzerObj analyzerObj) {
        if (c(h5ProAppInfo == null ? "" : h5ProAppInfo.getPkgName())) {
            String replace = (analyzerObj == null || TextUtils.isEmpty(analyzerObj.getId())) ? AppInfoUtil.getInstance().getHostOrPkgNameFroUrl(str, h5ProAppInfo).replace(".", "_") : analyzerObj.getId();
            if (TextUtils.isEmpty(replace)) {
                LogUtil.w("H5PRO_BIAnalytics", "resume: id is empty");
            } else {
                HiAnalytics.onResume(replace, new ParamBuilder().putUrl(str).putCustomData(analyzerObj == null ? null : analyzerObj.getCustomData()).build());
                this.i = str;
            }
        }
    }

    public void resume(String str, H5ProAppInfo h5ProAppInfo) {
        resume(str, h5ProAppInfo, null);
    }

    public void reportLoadResult(String str, String str2, H5ProAppInfo h5ProAppInfo) {
        setAppInfo(h5ProAppInfo);
        putAiopsEvent("80060001", new ParamBuilder().withUrl(str, h5ProAppInfo).put("flag", str2).build());
    }

    public void reportCode(H5ProInstance h5ProInstance, String str) {
        putAiopsEvent("80060003", new ParamBuilder().withInstance(h5ProInstance).put(OpAnalyticsConstants.ERROR_CODE, str).build());
    }

    public void putBiEvent(String str, String str2) {
        H5ProAppInfo h5ProAppInfo = this.f;
        if (!c(h5ProAppInfo == null ? "" : h5ProAppInfo.getPkgName())) {
            LogUtil.w("H5PRO_BIAnalytics", "putBiEvent-1: !isReady");
            return;
        }
        LinkedHashMap<String, String> c2 = c(this.f);
        c2.put("CONTENT", String.format(Locale.ENGLISH, "[%s]", str2));
        HiAnalytics.onEvent(0, str, c2);
        LogUtil.d("H5PRO_BIAnalytics", "putBiEvent-1 key:" + str + " content:" + c2);
    }

    public void putBiEvent(Context context, String str, JSONObject jSONObject) {
        H5ProAppInfo h5ProAppInfo = this.f;
        if (!c(h5ProAppInfo == null ? "" : h5ProAppInfo.getPkgName())) {
            LogUtil.w("H5PRO_BIAnalytics", "putBiEvent-2: !isReady");
            return;
        }
        LinkedHashMap<String, String> c2 = c(this.f);
        AnalyzerProxy analyzerProxy = h;
        if (analyzerProxy != null && analyzerProxy.putBiEvent(context, str, c2, jSONObject)) {
            LogUtil.w("H5PRO_BIAnalytics", "putBiEvent-2: consumed by proxy");
            return;
        }
        c2.put("CONTENT", String.format(Locale.ENGLISH, "[%s]", jSONObject.toString()));
        HiAnalytics.onEvent(0, str, c2);
        LogUtil.d("H5PRO_BIAnalytics", "putBiEvent-2 key:" + str + " content:" + c2);
    }

    public void putAiopsEvent(String str, LinkedHashMap<String, String> linkedHashMap) {
        synchronized (this) {
            H5ProAppInfo h5ProAppInfo = this.f;
            if (c(h5ProAppInfo == null ? "" : h5ProAppInfo.getPkgName())) {
                H5ProAppInfo h5ProAppInfo2 = this.f;
                if (h5ProAppInfo2 != null) {
                    if (!TextUtils.isEmpty(h5ProAppInfo2.getVersion())) {
                        linkedHashMap.put("version", this.f.getVersion());
                    }
                    if (!TextUtils.isEmpty(this.f.getPkgName())) {
                        linkedHashMap.put(SdkCfgSha256Record.PKGNAME, this.f.getPkgName());
                    }
                }
                HiAnalytics.onEvent(1, str, linkedHashMap);
            }
        }
    }

    public void pause(String str, H5ProAppInfo h5ProAppInfo, AnalyzerObj analyzerObj) {
        if (c(h5ProAppInfo == null ? "" : h5ProAppInfo.getPkgName())) {
            HiAnalytics.onPause((analyzerObj == null || TextUtils.isEmpty(analyzerObj.getId())) ? AppInfoUtil.getInstance().getHostOrPkgNameFroUrl(str, h5ProAppInfo).replace(".", "_") : analyzerObj.getId(), new ParamBuilder().putUrl(str).putCustomData(analyzerObj == null ? null : analyzerObj.getCustomData()).build());
        }
    }

    public void pause(String str, H5ProAppInfo h5ProAppInfo) {
        pause(str, h5ProAppInfo, null);
    }

    public void loadPageErr(H5ProInstance h5ProInstance, String str, int i) {
        if (h5ProInstance.getAppInfo() != null) {
            this.f = h5ProInstance.getAppInfo();
        }
        putAiopsEvent("80060003", new ParamBuilder().withInstance(h5ProInstance).put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(i)).put("resourceUrl", str).build());
    }

    public void loadNewPage(String str, H5ProAppInfo h5ProAppInfo) {
        if (c(h5ProAppInfo == null ? "" : h5ProAppInfo.getPkgName())) {
            if (!TextUtils.isEmpty(this.i) && !this.i.equals(str) && !this.i.equals(CommonUtil.filterUrl(str))) {
                pause(this.i, h5ProAppInfo);
                resume(str, h5ProAppInfo);
            }
            this.i = str;
        }
    }

    public void leave() {
        this.i = "";
        this.n = 0L;
        this.k = "";
        HiAnalytics.onReport();
    }

    public void endLoading(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String filterUrl = CommonUtil.filterUrl(str);
        if (TextUtils.isEmpty(this.k) || !this.k.equals(filterUrl)) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.n;
        this.n = 0L;
        this.k = "";
        putAiopsEvent("80060002", new ParamBuilder().putDomain(filterUrl).put(OpAnalyticsConstants.DELAY_MS, String.valueOf(currentTimeMillis - j2)).put(OpAnalyticsConstants.URL_HEADER, filterUrl).build());
    }

    public void biStartLoadH5App(String str, String str2) {
        biH5Load(str, str2, 2);
    }

    public boolean biReportOnPageEnd(String str, String str2) {
        JSONObject loadingRecord = H5ProLoadingRecordManager.e.getLoadingRecord(str);
        if (loadingRecord == null) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str2)) {
                jSONObject = new JSONObject(str2);
            }
            jSONObject.put("pageId", jSONObject.optString("pageId", str));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                loadingRecord.put(next, jSONObject.get(next));
            }
        } catch (JSONException e2) {
            LogUtil.e("H5PRO_BIAnalytics", "biReportOnPageEnd: exception -> " + e2.getMessage());
        }
        putBiEvent("2120006", loadingRecord.toString());
        return true;
    }

    public void biH5Load(String str, String str2, int i) {
        if (TextUtils.isEmpty(str)) {
            str = str2;
        }
        putBiEvent("2120165", new ParamBuilder().putClickType().put("Steps", String.valueOf(i)).put("H5package", str).toJson());
    }

    public void biFinishLoadH5App(String str, String str2) {
        biH5Load(str, str2, 3);
    }

    public static void setEnvironmentParam(JSONObject jSONObject) {
        synchronized (c) {
            if (jSONObject != null) {
                try {
                    JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        e.put(next, jSONObject2.optString(next));
                    }
                } catch (JSONException e2) {
                    LogUtil.e("H5PRO_BIAnalytics", "setEnvironmentParam: exception -> " + e2.getMessage());
                }
            }
            e.put("MANUFACTURER", Build.MANUFACTURER);
        }
    }

    public static void setAnalyzerProxy(AnalyzerProxy analyzerProxy) {
        synchronized (Analyzer.class) {
            if (h == null && analyzerProxy != null) {
                h = analyzerProxy;
            }
        }
    }

    private boolean c(String str) {
        return (d || (!TextUtils.isEmpty(str) && f2382a.contains(str))) && b();
    }

    private boolean b() {
        if (b) {
            return true;
        }
        if (HiAnalytics.getInitFlag()) {
            b = true;
        }
        return b;
    }

    public static boolean isGlobalParamKeys(String str) {
        boolean contains;
        synchronized (Analyzer.class) {
            contains = g.contains(str);
        }
        return contains;
    }

    public static Map<String, String> getGlobalParams() {
        synchronized (Analyzer.class) {
            if (j != null && !j.isEmpty()) {
                return Collections.unmodifiableMap(j);
            }
            return Collections.emptyMap();
        }
    }

    public static void enable(boolean z) {
        d = z;
    }

    private LinkedHashMap<String, String> c(H5ProAppInfo h5ProAppInfo) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        Map<String, String> globalParams = getGlobalParams();
        if (!globalParams.isEmpty()) {
            linkedHashMap.putAll(globalParams);
        }
        synchronized (c) {
            linkedHashMap.putAll(e);
        }
        if (h5ProAppInfo != null) {
            if (!TextUtils.isEmpty(h5ProAppInfo.getVersion())) {
                linkedHashMap.put("version", h5ProAppInfo.getVersion());
            }
            if (!TextUtils.isEmpty(h5ProAppInfo.getPkgName())) {
                linkedHashMap.put(SdkCfgSha256Record.PKGNAME, h5ProAppInfo.getPkgName());
            }
        }
        return linkedHashMap;
    }

    public static void addGlobalParamKeys(List<String> list) {
        synchronized (Analyzer.class) {
            g.addAll(list);
        }
    }

    public static void addForceBiPackageName(List<String> list) {
        synchronized (Analyzer.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        addForceBiPackageName(it.next());
                    }
                }
            }
        }
    }

    public static void addForceBiPackageName(String str) {
        synchronized (Analyzer.class) {
            if (!TextUtils.isEmpty(str)) {
                List<String> list = f2382a;
                if (!list.contains(str)) {
                    list.add(str);
                }
            }
        }
    }
}
