package com.huawei.ui.main.stories.me.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.agreement.AgrHttp;
import com.huawei.hwcloudmodel.https.HttpResCallBack;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.main.stories.me.activity.ServiceModelSettingActivity;
import defpackage.lbv;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.rvj;
import defpackage.rvq;
import defpackage.sds;
import health.compact.a.CommonLibUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class ServiceModelSettingActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f10347a;
    private LinearLayout b;
    private RadioButton c;
    private Context d;
    private RadioButton e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_service_model_setting);
        this.d = this;
        g();
    }

    private void g() {
        this.b = (LinearLayout) findViewById(R.id.full_service_layout);
        this.f10347a = (LinearLayout) findViewById(R.id.basic_service_layout);
        this.b.setOnClickListener(this);
        this.f10347a.setOnClickListener(this);
        this.e = (RadioButton) findViewById(R.id.full_service_radio_button);
        this.c = (RadioButton) findViewById(R.id.basic_service_radio_button);
        this.e.setClickable(false);
        this.c.setClickable(false);
        c(SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (isDestroyed() || isFinishing()) {
            LogUtil.h("ServiceModelSettingActivity", "onClick activity isDestroyed or isFinishing is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (nsn.a(750)) {
                LogUtil.a("ServiceModelSettingActivity", "onClick is too fast");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (view == this.b) {
                c();
            } else if (view == this.f10347a) {
                b();
            } else {
                LogUtil.h("ServiceModelSettingActivity", "onClick click to other view");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void c() {
        if (this.e.isChecked()) {
            return;
        }
        c(false);
        i();
    }

    private void b() {
        if (this.c.isChecked()) {
            return;
        }
        c(true);
        j();
    }

    private void i() {
        LogUtil.a("ServiceModelSettingActivity", "showFullServiceDialog in");
        String string = this.d.getString(R$string.IDS_hw_health_full_service);
        CustomTextAlertDialog.Builder c = new CustomTextAlertDialog.Builder(this.d).b(string).e(this.d.getString(R$string.IDS_hw_health_full_service_label)).cyU_(R$string.IDS_user_permission_ok, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.ServiceModelSettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ServiceModelSettingActivity.this.a(false);
                CommonLibUtil.d(ServiceModelSettingActivity.this.d);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.ServiceModelSettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ServiceModelSettingActivity", "showFullServiceDialog onClick to cancel");
                ServiceModelSettingActivity.this.c(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).c(true);
        CustomTextAlertDialog a2 = c.a();
        c.e().setBackground(this.d.getDrawable(R$drawable.button_background_emphasize));
        c.e().setTextColor(this.d.getColor(R$color.common_color_white));
        a2.setCancelable(false);
        a2.show();
    }

    private void j() {
        LogUtil.a("ServiceModelSettingActivity", "showBasicServiceDialog in");
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.d).b(this.d.getString(com.huawei.ui.main.R$string.IDS_hw_health_base_service)).cyQ_(dNZ_()).cyU_(R$string.IDS_device_release_user_profile_agree_and_continue, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.ServiceModelSettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ServiceModelSettingActivity.this.a(true);
                ServiceModelSettingActivity.this.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.ServiceModelSettingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ServiceModelSettingActivity", "showBasicServiceDialog onClick to cancel");
                ServiceModelSettingActivity.this.c(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = cyR_.a();
        cyR_.e().setBackground(this.d.getDrawable(R.drawable.button_background_emphasize));
        cyR_.e().setTextColor(this.d.getColor(R.color._2131299386_res_0x7f090c3a));
        a2.setCancelable(false);
        a2.show();
    }

    private SpannableString dNZ_() {
        String string = this.d.getResources().getString(com.huawei.ui.main.R$string.IDS_sentences_network);
        String string2 = this.d.getResources().getString(com.huawei.ui.main.R$string.IDS_hw_show_setting_about_service_item);
        String string3 = this.d.getResources().getString(com.huawei.ui.main.R$string.IDS_hwh_about_privacy_notice);
        String string4 = this.d.getString(com.huawei.ui.main.R$string.IDS_hw_health_base_service_content2, string, string2, string3);
        SpannableString spannableString = new SpannableString(string4);
        sds.dWm_(spannableString, string);
        int indexOf = string4.indexOf(string2);
        int length = string2.length() + indexOf;
        spannableString.setSpan(new rvq(this.d, "HealthUserAgreement"), indexOf, length, 33);
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), indexOf, length, 33);
        int indexOf2 = string4.indexOf(string3);
        int length2 = string3.length() + indexOf2;
        spannableString.setSpan(new rvq(this.d, "HealthPrivacy"), indexOf2, length2, 33);
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), indexOf2, length2, 33);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        f();
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", z);
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_agree_full_service_model", !z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        this.e.setChecked(!z);
        this.c.setChecked(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        final LoginInit loginInit = LoginInit.getInstance(this.d);
        final rvj rvjVar = new rvj(this);
        if (!loginInit.getIsLogined() || Utils.l()) {
            LogUtil.a("ServiceModelSettingActivity", "stopHealth isOverseaNoCloudVersion = true or logout");
            SharedPreferenceManager.e(this.d, Integer.toString(10005), "hw_health_terms_authorize_china", "", (StorageParams) null);
            rvjVar.b(this.d);
            a(this.d);
            h();
            return;
        }
        if (!CommonUtil.aa(this.d)) {
            nrh.b(this.d, com.huawei.ui.main.R$string.IDS_device_hygride_current_network_unavailable);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rgt
                @Override // java.lang.Runnable
                public final void run() {
                    ServiceModelSettingActivity.this.b(rvjVar, loginInit);
                }
            });
        }
    }

    public /* synthetic */ void b(rvj rvjVar, LoginInit loginInit) {
        e(this.d, rvjVar, loginInit);
    }

    private void e(final Context context, final rvj rvjVar, LoginInit loginInit) {
        String url = GRSManager.a(context).getUrl("agreementservice");
        String accountInfo = loginInit.getAccountInfo(1015);
        if (TextUtils.isEmpty(accountInfo) || TextUtils.isEmpty(url) || context == null) {
            LogUtil.h("ServiceModelSettingActivity", "cancelSignAgreement token or url is empty, context is null");
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(118);
        arrayList.add(10009);
        String accountInfo2 = loginInit.getAccountInfo(1010);
        String country = context.getResources().getConfiguration().locale.getCountry();
        String language = context.getResources().getConfiguration().locale.getLanguage();
        new AgrHttp().signHttpReq(accountInfo, url, false, arrayList, accountInfo2, language + "_" + country, new HttpResCallBack() { // from class: com.huawei.ui.main.stories.me.activity.ServiceModelSettingActivity.5
            @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
            public void onFinished(int i, String str) {
                if (i == 200 && sds.c(str)) {
                    LogUtil.a("ServiceModelSettingActivity", "cancelSignAgreement_result ", str);
                    rvjVar.b(context);
                    ServiceModelSettingActivity.this.a(context);
                    ServiceModelSettingActivity.this.h();
                    return;
                }
                LogUtil.a("ServiceModelSettingActivity", "cancelSignAgreement_result resultCode ", Integer.valueOf(i));
                nrh.b(context, com.huawei.ui.main.R$string.IDS_deauthorization_fail);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        SharedPreferenceManager.e(context, Integer.toString(10000), "agr_last_query_time", "", (StorageParams) null);
        SharedPreferenceManager.e(context, Integer.toString(10000), "if_first_agr_sign", "", (StorageParams) null);
        SharedPreferenceManager.e(context, Integer.toString(10000), "hw_health_show_grant_pwd", Integer.toString(0), (StorageParams) null);
        SharedPreferences.Editor edit = context.getSharedPreferences("IDEQ_IndoorEquipConnectedActivity" + CommonUtil.e(context), 0).edit();
        edit.putBoolean("IDEQ_IndoorEquipConnectedActivityprivacyDialogConfirm" + CommonUtil.e(context), false);
        edit.apply();
        lbv.a(context, false);
    }

    private void f() {
        Intent intent = new Intent();
        intent.setAction(Action.ACTION_HW_ACCOUNT_LOGOUT);
        intent.putExtra(JsbMapKeyNames.H5_USER_ID, LoginInit.getInstance(this.d).getAccountInfo(1011));
        intent.setPackage(this.d.getPackageName());
        this.d.sendBroadcast(intent, SecurityConstant.b);
    }

    private void d() {
        AppRouter.b("/home/main").e(com.huawei.operation.utils.Constants.HOME_TAB_NAME, com.huawei.operation.utils.Constants.HOME).c(this.d);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
