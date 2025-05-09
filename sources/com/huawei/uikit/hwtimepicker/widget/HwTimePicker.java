package com.huawei.uikit.hwtimepicker.widget;

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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker;
import defpackage.skj;
import defpackage.smp;
import defpackage.smr;
import defpackage.sms;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwTimePicker extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    protected HwAdvancedNumberPicker f10765a;
    private float ab;
    private HwAdvancedNumberPicker.OnColorChangeListener ac;
    private boolean ad;
    protected boolean b;
    protected HwAdvancedNumberPicker c;
    protected HwAdvancedNumberPicker e;
    private Locale g;
    private OnTimeChangedListener h;
    private GregorianCalendar k;
    private int l;
    private GregorianCalendar m;
    private int n;
    private LinearLayout o;
    private Drawable p;
    private String[] q;
    private boolean r;
    private final String[] s;
    private final Context t;
    private boolean u;
    private final List<HwAdvancedNumberPicker> v;
    private HwAdvancedNumberPicker.OnValueChangeListener w;
    private GestureDetector x;
    private String[] y;
    private float z;
    private static final String[] d = {"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    private static final String[] i = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, "21", "22", "23"};
    private static final String[] j = {"00", "05", "10", "15", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, "25", HealthZonePushReceiver.SLEEP_SCORE_NOTIFY, HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY, HealthZonePushReceiver.FAMILY_CARE_NOTIFY, "45", "50", "55"};
    private static final String[] f = {"00", HiAnalyticsConstant.KeyAndValue.NUMBER_01, com.huawei.hms.ads.dynamic.a.t, "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, "21", "22", "23", "24", "25", "26", HealthZonePushReceiver.BLOOD_SUGAR_DAWN_NOTIFY, "28", HealthZonePushReceiver.SLEEP_TIME_NOTIFY, HealthZonePushReceiver.SLEEP_SCORE_NOTIFY, "31", HealthZonePushReceiver.PRESSURE_NOTIFY, HealthZonePushReceiver.CYCLE_BLOOD_OXYGEN_NOTIFY, HealthZonePushReceiver.BODY_TEMPERATURE_HIGH_NOTIFY, HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY, HealthZonePushReceiver.DATA_POST_COMMENTS_NOTIFY, HealthZonePushReceiver.PROACTIVE_SHARING_NOTIFY, HealthZonePushReceiver.COMMENT_FAILED_NOTIFY, "39", HealthZonePushReceiver.FAMILY_CARE_NOTIFY, HealthZonePushReceiver.SOS_LOCATION_NOTIFY, "42", HealthZonePushReceiver.ECG_MEASUREMENT_ABNORMAL, "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

    public interface OnTimeChangedListener {
        void onTimeChanged(HwTimePicker hwTimePicker, GregorianCalendar gregorianCalendar, String str);
    }

    static class a extends View.BaseSavedState {
        public static final Parcelable.Creator<a> CREATOR = new b();

        /* renamed from: a, reason: collision with root package name */
        private int f10766a;
        private int b;

        static final class b implements Parcelable.Creator<a> {
            b() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public a[] newArray(int i) {
                return new a[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: eik_, reason: merged with bridge method [inline-methods] */
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
                parcel.writeInt(this.f10766a);
                parcel.writeInt(this.b);
            }
        }

        /* synthetic */ a(Parcelable parcelable, GregorianCalendar gregorianCalendar, b bVar) {
            this(parcelable, gregorianCalendar);
        }

        private a(Parcelable parcelable, GregorianCalendar gregorianCalendar) {
            super(parcelable);
            if (gregorianCalendar != null) {
                this.f10766a = gregorianCalendar.get(11);
                this.b = gregorianCalendar.get(12);
            }
        }

        private a(Parcel parcel) {
            super(parcel);
            if (parcel != null) {
                this.f10766a = parcel.readInt();
                this.b = parcel.readInt();
            }
        }
    }

    class b implements HwAdvancedNumberPicker.OnValueChangeListener {
        b() {
        }

        @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnValueChangeListener
        public void onValueChange(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i, int i2) {
            HwTimePicker.this.m.clear();
            HwTimePicker.this.m.setTimeInMillis(HwTimePicker.this.k.getTimeInMillis());
            HwTimePicker hwTimePicker = HwTimePicker.this;
            if (hwAdvancedNumberPicker == hwTimePicker.e) {
                if (hwTimePicker.b) {
                    hwTimePicker.b(i, i2, 23, 1, 11);
                } else {
                    hwTimePicker.b(i, i2, 11, 1, 11);
                }
            } else if (hwAdvancedNumberPicker == hwTimePicker.c) {
                if (hwTimePicker.u) {
                    HwTimePicker.this.b(i, i2, 11, 5, 12);
                } else {
                    HwTimePicker.this.b(i, i2, 59, 1, 12);
                }
            } else {
                if (hwAdvancedNumberPicker != hwTimePicker.f10765a) {
                    Log.w("HwTimePicker", "onValueChange: IllegalArgumentException");
                    return;
                }
                hwTimePicker.m.set(9, i2);
            }
            HwTimePicker hwTimePicker2 = HwTimePicker.this;
            hwTimePicker2.e(hwTimePicker2.m.get(1), HwTimePicker.this.m.get(2), HwTimePicker.this.m.get(5), HwTimePicker.this.m.get(11), HwTimePicker.this.m.get(12));
            HwTimePicker.this.q();
            HwTimePicker.this.o();
        }
    }

    class d implements HwAdvancedNumberPicker.OnColorChangeListener {
        d() {
        }

        @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnColorChangeListener
        public void onColorChange(HwAdvancedNumberPicker hwAdvancedNumberPicker) {
        }
    }

    class e extends GestureDetector.SimpleOnGestureListener {
        private e() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (motionEvent == null || !HwTimePicker.this.isEnabled()) {
                return false;
            }
            int[] iArr = new int[2];
            HwTimePicker.this.c.getLocationOnScreen(iArr);
            if (motionEvent.getRawX() > iArr[0] && motionEvent.getRawX() < r0 + HwTimePicker.this.c.getWidth()) {
                float rawY = motionEvent.getRawY();
                int i = iArr[1];
                if (rawY > ((HwTimePicker.this.c.getHeight() / 2) + i) - HwTimePicker.this.ab && rawY < i + (HwTimePicker.this.c.getHeight() / 2) + HwTimePicker.this.ab) {
                    HwTimePicker.this.u = !r0.u;
                    HwTimePicker.this.r();
                    HwTimePicker.this.q();
                }
            }
            return super.onDoubleTapEvent(motionEvent);
        }

        /* synthetic */ e(HwTimePicker hwTimePicker, b bVar) {
            this();
        }
    }

    public HwTimePicker(Context context) {
        this(context, null);
    }

    private boolean g() {
        return sms.b(this.t) != 1 || Float.compare(this.z, 1.75f) < 0;
    }

    public static long getCurrentMillis() {
        return new GregorianCalendar().getTimeInMillis();
    }

    private void j() {
        this.o = (LinearLayout) findViewById(R.id.hwtimepicker_ll);
        HwAdvancedNumberPicker hwAdvancedNumberPicker = (HwAdvancedNumberPicker) findViewById(R.id.hwtimepicker_ampm);
        this.f10765a = hwAdvancedNumberPicker;
        hwAdvancedNumberPicker.setOnValueChangedListener(this.w);
        this.f10765a.setOnLongPressUpdateInterval(100L);
        this.f10765a.setDisplayedValues(this.s);
        this.f10765a.setMaxValue(1);
        this.f10765a.setMinValue(0);
        if (this.b) {
            this.f10765a.setVisibility(8);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = (HwAdvancedNumberPicker) findViewById(R.id.hwtimepicker_hour);
        this.e = hwAdvancedNumberPicker2;
        hwAdvancedNumberPicker2.setOnLongPressUpdateInterval(100L);
        this.e.setOnValueChangedListener(this.w);
        if (this.b) {
            this.e.setMaxValue(23);
            this.e.setMinValue(0);
        } else {
            this.e.setMaxValue(11);
            this.e.setMinValue(0);
        }
        this.e.setFormatter(HwAdvancedNumberPicker.c);
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = (HwAdvancedNumberPicker) findViewById(R.id.hwtimepicker_minute);
        this.c = hwAdvancedNumberPicker3;
        hwAdvancedNumberPicker3.setOnLongPressUpdateInterval(100L);
        this.c.setOnValueChangedListener(this.w);
        if (this.u) {
            this.c.setMaxValue(11);
        } else {
            this.c.setMaxValue(59);
        }
        this.c.setMinValue(0);
        this.c.setFormatter(HwAdvancedNumberPicker.c);
        this.f10765a.setFlingAnnounceType(4);
        this.e.setFlingAnnounceType(5);
        this.c.setFlingAnnounceType(5);
        setSpinnersShown(true);
    }

    private void k() {
        this.o.removeAllViews();
        if (skj.b(this.t) || Locale.getDefault().getLanguage().contains("bo")) {
            ehZ_(this.f10765a, this.e, this.c);
            this.o.addView(this.f10765a);
            this.o.addView(this.e);
            this.o.addView(this.c);
            return;
        }
        ehZ_(this.e, this.c, this.f10765a);
        this.o.addView(this.e);
        this.o.addView(this.c);
        this.o.addView(this.f10765a);
    }

    private boolean l() {
        return (this.c == null || this.e == null || this.f10765a == null) ? false : true;
    }

    private boolean m() {
        return getLayoutDirection() == 1;
    }

    private boolean n() {
        String language = Locale.getDefault().getLanguage();
        return (language.contains("ar") || language.contains("fa") || language.contains("iw")) || (language.contains("ug") || language.contains(Constants.URDU_LANG)) || m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        OnTimeChangedListener onTimeChangedListener = this.h;
        if (onTimeChangedListener == null) {
            return;
        }
        onTimeChangedListener.onTimeChanged(this, this.k, null);
    }

    private void p() {
        String str = ",   " + DateUtils.formatDateTime(this.t, this.k.getTimeInMillis(), this.b ? 129 : 65);
        this.e.setAnnouncedSuffix(str);
        this.c.setAnnouncedSuffix(str);
        this.f10765a.setAnnouncedSuffix(str);
        this.e.setAccessibilityOptimizationEnabled(true);
        this.c.setAccessibilityOptimizationEnabled(true);
        this.f10765a.setAccessibilityOptimizationEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (l()) {
            y();
            this.e.postInvalidate();
            this.c.postInvalidate();
            this.f10765a.postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (l()) {
            if (!this.u) {
                this.c.setDisplayedValues(null);
                this.y = (String[]) f.clone();
                this.c.setMinValue(0);
                this.c.setMaxValue(59);
                this.c.setDisplayedValues(this.y);
                this.c.setValue(this.k.get(12));
                return;
            }
            this.c.setDisplayedValues(null);
            this.y = (String[]) j.clone();
            this.c.setMinValue(0);
            this.c.setMaxValue(11);
            this.c.setDisplayedValues(this.y);
            int intValue = new BigDecimal((this.k.get(12) / 5.0f) + "").setScale(0, 4).intValue();
            this.c.setValue(intValue);
            int i2 = intValue * 5;
            boolean z = i2 == 60;
            if (this.ad || !z) {
                this.k.set(12, i2);
            } else {
                this.k.set(12, 0);
            }
        }
    }

    private void s() {
        setPickersPercentage(0);
    }

    private void setCurrentLocale(Locale locale) {
        if (locale == null || locale.equals(this.g)) {
            return;
        }
        this.g = locale;
        this.m = c(this.m, locale);
        this.k = c(this.k, locale);
    }

    private void setSpinnersMiddleSelector(int i2) {
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.f10765a;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setMiddleStateDrawable(i2);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.e;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setMiddleStateDrawable(i2);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.c;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setMiddleStateDrawable(i2);
        }
    }

    private void setSpinnersSelectionDividerHeight(int i2) {
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.f10765a;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSelectionDividerHeight(i2);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.e;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSelectionDividerHeight(i2);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.c;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSelectionDividerHeight(i2);
        }
    }

    private void t() {
        this.s[0] = DateUtils.getAMPMString(0);
        this.s[1] = DateUtils.getAMPMString(1);
    }

    private void y() {
        this.e.setWrapSelectorWheel(true);
        this.c.setWrapSelectorWheel(true);
        if (this.b) {
            this.q = (String[]) i.clone();
            this.e.setDisplayedValues(null);
            this.e.setMinValue(0);
            this.e.setMaxValue(23);
            this.e.setDisplayedValues(this.q);
            this.e.setValue(this.k.get(11));
            this.f10765a.setValue(this.k.get(9));
        } else {
            this.q = (String[]) d.clone();
            this.e.setDisplayedValues(null);
            this.e.setMinValue(0);
            this.e.setMaxValue(11);
            this.e.setDisplayedValues(this.q);
            this.e.setValue(this.k.get(10));
            this.f10765a.setValue(this.k.get(9));
        }
        if (this.u) {
            String[] strArr = (String[]) j.clone();
            this.y = strArr;
            this.c.setDisplayedValues(strArr);
            int intValue = new BigDecimal((this.k.get(12) / 5.0f) + "").setScale(0, 4).intValue();
            this.c.setValue(intValue);
            int i2 = intValue * 5;
            boolean z = i2 == 60;
            if (this.ad || !z) {
                this.k.set(12, i2);
            } else {
                this.k.set(12, 0);
            }
        } else {
            String[] strArr2 = (String[]) f.clone();
            this.y = strArr2;
            this.c.setDisplayedValues(strArr2);
            this.c.setValue(this.k.get(12));
        }
        this.f10765a.setDisplayedValues(this.s);
        p();
    }

    public final void b(GregorianCalendar gregorianCalendar, OnTimeChangedListener onTimeChangedListener) {
        if (gregorianCalendar != null) {
            e(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5), gregorianCalendar.get(11), gregorianCalendar.get(12));
            q();
        }
        if (onTimeChangedListener != null) {
            this.h = onTimeChangedListener;
        }
    }

    public void c() {
        setDialogStyle();
        boolean z = getResources().getConfiguration().orientation == 2;
        this.e.a(z);
        this.c.a(z);
        this.f10765a.a(z);
    }

    public void d(int i2, int i3, int i4, int i5, int i6) {
        if (e(i5, i6)) {
            e(i2, i3, i4, i5, i6);
            q();
            o();
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
        GestureDetector gestureDetector = this.x;
        if (gestureDetector == null || !gestureDetector.onTouchEvent(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    protected void ehZ_(View view, View view2, View view3) {
    }

    protected void eia_(Context context, AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.enabled, R.attr._2131100178_res_0x7f060212, R.attr._2131100179_res_0x7f060213, R.attr._2131100194_res_0x7f060222, R.attr._2131100223_res_0x7f06023f, R.attr._2131100273_res_0x7f060271, R.attr._2131100418_res_0x7f060302, R.attr._2131100420_res_0x7f060304, R.attr._2131100573_res_0x7f06039d}, i2, 0);
        try {
            try {
                setEnabled(obtainStyledAttributes.getBoolean(0, true));
                setSpinnersSelectionDivider(obtainStyledAttributes.getDrawable(6));
                setSpinnersSelectionDividerHeight(obtainStyledAttributes.getDimensionPixelSize(7, 0));
            } catch (Resources.NotFoundException unused) {
                Log.w("HwTimePicker", "TypedArray get resource error");
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Deprecated
    public int getDayOfMonth() {
        GregorianCalendar gregorianCalendar = this.k;
        if (gregorianCalendar == null) {
            return 1;
        }
        return gregorianCalendar.get(5);
    }

    public int getHour() {
        GregorianCalendar gregorianCalendar = this.k;
        if (gregorianCalendar == null) {
            return 0;
        }
        return gregorianCalendar.get(11);
    }

    public int getMinute() {
        GregorianCalendar gregorianCalendar = this.k;
        if (gregorianCalendar == null) {
            return 0;
        }
        return gregorianCalendar.get(12);
    }

    @Deprecated
    public int getMonth() {
        GregorianCalendar gregorianCalendar = this.k;
        if (gregorianCalendar == null) {
            return 1;
        }
        return gregorianCalendar.get(2);
    }

    public OnTimeChangedListener getOnTimeChangedListener() {
        return this.h;
    }

    public Drawable getSpinnersSelectionDivider() {
        return this.p;
    }

    public int getSpinnersSelectorPaintColor() {
        return this.l;
    }

    public int getSpinnersUnselectedPaintColor() {
        return this.n;
    }

    @Deprecated
    public int getYear() {
        GregorianCalendar gregorianCalendar = this.k;
        if (gregorianCalendar == null) {
            return 1;
        }
        return gregorianCalendar.get(1);
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.r;
    }

    @Override // android.view.View
    public boolean isHapticFeedbackEnabled() {
        return super.isHapticFeedbackEnabled();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.x = new GestureDetector(getContext().getApplicationContext(), new e(this, null));
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
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(this.t, this.k.getTimeInMillis(), 20));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof a) {
            a aVar = (a) parcelable;
            super.onRestoreInstanceState(aVar.getSuperState());
            e(1, 0, 1, aVar.f10766a, aVar.b);
            q();
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return new a(super.onSaveInstanceState(), this.k, null);
    }

    public void setCurrentTime(int i2, int i3) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        d(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5), i2, i3);
    }

    public void setDialogStyle() {
        if (this.t == null) {
            return;
        }
        s();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (!l() || this.r == z) {
            return;
        }
        this.f10765a.setEnabled(z);
        this.c.setEnabled(z);
        this.e.setEnabled(z);
        this.r = z;
    }

    public void setFocusTextColor(int i2) {
    }

    @Override // android.view.View
    public void setHapticFeedbackEnabled(boolean z) {
        super.setHapticFeedbackEnabled(z);
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.f10765a;
        if (hwAdvancedNumberPicker == null || this.e == null || this.c == null) {
            return;
        }
        hwAdvancedNumberPicker.setHapticFeedbackEnabled(z);
        this.e.setHapticFeedbackEnabled(z);
        this.c.setHapticFeedbackEnabled(z);
    }

    public void setIs24HoursSystem(boolean z) {
        if (this.b == z) {
            return;
        }
        this.b = z;
        s();
        a();
        j();
        y();
    }

    public void setIsMinuteIntervalFiveMinute(boolean z) {
        this.u = z;
        GregorianCalendar gregorianCalendar = this.k;
        if (gregorianCalendar != null) {
            gregorianCalendar.setTimeInMillis(getCurrentMillis());
        } else {
            this.k = new GregorianCalendar();
        }
        r();
        q();
    }

    public void setLinkageScrollEnabled(boolean z) {
        this.ad = z;
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        this.h = onTimeChangedListener;
    }

    protected void setPickersPercentage(int i2) {
        if (l()) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(24, 0, 24, 0);
            this.o.setLayoutParams(layoutParams);
            if (this.b) {
                this.f10765a.setVisibility(8);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1);
                layoutParams2.weight = 1.0f;
                this.c.setLayoutParams(layoutParams2);
                this.e.setLayoutParams(layoutParams2);
                return;
            }
            this.f10765a.setVisibility(0);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -1);
            layoutParams3.weight = 1.0f;
            this.c.setLayoutParams(layoutParams3);
            this.e.setLayoutParams(layoutParams3);
            this.f10765a.setLayoutParams(layoutParams3);
        }
    }

    public void setSpinnersSelectionDivider(Drawable drawable) {
        Drawable drawable2 = this.p;
        if (drawable2 == null || !drawable2.equals(drawable)) {
            this.p = drawable;
            HwAdvancedNumberPicker hwAdvancedNumberPicker = this.f10765a;
            if (hwAdvancedNumberPicker != null) {
                hwAdvancedNumberPicker.setSelectionDivider(drawable);
            }
            HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.e;
            if (hwAdvancedNumberPicker2 != null) {
                hwAdvancedNumberPicker2.setSelectionDivider(drawable);
            }
            HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.c;
            if (hwAdvancedNumberPicker3 != null) {
                hwAdvancedNumberPicker3.setSelectionDivider(drawable);
            }
        }
    }

    public void setSpinnersSelectorPaintColor(int i2) {
        if (this.l == i2) {
            return;
        }
        this.l = i2;
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.f10765a;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSelectorPaintColor(i2);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.e;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSelectorPaintColor(i2);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.c;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSelectorPaintColor(i2);
        }
    }

    public final void setSpinnersShown(boolean z) {
        LinearLayout linearLayout = this.o;
        if (linearLayout != null) {
            linearLayout.setVisibility(z ? 0 : 8);
        }
    }

    public void setSpinnersUnselectedPaintColor(int i2) {
        if (this.n == i2) {
            return;
        }
        this.n = i2;
        HwAdvancedNumberPicker hwAdvancedNumberPicker = this.f10765a;
        if (hwAdvancedNumberPicker != null) {
            hwAdvancedNumberPicker.setSecondaryPaintColor(i2);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker2 = this.e;
        if (hwAdvancedNumberPicker2 != null) {
            hwAdvancedNumberPicker2.setSecondaryPaintColor(i2);
        }
        HwAdvancedNumberPicker hwAdvancedNumberPicker3 = this.c;
        if (hwAdvancedNumberPicker3 != null) {
            hwAdvancedNumberPicker3.setSecondaryPaintColor(i2);
        }
    }

    public HwTimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100580_res_0x7f0603a4);
    }

    private void a() {
        this.v.add(this.e);
        this.v.add(this.c);
        this.v.add(this.f10765a);
        this.e.setOnColorChangeListener(this.ac);
        this.c.setOnColorChangeListener(this.ac);
        this.f10765a.setOnColorChangeListener(this.ac);
        String language = Locale.getDefault().getLanguage();
        if (!language.contains("ar") && !language.contains("fa") && !language.contains("iw")) {
            k();
        }
        if (this.o != null && n()) {
            this.o.removeAllViews();
            ehZ_(this.c, this.e, this.f10765a);
            this.o.addView(this.c);
            this.o.addView(this.e);
            this.o.addView(this.f10765a);
        }
        if (this.o != null && (language.contains(Constants.URDU_LANG) || language.contains("ug"))) {
            this.o.removeAllViews();
            ehZ_(this.f10765a, this.c, this.e);
            this.o.addView(this.f10765a);
            this.o.addView(this.c);
            this.o.addView(this.e);
        }
        s();
    }

    private void f() {
        this.ac = new d();
    }

    private void h() {
        this.w = new b();
    }

    private void i() {
        this.k.setTimeInMillis(getCurrentMillis());
    }

    void b() {
        if (g()) {
            return;
        }
        if (this.b) {
            this.f10765a.setVisibility(8);
            this.e.setVisibility(0);
            this.c.setVisibility(0);
        } else {
            this.f10765a.setVisibility(0);
            this.e.setVisibility(8);
            this.c.setVisibility(8);
        }
    }

    public HwTimePicker(Context context, AttributeSet attributeSet, int i2) {
        super(e(context, i2), attributeSet, i2);
        this.r = true;
        this.t = getContext();
        this.s = new String[2];
        this.v = new ArrayList(4);
        this.u = true;
        Context context2 = super.getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100575_res_0x7f06039f});
        if (obtainStyledAttributes != null) {
            int resourceId = obtainStyledAttributes.getResourceId(0, 0);
            context2 = resourceId != 0 ? new ContextThemeWrapper(super.getContext(), resourceId) : context2;
            obtainStyledAttributes.recycle();
        }
        c(context2);
        d(context2);
        ehY_(context2, attributeSet, i2);
    }

    private void d(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) this.t.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            layoutInflater.cloneInContext(context).inflate(R.layout.hwtimepicker, (ViewGroup) this, true);
            h();
            f();
            i();
            j();
            b(this.k, (OnTimeChangedListener) null);
            a();
        }
    }

    private static Context e(Context context, int i2) {
        return smr.b(context, i2, R.style.Theme_Emui_HwTimePicker);
    }

    private void c(Context context) {
        this.ab = getResources().getDimension(R.dimen._2131363874_res_0x7f0a0822);
        this.b = DateFormat.is24HourFormat(context);
        this.z = smp.a(context);
        setCurrentLocale(Locale.getDefault());
        t();
    }

    private void ehY_(Context context, AttributeSet attributeSet, int i2) {
        eia_(context, attributeSet, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, int i3, int i4, int i5, int i6) {
        if (!this.ad) {
            if (i6 == 11 && !this.b && this.m.get(9) == 1) {
                this.m.set(i6, (i3 * i5) + 12);
                return;
            } else {
                this.m.set(i6, i3 * i5);
                return;
            }
        }
        if (i3 == 0 && i2 == i4) {
            this.m.add(i6, i5);
        } else if (i2 == 0 && i3 == i4) {
            this.m.add(i6, -i5);
        } else {
            this.m.add(i6, (i3 - i2) * i5);
        }
    }

    void e() {
        if (g()) {
            return;
        }
        this.e.setVisibility(0);
        this.c.setVisibility(0);
        this.f10765a.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2, int i3, int i4, int i5, int i6) {
        GregorianCalendar gregorianCalendar = this.k;
        if (gregorianCalendar != null) {
            gregorianCalendar.set(i2, i3, i4, i5, i6);
        }
    }

    void d() {
        if (g()) {
            return;
        }
        for (HwAdvancedNumberPicker hwAdvancedNumberPicker : this.v) {
            hwAdvancedNumberPicker.setAuxiliaryUnselectedTextSize(28.0f);
            hwAdvancedNumberPicker.setAuxiliarySelectedTextSize(35.0f);
        }
    }

    private GregorianCalendar c(GregorianCalendar gregorianCalendar, Locale locale) {
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

    private boolean e(int i2, int i3) {
        return (this.k.get(11) == i2 && this.k.get(12) == i3) ? false : true;
    }
}
