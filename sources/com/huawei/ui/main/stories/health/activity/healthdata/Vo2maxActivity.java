package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.UserInformationCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.Vo2maxActivity;
import com.huawei.ui.main.stories.health.fragment.Vo2maxDayDetailFragment;
import com.huawei.ui.main.stories.health.fragment.Vo2maxMonthDetailFragment;
import com.huawei.ui.main.stories.health.fragment.Vo2maxWeekDetailFragment;
import com.huawei.ui.main.stories.health.fragment.Vo2maxYearDetailFragment;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import defpackage.glz;
import defpackage.ixx;
import defpackage.nqx;
import defpackage.nsy;
import defpackage.ruf;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class Vo2maxActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f10089a;
    private RelativeLayout b;
    private Vo2maxDayDetailFragment c;
    private HealthTextView f;
    private UserInfomation g;
    private nqx h;
    private Vo2maxMonthDetailFragment i;
    private RunningStateIndexData j;
    private HealthSubTabWidget l;
    private HealthViewPager m;
    private CustomTitleBar n;
    private Vo2maxWeekDetailFragment o;
    private Vo2maxYearDetailFragment r;
    private int k = 0;
    private boolean d = false;
    private boolean e = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_vo2max);
        this.f10089a = this;
        b();
    }

    private void b() {
        getWindow().setBackgroundDrawable(null);
        d();
        e();
    }

    private void e() {
        a();
        HealthViewPager healthViewPager = this.m;
        if (healthViewPager == null) {
            LogUtil.h("Vo2maxActivity", "initViewPager mViewPager is null");
            return;
        }
        healthViewPager.setScrollHeightArea(200);
        this.h = new nqx(this, this.m, this.l);
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        this.g = userInfo;
        if (userInfo == null) {
            LogUtil.h("Vo2maxActivity", "initViewPager mLocalUserInfo is null");
            this.g = new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        int e = CommonUtil.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1005), 0);
        Bundle bundle = new Bundle();
        bundle.putInt("vo2max_age", this.g.getAgeOrDefaultValue());
        bundle.putInt("vo2max_gender", e);
        dBn_(bundle);
        Vo2maxDayDetailFragment vo2maxDayDetailFragment = new Vo2maxDayDetailFragment();
        this.c = vo2maxDayDetailFragment;
        vo2maxDayDetailFragment.setArguments(bundle);
        Vo2maxWeekDetailFragment vo2maxWeekDetailFragment = new Vo2maxWeekDetailFragment();
        this.o = vo2maxWeekDetailFragment;
        vo2maxWeekDetailFragment.setArguments(bundle);
        this.h.c(this.l.c(getString(R$string.IDS_fitness_detail_radio_button_tab_day)), this.c, true);
        this.h.c(this.l.c(getString(R$string.IDS_fitness_detail_radio_button_tab_week)), this.o, false);
        Vo2maxMonthDetailFragment vo2maxMonthDetailFragment = new Vo2maxMonthDetailFragment();
        this.i = vo2maxMonthDetailFragment;
        vo2maxMonthDetailFragment.setArguments(bundle);
        Vo2maxYearDetailFragment vo2maxYearDetailFragment = new Vo2maxYearDetailFragment();
        this.r = vo2maxYearDetailFragment;
        vo2maxYearDetailFragment.setArguments(bundle);
        this.h.c(this.l.c(this.f10089a.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_month)), this.i, false);
        this.h.c(this.l.c(this.f10089a.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_year)), this.r, false);
    }

    private void dBn_(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra("vo2max_value", 0);
            this.k = intExtra;
            if (intExtra != 0) {
                bundle.putInt("vo2max_value", intExtra);
                bundle.putLong("vo2max_time", intent.getLongExtra("vo2max_time", 0L));
            }
            if (intent.getParcelableExtra("running_level_data") instanceof RunningStateIndexData) {
                RunningStateIndexData runningStateIndexData = (RunningStateIndexData) intent.getParcelableExtra("running_level_data");
                this.j = runningStateIndexData;
                bundle.putParcelable("running_level_data", runningStateIndexData);
            }
            this.d = intent.getBooleanExtra("fromDetailFrag", false);
            this.e = intent.getBooleanExtra("from_sport_list_fragment", false);
        }
    }

    private void a() {
        this.l = (HealthSubTabWidget) findViewById(R.id.vo2max_subTabLayout);
        this.b = (RelativeLayout) findViewById(R.id.hw_health_vo2max_tips);
        this.f = (HealthTextView) findViewById(R.id.vo2max_set_gender_text);
        this.m = (HealthViewPager) nsy.cMc_(this, R.id.vo2max_detail_viewpager);
    }

    /* renamed from: com.huawei.ui.main.stories.health.activity.healthdata.Vo2maxActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends UserInformationCallback<Vo2maxActivity> {
        AnonymousClass2(Vo2maxActivity vo2maxActivity) {
            super(vo2maxActivity);
        }

        @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, final UserInfomation userInfomation) {
            if (userInfomation == null) {
                LogUtil.h("Vo2maxActivity", "refreshUserInfo getUserInfo userInfomation is null");
                return;
            }
            if (this.mReference == null) {
                LogUtil.h("Vo2maxActivity", "refreshUserInfo getUserInfo mReference is null");
                return;
            }
            final Vo2maxActivity vo2maxActivity = (Vo2maxActivity) this.mReference.get();
            if (vo2maxActivity != null) {
                vo2maxActivity.runOnUiThread(new Runnable() { // from class: qex
                    @Override // java.lang.Runnable
                    public final void run() {
                        Vo2maxActivity.this.b(userInfomation);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new AnonymousClass2(this));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    private void d() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.vo2max_detail_titlebar);
        this.n = customTitleBar;
        customTitleBar.setTitleText(this.f10089a.getString(R$string.IDS_hwh_health_vo2max));
        this.n.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.Vo2maxActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Vo2maxActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ruf.d(this, this.n, "VO2_MAX");
    }

    private void g() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.Vo2maxActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppRouter.b("/HWUserProfileMgr/UserInfoActivity").c(Vo2maxActivity.this.f10089a);
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                ixx.d().d(Vo2maxActivity.this.f10089a, AnalyticsValue.HEALTH_JUMP_USERINFO_FROM_VO2MAX_2040082.value(), hashMap, 0);
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
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).sync(new CommonCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.Vo2maxActivity.5
            @Override // com.huawei.up.callback.CommonCallback
            public void onSuccess(Bundle bundle) {
                LogUtil.a("Vo2maxActivity", "refreshUserInfoAfterSync onSuccess");
                Vo2maxActivity.this.f();
            }

            @Override // com.huawei.up.callback.CommonCallback
            public void onFail(int i) {
                LogUtil.a("Vo2maxActivity", "refreshUserInfoAfterSync onFail");
                if (i == -1) {
                    Vo2maxActivity.this.f();
                }
            }
        });
        glz.b("Vo2maxActivity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Intent intent = new Intent();
        if (this.d) {
            LogUtil.a("Vo2maxActivity", "enter backTrackDetail");
            intent.putExtra(CommonConstant.KEY_GENDER, this.g.getGenderOrDefaultValue());
            intent.putExtra("birthday", this.g.getAgeOrDefaultValue());
            setResult(100, intent);
        } else if (this.e) {
            LogUtil.a("Vo2maxActivity", "enter backSportListFragment");
            intent.putExtra("KEY_RUNNING_GENDER", this.g.getGender());
            intent.putExtra("KEY_RUNNING_AGE", this.g.getAge());
            setResult(-1, intent);
        } else {
            LogUtil.a("Vo2maxActivity", "not from trackDetail and sportList");
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        c();
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(UserInfomation userInfomation) {
        int gender = userInfomation.getGender();
        if (gender != 0 && gender != 1) {
            this.b.setVisibility(0);
            g();
        } else {
            this.b.setVisibility(8);
        }
        if (this.g.getGender() == userInfomation.getGender() && this.g.getAge() == userInfomation.getAge()) {
            LogUtil.h("Vo2maxActivity", "refreshGenderTips gender and age is not change");
        } else {
            this.g = userInfomation;
            this.c.d(gender);
        }
    }
}
