package com.huawei.ui.main.stories.me.views.privacy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.views.privacy.AdRecommendActivity;
import defpackage.bzs;
import defpackage.gmz;
import defpackage.nsn;
import defpackage.rvo;
import health.compact.a.GRSManager;

/* loaded from: classes7.dex */
public class AdRecommendActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f10377a;
    private boolean b;
    private HealthTextView c;
    private HealthSwitchButton d;
    private rvo e;
    private HealthSwitchButton f;
    private gmz g;
    private HealthSwitchButton j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ad_recommend);
        this.f10377a = this;
        d();
        this.g = gmz.d();
        this.e = rvo.e(getApplicationContext());
        a(this.g);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.b) {
            e();
            this.b = false;
        }
    }

    private void d() {
        this.j = (HealthSwitchButton) findViewById(R.id.ad_recommend_master_switch);
        this.d = (HealthSwitchButton) findViewById(R.id.ad_recommend_hw_switch);
        this.f = (HealthSwitchButton) findViewById(R.id.ad_recommend_third_switch);
        this.c = (HealthTextView) findViewById(R.id.ad_recommend_hw_des);
        b();
        this.j.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    private void b() {
        String string = getResources().getString(R$string.IDS_hwh_ad_recommend_more);
        SpannableString spannableString = new SpannableString(getResources().getString(R$string.IDS_hwh_ad_huawei_content, string));
        int indexOf = spannableString.toString().indexOf(string);
        if (indexOf <= -1) {
            LogUtil.h("AdRecommendActivity", "setAdHwDes index ", Integer.valueOf(indexOf));
            return;
        }
        spannableString.setSpan(new ClickableSpan() { // from class: com.huawei.ui.main.stories.me.views.privacy.AdRecommendActivity.5
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (!nsn.o()) {
                    AdRecommendActivity.this.a();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(ContextCompat.getColor(AdRecommendActivity.this.f10377a, R.color._2131296927_res_0x7f09029f));
                textPaint.setUnderlineText(false);
            }
        }, indexOf, string.length() + indexOf, 33);
        this.c.setText(spannableString);
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: rid
            @Override // java.lang.Runnable
            public final void run() {
                AdRecommendActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        String str = GRSManager.a(this.f10377a).getUrl("domainContentcenterDbankcdnNew") + "/cch5/PPS/ads/ad-personalized.htm?country=CN&language=zh-cn&version=latest";
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        Intent createWebViewIntent = bzs.e().createWebViewIntent(this.f10377a, bundle, 268435456);
        if (createWebViewIntent != null) {
            this.f10377a.startActivity(createWebViewIntent);
        }
    }

    private void a(gmz gmzVar) {
        String c = gmzVar.c(13);
        String c2 = gmzVar.c(14);
        String c3 = gmzVar.c(15);
        LogUtil.a("AdRecommendActivity", "initPersonalSwitch personalInitValue=", c, " personalHwValue=", c2, " personalThirdValue=", c3);
        boolean z = true;
        this.j.setChecked(TextUtils.isEmpty(c) || "true".equals(c));
        this.d.setChecked(TextUtils.isEmpty(c2) || "true".equals(c2));
        HealthSwitchButton healthSwitchButton = this.f;
        if (!TextUtils.isEmpty(c3) && !"true".equals(c3)) {
            z = false;
        }
        healthSwitchButton.setChecked(z);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        boolean z;
        int id = view.getId();
        if (id == R.id.ad_recommend_master_switch) {
            z = this.j.isChecked();
            this.b = true;
            this.d.setChecked(z);
            this.f.setChecked(z);
        } else if (id == R.id.ad_recommend_hw_switch) {
            z = this.d.isChecked();
            this.b = true;
            if (z && !this.j.isChecked()) {
                this.j.setChecked(z);
            } else if (this.f.isChecked() == z && this.j.isChecked() != z) {
                this.j.setChecked(z);
            }
        } else if (id == R.id.ad_recommend_third_switch) {
            this.b = true;
            z = this.f.isChecked();
            if (z && !this.j.isChecked()) {
                this.j.setChecked(z);
            } else if (this.d.isChecked() == z && this.j.isChecked() != z) {
                this.j.setChecked(z);
            }
        } else {
            LogUtil.h("AdRecommendActivity", "onClick unknown switch button");
            z = false;
        }
        LogUtil.a("AdRecommendActivity", "onClick isChecked = ", Boolean.valueOf(z));
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        LogUtil.a("AdRecommendActivity", "savePersonalPrivacySettings");
        this.e.e(13, this.j.isChecked());
        this.e.e(14, this.d.isChecked());
        this.e.e(15, this.f.isChecked());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
