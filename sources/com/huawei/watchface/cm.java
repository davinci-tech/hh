package com.huawei.watchface;

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
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwbutton.widget.HwButton;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class cm extends ad {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10965a = "cm";

    private cm(Context context, int i) {
        super(context, i);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private cm f10966a;
        private RelativeLayout b;
        private LinearLayout c;
        private LinearLayout d;
        private HwButton e;
        private HwButton f;
        private HwButton g;
        private Context h;
        private String i;
        private String j;
        private SpannableString k;
        private String l;
        private String m;
        private View.OnClickListener n;
        private View.OnClickListener o;
        private boolean p = false;
        private int q = 0;
        private int r = 0;

        public a(Context context) {
            this.h = context;
        }

        private String b(int i) {
            try {
                return (String) this.h.getText(i);
            } catch (Resources.NotFoundException unused) {
                HwLog.e(cm.f10965a, "Resources NotFound");
                return "";
            }
        }

        public a a(int i) {
            HwLog.d(cm.f10965a, "setTitle: " + i);
            this.i = b(i);
            return this;
        }

        public a a(String str) {
            HwLog.d(cm.f10965a, "setTitle: " + str);
            this.i = str;
            return this;
        }

        public a b(String str) {
            this.j = str;
            return this;
        }

        public a a(int i, View.OnClickListener onClickListener) {
            this.l = b(i);
            this.n = onClickListener;
            return this;
        }

        public a a(String str, View.OnClickListener onClickListener) {
            if (!TextUtils.isEmpty(str)) {
                this.l = str;
            }
            this.n = onClickListener;
            return this;
        }

        public a b(int i, View.OnClickListener onClickListener) {
            this.m = b(i);
            this.o = onClickListener;
            return this;
        }

        public cm a() {
            Drawable drawable;
            int dimensionPixelSize;
            int dimensionPixelSize2;
            LayoutInflater layoutInflater = (LayoutInflater) this.h.getSystemService("layout_inflater");
            this.f10966a = new cm(this.h, R.style.watch_face_customDialog);
            View inflate = layoutInflater.inflate(R$layout.watchface_dialog_custom_text_alert, (ViewGroup) null);
            TypedValue typedValue = new TypedValue();
            this.h.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            if (typedValue.resourceId != 0) {
                TypedArray obtainStyledAttributes = this.h.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
                drawable = obtainStyledAttributes.getDrawable(2);
                TypedValue typedValue2 = new TypedValue();
                TypedValue typedValue3 = new TypedValue();
                obtainStyledAttributes.getValue(3, typedValue2);
                obtainStyledAttributes.getValue(1, typedValue3);
                dimensionPixelSize = CommonUtils.a(this.h, (int) TypedValue.complexToFloat(typedValue2.data));
                dimensionPixelSize2 = CommonUtils.a(this.h, (int) TypedValue.complexToFloat(typedValue3.data));
                obtainStyledAttributes.recycle();
            } else {
                drawable = ContextCompat.getDrawable(this.h, R$drawable.watchface_shape_dialog_bg);
                dimensionPixelSize = this.h.getResources().getDimensionPixelSize(R$dimen.sp_18);
                dimensionPixelSize2 = this.h.getResources().getDimensionPixelSize(R$dimen.sp_15);
            }
            inflate.setBackground(drawable);
            a(inflate, dimensionPixelSize, dimensionPixelSize2);
            a(inflate);
            this.f10966a.setContentView(inflate);
            return this.f10966a;
        }

        private void a(View view, int i, int i2) {
            int i3;
            int i4 = this.q;
            if (i4 <= 0 || (i3 = this.r) <= 0) {
                i4 = i;
                i3 = i2;
            }
            TextView textView = (TextView) view.findViewById(R$id.tv_dailog_title);
            textView.setTextSize(0, i4);
            textView.setText(this.i);
            TextView textView2 = (TextView) view.findViewById(R$id.tv_text_alert_title);
            textView2.setTextSize(0, i);
            textView2.setText(this.i);
            HwLog.d(cm.f10965a, "mContent = " + this.j + " contentTextSize = " + i2);
            TextView textView3 = (TextView) view.findViewById(R$id.tv_text_alert_message);
            textView3.setTextSize(0, (float) i3);
            SpannableString spannableString = this.k;
            if (spannableString != null) {
                textView3.setText(spannableString);
                textView3.setMovementMethod(LinkMovementMethod.getInstance());
                return;
            }
            textView3.setText(this.j);
            if (TextUtils.isEmpty(this.i)) {
                textView.setVisibility(8);
            }
            if (TextUtils.isEmpty(this.j)) {
                textView3.setVisibility(8);
                textView.setVisibility(8);
                textView2.setVisibility(0);
            }
        }

        private void a(View view) {
            this.b = (RelativeLayout) view.findViewById(R$id.rl_dialog_alert);
            this.c = (LinearLayout) view.findViewById(R$id.dialog_linearlayout2);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.ll_text_alert_message_layout);
            this.d = linearLayout;
            if (this.p) {
                linearLayout.setGravity(GravityCompat.START);
            }
            this.e = (HwButton) this.b.findViewById(R$id.dialog_btn_negative);
            this.f = (HwButton) this.b.findViewById(R$id.dialog_btn_positive);
            this.g = (HwButton) this.c.findViewById(R$id.dialog_one_btn);
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
            this.f.setText(this.l);
            this.f.setAllCaps(true);
            if (this.n != null) {
                this.f.setOnClickListener(new b());
            }
            this.e.setText(this.m);
            this.e.setAllCaps(true);
            CommonUtils.a(this.e, this.f);
            if (this.o != null) {
                this.e.setOnClickListener(new ViewOnClickListenerC0283a());
            }
        }

        private void c() {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.g.setText(this.l);
            CommonUtils.a(this.g);
            if (this.n != null) {
                this.g.setOnClickListener(new b());
            }
        }

        private void d() {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.g.setText(this.m);
            CommonUtils.a(this.g);
            if (this.o != null) {
                this.g.setOnClickListener(new ViewOnClickListenerC0283a());
            }
        }

        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.this.f10966a != null) {
                    a.this.f10966a.dismiss();
                }
                if (a.this.n == null) {
                    HwLog.d(cm.f10965a, "positiveButtonClickListener is null");
                } else {
                    a.this.n.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        /* renamed from: com.huawei.watchface.cm$a$a, reason: collision with other inner class name */
        class ViewOnClickListenerC0283a implements View.OnClickListener {
            ViewOnClickListenerC0283a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.this.f10966a != null) {
                    a.this.f10966a.dismiss();
                }
                if (a.this.o != null) {
                    a.this.o.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
