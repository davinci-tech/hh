package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gmn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class gmn {
    private static UserProfileMgrApi e = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);

    public static void c(int i, Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("SercurityDataCheck", "showUserInfoDialog callback is null");
            return;
        }
        if (!Utils.i()) {
            iBaseResponseCallback.d(0, null);
            return;
        }
        if (context == null) {
            LogUtil.h("SercurityDataCheck", "context is null");
            iBaseResponseCallback.d(0, null);
            return;
        }
        String b = b(2);
        LogUtil.a("SercurityDataCheck", "showUserInfoDialog data = ", b);
        if ("true".equals(b)) {
            iBaseResponseCallback.d(0, null);
            return;
        }
        gnb popOutWindowInfo = e.getPopOutWindowInfo(context, "privacy_base_info_");
        int a2 = popOutWindowInfo.a();
        long c = popOutWindowInfo.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 < 3 && currentTimeMillis - c > Constants.ANALYSIS_EVENT_KEEP_TIME) {
            e.setPopOutWindowInfo(context, "privacy_base_info_");
            if (CommonUtil.bu()) {
                return;
            }
            d(i, context, iBaseResponseCallback);
            return;
        }
        iBaseResponseCallback.d(0, null);
    }

    private static void d(final int i, final Context context, final IBaseResponseCallback iBaseResponseCallback) {
        View inflate = View.inflate(context, R.layout.securyty_notify_custom_view_dialog, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_two);
        d(LoginInit.getInstance(context).getAccountInfo(1009), (HealthTextView) inflate.findViewById(R.id.hw_health_sync_service_desc));
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.hw_health_layout_data_sleep);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.hw_health_layout_data_health);
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(8);
        healthTextView.setText(context.getString(R$string.IDS_hwh_personal_profile_agreement));
        CustomAlertDialog c = new CustomAlertDialog.Builder(context).a(context.getString(R$string.IDS_service_area_notice_title)).cyp_(inflate).cyn_(R$string.IDS_common_disagree, new DialogInterface.OnClickListener() { // from class: gmk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                gmn.aPi_(IBaseResponseCallback.this, i, context, dialogInterface, i2);
            }
        }).cyo_(R$string.IDS_hw_show_agree, new DialogInterface.OnClickListener() { // from class: gmp
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                gmn.aPj_(IBaseResponseCallback.this, context, dialogInterface, i2);
            }
        }).c();
        c.setCancelable(false);
        c.show();
    }

    static /* synthetic */ void aPi_(IBaseResponseCallback iBaseResponseCallback, int i, Context context, DialogInterface dialogInterface, int i2) {
        b(2, false);
        b(300, false);
        iBaseResponseCallback.d(0, null);
        if (i == 2 || i == 3) {
            nrh.e(context, R$string.IDS_hw_me_userinfo_huawei_cloud_data_sync);
        }
        e(context, AnalyticsValue.HEALTH_PER_INFO_DATA_SWITCH_2040052.value(), "0");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
    }

    static /* synthetic */ void aPj_(IBaseResponseCallback iBaseResponseCallback, Context context, DialogInterface dialogInterface, int i) {
        b(2, true);
        b(300, true);
        iBaseResponseCallback.d(0, null);
        e(context, AnalyticsValue.HEALTH_PER_INFO_DATA_SWITCH_2040052.value(), "1");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static void b(Context context) {
        if (Utils.i()) {
            if (context == null) {
                LogUtil.h("SercurityDataCheck", "context is null");
                return;
            }
            boolean[] e2 = e(context);
            if (e2[1]) {
                SharedPreferenceManager.e(context, Integer.toString(10006), "health_data_record_health_agree", "false", new StorageParams());
            } else {
                SharedPreferenceManager.e(context, Integer.toString(10006), "health_data_record_health_agree", "true", new StorageParams());
            }
            if (e2[0] && e2[1]) {
                return;
            }
            gnb popOutWindowInfo = e.getPopOutWindowInfo(context, "privacy_sport_data_");
            int a2 = popOutWindowInfo.a();
            long c = popOutWindowInfo.c();
            long currentTimeMillis = System.currentTimeMillis();
            gnb popOutWindowInfo2 = e.getPopOutWindowInfo(context, "privacy_health_data_");
            int a3 = popOutWindowInfo2.a();
            long c2 = popOutWindowInfo2.c();
            long currentTimeMillis2 = System.currentTimeMillis();
            boolean z = !e2[0] && a2 < 3 && currentTimeMillis - c > Constants.ANALYSIS_EVENT_KEEP_TIME;
            boolean z2 = !e2[1] && a3 < 3 && currentTimeMillis2 - c2 > Constants.ANALYSIS_EVENT_KEEP_TIME;
            if (z || z2) {
                View inflate = View.inflate(context, R.layout.securyty_notify_custom_view_dialog, null);
                aPp_(context, inflate, z, z2);
                aPo_(context, e2, inflate, z, z2);
            }
        }
    }

    private static void aPp_(Context context, View view, boolean z, boolean z2) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.hw_health_layout_data_sport);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.hw_health_layout_data_sleep);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.hw_health_layout_data_health);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_health_sync_service_desc);
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1009);
        d(accountInfo, healthTextView);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_four);
        linearLayout.setVisibility(z ? 0 : 8);
        linearLayout2.setVisibility(8);
        linearLayout3.setVisibility(z2 ? 0 : 8);
        if (linearLayout.getVisibility() == 0) {
            d(context, accountInfo, (HealthTextView) linearLayout.findViewById(R.id.hw_health_service_item_two));
            e.setPopOutWindowInfo(context, "privacy_sport_data_");
        }
        if (linearLayout3.getVisibility() == 0) {
            c(context, accountInfo, healthTextView2);
            e.setPopOutWindowInfo(context, "privacy_health_data_");
        }
    }

    private static void aPo_(final Context context, final boolean[] zArr, View view, final boolean z, final boolean z2) {
        CustomAlertDialog c = new CustomAlertDialog.Builder(context).cyp_(view).cyn_(R$string.IDS_common_disagree, new DialogInterface.OnClickListener() { // from class: gmr
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                gmn.aPg_(zArr, z, z2, context, dialogInterface, i);
            }
        }).cyo_(R$string.IDS_hw_show_agree, new DialogInterface.OnClickListener() { // from class: gmo
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                gmn.aPh_(zArr, z, z2, context, dialogInterface, i);
            }
        }).c();
        c.setCancelable(false);
        if (!(context instanceof Activity) || ((Activity) context).isFinishing()) {
            return;
        }
        c.show();
    }

    static /* synthetic */ void aPg_(boolean[] zArr, boolean z, boolean z2, Context context, DialogInterface dialogInterface, int i) {
        if (!zArr[0] && z) {
            b(3, false);
        }
        if (!zArr[1] && z2) {
            b(7, false);
        }
        b(301, false);
        e(context, AnalyticsValue.HEALTH_SPORT_SYNC_DATA_SWITCH_2040051.value(), "0");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    static /* synthetic */ void aPh_(boolean[] zArr, boolean z, boolean z2, Context context, DialogInterface dialogInterface, int i) {
        if (!zArr[0] && z) {
            b(3, true);
        }
        if (!zArr[1] && z2) {
            b(7, true);
        }
        b(301, true);
        e(context, AnalyticsValue.HEALTH_SPORT_SYNC_DATA_SWITCH_2040051.value(), "1");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static void c(Context context) {
        if (LoginInit.getInstance(context).isBrowseMode()) {
            LogUtil.a("SercurityDataCheck", "showHealthDataSyncPrivacyDialog LoginInit isBrowseMode");
            return;
        }
        if (!Utils.i()) {
            LogUtil.a("SercurityDataCheck", "showHealthDataSyncPrivacyDialog Utils.ifAllowLogin false");
            return;
        }
        if (context == null) {
            LogUtil.h("SercurityDataCheck", "context == null");
            return;
        }
        if ("true".equals(SharedPreferenceManager.b(context, Integer.toString(10006), "health_data_record_health_agree"))) {
            LogUtil.a("SercurityDataCheck", "showHealthDataSyncPrivacyDialog health_data_record_health_agree is true");
            return;
        }
        if ("true".equals(SharedPreferenceManager.b(context, Integer.toString(10025), "privacy_data_already_show_agree"))) {
            LogUtil.a("SercurityDataCheck", "showHealthDataSyncPrivacyDialog privacy_data_already_show_agree is true");
            return;
        }
        if (e(context)[1]) {
            LogUtil.a("SercurityDataCheck", "showHealthDataSyncPrivacyDialog isStatus is true");
            return;
        }
        d(context);
        if (CommonUtil.bu()) {
            return;
        }
        a(context);
    }

    private static void d(Context context) {
        gnb popOutWindowInfo = e.getPopOutWindowInfo(context, "privacy_health_data_");
        int a2 = popOutWindowInfo.a();
        long c = popOutWindowInfo.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 < 3 && currentTimeMillis - c > Constants.ANALYSIS_EVENT_KEEP_TIME) {
            e.setPopOutWindowInfo(context, "privacy_health_data_");
        } else {
            LogUtil.h("SercurityDataCheck", "setPopOutWindowTimesMgr times is over or timestamp is too short");
        }
    }

    private static void a(final Context context) {
        View inflate = View.inflate(context, R.layout.securyty_notify_custom_view_dialog, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.hw_health_layout_data_sport);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.hw_health_layout_data_health);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.hw_health_layout_data_sleep);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_four);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.hw_health_sync_service_desc);
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1009);
        d(accountInfo, healthTextView2);
        linearLayout.setVisibility(8);
        linearLayout3.setVisibility(8);
        linearLayout2.setVisibility(0);
        c(context, accountInfo, healthTextView);
        CustomAlertDialog c = new CustomAlertDialog.Builder(context).a(context.getString(R$string.IDS_service_area_notice_title)).cyp_(inflate).cyn_(R$string.IDS_common_disagree, new DialogInterface.OnClickListener() { // from class: com.huawei.health.userprofile.privacy.SercurityDataCheck$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                gmn.aPm_(context, dialogInterface, i);
            }
        }).cyo_(R$string.IDS_hw_show_agree, new DialogInterface.OnClickListener() { // from class: com.huawei.health.userprofile.privacy.SercurityDataCheck$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                gmn.aPn_(context, dialogInterface, i);
            }
        }).c();
        c.setCancelable(false);
        c.show();
    }

    public static /* synthetic */ void aPm_(Context context, DialogInterface dialogInterface, int i) {
        SharedPreferenceManager.e(context, Integer.toString(10025), "privacy_data_already_show_agree", "true", new StorageParams());
        b(301, false);
        LogUtil.a("SercurityDataCheck", "showHealthDataSyncPrivacyCustomDialog Disagree");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void aPn_(Context context, DialogInterface dialogInterface, int i) {
        SharedPreferenceManager.e(context, Integer.toString(10025), "privacy_data_already_show_agree", "true", new StorageParams());
        b(7, true);
        b(301, true);
        LogUtil.a("SercurityDataCheck", "showHealthDataSyncPrivacyCustomDialog Agree");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static void e(Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (!Utils.i()) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
                return;
            }
            return;
        }
        if (context == null) {
            LogUtil.h("SercurityDataCheck", "context == null");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
                return;
            }
            return;
        }
        if (e(context)[1]) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
                return;
            }
            return;
        }
        gnb popOutWindowInfo = e.getPopOutWindowInfo(context, "privacy_health_data_");
        int a2 = popOutWindowInfo.a();
        long c = popOutWindowInfo.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 < 3 && currentTimeMillis - c > Constants.ANALYSIS_EVENT_KEEP_TIME) {
            e.setPopOutWindowInfo(context, "privacy_health_data_");
            c(context, iBaseResponseCallback);
        } else if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, null);
        }
    }

    private static void c(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        View inflate = View.inflate(context, R.layout.securyty_notify_custom_view_dialog, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.hw_health_layout_data_sport);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.hw_health_layout_data_health);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.hw_health_layout_data_sleep);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_four);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.hw_health_sync_service_desc);
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1009);
        d(accountInfo, healthTextView2);
        linearLayout.setVisibility(8);
        linearLayout3.setVisibility(8);
        linearLayout2.setVisibility(0);
        c(context, accountInfo, healthTextView);
        CustomAlertDialog c = new CustomAlertDialog.Builder(context).a(context.getString(R$string.IDS_service_area_notice_title)).cyp_(inflate).cyn_(R$string.IDS_common_disagree, new DialogInterface.OnClickListener() { // from class: gmq
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                gmn.aPk_(IBaseResponseCallback.this, dialogInterface, i);
            }
        }).cyo_(R$string.IDS_hw_show_agree, new DialogInterface.OnClickListener() { // from class: gms
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                gmn.aPl_(IBaseResponseCallback.this, dialogInterface, i);
            }
        }).c();
        c.setCancelable(false);
        c.show();
    }

    static /* synthetic */ void aPk_(IBaseResponseCallback iBaseResponseCallback, DialogInterface dialogInterface, int i) {
        b(301, false);
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, null);
        }
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    static /* synthetic */ void aPl_(IBaseResponseCallback iBaseResponseCallback, DialogInterface dialogInterface, int i) {
        b(7, true);
        b(301, true);
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, null);
        }
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private static boolean[] e(Context context) {
        String b = b(3);
        String b2 = b(7);
        LogUtil.a("SercurityDataCheck", "readPersonalPrivacySettingValue 01 is", b, " 03 status", b2);
        return new boolean[]{"true".equals(b), "true".equals(b2)};
    }

    private static void e(Context context, String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("agree", str2);
        }
        ixx.d().d(context, str, hashMap, 0);
    }

    private static String b(int i) {
        gmz d = gmz.d();
        if (i == 6) {
            return d.c(7);
        }
        return d.c(i);
    }

    private static void b(int i, boolean z) {
        LogUtil.a("SercurityDataCheck", "setPersonalPrivacySettingValue... privacyId = ", Integer.valueOf(i), ", isOpen = ", Boolean.valueOf(z));
        gmz.d().c(i, z, String.valueOf(i), new IBaseResponseCallback() { // from class: gmn.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0) {
                    LogUtil.a("SercurityDataCheck", "onResponse setUserPrivacy success");
                } else {
                    LogUtil.b("SercurityDataCheck", "onResponse setUserPrivacy failure");
                }
            }
        });
    }

    private static void d(String str, HealthTextView healthTextView) {
        if (String.valueOf(7).equals(str)) {
            healthTextView.setVisibility(0);
        }
    }

    private static void c(Context context, String str, HealthTextView healthTextView) {
        if (String.valueOf(7).equals(str)) {
            healthTextView.setText(context.getString(R$string.IDS_hwh_privacy_dialog_health_privacy_desc));
        } else if (!Utils.o()) {
            healthTextView.setText(context.getString(R$string.IDS_hwh_user_health_agreement_china));
        } else {
            healthTextView.setText(context.getString(R$string.IDS_hwh_user_health_agreement_oversea_blood_oxygen));
        }
    }

    private static void d(Context context, String str, HealthTextView healthTextView) {
        if (String.valueOf(7).equals(str)) {
            healthTextView.setText(context.getString(R$string.IDS_hwh_privacy_dialog_fitness_privacy_desc));
        } else {
            healthTextView.setText(context.getString(R$string.IDS_hwh_user_fitness_agreement));
        }
    }
}
