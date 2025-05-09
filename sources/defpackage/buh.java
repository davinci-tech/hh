package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import java.util.List;

/* loaded from: classes3.dex */
public class buh {
    public static void d(final ResultCallback<bua> resultCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: buh.1
            @Override // java.lang.Runnable
            public void run() {
                bty btyVar = new bty();
                lqi.d().b(btyVar.getUrl(), buh.c().getHeaders(), lql.b(buh.c().getBody(btyVar)), bua.class, ResultCallback.this);
            }
        });
    }

    public static void a(final List<Long> list, final ResultCallback<CloudCommonReponse> resultCallback) {
        if (koq.b(list)) {
            LogUtil.a("FamilyCareDataManager", "followUsers is empty");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: buh.5
                @Override // java.lang.Runnable
                public void run() {
                    bub bubVar = new bub();
                    bubVar.d(list);
                    lqi.d().b(bubVar.getUrl(), buh.c().getHeaders(), lql.b(buh.c().getBody(bubVar)), CloudCommonReponse.class, buh.e(resultCallback));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ResultCallback<CloudCommonReponse> e(final ResultCallback<CloudCommonReponse> resultCallback) {
        return new ResultCallback<CloudCommonReponse>() { // from class: buh.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                if (cloudCommonReponse.getResultCode().intValue() == 0) {
                    LogUtil.a("FamilyCareDataManager", "delete success");
                    ResultCallback.this.onSuccess(cloudCommonReponse);
                } else {
                    LogUtil.a("FamilyCareDataManager", "delete failure");
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ResultCallback.this.onFailure(th);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HealthDataCloudFactory c() {
        return new HealthDataCloudFactory(BaseApplication.e());
    }
}
