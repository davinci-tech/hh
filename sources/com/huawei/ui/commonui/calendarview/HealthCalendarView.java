package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nkw;
import defpackage.nky;
import defpackage.smr;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class HealthCalendarView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private final nky f8774a;
    private HealthMonthViewPager b;
    private View c;
    HealthCalendarLayout d;
    private HealthWeekBar e;
    private HealthWeekViewPager f;

    public interface OnCalendarSelectListener {
        void onCalendarOutOfRange(HealthCalendar healthCalendar);

        void onCalendarSelect(HealthCalendar healthCalendar, boolean z);
    }

    public interface OnInnerDateSelectedListener {
        void onMonthDateSelected(HealthCalendar healthCalendar, boolean z);

        void onWeekDateSelected(HealthCalendar healthCalendar, boolean z);
    }

    public interface OnViewChangeListener {
        void onViewChange(boolean z);
    }

    public interface OnWeekChangeListener {
        void onWeekChange(List<HealthCalendar> list);
    }

    public HealthCalendarView(Context context) {
        this(context, null);
    }

    public HealthCalendarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100133_res_0x7f0601e5);
    }

    public HealthCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(e(context, i), attributeSet, i);
        this.f8774a = new nky(context, attributeSet, i, R.style.Widget_Emui_HealthCalendarView);
        b(super.getContext());
    }

    private static Context e(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HealthCalendarView);
    }

    private void b(Context context) {
        LayoutInflater.from(context).inflate(R.layout.health_calendar_view, (ViewGroup) this, true);
        HealthWeekViewPager healthWeekViewPager = (HealthWeekViewPager) findViewById(R.id.vp_week);
        this.f = healthWeekViewPager;
        healthWeekViewPager.setup(this.f8774a);
        this.f.setIsAutoCurrentItemHeight(true);
        b();
        e();
        HealthMonthViewPager healthMonthViewPager = (HealthMonthViewPager) findViewById(R.id.vp_month);
        this.b = healthMonthViewPager;
        healthMonthViewPager.d = this.f;
        this.b.e = (HealthTextView) findViewById(R.id.sub_header);
        c();
        if (b(this.f8774a.q())) {
            nky nkyVar = this.f8774a;
            nkyVar.l = nkyVar.d();
        } else {
            nky nkyVar2 = this.f8774a;
            nkyVar2.l = nkyVar2.r();
        }
        nky nkyVar3 = this.f8774a;
        nkyVar3.f15354a = nkyVar3.l;
        this.b.setup(this.f8774a);
        this.b.setCurrentItem(this.f8774a.i);
        this.f.a(this.f8774a.d(), false);
    }

    private void e() {
        try {
            Constructor<?> constructor = this.f8774a.ad().getConstructor(Context.class);
            if (constructor.newInstance(getContext()) instanceof HealthWeekBar) {
                this.e = (HealthWeekBar) constructor.newInstance(getContext());
            }
        } catch (IllegalAccessException unused) {
            Log.e("HealthCalendarView", "IllegalAccessException in reflect call HealthWeekBar constructor");
        } catch (InstantiationException unused2) {
            Log.e("HealthCalendarView", "InstantiationException in reflect call HealthWeekBar constructor");
        } catch (NoSuchMethodException unused3) {
            Log.e("HealthCalendarView", "NoSuchMethodException in reflect call HealthWeekBar constructor");
        } catch (InvocationTargetException unused4) {
            Log.e("HealthCalendarView", "InvocationTargetException in reflect call HealthWeekBar constructor");
        }
        if (this.e == null) {
            this.e = new HealthWeekBar(getContext());
        }
        ((LinearLayout) findViewById(R.id.frameContent)).addView(this.e, 0, new ViewGroup.LayoutParams(-1, -2));
        this.e.c(this.f8774a);
        this.e.a(this.f8774a.z());
        HealthWeekBar healthWeekBar = this.e;
        healthWeekBar.measure(healthWeekBar.getMeasuredWidthAndState(), 0);
        this.f8774a.h(this.e.getMeasuredHeight());
    }

    private void c() {
        this.f8774a.c = new OnInnerDateSelectedListener() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarView.5
            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnInnerDateSelectedListener
            public void onMonthDateSelected(HealthCalendar healthCalendar, boolean z) {
                if (healthCalendar.getYear() == HealthCalendarView.this.f8774a.q().getYear() && healthCalendar.getMonth() == HealthCalendarView.this.f8774a.q().getMonth() && HealthCalendarView.this.b.getCurrentItem() != HealthCalendarView.this.f8774a.i) {
                    return;
                }
                HealthCalendarView.this.f8774a.f15354a = healthCalendar;
                if (z) {
                    HealthCalendarView.this.f8774a.l = healthCalendar;
                }
                HealthCalendarView.this.f.a(HealthCalendarView.this.f8774a.f15354a, false);
                HealthCalendarView.this.b.i();
            }

            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnInnerDateSelectedListener
            public void onWeekDateSelected(HealthCalendar healthCalendar, boolean z) {
                HealthCalendarView.this.f8774a.f15354a = healthCalendar;
                if (z || HealthCalendarView.this.f8774a.f15354a.equals(HealthCalendarView.this.f8774a.l)) {
                    HealthCalendarView.this.f8774a.l = healthCalendar;
                }
                HealthCalendarView.this.b.setCurrentItem(nkw.e(healthCalendar, HealthCalendarView.this.f8774a), false);
                HealthCalendarView.this.b.i();
            }
        };
    }

    private void b() {
        View findViewById = findViewById(R.id.titleBar);
        this.c = findViewById;
        findViewById.measure(findViewById.getMeasuredWidthAndState(), 0);
        this.f8774a.i(this.c.getMeasuredHeight());
        ImageView imageView = (ImageView) findViewById(R.id.left_btn);
        ImageView imageView2 = (ImageView) findViewById(R.id.right_btn);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HealthCalendarView.this.e(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.calendarview.HealthCalendarView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HealthCalendarView.this.c(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void b(boolean z) {
        this.c.setVisibility(z ? 8 : 0);
        if (z) {
            this.f8774a.i(0);
            return;
        }
        View view = this.c;
        view.measure(view.getMeasuredWidthAndState(), 0);
        this.f8774a.i(this.c.getMeasuredHeight());
    }

    public void a(boolean z) {
        HealthWeekBar healthWeekBar = this.e;
        if (healthWeekBar != null) {
            healthWeekBar.setVisibility(z ? 8 : 0);
            if (!z) {
                HealthWeekBar healthWeekBar2 = this.e;
                healthWeekBar2.measure(healthWeekBar2.getMeasuredWidthAndState(), 0);
                this.f8774a.h(this.e.getMeasuredHeight());
            }
        }
        if (z) {
            this.f8774a.h(0);
        }
    }

    public void setRange(HealthCalendar healthCalendar, HealthCalendar healthCalendar2) {
        if (healthCalendar.compareTo(healthCalendar2) > 0) {
            return;
        }
        this.f8774a.c(healthCalendar, healthCalendar2);
        this.f.b();
        this.b.d();
        if (!b(this.f8774a.l)) {
            nky nkyVar = this.f8774a;
            nkyVar.l = nkyVar.r();
            nky nkyVar2 = this.f8774a;
            nkyVar2.f15354a = nkyVar2.l;
        }
        this.f.e();
        this.b.h();
    }

    public void setRange(HealthCalendar healthCalendar, HealthCalendar healthCalendar2, boolean z) {
        setShowFullMonth(z);
        this.f8774a.b(true);
        setRange(healthCalendar, healthCalendar2);
        this.b.e();
    }

    void setShowFullMonth(boolean z) {
        this.f8774a.o(z);
    }

    public HealthCalendar getCurCanlendar() {
        return this.f8774a.q();
    }

    public void d(boolean z) {
        d();
        if (b(this.f8774a.q())) {
            HealthCalendar d = this.f8774a.d();
            this.f8774a.l = d;
            this.f8774a.f15354a = d;
            if (this.b.getVisibility() == 0) {
                this.b.e(z);
                this.f.a(this.f8774a.f15354a, false);
            } else {
                this.f.d(z);
            }
        }
    }

    public void c(boolean z) {
        if (this.f.getVisibility() == 0) {
            HealthWeekViewPager healthWeekViewPager = this.f;
            healthWeekViewPager.setCurrentItem(healthWeekViewPager.getCurrentItem() + 1, z);
        } else {
            HealthMonthViewPager healthMonthViewPager = this.b;
            healthMonthViewPager.setCurrentItem(healthMonthViewPager.getCurrentItem() + 1, z);
        }
    }

    public void e(boolean z) {
        if (this.f.getVisibility() == 0) {
            this.f.setCurrentItem(r0.getCurrentItem() - 1, z);
        } else {
            this.b.setCurrentItem(r0.getCurrentItem() - 1, z);
        }
    }

    public void a(HealthCalendar healthCalendar) {
        e(healthCalendar, false, true);
    }

    public void e(HealthCalendar healthCalendar, boolean z, boolean z2) {
        if (healthCalendar.isAvailable() && b(healthCalendar)) {
            if (this.f.getVisibility() == 0) {
                this.f.d(healthCalendar, z, z2);
            } else {
                this.b.a(healthCalendar, z, z2);
            }
        }
    }

    public final void setMonthViewScrollable(boolean z) {
        this.f8774a.j(z);
    }

    public final void setWeekViewScrollable(boolean z) {
        this.f8774a.k(z);
    }

    public final void setIsWeekDayReplaceDate(boolean z) {
        this.f8774a.g(z);
    }

    public final void setMarkViewSize(int i) {
        this.f8774a.g(i);
    }

    public final void setMarkTopMargin(int i) {
        this.f8774a.c(i);
    }

    public void setOnWeekChangeListener(OnWeekChangeListener onWeekChangeListener) {
        this.f8774a.o = onWeekChangeListener;
    }

    public void setOnCalendarSelectListener(OnCalendarSelectListener onCalendarSelectListener) {
        this.f8774a.d = onCalendarSelectListener;
        if (this.f8774a.d == null) {
            return;
        }
        b(this.f8774a.l);
    }

    public void setOnViewChangeListener(OnViewChangeListener onViewChangeListener) {
        this.f8774a.n = onViewChangeListener;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        if (this.f8774a == null) {
            return super.onSaveInstanceState();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", super.onSaveInstanceState());
        bundle.putSerializable("selected_calendar", this.f8774a.l);
        bundle.putSerializable("index_calendar", this.f8774a.f15354a);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            Serializable serializable = bundle.getSerializable("selected_calendar");
            if (serializable instanceof HealthCalendar) {
                this.f8774a.l = (HealthCalendar) serializable;
            }
            if (this.f8774a.d != null) {
                this.f8774a.d.onCalendarSelect(this.f8774a.l, false);
            }
            Serializable serializable2 = bundle.getSerializable("index_calendar");
            if (serializable2 instanceof HealthCalendar) {
                this.f8774a.f15354a = (HealthCalendar) serializable2;
            }
            if (this.f8774a.f15354a != null) {
                a(this.f8774a.f15354a);
            }
            j();
            super.onRestoreInstanceState(bundle.getParcelable("super"));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof HealthCalendarLayout) {
            HealthCalendarLayout healthCalendarLayout = (HealthCalendarLayout) getParent();
            this.d = healthCalendarLayout;
            this.b.c = healthCalendarLayout;
            this.f.c = this.d;
            this.d.a(this.f8774a);
            this.d.a();
        }
    }

    public final void b(Map<String, HealthCalendar> map) {
        if (this.f8774a == null || map == null || map.size() == 0) {
            return;
        }
        this.f8774a.e(map);
        this.b.b();
        this.f.d();
    }

    public final void a() {
        this.f8774a.e();
        this.b.b();
        this.f.d();
    }

    public void setWeekStart(int i) {
        if ((i == 1 || i == 2 || i == 7) && i != this.f8774a.z()) {
            this.f8774a.m(i);
            this.e.a(i);
            this.f.h();
            this.b.f();
        }
    }

    public void setDayTextSize(int i) {
        this.f8774a.a(i);
    }

    public void setMarkerViewClickable(boolean z) {
        this.f8774a.h(z);
    }

    public void setOnlyCurrentMode() {
        setDisplayMode(1);
    }

    public void setFixMode() {
        setDisplayMode(2);
    }

    private void setDisplayMode(int i) {
        if ((i == 1 || i == 2) && this.f8774a.p() != i) {
            this.f8774a.j(i);
            this.f.a();
            this.b.a();
            this.f.b();
        }
    }

    private void j() {
        this.e.a(this.f8774a.z());
        this.b.b();
        this.f.d();
    }

    public HealthCalendar getSelectedCalendar() {
        return this.f8774a.l;
    }

    protected final boolean b(HealthCalendar healthCalendar) {
        nky nkyVar = this.f8774a;
        return nkyVar != null && nkw.c(healthCalendar, nkyVar);
    }

    public void setModeOnlyWeekView() {
        this.f.setVisibility(0);
        this.b.setVisibility(8);
    }

    public void setModeOnlyMonthView() {
        this.b.setVisibility(0);
        this.f.setVisibility(8);
    }

    public void setWeekViewIsScroll(boolean z) {
        this.f.setIsScroll(z);
    }

    public void setMonthViewIsScroll(boolean z) {
        this.b.setIsScroll(z);
    }

    public void setIsSetGrayUnmarkedDate(boolean z) {
        this.f8774a.a(z);
    }

    public void setIsSetGrayFutureDate(boolean z) {
        this.f8774a.e(z);
    }

    public void setIsSelectFutureDate(boolean z) {
        this.f8774a.d(z);
    }

    public void setIsShowFutureDate(boolean z) {
        this.f8774a.i(z);
        this.b.e();
    }

    public void setRangeGray(HealthCalendar healthCalendar, HealthCalendar healthCalendar2) {
        this.f8774a.d(healthCalendar, healthCalendar2);
    }

    public void setSelectGrayDate(boolean z) {
        this.f8774a.f(z);
    }

    public final void d() {
        if (this.f8774a == null || this.b == null || this.f == null) {
            return;
        }
        Date date = new Date();
        if (this.f8774a.q().equals(new HealthCalendar(nkw.c("yyyy", date), nkw.c("MM", date), nkw.c("dd", date)))) {
            return;
        }
        this.f8774a.as();
        this.b.c();
        this.f.c();
    }

    public void setIsDrawSelected(boolean z) {
        this.f8774a.c(z);
    }

    public void setExtraSpace(int i) {
        this.f8774a.b(i);
    }

    public List<HealthCalendar> getCurrentWeekCalendars() {
        HealthWeekViewPager healthWeekViewPager = this.f;
        if (healthWeekViewPager == null) {
            return new ArrayList(0);
        }
        return healthWeekViewPager.getCurrentWeekCalendars();
    }

    public void setForbiddenSwipeNextWeek(boolean z) {
        HealthWeekViewPager healthWeekViewPager = this.f;
        if (healthWeekViewPager != null) {
            healthWeekViewPager.setIsScrollToLeft(!z);
        }
    }

    public void setForbiddenSwipePreWeek(boolean z) {
        HealthWeekViewPager healthWeekViewPager = this.f;
        if (healthWeekViewPager != null) {
            healthWeekViewPager.setIsScrollToRight(!z);
        }
    }
}
