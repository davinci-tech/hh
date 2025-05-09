package com.huawei.health.splash;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.byt;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class GuideActivity extends BaseActivity implements HealthViewPager.OnPageChangeListener, View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private GuidePointView f2986a;
    private int b;
    private LinearLayout c;
    private int d;
    private GestureDetector e;
    private List<View> i;
    private HealthButton j;

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(AppRouterExtras.COLDSTART);
        getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        this.e = new GestureDetector(new b());
        this.b = nsn.c(this, 20.0f);
        setContentView(R.layout.hw_health_guide_layout);
        b();
        e();
    }

    private void b() {
        LayoutInflater from = LayoutInflater.from(this);
        View inflate = from.inflate(R.layout.hw_health_guide_third_layout, (ViewGroup) null);
        this.c = (LinearLayout) findViewById(R.id.hw_health_guide_dot_layout);
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.hw_health_guide_done_button);
        this.j = healthButton;
        healthButton.setOnClickListener(this);
        this.i = new ArrayList();
        View inflate2 = from.inflate(R.layout.hw_health_guide_first_layout, (ViewGroup) null);
        View inflate3 = from.inflate(R.layout.hw_health_guide_second_layout, (ViewGroup) null);
        this.i.add(inflate2);
        this.i.add(inflate3);
        this.i.add(inflate);
        byt bytVar = new byt(this.i);
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.hw_health_guide_viewpager);
        healthViewPager.setAdapter(bytVar);
        healthViewPager.setOnPageChangeListener(this);
    }

    private void e() {
        this.d = 0;
        GuidePointView guidePointView = new GuidePointView(this, this.i.size(), this.d);
        this.f2986a = guidePointView;
        this.c.addView(guidePointView);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (i < 0 || i > this.i.size() - 1 || this.d == i) {
            return;
        }
        this.d = i;
        this.f2986a.setSelected(i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.j) {
            setResult(103);
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            setResult(102);
            finish();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.e.onTouchEvent(motionEvent)) {
            motionEvent.setAction(3);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    class b extends GestureDetector.SimpleOnGestureListener {
        private b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (GuideActivity.this.d != 2 || Math.abs(motionEvent.getX() - motionEvent2.getX()) <= Math.abs(motionEvent.getY() - motionEvent2.getY())) {
                return false;
            }
            if ((motionEvent.getX() - motionEvent2.getX() > (-GuideActivity.this.b) && motionEvent.getX() - motionEvent2.getX() < GuideActivity.this.b) || motionEvent.getX() - motionEvent2.getX() < GuideActivity.this.b) {
                return false;
            }
            GuideActivity.this.setResult(103);
            GuideActivity.this.finish();
            return true;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
