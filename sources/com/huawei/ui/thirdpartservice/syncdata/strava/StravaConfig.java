package com.huawei.ui.thirdpartservice.syncdata.strava;

import android.os.Handler;
import android.text.TextUtils;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.dql;
import defpackage.drd;
import defpackage.jdx;
import defpackage.koq;
import defpackage.mrx;
import defpackage.sjk;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class StravaConfig {
    private String e;
    private dql f;
    private final Object d = new Object();
    private final Set<String> h = new HashSet(16);
    private final Map<String, b> b = new HashMap(16);
    private Set<StravaCountryCallback> c = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private Set<StravaEnableCallback> f10567a = new HashSet();

    public interface StravaCountryCallback {
        void onStravaCountryCallback(boolean z);
    }

    public interface StravaEnableCallback {
        void onAbilityCallback(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dYe_(final int i, final Handler handler) {
        LogUtil.a("StravaConfig", "enter updateStravaCountryConfig retryCount = ", Integer.valueOf(i));
        if (i < 0) {
            LogUtil.h("StravaConfig", "updateStravaCountryConfig retryCount < 0");
            dYc_(handler);
            return;
        }
        if (this.f == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("deviceType", "stravaMonitor");
            this.f = new dql("com.huawei.health_common_config", hashMap);
        }
        drd.e(this.f, "strava_country", 1, new DownloadCallback<File>() { // from class: com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig.4
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                LogUtil.a("StravaConfig", "updatePpgConfig onFinish, data is: ", file.toString());
                StravaConfig.this.dYd_(file, handler);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str) {
                LogUtil.a("StravaConfig", "updatePpgConfig onProgress, handleBytes: ", Long.valueOf(j), ", contentLength: ", Long.valueOf(j2), ", isDone: ", Boolean.valueOf(z), ", fileId: ", str);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i2, Throwable th) {
                LogUtil.h("StravaConfig", "updatePpgConfig on Fail: ", th.getMessage());
                StravaConfig.this.dYe_(i - 1, handler);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dYd_(File file, Handler handler) {
        sjk sjkVar;
        String e2 = mrx.e(file);
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h("StravaConfig", "parseJsonFile urlJsonString is empty");
            if (handler == null) {
                return;
            } else {
                return;
            }
        }
        try {
            try {
                this.h.clear();
                this.b.clear();
                HashSet hashSet = new HashSet(16);
                JSONArray jSONArray = new JSONArray(e2);
                for (int i = 0; i < jSONArray.length(); i++) {
                    hashSet.add(jSONArray.get(i).toString());
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString("name");
                        JSONArray optJSONArray = optJSONObject.optJSONArray("countries");
                        if (optJSONArray != null) {
                            e(optString, optJSONArray);
                        }
                    }
                }
                if (this.h.isEmpty()) {
                    this.h.addAll(hashSet);
                }
            } catch (JSONException unused) {
                LogUtil.b("StravaConfig", "cache file read error");
                if (handler != null) {
                    sjkVar = new sjk(this);
                }
            }
            if (handler != null) {
                sjkVar = new sjk(this);
                handler.post(sjkVar);
                return;
            }
            a();
        } finally {
            if (handler == null) {
                a();
            } else {
                handler.post(new sjk(this));
            }
        }
    }

    private void e(String str, JSONArray jSONArray) {
        int i = 0;
        if ("entrance".equals(str)) {
            while (i < jSONArray.length()) {
                LogUtil.h("StravaConfig", "mEnableCountry content:", jSONArray.optJSONObject(i).optString("countryCode"));
                this.h.add(jSONArray.optJSONObject(i).optString("countryCode"));
                i++;
            }
            return;
        }
        if ("disability".equals(str)) {
            while (i < jSONArray.length()) {
                b bVar = new b();
                bVar.d = jSONArray.optJSONObject(i).optString("countryCode");
                bVar.f10569a = jSONArray.optJSONObject(i).optString("time");
                LogUtil.h("StravaConfig", "mDisabilityCountry content:", jSONArray.optJSONObject(i).toString());
                this.b.put(bVar.d, bVar);
                i++;
            }
            return;
        }
        LogUtil.h("StravaConfig", "name is disable");
    }

    public void dYg_(Handler handler, String str, StravaCountryCallback stravaCountryCallback) {
        LogUtil.a("StravaConfig", "checkCountrySupport ", str);
        this.e = str;
        this.c.add(stravaCountryCallback);
        jdx.b(new e(handler));
    }

    public void dYf_(Handler handler, String str, StravaEnableCallback stravaEnableCallback) {
        LogUtil.a("StravaConfig", "checkAbilitySupport ", str);
        this.e = str;
        this.f10567a.add(stravaEnableCallback);
        if (koq.b(this.c)) {
            jdx.b(new e(handler));
        } else if (handler == null) {
            a();
        } else {
            handler.post(new sjk(this));
        }
    }

    private void dYc_(Handler handler) {
        LogUtil.a("StravaConfig", "getDefaultCountry");
        String d = drd.d("com.huawei.health_common_config", "strava_country", "json");
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("StravaConfig", "DefaultFilePath is null");
            if (handler == null) {
                a();
                return;
            } else {
                handler.post(new sjk(this));
                return;
            }
        }
        LogUtil.a("StravaConfig", "DefaultCountry :", d);
        dYd_(new File(d), handler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        synchronized (this.d) {
            Iterator<StravaCountryCallback> it = this.c.iterator();
            while (it.hasNext()) {
                StravaCountryCallback next = it.next();
                it.remove();
                if (next != null) {
                    LogUtil.a("StravaConfig", "onStravaCountryCallback ", Boolean.valueOf(this.h.contains(this.e)), this.e);
                    next.onStravaCountryCallback(this.h.contains(this.e));
                }
            }
            Iterator<StravaEnableCallback> it2 = this.f10567a.iterator();
            while (it2.hasNext()) {
                StravaEnableCallback next2 = it2.next();
                it2.remove();
                if (next2 != null) {
                    LogUtil.a("StravaConfig", "Ability Config ", Boolean.valueOf(this.b.containsKey(this.e)), this.e);
                    if (this.b.containsKey(this.e)) {
                        Date c = this.b.get(this.e).c();
                        if (c == null) {
                            next2.onAbilityCallback(false);
                        } else {
                            next2.onAbilityCallback(c.after(new Date(System.currentTimeMillis())));
                        }
                    } else {
                        next2.onAbilityCallback(true);
                    }
                }
            }
        }
    }

    public void b() {
        LogUtil.a("StravaConfig", "release ");
        this.c.clear();
        this.f10567a.clear();
    }

    static final class e implements Runnable {
        private Handler b;
        private WeakReference<StravaConfig> e;

        private e(StravaConfig stravaConfig, Handler handler) {
            this.e = new WeakReference<>(stravaConfig);
            this.b = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            StravaConfig stravaConfig = this.e.get();
            if (stravaConfig == null) {
                return;
            }
            stravaConfig.dYe_(1, this.b);
        }
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        private String f10569a;
        private String d;

        private b() {
        }

        public Date c() {
            if (TextUtils.isEmpty(this.f10569a)) {
                return null;
            }
            String[] split = this.f10569a.split(Constants.LINK);
            if (split.length != 3) {
                return null;
            }
            try {
                return new Date(Integer.parseInt(split[0]) - 1900, Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
            } catch (NumberFormatException e) {
                ReleaseLogUtil.c("StravaConfig", "NumberFormatException", e.getMessage());
                return null;
            }
        }
    }
}
