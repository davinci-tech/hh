package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.phk;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes6.dex */
public class phj {
    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        GRSManager.a(BaseApplication.getContext()).e("healthCloudUrl", new GrsQueryCallback() { // from class: phj.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a("SCUI_ThreeCirclePerfectDaysHelper", "hostUrl is ", str);
                phj.this.a(str, iBaseResponseCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                ReleaseLogUtil.d("SCUI_ThreeCirclePerfectDaysHelper", "requestPerformanceBestOfHistory getUrl failed resultCode = ", Integer.valueOf(i));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, IBaseResponseCallback iBaseResponseCallback) {
        Map<String, Object> addPublicParams = HealthEngineRequestManager.addPublicParams();
        HashSet hashSet = new HashSet();
        hashSet.add("accumPerfectGoalAchievedDays");
        addPublicParams.put("accumulatedItems", hashSet);
        Map<String, String> addPublicHeaders = HealthEngineRequestManager.addPublicHeaders();
        b bVar = (b) lqi.d().d(str + "/dataQuery/report/getPersonalReport", addPublicHeaders, lql.b(addPublicParams), b.class);
        if (bVar == null || bVar.b() == null) {
            ReleaseLogUtil.c("SCUI_ThreeCirclePerfectDaysHelper", "requestPerformanceBestOfHistory() PerfectDaysResponses is null");
            iBaseResponseCallback.d(-1, null);
        } else if (bVar.c() != 0) {
            ReleaseLogUtil.d("SCUI_ThreeCirclePerfectDaysHelper", "requestPerformanceBestOfHistory fail resultCode is ", Integer.valueOf(bVar.c()), " resultDesc is ", bVar.a());
            iBaseResponseCallback.d(-1, null);
        } else {
            phk.e a2 = bVar.b().a();
            LogUtil.a("SCUI_ThreeCirclePerfectDaysHelper", "pData is ", a2);
            iBaseResponseCallback.d(0, a2);
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("accumulatedReports")
        private phk f16132a;

        @SerializedName("resultCode")
        private int b;

        @SerializedName("resultDesc")
        private String c;

        private b() {
        }

        public int c() {
            return this.b;
        }

        public String a() {
            return this.c;
        }

        public phk b() {
            return this.f16132a;
        }

        public String toString() {
            return "PerfectDaysResponses{resultCode=" + this.b + ", resultDesc=" + this.c + ", accumulatedReports=" + this.f16132a + '}';
        }
    }
}
