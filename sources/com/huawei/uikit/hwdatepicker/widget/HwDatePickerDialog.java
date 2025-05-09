package com.huawei.uikit.hwdatepicker.widget;

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
import com.huawei.uikit.hwdatepicker.R$style;
import com.huawei.uikit.hwdatepicker.widget.HwDatePicker;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import defpackage.skj;
import defpackage.skk;
import defpackage.slj;
import defpackage.slv;
import defpackage.smp;
import defpackage.sms;
import java.util.GregorianCalendar;

/* loaded from: classes7.dex */
public class HwDatePickerDialog extends AlertDialog implements HwDatePicker.OnDateChangedListener {

    /* renamed from: a, reason: collision with root package name */
    private static int f10635a = 0;
    private static final String e = "HwDatePickerDialog";
    private Activity b;
    protected OnButtonClickCallback c;
    protected Context d;
    private GregorianCalendar f;
    private boolean g;
    private HwTextView h;
    private String i;
    private HwTextView j;
    private boolean k;
    private View l;
    private HwDatePicker m;
    private HwTextView n;
    private boolean o;
    private LinearLayout p;
    private LinearLayout q;
    private LinearLayout r;

    public interface OnButtonClickCallback {
        void onNegativeButtonClick(HwDatePicker hwDatePicker);

        void onPositiveButtonClick(HwDatePicker hwDatePicker);
    }

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (HwDatePickerDialog.this.l()) {
                HwDatePickerDialog.this.i();
            } else {
                HwDatePickerDialog.this.m.c();
                HwDatePickerDialog.this.j.setText(R.string._2130850820_res_0x7f023404);
                HwDatePickerDialog.this.h.setText(R.string._2130850821_res_0x7f023405);
                HwDatePickerDialog.this.j();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (HwDatePickerDialog.this.o()) {
                HwDatePickerDialog.this.g();
            } else {
                HwDatePickerDialog.this.m.b();
                HwDatePickerDialog.this.j.setText(R.string._2130850822_res_0x7f023406);
                HwDatePickerDialog.this.h.setText(R.string._2130850827_res_0x7f02340b);
                HwDatePickerDialog.this.h();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class d implements Runnable {
        final /* synthetic */ TypedValue b;
        final /* synthetic */ int c;

        /* renamed from: com.huawei.uikit.hwdatepicker.widget.HwDatePickerDialog$d$d, reason: collision with other inner class name */
        class C0272d extends ViewOutlineProvider {
            C0272d() {
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), d.this.c);
                HwDatePickerDialog.this.d.getTheme().resolveAttribute(R.attr._2131100194_res_0x7f060222, d.this.b, true);
                HwDatePickerDialog.this.l.setBackgroundColor(d.this.b.data);
            }
        }

        d(int i, TypedValue typedValue) {
            this.c = i;
            this.b = typedValue;
        }

        @Override // java.lang.Runnable
        public void run() {
            HwDatePickerDialog.this.l.setOutlineProvider(new C0272d());
            HwDatePickerDialog.this.l.setClipToOutline(true);
        }
    }

    class e implements View.OnLayoutChangeListener {
        final /* synthetic */ Window b;

        e(Window window) {
            this.b = window;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int unused = HwDatePickerDialog.f10635a = HwDatePickerDialog.this.d.getResources().getConfiguration().orientation;
            DisplayMetrics displayMetrics = HwDatePickerDialog.this.d.getResources().getDisplayMetrics();
            WindowManager.LayoutParams attributes = this.b.getAttributes();
            if (skk.b() || skk.c(HwDatePickerDialog.this.b)) {
                HwDatePickerDialog.this.ebZ_(attributes, displayMetrics);
            } else if (slj.ebP_(HwDatePickerDialog.this.b)) {
                HwDatePickerDialog.this.ebW_(attributes, displayMetrics);
            } else if (HwDatePickerDialog.f10635a == 2) {
                HwDatePickerDialog.this.ebY_(true, attributes, displayMetrics);
                HwDatePickerDialog.this.k();
            } else {
                HwDatePickerDialog.this.ebY_(false, attributes, displayMetrics);
            }
            this.b.setAttributes(attributes);
        }
    }

