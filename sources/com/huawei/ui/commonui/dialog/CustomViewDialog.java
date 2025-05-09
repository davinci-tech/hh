package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.ResultUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

@Deprecated
/* loaded from: classes6.dex */
public class CustomViewDialog extends BaseDialog {
    private CustomViewDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private View f8806a;
        private CustomViewDialog b;
        private Context d;
        private boolean e;
        private boolean g;
        private HealthButtonBarLayout h;
        private RelativeLayout i;
        private HealthButton k;
        private String l;
        private View.OnClickListener m;
        private HealthButton n;
        private String p;
        private View.OnClickListener q;
        private View.OnClickListener r;
        private HealthProgressBar s;
        private HealthButton t;
        private HealthButton u;
        private String v;
        private int x;
        private HealthTextView y;
        private int o = 24;
        private int f = 24;
        private boolean j = true;
        private boolean c = true;

        public Builder(Context context) {
            this.d = context;
        }

        public void b(boolean z) {
            this.c = z;
        }

        public Builder d(int i) {
            try {
                this.v = (String) ResultUtils.a((String) this.d.getText(i));
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomViewDialog", "Resources NotFound");
                this.v = "";
            }
            return this;
        }

        public Builder d(boolean z) {
            this.e = z;
            return this;
        }

        public Builder a(String str) {
            this.v = str;
            return this;
        }

        public Builder c(boolean z) {
            this.j = z;
            return this;
        }

        public Builder czg_(View view) {
            this.f8806a = view;
            return this;
        }

        public Builder czh_(View view, int i, int i2) {
            this.f8806a = view;
            if (LanguageUtil.bc(this.d)) {
                this.o = i2;
                this.f = i;
            } else {
                this.o = i;
                this.f = i2;
            }
            return this;
        }

        public Builder cze_(int i, View.OnClickListener onClickListener) {
            try {
                this.p = (String) this.d.getText(i);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomViewDialog", "Resources NotFound");
                this.p = "";
            }
            this.q = onClickListener;
            return this;
        }

        public Builder czf_(String str, View.OnClickListener onClickListener) {
            LogUtil.c("CustomViewDialog", "setPositiveButton called ", str);
            this.p = str;
            this.q = onClickListener;
            return this;
        }

        public Builder czc_(int i, View.OnClickListener onClickListener) {
            LogUtil.c("CustomViewDialog", "setNegativeButton called ", Integer.valueOf(i));
            try {
                this.l = (String) this.d.getText(i);
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomViewDialog", "Resources NotFound");
                this.l = "";
            }
            this.m = onClickListener;
            return this;
        }

        public Builder czd_(String str, View.OnClickListener onClickListener) {
            LogUtil.c("CustomViewDialog", "setNegativeButton called ", str);
            this.l = str;
            this.m = onClickListener;
            return this;
        }

        public HealthButton b() {
            return this.k;
        }

        public HealthButton c() {
            return this.n;
        }

        public HealthTextView d() {
            return this.y;
        }

