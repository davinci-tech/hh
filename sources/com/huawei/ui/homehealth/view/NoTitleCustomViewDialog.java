package com.huawei.ui.homehealth.view;

import android.content.Context;
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
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class NoTitleCustomViewDialog extends BaseDialog {
    private NoTitleCustomViewDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private NoTitleCustomViewDialog f9635a;
        private Context b;
        private View c;
        private HealthColumnLinearLayout d;
        private HealthButton f;
        private View.OnClickListener g;
        private String j;
        private HealthButton k;
        private HealthColumnLinearLayout l;
        private View.OnClickListener m;
        private String n;
        private boolean h = true;
        private boolean i = false;
        private boolean e = true;

        public Builder(Context context) {
            this.b = context;
        }

        public Builder b(boolean z) {
            this.e = z;
            return this;
        }

        public Builder d(boolean z) {
            this.i = z;
            return this;
        }

        public Builder djk_(View view) {
            this.c = view;
            return this;
        }

        public Builder djj_(String str, View.OnClickListener onClickListener) {
            LogUtil.a("NoTitleCustomViewDialog", "setPositiveButton called ", str);
            this.n = str;
            this.m = onClickListener;
            return this;
        }

        public Builder dji_(String str, View.OnClickListener onClickListener) {
            LogUtil.a("NoTitleCustomViewDialog", "setNegativeButton called ", str);
            this.j = str;
            this.g = onClickListener;
            return this;
        }

        public HealthButton d() {
            return this.k;
        }

        public NoTitleCustomViewDialog e() {
            Drawable drawable;
            this.f9635a = new NoTitleCustomViewDialog(this.b, R.style.CustomDialog);
            Object systemService = this.b.getSystemService("layout_inflater");
            if (!(systemService instanceof LayoutInflater)) {
                LogUtil.h("NoTitleCustomViewDialog", "systemService is not instanceof LayoutInflater");
                return this.f9635a;
            }
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.no_title_custom_view_dialog, (ViewGroup) null);
            TypedValue typedValue = new TypedValue();
            this.b.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            if (typedValue.resourceId != 0) {
                TypedArray obtainStyledAttributes = this.b.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
                drawable = obtainStyledAttributes.getDrawable(2);
                obtainStyledAttributes.recycle();
            } else {
                drawable = ContextCompat.getDrawable(this.b, R.drawable._2131427507_res_0x7f0b00b3);
            }
            inflate.setBackground(drawable);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.dialog_rlyt_content);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            if (this.i) {
                if (!this.e) {
                    layoutParams.height = (int) (nsn.j() * 0.33f);
                }
            } else {
                djh_(layoutParams);
            }
            relativeLayout.setLayoutParams(layoutParams);
            if (this.c != null) {
                relativeLayout.removeAllViews();
                relativeLayout.addView(this.c);
            }
            djg_(inflate);
            this.f9635a.setContentView(inflate);
            return this.f9635a;
        }

        private void djh_(LinearLayout.LayoutParams layoutParams) {
            if (!nsn.aa(this.b) || this.e) {
                return;
            }
            int j = nsn.j();
            int n = nsn.n();
            if (j > n) {
                layoutParams.height = (int) (j * 0.33f);
            } else {
                layoutParams.height = (int) (n * 0.33f);
            }
        }

        private void djg_(View view) {
            this.d = (HealthColumnLinearLayout) view.findViewById(R.id.button_layout_horizontal);
            HealthColumnLinearLayout healthColumnLinearLayout = (HealthColumnLinearLayout) view.findViewById(R.id.button_layout_vertical);
            this.l = healthColumnLinearLayout;
            if (this.i) {
                this.f = (HealthButton) healthColumnLinearLayout.findViewById(R.id.vertical_dialog_btn_negative);
                this.k = (HealthButton) this.l.findViewById(R.id.vertical_dialog_btn_positive);
                this.d.setVisibility(8);
                this.l.setVisibility(0);
            } else {
                this.f = (HealthButton) this.d.findViewById(R.id.horizontal_dialog_btn_negative);
                this.k = (HealthButton) this.d.findViewById(R.id.horizontal_dialog_btn_positive);
                this.d.setVisibility(0);
                this.l.setVisibility(8);
            }
            if (this.n != null && this.j != null) {
                b();
            } else {
                this.d.setVisibility(8);
                this.l.setVisibility(8);
            }
        }

        private void b() {
            this.k.setText(this.n);
            if (this.m != null) {
                this.k.setOnClickListener(new c());
            } else {
                LogUtil.h("NoTitleCustomViewDialog", "mPositiveButtonClickListener is null");
            }
            this.f.setText(this.j);
            nsn.c(this.f, this.k);
            if (this.g != null) {
                this.f.setOnClickListener(new e());
            } else {
                LogUtil.h("NoTitleCustomViewDialog", "mNegativeButtonClickListener is null");
            }
        }

        class c implements View.OnClickListener {
            c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!Builder.this.h) {
                    if (Builder.this.m != null) {
                        Builder.this.m.onClick(view);
                    } else {
                        LogUtil.h("NoTitleCustomViewDialog", "mPositiveButtonClickListener is null in NoTitleCustomViewDialog");
                    }
                    if (Builder.this.f9635a != null && Builder.this.h) {
                        Builder.this.f9635a.dismiss();
                    }
                } else {
                    if (Builder.this.f9635a != null) {
                        Builder.this.f9635a.dismiss();
                    }
                    if (Builder.this.m != null) {
                        Builder.this.m.onClick(view);
                    } else {
                        LogUtil.h("NoTitleCustomViewDialog", "mPositiveButtonClickListener is null in NoTitleCustomViewDialog");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class e implements View.OnClickListener {
            e() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.f9635a != null) {
                    Builder.this.f9635a.dismiss();
                }
                if (Builder.this.g != null) {
                    Builder.this.g.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
