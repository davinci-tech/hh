package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;

/* loaded from: classes.dex */
public class NoTitleCustomAlertDialog extends BaseDialog {
    private NoTitleCustomAlertDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Context f8817a;
        private int b;
        private String c;
        private NoTitleCustomAlertDialog d;
        private RelativeLayout f;
        private HealthButton g;
        private LinearLayout h;
        private View.OnClickListener j;
        private String k;
        private String l;
        private View.OnClickListener n;
        private HealthButton o;
        private HealthButton p;
        private SpannableString s;
        private boolean e = false;
        private int r = 0;
        private int m = 0;
        private boolean i = false;

        public Builder a(String str) {
            return this;
        }

        public Builder(Context context) {
            this.f8817a = context;
        }

        public Builder e(boolean z) {
            this.e = z;
            return this;
        }

        public Builder b(boolean z) {
            this.i = z;
            return this;
        }

        public Builder e(int i) {
            try {
                Context context = this.f8817a;
                if (context != null) {
                    this.c = (String) context.getText(i);
                }
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("NoTitleCustomAlertDialog", "Resources NotFound");
                this.c = "";
            }
            return this;
        }

        public Builder e(String str) {
            this.c = str;
            return this;
        }

        public Builder czx_(SpannableString spannableString) {
            this.s = spannableString;
            return this;
        }

        public Builder czC_(int i, View.OnClickListener onClickListener) {
            try {
                Context context = this.f8817a;
                if (context != null) {
                    this.k = (String) context.getText(i);
                }
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("NoTitleCustomAlertDialog", "Resources NotFound");
                this.k = "";
            }
            this.n = onClickListener;
            return this;
        }

        public Builder czE_(String str, View.OnClickListener onClickListener) {
            this.k = str;
            this.n = onClickListener;
            return this;
        }

        public Builder czB_(int i, int i2, View.OnClickListener onClickListener) {
            try {
                this.k = (String) this.f8817a.getText(i);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("NoTitleCustomAlertDialog", "Resources NotFound");
                this.k = "";
            }
            this.r = i2;
            this.n = onClickListener;
            return this;
        }

        public Builder czD_(String str, int i, View.OnClickListener onClickListener) {
            this.k = str;
            this.r = i;
            this.n = onClickListener;
            return this;
        }

        public Builder czz_(int i, View.OnClickListener onClickListener) {
            try {
                Context context = this.f8817a;
                if (context != null) {
                    this.l = (String) context.getText(i);
                }
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("NoTitleCustomAlertDialog", "Resources NotFound");
                this.l = "";
            }
            this.j = onClickListener;
            return this;
        }

        public Builder czA_(String str, View.OnClickListener onClickListener) {
            this.l = str;
            this.j = onClickListener;
            return this;
        }

        public Builder czy_(int i, int i2, View.OnClickListener onClickListener) {
            try {
                this.l = (String) this.f8817a.getText(i);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("NoTitleCustomAlertDialog", "Resources NotFound");
                this.l = "";
            }
            this.m = i2;
            this.j = onClickListener;
            return this;
        }

