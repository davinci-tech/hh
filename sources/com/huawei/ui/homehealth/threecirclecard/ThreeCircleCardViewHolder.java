package com.huawei.ui.homehealth.threecirclecard;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.StepView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.threeCircle.ThreeCircleView;
import com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder;
import defpackage.jcf;
import defpackage.nmj;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsk;
import defpackage.nsn;
import defpackage.nsw;
import defpackage.owc;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class ThreeCircleCardViewHolder extends StepsBaseCardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    protected LinearLayout f9626a;
    protected HealthTextView aa;
    private RelativeLayout ab;
    protected LinearLayout ac;
    private HealthTextView ad;
    private HealthTextView ae;
    private LinearLayout af;
    private RelativeLayout ag;
    private HealthTextView ah;
    private ThreeCircleView ai;
    private LinearLayout aj;
    private float ak;
    private int al;
    private HealthColumnSystem am;
    private RelativeLayout an;
    private RelativeLayout ao;
    private LinearLayout ap;
    private HealthTextView aq;
    private Map<Integer, Integer> ar;
    private FrameLayout as;
    private LinearLayout at;
    private ImageView au;
    private ImageView av;
    private RelativeLayout aw;
    private HealthTextView ax;
    protected ImageView b;
    protected final String c;
    protected LinearLayout d;
    protected LinearLayout e;
    protected HealthTextView f;
    protected ImageView g;
    protected HealthTextView h;
    protected ImageView i;
    protected LinearLayout j;
    protected owc k;
    protected LinearLayout l;
    protected HealthTextView m;
    protected ImageView n;
    protected HealthCardView o;
    protected LinearLayout p;
    protected ImageView q;
    protected HealthTextView r;
    protected HealthTextView s;
    protected HealthTextView t;
    protected HealthTextView u;
    protected ImageView v;
    protected LinearLayout w;
    protected LinearLayout x;
    protected HealthTextView y;
    protected LinearLayout z;

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public View getDivider() {
        return null;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public HealthTextView getFitnessDataTextView() {
        return null;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public StepView getStepView() {
        return null;
    }

    public void i() {
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void refreshDistanceCalFloorMargin(String str, String str2, String str3, boolean z) {
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void refreshStepStrenActivityWidth() {
    }

    public ThreeCircleCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        this.c = d();
        this.k = new owc();
        this.ak = 0.0f;
        this.al = 0;
        this.ar = new HashMap(10);
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(getContext(), 1);
        this.am = healthColumnSystem;
        this.al = healthColumnSystem.f();
        dia_(view);
        dib_(view, context);
        if (p()) {
            q();
        }
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public boolean updateLayout() {
        LogUtil.a(this.c, "updateLayout");
        this.am.e(getContext());
        if (this.al != this.am.f()) {
            this.al = this.am.f();
            if (p()) {
                return q();
            }
            LogUtil.a(this.c, "close to single screen");
            ViewGroup.LayoutParams layoutParams = this.af.getLayoutParams();
            if (!(layoutParams instanceof RelativeLayout.LayoutParams)) {
                return false;
            }
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(0, 0, 0, 0);
            this.af.setLayoutParams(layoutParams2);
            this.af.setOrientation(1);
            this.af.setGravity(1);
            ViewGroup.LayoutParams layoutParams3 = this.o.getLayoutParams();
            if (!(layoutParams3 instanceof LinearLayout.LayoutParams)) {
                return false;
            }
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
            layoutParams4.setMargins(0, nrr.e(getContext(), 8.0f), 0, 0);
            this.o.setLayoutParams(layoutParams4);
            ViewGroup.LayoutParams layoutParams5 = this.ac.getLayoutParams();
            if (!(layoutParams5 instanceof LinearLayout.LayoutParams)) {
                return false;
            }
            LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) layoutParams5;
            layoutParams6.width = -1;
            layoutParams6.height = -2;
            layoutParams6.gravity = 1;
            layoutParams6.setMargins(0, 0, 0, nrr.e(getContext(), -5.0f));
            this.ac.setLayoutParams(layoutParams6);
            ViewGroup.LayoutParams layoutParams7 = this.aj.getLayoutParams();
            if (!(layoutParams7 instanceof LinearLayout.LayoutParams)) {
                return false;
            }
            LinearLayout.LayoutParams layoutParams8 = (LinearLayout.LayoutParams) layoutParams7;
            layoutParams8.width = -1;
            layoutParams8.height = -2;
            layoutParams8.gravity = 1;
            layoutParams8.setMargins(0, 0, 0, 0);
            this.aj.setLayoutParams(layoutParams8);
            this.aj.setOrientation(1);
            this.aj.setGravity(1);
        }
        return true;
    }

    private boolean q() {
        LogUtil.a(this.c, "open to wide screen");
        ViewGroup.LayoutParams layoutParams = this.af.getLayoutParams();
        if (!(layoutParams instanceof RelativeLayout.LayoutParams)) {
            return false;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(0, nrr.e(getContext(), 12.0f), 0, 0);
        this.af.setLayoutParams(layoutParams2);
        this.af.setOrientation(0);
        this.af.setGravity(16);
        ViewGroup.LayoutParams layoutParams3 = this.o.getLayoutParams();
        if (!(layoutParams3 instanceof LinearLayout.LayoutParams)) {
            return false;
        }
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
        layoutParams4.setMargins(0, nrr.e(getContext(), 16.0f), 0, 0);
        this.o.setLayoutParams(layoutParams4);
        ViewGroup.LayoutParams layoutParams5 = this.ac.getLayoutParams();
        if (!(layoutParams5 instanceof LinearLayout.LayoutParams)) {
            return false;
        }
        LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) layoutParams5;
        layoutParams6.width = -2;
        layoutParams6.height = -2;
        layoutParams6.gravity = 16;
        layoutParams6.setMargins(nrr.e(getContext(), 32.0f), 0, nrr.e(getContext(), 24.0f), 0);
        this.ac.setLayoutParams(layoutParams6);
        ViewGroup.LayoutParams layoutParams7 = this.aj.getLayoutParams();
        if (!(layoutParams7 instanceof LinearLayout.LayoutParams)) {
            return false;
        }
        LinearLayout.LayoutParams layoutParams8 = (LinearLayout.LayoutParams) layoutParams7;
        layoutParams8.width = -1;
        layoutParams8.height = -2;
        layoutParams8.gravity = 16;
        layoutParams8.setMargins(0, 0, nrr.e(getContext(), 48.0f), 0);
        this.aj.setLayoutParams(layoutParams8);
        this.aj.setOrientation(0);
        this.aj.setGravity(16);
        return true;
    }

    private void dia_(View view) {
        if (this.itemView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.removeAllViews();
            final View inflate = LayoutInflater.from(getContext()).inflate(nsw.cLT_(R.layout.home_item_layout_three_circle), (ViewGroup) null);
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder.3
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (inflate.getMeasuredWidth() <= 0) {
                        return;
                    }
                    ObserverManagerUtil.c("threeLeafCircleLayoutFlag", ThreeCircleCardViewHolder.this.getContext());
                    inflate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            viewGroup.addView(inflate);
        }
    }

    private boolean s() {
        return (!LanguageUtil.m(getContext()) || nsn.t() || k() || o()) ? false : true;
    }

    private boolean p() {
        return this.al >= 8;
    }

    private void dib_(View view, Context context) {
        g();
        r();
        b(context);
        a();
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.t.setTypeface(createFromAsset);
        this.ax.setTypeface(createFromAsset);
        this.ad.setTypeface(createFromAsset);
        this.aq.setTypeface(createFromAsset);
        this.t.setText(c(getContext(), 0, 1, 0));
        this.ax.setText(c(getContext(), 0, 1, 0));
        this.ad.setText(c(getContext(), 0, 1, 0));
        this.aq.setText(c(getContext(), 0, 1, 0));
        long currentTimeMillis = System.currentTimeMillis();
        if (CommonUtil.as() && CommonUtil.b() <= currentTimeMillis && currentTimeMillis <= CommonUtil.e()) {
            this.ag.setVisibility(0);
        } else {
            this.ag.setVisibility(8);
        }
    }

    private static String c(Context context, int i, int i2, int i3) {
        if (LanguageUtil.m(context)) {
            return String.valueOf(i);
        }
        return UnitUtil.e(i, i2, i3);
    }

    private void g() {
        this.as = (FrameLayout) this.itemView.findViewById(R.id.home_item_three_circle_card_layout);
        this.ao = (RelativeLayout) this.itemView.findViewById(R.id.three_circle_card_layout);
        this.ac = (LinearLayout) this.itemView.findViewById(R.id.three_circle_layout_container);
        this.an = (RelativeLayout) this.itemView.findViewById(R.id.stepLayout);
        this.q = (ImageView) this.itemView.findViewById(R.id.iv_steps_icon);
        this.r = (HealthTextView) this.itemView.findViewById(R.id.tv_steps);
        if (LanguageUtil.bj(getContext()) || LanguageUtil.ar(getContext())) {
            this.r.setMaxLines(1);
            this.r.setTextSize(2, 10.0f);
        }
        this.r.setText(getContext().getString(R.string.IDS_active_caloric));
        this.t = (HealthTextView) this.itemView.findViewById(R.id.stepsText);
        HealthTextView healthTextView = (HealthTextView) this.itemView.findViewById(R.id.hw_health_steps);
        this.s = healthTextView;
        healthTextView.setText(getContext().getString(R.string._2130837658_res_0x7f02009a));
        this.p = (LinearLayout) this.itemView.findViewById(R.id.data_layout_step_layout);
        this.w = (LinearLayout) this.itemView.findViewById(R.id.data_layout_sleep_array);
        this.u = (HealthTextView) this.itemView.findViewById(R.id.message_count_new);
        b();
        dip_(this.itemView);
        ImageView imageView = (ImageView) this.itemView.findViewById(R.id.hw_health_fitness_data_origin_icon2);
        this.n = imageView;
        jcf.bEz_(imageView, nsf.h(R.string._2130845073_res_0x7f021d91));
        this.l = (LinearLayout) this.itemView.findViewById(R.id.hw_health_fitness_data_origin_icon2_layout);
        this.au = (ImageView) this.itemView.findViewById(R.id.three_circle_icon_background);
        this.at = (LinearLayout) this.itemView.findViewById(R.id.switch_button_area);
        if (LanguageUtil.bc(getContext())) {
            this.au.setBackground(nrz.cKn_(getContext(), R.drawable.three_leaf_background));
        } else {
            this.au.setBackgroundResource(R.drawable.three_leaf_background);
        }
        jcf.bEz_(this.au, nsf.h(R.string.IDS_health_clover_title));
        this.av = (ImageView) this.itemView.findViewById(R.id.three_circle_icon);
        this.ah = (HealthTextView) this.itemView.findViewById(R.id.switch_bubble_text);
        this.aw = (RelativeLayout) this.itemView.findViewById(R.id.time_strength_layout);
        this.v = (ImageView) this.itemView.findViewById(R.id.sport_intensity_icon);
        HealthTextView healthTextView2 = (HealthTextView) this.itemView.findViewById(R.id.tv_sport_intensity);
        this.y = healthTextView2;
        healthTextView2.setText(getContext().getString(R.string.IDS_active_workout));
        this.ax = (HealthTextView) this.itemView.findViewById(R.id.timeText);
        HealthTextView healthTextView3 = (HealthTextView) this.itemView.findViewById(R.id.timeTextRight);
        this.aa = healthTextView3;
        healthTextView3.setText(getContext().getString(R.string._2130841518_res_0x7f020fae));
        LinearLayout linearLayout = (LinearLayout) this.itemView.findViewById(R.id.step_strength_activity_layout);
        this.ap = linearLayout;
        dio_(linearLayout);
        this.ab = (RelativeLayout) this.itemView.findViewById(R.id.activityLayout);
        this.ad = (HealthTextView) this.itemView.findViewById(R.id.activity_hours_value);
        this.ae = (HealthTextView) this.itemView.findViewById(R.id.activity_hours_unit);
        this.b = (ImageView) this.itemView.findViewById(R.id.activity_icon);
        this.aq = (HealthTextView) this.itemView.findViewById(R.id.pressure_value);
        this.d = (LinearLayout) this.itemView.findViewById(R.id.data_layout_activity);
        din_(this.d, (LinearLayout) this.itemView.findViewById(R.id.data_layout_emotion_pressure), (LinearLayout) this.itemView.findViewById(R.id.data_layout_emotion_breath_count), (LinearLayout) this.itemView.findViewById(R.id.data_layout_emotion_breath_array));
        n();
        l();
        m();
    }

    protected void dip_(View view) {
        View findViewById;
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.three_circle_layout);
        if (viewStub.getParent() != null) {
            findViewById = viewStub.inflate();
        } else {
            findViewById = view.findViewById(R.id.three_circle_layout_inflated);
        }
        if (findViewById instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) findViewById;
            frameLayout.setVisibility(0);
            this.ai = (ThreeCircleView) frameLayout.findViewById(R.id.circleProgressBar);
        }
    }

    private void n() {
        this.o = (HealthCardView) this.itemView.findViewById(R.id.distance_heat_floor_layout_for_chinese);
        LinearLayout linearLayout = (LinearLayout) this.itemView.findViewById(R.id.bottom_step_layout);
        this.e = linearLayout;
        this.g = (ImageView) linearLayout.findViewById(R.id.bottom_step_icon);
        this.h = (HealthTextView) this.e.findViewById(R.id.bottom_step_text);
        this.f = (HealthTextView) this.e.findViewById(R.id.bottom_step_value);
        this.i = (ImageView) this.itemView.findViewById(R.id.bottom_step_tip_icon);
        this.j = (LinearLayout) this.itemView.findViewById(R.id.bottom_step_tip_layout);
        if (nrt.a(getContext())) {
            this.o.setCardBackgroundColor(getContext().getColor(R.color._2131296997_res_0x7f0902e5));
            this.k.c(R.color._2131296997_res_0x7f0902e5);
        } else {
            this.o.setCardBackgroundColor(getContext().getColor(R.color._2131296920_res_0x7f090298));
            this.k.c(R.color._2131296920_res_0x7f090298);
        }
        if (!LanguageUtil.ak(BaseApplication.getContext())) {
            ViewGroup.LayoutParams layoutParams = this.h.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).setMarginEnd(getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516));
            }
        }
        this.f9626a = (LinearLayout) this.itemView.findViewById(R.id.bottom_step_layout_2);
    }

    private void r() {
        LinearLayout linearLayout = (LinearLayout) this.itemView.findViewById(R.id.stepLayout_middle);
        this.x = linearLayout;
        dij_(linearLayout, this.s, false);
        LinearLayout linearLayout2 = (LinearLayout) this.itemView.findViewById(R.id.time_strength_layout_middle);
        this.z = linearLayout2;
        dij_(linearLayout2, this.aa, false);
        h();
        t();
    }

    private void t() {
        refreshDistanceCalFloorParams(0);
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void refreshDistanceCalFloorParams(int i) {
        if (s()) {
            return;
        }
        if (i < 0 || i > 3) {
            LogUtil.b(this.c, "lines is error");
        }
    }

    private boolean o() {
        return this.am.f() == 8 && ((int) (((float) nsn.n()) / nsn.g(getContext()))) < 500;
    }

    private boolean k() {
        float f = getContext().getResources().getDisplayMetrics().density;
        float f2 = f();
        return f2 >= 0.01f && f - f2 > 0.01f;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float f() {
        /*
            r8 = this;
            float r0 = r8.ak
            r1 = 1008981770(0x3c23d70a, float:0.01)
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 <= 0) goto La
            return r0
        La:
            r0 = 1
            r1 = 0
            r2 = 0
            java.lang.String r3 = "android.view.WindowManagerGlobal"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6b
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6b
            java.lang.String r5 = "getWindowManagerService"
            java.lang.reflect.Method r4 = r3.getMethod(r5, r4)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6b
            r4.setAccessible(r0)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Object r3 = r4.invoke(r3, r5)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Class r5 = r3.getClass()     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Class[] r6 = new java.lang.Class[r0]     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            r6[r2] = r7     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.String r7 = "getInitialDisplayDensity"
            java.lang.reflect.Method r1 = r5.getMethod(r7, r6)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            r1.setAccessible(r0)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            r5[r2] = r6     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Object r3 = r1.invoke(r3, r5)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.String r5 = r8.c     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.String r7 = "densityDpi: "
            r6[r2] = r7     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            r6[r0] = r3     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            com.huawei.hwlogsmodel.LogUtil.a(r5, r6)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            int r3 = r3.intValue()     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            float r3 = (float) r3     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            r5 = 1126170624(0x43200000, float:160.0)
            float r3 = r3 / r5
            r8.ak = r3     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L83
            if (r1 == 0) goto L62
            r1.setAccessible(r2)
        L62:
            if (r4 == 0) goto L67
            r4.setAccessible(r2)
        L67:
            return r3
        L68:
            r0 = move-exception
            r4 = r1
            goto L84
        L6b:
            r4 = r1
        L6c:
            java.lang.String r3 = r8.c     // Catch: java.lang.Throwable -> L83
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L83
            java.lang.String r5 = "getDefaultDisplayDensity failed"
            r0[r2] = r5     // Catch: java.lang.Throwable -> L83
            com.huawei.hwlogsmodel.LogUtil.b(r3, r0)     // Catch: java.lang.Throwable -> L83
            if (r1 == 0) goto L7c
            r1.setAccessible(r2)
        L7c:
            if (r4 == 0) goto L81
            r4.setAccessible(r2)
        L81:
            r0 = 0
            return r0
        L83:
            r0 = move-exception
        L84:
            if (r1 == 0) goto L89
            r1.setAccessible(r2)
        L89:
            if (r4 == 0) goto L8e
            r4.setAccessible(r2)
        L8e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder.f():float");
    }

    protected void dij_(LinearLayout linearLayout, HealthTextView healthTextView, boolean z) {
        if (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            if (nsn.p() || !LanguageUtil.h(getContext()) || z) {
                linearLayout.setOrientation(1);
                this.p.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
                layoutParams.setMargins(0, nrr.e(getContext(), 2.0f), 0, 0);
                layoutParams.setMarginStart(0);
                healthTextView.setLayoutParams(layoutParams);
                healthTextView.setGravity(GravityCompat.START);
                return;
            }
            linearLayout.setOrientation(0);
            this.p.setOrientation(0);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
            layoutParams2.setMargins(nrr.e(getContext(), 2.0f), 0, 0, nrr.e(getContext(), 2.0f));
            healthTextView.setLayoutParams(layoutParams2);
            healthTextView.setGravity(80);
        }
    }

    protected void h() {
        dij_(this.d, this.ae, false);
    }

    private void m() {
        this.af = (LinearLayout) this.itemView.findViewById(R.id.model_and_data);
        this.aj = (LinearLayout) this.itemView.findViewById(R.id.data_layout);
    }

    protected void dio_(LinearLayout linearLayout) {
        linearLayout.setVisibility(0);
    }

    protected void din_(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4) {
        linearLayout.setVisibility(0);
        linearLayout2.setVisibility(8);
        linearLayout3.setVisibility(8);
        linearLayout4.setVisibility(8);
    }

    protected void b() {
        this.p.setVisibility(0);
        this.w.setVisibility(8);
    }

    private void l() {
        this.ag = (RelativeLayout) this.itemView.findViewById(R.id.hw_health_home_dameon_beta_version_no_expire);
    }

    private void b(Context context) {
        if (LanguageUtil.bc(context)) {
            this.ag.setBackgroundResource(R.drawable._2131429736_res_0x7f0b0968);
        }
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ImageView getThreeLeafIcon() {
        return this.av;
    }

    public ImageView die_() {
        return this.au;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public LinearLayout getTwoModelSwitchArea() {
        return this.at;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public HealthTextView getStepsText() {
        return this.t;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public HealthTextView getBubbleText() {
        return this.ah;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public HealthTextView getTimeText() {
        return this.ax;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setFitnessDataOriginIcon2Visible(int i) {
        this.i.setVisibility(i);
        dic_(this.e);
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ImageView getFitnessDataOriginIcon() {
        return this.i;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public LinearLayout getFitnessDataOriginIconLayout() {
        return this.j;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ViewGroup getTimeStrengthLayout() {
        return this.aw;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ViewGroup getStepLayout() {
        return this.an;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public View getHomeStepCardLayout() {
        return this.as;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public RelativeLayout getActivityLayout() {
        return this.ab;
    }

    public RelativeLayout did_() {
        return this.ao;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public LinearLayout getThreeCycleLayoutContainer() {
        return this.ac;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setTextByPosition(int i, int i2, int i3) {
        if (i == 0) {
            a(i, this.t, this.s, i2, i3, R.plurals._2130903380_res_0x7f030154);
        } else if (i == 1) {
            a(i, this.ax, this.aa, i2, i3, R.plurals.IDS_single_circle_intensity_target_desc);
        } else {
            if (i != 2) {
                return;
            }
            a(i, this.ad, this.ae, i2, i3, R.plurals._2130903199_res_0x7f03009f);
        }
    }

    private void a(int i, HealthTextView healthTextView, HealthTextView healthTextView2, int i2, int i3, int i4) {
        LogUtil.a(this.c, " curValue=" + i2, " targetValue=" + i3, " index=", Integer.valueOf(i));
        String e = UnitUtil.e((double) i2, 1, 0);
        healthTextView.setText(e);
        String string = getContext().getResources().getString(R.string._2130845622_res_0x7f021fb6, "", nsf.a(i4, i3, UnitUtil.e(i3, 1, 0)));
        healthTextView2.setText(string);
        int measuredWidth = this.ap.getMeasuredWidth() / 3;
        if (measuredWidth == 0) {
            LogUtil.h(this.c, "showCycleTextData itemWidth is 0 ");
            return;
        }
        TextPaint paint = healthTextView2.getPaint();
        paint.setTextSize(healthTextView2.getTextSize());
        int measureText = (int) paint.measureText(string);
        TextPaint paint2 = healthTextView.getPaint();
        paint2.setTextSize(healthTextView.getTextSize());
        int measureText2 = (int) paint2.measureText(e);
        int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        LogUtil.a(this.c, " targetWidth=" + measureText, " valueWidth=" + measureText2, " marginWidth=", Integer.valueOf(dimensionPixelOffset), " itemWidth=", Integer.valueOf(measuredWidth));
        if (measureText + measureText2 + dimensionPixelOffset > measuredWidth) {
            this.ar.put(Integer.valueOf(i), Integer.valueOf(i));
        } else {
            this.ar.remove(Integer.valueOf(i));
        }
        d(this.ar);
    }

    public void j() {
        e(0, this.t, this.s);
        e(1, this.ax, this.aa);
        e(2, this.ad, this.ae);
    }

    private void e(int i, HealthTextView healthTextView, HealthTextView healthTextView2) {
        int measuredWidth = this.ap.getMeasuredWidth() / 3;
        int measureWidth = (int) healthTextView.getMeasureWidth();
        int measureWidth2 = (int) healthTextView2.getMeasureWidth();
        if (measuredWidth <= 0 || measureWidth <= 0 || measureWidth2 <= 0) {
            LogUtil.h(this.c, "updateCurValueTextByIndex Width = 0 return");
            return;
        }
        int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        LogUtil.a(this.c, "updateCurValueTextByIndex targetWidth=" + measureWidth2, " valueWidth=" + measureWidth, " marginWidth=", Integer.valueOf(dimensionPixelOffset), " itemWidth=", Integer.valueOf(measuredWidth));
        if (measureWidth2 + measureWidth + dimensionPixelOffset > measuredWidth) {
            this.ar.put(Integer.valueOf(i), Integer.valueOf(i));
        } else {
            this.ar.remove(Integer.valueOf(i));
        }
        d(this.ar);
    }

    private void d(Map<Integer, Integer> map) {
        if (map.size() > 0) {
            dij_(this.x, this.s, true);
            dij_(this.z, this.aa, true);
            dij_(this.d, this.ae, true);
        } else {
            dij_(this.x, this.s, false);
            dij_(this.z, this.aa, false);
            dij_(this.d, this.ae, false);
        }
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void updateProgress(int i, int i2, int i3, boolean z) {
        this.ai.setIsAnimator(z);
        if (i == 0) {
            this.ai.c("firstCircle", i2, i3);
        } else if (i == 1) {
            this.ai.c("secondCircle", i2, i3);
        } else {
            if (i != 2) {
                return;
            }
            this.ai.c("thirdCircle", i2, i3);
        }
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void updateBottomData(int i, int i2) {
        String string;
        if (i > -1) {
            string = UnitUtil.e(i, 1, 0);
        } else {
            string = getContext().getString(R.string._2130849885_res_0x7f02305d);
        }
        this.f.setText(dii_(getContext().getResources().getString(R.string._2130845622_res_0x7f021fb6, string, nsf.a(R.plurals._2130903359_res_0x7f03013f, i2, UnitUtil.e(i2, 1, 0))), string));
        dic_(this.e);
    }

    protected boolean dic_(LinearLayout linearLayout) {
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.bottom_step_text);
        HealthTextView healthTextView2 = (HealthTextView) linearLayout.findViewById(R.id.bottom_step_value);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.bottom_step_tip_icon);
        if (healthTextView2 == null || imageView == null) {
            LogUtil.h(this.c, "checkBottomLayoutNeedLine bottomValue is null ,return");
            return false;
        }
        int width = linearLayout.getWidth();
        int measureWidth = (int) healthTextView.getMeasureWidth();
        int width2 = healthTextView2.getWidth();
        if (width <= 0 || measureWidth <= 0 || width2 <= 0) {
            LogUtil.h(this.c, "updateBottomLayoutOrientation Width = 0 return");
            return false;
        }
        float dimension = imageView.getVisibility() == 0 ? getContext().getResources().getDimension(R.dimen._2131363006_res_0x7f0a04be) : 0.0f;
        int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(R.dimen._2131363071_res_0x7f0a04ff);
        int i = width - dimensionPixelOffset;
        LogUtil.a(this.c, " titleWidth=" + measureWidth, " stepWidth=" + width2, " tipWidth=", Float.valueOf(dimension), " containerWidth=", Integer.valueOf(i));
        if (measureWidth + dimension + width2 >= i) {
            linearLayout.setOrientation(1);
            return true;
        }
        linearLayout.setOrientation(0);
        return false;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setBottomClickListener(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setThreeCircleDataListener(int i, View.OnClickListener onClickListener) {
        if (i == 0) {
            this.an.setOnClickListener(onClickListener);
        } else if (i == 1) {
            this.aw.setOnClickListener(onClickListener);
        } else {
            if (i != 2) {
                return;
            }
            this.ab.setOnClickListener(onClickListener);
        }
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setThreeCircleLayoutListener(View.OnClickListener onClickListener) {
        this.ac.setOnClickListener(onClickListener);
    }

    public void a() {
        this.h.setText(getContext().getString(R.string._2130846518_res_0x7f022336));
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable._2131428412_res_0x7f0b043c);
        int color = BaseApplication.getContext().getResources().getColor(R.color._2131296775_res_0x7f090207);
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, color);
        if (LanguageUtil.bc(getContext())) {
            this.g.setBackground(nrz.cKm_(getContext(), wrap));
        } else {
            this.g.setBackground(wrap);
        }
    }

    protected SpannableString dii_(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        int indexOf = spannableString.toString().indexOf(str2);
        if (indexOf != -1) {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color._2131299236_res_0x7f090ba4)), indexOf, str2.length() + indexOf, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(getContext().getResources().getDimensionPixelSize(R.dimen._2131365079_res_0x7f0a0cd7)), indexOf, str2.length() + indexOf, 33);
            spannableString.setSpan(new nmj(nsk.cKO_()), indexOf, str2.length() + indexOf, 17);
        }
        return spannableString;
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setMsgRedDotText(String str) {
        this.u.setText(str);
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void updateMsgRedDotVisibility(int i) {
        this.u.setVisibility(i);
    }

    protected String d() {
        return "ThreeCircleCardViewHolder";
    }
}
