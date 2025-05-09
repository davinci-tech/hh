package com.huawei.uikit.hwcardview.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes9.dex */
public class HwCard {

    /* renamed from: a, reason: collision with root package name */
    private ViewStub f10610a;
    private Context b;
    private ViewStub c;
    private ViewGroup d;
    private LayoutInflater e;
    private View f;
    private boolean g;
    private View h;
    private ViewStub i;
    private View j;
    private float n;
    private float o;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private LayoutInflater f10611a;
        private Context b;
        private Template f;
        private OnCardClickListener k;
        private CharSequence m;
        private int n;
        private boolean o;
        private CharSequence p;
        private CharSequence q;
        private OnCardClickListener r;
        private CharSequence s;
        private OnCardClickListener t;
        private OnCardClickListener u;
        private OnCardClickListener v;
        private int c = 4098;
        private int d = 8193;
        private int e = 0;
        private int h = 0;
        private int j = 0;
        private float g = 44.0f;
        private float i = 426.0f;
        private boolean l = true;

        class a implements View.OnClickListener {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ TextView f10612a;
            final /* synthetic */ TextView c;
            final /* synthetic */ TextView e;

            a(TextView textView, TextView textView2, TextView textView3) {
                this.c = textView;
                this.e = textView2;
                this.f10612a = textView3;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == this.c) {
                    if (Builder.this.t != null) {
                        Builder.this.t.onClick();
                    }
                } else if (view == this.e) {
                    if (Builder.this.v != null) {
                        Builder.this.v.onClick();
                    }
                } else if (view != this.f10612a) {
                    Log.e("HwCard", "invalid view");
                } else if (Builder.this.u != null) {
                    Builder.this.u.onClick();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Builder.this.k.onClick();
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class c implements View.OnClickListener {
            c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Builder.this.r.onClick();
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Builder.this.k.onClick();
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class e implements View.OnClickListener {
            final /* synthetic */ AppCompatImageView c;

            e(AppCompatImageView appCompatImageView) {
                this.c = appCompatImageView;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppCompatImageView appCompatImageView = this.c;
                if (appCompatImageView != null) {
                    appCompatImageView.performClick();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        public Builder(Context context) {
            this.b = context;
            this.f10611a = LayoutInflater.from(context);
            h();
        }

        private View eaR_() {
            return null;
        }

        private View eaW_() {
            return null;
        }

        private View eaY_() {
            int e2 = e(4099);
            if (e2 == 0) {
                return null;
            }
            View inflate = this.f10611a.inflate(e2, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.hwcardview_title);
            AppCompatImageView appCompatImageView = (AppCompatImageView) inflate.findViewById(R.id.hwcardview_icon);
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) inflate.findViewById(R.id.hwcardview_action_more);
            AppCompatImageView appCompatImageView3 = (AppCompatImageView) inflate.findViewById(R.id.hwcardview_action_info);
            textView.setText(this.m);
            int i = this.n;
            if (i != 0) {
                appCompatImageView.setImageResource(i);
            }
            if (this.k != null) {
                appCompatImageView2.setOnClickListener(new b());
            }
            if (this.r != null) {
                appCompatImageView3.setOnClickListener(new c());
            }
            if (this.o) {
                eaS_(inflate);
            }
            return inflate;
        }

        private boolean g() {
            return (TextUtils.isEmpty(this.p) && TextUtils.isEmpty(this.q) && TextUtils.isEmpty(this.s)) && (this.t == null && this.v == null && this.u == null);
        }

        private void h() {
            this.e = e(this.c);
            this.h = c(this.d);
        }

        public void c(Template template) {
            this.f = template;
        }

        private void a(HwCard hwCard) {
            if (this.e == 0 || hwCard == null) {
                Log.w("HwCard", "mHeaderLayout == 0 or hwcardview_activity is null");
                return;
            }
            View e2 = e(4097) == this.e ? e() : e(4098) == this.e ? eaX_() : e(4099) == this.e ? eaY_() : eaW_();
            if (e2 != null) {
                hwCard.eaN_(e2);
            }
        }

        private void b(HwCard hwCard) {
            if (this.h == 0 || hwCard == null) {
                Log.w("HwCard", "mFooterLayout == 0 or hwcardview_activity is null");
                return;
            }
            View eaV_ = c(8193) == this.h ? eaV_() : eaR_();
            if (eaV_ != null) {
                hwCard.eaM_(eaV_);
            }
        }

        private View eaX_() {
            int e2 = e(4098);
            if (e2 == 0) {
                return null;
            }
            View inflate = this.f10611a.inflate(e2, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.hwcardview_title);
            AppCompatImageView appCompatImageView = (AppCompatImageView) inflate.findViewById(R.id.hwcardview_icon);
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) inflate.findViewById(R.id.hwcardview_action_more);
            textView.setText(this.m);
            int i = this.n;
            if (i != 0) {
                appCompatImageView.setImageResource(i);
            }
            if (this.k != null) {
                appCompatImageView2.setOnClickListener(new d());
            }
            if (this.o) {
                eaS_(inflate);
            }
            return inflate;
        }

        private View e() {
            View inflate = this.f10611a.inflate(R.layout.hwcardview_header_no_button, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.hwcardview_title);
            AppCompatImageView appCompatImageView = (AppCompatImageView) inflate.findViewById(R.id.hwcardview_icon);
            textView.setText(this.m);
            int i = this.n;
            if (i != 0) {
                appCompatImageView.setImageResource(i);
            }
            if (this.o) {
                eaS_(inflate);
                eaP_(inflate);
            }
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public LayoutInflater eaO_() {
            return this.f10611a;
        }

        private int c(int i) {
            if (i != 8192) {
                if (i == 8193) {
                    return R.layout.hwcardview_footer_three_button;
                }
                Log.e("HwCard", "invalid type");
            }
            return 0;
        }

        private int e(int i) {
            if (i != 4096) {
                if (i == 4097) {
                    return R.layout.hwcardview_header_no_button;
                }
                if (i == 4098) {
                    return R.layout.hwcardview_header_with_one_button;
                }
                if (i == 4099) {
                    return R.layout.hwcardview_header_with_two_button;
                }
                Log.e("HwCard", "invalid type");
            }
            return 0;
        }

        private void c(HwCard hwCard) {
            int i = this.j;
            if (i != 0 && hwCard != null) {
                View inflate = this.f10611a.inflate(i, (ViewGroup) null);
                if (inflate != null) {
                    hwCard.eaL_(inflate);
                    return;
                }
                return;
            }
            Log.w("HwCard", "mContentLayout == 0 or hwcardview_activity is null");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HwCard d(boolean z) {
            HwCard hwCard = new HwCard(this.b, null);
            a(hwCard);
            b(hwCard);
            hwCard.d(this.g);
            hwCard.a(this.i);
            if (z) {
                Template template = this.f;
                if (template != null) {
                    hwCard.eaL_(template.makeContentView());
                }
            } else {
                c(hwCard);
            }
            d(hwCard);
            return hwCard;
        }

        private View eaV_() {
            int c2;
            if (g() || (c2 = c(8193)) == 0) {
                return null;
            }
            View inflate = this.f10611a.inflate(c2, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.hwcardview_btn_positive);
            TextView textView2 = (TextView) inflate.findViewById(R.id.hwcardview_btn_negative);
            TextView textView3 = (TextView) inflate.findViewById(R.id.hwcardview_btn_neutral);
            a aVar = new a(textView, textView2, textView3);
            c(textView, aVar);
            eaT_(textView3, aVar);
            eaQ_(textView2, aVar);
            return inflate;
        }

        private void eaS_(View view) {
            view.setOnClickListener(new e((AppCompatImageView) view.findViewById(R.id.hwcardview_action_expand)));
        }

        private void eaT_(TextView textView, View.OnClickListener onClickListener) {
            if (textView == null) {
                return;
            }
            CharSequence charSequence = this.s;
            if (charSequence != null || this.u != null) {
                textView.setText(charSequence);
                textView.setOnClickListener(onClickListener);
                textView.setVisibility(0);
            }
            if (this.q == null) {
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    marginLayoutParams.setMarginEnd(0);
                    textView.setLayoutParams(marginLayoutParams);
                }
            }
        }

        private void d(HwCard hwCard) {
            if (this.o) {
                hwCard.e(this.l);
            }
        }

        private void c(TextView textView, View.OnClickListener onClickListener) {
            if (textView == null) {
                return;
            }
            CharSequence charSequence = this.p;
            if (charSequence != null || this.t != null) {
                textView.setText(charSequence);
                textView.setOnClickListener(onClickListener);
                textView.setVisibility(0);
            }
            if (this.s == null && this.q == null) {
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    marginLayoutParams.setMarginEnd(0);
                    textView.setLayoutParams(marginLayoutParams);
                }
            }
        }

        private void eaP_(View view) {
            view.setPaddingRelative(view.getPaddingStart(), 0, this.b.getResources().getDimensionPixelSize(R.dimen._2131364039_res_0x7f0a08c7), 0);
        }

        private void eaQ_(TextView textView, View.OnClickListener onClickListener) {
            if (textView == null) {
                return;
            }
            CharSequence charSequence = this.q;
            if (charSequence == null && this.t == null) {
                return;
            }
            textView.setText(charSequence);
            textView.setOnClickListener(onClickListener);
            textView.setVisibility(0);
        }
    }

    public interface OnCardClickListener {
        void onClick();
    }

    public static abstract class Template {

        /* renamed from: a, reason: collision with root package name */
        private Builder f10613a;

        public Template(Builder builder) {
            a(builder);
        }

        private void a(Builder builder) {
            if (builder == null || this.f10613a == builder) {
                return;
            }
            this.f10613a = builder;
            builder.c(this);
        }

        public HwCard build() {
            Builder builder = this.f10613a;
            if (builder != null) {
                return builder.d(true);
            }
            return null;
        }

        public LayoutInflater getLayoutInflater() {
            Builder builder = this.f10613a;
            if (builder != null) {
                return builder.eaO_();
            }
            Log.w("HwCard", "mBuilder is null");
            return null;
        }

        public abstract View makeContentView();
    }

    class a implements Runnable {
        final /* synthetic */ int e;

        a(int i) {
            this.e = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewGroup.LayoutParams layoutParams = HwCard.this.f.getLayoutParams();
            int measuredHeight = HwCard.this.f.getMeasuredHeight();
            int i = this.e;
            if (measuredHeight > i) {
                layoutParams.height = i;
            }
            if (HwCard.this.f.getVisibility() == 4) {
                HwCard.this.f.setVisibility(8);
            }
            HwCard.this.f.setLayoutParams(layoutParams);
            HwCard.this.f.requestLayout();
        }
    }

    class e implements View.OnClickListener {
        final /* synthetic */ int b;
        final /* synthetic */ int d;
        final /* synthetic */ AppCompatImageView e;

        e(int i, int i2, AppCompatImageView appCompatImageView) {
            this.b = i;
            this.d = i2;
            this.e = appCompatImageView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HwCard.this.g = !r0.g;
            int i = HwCard.this.g ? this.b : this.d;
            if (i != 0) {
                this.e.setImageResource(i);
            }
            int i2 = HwCard.this.g ? 0 : 8;
            if (HwCard.this.f != null) {
                HwCard.this.f.setVisibility(i2);
            }
            if (HwCard.this.h != null) {
                HwCard.this.h.setVisibility(i2);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ HwCard(Context context, a aVar) {
        this(context);
    }

    protected void eaL_(View view) {
        if (this.f != null) {
            Log.w("HwCard", "mContentView already exists");
        } else if (view != null) {
            this.f = view;
            a(view, this.f10610a);
            d();
            a();
        }
    }

    protected void eaM_(View view) {
        if (this.h != null) {
            Log.w("HwCard", "mFooterView already exists");
        } else if (view != null) {
            a(view, this.i);
            this.h = view;
        }
    }

    protected void eaN_(View view) {
        if (this.j != null) {
            Log.w("HwCard", "mHeaderView already exists");
        } else if (view != null) {
            a(view, this.c);
            this.j = view;
        }
    }

    private HwCard(Context context) {
        this.n = 44.0f;
        this.o = 426.0f;
        this.b = context;
        this.e = LayoutInflater.from(context);
        c();
    }

    private void c() {
        View inflate = this.e.inflate(R.layout.hwcardview_layout_stubs, (ViewGroup) null);
        if (inflate instanceof ViewGroup) {
            this.d = (ViewGroup) inflate;
        }
        this.c = (ViewStub) inflate.findViewById(R.id.hwcardview_header_stub);
        this.f10610a = (ViewStub) inflate.findViewById(R.id.hwcardview_content_stub);
        this.i = (ViewStub) inflate.findViewById(R.id.hwcardview_footer_stub);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        this.o = f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(float f) {
        this.n = f;
    }

    private void a() {
        float f = this.n;
        if (f >= 0.0f) {
            this.f.setMinimumHeight((int) TypedValue.applyDimension(1, f, this.b.getResources().getDisplayMetrics()));
        }
        float f2 = this.o;
        if (f2 >= 0.0f) {
            this.f.post(new a((int) TypedValue.applyDimension(1, f2, this.b.getResources().getDisplayMetrics())));
        }
    }

    private void d() {
        View view = this.f;
        if (view != null && this.j == null && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f.getLayoutParams();
            marginLayoutParams.topMargin = this.b.getResources().getDimensionPixelSize(R.dimen._2131364032_res_0x7f0a08c0);
            this.f.setLayoutParams(marginLayoutParams);
        }
        View view2 = this.f;
        if (view2 != null && this.h == null && (view2.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.f.getLayoutParams();
            marginLayoutParams2.bottomMargin = this.b.getResources().getDimensionPixelSize(R.dimen._2131364032_res_0x7f0a08c0);
            this.f.setLayoutParams(marginLayoutParams2);
        }
    }

    private void a(View view, ViewStub viewStub) {
        ViewGroup viewGroup = this.d;
        if (viewGroup == null) {
            Log.w("HwCard", "mCardLayout is null");
            return;
        }
        int indexOfChild = viewGroup.indexOfChild(viewStub);
        this.d.removeViewInLayout(viewStub);
        ViewGroup.LayoutParams layoutParams = viewStub.getLayoutParams();
        if (layoutParams != null) {
            this.d.addView(view, indexOfChild, layoutParams);
        } else {
            this.d.addView(view, indexOfChild);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        AppCompatImageView appCompatImageView;
        this.g = z;
        View view = this.j;
        if (view == null || (appCompatImageView = (AppCompatImageView) view.findViewById(R.id.hwcardview_action_expand)) == null) {
            return;
        }
        appCompatImageView.setVisibility(0);
        View view2 = this.f;
        if (view2 != null) {
            view2.setVisibility(z ? 0 : this.o < 0.0f ? 8 : 4);
        }
        View view3 = this.h;
        if (view3 != null) {
            view3.setVisibility(z ? 0 : 8);
        }
        appCompatImageView.setImageResource(z ? R.drawable._2131428914_res_0x7f0b0632 : R.drawable._2131428915_res_0x7f0b0633);
        appCompatImageView.setOnClickListener(new e(R.drawable._2131428914_res_0x7f0b0632, R.drawable._2131428915_res_0x7f0b0633, appCompatImageView));
    }
}
