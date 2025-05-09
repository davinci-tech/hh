package com.huawei.uikit.hwdateandtimepicker.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Outline;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwdateandtimepicker.widget.HwDateAndTimePicker;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import defpackage.skj;
import defpackage.skk;
import defpackage.slh;
import defpackage.smp;
import defpackage.sms;
import java.io.Serializable;
import java.util.GregorianCalendar;

/* loaded from: classes9.dex */
public class HwDateAndTimePickerDialog extends AlertDialog implements HwDateAndTimePicker.OnDateChangedListener {
    private static int b = 0;
    private static final String c = "HwDateAndTimePickerDialog";

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f10630a;
    private LinearLayout d;
    private HwDateAndTimePicker e;
    private View f;
    private HwTextView g;
    private GregorianCalendar h;
    private HwTextView i;
    private LinearLayout j;
    private HwTextView k;
    private OnButtonClickCallback l;
    private Activity m;
    private Context n;

    public interface OnButtonClickCallback {
        void onNegativeButtonClick(HwDateAndTimePicker hwDateAndTimePicker);

        void onPositiveButtonClick(HwDateAndTimePicker hwDateAndTimePicker);
    }

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (HwDateAndTimePickerDialog.this.m()) {
                HwDateAndTimePickerDialog.this.i();
            } else {
                HwDateAndTimePickerDialog.this.e.d();
                HwDateAndTimePickerDialog.this.i.setText(R.string._2130851237_res_0x7f0235a5);
                HwDateAndTimePickerDialog.this.g.setText(R.string._2130850821_res_0x7f023405);
                HwDateAndTimePickerDialog.this.b();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (HwDateAndTimePickerDialog.this.k()) {
                HwDateAndTimePickerDialog.this.j();
            } else {
                HwDateAndTimePickerDialog.this.e.b();
                HwDateAndTimePickerDialog.this.i.setText(R.string._2130850822_res_0x7f023406);
                HwDateAndTimePickerDialog.this.g.setText(R.string._2130851238_res_0x7f0235a6);
                HwDateAndTimePickerDialog.this.a();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class d implements View.OnLayoutChangeListener {
        final /* synthetic */ Window c;

        d(Window window) {
            this.c = window;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int unused = HwDateAndTimePickerDialog.b = HwDateAndTimePickerDialog.this.n.getResources().getConfiguration().orientation;
            DisplayMetrics displayMetrics = HwDateAndTimePickerDialog.this.n.getResources().getDisplayMetrics();
            WindowManager.LayoutParams attributes = this.c.getAttributes();
            if (skk.b() || skk.c(HwDateAndTimePickerDialog.this.m)) {
                HwDateAndTimePickerDialog.this.ebM_(attributes, displayMetrics);
            } else if (slh.ebD_(HwDateAndTimePickerDialog.this.m)) {
                HwDateAndTimePickerDialog.this.ebH_(attributes, displayMetrics);
            } else if (HwDateAndTimePickerDialog.b == 2) {
                HwDateAndTimePickerDialog.this.ebJ_(true, attributes, displayMetrics);
                HwDateAndTimePickerDialog.this.o();
            } else {
                HwDateAndTimePickerDialog.this.ebJ_(false, attributes, displayMetrics);
            }
            this.c.setAttributes(attributes);
        }
    }

    class e implements Runnable {
        final /* synthetic */ TypedValue d;
        final /* synthetic */ int e;

        class d extends ViewOutlineProvider {
            d() {
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), e.this.e);
                HwDateAndTimePickerDialog.this.n.getTheme().resolveAttribute(R.attr._2131100194_res_0x7f060222, e.this.d, true);
                HwDateAndTimePickerDialog.this.f.setBackgroundColor(e.this.d.data);
            }
        }

        e(int i, TypedValue typedValue) {
            this.e = i;
            this.d = typedValue;
        }

        @Override // java.lang.Runnable
        public void run() {
            HwDateAndTimePickerDialog.this.f.setOutlineProvider(new d());
            HwDateAndTimePickerDialog.this.f.setClipToOutline(true);
        }
    }

    public HwDateAndTimePickerDialog(Activity activity, OnButtonClickCallback onButtonClickCallback) {
        this(activity, R.style.Widget_Emui_HwDateAndTimePickerDialogStyle, onButtonClickCallback);
    }

    private void n() {
        t();
        this.e.b();
        this.e.a();
    }

    private void t() {
        if (sms.b(this.n) == 1 && Float.compare(smp.a(this.n), 1.75f) >= 0) {
            this.k.setMaxLines(2);
            this.k.setAutoTextSize(1, 35.0f);
            if (Float.compare(smp.a(this.n), 2.0f) >= 0) {
                this.k.setAutoTextSize(1, 40.0f);
            }
        }
    }

