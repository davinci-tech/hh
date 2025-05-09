package com.huawei.hms.network.file.api;

import android.content.Context;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.file.a.h;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.api.RequestConfig;
import com.huawei.hms.network.file.api.RequestManager;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.core.b;
import com.huawei.hms.network.file.core.d;
import com.huawei.hms.network.file.core.f;
import com.huawei.hms.network.file.core.task.c;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes4.dex */
public class RequestManager<R extends Request> implements IRequestManager<R> {
    private static final String TAG = "RequestManager";
    private static Context appContext;
    private static volatile boolean isInited;
    public Context context;
    public final GlobalRequestConfig globalConfig;
    d managerImpl;
    final String tag;

    public String toString() {
        return "RequestManager{commonConfig=" + this.globalConfig + '}';
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result start(R r, Callback callback) {
        return this.managerImpl.start(r, callback);
    }

    public Result resumeRequest(R r, Callback callback) {
        return this.managerImpl.a(r, callback);
    }

    public Result pauseRequest(long j) {
        return this.managerImpl.a(j);
    }

    public void injectRequestHandler(c cVar) {
        this.managerImpl.a(cVar);
    }

    public String getTag() {
        return this.tag;
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result.STATUS getRequestStatus(long j) {
        return this.managerImpl.getRequestStatus(j);
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public R getRequest(long j) {
        return (R) this.managerImpl.getRequest(j);
    }

    public GlobalRequestConfig getGlobalRequestConfig() {
        return this.globalConfig;
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public List<R> getAllRequests() {
        return this.managerImpl.getAllRequests();
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result destoryRequests() {
        return this.managerImpl.destoryRequests();
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result closeThreadPools() {
        return this.managerImpl.closeThreadPools();
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result cancelRequest(long j) {
        return this.managerImpl.cancelRequest(j);
    }

    private static void setAppContext(Context context) {
        appContext = context.getApplicationContext();
    }

    public static RequestConfig.RequestConfBuilder newRequestConfigBuilder() {
        return new RequestConfig.RequestConfBuilder();
    }

    public static abstract class Builder<T extends Builder> {
        private GlobalRequestConfig globalConfig;
        private String tag;

        public abstract RequestManager build(Context context);

        public String getTag() {
            return this.tag;
        }

        public T commonConfig(GlobalRequestConfig globalRequestConfig) {
            if (globalRequestConfig != null) {
                this.globalConfig = globalRequestConfig;
            }
            return this;
        }

        public Builder(String str) {
            this.tag = "default";
            if (!Utils.isBlank(str)) {
                this.tag = str;
            }
            this.globalConfig = new GlobalRequestConfig.Builder().build();
        }

        public Builder() {
            this(null);
        }
    }

    public static GlobalRequestConfig.Builder newGlobalRequestConfigBuilder() {
        return new GlobalRequestConfig.Builder();
    }

    /* renamed from: com.huawei.hms.network.file.api.RequestManager$1, reason: invalid class name */
    class AnonymousClass1 extends NetworkKit.Callback {
        final /* synthetic */ ThreadPoolExecutor val$executor;

        @Override // com.huawei.hms.network.NetworkKit.Callback
        public void onResult(boolean z) {
            try {
                FLogger.i(RequestManager.TAG, "init result:" + z, new Object[0]);
                com.huawei.hms.network.file.core.c.h();
                h.f().e();
                FLogger.i(RequestManager.TAG, "init httpclient over", new Object[0]);
                this.val$executor.execute(new Runnable() { // from class: com.huawei.hms.network.file.api.RequestManager$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        RequestManager.AnonymousClass1.a();
                    }
                });
                FLogger.i(RequestManager.TAG, "init over", new Object[0]);
            } catch (Throwable unused) {
                FLogger.w(RequestManager.TAG, "NetworkKit.init exception", new Object[0]);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a() {
            FLogger.i(RequestManager.TAG, "init DecisionTreeManager module over", new Object[0]);
            boolean a2 = b.a("core_enable_privacy_policy", false);
            if (a2) {
                FLogger.i(RequestManager.TAG, "init enablePrivacyPolocy", new Object[0]);
                HianalyticsHelper.getInstance().enablePrivacyPolicy(a2);
            }
            String a3 = b.a("core_ha_tag");
            if (!Utils.isBlank(a3)) {
                HianalyticsHelper.getInstance().setHaTag(a3);
            }
            FLogger.i(RequestManager.TAG, "init set haTag:" + a3, new Object[0]);
        }

        AnonymousClass1(ThreadPoolExecutor threadPoolExecutor) {
            this.val$executor = threadPoolExecutor;
        }
    }

    public static void init(Context context) {
        synchronized (RequestManager.class) {
            if (!isInited && context != null) {
                isInited = true;
                ThreadPoolExecutor f = f.f();
                NetworkKit.init(context, new AnonymousClass1(f));
                f.execute(new Runnable() { // from class: com.huawei.hms.network.file.api.RequestManager$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        RequestManager.a();
                    }
                });
            }
        }
    }

    public static Context getAppContext() {
        return appContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a() {
        try {
            com.huawei.hms.network.file.a.k.b.j.b.a();
        } catch (Throwable unused) {
            FLogger.w(TAG, "Secure.initAesGcm() exception", new Object[0]);
        }
    }

    public RequestManager(Builder builder, Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        this.context = context.createDeviceProtectedStorageContext();
        setAppContext(context);
        this.tag = builder.getTag();
        GlobalRequestConfig globalRequestConfig = builder.globalConfig;
        this.globalConfig = globalRequestConfig;
        this.managerImpl = new d(globalRequestConfig);
    }
}
