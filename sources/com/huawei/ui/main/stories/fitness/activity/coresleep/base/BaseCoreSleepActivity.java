package com.huawei.ui.main.stories.fitness.activity.coresleep.base;

import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class BaseCoreSleepActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    public HealthTextView f9836a;
    public HealthTextView b;
    public HealthTextView c;
    public HealthTextView d;
    public HealthTextView e;
    public HealthTextView f;
    public HealthTextView g;
    public HealthTextView h;
    public HealthTextView i;
    public HealthTextView j;
    public HealthTextView k;
    public HealthTextView l;
    public HealthTextView m;
    public HealthTextView n;
    public HealthTextView o;
    private HealthTextView p;
    private HealthTextView r;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        d();
    }

    private void d() {
        this.h = (HealthTextView) findViewById(R.id.standard_reference);
        this.l = (HealthTextView) findViewById(R.id.user_score);
        this.j = (HealthTextView) findViewById(R.id.sleep_status);
        this.e = (HealthTextView) findViewById(R.id.sleep_explain_title_1);
        this.b = (HealthTextView) findViewById(R.id.sleep_explain_content_1);
        this.f9836a = (HealthTextView) findViewById(R.id.sleep_explain_title_2);
        this.d = (HealthTextView) findViewById(R.id.sleep_explain_content_2);
        this.n = (HealthTextView) findViewById(R.id.text_view_8);
        this.k = (HealthTextView) findViewById(R.id.text_view_9);
        this.f = (HealthTextView) findViewById(R.id.text_view_10);
        this.g = (HealthTextView) findViewById(R.id.text_view_11);
        this.o = (HealthTextView) findViewById(R.id.text_view_12);
        this.i = (HealthTextView) findViewById(R.id.sleep_reference_title);
        this.c = (HealthTextView) findViewById(R.id.sleep_reference);
        this.m = (HealthTextView) findViewById(R.id.text_view_14);
        this.r = (HealthTextView) findViewById(R.id.text_suggest_references);
        this.p = (HealthTextView) findViewById(R.id.text_days_sleep_references);
        if (LanguageUtil.bp(this)) {
            a(this.r);
            a(this.p);
        }
        if (LanguageUtil.h(BaseApplication.e())) {
            return;
        }
        nsy.e(this.e, false);
        nsy.e(this.b, false);
        nsy.e(this.f9836a, false);
        nsy.e(this.d, false);
    }

    public static void a(HealthTextView healthTextView) {
        if (healthTextView == null) {
            LogUtil.h("BaseCoreSleepActivity", "HealthTextView is null");
        } else {
            healthTextView.setAutoTextInfo(14, 1, 2);
        }
    }
}