    public void a(boolean z) {
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        if (hwDateAndTimePicker != null) {
            hwDateAndTimePicker.setIsMinuteIntervalFiveMinute(z);
        }
    }

    public void b(boolean z) {
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        if (hwDateAndTimePicker != null) {
            hwDateAndTimePicker.setLunarOrWestern(z);
        }
    }

    public void c(int i, int i2, int i3, int i4, int i5) {
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        if (hwDateAndTimePicker != null) {
            hwDateAndTimePicker.a(i, i2, i3, i4, i5);
        }
    }

    public void d(boolean z) {
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        if (hwDateAndTimePicker != null) {
            hwDateAndTimePicker.setIsLunarEnabled(z);
        }
    }

    protected void e() {
        Window window = getWindow();
        if (window == null || this.m == null) {
            return;
        }
        window.getDecorView().addOnLayoutChangeListener(new d(window));
    }

    public void e(GregorianCalendar gregorianCalendar) {
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        if (hwDateAndTimePicker == null || gregorianCalendar == null) {
            return;
        }
        hwDateAndTimePicker.setCustomArbitraryUpperBounds(gregorianCalendar);
    }

    @Override // com.huawei.uikit.hwdateandtimepicker.widget.HwDateAndTimePicker.OnDateChangedListener
    public void onDateChanged(HwDateAndTimePicker hwDateAndTimePicker, GregorianCalendar gregorianCalendar, String str) {
        if (hwDateAndTimePicker != null && gregorianCalendar != null) {
            hwDateAndTimePicker.d(gregorianCalendar, this);
        }
        if (str != null) {
            a(str);
        }
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        super.onRestoreInstanceState(bundle);
        try {
            boolean z = bundle.getBoolean("is_from_today");
            boolean z2 = bundle.getBoolean("is_lunar_enabled");
            boolean z3 = bundle.getBoolean("is_western");
            boolean z4 = bundle.getBoolean("is_minute_interval_five_minute");
            Serializable serializable = bundle.getSerializable("custom_upper_bounds");
            HwDateAndTimePicker hwDateAndTimePicker = this.e;
            if (hwDateAndTimePicker != null) {
                hwDateAndTimePicker.setIsFromToday(z);
                this.e.setIsLunarEnabled(z2);
                this.e.setLunarOrWestern(!z3);
                this.e.setIsMinuteIntervalFiveMinute(z4);
                if (serializable instanceof GregorianCalendar) {
                    this.e.setCustomArbitraryUpperBounds((GregorianCalendar) serializable);
                }
            }
        } catch (BadParcelableException unused) {
            Log.e(c, "onRestoreInstanceState: Bad parcel target when get time info.");
        }
        try {
            int i = bundle.getInt("year");
            int i2 = bundle.getInt("month");
            int i3 = bundle.getInt("day");
            int i4 = bundle.getInt("hour");
            int i5 = bundle.getInt("minute");
            if (this.h == null) {
                this.h = new GregorianCalendar();
            }
            this.h.set(i, i2, i3, i4, i5);
            HwDateAndTimePicker hwDateAndTimePicker2 = this.e;
            if (hwDateAndTimePicker2 != null) {
                hwDateAndTimePicker2.d(this.h, this);
            }
            a(DateUtils.formatDateTime(this.m, this.h.getTimeInMillis(), ebF_(this.m)));
        } catch (BadParcelableException unused2) {
            Log.e(c, "onRestoreInstanceState: Bad parcel target when get time zone info.");
        }
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        int year = hwDateAndTimePicker == null ? 1 : hwDateAndTimePicker.getYear();
        HwDateAndTimePicker hwDateAndTimePicker2 = this.e;
        int month = hwDateAndTimePicker2 == null ? 1 : hwDateAndTimePicker2.getMonth();
        HwDateAndTimePicker hwDateAndTimePicker3 = this.e;
        int dayOfMonth = hwDateAndTimePicker3 == null ? 1 : hwDateAndTimePicker3.getDayOfMonth();
        HwDateAndTimePicker hwDateAndTimePicker4 = this.e;
        int hour = hwDateAndTimePicker4 == null ? 1 : hwDateAndTimePicker4.getHour();
        HwDateAndTimePicker hwDateAndTimePicker5 = this.e;
        int minute = hwDateAndTimePicker5 == null ? 1 : hwDateAndTimePicker5.getMinute();
        try {
            onSaveInstanceState.putInt("year", year);
            onSaveInstanceState.putInt("month", month);
            onSaveInstanceState.putInt("day", dayOfMonth);
            onSaveInstanceState.putInt("hour", hour);
            onSaveInstanceState.putInt("minute", minute);
        } catch (BadParcelableException unused) {
            Log.e(c, "Bad Parcel target when put time info calling onSaveInstanceState.");
        }
        HwDateAndTimePicker hwDateAndTimePicker6 = this.e;
        boolean z = hwDateAndTimePicker6 != null && hwDateAndTimePicker6.c();
        HwDateAndTimePicker hwDateAndTimePicker7 = this.e;
        boolean z2 = hwDateAndTimePicker7 != null && hwDateAndTimePicker7.j();
        HwDateAndTimePicker hwDateAndTimePicker8 = this.e;
        boolean z3 = hwDateAndTimePicker8 != null && hwDateAndTimePicker8.g();
        HwDateAndTimePicker hwDateAndTimePicker9 = this.e;
        boolean z4 = hwDateAndTimePicker9 != null && hwDateAndTimePicker9.i();
        HwDateAndTimePicker hwDateAndTimePicker10 = this.e;
        GregorianCalendar customArbitraryUpperBounds = hwDateAndTimePicker10 == null ? null : hwDateAndTimePicker10.getCustomArbitraryUpperBounds();
        try {
            onSaveInstanceState.putBoolean("is_from_today", z);
            onSaveInstanceState.putBoolean("is_lunar_enabled", z2);
            onSaveInstanceState.putBoolean("is_western", z3);
            onSaveInstanceState.putBoolean("is_minute_interval_five_minute", z4);
            onSaveInstanceState.putSerializable("custom_upper_bounds", customArbitraryUpperBounds);
        } catch (BadParcelableException unused2) {
            Log.e(c, "Bad Parcel target when put time zone info calling onSaveInstanceState.");
        }
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        e();
    }

