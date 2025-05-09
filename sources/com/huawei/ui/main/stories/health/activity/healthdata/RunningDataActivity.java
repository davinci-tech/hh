package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.UserInformationCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.RunningDataActivity;
import com.huawei.ui.main.stories.health.fragment.RunningDayDetailFragment;
import com.huawei.ui.main.stories.health.fragment.RunningMonthDetailFragment;
import com.huawei.ui.main.stories.health.fragment.RunningWeekDetailFragment;
import com.huawei.ui.main.stories.health.fragment.RunningYearDetailFragment;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import com.huawei.ui.main.stories.template.BaseActivity;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import defpackage.glz;
import defpackage.ixx;
import defpackage.nqx;
import defpackage.nsy;
import defpackage.ruf;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class RunningDataActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private RunningStateIndexData f10080a;
    private RunningDayDetailFragment b;
    private UserInfomation c;
    private RelativeLayout d;
    private Context e;
    private HealthTextView f;
    private HealthSubTabWidget g;
    private CustomTitleBar h;
    private String i;
    private nqx j;
    private int m = 0;
    private HealthViewPager n;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_running_data_layout);
        this.e = this;
        c();
        a();
    }

    private void a() {
        this.d = (RelativeLayout) findViewById(R.id.hw_health_running_tips);
        this.f = (HealthTextView) findViewById(R.id.running_set_gender_text);
        Intent intent = getIntent();
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("running_level_data");
            if (parcelableExtra instanceof RunningStateIndexData) {
                this.f10080a = (RunningStateIndexData) parcelableExtra;
            }
            if (intent.hasExtra("KEY_RUNNING_FLAG")) {
                this.i = intent.getStringExtra("KEY_RUNNING_FLAG");
            }
            this.m = intent.getIntExtra("vo2max_value", 0);
        }
        d();
    }

    private void d() {
        this.g = (HealthSubTabWidget) findViewById(R.id.running_subTabLayout);
        HealthViewPager healthViewPager = (HealthViewPager) nsy.cMc_(this, R.id.running_detail_viewpager);
        this.n = healthViewPager;
        if (healthViewPager == null) {
            LogUtil.h("Track_RunningDataActivity", "initViewPager mViewPager is null");
            return;
        }
        healthViewPager.setScrollHeightArea(200);
        this.n.setOffscreenPageLimit(3);
        this.j = new nqx(this, this.n, this.g);
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        this.c = userInfo;
        if (userInfo == null) {
            LogUtil.h("Track_RunningDataActivity", "initViewPager mLocalUserInfo is null");
            this.c = new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        int gender = this.c.getGender();
        Bundle bundle = new Bundle();
        bundle.putInt("vo2max_age", this.c.getAgeOrDefaultValue());
        bundle.putInt("vo2max_gender", gender);
        bundle.putParcelable("running_level_data", this.f10080a);
        bundle.putInt("vo2max_value", this.m);
        RunningDayDetailFragment runningDayDetailFragment = new RunningDayDetailFragment();
        this.b = runningDayDetailFragment;
        runningDayDetailFragment.setArguments(bundle);
        RunningWeekDetailFragment runningWeekDetailFragment = new RunningWeekDetailFragment();
        runningWeekDetailFragment.setArguments(bundle);
        RunningMonthDetailFragment runningMonthDetailFragment = new RunningMonthDetailFragment();
        runningMonthDetailFragment.setArguments(bundle);
        RunningYearDetailFragment runningYearDetailFragment = new RunningYearDetailFragment();
        runningYearDetailFragment.setArguments(bundle);
        this.j.c(this.g.c(this.e.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_day)), this.b, true);
        this.j.c(this.g.c(this.e.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_week)), runningWeekDetailFragment, false);
        this.j.c(this.g.c(this.e.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_month)), runningMonthDetailFragment, false);
        this.j.c(this.g.c(this.e.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_year)), runningYearDetailFragment, false);
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.running_detail_titlebar);
        this.h = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_data_running_index_title_outside));
        this.h.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.RunningDataActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("RQ_RUNNING".equals(RunningDataActivity.this.i)) {
                    RunningDataActivity.this.e();
                }
                RunningDataActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ruf.d(this, this.h, "RUNNING_STATE_INDEX");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Intent intent = new Intent();
        intent.putExtra("KEY_RUNNING_GENDER", this.c.getGender());
        intent.putExtra("KEY_RUNNING_AGE", this.c.getAge());
        setResult(-1, intent);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if ("RQ_RUNNING".equals(this.i)) {
            e();
        }
        super.onBackPressed();
    }

    private void f() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.RunningDataActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("show_gender_window", true);
                AppRouter.b("/HWUserProfileMgr/UserInfoActivity").zF_(bundle).c(RunningDataActivity.this.e);
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                ixx.d().d(RunningDataActivity.this.e, AnalyticsValue.HEALTH_JUMP_USERINFO_FROM_VO2MAX_2040082.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j();
    }

    private void j() {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).sync(new CommonCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.RunningDataActivity.3
            @Override // com.huawei.up.callback.CommonCallback
            public void onSuccess(Bundle bundle) {
                LogUtil.a("Track_RunningDataActivity", "refreshUserInfoAfterSync onSuccess");
                RunningDataActivity.this.b();
            }

            @Override // com.huawei.up.callback.CommonCallback
            public void onFail(int i) {
                LogUtil.a("Track_RunningDataActivity", "refreshUserInfoAfterSync onFail");
                if (-1 == i) {
                    RunningDataActivity.this.b();
                }
            }
        });
        glz.b("RunningDataActivity");
    }

    /* renamed from: com.huawei.ui.main.stories.health.activity.healthdata.RunningDataActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends UserInformationCallback<RunningDataActivity> {
        AnonymousClass5(RunningDataActivity runningDataActivity) {
            super(runningDataActivity);
        }

        @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, final UserInfomation userInfomation) {
            if (userInfomation == null) {
                LogUtil.h("Track_RunningDataActivity", "refreshUserInfo getUserInfo userInfomation is null");
                return;
            }
            if (this.mReference == null) {
                LogUtil.h("Track_RunningDataActivity", "refreshUserInfo getUserInfo mReference is null");
                return;
            }
            final RunningDataActivity runningDataActivity = (RunningDataActivity) this.mReference.get();
            if (runningDataActivity != null) {
                runningDataActivity.runOnUiThread(new Runnable() { // from class: qeq
                    @Override // java.lang.Runnable
                    public final void run() {
                        RunningDataActivity.this.b(userInfomation);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new AnonymousClass5(this));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(UserInfomation userInfomation) {
        int gender = userInfomation.getGender();
        if (gender != 0 && gender != 1) {
            this.d.setVisibility(0);
            f();
        } else {
            this.d.setVisibility(8);
        }
        if (this.c.getGender() == userInfomation.getGender() && this.c.getAge() == userInfomation.getAge()) {
            LogUtil.h("Track_RunningDataActivity", "refreshGenderTips gender and age is not change");
        } else {
            this.c = userInfomation;
            this.b.updateGenderView(gender);
        }
    }
}
