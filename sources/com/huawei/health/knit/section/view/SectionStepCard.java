package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.StepView;
import com.huawei.health.knit.section.listener.IUpdateLayoutCallback;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bdu;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionStepCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f2742a;
    private HealthTextView aa;
    private HealthTextView ab;
    private RelativeLayout ac;
    private HealthTextView ad;
    private HealthTextView ae;
    private ImageView af;
    private ImageView ag;
    private ImageView ah;
    private ImageView ai;
    private HealthTextView aj;
    private HealthTextView ak;
    private ImageView al;
    private StepView am;
    private ViewGroup an;
    private LinearLayout ao;
    private HealthTextView ap;
    private HealthTextView aq;
    private HealthTextView ar;
    private HealthTextView as;
    private HealthTextView at;
    private HealthTextView au;
    private HealthTextView av;
    private HealthTextView aw;
    private ViewGroup ax;
    private HealthTextView ay;
    private HealthTextView az;
    private HealthTextView b;
    private HealthTextView ba;
    private HealthTextView bb;
    private RelativeLayout bc;
    private IUpdateLayoutCallback c;
    private RelativeLayout d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthColumnSystem g;
    private HealthTextView h;
    private int i;
    private HealthTextView j;
    private HealthTextView k;
    private Context l;
    private RelativeLayout m;
    private HealthTextView n;
    private LinearLayout o;
    private View p;
    private View q;
    private HealthTextView r;
    private HealthTextView s;
    private ImageView t;
    private HealthTextView u;
    private HealthTextView v;
    private LinearLayout w;
    private LinearLayout x;
    private View y;
    private RelativeLayout z;

    public SectionStepCard(Context context) {
        super(context);
        this.i = 0;
    }

    public SectionStepCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 0;
    }

    public SectionStepCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 0;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.l = context;
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.l, 1);
        this.g = healthColumnSystem;
        this.i = healthColumnSystem.f();
        View inflate = LayoutInflater.from(this.l).inflate(R.layout.layout_step_card_container, (ViewGroup) this, false);
        this.y = inflate;
        ajK_(inflate);
        ajM_(this.y, this.l);
        return this.y;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionStepCard", "no need to bind");
            return;
        }
        LogUtil.a("SectionStepCard", "bindParamsToView, size: " + hashMap.size());
        setText(hashMap);
        setOnClickListener(hashMap);
        setVisible(hashMap);
        setOnSectionClickListener(hashMap);
        setLargeModeTextSize(hashMap);
        setTextAndVisible(hashMap);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        IUpdateLayoutCallback iUpdateLayoutCallback;
        super.onConfigurationChanged(configuration);
        if (!e() || (iUpdateLayoutCallback = this.c) == null) {
            return;
        }
        iUpdateLayoutCallback.onUpdateLayoutSuccess();
    }

    public boolean e() {
        this.g.e(this.l);
        if (this.i == this.g.f()) {
            return false;
        }
        this.i = this.g.f();
        View view = this.y;
        if (view == null) {
            return false;
        }
        ajK_(view);
        View view2 = this.y;
        ajM_(view2, view2.getContext());
        return true;
    }

    private void ajK_(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.removeAllViews();
            if (d()) {
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

    private boolean d() {
        return this.i >= 8;
    }

    private void ajM_(View view, Context context) {
        this.am = (StepView) view.findViewById(R.id.stepView);
        this.aj = (HealthTextView) view.findViewById(R.id.stepsText);
        if (d()) {
            this.aw = (HealthTextView) view.findViewById(R.id.targetStep);
            this.ar = (HealthTextView) view.findViewById(R.id.targetTime);
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append("/");
            stringBuffer.append(UnitUtil.e(30.0d, 1, 0));
            this.ar.setText(stringBuffer.toString());
            Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
            this.aw.setTypeface(createFromAsset);
            this.ar.setTypeface(createFromAsset);
        }
        this.av = (HealthTextView) view.findViewById(R.id.timeText);
        this.ay = (HealthTextView) view.findViewById(R.id.timeTextRight);
        Typeface createFromAsset2 = Typeface.createFromAsset(context.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.aj.setTypeface(createFromAsset2);
        this.av.setTypeface(createFromAsset2);
        this.aj.setText("0");
        this.av.setText("0");
        ajL_(view);
        a(context);
        if (LanguageUtil.f(context)) {
            this.e.setTextSize(1, 15.0f);
            this.b.setTextSize(1, 10.0f);
            this.k.setTextSize(1, 15.0f);
            this.n.setTextSize(1, 10.0f);
            this.s.setTextSize(1, 15.0f);
            this.aa.setTextSize(1, 10.0f);
        }
        if (LanguageUtil.at(context)) {
            this.f.setTextSize(1, 10.0f);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (CommonUtil.as() && CommonUtil.b() <= currentTimeMillis && currentTimeMillis <= CommonUtil.e()) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    private void ajL_(View view) {
        this.p = view.findViewById(R.id.calories_climb_devider);
        this.ax = (ViewGroup) view.findViewById(R.id.time_strength_layout);
        this.al = (ImageView) view.findViewById(R.id.iv_steps_icon);
        this.ak = (HealthTextView) view.findViewById(R.id.tv_steps);
        this.ag = (ImageView) view.findViewById(R.id.sport_intensity_icon);
        this.ae = (HealthTextView) view.findViewById(R.id.tv_sport_intensity);
        this.d = (RelativeLayout) view.findViewById(R.id.hw_health_home_dameon_beta_version_no_expire);
        this.w = (LinearLayout) view.findViewById(R.id.home_item_step_card_layout);
        this.au = (HealthTextView) view.findViewById(R.id.tv_distance_title);
        this.at = (HealthTextView) view.findViewById(R.id.tv_calorie_title);
        this.f = (HealthTextView) view.findViewById(R.id.climb_title);
        this.ah = (ImageView) view.findViewById(R.id.sport_distance_icon);
        this.af = (ImageView) view.findViewById(R.id.sport_calorie_icon);
        this.ai = (ImageView) view.findViewById(R.id.sport_climbed_icon);
        this.t = (ImageView) view.findViewById(R.id.hw_health_fitness_data_origin_icon);
        this.aa = (HealthTextView) view.findViewById(R.id.unit_meter);
        this.b = (HealthTextView) view.findViewById(R.id.unit_calories);
        this.n = (HealthTextView) view.findViewById(R.id.unit_distance);
        this.q = view.findViewById(R.id.tv_calories_distance_devider);
        this.r = (HealthTextView) view.findViewById(R.id.hw_health_fitness_data_textview);
        this.an = (ViewGroup) view.findViewById(R.id.stepLayout);
        this.ap = (HealthTextView) view.findViewById(R.id.notice_message11);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.calories);
        this.e = healthTextView;
        healthTextView.setText("0");
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.distance);
        this.k = healthTextView2;
        healthTextView2.setText("0");
        this.s = (HealthTextView) view.findViewById(R.id.floor);
        this.f2742a = (LinearLayout) view.findViewById(R.id.hw_health_steps_card_calories);
        this.x = (LinearLayout) view.findViewById(R.id.hw_health_steps_card_floors);
        this.o = (LinearLayout) view.findViewById(R.id.hw_health_steps_card_distance);
        this.m = (RelativeLayout) view.findViewById(R.id.hw_health_home_dameon_killed_layout);
        this.z = (RelativeLayout) view.findViewById(R.id.rl_oppo_vivo_help);
        this.j = (HealthTextView) this.m.findViewById(R.id.img_show_tips_close);
        this.u = (HealthTextView) this.z.findViewById(R.id.tv_go_to);
        this.v = (HealthTextView) this.z.findViewById(R.id.tv_ignore);
        this.ac = (RelativeLayout) view.findViewById(R.id.rl_nps);
        this.bb = (HealthTextView) view.findViewById(R.id.tv_title);
        this.h = (HealthTextView) view.findViewById(R.id.tv_content);
        this.ad = (HealthTextView) view.findViewById(R.id.tv_join);
        this.ab = (HealthTextView) view.findViewById(R.id.tv_not_join);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.sync_cloud_data_fail_layout);
        this.ao = linearLayout;
        this.aq = (HealthTextView) linearLayout.findViewById(R.id.sync_ignore_tv);
        this.as = (HealthTextView) this.ao.findViewById(R.id.sync_try_again_tv);
        this.bc = (RelativeLayout) view.findViewById(R.id.layout_wechat_bind);
        this.az = (HealthTextView) view.findViewById(R.id.tv_wechat_join);
        this.ba = (HealthTextView) view.findViewById(R.id.tv_wechat_ignore);
    }

    private void a(Context context) {
        if (LanguageUtil.bc(context)) {
            this.al.setBackgroundResource(R.drawable._2131430097_res_0x7f0b0ad1);
            this.ag.setBackgroundResource(R.drawable._2131430118_res_0x7f0b0ae6);
            this.ah.setBackgroundResource(R.drawable._2131430100_res_0x7f0b0ad4);
            this.af.setBackgroundResource(R.drawable._2131430095_res_0x7f0b0acf);
            this.ai.setBackgroundResource(R.drawable._2131430099_res_0x7f0b0ad3);
            this.d.setBackgroundResource(R.drawable._2131429736_res_0x7f0b0968);
        }
        if (LanguageUtil.m(context)) {
            this.ak.setVisibility(0);
            this.al.setVisibility(8);
            this.au.setVisibility(0);
            this.ah.setVisibility(8);
            this.at.setVisibility(0);
            this.af.setVisibility(8);
            this.f.setVisibility(0);
            this.ai.setVisibility(8);
            this.ag.setVisibility(8);
            this.ae.setVisibility(0);
            return;
        }
        this.ak.setVisibility(8);
        this.al.setVisibility(0);
        this.au.setVisibility(8);
        this.ah.setVisibility(0);
        this.at.setVisibility(8);
        this.af.setVisibility(0);
        this.f.setVisibility(8);
        this.ai.setVisibility(0);
        this.ag.setVisibility(0);
        this.ae.setVisibility(8);
    }

    private float getDefaultValue() {
        if (nsn.r()) {
            return this.l.getResources().getDimension(R.dimen._2131364590_res_0x7f0a0aee);
        }
        return this.l.getResources().getDimension(R.dimen._2131365074_res_0x7f0a0cd2);
    }

    private void setText(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.h, nru.b(hashMap, "TV_CONTENT_TEXT_VIEW", ""));
        nsy.cMr_(this.ad, nru.b(hashMap, "TV_JOIN_TEXT_VIEW", ""));
        nsy.cMr_(this.ab, nru.b(hashMap, "TV_NOT_JOIN_TEXT_VIEW", ""));
        nsy.cMr_(this.r, nru.b(hashMap, "HW_HEALTH_FITNESS_DATA_TEXT", ""));
        nsy.cMr_(this.ae, nru.b(hashMap, "TV_SPORT_INTENSITY", ""));
        nsy.cMr_(this.ay, nru.b(hashMap, "TIME_TEXT_RIGHT", ""));
        nsy.cMr_(this.f, nru.b(hashMap, "CLIMB_TITLE", ""));
        nsy.cMr_(this.as, nru.b(hashMap, "SYNC_TRY_AGAIN_TV", ""));
        nsy.cMr_(this.as, nru.b(hashMap, "SYNC_TRY_AGAIN_TV", ""));
        nsy.cMr_(this.aa, nru.b(hashMap, "METER_TEXT_UNIT", this.l.getString(R$string.IDS_fitness_data_list_activity_meter_unit)));
        nsy.cMr_(this.s, nru.b(hashMap, "FLOOR_TEXT", ""));
        nsy.cMA_(this.aa, nru.d((Map) hashMap, "METER_TEXT_UNIT_VISIBILITY", 0));
        nsy.cMr_(this.n, nru.b(hashMap, "DISTANCE_UNIT_TEXT", this.l.getString(R$string.IDS_band_data_sport_distance_unit)));
        this.c = (IUpdateLayoutCallback) nru.c(hashMap, "UPDATE_CALLBACK", IUpdateLayoutCallback.class, null);
    }

    private void setOnClickListener(HashMap<String, Object> hashMap) {
        StepView stepView;
        nsy.cMn_(this.an, bdu.e(hashMap, "STEP_CLICK_LISTENER", null));
        nsy.cMn_(this.o, bdu.e(hashMap, "STEP_CLICK_LISTENER", null));
        nsy.cMn_(this.f2742a, bdu.e(hashMap, "CALORIE_CLICK_LISTENER", null));
        nsy.cMn_(this.x, bdu.e(hashMap, "FLOOR_CLICK_LISTENER", null));
        nsy.cMn_(this.ax, bdu.e(hashMap, "STRENGTH_TIME_CLICK_LISTENER", null));
        nsy.cMn_(this.t, nru.a(hashMap, "BASE_CLICK_LISTENER", null));
        nsy.cMn_(this.r, nru.a(hashMap, "BASE_CLICK_LISTENER", null));
        nsy.cMn_(this.m, nru.a(hashMap, "BASE_CLICK_LISTENER", null));
        nsy.cMn_(this.j, nru.a(hashMap, "BASE_CLICK_LISTENER", null));
        StepView.OnStepViewClickListener onStepViewClickListener = (StepView.OnStepViewClickListener) nru.c(hashMap, "STEP_CIRCLE_CLICK_LISTENER", StepView.OnStepViewClickListener.class, null);
        if (onStepViewClickListener == null || (stepView = this.am) == null) {
            return;
        }
        stepView.setOnStepViewListener(onStepViewClickListener);
    }

    private void setVisible(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.aj, nru.b(hashMap, "STEPS_TEXT", ""));
        nsy.cMr_(this.av, nru.b(hashMap, "TIME_TEXT", ""));
        nsy.cMA_(this.q, nru.d((Map) hashMap, "DIVIDER_VISIBILITY", 0));
        nsy.cMA_(this.p, nru.d((Map) hashMap, "DIVIDER_RIGHT_VISIBILITY", 8));
        nsy.cMA_(this.x, nru.d((Map) hashMap, "FLOORS_LINEAR_LAYOUT_VISIBILITY", 8));
        this.f2742a.setGravity(nru.d((Map) hashMap, "CALORIES_LINEAR_LAYOUT_GRAVITY", 16));
        this.am.setRightProgress(nru.e(hashMap, "STEP_VIEW_RIGHT_PROGRESS", 0.0f));
        this.am.setLeftProgress(nru.e(hashMap, "STEP_VIEW_LEFT_PROGRESS", 0.0f));
        nsy.cMA_(this.ao, nru.d((Map) hashMap, "SYNC_CLOUD_DATA_FAIL_LAYOUT_VISIBILITY", 8));
        nsy.cMA_(this.m, nru.d((Map) hashMap, "DAEMON_LAYOUT_VISIBILITY", 8));
        nsy.cMA_(this.z, nru.d((Map) hashMap, "OPPO_OR_VIVO_HELP_LAYOUT_VISIBILITY", 8));
        nsy.cMA_(this.bc, nru.d((Map) hashMap, "WECHAT_RELATIVE_LAYOUT_VISIBILITY", 8));
    }

    private void setOnSectionClickListener(HashMap<String, Object> hashMap) {
        nsy.cMo_(this.u, nru.a(hashMap, "TEXT_VIEW_CLICK_EVENT", null), "GO_TO_TEXT");
        nsy.cMo_(this.v, nru.a(hashMap, "TEXT_VIEW_CLICK_EVENT", null), "IGNORE_TEXT_VIEW");
        nsy.cMr_(this.ap, nru.b(hashMap, "SYNC_DATA_FAILED_TEXT", ""));
        nsy.cMo_(this.ap, nru.a(hashMap, "TEXT_VIEW_CLICK_EVENT", null), "SYNC_DATA_FAILED_CLICK_LISTENER");
        nsy.cMj_(this.t, nru.cKj_(hashMap, "FITNESS_DATA_ORIGIN_ICON_BACKGROUND", this.l.getDrawable(R.drawable._2131430119_res_0x7f0b0ae7)));
        nsy.cMo_(this.az, nru.a(hashMap, "WECHAT_CLICK_LISTENER", null), "WECHAT_JOIN_CLICK_LISTENER");
        nsy.cMo_(this.ba, nru.a(hashMap, "WECHAT_CLICK_LISTENER", null), "WECHAT_IGNORE_CLICK_LISTENER");
        nsy.cMo_(this.aq, nru.a(hashMap, "SYNC_CLICK_LISTENER", null), "SYNC_IGNORE_CLICK_LISTENER");
        nsy.cMo_(this.as, nru.a(hashMap, "SYNC_CLICK_LISTENER", null), "SYNC_TRY_AGAIN_CLICK_LISTENER");
    }

    private void setLargeModeTextSize(HashMap<String, Object> hashMap) {
        if (nsn.s()) {
            nsy.cMw_(this.k, nru.e(hashMap, "DISTANCE_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131362906_res_0x7f0a045a)));
            nsy.cMw_(this.s, nru.e(hashMap, "FLOOR_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131362906_res_0x7f0a045a)));
            nsy.cMw_(this.e, nru.e(hashMap, "CALORIES_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131362906_res_0x7f0a045a)));
        } else if (nsn.r()) {
            nsy.cMw_(this.k, nru.e(hashMap, "DISTANCE_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131362869_res_0x7f0a0435)));
            nsy.cMw_(this.s, nru.e(hashMap, "FLOOR_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131362869_res_0x7f0a0435)));
            nsy.cMw_(this.e, nru.e(hashMap, "CALORIES_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131362869_res_0x7f0a0435)));
        } else {
            nsy.cMw_(this.k, nru.e(hashMap, "DISTANCE_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131365062_res_0x7f0a0cc6)));
            nsy.cMw_(this.s, nru.e(hashMap, "FLOOR_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131365062_res_0x7f0a0cc6)));
            nsy.cMw_(this.e, nru.e(hashMap, "CALORIES_TEXT_SIZE", this.l.getResources().getDimension(R.dimen._2131365062_res_0x7f0a0cc6)));
        }
    }

    private void setTextAndVisible(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.k, nru.b(hashMap, "DISTANCE_TEXT", ""));
        nsy.cMA_(this.ac, nru.d((Map) hashMap, "NPS_LAYOUT_VISIBILITY", 8));
        nsy.cMr_(this.bb, nru.b(hashMap, "TITLE_TEXT_VIEW", ""));
        nsy.cMo_(this.ad, nru.a(hashMap, "JOIN_TEXT_VIEW_CLICK_LISTENER", null), "JOIN_TEXT_VIEW_CLICK_LISTENER");
        nsy.cMo_(this.ab, nru.a(hashMap, "JOIN_TEXT_VIEW_CLICK_LISTENER", null), "NOT_JOIN_TEXT_VIEW_CLICK_LISTENER");
        nsy.cMr_(this.e, nru.b(hashMap, "CALORIES_TEXT", ""));
        nsy.cMA_(this.t, nru.d((Map) hashMap, "FITNESS_DATA_ORIGIN_ICON_VISIBILITY", 8));
        nsy.cMr_(this.aw, nru.b(hashMap, "TARGET_STEP_TEXT", ""));
        nsy.cMw_(this.aj, nru.e(hashMap, "STEPS_TEXT_SIZE", getDefaultValue()));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionStepCard";
    }
}
