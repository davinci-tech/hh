package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ixx;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes6.dex */
public class SportCustomTargetDialog extends BaseDialog {
    public SportCustomTargetDialog(Context context) {
        super(context);
    }

    public SportCustomTargetDialog(Context context, int i) {
        super(context, i);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f9561a;
        private HealthEditText b;
        private Handler c;
        private Context d;
        private HealthButton e;
        private double f;
        private int g = 0;
        private int h = 0;
        private String i = null;
        private int j = 258;

        public b(Context context, Handler handler) {
            this.d = context;
            this.c = handler;
        }

        public void d(double d) {
            this.f = d;
        }

        public SportCustomTargetDialog d(int i, int i2) {
            this.g = i;
            Object systemService = this.d.getSystemService("layout_inflater");
            if (!(systemService instanceof LayoutInflater)) {
                LogUtil.a("Track_SportCustomTargetDialog", "object is not instanceof Inflater");
                return null;
            }
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.sport_dialog_custom_target_item, (ViewGroup) null);
            this.j = i2;
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.ok);
            this.e = healthButton;
            healthButton.setEnabled(false);
            dfW_(i, inflate, i2);
            HealthButton healthButton2 = (HealthButton) inflate.findViewById(R.id.cancel);
            final SportCustomTargetDialog sportCustomTargetDialog = new SportCustomTargetDialog(this.d, R.style.TrackDialog);
            healthButton2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportCustomTargetDialog.b.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    sportCustomTargetDialog.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.b.addTextChangedListener(new TextWatcher() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportCustomTargetDialog.b.1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    if (editable == null) {
                        return;
                    }
                    if (!TextUtils.isEmpty(editable.toString())) {
                        b.this.e.setEnabled(true);
                    } else {
                        b.this.e.setEnabled(false);
                    }
                    b.this.dfV_(editable);
                    b.this.dfU_(editable);
                }
            });
            a(sportCustomTargetDialog);
            sportCustomTargetDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            sportCustomTargetDialog.setContentView(inflate);
            return sportCustomTargetDialog;
        }

        private void a(final SportCustomTargetDialog sportCustomTargetDialog) {
            this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportCustomTargetDialog.b.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    float f;
                    b.this.b();
                    float c = b.this.c();
                    int i = b.this.h;
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i == 5 && b.this.a(5, c)) {
                                    b.this.c(false);
                                    ViewClickInstrumentation.clickOnView(view);
                                    return;
                                }
                            } else {
                                if (b.this.a(2, c)) {
                                    b.this.c(false);
                                    ViewClickInstrumentation.clickOnView(view);
                                    return;
                                }
                                f = 1000.0f;
                            }
                        } else if (b.this.a(1, c)) {
                            b.this.c(false);
                            ViewClickInstrumentation.clickOnView(view);
                            return;
                        } else if (UnitUtil.h()) {
                            c = (float) UnitUtil.d(c, 3);
                            b.this.i = c + "";
                        }
                        b.this.c(c, sportCustomTargetDialog);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                    if (b.this.a(0, c)) {
                        b.this.c(false);
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    f = 60.0f;
                    c *= f;
                    b.this.c(c, sportCustomTargetDialog);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            this.h = this.g;
            String replaceAll = this.b.getText().toString().trim().replaceAll(",", "");
            this.i = replaceAll;
            if (TextUtils.isEmpty(replaceAll)) {
                d();
            } else {
                if (this.h != 1 || b(this.i)) {
                    return;
                }
                c(false);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float c() {
            try {
                return Float.parseFloat(this.i);
            } catch (NumberFormatException e) {
                LogUtil.b("Track_SportCustomTargetDialog", e.getMessage());
                return 0.0f;
            }
        }

        private void d() {
            Context context = this.d;
            if (context == null) {
                LogUtil.a("Track_SportCustomTargetDialog", "mContext is null");
            } else {
                this.f9561a.setTextColor(context.getResources().getColor(R.color._2131296671_res_0x7f09019f));
                this.e.setEnabled(false);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(float f, SportCustomTargetDialog sportCustomTargetDialog) {
            Handler handler = this.c;
            if (handler == null) {
                LogUtil.a("Track_SportCustomTargetDialog", "mHandler is null");
                return;
            }
            Message obtainMessage = handler.obtainMessage(1, this.h, 0, Float.valueOf(f));
            LogUtil.a("Track_SportCustomTargetDialog", " mTargetType = ", Integer.valueOf(this.h), " mTargetValueString = ", Float.valueOf(f));
            this.c.sendMessage(obtainMessage);
            d((int) f);
            sportCustomTargetDialog.dismiss();
        }

        private void d(int i) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("click", 1);
            hashMap.put("goalType", Integer.valueOf(this.h));
            if (!Utils.o()) {
                hashMap.put("goalValue", Integer.valueOf(i));
            }
            ixx.d().d(this.d, AnalyticsValue.BI_TRACK_SPORT_GOAL_ACTION_KEY.value(), hashMap, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(int i, float f) {
            int i2;
            float f2;
            if (i != 0) {
                if (i == 1) {
                    if (UnitUtil.h()) {
                        i2 = 62;
                        f2 = 0.06f;
                    } else {
                        i2 = 100;
                        f2 = 0.1f;
                    }
                    if (this.j == 259) {
                        i2 *= 2;
                    }
                    if (f < f2 || f > i2) {
                        return true;
                    }
                } else if (i != 2) {
                    if (i == 5 && (f < 50.0f || f > 10000.0f)) {
                        return true;
                    }
                } else if (f < 100.0f || f > 5000.0f) {
                    return true;
                }
            } else if (f < 10.0f || f > 1440.0f) {
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dfU_(Editable editable) {
            if (this.e.isEnabled() || TextUtils.isEmpty(editable)) {
                return;
            }
            if (this.h != 1 || b(editable.toString())) {
                float j = CommonUtil.j(editable.toString());
                int i = this.h;
                if (i != 0) {
                    if (i != 1) {
                        if (i == 2) {
                            if (a(2, j)) {
                                return;
                            }
                        } else if (i == 5 && a(5, j)) {
                            return;
                        }
                    } else if (a(1, j)) {
                        return;
                    }
                } else if (a(0, j)) {
                    return;
                }
                c(true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dfV_(Editable editable) {
            if (this.g == 1) {
                String obj = editable.toString();
                if (obj.length() > 1 && !b(obj)) {
                    editable.delete(obj.length() - 1, obj.length());
                    return;
                }
                int e = e(obj);
                if (e >= 0 && (obj.length() - e) - 1 > 2) {
                    editable.delete(e + 3, e + 4);
                }
            }
        }

        private void dfW_(int i, View view, int i2) {
            String c;
            HealthEditText healthEditText = (HealthEditText) view.findViewById(R.id.custom_target_editText);
            this.b = healthEditText;
            healthEditText.setInputType(2);
            new Timer("Track_SportCustomTargetDialog").schedule(new TimerTask() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportCustomTargetDialog.b.5
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Object systemService = b.this.b.getContext().getSystemService("input_method");
                    if (systemService instanceof InputMethodManager) {
                        ((InputMethodManager) systemService).showSoftInput(b.this.b, 0);
                    } else {
                        LogUtil.a("Track_SportCustomTargetDialog", "object is not instanceof InputMethodManager");
                    }
                }
            }, 300L);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.custom_target_unit);
            this.f9561a = (HealthTextView) view.findViewById(R.id.custom_target_tip);
            String string = this.d.getResources().getString(R.string._2130841789_res_0x7f0210bd);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.dialog_title);
            if (i == 0) {
                c = c(healthTextView2, healthTextView, string);
            } else if (i == 1) {
                c = e(i2, healthTextView2, healthTextView);
            } else if (i == 2) {
                c = a(healthTextView2, healthTextView, string);
            } else {
                c = i != 5 ? null : e(healthTextView2, healthTextView, this.d.getResources().getString(R.string._2130840003_res_0x7f0209c3));
            }
            e();
            if (c != null) {
                this.f9561a.setText(c);
            }
        }

        private void e() {
            String e;
            double d = this.f;
            if (d == -1.0d || Math.abs(d) <= 1.0E-6d) {
                return;
            }
            int i = this.g;
            if (i == 0) {
                e = UnitUtil.e(this.f / 60.0d, 1, 0);
            } else if (i == 1) {
                if (UnitUtil.h()) {
                    this.f = (float) UnitUtil.e(this.f, 3);
                }
                e = UnitUtil.e(this.f, 1, 2);
            } else if (i == 2) {
                e = UnitUtil.e(this.f / 1000.0d, 1, 0);
            } else {
                e = i != 5 ? "" : UnitUtil.e(this.f, 1, 0);
            }
            if (TextUtils.isEmpty(e)) {
                return;
            }
            this.b.setText(e.replaceAll(",", ""));
            this.b.setSelection(e.length() - 1);
            this.e.setEnabled(true);
        }

        private String c(HealthTextView healthTextView, HealthTextView healthTextView2, String str) {
            a(4);
            String string = this.d.getResources().getString(R.string._2130841436_res_0x7f020f5c);
            healthTextView2.setText(string);
            healthTextView.setText(this.d.getResources().getString(R.string._2130841790_res_0x7f0210be));
            return String.format(str, 10, Integer.valueOf(ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL), string).replace("1440", UnitUtil.e(1440.0d, 1, 0));
        }

        private String e(int i, HealthTextView healthTextView, HealthTextView healthTextView2) {
            int i2;
            int i3;
            String format;
            String string = this.d.getResources().getString(R.string._2130842530_res_0x7f0213a2);
            if (LanguageUtil.m(this.d.getApplicationContext())) {
                SpannableString spannableString = new SpannableString(this.d.getResources().getString(R.string._2130850490_res_0x7f0232ba));
                spannableString.setSpan(new AbsoluteSizeSpan(15, true), 0, spannableString.length(), 33);
                this.b.setHint(new SpannedString(spannableString));
            }
            a(6);
            if (i == 259) {
                i2 = 200;
                i3 = 124;
            } else {
                i2 = 100;
                i3 = 62;
            }
            String e = UnitUtil.e(0.10000000149011612d, 1, 1);
            String e2 = UnitUtil.e(0.05999999865889549d, 1, 2);
            if (UnitUtil.h()) {
                String string2 = this.d.getResources().getString(R.string._2130841383_res_0x7f020f27);
                healthTextView2.setText(string2);
                healthTextView.setText(this.d.getResources().getString(R.string._2130841530_res_0x7f020fba));
                format = String.format(string, e2, Integer.valueOf(i3), string2);
            } else {
                String string3 = this.d.getResources().getString(R.string._2130841382_res_0x7f020f26);
                healthTextView2.setText(string3);
                healthTextView.setText(this.d.getResources().getString(R.string._2130841530_res_0x7f020fba));
                format = String.format(string, e, Integer.valueOf(i2), string3);
            }
            this.b.setInputType(8194);
            return format;
        }

        private String a(HealthTextView healthTextView, HealthTextView healthTextView2, String str) {
            a(4);
            String string = this.d.getResources().getString(R.string._2130841384_res_0x7f020f28);
            healthTextView2.setText(string);
            healthTextView.setText(this.d.getResources().getString(R.string._2130841791_res_0x7f0210bf));
            return String.format(str, 100, 5000, string).replace("5000", UnitUtil.e(5000.0d, 1, 0));
        }

        private String e(HealthTextView healthTextView, HealthTextView healthTextView2, String str) {
            a(5);
            String quantityString = this.d.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 50, "");
            if (!TextUtils.isEmpty(quantityString)) {
                healthTextView2.setText(quantityString);
                healthTextView2.setVisibility(0);
            }
            healthTextView.setText(this.d.getResources().getString(R.string._2130839991_res_0x7f0209b7));
            if (LanguageUtil.m(this.d.getApplicationContext())) {
                SpannableString spannableString = new SpannableString(this.d.getResources().getString(R.string._2130839992_res_0x7f0209b8));
                spannableString.setSpan(new AbsoluteSizeSpan(15, true), 0, spannableString.length(), 33);
                this.b.setHint(new SpannedString(spannableString));
                this.b.setInputType(2);
            }
            return String.format(str, UnitUtil.e(50.0d, 1, 0), UnitUtil.e(10000.0d, 1, 0), quantityString);
        }

        public void c(boolean z) {
            if (z) {
                Context context = this.d;
                if (context == null) {
                    LogUtil.a("Track_SportCustomTargetDialog", "mContext is null");
                    return;
                } else {
                    this.f9561a.setTextColor(context.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
                    this.e.setEnabled(true);
                    return;
                }
            }
            d();
        }

        public void a(int i) {
            if (i > 0) {
                this.b.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
            }
        }

        public static boolean b(String str) {
            LogUtil.a("Track_SportCustomTargetDialog", " isNumber = ", str, " , isInteger is ", Boolean.valueOf(c(str)), " ,isFloat is ", Boolean.valueOf(d(str)));
            return c(str) || d(str);
        }

        public static boolean c(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException unused) {
                return false;
            }
        }

        public static boolean d(String str) {
            try {
                Float.parseFloat(str);
                return true;
            } catch (NumberFormatException unused) {
                return false;
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
    }
}
