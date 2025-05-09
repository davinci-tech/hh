package com.huawei.uikit.hwtimepicker.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Outline;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import com.huawei.uikit.hwtextview.widget.HwTextView;
import com.huawei.uikit.hwtimepicker.widget.HwTimePicker;
import defpackage.skk;
import defpackage.smp;
import defpackage.sms;
import defpackage.snc;
import java.util.GregorianCalendar;

/* loaded from: classes7.dex */
public class HwTimePickerDialog extends AlertDialog implements HwTimePicker.OnTimeChangedListener {
    private static final String d = "HwTimePickerDialog";
    private static int e;

    /* renamed from: a, reason: collision with root package name */
    private String f10768a;
    private HwTextView b;
    private HwTextView c;
    private Activity f;
    private Context g;
    private HwTextView h;
    private View i;
    private OnButtonClickCallback j;
    private HwTimePicker k;
    private LinearLayout m;
    private LinearLayout n;
    private LinearLayout o;

    public interface OnButtonClickCallback {
        void onNegativeButtonClick(HwTimePicker hwTimePicker);

        void onPositiveButtonClick(HwTimePicker hwTimePicker);
    }

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (HwTimePickerDialog.this.o()) {
                HwTimePickerDialog.this.j();
            } else {
                HwTimePickerDialog.this.k.b();
                HwTimePickerDialog.this.b.setText(R.string._2130850822_res_0x7f023406);
                HwTimePickerDialog.this.c.setText(R.string._2130850827_res_0x7f02340b);
                HwTimePickerDialog.this.h();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class c implements View.OnLayoutChangeListener {
        final /* synthetic */ Window b;

        c(Window window) {
            this.b = window;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int unused = HwTimePickerDialog.e = HwTimePickerDialog.this.g.getResources().getConfiguration().orientation;
            DisplayMetrics displayMetrics = HwTimePickerDialog.this.g.getResources().getDisplayMetrics();
            WindowManager.LayoutParams attributes = this.b.getAttributes();
            if (skk.b() || skk.c(HwTimePickerDialog.this.f)) {
                HwTimePickerDialog.this.eic_(attributes, displayMetrics);
            } else if (snc.ehX_(HwTimePickerDialog.this.f)) {
                HwTimePickerDialog.this.eih_(attributes, displayMetrics);
            } else if (HwTimePickerDialog.e == 2) {
                HwTimePickerDialog.this.eif_(true, attributes, displayMetrics);
                HwTimePickerDialog.this.m();
            } else {
                HwTimePickerDialog.this.eif_(false, attributes, displayMetrics);
            }
            this.b.setAttributes(attributes);
        }
    }

    class d implements Runnable {
        final /* synthetic */ int b;
        final /* synthetic */ TypedValue d;

        class a extends ViewOutlineProvider {
            a() {
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), d.this.b);
                HwTimePickerDialog.this.g.getTheme().resolveAttribute(R.attr._2131100194_res_0x7f060222, d.this.d, true);
                HwTimePickerDialog.this.i.setBackgroundColor(d.this.d.data);
            }
        }

        d(int i, TypedValue typedValue) {
            this.b = i;
            this.d = typedValue;
        }

