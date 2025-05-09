package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.activity.result.ActivityResultCaller;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.gso;
import defpackage.hoq;
import defpackage.nrh;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class SportBaseActivity extends AbstractSportActivity {

    /* renamed from: a, reason: collision with root package name */
    private SportLaunchParams f3667a;
    private b d;
    private HealthViewPager h;
    private BaseSportingViewModel j;
    private int e = 0;
    private int c = 0;
    private long b = 0;

    protected int c() {
        return R.layout.activity_sportting;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity
    protected void doAfterPreCheck() {
        b();
        e();
        a();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        g();
        super.onCreate(bundle);
        setContentView(c());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity
    protected void initPreCheckAction() {
        super.initPreCheckAction();
    }

    private void g() {
        SportLaunchParams i = i();
        this.f3667a = i;
        this.e = c(i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity
    protected PermissionUtil.PermissionType getPermissionType() {
        return gso.d(this.e);
    }

    protected void b() {
        this.h = (HealthViewPager) findViewById(R.id.sporting_view_pager);
    }

    protected void e() {
        LogUtil.a("Track_SportBaseActivity", "initViewModel with sport type: ", Integer.valueOf(this.e));
        Class b2 = hoq.b(this.e);
        if (b2 == null) {
            ReleaseLogUtil.c("Track_SportBaseActivity", "get view model failed, pls check sport type:", Integer.valueOf(this.e));
            finish();
            return;
        }
        this.j = (BaseSportingViewModel) new ViewModelProvider(this).get(b2);
        this.f3667a.setLaunchActivity(getClass().getName());
        Bundle bundle = new Bundle();
        bundle.putParcelable("bundle_key_sport_launch_paras", this.f3667a);
        this.j.init(bundle);
    }

    protected void a() {
        BaseSportingViewModel baseSportingViewModel = this.j;
        if (baseSportingViewModel == null) {
            LogUtil.b("Track_SportBaseActivity", "mViewModel is null");
            return;
        }
        baseSportingViewModel.requestData();
        List<Fragment> fragments = this.j.getFragments();
        this.c = this.j.getDefaultItem();
        boolean isCanScroll = this.j.isCanScroll();
        LogUtil.a("Track_SportBaseActivity", "initData with Fragment size:", Integer.valueOf(fragments.size()), " default item:", Integer.valueOf(this.c), " isCanScroll:", Boolean.valueOf(isCanScroll));
        b bVar = new b(getSupportFragmentManager(), fragments);
        this.d = bVar;
        this.h.setAdapter(bVar);
        this.h.setCurrentItem(this.c);
        this.h.setIsScroll(isCanScroll);
        this.h.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.SportBaseActivity.2
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.a("Track_SportBaseActivity", "setOnPageChangeListener position = ", Integer.valueOf(i));
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("Track_SportBaseActivity", "onBackPressed()");
        d();
        if (j()) {
            if (this.j.getSportType() == 700001) {
                return;
            }
            h();
            return;
        }
        HealthViewPager healthViewPager = this.h;
        if (healthViewPager != null && healthViewPager.getCurrentItem() != 0 && this.h.getCurrentItem() != this.c) {
            e(0);
        } else {
            super.onBackPressed();
        }
    }

    private void d() {
        BaseSportingViewModel baseSportingViewModel = this.j;
        if (baseSportingViewModel == null) {
            LogUtil.b("Track_SportBaseActivity", "dispatchBackPressedToFragment viewModel is null");
            return;
        }
        for (ActivityResultCaller activityResultCaller : baseSportingViewModel.getFragments()) {
            if (activityResultCaller instanceof IOnBackPressed) {
                ((IOnBackPressed) activityResultCaller).onBackPressed();
            }
        }
    }

    private boolean j() {
        BaseSportingViewModel baseSportingViewModel = this.j;
        return (baseSportingViewModel == null || baseSportingViewModel.getSportStatus() == 0 || this.j.getSportStatus() == 7) ? false : true;
    }

    private void h() {
        if (this.b <= 0 || System.currentTimeMillis() - this.b >= 2000) {
            this.b = System.currentTimeMillis();
            nrh.b(BaseApplication.getContext(), R.string._2130842471_res_0x7f021367);
        }
    }

    public void e(int i) {
        LogUtil.a("Track_SportBaseActivity", "mViewPager:", this.h, " item:", Integer.valueOf(i));
        if (this.h == null || i < 0 || i >= this.d.getCount()) {
            return;
        }
        this.h.setCurrentItem(i);
    }

    private int c(SportLaunchParams sportLaunchParams) {
        if (sportLaunchParams == null) {
            return 0;
        }
        return sportLaunchParams.getSportType();
    }

    private SportLaunchParams i() {
        Intent intent = getIntent();
        if (intent != null) {
            return (SportLaunchParams) intent.getParcelableExtra("bundle_key_sport_launch_paras");
        }
        return null;
    }

    public static class b extends HealthFragmentStatePagerAdapter {
        private List<Fragment> d;

        @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter, com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public Parcelable saveState() {
            return null;
        }

        public b(FragmentManager fragmentManager, List<Fragment> list) {
            super(fragmentManager);
            this.d = list;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter
        public Fragment getItem(int i) {
            List<Fragment> list = this.d;
            if (list == null || i < 0 || i >= list.size()) {
                return null;
            }
            return this.d.get(i);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            return this.d.size();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
