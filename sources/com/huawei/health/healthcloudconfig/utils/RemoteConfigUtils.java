package com.huawei.health.healthcloudconfig.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.remoteconfig.AGConnectConfig;
import com.huawei.agconnect.remoteconfig.ConfigValues;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.AuthorizationUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.security.InvalidParameterException;

/* loaded from: classes.dex */
public final class RemoteConfigUtils {

    public interface ConfigCallback {
        void onFailure(Exception exc, String str);

        void onSuccess(String str);
    }

    public static void d(final String str, final ConfigCallback configCallback) {
        if (Utils.o()) {
            ReleaseLogUtil.b("R_RemoteConfigUtils", "downloadRemoteConfigInfo, not initialize agc oversea ");
            configCallback.onFailure(new Exception("isOversea not use agc"), "");
            return;
        }
        Task<ConfigValues> task = null;
        if (!AuthorizationUtils.a(null)) {
            configCallback.onFailure(new Exception("not authorize"), "");
            return;
        }
        if (AGConnectInstance.getInstance() == null) {
            AGConnectInstance.initialize(BaseApplication.e());
        }
        final AGConnectConfig aGConnectConfig = AGConnectConfig.getInstance();
        if (aGConnectConfig == null) {
            LogUtil.b("RemoteConfigUtils", "AGConnectConfig getInstance is null");
            configCallback.onFailure(new Exception("agc is null"), "");
            return;
        }
        try {
            task = aGConnectConfig.fetch(10L);
        } catch (InvalidParameterException e2) {
            LogUtil.b("RemoteConfigUtils", "InvalidParameterException: ", LogAnonymous.b((Throwable) e2));
        }
        if (task == null) {
            LogUtil.b("RemoteConfigUtils", "task is null");
            configCallback.onFailure(new Exception("task is null"), "");
        } else {
            task.addOnSuccessListener(new OnSuccessListener<ConfigValues>() { // from class: com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.1
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(ConfigValues configValues) {
                    try {
                        AGConnectConfig.this.apply(configValues);
                        String valueAsString = AGConnectConfig.this.getValueAsString(str);
                        LogUtil.a("RemoteConfigUtils", "RemoteConfig success, getValue is: ", valueAsString);
                        configCallback.onSuccess(valueAsString);
                    } catch (NullPointerException e3) {
                        ReleaseLogUtil.c("R_RemoteConfigUtils", "RemoteConfig failure, NullPointerException");
                        configCallback.onFailure(e3, "");
                    }
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.5
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    LogUtil.a("RemoteConfigUtils", "RemoteConfig, fetch onFailure = ", exc.getMessage());
                    AGConnectConfig.this.apply(AGConnectConfig.this.loadLastFetched());
                    String valueAsString = AGConnectConfig.this.getValueAsString(str);
                    LogUtil.a("RemoteConfigUtils", "RemoteConfig failure, lastConfig value = ", valueAsString);
                    configCallback.onFailure(exc, valueAsString);
                }
            });
        }
    }

    public static void d(String str, ConfigCallback configCallback, int i) {
        String str2 = "RemoteConfigUtils_" + str;
        String e2 = KeyValDbManager.b(BaseApplication.e()).e(str2);
        if (TextUtils.isEmpty(e2)) {
            LogUtil.a("RemoteConfigUtils", "trigger first download");
            b(str, str2, configCallback);
            return;
        }
        e eVar = (e) new Gson().fromJson(e2, e.class);
        if (System.currentTimeMillis() - eVar.d >= i * 86400000) {
            b(str, str2, configCallback);
        } else {
            LogUtil.a("RemoteConfigUtils", "return value from local cache");
            configCallback.onSuccess(eVar.f2495a);
        }
    }

    private static void b(String str, final String str2, final ConfigCallback configCallback) {
        d(str, new ConfigCallback() { // from class: com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.2
            @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
            public void onSuccess(String str3) {
                e eVar = new e();
                eVar.d = System.currentTimeMillis();
                eVar.f2495a = str3;
                KeyValDbManager.b(BaseApplication.e()).e(str2, new Gson().toJson(eVar));
                configCallback.onSuccess(str3);
            }

            @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
            public void onFailure(Exception exc, String str3) {
                configCallback.onFailure(exc, str3);
            }
        });
    }

    /* loaded from: classes3.dex */
    static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("value")
        public String f2495a;

        @SerializedName("lastSyncTime")
        public long d;

        private e() {
        }
    }
}
