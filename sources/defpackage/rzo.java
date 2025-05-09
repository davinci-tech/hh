package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class rzo {
    public static void d(Context context) {
        if (context == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "goToActivity context is null.");
            return;
        }
        String value = AnalyticsValue.HEALTH_ACTIVITY_WEBVIEW_MY_ACTIVITY_1100035.value();
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("from", 2);
        ixx.d().d(context, value, hashMap, 0);
        if (Utils.o()) {
            if (CommonUtil.as()) {
                b("domainContentcenterDbankcdnNew", "/cch5/HuaweiHealth/activityBeta/web/html/myActivity.html?pullfrom=myActivity", context);
                return;
            } else {
                b("domainContentcenterDbankcdnNew", "/cch5/HuaweiHealth/activity/web/html/myActivity.html?pullfrom=myActivity", context);
                return;
            }
        }
        b("domainContentcenterDbankcdnNew", ((ActivityHtmlPathApi) Services.c("PluginOperation", ActivityHtmlPathApi.class)).getActivityHtmlPath() + "myActivity.html?pullfrom=myActivity", context);
    }

    /* renamed from: rzo$1, reason: invalid class name */
    class AnonymousClass1 implements GrsQueryCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f16976a;
        final /* synthetic */ Context b;

        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
        public void onCallBackFail(int i) {
        }

        AnonymousClass1(String str, Context context) {
            this.f16976a = str;
            this.b = context;
        }

        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
        public void onCallBackSuccess(String str) {
            LogUtil.c("UIME_PersonalCenterFragment", "GRSManager onCallBackSuccess ACTIVITY_KEY url = ", str);
            String str2 = str + this.f16976a;
            final Intent intent = new Intent(this.b, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", str2);
            intent.putExtra("EXTRA_BI_SOURCE", "ACT");
            intent.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
            final Context context = this.b;
            HandlerExecutor.e(new Runnable() { // from class: rzr
                @Override // java.lang.Runnable
                public final void run() {
                    context.startActivity(intent);
                }
            });
        }
    }

    private static void b(String str, String str2, Context context) {
        GRSManager.a(context).e(str, new AnonymousClass1(str2, context));
    }

    public static void b(int i, String str, String str2) {
        LogUtil.a("UIME_PersonalCenterFragment", "get message MSG_GET_DEVICE_LOG, bugTypeId = " + i + ", dtsNumber = " + str + ", fileLogId = " + str2);
        long currentTimeMillis = System.currentTimeMillis();
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "Crowd_test_last_time");
        try {
            long parseLong = TextUtils.isEmpty(b) ? 0L : Long.parseLong(b);
            LogUtil.a("UIME_PersonalCenterFragment", "WearHomeActivity curTime = ", Long.valueOf(currentTimeMillis), ", lastTime = ", Long.valueOf(parseLong));
            if (currentTimeMillis - parseLong < 300000) {
                return;
            }
        } catch (NumberFormatException e) {
            LogUtil.b("UIME_PersonalCenterFragment", "WearHomeActivity... e = ", e.getMessage());
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "Crowd_test_last_time", String.valueOf(currentTimeMillis), new StorageParams(0));
        jgp a2 = jgp.a(BaseApplication.getContext());
        a2.d(i, str, str2);
        a2.c(0, new DeviceDfxBaseResponseCallback() { // from class: rzo.2
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
            public void onProgress(int i2, String str3) {
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
            public void onSuccess(int i2, String str3) {
                LogUtil.a("UIME_PersonalCenterFragment", "onSuccess sucCode = ", Integer.valueOf(i2));
                jgp.a(BaseApplication.getContext()).d(false, str3);
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxBaseResponseCallback
            public void onFailure(int i2, String str3) {
                ReleaseLogUtil.c("UIME_PersonalCenterFragment", "onFailure errCode = ", Integer.valueOf(i2), ", errMsg = ", str3);
                jgp.a(BaseApplication.getContext()).d(false, jsd.j(str3));
            }
        });
    }
}
