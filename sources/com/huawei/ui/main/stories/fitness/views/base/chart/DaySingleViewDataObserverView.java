package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import defpackage.jcf;
import defpackage.nrz;
import defpackage.nsf;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class DaySingleViewDataObserverView extends ScrollChartObserverView {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9936a;
    private LinearLayout b;
    private HealthTextView c;
    private HealthDivider d;
    private final Context e;
    private HealthDivider f;
    private LinearLayout g;
    private LinearLayout h;
    private View i;
    private HealthTextView j;
    private HealthButton k;
    private RelativeLayout l;
    private RelativeLayout m;
    private HealthImageView n;
    private RelativeLayout o;
    private HealthRecycleView p;
    private ScrollChartObserverView q;
    private HealthTextView r;
    private LinearLayout s;
    private HealthTextView t;
    private HealthDivider u;
    private HealthTextView x;
    private View y;

    public DaySingleViewDataObserverView(Context context) {
        super(context, null, null, null);
        this.e = BaseApplication.e();
        d();
    }

    private void d() {
        inflate(getContext(), R.layout.single_data_step_transfer_view, this);
        this.u = (HealthDivider) findViewById(R.id.day_tip_divider);
        this.r = (HealthTextView) findViewById(R.id.day_step_data_description);
        this.g = (LinearLayout) findViewById(R.id.day_distance_description);
        this.f = (HealthDivider) findViewById(R.id.day_distance_description_divider);
        this.b = (LinearLayout) findViewById(R.id.day_calorie_description);
        this.d = (HealthDivider) findViewById(R.id.day_calorie_description_divider);
        this.h = (LinearLayout) findViewById(R.id.day_step_progress_layout);
        this.c = (HealthTextView) findViewById(R.id.step_away_from_target);
        this.k = (HealthButton) findViewById(R.id.jump_to_sport_share_btn);
        this.f9936a = (RelativeLayout) findViewById(R.id.day_step_jump_to_sport_layout);
        this.y = findViewById(R.id.day_step_target_layout);
        this.j = (HealthTextView) findViewById(R.id.day_step_target_edit);
        HealthImageView healthImageView = (HealthImageView) findViewById(R.id.day_step_target_edit_icon);
        this.n = healthImageView;
        healthImageView.setImageDrawable(nrz.cKl_(this.e, R.drawable._2131429937_res_0x7f0b0a31));
        this.i = findViewById(R.id.day_step_target_edit_accessibility);
        this.x = (HealthTextView) findViewById(R.id.day_step_target_progress);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.hrv_step_source);
        this.p = healthRecycleView;
        healthRecycleView.setNestedScrollingEnabled(false);
        this.t = (HealthTextView) findViewById(R.id.step_source_management);
        this.s = (LinearLayout) findViewById(R.id.step_source_title_layout);
        ((HealthSubHeader) findViewById(R.id.hsh_step_source_title)).setSubHeaderBackgroundColor(nsf.c(R.color._2131296971_res_0x7f0902cb));
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.day_step_target_sub_header);
        healthSubHeader.setSubHeaderBackgroundColor(nsf.c(R.color._2131296971_res_0x7f0902cb));
        LinearLayout parentLayout = healthSubHeader.getParentLayout();
        if (parentLayout != null) {
            parentLayout.setPadding(0, parentLayout.getPaddingTop(), 0, parentLayout.getPaddingBottom());
        }
    }

    public void dvx_(ScrollChartObserverView scrollChartObserverView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3) {
        this.q = scrollChartObserverView;
        int i = 0;
        if (relativeLayout == null) {
            this.d.setVisibility(8);
            this.b.setVisibility(8);
        } else {
            this.m = relativeLayout;
            this.d.setVisibility(0);
            this.b.setVisibility(0);
            this.b.addView(relativeLayout);
        }
        if (relativeLayout2 == null) {
            this.f.setVisibility(8);
            this.g.setVisibility(8);
        } else {
            this.l = relativeLayout2;
            this.f.setVisibility(0);
            this.g.setVisibility(0);
            this.g.addView(relativeLayout2);
        }
        HealthTextView healthTextView = this.r;
        if (this.b.getVisibility() != 0 && this.g.getVisibility() != 0) {
            i = 8;
        }
        healthTextView.setVisibility(i);
        this.o = relativeLayout3;
        this.h.addView(relativeLayout3);
    }

    public ScrollChartObserverView a() {
        return this.q;
    }

    public RelativeLayout dvw_() {
        return this.o;
    }

    public HealthRecycleView e() {
        return this.p;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        this.q.onRangeShow(hwHealthBaseScrollBarLineChart, i, i2);
    }

    public HealthTextView getTargetTextView() {
        return this.x;
    }

    public void setTargetLayoutVisibility(int i) {
        View view = this.y;
        if (view == null) {
            return;
        }
        view.setVisibility(i);
    }

    public void setEditTargetVisibility(int i) {
        HealthTextView healthTextView = this.j;
        if (healthTextView != null) {
            healthTextView.setVisibility(i);
        }
        HealthImageView healthImageView = this.n;
        if (healthImageView != null) {
            healthImageView.setVisibility(i);
        }
        setEditTargetAccessibilityVisibility(i);
    }

    public void setEditTargetAccessibilityVisibility(int i) {
        if (this.i == null) {
            ReleaseLogUtil.a("DaySingleViewDataObserverView", "setEditTargetAccessibilityVisibility mEditTargetAccessibility is null");
        } else if (jcf.c()) {
            jcf.bEA_(this.i, nsf.h(R$string.IDS_edit_target), Button.class);
            this.i.setVisibility(i);
        } else {
            this.i.setVisibility(8);
        }
    }

    public void setEditTargetOnClickListener(View.OnClickListener onClickListener) {
        HealthTextView healthTextView = this.j;
        if (healthTextView != null) {
            healthTextView.setOnClickListener(onClickListener);
        }
        HealthImageView healthImageView = this.n;
        if (healthImageView != null) {
            healthImageView.setOnClickListener(onClickListener);
        }
        View view = this.i;
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    private void c() {
        LinearLayout linearLayout;
        if (this.f9936a == null || (linearLayout = this.b) == null || this.g == null || linearLayout.getVisibility() != 8 || this.g.getVisibility() != 8) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.f9936a.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(0, nsf.b(R.dimen._2131363122_res_0x7f0a0532), 0, nsf.b(R.dimen._2131363122_res_0x7f0a0532));
            this.f9936a.setLayoutParams(layoutParams2);
        }
    }

    public void setTipVisibility(int i) {
        RelativeLayout relativeLayout = this.f9936a;
        if (relativeLayout == null) {
            return;
        }
        relativeLayout.setVisibility(i);
        HealthDivider healthDivider = this.u;
        if (healthDivider != null) {
            healthDivider.setVisibility(i);
        }
        c();
    }

    public int getTipVisibility() {
        RelativeLayout relativeLayout = this.f9936a;
        if (relativeLayout == null) {
            return 8;
        }
        return relativeLayout.getVisibility();
    }

    public void setTipText(CharSequence charSequence) {
        if (this.f9936a == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f9936a.setVisibility(8);
            HealthDivider healthDivider = this.u;
            if (healthDivider != null) {
                healthDivider.setVisibility(8);
                return;
            }
            return;
        }
        HealthTextView healthTextView = this.c;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setText(charSequence);
    }

    public void setTipButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        if (this.k == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.k.setText(charSequence);
        this.k.setOnClickListener(onClickListener);
    }

    public void setTipButton(int i, CharSequence charSequence, View.OnClickListener onClickListener) {
        if (this.k == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.k.setText(charSequence);
        this.k.setTextColor(nsf.c(i));
        this.k.setOnClickListener(onClickListener);
    }

    public HealthTextView getSourceManagement() {
        return this.t;
    }

    public LinearLayout getSourceLayout() {
        return this.s;
    }
}
