package com.huawei.health.device.ui.privacy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.cof;
import defpackage.cun;
import defpackage.cuw;
import defpackage.dcx;
import defpackage.jah;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import java.util.Locale;

/* loaded from: classes3.dex */
public class HonorDeviceShowPrivacyActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private AnimationDrawable f2262a;
    private HealthButton b;
    private String c;
    private Context e;
    private ImageView g;
    private HealthColumnLinearLayout h;
    private String i;
    private HealthButton j;
    private HealthTextView l;
    private String m;
    private ImageView n;
    private String o;
    private boolean d = false;
    private int f = 0;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("HonorDeviceShowPrivacyActivity", "Enter onCreate():");
        overridePendingTransition(R.anim._2130772016_res_0x7f010030, 0);
        super.onCreate(bundle);
        this.e = BaseApplication.getContext();
        d();
        this.c = GRSManager.a(this.e).getCommonCountryCode();
    }

    private void d() {
        setContentView(R.layout.activity_honor_device_pairing_guide_black);
        this.m = this.e.getResources().getString(R.string._2130844142_res_0x7f0219ee);
        this.i = this.e.getResources().getString(R.string._2130844144_res_0x7f0219f0);
        this.l = (HealthTextView) nsy.cMc_(this, R.id.honor_device_privacy_content_01);
        this.j = (HealthButton) nsy.cMc_(this, R.id.honor_devicepermission_ok_bt);
        this.b = (HealthButton) nsy.cMc_(this, R.id.honor_devicepermission_ok_cancel);
        this.h = (HealthColumnLinearLayout) nsy.cMc_(this, R.id.honor_ll_device);
        this.n = (ImageView) nsy.cMc_(this, R.id.honor_device_pair_guide_progress_anim);
        this.g = (ImageView) nsy.cMc_(this, R.id.honor_pair_result_device_img);
        Intent intent = getIntent();
        if (intent != null) {
            this.f = intent.getIntExtra("honor_device_type_key", 0);
            this.d = intent.getBooleanExtra("is_support_special_midware", false);
            this.o = intent.getStringExtra("image_path");
        }
        LogUtil.a("HonorDeviceShowPrivacyActivity", "mDeviceType:", Integer.valueOf(this.f));
        if (this.n.getDrawable() instanceof AnimationDrawable) {
            this.f2262a = (AnimationDrawable) this.n.getDrawable();
        }
        this.f2262a.start();
        int i = this.f;
        if (i != 0) {
            JA_(i, this.g);
        } else {
            LogUtil.a("HonorDeviceShowPrivacyActivity", "imagePath not null , path is ", Integer.valueOf(i));
            if (!TextUtils.isEmpty(this.o)) {
                LogUtil.a("HonorDeviceShowPrivacyActivity", "imagePath not null , path is ", Integer.valueOf(this.f));
                this.g.setImageBitmap(dcx.TK_(this.o));
            }
        }
        this.h.setVisibility(0);
        this.n.setVisibility(0);
        this.j.setOnClickListener(this);
        this.b.setOnClickListener(this);
        b();
    }

    private void JA_(int i, ImageView imageView) {
        if (imageView == null) {
            LogUtil.a("HonorDeviceShowPrivacyActivity", "pairResultDeviceImage is null");
            return;
        }
        if (i == 18) {
            imageView.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
            return;
        }
        if (i == 35) {
            imageView.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
            return;
        }
        if (i == 60) {
            imageView.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
            return;
        }
        if (i == 64) {
            imageView.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
        } else if (i == 65) {
            imageView.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
        } else {
            Jz_(i, imageView);
        }
    }

    private void Jz_(int i, ImageView imageView) {
        cuw deviceInfoUx = cun.c().getDeviceInfoUx(i, "HonorDeviceShowPrivacyActivity");
        if (deviceInfoUx == null) {
            LogUtil.h("HonorDeviceShowPrivacyActivity", "DeviceInfoUx is null.");
            return;
        }
        int i2 = deviceInfoUx.i();
        if (i2 != -1) {
            if (i2 == 1) {
                imageView.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
                return;
            }
            if (i2 == 2) {
                imageView.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
            } else if (i2 == 3) {
                imageView.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
            } else {
                LogUtil.h("HonorDeviceShowPrivacyActivity", "setImageResourceByType default.");
            }
        }
    }

    private void b() {
        String string = this.e.getResources().getString(R.string._2130844145_res_0x7f0219f1, this.i, this.m);
        if (nsn.ae(BaseApplication.getContext())) {
            string = this.e.getResources().getString(R.string._2130844400_res_0x7f021af0, this.i, this.m);
        }
        int[] iArr = new int[2];
        if (string.lastIndexOf(this.i) != -1) {
            iArr[0] = string.lastIndexOf(this.i);
        }
        if (string.lastIndexOf(this.m) != -1) {
            iArr[1] = string.lastIndexOf(this.m);
        }
        SpannableString spannableString = new SpannableString(string);
        Jy_(spannableString, iArr[0], this.i.length(), 2);
        Jy_(spannableString, iArr[1], this.m.length(), 1);
        this.l.setText(spannableString);
        this.l.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void Jy_(SpannableString spannableString, int i, int i2, final int i3) {
        nsi.cKG_(spannableString, i, i2 + i, new ClickableSpan() { // from class: com.huawei.health.device.ui.privacy.HonorDeviceShowPrivacyActivity.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                LogUtil.a("HonorDeviceShowPrivacyActivity", "jump to HonorDevicePrivacyWebViewActivity");
                Intent intent = new Intent(HonorDeviceShowPrivacyActivity.this.e, (Class<?>) WebViewActivity.class);
                intent.putExtra("WebViewActivity.REQUEST_URL_KEY", HonorDeviceShowPrivacyActivity.this.e(i3));
                intent.putExtra(Constants.JUMP_MODE_KEY, 10);
                if (i3 == 1) {
                    intent.putExtra("WebViewActivity.TITLE", HonorDeviceShowPrivacyActivity.this.m);
                } else {
                    intent.putExtra("WebViewActivity.TITLE", HonorDeviceShowPrivacyActivity.this.i);
                }
                HonorDeviceShowPrivacyActivity.this.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(HonorDeviceShowPrivacyActivity.this.e.getResources().getColor(R$color.common_colorAccent));
                textPaint.setUnderlineText(false);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.honor_devicepermission_ok_bt) {
            LogUtil.a("HonorDeviceShowPrivacyActivity", "click ok");
            cof.d(true);
            a(1);
        } else if (id == R.id.honor_devicepermission_ok_cancel) {
            a(0);
            LogUtil.a("HonorDeviceShowPrivacyActivity", "click cancel");
        } else {
            LogUtil.h("HonorDeviceShowPrivacyActivity", "click nothing");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(int i) {
        LogUtil.a("HonorDeviceShowPrivacyActivity", "setResult data:", Integer.valueOf(i));
        this.h.setVisibility(4);
        this.n.setVisibility(4);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("honor_show_privacy_key", i);
        intent.putExtras(bundle);
        intent.putExtra("is_support_special_midware", this.d);
        setResult(3, intent);
        finish();
        overridePendingTransition(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(int i) {
        StringBuilder sb;
        String language = Locale.getDefault().getLanguage();
        String script = Locale.getDefault().getScript();
        if (TextUtils.isEmpty(this.c)) {
            String b = SharedPreferenceManager.b(this.e, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
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
        LogUtil.h("HonorDeviceShowPrivacyActivity", "getUrl error type");
        return "";
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
