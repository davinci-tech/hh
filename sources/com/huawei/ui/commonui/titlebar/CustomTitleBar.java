package com.huawei.ui.commonui.titlebar;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.spinner.HealthSpinner;
import defpackage.jcf;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class CustomTitleBar extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private BackToTopListener f8961a;
    private String aa;
    private View ab;
    private ViewStub ac;
    private ViewStub ad;
    private int ae;
    private int af;
    private ViewStub ag;
    private HealthTextView ah;
    private HealthSpinner ai;
    private int ak;
    private int al;
    private HealthSpinner am;
    private HealthTextView an;
    private Boolean b;
    private RelativeLayout c;
    private Context d;
    private Drawable e;
    private String f;
    private Boolean g;
    private ImageView h;
    private Boolean i;
    private Drawable j;
    private String k;
    private ViewStub l;
    private Drawable m;
    private int n;
    private LinearLayout o;
    private LinearLayout p;
    private ImageView q;
    private ImageView r;
    private ImageView s;
    private ImageView t;
    private LinearLayout u;
    private ViewStub v;
    private LinearLayout w;
    private LinearLayout x;
    private ViewStub y;
    private HealthTextView z;

    public interface BackToTopListener {
        void backToTop();
    }

    public CustomTitleBar(Context context) {
        this(context, null);
        this.d = context;
        c();
    }

    public CustomTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ak = -1;
        this.n = -1;
        if (attributeSet == null) {
            return;
        }
        this.d = context;
        this.ae = context.getResources().getColor(R$color.colorPrimary);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.custom_title_bar);
        if (obtainStyledAttributes != null) {
            this.ak = obtainStyledAttributes.getInteger(R$styleable.custom_title_bar_custom_type, 2);
            this.aa = obtainStyledAttributes.getString(R$styleable.custom_title_bar_titleBarText);
            this.e = obtainStyledAttributes.getDrawable(R$styleable.custom_title_bar_titleBarBg);
            this.af = obtainStyledAttributes.getColor(R$styleable.custom_title_bar_titleBarBgColor, context.getResources().getColor(R$color.colorStatusbarBg));
            this.n = obtainStyledAttributes.getInteger(R$styleable.custom_title_bar_leftSoftkey_visibility, 0);
            this.j = obtainStyledAttributes.getDrawable(R$styleable.custom_title_bar_leftIcon);
            this.f = obtainStyledAttributes.getString(R$styleable.custom_title_bar_leftIconContentDescription);
            this.m = obtainStyledAttributes.getDrawable(R$styleable.custom_title_bar_rightIcon);
            this.k = obtainStyledAttributes.getString(R$styleable.custom_title_bar_rightIconContentDescription);
            this.b = Boolean.valueOf(obtainStyledAttributes.getBoolean(R$styleable.custom_title_bar_titleBarLeftIconTint, true));
            this.i = Boolean.valueOf(obtainStyledAttributes.getBoolean(R$styleable.custom_title_bar_titleBarRightIconTint, true));
            this.g = Boolean.valueOf(obtainStyledAttributes.getBoolean(R$styleable.custom_title_bar_titleBarTint, true));
            try {
                this.al = obtainStyledAttributes.getColor(R$styleable.custom_title_bar_titleBarTextColor, this.d.getResources().getColor(R$color.colorAppbarTitle));
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomTitleBar", "Resources.NotFoundException.");
            }
            obtainStyledAttributes.recycle();
        }
        c();
    }

    private void c() {
        ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.health_commonui_app_bar, this);
        this.c = (RelativeLayout) findViewById(R.id.app_titlebar);
        this.ab = findViewById(R.id.statusbar_panel);
        this.l = (ViewStub) findViewById(R.id.hwtoolbar_icon1);
        this.y = (ViewStub) findViewById(R.id.hwtoolbar_icon2);
        this.ac = (ViewStub) findViewById(R.id.hwtoolbar_icon3);
        this.ad = (ViewStub) findViewById(R.id.hwtoolbar_icon4);
        this.v = (ViewStub) findViewById(R.id.hwtoolbar_icon5);
        this.an = (HealthTextView) findViewById(R.id.action_bar_title);
        this.ah = (HealthTextView) findViewById(R.id.action_bar_subtitle);
        this.ag = (ViewStub) findViewById(R.id.titleSpinner_layout);
        this.z = (HealthTextView) findViewById(R.id.hwtoolbar_right_text);
        g();
        this.an.setTextColor(this.al);
        LogUtil.a("CustomTitleBar", "mTypeIndex: ", Integer.valueOf(this.ak));
        int i = this.ak;
        if (i == 0) {
            b();
        } else {
            if (i != 1) {
                if (i == 2) {
                    e();
                } else if (i != 3) {
                    e();
                }
            }
            a();
        }
        if (this.ak == 4) {
            h();
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a("CustomTitleBar", "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        l();
    }

    private void j() {
        TypedArray obtainStyledAttributes;
        Drawable drawable = this.e;
        if (drawable != null) {
            this.c.setBackgroundDrawable(drawable);
            return;
        }
        int i = this.af;
        if (i != 0) {
            this.c.setBackgroundColor(i);
            return;
        }
        Resources.Theme theme = this.d.getTheme();
        if (theme == null || (obtainStyledAttributes = theme.obtainStyledAttributes(new int[]{R.attr._2131101278_res_0x7f06065e})) == null) {
            return;
        }
        this.c.setBackgroundColor(obtainStyledAttributes.getColor(0, ContextCompat.getColor(this.d, R$color.colorStatusbarBg)));
        obtainStyledAttributes.recycle();
    }

    private void i() {
        HealthTextView healthTextView = this.ah;
        if (healthTextView != null && healthTextView.getVisibility() != 8) {
            this.an.setSingleLine(true);
            this.an.setAutoTextSize(2, 20.0f);
            this.an.setAutoTextInfo(14, 2, 2);
        } else {
            this.an.setAutoTextSize(2, 20.0f);
            this.an.setAutoTextInfo(14, 2, 2);
            this.an.setSingleLine(false);
            this.an.setMaxLines(2);
        }
    }

    private void e() {
        String str = this.aa;
        if (str != null) {
            this.an.setText(str);
        }
        this.h.setVisibility(0);
        this.s.setVisibility(8);
        this.r.setVisibility(8);
        this.t.setVisibility(8);
        Drawable drawable = this.j;
        if (drawable != null) {
            this.h.setImageDrawable(drawable);
        } else {
            d();
        }
        jcf.bEz_(this.h, this.f);
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.titlebar.CustomTitleBar.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Activity activity = (Activity) CustomTitleBar.this.d;
                LogUtil.a("CustomTitleBar", "Finish Acitivity for cause: left soft key clicked");
                activity.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b() {
        String str = this.aa;
        if (str != null) {
            this.an.setText(str);
        }
        this.h.setVisibility(8);
        this.s.setVisibility(4);
        this.r.setVisibility(8);
        this.t.setVisibility(8);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.titleContainer);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.setMarginStart(nsn.c(this.d, 24.0f));
        linearLayout.setLayoutParams(layoutParams);
    }

    private void a() {
        String str = this.aa;
        if (str != null) {
            this.an.setText(str);
        }
        this.h.setVisibility(0);
        if (this.j != null) {
            jcf.bEz_(this.h, this.f);
            this.h.setImageDrawable(this.j);
        } else if (this.ak == 1) {
            jcf.bEz_(this.h, nsf.j(R$string.accessibility_cancel));
            this.h.setImageDrawable(this.d.getResources().getDrawable(R$drawable.ic_public_select_cancel));
        } else {
            jcf.bEz_(this.h, this.f);
            d();
        }
        setLeftSoftkeyVisibility(this.o);
        this.s.setVisibility(0);
        Drawable drawable = this.m;
        if (drawable != null) {
            this.s.setImageDrawable(drawable);
            jcf.bEz_(this.s, this.k);
        } else if (this.ak == 1) {
            f();
            jcf.bEz_(this.s, this.k);
        } else {
            this.s.setVisibility(8);
            LogUtil.a("CustomTitleBar", "mRightIconDrawable == null.");
        }
        this.r.setVisibility(8);
        this.t.setVisibility(8);
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.titlebar.CustomTitleBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Activity activity = (Activity) CustomTitleBar.this.d;
                LogUtil.a("CustomTitleBar", "Finish Acitivity for cause: left soft key clicked");
                activity.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void g() {
        LinearLayout linearLayout = (LinearLayout) this.l.inflate();
        this.o = linearLayout;
        this.h = (ImageView) linearLayout.findViewById(R.id.hwappbarpattern_cancel_icon);
        if (LanguageUtil.b(this.d) || LanguageUtil.bn(this.d)) {
            this.o.setLayoutDirection(1);
        }
        LinearLayout linearLayout2 = (LinearLayout) this.y.inflate();
        this.p = linearLayout2;
        this.s = (ImageView) linearLayout2.findViewById(R.id.hwappbarpattern_ok_icon);
        LinearLayout linearLayout3 = (LinearLayout) this.ac.inflate();
        this.x = linearLayout3;
        ImageView imageView = (ImageView) linearLayout3.findViewById(R.id.hwappbarpattern_menu_icon);
        this.r = imageView;
        imageView.setContentDescription(this.d.getResources().getString(R$string.IDS_global_search_button));
        LinearLayout linearLayout4 = (LinearLayout) this.ad.inflate();
        this.u = linearLayout4;
        ImageView imageView2 = (ImageView) linearLayout4.findViewById(R.id.hwappbarpattern_menu_icon);
        this.t = imageView2;
        imageView2.setContentDescription(this.d.getResources().getString(R$string.IDS_global_search_button));
        LinearLayout linearLayout5 = (LinearLayout) this.v.inflate();
        this.w = linearLayout5;
        ImageView imageView3 = (ImageView) linearLayout5.findViewById(R.id.hwappbarpattern_menu_icon);
        this.q = imageView3;
        imageView3.setContentDescription(this.d.getResources().getString(R$string.IDS_global_search_button));
        if (this.g.booleanValue()) {
            if (this.b.booleanValue()) {
                this.h.setImageTintList(ColorStateList.valueOf(this.ae));
            }
            if (this.i.booleanValue()) {
                this.s.setImageTintList(ColorStateList.valueOf(this.ae));
                this.r.setImageTintList(ColorStateList.valueOf(this.ae));
                this.t.setImageTintList(ColorStateList.valueOf(this.ae));
                this.q.setImageTintList(ColorStateList.valueOf(this.ae));
            }
        }
        l();
        j();
        if (nsn.r()) {
            this.an.setTextSize(1, 35.0f);
        } else {
            i();
        }
        BaseActivity.setViewSafeRegion(false, this.c);
    }

    private void h() {
        HealthTextView healthTextView = this.an;
        if (healthTextView != null) {
            healthTextView.setVisibility(8);
        }
        if (this.ag.getParent() != null) {
            View inflate = this.ag.inflate();
            this.ai = (HealthSpinner) inflate.findViewById(R.id.titleSpinner);
            this.am = (HealthSpinner) inflate.findViewById(R.id.titleSpinners);
        }
        HealthSpinner healthSpinner = this.ai;
        if (healthSpinner != null) {
            healthSpinner.setVisibility(0);
        }
        HealthSpinner healthSpinner2 = this.am;
        if (healthSpinner2 != null) {
            healthSpinner2.setVisibility(8);
        }
    }

    private void d() {
        TypedArray obtainStyledAttributes;
        Resources.Theme theme = this.d.getTheme();
        if (theme != null) {
            if (LanguageUtil.bc(this.d)) {
                LogUtil.a("CustomTitleBar", "loadLeftCrossIconByThemeSet isRTLLanguage");
                obtainStyledAttributes = theme.obtainStyledAttributes(new int[]{R.attr._2131101282_res_0x7f060662});
            } else {
                obtainStyledAttributes = theme.obtainStyledAttributes(new int[]{R.attr._2131101276_res_0x7f06065c});
            }
            if (obtainStyledAttributes != null) {
                Drawable drawable = obtainStyledAttributes.getDrawable(0);
                if (drawable != null) {
                    this.h.setImageDrawable(drawable);
                } else {
                    LogUtil.a("CustomTitleBar", "loadLeftCrossIconByThemeSet drawableLeft is null");
                    if (LanguageUtil.bc(this.d)) {
                        this.h.setImageDrawable(ContextCompat.getDrawable(this.d, R$drawable.health_navbar_rtl_back_selector));
                    } else {
                        this.h.setImageDrawable(ContextCompat.getDrawable(this.d, R$drawable.health_navbar_back_selector));
                    }
                }
                jcf.bEz_(this.h, nsf.j(R$string.accessibility_go_back));
                obtainStyledAttributes.recycle();
            }
        }
    }

    private void f() {
        TypedArray obtainStyledAttributes;
        Resources.Theme theme = this.d.getTheme();
        if (theme == null || (obtainStyledAttributes = theme.obtainStyledAttributes(new int[]{R.attr._2131101286_res_0x7f060666})) == null) {
            return;
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            LogUtil.a("CustomTitleBar", "loadRightIconByThemeSet drawableRight is not null");
            this.s.setImageDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
    }

    private void setLeftSoftkeyVisibility(LinearLayout linearLayout) {
        int i = this.n;
        if (i == 0) {
            linearLayout.setVisibility(0);
        } else if (i == 4) {
            linearLayout.setVisibility(4);
        } else {
            if (i != 8) {
                return;
            }
            linearLayout.setVisibility(8);
        }
    }

    private void l() {
        RelativeLayout.LayoutParams cLc_ = nsn.cLc_(this.d);
        View view = this.ab;
        if (view != null) {
            view.setLayoutParams(cLc_);
        }
    }

    public void setBackToTopListener(BackToTopListener backToTopListener) {
        this.f8961a = backToTopListener;
    }

    public void setDoubleClickEnable(boolean z) {
        if (z) {
            this.an.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.titlebar.CustomTitleBar.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nsn.o() && CustomTitleBar.this.f8961a != null) {
                        CustomTitleBar.this.f8961a.backToTop();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    public void setTitleBarBackgroundColor(int i) {
        this.c.setBackgroundColor(i);
    }

    public void setTitleText(String str) {
        HealthTextView healthTextView = this.an;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setText(str);
    }

    public void setTitleSize(float f) {
        HealthTextView healthTextView = this.an;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setAutoTextSize(1, nsn.a(this.d, f));
    }

    public void setTitleMarginStart(int i) {
        HealthTextView healthTextView = this.an;
        if (healthTextView == null) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
        layoutParams.setMarginStart(i);
        this.an.setLayoutParams(layoutParams);
    }

    public void setTitleTextColor(int i) {
        HealthTextView healthTextView = this.an;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setTextColor(i);
    }

    private void cGU_(ImageView imageView, Drawable drawable, CharSequence charSequence) {
        if (imageView == null || drawable == null) {
            ReleaseLogUtil.a("CustomTitleBar", "setDrawable imageView ", imageView, " drawable ", drawable);
            return;
        }
        imageView.setImageDrawable(drawable);
        if (TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("CustomTitleBar", "setDrawable contentDescription ", charSequence);
        } else {
            jcf.bEz_(imageView, charSequence);
        }
    }

    public void setLeftButtonVisibility(int i) {
        ViewStub viewStub = this.l;
        if (viewStub != null) {
            viewStub.setVisibility(i);
        }
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
    }

    public void setLeftButtonDrawable(Drawable drawable, CharSequence charSequence) {
        cGU_(this.h, drawable, charSequence);
    }

    public void setLeftButtonTextColor(Drawable drawable, int i, CharSequence charSequence) {
        setLeftButtonTextColor(drawable, i, this.b.booleanValue(), charSequence);
    }

    public void setLeftButtonTextColor(Drawable drawable, int i, boolean z, CharSequence charSequence) {
        setTitleTextColor(i);
        ImageView imageView = this.h;
        if (imageView == null) {
            ReleaseLogUtil.a("CustomTitleBar", "setLeftButtonTextColor mLeftIconImage is null");
        } else {
            imageView.setImageTintList(z ? ColorStateList.valueOf(this.ae) : null);
            cGU_(this.h, drawable, charSequence);
        }
    }

    public void setLeftButtonClickable(boolean z) {
        ViewStub viewStub = this.l;
        if (viewStub != null) {
            viewStub.setClickable(z);
        }
    }

    public void setLeftButtonOnClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.o;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRightButtonVisibility(int i) {
        ViewStub viewStub = this.y;
        if (viewStub == null) {
            return;
        }
        viewStub.setVisibility(i);
        if (this.p == null) {
            LinearLayout linearLayout = (LinearLayout) this.y.inflate();
            this.p = linearLayout;
            linearLayout.setVisibility(i);
            this.s = (ImageView) this.p.findViewById(R.id.hwappbarpattern_ok_icon);
        }
        ImageView imageView = this.s;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
    }

    public void setRightButtonDrawable(Drawable drawable, CharSequence charSequence) {
        cGU_(this.s, drawable, charSequence);
    }

    public void setRightButtonClickable(boolean z) {
        LinearLayout linearLayout = this.p;
        if (linearLayout != null) {
            linearLayout.setClickable(z);
        }
    }

    public void setRightButtonOnClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.p;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRightFourKeyVisibility(int i) {
        ViewStub viewStub = this.v;
        if (viewStub == null) {
            return;
        }
        viewStub.setVisibility(i);
        if (this.w == null && (this.v.inflate() instanceof LinearLayout)) {
            LinearLayout linearLayout = (LinearLayout) this.v.inflate();
            this.w = linearLayout;
            this.q = (ImageView) linearLayout.findViewById(R.id.hwappbarpattern_menu_icon);
        }
        ImageView imageView = this.q;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
    }

    public void setRightFourKeyBackground(Drawable drawable, CharSequence charSequence) {
        cGU_(this.q, drawable, charSequence);
    }

    public void setRightFourKeyOnClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.w;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRightThirdKeyVisibility(int i) {
        ViewStub viewStub = this.ad;
        if (viewStub == null) {
            return;
        }
        viewStub.setVisibility(i);
        if (this.u == null) {
            LinearLayout linearLayout = (LinearLayout) this.ac.inflate();
            this.u = linearLayout;
            this.t = (ImageView) linearLayout.findViewById(R.id.hwappbarpattern_menu_icon);
        }
        ImageView imageView = this.t;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
    }

    public void setRightThirdKeyBackground(Drawable drawable, CharSequence charSequence) {
        cGU_(this.t, drawable, charSequence);
    }

    public void setRightThirdKeyOnClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.u;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRightSoftkeyVisibility(int i) {
        ViewStub viewStub = this.ac;
        if (viewStub == null) {
            return;
        }
        viewStub.setVisibility(i);
        if (this.x == null) {
            LinearLayout linearLayout = (LinearLayout) this.ac.inflate();
            this.x = linearLayout;
            this.r = (ImageView) linearLayout.findViewById(R.id.hwappbarpattern_menu_icon);
        }
        ImageView imageView = this.r;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
    }

    public void setRightSoftkeyBackground(Drawable drawable, CharSequence charSequence) {
        cGU_(this.r, drawable, charSequence);
    }

    public void setRightSoftkeyOnClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.x;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRightTextButtonVisibility(int i) {
        setRightButtonVisibility(i);
    }

    public void setRightTextButtonClickable(boolean z) {
        setRightButtonClickable(z);
    }

    public void setRightTextButtonOnClickListener(View.OnClickListener onClickListener) {
        setRightButtonOnClickListener(onClickListener);
    }

    public HealthTextView getViewTitle() {
        return this.an;
    }

    public HealthSpinner getTitleSpinner() {
        if (this.ak != 4) {
            LogUtil.b("CustomTitleBar", "getTitleSpinner is null");
            return null;
        }
        return this.ai;
    }

    public View getRightIconImage() {
        setRightButtonVisibility(0);
        this.p.setVisibility(8);
        return this.p;
    }

    public void setAppBarVisible(boolean z) {
        RelativeLayout.LayoutParams layoutParams;
        RelativeLayout relativeLayout = this.c;
        if (relativeLayout != null) {
            if (z) {
                relativeLayout.setVisibility(0);
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            } else {
                relativeLayout.setVisibility(4);
                layoutParams = new RelativeLayout.LayoutParams(-1, nsn.r(this.d));
            }
            this.c.setLayoutParams(layoutParams);
        }
    }

    public void setCustomTitleBarTypeNormal() {
        b();
    }

    public void setRightTextVisible(int i) {
        this.z.setVisibility(i);
    }

    public void setRightTextSize(float f) {
        this.z.setTextSize(1, f);
    }

    public void setRightTextColor(int i) {
        this.z.setTextColor(i);
    }

    public void setRightTextContent(String str) {
        this.z.setText(str);
    }

    public void setRightTextOnClickListener(View.OnClickListener onClickListener) {
        this.z.setOnClickListener(onClickListener);
    }

    public View getRightSoftKey() {
        return this.x;
    }

    public View getRightThirdKey() {
        return this.u;
    }
}
