package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes4.dex */
public class IndoorRunCalibrationDistanceDialog {

    /* renamed from: a, reason: collision with root package name */
    private Context f3817a;
    private HealthEditText b;
    private CustomViewDialog.Builder c;
    private CustomViewDialog d;
    private View e;
    private IndoorRunCalibrationDistanceInterface f;
    private HealthTextView g;
    private float h;
    private float i;
    private float j;
    private String l;
    private String m = null;
    private HealthButton n;
    private IndoorRunCalibrationDistanceInterface o;

    public interface IndoorRunCalibrationDistanceInterface {
        void onClick(Dialog dialog, float f);
    }

    public IndoorRunCalibrationDistanceDialog(Context context, float f) {
        this.f3817a = context;
        this.h = f;
        c();
        d();
        e();
    }

    private void c() {
        View inflate = View.inflate(this.f3817a, R.layout.dialog_indoor_run_calibration_distance, null);
        this.e = inflate;
        HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.custom_target_editText);
        this.b = healthEditText;
        healthEditText.setInputType(8194);
        new Timer("Track_IndoorRunCalibrationDistanceDialog").schedule(new TimerTask() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.IndoorRunCalibrationDistanceDialog.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Object systemService = IndoorRunCalibrationDistanceDialog.this.b.getContext().getSystemService("input_method");
                if (systemService instanceof InputMethodManager) {
                    ((InputMethodManager) systemService).showSoftInput(IndoorRunCalibrationDistanceDialog.this.b, 0);
                } else {
                    LogUtil.a("Track_IndoorRunCalibrationDistanceDialog", "object is not instanceof InputMethodManager");
                }
            }
        }, 300L);
        this.g = (HealthTextView) this.e.findViewById(R.id.custom_target_tip);
        this.e.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
    }

    private void j() {
        this.b.addTextChangedListener(new TextWatcher() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.IndoorRunCalibrationDistanceDialog.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable == null) {
                    return;
                }
                if (!TextUtils.isEmpty(editable.toString())) {
                    IndoorRunCalibrationDistanceDialog.this.c.a(true);
                } else {
                    IndoorRunCalibrationDistanceDialog.this.c.a(false);
                }
                IndoorRunCalibrationDistanceDialog.this.bkL_(editable);
                IndoorRunCalibrationDistanceDialog.this.bkK_(editable);
            }
        });
    }

    private void e() {
        String i = i();
        float round = (Math.round(this.h * 100.0f) * 1.0f) / 100.0f;
        this.h = round;
        String f = Float.toString((float) UnitUtil.a(round, 2));
        if (!TextUtils.isEmpty(f) && f.length() <= 6) {
            this.b.setText(f);
            this.b.setSelection(f.length());
            this.b.requestFocus();
            this.c.a(true);
        }
        if (i != null) {
            this.g.setText(i);
        }
    }

    private void d() {
        Resources resources = BaseApplication.getContext().getResources();
        if (resources == null) {
            LogUtil.h("Track_IndoorRunCalibrationDistanceDialog", "initData resources is null");
            return;
        }
        this.l = resources.getString(R.string._2130844220_res_0x7f021a3c);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f3817a);
        this.c = builder;
        builder.b(false);
        CustomViewDialog e = this.c.czg_(this.e).a(this.l).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.IndoorRunCalibrationDistanceDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_IndoorRunCalibrationDistanceDialog", "dialog cancel");
                if (IndoorRunCalibrationDistanceDialog.this.f != null) {
                    IndoorRunCalibrationDistanceDialog.this.f.onClick(IndoorRunCalibrationDistanceDialog.this.d, 0.0f);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.IndoorRunCalibrationDistanceDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_IndoorRunCalibrationDistanceDialog", "dialog ok");
                IndoorRunCalibrationDistanceDialog indoorRunCalibrationDistanceDialog = IndoorRunCalibrationDistanceDialog.this;
                indoorRunCalibrationDistanceDialog.m = indoorRunCalibrationDistanceDialog.b.getText().toString().trim();
                if (TextUtils.isEmpty(IndoorRunCalibrationDistanceDialog.this.m)) {
                    IndoorRunCalibrationDistanceDialog.this.b();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (!IndoorRunCalibrationDistanceDialog.d(IndoorRunCalibrationDistanceDialog.this.m)) {
                    IndoorRunCalibrationDistanceDialog.this.b(false);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                float j = CommonUtil.j(IndoorRunCalibrationDistanceDialog.this.m);
                if (IndoorRunCalibrationDistanceDialog.this.e(j)) {
                    IndoorRunCalibrationDistanceDialog.this.b(false);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    if (IndoorRunCalibrationDistanceDialog.this.o != null) {
                        IndoorRunCalibrationDistanceDialog.this.o.onClick(IndoorRunCalibrationDistanceDialog.this.d, j);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).e();
        this.d = e;
        e.setCanceledOnTouchOutside(false);
        j();
        this.n = this.c.b();
    }

    public IndoorRunCalibrationDistanceDialog c(IndoorRunCalibrationDistanceInterface indoorRunCalibrationDistanceInterface) {
        this.o = indoorRunCalibrationDistanceInterface;
        return this;
    }

    public IndoorRunCalibrationDistanceDialog e(IndoorRunCalibrationDistanceInterface indoorRunCalibrationDistanceInterface) {
        this.f = indoorRunCalibrationDistanceInterface;
        return this;
    }

    private String i() {
        String quantityString;
        c(6);
        float f = 0.35f * this.h;
        this.i = (float) UnitUtil.a(r0 - f, 2);
        this.j = (float) UnitUtil.a(this.h + f, 2);
        if (UnitUtil.h()) {
            if (this.i < 0.07f) {
                this.i = 0.07f;
            }
            if (this.j > 620.0f) {
                this.j = 620.0f;
            }
        } else {
            if (this.i < 0.1f) {
                this.i = 0.1f;
            }
            if (this.j > 999.99f) {
                this.j = 999.99f;
            }
        }
        String e = UnitUtil.e(this.i, 1, 2);
        String e2 = UnitUtil.e(this.j, 1, 2);
        if (UnitUtil.h()) {
            quantityString = this.f3817a.getResources().getQuantityString(R.plurals._2130903302_res_0x7f030106, (int) this.j, e2);
        } else {
            quantityString = this.f3817a.getResources().getQuantityString(R.plurals._2130903301_res_0x7f030105, (int) this.j, e2);
        }
        String format = String.format(Locale.ENGLISH, this.f3817a.getResources().getString(R.string._2130844221_res_0x7f021a3d), e, quantityString);
        this.b.setInputType(8194);
        return format;
    }

    public void c(int i) {
        if (i > 0) {
            this.b.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bkK_(Editable editable) {
        if (this.n.isEnabled() || TextUtils.isEmpty(editable) || !d(editable.toString()) || e(CommonUtil.j(editable.toString()))) {
            return;
        }
        b(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (z) {
            Context context = this.f3817a;
            if (context == null) {
                LogUtil.b("Track_IndoorRunCalibrationDistanceDialog", "mContext is null");
                return;
            } else {
                this.g.setTextColor(context.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
                this.c.a(true);
                return;
            }
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Context context = this.f3817a;
        if (context == null) {
            LogUtil.b("Track_IndoorRunCalibrationDistanceDialog", "mContext is null");
        } else {
            this.g.setTextColor(context.getResources().getColor(R.color._2131296671_res_0x7f09019f));
            this.c.a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(float f) {
        LogUtil.a("Track_IndoorRunCalibrationDistanceDialog", "isValueInRange tmpValue:", Float.valueOf(f));
        return f < this.i || f > this.j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bkL_(Editable editable) {
        String obj = editable.toString();
        if (obj.length() > 1 && !d(obj)) {
            editable.delete(obj.length() - 1, obj.length());
            return;
        }
        int e = e(obj);
        if (e >= 0 && (obj.length() - e) - 1 > 2) {
            editable.delete(e + 3, e + 4);
        }
    }

    public static int e(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        char[] charArray = str.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            int i2 = i + 1;
            if (!c(str.substring(i, i2))) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(String str) {
        LogUtil.a("Track_IndoorRunCalibrationDistanceDialog", " isNumber = ", str, " , isInteger is ", Boolean.valueOf(c(str)), " ,isFloat is ", Boolean.valueOf(b(str)));
        return c(str) || b(str);
    }

    private static boolean c(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private static boolean b(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void a() {
        CustomViewDialog customViewDialog = this.d;
        if (customViewDialog == null || customViewDialog.isShowing()) {
            return;
        }
        this.d.show();
    }
}
