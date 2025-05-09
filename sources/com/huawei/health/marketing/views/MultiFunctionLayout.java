package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.MultiFunctionTemplate;
import com.huawei.health.marketing.datatype.templates.SingleFunctionGridContent;
import com.huawei.health.marketing.views.MultiFunctionLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.eie;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class MultiFunctionLayout extends BaseLifeCycleLinearLayout implements ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    private List<SingleFunctionGridContent> f2876a;
    private int b;
    private Context c;
    private HealthSubHeader d;
    private ResourceBriefInfo e;
    private LinearLayout f;
    private boolean g;
    private RelativeLayout h;
    private MultiFunctionPagerAdapter i;
    private HealthDotsPageIndicator j;
    private HealthViewPager k;
    private MultiFunctionTemplate l;
    private int m;
    private long n;
    private final Pair<Integer, Integer> o;

    public MultiFunctionLayout(Context context) {
        super(context);
        this.m = 0;
        this.g = false;
        this.o = BaseActivity.getSafeRegionWidth();
        this.c = context;
    }

    public void e(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        LogUtil.a("MultiFunctionLayout", "initData");
        this.e = resourceBriefInfo;
        this.m = i;
        this.l = (MultiFunctionTemplate) baseTemplate;
        LogUtil.a("MultiFunctionLayout", "template:", baseTemplate.toString());
        if (resourceBriefInfo != null) {
            this.b = resourceBriefInfo.getContentType();
        }
        e();
    }

    private void e() {
        List<SingleFunctionGridContent> gridContents = this.l.getGridContents();
        this.f2876a = gridContents;
        if (koq.b(gridContents)) {
            setVisibility(8);
            LogUtil.h("MultiFunctionLayout", "dataShow() entryContentList is empty.");
            return;
        }
        this.n = System.currentTimeMillis();
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.layout_multi_function_with_slider, this);
        this.f = (LinearLayout) inflate.findViewById(R.id.multi_function_linear_layout);
        this.k = (HealthViewPager) inflate.findViewById(R.id.multi_function_view_pager);
        this.j = (HealthDotsPageIndicator) inflate.findViewById(R.id.multi_function_indicator);
        this.h = (RelativeLayout) inflate.findViewById(R.id.multi_function_relative_layout);
        this.d = (HealthSubHeader) inflate.findViewById(R.id.multi_function_sub_header);
        MultiFunctionPagerAdapter multiFunctionPagerAdapter = new MultiFunctionPagerAdapter(this.c);
        this.i = multiFunctionPagerAdapter;
        multiFunctionPagerAdapter.b(this.f2876a, this.e, this.m, this.b);
        this.k.setAdapter(this.i);
        this.j.setViewPager(this.k);
        this.j.setOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.health.marketing.views.MultiFunctionLayout.3
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.a("MultiFunctionLayout", "onPageSelected() position = ", Integer.valueOf(i));
            }
        });
        if (this.f2876a.size() <= 8) {
            ((LinearLayout) inflate.findViewById(R.id.multi_function_indicator_layout)).setVisibility(8);
            this.k.setIsScroll(false);
        }
        this.g = nsn.ag(BaseApplication.getContext());
        this.d.setVisibility(4);
        LinearLayout.LayoutParams subHeaderParams = getSubHeaderParams();
        if (subHeaderParams != null) {
            subHeaderParams.height = this.c.getResources().getDimensionPixelSize(R.dimen._2131362573_res_0x7f0a030d);
        }
        b();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        eie.d(configuration.orientation);
        this.g = nsn.ag(BaseApplication.getContext());
        super.onConfigurationChanged(configuration);
        this.i.e(this.g);
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        return (resourceBriefInfo == null || this.e == null || !resourceBriefInfo.getResourceId().equals(this.e.getResourceId())) ? false : true;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.e;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    private LinearLayout.LayoutParams getSubHeaderParams() {
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            int i = layoutParams2.leftMargin;
            int i2 = layoutParams2.rightMargin;
            if (eie.e(this.m)) {
                layoutParams2.leftMargin = i;
                layoutParams2.rightMargin = i2;
            } else {
                layoutParams2.leftMargin = i + ((Integer) this.o.first).intValue();
                layoutParams2.rightMargin = i2 + ((Integer) this.o.second).intValue();
            }
        }
        return layoutParams2;
    }

    private void b() {
        a();
        d();
        this.k.setIsAutoWidthAndHeight(true);
        setRecycleViewClickListener(this.l.getLinkValue());
        setLayoutParam();
        RequestOptions centerCrop = RequestOptions.bitmapTransform(new RoundedCorners((int) this.c.getResources().getDimension(R.dimen._2131362359_res_0x7f0a0237))).centerCrop();
        CustomTarget<Drawable> customTarget = new CustomTarget<Drawable>() { // from class: com.huawei.health.marketing.views.MultiFunctionLayout.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: apE_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) MultiFunctionLayout.this.h.getLayoutParams();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                layoutParams.height = (layoutParams.width * bitmapDrawable.getBitmap().getHeight()) / bitmapDrawable.getBitmap().getWidth();
                MultiFunctionLayout.this.h.setLayoutParams(layoutParams);
                MultiFunctionLayout.this.h.setBackground(drawable);
            }
        };
        if (nrt.a(this.c)) {
            nrf.b(this.l.getDarkBackgroundPicture(), centerCrop, customTarget);
        } else {
            nrf.b(this.l.getPicture(), centerCrop, customTarget);
        }
    }

    private void a() {
        ViewGroup.LayoutParams layoutParams = this.h.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
            int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
            int intValue = eie.e(this.m) ? 0 : ((Integer) this.o.first).intValue();
            int intValue2 = eie.e(this.m) ? 0 : ((Integer) this.o.second).intValue();
            layoutParams2.setMarginStart(dimensionPixelSize + intValue);
            layoutParams2.setMarginEnd(dimensionPixelSize2 + intValue2);
            this.h.setLayoutParams(layoutParams2);
        }
    }

    private void d() {
        int e;
        ViewGroup.LayoutParams layoutParams = this.k.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
            if (!eie.e(this.m)) {
                dimensionPixelSize += ((Integer) this.o.first).intValue();
                dimensionPixelSize2 += ((Integer) this.o.second).intValue();
            }
            layoutParams2.addRule(14);
            if (this.f2876a.size() <= 8) {
                e = nrr.e(this.c, 16.0f);
            } else {
                e = nrr.e(this.c, 8.0f);
            }
            layoutParams2.setMargins(dimensionPixelSize, nrr.e(this.c, 50.0f), dimensionPixelSize2, e);
            this.k.setLayoutParams(layoutParams2);
        }
    }

    private void setRecycleViewClickListener(final String str) {
        this.h.setOnClickListener(new View.OnClickListener() { // from class: ekf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MultiFunctionLayout.this.apD_(str, view);
            }
        });
    }

    public /* synthetic */ void apD_(String str, View view) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MultiFunctionLayout", "goToDetail linkValue is empty ");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        c();
        LogUtil.a("MultiFunctionLayout", "more linkValue: ", str);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("OperationBundle", MarketRouterApi.class);
        if (marketRouterApi != null && str != null) {
            marketRouterApi.router(str);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("resourcePositionId", Integer.valueOf(this.m));
        hashMap.put("resourceId", this.e.getResourceId());
        hashMap.put("resourceName", this.e.getResourceName());
        hashMap.put("event", 3);
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("moreEntryName", "");
        hashMap.put("smartRecommend", Boolean.valueOf(this.e.getSmartRecommend()));
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.n));
        this.n = System.currentTimeMillis();
        ixx.d().d(this.c, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    public void setLayoutParam() {
        LogUtil.a("MultiFunctionLayout", "setLayoutParam");
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f.getLayoutParams();
        layoutParams.width = -2;
        this.f.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.k.getLayoutParams();
        layoutParams2.width = -2;
        this.k.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.h.getLayoutParams();
        layoutParams3.width = -2;
        this.h.setLayoutParams(layoutParams3);
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        super.onResume();
        LogUtil.a("MultiFunctionLayout", "onResume");
    }
}