    @Override // android.app.Dialog
    protected void onStop() {
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        setContentView(this.f);
        f();
    }

    public HwDateAndTimePickerDialog(Activity activity, int i, OnButtonClickCallback onButtonClickCallback) {
        super(activity, i);
        this.l = onButtonClickCallback;
        Context context = getContext();
        this.n = context;
        this.m = activity;
        b = context.getResources().getConfiguration().orientation;
        g();
        if (this.f != null) {
            TypedValue typedValue = new TypedValue();
            this.f.post(new e(getContext().getResources().getDimensionPixelSize(R.dimen._2131362601_res_0x7f0a0329), typedValue));
            h();
            setIcon(0);
            this.h = new GregorianCalendar();
            a(DateUtils.formatDateTime(activity, this.h.getTimeInMillis(), ebF_(activity)));
            this.e.d(this.h, this);
            n();
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (Float.compare(smp.a(this.n), 2.0f) < 0) {
            return;
        }
        if (b != 2 || skk.c(this.m) || skk.b()) {
            this.d.removeView(this.g);
            this.d.addView(this.g);
            ebG_(this.i);
            ebG_(this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (Float.compare(smp.a(this.n), 2.0f) < 0) {
            return;
        }
        if (b != 2 || skk.c(this.m) || skk.b()) {
            this.d.removeView(this.i);
            this.d.addView(this.i);
            ebG_(this.i);
            ebG_(this.g);
        }
    }

    private void f() {
        Window window = getWindow();
        if (window == null || this.n == null || this.e == null) {
            return;
        }
        window.setWindowAnimations(R.style.Widget_Emui_HwDateAndTimePickerDialogAnim);
        if (this.m == null) {
            return;
        }
        DisplayMetrics displayMetrics = this.n.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (skk.b() || skk.c(this.m)) {
            window.setGravity(17);
            ebM_(attributes, displayMetrics);
        } else if (slh.ebD_(this.m)) {
            window.setGravity(17);
            ebH_(attributes, displayMetrics);
        } else if (b == 2) {
            window.setGravity(17);
            ebJ_(true, attributes, displayMetrics);
        } else {
            window.setGravity(80);
            ebJ_(false, attributes, displayMetrics);
        }
        window.setAttributes(attributes);
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        if (hwDateAndTimePicker != null) {
            hwDateAndTimePicker.e();
        }
        o();
    }

    private void g() {
        View inflate = View.inflate(this.n, R.layout.hwdateandtimepicker_dialog, null);
        this.f = inflate;
        this.f10630a = (LinearLayout) inflate.findViewById(R.id.hwdateandtimepicker_dialog_title_layout);
        this.j = (LinearLayout) this.f.findViewById(R.id.hwdateandtimepicker_dialog_content_layout);
        this.d = (LinearLayout) this.f.findViewById(R.id.hwdateandtimepicker_dialog_button_layout);
        this.k = (HwTextView) this.f.findViewById(R.id.hwdateandtimepicker_dialog_title);
        this.e = (HwDateAndTimePicker) this.f.findViewById(R.id.hwdateandtimepicker_dialog_dateandtimepicker);
        this.i = (HwTextView) this.f.findViewById(R.id.hwdateandtimepicker_dialog_positive_btn);
        this.g = (HwTextView) this.f.findViewById(R.id.hwdateandtimepicker_dialog_negative_btn);
    }

    private void h() {
        c();
        this.i.setOnClickListener(new a());
        this.g.setOnClickListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        dismiss();
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        if (hwDateAndTimePicker == null || this.l == null) {
            return;
        }
        hwDateAndTimePicker.clearFocus();
        this.l.onPositiveButtonClick(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        dismiss();
        HwDateAndTimePicker hwDateAndTimePicker = this.e;
        if (hwDateAndTimePicker == null || this.l == null) {
            return;
        }
        hwDateAndTimePicker.clearFocus();
        this.l.onNegativeButtonClick(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        if (Float.compare(smp.a(this.n), 1.75f) < 0) {
            return true;
        }
        return this.n.getResources().getString(R.string._2130851238_res_0x7f0235a6).contentEquals(this.g.getText());
    }

    private void l() {
        if (smp.b(this.n) && Float.compare(smp.a(this.n), 1.75f) >= 0) {
            this.i.setMaxLines(2);
            this.g.setMaxLines(2);
            this.i.setAutoTextInfo(13, 16, 2);
            this.g.setAutoTextInfo(13, 16, 2);
            ebL_(this.i);
            ebL_(this.g);
            if ((b != 2 || skk.c(this.m) || skk.b()) && Float.compare(smp.a(this.n), 2.0f) >= 0) {
                this.d.setOrientation(1);
                this.f.findViewById(R.id.hwdateandtimepicker_dialog_split_line).setVisibility(8);
                this.d.removeView(this.g);
                this.d.addView(this.g);
                ebG_(this.i);
                ebG_(this.g);
                if (this.d.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.d.getLayoutParams();
                    layoutParams.weight = 0.0f;
                    layoutParams.height = -2;
                    layoutParams.width = -1;
                    this.d.setLayoutParams(layoutParams);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        if (Float.compare(smp.a(this.n), 1.75f) < 0) {
            return true;
        }
        return this.n.getResources().getString(R.string._2130851237_res_0x7f0235a5).contentEquals(this.i.getText());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (smp.b(this.n) && Float.compare(smp.a(this.n), 2.0f) >= 0 && b == 2 && !skk.b() && !skk.c(this.m)) {
            this.i.setAutoTextSize(1, 32.0f);
            this.g.setAutoTextSize(1, 32.0f);
        }
    }

    private void ebL_(View view) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = (int) TypedValue.applyDimension(1, 8.0f, this.n.getResources().getDisplayMetrics());
            layoutParams.bottomMargin = (int) TypedValue.applyDimension(1, 8.0f, this.n.getResources().getDisplayMetrics());
            view.setLayoutParams(layoutParams);
        }
    }

    private void ebG_(View view) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.weight = 0.0f;
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
            layoutParams.topMargin = (int) TypedValue.applyDimension(1, 8.0f, this.n.getResources().getDisplayMetrics());
            layoutParams.bottomMargin = 0;
            if (view == this.d.getChildAt(2)) {
                layoutParams.bottomMargin = (int) TypedValue.applyDimension(1, 8.0f, this.n.getResources().getDisplayMetrics());
            }
            layoutParams.height = -2;
            layoutParams.width = -1;
            view.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ebM_(WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        Activity activity = this.m;
        if (activity == null || layoutParams == null || displayMetrics == null) {
            return;
        }
        layoutParams.width = Math.min((int) activity.getResources().getDimension(R.dimen._2131364106_res_0x7f0a090a), displayMetrics.widthPixels);
    }

    private void c() {
        if (smp.b(this.n) && Float.compare(smp.a(this.n), 1.75f) >= 0) {
            this.i.setText(R.string._2130850822_res_0x7f023406);
        }
    }

    private int ebF_(Activity activity) {
        return (activity == null || skj.b(activity)) ? 65558 : 98326;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ebH_(WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        if (layoutParams == null || displayMetrics == null) {
            return;
        }
        layoutParams.width = displayMetrics.widthPixels;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ebJ_(boolean z, WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        if (layoutParams == null || displayMetrics == null) {
            return;
        }
        if (z) {
            layoutParams.width = (int) (displayMetrics.widthPixels * 0.65d);
        } else {
            layoutParams.width = displayMetrics.widthPixels;
        }
    }

    private void a(String str) {
        HwTextView hwTextView;
        if (str == null || (hwTextView = this.k) == null) {
            return;
        }
        hwTextView.setText(str);
        this.k.requestLayout();
    }
}
