package com.huawei.hwappdfxmgr.crashmgr;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.dfx.block.MainThreadMonitor;
import com.huawei.haf.common.dfx.crash.DefaultCrashHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.EnvironmentUtils;
import health.compact.a.ProcessUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class HealthCrashHandler extends DefaultCrashHandler {
    private static List<String> d = new ArrayList(Arrays.asList("libsqlcipher.so", "libwhiteBoxJniLib.so", "file is not a database", "database disk image is malformed", "disk I/O error"));
    private AtomicBoolean c;
    private e e;

    @Override // com.huawei.haf.common.dfx.crash.DefaultCrashHandler, com.huawei.haf.common.dfx.crash.CrashCallback
    public boolean isAllowSelfKill() {
        return false;
    }

    public HealthCrashHandler() {
        super("HealthCrashHandler");
        this.c = new AtomicBoolean(true);
    }

    @Override // com.huawei.haf.common.dfx.crash.DefaultCrashHandler, com.huawei.haf.common.dfx.crash.CrashCallback
    public boolean isAllowRethrow() {
        return this.c.get();
    }

    @Override // com.huawei.haf.common.dfx.crash.DefaultCrashHandler
    public boolean d(Throwable th, String str, String str2, StringBuilder sb) {
        this.c.set(!c(str2));
        if (!(th instanceof UnsatisfiedLinkError)) {
            return true;
        }
        Iterator<String> it = d.iterator();
        while (it.hasNext()) {
            if (a(it.next(), str, str2, sb)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        long currentTimeMillis = System.currentTimeMillis();
        RemoteConfigUtils.d("crashConfig", new RemoteConfigUtils.ConfigCallback() { // from class: com.huawei.hwappdfxmgr.crashmgr.HealthCrashHandler.4
            @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
            public void onSuccess(String str) {
                try {
                    HealthCrashHandler.this.e = (e) new Gson().fromJson(str, e.class);
                } catch (JsonSyntaxException e2) {
                    ReleaseLogUtil.c("HealthCrashHandler", "exception happens ", e2.getMessage());
                }
            }

            @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
            public void onFailure(Exception exc, String str) {
                LogUtil.h("HealthCrashHandler", "exception happens ", exc.getMessage());
            }
        }, 1);
        LogUtil.a("HealthCrashHandler", "downloadRemoteConfigInfo, time cost: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public void c() {
        if (EnvironmentUtils.b()) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwappdfxmgr.crashmgr.HealthCrashHandler.2
            @Override // java.lang.Runnable
            public void run() {
                HealthCrashHandler.this.e();
            }
        });
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hwappdfxmgr.crashmgr.HealthCrashHandler.5
            @Override // java.lang.Runnable
            public void run() {
                if (!EnvironmentUtils.c()) {
                    return;
                }
                MainThreadMonitor.a(new HealthAnrHandler());
                while (true) {
                    try {
                        Looper.loop();
                    } catch (Throwable th) {
                        String d2 = DfxUtils.d(Thread.currentThread(), th);
                        boolean c = HealthCrashHandler.this.c(d2);
                        Object[] objArr = new Object[3];
                        objArr[0] = c ? "Matched!" : "Not Matched!";
                        objArr[1] = " and crashMessage is ";
                        objArr[2] = d2;
                        LogUtil.a("HealthCrashHandler", objArr);
                        if (!c) {
                            throw th;
                        }
                    }
                }
            }
        });
        LogUtil.a("HealthCrashHandler", "enable finished");
    }

    class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("keyWords")
        private String[] f6177a;

        @SerializedName("versionName")
        private String b;

        @SerializedName("matchRule")
        private int c;

        @SerializedName("currentPage")
        private String d;

        public String toString() {
            return "CrashConfig{versionName='" + this.b + "', keyWords='" + Arrays.toString(this.f6177a) + "', currentPage='" + this.d + "'}";
        }

        public boolean a(String str) {
            if (!CommonUtil.bv()) {
                ReleaseLogUtil.e("HealthCrashHandler", "crash happens because app not release version");
                return false;
            }
            for (String str2 : this.f6177a) {
                if (str.contains(str2)) {
                    ReleaseLogUtil.e("HealthCrashHandler", "crash intercepted, app not crash");
                    return true;
                }
            }
            return false;
        }
    }

    public boolean c(String str) {
        e eVar = this.e;
        return eVar != null && eVar.a(str);
    }

    @Override // com.huawei.haf.common.dfx.crash.DefaultCrashHandler
    public void d(String str, String str2, String str3) {
        boolean z = !EnvironmentInfo.c(getContext());
        LogUtil.c("HealthCrashHandler", "onSendCrash isAppSigned=", Boolean.valueOf(z));
        if (z) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("crash_type", str3);
            linkedHashMap.put(OpAnalyticsConstants.MONITOR_INFO, str2);
            linkedHashMap.put(OpAnalyticsConstants.PROC_NAME, ProcessUtil.b());
            linkedHashMap.put(OpAnalyticsConstants.ANDROID_ID, FoundationCommonUtil.getAndroidId(getContext()));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_CRASH_90030001.value(), linkedHashMap);
            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
            linkedHashMap2.put("excetion", str);
            OpAnalyticsUtil.getInstance().setEvent(OperationKey.HEALTH_APP_CRASH_2050003.value(), linkedHashMap2);
        }
    }
}