        public CustomViewDialog e() {
            Drawable drawable;
            int dimensionPixelSize;
            LayoutInflater layoutInflater = (LayoutInflater) this.d.getSystemService("layout_inflater");
            this.b = new CustomViewDialog(this.d, R.style.CustomDialog);
            View inflate = layoutInflater.inflate(R.layout.commonui_custom_view_dialog, (ViewGroup) null);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.custom_view_dialog_title_layout);
            cyZ_(linearLayout);
            View findViewById = inflate.findViewById(R.id.dailog_no_title);
            TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            if (typedValue.resourceId != 0) {
                TypedArray obtainStyledAttributes = this.d.getTheme().obtainStyledAttributes(typedValue.resourceId, R$styleable.customDialogDefinition);
                drawable = obtainStyledAttributes.getDrawable(R$styleable.customDialogDefinition_dialogBackground);
                obtainStyledAttributes.getValue(R$styleable.customDialogDefinition_titleTextSize, new TypedValue());
                dimensionPixelSize = nsn.c(this.d, (int) TypedValue.complexToFloat(r5.data));
                obtainStyledAttributes.recycle();
            } else {
                drawable = ContextCompat.getDrawable(this.d, R$drawable.activity_dialog_bg);
                dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen._2131362341_res_0x7f0a0225);
            }
            inflate.setBackground(drawable);
            czb_(linearLayout, findViewById, inflate, dimensionPixelSize);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.dialog_rlyt_content);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(nsn.c(this.d, this.o), 0, nsn.c(this.d, this.f), 0);
            if (nsn.r()) {
                layoutParams.height = nsn.j() / 2;
                if (nsn.s()) {
                    layoutParams.height = (int) (nsn.j() / 2.5d);
                }
            }
            relativeLayout.setLayoutParams(layoutParams);
            if (this.f8806a != null) {
                relativeLayout.removeAllViews();
                relativeLayout.addView(this.f8806a);
            }
            cza_(inflate);
            this.b.setContentView(inflate);
            return this.b;
        }

        public void a(HealthTextView healthTextView, int i) {
            healthTextView.setMaxLines(i);
        }

        private void cyZ_(LinearLayout linearLayout) {
            HealthProgressBar healthProgressBar = (HealthProgressBar) linearLayout.findViewById(R.id.dialog_listview_loading);
            this.s = healthProgressBar;
            if (this.e) {
                healthProgressBar.setVisibility(0);
            } else {
                healthProgressBar.setVisibility(8);
            }
            HealthButton healthButton = (HealthButton) linearLayout.findViewById(R.id.dialog_right_top_Button);
            this.t = healthButton;
            healthButton.setBackgroundResource(this.x);
            this.t.setClickable(true);
            if (this.g) {
                this.t.setVisibility(0);
            } else {
                this.t.setVisibility(8);
            }
            this.t.setOnClickListener(new d());
        }

        private void cza_(View view) {
            this.i = (RelativeLayout) view.findViewById(R.id.dialog_linearlayout1);
            this.h = (HealthButtonBarLayout) view.findViewById(R.id.dialog_linearlayout2);
            this.n = (HealthButton) this.i.findViewById(R.id.dialog_btn_negative);
            this.k = (HealthButton) this.i.findViewById(R.id.dialog_btn_positive);
            this.u = (HealthButton) this.h.findViewById(R.id.dialog_one_btn);
            String str = this.p;
            if (str != null && this.l != null) {
                a();
                return;
            }
            if (str == null && this.l != null) {
                g();
            } else if (str != null && this.l == null) {
                f();
            } else {
                this.i.setVisibility(8);
                this.h.setVisibility(8);
            }
        }

        public void a(boolean z) {
            HealthButton healthButton = this.k;
            if (healthButton != null) {
                healthButton.setEnabled(z);
                if (!z) {
                    this.k.setTextColor(this.d.getResources().getColor(R$color.common_colorAccent_disabled));
                } else {
                    this.k.setTextColor(this.d.getResources().getColor(R$color.common_colorAccent));
                }
            }
        }

        public void a(HealthButton healthButton, int i) {
            if (healthButton.getText() != null || i != 0) {
                healthButton.setTextColor(this.d.getResources().getColor(i));
            } else {
                LogUtil.h("CustomViewDialog", "The text is empty or has no color.");
            }
        }

        private void czb_(LinearLayout linearLayout, View view, View view2, int i) {
            if (this.v == null) {
                linearLayout.setVisibility(8);
                if (this.j) {
                    view.setVisibility(0);
                    return;
                } else {
                    view.setVisibility(8);
                    return;
                }
            }
            HealthTextView healthTextView = (HealthTextView) view2.findViewById(R.id.custom_dailog_title);
            this.y = healthTextView;
            healthTextView.setTextSize(0, i);
            this.y.setText(this.v);
        }

        private void a() {
            this.i.setVisibility(0);
            this.h.setVisibility(8);
            this.k.setText(this.p);
            if (this.q != null) {
                this.k.setOnClickListener(new b());
            } else {
                LogUtil.c("CustomViewDialog", "mPositiveButtonClickListener is null");
            }
            this.n.setText(this.l);
            nsn.c(this.n, this.k);
            if (this.m != null) {
                this.n.setOnClickListener(new a());
            } else {
                LogUtil.c("CustomViewDialog", "mNegativeButtonClickListener is null");
            }
        }

        private void f() {
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.u.setText(this.p);
            nsn.a(this.u);
            if (this.q != null) {
                this.u.setOnClickListener(new b());
            } else {
                LogUtil.c("CustomViewDialog", "mPositiveButtonClickListener is null");
            }
        }

        private void g() {
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.u.setText(this.l);
            nsn.a(this.u);
            if (this.m != null) {
                this.u.setOnClickListener(new a());
            } else {
                LogUtil.c("CustomViewDialog", "mNegativeButtonClickListener is null");
            }
        }

        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!Builder.this.c) {
                    if (Builder.this.q != null) {
                        Builder.this.q.onClick(view);
                    } else {
                        LogUtil.b("CustomViewDialog", "mPositiveButtonClickListener is null in CustomViewDialog");
                    }
                    if (Builder.this.b != null && Builder.this.c) {
                        Builder.this.b.dismiss();
                    }
                } else {
                    if (Builder.this.b != null) {
                        Builder.this.b.dismiss();
                    }
                    if (Builder.this.q != null) {
                        Builder.this.q.onClick(view);
                    } else {
                        LogUtil.b("CustomViewDialog", "mPositiveButtonClickListener is null in CustomViewDialog");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.b != null) {
                    Builder.this.b.dismiss();
                }
                if (Builder.this.m != null) {
                    Builder.this.m.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.r != null) {
                    Builder.this.r.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
