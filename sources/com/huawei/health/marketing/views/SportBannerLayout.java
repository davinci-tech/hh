package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.SportBannerLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ccq;
import defpackage.eie;
import defpackage.eil;
import defpackage.eiv;
import defpackage.ekp;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SportBannerLayout extends BaseLifeCycleLinearLayout implements View.OnClickListener, ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    protected HealthViewPager f2891a;
    protected ResourceBriefInfo b;
    protected Context c;
    protected HealthViewPager d;
    protected HealthDotsPageIndicator e;
    protected HealthTextView f;
    private int g;
    private int[] h;
    protected FrameLayout i;
    private GridTemplate j;
    private int k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private Pair<Integer, Integer> p;
    private int q;
    private List<SingleGridContent> r;
    private double s;
    private HealthScrollView t;
    private ekp u;
    private long v;
    private ekp x;
    private boolean y;

    protected void c() {
    }

    protected boolean getIsForceWholeScreen() {
        return false;
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public SportBannerLayout(Context context) {
        super(context);
        this.g = 4000;
        this.p = BaseActivity.getSafeRegionWidth();
        this.r = new ArrayList(10);
        this.y = true;
        this.s = 0.786d;
        this.l = false;
        this.k = -1;
        this.c = context;
    }

    public void setParentViewGroup(ViewGroup viewGroup) {
        if (viewGroup == null) {
            LogUtil.h("SportBannerLayout", "parentViewGroup is null");
            return;
        }
        int b = eie.b(this.c, this.q) - viewGroup.getMeasuredWidth();
        this.o = b;
        LogUtil.a("SportBannerLayout", "mLeftMargin =", Integer.valueOf(b));
        ekp ekpVar = this.u;
        if (ekpVar != null) {
            ekpVar.b(this.o);
        }
        ekp ekpVar2 = this.x;
        if (ekpVar2 != null) {
            ekpVar2.b(this.o);
        }
        if (this.h != null) {
            a();
        }
    }

    private void f() {
        if (!(getParent() instanceof ViewGroup) || this.t != null) {
            return;
        }
        ViewParent parent = getParent();
        while (true) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup instanceof HealthScrollView) {
                this.t = (HealthScrollView) viewGroup;
                k();
                p();
                return;
            } else if (!(viewGroup.getParent() instanceof ViewGroup)) {
                return;
            } else {
                parent = viewGroup.getParent();
            }
        }
    }

    private void k() {
        HealthScrollView healthScrollView = this.t;
        if (healthScrollView == null) {
            LogUtil.a("SportBannerLayout", "mScrollView is null");
        } else {
            healthScrollView.d(new HealthScrollView.ScrollChangeToBoundaryListener() { // from class: com.huawei.health.marketing.views.SportBannerLayout.4
                @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
                public void onScrollToChangeAlpha(float f) {
                }

                @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
                public void onScrolledToBottom() {
                }

                @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
                public void onScrolledTop() {
                }

                @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
                public void onScrollStateChange(int i) {
                    if (i == 0) {
                        SportBannerLayout.this.e();
                    } else if ((i == 2 || i == 1) && SportBannerLayout.this.y) {
                        SportBannerLayout.this.t();
                    }
                }
            });
        }
    }

    private void p() {
        HealthScrollView healthScrollView = this.t;
        if (healthScrollView == null) {
            LogUtil.a("SportBannerLayout", "mScrollView is null");
        } else {
            healthScrollView.setScrollChangeOtherListener(new HealthScrollView.ScrollChangeOtherListener() { // from class: com.huawei.health.marketing.views.SportBannerLayout.3
                @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeOtherListener
                public void scrollChangeOtherListener() {
                    SportBannerLayout sportBannerLayout = SportBannerLayout.this;
                    sportBannerLayout.y = ScrollUtil.cKv_(sportBannerLayout.t, SportBannerLayout.this);
                    LogUtil.a("SportBannerLayout", "registerScrollListener isVisibleArea = ", Boolean.valueOf(SportBannerLayout.this.y));
                    if (SportBannerLayout.this.y && !SportBannerLayout.this.n) {
                        SportBannerLayout.this.t();
                    }
                    if (SportBannerLayout.this.y || !SportBannerLayout.this.n) {
                        return;
                    }
                    SportBannerLayout.this.e();
                }
            });
        }
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        this.q = i;
        this.b = resourceBriefInfo;
        if (baseTemplate instanceof GridTemplate) {
            GridTemplate gridTemplate = (GridTemplate) baseTemplate;
            this.j = gridTemplate;
            this.r = gridTemplate.getGridContents();
        }
        if (koq.b(this.r)) {
            LogUtil.h("SportBannerLayout", "List isEmpty");
        } else {
            LogUtil.a("SportBannerLayout", "List size:", Integer.valueOf(this.r.size()));
            h();
        }
    }

    private void h() {
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.common_sport_banner_marketing, this);
        this.i = (FrameLayout) inflate.findViewById(R.id.view_sport_banner_root);
        this.d = (HealthViewPager) inflate.findViewById(R.id.view_pager_sport_banner);
        this.f2891a = (HealthViewPager) inflate.findViewById(R.id.view_pager_sport_banner_cant_loop);
        this.e = (HealthDotsPageIndicator) inflate.findViewById(R.id.indicator);
        this.f = (HealthTextView) inflate.findViewById(R.id.member_title_text);
        if (this.b.getContentType() == 63) {
            ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                layoutParams2.setMarginEnd(getResources().getDimensionPixelSize(R.dimen._2131362007_res_0x7f0a00d7));
                layoutParams2.gravity = nsn.ag(BaseApplication.getActivity()) ? 81 : 8388693;
                this.e.setLayoutParams(layoutParams2);
            }
        }
        m();
        if ((this.b.getContentType() == 52 && this.j.getImgShowSize() == 99) || this.b.getContentType() == 98) {
            LogUtil.a("SportBannerLayout", "mResourceBriefInfo.getContentType() is ", Integer.valueOf(this.b.getContentType()), " initBannerImgProportion");
            j();
        }
        a();
        c();
        nsy.cMa_(this.i, new d(this));
    }

    private void m() {
        if (eie.b(this.q)) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = this.c.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
            this.i.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ekq
            @Override // java.lang.Runnable
            public final void run() {
                SportBannerLayout.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        if (!koq.b(this.r) && this.r.get(0) != null && !TextUtils.isEmpty(this.r.get(0).getPicture())) {
            String picture = this.r.get(0).getPicture();
            if (this.b.getContentType() == 98 && nsn.ag(BaseApplication.getActivity())) {
                LogUtil.a("SportBannerLayout", "TEMPLATE_MEMBER_TOP_BANNER and isWidescreen, get tahitiPicture");
                picture = this.r.get(0).getTahitiPicture();
            }
            Drawable cId_ = nrf.cId_(BaseApplication.getContext(), picture, nrf.d);
            if (cId_ != null) {
                this.h = new int[]{cId_.getIntrinsicWidth(), 0};
                this.h[1] = cId_.getIntrinsicHeight();
                int[] iArr = this.h;
                double d2 = iArr[1];
                int i = iArr[0];
                this.s = d2 / i;
                LogUtil.a("SportBannerLayout", "handleImageView drawable.width=", Integer.valueOf(i), " height=", Integer.valueOf(this.h[1]));
            } else {
                LogUtil.a("SportBannerLayout", "handleImageView drawable is null");
            }
        }
        HandlerExecutor.a(new Runnable() { // from class: ekk
            @Override // java.lang.Runnable
            public final void run() {
                SportBannerLayout.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a() {
        HealthViewPager healthViewPager = this.d;
        if (healthViewPager == null || this.f2891a == null) {
            LogUtil.a("SportBannerLayout", "resetLayout mNormalViewPager is ", healthViewPager, " mLoopViewPager is ", this.f2891a, " so return!");
        } else {
            c(this.r.size());
            t();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(final Configuration configuration) {
        eie.d(configuration.orientation);
        super.onConfigurationChanged(configuration);
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.marketing.views.SportBannerLayout.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SportBannerLayout", "SportBannerLayout,", Integer.valueOf(configuration.orientation));
                ViewGroup.LayoutParams layoutParams = SportBannerLayout.this.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = -1;
                    SportBannerLayout.this.setLayoutParams(layoutParams);
                }
                if (SportBannerLayout.this.b != null && SportBannerLayout.this.b.getContentType() == 98) {
                    SportBannerLayout.this.j();
                } else {
                    SportBannerLayout.this.a();
                }
            }
        }, 200L);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = -1;
            setLayoutParams(layoutParams);
        }
        f();
    }

    private void o() {
        c(0, 8, 0);
        ekp ekpVar = new ekp(this.d, this.c);
        this.x = ekpVar;
        ekpVar.c(this.q, this.j, this.b, true);
        this.x.b(this.s);
        this.d.setOffscreenPageLimit(this.r.size());
        this.d.addOnPageChangeListener(new a(this));
        this.x.b(this.o);
        this.x.aqf_(new View.OnClickListener() { // from class: ekl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportBannerLayout.this.apU_(view);
            }
        });
        this.d.setAdapter(this.x);
        this.e.setViewPager(this.d);
        b(this.d);
        i();
        this.d.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.marketing.views.SportBannerLayout.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SportBannerLayout.this.apS_(motionEvent);
                return false;
            }
        });
    }

    public /* synthetic */ void apU_(View view) {
        g();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void setNeedExtraHeight(boolean z) {
        this.l = z;
    }

    public boolean getNeedExtraHeight() {
        return this.l;
    }

    public void setExtraCardHeight(int i) {
        this.k = i;
    }

    public int getExtraCardHeight() {
        return this.k;
    }

    static class a implements HealthViewPager.OnPageChangeListener {
        private final WeakReference<SportBannerLayout> e;

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        public a(SportBannerLayout sportBannerLayout) {
            this.e = new WeakReference<>(sportBannerLayout);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            SingleGridContent singleGridContent;
            LogUtil.a("SportBannerLayout", "onPageSelected: ", Integer.valueOf(i));
            SportBannerLayout sportBannerLayout = this.e.get();
            if (sportBannerLayout == null) {
                LogUtil.h("SportBannerLayout", "layout activity is null");
                return;
            }
            boolean alE_ = eie.alE_(sportBannerLayout);
            boolean alF_ = eie.alF_(sportBannerLayout, i);
            LogUtil.a("SportBannerLayout", "isRectVisibleToUser: ", Boolean.valueOf(alE_), " isWindowVisibleToUser: ", Boolean.valueOf(alF_));
            if (alE_ && alF_ && sportBannerLayout.x != null && koq.d(sportBannerLayout.r, i) && (singleGridContent = (SingleGridContent) sportBannerLayout.r.get(i)) != null) {
                sportBannerLayout.x.d(1, singleGridContent, i);
            }
        }
    }

    private void l() {
        c(8, 0, 8);
        ekp ekpVar = new ekp(this.f2891a, this.c);
        this.u = ekpVar;
        ekpVar.c(this.q, this.j, this.b, false);
        this.u.b(this.s);
        this.u.b(this.o);
        this.u.aqf_(new View.OnClickListener() { // from class: eko
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportBannerLayout.this.apT_(view);
            }
        });
        this.f2891a.setAdapter(this.u);
        b(this.f2891a);
        i();
        this.f2891a.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.marketing.views.SportBannerLayout.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SportBannerLayout.this.apS_(motionEvent);
                return false;
            }
        });
    }

    public /* synthetic */ void apT_(View view) {
        g();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.u == null || koq.b(this.r)) {
            return;
        }
        int i = -1;
        for (SingleGridContent singleGridContent : this.r) {
            if (singleGridContent != null) {
                i++;
                this.u.d(1, singleGridContent, i);
            }
        }
    }

    private void c(int i, int i2, int i3) {
        this.d.setVisibility(i);
        this.f2891a.setVisibility(i2);
        this.e.setVisibility(i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        List<SingleGridContent> list = this.r;
        if (list == null || this.e == null) {
            return;
        }
        if (list.size() > 1) {
            if (nsn.ag(BaseApplication.getActivity()) && this.r.size() <= 2) {
                this.n = false;
                this.e.c();
                return;
            } else {
                this.n = true;
                this.e.a(this.g);
                return;
            }
        }
        this.n = false;
        this.e.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void apS_(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            t();
        } else if (action == 0) {
            e();
        } else {
            LogUtil.a("SportBannerLayout", "not need deal");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        HealthDotsPageIndicator healthDotsPageIndicator = this.e;
        if (healthDotsPageIndicator != null) {
            this.n = false;
            healthDotsPageIndicator.c();
        }
    }

    private void b(HealthViewPager healthViewPager) {
        int i;
        int intValue;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (koq.b(this.r)) {
            LogUtil.h("SportBannerLayout", "resizeViewPager() mADImageShowList is empty.");
            return;
        }
        int b = eie.b(this.c, this.q) - this.o;
        try {
            i = (int) this.c.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("SportBannerLayout", "resizeViewPager() dimen id is not found.");
            i = 0;
        }
        if (nsn.ag(BaseApplication.getActivity())) {
            int d2 = eiv.d(this.c, this.o);
            i2 = nrr.b(this.c);
            int size = this.r.size();
            if (size == 1) {
                i3 = ((b - (d2 * 2)) - i2) / 2;
                intValue = (b - d2) - i3;
                healthViewPager.setIsScroll(false);
                i2 = 0;
            } else if (size == 2) {
                i3 = ((b - (d2 * 2)) - i2) / 2;
                intValue = (b - d2) - i3;
                healthViewPager.setIsScroll(false);
            } else if (size >= 3) {
                i3 = (b - d2) / 2;
                healthViewPager.setIsScroll(true);
                intValue = i3;
            } else {
                LogUtil.h("SportBannerLayout", "resizeViewPager() imageListSize <= 0.");
                intValue = 0;
                i2 = 0;
                i3 = 0;
            }
        } else {
            boolean e = eie.e(this.q);
            int dimension = (int) this.c.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e);
            int intValue2 = e ? 0 : ((Integer) this.p.first).intValue() + ((Integer) this.p.second).intValue();
            intValue = (e ? 0 : ((Integer) this.p.first).intValue()) + i;
            healthViewPager.setIsScroll(this.r.size() > 1);
            i2 = dimension + intValue2;
            i3 = intValue;
        }
        LogUtil.a("SportBannerLayout", "resizeViewPager() pageMargin = ", Integer.valueOf(i2), ", startPadding = ", Integer.valueOf(i3), ", endPadding = ", Integer.valueOf(intValue));
        if (getIsForceWholeScreen()) {
            i5 = 0;
            i6 = 0;
            i4 = 0;
        } else {
            i4 = intValue;
            i5 = i2;
            i6 = i3;
        }
        ccq.d(healthViewPager, i5, "SportBannerLayout", i6, i4, 0);
    }

    private void i() {
        int d2;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        int i5;
        int contentType = this.b.getContentType();
        if (contentType != 51) {
            if (contentType == 52) {
                int d3 = eiv.d(this.c, this.o);
                if (this.j.getImgShowSize() == 99 && (iArr = this.h) != null && iArr.length == 2 && (i5 = iArr[0]) != 0) {
                    i4 = (iArr[1] * d3) / i5;
                } else {
                    i4 = (d3 * 8) / 27;
                }
                LogUtil.a("SportBannerLayout", "resizeRootLayout width:", Integer.valueOf(d3), "  height:", Integer.valueOf(i4));
                d2 = i4;
            } else if (contentType == 98) {
                int i6 = this.c.getResources().getDisplayMetrics().widthPixels;
                int[] iArr2 = this.h;
                if (iArr2 != null && iArr2.length == 2 && (i3 = iArr2[0]) != 0) {
                    i = (i6 * iArr2[1]) / i3;
                } else {
                    i = (i6 * 708) / 1080;
                }
                int i7 = this.k;
                if (i7 == -1) {
                    i2 = 26;
                } else {
                    double d4 = i;
                    double d5 = i7 + (0.656d * d4);
                    i2 = d5 > d4 ? ((int) d5) - i : 0;
                }
                d2 = i + (this.l ? i2 : 0);
                LogUtil.a("SportBannerLayout", "resizeRootLayout extraHeight:", Integer.valueOf(i2), "  height:", Integer.valueOf(d2));
            } else {
                d2 = (eiv.d(this.c, this.o) * 9) / 16;
            }
        } else if (this.e.getVisibility() == 0) {
            d2 = this.c.getResources().getDimensionPixelSize(R.dimen._2131363134_res_0x7f0a053e);
        } else {
            d2 = this.c.getResources().getDimensionPixelSize(R.dimen._2131363107_res_0x7f0a0523);
        }
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.height = d2;
            this.i.setLayoutParams(layoutParams2);
        }
    }

    private void c(int i) {
        if (i == 1) {
            l();
            return;
        }
        if (i == 2) {
            if (nsn.ag(BaseApplication.getActivity())) {
                l();
                return;
            } else {
                o();
                return;
            }
        }
        if (i >= 3) {
            o();
        } else {
            LogUtil.c("SportBannerLayout", "mAdImageShowList size is illegal");
        }
    }

    private void g() {
        ReleaseLogUtil.e("R_SportBannerLayout", "setResourceCloseRecord. closeTime = ", Long.valueOf(System.currentTimeMillis()), "; resourceName = ", this.b.getResourceName(), "; contentType = ", Integer.valueOf(this.b.getContentType()));
        eil.a(this.q, 1, this.b);
        MarketingBiUtils.c(this.q, this.b, this.v);
        setVisibility(8);
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        return (resourceBriefInfo == null || this.b == null || !resourceBriefInfo.getResourceId().equals(this.b.getResourceId())) ? false : true;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.b;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    @Override // com.huawei.health.marketing.views.BaseLifeCycleLinearLayout, com.huawei.health.knit.data.IKnitLifeCycle
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.m = z;
        LogUtil.a("SportBannerLayout", "setUserVisibleHint: ", Boolean.valueOf(z));
        if (z) {
            t();
        } else {
            e();
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        LogUtil.a("SportBannerLayout", "onWindowVisibilityChanged: ", Integer.valueOf(i));
        if (i == 0) {
            if (this.m) {
                t();
                return;
            }
            return;
        }
        e();
    }

    static class d implements ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<SportBannerLayout> b;

        public d(SportBannerLayout sportBannerLayout) {
            this.b = new WeakReference<>(sportBannerLayout);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            SportBannerLayout sportBannerLayout = this.b.get();
            if (sportBannerLayout != null && MarketingBiUtils.alP_(sportBannerLayout.i)) {
                LogUtil.a("SportBannerLayout", "ViewTreeObserverListener sportBanner visible ");
                if (sportBannerLayout.f2891a.getVisibility() == 0 && sportBannerLayout.u != null) {
                    sportBannerLayout.n();
                } else if (sportBannerLayout.d.getVisibility() == 0 && sportBannerLayout.x != null) {
                    SingleGridContent singleGridContent = (SingleGridContent) sportBannerLayout.r.get(0);
                    if (singleGridContent != null) {
                        sportBannerLayout.x.d(1, singleGridContent, 0);
                    }
                    sportBannerLayout.v = System.currentTimeMillis();
                } else {
                    LogUtil.h("SportBannerLayout", "ViewTreeObserverListener can not report bi");
                }
                nsy.cMf_(sportBannerLayout.i, this);
            }
        }
    }
}
