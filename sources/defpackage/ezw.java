package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class ezw {
    public static void b(String str) {
        d dVar = (d) GsonUtil.parseJson(str, d.class);
        LogUtil.a("SleepPushUtils", "intelSleepPush msgContext: ", dVar);
        if (dVar == null) {
            LogUtil.h("SleepPushUtils", "msgContext is null.");
            return;
        }
        int i = dVar.b;
        if (i != 601) {
            LogUtil.h("SleepPushUtils", "parsesContext detailType undefined: ", Integer.valueOf(i));
        } else {
            d();
        }
    }

    private static void d() {
        ReleaseLogUtil.e("SleepPushUtils", "detailType is MONTHLY_SEND_MSG");
        new pln().e();
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BUBBLE_ON_WEBVIEW_FOR_MONTH_DATA", Boolean.toString(true), (StorageParams) null);
    }

    static class d {

        @SerializedName(HealthZonePushReceiver.DETAIL_TYPE)
        public int b;

        private d() {
        }
    }
}
