package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ehw;
import defpackage.eie;
import defpackage.eiv;
import defpackage.ekv;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.nsy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class TopBannerLayout extends BaseLifeCycleLinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    protected FrameLayout f2896a;
    private HealthViewPager b;
    private Context c;
    private HealthDotsPageIndicator d;
    private int e;
    private HealthViewPager f;
    private List<ResourceBriefInfo> g;
    private ekv h;
    private final HealthViewPager.OnPageChangeListener i;
    private Pair<Integer, Integer> j;
    private ekv l;
    private c n;

    public TopBannerLayout(Context context) {
        super(context);
        this.e = 4000;
        this.g = new ArrayList();
        this.j = BaseActivity.getSafeRegionWidth();
        this.i = new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.health.marketing.views.TopBannerLayout.3
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ResourceBriefInfo resourceBriefInfo;
                LogUtil.a("TopBannerLayout", "onPageSelected: ", Integer.valueOf(i));
                boolean alE_ = eie.alE_(TopBannerLayout.this);
                boolean alF_ = eie.alF_(TopBannerLayout.this, i);
                LogUtil.a("TopBannerLayout", "isRectVisibleToUser: ", Boolean.valueOf(alE_), " isWindowVisibleToUser: ", Boolean.valueOf(alF_));
                if (alE_ && alF_ && TopBannerLayout.this.h != null && koq.d(TopBannerLayout.this.g, i) && (resourceBriefInfo = (ResourceBriefInfo) TopBannerLayout.this.g.get(i)) != null) {
                    TopBannerLayout.this.h.a(1, resourceBriefInfo, i);
                }
            }
        };
        this.c = context;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c(List<ResourceBriefInfo> list) {
        if (koq.b(list)) {
            LogUtil.h("TopBannerLayout", "resourceBriefInfoList isEmpty");
            return;
        }
        this.g = list;
        LogUtil.a("TopBannerLayout", "mResourceBriefInfoList size:", Integer.valueOf(list.size()));
        Collections.sort(this.g, new ehw());
        if (this.g.size() > 3) {
            this.g = this.g.subList(0, 3);
        }
        c();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(final Configuration configuration) {
        eie.d(configuration.orientation);
        super.onConfigurationChanged(configuration);
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.marketing.views.TopBannerLayout.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("TopBannerLayout", "onConfigurationChanged,", Integer.valueOf(configuration.orientation));
                TopBannerLayout.this.h();
            }
        }, 200L);
    }

    private void c() {
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.common_top_banner_marketing, this);
        this.f2896a = (FrameLayout) inflate.findViewById(R.id.view_top_banner_root);
        HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.view_pager_common_top_banner);
        this.f = healthViewPager;
        healthViewPager.addOnPageChangeListener(this.i);
        this.b = (HealthViewPager) inflate.findViewById(R.id.view_pager_common_top_banner_cant_loop);
        this.h = new ekv(this.g, this.f, this.c);
        this.l = new ekv(this.g, this.b, this.c);
        this.f.setAdapter(this.h);
        this.b.setAdapter(this.l);
        HealthDotsPageIndicator healthDotsPageIndicator = (HealthDotsPageIndicator) inflate.findViewById(R.id.indicator);
        this.d = healthDotsPageIndicator;
        healthDotsPageIndicator.setViewPager(this.f);
        this.f.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.marketing.views.TopBannerLayout.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 1 || action == 3 || action == 4) {
                    TopBannerLayout.this.d();
                    return false;
                }
                if (action == 0) {
                    TopBannerLayout.this.b();
                    return false;
                }
                LogUtil.a("TopBannerLayout", "not need deal");
                return false;
            }
        });
        this.b.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.marketing.views.TopBannerLayout.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 1 || action == 3 || action == 4) {
                    TopBannerLayout.this.d();
                    return false;
                }
                if (action == 0) {
                    TopBannerLayout.this.b();
                    return false;
                }
                LogUtil.a("TopBannerLayout", "not need deal");
                return false;
            }
        });
        h();
        if (this.n == null) {
            c cVar = new c(this);
            this.n = cVar;
            nsy.cMa_(this.f2896a, cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.f == null || this.b == null) {
            return;
        }
        LogUtil.a("TopBannerLayout", "initData:", Integer.valueOf(this.g.size()));
        this.h.d(this.g);
        this.h.notifyDataSetChanged();
        this.l.d(this.g);
        this.l.notifyDataSetChanged();
        b(this.f);
        b(this.b);
        a();
        d(this.g.size());
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        List<ResourceBriefInfo> list = this.g;
        if (list == null || this.d == null) {
            return;
        }
        if (list.size() > 1) {
            if (nsn.ag(this.c) && this.g.size() <= 2) {
                this.d.c();
                return;
            } else {
                this.d.a(this.e);
                return;
            }
        }
        this.d.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        HealthDotsPageIndicator healthDotsPageIndicator = this.d;
        if (healthDotsPageIndicator != null) {
            healthDotsPageIndicator.c();
        }
    }

    private void b(HealthViewPager healthViewPager) {
        Context context;
        int i;
        int i2;
        int i3;
        if (koq.b(this.g) || (context = this.c) == null) {
            LogUtil.h("TopBannerLayout", "resizeViewPager() mADImageShowList is empty or mContext = null");
            return;
        }
        int b = eie.b(context, 1003);
        r3 = 0;
        int i4 = 0;
        try {
            i = (int) this.c.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("TopBannerLayout", "resizeViewPager() dimen id is not found.");
            i = 0;
        }
        if (nsn.ag(this.c)) {
            int d = eiv.d(this.c, 0);
            int b2 = nrr.b(this.c);
            int size = this.g.size();
            if (size == 0) {
                LogUtil.h("TopBannerLayout", "resizeViewPager() imageListSize <= 0.");
                i2 = 0;
            } else if (size == 1) {
                i2 = (b - d) / 2;
                healthViewPager.setIsScroll(false);
            } else {
                if (size == 2) {
                    int i5 = ((b - (d * 2)) - b2) / 2;
                    healthViewPager.setIsScroll(false);
                    i3 = (b - d) - i5;
                    i2 = i5;
                } else {
                    i2 = (b - d) / 2;
                    healthViewPager.setIsScroll(true);
                    i3 = i2;
                }
                i4 = b2;
                c(healthViewPager, i4, i2, i3);
            }
        } else {
            int dimension = (int) this.c.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
            int intValue = ((Integer) this.j.first).intValue();
            int intValue2 = ((Integer) this.j.second).intValue();
            int intValue3 = i + ((Integer) this.j.first).intValue();
            healthViewPager.setIsScroll(this.g.size() > 1);
            i4 = dimension + intValue + intValue2;
            i2 = intValue3;
        }
        i3 = i2;
        c(healthViewPager, i4, i2, i3);
    }

    private void a() {
        int d = (eiv.d(this.c, 0) * 9) / 16;
        ViewGroup.LayoutParams layoutParams = this.f2896a.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.height = d;
            this.f2896a.setLayoutParams(layoutParams2);
        }
    }

    private void d(int i) {
        if (i == 1) {
            if (nsn.ag(this.c)) {
                this.f.setVisibility(8);
                this.b.setVisibility(0);
                this.d.setViewPager(this.b);
                this.d.setVisibility(8);
                return;
            }
            this.f.setVisibility(0);
            this.b.setVisibility(8);
            this.d.setViewPager(this.f);
            this.d.setVisibility(8);
            return;
        }
        if (i != 2) {
            if (i == 3) {
                this.f.setVisibility(0);
                this.b.setVisibility(8);
                this.d.setViewPager(this.f);
                this.d.setVisibility(0);
                return;
            }
            LogUtil.c("TopBannerLayout", "mAdImageShowList size is illegal");
            return;
        }
        if (nsn.ag(this.c)) {
            this.f.setVisibility(8);
            this.b.setVisibility(0);
            this.d.setViewPager(this.b);
            this.d.setVisibility(8);
            return;
        }
        this.f.setVisibility(0);
        this.b.setVisibility(8);
        this.d.setViewPager(this.f);
        this.d.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.l == null || koq.b(this.g)) {
            return;
        }
        LogUtil.a("TopBannerLayout", "setCantLoopBiEvent");
        int i = -1;
        for (ResourceBriefInfo resourceBriefInfo : this.g) {
            if (resourceBriefInfo != null) {
                i++;
                this.l.a(1, resourceBriefInfo, i);
            }
        }
    }

    private void c(HealthViewPager healthViewPager, int i, int i2, int i3) {
        LogUtil.a("TopBannerLayout", "resizeViewPager() pageMargin = ", Integer.valueOf(i), ", startPadding = ", Integer.valueOf(i2), ", endPadding = ", Integer.valueOf(i3));
        try {
            healthViewPager.setPageMargin(i);
            healthViewPager.setPadding(i2, 0, i3, 0);
        } catch (IllegalStateException unused) {
            LogUtil.b("TopBannerLayout", "AdViewPager setPageMargin llegalStateException.");
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.n != null) {
            LogUtil.a("TopBannerLayout", "onDetachedFromWindow removeOnGlobalLayoutListener");
            nsy.cMf_(this.f2896a, this.n);
        }
    }

    static class c implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<TopBannerLayout> f2898a;

        public c(TopBannerLayout topBannerLayout) {
            this.f2898a = new WeakReference<>(topBannerLayout);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            TopBannerLayout topBannerLayout = this.f2898a.get();
            if (topBannerLayout != null && MarketingBiUtils.alP_(topBannerLayout.f2896a)) {
                LogUtil.a("TopBannerLayout", "ViewTreeObserverListener sportBanner visible ");
                if (topBannerLayout.b.getVisibility() != 0 || topBannerLayout.l == null) {
                    if (topBannerLayout.f.getVisibility() == 0 && topBannerLayout.h != null) {
                        ResourceBriefInfo resourceBriefInfo = (ResourceBriefInfo) topBannerLayout.g.get(0);
                        if (resourceBriefInfo != null) {
                            topBannerLayout.h.a(1, resourceBriefInfo, 0);
                        }
                    } else {
                        LogUtil.h("TopBannerLayout", "ViewTreeObserverListener can not report bi");
                    }
                } else {
                    topBannerLayout.e();
                }
                nsy.cMf_(topBannerLayout.f2896a, this);
            }
        }
    }
}
