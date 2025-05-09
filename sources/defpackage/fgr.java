package defpackage;

import android.content.Context;
import android.os.SystemClock;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.health.sportservice.download.cloud.VoiceDataApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class fgr implements VoiceDataApi {
    @Override // com.huawei.health.sportservice.download.cloud.VoiceDataApi
    /* renamed from: getVoiceBroadcastList, reason: merged with bridge method [inline-methods] */
    public void e(final Context context, final List<fha> list, final UiCallback<List<fgy>> uiCallback) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_VoiceDataImpl", "getVoiceBroadcastList: ");
            if (uiCallback != null) {
                uiCallback.onFailure(-1, "broadcastInfoList is empty");
                return;
            }
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: fgw
                @Override // java.lang.Runnable
                public final void run() {
                    fgr.this.e(context, list, uiCallback);
                }
            });
            return;
        }
        ReleaseLogUtil.b("Suggestion_VoiceDataImpl", " getVoiceBroadcastList begin.");
        beb bebVar = new beb();
        fgx fgxVar = new fgx();
        fgxVar.b(list);
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        lqi.d().b(fgxVar.getUrl(), bebVar.getHeaders(), lql.b(bebVar.getBody(fgxVar)), fgv.class, new ResultCallback<fgv>() { // from class: fgr.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(fgv fgvVar) {
                ReleaseLogUtil.b("Suggestion_VoiceDataImpl", " getVoiceBroadcastList data=", fgvVar);
                if (fgvVar == null || fgvVar.getResultCode() == null || fgvVar.getResultCode().intValue() != 0) {
                    fgr.a(uiCallback);
                    if (fgvVar == null || fgvVar.getResultCode() == null) {
                        return;
                    }
                    fgr.this.e(fgvVar.getResultCode().intValue(), elapsedRealtime);
                    return;
                }
                fgr.c(fgvVar.d(), uiCallback);
                fgr.this.b(elapsedRealtime);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.b("Suggestion_VoiceDataImpl", "getVoiceBroadcastList failed. throwable= ", LogAnonymous.b(th));
                fgr.a(uiCallback);
                fgr.this.e(9999, elapsedRealtime);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void c(T t, UiCallback<T> uiCallback) {
        if (uiCallback != null) {
            uiCallback.onSuccess(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void a(UiCallback<T> uiCallback) {
        if (uiCallback != null) {
            uiCallback.onFailure(-2, ResultUtil.d(9999));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        if (elapsedRealtime >= 5000) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4);
            linkedHashMap.put("module", "3");
            linkedHashMap.put("status", "0");
            linkedHashMap.put(OpAnalyticsConstants.DELAY, String.valueOf(elapsedRealtime));
            linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(200));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_REQUEST_MODULE_85030001.value(), linkedHashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4);
        linkedHashMap.put("module", "3");
        linkedHashMap.put("status", "1");
        linkedHashMap.put(OpAnalyticsConstants.DELAY, String.valueOf(elapsedRealtime - j));
        linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(i));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_REQUEST_MODULE_85030001.value(), linkedHashMap);
    }
}
