package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class CustomProgressDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f8801a;
    private static final byte[] b = new byte[1];
    private static HealthProgressBar e;

    public CustomProgressDialog(Context context, int i) {
        super(context, i);
    }

    public CustomProgressDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    private void c(boolean z) {
        f8801a = z;
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f8802a;
        private CustomProgressDialog b;
        private Context c;
        private HealthProgressBar d;
        private View.OnClickListener e;
        private HealthTextView g;
        private String h;
        private HealthTextView i;

        public Builder(Context context) {
            this.c = context;
        }

        public Builder d(String str) {
            this.h = str;
            HealthTextView healthTextView = this.g;
            if (healthTextView != null && str != null) {
                healthTextView.setText(str);
            }
            return this;
        }

        public Builder b() {
            if (LanguageUtil.ao(this.c) || LanguageUtil.ar(this.c)) {
                HealthTextView healthTextView = this.g;
                if (healthTextView != null) {
                    healthTextView.setTextSize(0, nsn.c(this.c, 12.0f));
                }
                HealthTextView healthTextView2 = this.i;
                if (healthTextView2 != null) {
                    healthTextView2.setTextSize(0, nsn.c(this.c, 12.0f));
                }
            }
            return this;
        }

        public Builder d(int i) {
            this.d.setProgress(i);
            return this;
        }

        public Builder c(int i) {
            this.i.setText(UnitUtil.e(i, 2, 0));
            return this;
        }

        public Builder e(String str) {
            if (str != null) {
                this.i.setText(str);
            }
            return this;
        }

        public Builder cyH_(View.OnClickListener onClickListener) {
            this.e = onClickListener;
            return this;
        }

        public CustomProgressDialog e() {
            View inflate;
            LayoutInflater layoutInflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
            this.c.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, new TypedValue(), true);
            Drawable drawable = ContextCompat.getDrawable(this.c, R$drawable.activity_dialog_bg);
            if (CustomProgressDialog.f8801a) {
                this.b = new CustomProgressDialog(this.c, R.style.health_data_insert_dialog);
                inflate = layoutInflater.inflate(R.layout.commonui_custom_loading_dialog, (ViewGroup) null);
                HealthProgressBar unused = CustomProgressDialog.e = (HealthProgressBar) inflate.findViewById(R.id.health_data_inserting_progressbar);
            } else {
                this.b = new CustomProgressDialog(this.c, R.style.CustomDialog);
                inflate = layoutInflater.inflate(R.layout.commonui_custom_progress_dialog, (ViewGroup) null);
                this.i = (HealthTextView) inflate.findViewById(R.id.custom_progress_dialog_percent);
                this.d = (HealthProgressBar) inflate.findViewById(R.id.custom_progress_dialog_progressbar);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.custom_progress_dialog_cancel);
                this.f8802a = imageView;
                if (this.e != null) {
                    imageView.setOnClickListener(new d());
                    this.f8802a.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
                HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.custom_progress_dialog_desc);
                this.g = healthTextView;
                healthTextView.setText(this.h);
                LogUtil.a("CustomProgressDialog", "mProgressDesc :", this.h);
                this.d.setMax(100);
            }
            inflate.setBackground(drawable);
            this.b.setContentView(inflate);
            return this.b;
        }

        class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.b != null) {
                    LogUtil.a("CustomProgressDialog", "mCustomProgressDialog.dismiss");
                    Builder.this.b.dismiss();
                }
                if (Builder.this.e != null) {
                    Builder.this.e.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        super.show();
        HealthProgressBar healthProgressBar = e;
        if (healthProgressBar != null) {
            healthProgressBar.setVisibility(0);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        synchronized (b) {
            c(false);
            HealthProgressBar healthProgressBar = e;
            if (healthProgressBar != null && healthProgressBar.getVisibility() == 0) {
                e.setVisibility(4);
                e = null;
            }
        }
    }
}
