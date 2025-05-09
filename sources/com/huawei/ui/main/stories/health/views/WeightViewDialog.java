package com.huawei.ui.main.stories.health.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import defpackage.nsn;

/* loaded from: classes9.dex */
public class WeightViewDialog extends BaseDialog {
    private WeightViewDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private HealthColumnLinearLayout f10267a;
        private View b;
        private WeightViewDialog c;
        private Context d;
        private HealthButton g;
        private HealthButton h;
        private String i;
        private View.OnClickListener j;
        private HealthButton k;
        private String l;
        private View.OnClickListener m;
        private String n;
        private View.OnClickListener o;
        private LinearLayout t;
        private boolean f = false;
        private int s = 3;
        private boolean e = true;

        public Builder(Context context) {
            this.d = context;
        }

        public Builder a(int i) {
            this.s = i;
            return this;
        }

        public Builder b(boolean z) {
            this.f = z;
            return this;
        }

        public Builder dIA_(View view) {
            this.b = view;
            return this;
        }

        public Builder dIz_(String str, View.OnClickListener onClickListener) {
            LogUtil.a("WeightViewDialog", "setPositiveButton called ", str);
            this.l = str;
            this.m = onClickListener;
            return this;
        }

        public Builder dIx_(String str, View.OnClickListener onClickListener) {
            LogUtil.a("WeightViewDialog", "setNegativeButton called ", str);
            this.i = str;
            this.j = onClickListener;
            return this;
        }

        public Builder dIy_(String str, View.OnClickListener onClickListener) {
            LogUtil.a("WeightViewDialog", "setNegativeCancelButton called ", str);
            this.n = str;
            this.o = onClickListener;
            return this;
        }

        public WeightViewDialog c() {
            Drawable drawable;
            HealthScrollView healthScrollView;
            this.c = new WeightViewDialog(this.d, R.style.CustomDialog);
            Object systemService = this.d.getSystemService("layout_inflater");
            if (!(systemService instanceof LayoutInflater)) {
                LogUtil.h("WeightViewDialog", "systemService is not instanceof LayoutInflater");
                return this.c;
            }
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.weight_view_dialog, (ViewGroup) null);
            if (!nsn.aa(this.d) && (healthScrollView = (HealthScrollView) inflate.findViewById(R.id.dialog_scroll_view)) != null) {
                healthScrollView.setIsScrollEnable(true);
            }
            TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            if (typedValue.resourceId != 0) {
                TypedArray obtainStyledAttributes = this.d.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
                drawable = obtainStyledAttributes.getDrawable(2);
                obtainStyledAttributes.recycle();
            } else {
                drawable = ContextCompat.getDrawable(this.d, R.drawable._2131427507_res_0x7f0b00b3);
            }
            inflate.setBackground(drawable);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.dialog_rlyt_content);
            relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            if (this.b != null) {
                relativeLayout.removeAllViews();
                relativeLayout.addView(this.b);
            }
            dIw_(inflate);
            if (nsn.aa(this.d)) {
                int h = nsn.h(this.d);
                int f = nsn.f(this.d);
                if (h > 0 && f > 0) {
                    Window window = this.c.getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.width = nsn.h(this.d);
                    attributes.height = nsn.f(this.d);
                    window.setAttributes(attributes);
                }
            }
            this.c.setContentView(inflate);
            return this.c;
        }

        private void dIw_(View view) {
            this.f10267a = (HealthColumnLinearLayout) view.findViewById(R.id.button_layout_horizontal);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.button_layout_vertical);
            this.t = linearLayout;
            if (this.f) {
                this.g = (HealthButton) linearLayout.findViewById(R.id.vertical_dialog_btn_negative);
                this.k = (HealthButton) this.t.findViewById(R.id.vertical_dialog_btn_positive);
                this.h = (HealthButton) this.t.findViewById(R.id.vertical_dialog_btn_cancel);
                d();
                this.f10267a.setVisibility(8);
                this.t.setVisibility(0);
            } else {
                this.g = (HealthButton) this.f10267a.findViewById(R.id.horizontal_dialog_btn_negative);
                this.k = (HealthButton) this.f10267a.findViewById(R.id.horizontal_dialog_btn_positive);
                this.f10267a.setVisibility(0);
                this.t.setVisibility(8);
            }
            String str = this.l;
            if (str != null && this.i != null) {
                e();
            } else if (str != null) {
                a();
            } else {
                this.f10267a.setVisibility(8);
                this.t.setVisibility(8);
            }
        }

        private void d() {
            if (this.s == 2) {
                this.h.setVisibility(8);
            }
            if (this.s == 1) {
                this.h.setVisibility(8);
                this.g.setVisibility(8);
            }
        }

        private void a() {
            this.k.setText(this.l);
            if (this.m != null) {
                this.k.setOnClickListener(new b());
            } else {
                LogUtil.h("WeightViewDialog", "mPositiveButtonClickListener is null");
            }
        }

        private void e() {
            this.k.setText(this.l);
            if (this.m != null) {
                this.k.setOnClickListener(new b());
            } else {
                LogUtil.h("WeightViewDialog", "mPositiveButtonClickListener is null");
            }
            this.g.setText(this.i);
            nsn.c(this.g, this.k);
            if (this.j != null) {
                this.g.setOnClickListener(new c());
            } else {
                LogUtil.h("WeightViewDialog", "mNegativeButtonClickListener is null");
            }
            if (this.f && this.s == 3) {
                b();
            }
        }

        private void b() {
            this.h.setText(this.n);
            if (this.o != null) {
                this.h.setOnClickListener(new d());
            } else {
                LogUtil.h("WeightViewDialog", "mPositiveButtonClickListener is null");
            }
        }

        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!Builder.this.e) {
                    if (Builder.this.m != null) {
                        Builder.this.m.onClick(view);
                    } else {
                        LogUtil.h("WeightViewDialog", "mPositiveButtonClickListener is null in WeightViewDialog");
                    }
                    if (Builder.this.c != null && Builder.this.e) {
                        Builder.this.c.dismiss();
                    }
                } else {
                    if (Builder.this.c != null) {
                        Builder.this.c.dismiss();
                    }
                    if (Builder.this.m != null) {
                        Builder.this.m.onClick(view);
                    } else {
                        LogUtil.h("WeightViewDialog", "mPositiveButtonClickListener is null in WeightViewDialog");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class c implements View.OnClickListener {
            c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.c != null) {
                    Builder.this.c.dismiss();
                }
                if (Builder.this.j != null) {
                    Builder.this.j.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.c != null) {
                    Builder.this.c.dismiss();
                }
                if (Builder.this.o != null) {
                    Builder.this.o.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
