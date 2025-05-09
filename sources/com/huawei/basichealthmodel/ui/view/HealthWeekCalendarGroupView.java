package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.basichealthmodel.callback.DateChangeListener;
import com.huawei.basichealthmodel.callback.DateSelectListener;
import com.huawei.basichealthmodel.callback.PageScrollChangeListener;
import com.huawei.basichealthmodel.callback.WeekCloverItemClickListener;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.axz;
import defpackage.ayd;
import defpackage.azi;
import defpackage.jdl;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class HealthWeekCalendarGroupView extends LinearLayout implements HealthViewPager.OnPageChangeListener, DateSelectListener, WeekCloverItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1924a;
    private HealthViewPager b;
    private Context c;
    private boolean d;
    private HealthWeekCloverView e;
    private int f;
    private boolean g;
    private boolean h;
    private PageScrollChangeListener i;
    private DateChangeListener j;
    private axz k;
    private int l;
    private int m;
    private HealthWeekCalendarView n;
    private List<HealthWeekCloverView> o;

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    public HealthWeekCalendarGroupView(Context context) {
        this(context, null);
    }

    public HealthWeekCalendarGroupView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HealthWeekCalendarGroupView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = 0;
        this.f1924a = true;
        this.h = true;
        this.d = true;
        this.g = false;
        this.c = context;
        int t = azi.t();
        this.f = t;
        if (t == 0) {
            this.f = DateFormatUtil.b(System.currentTimeMillis());
        }
        lA_(context, attributeSet);
        c(context);
    }

    private void lA_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100136_res_0x7f0601e8, R.attr._2131100140_res_0x7f0601ec, R.attr._2131100141_res_0x7f0601ed});
        if (obtainStyledAttributes == null) {
            return;
        }
        try {
            this.d = obtainStyledAttributes.getBoolean(1, true);
            this.f1924a = obtainStyledAttributes.getBoolean(2, true);
            this.h = obtainStyledAttributes.getBoolean(0, true);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthLife_HealthWeekCalendarGroupView", "init NotFoundException");
        }
        obtainStyledAttributes.recycle();
    }

    public void setDateChangeListener(DateChangeListener dateChangeListener) {
        this.j = dateChangeListener;
    }

    public void setPageScrollChangeListener(PageScrollChangeListener pageScrollChangeListener) {
        this.i = pageScrollChangeListener;
    }

    public void setWeekData(Map<Integer, List<ayd>> map, int i) {
        List<HealthWeekCloverView> list = this.o;
        if (list == null || list.size() <= 0) {
            LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "setWeekData mWeekCloverViewList can not be null");
            return;
        }
        LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "setWeekData pageNumber = ", Integer.valueOf(i));
        if (koq.d(this.o, i)) {
            HealthWeekCloverView healthWeekCloverView = this.o.get((r0.size() - i) - 1);
            healthWeekCloverView.setPageNumber(i);
            healthWeekCloverView.setCloverMapData(map);
        }
    }

    public void d(int i, int i2, Map<Integer, List<ayd>> map, boolean z) {
        if (i2 < i) {
            LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "initExactWeekViewData endTime < beginTime");
            return;
        }
        if (map == null || map.size() <= 0) {
            LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "initExactWeekViewData map = null");
            return;
        }
        LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "initExactWeekViewData beginTime = ", Integer.valueOf(i), ", endTime = ", Integer.valueOf(i2));
        SparseIntArray lT_ = azi.lT_(i);
        if (lT_.size() <= 0) {
            LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "initExactWeekViewData getWeekArrayFromInt by beginTime get null");
            return;
        }
        if (lT_.get(i) <= 0 || lT_.get(i2) <= 0) {
            LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "initExactWeekViewData getWeekArrayFromInt by beginTime not contains beginTime and endTime");
            return;
        }
        this.n.setSparseArrayStrDate(azi.lW_(i));
        this.n.setSparseArrayIntDate(lT_);
        this.n.e(z);
        this.e.setCloverToDateData(lT_);
        this.e.setCloverMapData(map);
    }

    private void c(Context context) {
        Object systemService = context.getSystemService("layout_inflater");
        if (!(systemService instanceof LayoutInflater)) {
            LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "initView object is null or not LayoutInflater");
            return;
        }
        View inflate = ((LayoutInflater) systemService).inflate(R.layout.health_week_calendar_group_view, this);
        this.n = (HealthWeekCalendarView) inflate.findViewById(R.id.week_calendar_view);
        this.b = (HealthViewPager) inflate.findViewById(R.id.clover_view_pager);
        this.n.setHeaderViewCanShow(this.d);
        this.n.setClickAble(this.f1924a);
        this.b.setIsScroll(this.h);
        this.b.setIsCompatibleScrollView(true);
        this.o = new ArrayList();
        HealthWeekCloverView healthWeekCloverView = new HealthWeekCloverView(this.c);
        this.e = healthWeekCloverView;
        healthWeekCloverView.setCloverMapData(null);
        this.e.setWeekCloverItemClickListener(this);
        this.o.add(this.e);
        LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "set viewpager can Scroll = ", Boolean.valueOf(this.h));
        if (this.h) {
            this.b.setOffscreenPageLimit(3);
            int b = DateFormatUtil.b(jdl.c(System.currentTimeMillis(), 1, 0));
            LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "initView nowWeekStartDate ", Integer.valueOf(b), " mJoinTime ", Integer.valueOf(this.f));
            if (b > this.f) {
                HealthWeekCloverView healthWeekCloverView2 = new HealthWeekCloverView(this.c);
                healthWeekCloverView2.setPageNumber(1);
                healthWeekCloverView2.setCloverMapData(null);
                healthWeekCloverView2.setWeekCloverItemClickListener(this);
                this.o.add(0, healthWeekCloverView2);
                this.b.addOnPageChangeListener(this);
            }
        }
        axz axzVar = new axz(context, this.o);
        this.k = axzVar;
        this.b.setAdapter(axzVar);
        this.b.setCurrentItem(this.k.getCount() - 1);
        this.n.c();
        this.n.setListener(this);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.g = true;
        int count = (this.k.getCount() - 1) - i;
        this.m = count;
        this.l = azi.j(count);
        LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "onPageSelected mWeekPosition=", Integer.valueOf(this.m), ",mPageSelectedMonday=", Integer.valueOf(this.l));
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (i == 0 && this.g) {
            this.g = false;
            LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "scroll has stop, must refresh calendarview");
            c();
            b();
            e();
        }
    }

    private void e() {
        if (this.b.getCurrentItem() == 0) {
            LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "scroll to left， mWeekPosition = ", Integer.valueOf(this.m));
            if (this.l > this.f) {
                a();
            }
        }
    }

    private void b() {
        HealthWeekCalendarView healthWeekCalendarView;
        if (this.i == null || (healthWeekCalendarView = this.n) == null) {
            return;
        }
        int smoothSelectDate = healthWeekCalendarView.getSmoothSelectDate();
        LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "onPageScrollStateChanged send request event to HealthModelActivity smoothSelectDate = ", Integer.valueOf(smoothSelectDate));
        this.i.onScrollStateIdle(smoothSelectDate, this.m);
    }

    private void c() {
        this.n.setCountPageNumber(this.m);
        this.n.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a() {
        /*
            r4 = this;
            axz r0 = r4.k
            java.util.LinkedList r0 = r0.c()
            boolean r1 = defpackage.koq.c(r0)
            java.lang.String r2 = "HealthLife_HealthWeekCalendarGroupView"
            r3 = 0
            if (r1 == 0) goto L2b
            java.lang.Object r0 = r0.poll()
            android.view.View r0 = (android.view.View) r0
            boolean r1 = r0 instanceof com.huawei.basichealthmodel.ui.view.HealthWeekCloverView
            if (r1 == 0) goto L2b
            android.view.ViewParent r1 = r0.getParent()
            if (r1 != 0) goto L2b
            java.lang.String r1 = "addEmptyWeekView has view cache, getView from viewCache"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            com.huawei.basichealthmodel.ui.view.HealthWeekCloverView r0 = (com.huawei.basichealthmodel.ui.view.HealthWeekCloverView) r0
            goto L2c
        L2b:
            r0 = r3
        L2c:
            if (r0 != 0) goto L3e
            java.lang.String r0 = "addEmptyWeekView no view cache, new HealthWeekCloverView"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r0)
            com.huawei.basichealthmodel.ui.view.HealthWeekCloverView r0 = new com.huawei.basichealthmodel.ui.view.HealthWeekCloverView
            android.content.Context r1 = r4.c
            r0.<init>(r1)
        L3e:
            int r1 = r4.m
            int r1 = r1 + 1
            r0.setPageNumber(r1)
            r0.setCloverMapData(r3)
            r0.setWeekCloverItemClickListener(r4)
            java.util.List<com.huawei.basichealthmodel.ui.view.HealthWeekCloverView> r1 = r4.o
            r2 = 0
            r1.add(r2, r0)
            axz r0 = r4.k
            r0.notifyDataSetChanged()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.basichealthmodel.ui.view.HealthWeekCalendarGroupView.a():void");
    }

    @Override // com.huawei.basichealthmodel.callback.DateSelectListener
    public void loadDayDataCallback(int i) {
        DateChangeListener dateChangeListener = this.j;
        if (dateChangeListener != null) {
            dateChangeListener.weekDateChangeCallback(i);
        }
    }

    public void d(List<ayd> list) {
        if (koq.b(this.o)) {
            LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "notifyWeekCloverView mWeekCloverViewList = null");
        } else {
            if (koq.b(list)) {
                LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "notifyWeekCloverView list form outside is null");
                return;
            }
            LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "notifyWeekCloverView begin notify the current week clover");
            this.o.get(r0.size() - 1).e(list);
        }
    }

    @Override // com.huawei.basichealthmodel.callback.WeekCloverItemClickListener
    public void onCloverItemClick(int i) {
        LogUtil.a("HealthLife_HealthWeekCalendarGroupView", "onCloverItemClick position = ", Integer.valueOf(i));
        if (!this.f1924a) {
            LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "onCloverItemClick is not support clicked event");
            return;
        }
        if (i <= 0 || i > 7) {
            LogUtil.h("HealthLife_HealthWeekCalendarGroupView", "onCloverItemClick error position");
            return;
        }
        HealthWeekCalendarView healthWeekCalendarView = this.n;
        if (healthWeekCalendarView != null) {
            healthWeekCalendarView.e(i);
        }
    }
}
