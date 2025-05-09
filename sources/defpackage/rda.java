package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes7.dex */
public class rda extends BaseDialog {
    public rda(Context context, int i) {
        super(context, i);
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f16707a;
        private Context b;
        private EditText c;
        private Handler d;
        private HealthTextView e;
        private String g;
        private Toast h;
        private HealthButton i;
        private String j = null;
        private int f = 0;

        public c(Context context, Handler handler) {
            this.b = context;
            this.d = handler;
        }

        public rda e() {
            LogUtil.a("Track_InputHistoryDataSportDistanceDialog", "InputHistoryDataSportDistanceDialog is create");
            Object systemService = this.b.getSystemService("layout_inflater");
            if (!(systemService instanceof LayoutInflater)) {
                LogUtil.a("Track_InputHistoryDataSportDistanceDialog", "create ", "object is invalid type");
                return null;
            }
            final rda rdaVar = new rda(this.b, R.style.TrackDialog);
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.track_dialog_custom_target_item_emui9, (ViewGroup) null);
            dMc_(inflate);
            ((HealthButton) inflate.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: rda.c.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    rdaVar.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.ok);
            this.i = healthButton;
            healthButton.setEnabled(false);
            b();
            b(rdaVar);
            rdaVar.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            rdaVar.setContentView(inflate);
            return rdaVar;
        }

        public void e(int i) {
            this.f = i;
        }

