package com.huawei.watchface;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
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
import com.huawei.uikit.phone.hwtextview.widget.HwTextView;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class cn extends ad {
    private cn(Context context, int i) {
        super(context, i);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private cn f10969a;
        private RelativeLayout b;
        private LinearLayout c;
        private HwButton d;
        private HwButton e;
        private HwButton f;
        private int g;
        private Context h;
        private String i;
        private SpannableString j;
        private String l;
        private String m;
        private View.OnClickListener q;
        private View.OnClickListener r;
        private boolean k = false;
        private int n = 0;
        private int o = 0;
        private boolean p = false;

        public a(Context context) {
            this.h = context;
        }

        public a a(int i) {
            try {
                this.i = (String) this.h.getText(i);
            } catch (Resources.NotFoundException unused) {
                HwLog.e("NoTitleCustomAlertDialog", "Resources NotFound");
                this.i = "";
            }
            return this;
        }

        public a a(String str) {
            this.i = str;
            return this;
        }

        public a a(int i, View.OnClickListener onClickListener) {
            try {
                this.l = (String) this.h.getText(i);
            } catch (Resources.NotFoundException unused) {
                HwLog.e("NoTitleCustomAlertDialog", "Resources NotFound");
                this.l = "";
            }
            this.q = onClickListener;
            return this;
        }

        public a a(String str, View.OnClickListener onClickListener) {
            this.l = str;
            this.q = onClickListener;
            return this;
        }

        public a b(int i, View.OnClickListener onClickListener) {
            try {
                this.m = (String) this.h.getText(i);
            } catch (Resources.NotFoundException unused) {
                HwLog.e("NoTitleCustomAlertDialog", "Resources NotFound");
                this.m = "";
            }
            this.r = onClickListener;
            return this;
        }

        public a b(String str, View.OnClickListener onClickListener) {
            this.m = str;
            this.r = onClickListener;
            return this;
        }

        public cn a() {
            Drawable drawable;
            int dimensionPixelSize;
            LayoutInflater layoutInflater = (LayoutInflater) this.h.getSystemService("layout_inflater");
            this.f10969a = new cn(this.h, R.style.watch_face_customDialog);
            View inflate = layoutInflater.inflate(R$layout.watchface_dialog_no_title_custom, (ViewGroup) null);
            this.f10969a.setContentView(inflate);
            TypedValue typedValue = new TypedValue();
            this.h.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            if (typedValue.resourceId != 0) {
                TypedArray obtainStyledAttributes = this.h.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
                drawable = obtainStyledAttributes.getDrawable(2);
                obtainStyledAttributes.getValue(1, new TypedValue());
                this.g = obtainStyledAttributes.getColor(0, R$color.button_color);
                dimensionPixelSize = CommonUtils.a(this.h, (int) TypedValue.complexToFloat(r5.data));
                obtainStyledAttributes.recycle();
            } else {
                drawable = ContextCompat.getDrawable(this.h, R$drawable.watchface_shape_dialog_bg);
                this.g = ContextCompat.getColor(this.h, R$color.button_color);
                dimensionPixelSize = this.h.getResources().getDimensionPixelSize(R$dimen.dp_15);
            }
            inflate.setBackground(drawable);
            HwTextView hwTextView = (HwTextView) inflate.findViewById(R$id.dialog_no_title_tv_message);
            TextView textView = (TextView) inflate.findViewById(R$id.dialog_no_title_auto_tv_message);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R$id.dialog_no_title_llyt_message);
            if (this.k) {
                linearLayout.setGravity(17);
            }
            if (dimensionPixelSize != -1) {
                hwTextView.setTextSize(0, dimensionPixelSize);
            }
            if (this.p) {
                hwTextView.setVisibility(8);
                textView.setVisibility(0);
            } else {
                hwTextView.setVisibility(0);
                textView.setVisibility(8);
            }
            a(hwTextView, textView);
            a(inflate);
            this.f10969a.setContentView(inflate);
            return this.f10969a;
        }

        private void a(TextView textView, TextView textView2) {
            SpannableString spannableString = this.j;
            if (spannableString != null) {
                textView.setText(spannableString);
                textView2.setText(this.j);
            } else {
                textView.setText(this.i);
                textView2.setText(this.i);
            }
        }

        private void a(View view) {
            this.b = (RelativeLayout) view.findViewById(R$id.rl_dialog_alert);
            this.c = (LinearLayout) view.findViewById(R$id.dialog_linearlayout2);
            this.d = (HwButton) this.b.findViewById(R$id.dialog_btn_negative);
            this.e = (HwButton) this.b.findViewById(R$id.dialog_btn_positive);
            this.f = (HwButton) this.c.findViewById(R$id.dialog_one_btn);
            HwLog.d("NoTitleCustomAlertDialog", "negativeButtonText = " + this.m);
            String str = this.l;
            if (str != null && this.m != null) {
                b();
                return;
            }
            if (str == null && this.m != null) {
                d();
            } else if (str != null && this.m == null) {
                c();
            } else {
                this.b.setVisibility(8);
                this.c.setVisibility(8);
            }
        }

        private void b() {
            this.b.setVisibility(0);
            this.c.setVisibility(8);
            this.e.setText(this.l);
            if (this.n != 0) {
                this.e.setTextColor(this.h.getResources().getColor(this.n));
            } else {
                int i = this.g;
                if (i != 0) {
                    this.e.setTextColor(i);
                } else {
                    HwLog.d("NoTitleCustomAlertDialog", "positiveButton setTextColor default.");
                }
            }
            if (this.q != null) {
                this.e.setOnClickListener(new b());
            } else {
                HwLog.d("NoTitleCustomAlertDialog", "positiveButtonClickListener is null");
            }
            this.d.setText(this.m);
            CommonUtils.a(this.d, this.e);
            if (this.o != 0) {
                this.d.setTextColor(this.h.getResources().getColor(this.o));
            } else {
                int i2 = this.g;
                if (i2 != 0) {
                    this.d.setTextColor(i2);
                } else {
                    HwLog.d("NoTitleCustomAlertDialog", "negativeButton setTextColor default.");
                }
            }
            if (this.r != null) {
                this.d.setOnClickListener(new ViewOnClickListenerC0284a());
            }
        }

        private void c() {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.f.setText(this.l);
            CommonUtils.a(this.f);
            if (this.n != 0) {
                this.e.setTextColor(this.h.getResources().getColor(this.n));
            } else {
                int i = this.g;
                if (i != 0) {
                    this.e.setTextColor(i);
                } else {
                    HwLog.d("NoTitleCustomAlertDialog", "positiveButtonClickListener is null");
                }
            }
            if (this.q != null) {
                this.f.setOnClickListener(new b());
            }
        }

        private void d() {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.f.setText(this.m);
            CommonUtils.a(this.f);
            if (this.o != 0) {
                this.d.setTextColor(this.h.getResources().getColor(this.o));
            } else {
                int i = this.g;
                if (i != 0) {
                    this.d.setTextColor(i);
                } else {
                    HwLog.d("NoTitleCustomAlertDialog", "negativeButton setTextColor default.");
                }
            }
            if (this.r != null) {
                this.f.setOnClickListener(new ViewOnClickListenerC0284a());
            }
        }

        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.this.f10969a != null) {
                    a.this.f10969a.dismiss();
                }
                if (a.this.q != null) {
                    a.this.q.onClick(view);
                } else {
                    HwLog.d("NoTitleCustomAlertDialog", "positiveButtonClickListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        /* renamed from: com.huawei.watchface.cn$a$a, reason: collision with other inner class name */
        class ViewOnClickListenerC0284a implements View.OnClickListener {
            ViewOnClickListenerC0284a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.this.f10969a != null) {
                    a.this.f10969a.dismiss();
                }
                if (a.this.r != null) {
                    a.this.r.onClick(view);
                } else {
                    HwLog.d("NoTitleCustomAlertDialog", "negativeButtonClickListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
