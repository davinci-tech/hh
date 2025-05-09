package defpackage;

import android.app.Activity;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.homehealth.healthheadlinecard.utils.MediaCenterCallback;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes6.dex */
public class omn {
    public static boolean e() {
        boolean a2 = SharedPreferenceManager.a("healthHeadLines", "isNonWifiTipsShow", true);
        boolean m = NetworkUtil.m();
        boolean i = NetworkUtil.i();
        LogUtil.a("MediaCenter", "isNetworkConnected = ", Boolean.valueOf(i), "; isWifiConnected = ", Boolean.valueOf(m), "; isNeedShowWifiTips = ", Boolean.valueOf(a2));
        return i && !m && a2;
    }

    public static void d(MediaCenterCallback mediaCenterCallback) {
        if (e()) {
            b(mediaCenterCallback);
        } else {
            mediaCenterCallback.onResponse(0, "noNeedToShowDialog");
        }
    }

    private static void b(final MediaCenterCallback mediaCenterCallback) {
        LogUtil.a("MediaCenter", "showWifiTipsDialog");
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("MediaCenter", "context is null when show mobile data dialog.");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(wa_);
        builder.e(R.string._2130846579_res_0x7f022373).czC_(R.string._2130845372_res_0x7f021ebc, new View.OnClickListener() { // from class: omn.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferenceManager.e("healthHeadLines", "isNonWifiTipsShow", false);
                MediaCenterCallback mediaCenterCallback2 = MediaCenterCallback.this;
                if (mediaCenterCallback2 != null) {
                    mediaCenterCallback2.onResponse(0, "success");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: omn.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaCenterCallback mediaCenterCallback2 = MediaCenterCallback.this;
                if (mediaCenterCallback2 != null) {
                    mediaCenterCallback2.onResponse(-1, ParamConstants.CallbackMethod.ON_FAIL);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }
}
