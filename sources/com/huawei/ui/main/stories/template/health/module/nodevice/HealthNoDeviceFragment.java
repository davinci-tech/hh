package com.huawei.ui.main.stories.template.health.module.nodevice;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.stories.template.ResourceParseHelper;
import com.huawei.ui.main.stories.template.health.HealthMvpFragment;
import com.huawei.ui.main.stories.template.health.config.HealthNoDeviceConfig;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import com.huawei.ui.main.stories.template.health.module.nodevice.HealthNoDeviceContract;
import com.huawei.ui.main.stories.template.health.module.nodevice.HealthNoDeviceFragment;
import com.huawei.ui.main.stories.template.health.module.nodevice.bean.NoDataPageInfoBean;
import com.huawei.ui.main.stories.template.health.view.NoDataResolutionView;
import com.huawei.ui.main.stories.template.health.view.NoDataViewContainer;
import defpackage.cdy;
import defpackage.nrf;
import defpackage.rze;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class HealthNoDeviceFragment extends HealthMvpFragment<HealthNoDeviceContract.Presenter> implements HealthNoDeviceContract.View, ResourceParseHelper.ConfigInfoCallback {

    /* renamed from: a, reason: collision with root package name */
    private int f10522a;
    private Activity b;
    private NoDataViewContainer c;
    private LinearLayout d;
    private TextView e;
    private NoDataResolutionView f;
    private ImageView g;
    private ConstraintLayout h;
    private boolean i;
    private HealthNoDeviceConfig j;
    private int k;
    private String l;
    private HealthTextView m;
    private ImageView n;
    private NestedScrollView o;
    private int r;
    private CustomTitleBar t;

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getTitleName(String str) {
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initData() {
    }

    public static HealthNoDeviceFragment e(HealthNoDeviceConfig healthNoDeviceConfig, NoDataPageInfoBean noDataPageInfoBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_config", healthNoDeviceConfig);
        bundle.putParcelable("extra_page_info", noDataPageInfoBean);
        HealthNoDeviceFragment healthNoDeviceFragment = new HealthNoDeviceFragment();
        healthNoDeviceFragment.setArguments(bundle);
        return healthNoDeviceFragment;
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_health_no_device, viewGroup, false);
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    public void initViews(View view) {
        this.n = (ImageView) view.findViewById(R.id.no_data_fragment_head_img);
        this.f = (NoDataResolutionView) view.findViewById(R.id.health_detail_resolution_view);
        this.c = (NoDataViewContainer) view.findViewById(R.id.no_data_fragment_view_container);
        this.d = (LinearLayout) view.findViewById(R.id.common_bonded_product_no_data_layout);
        this.g = (ImageView) view.findViewById(R.id.has_device_no_data_img);
        this.e = (TextView) view.findViewById(R.id.has_device_no_data_desc);
        Bundle arguments = getArguments();
        if (arguments != null) {
            NoDataPageInfoBean noDataPageInfoBean = (NoDataPageInfoBean) arguments.getParcelable("extra_page_info");
            if (noDataPageInfoBean != null) {
                this.k = noDataPageInfoBean.getPageType();
                this.l = noDataPageInfoBean.getServiceId();
                this.i = noDataPageInfoBean.isHasDevice();
                d(noDataPageInfoBean);
            }
            HealthNoDeviceConfig healthNoDeviceConfig = (HealthNoDeviceConfig) arguments.getParcelable("extra_config");
            this.j = healthNoDeviceConfig;
            this.c.setConfigInfo(healthNoDeviceConfig, this.i);
        }
        if (this.i) {
            this.d.setVisibility(0);
            this.f.setVisibility(8);
            this.n.setVisibility(8);
        }
        this.o = (NestedScrollView) view.findViewById(R.id.health_detail_no_data_scrollview);
        c();
        this.m = (HealthTextView) view.findViewById(R.id.tips_net_work_down);
        this.h = (ConstraintLayout) view.findViewById(R.id.net_work_layout);
        this.m.setOnClickListener(new View.OnClickListener() { // from class: ryu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthNoDeviceFragment.this.dUk_(view2);
            }
        });
        ResourceParseHelper.d(this);
        this.b = getActivity();
        a();
        getPresenter().requestActivityInfo(this.k);
        this.c.setMarketingResource(getActivity(), this.l);
    }

    public /* synthetic */ void dUk_(View view) {
        this.m.setClickable(false);
        FragmentActivity activity = getActivity();
        if (activity instanceof HealthDataDetailActivity) {
            ((HealthDataDetailActivity) activity).b();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        String str = this.l;
        str.hashCode();
        ScrollUtil.cKx_(this.o, getActivity().getWindow().getDecorView(), !str.equals("BloodSugarCardConstructor") ? !str.equals("BodyTemperatureCardConstructor") ? 0 : 3040 : 3038);
    }

    private void d(NoDataPageInfoBean noDataPageInfoBean) {
        ResourceParseHelper.a(noDataPageInfoBean.getDesc(), this.l, this);
        ResourceParseHelper.d(noDataPageInfoBean.getImageName(), this.l, this);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        NoDataViewContainer noDataViewContainer = this.c;
        if (noDataViewContainer != null) {
            noDataViewContainer.refreshTahiti();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!CommonUtil.aa(getActivity())) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public HealthNoDeviceContract.Presenter onCreatePresenter() {
        return (HealthNoDeviceContract.Presenter) rze.d(this.j.getContentPresenter(), this);
    }

    @Override // com.huawei.ui.main.stories.template.health.module.nodevice.HealthNoDeviceContract.View
    public void onResponsePageModule(List<cdy> list) {
        this.c.setAllViewData(list);
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        getPresenter().detachView();
        if (this.f != null) {
            this.f = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        ResourceParseHelper.a();
        super.onDestroy();
    }

    @Override // com.huawei.ui.main.stories.template.BaseView
    public Context getViewContext() {
        return getContext();
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getDescription(String str) {
        if (this.i) {
            TextView textView = this.e;
            if (textView != null) {
                textView.setText(str);
                return;
            }
            return;
        }
        NoDataResolutionView noDataResolutionView = this.f;
        if (noDataResolutionView != null) {
            noDataResolutionView.setData(str);
        }
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getImagePath(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthNoDeviceFragment", "imgPath must not be null");
            return;
        }
        if (getActivity() == null) {
            LogUtil.h("HealthNoDeviceFragment", "getImagePath getActivity is null");
            return;
        }
        Activity activity = this.b;
        if (activity == null || activity.isFinishing() || this.b.isDestroyed()) {
            return;
        }
        RequestOptions skipMemoryCache = new RequestOptions().skipMemoryCache(true);
        if (this.i) {
            nrf.cIv_(str, skipMemoryCache, this.g);
        } else {
            nrf.cIv_(str, skipMemoryCache, this.n);
        }
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void showParseErrorAlert() {
        HealthTextView healthTextView = this.m;
        if (healthTextView != null) {
            healthTextView.setClickable(true);
            this.m.setVisibility(0);
            this.f.setVisibility(4);
            this.c.setVisibility(4);
        }
    }

    private void a() {
        Activity activity = this.b;
        if (activity instanceof HealthDataDetailActivity) {
            CustomTitleBar customTitleBar = ((HealthDataDetailActivity) activity).getCustomTitleBar();
            this.t = customTitleBar;
            this.r = customTitleBar.getMeasuredHeight();
        }
        this.o.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.huawei.ui.main.stories.template.health.module.nodevice.HealthNoDeviceFragment$$ExternalSyntheticLambda0
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                HealthNoDeviceFragment.this.b(nestedScrollView, i, i2, i3, i4);
            }
        });
    }

    /* synthetic */ void b(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        this.f10522a = this.n.getMeasuredHeight() - this.r;
        Resources resources = getContext().getResources();
        if (this.f10522a > i2) {
            this.t.setTitleBarBackgroundColor(resources.getColor(R.color._2131298874_res_0x7f090a3a));
        } else {
            this.t.setTitleBarBackgroundColor(resources.getColor(R.color._2131296657_res_0x7f090191));
        }
    }
}
