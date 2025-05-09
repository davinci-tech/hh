package com.huawei.uikit.hwdateandtimepicker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker;
import com.huawei.uikit.hwcheckbox.widget.HwCheckBox;
import com.huawei.uikit.hwlunar.utils.HwLunarCalendar;
import defpackage.skj;
import defpackage.slu;
import defpackage.smp;
import defpackage.smr;
import defpackage.sms;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class HwDateAndTimePicker extends FrameLayout {
    private OnDateChangedListener aa;
    private boolean ab;
    private float ac;
    private GregorianCalendar ad;
    private GregorianCalendar ae;
    private GregorianCalendar af;
    private String ag;
    private float ah;
    private boolean ai;
    private HwAdvancedNumberPicker aj;
    private GregorianCalendar ak;
    private LinearLayout al;
    private HwAdvancedNumberPicker am;
    private HwAdvancedNumberPicker an;
    private int ao;
    private int ap;
    private HwAdvancedNumberPicker aq;
    private Drawable ar;
    private boolean as;
    private String at;
    private boolean au;
    private final Context av;
    private HwLunarCalendar aw;
    private boolean ax;
    private final List<HwAdvancedNumberPicker> ay;
    private final String[] az;
    private String[] ba;
    private boolean bb;
    private String[] bc;
    private GestureDetector bd;
    private boolean be;
    private HwAdvancedNumberPicker.OnValueChangeListener bf;
    private GregorianCalendar bg;
    private HwAdvancedNumberPicker.OnColorChangeListener bh;
    private CompoundButton.OnCheckedChangeListener bi;
    private int bj;
    private String[] bk;
    private int bl;
    private int d;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private LinearLayout p;
    private int q;
    private int r;
    private int s;
    private int t;
    private HwCheckBox u;
    private int v;
    private int w;
    private Locale x;
    private int y;
    private GregorianCalendar z;
    private static final String[] e = {"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    private static final String[] c = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, "21", "22", "23"};
    private static final String[] b = {"00", "05", "10", "15", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, "25", HealthZonePushReceiver.SLEEP_SCORE_NOTIFY, HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY, HealthZonePushReceiver.FAMILY_CARE_NOTIFY, "45", "50", "55"};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f10627a = {"00", HiAnalyticsConstant.KeyAndValue.NUMBER_01, com.huawei.hms.ads.dynamic.a.t, "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, "21", "22", "23", "24", "25", "26", HealthZonePushReceiver.BLOOD_SUGAR_DAWN_NOTIFY, "28", HealthZonePushReceiver.SLEEP_TIME_NOTIFY, HealthZonePushReceiver.SLEEP_SCORE_NOTIFY, "31", HealthZonePushReceiver.PRESSURE_NOTIFY, HealthZonePushReceiver.CYCLE_BLOOD_OXYGEN_NOTIFY, HealthZonePushReceiver.BODY_TEMPERATURE_HIGH_NOTIFY, HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY, HealthZonePushReceiver.DATA_POST_COMMENTS_NOTIFY, HealthZonePushReceiver.PROACTIVE_SHARING_NOTIFY, HealthZonePushReceiver.COMMENT_FAILED_NOTIFY, "39", HealthZonePushReceiver.FAMILY_CARE_NOTIFY, HealthZonePushReceiver.SOS_LOCATION_NOTIFY, "42", HealthZonePushReceiver.ECG_MEASUREMENT_ABNORMAL, "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

    public interface OnDateChangedListener {
        void onDateChanged(HwDateAndTimePicker hwDateAndTimePicker, GregorianCalendar gregorianCalendar, String str);
    }

    static class a extends View.BaseSavedState {
        public static final Parcelable.Creator<a> CREATOR = new C0271a();

        /* renamed from: a, reason: collision with root package name */
        private int f10628a;
        private int b;
        private int c;
        private int d;
        private int e;

        /* renamed from: com.huawei.uikit.hwdateandtimepicker.widget.HwDateAndTimePicker$a$a, reason: collision with other inner class name */
        static final class C0271a implements Parcelable.Creator<a> {
            C0271a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public a[] newArray(int i) {
                return new a[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: ebO_, reason: merged with bridge method [inline-methods] */
            public a createFromParcel(Parcel parcel) {
                return new a(parcel, (b) null);
            }
        }

        /* synthetic */ a(Parcel parcel, b bVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            if (parcel != null) {
                parcel.writeInt(this.f10628a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.c);
                parcel.writeInt(this.d);
                parcel.writeInt(this.e);
            }
        }

        /* synthetic */ a(Parcelable parcelable, GregorianCalendar gregorianCalendar, b bVar) {
            this(parcelable, gregorianCalendar);
        }

        private a(Parcelable parcelable, GregorianCalendar gregorianCalendar) {
            super(parcelable);
            if (gregorianCalendar != null) {
                this.f10628a = gregorianCalendar.get(1);
                this.b = gregorianCalendar.get(2);
                this.c = gregorianCalendar.get(5);
                this.d = gregorianCalendar.get(11);
                this.e = gregorianCalendar.get(12);
            }
        }

        private a(Parcel parcel) {
            super(parcel);
            this.f10628a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
        }
    }

    class b implements HwAdvancedNumberPicker.OnValueChangeListener {
        b() {
        }

        @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnValueChangeListener
        public void onValueChange(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, int i2) {
            HwDateAndTimePicker.this.z.setTimeInMillis(HwDateAndTimePicker.this.af.getTimeInMillis());
            if (hwAdvancedNumberPicker == HwDateAndTimePicker.this.an) {
                if (HwDateAndTimePicker.this.ax) {
                    HwDateAndTimePicker.this.b(i, i2, 23, 1, 11);
                } else {
                    HwDateAndTimePicker.this.b(i, i2, 11, 1, 11);
                }
            } else if (hwAdvancedNumberPicker == HwDateAndTimePicker.this.aj) {
                if (HwDateAndTimePicker.this.bb) {
                    HwDateAndTimePicker.this.b(i, i2, 11, 5, 12);
                } else {
                    HwDateAndTimePicker.this.b(i, i2, 59, 1, 12);
                }
            } else if (hwAdvancedNumberPicker == HwDateAndTimePicker.this.am) {
                HwDateAndTimePicker.this.z.set(9, i2);
            } else {
                if (hwAdvancedNumberPicker != HwDateAndTimePicker.this.aq) {
                    Log.e("HwDateAndTimePicker", "onValueChange: Invalid picker.");
                    return;
                }
                int actualMaximum = HwDateAndTimePicker.this.z.getActualMaximum(5);
                if (i == actualMaximum && i2 == 1) {
                    HwDateAndTimePicker.this.z.add(5, 1);
                } else if (i == 1 && i2 == actualMaximum) {
                    HwDateAndTimePicker.this.z.add(5, -1);
                } else {
                    HwDateAndTimePicker.this.z.add(5, i2 - i);
                }
            }
            HwDateAndTimePicker hwDateAndTimePicker = HwDateAndTimePicker.this;
            hwDateAndTimePicker.d(hwDateAndTimePicker.z.get(1), HwDateAndTimePicker.this.z.get(2), HwDateAndTimePicker.this.z.get(5), HwDateAndTimePicker.this.z.get(11), HwDateAndTimePicker.this.z.get(12));
            HwDateAndTimePicker.this.q();
            HwDateAndTimePicker.this.h();
        }
    }

    class c implements HwAdvancedNumberPicker.OnColorChangeListener {
        c() {
        }

        @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnColorChangeListener
        public void onColorChange(HwAdvancedNumberPicker hwAdvancedNumberPicker) {
        }
    }

    class d extends GestureDetector.SimpleOnGestureListener {
        private d() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (motionEvent == null || !HwDateAndTimePicker.this.isEnabled()) {
                return false;
            }
            int[] iArr = new int[2];
            HwDateAndTimePicker.this.aj.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            float rawX = motionEvent.getRawX();
            if (rawX > i && i + HwDateAndTimePicker.this.aj.getWidth() > rawX && motionEvent.getRawY() < HwDateAndTimePicker.this.aj.getHeight() + i2) {
                float rawY = motionEvent.getRawY();
                if (rawY > ((HwDateAndTimePicker.this.aj.getHeight() / 2) + i2) - HwDateAndTimePicker.this.ac && rawY < i2 + (HwDateAndTimePicker.this.aj.getHeight() / 2) + HwDateAndTimePicker.this.ac) {
                    HwDateAndTimePicker.this.bb = !r0.bb;
                    HwDateAndTimePicker.this.s();
                    HwDateAndTimePicker.this.q();
                }
            }
            return super.onDoubleTapEvent(motionEvent);
        }

        /* synthetic */ d(HwDateAndTimePicker hwDateAndTimePicker, b bVar) {
            this();
        }
    }

    class e implements CompoundButton.OnCheckedChangeListener {
        e() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            HwDateAndTimePicker.this.au = !z;
            HwDateAndTimePicker.this.q();
            HwDateAndTimePicker.this.h();
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    class g implements View.OnClickListener {
        g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HwDateAndTimePicker.this.u.setChecked(!HwDateAndTimePicker.this.u.isChecked());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public HwDateAndTimePicker(Context context) {
        this(context, null);
    }

    private int a(int i) {
        if (i > 59) {
            return 59;
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    private void ab() {
        if (this.au) {
            this.k = 5000;
            this.o = 11;
            this.m = 31;
            this.q = 23;
            this.r = 30;
            this.s = 22;
            this.t = 11;
            this.w = 59;
            this.v = 10;
            this.y = 58;
            return;
        }
        this.k = 2101;
        this.o = 0;
        this.m = 28;
        this.q = 23;
        this.r = 27;
        this.s = 22;
        this.t = 11;
        this.w = 59;
        this.v = 10;
        this.y = 58;
    }

    private void ae() {
        this.bi = new e();
    }

    private void af() {
        this.bf = new b();
    }

    private void ag() {
        this.p = (LinearLayout) findViewById(R.id.hwdateandtimepicker_lunar_or_western);
        HwCheckBox hwCheckBox = (HwCheckBox) findViewById(R.id.hwdateandtimepicker_switch);
        this.u = hwCheckBox;
        hwCheckBox.setChecked(false);
        ((LinearLayout) findViewById(R.id.hwdateandtimepicker_switch_layout)).setOnClickListener(new g());
        this.u.setOnCheckedChangeListener(this.bi);
        if (this.ab && skj.b(this.av)) {
            return;
        }
        this.p.setVisibility(8);
    }

    private void ah() {
        this.bh = new c();
    }

    private void ai() {
        this.am.setMaxValue(1);
        this.am.setMinValue(0);
        if (this.ax) {
            this.an.setMaxValue(23);
            this.an.setMinValue(0);
        } else {
            this.an.setMaxValue(11);
            this.an.setMinValue(0);
        }
        this.an.setIsNeedStopDownScroll(false);
        if (this.bb) {
            this.aj.setMaxValue(11);
        } else {
            this.aj.setMaxValue(59);
        }
        this.aj.setIsNeedStopDownScroll(false);
    }

    private boolean aj() {
        String language = Locale.getDefault().getLanguage();
        return (language.contains("ar") || language.contains("fa") || language.contains("iw")) || (language.contains("ug") || language.contains(Constants.URDU_LANG)) || f();
    }

    private boolean ak() {
        return sms.b(this.av) != 1 || Float.compare(this.ah, 1.75f) < 0;
    }

    private boolean al() {
        return this.bb ? this.aj.getValue() == this.v : this.aj.getValue() == this.y;
    }

    private void am() {
        this.al = (LinearLayout) findViewById(R.id.hwdateandtimepicker_pickers);
        HwAdvancedNumberPicker hwAdvancedNumberPicker = (HwAdvancedNumberPicker) findViewById(R.id.hwdateandtimepicker_date);
        this.aq = hwAdvancedNumberPicker;
        hwAdvancedNumberPicker.setOnLongPressUpdateInterval(100L);
        this.aq.setOnValueChangedListener(this.bf);
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = (HwAdvancedNumberPicker) findViewById(R.id.hwdateandtimepicker_ampm);
        this.am = hwAdvancedNumberPicker2;
        hwAdvancedNumberPicker2.setOnValueChangedListener(this.bf);
        this.am.setOnLongPressUpdateInterval(100L);
        this.am.setDisplayedValues(this.az);
        this.am.setOnValueChangedListener(this.bf);
        if (this.ax) {
            this.am.setVisibility(8);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = (HwAdvancedNumberPicker) findViewById(R.id.hwdateandtimepicker_hour);
        this.an = hwAdvancedNumberPicker3;
        hwAdvancedNumberPicker3.setOnLongPressUpdateInterval(100L);
        this.an.setOnValueChangedListener(this.bf);
        this.an.setFormatter(HwAdvancedNumberPicker.c);
        HwAdvancedNumberPicker hwAdvancedNumberPicker4 = (HwAdvancedNumberPicker) findViewById(R.id.hwdateandtimepicker_minute);
        this.aj = hwAdvancedNumberPicker4;
        hwAdvancedNumberPicker4.setOnLongPressUpdateInterval(100L);
        this.aj.setOnValueChangedListener(this.bf);
        this.aj.setFormatter(HwAdvancedNumberPicker.c);
        this.aj.setMinValue(0);
        ai();
        this.aq.setFlingAnnounceType(2);
        this.am.setFlingAnnounceType(4);
        this.an.setFlingAnnounceType(3);
        this.aj.setFlingAnnounceType(3);
        setSpinnersShown(true);
    }

    private boolean an() {
        return this.bb ? this.aj.getValue() == this.t : this.aj.getValue() == this.w;
    }

    private boolean ar() {
        return (this.aq == null || this.aj == null || this.an == null || this.am == null) ? false : true;
    }

    private int b(int i) {
        if (i > 5000) {
            return 5000;
        }
        if (i < 1) {
            return 1;
        }
        return i;
    }

    private int c(int i) {
        if (i > 23) {
            return 23;
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    private int d(int i) {
        if (i > 31) {
            return 31;
        }
        if (i < 1) {
            return 1;
        }
        return i;
    }

    private int e(int i) {
        if (i > 11) {
            return 11;
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        u();
        sendAccessibilityEvent(4);
        String formatDateTime = DateUtils.formatDateTime(this.av, this.af.getTimeInMillis(), !skj.b(this.av) ? 98326 : 65558);
        n();
        OnDateChangedListener onDateChangedListener = this.aa;
        if (onDateChangedListener != null) {
            onDateChangedListener.onDateChanged(this, this.af, formatDateTime);
        }
    }

    private void k() {
        this.al.removeAllViews();
        this.al.addView(this.aq);
        if (skj.b(this.av) || Locale.getDefault().getLanguage().contains("bo")) {
            this.al.addView(this.am);
            this.al.addView(this.an);
            this.al.addView(this.aj);
        } else {
            this.al.addView(this.an);
            this.al.addView(this.aj);
            this.al.addView(this.am);
        }
    }

    private void l() {
        this.az[0] = DateUtils.getAMPMString(0);
        this.az[1] = DateUtils.getAMPMString(1);
    }

    private void m() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(this.af.getTimeInMillis());
        gregorianCalendar.add(6, -4);
        this.bg.setTimeInMillis(slu.c());
        for (int i = 0; i < 7; i++) {
            gregorianCalendar.add(6, 1);
            int i2 = !skj.b(this.av) ? 98328 : 65560;
            if (this.bg.get(1) == gregorianCalendar.get(1) && this.bg.get(2) == gregorianCalendar.get(2) && this.bg.get(5) == gregorianCalendar.get(5)) {
                this.bk[i] = this.at;
            } else if (this.au) {
                this.bk[i] = DateUtils.formatDateTime(this.av, gregorianCalendar.getTimeInMillis(), i2);
            } else {
                this.aw.e(gregorianCalendar.get(1), gregorianCalendar.get(2) + 1, gregorianCalendar.get(5));
                String d2 = this.aw.d();
                String c2 = this.aw.c();
                this.bk[i] = c2 + d2;
            }
            this.ag = this.bk[3];
        }
    }

    private void n() {
        String str = ",   " + this.ag + ", " + DateUtils.formatDateTime(this.av, this.af.getTimeInMillis(), this.ax ? 129 : 65);
        this.aq.setAnnouncedSuffix(str);
        this.an.setAnnouncedSuffix(str);
        this.aj.setAnnouncedSuffix(str);
        this.am.setAnnouncedSuffix(str);
        this.aq.setAccessibilityOptimizationEnabled(true);
        this.an.setAccessibilityOptimizationEnabled(true);
        this.aj.setAccessibilityOptimizationEnabled(true);
        this.am.setAccessibilityOptimizationEnabled(true);
    }

    private void o() {
        setPickersPercentage(0);
    }

    private void p() {
        this.an.setWrapSelectorWheel(true);
        this.aj.setWrapSelectorWheel(true);
        if (this.ax) {
            String[] strArr = (String[]) c.clone();
            this.ba = strArr;
            this.an.setDisplayedValues(strArr);
            this.an.setValue(this.af.get(11));
            this.am.setValue(this.af.get(9));
        } else {
            String[] strArr2 = (String[]) e.clone();
            this.ba = strArr2;
            this.an.setDisplayedValues(strArr2);
            this.an.setValue(this.af.get(10));
            this.am.setValue(this.af.get(9));
        }
        if (this.bb) {
            String[] strArr3 = (String[]) b.clone();
            this.bc = strArr3;
            this.aj.setDisplayedValues(strArr3);
            int intValue = new BigDecimal((this.af.get(12) / 5.0f) + "").setScale(0, 4).intValue();
            this.aj.setValue(intValue);
            int i = intValue * 5;
            boolean z = i == 60;
            if (this.ai || !z) {
                this.af.set(12, i);
            } else {
                this.af.set(12, 0);
            }
        } else {
            String[] strArr4 = (String[]) f10627a.clone();
            this.bc = strArr4;
            this.aj.setDisplayedValues(strArr4);
            this.aj.setValue(this.af.get(12));
        }
        l();
        this.am.setDisplayedValues(this.az);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (ar()) {
            u();
            x();
            p();
            this.aq.setMinValue(0);
            this.aq.setMaxValue(6);
            this.aq.setDisplayedValues(null);
            m();
            y();
            this.aq.setValue(3);
            this.aq.setDisplayedValues(this.bk);
            this.aq.setWrapSelectorWheel(false);
            this.an.postInvalidate();
            this.aj.postInvalidate();
            this.am.postInvalidate();
            this.aq.postInvalidate();
            this.z.setTimeInMillis(this.af.getTimeInMillis());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (!ar() || this.z == null) {
            return;
        }
        if (!this.bb) {
            this.aj.setDisplayedValues(null);
            this.bc = (String[]) f10627a.clone();
            this.aj.setMinValue(0);
            this.aj.setMaxValue(59);
            this.aj.setDisplayedValues(this.bc);
            this.aj.setValue(this.af.get(12));
            this.z.set(12, this.af.get(12));
            return;
        }
        this.aj.setDisplayedValues(null);
        this.bc = (String[]) b.clone();
        this.aj.setMinValue(0);
        this.aj.setMaxValue(11);
        this.aj.setDisplayedValues(this.bc);
        int intValue = new BigDecimal((this.af.get(12) / 5.0f) + "").setScale(0, 4).intValue();
        int i = intValue * 5;
        boolean z = i == 60;
        if (this.ai || !z) {
            this.z.set(12, i);
            this.af.set(12, i);
        } else {
            this.z.set(12, 0);
            this.af.set(12, 0);
        }
        this.aj.setValue(intValue);
        h();
    }

    private void setCurrentLocale(Locale locale) {
        if (locale == null || locale.equals(this.x)) {
            return;
        }
        this.x = locale;
        this.z = d(this.z, locale);
        this.ad = d(this.ad, locale);
        this.ae = d(this.ae, locale);
        this.af = d(this.af, locale);
        String[] strArr = new String[31];
        String bestDateTimePattern = DateFormat.getBestDateTimePattern(locale, FitRunPlayAudio.PLAY_TYPE_D);
        Object clone = this.z.clone();
        if (clone instanceof GregorianCalendar) {
            GregorianCalendar gregorianCalendar = (GregorianCalendar) clone;
            int i = 0;
            gregorianCalendar.set(2, 0);
            while (i < 31) {
                int i2 = i + 1;
                gregorianCalendar.set(5, i2);
                strArr[i] = DateFormat.format(bestDateTimePattern, gregorianCalendar).toString();
                i = i2;
            }
        }
    }

    private void setMaxDate(long j) {
        this.z.setTimeInMillis(j);
        this.ae.setTimeInMillis(j);
        if (this.af.after(this.ae)) {
            this.af.setTimeInMillis(this.ae.getTimeInMillis());
        }
        q();
    }

    private void setMinDate(long j) {
        this.z.setTimeInMillis(j);
        this.ad.setTimeInMillis(j);
        if (this.af.before(this.ad)) {
            this.af.setTimeInMillis(this.ad.getTimeInMillis());
        }
        q();
    }

    private void setPickersPercentage(int i) {
        if (ar()) {
            if (!this.ax) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
                layoutParams.weight = 4.0f;
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1);
                layoutParams2.weight = 3.0f;
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -1);
                layoutParams3.weight = 2.0f;
                this.aq.setLayoutParams(layoutParams);
                this.aj.setLayoutParams(layoutParams3);
                this.an.setLayoutParams(layoutParams3);
                this.am.setLayoutParams(layoutParams2);
                return;
            }
            this.am.setVisibility(8);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -1);
            layoutParams4.weight = 3.0f;
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, -1);
            layoutParams5.weight = 2.0f;
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, -1);
            layoutParams6.weight = 0.0f;
            this.aq.setLayoutParams(layoutParams4);
            this.aj.setLayoutParams(layoutParams5);
            this.an.setLayoutParams(layoutParams5);
            this.am.setLayoutParams(layoutParams6);
        }
    }

    private void setSpinnersMiddleDrawable(int i) {
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.aq;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setMiddleStateDrawable(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.am;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setMiddleStateDrawable(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.an;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setMiddleStateDrawable(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker4 = this.aj;
        if (hwAdvancedNumberPicker4 != null) {
            hwAdvancedNumberPicker4.setMiddleStateDrawable(i);
        }
    }

    private void setSpinnersSelectionDividerHeight(int i) {
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.aq;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSelectionDividerHeight(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.am;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSelectionDividerHeight(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.an;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSelectionDividerHeight(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker4 = this.aj;
        if (hwAdvancedNumberPicker4 != null) {
            hwAdvancedNumberPicker4.setSelectionDividerHeight(i);
        }
    }

    private void z() {
        if (this.au) {
            this.bj = 1;
            this.bl = 0;
            this.d = 1;
            this.g = 0;
            this.j = 0;
            this.f = 1;
            this.i = 0;
            this.h = 2;
            this.l = 1;
            this.n = this.bb ? 5 : 1;
            return;
        }
        this.bj = 1900;
        this.bl = 0;
        this.d = 31;
        this.g = 0;
        this.j = 0;
        this.f = 1900;
        this.i = 1;
        this.h = 1;
        this.l = 1;
        this.n = this.bb ? 5 : 1;
    }

    public void a(int i, int i2, int i3, int i4, int i5) {
        int b2 = b(i);
        int e2 = e(i2);
        int d2 = d(i3);
        int c2 = c(i4);
        int a2 = a(i5);
        if (e(b2, e2, d2, c2, a2)) {
            d(b2, e2, d2, c2, a2);
            q();
            h();
        }
    }

    public boolean c() {
        return this.be;
    }

    public final void d(GregorianCalendar gregorianCalendar, OnDateChangedListener onDateChangedListener) {
        if (gregorianCalendar != null) {
            d(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5), gregorianCalendar.get(11), gregorianCalendar.get(12));
            q();
        }
        if (onDateChangedListener != null) {
            this.aa = onDateChangedListener;
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return true;
        }
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return true;
        }
        GestureDetector gestureDetector = this.bd;
        if (gestureDetector == null || !gestureDetector.onTouchEvent(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    public void e() {
        setDialogStyle();
        boolean z = getResources().getConfiguration().orientation == 2;
        this.aq.a(z);
        this.am.a(z);
        this.an.a(z);
        this.aj.a(z);
    }

    public boolean f() {
        return getLayoutDirection() == 1;
    }

    public boolean g() {
        return this.au;
    }

    public GregorianCalendar getCustomArbitraryUpperBounds() {
        return this.ak;
    }

    public int getDayOfMonth() {
        GregorianCalendar gregorianCalendar = this.af;
        if (gregorianCalendar == null) {
            return 1;
        }
        return gregorianCalendar.get(5);
    }

    public int getHour() {
        GregorianCalendar gregorianCalendar = this.af;
        if (gregorianCalendar == null) {
            return 0;
        }
        return gregorianCalendar.get(11);
    }

    public int getMinute() {
        GregorianCalendar gregorianCalendar = this.af;
        if (gregorianCalendar == null) {
            return 0;
        }
        return gregorianCalendar.get(12);
    }

    public int getMonth() {
        GregorianCalendar gregorianCalendar = this.af;
        if (gregorianCalendar == null) {
            return 1;
        }
        return gregorianCalendar.get(2);
    }

    public Drawable getSpinnersSelectionDivider() {
        return this.ar;
    }

    public int getSpinnersSelectorPaintColor() {
        return this.ao;
    }

    public int getSpinnersUnselectedPaintColor() {
        return this.ap;
    }

    public int getYear() {
        GregorianCalendar gregorianCalendar = this.af;
        if (gregorianCalendar == null) {
            return 1;
        }
        return gregorianCalendar.get(1);
    }

    public boolean i() {
        return this.bb;
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.as;
    }

    @Override // android.view.View
    public boolean isHapticFeedbackEnabled() {
        return super.isHapticFeedbackEnabled();
    }

    public boolean j() {
        return this.ab;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.bd = new GestureDetector(getContext().getApplicationContext(), new d(this, null));
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        if (configuration == null) {
            return;
        }
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(this.av, this.af.getTimeInMillis(), 20));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof a) {
            a aVar = (a) parcelable;
            super.onRestoreInstanceState(aVar.getSuperState());
            d(aVar.f10628a, aVar.b, aVar.c, aVar.d, aVar.e);
            q();
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return new a(super.onSaveInstanceState(), this.af, null);
    }

    public void setCustomArbitraryUpperBounds(GregorianCalendar gregorianCalendar) {
        if (gregorianCalendar == null) {
            return;
        }
        this.ak = gregorianCalendar;
        gregorianCalendar.set(13, 0);
        this.ak.set(14, 0);
        q();
        h();
    }

    public void setDialogStyle() {
        Context context = this.av;
        if (context == null || this.p == null) {
            return;
        }
        if (this.ab && skj.b(context)) {
            this.p.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.p.setVisibility(0);
        } else {
            this.p.setVisibility(8);
        }
        o();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (!ar() || this.as == z) {
            return;
        }
        this.aq.setEnabled(z);
        this.am.setEnabled(z);
        this.aj.setEnabled(z);
        this.an.setEnabled(z);
        this.as = z;
    }

    @Override // android.view.View
    public void setHapticFeedbackEnabled(boolean z) {
        super.setHapticFeedbackEnabled(z);
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.aj;
        if (hwAdvancedNumberPicker == null || this.am == null || this.aq == null || this.an == null) {
            return;
        }
        hwAdvancedNumberPicker.setHapticFeedbackEnabled(z);
        this.am.setHapticFeedbackEnabled(z);
        this.aq.setHapticFeedbackEnabled(z);
        this.an.setHapticFeedbackEnabled(z);
    }

    public void setIsFromToday(boolean z) {
        this.be = z;
        q();
    }

    public void setIsLunarEnabled(boolean z) {
        HwCheckBox hwCheckBox;
        this.ab = z;
        if (!z && (hwCheckBox = this.u) != null) {
            hwCheckBox.setChecked(false);
        }
        setDialogStyle();
    }

    public void setIsMinuteIntervalFiveMinute(boolean z) {
        this.bb = z;
        GregorianCalendar gregorianCalendar = this.af;
        if (gregorianCalendar != null) {
            gregorianCalendar.setTimeInMillis(slu.c());
        }
        s();
        q();
    }

    public void setLinkageScrollEnabled(boolean z) {
        this.ai = z;
    }

    public void setLunarOrWestern(boolean z) {
        if (this.u != null) {
            if (this.ab && skj.b(this.av)) {
                this.u.setChecked(z);
            } else {
                this.u.setChecked(false);
            }
        }
    }

    public void setSpinnersSelectionDivider(Drawable drawable) {
        Drawable drawable2 = this.ar;
        if (drawable2 == null || !drawable2.equals(drawable)) {
            this.ar = drawable;
            HwAdvancedNumberPicker hwAdvancedNumberPicker = this.aq;
            if (hwAdvancedNumberPicker != null) {
                hwAdvancedNumberPicker.setSelectionDivider(drawable);
            }
            HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.am;
            if (hwAdvancedNumberPicker2 != null) {
                hwAdvancedNumberPicker2.setSelectionDivider(drawable);
            }
            HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.an;
            if (hwAdvancedNumberPicker3 != null) {
                hwAdvancedNumberPicker3.setSelectionDivider(drawable);
            }
            HwAdvancedNumberPicker hwAdvancedNumberPicker4 = this.aj;
            if (hwAdvancedNumberPicker4 != null) {
                hwAdvancedNumberPicker4.setSelectionDivider(drawable);
            }
        }
    }

    public void setSpinnersSelectorPaintColor(int i) {
        if (this.ao == i) {
            return;
        }
        this.ao = i;
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.aq;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSelectorPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.am;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSelectorPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.an;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSelectorPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker4 = this.aj;
        if (hwAdvancedNumberPicker4 != null) {
            hwAdvancedNumberPicker4.setSelectorPaintColor(i);
        }
    }

    public final void setSpinnersShown(boolean z) {
        LinearLayout linearLayout = this.al;
        if (linearLayout != null) {
            linearLayout.setVisibility(z ? 0 : 8);
        }
    }

    public void setSpinnersUnselectedPaintColor(int i) {
        if (this.ap == i) {
            return;
        }
        this.ap = i;
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.aq;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSecondPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.am;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSecondPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.an;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSecondPaintColor(i);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker4 = this.aj;
        if (hwAdvancedNumberPicker4 != null) {
            hwAdvancedNumberPicker4.setSecondPaintColor(i);
        }
    }

    public HwDateAndTimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100271_res_0x7f06026f);
    }

    private void aa() {
        this.z.clear();
        this.z.set(1, 0, 1);
        this.af.setTimeInMillis(slu.c());
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        this.bg = gregorianCalendar;
        gregorianCalendar.setTimeInMillis(this.af.getTimeInMillis());
        setMinDate(this.z.getTimeInMillis());
        this.z.clear();
        this.z.set(5000, 11, 31, 23, 59);
        setMaxDate(this.z.getTimeInMillis());
        this.af.setTimeInMillis(slu.c());
        h();
    }

    private void ac() {
        if (this.be) {
            ad();
        } else if (this.ak != null) {
            v();
        } else {
            Log.d("HwDateAndTimePicker", "No custom upper bounds.");
        }
    }

    private void ad() {
        this.bg.setTimeInMillis(slu.c());
        if (this.bg.get(1) < this.bj) {
            return;
        }
        this.bj = this.bg.get(1);
        this.bl = this.bg.get(2);
        this.d = this.bg.get(5);
        this.g = 0;
        this.j = 0;
        this.bg.add(5, 1);
        this.f = this.bg.get(1);
        this.i = this.bg.get(2);
        this.h = this.bg.get(5);
        this.l = 1;
        this.n = this.bb ? 5 : 1;
    }

    private void r() {
        if (this.ax || this.g <= 11) {
            return;
        }
        this.am.setMaxValue(0);
        this.az[0] = DateUtils.getAMPMString(1);
        this.am.setDisplayedValues(this.az);
    }

    private void t() {
        this.ay.add(this.an);
        this.ay.add(this.aj);
        this.ay.add(this.am);
        this.ay.add(this.aq);
        this.an.setOnColorChangeListener(this.bh);
        this.aj.setOnColorChangeListener(this.bh);
        this.am.setOnColorChangeListener(this.bh);
        this.aq.setOnColorChangeListener(this.bh);
        String language = Locale.getDefault().getLanguage();
        if (!language.contains("ar") && !language.contains("fa") && !language.contains("iw")) {
            k();
        }
        if (this.al != null && aj()) {
            this.al.removeAllViews();
            this.al.addView(this.aq);
            this.al.addView(this.aj);
            this.al.addView(this.an);
            this.al.addView(this.am);
        }
        if (this.al != null) {
            if (language.contains(Constants.URDU_LANG) || language.contains("ug")) {
                this.al.removeAllViews();
                this.al.addView(this.aq);
                this.al.addView(this.am);
                this.al.addView(this.aj);
                this.al.addView(this.an);
            }
        }
    }

    private void u() {
        if (this.be) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTimeInMillis(slu.c());
            gregorianCalendar.set(11, 0);
            gregorianCalendar.set(12, 0);
            gregorianCalendar.set(13, 0);
            gregorianCalendar.set(14, 0);
            if (this.af.before(gregorianCalendar)) {
                this.af.setTimeInMillis(gregorianCalendar.getTimeInMillis());
            }
        }
        if (this.au) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, 0, 1);
            if (this.af.before(calendar)) {
                this.af.set(1, 0, 1);
                return;
            } else {
                calendar.set(5000, 11, 31, 23, 59);
                d(calendar);
                return;
            }
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1900, 0, 31);
        if (this.af.before(calendar2)) {
            this.af.set(1900, 0, 31);
        } else {
            calendar2.set(2101, 0, 28, 23, 59);
            c(calendar2);
        }
    }

    private void v() {
        int i;
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(this.bj, this.bl, this.d, this.g, this.j, 0);
        gregorianCalendar.set(14, 0);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.set(this.k, this.o, this.m, this.q, this.w, 0);
        gregorianCalendar2.set(14, 0);
        if (this.ak.before(gregorianCalendar) || this.ak.after(gregorianCalendar2)) {
            return;
        }
        int i2 = this.ak.get(12);
        if (this.bb) {
            i = new BigDecimal((i2 / 5.0f) + "").setScale(0, 0).intValue() * 5;
        } else {
            i = this.ak.get(12);
        }
        GregorianCalendar gregorianCalendar3 = new GregorianCalendar();
        gregorianCalendar3.setTimeInMillis(this.ak.getTimeInMillis());
        gregorianCalendar3.set(12, i);
        gregorianCalendar3.set(13, 0);
        gregorianCalendar3.set(14, 0);
        if (gregorianCalendar3.after(gregorianCalendar2)) {
            gregorianCalendar3.add(12, -5);
        }
        if (this.af.before(gregorianCalendar3)) {
            this.af.setTimeInMillis(gregorianCalendar3.getTimeInMillis());
        }
        this.bj = gregorianCalendar3.get(1);
        this.bl = gregorianCalendar3.get(2);
        this.d = gregorianCalendar3.get(5);
        this.g = gregorianCalendar3.get(11);
        this.j = gregorianCalendar3.get(12);
        gregorianCalendar3.add(5, 1);
        this.f = gregorianCalendar3.get(1);
        this.i = gregorianCalendar3.get(2);
        this.h = gregorianCalendar3.get(5);
        gregorianCalendar3.add(10, 1);
        this.l = gregorianCalendar3.get(11);
        if (this.bb) {
            gregorianCalendar3.add(12, 5);
        } else {
            gregorianCalendar3.add(12, 1);
        }
        this.n = gregorianCalendar3.get(12);
    }

    private void w() {
        if (this.af.get(11) == this.q) {
            String[] strArr = this.ba;
            strArr[0] = "";
            strArr[1] = "";
            this.an.setWrapSelectorWheel(false);
            if (an()) {
                String[] strArr2 = this.bc;
                strArr2[0] = "";
                strArr2[1] = "";
                this.aj.setWrapSelectorWheel(false);
            } else if (al()) {
                this.bc[0] = "";
            } else {
                Log.d("HwDateAndTimePicker", "The selected minute is before end boundary.");
            }
            this.aj.setDisplayedValues(this.bc);
        } else if (this.af.get(11) == this.s) {
            this.ba[0] = "";
        } else {
            Log.d("HwDateAndTimePicker", "The selected hour is before end boundary.");
        }
        this.an.setDisplayedValues(this.ba);
    }

    private void x() {
        z();
        ab();
        ac();
    }

    private void y() {
        boolean z;
        ai();
        if (this.af.get(1) == this.bj && this.af.get(2) == this.bl && this.af.get(5) == this.d) {
            String[] strArr = this.bk;
            this.bk = new String[]{strArr[3], strArr[4], strArr[5], "", "", "", ""};
            this.aq.setMinValue(3);
            this.aq.setWrapSelectorWheel(false);
            r();
            c(true);
            z = true;
        } else {
            if (this.af.get(1) == this.f && this.af.get(2) == this.i && this.af.get(5) == this.h) {
                String[] strArr2 = this.bk;
                strArr2[0] = "";
                strArr2[1] = "";
                if (this.l == 0) {
                    c(false);
                }
            } else {
                Log.d("HwDateAndTimePicker", "The selected date is before end boundary.");
            }
            z = false;
        }
        if (this.af.get(1) == this.k && this.af.get(2) == this.o && this.af.get(5) == this.m) {
            String[] strArr3 = this.bk;
            strArr3[4] = "";
            strArr3[5] = "";
            strArr3[6] = "";
            this.aq.setMaxValue(3);
            this.aq.setWrapSelectorWheel(false);
            w();
            return;
        }
        if (this.af.get(1) != this.k || this.af.get(2) != this.o || this.af.get(5) != this.r) {
            Log.d("HwDateAndTimePicker", "The selected date is before end boundary.");
        } else {
            if (z) {
                this.bk[2] = "";
                return;
            }
            String[] strArr4 = this.bk;
            strArr4[5] = "";
            strArr4[6] = "";
        }
    }

    void b() {
        if (ak()) {
            return;
        }
        this.aq.setVisibility(0);
        this.an.setVisibility(8);
        this.aj.setVisibility(8);
        if (this.ax) {
            this.am.setVisibility(8);
        } else {
            this.am.setVisibility(0);
        }
    }

    public HwDateAndTimePicker(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        this.ag = "";
        this.as = true;
        this.au = true;
        this.av = getContext();
        this.az = new String[2];
        this.ay = new ArrayList(4);
        this.bb = true;
        this.bk = new String[7];
        this.ab = true;
        Context context2 = super.getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100575_res_0x7f06039f});
        if (obtainStyledAttributes != null) {
            int resourceId = obtainStyledAttributes.getResourceId(0, 0);
            context2 = resourceId != 0 ? new ContextThemeWrapper(super.getContext(), resourceId) : context2;
            obtainStyledAttributes.recycle();
        }
        d(context2);
        ebE_(context2, attributeSet, i);
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwDateAndTimePicker);
    }

    void d() {
        if (ak()) {
            return;
        }
        this.aq.setVisibility(8);
        this.an.setVisibility(0);
        this.aj.setVisibility(0);
        this.am.setVisibility(8);
    }

    private void d(Context context) {
        this.ax = DateFormat.is24HourFormat(context);
        setCurrentLocale(Locale.getDefault());
        l();
        this.at = getResources().getString(R.string._2130851236_res_0x7f0235a4);
        this.ac = getResources().getDimension(R.dimen._2131363874_res_0x7f0a0822);
        this.aw = new HwLunarCalendar(context);
        this.ah = smp.a(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            layoutInflater.cloneInContext(context).inflate(R.layout.hwdateandtimepicker, (ViewGroup) this, true);
            af();
            ah();
            ae();
            am();
            ag();
            aa();
            d(this.af, (OnDateChangedListener) null);
            t();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, int i3, int i4, int i5) {
        if (!this.ai) {
            if (i5 == 11 && !this.ax && this.z.get(9) == 1) {
                this.z.set(i5, (i2 * i4) + 12);
                return;
            } else {
                this.z.set(i5, i2 * i4);
                return;
            }
        }
        if (i2 == 0 && i == i3) {
            this.z.add(i5, i4);
        } else if (i == 0 && i2 == i3) {
            this.z.add(i5, -i4);
        } else {
            this.z.add(i5, (i2 - i) * i4);
        }
    }

    private void d(Calendar calendar) {
        if (calendar != null && this.af.after(calendar)) {
            if (this.bb) {
                this.af.set(5000, 11, 31, 23, 55);
            } else {
                this.af.set(5000, 11, 31, 23, 59);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2, int i3, int i4, int i5) {
        GregorianCalendar gregorianCalendar = this.af;
        if (gregorianCalendar != null) {
            gregorianCalendar.set(i, i2, i3, i4, i5);
            u();
        }
    }

    private void d(boolean z) {
        int d2;
        if (this.bb) {
            d2 = d((this.j / 5) - 1, this.bc.length);
        } else {
            d2 = d(this.j - 1, this.bc.length);
        }
        int d3 = d(d2 - 1, this.bc.length);
        int d4 = d(d3 - 1, this.bc.length);
        if (z && this.af.get(12) == this.j) {
            if (d2 >= 0) {
                String[] strArr = this.bc;
                if (d2 < strArr.length) {
                    strArr[d2] = "";
                }
            }
            if (d3 >= 0) {
                String[] strArr2 = this.bc;
                if (d3 < strArr2.length) {
                    strArr2[d3] = "";
                }
            }
            if (d4 >= 0) {
                String[] strArr3 = this.bc;
                if (d4 < strArr3.length) {
                    strArr3[d4] = "";
                }
            }
            this.aj.setIsNeedStopDownScroll(true);
        } else if (this.af.get(12) != this.n) {
            Log.d("HwDateAndTimePicker", "The selected minute is after start boundary.");
        } else if (d2 >= 0) {
            String[] strArr4 = this.bc;
            if (d2 < strArr4.length) {
                strArr4[d2] = "";
            }
        }
        this.aj.setDisplayedValues(this.bc);
    }

    private boolean e(int i, int i2) {
        return (this.af.get(11) == i && this.af.get(12) == i2) ? false : true;
    }

    private void ebE_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.enabled, R.attr._2131100178_res_0x7f060212, R.attr._2131100179_res_0x7f060213, R.attr._2131100194_res_0x7f060222, R.attr._2131100223_res_0x7f06023f, R.attr._2131100254_res_0x7f06025e, R.attr._2131100273_res_0x7f060271, R.attr._2131100418_res_0x7f060302, R.attr._2131100420_res_0x7f060304, R.attr._2131100573_res_0x7f06039d}, i, 0);
        try {
            try {
                setEnabled(obtainStyledAttributes.getBoolean(0, true));
                setSpinnersSelectionDivider(obtainStyledAttributes.getDrawable(7));
                setSpinnersSelectionDividerHeight(obtainStyledAttributes.getDimensionPixelSize(8, 0));
            } catch (Resources.NotFoundException unused) {
                Log.w("HwDateAndTimePicker", "TypedArray get resource error");
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    void a() {
        if (ak()) {
            return;
        }
        for (HwAdvancedNumberPicker hwAdvancedNumberPicker : this.ay) {
            hwAdvancedNumberPicker.setAuxiliaryUnselectedTextSize(28.0f);
            hwAdvancedNumberPicker.setAuxiliarySelectedTextSize(35.0f);
        }
        if (Float.compare(this.ah, 2.0f) >= 0) {
            this.u.setTextSize(1, 32.0f);
        }
    }

    private int d(int i, int i2) {
        return i - (b(i, i2) * i2);
    }

    private void c(Calendar calendar) {
        if (calendar != null && this.af.after(calendar)) {
            if (this.bb) {
                this.af.set(2101, 0, 28, 23, 55);
            } else {
                this.af.set(2101, 0, 28, 23, 59);
            }
        }
    }

    private GregorianCalendar d(GregorianCalendar gregorianCalendar, Locale locale) {
        if (gregorianCalendar == null && locale == null) {
            return new GregorianCalendar(Locale.ENGLISH);
        }
        if (gregorianCalendar == null) {
            return new GregorianCalendar(locale);
        }
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar(locale);
        gregorianCalendar2.setTimeInMillis(timeInMillis);
        return gregorianCalendar2;
    }

    private boolean e(int i, int i2, int i3, int i4, int i5) {
        return (this.af.get(1) == i && this.af.get(2) == i3 && this.af.get(5) == i2 && !e(i4, i5)) ? false : true;
    }

    private void c(boolean z) {
        int d2 = d(this.g - 1, this.ba.length);
        int d3 = d(d2 - 1, this.ba.length);
        int d4 = d(d3 - 1, this.ba.length);
        if (z && this.af.get(11) == this.g) {
            if (d2 >= 0) {
                String[] strArr = this.ba;
                if (d2 < strArr.length) {
                    strArr[d2] = "";
                }
            }
            if (d3 >= 0) {
                String[] strArr2 = this.ba;
                if (d3 < strArr2.length) {
                    strArr2[d3] = "";
                }
            }
            if (d4 >= 0) {
                String[] strArr3 = this.ba;
                if (d4 < strArr3.length) {
                    strArr3[d4] = "";
                }
            }
            this.an.setIsNeedStopDownScroll(true);
            d(true);
        } else if (this.af.get(11) == this.l) {
            if (d2 >= 0) {
                String[] strArr4 = this.ba;
                if (d2 < strArr4.length) {
                    strArr4[d2] = "";
                }
            }
            if (this.n == 0) {
                d(false);
            }
        } else {
            Log.d("HwDateAndTimePicker", "The selected hour is after start boundary.");
        }
        this.an.setDisplayedValues(this.ba);
    }

    private int b(int i, int i2) {
        int i3;
        if (i2 != 0) {
            i3 = i / i2;
        } else {
            Log.w("HwDateAndTimePicker", "denominator is invalid.");
            i3 = i;
        }
        return ((i ^ i2) >= 0 || i2 * i3 == i) ? i3 : i3 - 1;
    }
}
