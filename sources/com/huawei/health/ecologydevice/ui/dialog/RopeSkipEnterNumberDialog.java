package com.huawei.health.ecologydevice.ui.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.dialog.RopeSkipEnterNumberDialog;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public class RopeSkipEnterNumberDialog extends BaseDialog {

    public interface OnConfirmClickListener {
        void onConfirmClick(int i);
    }

    public RopeSkipEnterNumberDialog(Context context, int i) {
        super(context, i);
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private HealthButton f2313a;
        private HealthEditText b;
        private OnConfirmClickListener c;
        private HealthTextView d;
        private Context e;
        private String j = null;

        private boolean c(int i) {
            return i < 50 || i > 10000;
        }

        public d(Context context, OnConfirmClickListener onConfirmClickListener) {
            this.e = context;
            this.c = onConfirmClickListener;
        }

        public RopeSkipEnterNumberDialog e() {
            Object systemService = this.e.getSystemService("layout_inflater");
            if (!(systemService instanceof LayoutInflater)) {
                LogUtil.a("RopeSkipEnterNumberDialog", "object is not instanceof Inflater");
                return null;
            }
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.dialog_rope_skip_set_num_item, (ViewGroup) null);
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.ok);
            this.f2313a = healthButton;
            healthButton.setEnabled(false);
            RopeSkipEnterNumberDialog ropeSkipEnterNumberDialog = new RopeSkipEnterNumberDialog(this.e, R.style.TrackDialog);
            TU_(inflate, ropeSkipEnterNumberDialog);
            ropeSkipEnterNumberDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            ropeSkipEnterNumberDialog.setContentView(inflate);
            return ropeSkipEnterNumberDialog;
        }

        private void TU_(View view, RopeSkipEnterNumberDialog ropeSkipEnterNumberDialog) {
            ((HealthTextView) view.findViewById(R.id.dialog_title)).setText(this.e.getResources().getString(R.string._2130845829_res_0x7f022085));
            ((HealthTextView) view.findViewById(R.id.number_unit_text)).setText(this.e.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, ""));
            HealthEditText healthEditText = (HealthEditText) view.findViewById(R.id.custom_target_editText);
            this.b = healthEditText;
            healthEditText.setInputType(2);
            SpannableString spannableString = new SpannableString(this.e.getResources().getString(R.string._2130839992_res_0x7f0209b8));
            spannableString.setSpan(new AbsoluteSizeSpan(15, true), 0, spannableString.length(), 33);
            this.b.setHint(new SpannedString(spannableString));
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.custom_target_tip);
            this.d = healthTextView;
            healthTextView.setText(this.e.getResources().getString(R.string._2130840003_res_0x7f0209c3, String.valueOf(50), String.valueOf(10000)));
            TW_(view, ropeSkipEnterNumberDialog);
        }

        private void TW_(View view, final RopeSkipEnterNumberDialog ropeSkipEnterNumberDialog) {
            this.b.addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.ecologydevice.ui.dialog.RopeSkipEnterNumberDialog.d.3
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
                        d.this.f2313a.setEnabled(true);
                    } else {
                        d.this.f2313a.setEnabled(false);
                    }
                    d.this.TT_(editable);
                }
            });
            ((HealthButton) view.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: dfi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    RopeSkipEnterNumberDialog.d.TV_(RopeSkipEnterNumberDialog.this, view2);
                }
            });
            this.f2313a.setOnClickListener(new View.OnClickListener() { // from class: dfj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    RopeSkipEnterNumberDialog.d.this.TX_(ropeSkipEnterNumberDialog, view2);
                }
            });
        }

        public static /* synthetic */ void TV_(RopeSkipEnterNumberDialog ropeSkipEnterNumberDialog, View view) {
            ropeSkipEnterNumberDialog.dismiss();
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void TX_(RopeSkipEnterNumberDialog ropeSkipEnterNumberDialog, View view) {
            d();
            int b = b();
            if (c(b)) {
                e(false);
                ViewClickInstrumentation.clickOnView(view);
            } else {
                this.c.onConfirmClick(b);
                ropeSkipEnterNumberDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        private void d() {
            String replaceAll = this.b.getText().toString().trim().replaceAll(",", "");
            this.j = replaceAll;
            if (TextUtils.isEmpty(replaceAll)) {
                a();
            }
        }

        private int b() {
            try {
                return Integer.parseInt(this.j);
            } catch (NumberFormatException e) {
                LogUtil.b("RopeSkipEnterNumberDialog", e.getMessage());
                return 0;
            }
        }

        private void a() {
            this.d.setTextColor(this.e.getResources().getColor(R.color._2131296671_res_0x7f09019f));
            this.f2313a.setEnabled(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void TT_(Editable editable) {
            if (this.f2313a.isEnabled() || TextUtils.isEmpty(editable)) {
                return;
            }
            try {
                if (c(Integer.parseInt(editable.toString()))) {
                    return;
                }
                e(true);
            } catch (NumberFormatException unused) {
                LogUtil.b("RopeSkipEnterNumberDialog", "afterTextChangedDialogState NumberFormatException");
            }
        }

        private void e(boolean z) {
            if (z) {
                this.d.setTextColor(this.e.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
                this.f2313a.setEnabled(true);
            } else {
                a();
            }
        }
    }
}