        @Override // java.lang.Runnable
        public void run() {
            HwTimePickerDialog.this.i.setOutlineProvider(new a());
            HwTimePickerDialog.this.i.setClipToOutline(true);
        }
    }

    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (HwTimePickerDialog.this.n()) {
                HwTimePickerDialog.this.f();
            } else {
                HwTimePickerDialog.this.k.e();
                HwTimePickerDialog.this.b.setText(R.string._2130850820_res_0x7f023404);
                HwTimePickerDialog.this.c.setText(R.string._2130850821_res_0x7f023405);
                HwTimePickerDialog.this.d();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public HwTimePickerDialog(Activity activity, OnButtonClickCallback onButtonClickCallback) {
        this(activity, R.style.Theme_Emui_HwTimePickerDialog, onButtonClickCallback);
    }

    private void q() {
        if (sms.b(this.g) == 1 && Float.compare(smp.a(this.g), 1.75f) >= 0) {
            this.h.setMaxLines(2);
            this.h.setAutoTextSize(1, 35.0f);
            if (Float.compare(smp.a(this.g), 2.0f) >= 0) {
                this.h.setAutoTextSize(1, 40.0f);
            }
        }
    }

    private void r() {
        q();
        this.k.b();
        this.k.d();
    }

    protected void a() {
        Window window = getWindow();
        if (window == null || this.f == null) {
            return;
        }
        window.getDecorView().addOnLayoutChangeListener(new c(window));
    }

    public void a(int i) {
        HwTimePicker hwTimePicker = this.k;
        if (hwTimePicker != null) {
            hwTimePicker.setSpinnersSelectorPaintColor(i);
        }
    }

    public void a(boolean z) {
        HwTimePicker hwTimePicker = this.k;
        if (hwTimePicker != null) {
            hwTimePicker.setIsMinuteIntervalFiveMinute(z);
        }
    }

    public void b(String str) {
        this.f10768a = str;
    }

    public void d(int i) {
        HwTextView hwTextView = this.b;
        if (hwTextView != null) {
            hwTextView.setTextColor(i);
        }
        HwTextView hwTextView2 = this.c;
        if (hwTextView2 != null) {
            hwTextView2.setTextColor(i);
        }
    }

    public HwTimePicker e() {
        return this.k;
    }

    public void e(int i, int i2, int i3, int i4, int i5) {
        HwTimePicker hwTimePicker = this.k;
        if (hwTimePicker != null) {
            hwTimePicker.d(i, i2, i3, i4, i5);
        }
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        super.onRestoreInstanceState(bundle);
        try {
            int i = bundle.getInt("hour");
            int i2 = bundle.getInt("minute");
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(11, i);
            gregorianCalendar.set(12, i2);
            HwTimePicker hwTimePicker = this.k;
            if (hwTimePicker != null) {
                hwTimePicker.b(gregorianCalendar, this);
            }
        } catch (BadParcelableException unused) {
            Log.e(d, "onRestoreInstanceState: Bad parcel target.");
        }
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        HwTimePicker hwTimePicker = this.k;
        int hour = hwTimePicker == null ? 1 : hwTimePicker.getHour();
        HwTimePicker hwTimePicker2 = this.k;
        int minute = hwTimePicker2 != null ? hwTimePicker2.getMinute() : 1;
        try {
            onSaveInstanceState.putInt("hour", hour);
            onSaveInstanceState.putInt("minute", minute);
        } catch (BadParcelableException unused) {
            Log.e(d, "onSaveInstanceState: Bad parcel target.");
        }
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        a();
    }

    @Override // android.app.Dialog
    protected void onStop() {
    }

    @Override // com.huawei.uikit.hwtimepicker.widget.HwTimePicker.OnTimeChangedListener
    public void onTimeChanged(HwTimePicker hwTimePicker, GregorianCalendar gregorianCalendar, String str) {
        if (hwTimePicker != null && gregorianCalendar != null) {
            hwTimePicker.b(gregorianCalendar, this);
        }
        if (str != null) {
            c(str);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        View view = this.i;
        if (view != null) {
            setContentView(view);
            c(this.f10768a);
            g();
        }
    }

    public HwTimePickerDialog(Activity activity, int i, OnButtonClickCallback onButtonClickCallback) {
        super(activity, i);
        this.f10768a = "设置时间";
        this.j = onButtonClickCallback;
        Context context = getContext();
        this.g = context;
        this.f = activity;
        e = context.getResources().getConfiguration().orientation;
        k();
        if (this.i != null) {
            TypedValue typedValue = new TypedValue();
            this.i.post(new d(getContext().getResources().getDimensionPixelSize(R.dimen._2131362601_res_0x7f0a0329), typedValue));
            i();
            setIcon(0);
            c(this.f10768a);
            this.k.b(new GregorianCalendar(), this);
            r();
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (Float.compare(smp.a(this.g), 2.0f) < 0) {
            return;
        }
        if (e != 2 || skk.c(this.f) || skk.b()) {
            this.n.removeView(this.b);
            this.n.addView(this.b);
            eib_(this.b);
            eib_(this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        dismiss();
        HwTimePicker hwTimePicker = this.k;
        if (hwTimePicker == null || this.j == null) {
            return;
        }
        hwTimePicker.clearFocus();
        this.j.onPositiveButtonClick(this.k);
    }

    private void g() {
        Window window = getWindow();
        if (window == null || this.g == null) {
            return;
        }
        window.setWindowAnimations(R.style.Animation_Emui_HwTimePickerDialog);
        e = this.g.getResources().getConfiguration().orientation;
        if (this.f == null) {
            return;
        }
        DisplayMetrics displayMetrics = this.g.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (skk.b() || skk.c(this.f)) {
            window.setGravity(17);
            eic_(attributes, displayMetrics);
        } else if (snc.ehX_(this.f)) {
            window.setGravity(17);
            eih_(attributes, displayMetrics);
        } else if (e == 2) {
            window.setGravity(17);
            eif_(true, attributes, displayMetrics);
        } else {
            window.setGravity(80);
            eif_(false, attributes, displayMetrics);
        }
        window.setAttributes(attributes);
        HwTimePicker hwTimePicker = this.k;
        if (hwTimePicker != null) {
            hwTimePicker.c();
        }
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (Float.compare(smp.a(this.g), 2.0f) < 0) {
            return;
        }
        if (e != 2 || skk.c(this.f) || skk.b()) {
            this.n.removeView(this.c);
            this.n.addView(this.c);
            eib_(this.b);
            eib_(this.c);
        }
    }

    private void i() {
        c();
        this.b.setOnClickListener(new e());
        this.c.setOnClickListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        dismiss();
        HwTimePicker hwTimePicker = this.k;
        if (hwTimePicker == null || this.j == null) {
            return;
        }
        hwTimePicker.clearFocus();
        this.j.onNegativeButtonClick(this.k);
    }

    private void k() {
        View inflate = View.inflate(this.g, R.layout.hwtimepicker_dialog, null);
        this.i = inflate;
        this.m = (LinearLayout) inflate.findViewById(R.id.hwtimepicker_title_layout);
        this.o = (LinearLayout) this.i.findViewById(R.id.hwtimepicker_dialog_content_layout);
        this.n = (LinearLayout) this.i.findViewById(R.id.hwtimepicker_button_layout);
        this.h = (HwTextView) this.i.findViewById(R.id.hwtimepicker_title);
        this.k = (HwTimePicker) this.i.findViewById(R.id.hwtimepicker);
        this.b = (HwTextView) this.i.findViewById(R.id.hwtimepicker_positive_btn);
        this.c = (HwTextView) this.i.findViewById(R.id.hwtimepicker_negative_btn);
    }

    private void l() {
        if (smp.b(this.g) && Float.compare(smp.a(this.g), 1.75f) >= 0) {
            this.b.setMaxLines(2);
            this.c.setMaxLines(2);
            this.b.setAutoTextInfo(13, 16, 2);
            this.c.setAutoTextInfo(13, 16, 2);
            b(this.b);
            b(this.c);
            if ((e != 2 || skk.c(this.f) || skk.b()) && Float.compare(smp.a(this.g), 2.0f) >= 0) {
                this.n.setOrientation(!DateFormat.is24HourFormat(this.g) ? 1 : 0);
                this.i.findViewById(R.id.hwtimepicker_dialog_split_line).setVisibility(8);
                if (DateFormat.is24HourFormat(this.g)) {
                    b(this.b);
                    b(this.c);
                } else {
                    this.n.removeView(this.c);
                    this.n.addView(this.c);
                    eib_(this.b);
                    eib_(this.c);
                }
                if (this.n.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.n.getLayoutParams();
                    layoutParams.weight = 0.0f;
                    layoutParams.height = -2;
                    layoutParams.width = -1;
                    this.n.setLayoutParams(layoutParams);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (smp.b(this.g) && Float.compare(smp.a(this.g), 2.0f) >= 0 && e == 2 && !skk.b() && !skk.c(this.f)) {
            this.b.setAutoTextSize(1, 32.0f);
            this.c.setAutoTextSize(1, 32.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        if (Float.compare(smp.a(this.g), 1.75f) < 0) {
            return true;
        }
        return this.g.getResources().getString(R.string._2130850820_res_0x7f023404).contentEquals(this.b.getText());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean o() {
        if (Float.compare(smp.a(this.g), 1.75f) < 0) {
            return true;
        }
        return this.g.getResources().getString(R.string._2130850827_res_0x7f02340b).contentEquals(this.c.getText());
    }

    private void b(View view) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = (int) TypedValue.applyDimension(1, 8.0f, this.g.getResources().getDisplayMetrics());
            layoutParams.bottomMargin = (int) TypedValue.applyDimension(1, 8.0f, this.g.getResources().getDisplayMetrics());
            view.setLayoutParams(layoutParams);
        }
    }

    private void eib_(View view) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.weight = 0.0f;
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
            layoutParams.topMargin = (int) TypedValue.applyDimension(1, 8.0f, this.g.getResources().getDisplayMetrics());
            layoutParams.bottomMargin = 0;
            if (view == this.n.getChildAt(2)) {
                layoutParams.bottomMargin = (int) TypedValue.applyDimension(1, 8.0f, this.g.getResources().getDisplayMetrics());
            }
            layoutParams.height = -2;
            layoutParams.width = -1;
            view.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eih_(WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        if (layoutParams == null || displayMetrics == null) {
            return;
        }
        layoutParams.width = displayMetrics.widthPixels;
    }

    private void c() {
        if (smp.b(this.g) && Float.compare(smp.a(this.g), 1.75f) >= 0 && !DateFormat.is24HourFormat(this.g)) {
            this.b.setText(R.string._2130850822_res_0x7f023406);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eic_(WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        Activity activity = this.f;
        if (activity == null || layoutParams == null || displayMetrics == null) {
            return;
        }
        layoutParams.width = Math.min((int) activity.getResources().getDimension(R.dimen._2131364508_res_0x7f0a0a9c), displayMetrics.widthPixels);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eif_(boolean z, WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        if (layoutParams == null || displayMetrics == null) {
            return;
        }
        if (z) {
            layoutParams.width = (int) (displayMetrics.widthPixels * 0.65d);
        } else {
            layoutParams.width = displayMetrics.widthPixels;
        }
    }

    private void c(String str) {
        HwTextView hwTextView;
        if (str == null || (hwTextView = this.h) == null) {
            return;
        }
        hwTextView.setText(str);
    }
}
