package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.utils.Constants;
import health.compact.a.KeyValDbManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class goj {

    /* renamed from: a, reason: collision with root package name */
    private static goj f12884a;
    private static final Object b = new Object();

    public static goj a() {
        goj gojVar;
        synchronized (b) {
            if (f12884a == null) {
                f12884a = new goj();
            }
            gojVar = f12884a;
        }
        return gojVar;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:16|(3:25|26|27)|28|(1:30)|31|32|33|26|27) */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00cd, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ce, code lost:
    
        health.compact.a.ReleaseLogUtil.c("R_HwIdAccountLogoutUtil", "walletAccountRemove exception:", r11.getClass().getSimpleName());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void aQB_(android.content.Context r10, android.content.Intent r11) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.goj.aQB_(android.content.Context, android.content.Intent):void");
    }

    private void c(Context context) {
        HiHealthNativeApi.a(context).hiLogout(new HiCommonListener() { // from class: goj.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                ReleaseLogUtil.b("R_HwIdAccountLogoutUtil", "accountmigrate: hiLogout success");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                ReleaseLogUtil.c("R_HwIdAccountLogoutUtil", "accountmigrate: hiLogout failure");
            }
        });
    }

    private boolean aQA_(Context context, Intent intent) {
        boolean isLogined = LoginInit.getInstance(BaseApplication.getContext()).getIsLogined();
        LogUtil.a("HwIdAccountLogoutUtil", "isLoginAccount is : ", Boolean.valueOf(isLogined));
        return context != null && intent != null && isLogined && Action.ACTION_HW_ACCOUNT_LOGOUT.equals(intent.getAction());
    }

    private String aQz_(Intent intent) {
        if (intent == null) {
            return null;
        }
        try {
            String stringExtra = intent.getStringExtra(JsbMapKeyNames.H5_USER_ID);
            LogUtil.a("HwIdAccountLogoutUtil", "receive account removed broadcast, userId:", stringExtra);
            return stringExtra;
        } catch (BadParcelableException e) {
            LogUtil.b("HwIdAccountLogoutUtil", "BadParcelableException exception :", e.getMessage());
            return null;
        } catch (Exception unused) {
            LogUtil.b("HwIdAccountLogoutUtil", "Exception logoutHwIdAccount");
            return null;
        }
    }

    private void e(Context context) {
        SharedPreferenceManager.e(context, "socialsharedpreference", Constants.IS_SIGN_VMALL_ARG, Boolean.toString(true), (StorageParams) null);
        SharedPreferenceManager.e(context, Integer.toString(10015), "key_ui_login_last_time", "", new StorageParams());
        SharedPreferenceManager.c(BaseApplication.getContext(), Integer.toString(10000), "privacy_last_clear_cloud_data_time");
        SharedPreferenceManager.c(context, Integer.toString(10000), "grs_config_last_update_time");
        SharedPreferenceManager.c(context, Integer.toString(20002), "sport_sort_list_paras");
        SharedPreferenceManager.c(context, String.valueOf(10000), "AB_TEST_STRATEGY_INFO");
        SharedPreferenceManager.c(context, String.valueOf(10000), "AB_TEST_BI_INFO");
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "USER_VIP_INFO_KEY", "", new StorageParams());
        SharedPreferenceManager.c(context, "threeCircleSp", "animConfigInfo");
        SharedPreferenceManager.c(context, "threeCircleSp", "getAnimConfigInfoTime");
        SharedPreferenceManager.c(context, Integer.toString(10000), "kid_account_reboot_before");
        SharedPreferenceManager.c(context, Integer.toString(10000), "SHOW_PROMOTION_DIALOG");
        SharedPreferenceManager.c(context, Integer.toString(2039), "beta_log_switch");
        SharedPreferenceManager.c(context, Integer.toString(Constants.START_TO_INDOOR_RUNNING), "beta_debug_log_switch");
    }

    private void e() {
        Context context = BaseApplication.getContext();
        SharedPreferenceManager.e(context, "BIND_WEIGHT", "bind_weight_time", "", new StorageParams());
        ArrayList arrayList = new ArrayList(Arrays.asList("EXCE_SCORE__ERROR", "EXCE_LIGHTSLEEP__MIN_ERROR", "EXCE_LIGHTSLEEP__MIX _ERROR", "EXCE_DEEPSLEEP__MIN_ERROR", "EXCE_DEEPSLEEP__MIX _ERROR", "EXCE_REMLEEP__MIN_ERROR", "EXCE_REMSLEEP__MIX _ERROR", "EXCE_BREATH__SCORE_ERROR", "EXCE_TRUSLEEP__TIME_ERROR", "EXCE_NORMALSLEEP__TIME_ERROR"));
        KeyValDbManager.b(context).e("key_device_code", "");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sdk.c().c("", (String) it.next());
        }
        qpm.d(false);
        SharedPreferenceManager.c(context, Integer.toString(10100), "FirstJudgeSetFlase");
        SharedPreferenceManager.j(context, "privacy_center");
        SharedPreferenceManager.c(context, String.valueOf(1004), "cloud_user_privacy701");
        sbc.b();
    }
}