        private void b(final rda rdaVar) {
            this.i.setOnClickListener(new View.OnClickListener() { // from class: rda.c.3
                /* JADX WARN: Removed duplicated region for block: B:11:0x0046  */
                /* JADX WARN: Removed duplicated region for block: B:13:0x004a  */
                @Override // android.view.View.OnClickListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onClick(android.view.View r6) {
                    /*
                        r5 = this;
                        rda$c r0 = rda.c.this
                        android.widget.EditText r1 = rda.c.dLZ_(r0)
                        android.text.Editable r1 = r1.getText()
                        java.lang.String r1 = r1.toString()
                        rda.c.c(r0, r1)
                        rda r0 = r2
                        if (r0 != 0) goto L19
                        com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation.clickOnView(r6)
                        return
                    L19:
                        rda$c r0 = rda.c.this
                        java.lang.String r0 = rda.c.a(r0)
                        boolean r0 = android.text.TextUtils.isEmpty(r0)
                        if (r0 == 0) goto L26
                        goto L3c
                    L26:
                        rda$c r0 = rda.c.this     // Catch: java.lang.NumberFormatException -> L31
                        java.lang.String r0 = rda.c.a(r0)     // Catch: java.lang.NumberFormatException -> L31
                        double r0 = java.lang.Double.parseDouble(r0)     // Catch: java.lang.NumberFormatException -> L31
                        goto L3e
                    L31:
                        java.lang.String r0 = "setOkButtonListener exception"
                        java.lang.Object[] r0 = new java.lang.Object[]{r0}
                        java.lang.String r1 = "Track_InputHistoryDataSportDistanceDialog"
                        com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
                    L3c:
                        r0 = 0
                    L3e:
                        rda$c r2 = rda.c.this
                        boolean r2 = rda.c.a(r2, r0)
                        if (r2 == 0) goto L4a
                        com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation.clickOnView(r6)
                        return
                    L4a:
                        r2 = 4636737291354636288(0x4059000000000000, double:100.0)
                        double r0 = r0 * r2
                        long r0 = java.lang.Math.round(r0)
                        int r0 = (int) r0
                        rda$c r1 = rda.c.this
                        android.os.Handler r1 = rda.c.dMa_(r1)
                        rda$c r2 = rda.c.this
                        java.lang.String r2 = rda.c.d(r2)
                        r3 = 10002(0x2712, float:1.4016E-41)
                        r4 = 0
                        android.os.Message r0 = r1.obtainMessage(r3, r0, r4, r2)
                        rda$c r1 = rda.c.this
                        android.os.Handler r1 = rda.c.dMa_(r1)
                        r1.sendMessage(r0)
                        rda r0 = r2
                        r0.dismiss()
                        com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation.clickOnView(r6)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: rda.c.AnonymousClass3.onClick(android.view.View):void");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c(double d) {
            int i;
            int i2;
            String string = this.b.getResources().getString(R$string.IDS_hwh_input_history_data_incorrext_distance_toast);
            double d2 = 25.0d;
            double d3 = 0.1d;
            if (UnitUtil.h()) {
                int i3 = this.f;
                if (i3 == 262 || i3 == 266) {
                    d3 = UnitUtil.e(25.0d, 2);
                    i2 = 300000;
                } else {
                    i2 = 300;
                    if (d < 0.1d || d > 300) {
                        c(String.format(string, Double.valueOf(0.1d), 300, this.g), this.b);
                        return true;
                    }
                }
                if (d >= d3 && d <= i2) {
                    return false;
                }
                c(String.format(string, Double.valueOf(d3), Integer.valueOf(i2), this.g), this.b);
                return true;
            }
            int i4 = this.f;
            if (i4 == 262 || i4 == 266) {
                i = 500000;
            } else {
                i = 500;
                d2 = 0.1d;
            }
            if (d >= d2 && d <= i) {
                return false;
            }
            c(String.format(string, Double.valueOf(d2), Integer.valueOf(i), this.g), this.b);
            return true;
        }

        private void b() {
            this.c.addTextChangedListener(new TextWatcher() { // from class: rda.c.4
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 1 && ".".equals(editable.toString())) {
                        c.this.c.setText("");
                        c.this.i.setEnabled(false);
                    } else if (TextUtils.isEmpty(editable.toString())) {
                        c.this.i.setEnabled(false);
                    } else {
                        c.this.i.setEnabled(true);
                    }
                    if (editable.length() > 1 && c.this.c.getText().toString().indexOf(".") >= 0 && c.this.c.getText().toString().indexOf(".", c.this.c.getText().toString().indexOf(".") + 1) > 0) {
                        c.this.c.setText(c.this.c.getText().toString().substring(0, c.this.c.getText().toString().length() - 1));
                        c.this.c.setSelection(c.this.c.getText().toString().length());
                    }
                    String obj = editable.toString();
                    int indexOf = obj.indexOf(".");
                    if (indexOf > 0 && (obj.length() - indexOf) - 1 > 2) {
                        editable.delete(indexOf + 3, indexOf + 4);
                    }
                }
            });
        }

        private void dMc_(View view) {
            int i;
            EditText editText = (EditText) view.findViewById(R.id.custom_target_editText);
            this.c = editText;
            editText.setInputType(8194);
            this.f16707a = (ImageView) view.findViewById(R.id.custom_target_under_line);
            this.c.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: rda.c.1
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view2, boolean z) {
                    if (c.this.b != null) {
                        c.this.f16707a.setBackgroundColor(c.this.b.getResources().getColor(R.color._2131298738_res_0x7f0909b2));
                    }
                    ViewScrollInstrumentation.focusChangeOnView(view2, z);
                }
            });
            new Timer("Track_InputHistoryDataSportDistanceDialog").schedule(new TimerTask() { // from class: rda.c.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Object systemService = c.this.c.getContext().getSystemService("input_method");
                    if (systemService instanceof InputMethodManager) {
                        ((InputMethodManager) systemService).showSoftInput(c.this.c, 0);
                    }
                }
            }, 300L);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.custom_target_unit);
            healthTextView.setVisibility(0);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.custom_target_tip);
            this.e = healthTextView2;
            healthTextView2.setVisibility(8);
            int i2 = this.f;
            if (i2 == 262 || i2 == 266) {
                if (!UnitUtil.h()) {
                    String string = this.b.getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
                    this.g = string;
                    healthTextView.setText(string);
                } else {
                    String quantityString = this.b.getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, 0);
                    this.g = quantityString;
                    healthTextView.setText(quantityString);
                }
                i = 9;
            } else {
                if (UnitUtil.h()) {
                    String string2 = this.b.getResources().getString(R$string.IDS_band_data_sport_distance_unit_en);
                    this.g = string2;
                    healthTextView.setText(string2);
                } else {
                    String string3 = this.b.getResources().getString(R$string.IDS_band_data_sport_distance_unit);
                    this.g = string3;
                    healthTextView.setText(string3);
                }
                i = 6;
            }
            this.c.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
        }

        private void c(String str, Context context) {
            Toast toast = this.h;
            if (toast == null) {
                this.h = Toast.makeText(context.getApplicationContext(), str, 1);
            } else {
                toast.setText(str);
                this.h.setDuration(1);
            }
            this.h.show();
        }
    }
}
