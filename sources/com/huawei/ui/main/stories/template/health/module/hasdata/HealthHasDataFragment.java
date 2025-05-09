package com.huawei.ui.main.stories.template.health.module.hasdata;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.Constants;
import com.huawei.ui.main.stories.template.data.PageDataObserver;
import com.huawei.ui.main.stories.template.health.HealthMvpActivity;
import com.huawei.ui.main.stories.template.health.HealthMvpFragment;
import com.huawei.ui.main.stories.template.health.config.HealthDateFragmentConfig;
import com.huawei.ui.main.stories.template.health.config.HealthHasDataConfig;
import com.huawei.ui.main.stories.template.health.module.hasdata.HealthHasDataFragment;
import defpackage.cdy;
import defpackage.nqx;
import defpackage.qle;
import defpackage.ryg;
import defpackage.ryn;
import defpackage.rzh;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class HealthHasDataFragment extends HealthMvpFragment implements PageDataObserver {

    /* renamed from: a, reason: collision with root package name */
    private int f10520a;
    private List<HealthDataDetailBaseFragment> b;
    private HealthSubTabWidget e;
    private nqx f;
    private HealthViewPager i;
    private View j;
    private int h = 0;
    private long d = 0;
    private HealthHasDataConfig c = null;

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public BasePresenter onCreatePresenter() {
        return null;
    }

    public void e(HealthHasDataConfig healthHasDataConfig, int i, long j) {
        this.c = healthHasDataConfig;
        this.h = i;
        this.d = j;
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_health_has_data, viewGroup, false);
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initViews(View view) {
        this.b = new ArrayList(4);
        this.j = view;
        this.e = (HealthSubTabWidget) view.findViewById(R.id.health_detail_sub_tab_widget);
        HealthViewPager healthViewPager = (HealthViewPager) view.findViewById(R.id.health_detail_viewpager);
        this.i = healthViewPager;
        healthViewPager.setIsScroll(true);
        this.i.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.template.health.module.hasdata.HealthHasDataFragment.2
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                HealthHasDataFragment.this.f10520a = i;
                HealthHasDataFragment.this.e(i);
                Iterator it = HealthHasDataFragment.this.b.iterator();
                while (it.hasNext()) {
                    ((HealthDataDetailBaseFragment) it.next()).a(i);
                }
                HealthHasDataFragment.this.d(i);
            }
        });
        e(this.f10520a);
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initData() {
        if (this.c != null) {
            a();
            c();
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        qle e = e();
        if (e != null) {
            e.notifyViewPagerChange(i);
            rzh.e("sub_tab_bottom_index_y", Integer.valueOf(e.dFn_(this.e, false)));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        qle e = e();
        if (e != null) {
            e.dFo_(this.j.findViewById(R.id.layout_blood_sugar_remind));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        qle e = e();
        if (e != null) {
            e.d(i);
        }
    }

    private qle e() {
        FragmentActivity activity = getActivity();
        if (activity instanceof HealthMvpActivity) {
            BasePresenter presenter = ((HealthMvpActivity) activity).getPresenter();
            if (presenter instanceof qle) {
                return (qle) presenter;
            }
        }
        return null;
    }

    public void e(long j) {
        Iterator<HealthDataDetailBaseFragment> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().c(j);
        }
    }

    private void c(String str, String str2, HealthDateFragmentConfig healthDateFragmentConfig, Constants.PageType pageType, boolean z) {
        HealthDataDetailBaseFragment healthDataDetailBaseFragment = new HealthDataDetailBaseFragment();
        healthDataDetailBaseFragment.d(str2, healthDateFragmentConfig, pageType);
        this.f.c(this.e.c(str), healthDataDetailBaseFragment, z);
        this.b.add(healthDataDetailBaseFragment);
    }

    private void a() {
        this.f = new nqx(getChildFragmentManager(), this.i, this.e);
        HealthDateFragmentConfig dayFragmentConfig = this.c.getDayFragmentConfig();
        if (dayFragmentConfig != null) {
            c(getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_day), "Main_HealthDayDetailFragment", dayFragmentConfig, Constants.PageType.DAY, true);
        }
        HealthDateFragmentConfig weekFragmentConfig = this.c.getWeekFragmentConfig();
        if (weekFragmentConfig != null) {
            c(getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_week), "Main_HealthWeekDetailFragment", weekFragmentConfig, Constants.PageType.WEEK, false);
        }
        HealthDateFragmentConfig monthFragmentConfig = this.c.getMonthFragmentConfig();
        if (monthFragmentConfig != null) {
            c(getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_month), "Main_HealthMonthDetailFragment", monthFragmentConfig, Constants.PageType.MONTH, false);
        }
        HealthDateFragmentConfig yearFragmentConfig = this.c.getYearFragmentConfig();
        if (yearFragmentConfig != null) {
            c(getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_year), "Main_HealthYearDetailFragment", yearFragmentConfig, Constants.PageType.YEAR, false);
        }
        e(this.d);
        this.i.setOffscreenPageLimit(1);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: ryx
            @Override // java.lang.Runnable
            public final void run() {
                HealthHasDataFragment.this.d();
            }
        }, 600L);
    }

    public /* synthetic */ void d() {
        this.i.setOffscreenPageLimit(4);
    }

    private void c() {
        ryg.b().e(this.h, this);
        ryn.d().a(this.h);
    }

    @Override // com.huawei.ui.main.stories.template.data.PageDataObserver
    public void update(ryg rygVar, List<cdy> list) {
        Iterator<HealthDataDetailBaseFragment> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().d(list);
        }
    }

    private void b() {
        int i;
        final int i2;
        int i3 = this.h;
        int i4 = 8;
        if (i3 != 8) {
            i4 = 24;
            if (i3 != 24) {
                return;
            }
            i = 4027;
            i2 = 3031;
        } else {
            i = 4010;
            i2 = IEventListener.EVENT_ID_DEVICE_REQUEST_PLAY;
        }
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(getActivity()).setPageId(i4).build());
            marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener() { // from class: ryv
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    HealthHasDataFragment.this.c(marketingApi, i2, (Map) obj);
                }
            });
        }
    }

    public /* synthetic */ void c(MarketingApi marketingApi, int i, Map map) {
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        b(filterMarketingRules, i);
        LogUtil.a("datafragment", "filterResult size: ", Integer.valueOf(filterMarketingRules.size()));
    }

    private void b(final Map<Integer, ResourceResultInfo> map, final int i) {
        this.b.get(0).c(map, i);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: ryt
            @Override // java.lang.Runnable
            public final void run() {
                HealthHasDataFragment.this.a(map, i);
            }
        }, 600L);
    }

    public /* synthetic */ void a(Map map, int i) {
        for (int i2 = 1; i2 < this.b.size(); i2++) {
            this.b.get(i2).c(map, i);
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ryg.b().e(this);
        ryn.d().c();
    }
}
