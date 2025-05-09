package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import health.compact.a.KeyValDbManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class nry {
    public static boolean e() {
        Context context = BaseApplication.getContext();
        if (!Utils.i()) {
            return false;
        }
        String e = KeyValDbManager.b(context).e("key_profile_sync_user_info_complete_" + knl.e(""));
        LogUtil.a("ProfileUtil", "isSyncComplete value=", e);
        return String.valueOf(true).equalsIgnoreCase(e);
    }

    public static void a(boolean z) {
        Context context = BaseApplication.getContext();
        if (Utils.i()) {
            LogUtil.a("ProfileUtil", "setSyncComplete isComplete=", Boolean.valueOf(z));
            KeyValDbManager.b(context).d("key_profile_sync_user_info_complete_" + knl.e(""), String.valueOf(z), null);
        }
    }

    public static boolean b() {
        if (Utils.i() && !e()) {
            LogUtil.h("ProfileUtil", "canShowSettingDialog isSyncComplete false");
            return false;
        }
        if (d("key_profile_setting_dialog_show_count_") >= 2) {
            return false;
        }
        long a2 = a();
        return a2 == 0 || System.currentTimeMillis() - a2 > 2592000000L;
    }

    public static boolean c() {
        if (Utils.i() && !e()) {
            LogUtil.h("ProfileUtil", "canShowSettingDialog isSyncComplete false");
            return false;
        }
        if (d("key_person_info_setting_dialog_show_count_") >= 2) {
            return false;
        }
        LogUtil.a("ProfileUtil", "canShowSettingDialog isSyncComplete true");
        return true;
    }

    public static int d(String str) {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e(str + knl.e(""));
        LogUtil.a("ProfileUtil", "getShowCount showCount=", e);
        if (TextUtils.isEmpty(e)) {
            return 0;
        }
        try {
            return Integer.parseInt(e);
        } catch (NumberFormatException e2) {
            LogUtil.b("ProfileUtil", "getShowCount e=", ExceptionUtils.d(e2));
            return 0;
        }
    }

    public static long a() {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("key_profile_setting_dialog_last_show_time_" + knl.e(""));
        LogUtil.a("ProfileUtil", "getLastShowTime lastShowTime=", e);
        if (TextUtils.isEmpty(e)) {
            return 0L;
        }
        try {
            return Long.parseLong(e);
        } catch (NumberFormatException e2) {
            LogUtil.b("ProfileUtil", "getLastShowTime e=", ExceptionUtils.d(e2));
            return 0L;
        }
    }

    public static void c(Context context, IBaseResponseCallback iBaseResponseCallback) {
        d(context, true, iBaseResponseCallback);
    }

    public static void d(Context context, boolean z, IBaseResponseCallback iBaseResponseCallback, int i) {
        CustomTextAlertDialog.Builder d = d(context, z, iBaseResponseCallback);
        if (d != null) {
            d.e().setTextColor(i);
            d.c().setTextColor(i);
        }
    }

    private static CustomTextAlertDialog.Builder d(final Context context, final boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        if (z && !b()) {
            LogUtil.h("ProfileUtil", "showSettingDialog canShowSettingDialog false");
            e(iBaseResponseCallback, -2);
            return null;
        }
        if (!(context instanceof Activity)) {
            LogUtil.h("ProfileUtil", "showSettingDialog not activity");
            e(iBaseResponseCallback, -3);
            return null;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        CustomTextAlertDialog.Builder cyU_ = new CustomTextAlertDialog.Builder(context).b(R$string.IDS_profile_dialog_title).d(R$string.IDS_hw_health_user_profile_not_set_tips).cyR_(R$string.IDS_profile_set_later, new View.OnClickListener() { // from class: nry.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ProfileUtil", "showSettingDialog cancel");
                nry.b(context, AnalyticsValue.WEIGHT_INPUT_DATA_2160126.value(), -1);
                if (z) {
                    nry.d();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R$string.IDS_hw_health_user_profile_go_setting, new View.OnClickListener() { // from class: nry.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(context, KakaConstants.USER_INFO_URL);
                nry.b(context, AnalyticsValue.WEIGHT_INPUT_DATA_2160126.value(), 1);
                intent.setPackage(context.getPackageName());
                if (intent.resolveActivity(context.getPackageManager()) == null) {
                    LogUtil.h("ProfileUtil", "showSettingDialog resolveActivity == null");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                atomicBoolean.set(false);
                LogUtil.a("ProfileUtil", "showSettingDialog setting");
                if (z) {
                    nry.d();
                }
                try {
                    ((Activity) context).startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("ProfileUtil", "StartUserInfoActivity exception", e.getMessage());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        b(context, AnalyticsValue.WEIGHT_INPUT_DATA_2160126.value(), 0);
        CustomTextAlertDialog a2 = cyU_.a();
        a2.setCanceledOnTouchOutside(false);
        a2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: nrx
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                nry.cKo_(atomicBoolean, iBaseResponseCallback, dialogInterface);
            }
        });
        a2.show();
        return cyU_;
    }

    static /* synthetic */ void cKo_(AtomicBoolean atomicBoolean, IBaseResponseCallback iBaseResponseCallback, DialogInterface dialogInterface) {
        boolean z = atomicBoolean.get();
        ReleaseLogUtil.e("ProfileUtil", "isCancelDialog ", Boolean.valueOf(z));
        e(iBaseResponseCallback, z ? -1 : 0);
    }

    public static void b(Context context, String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(context, str, hashMap, 0);
    }

    public static void d() {
        int i;
        Context context = BaseApplication.getContext();
        String e = knl.e("");
        KeyValDbManager b = KeyValDbManager.b(context);
        b.d("key_profile_setting_dialog_last_show_time_" + e, String.valueOf(System.currentTimeMillis()), null);
        String str = "key_profile_setting_dialog_show_count_" + e;
        String e2 = b.e(str);
        LogUtil.a("ProfileUtil", "updateShowCountAndTime count=", e2);
        if (TextUtils.isEmpty(e2)) {
            b.d(str, Integer.toString(1), null);
            return;
        }
        try {
            i = Integer.parseInt(e2);
        } catch (NumberFormatException e3) {
            LogUtil.b("ProfileUtil", "updateShowCountAndTime e=", ExceptionUtils.d(e3));
            i = 0;
        }
        b.d(str, Integer.toString(i + 1), null);
    }

    private static void e(IBaseResponseCallback iBaseResponseCallback, int i) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, null);
        }
    }
}
