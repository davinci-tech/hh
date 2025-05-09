package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes.dex */
public class CustomTextAlertDialog extends BaseDialog {
    private CustomTextAlertDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        private Context c;
        private CustomTextAlertDialog d;
        private String e;
        private LinearLayout f;
        private RelativeLayout g;
        private HealthButton h;
        private LinearLayout i;
        private HealthButton k;
        private View.OnClickListener l;
        private Integer m;
        private String n;
        private View.OnClickListener o;
        private String p;
        private String q;
        private SpannableString r;
        private Integer t;
        private HealthButton x;

        /* renamed from: a, reason: collision with root package name */
        private boolean f8804a = false;
        private boolean j = false;
        private int s = 0;
        private int b = 0;

        public Builder(Context context) {
            this.c = context;
        }

        private String e(int i) {
            if (this.c == null) {
                this.c = BaseApplication.e();
            }
            try {
                return (String) this.c.getText(i);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomTextAlertDialog", "Resources NotFound");
                return "";
            }
        }

        public Builder e(Integer num) {
            this.t = num;
            return this;
        }

        public Builder a(Integer num) {
            this.m = num;
            return this;
        }

        public Builder b(int i) {
            LogUtil.c("CustomTextAlertDialog", "setTitle: ", Integer.valueOf(i));
            this.p = e(i);
            return this;
        }

        public Builder b(String str) {
            LogUtil.c("CustomTextAlertDialog", "setTitle: ", str);
            this.p = str;
            return this;
        }

        public Builder c(Boolean bool) {
            this.f8804a = bool.booleanValue();
            return this;
        }

        public Builder b(Boolean bool) {
            this.j = bool.booleanValue();
            return this;
        }

        public Builder d(int i) {
            this.e = e(i);
            return this;
        }

        public Builder e(String str) {
            this.e = str;
            return this;
        }

        public Builder cyQ_(SpannableString spannableString) {
            this.r = spannableString;
            return this;
        }

        public Builder cyU_(int i, View.OnClickListener onClickListener) {
            this.q = e(i);
            this.l = onClickListener;
            return this;
        }

        public Builder cyV_(String str, View.OnClickListener onClickListener) {
            if (!TextUtils.isEmpty(str)) {
                this.q = str;
            }
            this.l = onClickListener;
            return this;
        }

        public Builder cyT_(int i, int i2, View.OnClickListener onClickListener) {
            cyU_(i, onClickListener);
            HealthButton healthButton = this.k;
            if (healthButton == null) {
                return this;
            }
            try {
                healthButton.setTextColor(this.c.getResources().getColor(i2));
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomTextAlertDialog", "color not found");
            }
            return this;
        }

        public Builder cyR_(int i, View.OnClickListener onClickListener) {
            this.n = e(i);
            this.o = onClickListener;
            return this;
        }

        public Builder cyS_(String str, View.OnClickListener onClickListener) {
            if (!TextUtils.isEmpty(str)) {
                this.n = str;
            }
            this.o = onClickListener;
            return this;
        }

        public CustomTextAlertDialog a() {
            Drawable drawable;
            int dimensionPixelSize;
            int dimensionPixelSize2;
            LayoutInflater layoutInflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
            this.d = new CustomTextAlertDialog(this.c, R.style.CustomDialog);
            View inflate = layoutInflater.inflate(R.layout.commonui_custom_text_alert_dialog, (ViewGroup) null);
            TypedValue typedValue = new TypedValue();
            this.c.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            if (typedValue.resourceId != 0) {
                TypedArray obtainStyledAttributes = this.c.getTheme().obtainStyledAttributes(typedValue.resourceId, R$styleable.customDialogDefinition);
                drawable = obtainStyledAttributes.getDrawable(R$styleable.customDialogDefinition_dialogBackground);
                TypedValue typedValue2 = new TypedValue();
                TypedValue typedValue3 = new TypedValue();
                obtainStyledAttributes.getValue(R$styleable.customDialogDefinition_titleTextSize, typedValue2);
                obtainStyledAttributes.getValue(R$styleable.customDialogDefinition_contentTextSize, typedValue3);
                dimensionPixelSize = nsn.c(this.c, (int) TypedValue.complexToFloat(typedValue2.data));
                dimensionPixelSize2 = nsn.c(this.c, (int) TypedValue.complexToFloat(typedValue3.data));
                obtainStyledAttributes.recycle();
            } else {
                drawable = ContextCompat.getDrawable(this.c, R$drawable.activity_dialog_bg);
                dimensionPixelSize = this.c.getResources().getDimensionPixelSize(R.dimen._2131362341_res_0x7f0a0225);
                dimensionPixelSize2 = this.c.getResources().getDimensionPixelSize(R.dimen._2131365061_res_0x7f0a0cc5);
            }
            inflate.setBackground(drawable);
            cyP_(inflate, dimensionPixelSize, dimensionPixelSize2);
            cyO_(inflate);
            this.d.setContentView(inflate);
            return this.d;
        }

