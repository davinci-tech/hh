package com.huawei.sim.multisim.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.sim.esim.view.EsimManagerActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ktx;
import defpackage.ncf;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
public class MultiSimOpenGuideActivity extends BaseActivity implements View.OnClickListener {
    private HealthTextView c;
    private HealthButton e;
    private HealthTextView f;
    private ImageView g;
    private HealthTextView h;
    private CustomTitleBar i;
    private RelativeLayout j;
    private Context b = null;

    /* renamed from: a, reason: collision with root package name */
    private String f8716a = "";
    private int d = 1;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("MultiSimOpenGuideActivity", "onCreate");
        this.b = this;
        setContentView(R.layout.activity_multi_sim_open_guide);
        Intent intent = getIntent();
        if (intent != null) {
            this.f8716a = intent.getStringExtra("simImsi");
            this.d = intent.getIntExtra("cardType", 1);
        }
        e();
        c();
    }

    private void e() {
        String string;
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.multi_sim_open_guide_title_bar);
        this.i = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.j = (RelativeLayout) findViewById(R.id.multi_sim_open_guide_image_layout);
        this.g = (ImageView) findViewById(R.id.multi_sim_open_guide_image);
        this.h = (HealthTextView) findViewById(R.id.multi_sim_open_guide_tips);
        this.f = (HealthTextView) findViewById(R.id.multi_sim_open_guide_page_num);
        this.c = (HealthTextView) findViewById(R.id.multi_sim_open_guide_content);
        if (this.d == 1) {
            string = this.b.getString(R.string._2130848076_res_0x7f02294c);
        } else {
            string = this.b.getString(R.string._2130848135_res_0x7f022987);
        }
        if (ncf.b(this.f8716a)) {
            if (this.d == 2) {
                this.c.setText(getResources().getString(R.string._2130848132_res_0x7f022984));
            } else {
                this.c.setText(getResources().getString(R.string._2130848131_res_0x7f022983));
            }
        } else if (ncf.d(this.f8716a)) {
            this.c.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130848133_res_0x7f022985), string));
        } else if (ncf.a(this.f8716a)) {
            this.c.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130848134_res_0x7f022986), string));
        } else {
            this.c.setText(getResources().getString(R.string._2130848021_res_0x7f022915));
        }
        HealthButton healthButton = (HealthButton) findViewById(R.id.multi_sim_open_guide_open);
        this.e = healthButton;
        healthButton.setOnClickListener(this);
        if (nsn.j() - nsn.r(this.b) < nrr.e(this.b, 640.0f)) {
            LogUtil.a("MultiSimOpenGuideActivity", "initView low phone");
            this.g.getLayoutParams().width = nrr.e(this.b, 212.0f);
            this.g.getLayoutParams().height = nrr.e(this.b, 212.0f);
            this.j.getLayoutParams().height = nrr.e(this.b, 248.0f);
        }
    }

    private void c() {
        LogUtil.a("MultiSimOpenGuideActivity", "setViewData()");
        Bitmap csL_ = ncf.csL_(true, this.f8716a, this.d);
        if (csL_ != null) {
            this.g.setImageBitmap(csL_);
        } else {
            LogUtil.h("MultiSimOpenGuideActivity", "open guide image is default");
            this.g.setImageResource(R.drawable._2131430950_res_0x7f0b0e26);
        }
        String format = String.format(Locale.ENGLISH, this.b.getString(R.string._2130840019_res_0x7f0209d3), UnitUtil.e(2.0d, 1, 0), UnitUtil.e(2.0d, 1, 0));
        this.h.setText(getResources().getString(R.string._2130848070_res_0x7f022946));
        this.f.setText(format);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.multi_sim_open_guide_open) {
            LogUtil.a("MultiSimOpenGuideActivity", "onClick open button");
            try {
                Intent intent = new Intent(this.b, (Class<?>) MultiSimAuthActivity.class);
                intent.putExtra("simImsi", this.f8716a);
                intent.putExtra("cardType", this.d);
                this.b.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("MultiSimOpenGuideActivity", "click open button ActivityNotFoundException");
            }
        } else {
            LogUtil.h("MultiSimOpenGuideActivity", "onClick other");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        boolean j = ktx.e().j();
        LogUtil.a("MultiSimOpenGuideActivity", "onResume isOperatorOpenSuccess = ", Boolean.valueOf(j));
        if (j) {
            Intent intent = new Intent(this, (Class<?>) EsimManagerActivity.class);
            intent.setFlags(AppRouterExtras.COLDSTART);
            startActivity(intent);
            finish();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
