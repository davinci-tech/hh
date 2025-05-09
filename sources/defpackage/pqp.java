package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class pqp {
    public static void b(final CommonUiBaseResponse commonUiBaseResponse) {
        long j;
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "core_sleep_user_rate_data_update_time");
        if (!TextUtils.isEmpty(b)) {
            try {
                j = Long.parseLong(b);
            } catch (NumberFormatException unused) {
                LogUtil.h("SleepUserRateRequestUtil", "NumberFormatException");
                j = 0;
            }
            if (System.currentTimeMillis() - j < 604800000) {
                LogUtil.a("SleepUserRateRequestUtil", "time less than WEEK_IN_MILLIS");
                a(commonUiBaseResponse);
                return;
            }
        }
        if (!lop.c(BaseApplication.getContext())) {
            LogUtil.h("SleepUserRateRequestUtil", "no network, please check");
            a(commonUiBaseResponse);
        } else {
            c(new HttpResCallback() { // from class: pqq
                @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                public final void onFinished(int i, String str) {
                    pqp.a(CommonUiBaseResponse.this, i, str);
                }
            }, commonUiBaseResponse);
        }
    }

    static /* synthetic */ void a(CommonUiBaseResponse commonUiBaseResponse, int i, String str) {
        if (i != 200 || TextUtils.isEmpty(str)) {
            LogUtil.h("SleepUserRateRequestUtil", "requestUserRateCloudData resCode = ", Integer.valueOf(i), " or result is empty");
            a(commonUiBaseResponse);
        } else {
            a(str, commonUiBaseResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(CommonUiBaseResponse commonUiBaseResponse) {
        a(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "core_sleep_user_rate_data"), commonUiBaseResponse);
    }

    private static void c(final HttpResCallback httpResCallback, final CommonUiBaseResponse commonUiBaseResponse) {
        final HashMap<String, String> e = pfe.e();
        final HashMap<String, String> a2 = pfe.a();
        GRSManager.a(BaseApplication.getContext()).e("achievementUrl", new GrsQueryCallback() { // from class: pqp.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                if (TextUtils.isEmpty(str)) {
                    LogUtil.h("SleepUserRateRequestUtil", "requestUserRateCloudData grs get url is null");
                    pqp.a(CommonUiBaseResponse.this);
                    return;
                }
                jei.d(str + "/achievement/getSleepScoreRank" + CommonUtil.getUrlSuffix(), e, a2, httpResCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("SleepUserRateRequestUtil", "requestUserRateCloudData grs get url errorCode ", Integer.valueOf(i));
                pqp.a(CommonUiBaseResponse.this);
            }
        });
    }

    private static void a(String str, CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.c("SleepUserRateRequestUtil", "parseUserRateData result = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SleepUserRateRequestUtil", "parseUserRateData result is empty");
            if (commonUiBaseResponse != null) {
                commonUiBaseResponse.onResponse(-1, null);
                return;
            }
            return;
        }
        e(str, commonUiBaseResponse);
        c(str);
    }

    private static void e(String str, CommonUiBaseResponse commonUiBaseResponse) {
        ppj ppjVar = (ppj) HiJsonUtil.e(str, ppj.class);
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(0, ppjVar);
        }
    }

    private static void c(String str) {
        Context context = BaseApplication.getContext();
        String num = Integer.toString(PrebakedEffectId.RT_COIN_DROP);
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferenceManager.e(context, num, "core_sleep_user_rate_data_update_time", String.valueOf(currentTimeMillis), new StorageParams());
        if (str == null || !str.equals(a())) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_COIN_DROP), "core_sleep_user_rate_data", str, new StorageParams());
        }
    }

    private static String a() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_COIN_DROP), "core_sleep_user_rate_data");
    }
}