        public NoTitleCustomAlertDialog e() {
            Drawable drawable;
            int dimensionPixelSize;
            LayoutInflater layoutInflater = (LayoutInflater) this.f8817a.getSystemService("layout_inflater");
            this.d = new NoTitleCustomAlertDialog(this.f8817a, R.style.CustomDialog);
            View inflate = layoutInflater.inflate(R.layout.commonui_no_title_custom_dialog, (ViewGroup) null);
            this.d.setContentView(inflate);
            TypedValue typedValue = new TypedValue();
            this.f8817a.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            if (typedValue.resourceId != 0) {
                TypedArray obtainStyledAttributes = this.f8817a.getTheme().obtainStyledAttributes(typedValue.resourceId, R$styleable.customDialogDefinition);
                drawable = obtainStyledAttributes.getDrawable(R$styleable.customDialogDefinition_dialogBackground);
                TypedValue typedValue2 = new TypedValue();
                obtainStyledAttributes.getValue(R$styleable.customDialogDefinition_contentTextSize, typedValue2);
                this.b = obtainStyledAttributes.getColor(R$styleable.customDialogDefinition_buttonTextColor, R$color.common_colorAccent);
                dimensionPixelSize = TypedValue.complexToDimensionPixelSize(typedValue2.data, this.f8817a.getResources().getDisplayMetrics());
                obtainStyledAttributes.recycle();
            } else {
                drawable = ContextCompat.getDrawable(this.f8817a, R$drawable.activity_dialog_bg);
                this.b = ContextCompat.getColor(this.f8817a, R$color.common_colorAccent);
                dimensionPixelSize = this.f8817a.getResources().getDimensionPixelSize(R.dimen._2131362372_res_0x7f0a0244);
            }
            inflate.setBackground(drawable);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.dialog_no_title_tv_message);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.dialog_no_title_auto_tv_message);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.dialog_no_title_llyt_message);
            if (this.e) {
                linearLayout.setGravity(17);
            }
            if (dimensionPixelSize != -1) {
                healthTextView.setTextSize(0, dimensionPixelSize);
            }
            if (nsn.r()) {
                healthTextView.setTextSize(1, 32.0f);
            }
            if (this.i) {
                healthTextView.setVisibility(8);
                healthTextView2.setVisibility(0);
            } else {
                healthTextView.setVisibility(0);
                healthTextView2.setVisibility(8);
            }
            a(healthTextView, healthTextView2);
            czv_(inflate);
            this.d.setContentView(inflate);
            return this.d;
        }

        private void a(HealthTextView healthTextView, HealthTextView healthTextView2) {
            SpannableString spannableString = this.s;
            if (spannableString != null) {
                healthTextView.setText(spannableString);
                healthTextView2.setText(this.s);
                healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
                healthTextView2.setMovementMethod(LinkMovementMethod.getInstance());
                return;
            }
            healthTextView.setText(this.c);
            healthTextView2.setText(this.c);
        }

        private void czv_(View view) {
            this.f = (RelativeLayout) view.findViewById(R.id.dialog_linearlayout1);
            this.h = (LinearLayout) view.findViewById(R.id.dialog_linearlayout2);
            this.g = (HealthButton) this.f.findViewById(R.id.dialog_btn_negative);
            this.o = (HealthButton) this.f.findViewById(R.id.dialog_btn_positive);
            this.p = (HealthButton) this.h.findViewById(R.id.dialog_one_btn);
            LogUtil.c("NoTitleCustomAlertDialog", "negativeButtonText = ", this.l);
            String str = this.k;
            if (str != null && this.l != null) {
                c();
                return;
            }
            if (str == null && this.l != null) {
                b();
            } else if (str != null && this.l == null) {
                d();
            } else {
                this.f.setVisibility(8);
                this.h.setVisibility(8);
            }
        }

        private void c() {
            this.f.setVisibility(0);
            this.h.setVisibility(8);
            int length = this.k.length() - this.l.length();
            StringBuilder sb = new StringBuilder();
            LogUtil.c("NoTitleCustomAlertDialog", "number=", Integer.valueOf(length));
            if (length > 1) {
                this.l = b(length, sb, new StringBuilder(this.l));
            }
            if (length < -1) {
                this.k = b(length, sb, new StringBuilder(this.k));
            }
            this.o.setText(this.k);
            if (this.r != 0) {
                this.o.setTextColor(this.f8817a.getResources().getColor(this.r));
            } else {
                int i = this.b;
                if (i != 0) {
                    this.o.setTextColor(i);
                } else {
                    LogUtil.c("NoTitleCustomAlertDialog", "positiveButton setTextColor default.");
                }
            }
            if (this.n != null) {
                this.o.setOnClickListener(new a());
            } else {
                LogUtil.c("NoTitleCustomAlertDialog", "positiveButtonClickListener is null");
            }
            this.g.setText(this.l);
            nsn.c(this.g, this.o);
            if (this.m != 0) {
                this.g.setTextColor(this.f8817a.getResources().getColor(this.m));
            } else {
                int i2 = this.b;
                if (i2 != 0) {
                    this.g.setTextColor(i2);
                } else {
                    LogUtil.c("NoTitleCustomAlertDialog", "negativeButton setTextColor default.");
                }
            }
            if (this.j != null) {
                this.g.setOnClickListener(new d());
            }
        }

        private void d() {
            czw_(this.k, this.r, this.o, this.n, new a());
        }

        private void b() {
            czw_(this.l, this.m, this.g, this.j, new d());
        }

        /* loaded from: classes6.dex */
        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NoTitleCustomAlertDialog.czs_(Builder.this.d, Builder.this.n, view);
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        /* loaded from: classes6.dex */
        class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NoTitleCustomAlertDialog.czs_(Builder.this.d, Builder.this.j, view);
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        private String b(int i, StringBuilder sb, StringBuilder sb2) {
            return nsn.a(i, sb, sb2, false);
        }

        private void czw_(String str, int i, HealthButton healthButton, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
            this.f.setVisibility(8);
            this.h.setVisibility(0);
            this.p.setText(str);
            nsn.a(this.p);
            if (i != 0) {
                healthButton.setTextColor(this.f8817a.getResources().getColor(i));
            } else {
                int i2 = this.b;
                if (i2 != 0) {
                    healthButton.setTextColor(i2);
                } else {
                    LogUtil.c("NoTitleCustomAlertDialog", "setTextColor default.");
                }
            }
            if (onClickListener != null) {
                this.p.setOnClickListener(onClickListener2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void czs_(NoTitleCustomAlertDialog noTitleCustomAlertDialog, View.OnClickListener onClickListener, View view) {
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
        }
        if (onClickListener != null) {
            onClickListener.onClick(view);
        } else {
            LogUtil.c("NoTitleCustomAlertDialog", "buttonClickListener is null");
        }
    }
}
