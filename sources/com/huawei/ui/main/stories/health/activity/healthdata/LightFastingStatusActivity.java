package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.horizontalscrollview.HealthHorizontalScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.qgd;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes.dex */
public class LightFastingStatusActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private FrameLayout f10078a;
    private ImageView b;
    private FrameLayout c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private FrameLayout g;
    private HealthViewPager h;
    private FrameLayout i;
    private HealthHorizontalScrollView j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_light_fasting_status);
        b();
        e();
    }

    private void b() {
        ((CustomTitleBar) findViewById(R.id.light_fasting_title_bar)).setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131299296_res_0x7f090be0));
        this.j = (HealthHorizontalScrollView) findViewById(R.id.light_fasting_status_scrollview);
        this.c = (FrameLayout) findViewById(R.id.light_fasting_increased_layout);
        this.f10078a = (FrameLayout) findViewById(R.id.light_fasting_decreased_layout);
        this.g = (FrameLayout) findViewById(R.id.light_fasting_maintenance_layout);
        this.i = (FrameLayout) findViewById(R.id.light_fasting_phase_layout);
        this.b = (ImageView) findViewById(R.id.light_fasting_increased_image);
        this.e = (ImageView) findViewById(R.id.light_fasting_decreased_image);
        this.d = (ImageView) findViewById(R.id.light_fasting_maintenance_image);
        this.f = (ImageView) findViewById(R.id.light_fasting_phase_image);
        this.h = (HealthViewPager) findViewById(R.id.light_fasting_status_view_page);
        this.c.setOnClickListener(this);
        this.f10078a.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.i.setOnClickListener(this);
    }

    private void e() {
        int intExtra = getIntent().getIntExtra("status", 0);
        qgd qgdVar = new qgd();
        this.h.setAdapter(qgdVar);
        if (intExtra == 0) {
            c(qgdVar.a());
        } else {
            c(intExtra);
        }
        this.h.addOnPageChangeListener(new HwViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.LightFastingStatusActivity.4
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LightFastingStatusActivity.this.c(i + 1);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.c) {
            c(1);
        } else if (view == this.f10078a) {
            c(2);
        } else if (view == this.g) {
            c(3);
        } else if (view == this.i) {
            c(4);
        } else {
            LogUtil.c("LightFastingStatusActivity", "onClick is error");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i == 1) {
            dBj_(this.c, this.b);
            if (LanguageUtil.bc(this)) {
                this.j.fullScroll(66);
            } else {
                this.j.fullScroll(17);
            }
        } else if (i == 2) {
            dBj_(this.f10078a, this.e);
        } else if (i == 3) {
            dBj_(this.g, this.d);
        } else if (i == 4) {
            dBj_(this.i, this.f);
            if (LanguageUtil.bc(this)) {
                this.j.fullScroll(17);
            } else {
                this.j.fullScroll(66);
            }
        }
        int i2 = i - 1;
        if (this.h.getCurrentItem() != i2) {
            this.h.setCurrentItem(i2);
        }
    }

    private void dBj_(FrameLayout frameLayout, ImageView imageView) {
        this.c.setBackgroundResource(R.drawable._2131430782_res_0x7f0b0d7e);
        this.f10078a.setBackgroundResource(R.drawable._2131430782_res_0x7f0b0d7e);
        this.g.setBackgroundResource(R.drawable._2131430782_res_0x7f0b0d7e);
        this.i.setBackgroundResource(R.drawable._2131430782_res_0x7f0b0d7e);
        this.b.setAlpha(0.3f);
        this.e.setAlpha(0.3f);
        this.d.setAlpha(0.3f);
        this.f.setAlpha(0.3f);
        frameLayout.setBackgroundResource(R.drawable._2131430784_res_0x7f0b0d80);
        imageView.setAlpha(1.0f);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
