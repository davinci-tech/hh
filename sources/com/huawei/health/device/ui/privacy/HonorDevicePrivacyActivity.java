package com.huawei.health.device.ui.privacy;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.ui.privacy.HonorDevicePrivacyActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.ceo;
import defpackage.jah;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes3.dex */
public class HonorDevicePrivacyActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2261a = false;
    private Context b;
    private String c;
    private LinearLayout d;
    private CustomTextAlertDialog e;
    private CustomTitleBar f;
    private String g;
    private LinearLayout h;
    private LinearLayout i;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.honor_device_privacy_layout);
        d();
        ArrayList<ContentValues> a2 = ceo.d().a("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4");
        LogUtil.a("DevicePrivacyActivity", "isMultipleDevices bondedDeviceCount ", Integer.valueOf(a2.size()));
        int size = a2.size();
        int a3 = SharedPreferenceManager.a(this.b);
        LogUtil.a("DevicePrivacyActivity", "isMultipleDevices wearDeviceCount ", Integer.valueOf(a3));
        if (a3 + size > 1) {
            this.f2261a = true;
        }
        this.c = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
    }

    private void d() {
        this.b = this;
        this.f = (CustomTitleBar) nsy.cMc_(this, R.id.titlebar);
        this.h = (LinearLayout) nsy.cMc_(this, R.id.rela_revoked);
        this.i = (LinearLayout) nsy.cMc_(this, R.id.ll_license_agreement);
        this.d = (LinearLayout) nsy.cMc_(this, R.id.rela_about);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.d.setOnClickListener(this);
        ImageView imageView = (ImageView) nsy.cMc_(this, R.id.iv_privacy_arrow_about);
        ImageView imageView2 = (ImageView) nsy.cMc_(this, R.id.iv_privacy_arrow_license_agreement);
        ImageView imageView3 = (ImageView) nsy.cMc_(this, R.id.iv_privacy_arrow_revoked);
        if (LanguageUtil.bc(this.b)) {
            Context context = this.b;
            BitmapDrawable cKm_ = nrz.cKm_(context, context.getResources().getDrawable(R$drawable.common_ui_arrow_right));
            imageView.setImageDrawable(cKm_);
            imageView3.setImageDrawable(cKm_);
            imageView2.setImageDrawable(cKm_);
        }
        this.f.setTitleTextColor(getResources().getColor(R.color._2131297370_res_0x7f09045a));
        this.f.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.f.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: cog
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HonorDevicePrivacyActivity.this.Jv_(view);
            }
        });
    }

    public /* synthetic */ void Jv_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rela_revoked) {
            LogUtil.a("DevicePrivacyActivity", "click revoked");
            if (this.f2261a) {
                e();
            } else {
                a();
            }
        } else if (id == R.id.rela_about) {
            LogUtil.a("DevicePrivacyActivity", "click about");
            this.g = this.b.getResources().getString(R.string._2130844142_res_0x7f0219ee);
            if (!nsn.o()) {
                e(1);
            } else {
                LogUtil.h("DevicePrivacyActivity", "click too fast");
            }
        } else if (id == R.id.ll_license_agreement) {
            LogUtil.h("DevicePrivacyActivity", "click License agreement");
            this.g = this.b.getResources().getString(R.string._2130844144_res_0x7f0219f0);
            if (!nsn.o()) {
                e(2);
            } else {
                LogUtil.h("DevicePrivacyActivity", "click too fast");
            }
        } else {
            LogUtil.h("DevicePrivacyActivity", "click other");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(int i) {
        LogUtil.a("DevicePrivacyActivity", "jump to HonorDevicePrivacyWebViewActivity");
        Intent intent = new Intent(this.b, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", b(i));
        intent.putExtra(Constants.JUMP_MODE_KEY, 10);
        intent.putExtra("WebViewActivity.TITLE", this.g);
        try {
            this.b.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("DevicePrivacyActivity", "goBackToLastPage ActivityNotFoundException");
        }
    }

    private void a() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.b).b(R.string._2130844141_res_0x7f0219ed).d(R.string._2130844143_res_0x7f0219ef).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cod
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HonorDevicePrivacyActivity.this.Jw_(view);
            }
        }).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: coh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HonorDevicePrivacyActivity.this.Jx_(view);
            }
        }).a();
        this.e = a2;
        a2.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.e.show();
    }

    public /* synthetic */ void Jw_(View view) {
        LogUtil.a("DevicePrivacyActivity", "showSwitchDeviceDialog click cancel");
        CustomTextAlertDialog customTextAlertDialog = this.e;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
        }
        this.e = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Jx_(View view) {
        LogUtil.a("DevicePrivacyActivity", "showSwitchDeviceDialog click ok.");
        c();
        CustomTextAlertDialog customTextAlertDialog = this.e;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
        }
        this.e = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(this.b.getResources().getString(R.string._2130839506_res_0x7f0207d2)).e(this.b.getResources().getString(R.string._2130844154_res_0x7f0219fa)).cyV_(this.b.getResources().getString(R.string._2130839504_res_0x7f0207d0), new View.OnClickListener() { // from class: coe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HonorDevicePrivacyActivity.Ju_(view);
            }
        });
        builder.a().show();
    }

    public static /* synthetic */ void Ju_(View view) {
        LogUtil.h("DevicePrivacyActivity", "onClick");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        Intent intent = new Intent();
        intent.putExtra("revoke_honor_privacy", true);
        setResult(10005, intent);
        finish();
    }

    private String b(int i) {
        StringBuilder sb;
        String language = Locale.getDefault().getLanguage();
        String script = Locale.getDefault().getScript();
        if (TextUtils.isEmpty(this.c)) {
            String b = SharedPreferenceManager.b(this.b, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
            this.c = b;
            if (TextUtils.isEmpty(b)) {
                this.c = "GB";
            }
        }
        String country = Locale.getDefault().getCountry();
        if (TextUtils.isEmpty(script)) {
            sb = new StringBuilder();
            sb.append(language);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(language);
            sb2.append("_");
            sb2.append(script);
            sb = sb2;
        }
        sb.append("_");
        sb.append(country);
        String sb3 = sb.toString();
        String e = jah.c().e("domain_honor");
        if (i == 1) {
            return e + "/minisite/cloudservice/Health/privacy-statement.htm?country=" + this.c + com.huawei.openalliance.ad.constant.Constants.LANGUAGE + sb3;
        }
        if (i == 2) {
            return e + "/minisite/cloudservice/Health/terms.htm?country=" + this.c + com.huawei.openalliance.ad.constant.Constants.LANGUAGE + sb3;
        }
        LogUtil.h("DevicePrivacyActivity", "getUrl error type");
        return "";
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
