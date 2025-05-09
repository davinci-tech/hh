package com.huawei.watchface.mvp.ui.dialog;

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
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwbutton.widget.HwButton;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import com.huawei.watchface.R$dimen;
import com.huawei.watchface.R$drawable;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.ad;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class CustomViewDialog extends ad {
    private CustomViewDialog(Context context, int i) {
        super(context, i);
    }

    /* loaded from: classes9.dex */
    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private CustomViewDialog f11107a;
        private RelativeLayout b;
        private LinearLayout c;
        private HwButton d;
        private HwButton e;
        private HwButton f;
        private Context g;
        private String h;
        private View i;
        private String j;
        private String k;
        private View.OnClickListener l;
        private View.OnClickListener m;
        private int n = 24;
        private int o = 24;
        private boolean p = true;
        private boolean q = true;
        private HwProgressBar r;
        private boolean s;

        public Builder(Context context) {
            this.g = context;
        }

        public Builder setTitle(String str) {
            this.h = str;
            return this;
        }

        public Builder setView(View view) {
            this.i = view;
            return this;
        }

        public Builder setNegativeButton(int i, View.OnClickListener onClickListener) {
            HwLog.d("CustomViewDialog", "setNegativeButton called " + i);
            try {
                this.k = (String) this.g.getText(i);
            } catch (Resources.NotFoundException unused) {
                HwLog.e("CustomViewDialog", "Resources NotFound");
                this.k = "";
            }
            this.m = onClickListener;
            return this;
        }

        public CustomViewDialog create() {
            Drawable drawable;
            int dimensionPixelSize;
            LayoutInflater layoutInflater = (LayoutInflater) this.g.getSystemService("layout_inflater");
            this.f11107a = new CustomViewDialog(this.g, R.style.watch_face_customDialog);
            View inflate = layoutInflater.inflate(R$layout.watchface_dialog_custom_view, (ViewGroup) null);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R$id.custom_view_dialog_title_layout);
            a(linearLayout);
            View findViewById = inflate.findViewById(R$id.dailog_no_title);
            TypedValue typedValue = new TypedValue();
            this.g.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            if (typedValue.resourceId != 0) {
                TypedArray obtainStyledAttributes = this.g.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
                drawable = obtainStyledAttributes.getDrawable(2);
                obtainStyledAttributes.getValue(3, new TypedValue());
                dimensionPixelSize = CommonUtils.a(this.g, (int) TypedValue.complexToFloat(r5.data));
                obtainStyledAttributes.recycle();
            } else {
                drawable = ContextCompat.getDrawable(this.g, R$drawable.watchface_shape_dialog_bg);
                dimensionPixelSize = this.g.getResources().getDimensionPixelSize(R$dimen.sp_18);
            }
            inflate.setBackground(drawable);
            if (this.h == null) {
                linearLayout.setVisibility(8);
                if (this.p) {
                    findViewById.setVisibility(0);
                } else {
                    findViewById.setVisibility(8);
                }
            } else {
                TextView textView = (TextView) inflate.findViewById(R$id.custom_dialog_title);
                textView.setTextSize(0, dimensionPixelSize);
                textView.setText(this.h);
            }
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R$id.dialog_rlyt_content);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(CommonUtils.a(this.g, this.n), 0, CommonUtils.a(this.g, this.o), 0);
            relativeLayout.setLayoutParams(layoutParams);
            if (this.i != null) {
                relativeLayout.removeAllViews();
                relativeLayout.addView(this.i);
            }
            a(inflate);
            this.f11107a.setContentView(inflate);
            return this.f11107a;
        }

        private void a(LinearLayout linearLayout) {
            HwProgressBar hwProgressBar = (HwProgressBar) linearLayout.findViewById(R$id.pb_dialog_title_loading);
            this.r = hwProgressBar;
            if (this.s) {
                hwProgressBar.setVisibility(0);
            } else {
                hwProgressBar.setVisibility(8);
            }
        }

        private void a(View view) {
            this.b = (RelativeLayout) view.findViewById(R$id.rl_dialog_alert);
            this.c = (LinearLayout) view.findViewById(R$id.dialog_linearlayout2);
            this.d = (HwButton) this.b.findViewById(R$id.dialog_btn_negative);
            this.e = (HwButton) this.b.findViewById(R$id.dialog_btn_positive);
            this.f = (HwButton) this.c.findViewById(R$id.dialog_one_btn);
            String str = this.j;
            if (str != null && this.k != null) {
                a();
                return;
            }
            if (str == null && this.k != null) {
                c();
            } else if (str != null && this.k == null) {
                b();
            } else {
                this.b.setVisibility(8);
                this.c.setVisibility(8);
            }
        }

        private void a() {
            this.b.setVisibility(0);
            this.c.setVisibility(8);
            this.e.setText(this.j);
            if (this.l != null) {
                this.e.setOnClickListener(new b());
            } else {
                HwLog.d("CustomViewDialog", "mPositiveButtonClickListener is null");
            }
            this.d.setText(this.k);
            CommonUtils.a(this.d, this.e);
            if (this.m != null) {
                this.d.setOnClickListener(new a());
            } else {
                HwLog.d("CustomViewDialog", "mNegativeButtonClickListener is null");
            }
        }

        private void b() {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.f.setText(this.j);
            CommonUtils.a(this.f);
            if (this.l != null) {
                this.f.setOnClickListener(new b());
            } else {
                HwLog.d("CustomViewDialog", "mPositiveButtonClickListener is null");
            }
        }

        private void c() {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.f.setText(this.k);
            CommonUtils.a(this.f);
            if (this.m != null) {
                this.f.setOnClickListener(new a());
            } else {
                HwLog.d("CustomViewDialog", "mNegativeButtonClickListener is null");
            }
        }

        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!Builder.this.q) {
                    if (Builder.this.l != null) {
                        Builder.this.l.onClick(view);
                    } else {
                        HwLog.e("CustomViewDialog", "mPositiveButtonClickListener is null in CustomViewDialog");
                    }
                    if (Builder.this.f11107a != null && Builder.this.q) {
                        Builder.this.f11107a.dismiss();
                    }
                } else {
                    if (Builder.this.f11107a != null) {
                        Builder.this.f11107a.dismiss();
                    }
                    if (Builder.this.l != null) {
                        Builder.this.l.onClick(view);
                    } else {
                        HwLog.e("CustomViewDialog", "mPositiveButtonClickListener is null in CustomViewDialog");
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
                if (Builder.this.f11107a != null) {
                    Builder.this.f11107a.dismiss();
                }
                if (Builder.this.m != null) {
                    Builder.this.m.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
