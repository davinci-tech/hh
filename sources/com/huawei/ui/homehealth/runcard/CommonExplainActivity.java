package com.huawei.ui.homehealth.runcard;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.koq;
import defpackage.qrp;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class CommonExplainActivity extends BaseActivity {
    private LinearLayout c;
    private CustomTitleBar d;
    private String b = "";
    private String e = "";

    /* renamed from: a, reason: collision with root package name */
    private List<String> f9500a = new ArrayList();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelMarginAdaptation();
        setContentView(R.layout.activity_common_explain);
        i();
        b();
        c();
    }

    private void i() {
        this.d = (CustomTitleBar) findViewById(R.id.explain_title_bar);
        this.c = (LinearLayout) findViewById(R.id.explain_layout);
    }

    private void b() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.b = intent.getStringExtra("bar_title");
                this.e = intent.getStringExtra("extra_title");
                this.f9500a = intent.getStringArrayListExtra("extra_content");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("Suggestion_CommonExplainActivity", "initData mContentList index out of bounds.");
            }
        }
    }

    private void c() {
        d();
        e();
        a();
    }

    private void d() {
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        this.d.setTitleText(this.b);
    }

    private void e() {
        if (TextUtils.isEmpty(this.e)) {
            return;
        }
        HealthTextView healthTextView = new HealthTextView(this);
        healthTextView.setText(this.e);
        healthTextView.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        healthTextView.setTextSize(0, getResources().getDimension(R.dimen._2131365062_res_0x7f0a0cc6));
        healthTextView.setTypeface(Typeface.DEFAULT_BOLD);
        healthTextView.setGravity(GravityCompat.START);
        this.c.addView(healthTextView);
    }

    private void a() {
        if (koq.b(this.f9500a)) {
            return;
        }
        int i = 0;
        for (String str : this.f9500a) {
            if (str != null) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                if (i != 0 || !TextUtils.isEmpty(this.e)) {
                    layoutParams.topMargin = qrp.a(getApplicationContext(), getResources().getDimension(R.dimen._2131362566_res_0x7f0a0306));
                }
                HealthTextView healthTextView = new HealthTextView(this);
                healthTextView.setText(str);
                healthTextView.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                healthTextView.setTextSize(0, getResources().getDimension(R.dimen._2131365062_res_0x7f0a0cc6));
                healthTextView.setGravity(GravityCompat.START);
                this.c.addView(healthTextView, layoutParams);
                i++;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
