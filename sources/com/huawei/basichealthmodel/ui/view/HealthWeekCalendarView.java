package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.callback.DateSelectListener;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.azi;
import defpackage.jcf;
import defpackage.nsf;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class HealthWeekCalendarView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f1925a;
    private int b;
    private HealthTextView c;
    private boolean d;
    private int e;
    private boolean f;
    private boolean g;
    private final SparseArray<View> h;
    private SparseArray<View> i;
    private int j;
    private SparseArray<HealthTextView> k;
    private SparseArray<String> l;
    private SparseIntArray m;
    private DateSelectListener n;
    private int o;
    private String[] p;
    private final String[] t;

    public HealthWeekCalendarView(Context context) {
        this(context, null);
    }

    public HealthWeekCalendarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HealthWeekCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = new SparseArray<>();
        this.m = new SparseIntArray();
        this.h = new SparseArray<>(7);
        this.k = new SparseArray<>();
        this.i = new SparseArray<>();
        this.p = new String[]{nsf.h(R$string.IDS_report_sunday), nsf.h(R$string.IDS_report_monday), nsf.h(R$string.IDS_report_thuesday), nsf.h(R$string.IDS_report_wedesday), nsf.h(R$string.IDS_report_thursday), nsf.h(R$string.IDS_report_friday), nsf.h(R$string.IDS_report_saturday)};
        this.t = new String[]{nsf.h(R$string.IDS_sunday), nsf.h(R$string.IDS_monday), nsf.h(R$string.IDS_tuesday), nsf.h(R$string.IDS_wednesday), nsf.h(R$string.IDS_thursday), nsf.h(R$string.IDS_friday), nsf.h(R$string.IDS_saturday)};
        this.d = true;
        this.g = true;
        b();
        d();
    }

    public void setHeaderViewCanShow(boolean z) {
        this.g = z;
    }

    public void setClickAble(boolean z) {
        this.d = z;
    }

    public void setListener(DateSelectListener dateSelectListener) {
        this.n = dateSelectListener;
    }

    public void setSparseArrayStrDate(SparseArray<String> sparseArray) {
        this.l = sparseArray;
    }

    public void setCountPageNumber(int i) {
        this.e = i;
        this.l = azi.lU_(i);
        this.m = azi.lL_(this.e);
        e();
    }

    public void setSparseArrayIntDate(SparseIntArray sparseIntArray) {
        this.m = sparseIntArray;
        e();
    }

    private void d() {
        HealthTextView healthTextView = this.c;
        if (healthTextView == null) {
            ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "initData mHeaderDataTextView is null");
            return;
        }
        healthTextView.setText(UnitUtil.a(azi.o(), 22));
        int v = azi.v();
        this.b = v;
        this.f1925a = v;
        this.l = azi.lU_(0);
        this.m = azi.lL_(0);
        e();
        int t = azi.t();
        this.j = t;
        if (t == 0) {
            this.j = DateFormatUtil.b(System.currentTimeMillis());
        }
        this.f = LanguageUtil.h(BaseApplication.e());
    }

    private void b() {
        View cKr_ = nsf.cKr_(BaseApplication.e(), R.layout.item_week_calendar_header_view, this);
        if (cKr_ == null) {
            LogUtil.h("HealthLife_HealthWeekCalendarView", "initView view is null");
        } else {
            lB_(cKr_);
        }
    }

    private void lB_(View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.header_date_view);
        this.c = healthTextView;
        azi.e(healthTextView, R.dimen._2131362906_res_0x7f0a045a, 1.2f);
        setWeekCalendarLayout(view);
    }

    private void setWeekCalendarLayout(View view) {
        this.h.put(2, view.findViewById(R.id.week_day_layout_monday));
        this.h.put(3, view.findViewById(R.id.week_day_layout_thuesday));
        this.h.put(4, view.findViewById(R.id.week_day_layout_wedesday));
        this.h.put(5, view.findViewById(R.id.week_day_layout_thursday));
        this.h.put(6, view.findViewById(R.id.week_day_layout_friday));
        this.h.put(7, view.findViewById(R.id.week_day_layout_saturday));
        this.h.put(1, view.findViewById(R.id.week_day_layout_sunday));
        this.k.put(2, (HealthTextView) view.findViewById(R.id.week_day_monday));
        this.k.put(3, (HealthTextView) view.findViewById(R.id.week_day_tuesday));
        this.k.put(4, (HealthTextView) view.findViewById(R.id.week_day_wednesday));
        this.k.put(5, (HealthTextView) view.findViewById(R.id.week_day_thursday));
        this.k.put(6, (HealthTextView) view.findViewById(R.id.week_day_friday));
        this.k.put(7, (HealthTextView) view.findViewById(R.id.week_day_saturday));
        this.k.put(1, (HealthTextView) view.findViewById(R.id.week_day_sunday));
        this.i.put(2, view.findViewById(R.id.week_day_line_monday));
        this.i.put(3, view.findViewById(R.id.week_day_line_thuesday));
        this.i.put(4, view.findViewById(R.id.week_day_line_wedesday));
        this.i.put(5, view.findViewById(R.id.week_day_line_thursday));
        this.i.put(6, view.findViewById(R.id.week_day_line_friday));
        this.i.put(7, view.findViewById(R.id.week_day_line_saturday));
        this.i.put(1, view.findViewById(R.id.week_day_line_sunday));
        view.findViewById(R.id.week_day_layout_monday).setOnClickListener(this);
        view.findViewById(R.id.week_day_layout_thuesday).setOnClickListener(this);
        view.findViewById(R.id.week_day_layout_wedesday).setOnClickListener(this);
        view.findViewById(R.id.week_day_layout_thursday).setOnClickListener(this);
        view.findViewById(R.id.week_day_layout_friday).setOnClickListener(this);
        view.findViewById(R.id.week_day_layout_saturday).setOnClickListener(this);
        view.findViewById(R.id.week_day_layout_sunday).setOnClickListener(this);
    }

    private void b(int i) {
        SparseIntArray sparseIntArray;
        this.c.setText(this.l.get(i));
        LogUtil.h("HealthLife_HealthWeekCalendarView", "updateHeaderDataView tv = ", this.l.get(i));
        if (this.n == null || (sparseIntArray = this.m) == null) {
            return;
        }
        int size = sparseIntArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            int keyAt = this.m.keyAt(i2);
            if (this.m.get(keyAt) == i) {
                this.n.loadDayDataCallback(keyAt);
                return;
            }
        }
    }

    private void j() {
        int i = this.m.get(this.j);
        int i2 = this.b;
        if (i > i2) {
            HealthTextView healthTextView = this.k.get(i2);
            if (healthTextView != null) {
                healthTextView.setTypeface(nsk.cKP_());
                healthTextView.setTextColor(nsf.c(R$color.textColorSecondary));
                setAccessibilitySelected(false);
            }
            View view = this.i.get(this.b);
            if (view != null) {
                view.setVisibility(4);
            }
            this.b = i;
        }
        this.c.setText(this.l.get(this.b));
        HealthTextView healthTextView2 = this.k.get(this.b);
        View view2 = this.i.get(this.b);
        lC_(healthTextView2, view2);
        if (view2 == null) {
            ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "smoothToMaxLeft lineView is null");
        } else {
            view2.setVisibility(0);
        }
    }

    private void lC_(HealthTextView healthTextView, View view) {
        if (healthTextView == null || view == null) {
            return;
        }
        healthTextView.setTypeface(nsk.cKO_());
        healthTextView.setTextColor(getResources().getColor(R$color.colorAccent));
        setAccessibilitySelected(true);
        if (TextUtils.isEmpty(healthTextView.getText())) {
            return;
        }
        String obj = healthTextView.getText().toString();
        Rect rect = new Rect();
        healthTextView.getPaint().getTextBounds(obj, 0, obj.length(), rect);
        int width = rect.width();
        if (width > nsn.c(BaseApplication.e(), 16.0f)) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = width;
            view.setLayoutParams(layoutParams);
        }
    }

    public void e(boolean z) {
        this.c.setVisibility(this.g ? 0 : 8);
        this.c.setText(this.l.get(this.b));
        if (z) {
            return;
        }
        LogUtil.a("HealthLife_HealthWeekCalendarView", "updateWeekViewWithData is not NowWeek");
        this.i.get(this.b).setVisibility(4);
        HealthTextView healthTextView = this.k.get(this.b);
        healthTextView.setTypeface(nsk.cKP_());
        healthTextView.setTextColor(getResources().getColor(R$color.textColorSecondary));
        setAccessibilitySelected(false);
        int i = this.b - 1;
        if (i >= 0) {
            String[] strArr = this.p;
            if (i <= strArr.length - 1) {
                healthTextView.setText(strArr[i]);
            }
        }
    }

    public void c() {
        h();
        o();
        f();
    }

    private void o() {
        if (this.e == 0) {
            m();
        } else {
            g();
        }
    }

    private void h() {
        HealthTextView healthTextView = this.c;
        if (healthTextView == null) {
            ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "setHeaderDataTextView mHeaderDataTextView is null");
            return;
        }
        healthTextView.setVisibility(this.g ? 0 : 8);
        if (this.e == 0) {
            i();
        }
        this.c.setText(this.l.get(this.b));
    }

    private void g() {
        HealthTextView healthTextView = this.k.get(this.f1925a);
        int i = this.f1925a - 1;
        if (i >= 0) {
            String[] strArr = this.p;
            if (i <= strArr.length - 1) {
                healthTextView.setText(strArr[i]);
            }
        }
        if (this.m.get(this.j) > 0) {
            j();
            return;
        }
        HealthTextView healthTextView2 = this.k.get(this.b);
        int i2 = this.b - 1;
        if (i2 >= 0) {
            String[] strArr2 = this.p;
            if (i2 <= strArr2.length - 1 && healthTextView2 != null) {
                healthTextView2.setText(strArr2[i2]);
            }
        }
        View view = this.i.get(this.b);
        lC_(healthTextView2, view);
        if (view == null) {
            ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "updateMultiPages lineView is null");
        } else {
            view.setVisibility(0);
        }
    }

    private void m() {
        HealthTextView healthTextView = this.k.get(this.f1925a);
        if (this.f && healthTextView != null) {
            healthTextView.setText(com.huawei.ui.commonui.R$string.IDS_hwh_home_group_today);
        }
        int i = this.b;
        int i2 = this.f1925a;
        if (i == i2) {
            LogUtil.a("HealthLife_HealthWeekCalendarView", "updateWeekView mIndex = mCurrentIndex ", Integer.valueOf(i2));
            View view = this.i.get(this.b);
            lC_(healthTextView, view);
            if (view == null) {
                ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "updateOnePage lineView is null");
            } else {
                view.setVisibility(0);
            }
        }
    }

    private void f() {
        SparseIntArray sparseIntArray = this.m;
        if (sparseIntArray == null) {
            return;
        }
        int size = sparseIntArray.size();
        for (int i = 0; i < size; i++) {
            int keyAt = this.m.keyAt(i);
            if (this.m.get(keyAt) == this.b) {
                this.o = keyAt;
                return;
            }
        }
    }

    public int getSmoothSelectDate() {
        return this.o;
    }

    private void i() {
        int i = this.b;
        if (i > this.f1925a) {
            View view = this.i.get(i);
            if (view != null) {
                view.setVisibility(4);
            }
            HealthTextView healthTextView = this.k.get(this.b);
            if (healthTextView != null) {
                healthTextView.setTypeface(nsk.cKP_());
                healthTextView.setTextColor(nsf.c(R$color.textColorSecondary));
                setAccessibilitySelected(false);
            }
            this.b = this.f1925a;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.d) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.week_day_layout_monday) {
            e(2);
        } else if (id == R.id.week_day_layout_thuesday) {
            e(3);
        } else if (id == R.id.week_day_layout_wedesday) {
            e(4);
        } else if (id == R.id.week_day_layout_thursday) {
            e(5);
        } else if (id == R.id.week_day_layout_friday) {
            e(6);
        } else if (id == R.id.week_day_layout_saturday) {
            e(7);
        } else if (id == R.id.week_day_layout_sunday) {
            e(1);
        } else {
            LogUtil.h("HealthLife_HealthWeekCalendarView", "can not find click id");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e(int i) {
        int i2;
        LogUtil.a("HealthLife_HealthWeekCalendarView", "notifyViewChange clicked index = ", Integer.valueOf(i));
        if (this.e == 0 && (i2 = this.f1925a) < i) {
            LogUtil.a("HealthLife_HealthWeekCalendarView", "future date, can not clickable mCurrentIndex = ", Integer.valueOf(i2));
            return;
        }
        if (this.m.get(this.j) > 0) {
            int i3 = this.m.get(this.j);
            LogUtil.h("HealthLife_HealthWeekCalendarView", "the beginTime index = ", Integer.valueOf(i3));
            if (i < i3) {
                LogUtil.h("HealthLife_HealthWeekCalendarView", "past date, can not clickable index = ", Integer.valueOf(i));
                return;
            }
        }
        int i4 = this.b;
        if (i4 != i) {
            this.i.get(i4).setVisibility(4);
            HealthTextView healthTextView = this.k.get(this.b);
            healthTextView.setTypeface(nsk.cKP_());
            healthTextView.setTextColor(getResources().getColor(R$color.textColorSecondary));
            setAccessibilitySelected(false);
            this.b = i;
            View view = this.i.get(i);
            HealthTextView healthTextView2 = this.k.get(i);
            if (this.e == 0) {
                if (this.f1925a == i) {
                    if (this.f) {
                        healthTextView2.setText(com.huawei.ui.commonui.R$string.IDS_hwh_home_group_today);
                    }
                    lC_(healthTextView2, view);
                    view.setVisibility(0);
                } else {
                    healthTextView2.setText(this.p[i - 1]);
                }
            }
            b(i);
        }
        HealthTextView healthTextView3 = this.k.get(i);
        View view2 = this.i.get(i);
        lC_(healthTextView3, view2);
        view2.setVisibility(0);
    }

    private void e() {
        SparseIntArray sparseIntArray = this.m;
        if (sparseIntArray == null || this.t == null) {
            ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "initAccessibility mSparseArrayIntDate ", sparseIntArray, " mWeekNameContentDescription ", this.t);
            return;
        }
        int size = sparseIntArray.size();
        int b = DateFormatUtil.b(System.currentTimeMillis());
        int length = this.t.length;
        String h = nsf.h(com.huawei.ui.commonui.R$string.IDS_hwh_home_group_today);
        for (int i = 0; i < size; i++) {
            int keyAt = this.m.keyAt(i);
            View view = this.h.get(this.m.get(keyAt));
            jcf.bEE_(view, keyAt <= b ? 1 : 2);
            if (keyAt == b && this.f) {
                jcf.bEB_(view, h, this.d);
            } else if (i < length) {
                jcf.bEB_(view, this.t[i], this.d);
            }
        }
    }

    private void setAccessibilitySelected(boolean z) {
        CharSequence text;
        if (this.d) {
            if (this.h == null) {
                ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "setAccessibilitySelected mLayoutArray is null");
                return;
            }
            CharSequence[] charSequenceArr = this.t;
            if (charSequenceArr == null || (this.f && this.e == 0 && this.f1925a == this.b)) {
                HealthTextView healthTextView = this.k.get(this.b);
                if (healthTextView == null) {
                    ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "setAccessibilitySelected textView is null");
                    return;
                }
                text = healthTextView.getText();
            } else {
                int i = this.b - 1;
                if (i < 0 || i >= charSequenceArr.length) {
                    ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "setAccessibilitySelected index ", Integer.valueOf(i), " mWeekNameContentDescription ", this.t);
                    return;
                }
                text = charSequenceArr[i];
            }
            a();
            View view = this.h.get(this.b);
            if (z) {
                jcf.bEL_(view, text, true, false, null);
            } else {
                jcf.bEJ_(view, text, true);
            }
        }
    }

    private void a() {
        SparseArray<View> sparseArray = this.h;
        if (sparseArray == null || this.t == null) {
            ReleaseLogUtil.a("HealthLife_HealthWeekCalendarView", "resetAccessibilitySelected mLayoutArray ", sparseArray, " mWeekNameContentDescription ", this.t);
            return;
        }
        int size = sparseArray.size();
        int length = this.t.length;
        boolean z = this.f && this.e == 0;
        String h = nsf.h(com.huawei.ui.commonui.R$string.IDS_hwh_home_group_today);
        for (int i = 0; i < size; i++) {
            int keyAt = this.h.keyAt(i);
            View view = this.h.get(keyAt);
            if (z && this.f1925a == keyAt) {
                jcf.bEJ_(view, h, false);
            } else if (length != 0 && i < length) {
                jcf.bEJ_(view, this.t[i], false);
            }
        }
    }
}
