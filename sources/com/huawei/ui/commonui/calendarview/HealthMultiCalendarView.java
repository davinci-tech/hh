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
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jcf;
import defpackage.nkw;
import defpackage.nky;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.skh;
import defpackage.smr;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* loaded from: classes6.dex */
public class HealthMultiCalendarView extends FrameLayout {
    private static final String d = "HealthMultiCalendarView";

    /* renamed from: a, reason: collision with root package name */
    private MarkDateTrigger f8782a;
    private final nky b;
    private HealthMonthRecyclerView c;
    private HealthWeekBar e;

    public HealthMultiCalendarView(Context context) {
        this(context, null);
    }

    public HealthMultiCalendarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100134_res_0x7f0601e6);
    }

    public HealthMultiCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(b(context, i), attributeSet, i);
        this.b = new nky(context, attributeSet, i, R.style.Widget_Emui_HealthMultiCalendarView);
        b(super.getContext());
    }

    private static Context b(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HealthMultiCalendarView);
    }

    private void b(Context context) {
        LayoutInflater.from(context).inflate(R.layout.health_multi_calendar_view, (ViewGroup) this, true);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameContent);
        HealthMonthRecyclerView healthMonthRecyclerView = (HealthMonthRecyclerView) frameLayout.findViewById(R.id.list);
        this.c = healthMonthRecyclerView;
        healthMonthRecyclerView.c(this.b);
        cxC_(frameLayout);
        this.b.c = new HealthCalendarView.OnInnerDateSelectedListener() { // from class: com.huawei.ui.commonui.calendarview.HealthMultiCalendarView.4
            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnInnerDateSelectedListener
            public void onWeekDateSelected(HealthCalendar healthCalendar, boolean z) {
            }

            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnInnerDateSelectedListener
            public void onMonthDateSelected(HealthCalendar healthCalendar, boolean z) {
                if (z) {
                    HealthMultiCalendarView.this.b.l = healthCalendar;
                    HealthMultiCalendarView.this.c.getAdapter().notifyDataSetChanged();
                }
            }
        };
        if (c(this.b.q())) {
            nky nkyVar = this.b;
            nkyVar.l = nkyVar.d();
        } else {
            nky nkyVar2 = this.b;
            nkyVar2.l = nkyVar2.r();
        }
        this.c.e();
        d();
    }

    private void d() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.title);
        healthTextView.setText(R$string.IDS_select_time);
        jcf.bEz_(healthTextView, nsf.h(R$string.IDS_select_time));
        if (nsn.r()) {
            healthTextView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen._2131362996_res_0x7f0a04b4));
        }
        ((HealthTextView) findViewById(R.id.icon)).setText(skh.b(getCurCanlendar().getDay()));
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.icon_parent);
        jcf.bEz_(viewGroup, nsf.b(R$string.IDS_two_parts, UnitUtil.c(getCurCanlendar().transformCalendar(), 8), nsf.h(R$string.IDS_detail_sleep_bottom_btu_day_txt)));
        boolean z = !b() || this.b.q().hasMark();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewGroup.getChildAt(i).setEnabled(z);
        }
        viewGroup.setEnabled(z);
        viewGroup.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.calendarview.HealthMultiCalendarView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HealthMultiCalendarView.this.d(true, true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void cxC_(FrameLayout frameLayout) {
        try {
            this.e = (HealthWeekBar) this.b.ad().getConstructor(Context.class).newInstance(getContext());
        } catch (IllegalAccessException unused) {
            Log.e(d, "IllegalAccessException in reflect call HealthWeekBar constructor");
        } catch (InstantiationException unused2) {
            Log.e(d, "InstantiationException in reflect call HealthWeekBar constructor");
        } catch (NoSuchMethodException unused3) {
            Log.e(d, "NoSuchMethodException in reflect call HealthWeekBar constructor");
        } catch (InvocationTargetException unused4) {
            Log.e(d, "InvocationTargetException in reflect call HealthWeekBar constructor");
        }
        if (this.e == null) {
            this.e = new HealthWeekBar(getContext());
        }
        frameLayout.addView(this.e, 1, new ViewGroup.LayoutParams(-1, -2));
        this.e.c(this.b);
        this.e.a(this.b.z());
        HealthWeekBar healthWeekBar = this.e;
        healthWeekBar.measure(healthWeekBar.getMeasuredWidthAndState(), 0);
        int measuredHeight = this.e.getMeasuredHeight();
        if (this.b.aa() == measuredHeight || !(this.c.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return;
        }
        this.b.h(measuredHeight);
        ((FrameLayout.LayoutParams) this.c.getLayoutParams()).setMargins(0, measuredHeight + getResources().getDimensionPixelSize(R.dimen._2131362853_res_0x7f0a0425), 0, 0);
    }

    public void setSelectedThemeColor(int i) {
        this.b.f(i);
    }

    public void setSelectedMarkerSize(int i) {
        this.b.l(i);
    }

    public void setItemVerticalSpace(int i) {
        this.b.e(i);
    }

    public void setRange(HealthCalendar healthCalendar, HealthCalendar healthCalendar2) {
        if (healthCalendar.compareTo(healthCalendar2) > 0) {
            return;
        }
        this.b.c(healthCalendar, healthCalendar2);
        if (!c(this.b.l)) {
            nky nkyVar = this.b;
            nkyVar.l = nkyVar.r();
        }
        this.c.d();
    }

    public HealthCalendar getCurCanlendar() {
        return this.b.q();
    }

    public void d(boolean z, boolean z2) {
        HealthCalendar q = this.b.q();
        setSelectedCalendar(q);
        b(q, z, true);
        if (!z2 || !this.b.am() || q.hasMark() || this.f8782a == null) {
            return;
        }
        c(q, z);
    }

    public void b(HealthCalendar healthCalendar, boolean z, boolean z2) {
        if (healthCalendar.isAvailable() && c(healthCalendar)) {
            this.c.d(healthCalendar, z, z2);
            if (this.b.d == null || !z2) {
                return;
            }
            this.b.d.onCalendarSelect(healthCalendar, false);
        }
    }

    private void c(HealthCalendar healthCalendar, boolean z) {
        LogUtil.a(d, "retrieveMarkAndJump, presentDay: ", healthCalendar);
        this.f8782a.retrieveMarkDate(nkw.d(healthCalendar), nkw.c(healthCalendar), new a(healthCalendar, this.b));
    }

    /* loaded from: classes9.dex */
    static class a extends UiCallback<Map<String, HealthCalendar>> {
        private HealthCalendar d;
        private WeakReference<nky> e;

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
        }

        a(HealthCalendar healthCalendar, nky nkyVar) {
            this.d = healthCalendar;
            this.e = new WeakReference<>(nkyVar);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final Map<String, HealthCalendar> map) {
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.commonui.calendarview.HealthMultiCalendarView.a.2
                @Override // java.lang.Runnable
                public void run() {
                    Map map2 = map;
                    HealthCalendar healthCalendar = map2 != null ? (HealthCalendar) map2.get(a.this.d.toString()) : null;
                    LogUtil.a(HealthMultiCalendarView.d, "get present day with marks: ", healthCalendar);
                    nky nkyVar = (nky) a.this.e.get();
                    if (healthCalendar == null || nkyVar == null || nkyVar.d == null) {
                        return;
                    }
                    nkyVar.d.onCalendarSelect(healthCalendar, true);
                }
            });
        }
    }

    public void b(HealthCalendar healthCalendar) {
        b(healthCalendar, false, true);
    }

    public void c() {
        HealthCalendar healthCalendar = this.b.l;
        if (healthCalendar.isAvailable() && c(healthCalendar)) {
            healthCalendar.setPresentDay(healthCalendar.equals(this.b.q()));
            b(this.b.l, false, true);
            if (this.b.c != null) {
                this.b.c.onMonthDateSelected(healthCalendar, false);
            }
        }
    }

    public void setOnCalendarSelectListener(HealthCalendarView.OnCalendarSelectListener onCalendarSelectListener) {
        this.b.d = onCalendarSelectListener;
        if (this.b.d == null) {
            return;
        }
        c(this.b.l);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        if (this.b == null) {
            return super.onSaveInstanceState();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", super.onSaveInstanceState());
        bundle.putSerializable("selected_calendar", this.b.l);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            Serializable serializable = bundle.getSerializable("selected_calendar");
            if (serializable instanceof HealthCalendar) {
                this.b.l = (HealthCalendar) serializable;
            }
            if (this.b.d != null) {
                this.b.d.onCalendarSelect(this.b.l, false);
            }
            if (this.b.l != null) {
                b(this.b.l);
            }
            super.onRestoreInstanceState(bundle.getParcelable("super"));
        }
    }

    public void setWeekStart(int i) {
        if ((i == 1 || i == 2 || i == 7) && i != this.b.z()) {
            this.b.m(i);
            this.e.a(i);
            a();
        }
    }

    private void a() {
        this.c.getAdapter().notifyDataSetChanged();
    }

    public HealthCalendar getSelectedCalendar() {
        return this.b.l;
    }

    public void setSelectedCalendar(HealthCalendar healthCalendar) {
        this.b.l = healthCalendar;
        this.c.getAdapter().notifyDataSetChanged();
    }

    protected final boolean c(HealthCalendar healthCalendar) {
        nky nkyVar = this.b;
        return nkyVar != null && nkw.c(healthCalendar, nkyVar);
    }

    public void setIsSetGrayUnmarkedDate(boolean z) {
        this.b.a(z);
    }

    public boolean b() {
        return this.b.am();
    }

    public void setIsShowFutureDate(boolean z) {
        this.b.i(z);
    }

    public void setExtraSpace(int i) {
        this.b.b(i);
    }

    public void setLastExtraSpace(int i) {
        this.b.d(i);
    }

    public void setMarkDateTrigger(MarkDateTrigger markDateTrigger) {
        this.f8782a = markDateTrigger;
        this.c.setMarkDateTrigger(markDateTrigger);
    }

    public void setMarkerViewClickable(boolean z) {
        this.b.h(z);
    }
}
