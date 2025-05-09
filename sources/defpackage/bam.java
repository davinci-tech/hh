package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bam {
    public static void c(final String str, final ResponseCallback<drx> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_HealthChallengeTimeUtil", "getHealthLifeChallenges callback is null");
            return;
        }
        if (!azi.aa()) {
            LogUtil.h("HealthLife_HealthChallengeTimeUtil", "getHealthLifeChallenges isJoinHealthModel false source ", str);
            responseCallback.onResponse(0, null);
        } else if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bak
                @Override // java.lang.Runnable
                public final void run() {
                    bam.c(str, responseCallback);
                }
            });
        } else if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h("HealthLife_HealthChallengeTimeUtil", "getHealthLifeChallenges isNetworkConnected false source ", str);
            responseCallback.onResponse(-1, null);
        } else {
            int b = DateFormatUtil.b(System.currentTimeMillis());
            aue.e().d(b, b, new ResultCallback<aul>() { // from class: bam.1
                @Override // com.huawei.networkclient.ResultCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(aul aulVar) {
                    if (aulVar == null) {
                        LogUtil.h("HealthLife_HealthChallengeTimeUtil", "getHealthLifeChallenges onSuccess response is null source ", str);
                        responseCallback.onResponse(-1, null);
                        return;
                    }
                    int a2 = aulVar.a();
                    List<drx> b2 = aulVar.b();
                    if (a2 != 0 || koq.b(b2)) {
                        LogUtil.h("HealthLife_HealthChallengeTimeUtil", "getHealthLifeChallenges onSuccess response ", aulVar, " source ", str);
                        responseCallback.onResponse(a2, null);
                    } else {
                        LogUtil.a("HealthLife_HealthChallengeTimeUtil", "getHealthLifeChallenges onSuccess data ", aulVar.toString());
                        bam.a(b2, responseCallback);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.h("HealthLife_HealthChallengeTimeUtil", "getHealthLifeChallenges onFailure ", LogAnonymous.b(th), " source ", str);
                    responseCallback.onResponse(-1, null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List<drx> list, ResponseCallback<drx> responseCallback) {
        drx drxVar = list.get(0);
        Iterator<drx> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            drx next = it.next();
            if (next != null) {
                int b = next.b();
                if (b == 0) {
                    drxVar = next;
                    break;
                } else if (drxVar == null || b <= drxVar.b()) {
                    LogUtil.c("HealthLife_HealthChallengeTimeUtil", "parseChallenges other time");
                } else {
                    drxVar = next;
                }
            }
        }
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        if (drxVar != null && drxVar.b() == b2) {
            drxVar = null;
        }
        if (drxVar == null) {
            LogUtil.h("HealthLife_HealthChallengeTimeUtil", "parseChallenges jsonObject is null");
            responseCallback.onResponse(0, null);
        } else if (b2 < drxVar.d()) {
            LogUtil.a("HealthLife_HealthChallengeTimeUtil", "switch system time");
            responseCallback.onResponse(0, null);
        } else {
            responseCallback.onResponse(0, drxVar);
        }
    }

    public static void e() {
        c("HealthLife_HealthChallengeTimeUtil", new ResponseCallback() { // from class: bap
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                bam.e(i, (drx) obj);
            }
        });
    }

    static /* synthetic */ void e(int i, drx drxVar) {
        LogUtil.a("HealthLife_HealthChallengeTimeUtil", "setHealthGoalId errorCode ", Integer.valueOf(i), " object ", drxVar);
        if (drxVar == null) {
            return;
        }
        bao.e("health_model_challenge_id", String.valueOf(drxVar.c()));
        bao.e("health_model_current_challenge_join_time", String.valueOf(drxVar.d()));
    }
}
