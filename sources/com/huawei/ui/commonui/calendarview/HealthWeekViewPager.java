package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import defpackage.nkw;
import defpackage.nky;
import java.util.List;

/* loaded from: classes6.dex */
public final class HealthWeekViewPager extends HealthViewPager {
    HealthCalendarLayout c;
    private boolean d;
    private boolean e;
    private int g;
    private nky h;

    public HealthWeekViewPager(Context context) {
        this(context, null);
    }

    public HealthWeekViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    void setup(nky nkyVar) {
        this.h = nkyVar;
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.h.o != null) {
            this.h.o.onWeekChange(getCurrentWeekCalendars());
        }
    }

    private void i() {
        this.g = nkw.c(this.h.r(), this.h.n(), this.h.z());
        setAdapter(new e());
        addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.commonui.calendarview.HealthWeekViewPager.3
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (HealthWeekViewPager.this.getVisibility() != 0) {
                    HealthWeekViewPager.this.e = false;
                    HealthWeekViewPager.this.l();
                } else {
                    if (HealthWeekViewPager.this.e) {
                        HealthWeekViewPager.this.e = false;
                        return;
                    }
                    HealthBaseWeekView healthBaseWeekView = (HealthBaseWeekView) HealthWeekViewPager.this.findViewWithTag(Integer.valueOf(i));
                    if (healthBaseWeekView != null) {
                        healthBaseWeekView.performClickCalendar(HealthWeekViewPager.this.h.l, !HealthWeekViewPager.this.e);
                        HealthWeekViewPager.this.l();
                    }
                    HealthWeekViewPager.this.e = false;
                }
            }
        });
    }

    List<HealthCalendar> getCurrentWeekCalendars() {
        List<HealthCalendar> a2 = nkw.a(this.h.f15354a, this.h);
        this.h.e(a2);
        return a2;
    }

    void b() {
        this.g = nkw.c(this.h.r(), this.h.n(), this.h.z());
        f();
    }

    @Override // com.huawei.ui.commonui.viewpager.HealthViewPager, com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.h.ap() && super.onTouchEvent(motionEvent);
    }

    @Override // com.huawei.ui.commonui.viewpager.HealthViewPager, com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.h.ap() && super.onInterceptTouchEvent(motionEvent);
    }

    void e() {
        this.d = true;
        b();
        this.d = false;
        if (getVisibility() != 0) {
            return;
        }
        this.e = true;
        HealthCalendar healthCalendar = this.h.l;
        a(healthCalendar, false);
        if (this.h.c != null) {
            this.h.c.onWeekDateSelected(healthCalendar, false);
        }
        if (this.h.d != null) {
            this.h.d.onCalendarSelect(healthCalendar, false);
        }
        int a2 = nkw.a(healthCalendar, this.h.z());
        HealthCalendarLayout healthCalendarLayout = this.c;
        if (healthCalendarLayout != null) {
            healthCalendarLayout.e(a2);
        }
    }

    void d(HealthCalendar healthCalendar, boolean z, boolean z2) {
        this.e = true;
        HealthCalendar healthCalendar2 = new HealthCalendar(healthCalendar.getYear(), healthCalendar.getMonth(), healthCalendar.getDay());
        healthCalendar2.setPresentDay(healthCalendar2.equals(this.h.q()));
        this.h.f15354a = healthCalendar2;
        this.h.l = healthCalendar2;
        a(healthCalendar2, z);
        if (this.h.c != null) {
            this.h.c.onWeekDateSelected(healthCalendar2, false);
        }
        if (this.h.d != null && z2) {
            this.h.d.onCalendarSelect(healthCalendar2, false);
        }
        int a2 = nkw.a(healthCalendar2, this.h.z());
        HealthCalendarLayout healthCalendarLayout = this.c;
        if (healthCalendarLayout != null) {
            healthCalendarLayout.e(a2);
        }
    }

    void d(boolean z) {
        this.e = true;
        int d = nkw.d(this.h.q(), this.h.r(), this.h.z()) - 1;
        if (getCurrentItem() == d) {
            this.e = false;
        }
        setCurrentItem(d, z);
        HealthBaseWeekView healthBaseWeekView = (HealthBaseWeekView) findViewWithTag(Integer.valueOf(d));
        if (healthBaseWeekView != null) {
            healthBaseWeekView.performClickCalendar(this.h.q(), false);
            healthBaseWeekView.setSelectedCalendar(this.h.q());
            healthBaseWeekView.invalidate();
        }
        if (this.h.d != null && getVisibility() == 0) {
            this.h.d.onCalendarSelect(this.h.l, false);
        }
        if (getVisibility() == 0) {
            this.h.c.onWeekDateSelected(this.h.q(), false);
        }
        int a2 = nkw.a(this.h.q(), this.h.z());
        HealthCalendarLayout healthCalendarLayout = this.c;
        if (healthCalendarLayout != null) {
            healthCalendarLayout.e(a2);
        }
    }

    void a(HealthCalendar healthCalendar, boolean z) {
        int d = nkw.d(healthCalendar, this.h.r(), this.h.z()) - 1;
        this.e = getCurrentItem() != d;
        setCurrentItem(d, z);
        HealthBaseWeekView healthBaseWeekView = (HealthBaseWeekView) findViewWithTag(Integer.valueOf(d));
        if (healthBaseWeekView != null) {
            healthBaseWeekView.setSelectedCalendar(healthCalendar);
            healthBaseWeekView.invalidate();
        }
    }

    void c() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof HealthBaseWeekView) {
                ((HealthBaseWeekView) childAt).updatePresentDate();
            }
        }
    }

    void d() {
        for (int i = 0; i < getChildCount(); i++) {
            ((HealthBaseWeekView) getChildAt(i)).update();
        }
    }

    void a() {
        for (int i = 0; i < getChildCount(); i++) {
            ((HealthBaseWeekView) getChildAt(i)).updateDisplayMode();
        }
    }

    void h() {
        if (getAdapter() == null) {
            return;
        }
        int count = getAdapter().getCount();
        int c = nkw.c(this.h.r(), this.h.n(), this.h.z());
        this.g = c;
        if (count != c) {
            this.d = true;
            getAdapter().notifyDataSetChanged();
        }
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof HealthBaseWeekView) {
                ((HealthBaseWeekView) getChildAt(i)).updateWeekStart();
            }
        }
        this.d = false;
        a(this.h.l, false);
    }

    private void f() {
        if (getAdapter() == null) {
            return;
        }
        getAdapter().notifyDataSetChanged();
    }

    /* loaded from: classes9.dex */
    class e extends HwPagerAdapter {
        private e() {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            return HealthWeekViewPager.this.g;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getItemPosition(Object obj) {
            if (HealthWeekViewPager.this.d) {
                return -2;
            }
            return super.getItemPosition(obj);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view.equals(obj);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0055  */
        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object instantiateItem(android.view.ViewGroup r7, int r8) {
            /*
                r6 = this;
                java.lang.String r0 = "HealthWeekViewPager"
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r1 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                nky r1 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.c(r1)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Class r1 = r1.ag()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r2 = 1
                java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Class<android.content.Context> r4 = android.content.Context.class
                r5 = 0
                r3[r5] = r4     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.reflect.Constructor r1 = r1.getConstructor(r3)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r4 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                android.content.Context r4 = r4.getContext()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r3[r5] = r4     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object r3 = r1.newInstance(r3)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                boolean r3 = r3 instanceof com.huawei.ui.commonui.calendarview.HealthBaseWeekView     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                if (r3 == 0) goto L52
                java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r3 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                android.content.Context r3 = r3.getContext()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r2[r5] = r3     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object r1 = r1.newInstance(r2)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthBaseWeekView r1 = (com.huawei.ui.commonui.calendarview.HealthBaseWeekView) r1     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
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
                if (r1 != 0) goto L61
                com.huawei.ui.commonui.calendarview.HealthDefaultWeekView r7 = new com.huawei.ui.commonui.calendarview.HealthDefaultWeekView
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r8 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this
                android.content.Context r8 = r8.getContext()
                r7.<init>(r8)
                return r7
            L61:
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r0 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this
                com.huawei.ui.commonui.calendarview.HealthCalendarLayout r0 = r0.c
                r1.mParentLayout = r0
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r0 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this
                nky r0 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.c(r0)
                r1.init(r0)
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r0 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this
                nky r0 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.c(r0)
                com.huawei.ui.commonui.calendarview.HealthCalendar r0 = r0.r()
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r2 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this
                nky r2 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.c(r2)
                int r2 = r2.z()
                int r3 = r8 + 1
                com.huawei.ui.commonui.calendarview.HealthCalendar r0 = defpackage.nkw.d(r0, r3, r2)
                r1.init(r0)
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
                r1.setTag(r8)
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r8 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this
                nky r8 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.c(r8)
                com.huawei.ui.commonui.calendarview.HealthCalendar r8 = r8.l
                r1.setSelectedCalendar(r8)
                com.huawei.ui.commonui.calendarview.HealthWeekViewPager r8 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.this
                nky r8 = com.huawei.ui.commonui.calendarview.HealthWeekViewPager.c(r8)
                boolean r8 = r8.af()
                r1.setMarkerViewClickable(r8)
                r7.addView(r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.calendarview.HealthWeekViewPager.e.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj instanceof HealthBaseWeekView) {
                HealthBaseWeekView healthBaseWeekView = (HealthBaseWeekView) obj;
                healthBaseWeekView.onDestroy();
                viewGroup.removeView(healthBaseWeekView);
            }
        }
    }
}
