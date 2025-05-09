package com.huawei.ui.main.stories.template.health.module.hasdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.stories.health.temperature.view.OnActivityResultInterface;
import com.huawei.ui.main.stories.template.BaseComponent;
import com.huawei.ui.main.stories.template.BaseView;
import com.huawei.ui.main.stories.template.Constants;
import com.huawei.ui.main.stories.template.health.HealthMvpFragment;
import com.huawei.ui.main.stories.template.health.config.HealthCommonExpandViewConfig;
import com.huawei.ui.main.stories.template.health.config.HealthDateFragmentConfig;
import com.huawei.ui.main.stories.template.health.contract.DataDetailFragmentContract;
import com.huawei.ui.main.stories.template.health.module.hasdata.HealthDataDetailBaseFragment;
import com.huawei.ui.main.stories.template.health.view.NoDataNewsView;
import com.huawei.ui.main.stories.template.health.view.RecommendActivityView;
import defpackage.cdy;
import defpackage.nsn;
import defpackage.qmc;
import defpackage.ryq;
import defpackage.rzd;
import defpackage.rze;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class HealthDataDetailBaseFragment extends HealthMvpFragment<ryq> implements BaseView, DataDetailFragmentContract.DetailFragmentView {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10519a;
    private List<BaseComponent> b = new ArrayList(10);
    private long c;
    protected HealthDateFragmentConfig d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout.LayoutParams g;
    private Constants.PageType h;
    private List<cdy> i;
    private HealthScrollView j;
    private String m;

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initData() {
    }

    public void d(String str, HealthDateFragmentConfig healthDateFragmentConfig, Constants.PageType pageType) {
        this.m = str;
        this.d = healthDateFragmentConfig;
        this.h = pageType;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f10519a = nsn.ag(getContext());
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Iterator<BaseComponent> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onCreate();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Iterator<BaseComponent> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Iterator<BaseComponent> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Iterator<BaseComponent> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onStop();
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        Iterator<BaseComponent> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onDestory();
        }
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean ag = nsn.ag(getContext());
        boolean z = this.f10519a;
        if (ag != z) {
            this.f10519a = !z;
            Iterator<BaseComponent> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().refreshView(this.f10519a);
            }
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_common_health, viewGroup, false);
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initViews(View view) {
        this.e = (LinearLayout) view.findViewById(R.id.health_common_container);
        this.f = (LinearLayout) view.findViewById(R.id.marketing_resource_layout);
        this.j = (HealthScrollView) view.findViewById(R.id.health_common_scroll_view);
        this.b = new ArrayList();
        this.f10519a = nsn.ag(getContext());
        dUj_(this.e);
        this.j.setScrollViewListener(new ScrollViewListener() { // from class: ryw
            @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
            public final void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
                HealthDataDetailBaseFragment.this.a(healthScrollView, i, i2, i3, i4);
            }
        });
    }

    public /* synthetic */ void a(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
        ryq presenter = getPresenter();
        if (presenter instanceof qmc) {
            if (this.h == Constants.PageType.WEEK || this.h == Constants.PageType.MONTH) {
                ((qmc) presenter).d();
            }
        }
    }

    public void dUj_(LinearLayout linearLayout) {
        HealthDateFragmentConfig healthDateFragmentConfig = this.d;
        if (healthDateFragmentConfig == null) {
            return;
        }
        dUh_(healthDateFragmentConfig.getLayoutConfig().getChartView(), linearLayout);
        dUh_(this.d.getLayoutConfig().getStatisticsViewConfig(), linearLayout);
        dUh_(this.d.getLayoutConfig().getAnalysisViewConfig(), linearLayout);
        dUh_(this.d.getLayoutConfig().getHealthRecommendServiceViewConfig(), linearLayout);
        dUh_(this.d.getLayoutConfig().getHealthServicesDisplayViewConfig(), linearLayout);
        dUh_(this.d.getLayoutConfig().getRecommendActivityViewConfig(), linearLayout);
        dUh_(this.d.getLayoutConfig().getInformationViewConfig(), linearLayout);
        getPresenter().setComponents(this.b);
    }

    private void dUh_(HealthCommonExpandViewConfig healthCommonExpandViewConfig, LinearLayout linearLayout) {
        BaseComponent d;
        if (healthCommonExpandViewConfig == null) {
            return;
        }
        String newComponent = healthCommonExpandViewConfig.getNewComponent();
        if (TextUtils.isEmpty(newComponent) || (d = rzd.d(newComponent, getContext())) == null) {
            return;
        }
        if (this.g == null) {
            this.g = new LinearLayout.LayoutParams(-1, -2);
        }
        d.initComponent(healthCommonExpandViewConfig.getParams());
        d.setPageType(this.h);
        d.refreshView(this.f10519a);
        d.setPresenter(getPresenter());
        d.setDateStamp(this.c);
        linearLayout.addView(d.getView(getContext()), this.g);
        this.b.add(d);
    }

    protected View dUi_(HealthCommonExpandViewConfig healthCommonExpandViewConfig) {
        BaseComponent d;
        if (healthCommonExpandViewConfig == null) {
            return null;
        }
        String newComponent = healthCommonExpandViewConfig.getNewComponent();
        if (TextUtils.isEmpty(newComponent) || (d = rzd.d(newComponent, getContext())) == null) {
            return null;
        }
        if (this.g == null) {
            this.g = new LinearLayout.LayoutParams(-1, -2);
        }
        d.initComponent(healthCommonExpandViewConfig.getParams());
        d.setPageType(this.h);
        d.refreshView(this.f10519a);
        d.setPresenter(getPresenter());
        d.setDateStamp(this.c);
        View view = d.getView(getContext());
        this.b.add(d);
        return view;
    }

    public void c(long j) {
        this.c = j;
        List<BaseComponent> list = this.b;
        if (list != null) {
            Iterator<BaseComponent> it = list.iterator();
            while (it.hasNext()) {
                it.next().setDateStamp(j);
            }
        }
    }

    public void a(int i) {
        Iterator<BaseComponent> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onDayWeekYear(i);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        d(this.i);
    }

    private void b() {
        Iterator<BaseComponent> it = this.b.iterator();
        while (it.hasNext()) {
            KeyEvent.Callback view = it.next().getView(getContext());
            if (view instanceof OnActivityResultInterface) {
                ((OnActivityResultInterface) view).setFragment(this);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Iterator<BaseComponent> it = this.b.iterator();
        while (it.hasNext()) {
            KeyEvent.Callback view = it.next().getView(getContext());
            if (view instanceof OnActivityResultInterface) {
                ((OnActivityResultInterface) view).onActivityResult(i, i2, intent);
            }
        }
    }

    public void c(Map<Integer, ResourceResultInfo> map, int i) {
        if (getActivity() == null || getActivity().getWindow() == null) {
            LogUtil.h(this.m, "activity is null.");
            return;
        }
        ScrollUtil.cKx_(this.j, getActivity().getWindow().getDecorView(), i);
        if (map == null) {
            return;
        }
        Iterator<View> it = c(map).iterator();
        while (it.hasNext()) {
            this.f.addView(it.next());
        }
    }

    private List<View> c(Map<Integer, ResourceResultInfo> map) {
        ArrayList arrayList = new ArrayList();
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        return marketingApi != null ? marketingApi.getMarketingViewList(getActivity(), map) : arrayList;
    }

    public void d(List<cdy> list) {
        this.i = list;
        if (this.d == null || list == null) {
            LogUtil.h(this.m, "setOperationInfo mHealthDateFragmentConfig or moduleObjectList is null");
            return;
        }
        LogUtil.a(this.m, "setOperationInfo moduleObjectList size is ", Integer.valueOf(list.size()));
        for (cdy cdyVar : list) {
            if (cdyVar != null) {
                int b = cdyVar.b();
                if (b == 3) {
                    e(cdyVar);
                } else if (b == 4) {
                    c(cdyVar);
                } else {
                    LogUtil.h(this.m, "setOperationInfo unSupport moduleType", Integer.valueOf(b));
                }
            }
        }
    }

    private void e(cdy cdyVar) {
        RecommendActivityView recommendActivityView;
        if (cdyVar == null) {
            LogUtil.h(this.m, "setActivityInfo activityInfo is null");
            return;
        }
        Iterator<BaseComponent> it = this.b.iterator();
        while (true) {
            if (!it.hasNext()) {
                recommendActivityView = null;
                break;
            }
            BaseComponent next = it.next();
            if (next instanceof RecommendActivityView) {
                recommendActivityView = (RecommendActivityView) next;
                break;
            }
        }
        if (recommendActivityView == null) {
            LogUtil.h(this.m, "setNewsUiData recommendActivityView is null");
            return;
        }
        this.b.remove(recommendActivityView);
        this.e.removeView(recommendActivityView);
        View dUi_ = dUi_(this.d.getLayoutConfig().getRecommendActivityViewConfig());
        if (dUi_ instanceof RecommendActivityView) {
            RecommendActivityView recommendActivityView2 = (RecommendActivityView) dUi_;
            recommendActivityView2.setUiData(cdyVar);
            this.e.addView(recommendActivityView2);
        }
    }

    private void c(cdy cdyVar) {
        NoDataNewsView noDataNewsView;
        if (cdyVar == null) {
            LogUtil.h(this.m, "setNewsUiData newsUiData is null");
            return;
        }
        Iterator<BaseComponent> it = this.b.iterator();
        while (true) {
            if (!it.hasNext()) {
                noDataNewsView = null;
                break;
            }
            BaseComponent next = it.next();
            if (next instanceof NoDataNewsView) {
                noDataNewsView = (NoDataNewsView) next;
                break;
            }
        }
        if (noDataNewsView == null) {
            LogUtil.h(this.m, "setNewsUiData noDataNewsView is null");
            return;
        }
        this.b.remove(noDataNewsView);
        this.e.removeView(noDataNewsView);
        View dUi_ = dUi_(this.d.getLayoutConfig().getInformationViewConfig());
        if (dUi_ instanceof NoDataNewsView) {
            NoDataNewsView noDataNewsView2 = (NoDataNewsView) dUi_;
            noDataNewsView2.setUiData(cdyVar);
            this.e.addView(noDataNewsView2);
        }
    }

    @Override // com.huawei.ui.main.stories.template.BaseView
    public Context getViewContext() {
        return getContext();
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public ryq onCreatePresenter() {
        HealthDateFragmentConfig healthDateFragmentConfig = this.d;
        if (healthDateFragmentConfig == null) {
            return null;
        }
        return (ryq) rze.d(healthDateFragmentConfig.getFragmentPresenter(), this);
    }
}
