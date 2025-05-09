package com.huawei.ui.homehealth.stepscard;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.StepView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class StepsCardViewHolder extends StepsBaseCardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9621a;
    private HealthTextView aa;
    private ViewGroup ab;
    private StepView ac;
    private HealthTextView ad;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private HealthTextView ai;
    private ViewGroup aj;
    private HealthTextView al;
    private HealthTextView am;
    private LinearLayout b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private LinearLayout f;
    private HealthTextView g;
    private int h;
    private HealthTextView i;
    private HealthColumnSystem j;
    private ImageView k;
    private HealthTextView l;
    private HealthTextView m;
    private View n;
    private View o;
    private LinearLayout p;
    private ImageView q;
    private LinearLayout r;
    private HealthTextView s;
    private HealthTextView t;
    private ImageView u;
    private ImageView v;
    private ImageView w;
    private HealthTextView x;
    private ImageView y;
    private HealthTextView z;

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public RelativeLayout getActivityLayout() {
        return null;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public LinearLayout getFitnessDataOriginIconLayout() {
        return null;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ImageView getThreeLeafIcon() {
        return null;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public LinearLayout getTwoModelSwitchArea() {
        return null;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void refreshDistanceCalFloorMargin(String str, String str2, String str3, boolean z) {
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void refreshDistanceCalFloorParams(int i) {
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void refreshStepStrenActivityWidth() {
    }

    public StepsCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        this.h = 0;
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(getContext(), 1);
        this.j = healthColumnSystem;
        this.h = healthColumnSystem.f();
        dhC_(view);
        dhE_(view, context);
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public boolean updateLayout() {
        this.j.e(getContext());
        if (this.h == this.j.f()) {
            return false;
        }
        this.h = this.j.f();
        if (this.itemView == null) {
            return false;
        }
        dhC_(this.itemView);
        dhE_(this.itemView, this.itemView.getContext());
        return true;
    }

    private void dhC_(View view) {
        if (this.itemView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.removeAllViews();
            if (c()) {
                viewGroup.addView(View.inflate(view.getContext(), R.layout.home_item_layout_step_card_cd, null));
                return;
            }
            if (nsn.s()) {
                viewGroup.addView(View.inflate(view.getContext(), R.layout.home_item_layout_step_card_large_font_scale_2, null));
            } else if (nsn.r()) {
                viewGroup.addView(View.inflate(view.getContext(), R.layout.home_item_layout_step_card_large_font_scale, null));
            } else {
                viewGroup.addView(View.inflate(view.getContext(), R.layout.home_item_layout_step_card, null));
            }
        }
    }

    private boolean c() {
        return this.h >= 8;
    }

    private void dhE_(View view, Context context) {
        RelativeLayout relativeLayout;
        this.ac = (StepView) view.findViewById(R.id.stepView);
        this.ad = (HealthTextView) view.findViewById(R.id.stepsText);
        this.aa = (HealthTextView) view.findViewById(R.id.hw_health_steps);
        dhD_(view, context);
        this.al = (HealthTextView) view.findViewById(R.id.timeText);
        this.am = (HealthTextView) view.findViewById(R.id.timeTextRight);
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.ad.setTypeface(createFromAsset);
        this.al.setTypeface(createFromAsset);
        this.ad.setText("0");
        this.al.setText("0");
        e();
        a(context);
        this.am.setText(getContext().getString(R.string._2130837658_res_0x7f02009a));
        if (LanguageUtil.f(context)) {
            this.c.setTextSize(1, 15.0f);
            this.e.setTextSize(1, 10.0f);
            this.i.setTextSize(1, 15.0f);
            this.l.setTextSize(1, 10.0f);
            this.t.setTextSize(1, 15.0f);
            this.s.setTextSize(1, 10.0f);
        }
        if (LanguageUtil.at(context)) {
            this.g.setTextSize(1, 10.0f);
        }
        if (LanguageUtil.bk(context) && nsn.p()) {
            this.aa.setTextSize(1, 15.0f);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (CommonUtil.as() && CommonUtil.b() <= currentTimeMillis && currentTimeMillis <= CommonUtil.e()) {
            this.f9621a.setVisibility(0);
        } else {
            this.f9621a.setVisibility(8);
        }
        if (nsn.t() && nsn.ag(getContext()) && (relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout2)) != null) {
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            layoutParams.height = -2;
            relativeLayout.setLayoutParams(layoutParams);
        }
    }

    private void dhD_(View view, Context context) {
        if (c()) {
            this.ag = (HealthTextView) view.findViewById(R.id.targetStep);
            this.af = (HealthTextView) view.findViewById(R.id.targetTime);
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append("/");
            stringBuffer.append(UnitUtil.e(30.0d, 1, 0));
            this.af.setText(stringBuffer.toString());
            Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
            this.ag.setTypeface(createFromAsset);
            this.af.setTypeface(createFromAsset);
        }
    }

    private void a(Context context) {
        if (LanguageUtil.bc(context)) {
            this.u.setBackgroundResource(R.drawable._2131430097_res_0x7f0b0ad1);
            this.y.setBackgroundResource(R.drawable._2131430118_res_0x7f0b0ae6);
            this.v.setBackgroundResource(R.drawable._2131430100_res_0x7f0b0ad4);
            this.q.setBackgroundResource(R.drawable._2131430095_res_0x7f0b0acf);
            this.w.setBackgroundResource(R.drawable._2131430099_res_0x7f0b0ad3);
            this.f9621a.setBackgroundResource(R.drawable._2131429736_res_0x7f0b0968);
        }
        if (LanguageUtil.m(context)) {
            this.ae.setVisibility(0);
            this.u.setVisibility(8);
            this.ai.setVisibility(0);
            this.v.setVisibility(8);
            this.ah.setVisibility(0);
            this.q.setVisibility(8);
            this.g.setVisibility(0);
            this.w.setVisibility(8);
            this.y.setVisibility(8);
            this.x.setVisibility(0);
            return;
        }
        dhF_(this.itemView);
        this.ae.setVisibility(8);
        this.u.setVisibility(0);
        this.ai.setVisibility(8);
        this.v.setVisibility(0);
        this.ah.setVisibility(8);
        this.q.setVisibility(0);
        this.g.setVisibility(8);
        this.w.setVisibility(0);
        this.y.setVisibility(0);
        this.x.setVisibility(8);
    }

    private void e() {
        this.n = this.itemView.findViewById(R.id.calories_climb_devider);
        this.aj = (ViewGroup) this.itemView.findViewById(R.id.time_strength_layout);
        this.u = (ImageView) this.itemView.findViewById(R.id.iv_steps_icon);
        this.ae = (HealthTextView) this.itemView.findViewById(R.id.tv_steps);
        this.y = (ImageView) this.itemView.findViewById(R.id.sport_intensity_icon);
        HealthTextView healthTextView = (HealthTextView) this.itemView.findViewById(R.id.tv_sport_intensity);
        this.x = healthTextView;
        healthTextView.setText(getContext().getString(R.string._2130837803_res_0x7f02012b));
        this.f9621a = (RelativeLayout) this.itemView.findViewById(R.id.hw_health_home_dameon_beta_version_no_expire);
        this.r = (LinearLayout) this.itemView.findViewById(R.id.home_item_step_card_layout);
        d();
    }

    private void d() {
        this.ai = (HealthTextView) this.itemView.findViewById(R.id.tv_distance_title);
        this.ah = (HealthTextView) this.itemView.findViewById(R.id.tv_calorie_title);
        HealthTextView healthTextView = (HealthTextView) this.itemView.findViewById(R.id.climb_title);
        this.g = healthTextView;
        healthTextView.setText(getContext().getString(R.string.IDS_motiontrack_climb_stairs_tip));
        this.v = (ImageView) this.itemView.findViewById(R.id.sport_distance_icon);
        this.q = (ImageView) this.itemView.findViewById(R.id.sport_calorie_icon);
        this.w = (ImageView) this.itemView.findViewById(R.id.sport_climbed_icon);
        this.k = (ImageView) this.itemView.findViewById(R.id.hw_health_fitness_data_origin_icon);
        this.s = (HealthTextView) this.itemView.findViewById(R.id.unit_meter);
        this.e = (HealthTextView) this.itemView.findViewById(R.id.unit_calories);
        this.l = (HealthTextView) this.itemView.findViewById(R.id.unit_distance);
        this.o = this.itemView.findViewById(R.id.tv_calories_distance_devider);
        HealthTextView healthTextView2 = (HealthTextView) this.itemView.findViewById(R.id.hw_health_fitness_data_textview);
        this.m = healthTextView2;
        healthTextView2.setText(getContext().getString(R.string._2130837789_res_0x7f02011d));
        this.ab = (ViewGroup) this.itemView.findViewById(R.id.stepLayout);
        HealthTextView healthTextView3 = (HealthTextView) this.itemView.findViewById(R.id.calories);
        this.c = healthTextView3;
        healthTextView3.setText("0");
        HealthTextView healthTextView4 = (HealthTextView) this.itemView.findViewById(R.id.distance);
        this.i = healthTextView4;
        healthTextView4.setText("0");
        this.t = (HealthTextView) this.itemView.findViewById(R.id.floor);
        this.b = (LinearLayout) this.itemView.findViewById(R.id.hw_health_steps_card_calories);
        this.p = (LinearLayout) this.itemView.findViewById(R.id.hw_health_steps_card_floors);
        this.f = (LinearLayout) this.itemView.findViewById(R.id.hw_health_steps_card_distance);
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public HealthTextView getBubbleText() {
        return this.d;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public HealthTextView getStepsText() {
        return this.ad;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public StepView getStepView() {
        return this.ac;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public HealthTextView getTimeText() {
        return this.al;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public HealthTextView getFitnessDataTextView() {
        return this.m;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ImageView getFitnessDataOriginIcon() {
        return this.k;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public View getDivider() {
        return this.o;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public View getHomeStepCardLayout() {
        return this.r;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ViewGroup getTimeStrengthLayout() {
        return this.aj;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ViewGroup getStepLayout() {
        return this.ab;
    }

    private void dhF_(View view) {
        if (nsn.t() && c()) {
            dhG_(view);
            if (nsn.l()) {
                b();
            }
        }
    }

    private void dhG_(View view) {
        this.u = (ImageView) view.findViewById(R.id.iv_steps_icon_large);
        view.findViewById(R.id.sport_intensity_icon_large).setVisibility(8);
        this.y = (ImageView) view.findViewById(R.id.sport_intensity_icon_large);
        view.findViewById(R.id.sport_intensity_icon).setVisibility(8);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_health_steps_large);
        this.z = healthTextView;
        healthTextView.setVisibility(0);
        view.findViewById(R.id.hw_health_steps).setVisibility(8);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.timeTextRight_large);
        this.am = healthTextView2;
        healthTextView2.setVisibility(0);
        view.findViewById(R.id.timeTextRight).setVisibility(8);
    }

    private void b() {
        LogUtil.c("StepsCardViewHolder", "foldable current font scale: ", Float.valueOf(nsn.c()));
        int c = nsn.c(getContext(), 4.0f);
        dhH_(this.u, 0, 0, 0, c);
        dhH_(this.y, 0, 0, 0, c);
        dhH_(this.z, 0, c, 0, 0);
        dhH_(this.am, 0, c, 0, 0);
    }

    private void dhH_(View view, int i, int i2, int i3, int i4) {
        if (view == null) {
            LogUtil.c("StepsCardViewHolder", "imageView is null,can not set margin");
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof RelativeLayout.LayoutParams)) {
            LogUtil.c("StepsCardViewHolder", "parent layout params type is not match,return");
            return;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(layoutParams2.leftMargin + i, layoutParams2.topMargin + i2, layoutParams2.rightMargin + i3, layoutParams2.bottomMargin + i4);
        view.setLayoutParams(layoutParams2);
    }
}
