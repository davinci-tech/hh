package com.huawei.ui.commonui.toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrs;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class HealthToolBar extends LinearLayout implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthColumnSystem f8964a;
    private boolean b;
    private boolean c;
    private LinearLayout d;
    private Context e;
    private OnSingleTapListener f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private HealthTextView k;
    private LinearLayout l;
    private LinearLayout n;
    private int o;

    /* loaded from: classes.dex */
    public interface OnSingleTapListener {
        void onSingleTap(int i);
    }

    public HealthToolBar(Context context) {
        super(context);
        this.b = true;
        this.i = 10;
        this.g = 0;
        this.h = 0;
        this.c = true;
        this.j = true;
        this.e = context;
        c();
    }

    public HealthToolBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = true;
        this.i = 10;
        this.g = 0;
        this.h = 0;
        this.c = true;
        this.j = true;
        if (attributeSet == null) {
            return;
        }
        this.e = context;
        c();
    }

    private void c() {
        ((LayoutInflater) this.e.getSystemService("layout_inflater")).inflate(R.layout.toobarlayout, this);
        this.l = (LinearLayout) findViewById(R.id.toolbar_layout);
        if (nsn.af(this.e)) {
            LogUtil.a("CommonUI_HealthToolBar", "old samsung night mode");
            this.l.setBackgroundColor(Color.parseColor("#FAFAFA"));
        }
        e();
    }

    private void e() {
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.e);
        this.f8964a = healthColumnSystem;
        healthColumnSystem.e(this.e);
        j(0);
    }

    private int b(HealthColumnSystem healthColumnSystem, int i) {
        healthColumnSystem.e(6);
        int h = healthColumnSystem.h();
        healthColumnSystem.e(7);
        int h2 = healthColumnSystem.h();
        if (i != 1) {
            return i > 3 ? h2 : h;
        }
        healthColumnSystem.e(1);
        return healthColumnSystem.h();
    }

    private LinearLayout cGZ_(int i) {
        LinearLayout linearLayout = this.d;
        if (linearLayout == null) {
            LogUtil.h("CommonUI_HealthToolBar", "mDefaultView is null");
        } else {
            if (i == 0) {
                return (LinearLayout) linearLayout.findViewById(R.id.toolbar_item_left_border);
            }
            if (i == 1) {
                return (LinearLayout) linearLayout.findViewById(R.id.toolbar_item_left);
            }
            if (i == 2) {
                return (LinearLayout) linearLayout.findViewById(R.id.toolbar_item_mid);
            }
            if (i == 3) {
                return (LinearLayout) linearLayout.findViewById(R.id.toolbar_item_right);
            }
            if (i == 4) {
                return (LinearLayout) linearLayout.findViewById(R.id.toolbar_item_right_border);
            }
        }
        return null;
    }

    private ImageView cGY_(int i) {
        ImageView imageView;
        LinearLayout cGZ_ = cGZ_(i);
        if (cGZ_ == null) {
            LogUtil.h("CommonUI_HealthToolBar", "initImageView() item is null");
        } else {
            if (i == 0) {
                imageView = (ImageView) cGZ_.findViewById(R.id.toolbar_imageView);
            } else if (i == 1) {
                imageView = (ImageView) cGZ_.findViewById(R.id.toolbar_imageView);
            } else if (i == 2) {
                imageView = (ImageView) cGZ_.findViewById(R.id.toolbar_imageView);
            } else if (i == 3) {
                imageView = (ImageView) cGZ_.findViewById(R.id.toolbar_imageView);
            } else if (i == 4) {
                imageView = (ImageView) cGZ_.findViewById(R.id.toolbar_imageView);
            }
            if (imageView != null && this.j) {
                imageView.setImageTintList(ColorStateList.valueOf(this.e.getResources().getColor(R$color.colorPrimary)));
            }
            return imageView;
        }
        imageView = null;
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(this.e.getResources().getColor(R$color.colorPrimary)));
        }
        return imageView;
    }

    private HealthTextView f(int i) {
        LinearLayout cGZ_ = cGZ_(i);
        if (cGZ_ == null) {
            LogUtil.h("CommonUI_HealthToolBar", "initTextView() item is null");
        } else {
            if (i == 0) {
                return (HealthTextView) cGZ_.findViewById(R.id.toolbar_textView);
            }
            if (i == 1) {
                return (HealthTextView) cGZ_.findViewById(R.id.toolbar_textView);
            }
            if (i == 2) {
                return (HealthTextView) cGZ_.findViewById(R.id.toolbar_textView);
            }
            if (i == 3) {
                return (HealthTextView) cGZ_.findViewById(R.id.toolbar_textView);
            }
            if (i == 4) {
                return (HealthTextView) cGZ_.findViewById(R.id.toolbar_textView);
            }
        }
        return null;
    }

    private void d(int i, int i2, float f) {
        setIconVisible(i, 0);
        ImageView cGY_ = cGY_(i);
        if (cGY_ == null) {
            LogUtil.h("CommonUI_HealthToolBar", "imageView is null");
        } else {
            cGY_.setImageDrawable(this.e.getResources().getDrawable(i2));
            cGY_.setAlpha(f);
        }
    }

    private void cHa_(int i, Drawable drawable, float f) {
        setIconVisible(i, 0);
        ImageView cGY_ = cGY_(i);
        if (cGY_ == null) {
            LogUtil.h("CommonUI_HealthToolBar", "imageView is null");
        } else {
            cGY_.setImageDrawable(drawable);
            cGY_.setAlpha(f);
        }
    }

    private void d(int i, CharSequence charSequence, boolean z, boolean z2) {
        HealthTextView f = f(i);
        if (f == null) {
            LogUtil.h("CommonUI_HealthToolBar", "textView is null");
            return;
        }
        f.setText(charSequence);
        if (z) {
            if (z2) {
                f.setTextColor(this.e.getResources().getColor(R$color.common_colorAccent));
            } else {
                f.setTextColor(this.e.getResources().getColor(R$color.textColorSecondary));
            }
        }
    }

    private void cHb_(View view, int i, int i2) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMarginStart(i);
            marginLayoutParams.setMarginEnd(i2);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    private void a() {
        if (this.k.getLineCount() > 1) {
            int i = this.i;
            if (i > 9) {
                int i2 = i - 1;
                this.i = i2;
                this.k.setTextSize(2, i2);
                return;
            } else {
                if (this.k.getLineCount() > 1 && this.k.getMaxLines() < 2) {
                    this.k.setMaxLines(2);
                }
                this.k.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                return;
            }
        }
        this.k.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        LinearLayout linearLayout = this.d;
        if (linearLayout == null) {
            return;
        }
        this.o = 0;
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.toolbar_linear_layout);
        this.n = linearLayout2;
        int childCount = linearLayout2.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            if (this.n.getChildAt(i5).getVisibility() != 8) {
                this.o++;
            }
        }
        int i6 = this.o;
        if (i6 <= 0) {
            return;
        }
        if (i6 < 4) {
            if (this.n.getWeightSum() != 3.0f) {
                cHb_(this.n, nsn.c(this.e, 24.0f), nsn.c(this.e, 24.0f));
                this.n.setWeightSum(3.0f);
            }
        } else if (i6 == 4) {
            if (this.n.getWeightSum() != 4.0f) {
                cHb_(this.n, nsn.c(this.e, 24.0f), nsn.c(this.e, 24.0f));
                this.n.setWeightSum(4.0f);
            }
        } else if (this.n.getWeightSum() != this.o) {
            cHb_(this.n, 0, 0);
            this.n.setWeightSum(this.o);
        }
        if (this.c) {
            int i7 = this.h;
            int i8 = this.o;
            if (i7 != i8) {
                j(i8);
            }
            LinearLayout linearLayout3 = this.n;
            int i9 = this.g;
            cHb_(linearLayout3, i9, i9);
            this.n.setWeightSum(this.o);
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = this.n.getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                HealthTextView healthTextView = (HealthTextView) childAt.findViewById(R.id.toolbar_textView);
                this.k = healthTextView;
                healthTextView.getViewTreeObserver().addOnGlobalLayoutListener(this);
            }
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f8964a.e(this.e);
        j(this.o);
    }

    private void j(int i) {
        if (this.c) {
            this.h = i;
            this.g = (nrs.c(getContext()) - b(this.f8964a, i)) / 2;
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (this.k.getLineCount() > 1) {
            if (this.b) {
                cHb_(this.n, 0, 0);
                this.n.setWeightSum(this.o);
                this.b = false;
            }
            a();
            return;
        }
        this.k.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.d.findViewById(R.id.toolbar_item_left_border)) {
            this.f.onSingleTap(0);
        } else if (view == this.d.findViewById(R.id.toolbar_item_left)) {
            this.f.onSingleTap(1);
        } else if (view == this.d.findViewById(R.id.toolbar_item_mid)) {
            this.f.onSingleTap(2);
        } else if (view == this.d.findViewById(R.id.toolbar_item_right)) {
            this.f.onSingleTap(3);
        } else if (view == this.d.findViewById(R.id.toolbar_item_right_border)) {
            this.f.onSingleTap(4);
        } else {
            LogUtil.h("Invalid click", new Object[0]);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void cHd_(View view) {
        LinearLayout linearLayout = this.l;
        if (linearLayout == null || view == null) {
            LogUtil.h("CommonUI_HealthToolBar", "addItemView() mToolbarLayout is null or view is null");
            return;
        }
        linearLayout.addView(view);
        view.invalidate();
        view.requestLayout();
    }

    public void cHc_(View view) {
        LinearLayout linearLayout;
        if (view instanceof LinearLayout) {
            this.d = (LinearLayout) view;
        } else {
            ReleaseLogUtil.a("CommonUI_HealthToolBar", "addDefaultView view is not LinearLayout view ", view);
        }
        LinearLayout linearLayout2 = this.l;
        if (linearLayout2 == null || (linearLayout = this.d) == null) {
            ReleaseLogUtil.a("CommonUI_HealthToolBar", "addDefaultView mToolbarLayout ", linearLayout2, " mDefaultView ", this.d);
            return;
        }
        linearLayout2.addView(linearLayout);
        this.d.invalidate();
        this.d.requestLayout();
    }

    public void cHf_(Activity activity) {
        if (activity == null || this.l == null) {
            LogUtil.h("CommonUI_HealthToolBar", "activity is null");
        } else if (CommonUtil.bh()) {
            activity.getWindow().setNavigationBarColor(((ColorDrawable) this.l.getBackground().mutate()).getColor());
        }
    }

    public LinearLayout cHe_(int i) {
        LinearLayout cGZ_ = cGZ_(i);
        if (cGZ_ != null) {
            return cGZ_;
        }
        LogUtil.h("CommonUI_HealthToolBar", "getItem() item is null");
        return null;
    }

    public boolean d(int i) {
        LinearLayout cGZ_ = cGZ_(i);
        if (cGZ_ != null) {
            return cGZ_.getVisibility() == 0;
        }
        LogUtil.h("CommonUI_HealthToolBar", "getIconVisible() item is null");
        return false;
    }

    public void setIconVisible(int i, int i2) {
        LinearLayout cGZ_ = cGZ_(i);
        if (cGZ_ == null) {
            LogUtil.h("CommonUI_HealthToolBar", "setIconVisible() item is null");
            return;
        }
        cGZ_.setVisibility(i2);
        if (i2 == 0) {
            cGZ_.setOnClickListener(this);
        }
        this.d.invalidate();
        this.d.requestLayout();
    }

    public boolean b(int i) {
        LinearLayout cGZ_ = cGZ_(i);
        if (cGZ_ == null) {
            LogUtil.h("CommonUI_HealthToolBar", "getIconEnabled() item is null");
            return false;
        }
        return cGZ_.isEnabled();
    }

    public void setIconEnabled(int i, boolean z) {
        setIconVisible(i, 0);
        LinearLayout cGZ_ = cGZ_(i);
        if (cGZ_ == null) {
            LogUtil.h("CommonUI_HealthToolBar", "setIconEnabled() item is null");
            return;
        }
        cGZ_.setEnabled(z);
        if (z) {
            cGZ_.setOnClickListener(this);
        }
    }

    public void setIcon(int i, int i2) {
        d(i, i2, 1.0f);
    }

    public void setIcon(int i, Drawable drawable) {
        cHa_(i, drawable, 1.0f);
    }

    public void setIconTitle(int i, CharSequence charSequence) {
        d(i, charSequence, false, false);
    }

    public void setIconTitleColor(int i, CharSequence charSequence, int i2) {
        HealthTextView f = f(i);
        if (f == null) {
            LogUtil.h("CommonUI_HealthToolBar", "textView is null");
        } else {
            f.setText(charSequence);
            f.setTextColor(this.e.getResources().getColor(i2));
        }
    }

    public void setOnSingleTapListener(OnSingleTapListener onSingleTapListener) {
        this.f = onSingleTapListener;
    }
}
