package defpackage;

import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class phn {
    public static phs d() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        phm phmVar = new phm();
        phmVar.e(0);
        phmVar.a(1);
        phmVar.b(1);
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        String b = lql.b(healthDataCloudFactory.getBody(phmVar));
        LogUtil.a("SCUI_ThreeCircleTaskCloudHelper", "queryTask body ", b);
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("achievementUrl");
        phs phsVar = (phs) lqi.d().d(url + "/achievement/queryTaskInfo", healthDataCloudFactory.getHeaders(), b, phs.class);
        countDownLatch.countDown();
        LogUtil.a("SCUI_ThreeCircleTaskCloudHelper", "queryTask response", phsVar);
        try {
            if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("SCUI_ThreeCircleTaskCloudHelper", "queryTask wait timeout with 3s");
                return phsVar;
            }
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("SCUI_ThreeCircleTaskCloudHelper", "interrupted while waiting for requestData");
        }
        return phsVar;
    }

    public static void e(ResultCallback<phq> resultCallback, String str) {
        phm phmVar = new phm();
        phmVar.d(str);
        a(phmVar, "/achievement/subscribeTask", phq.class, resultCallback);
    }

    public static void a(ResultCallback<phq> resultCallback, phs phsVar) {
        phh phhVar = new phh();
        phhVar.c(phsVar.e());
        phhVar.c(phsVar.h());
        a(phhVar, "/achievement/acquirePoints", phq.class, resultCallback);
    }

    public static void c(ResultCallback<phl> resultCallback) {
        a(new php(), "/achievement/queryPointsNewAdd", phl.class, resultCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void a(final php phpVar, final String str, final Class<T> cls, final ResultCallback<T> resultCallback) {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pho
                @Override // java.lang.Runnable
                public final void run() {
                    phn.a(php.this, str, cls, resultCallback);
                }
            });
            return;
        }
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        String b = lql.b(healthDataCloudFactory.getBody(phpVar));
        LogUtil.a("SCUI_ThreeCircleTaskCloudHelper", "url: ", str, "callHttpPostCommon body ", b);
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("achievementUrl");
        lqi.d().b(url + str, healthDataCloudFactory.getHeaders(), b, cls, resultCallback);
    }
}
