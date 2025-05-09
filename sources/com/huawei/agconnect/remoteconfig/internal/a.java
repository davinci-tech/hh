package com.huawei.agconnect.remoteconfig.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.abtest.ABTestException;
import com.huawei.agconnect.abtest.AGConnectABTesting;
import com.huawei.agconnect.annotation.Singleton;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.remoteconfig.AGCConfigException;
import com.huawei.agconnect.remoteconfig.AGConnectConfig;
import com.huawei.agconnect.remoteconfig.ConfigValues;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.TaskExecutors;
import com.huawei.hmf.tasks.Tasks;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Singleton
/* loaded from: classes2.dex */
public class a extends AGConnectConfig {

    /* renamed from: a, reason: collision with root package name */
    private static final Executor f1811a = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.huawei.agconnect.remoteconfig.internal.a.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AGC-RemoteConfig-Thread");
        }
    });
    private boolean b = false;
    private boolean c = true;
    private final AGConnectABTesting d;
    private final c e;
    private final c f;
    private final c g;
    private final d h;
    private final com.huawei.agconnect.remoteconfig.internal.b.a i;
    private final com.huawei.agconnect.remoteconfig.internal.a.c j;
    private final Handler k;

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public void setEnableCollectUserPrivacy(boolean z) {
        this.c = z;
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public void setDeveloperMode(boolean z) {
        Logger.v("AGConnectConfig", "set developer mode : " + z);
        Context context = AGConnectInstance.getInstance().getContext();
        if (context.getApplicationInfo() == null || (context.getApplicationInfo().flags & 2) == 0) {
            return;
        }
        this.b = z;
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public void setCustomAttributes(Map<String, String> map) {
        this.i.a(map);
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public ConfigValues loadLastFetched() {
        return this.g;
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public String getValueAsString(String str) {
        return this.h.d(str);
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public Long getValueAsLong(String str) {
        return Long.valueOf(this.h.c(str));
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public Double getValueAsDouble(String str) {
        return Double.valueOf(this.h.b(str));
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public byte[] getValueAsByteArray(String str) {
        return this.h.e(str);
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public Boolean getValueAsBoolean(String str) {
        return Boolean.valueOf(this.h.a(str));
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public AGConnectConfig.SOURCE getSource(String str) {
        c cVar = this.f;
        if (cVar != null && cVar.containKey(str)) {
            Logger.v("AGConnectConfig", "get source remote : " + str);
            return AGConnectConfig.SOURCE.REMOTE;
        }
        c cVar2 = this.e;
        if (cVar2 == null || !cVar2.containKey(str)) {
            Logger.v("AGConnectConfig", "get source static : " + str);
            return AGConnectConfig.SOURCE.STATIC;
        }
        Logger.v("AGConnectConfig", "get source default : " + str);
        return AGConnectConfig.SOURCE.DEFAULT;
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public Map<String, Object> getMergedAll() {
        return this.h.a();
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public boolean getEnableCollectUserPrivacy() {
        return this.c;
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public Map<String, String> getCustomAttributes() {
        return this.i.a();
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public Task<ConfigValues> fetchDiscrete(final long j, long j2) {
        ConfigContainer a2 = this.g.a();
        if (a2 == null) {
            return fetch(j);
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (a2.b(j)) {
            long a3 = a(j2);
            Logger.i("AGConnectConfig", "discrete random time : " + a3);
            this.k.postDelayed(new Runnable() { // from class: com.huawei.agconnect.remoteconfig.internal.a.3
                @Override // java.lang.Runnable
                public void run() {
                    a.this.fetch(j);
                }
            }, a3 * 1000);
        }
        taskCompletionSource.setResult(this.g);
        return taskCompletionSource.getTask();
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public Task<ConfigValues> fetch(final long j) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        f1811a.execute(new Runnable() { // from class: com.huawei.agconnect.remoteconfig.internal.a.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    taskCompletionSource.setResult((ConfigValues) Tasks.await(a.this.b(j)));
                } catch (Exception e) {
                    taskCompletionSource.setException(e);
                }
            }
        });
        return taskCompletionSource.getTask();
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public Task<ConfigValues> fetch() {
        return fetch(43200L);
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public void clearAll() {
        Logger.v("AGConnectConfig", "clear all");
        this.e.b();
        this.f.b();
        this.g.b();
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public void applyDefault(Map<String, Object> map) {
        Logger.v("AGConnectConfig", "apply default map");
        this.e.a(new ConfigContainer(com.huawei.agconnect.remoteconfig.internal.c.b.a(map)));
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public void applyDefault(int i) {
        Logger.v("AGConnectConfig", "apply default resources");
        this.e.a(new ConfigContainer(com.huawei.agconnect.remoteconfig.internal.c.b.a(i)));
    }

    @Override // com.huawei.agconnect.remoteconfig.AGConnectConfig
    public void apply(ConfigValues configValues) {
        Logger.v("AGConnectConfig", "apply config values");
        if (this.g != configValues) {
            throw new IllegalArgumentException("only can apply last fetched config values");
        }
        c cVar = (c) configValues;
        if (cVar.a() != null) {
            this.f.a(cVar.a());
            try {
                if (this.f.a().b() != null) {
                    Logger.v("AGConnectConfig", "replace experiments");
                    this.d.replaceAllExperiments(this.f.a().b());
                }
            } catch (ABTestException e) {
                Logger.e("RemoteConfig", "ab test exception", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<ConfigValues> b(long j) {
        Logger.i("AGConnectConfig", "fetch : " + j);
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (j <= 1) {
            j = 1;
        }
        Logger.i("AGConnectConfig", "isDeveloperMode:" + this.b);
        if (!this.b) {
            j = Math.max(j, 1800L);
        }
        ConfigContainer a2 = this.g.a();
        if (a2 == null || a2.b(j)) {
            String c = a2 != null ? a2.c() : "";
            Logger.i("AGConnectConfig", "config send fetch request");
            this.j.getConfigFromRemote(c, this.i.a()).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<ConfigContainer>() { // from class: com.huawei.agconnect.remoteconfig.internal.a.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(ConfigContainer configContainer) {
                    Logger.v("AGConnectConfig", "fetch success");
                    a.this.g.a(configContainer);
                    taskCompletionSource.setResult(a.this.g);
                }
            }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: com.huawei.agconnect.remoteconfig.internal.a.4
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    Logger.v("AGConnectConfig", "fetch failure");
                    if (!(exc instanceof AGCConfigException) || ((AGCConfigException) exc).getCode() != 204091393) {
                        taskCompletionSource.setException(exc);
                        return;
                    }
                    Logger.i("AGConnectConfig", "data not modified");
                    ConfigContainer a3 = a.this.g.a();
                    if (a3 != null) {
                        a3.a(System.currentTimeMillis());
                        a.this.g.a(a3);
                    }
                    taskCompletionSource.setResult(a.this.g);
                }
            });
        } else {
            Logger.i("AGConnectConfig", "config use cache");
            taskCompletionSource.setResult(this.g);
        }
        return taskCompletionSource.getTask();
    }

    private long a(long j) {
        return (long) (j * ((new SecureRandom().nextInt(1000) + 1) / 1000.0f));
    }

    public a(Context context, AGConnectInstance aGConnectInstance) {
        c cVar = new c("defaultConfigValues", aGConnectInstance);
        this.e = cVar;
        c cVar2 = new c("appliedConfigValues", aGConnectInstance);
        this.f = cVar2;
        this.g = new c("unusedConfigValues", aGConnectInstance);
        this.j = new com.huawei.agconnect.remoteconfig.internal.a.c(aGConnectInstance);
        this.d = AGConnectABTesting.get(context, "REMOTE_CONFIG");
        this.h = new d(cVar2, cVar);
        this.i = new com.huawei.agconnect.remoteconfig.internal.b.a(aGConnectInstance);
        this.k = new Handler(Looper.getMainLooper());
    }
}
