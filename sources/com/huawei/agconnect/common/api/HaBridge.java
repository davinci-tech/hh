package com.huawei.agconnect.common.api;

import android.os.Bundle;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.credential.obs.aw;
import com.huawei.agconnect.credential.obs.ax;
import com.huawei.agconnect.credential.obs.ay;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.TaskExecutors;
import com.huawei.hmf.tasks.Tasks;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public class HaBridge {
    private static final String HA_HTTP_HEADER = "com.huawei.agconnect";
    public static final String HA_SERVICE_TAG_ABTEST = "AGC_TAG_ABTest";
    public static final String HA_SERVICE_TAG_APP_LINKING = "AGC_TAG_AppLinking";
    public static final String HA_SERVICE_TAG_CONFIG = "AGC_TAG_Config";
    public static final String HA_SERVICE_TAG_CRASH = "Crash_TAG";
    public static final String HA_SERVICE_TAG_IAM = "AGC_TAG_IAM";
    private static final String TAG = "HaBridge";
    private ay bridgeInstance;
    private String haTag;

    public Task<Void> syncOAID(final HaSyncCallBack haSyncCallBack) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        initHaInMain().addOnCompleteListener(new OnCompleteListener() { // from class: com.huawei.agconnect.common.api.HaBridge$$ExternalSyntheticLambda1
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                HaBridge.this.m119lambda$syncOAID$5$comhuaweiagconnectcommonapiHaBridge(haSyncCallBack, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    public Task<Void> onReport() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        initHaInMain().addOnCompleteListener(new OnCompleteListener() { // from class: com.huawei.agconnect.common.api.HaBridge$$ExternalSyntheticLambda2
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ((ay) task.getResult()).a();
            }
        });
        return taskCompletionSource.getTask();
    }

    public Task<Void> onEvent(final String str, final Bundle bundle) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        initHaInMain().addOnCompleteListener(new OnCompleteListener() { // from class: com.huawei.agconnect.common.api.HaBridge$$ExternalSyntheticLambda3
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ((ay) task.getResult()).a(str, bundle);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$syncOAID$5$com-huawei-agconnect-common-api-HaBridge, reason: not valid java name */
    /* synthetic */ void m119lambda$syncOAID$5$comhuaweiagconnectcommonapiHaBridge(final HaSyncCallBack haSyncCallBack, Task task) {
        Logger.i(TAG, "start sync ha oaid");
        ((ay) task.getResult()).syncOaid(new aw() { // from class: com.huawei.agconnect.common.api.HaBridge.1
            @Override // com.huawei.agconnect.credential.obs.aw
            public void result(int i, String str) {
                haSyncCallBack.syncCallBack(i, str);
                Logger.i(HaBridge.TAG, "end sync ha oaid:code:--->" + i + ", msg---->" + str);
            }
        });
    }

    /* renamed from: lambda$initHaInMain$0$com-huawei-agconnect-common-api-HaBridge, reason: not valid java name */
    /* synthetic */ ay m118lambda$initHaInMain$0$comhuaweiagconnectcommonapiHaBridge() {
        ay ayVar = this.bridgeInstance;
        if (ayVar != null) {
            return ayVar;
        }
        try {
            this.bridgeInstance = ax.a(AGConnectInstance.getInstance().getContext(), this.haTag, HA_HTTP_HEADER, AGConnectInstance.getInstance().getOptions().getRoutePolicy().getRouteName());
            Logger.i(TAG, "init HiAnalyticsBridge SDK end.");
            return this.bridgeInstance;
        } catch (Error e) {
            Logger.e(TAG, "please upgrade HiAnalytics SDK (com.huawei.hms:hianalytics) to the latest version");
            throw e;
        }
    }

    public Task<Map<String, String>> getUserProfiles(final boolean z) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        initHaInMain().addOnCompleteListener(new OnCompleteListener() { // from class: com.huawei.agconnect.common.api.HaBridge$$ExternalSyntheticLambda4
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                TaskCompletionSource.this.setResult(((ay) task.getResult()).b(z));
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.agconnect.common.api.HaBridge$$ExternalSyntheticLambda5
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                TaskCompletionSource.this.setResult(null);
            }
        });
        return taskCompletionSource.getTask();
    }

    private Task<ay> initHaInMain() {
        return Tasks.callInBackground(TaskExecutors.uiThread(), new Callable() { // from class: com.huawei.agconnect.common.api.HaBridge$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return HaBridge.this.m118lambda$initHaInMain$0$comhuaweiagconnectcommonapiHaBridge();
            }
        });
    }

    public HaBridge(String str) {
        this.haTag = str;
    }
}
