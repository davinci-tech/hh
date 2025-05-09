package com.huawei.hms.network;

import android.content.Context;
import com.huawei.hms.feature.dynamic.DynamicModule;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.Utils;
import com.huawei.hms.framework.common.hianalytics.LinkedHashMapPack;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class RemoteInitializer {
    private static final String b = "RemoteInitializer";
    private static final String c = "huawei_module_networkkit";

    /* renamed from: a, reason: collision with root package name */
    private Context f5110a;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final String f5113a = "req_start_time";
        private static final String b = "total_time";
        private static final String c = "error_code";
        private static final String d = "exception_name";
        private static final String e = "message";
        private static final String f = "split_modules_msg";
        private static final String g = "split_module";
        public static final String h = "kit_version";
        public static final String i = "network_load_analytics";
        private static final int j = 10000900;
        private static final int k = 10000901;
    }

    boolean isInited() {
        return this.f5110a != null;
    }

    public Future init(Context context) {
        Future<?> submit;
        synchronized (this) {
            ExecutorService newSingleThreadExecutor = ExecutorsUtils.newSingleThreadExecutor("Loading_NetworkKit");
            submit = newSingleThreadExecutor.submit(new a(context, newSingleThreadExecutor));
        }
        return submit;
    }

    Context getRemoteContext() {
        return this.f5110a;
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5111a;
        final /* synthetic */ ExecutorService b;

        /* renamed from: com.huawei.hms.network.RemoteInitializer$a$a, reason: collision with other inner class name */
        class RunnableC0138a implements Runnable {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ LinkedHashMapPack f5112a;

            @Override // java.lang.Runnable
            public void run() {
                PLSharedPreferences pLSharedPreferences = new PLSharedPreferences(a.this.f5111a, b.i);
                for (Map.Entry<String, String> entry : this.f5112a.getAll().entrySet()) {
                    pLSharedPreferences.putString(entry.getKey(), entry.getValue());
                }
            }

            RunnableC0138a(LinkedHashMapPack linkedHashMapPack) {
                this.f5112a = linkedHashMapPack;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            LinkedHashMapPack linkedHashMapPack = new LinkedHashMapPack();
            linkedHashMapPack.put("req_start_time", Utils.getCurrentTime(false));
            long currentTime = Utils.getCurrentTime(true);
            RemoteInitializer remoteInitializer = RemoteInitializer.this;
            remoteInitializer.f5110a = remoteInitializer.a(RemoteInitializer.c, this.f5111a, linkedHashMapPack);
            linkedHashMapPack.put("total_time", Utils.getCurrentTime(true) - currentTime);
            this.b.execute(new RunnableC0138a(linkedHashMapPack));
        }

        a(Context context, ExecutorService executorService) {
            this.f5111a = context;
            this.b = executorService;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context a(String str, Context context, LinkedHashMapPack linkedHashMapPack) {
        DynamicModule.enable3rdPhone(str, true);
        DynamicModule.enableLowEMUI(str, true);
        try {
            Context moduleContext = DynamicModule.loadV2(context, DynamicModule.PREFER_REMOTE, str).getModuleContext();
            linkedHashMapPack.put("error_code", 10000900L);
            return moduleContext;
        } catch (DynamicModule.LoadingException e) {
            linkedHashMapPack.put("exception_name", e.getClass().getName());
            linkedHashMapPack.put("message", StringUtils.anonymizeMessage(e.getMessage()));
            linkedHashMapPack.put("error_code", 10000901L);
            Logger.w(b, " load module " + str + " failed " + StringUtils.anonymizeMessage(e.getMessage()));
            return null;
        }
    }

    RemoteInitializer() {
    }
}
