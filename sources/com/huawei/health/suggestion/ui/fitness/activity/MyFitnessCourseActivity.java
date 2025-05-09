package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ggr;
import defpackage.ggs;
import defpackage.ixx;
import defpackage.nqx;
import defpackage.nsf;
import defpackage.smy;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class MyFitnessCourseActivity extends BaseStateActivity {

    /* renamed from: a, reason: collision with root package name */
    private FitnessCourseBehaviorFragment f3097a;
    private CustomTitleBar c;
    private FitnessCourseBehaviorFragment d;
    private FitnessCourseBehaviorFragment e;
    private int i;
    private nqx j;
    private HealthSubTabWidget k;
    private HealthViewPager l;
    private String n;
    private List<FitnessCourseBehaviorFragment> f = new ArrayList(3);
    private boolean h = false;
    private int b = 0;
    private int g = 0;
    private FitnessCourseBehaviorFragment.DeleteModeListener m = new FitnessCourseBehaviorFragment.DeleteModeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity.4
        @Override // com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.DeleteModeListener
        public void deleteItem(int i) {
            LogUtil.a("Suggestion_MyFitnessCourseActivity", "mShowListener deleteItem fragmentType = ", Integer.valueOf(i), " isCustomType(fragmentType) =", Integer.valueOf(MyFitnessCourseActivity.this.d(i)));
            MyFitnessCourseActivity myFitnessCourseActivity = MyFitnessCourseActivity.this;
            myFitnessCourseActivity.a(myFitnessCourseActivity.d(i));
        }
    };

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c(-1);
        getWindow().setFlags(16777216, 16777216);
        clearBackgroundDrawable();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_fitness_activity_course_my);
        this.k = (HealthSubTabWidget) findViewById(R.id.sug_my_fitness_detail_tab);
        this.l = (HealthViewPager) findViewById(R.id.sug_my_fitness_detail_vp);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.sug_my_fitness_course_title);
        this.c = customTitleBar;
        customTitleBar.setRightButtonVisibility(0);
        this.c.setRightButtonClickable(true);
        cancelAdaptRingRegion();
        Intent intent = getIntent();
        this.n = nsf.h(R.string._2130848532_res_0x7f022b14);
        if (intent != null) {
            if (intent.hasExtra("titleName")) {
                String stringExtra = intent.getStringExtra("titleName");
                this.n = stringExtra;
                this.c.setTitleText(stringExtra);
            }
            this.b = intent.getIntExtra("courseCategoryKey", 0);
            int intExtra = intent.getIntExtra("intent_behavior_key", 0);
            this.g = intExtra;
            c(intExtra);
        }
        FitnessCourseBehaviorFragment a2 = FitnessCourseBehaviorFragment.a(1, this.b);
        this.e = a2;
        a2.b(this.m);
        FitnessCourseBehaviorFragment a3 = FitnessCourseBehaviorFragment.a(0, this.b);
        this.d = a3;
        a3.b(this.m);
        int i = this.b;
        FitnessCourseBehaviorFragment a4 = FitnessCourseBehaviorFragment.a(i == 258 ? 3 : 2, i);
        this.f3097a = a4;
        a4.b(this.m);
        this.f.add(this.d);
        this.f.add(this.e);
        if (a()) {
            this.f.add(this.f3097a);
        } else {
            LogUtil.a("Suggestion_MyFitnessCourseActivity", "isOversea purchase");
        }
        h();
        this.l.setOffscreenPageLimit(this.k.getSubTabCount());
        this.c.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyFitnessCourseActivity.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        d();
    }

    private boolean a() {
        return !Utils.o() || this.b == 258;
    }

    private void h() {
        String string;
        this.j = new nqx(this, this.l, this.k);
        smy c = this.k.c(ggs.d(this.b) ? getString(R.string._2130845583_res_0x7f021f8f) : getString(R.string._2130845556_res_0x7f021f74));
        smy c2 = this.k.c(getString(R.string._2130845557_res_0x7f021f75));
        this.j.c(c, this.d, this.g == 0);
        this.j.c(c2, this.e, this.g == 1);
        if (a()) {
            HealthSubTabWidget healthSubTabWidget = this.k;
            if (this.b == 258) {
                string = getString(R.string._2130841788_res_0x7f0210bc);
            } else {
                string = getString(R.string._2130845558_res_0x7f021f76);
            }
            smy c3 = healthSubTabWidget.c(string);
            nqx nqxVar = this.j;
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = this.f3097a;
            int i = this.g;
            nqxVar.c(c3, fitnessCourseBehaviorFragment, i == 3 || i == 2);
            return;
        }
        LogUtil.a("Suggestion_MyFitnessCourseActivity", "setSubTabTitle isOversea mCourseCategory", Integer.valueOf(this.b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        FitnessCourseBehaviorFragment c = c();
        if (c == null) {
            return;
        }
        int d = d(c.c());
        boolean f = c.f();
        if (d == this.i) {
            boolean z = !f;
            this.h = z;
            c(z);
            LogUtil.a("Suggestion_MyFitnessCourseActivity", "setDeleteModeTitle(mSelectedSubTabPosition) mSelectedSubTabPosition = ", Integer.valueOf(this.i));
            a(this.i);
        }
        c(c.c());
    }

    private void d() {
        this.l.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity.2
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                FitnessCourseBehaviorFragment c = MyFitnessCourseActivity.this.c();
                if (c == null) {
                    return;
                }
                int c2 = c.c();
                if (i == 2 && c2 == 2) {
                    LogUtil.a("Suggestion_MyFitnessCourseActivity", "ViewPager Purchase reflash");
                    c.b();
                }
                LogUtil.a("Suggestion_MyFitnessCourseActivity", "mShowListener : setDeleteModeTitle(position) position = ", Integer.valueOf(i));
                MyFitnessCourseActivity.this.a(i);
                MyFitnessCourseActivity.this.c(c2);
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        LogUtil.a("Suggestion_MyFitnessCourseActivity", "initViewController()");
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        LogUtil.a("Suggestion_MyFitnessCourseActivity", "initData()");
    }

    private void c(boolean z) {
        if (c() != null) {
            c().e(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        final FitnessCourseBehaviorFragment c = c();
        if (c == null) {
            return;
        }
        int d = d(c.c());
        boolean f = c.f();
        LogUtil.a("Suggestion_MyFitnessCourseActivity", "setDeleteModeTitle :", "fragmentType = ", Integer.valueOf(i), " currentFragmentType = ", Integer.valueOf(d), " isDeleteModel = ", Boolean.valueOf(f));
        if (i == d) {
            boolean e = e(i, c, c.g());
            LogUtil.a("Suggestion_MyFitnessCourseActivity", "setDeleteModeTitle isRecord = ", Boolean.valueOf(e));
            if (!e) {
                this.c.setLeftButtonDrawable(azH_(), nsf.h(R.string._2130850617_res_0x7f023339));
                this.c.setTitleText(this.n);
                this.c.setRightButtonVisibility(8);
            } else if (f) {
                int e2 = c.e();
                this.c.setTitleText(nsf.a(R.plurals._2130903044_res_0x7f030004, e2, Integer.valueOf(e2)));
                this.c.setLeftButtonDrawable(ContextCompat.getDrawable(BaseApplication.e(), R$drawable.hwappbarpattern_selector_public_cancel), nsf.h(R.string._2130850611_res_0x7f023333));
                this.c.setRightButtonVisibility(8);
            } else if (d == 2 && this.b != 258) {
                this.c.setLeftButtonDrawable(azH_(), nsf.h(R.string._2130850617_res_0x7f023339));
                this.c.setTitleText(this.n);
                this.c.setRightButtonVisibility(8);
            } else {
                this.c.setLeftButtonDrawable(azH_(), nsf.h(R.string._2130850617_res_0x7f023339));
                this.c.setTitleText(this.n);
                this.c.setRightButtonVisibility(0);
                this.c.setRightButtonDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.drawable._2131430279_res_0x7f0b0b87), nsf.h(R.string._2130841397_res_0x7f020f35));
            }
        }
        this.c.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyFitnessCourseActivity.this.b(c, i);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private boolean e(int i, FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment, boolean z) {
        return ggs.d(this.b) ? (i == 0 || i == 1) ? fitnessCourseBehaviorFragment.h() : z : z;
    }

    private Drawable azH_() {
        Context e = BaseApplication.e();
        if (LanguageUtil.bc(e)) {
            return ContextCompat.getDrawable(e, R.drawable._2131428443_res_0x7f0b045b);
        }
        return ContextCompat.getDrawable(e, R.drawable._2131428438_res_0x7f0b0456);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(int i) {
        if (this.b == 258 && i == 3) {
            return 2;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FitnessCourseBehaviorFragment c() {
        int selectedSubTabPostion = this.k.getSelectedSubTabPostion();
        this.i = selectedSubTabPostion;
        Fragment item = this.j.getItem(selectedSubTabPostion);
        if (item instanceof FitnessCourseBehaviorFragment) {
            return (FitnessCourseBehaviorFragment) item;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment, int i) {
        if (fitnessCourseBehaviorFragment == null) {
            return;
        }
        boolean e = e(i, fitnessCourseBehaviorFragment, fitnessCourseBehaviorFragment.g());
        boolean f = fitnessCourseBehaviorFragment.f();
        LogUtil.c("Suggestion_MyFitnessCourseActivity", "dataIsEmpty =", Boolean.valueOf(e), "isDeleteMode = ", Boolean.valueOf(this.h));
        if (f) {
            e();
        } else {
            finish();
        }
    }

    private void e() {
        this.h = false;
        this.c.setLeftButtonDrawable(azH_(), nsf.h(R.string._2130850617_res_0x7f023339));
        this.c.setTitleText(this.n);
        this.c.setRightButtonVisibility(0);
        this.c.setRightButtonDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.drawable._2131430279_res_0x7f0b0b87), nsf.h(R.string._2130841397_res_0x7f020f35));
        c(this.h);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        FitnessCourseBehaviorFragment c = c();
        if (c == null) {
            finish();
        } else if (c.f()) {
            e();
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        String value;
        if (getIntent() == null) {
            LogUtil.b("Suggestion_MyFitnessCourseActivity", "doBiEvent intent is null.");
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        if (i == -1) {
            hashMap.put("type", "2");
            value = AnalyticsValue.EVENT_CLICK_FITNESS_COURSE_MORE.value();
        } else if (this.b == 5) {
            hashMap.put("tabType", Integer.valueOf(i + 1));
            value = AnalyticsValue.INT_PLAN_2030104.value();
        } else {
            hashMap.put("page", Integer.valueOf(i));
            value = AnalyticsValue.HEALTH_FITNESS_MY_COURSE_1130030.value();
            hashMap.put("from", Integer.valueOf(ggr.a(this.b)));
        }
        ixx.d().d(BaseApplication.e(), value, hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
