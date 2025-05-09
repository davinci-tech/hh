package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import defpackage.nkw;
import defpackage.nky;
import health.compact.a.UnitUtil;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* loaded from: classes6.dex */
public final class HealthMonthViewPager extends HealthViewPager {
    HealthCalendarLayout c;
    HealthWeekViewPager d;
    HealthTextView e;
    private HwPagerAdapter f;
    private boolean g;
    private nky h;
    private boolean i;
    private int j;
    private int l;
    private int m;
    private int o;

    public HealthMonthViewPager(Context context) {
        this(context, null);
    }

    public HealthMonthViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    void setup(nky nkyVar) {
        this.h = nkyVar;
        a(nkyVar.q());
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.j;
        setLayoutParams(layoutParams);
        l();
    }

    private void l() {
        this.l = nkw.b(this.h);
        a aVar = new a();
        this.f = aVar;
        setAdapter(aVar);
        addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.commonui.calendarview.HealthMonthViewPager.2
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                HealthMonthViewPager.this.e(i, f);
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                HealthCalendar e = nkw.e(i, HealthMonthViewPager.this.h);
                HealthMonthViewPager healthMonthViewPager = HealthMonthViewPager.this;
                healthMonthViewPager.e(healthMonthViewPager.h.aq() ? HealthMonthViewPager.this.h.f15354a : e);
                if (HealthMonthViewPager.this.getVisibility() == 0) {
                    HealthMonthViewPager.this.h.f15354a = e;
                }
                if (HealthMonthViewPager.this.d.getVisibility() == 0) {
                    HealthMonthViewPager.this.a(e);
                    return;
                }
                if (!e.isPresentMonth()) {
                    HealthMonthViewPager.this.h.l = e;
                } else {
                    HealthMonthViewPager.this.h.l = nkw.b(e, HealthMonthViewPager.this.h);
                }
                HealthMonthViewPager.this.h.f15354a = HealthMonthViewPager.this.h.l;
                if (!HealthMonthViewPager.this.i && HealthMonthViewPager.this.h.d != null) {
                    HealthMonthViewPager.this.h.d.onCalendarSelect(HealthMonthViewPager.this.h.l, false);
                }
                HealthCalendarBaseMonthView healthCalendarBaseMonthView = (HealthCalendarBaseMonthView) HealthMonthViewPager.this.findViewWithTag(Integer.valueOf(i));
                if (healthCalendarBaseMonthView != null) {
                    int selectedIndex = healthCalendarBaseMonthView.getSelectedIndex(HealthMonthViewPager.this.h.f15354a);
                    healthCalendarBaseMonthView.mCurrentItem = selectedIndex;
                    if (selectedIndex >= 0 && HealthMonthViewPager.this.c != null) {
                        HealthMonthViewPager.this.c.b(selectedIndex);
                    }
                    healthCalendarBaseMonthView.invalidate();
                }
                HealthMonthViewPager.this.d.a(HealthMonthViewPager.this.h.f15354a, false);
                HealthMonthViewPager.this.a(e);
                HealthMonthViewPager.this.i = false;
            }
        });
    }

    @Override // com.huawei.ui.commonui.viewpager.HealthViewPager, com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.h.ai() && super.onTouchEvent(motionEvent);
    }

    @Override // com.huawei.ui.commonui.viewpager.HealthViewPager, com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.h.ai() && super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HealthCalendar healthCalendar) {
        Calendar transformCalendar = healthCalendar.transformCalendar();
        int i = this.h.aq() ? 22 : 36;
        this.e.setText(this.h.aq() ? UnitUtil.a(new Date(transformCalendar.getTimeInMillis()), i) : DateUtils.formatDateTime(getContext(), transformCalendar.getTimeInMillis(), i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, float f) {
        float f2;
        int i2;
        if (i < getCurrentItem()) {
            f2 = this.o * (1.0f - f);
            i2 = this.j;
        } else {
            f2 = this.j * (1.0f - f);
            i2 = this.m;
        }
        int i3 = (int) (f2 + (i2 * f));
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = i3;
        setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthCalendar healthCalendar) {
        if (this.c != null) {
            if (getVisibility() != 0) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = nkw.d(healthCalendar, this.h);
                setLayoutParams(layoutParams);
            }
            this.c.i();
        }
        b(healthCalendar);
    }

    void e() {
        b(this.h.f15354a);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.j;
        setLayoutParams(layoutParams);
    }

    private void b(HealthCalendar healthCalendar) {
        this.j = nkw.d(healthCalendar, this.h);
        int month = healthCalendar.getMonth();
        int year = healthCalendar.getYear();
        if (month == 1) {
            this.o = nkw.a(year - 1, 12, this.h);
            this.m = nkw.a(year, 2, this.h);
            return;
        }
        this.o = nkw.a(year, month - 1, this.h);
        if (month == 12) {
            this.m = nkw.a(year + 1, 1, this.h);
        } else {
            this.m = nkw.a(year, month + 1, this.h);
        }
    }

    void d() {
        this.l = nkw.b(this.h);
        k();
    }

    void b() {
        for (int i = 0; i < getChildCount(); i++) {
            ((HealthCalendarBaseMonthView) getChildAt(i)).update();
        }
    }

    void h() {
        this.g = true;
        d();
        this.g = false;
        if (getVisibility() != 0) {
            return;
        }
        this.i = false;
        HealthCalendar healthCalendar = this.h.l;
        int e = nkw.e(healthCalendar, this.h);
        setCurrentItem(e, false);
        c(e, healthCalendar);
        if (this.h.c != null) {
            this.h.c.onMonthDateSelected(healthCalendar, false);
        }
        if (this.h.d != null) {
            this.h.d.onCalendarSelect(healthCalendar, false);
        }
        i();
    }

    void a(HealthCalendar healthCalendar, boolean z, boolean z2) {
        this.i = true;
        HealthCalendar healthCalendar2 = new HealthCalendar(healthCalendar.getYear(), healthCalendar.getMonth(), healthCalendar.getDay());
        healthCalendar2.setPresentDay(healthCalendar2.equals(this.h.q()));
        this.h.f15354a = healthCalendar2;
        this.h.l = healthCalendar2;
        int e = nkw.e(healthCalendar2, this.h);
        if (getCurrentItem() == e) {
            this.i = false;
        }
        setCurrentItem(e, z);
        c(e, healthCalendar2);
        if (this.h.d != null && z2) {
            this.h.d.onCalendarSelect(healthCalendar2, false);
        }
        if (this.h.c != null) {
            this.h.c.onMonthDateSelected(healthCalendar2, false);
        }
        i();
    }

    public void c(int i, HealthCalendar healthCalendar) {
        HealthCalendarBaseMonthView healthCalendarBaseMonthView = (HealthCalendarBaseMonthView) findViewWithTag(Integer.valueOf(i));
        if (healthCalendarBaseMonthView != null) {
            healthCalendarBaseMonthView.setSelectedCalendar(this.h.f15354a);
            healthCalendarBaseMonthView.invalidate();
            HealthCalendarLayout healthCalendarLayout = this.c;
            if (healthCalendarLayout != null) {
                healthCalendarLayout.b(healthCalendarBaseMonthView.getSelectedIndex(this.h.f15354a));
            }
        }
        if (this.c != null) {
            this.c.e(nkw.a(healthCalendar, this.h.z()));
        }
    }

    void e(boolean z) {
        this.i = true;
        int c = nkw.c(this.h);
        if (getCurrentItem() == c) {
            this.i = false;
        }
        setCurrentItem(c, z);
        HealthCalendarBaseMonthView healthCalendarBaseMonthView = (HealthCalendarBaseMonthView) findViewWithTag(Integer.valueOf(c));
        if (healthCalendarBaseMonthView != null) {
            healthCalendarBaseMonthView.setSelectedCalendar(this.h.q());
            healthCalendarBaseMonthView.invalidate();
            HealthCalendarLayout healthCalendarLayout = this.c;
            if (healthCalendarLayout != null) {
                healthCalendarLayout.b(healthCalendarBaseMonthView.getSelectedIndex(this.h.q()));
            }
        }
        if (this.h.d == null || getVisibility() != 0) {
            return;
        }
        this.h.d.onCalendarSelect(this.h.l, false);
    }

    List<HealthCalendar> getPresentMonthCalendars() {
        HealthCalendarBaseMonthView healthCalendarBaseMonthView = (HealthCalendarBaseMonthView) findViewWithTag(Integer.valueOf(getCurrentItem()));
        if (healthCalendarBaseMonthView == null) {
            return Collections.emptyList();
        }
        return healthCalendarBaseMonthView.mItems;
    }

    void i() {
        for (int i = 0; i < getChildCount(); i++) {
            HealthCalendarBaseMonthView healthCalendarBaseMonthView = (HealthCalendarBaseMonthView) getChildAt(i);
            healthCalendarBaseMonthView.setSelectedCalendar(this.h.l);
            healthCalendarBaseMonthView.invalidate();
        }
        if (this.h.aq()) {
            e(this.h.f15354a);
        }
    }

    void a() {
        for (int i = 0; i < getChildCount(); i++) {
            HealthCalendarBaseMonthView healthCalendarBaseMonthView = (HealthCalendarBaseMonthView) getChildAt(i);
            healthCalendarBaseMonthView.updateDisplayMode();
            healthCalendarBaseMonthView.requestLayout();
        }
        a(this.h.l);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.j;
        setLayoutParams(layoutParams);
        HealthCalendarLayout healthCalendarLayout = this.c;
        if (healthCalendarLayout != null) {
            healthCalendarLayout.i();
        }
    }

    void f() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof HealthCalendarBaseMonthView) {
                HealthCalendarBaseMonthView healthCalendarBaseMonthView = (HealthCalendarBaseMonthView) getChildAt(i);
                healthCalendarBaseMonthView.updateWeekStart();
                healthCalendarBaseMonthView.requestLayout();
            }
        }
        a(this.h.l);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = this.j;
        setLayoutParams(layoutParams);
        if (this.c != null) {
            this.c.e(nkw.a(this.h.l, this.h.z()));
        }
        i();
    }

    private void k() {
        if (getAdapter() == null) {
            return;
        }
        getAdapter().notifyDataSetChanged();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager
    public void setCurrentItem(int i) {
        setCurrentItem(i, true);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager
    public void setCurrentItem(int i, boolean z) {
        if (Math.abs(getCurrentItem() - i) > 1) {
            super.setCurrentItem(i, false);
        } else {
            super.setCurrentItem(i, z);
        }
    }

    void c() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof HealthCalendarBaseMonthView) {
                ((HealthCalendarBaseMonthView) childAt).updatePresentDate();
            }
        }
    }

    /* loaded from: classes9.dex */
    final class a extends HwPagerAdapter {
        private a() {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            return HealthMonthViewPager.this.l;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getItemPosition(Object obj) {
            if (HealthMonthViewPager.this.g) {
                return -2;
            }
            return super.getItemPosition(obj);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view.equals(obj);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x006a  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0055  */
        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object instantiateItem(android.view.ViewGroup r7, int r8) {
            /*
                r6 = this;
                java.lang.String r0 = "HealthMonthViewPager"
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r1 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                nky r1 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.d(r1)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Class r1 = r1.t()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r2 = 1
                java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Class<android.content.Context> r4 = android.content.Context.class
                r5 = 0
                r3[r5] = r4     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.reflect.Constructor r1 = r1.getConstructor(r3)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r4 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                android.content.Context r4 = r4.getContext()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r3[r5] = r4     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object r3 = r1.newInstance(r3)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                boolean r3 = r3 instanceof com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                if (r3 == 0) goto L52
                java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r3 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                android.content.Context r3 = r3.getContext()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r2[r5] = r3     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object r1 = r1.newInstance(r2)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView r1 = (com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView) r1     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                goto L53
            L3b:
                java.lang.String r1 = "IllegalAccessException in reflect call HealthCalendarBaseMonthView constructor"
                android.util.Log.e(r0, r1)
                goto L52
            L41:
                java.lang.String r1 = "NoSuchMethodException in reflect call HealthCalendarBaseMonthView constructor"
                android.util.Log.e(r0, r1)
                goto L52
            L47:
                java.lang.String r1 = "InvocationTargetException in reflect call HealthCalendarBaseMonthView constructor"
                android.util.Log.e(r0, r1)
                goto L52
            L4d:
                java.lang.String r1 = "InstantiationException in reflect call HealthCalendarBaseMonthView constructor"
                android.util.Log.e(r0, r1)
            L52:
                r1 = 0
            L53:
                if (r1 != 0) goto L6a
                com.huawei.ui.commonui.calendarview.HealthDefaultMonthView r7 = new com.huawei.ui.commonui.calendarview.HealthDefaultMonthView
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r8 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this
                android.content.Context r8 = r8.getContext()
                r7.<init>(r8)
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r8 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this
                nky r8 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.d(r8)
                r7.init(r8)
                return r7
            L6a:
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r0 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this
                r1.mHealthMonthViewPager = r0
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r0 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this
                com.huawei.ui.commonui.calendarview.HealthCalendarLayout r0 = r0.c
                r1.mParentLayout = r0
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r0 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this
                nky r0 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.d(r0)
                r1.init(r0)
                java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
                r1.setTag(r0)
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r0 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this
                nky r0 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.d(r0)
                int r0 = defpackage.nkw.c(r8, r0)
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r2 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this
                nky r2 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.d(r2)
                int r8 = defpackage.nkw.d(r8, r2)
                r1.initMonthWithDate(r0, r8)
                com.huawei.ui.commonui.calendarview.HealthMonthViewPager r8 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.this
                nky r8 = com.huawei.ui.commonui.calendarview.HealthMonthViewPager.d(r8)
                com.huawei.ui.commonui.calendarview.HealthCalendar r8 = r8.l
                r1.setSelectedCalendar(r8)
                r7.addView(r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.calendarview.HealthMonthViewPager.a.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj instanceof HealthCalendarBaseView) {
                HealthCalendarBaseView healthCalendarBaseView = (HealthCalendarBaseView) obj;
                healthCalendarBaseView.onDestroy();
                viewGroup.removeView(healthCalendarBaseView);
            }
        }
    }
}
