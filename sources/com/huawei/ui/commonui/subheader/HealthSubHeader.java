package com.huawei.ui.commonui.subheader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.uikit.phone.hwsubheader.widget.HwSubHeader;
import defpackage.jcf;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class HealthSubHeader extends HwSubHeader {

    /* renamed from: a, reason: collision with root package name */
    private TextView f8952a;
    private ViewStub aa;
    private String ad;
    private LinearLayout b;
    private ImageView c;
    private Guideline d;
    private String e;
    private TextView f;
    private Context g;
    private boolean h;
    private ImageView i;
    private TextView j;
    private ConstraintLayout k;
    private boolean l;
    private LinearLayout m;
    private TextView n;
    private boolean o;
    private LinearLayout p;
    private ImageView q;
    private View r;
    private View s;
    private int t;
    private View u;
    private LinearLayout v;
    private View w;
    private ViewStub x;
    private ViewStub y;
    private ViewStub z;

    public HealthSubHeader(Context context) {
        this(context, null);
    }

    public HealthSubHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthSubHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.t = 0;
        this.h = false;
        this.l = true;
        this.o = false;
        this.g = context;
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.sub_title);
        if (obtainStyledAttributes != null) {
            try {
                this.t = obtainStyledAttributes.getInteger(R$styleable.sub_title_styleType, 0);
                this.ad = obtainStyledAttributes.getString(R$styleable.sub_title_subHeaderText);
                this.e = obtainStyledAttributes.getString(R$styleable.sub_title_subHeaderAction);
                this.h = obtainStyledAttributes.getBoolean(R$styleable.sub_title_splitter, false);
                this.l = obtainStyledAttributes.getBoolean(R$styleable.sub_title_useOnlyLayout, true);
                this.o = obtainStyledAttributes.getBoolean(R$styleable.sub_title_textAllCaps, false);
                obtainStyledAttributes.recycle();
            } catch (IndexOutOfBoundsException unused) {
                LogUtil.b("HealthSubHeader", "get attribute index error");
            }
        }
        if (this.l) {
            i();
        }
    }

    private void i() {
        View inflate = ((LayoutInflater) this.g.getSystemService("layout_inflater")).inflate(R.layout.health_common_sub_header, this);
        this.v = (LinearLayout) inflate.findViewById(R.id.health_subheader_content);
        this.aa = (ViewStub) inflate.findViewById(R.id.headlth_subheader_list);
        this.y = (ViewStub) inflate.findViewById(R.id.headlth_subheader_grid);
        this.z = (ViewStub) inflate.findViewById(R.id.headlth_subheader_list_action);
        this.x = (ViewStub) inflate.findViewById(R.id.headlth_subheader_grid_action);
        View findViewById = inflate.findViewById(R.id.headlth_subheader_splitter);
        this.s = findViewById;
        if (this.h) {
            findViewById.setVisibility(0);
        } else {
            findViewById.setVisibility(8);
        }
        switch (this.t) {
            case 0:
                f();
                break;
            case 1:
                j();
                break;
            case 2:
                c();
                break;
            case 3:
                b();
                break;
            case 4:
                e();
                break;
            case 5:
                d();
                break;
            case 6:
                cGf_(inflate);
                break;
        }
    }

    public void setSplitterVisible(int i) {
        this.s.setVisibility(i);
    }

    private void f() {
        this.u = this.z.inflate();
        this.aa.setVisibility(8);
        this.y.setVisibility(8);
        this.x.setVisibility(8);
        this.z.setVisibility(0);
        cGd_(this.u);
        LinearLayout linearLayout = this.p;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    private void j() {
        this.r = this.aa.inflate();
        this.aa.setVisibility(0);
        this.y.setVisibility(8);
        this.x.setVisibility(8);
        this.z.setVisibility(8);
        cGg_(this.r);
        ConstraintLayout constraintLayout = this.k;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
    }

    private void c() {
        this.u = this.x.inflate();
        this.aa.setVisibility(8);
        this.y.setVisibility(8);
        this.x.setVisibility(0);
        this.z.setVisibility(8);
        cGd_(this.u);
        LinearLayout linearLayout = this.p;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    private void b() {
        this.r = this.y.inflate();
        this.aa.setVisibility(8);
        this.y.setVisibility(0);
        this.x.setVisibility(8);
        this.z.setVisibility(8);
        cGg_(this.r);
        ConstraintLayout constraintLayout = this.k;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
    }

    private void e() {
        this.u = this.z.inflate();
        this.aa.setVisibility(8);
        this.y.setVisibility(8);
        this.x.setVisibility(8);
        this.z.setVisibility(0);
        cGd_(this.u);
    }

    private void d() {
        this.u = this.x.inflate();
        this.aa.setVisibility(8);
        this.y.setVisibility(8);
        this.x.setVisibility(0);
        this.z.setVisibility(8);
        cGd_(this.u);
    }

    private void cGf_(View view) {
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.headlth_subheader_description_action);
        this.w = viewStub.inflate();
        viewStub.setVisibility(0);
        cGd_(this.w);
    }

    private void cGg_(View view) {
        this.f = (TextView) view.findViewById(R.id.hwsubheader_title_left);
        this.k = (ConstraintLayout) view.findViewById(R.id.hwsubheader_more_container);
        this.n = (TextView) view.findViewById(R.id.hwsubheader_more_text);
        this.q = (ImageView) view.findViewById(R.id.hwsubheader_more_arrow);
        this.d = (Guideline) view.findViewById(R.id.hwsubheader_guide_horizontal);
        jcf.bEE_(this.q, 1);
        jcf.bEz_(this.q, nsf.j(R$string.accessibility_more));
        TextView textView = this.f;
        if (textView != null) {
            textView.setText(this.ad);
            this.f.setAllCaps(this.o);
        } else {
            LogUtil.a("HealthSubHeader", "mHeaderTitle is null.");
        }
        TextView textView2 = this.n;
        if (textView2 != null) {
            textView2.setText(R$string.IDS_user_profile_more_new);
            this.n.setAllCaps(this.o);
            if (LanguageUtil.bc(this.g)) {
                TextView textView3 = this.n;
                textView3.setCompoundDrawablesWithIntrinsicBounds(nrz.cKm_(this.g, textView3.getCompoundDrawables()[2]), (Drawable) null, (Drawable) null, (Drawable) null);
            }
            if (nsn.s()) {
                this.n.setTextSize(1, 20.0f);
            }
        }
        cGe_(view);
    }

    private void cGe_(View view) {
        this.b = (LinearLayout) view.findViewById(R.id.close_layout);
        this.c = (ImageView) view.findViewById(R.id.hwsubheader_close_btn);
        this.i = (ImageView) view.findViewById(R.id.hwsubheader_info_btn);
    }

    private void cGd_(View view) {
        this.f = (TextView) view.findViewById(R.id.hwsubheader_title_left);
        this.j = (TextView) view.findViewById(R.id.hwsubheader_title_description);
        this.p = (LinearLayout) view.findViewById(R.id.hwsubheader_action_right_container);
        this.f8952a = (TextView) view.findViewById(R.id.hwsubheader_action_right);
        this.m = (LinearLayout) view.findViewById(R.id.hwsubheader_layout_background);
        TextView textView = this.f;
        if (textView != null) {
            textView.setText(this.ad);
            this.f.setAllCaps(this.o);
        }
        TextView textView2 = this.j;
        if (textView2 != null) {
            textView2.setText(this.ad);
            this.f.setAllCaps(this.o);
        }
        TextView textView3 = this.f8952a;
        if (textView3 != null && this.e != null) {
            textView3.setTextColor(getResources().getColor(R$color.common_colorAccent));
            this.f8952a.setText(this.e);
            this.f8952a.setAllCaps(this.o);
            return;
        }
        LogUtil.a("HealthSubHeader", "mActionTextView is null.");
    }

    public void setMoreViewClickListener(View.OnClickListener onClickListener) {
        int i;
        ConstraintLayout constraintLayout = this.k;
        if (constraintLayout == null || onClickListener == null || (i = this.t) == 2 || i == 0) {
            return;
        }
        constraintLayout.setOnClickListener(onClickListener);
    }

    public void setActionViewClickListener(View.OnClickListener onClickListener) {
        TextView textView = this.f8952a;
        if (textView == null || onClickListener == null) {
            return;
        }
        textView.setOnClickListener(onClickListener);
    }

    public TextView getActionView() {
        return this.f8952a;
    }

    public LinearLayout getActionViewContainner() {
        return this.p;
    }

    public LinearLayout getParentLayout() {
        return this.m;
    }

    public void setHeadTitleText(String str) {
        TextView textView = this.f;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    public void setHeadTitleText(SpannableString spannableString) {
        TextView textView = this.f;
        if (textView == null || spannableString == null) {
            return;
        }
        textView.setText(spannableString);
    }

    public void setHeadTitleForEnglish() {
        TextView textView = this.f;
        if (textView != null) {
            textView.setAllCaps(false);
        }
    }

    public void setHeaderDescriptionText(String str) {
        TextView textView = this.j;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    public void setHeadTitleTextColor(int i) {
        TextView textView = this.f;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setSubHeaderTitleScaleTextSize(float f) {
        TextView textView = this.f;
        if (textView != null) {
            textView.setTextSize(0, textView.getTextSize() * f);
        }
    }

    public void setHeadTitleMaxLine(int i) {
        TextView textView = this.f;
        if (textView != null) {
            textView.setMaxLines(i);
            this.f.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    public void setGuideLine() {
        LogUtil.a("HealthSubHeader", "guideLine," + this.d);
        Guideline guideline = this.d;
        if (guideline != null) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) guideline.getLayoutParams();
            layoutParams.guidePercent = 0.99f;
            this.d.setLayoutParams(layoutParams);
        }
    }

    public void setMoreText(String str) {
        int i = this.t;
        if (i != 1 && i != 3) {
            LogUtil.a("HealthSubHeader", "mStyleType != STYLE_LIST_MORE && mStyleType != STYLE_GRID_MORE");
            return;
        }
        TextView textView = this.n;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    public void setMoreTextMaxLines(int i) {
        int i2 = this.t;
        if (i2 != 1 && i2 != 3) {
            LogUtil.a("HealthSubHeader", "mStyleType != STYLE_LIST_MORE && mStyleType != STYLE_GRID_MORE");
            return;
        }
        TextView textView = this.n;
        if (textView == null || i <= 0) {
            return;
        }
        textView.setMaxLines(i);
        ConstraintLayout constraintLayout = this.k;
        if (constraintLayout != null) {
            ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
            layoutParams.height = -2;
            this.k.setLayoutParams(layoutParams);
        }
    }

    public void setMoreTextAlignment(int i) {
        int i2 = this.t;
        if (i2 != 1 && i2 != 3) {
            LogUtil.a("HealthSubHeader", "mStyleType != STYLE_LIST_MORE && mStyleType != STYLE_GRID_MORE");
            return;
        }
        TextView textView = this.n;
        if (textView != null) {
            textView.setTextAlignment(i);
        }
    }

    public void setMoreTextColor(int i) {
        int i2 = this.t;
        if (i2 != 1 && i2 != 3) {
            LogUtil.a("HealthSubHeader", "mStyleType != STYLE_LIST_MORE && mStyleType != STYLE_GRID_MORE");
            return;
        }
        TextView textView = this.n;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setMoreTextVisibility(int i) {
        int i2 = this.t;
        if (i2 != 1 && i2 != 3) {
            LogUtil.a("HealthSubHeader", "mStyleType != STYLE_LIST_MORE && mStyleType != STYLE_GRID_MORE");
            return;
        }
        TextView textView = this.n;
        if (textView != null) {
            textView.setVisibility(i);
        }
    }

    @Deprecated
    public void setRightDrawable(int i) {
        int i2 = this.t;
        if (i2 != 1 && i2 != 3) {
            LogUtil.a("HealthSubHeader", "current subHeader type is invalid");
            return;
        }
        if (this.q.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.q.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            this.q.setLayoutParams(layoutParams);
            if (ContextCompat.getDrawable(this.g, i) != null) {
                this.q.setImageDrawable(nrf.cJH_(ContextCompat.getDrawable(this.g, i), ContextCompat.getColor(this.g, R$color.colorPrimary)));
            }
        }
    }

    public void setRightDrawable(int i, CharSequence charSequence) {
        int i2 = this.t;
        if (i2 != 1 && i2 != 3) {
            ReleaseLogUtil.a("HealthSubHeader", "setRightDrawable mStyleType ", Integer.valueOf(i2));
            return;
        }
        ImageView imageView = this.q;
        if (imageView == null) {
            ReleaseLogUtil.a("HealthSubHeader", "setRightDrawable mRightArray is null");
            return;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (!(layoutParams instanceof ConstraintLayout.LayoutParams)) {
            ReleaseLogUtil.a("HealthSubHeader", "setRightDrawable layoutParams ", layoutParams);
            return;
        }
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        this.q.setLayoutParams(layoutParams2);
        Drawable cKq_ = nsf.cKq_(i);
        if (cKq_ == null) {
            ReleaseLogUtil.a("HealthSubHeader", "setRightDrawable drawable is null");
            return;
        }
        this.q.setImageDrawable(nrf.cJH_(cKq_, nsf.c(R$color.colorPrimary)));
        if (TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("HealthSubHeader", "setRightDrawable contentDescription ", charSequence);
        } else {
            jcf.bEz_(this.q, charSequence);
        }
    }

    public void setRightArrayVisibility(int i) {
        int i2 = this.t;
        if (i2 != 1 && i2 != 3) {
            LogUtil.a("HealthSubHeader", "current subHeader type is invalid");
            return;
        }
        ImageView imageView = this.q;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
    }

    public void setSplitterVisibility(int i) {
        View view = this.s;
        if (view != null) {
            view.setVisibility(i);
        }
    }

    public void setHeadActionText(String str) {
        TextView textView = this.f8952a;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    public void setPaddingStartEnd(float f, float f2) {
        Context context = this.g;
        if (context == null) {
            return;
        }
        View view = this.u;
        if (view != null) {
            view.setPadding(nsn.c(context, f), this.u.getPaddingTop(), nsn.c(this.g, f2), this.u.getPaddingBottom());
        }
        View view2 = this.r;
        if (view2 != null) {
            view2.setPadding(nsn.c(this.g, f), this.r.getPaddingTop(), nsn.c(this.g, f2), this.r.getPaddingBottom());
        }
    }

    public void setMarginStartEnd(float f, float f2) {
        if (this.g == null) {
            return;
        }
        if (this.u != null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(nsn.c(this.g, f), 0, nsn.c(this.g, f2), 0);
            this.u.setLayoutParams(layoutParams);
        }
        if (this.r != null) {
            ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(-2, -2);
            layoutParams2.setMargins(nsn.c(this.g, f), 0, nsn.c(this.g, f2), 0);
            this.r.setLayoutParams(layoutParams2);
        }
    }

    public void setSubHeaderSafeRegion() {
        LinearLayout linearLayout = this.v;
        if (linearLayout == null) {
            return;
        }
        BaseActivity.setViewSafeRegion(false, linearLayout);
    }

    public void setMoreLayoutVisibility(int i) {
        ConstraintLayout constraintLayout = this.k;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(i);
        }
    }

    public void setSubHeaderBackgroundColor(int i) {
        LinearLayout linearLayout = this.v;
        if (linearLayout == null) {
            LogUtil.h("HealthSubHeader", "setSubHeaderBackgroundColor mSubHeaderContent is null");
            return;
        }
        int childCount = linearLayout.getChildCount();
        if (childCount < 1) {
            LogUtil.h("HealthSubHeader", "setSubHeaderBackgroundColor childCount is zero");
            return;
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.v.getChildAt(i2);
            if (childAt == null) {
                LogUtil.h("HealthSubHeader", "setSubHeaderBackgroundColor child is null");
            } else {
                childAt.setBackgroundColor(i);
            }
        }
    }

    public void setCloseBtnVisibility(int i) {
        ImageView imageView = this.c;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(i);
        g();
    }

    public void setInfoBtnVisibility(int i) {
        ImageView imageView = this.i;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(i);
        g();
    }

    private void g() {
        if (this.c.getVisibility() == 0 || this.i.getVisibility() == 0) {
            this.b.setVisibility(0);
            setMoreLayoutVisibility(8);
        }
        if (this.c.getVisibility() == 8 && this.i.getVisibility() == 8) {
            this.b.setVisibility(8);
        }
    }

    private void cGh_(ImageView imageView, int i, CharSequence charSequence) {
        if (imageView == null) {
            ReleaseLogUtil.a("HealthSubHeader", "setImageResource imageView is null");
            return;
        }
        imageView.setImageResource(i);
        if (TextUtils.isEmpty(charSequence)) {
            ReleaseLogUtil.a("HealthSubHeader", "setImageResource contentDescription ", charSequence);
        } else {
            jcf.bEz_(imageView, charSequence);
        }
    }

    public void setCloseBtnSrc(int i, CharSequence charSequence) {
        cGh_(this.c, i, charSequence);
    }

    public void setInfoBtnSrc(int i, CharSequence charSequence) {
        cGh_(this.i, i, charSequence);
    }

    public void setCloseBtnClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.c;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void setInfoBtnClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }
}
