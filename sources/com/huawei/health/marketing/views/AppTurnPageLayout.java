package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.request.ColumnProvider;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.eie;
import defpackage.eit;
import defpackage.eiv;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class AppTurnPageLayout extends BaseLifeCycleLinearLayout implements View.OnClickListener, ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    private int f2818a;
    private HealthDotsPageIndicator b;
    private Context c;
    private HealthViewPager d;
    private HealthViewPager e;
    private List<List<SingleGridContent>> f;
    private int g;
    private ResourceBriefInfo h;
    private List<SingleGridContent> i;
    private FrameLayout j;
    private int k;
    private int l;
    private eit m;
    private eit o;

    public AppTurnPageLayout(Context context) {
        super(context);
        this.i = new ArrayList(10);
        this.f = new ArrayList(10);
        this.c = context;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        if (resourceBriefInfo == null) {
            LogUtil.h("AppTurnPageLayout", "resourceBriefInfo is null");
            return;
        }
        this.g = i;
        this.h = resourceBriefInfo;
        if (baseTemplate instanceof GridTemplate) {
            this.i = ((GridTemplate) baseTemplate).getGridContents();
        }
        if (koq.b(this.i)) {
            LogUtil.h("AppTurnPageLayout", "mSingleGridContentList is null.");
            return;
        }
        LogUtil.a("AppTurnPageLayout", "initData size:", Integer.valueOf(this.i.size()));
        a();
        d();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        eie.d(configuration.orientation);
        super.onConfigurationChanged(configuration);
        c();
    }

    private void d() {
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.common_app_turn_page, this);
        this.j = (FrameLayout) inflate.findViewById(R.id.view_pager_function_menu_root);
        this.d = (HealthViewPager) inflate.findViewById(R.id.view_pager_app_turn_page);
        this.e = (HealthViewPager) inflate.findViewById(R.id.view_pager_app_turn_page_cant_loop);
        this.b = (HealthDotsPageIndicator) inflate.findViewById(R.id.indicator_app_turn_page);
        eit eitVar = new eit(this.f, this.e, this.c, this.g);
        this.o = eitVar;
        this.e.setAdapter(eitVar);
        eit eitVar2 = new eit(this.f, this.d, this.c, this.g);
        this.m = eitVar2;
        this.d.setAdapter(eitVar2);
        c();
    }

    private void c() {
        if (this.j == null) {
            return;
        }
        if (this.f.size() <= 1) {
            amb_(this.e, this.j);
            this.o.d(this.f, this.h);
            this.o.notifyDataSetChanged();
            this.e.setIsScroll(false);
            this.d.setVisibility(8);
            this.e.setVisibility(0);
            this.b.setVisibility(8);
        } else {
            amb_(this.d, this.j);
            this.m.d(this.f, this.h);
            this.m.notifyDataSetChanged();
            this.d.setIsScroll(true);
            this.d.setVisibility(0);
            this.e.setVisibility(8);
            this.b.setViewPager(this.d);
            this.b.setVisibility(0);
        }
        this.b.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void amb_(com.huawei.ui.commonui.viewpager.HealthViewPager r6, android.widget.FrameLayout r7) {
        /*
            r5 = this;
            android.app.Activity r0 = com.huawei.hwcommonmodel.application.BaseApplication.getActivity()
            boolean r0 = defpackage.nsn.ag(r0)
            java.util.List<java.util.List<com.huawei.health.marketing.datatype.SingleGridContent>> r1 = r5.f
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L34
            int r1 = r1.size()
            if (r1 != r2) goto L34
            java.util.List<java.util.List<com.huawei.health.marketing.datatype.SingleGridContent>> r1 = r5.f
            boolean r1 = defpackage.koq.d(r1, r3)
            if (r1 == 0) goto L34
            java.util.List<java.util.List<com.huawei.health.marketing.datatype.SingleGridContent>> r1 = r5.f
            java.lang.Object r1 = r1.get(r3)
            java.util.List r1 = (java.util.List) r1
            int r1 = r1.size()
            if (r0 == 0) goto L2f
            r0 = 8
            if (r1 > r0) goto L34
            goto L32
        L2f:
            r0 = 5
            if (r1 > r0) goto L34
        L32:
            r0 = r2
            goto L35
        L34:
            r0 = r3
        L35:
            android.view.ViewGroup$LayoutParams r1 = r6.getLayoutParams()
            boolean r1 = r1 instanceof android.widget.FrameLayout.LayoutParams
            if (r1 != 0) goto L3e
            return
        L3e:
            android.content.Context r1 = r5.c
            android.content.res.Resources r1 = r1.getResources()
            r5.amc_(r6, r1, r0)
            android.view.ViewGroup$LayoutParams r6 = r7.getLayoutParams()
            boolean r6 = r6 instanceof android.widget.LinearLayout.LayoutParams
            if (r6 != 0) goto L50
            return
        L50:
            r6 = 2131363834(0x7f0a07fa, float:1.8347488E38)
            int r6 = r1.getDimensionPixelSize(r6)
            java.util.List<java.util.List<com.huawei.health.marketing.datatype.SingleGridContent>> r4 = r5.f
            int r4 = r4.size()
            if (r4 > r2) goto L60
            r6 = r3
        L60:
            boolean r2 = defpackage.eiv.b()
            if (r2 == 0) goto L98
            r2 = 2131365063(0x7f0a0cc7, float:1.834998E38)
            int r2 = r1.getDimensionPixelSize(r2)
            boolean r3 = defpackage.nsn.s()
            if (r3 == 0) goto L84
            android.util.DisplayMetrics r3 = r1.getDisplayMetrics()
            float r3 = r3.scaledDensity
            float r2 = (float) r2
            float r2 = r2 / r3
            android.content.Context r3 = r5.c
            r4 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 * r4
            int r2 = defpackage.nsn.c(r3, r2)
        L84:
            int r3 = r5.k
            r4 = 2131363833(0x7f0a07f9, float:1.8347486E38)
            int r1 = r1.getDimensionPixelSize(r4)
            int r3 = r3 - r1
            int r2 = r2 * 2
            int r3 = r3 + r2
            if (r0 != 0) goto L98
            int r3 = r3 * 2
            int r0 = r5.f2818a
            int r3 = r3 + r0
        L98:
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r0 = (android.widget.LinearLayout.LayoutParams) r0
            boolean r1 = defpackage.eiv.b()
            if (r1 == 0) goto La5
            goto La7
        La5:
            int r3 = r5.l
        La7:
            int r3 = r3 + r6
            r0.height = r3
            r7.setLayoutParams(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.marketing.views.AppTurnPageLayout.amb_(com.huawei.ui.commonui.viewpager.HealthViewPager, android.widget.FrameLayout):void");
    }

    private void amc_(HealthViewPager healthViewPager, Resources resources, boolean z) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) healthViewPager.getLayoutParams();
        Context activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h("AppTurnPageLayout", "getActivity = null");
            activity = BaseApplication.getContext();
        }
        layoutParams.width = nsn.h(activity);
        this.f2818a = eie.a(this.h.getContentType());
        int dimensionPixelSize = (resources.getDimensionPixelSize(R.dimen._2131362566_res_0x7f0a0306) * 2) + resources.getDimensionPixelSize(R.dimen._2131363589_res_0x7f0a0705) + resources.getDimensionPixelSize(R.dimen._2131363833_res_0x7f0a07f9);
        this.k = dimensionPixelSize;
        if (!z) {
            dimensionPixelSize = (dimensionPixelSize * 2) + this.f2818a;
        }
        this.l = dimensionPixelSize;
        layoutParams.height = eiv.b() ? -2 : this.l;
        healthViewPager.setLayoutParams(layoutParams);
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        return (resourceBriefInfo == null || this.h == null || !resourceBriefInfo.getResourceId().equals(this.h.getResourceId())) ? false : true;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.h;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    private void a() {
        List<SingleGridContent> functionMenuFinallyData = ColumnRequestUtils.getFunctionMenuFinallyData(this.i, ColumnProvider.getInstance(BaseApplication.getContext()).getFavoriteList());
        int i = nsn.ag(BaseApplication.getActivity()) ? 16 : 10;
        this.f.clear();
        int i2 = 0;
        while (i2 < ((functionMenuFinallyData.size() - 1) / i) + 1) {
            int i3 = i2 + 1;
            int i4 = i3 * i;
            if (i4 >= functionMenuFinallyData.size()) {
                this.f.add(functionMenuFinallyData.subList(i2 * i, functionMenuFinallyData.size()));
            } else {
                this.f.add(functionMenuFinallyData.subList(i2 * i, i4));
            }
            i2 = i3;
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        LogUtil.a("AppTurnPageLayout", "onWindowVisibilityChanged visibility=", Integer.valueOf(i), "mPageSizeList size=", Integer.valueOf(this.f.size()));
        if (i != 0 || koq.b(this.f)) {
            return;
        }
        a();
        this.m.notifyDataSetChanged();
    }
}
