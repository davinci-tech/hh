package com.huawei.hihealthservice.hihealthkit.util;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.ipb;
import defpackage.ipe;
import defpackage.ipg;
import defpackage.ipq;
import defpackage.ipv;
import defpackage.ipy;
import defpackage.iqg;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AppStatusHelper {

    /* renamed from: a, reason: collision with root package name */
    private String f4197a;
    private boolean c;
    private LinkedTreeMap<String, iqg> b = new LinkedTreeMap<>();
    private LinkedTreeMap<String, iqg> h = new LinkedTreeMap<>();
    private LinkedTreeMap<String, Long> d = new LinkedTreeMap<>();
    private long e = 0;
    private long j = 0;

    public AppStatusHelper(Context context) {
        this.c = true;
        this.c = !Utils.c(context.getApplicationContext());
        d();
    }

    private void d() {
        try {
            this.f4197a = c();
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), "AppStatusHelper", "beta_scope");
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "AppStatusHelper", "pass_scope");
            String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "AppStatusHelper", "beta_User");
            if (a(b)) {
                this.b = d(b);
            }
            if (this.b == null) {
                this.b = new LinkedTreeMap<>();
            }
            if (a(b2)) {
                this.h = d(b2);
            }
            if (this.h == null) {
                this.h = new LinkedTreeMap<>();
            }
            if (a(b3)) {
                JSONObject jSONObject = new JSONObject(b3);
                if (jSONObject.optString("betaUserId", "").equals(this.f4197a)) {
                    this.d = (LinkedTreeMap) new Gson().fromJson(jSONObject.optString("betaUser", "{}"), new TypeToken<LinkedTreeMap<String, Long>>() { // from class: com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper.4
                    }.getType());
                }
                if (this.d == null) {
                    this.d = new LinkedTreeMap<>();
                }
            }
        } catch (JSONException e) {
            ReleaseLogUtil.d("R_HiH_AppStatusHelper", "initHelper JSONException: ", e.getMessage());
        }
    }

    private LinkedTreeMap<String, iqg> d(String str) {
        try {
            return (LinkedTreeMap) new Gson().fromJson(str, new TypeToken<LinkedTreeMap<String, iqg>>() { // from class: com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper.5
            }.getType());
        } catch (JsonSyntaxException e) {
            String message = e.getMessage();
            ReleaseLogUtil.c("R_HiH_AppStatusHelper", "deserializeFromJson JsonSyntaxException msg=", message);
            if (message == null || !message.contains("duplicate key")) {
                return null;
            }
            try {
                return (LinkedTreeMap) new GsonBuilder().registerTypeAdapter(LinkedTreeMap.class, new JsonDeserializer<LinkedTreeMap<String, iqg>>() { // from class: com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper.3
                    @Override // com.google.gson.JsonDeserializer
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public LinkedTreeMap<String, iqg> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return (LinkedTreeMap) new Gson().fromJson(jsonElement, type);
                    }
                }).create().fromJson(str, new TypeToken<LinkedTreeMap<String, iqg>>() { // from class: com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper.2
                }.getType());
            } catch (JsonSyntaxException e2) {
                ReleaseLogUtil.c("R_HiH_AppStatusHelper", "deserializeFromJson JsonSyntaxException msg=", e2.getMessage());
                return null;
            }
        }
    }

    public int c(String str, int i, int i2) {
        if (TextUtils.isEmpty(this.f4197a) || !this.f4197a.equals(c())) {
            ReleaseLogUtil.e("R_HiH_AppStatusHelper", "user changed: ", this.f4197a);
            d();
        }
        ReleaseLogUtil.e("R_HiH_AppStatusHelper", "checkBetaScope");
        if (i == 0 || !this.c) {
            ReleaseLogUtil.e("R_HiH_AppStatusHelper", "not need to check beta scope, return success");
            return 0;
        }
        try {
            if (!a(str, ipq.a(i2, i == 1))) {
                ReleaseLogUtil.e("R_HiH_AppStatusHelper", "isBetaScope: false, callback success");
                return 0;
            }
            return i(str);
        } catch (NetworkErrorException unused) {
            ReleaseLogUtil.c("R_HiH_AppStatusHelper", "checkBetaScope failed, callback ", 1004);
            return 1004;
        }
    }

    private String c() {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        LogUtil.d("AppStatusHelper", "getHuid: ", e);
        return e;
    }

    private boolean a(String str, List<String> list) throws NetworkErrorException {
        if (!this.b.containsKey(str)) {
            this.b.put(str, new iqg());
        }
        if (c(str, list)) {
            ReleaseLogUtil.e("R_HiH_AppStatusHelper", "check scope by cache, result is pass");
            return false;
        }
        if (c(str)) {
            try {
                b(str);
            } catch (InterruptedException e) {
                ReleaseLogUtil.d("R_HiH_AppStatusHelper", "getDataFromHealthKit InterruptedException: ", e.getMessage());
            }
        }
        List<String> scopeList = this.b.get(str).getScopeList();
        for (String str2 : list) {
            if (a(str2) && scopeList.contains(str2)) {
                ReleaseLogUtil.d("R_HiH_AppStatusHelper", "isBetaScope: true");
                return true;
            }
        }
        return false;
    }

    private int i(String str) throws NetworkErrorException {
        if (j(str)) {
            try {
                e(str);
            } catch (InterruptedException e) {
                ReleaseLogUtil.d("R_HiH_AppStatusHelper", "checkUser InterruptedException: ", e.getMessage());
            }
        }
        boolean containsKey = this.d.containsKey(str);
        ReleaseLogUtil.e("R_HiH_AppStatusHelper", "isBetaUser: ", Boolean.valueOf(containsKey));
        if (containsKey) {
            return 0;
        }
        return HiHealthStatusCodes.USER_OF_BETA_APP_EXCEED_RANGE;
    }

    private boolean c(String str, List<String> list) throws NetworkErrorException {
        if (!this.h.containsKey(str)) {
            this.h.put(str, new iqg());
        }
        if (h(str)) {
            ReleaseLogUtil.e("R_HiH_AppStatusHelper", "scope cache expired, get scope from cloud");
            try {
                b(str);
            } catch (InterruptedException e) {
                ReleaseLogUtil.c("R_HiH_AppStatusHelper", "getDataFromHealthKit InterruptedException:", e.getMessage());
            }
        }
        List<String> scopeList = this.h.get(str).getScopeList();
        for (String str2 : list) {
            LogUtil.d("AppStatusHelper", "need scope: ", str2);
            if (a(str2) && scopeList.contains(str2)) {
                ReleaseLogUtil.e("R_HiH_AppStatusHelper", "isPassScope: true");
                return true;
            }
        }
        return false;
    }

    private boolean c(String str) {
        if (this.b.containsKey(str)) {
            this.e = this.b.get(str).getUpdateTimeStamp();
        }
        return System.currentTimeMillis() - this.e > 86400000;
    }

    private boolean h(String str) {
        if (this.h.containsKey(str)) {
            this.j = this.h.get(str).getUpdateTimeStamp();
        }
        return System.currentTimeMillis() - this.j > 604800000;
    }

    private boolean j(String str) {
        LinkedTreeMap<String, Long> linkedTreeMap = this.d;
        return linkedTreeMap == null || linkedTreeMap.get(str) == null || (this.d.get(str).longValue() != 0 && System.currentTimeMillis() - this.d.get(str).longValue() > 604800000);
    }

    private void b(final String str) throws InterruptedException, NetworkErrorException {
        ReleaseLogUtil.e("R_HiH_AppStatusHelper", "enter getDataFromHealthKit");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = {false};
        ipb.d().c(this, new ipe(str).getRequestParamsBuilder().e(true).b(new OnRequestCallBack<ipv>() { // from class: com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper.1
            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ipv ipvVar) {
                ReleaseLogUtil.e("R_HiH_AppStatusHelper", "getDataFromHealthKit has success response");
                if (ipvVar == null || ipvVar.getAppInfos() == null || ipvVar.getAppInfos().size() == 0) {
                    zArr[0] = true;
                    ReleaseLogUtil.c("R_HiH_AppStatusHelper", "AppInfo is empty :", str);
                    countDownLatch.countDown();
                    return;
                }
                ipy.a appAuditInfo = ipvVar.getAppInfos().get(0).getAppAuditInfo();
                if (appAuditInfo != null) {
                    if (!TextUtils.isEmpty(appAuditInfo.getBetaScopes()) || !TextUtils.isEmpty(appAuditInfo.getPassedScopes())) {
                        if (AppStatusHelper.this.a(appAuditInfo.getBetaScopes()) || AppStatusHelper.this.a(appAuditInfo.getPassedScopes())) {
                            ReleaseLogUtil.e("R_HiH_AppStatusHelper", "save scope in cache");
                            AppStatusHelper.this.e(str, (List<String>) Arrays.asList(appAuditInfo.getBetaScopes().split(",")));
                            AppStatusHelper.this.d(str, Arrays.asList(appAuditInfo.getPassedScopes().split(",")));
                        }
                    } else {
                        ReleaseLogUtil.c("R_HiH_AppStatusHelper", "App scope is empty :", str);
                        zArr[0] = true;
                        countDownLatch.countDown();
                        return;
                    }
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c("R_HiH_AppStatusHelper", "App scope fail ", "errorCode:", Integer.valueOf(i), " msg ", th.getMessage());
                zArr[0] = true;
                countDownLatch.countDown();
            }
        }).a());
        ReleaseLogUtil.e("R_HiH_AppStatusHelper", "getDataFromHealthKit on time: ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        if (zArr[0]) {
            throw new NetworkErrorException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    private void e(final String str) throws InterruptedException, NetworkErrorException {
        ReleaseLogUtil.e("R_HiH_AppStatusHelper", "enter checkUser");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = {false};
        ipb.d().c(this, new ipg(str, this.f4197a).getRequestParamsBuilder().e(true).b(new OnRequestCallBack<Object>() { // from class: com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper.9
            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            public void onSuccess(Object obj) {
                ReleaseLogUtil.e("R_HiH_AppStatusHelper", "checkUser has success response");
                AppStatusHelper.this.e(str, true);
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealthservice.hihealthkit.cpcheck.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                if (i == 403) {
                    AppStatusHelper.this.e(str, false);
                } else {
                    ReleaseLogUtil.c("R_HiH_AppStatusHelper", "beta user fail ", "errorCode:", Integer.valueOf(i), " msg ", th.getMessage());
                    zArr[0] = true;
                }
                countDownLatch.countDown();
            }
        }).a());
        LogUtil.d("R_HiH_AppStatusHelper", "checkUser on time: ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        if (zArr[0]) {
            throw new NetworkErrorException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, List<String> list) {
        iqg iqgVar = this.h.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        this.j = currentTimeMillis;
        iqgVar.setUpdateTimeStamp(currentTimeMillis);
        iqgVar.getScopeList().clear();
        iqgVar.getScopeList().addAll(list);
        this.h.put(str, iqgVar);
        String json = new Gson().toJson(this.h);
        LogUtil.d("AppStatusHelper", "savePassScopeCache", json);
        SharedPreferenceManager.e(BaseApplication.getContext(), "AppStatusHelper", "pass_scope", json, new StorageParams(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, List<String> list) {
        iqg iqgVar = this.b.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        this.e = currentTimeMillis;
        iqgVar.setUpdateTimeStamp(currentTimeMillis);
        iqgVar.getScopeList().clear();
        iqgVar.getScopeList().addAll(list);
        this.b.put(str, iqgVar);
        String json = new Gson().toJson(this.b);
        LogUtil.d("AppStatusHelper", "saveBetaScopeCache", json);
        SharedPreferenceManager.e(BaseApplication.getContext(), "AppStatusHelper", "beta_scope", json, new StorageParams(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, boolean z) {
        try {
            this.d.put(str, Long.valueOf(z ? System.currentTimeMillis() : 0L));
            JSONObject jSONObject = new JSONObject("{}");
            for (Map.Entry<String, Long> entry : this.d.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            JSONObject jSONObject2 = new JSONObject("{}");
            jSONObject2.put("betaUserId", this.f4197a);
            jSONObject2.put("betaUser", jSONObject);
            String jSONObject3 = jSONObject2.toString();
            LogUtil.d("AppStatusHelper", "saveBetaUserCache", jSONObject3);
            SharedPreferenceManager.e(BaseApplication.getContext(), "AppStatusHelper", "beta_User", jSONObject3, new StorageParams(0));
        } catch (JSONException e) {
            ReleaseLogUtil.c("R_HiH_AppStatusHelper", "saveBetaUserCache JSONException: ", e.getMessage());
        }
    }
}
