package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.module.TrainActionIntro;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class TrainActionIntro extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3193a;
    private ImageView b;
    private HealthViewPager c;
    public ImageView d;
    private SlideDownLayout e;
    private int f;
    private HealthTextView g;
    private CustomTitleBar h;
    private int i;
    private HealthTextView j;

    public TrainActionIntro(Context context) {
        super(context);
        this.i = 0;
    }

    public TrainActionIntro(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 0;
        aEA_(context, attributeSet);
    }

    public TrainActionIntro(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 0;
        aEA_(context, attributeSet);
    }

    private void aEA_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100657_res_0x7f0603f1});
        this.f = obtainStyledAttributes.getInteger(0, 0);
        obtainStyledAttributes.recycle();
        int i = this.f == 1 ? R.layout.sug_coach_action_intro : R.layout.sug_traindetail_action_intro;
        setClickable(true);
        View inflate = LayoutInflater.from(getContext()).inflate(i, this);
        this.e = (SlideDownLayout) inflate.findViewById(R.id.sug_coach_ll_yaodian);
        HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.sug_coach_vp_action_yaodian);
        this.c = healthViewPager;
        healthViewPager.setIsScroll(false);
        this.g = (HealthTextView) inflate.findViewById(R.id.sug_coach_intro_motionc);
        this.j = (HealthTextView) inflate.findViewById(R.id.sug_coach_intro_totle);
        this.f3193a = (ImageView) inflate.findViewById(R.id.sug_coach_iv_action_pre);
        this.b = (ImageView) inflate.findViewById(R.id.sug_coach_iv_action_nex);
        if (LanguageUtil.bc(context)) {
            this.f3193a.setImageDrawable(nrz.cKn_(context, R.drawable._2131431629_res_0x7f0b10cd));
            this.b.setImageDrawable(nrz.cKn_(context, R.drawable._2131431630_res_0x7f0b10ce));
        }
        if (this.f == 1) {
            this.d = (ImageView) inflate.findViewById(R.id.sug_coachiv_close);
        }
        aEB_(inflate);
    }

    public void c() {
        if (this.h != null && (this.c.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
            if (nsn.ag(BaseApplication.getContext())) {
                layoutParams.topMargin = this.i;
            } else {
                layoutParams.topMargin = 0;
            }
            this.c.setLayoutParams(layoutParams);
        }
    }

    private void aEB_(View view) {
        if (this.f == 1) {
            return;
        }
        CustomTitleBar customTitleBar = (CustomTitleBar) view.findViewById(R.id.sug_action_title_bar);
        this.h = customTitleBar;
        if (customTitleBar == null) {
            return;
        }
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131299296_res_0x7f090be0));
        this.h.setLeftButtonVisibility(0);
        this.h.setLeftButtonDrawable(ContextCompat.getDrawable(getContext(), R$drawable.ic_health_navbar_close_black), nsf.h(R$string.accessibility_close));
        this.h.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: fqx
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                TrainActionIntro.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        this.i = this.h.getHeight();
        c();
    }

    public void setAdapter(HealthPagerAdapter healthPagerAdapter) {
        this.c.setAdapter(healthPagerAdapter);
    }

    public void setOnSlidingListener(OnSlidingListener onSlidingListener) {
        if (onSlidingListener != null) {
            this.e.setOnSlidingListener(onSlidingListener);
        }
    }

    public int getCurrentIndex() {
        try {
            return Integer.parseInt(this.g.getText().toString().trim());
        } catch (NumberFormatException e) {
            LogUtil.b("Suggestion_TrainActionIntro", e.getMessage());
            return 0;
        }
    }

    public void setCurrentIndex(int i) {
        this.g.setText(UnitUtil.e(i, 1, 0));
    }

    public void setCurrentIndex(String str) {
        this.g.setText(str);
    }

    public void a(String str, String str2) {
        this.g.setText(str);
        this.j.setText(str2);
    }

    public void b(int i, int i2) {
        this.g.setText(UnitUtil.e(i, 1, 0));
        this.j.setText(UnitUtil.e(i2, 1, 0));
    }

    public ImageView getPreAction() {
        return this.f3193a;
    }

    public ImageView getNextAction() {
        return this.b;
    }

    public CustomTitleBar getTitleBar() {
        return this.h;
    }
}
