package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iki {
    public static void e(List<String> list, List<String> list2, final ResultCallback<ikj> resultCallback) {
        ReleaseLogUtil.e("R_HlthTrendDwnldUt", "downloadHealthTrend enter");
        HealthDataCloudFactory d = jbs.a(BaseApplication.e()).d();
        ikb ikbVar = new ikb();
        ikbVar.a(list);
        ikbVar.c(list2);
        lqi.d().b(ikbVar.getUrl(), d.getHeaders(), lql.b(d.getBody(ikbVar)), ikj.class, new ResultCallback<ikj>() { // from class: iki.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ikj ikjVar) {
                LogUtil.a("HlthTrendDwnldUt", "downloadHealthTrend success response = ", ikjVar);
                ResultCallback.this.onSuccess(ikjVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.e("R_HlthTrendDwnldUt", "downloadHealthTrend fail throwable = ", ExceptionUtils.d(th));
                ResultCallback.this.onFailure(th);
            }
        });
    }

    public static void e(List<String> list, List<String> list2, String str, final ResultCallback<ikj> resultCallback) {
        ReleaseLogUtil.e("R_HlthTrendDwnldUt", "downloadHealthTrendWithRecordDay enter");
        HealthDataCloudFactory d = jbs.a(BaseApplication.e()).d();
        ike ikeVar = new ike();
        ikeVar.a(list);
        ikeVar.c(list2);
        ikeVar.b(str);
        ReleaseLogUtil.e("R_HlthTrendDwnldUt", "downloadHealthTrendWithRecordDay request = ", ikeVar);
        lqi.d().b(ikeVar.getUrl(), d.getHeaders(), lql.b(d.getBody(ikeVar)), ikj.class, new ResultCallback<ikj>() { // from class: iki.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ikj ikjVar) {
                LogUtil.a("HlthTrendDwnldUt", "downloadHealthTrendWithRecordDay success response = ", ikjVar);
                ResultCallback.this.onSuccess(ikjVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.e("R_HlthTrendDwnldUt", "downloadHealthTrendWithRecordDay fail throwable = ", ExceptionUtils.d(th));
                ResultCallback.this.onFailure(th);
            }
        });
    }
}
