package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.api.SyncApi;
import com.huawei.hihealth.util.HealthSyncUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomButtonMenuDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider;
import defpackage.rvn;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class rvh {

    /* renamed from: a, reason: collision with root package name */
    private Activity f16928a;
    private View b;
    private boolean c;
    private Context d;
    private IBaseResponseCallback e;
    private CommonDialog21 i;
    private boolean h = false;
    private boolean g = false;

    public rvh(Context context, Activity activity, IBaseResponseCallback iBaseResponseCallback) {
        this.d = context;
        this.f16928a = activity;
        this.e = iBaseResponseCallback;
        this.c = LoginInit.getInstance(context).isBrowseMode();
    }

    public void b(boolean z) {
        this.h = z;
    }

    public void e(boolean z) {
        this.g = z;
    }

    public void d() {
        CustomButtonMenuDialog e;
        if (this.d == null) {
            LogUtil.h("HealthServiceController", "showStopServiceDialog context is null");
            return;
        }
        CustomButtonMenuDialog.Builder builder = new CustomButtonMenuDialog.Builder(this.d);
        if (this.c || Utils.l()) {
            e = e(builder);
        } else {
            e = d(builder);
        }
        if (e.isShowing()) {
            return;
        }
        e.show();
    }

    private CustomButtonMenuDialog e(CustomButtonMenuDialog.Builder builder) {
        return builder.c(R$string.IDS_deauthorization_dialog_title).a(R$string.IDS_deauthorization_dialog_content).cyw_(R$string.IDS_hw_stop_health_service, new View.OnClickListener() { // from class: rvh.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                rvh.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyw_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: rvh.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HealthServiceController", "showBrowseModeDialog onClick to cancel button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).b();
    }

    private CustomButtonMenuDialog d(CustomButtonMenuDialog.Builder builder) {
        return builder.c(R$string.IDS_deauthorization_dialog_title).a(R$string.IDS_deauthorization_dialog_content).cyw_(R$string.IDS_hw_stop_health_service, new View.OnClickListener() { // from class: rvh.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                rvh.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyw_(this.h ? R$string.IDS_hw_stop_service_delete_data : R$string.IDS_hwh_privacy_delete_cloud_data_change_title, new View.OnClickListener() { // from class: rvh.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                rvh.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyw_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: rvh.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HealthServiceController", "showAccountModeDialog onClick to cancel button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "agr_authorized_versions", "", new StorageParams());
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "agr_if_privacy_authorize", "2", new StorageParams());
        if (Utils.o()) {
            new rvn.d(this.d, this.f16928a).c();
        } else {
            new rvr(this.f16928a).a(this.d);
        }
    }

    public void c() {
        if (e(this.d, R$string.IDS_hw_weight_wifi_cloud_data_outh_dialog_msg)) {
            return;
        }
        LogUtil.a("HealthServiceController", "clearCloudData to enter");
        j();
    }

    public boolean e(final Context context, int i) {
        if (!csf.d()) {
            return false;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.d).b(R$string.IDS_hw_health_show_common_dialog_title).d(i).cyU_(R$string.IDS_hw_weight_wifi_outh_dialog_unbind_buttom, new View.OnClickListener() { // from class: rvh.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                rvh.this.d(10);
                csf.a(context);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: rvh.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                rvh.this.d(11);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
        return true;
    }

    private void j() {
        View inflate = View.inflate(this.d, R.layout.dialog_clear_user_data, null);
        this.b = inflate;
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.hw_health_clear_cloud_data_item_two_lyt);
        LinearLayout linearLayout2 = (LinearLayout) this.b.findViewById(R.id.hw_health_clear_cloud_data_item_three_lyt);
        if (Utils.o()) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(8);
        } else {
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(0);
        }
        dRW_(this.b);
        CustomAlertDialog c = new CustomAlertDialog.Builder(this.d).a(this.d.getString(R$string.IDS_settings_restore_factory_settings_dialog_title)).cyp_(this.b).cyo_(R$string.IDS_device_privacy_clear, new DialogInterface.OnClickListener() { // from class: rvi
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                rvh.this.dRX_(dialogInterface, i);
            }
        }).cyn_(R$string.IDS_settings_button_cancal, new DialogInterface.OnClickListener() { // from class: rvm
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                rvh.this.dRY_(dialogInterface, i);
            }
        }).c();
        c.setCancelable(false);
        c.show();
    }

    /* synthetic */ void dRX_(DialogInterface dialogInterface, int i) {
        IBaseResponseCallback iBaseResponseCallback;
        if (this.g && (iBaseResponseCallback = this.e) != null) {
            iBaseResponseCallback.d(12, null);
        } else {
            e();
        }
        d(AnalyticsValue.HEALTH_MINE_SETTING_PRIVACY_CLEAR_ALL_DATA_2040040.value(), "1");
        this.b = null;
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* synthetic */ void dRY_(DialogInterface dialogInterface, int i) {
        d(13);
        this.b = null;
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        IBaseResponseCallback iBaseResponseCallback;
        if (this.g && (iBaseResponseCallback = this.e) != null) {
            iBaseResponseCallback.d(i, null);
        }
        d(AnalyticsValue.HEALTH_MINE_SETTING_PRIVACY_CLEAR_ALL_DATA_2040040.value(), "2");
    }

    private void dRW_(View view) {
        String string = this.d.getString(R$string.IDS_hw_privacy_item_four);
        SpannableString spannableString = new SpannableString(this.d.getString(R$string.IDS_hw_privacy_verify_content2, AppInfoUtils.b(), string));
        int indexOf = spannableString.toString().indexOf(string);
        if (indexOf == -1) {
            return;
        }
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), indexOf, string.length() + indexOf, 18);
        ((HealthTextView) view.findViewById(R.id.hw_health_clear_cloud_data_content)).setText(spannableString);
    }

    private void d(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.d, str, hashMap, 0);
    }

    private void a() {
        f();
        LogUtil.a("HealthServiceController", "clearAllPersonalData to enter ");
        rvo e = rvo.e(this.d);
        e.b(new AnonymousClass8(e));
    }

    /* renamed from: rvh$8, reason: invalid class name */
    class AnonymousClass8 implements IBaseResponseCallback {
        final /* synthetic */ rvo b;

        AnonymousClass8(rvo rvoVar) {
            this.b = rvoVar;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("HealthServiceController", "clearAllPersonalData onResponse errorCode:", Integer.valueOf(i));
            rvh.this.b();
            if (rvh.this.h) {
                rvh.this.g();
            }
            if (i == 0) {
                if (rvh.this.g && rvh.this.e != null) {
                    rvh.this.e.d(14, null);
                } else {
                    rvh.this.d(this.b, (Utils.o() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) ? false : true);
                }
                try {
                    HealthSyncUtil.a(SyncApi.HEALTH_LIFE, true, new ResponseCallback() { // from class: rvp
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i2, Object obj2) {
                            LogUtil.a("HealthServiceController", "clearAllPersonalData onComplete resultCode:", Integer.valueOf(i2));
                        }
                    });
                } catch (IllegalArgumentException e) {
                    LogUtil.b("HealthServiceController", "clearAllPersonalData onResponse exception:", e.getMessage());
                }
                rvh.this.b(R$string.IDS_music_management_operation_success);
                return;
            }
            rvh.this.b(R$string.IDS_music_management_operation_failed);
        }
    }

    public void d(rvo rvoVar, boolean z) {
        if (rvoVar == null) {
            LogUtil.b("HealthServiceController", "setDefaultUserPrivacy is error");
            return;
        }
        rvoVar.e(2, false);
        rvoVar.e(3, false);
        rvoVar.e(6, false);
        rvoVar.e(7, false);
        rvoVar.e(202, false);
        rvoVar.e(203, false);
        rvoVar.e(10, false);
        rvoVar.e(12, z);
        rvoVar.e(11, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i) {
        if (this.f16928a.isFinishing()) {
            LogUtil.h("HealthServiceController", "showToast activity is null or is not running");
        } else {
            this.f16928a.runOnUiThread(new Runnable() { // from class: rvh.9
                @Override // java.lang.Runnable
                public void run() {
                    nrh.b(rvh.this.d, i);
                }
            });
        }
    }

    private void f() {
        if (this.i == null) {
            new CommonDialog21(this.d, R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(this.d);
            this.i = a2;
            a2.e(this.d.getString(R$string.IDS_sns_waiting));
            this.i.setCancelable(false);
        }
        Activity activity = this.f16928a;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        LogUtil.a("HealthServiceController", "showLoadingDialog mLoadingDialog is show");
        this.i.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        CommonDialog21 commonDialog21;
        Activity activity = this.f16928a;
        if (activity == null || activity.isFinishing() || (commonDialog21 = this.i) == null) {
            return;
        }
        commonDialog21.dismiss();
        this.i = null;
        LogUtil.a("HealthServiceController", "destroyLoadingDialog");
    }

    public void e() {
        Intent intent = new Intent();
        intent.putExtra("requestCode", SleepBaseBarLineChartProvider.MSG_UPDATE_DATA_ORIGINAL_ICON);
        intent.setClassName(this.d, "com.huawei.health.HuaweiLoginActivity");
        this.f16928a.startActivityForResult(intent, 101);
    }

    public void c(int i, int i2) {
        if (i == 101 && i2 == -1) {
            a();
        } else {
            LogUtil.h("HealthServiceController", "onActivityResult to else branch");
        }
    }
}
