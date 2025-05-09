package com.huawei.health.receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.health.weight.WeightApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dsl;
import defpackage.eme;
import health.compact.a.Services;

/* loaded from: classes8.dex */
public class PullPackageService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private static final IBinder f2955a = null;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("PullPackageService", "PullPackageService is start");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent == null) {
            return 2;
        }
        String stringExtra = intent.getStringExtra("packageName");
        LogUtil.a("PullPackageService", "packageName:", stringExtra);
        if (!TextUtils.isEmpty(stringExtra)) {
            e(stringExtra);
        }
        stopSelf(i2);
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return f2955a;
    }

    private void e(String str) {
        char c;
        LogUtil.a("PullPackageService", "dispatchCommand packageName ", str);
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1518033795) {
            if (str.equals("com.huawei.ohos.fastinglite")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1101632487) {
            if (hashCode == -416151250 && str.equals("com.huawei.health.healthyliving")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("com.huawei.health.wear.golf")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
            LogUtil.a("PullPackageService", "onStartCommand weightApi ", weightApi);
            if (weightApi != null) {
                weightApi.registerFastingLite();
                return;
            }
            return;
        }
        if (c != 1) {
            if (c != 2) {
                return;
            }
            dsl.g();
        } else {
            eme b = eme.b();
            if (b != null) {
                b.initGolfDeviceEngineManager();
            } else {
                LogUtil.b("PullPackageService", "golf start trackManagerProxy failed");
            }
        }
    }
}