    public HwDatePickerDialog(Activity activity, OnButtonClickCallback onButtonClickCallback) {
        this(activity, R$style.Widget_Emui_HwDatePickerDialogStyle, onButtonClickCallback, null);
    }

    private void s() {
        t();
        this.m.b();
        this.m.e();
    }

    private void t() {
        if (sms.b(this.d) == 1 && Float.compare(smp.a(this.d), 1.75f) >= 0) {
            this.n.setMaxLines(2);
            this.n.setAutoTextSize(1, 35.0f);
            if (Float.compare(smp.a(this.d), 2.0f) >= 0) {
                this.n.setAutoTextSize(1, 40.0f);
            }
        }
    }

    public void a(int i) {
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker != null) {
            hwDatePicker.setSpinnersSelectorPaintColor(i);
        }
        e(i);
    }

    public void a(boolean z) {
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker != null) {
            hwDatePicker.setmIsWestern(z);
        }
    }

    public void a(boolean z, String str) {
        if (str == null) {
            this.g = false;
        } else {
            this.g = z;
        }
        if (this.g) {
            this.i = str;
        }
    }

    protected void b() {
        Window window = getWindow();
        if (window == null || this.b == null) {
            return;
        }
        window.getDecorView().addOnLayoutChangeListener(new e(window));
    }

    public void b(String str) {
        HwTextView hwTextView = this.n;
        if (hwTextView != null) {
            hwTextView.setText(str);
            this.n.requestLayout();
        }
    }

    protected void c() {
        if (this.m != null && "".equals(this.n.getText().toString())) {
            b(b(this.m.getMonth(), this.m.getDayOfMonth(), this.f));
        }
    }

    public void c(boolean z) {
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker != null) {
            hwDatePicker.setIsShowAllYears(z);
        }
    }

    public HwDatePicker d() {
        return this.m;
    }

    public void d(GregorianCalendar gregorianCalendar, GregorianCalendar gregorianCalendar2) {
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker == null) {
            return;
        }
        hwDatePicker.a(gregorianCalendar, gregorianCalendar2);
    }

    public void d(boolean z) {
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker != null) {
            hwDatePicker.setmIsSupportLunarSwitch(z);
        }
    }

    protected void e() {
        Window window = getWindow();
        f10635a = this.d.getResources().getConfiguration().orientation;
        if (window != null && this.b != null) {
            window.setWindowAnimations(R$style.Widget_Emui_HwDatePickerDialogAnim);
            DisplayMetrics displayMetrics = this.d.getResources().getDisplayMetrics();
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (skk.b() || skk.c(this.b)) {
                window.setGravity(17);
                ebZ_(attributes, displayMetrics);
            } else if (slj.ebP_(this.b)) {
                window.setGravity(17);
                ebW_(attributes, displayMetrics);
            } else if (f10635a == 2) {
                window.setGravity(17);
                ebY_(true, attributes, displayMetrics);
            } else {
                window.setGravity(80);
                ebY_(false, attributes, displayMetrics);
            }
            window.setAttributes(attributes);
        }
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker != null) {
            hwDatePicker.d();
        }
        k();
    }

    public void e(int i) {
        HwTextView hwTextView = this.j;
        if (hwTextView != null) {
            hwTextView.setTextColor(i);
        }
        HwTextView hwTextView2 = this.h;
        if (hwTextView2 != null) {
            hwTextView2.setTextColor(i);
        }
    }

    public void e(int i, int i2) {
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker == null) {
            return;
        }
        hwDatePicker.e(i, i2);
    }

    public void e(int i, int i2, int i3) {
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker == null) {
            return;
        }
        hwDatePicker.a(i, i2, i3);
    }

    public void e(boolean z) {
        this.o = z;
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker != null) {
            hwDatePicker.setLunarOrWestern(z);
        }
    }

    @Override // com.huawei.uikit.hwdatepicker.widget.HwDatePicker.OnDateChangedListener
    public void onDateChanged(HwDatePicker hwDatePicker, int i, int i2, int i3, GregorianCalendar gregorianCalendar) {
        HwDatePicker hwDatePicker2 = this.m;
        if (hwDatePicker2 == null) {
            return;
        }
        hwDatePicker2.c(i, i2, i3, this);
        if (this.k) {
            return;
        }
        b(b(i2, i3, gregorianCalendar));
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle == null) {
            return;
        }
        try {
            int i = bundle.getInt("year", 1);
            int i2 = bundle.getInt("month", 0);
            int i3 = bundle.getInt("day", 1);
            if (this.f == null) {
                this.f = new GregorianCalendar();
            }
            this.f.set(i, i2, i3);
            HwDatePicker hwDatePicker = this.m;
            if (hwDatePicker != null) {
                hwDatePicker.c(i, i2, i3, this);
                this.m.setLunarOrWestern(this.o);
            }
            boolean z = bundle.getBoolean("is_support_lunar_switch", false);
            boolean z2 = bundle.getBoolean("is_western", true);
            boolean z3 = bundle.getBoolean("is_show_all_years", true);
            HwDatePicker hwDatePicker2 = this.m;
            if (hwDatePicker2 != null) {
                hwDatePicker2.setmIsSupportLunarSwitch(z);
                this.m.setmIsWestern(z2);
                this.m.setIsShowAllYears(z3);
            }
            if (this.k) {
                return;
            }
            String string = bundle.getString("dialog_title", null);
            if (string == null) {
                string = b(i2, i3, this.f);
            }
            b(string);
        } catch (BadParcelableException unused) {
            Log.e(e, "onRestoreInstanceState: Bad parcel target.");
        }
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        HwTextView hwTextView;
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        try {
            HwDatePicker hwDatePicker = this.m;
            boolean z = true;
            int year = hwDatePicker == null ? 1 : hwDatePicker.getYear();
            HwDatePicker hwDatePicker2 = this.m;
            int month = hwDatePicker2 == null ? 1 : hwDatePicker2.getMonth();
            HwDatePicker hwDatePicker3 = this.m;
            int dayOfMonth = hwDatePicker3 == null ? 1 : hwDatePicker3.getDayOfMonth();
            onSaveInstanceState.putInt("year", year);
            onSaveInstanceState.putInt("month", month);
            onSaveInstanceState.putInt("day", dayOfMonth);
            HwDatePicker hwDatePicker4 = this.m;
            boolean z2 = hwDatePicker4 != null && hwDatePicker4.g();
            HwDatePicker hwDatePicker5 = this.m;
            boolean z3 = hwDatePicker5 != null && hwDatePicker5.h();
            HwDatePicker hwDatePicker6 = this.m;
            if (hwDatePicker6 == null || !hwDatePicker6.j()) {
                z = false;
            }
            onSaveInstanceState.putBoolean("is_support_lunar_switch", z2);
            onSaveInstanceState.putBoolean("is_western", z3);
            onSaveInstanceState.putBoolean("is_show_all_years", z);
            if (!this.k && (hwTextView = this.n) != null) {
                onSaveInstanceState.putString("dialog_title", hwTextView.getText().toString());
            }
        } catch (BadParcelableException unused) {
            Log.e(e, "onSaveInstanceState: Bad parcel target.");
        }
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        b();
    }

    @Override // android.app.Dialog
    protected void onStop() {
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        setContentView(this.l);
        e();
        c();
    }

    public HwDatePickerDialog(Activity activity, int i, OnButtonClickCallback onButtonClickCallback, GregorianCalendar gregorianCalendar) {
        super(activity, i);
        this.o = false;
        this.g = false;
        this.i = null;
        this.c = onButtonClickCallback;
        Context context = getContext();
        this.d = context;
        this.b = activity;
        f10635a = context.getResources().getConfiguration().orientation;
        this.k = this.d.getResources().getInteger(R.integer._2131623948_res_0x7f0e000c) == 2;
        m();
        this.l.post(new d(getContext().getResources().getDimensionPixelSize(R.dimen._2131362601_res_0x7f0a0329), new TypedValue()));
        n();
        setIcon(0);
        if (gregorianCalendar == null) {
            this.f = new GregorianCalendar();
        } else {
            this.f = gregorianCalendar;
        }
        this.m.c(this.f.get(1), this.f.get(2), this.f.get(5), this);
        s();
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        dismiss();
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker == null || this.c == null) {
            return;
        }
        hwDatePicker.clearFocus();
        this.c.onNegativeButtonClick(this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (Float.compare(smp.a(this.d), 2.0f) < 0) {
            return;
        }
        if (f10635a != 2 || skk.c(this.b) || skk.b()) {
            this.q.removeView(this.h);
            this.q.addView(this.h);
            ebV_(this.j);
            ebV_(this.h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        dismiss();
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker == null || this.c == null) {
            return;
        }
        hwDatePicker.clearFocus();
        this.c.onPositiveButtonClick(this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (Float.compare(smp.a(this.d), 2.0f) < 0) {
            return;
        }
        if (f10635a != 2 || skk.c(this.b) || skk.b()) {
            this.q.removeView(this.j);
            this.q.addView(this.j);
            ebV_(this.j);
            ebV_(this.h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (smp.b(this.d) && Float.compare(smp.a(this.d), 2.0f) >= 0 && f10635a == 2 && !skk.b() && !skk.c(this.b)) {
            this.j.setAutoTextSize(1, 32.0f);
            this.h.setAutoTextSize(1, 32.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        if (Float.compare(smp.a(this.d), 1.75f) < 0) {
            return true;
        }
        return this.d.getResources().getString(R.string._2130850820_res_0x7f023404).contentEquals(this.j.getText());
    }

    private void m() {
        View inflate = View.inflate(this.d, R.layout.hwdatepicker_dialog, null);
        this.l = inflate;
        this.p = (LinearLayout) inflate.findViewById(R.id.hwdatepicker_dialog_title_layout);
        this.r = (LinearLayout) this.l.findViewById(R.id.hwdatepicker_dialog_content_layout);
        this.q = (LinearLayout) this.l.findViewById(R.id.hwdatepicker_dialog_button_layout);
        this.n = (HwTextView) this.l.findViewById(R.id.hwdatepicker_dialog_title);
        this.m = (HwDatePicker) this.l.findViewById(R.id.hwdatepicker_dialog_date_picker);
        this.j = (HwTextView) this.l.findViewById(R.id.hwdatepicker_dialog_positive_btn);
        this.h = (HwTextView) this.l.findViewById(R.id.hwdatepicker_dialog_negative_btn);
    }

    private void n() {
        if (this.k) {
            return;
        }
        f();
        this.j.setOnClickListener(new a());
        this.h.setOnClickListener(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean o() {
        if (Float.compare(smp.a(this.d), 1.75f) < 0) {
            return true;
        }
        return this.d.getResources().getString(R.string._2130850827_res_0x7f02340b).contentEquals(this.h.getText());
    }

    private void q() {
        if (smp.b(this.d) && Float.compare(smp.a(this.d), 1.75f) >= 0) {
            this.j.setMaxLines(2);
            this.h.setMaxLines(2);
            this.j.setAutoTextInfo(13, 16, 2);
            this.h.setAutoTextInfo(13, 16, 2);
            b(this.j);
            b(this.h);
            if ((f10635a != 2 || skk.c(this.b) || skk.b()) && Float.compare(smp.a(this.d), 2.0f) >= 0) {
                this.q.setOrientation(1);
                this.l.findViewById(R.id.hwdatepicker_dialog_split_line).setVisibility(8);
                this.q.removeView(this.h);
                this.q.addView(this.h);
                ebV_(this.j);
                ebV_(this.h);
                if (this.q.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.q.getLayoutParams();
                    layoutParams.weight = 0.0f;
                    layoutParams.height = -2;
                    layoutParams.width = -1;
                    this.q.setLayoutParams(layoutParams);
                }
            }
        }
    }

    private void b(View view) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = (int) TypedValue.applyDimension(1, 8.0f, this.d.getResources().getDisplayMetrics());
            layoutParams.bottomMargin = (int) TypedValue.applyDimension(1, 8.0f, this.d.getResources().getDisplayMetrics());
            view.setLayoutParams(layoutParams);
        }
    }

    private void ebV_(View view) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.weight = 0.0f;
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
            layoutParams.topMargin = (int) TypedValue.applyDimension(1, 8.0f, this.d.getResources().getDisplayMetrics());
            layoutParams.bottomMargin = 0;
            if (view == this.q.getChildAt(2)) {
                layoutParams.bottomMargin = (int) TypedValue.applyDimension(1, 8.0f, this.d.getResources().getDisplayMetrics());
            }
            layoutParams.height = -2;
            layoutParams.width = -1;
            view.setLayoutParams(layoutParams);
        }
    }

    private void f() {
        if (smp.b(this.d) && Float.compare(smp.a(this.d), 1.75f) >= 0) {
            this.j.setText(R.string._2130850822_res_0x7f023406);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ebZ_(WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        Activity activity = this.b;
        if (activity == null || layoutParams == null || displayMetrics == null) {
            return;
        }
        layoutParams.width = Math.min((int) activity.getResources().getDimension(R.dimen._2131364139_res_0x7f0a092b), displayMetrics.widthPixels);
    }

    private int ebT_(Activity activity) {
        return skj.b(activity) ? 65558 : 98326;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ebW_(WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        if (layoutParams == null || displayMetrics == null) {
            return;
        }
        layoutParams.width = displayMetrics.widthPixels;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ebY_(boolean z, WindowManager.LayoutParams layoutParams, DisplayMetrics displayMetrics) {
        if (layoutParams == null || displayMetrics == null) {
            return;
        }
        if (z) {
            layoutParams.width = (int) (displayMetrics.widthPixels * 0.65d);
        } else {
            layoutParams.width = displayMetrics.widthPixels;
        }
    }

    private String b(int i, int i2, GregorianCalendar gregorianCalendar) {
        HwDatePicker hwDatePicker = this.m;
        if (hwDatePicker == null) {
            return "";
        }
        if (!this.g) {
            if (hwDatePicker.a()) {
                if (!this.m.j() && !this.m.h()) {
                    String displayedString = this.m.getDisplayedString();
                    String[] strArr = slj.c;
                    int i3 = gregorianCalendar.get(7) - 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append(displayedString);
                    if (i3 < 0) {
                        i3 = 0;
                    }
                    sb.append(strArr[i3]);
                    return sb.toString();
                }
                return DateUtils.formatDateTime(this.b, gregorianCalendar.getTimeInMillis(), ebT_(this.b));
            }
            if (this.m.h()) {
                String[] shortMonths = this.m.getShortMonths();
                String[] shortMonthDays = this.m.getShortMonthDays();
                return shortMonths[i % shortMonths.length] + shortMonthDays[(i2 - 1) % shortMonthDays.length];
            }
            String[] strArr2 = slv.d;
            String[] strArr3 = slv.e;
            return strArr2[(i - 1) % strArr2.length] + strArr3[(i2 - 1) % strArr3.length];
        }
        return this.i;
    }
}
