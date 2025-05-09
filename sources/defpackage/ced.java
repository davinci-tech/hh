package defpackage;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.networkclient.ResultCallback;
import defpackage.cdz;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class ced {
    public static void e(String str, int i, final UiCallback<njb> uiCallback) {
        ReleaseLogUtil.e("ThreeCircle_CloudManager", "querySummary queryType", str, ", queryDate:", Integer.valueOf(i));
        cdz e = new cdz.c().a(str).e(i).e();
        lqi.d().b(e.getUrl(), a().getHeaders(), lql.b(a().getBody(e)), njb.class, new ResultCallback<njb>() { // from class: ced.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(njb njbVar) {
                if (njbVar == null) {
                    ReleaseLogUtil.c("ThreeCircle_CloudManager", "querySummary data == null");
                } else {
                    ReleaseLogUtil.e("ThreeCircle_CloudManager", "querySummary onSuccess.", moj.e(njbVar));
                    UiCallback.this.onSuccess(njbVar);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                if (th != null) {
                    ReleaseLogUtil.d("ThreeCircle_CloudManager", "querySummary throwable:", th.getMessage());
                } else {
                    ReleaseLogUtil.d("ThreeCircle_CloudManager", "querySummary throwable null");
                }
            }
        });
    }

    public static void b(String str, int i, final UiCallback<nje> uiCallback) {
        ReleaseLogUtil.e("ThreeCircle_CloudManager", "queryEncourage queryType", str, ", queryDate:", Integer.valueOf(i));
        cdz e = new cdz.c().a(str).e(i).e();
        lqi.d().b(e.getUrl(), a().getHeaders(), lql.b(a().getBody(e)), nje.class, new ResultCallback<nje>() { // from class: ced.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(nje njeVar) {
                if (njeVar == null) {
                    ReleaseLogUtil.c("ThreeCircle_CloudManager", "queryEncourage data == null");
                } else {
                    ReleaseLogUtil.e("ThreeCircle_CloudManager", "queryEncourage onSuccess.", moj.e(njeVar));
                    UiCallback.this.onSuccess(njeVar);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                if (th != null) {
                    ReleaseLogUtil.d("ThreeCircle_CloudManager", "queryEncourage throwable:", th.getMessage());
                } else {
                    ReleaseLogUtil.d("ThreeCircle_CloudManager", "queryEncourage throwable null");
                }
            }
        });
    }

    private static HealthDataCloudFactory a() {
        return new HealthDataCloudFactory(BaseApplication.e());
    }
}