        private void cyP_(View view, int i, int i2) {
            int i3;
            int i4 = this.s;
            if (i4 <= 0 || (i3 = this.b) <= 0) {
                i4 = i;
                i3 = i2;
            }
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.custom_dailog_title);
            if (this.j) {
                healthTextView.setTextAlignment(4);
            }
            healthTextView.setTextSize(0, i4);
            healthTextView.setText(this.p);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.custom_text_alert_dialog_title_simple);
            healthTextView2.setTextSize(0, i);
            healthTextView2.setText(this.p);
            LogUtil.c("CustomTextAlertDialog", "mContent = ", this.e, " contentTextSize = ", Integer.valueOf(i2));
            HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.dialog_text_alert_message);
            if (nsn.r()) {
                healthTextView3.setTextSize(1, 28.0f);
            } else {
                healthTextView3.setTextSize(0, i3);
            }
            SpannableString spannableString = this.r;
            if (spannableString != null) {
                healthTextView3.setText(spannableString);
                healthTextView3.setMovementMethod(LinkMovementMethod.getInstance());
            } else {
                healthTextView3.setText(this.e);
                if (TextUtils.isEmpty(this.e)) {
                    healthTextView3.setVisibility(8);
                    healthTextView.setVisibility(8);
                    healthTextView2.setVisibility(0);
                }
            }
            if (LanguageUtil.bi(this.c)) {
                healthTextView3.setPadding(healthTextView3.getPaddingLeft(), healthTextView3.getPaddingTop(), healthTextView3.getPaddingRight(), (int) this.c.getResources().getDimension(R.dimen._2131362944_res_0x7f0a0480));
            }
        }

        private void cyO_(View view) {
            this.g = (RelativeLayout) view.findViewById(R.id.dialog_linearlayout1);
            this.f = (LinearLayout) view.findViewById(R.id.dialog_linearlayout2);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dialog_text_alert_message_layout);
            this.i = linearLayout;
            if (this.f8804a) {
                linearLayout.setGravity(GravityCompat.START);
            }
            this.h = (HealthButton) this.g.findViewById(R.id.dialog_btn_negative);
            this.k = (HealthButton) this.g.findViewById(R.id.dialog_btn_positive);
            this.x = (HealthButton) this.f.findViewById(R.id.dialog_one_btn);
            try {
                if (this.t != null) {
                    this.k.setTextColor(this.c.getResources().getColor(this.t.intValue()));
                }
                if (this.m != null) {
                    this.h.setTextColor(this.c.getResources().getColor(this.m.intValue()));
                }
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomTextAlertDialog", "color not found");
            }
            String str = this.q;
            if (str != null && this.n != null) {
                d();
                return;
            }
            if (str == null && this.n != null) {
                b();
            } else if (str != null && this.n == null) {
                f();
            } else {
                this.g.setVisibility(8);
                this.f.setVisibility(8);
            }
        }

        private void d() {
            this.g.setVisibility(0);
            this.f.setVisibility(8);
            int length = this.q.length() - this.n.length();
            StringBuilder sb = new StringBuilder();
            LogUtil.c("CustomTextAlertDialog", "number=", Integer.valueOf(length));
            if (length > 1) {
                this.n = a(length, sb, new StringBuilder(this.n));
            }
            if (length < -1) {
                this.q = a(length, sb, new StringBuilder(this.q));
            }
            this.k.setText(this.q);
            this.k.setAllCaps(true);
            if (this.l != null) {
                this.k.setOnClickListener(new b());
            }
            this.h.setText(this.n);
            this.h.setAllCaps(true);
            nsn.c(this.h, this.k);
            if (this.o != null) {
                this.h.setOnClickListener(new a());
            }
        }

        private void f() {
            this.g.setVisibility(8);
            this.f.setVisibility(0);
            this.x.setText(this.q);
            nsn.a(this.x);
            if (this.l != null) {
                this.x.setOnClickListener(new b());
            }
        }

        private void b() {
            this.g.setVisibility(8);
            this.f.setVisibility(0);
            this.x.setText(this.n);
            nsn.a(this.x);
            if (this.o != null) {
                this.x.setOnClickListener(new a());
            }
        }

        /* loaded from: classes6.dex */
        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomTextAlertDialog.cyL_(Builder.this.d, Builder.this.l, view);
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        /* loaded from: classes6.dex */
        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomTextAlertDialog.cyL_(Builder.this.d, Builder.this.o, view);
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        public void a(int i, int i2) {
            this.s = i;
            this.b = i2;
        }

        public HealthButton e() {
            return this.k;
        }

        public HealthButton c() {
            return this.h;
        }

        private String a(int i, StringBuilder sb, StringBuilder sb2) {
            int abs = Math.abs(i);
            if (abs % 2 == 0) {
                return e(abs, sb, sb2);
            }
            return e(abs - 1, sb, sb2);
        }

        private String e(int i, StringBuilder sb, StringBuilder sb2) {
            for (int i2 = 0; i2 < i / 2; i2++) {
                sb.append("  ");
            }
            sb2.insert(0, "  ");
            sb2.insert(0, (CharSequence) sb);
            sb2.append((CharSequence) sb);
            sb2.append("  ");
            return sb2.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cyL_(CustomTextAlertDialog customTextAlertDialog, View.OnClickListener onClickListener, View view) {
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
        }
        if (onClickListener != null) {
            onClickListener.onClick(view);
        } else {
            LogUtil.c("CustomTextAlertDialog", "buttonClickListener is null");
        }
    }
}
