package com.huawei.uikit.hwdatepicker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker;
import com.huawei.uikit.hwcheckbox.widget.HwCheckBox;
import com.huawei.uikit.hwdatepicker.R$style;
import com.huawei.uikit.hwlunar.utils.HwLunarCalendar;
import defpackage.skj;
import defpackage.sli;
import defpackage.slj;
import defpackage.slt;
import defpackage.slv;
import defpackage.smp;
import defpackage.smr;
import defpackage.sms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes7.dex */
public class HwDatePicker extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f10633a;
    private String aa;
    private String[] ab;
    private String[] ac;
    private String[] ad;
    private List<HwAdvancedNumberPicker> ae;
    private Context af;
    private String[] ag;
    private int ah;
    private int ai;
    private boolean aj;
    private LinearLayout ak;
    private boolean al;
    private float am;
    private LinearLayout an;
    private LinearLayout ao;
    private LinearLayout ap;
    private Locale aq;
    private HwCheckBox ar;
    private OnDateChangedListener as;
    private int at;
    private String[] au;
    private GregorianCalendar av;
    private GregorianCalendar aw;
    private String[] ax;
    protected HwAdvancedNumberPicker b;
    private GregorianCalendar ba;
    private GregorianCalendar bb;
    private int bc;
    private int c;
    protected HwAdvancedNumberPicker d;
    protected HwAdvancedNumberPicker e;
    private boolean f;
    private boolean g;
    private int h;
    private Drawable i;
    private boolean j;
    private String k;
    private boolean l;
    private HwLunarCalendar m;
    private boolean n;
    private boolean o;
    private slt p;
    private String[] q;
    private String r;
    private String[] s;
    private String t;
    private String[] u;
    private String[] v;
    private String[] w;
    private String[] x;
    private String[] y;
    private String z;

    public interface OnDateChangedListener {
        void onDateChanged(HwDatePicker hwDatePicker, int i, int i2, int i3, GregorianCalendar gregorianCalendar);
    }

    static class a extends View.BaseSavedState {
        public static final Parcelable.Creator<a> CREATOR = new c();

        /* renamed from: a, reason: collision with root package name */
        private final int f10634a;
        private final int b;
        private final int c;

        static final class c implements Parcelable.Creator<a> {
            c() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public a[] newArray(int i) {
                return new a[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: ecc_, reason: merged with bridge method [inline-methods] */
            public a createFromParcel(Parcel parcel) {
                return new a(parcel, null);
            }
        }

        /* synthetic */ a(Parcel parcel, e eVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel != null) {
                super.writeToParcel(parcel, i);
                parcel.writeInt(this.f10634a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.c);
            }
        }

        /* synthetic */ a(Parcelable parcelable, int i, int i2, int i3, e eVar) {
            this(parcelable, i, i2, i3);
        }

        private a(Parcelable parcelable, int i, int i2, int i3) {
            super(parcelable);
            this.f10634a = i;
            this.b = i2;
            this.c = i3;
        }

        private a(Parcel parcel) {
            super(parcel);
            this.f10634a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
        }
    }

    class b implements HwAdvancedNumberPicker.OnValueChangeListener {
        b() {
        }

        @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnValueChangeListener
        public void onValueChange(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, int i2) {
            HwDatePicker.this.e(hwAdvancedNumberPicker, i, i2);
            HwDatePicker hwDatePicker = HwDatePicker.this;
            hwDatePicker.c(hwDatePicker.av.get(1), HwDatePicker.this.av.get(2), HwDatePicker.this.av.get(5));
            HwDatePicker.this.af();
            HwDatePicker.this.v();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HwDatePicker.this.ar.setChecked(!HwDatePicker.this.ar.isChecked());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class d implements CompoundButton.OnCheckedChangeListener {
        d() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            HwDatePicker.this.f = !z;
            if (skj.b(HwDatePicker.this.af)) {
                HwDatePicker.this.s();
            }
            HwDatePicker.this.af();
            HwDatePicker.this.v();
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    class e implements HwAdvancedNumberPicker.OnColorChangeListener {
        e() {
        }

        @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnColorChangeListener
        public void onColorChange(HwAdvancedNumberPicker hwAdvancedNumberPicker) {
        }
    }

    public HwDatePicker(Context context) {
        this(context, null);
    }

    private void aa() {
        if (!skj.b(this.af) || !this.g) {
            this.ap.setVisibility(8);
        }
        if (!this.al) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
            layoutParams.weight = 3.0f;
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1);
            layoutParams2.weight = 2.0f;
            this.e.setLayoutParams(layoutParams);
            this.d.setLayoutParams(layoutParams2);
            this.b.setLayoutParams(layoutParams2);
        }
        af();
    }

    private void ab() {
        this.ak.removeAllViews();
        char[] e2 = sli.e(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd"));
        int length = e2.length;
        for (char c2 : e2) {
            if (c2 == 'M') {
                this.ak.addView(this.d);
            } else if (c2 == 'd') {
                this.ak.addView(this.b);
            } else {
                if (c2 != 'y') {
                    Log.e("HwDatePicker", "reorderSpinners: ");
                    return;
                }
                this.ak.addView(this.e);
            }
        }
        ebR_(this.ak.getChildAt(0), this.ak.getChildAt(1), this.ak.getChildAt(length - 1));
    }

    private void ac() {
        e eVar = new e();
        this.b.setOnColorChangeListener(eVar);
        this.d.setOnColorChangeListener(eVar);
        this.e.setOnColorChangeListener(eVar);
    }

    private void ad() {
        b bVar = new b();
        this.b.setOnValueChangedListener(bVar);
        this.d.setOnValueChangedListener(bVar);
        this.e.setOnValueChangedListener(bVar);
    }

    private void ae() {
        if (this.n) {
            ag();
        } else {
            ak();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        if (y()) {
            if (this.f) {
                f();
            } else {
                if (!this.o) {
                    this.p = slt.d(this.af);
                    this.m = new HwLunarCalendar(this.af);
                    this.o = true;
                }
                ae();
            }
            this.e.postInvalidate();
            this.d.postInvalidate();
            this.b.postInvalidate();
        }
    }

    private void ag() {
        GregorianCalendar e2 = slt.e();
        GregorianCalendar b2 = slt.b();
        boolean c2 = c(e2);
        boolean e3 = e(b2);
        z();
        String string = this.af.getString(R.string._2130851368_res_0x7f023628);
        String string2 = this.af.getString(R.string._2130851369_res_0x7f023629);
        if (string.equals(this.r)) {
            this.r = string2;
        }
        String string3 = this.af.getString(R.string._2130851481_res_0x7f023699);
        String string4 = this.af.getString(R.string._2130851482_res_0x7f02369a);
        if (this.r.length() == 3 && string3.equals(this.r.substring(0, 1))) {
            this.r = this.r.replace(string3, string4);
        }
        int intValue = this.p.g().get(this.k).intValue();
        String str = this.z;
        if (str == null || !str.equals(this.k)) {
            this.p.a(intValue);
        }
        Map<String, Integer> a2 = this.p.a();
        int intValue2 = a2 != null ? a2.get(this.k).intValue() : 0;
        e(intValue);
        int d2 = slv.d(this.r, this.v);
        this.y = slj.e(d2, this.v, this.x, this.w);
        t();
        int d3 = slv.d(this.t, this.u);
        this.ad = slj.e(d3, this.u, this.ab, this.ac);
        int d4 = d(intValue2, c2, e3);
        int a3 = a(d2, c2, e3);
        int c3 = c(d3, c2, e3);
        String[] strArr = (String[]) this.q.clone();
        e(d4, strArr);
        c(this.e, d4, strArr, true);
        String[] e4 = e(string, string2, string3, string4);
        this.d.setValue(a3);
        c(this.b, c3, e4, true);
        this.z = this.k;
        this.aa = this.k + this.r;
    }

    private void ah() {
        int i = this.bb.get(1);
        int i2 = this.bb.get(2);
        int i3 = this.bb.get(5);
        if (i == this.aw.get(1) && i2 == this.aw.get(2)) {
            g(i3);
        } else if (i == this.ba.get(1) && i2 == this.ba.get(2)) {
            j(i3);
        } else {
            m();
        }
        this.d.setDisplayedValues((String[]) Arrays.copyOfRange(this.ax, this.d.getMinValue(), this.d.getMaxValue() + 1));
        this.b.setDisplayedValues((String[]) Arrays.copyOfRange(this.au, this.b.getMinValue() - 1, this.b.getMaxValue()));
    }

    private void ai() {
        int i;
        this.av.clear();
        this.av.set(1, 0, 1);
        setMinDate(this.av.getTimeInMillis());
        if (this.n) {
            i = 5000;
        } else {
            i = this.bc + 1;
            this.f10633a = i;
        }
        this.av.clear();
        this.av.set(i, 11, 31);
        setMaxDate(this.av.getTimeInMillis());
    }

    private void aj() {
        int i;
        this.q = this.p.c();
        int i2 = this.f10633a;
        String[] strArr = new String[i2 - 1899];
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = i2 - 1900;
            if (i4 >= i) {
                break;
            }
            strArr[i4] = this.q[i4 + 2];
            i4++;
        }
        strArr[i] = "-- --";
        this.v = slv.d;
        String[] strArr2 = slv.e;
        this.u = strArr2;
        this.ad = strArr2;
        this.e.setDisplayedValues(null);
        this.e.setMinValue(1900);
        this.e.setMaxValue(this.f10633a);
        this.e.setDisplayedValues(strArr);
        this.e.setValue(i2 + 1);
        this.e.setWrapSelectorWheel(false);
        String str = this.r;
        int i5 = 0;
        while (true) {
            String[] strArr3 = this.v;
            if (i5 >= strArr3.length || str.equals(strArr3[i5])) {
                break;
            } else {
                i5++;
            }
        }
        this.ai = i5 + 1;
        this.d.setDisplayedValues(null);
        this.d.setMinValue(1);
        this.d.setMaxValue(this.v.length);
        this.d.setValue(this.ai);
        this.d.setDisplayedValues(this.v);
        this.d.setWrapSelectorWheel(true);
        String str2 = this.t;
        while (true) {
            String[] strArr4 = this.u;
            if (i3 >= strArr4.length || str2.equals(strArr4[i3])) {
                break;
            } else {
                i3++;
            }
        }
        this.ah = i3 + 1;
        this.b.setDisplayedValues(null);
        this.b.setMinValue(1);
        this.b.setMaxValue(this.u.length);
        this.b.setValue(this.ah);
        this.b.setDisplayedValues(this.u);
        this.b.setWrapSelectorWheel(true);
    }

    private void ak() {
        z();
        String string = this.af.getString(R.string._2130851368_res_0x7f023628);
        String string2 = this.af.getString(R.string._2130851369_res_0x7f023629);
        if (string.equals(this.r)) {
            this.r = string2;
        }
        String string3 = this.af.getString(R.string._2130851481_res_0x7f023699);
        String string4 = this.af.getString(R.string._2130851482_res_0x7f02369a);
        if (this.r.length() == 3 && string3.equals(this.r.substring(0, 1))) {
            this.r = this.r.replace(string3, string4);
        }
        int intValue = this.p.g().get(this.k).intValue();
        if (this.l) {
            h(intValue);
        } else {
            aj();
        }
    }

    private boolean e(int i, int i2, int i3) {
        return i == i3 && i2 == 0;
    }

    private void f() {
        if (this.n) {
            i();
        } else {
            l();
        }
    }

    private String getShowString() {
        if (this.f) {
            return DateUtils.formatDateTime(this.af, this.bb.getTimeInMillis(), this.l ? 20 : 24);
        }
        if (this.l) {
            return getWholeLunarStrings();
        }
        return this.r + this.t;
    }

    private String getWholeLunarStrings() {
        String str = this.k;
        if (str == null) {
            return "";
        }
        String[] split = str.split("年");
        if (split.length != 2) {
            return "";
        }
        return split[1] + "年" + this.r + this.t;
    }

    private void i() {
        ah();
        this.e.setDisplayedValues(null);
        this.e.setMinValue(this.aw.get(1));
        this.e.setMaxValue(this.ba.get(1));
        this.e.setWrapSelectorWheel(false);
        this.e.setValue(this.bb.get(1));
        this.d.setValue(this.bb.get(2));
        this.b.setValue(this.bb.get(5));
    }

    private boolean j(int i, int i2, int i3) {
        return i == 0 && i2 == i3;
    }

    private void k() {
        ah();
        this.e.setDisplayedValues(null);
        this.e.setMinValue(this.aw.get(1));
        this.e.setMaxValue(this.f10633a);
        String[] displayedValues = this.e.getDisplayedValues();
        displayedValues[displayedValues.length - 1] = "-- --";
        this.e.setDisplayedValues(displayedValues);
        this.e.setWrapSelectorWheel(false);
        this.e.setValue(this.bb.get(1));
        this.d.setValue(this.bb.get(2));
        this.b.setValue(this.bb.get(5));
    }

    private void l() {
        if (this.l) {
            k();
        } else {
            o();
        }
    }

    private void m() {
        this.b.setDisplayedValues(null);
        this.b.setMinValue(1);
        this.b.setMaxValue(this.bb.getActualMaximum(5));
        this.b.setWrapSelectorWheel(true);
        this.d.setDisplayedValues(null);
        this.d.setMinValue(0);
        this.d.setMaxValue(11);
        this.d.setWrapSelectorWheel(true);
    }

    private void o() {
        this.e.setDisplayedValues(null);
        this.e.setMinValue(this.aw.get(1));
        this.e.setMaxValue(this.f10633a);
        String[] displayedValues = this.e.getDisplayedValues();
        displayedValues[displayedValues.length - 1] = "-- --";
        this.e.setDisplayedValues(displayedValues);
        this.e.setWrapSelectorWheel(false);
        this.d.setDisplayedValues(null);
        this.d.setDisplayedValues((String[]) Arrays.copyOfRange(this.ax, this.d.getMinValue(), this.d.getMaxValue() + 1));
        this.b.setDisplayedValues(null);
        int[] iArr = slj.e;
        int i = iArr[this.ai % iArr.length];
        String[] strArr = new String[i];
        System.arraycopy(this.au, 0, strArr, 0, i);
        this.b.setMinValue(1);
        this.b.setMaxValue(i);
        this.b.setDisplayedValues(strArr);
        this.e.setValue(this.f10633a);
        this.d.setValue(this.ai);
        this.b.setValue(this.ah);
    }

    private void q() {
        this.bb.setTimeInMillis(GregorianCalendar.getInstance().getTimeInMillis());
        this.bc = this.bb.get(1);
        ai();
        c(this.bb.get(1), this.bb.get(2), this.bb.get(5), (OnDateChangedListener) null);
    }

    private void r() {
        this.s = this.p.f();
        this.q = this.p.c();
        this.v = this.l ? slv.c(this.p.i().get(this.k)) : slv.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (this.f) {
            this.b.a(getResources().getString(R.string._2130850814_res_0x7f0233fe), true);
        } else {
            this.b.a("", false);
        }
    }

    private void setCurrentLocale(Locale locale) {
        if (locale.equals(this.aq)) {
            return;
        }
        this.aq = locale;
        this.av = c(this.av, locale);
        this.aw = c(this.aw, locale);
        this.ba = c(this.ba, locale);
        this.bb = c(this.bb, locale);
        int actualMaximum = this.av.getActualMaximum(2) + 1;
        this.at = actualMaximum;
        this.ax = new String[actualMaximum];
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(5, 1);
        int i = 0;
        for (int i2 = 0; i2 < this.at; i2++) {
            gregorianCalendar.set(2, i2);
            this.ax[i2] = DateUtils.formatDateTime(this.af, gregorianCalendar.getTimeInMillis(), 65592);
        }
        this.au = new String[31];
        String bestDateTimePattern = DateFormat.getBestDateTimePattern(locale, FitRunPlayAudio.PLAY_TYPE_D);
        Object clone = this.av.clone();
        if (clone instanceof GregorianCalendar) {
            GregorianCalendar gregorianCalendar2 = (GregorianCalendar) clone;
            gregorianCalendar2.set(2, 0);
            while (i < 31) {
                int i3 = i + 1;
                gregorianCalendar2.set(5, i3);
                this.au[i] = DateFormat.format(bestDateTimePattern, gregorianCalendar2).toString();
                i = i3;
            }
        }
    }

    private void setMaxDate(long j) {
        this.av.setTimeInMillis(j);
        this.ba.setTimeInMillis(j);
        n();
        af();
    }

    private void setMinDate(long j) {
        this.av.setTimeInMillis(j);
        this.aw.setTimeInMillis(j);
        n();
        af();
    }

    private void setSpinnersMiddleDrawable(int i) {
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.e;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setMiddleStateDrawable(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.d;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setMiddleStateDrawable(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.b;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setMiddleStateDrawable(i);
        }
    }

    private void setSpinnersSelectionDividerHeight(int i) {
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.e;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSelectionDividerHeight(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.d;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSelectionDividerHeight(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.b;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSelectionDividerHeight(i);
        }
    }

    private boolean u() {
        return sms.b(this.af) != 1 || Float.compare(this.am, 1.75f) < 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        String str = ",   " + getShowString();
        this.e.setAnnouncedSuffix(str);
        this.d.setAnnouncedSuffix(str);
        this.b.setAnnouncedSuffix(str);
        this.e.setAccessibilityOptimizationEnabled(true);
        this.d.setAccessibilityOptimizationEnabled(true);
        this.b.setAccessibilityOptimizationEnabled(true);
        OnDateChangedListener onDateChangedListener = this.as;
        if (onDateChangedListener != null) {
            onDateChangedListener.onDateChanged(this, getYear(), getMonth(), getDayOfMonth(), this.bb);
        }
    }

    private void w() {
        this.ap = (LinearLayout) findViewById(R.id.hwdatepicker_lunar_or_western);
        HwCheckBox hwCheckBox = (HwCheckBox) findViewById(R.id.hwdatepicker_checkbox);
        this.ar = hwCheckBox;
        if (hwCheckBox != null) {
            hwCheckBox.setChecked(false);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hwdatepicker_switch_button);
        this.ao = linearLayout;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new c());
        }
        d dVar = new d();
        HwCheckBox hwCheckBox2 = this.ar;
        if (hwCheckBox2 != null) {
            hwCheckBox2.setOnCheckedChangeListener(dVar);
        }
    }

    private void x() {
        this.an = (LinearLayout) findViewById(R.id.hwdatepicker_pickers_Layout);
        this.ak = (LinearLayout) findViewById(R.id.hwdatepicker_pickers);
        HwAdvancedNumberPicker hwAdvancedNumberPicker = (HwAdvancedNumberPicker) findViewById(R.id.hwdatepicker_day);
        this.b = hwAdvancedNumberPicker;
        hwAdvancedNumberPicker.setOnLongPressUpdateInterval(100L);
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = (HwAdvancedNumberPicker) findViewById(R.id.hwdatepicker_month);
        this.d = hwAdvancedNumberPicker2;
        hwAdvancedNumberPicker2.setMinValue(0);
        this.d.setMaxValue(this.at - 1);
        this.d.setDisplayedValues(this.ax);
        this.d.setOnLongPressUpdateInterval(100L);
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = (HwAdvancedNumberPicker) findViewById(R.id.hwdatepicker_year);
        this.e = hwAdvancedNumberPicker3;
        hwAdvancedNumberPicker3.setOnLongPressUpdateInterval(100L);
        this.e.setFlingAnnounceType(3);
        this.d.setFlingAnnounceType(1);
        this.b.setFlingAnnounceType(3);
    }

    private boolean y() {
        return (this.b == null || this.d == null || this.e == null) ? false : true;
    }

    private void z() {
        if (!this.l) {
            Log.w("HwDatePicker", "no need to reset LunarYearMonthDay.");
            return;
        }
        this.m.e(this.bb.get(1), this.bb.get(2) + 1, this.bb.get(5));
        this.k = this.m.b();
        this.r = this.m.c();
        this.t = this.m.d();
    }

    public void a(int i, int i2, int i3) {
        if (d(i, i2, i3)) {
            c(i, i2, i3);
            af();
            v();
        }
    }

    public void a(GregorianCalendar gregorianCalendar, GregorianCalendar gregorianCalendar2) {
        if (gregorianCalendar != null) {
            int i = gregorianCalendar.get(1);
            int i2 = gregorianCalendar.get(2);
            int i3 = gregorianCalendar.get(5);
            if (i < 1) {
                i = 1;
            }
            this.av.clear();
            this.av.set(i, i2, i3);
            setMinDate(this.av.getTimeInMillis());
        }
        if (gregorianCalendar2 != null) {
            int i4 = gregorianCalendar2.get(1);
            int i5 = gregorianCalendar2.get(2);
            int i6 = gregorianCalendar2.get(5);
            int i7 = i4 >= 1 ? i4 : 1;
            this.av.clear();
            this.av.set(i7, i5, i6);
            setMaxDate(this.av.getTimeInMillis());
        }
    }

    public boolean a() {
        return this.l;
    }

    public final void c(int i, int i2, int i3, OnDateChangedListener onDateChangedListener) {
        c(i, i2, i3);
        af();
        this.as = onDateChangedListener;
    }

    public void d() {
        aa();
        boolean z = !this.al && getResources().getConfiguration().orientation == 2;
        this.b.a(z);
        this.d.a(z);
        this.e.a(z);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public void e(int i, int i2) {
        if (i < 1) {
            i = 1;
        }
        this.av.clear();
        this.av.set(i, 0, 1);
        setMinDate(this.av.getTimeInMillis());
        if (i2 > 5000) {
            i2 = 5000;
        }
        this.av.clear();
        this.av.set(i2, 11, 31);
        setMaxDate(this.av.getTimeInMillis());
    }

    protected void ebR_(View view, View view2, View view3) {
    }

    protected void ebS_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.enabled, R.attr._2131100178_res_0x7f060212, R.attr._2131100179_res_0x7f060213, R.attr._2131100194_res_0x7f060222, R.attr._2131100223_res_0x7f06023f, R.attr._2131100254_res_0x7f06025e, R.attr._2131100273_res_0x7f060271, R.attr._2131100418_res_0x7f060302, R.attr._2131100420_res_0x7f060304, R.attr._2131100573_res_0x7f06039d}, i, 0);
        try {
            try {
                setEnabled(obtainStyledAttributes.getBoolean(0, true));
                setSpinnersSelectionDivider(obtainStyledAttributes.getDrawable(7));
                setSpinnersSelectionDividerHeight(obtainStyledAttributes.getDimensionPixelSize(8, 0));
            } catch (Resources.NotFoundException unused) {
                Log.w("HwDatePicker", "TypedArray get resource error");
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public boolean g() {
        return this.g;
    }

    public int getDayOfMonth() {
        return this.l ? this.bb.get(5) : this.ah;
    }

    public String getDisplayedString() {
        return !this.f ? getWholeLunarStrings() : "";
    }

    public int getMonth() {
        return this.l ? this.bb.get(2) : this.ai;
    }

    public String[] getShortMonthDays() {
        return this.au;
    }

    public String[] getShortMonths() {
        return this.ax;
    }

    public Drawable getSpinnersSelectionDivider() {
        return this.i;
    }

    public int getSpinnersSelectorPaintColor() {
        return this.c;
    }

    public boolean getSpinnersShown() {
        return this.ak.isShown();
    }

    public int getSpinnersUnselectedPaintColor() {
        return this.h;
    }

    public int getYear() {
        return this.bb.get(1);
    }

    public boolean h() {
        return this.f;
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.j;
    }

    @Override // android.view.View
    public boolean isHapticFeedbackEnabled() {
        return super.isHapticFeedbackEnabled();
    }

    public boolean j() {
        return this.n;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(this.af, this.bb.getTimeInMillis(), 20));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof a) {
            a aVar = (a) parcelable;
            super.onRestoreInstanceState(aVar.getSuperState());
            c(aVar.f10634a, aVar.b, aVar.c);
            af();
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return new a(super.onSaveInstanceState(), getYear(), getMonth(), getDayOfMonth(), null);
    }

    public void setDayDisplayValueIndex(int i) {
        this.ah = i;
    }

    public void setDialogStyle() {
        aa();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (!y() || this.j == z) {
            return;
        }
        this.j = z;
        this.b.setEnabled(z);
        this.d.setEnabled(z);
        this.e.setEnabled(z);
    }

    @Override // android.view.View
    public void setHapticFeedbackEnabled(boolean z) {
        super.setHapticFeedbackEnabled(z);
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.b;
        if (hwAdvancedNumberPicker == null || this.d == null || this.e == null) {
            return;
        }
        hwAdvancedNumberPicker.setHapticFeedbackEnabled(z);
        this.d.setHapticFeedbackEnabled(z);
        this.e.setHapticFeedbackEnabled(z);
    }

    public void setIsShowAllYears(boolean z) {
        this.n = z;
        if (z) {
            return;
        }
        this.f10633a = this.bc + 1;
    }

    public void setLinkageScrollEnabled(boolean z) {
        this.aj = z;
    }

    public void setLunarOrWestern(boolean z) {
        this.ar.setChecked(z);
    }

    public void setMonthDisplayValueIndex(int i) {
        this.ai = i;
    }

    public void setSelectYear(boolean z) {
        this.l = z;
    }

    public void setSpinnersSelectionDivider(Drawable drawable) {
        Drawable drawable2 = this.i;
        if (drawable2 == null || !drawable2.equals(drawable)) {
            this.i = drawable;
            HwAdvancedNumberPicker hwAdvancedNumberPicker = this.e;
            if (hwAdvancedNumberPicker != null) {
                hwAdvancedNumberPicker.setSelectionDivider(drawable);
            }
            HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.d;
            if (hwAdvancedNumberPicker2 != null) {
                hwAdvancedNumberPicker2.setSelectionDivider(drawable);
            }
            HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.b;
            if (hwAdvancedNumberPicker3 != null) {
                hwAdvancedNumberPicker3.setSelectionDivider(drawable);
            }
        }
    }

    public void setSpinnersSelectorPaintColor(int i) {
        if (this.c == i) {
            return;
        }
        this.c = i;
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.e;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSelectorPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.d;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSelectorPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.b;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSelectorPaintColor(i);
        }
    }

    public final void setSpinnersShown() {
        this.ak.setVisibility(0);
    }

    public void setSpinnersUnselectedPaintColor(int i) {
        if (this.h == i) {
            return;
        }
        this.h = i;
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.e;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSecondaryPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.d;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSecondaryPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.b;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSecondaryPaintColor(i);
        }
    }

    public void setmIsSupportLunarSwitch(boolean z) {
        HwCheckBox hwCheckBox;
        this.g = z;
        if (!z && (hwCheckBox = this.ar) != null) {
            hwCheckBox.setChecked(false);
        }
        setDialogStyle();
    }

    public void setmIsWestern(boolean z) {
        if (!skj.b(this.af)) {
            this.f = true;
            return;
        }
        this.f = z;
        if (this.g) {
            setLunarOrWestern(!z);
        }
        s();
    }

    public HwDatePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100272_res_0x7f060270);
    }

    private void b(int i, int i2, int i3) {
        if (i == i3 && i2 == 1) {
            this.av.add(5, this.n ? 1 : 1 - i3);
        } else if (i == 1 && i2 == i3) {
            this.av.add(5, this.n ? -1 : i3 - 1);
        } else {
            this.av.add(5, i2 - i);
        }
    }

    private void j(int i, int i2) {
        if (i == 0 && i2 == 11) {
            this.av.add(2, 11);
        } else if (i == 11 && i2 == 0) {
            this.av.add(2, -11);
        } else {
            this.av.add(2, i2 - i);
        }
    }

    private void p() {
        String[] strArr;
        Map<String, List<String>> l = this.p.l();
        Map<Integer, String> d2 = this.p.d();
        Map<String, Integer> h = this.p.h();
        if (l == null || d2 == null || h == null) {
            Log.e("HwDatePicker", "yearMonthToDays || indexYearMonth || yearMonthIndex is null");
            return;
        }
        if (this.l) {
            strArr = slv.c(l.get(this.k + this.r));
        } else {
            strArr = slv.e;
        }
        this.u = strArr;
    }

    void b() {
        if (u()) {
            return;
        }
        this.e.setVisibility(0);
        this.d.setVisibility(8);
        this.b.setVisibility(8);
    }

    void c() {
        if (u()) {
            return;
        }
        this.e.setVisibility(8);
        this.d.setVisibility(0);
        this.b.setVisibility(0);
    }

    public HwDatePicker(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        this.f10633a = 2100;
        this.j = true;
        this.g = true;
        this.f = true;
        this.n = true;
        this.l = true;
        this.o = false;
        this.af = getContext();
        this.ae = new ArrayList(10);
        this.ai = 0;
        this.ah = 0;
        Context context2 = super.getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100575_res_0x7f06039f});
        if (obtainStyledAttributes != null) {
            int resourceId = obtainStyledAttributes.getResourceId(0, 0);
            context2 = resourceId != 0 ? new ContextThemeWrapper(super.getContext(), resourceId) : context2;
            obtainStyledAttributes.recycle();
        }
        this.al = this.af.getResources().getInteger(R.integer._2131623948_res_0x7f0e000c) == 2;
        setCurrentLocale(Locale.getDefault());
        a(context2);
        x();
        ad();
        w();
        if (skj.e(this.af)) {
            this.e.a(getResources().getString(R.string._2130851660_res_0x7f02374c), true);
        }
        if (skj.b(this.af)) {
            s();
            if (!this.g) {
                this.ap.setVisibility(8);
            }
        }
        setSpinnersShown();
        ebQ_(context2, attributeSet, i);
        q();
        this.ae.add(this.b);
        this.ae.add(this.d);
        this.ae.add(this.e);
        ac();
        try {
            if (!Locale.getDefault().getLanguage().contains("ar") && !Locale.getDefault().getLanguage().contains("fa") && !Locale.getDefault().getLanguage().contains("iw")) {
                ab();
            }
        } catch (IllegalArgumentException unused) {
            Log.e("HwDatePicker", "Exception catched");
        }
        aa();
    }

    private void j(int i) {
        if (this.n) {
            this.b.setDisplayedValues(null);
            this.b.setMinValue(this.bb.getActualMinimum(5));
            if (i == this.ba.get(5)) {
                this.b.setMaxValue(this.ba.get(5));
                this.b.setWrapSelectorWheel(false);
            } else {
                this.b.setMaxValue(this.bb.getActualMaximum(5));
                this.b.setWrapSelectorWheel(true);
            }
            this.d.setDisplayedValues(null);
            this.d.setMinValue(this.bb.getActualMinimum(2));
            this.d.setMaxValue(this.bb.get(2));
            this.d.setWrapSelectorWheel(false);
            return;
        }
        this.b.setDisplayedValues(null);
        this.b.setMinValue(this.bb.getActualMinimum(5));
        this.b.setMaxValue(this.bb.getActualMaximum(5));
        this.b.setWrapSelectorWheel(true);
        this.d.setDisplayedValues(null);
        this.d.setMinValue(this.bb.getActualMinimum(2));
        this.d.setMaxValue(this.bb.get(2));
        this.d.setWrapSelectorWheel(true);
    }

    private void ebQ_(Context context, AttributeSet attributeSet, int i) {
        ebS_(context, attributeSet, i);
    }

    private GregorianCalendar f(int i) {
        if (i >= 0) {
            String[] strArr = this.s;
            if (i < strArr.length) {
                GregorianCalendar b2 = this.p.b(strArr[i], this.r, this.t, 0, true);
                if (b2 == null && this.t.equals("三十")) {
                    this.t = "廿九";
                    b2 = this.p.b(this.s[i], this.r, "廿九", 1, true);
                }
                if (b2 != null || !this.r.contains("闰")) {
                    return b2;
                }
                return this.p.b(this.s[i], this.r.replace("闰", ""), this.t, 1, true);
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0079, code lost:
    
        if (r2.equals(r8.k + r8.r) == false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void t() {
        /*
            r8 = this;
            slt r0 = r8.p
            java.util.Map r0 = r0.l()
            slt r1 = r8.p
            java.util.Map r1 = r1.d()
            slt r2 = r8.p
            java.util.Map r2 = r2.h()
            java.lang.String r3 = "HwDatePicker"
            if (r0 == 0) goto Ld1
            if (r1 == 0) goto Ld1
            if (r2 != 0) goto L1c
            goto Ld1
        L1c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r8.k
            r4.append(r5)
            java.lang.String r5 = "_"
            r4.append(r5)
            java.lang.String r5 = r8.r
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.Object r2 = r2.get(r4)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r4 = r2 + (-1)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object r4 = r1.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r4 = defpackage.slv.b(r4)
            r5 = 1
            int r2 = r2 + r5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r1 = defpackage.slv.b(r1)
            java.lang.String r2 = r8.aa
            if (r2 == 0) goto L7b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r8.k
            r6.append(r7)
            java.lang.String r7 = r8.r
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto Lb2
        L7b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r8.k
            r2.append(r6)
            java.lang.String r6 = r8.r
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            java.lang.Object r2 = r0.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.lang.String[] r2 = defpackage.slv.c(r2)
            r8.u = r2
            java.lang.Object r2 = r0.get(r4)
            java.util.List r2 = (java.util.List) r2
            java.lang.String[] r2 = defpackage.slv.c(r2)
            r8.ab = r2
            java.lang.Object r0 = r0.get(r1)
            java.util.List r0 = (java.util.List) r0
            java.lang.String[] r0 = defpackage.slv.c(r0)
            r8.ac = r0
        Lb2:
            java.lang.String[] r0 = r8.u
            int r0 = r0.length
            if (r0 == 0) goto Lc1
            java.lang.String[] r0 = r8.ab
            int r0 = r0.length
            if (r0 == 0) goto Lc1
            java.lang.String[] r0 = r8.ac
            int r0 = r0.length
            if (r0 != 0) goto Ld0
        Lc1:
            java.lang.String r0 = "mCurrentMonthDays maybe not found "
            android.util.Log.w(r3, r0)
            r8.f = r5
            com.huawei.uikit.hwcheckbox.widget.HwCheckBox r0 = r8.ar
            if (r0 == 0) goto Ld0
            r1 = 0
            r0.setChecked(r1)
        Ld0:
            return
        Ld1:
            java.lang.String r0 = "yearMonthToDays || indexYearMonth || yearMonthIndex is null"
            android.util.Log.e(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwdatepicker.widget.HwDatePicker.t():void");
    }

    private void a(int i, int i2) {
        int i3 = this.f10633a;
        if (i2 == i3) {
            this.l = false;
            this.ai = this.bb.get(2);
            this.ah = this.bb.get(5);
        } else {
            if (i2 == i3 - 1 && i == i3) {
                this.l = true;
                this.av.set(2, this.ai);
                this.av.set(5, this.ah);
                return;
            }
            Log.w("HwDatePicker", "mIsSelectYear status not change.");
        }
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, R$style.Theme_Emui_HwDatePicker);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, int i2) {
        if (this.f) {
            this.av.setTimeInMillis(this.bb.getTimeInMillis());
            if (hwAdvancedNumberPicker == this.b) {
                this.ah = this.n ? this.ah : i2;
                if (!this.aj) {
                    this.av.set(5, i2);
                    return;
                } else {
                    b(i, i2, this.av.getActualMaximum(5));
                    return;
                }
            }
            if (hwAdvancedNumberPicker == this.d) {
                this.ai = this.n ? this.ai : i2;
                if (!this.aj) {
                    j(i, i2);
                    return;
                } else {
                    d(i, i2);
                    return;
                }
            }
            if (hwAdvancedNumberPicker == this.e) {
                if (!this.n) {
                    a(i, i2);
                }
                this.av.add(1, i2 - i);
                return;
            }
            Log.e("HwDatePicker", "invalid picker");
            return;
        }
        if (this.n) {
            d(hwAdvancedNumberPicker, i, i2);
        } else {
            c(hwAdvancedNumberPicker, i2);
        }
    }

    private void d(int i, int i2) {
        if (e(i, i2, 11)) {
            this.av.add(2, this.n ? 1 : -11);
        } else if (i == 0 && i2 == 11) {
            this.av.add(2, this.n ? -1 : 11);
        } else {
            this.av.add(2, i2 - i);
        }
    }

    private void h(int i) {
        int i2;
        String str = this.z;
        if (str == null || !str.equals(this.k)) {
            this.p.a(i);
        }
        r();
        this.y = this.v;
        p();
        this.ad = this.u;
        int i3 = this.f10633a;
        String[] strArr = new String[i3 - 1899];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            i2 = i3 - 1900;
            if (i5 >= i2) {
                break;
            }
            strArr[i5] = this.q[i5 + 2];
            i5++;
        }
        strArr[i2] = "-- --";
        this.e.setDisplayedValues(null);
        this.e.setMinValue(1900);
        this.e.setMaxValue(this.f10633a);
        this.e.setValue(i);
        this.e.setDisplayedValues(strArr);
        this.e.setWrapSelectorWheel(false);
        String str2 = this.r;
        int i6 = 0;
        while (true) {
            String[] strArr2 = this.v;
            if (i6 >= strArr2.length || str2.equals(strArr2[i6])) {
                break;
            } else {
                i6++;
            }
        }
        this.d.setDisplayedValues(null);
        this.d.setMinValue(1);
        this.d.setMaxValue(this.v.length);
        this.d.setValue(i6 + 1);
        this.d.setDisplayedValues(this.v);
        this.d.setWrapSelectorWheel(true);
        String str3 = this.t;
        while (true) {
            String[] strArr3 = this.u;
            if (i4 >= strArr3.length || str3.equals(strArr3[i4])) {
                break;
            } else {
                i4++;
            }
        }
        this.b.setDisplayedValues(null);
        this.b.setMinValue(1);
        this.b.setMaxValue(this.u.length);
        this.b.setValue(i4 + 1);
        this.b.setDisplayedValues(this.u);
        this.b.setWrapSelectorWheel(true);
    }

    private GregorianCalendar i(int i) {
        int i2 = this.f10633a;
        if (i == i2) {
            this.l = false;
        } else if (i == i2 - 1) {
            this.l = true;
        } else {
            Log.w("HwDatePicker", "mIsSelectYear status not change.");
        }
        if (i < 1898) {
            return null;
        }
        int i3 = i - 1898;
        String[] strArr = this.s;
        if (i3 > strArr.length) {
            return null;
        }
        GregorianCalendar b2 = this.p.b(strArr[i3], this.r, this.t, 0, true);
        boolean z = b2 == null && this.t.equals("三十");
        if (this.l && z) {
            b2 = this.p.b(this.s[i3], this.r, "廿九", 1, true);
        }
        boolean z2 = b2 == null && this.r.contains("闰");
        if (this.l && z2) {
            b2 = this.p.b(this.s[i3], this.r.replace("闰", ""), this.t, 1, true);
        }
        boolean z3 = b2 == null && this.r.contains("闰") && this.t.equals("三十");
        if (this.l && z3) {
            return this.p.b(this.s[i3], this.r.replace("闰", ""), "廿九", 1, true);
        }
        return b2;
    }

    void e() {
        if (u()) {
            return;
        }
        for (HwAdvancedNumberPicker hwAdvancedNumberPicker : this.ae) {
            hwAdvancedNumberPicker.setAuxiliaryUnselectedTextSize(28.0f);
            hwAdvancedNumberPicker.setAuxiliarySelectedTextSize(35.0f);
        }
        if (Float.compare(this.am, 2.0f) >= 0) {
            this.ar.setTextSize(1, 32.0f);
        }
    }

    private void g(int i) {
        this.b.setDisplayedValues(null);
        if (i == this.aw.get(5)) {
            this.b.setMinValue(this.aw.get(5));
            this.b.setMaxValue(this.bb.getActualMaximum(5));
            this.b.setWrapSelectorWheel(false);
        } else {
            this.b.setMinValue(this.bb.getActualMinimum(5));
            this.b.setMaxValue(this.bb.getActualMaximum(5));
            this.b.setWrapSelectorWheel(true);
        }
        this.d.setDisplayedValues(null);
        this.d.setMinValue(this.bb.get(2));
        this.d.setMaxValue(this.bb.getActualMaximum(2));
        this.d.setWrapSelectorWheel(false);
    }

    private void a(Context context) {
        this.ag = slv.d;
        this.am = smp.a(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            layoutInflater.cloneInContext(context).inflate(R.layout.hwdatepicker, (ViewGroup) this, true);
        }
    }

    private void n() {
        if (this.bb.before(this.aw)) {
            this.bb.setTimeInMillis(this.aw.getTimeInMillis());
        } else if (this.bb.after(this.ba)) {
            this.bb.setTimeInMillis(this.ba.getTimeInMillis());
        } else {
            Log.w("HwDatePicker", "normal date");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2, int i3) {
        this.bb.set(i, i2, i3);
        n();
    }

    private int d(int i, boolean z, boolean z2) {
        if (z) {
            String[] d2 = d(i, this.s, this.q);
            this.s = d2;
            int d3 = slv.d(this.k, d2);
            this.e.setDisplayedValues(null);
            this.e.setMinValue(0);
            this.e.setMaxValue(this.s.length - 1);
            this.e.setWrapSelectorWheel(false);
            return d3;
        }
        if (z2) {
            String[] c2 = c(i, this.s, this.q);
            this.s = c2;
            int d4 = slv.d(this.k, c2);
            this.e.setDisplayedValues(null);
            this.e.setMinValue(0);
            this.e.setMaxValue(this.s.length - 1);
            this.e.setWrapSelectorWheel(false);
            return d4;
        }
        this.e.setDisplayedValues(null);
        this.e.setMinValue(0);
        this.e.setMaxValue(this.s.length - 1);
        this.e.setWrapSelectorWheel(false);
        return i;
    }

    private GregorianCalendar c(int i) {
        GregorianCalendar b2 = this.p.b(this.k, this.y[i], this.t, 1, true);
        if (!this.l || b2 != null || !this.t.equals("三十")) {
            return b2;
        }
        this.t = "廿九";
        return this.p.b(this.k, this.y[i], "廿九", 1, true);
    }

    private GregorianCalendar c(GregorianCalendar gregorianCalendar, Locale locale) {
        if (gregorianCalendar == null) {
            return new GregorianCalendar(locale);
        }
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar(locale);
        gregorianCalendar2.setTimeInMillis(timeInMillis);
        return gregorianCalendar2;
    }

    private GregorianCalendar d(int i) {
        String str;
        String[] strArr = this.y;
        if (strArr.length == 0) {
            return null;
        }
        int i2 = i - 1;
        String str2 = strArr[i2 % strArr.length];
        GregorianCalendar b2 = this.p.b(this.k, str2, this.t, 1, true);
        if (this.l && b2 == null && this.t.equals("三十")) {
            this.t = "廿九";
            b2 = this.p.b(this.k, str2, "廿九", 1, true);
        }
        if (this.l) {
            str = this.r;
        } else {
            String[] strArr2 = this.y;
            str = strArr2[i2 % strArr2.length];
        }
        this.r = str;
        return b2;
    }

    private boolean d(int i, int i2, int i3) {
        return Boolean.valueOf(this.bb.get(1) != i).booleanValue() || Boolean.valueOf(this.bb.get(2) != i3).booleanValue() || Boolean.valueOf(this.bb.get(5) != i2).booleanValue();
    }

    private void c(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, String[] strArr, boolean z) {
        hwAdvancedNumberPicker.setDisplayedValues(strArr);
        hwAdvancedNumberPicker.setValue(i);
        hwAdvancedNumberPicker.setEnabled(z);
    }

    private int a(int i, boolean z, boolean z2) {
        if (z) {
            String[] a2 = slv.a(i, this.y);
            this.y = a2;
            int d2 = slv.d(this.r, a2);
            this.d.setDisplayedValues(null);
            this.d.setMinValue(0);
            this.d.setMaxValue(this.y.length - 1);
            this.d.setWrapSelectorWheel(false);
            return d2;
        }
        if (z2) {
            String[] e2 = slv.e(i, this.y);
            this.y = e2;
            int d3 = slv.d(this.r, e2);
            this.d.setDisplayedValues(null);
            this.d.setMinValue(0);
            this.d.setMaxValue(this.y.length - 1);
            this.d.setWrapSelectorWheel(false);
            return d3;
        }
        this.d.setDisplayedValues(null);
        this.d.setMinValue(0);
        this.d.setMaxValue(this.y.length - 1);
        this.d.setWrapSelectorWheel(true);
        return i;
    }

    private void e(int i) {
        this.s = this.p.f();
        this.q = this.p.c();
        SparseArray<String> edM_ = this.p.edM_();
        String str = edM_.get(i - 1);
        String str2 = edM_.get(i + 1);
        Map<String, List<String>> i2 = this.p.i();
        String str3 = this.z;
        if (str3 == null || !str3.equals(this.k)) {
            this.v = slv.c(i2.get(this.k));
            this.x = slv.c(i2.get(str));
            this.w = slv.c(i2.get(str2));
        }
        if (this.v.length == 0 || this.x.length == 0 || this.w.length == 0) {
            Log.w("HwDatePicker", "mCurrentYearMonths maybe not found ");
            this.f = true;
            HwCheckBox hwCheckBox = this.ar;
            if (hwCheckBox != null) {
                hwCheckBox.setChecked(false);
            }
        }
    }

    private GregorianCalendar b(int i) {
        String str;
        String[] strArr = this.ad;
        if (strArr.length == 0) {
            return null;
        }
        int i2 = i - 1;
        GregorianCalendar c2 = this.p.c(this.k, this.r, strArr[i2 % strArr.length], true);
        if (this.l) {
            str = this.t;
        } else {
            String[] strArr2 = this.ad;
            str = strArr2[i2 % strArr2.length];
        }
        this.t = str;
        return c2;
    }

    private boolean c(GregorianCalendar gregorianCalendar) {
        if (!this.bb.before(gregorianCalendar)) {
            return false;
        }
        this.bb.set(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5));
        return true;
    }

    private String[] e(String str, String str2, String str3, String str4) {
        String country = Locale.getDefault().getCountry();
        String[] strArr = (String[]) this.ad.clone();
        String[] strArr2 = (String[]) this.y.clone();
        e(strArr, strArr2);
        boolean z = "hk".equals(country) || "HK".equals(country);
        boolean z2 = "tw".equals(country) || "TW".equals(country);
        boolean z3 = TextUtils.equals("MO", country) || TextUtils.equals("mo", country);
        if (z || z2 || z3) {
            String[] strArr3 = (String[]) strArr2.clone();
            int length = strArr2.length;
            for (int i = 0; i < length; i++) {
                if (strArr3[i].equals(str2)) {
                    strArr3[i] = str;
                } else if (strArr3[i].length() == 3 && str4.equals(strArr3[i].substring(0, 1))) {
                    strArr3[i] = strArr3[i].replace(str4, str3);
                }
            }
            this.d.setDisplayedValues(strArr3);
        } else {
            this.d.setDisplayedValues(strArr2);
        }
        return (String[]) strArr.clone();
    }

    private GregorianCalendar c(int i, int i2) {
        int length = this.y.length - 1;
        String str = "";
        if (e(i, i2, length)) {
            String str2 = this.p.edM_().get(this.p.g().get(this.k).intValue() + 1);
            if (i2 >= 0) {
                String[] strArr = this.y;
                if (i2 < strArr.length) {
                    str = strArr[i2];
                }
            }
            GregorianCalendar b2 = this.p.b(str2, str, this.t, 1, true);
            if (!this.l || b2 != null || !this.t.equals("三十")) {
                return b2;
            }
            this.t = "廿九";
            return this.p.b(str2, str, "廿九", 1, true);
        }
        if (i == 0 && i2 == length) {
            String str3 = this.p.edM_().get(this.p.g().get(this.k).intValue() - 1);
            if (i2 >= 0) {
                String[] strArr2 = this.y;
                if (i2 < strArr2.length) {
                    str = strArr2[i2];
                }
            }
            GregorianCalendar b3 = this.p.b(str3, str, this.t, 1, true);
            if (!this.l || b3 != null || !this.t.equals("三十")) {
                return b3;
            }
            this.t = "廿九";
            return this.p.b(str3, str, "廿九", 1, true);
        }
        if (i2 >= 0) {
            String[] strArr3 = this.y;
            if (i2 < strArr3.length) {
                str = strArr3[i2];
            }
        }
        GregorianCalendar b4 = this.p.b(this.k, str, this.t, 1, true);
        if (!this.l || b4 != null || !this.t.equals("三十")) {
            return b4;
        }
        this.t = "廿九";
        return this.p.b(this.k, str, "廿九", 1, true);
    }

    private int c(int i, boolean z, boolean z2) {
        if (z) {
            String[] a2 = slv.a(i, this.ad);
            this.ad = a2;
            int d2 = slv.d(this.t, a2);
            this.b.setDisplayedValues(null);
            this.b.setMinValue(0);
            this.b.setMaxValue(this.ad.length - 1);
            this.b.setWrapSelectorWheel(false);
            return d2;
        }
        if (z2) {
            String[] e2 = slv.e(i, this.ad);
            this.ad = e2;
            int d3 = slv.d(this.t, e2);
            this.b.setDisplayedValues(null);
            this.b.setMinValue(0);
            this.b.setMaxValue(this.ad.length - 1);
            this.b.setWrapSelectorWheel(false);
            return d3;
        }
        this.b.setDisplayedValues(null);
        this.b.setMinValue(0);
        this.b.setMaxValue(this.ad.length - 1);
        this.b.setWrapSelectorWheel(true);
        return i;
    }

    private GregorianCalendar a(int i) {
        String str;
        GregorianCalendar c2;
        if (i >= 0) {
            String[] strArr = this.ad;
            if (i < strArr.length) {
                str = strArr[i];
                c2 = this.p.c(this.k, this.r, str, false);
                if (this.l || c2 != null || !this.t.equals("初一")) {
                    return c2;
                }
                this.t = "三十";
                GregorianCalendar c3 = this.p.c(this.k, this.r, "三十", false);
                if (c3 != null) {
                    return c3;
                }
                this.t = "廿九";
                return this.p.c(this.k, this.r, "廿九", true);
            }
        }
        str = "";
        c2 = this.p.c(this.k, this.r, str, false);
        return this.l ? c2 : c2;
    }

    private boolean e(GregorianCalendar gregorianCalendar) {
        if (!this.bb.after(gregorianCalendar)) {
            return false;
        }
        this.bb.set(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5) + 1);
        return true;
    }

    private String[] d(int i, String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        slv.c(i, strArr, strArr2, arrayList, arrayList2);
        String[] strArr3 = new String[arrayList.size()];
        String[] strArr4 = new String[arrayList.size()];
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            strArr3[i2] = (String) arrayList.get(i2);
            strArr4[i2] = (String) arrayList2.get(i2);
        }
        this.q = strArr4;
        return strArr3;
    }

    private void e(int i, String[] strArr) {
        if (this.m.h() == 1900 && i == 2) {
            strArr[0] = "";
            strArr[1] = "";
        } else if (this.m.h() == 1901 && i == 3) {
            strArr[1] = "";
        } else {
            Log.w("HwDatePicker", "no need to hide year.");
        }
    }

    private void e(String[] strArr, String[] strArr2) {
        if (this.m.h() == 1900) {
            if (this.m.a() == 1 && this.m.e() == 2 && strArr.length > 28) {
                strArr[28] = "";
            }
            if ((this.m.a() == 1 || this.m.a() == 2) && strArr2.length > 12) {
                strArr2[11] = "";
                strArr2[12] = "";
            }
        }
    }

    private void d(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, int i2) {
        GregorianCalendar gregorianCalendar;
        GregorianCalendar gregorianCalendar2;
        GregorianCalendar gregorianCalendar3 = this.av;
        if (gregorianCalendar3 != null && (gregorianCalendar2 = this.bb) != null) {
            gregorianCalendar3.setTimeInMillis(gregorianCalendar2.getTimeInMillis());
        }
        if (hwAdvancedNumberPicker == this.e && this.s != null) {
            gregorianCalendar = f(i2);
        } else if (hwAdvancedNumberPicker == this.d && this.y != null) {
            if (!this.aj) {
                gregorianCalendar = c(i2);
            } else {
                gregorianCalendar = c(i, i2);
            }
        } else if (hwAdvancedNumberPicker == this.b && this.ad != null) {
            if (!this.aj) {
                gregorianCalendar = a(i2);
            } else {
                gregorianCalendar = b(i, i2);
            }
        } else {
            Log.w("HwDatePicker", "error spinner value change");
            gregorianCalendar = null;
        }
        GregorianCalendar gregorianCalendar4 = this.av;
        if (gregorianCalendar4 != null && gregorianCalendar != null) {
            gregorianCalendar4.setTimeInMillis(gregorianCalendar.getTimeInMillis());
        } else {
            Log.w("HwDatePicker", "error date");
        }
    }

    private void c(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i) {
        GregorianCalendar gregorianCalendar;
        GregorianCalendar gregorianCalendar2;
        GregorianCalendar gregorianCalendar3 = this.av;
        if (gregorianCalendar3 != null && (gregorianCalendar2 = this.bb) != null) {
            gregorianCalendar3.setTimeInMillis(gregorianCalendar2.getTimeInMillis());
        }
        if (hwAdvancedNumberPicker == this.e && this.s != null) {
            gregorianCalendar = i(i);
        } else if (hwAdvancedNumberPicker == this.d && this.y != null) {
            gregorianCalendar = d(i);
        } else if (hwAdvancedNumberPicker == this.b && this.ad != null) {
            gregorianCalendar = b(i);
        } else {
            Log.w("HwDatePicker", "error spinner value change");
            gregorianCalendar = null;
        }
        GregorianCalendar gregorianCalendar4 = this.av;
        if (gregorianCalendar4 == null || gregorianCalendar == null) {
            return;
        }
        gregorianCalendar4.setTimeInMillis(gregorianCalendar.getTimeInMillis());
    }

    private GregorianCalendar b(int i, int i2) {
        String str;
        int length = this.ad.length - 1;
        if (e(i, i2, length)) {
            int intValue = this.p.h().get(this.k + "_" + this.r).intValue();
            if (this.n) {
                intValue++;
            }
            String[] split = this.p.d().get(Integer.valueOf(intValue)).split("_");
            if (split.length > 1) {
                String[] strArr = this.ad;
                if (strArr.length > i2) {
                    return this.p.c(split[0], split[1], strArr[i2], true);
                }
            }
        } else if (j(i, i2, length)) {
            int intValue2 = this.p.h().get(this.k + "_" + this.r).intValue();
            if (this.n) {
                intValue2--;
            }
            String[] split2 = this.p.d().get(Integer.valueOf(intValue2)).split("_");
            if (split2.length > 1) {
                String[] strArr2 = this.ad;
                if (strArr2.length > i2 && i2 >= 0) {
                    return this.p.c(split2[0], split2[1], strArr2[i2], true);
                }
            }
        } else {
            if (i2 >= 0) {
                String[] strArr3 = this.ad;
                if (i2 < strArr3.length) {
                    str = strArr3[i2];
                    return this.p.c(this.k, this.r, str, true);
                }
            }
            str = "";
            return this.p.c(this.k, this.r, str, true);
        }
        return null;
    }

    private String[] c(int i, String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        slv.e(i, strArr, strArr2, arrayList, arrayList2);
        String[] strArr3 = new String[arrayList.size()];
        String[] strArr4 = new String[arrayList.size()];
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            strArr3[i2] = (String) arrayList.get(i2);
            strArr4[i2] = (String) arrayList2.get(i2);
        }
        this.q = strArr4;
        return strArr3;
    }
}
