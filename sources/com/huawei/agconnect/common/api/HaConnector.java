package com.huawei.agconnect.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.HaConnector;
import com.huawei.agconnect.common.network.AccessNetworkManager;
import com.huawei.agconnect.credential.obs.aw;
import com.huawei.agconnect.credential.obs.ax;
import com.huawei.agconnect.credential.obs.ay;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class HaConnector {
    private static final String HA_HTTP_HEADER = "com.huawei.agconnect";
    private static final String HA_SERVICE_TAG = "AGC_TAG";
    private static final HaConnector INSTANCE = new HaConnector();
    private static final String TAG = "HaConnector";
    private ay hiAnalytics;
    private Handler mainHandler;

    public void syncOAID(final HaSyncCallBack haSyncCallBack) {
        if (this.hiAnalytics != null) {
            Logger.i(TAG, "start sync ha oaid");
            this.hiAnalytics.syncOaid(new aw() { // from class: com.huawei.agconnect.common.api.HaConnector.2
                @Override // com.huawei.agconnect.credential.obs.aw
                public void result(int i, String str) {
                    haSyncCallBack.syncCallBack(i, str);
                    Logger.i(HaConnector.TAG, "end sync ha oaid:code:--->" + i + ", msg---->" + str);
                }
            });
        }
    }

    public void setAnalyticsEnabled(boolean z) {
        ay ayVar = this.hiAnalytics;
        if (ayVar != null) {
            ayVar.c(z);
        }
    }

    public void onReport() {
        ay ayVar = this.hiAnalytics;
        if (ayVar != null) {
            ayVar.a();
        }
    }

    public void onEvent(String str, Bundle bundle) {
        ay ayVar = this.hiAnalytics;
        if (ayVar != null) {
            ayVar.a(str, bundle);
        }
    }

    public void init(Context context) {
        AccessNetworkManager.getInstance().addCallback(new AnonymousClass1(context));
    }

    public Map<String, String> getUserProfiles(boolean z) {
        HashMap hashMap = new HashMap();
        ay ayVar = this.hiAnalytics;
        return ayVar != null ? ayVar.b(z) : hashMap;
    }

    public boolean containHaInstance() {
        return this.hiAnalytics != null;
    }

    private boolean useOldHaInitFunction() {
        return TextUtils.isEmpty(AGConnectInstance.getInstance().getOptions().getString("/service/analytics/collector_url_ru")) || TextUtils.isEmpty(AGConnectInstance.getInstance().getOptions().getString("/service/analytics/collector_url_sg")) || TextUtils.isEmpty(AGConnectInstance.getInstance().getOptions().getString("/service/analytics/collector_url_de")) || TextUtils.isEmpty(AGConnectInstance.getInstance().getOptions().getString("/service/analytics/collector_url_cn"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initInMainThread(Context context) {
        initHiAnalytics(context);
    }

    private void initHiAnalytics(Context context) {
        try {
            ay a2 = useOldHaInitFunction() ? ax.a(context, HA_SERVICE_TAG, HA_HTTP_HEADER) : ax.a(context, HA_SERVICE_TAG, HA_HTTP_HEADER, AGConnectInstance.getInstance().getOptions().getRoutePolicy().getRouteName());
            this.hiAnalytics = a2;
            if (a2 == null) {
                Logger.e(TAG, "init HiAnalyticsBridge SDK error, please make sure the project inherits HiAnalytics SDK correctlyor download the latest agconnect-services.json file and try again.");
            } else {
                Logger.i(TAG, "init HiAnalyticsBridge SDK end.");
            }
        } catch (Error e) {
            if (e instanceof NoSuchMethodError) {
                Logger.e(TAG, "please upgrade HiAnalytics SDK (com.huawei.hms:hianalytics) to the latest version");
            }
            throw e;
        }
    }

    public static HaConnector getInstance() {
        HaConnector haConnector;
        synchronized (HaConnector.class) {
            haConnector = INSTANCE;
        }
        return haConnector;
    }

    /* renamed from: com.huawei.agconnect.common.api.HaConnector$1, reason: invalid class name */
    class AnonymousClass1 implements AccessNetworkManager.AccessNetworkCallback {
        final /* synthetic */ Context val$context;

        @Override // com.huawei.agconnect.common.network.AccessNetworkManager.AccessNetworkCallback
        public void onNetWorkReady() {
            if (HaConnector.this.mainHandler == null) {
                HaConnector.this.mainHandler = new Handler(Looper.getMainLooper());
            }
            Handler handler = HaConnector.this.mainHandler;
            final Context context = this.val$context;
            handler.post(new Runnable() { // from class: com.huawei.agconnect.common.api.HaConnector$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HaConnector.AnonymousClass1.this.m120x8d4882c8(context);
                }
            });
            HaConnector.this.mainHandler.postDelayed(new Runnable() { // from class: com.huawei.agconnect.common.api.HaConnector$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    HaConnector.AnonymousClass1.this.m121x2c2a909();
                }
            }, 5000L);
        }

        /* renamed from: lambda$onNetWorkReady$1$com-huawei-agconnect-common-api-HaConnector$1, reason: not valid java name */
        /* synthetic */ void m121x2c2a909() {
            if (HaConnector.this.hiAnalytics != null) {
                Logger.i(HaConnector.TAG, "Auto onReport");
                HaConnector.this.hiAnalytics.a();
            }
        }

        /* renamed from: lambda$onNetWorkReady$0$com-huawei-agconnect-common-api-HaConnector$1, reason: not valid java name */
        /* synthetic */ void m120x8d4882c8(Context context) {
            HaConnector.this.initInMainThread(context);
        }

        AnonymousClass1(Context context) {
            this.val$context = context;
        }
    }

    private HaConnector() {
    }
}
