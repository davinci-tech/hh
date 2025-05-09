package com.huawei.health.knit.section.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.SectionActiveRecordThreeCircle;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.view.threeCircle.ThreeCircleView;
import defpackage.eea;
import defpackage.efa;
import defpackage.nrr;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.ntp;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes3.dex */
public class SectionActiveRecordThreeCircle extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private int f2663a;
    private HealthTextView aa;
    private View ab;
    private View ac;
    private ImageView ad;
    private ImageView ae;
    private HealthTextView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private ImageView ai;
    private HealthColumnLinearLayout aj;
    private ThreeCircleView ak;
    private HealthTextView al;
    private LinearLayout am;
    private HealthTextView an;
    private HealthTextView b;
    private HealthTextView c;
    private LinearLayout d;
    private HealthBubbleLayout e;
    private RelativeLayout f;
    private Context g;
    private HealthTextView h;
    private HealthCardView i;
    private LinearLayout j;
    private HealthTextView k;
    private HealthTextView l;
    private RelativeLayout m;
    private eea n;
    private LinearLayout o;
    private int p;
    private HealthTextView q;
    private HealthTextView r;
    private int s;
    private LinearLayout t;
    private HealthTextView u;
    private HealthImageView v;
    private final Observer w;
    private boolean x;
    private View y;
    private PopupWindow z;

    public SectionActiveRecordThreeCircle(Context context) {
        super(context);
        this.w = new d(this);
    }

    public SectionActiveRecordThreeCircle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w = new d(this);
    }

    public SectionActiveRecordThreeCircle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = new d(this);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        if (this.ab == null) {
            this.g = context;
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_active_record_three_circle, (ViewGroup) this, false);
            this.ab = inflate;
            ThreeCircleView threeCircleView = (ThreeCircleView) inflate.findViewById(R.id.three_circle_layout);
            this.ak = threeCircleView;
            threeCircleView.setIsAnimator(true);
            a();
            e();
            HealthCardView healthCardView = (HealthCardView) this.ab.findViewById(R.id.distance_heat_floor_layout);
            this.i = healthCardView;
            this.o = (LinearLayout) healthCardView.findViewById(R.id.hw_health_steps_card_distance);
            ImageView imageView = (ImageView) this.i.findViewById(R.id.sport_distance_icon);
            this.ae = imageView;
            imageView.setVisibility(8);
            this.k = (HealthTextView) this.ab.findViewById(R.id.distance);
            this.d = (LinearLayout) this.i.findViewById(R.id.hw_health_steps_card_calories);
            ImageView imageView2 = (ImageView) this.i.findViewById(R.id.sport_calorie_icon);
            this.ad = imageView2;
            imageView2.setVisibility(8);
            this.b = (HealthTextView) this.ab.findViewById(R.id.calories);
            this.c = (HealthTextView) this.ab.findViewById(R.id.unit_calories);
            this.j = (LinearLayout) this.i.findViewById(R.id.floor_layout);
            ImageView imageView3 = (ImageView) this.i.findViewById(R.id.sport_climbed_icon);
            this.ai = imageView3;
            imageView3.setVisibility(8);
            HealthTextView healthTextView = (HealthTextView) this.i.findViewById(R.id.climb_title);
            this.h = healthTextView;
            healthTextView.setText(context.getString(R$string.IDS_start_leisure_sports_type_climbing_stairs));
            this.al = (HealthTextView) this.i.findViewById(R.id.tv_calorie_title);
            this.an = (HealthTextView) this.i.findViewById(R.id.tv_distance_title);
            this.r = (HealthTextView) this.ab.findViewById(R.id.floor);
            this.q = (HealthTextView) this.ab.findViewById(R.id.unit_meter);
            this.l = (HealthTextView) this.ab.findViewById(R.id.unit_distance);
            this.m = (RelativeLayout) this.i.findViewById(R.id.distance_root_layout);
            this.f = (RelativeLayout) this.i.findViewById(R.id.cal_root_layout);
            this.t = (LinearLayout) this.i.findViewById(R.id.hw_health_steps_card_floors);
            View findViewById = this.i.findViewById(R.id.floors_split_line);
            this.y = findViewById;
            findViewById.setVisibility(8);
            this.t.setVisibility(8);
            d();
            e(this.g);
            this.aj = (HealthColumnLinearLayout) this.ab.findViewById(R.id.three_circle_root);
            b();
        }
        return this.ab;
    }

    private void b() {
        View inflate = View.inflate(this.g, R.layout.pop_threecircle_achieve, null);
        this.ac = inflate;
        this.aa = (HealthTextView) inflate.findViewById(R.id.pop_title);
        this.u = (HealthTextView) this.ac.findViewById(R.id.pop_content);
        this.v = (HealthImageView) this.ac.findViewById(R.id.pop_close_tips);
        this.e = (HealthBubbleLayout) this.ac.findViewById(R.id.route_draw_switch_bubble);
        this.v.setOnClickListener(new View.OnClickListener() { // from class: egc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SectionActiveRecordThreeCircle.this.aiA_(view);
            }
        });
        ObserverManagerUtil.d(this.w, ObserveLabels.KNIT_FRAGMENT_LOADING_FINISH);
    }

    public /* synthetic */ void aiA_(View view) {
        PopupWindow popupWindow = this.z;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c(eea eeaVar) {
        LinkedHashMap<String, ntp> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < 3; i++) {
            eea.b a2 = eeaVar.a(i);
            ntp e = new ntp.b().a(a2.i).c(a2.d).b(a2.c).e();
            if (i == 0) {
                linkedHashMap.put("firstCircle", e);
            } else if (i == 1) {
                linkedHashMap.put("secondCircle", e);
            } else {
                linkedHashMap.put("thirdCircle", e);
            }
        }
        this.ak.a(linkedHashMap);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionActiveRecordThreeCircle", "no need to bind");
            return;
        }
        Object obj = hashMap.get("SECTION_HEALTH_DICT_BEAN");
        if (obj instanceof eea) {
            eea eeaVar = (eea) obj;
            this.n = eeaVar;
            this.x = eeaVar.i();
            c(eeaVar);
            setThreeCircleData(eeaVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPopWindowData(eea eeaVar) {
        eea.a j = eeaVar.j();
        if (j == null) {
            return;
        }
        if (!j.d) {
            ReleaseLogUtil.e("SectionActiveRecordThreeCircle", "setPopWindowData isShowPop false");
            return;
        }
        String str = j.f11975a;
        String str2 = j.e;
        if (TextUtils.isEmpty(str2)) {
            this.u.setVisibility(8);
        } else {
            this.u.setVisibility(0);
            this.u.setText(str2);
        }
        this.aa.setText(str);
        a(j.c);
    }

    private void a(final String str) {
        ThreeCircleView threeCircleView = this.ak;
        if (threeCircleView != null) {
            threeCircleView.post(new Runnable() { // from class: ege
                @Override // java.lang.Runnable
                public final void run() {
                    SectionActiveRecordThreeCircle.this.d(str);
                }
            });
        }
    }

    public /* synthetic */ void d(String str) {
        if (this.z != null || this.ac == null) {
            ReleaseLogUtil.e("SectionActiveRecordThreeCircle", "mPopupWindow is created or mPopupWindowContentView is null");
            return;
        }
        Context context = this.g;
        if (!(context instanceof Activity)) {
            ReleaseLogUtil.d("SectionActiveRecordThreeCircle", "mContext null");
            return;
        }
        if (((Activity) context).isDestroyed() || ((Activity) this.g).isFinishing()) {
            ReleaseLogUtil.d("SectionActiveRecordThreeCircle", "mContext isFinishing");
            return;
        }
        int width = this.am.getWidth() - this.ak.getWidth();
        this.e.setArrowPosition(1);
        if (LanguageUtil.bc(this.g)) {
            this.e.setArrowPosition(2);
        }
        int width2 = this.ak.getWidth() / 2;
        int i = width + width2;
        LogUtil.a("SectionActiveRecordThreeCircle", "left:", Integer.valueOf(width), "width:", Integer.valueOf(width2));
        int bubbleRadius = this.e.getBubbleRadius();
        int sqrt = (int) ((2.0d - Math.sqrt(2.0d)) * 2.0d * bubbleRadius);
        int b = ((i - sqrt) - nsf.b(R.dimen._2131362886_res_0x7f0a0446)) - (nsf.b(R.dimen._2131362964_res_0x7f0a0494) / 2);
        LogUtil.a("SectionActiveRecordThreeCircle", "circleViewX:", Integer.valueOf(i), "mRadius:", Integer.valueOf(sqrt), "arrowX:", Integer.valueOf(b), "bubbleRadius:", Integer.valueOf(bubbleRadius));
        this.e.setArrowPosition(b);
        PopupWindow popupWindow = new PopupWindow(this.g);
        this.z = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.z.setContentView(this.ac);
        this.z.setOutsideTouchable(true);
        this.z.setFocusable(true);
        int h = nsn.h(this.g);
        this.z.setWidth((h - (nsf.b(R.dimen._2131364635_res_0x7f0a0b1b) * 2)) + (nsf.b(R.dimen._2131363122_res_0x7f0a0532) * 2));
        this.z.setHeight(-2);
        this.z.showAsDropDown(this.aj, nsf.b(R.dimen._2131363063_res_0x7f0a04f7), -nsf.b(R.dimen._2131363122_res_0x7f0a0532), 17);
        SharedPreferenceManager.e(this.g, String.valueOf(10000), str, String.valueOf(DateFormatUtil.b(System.currentTimeMillis())), new StorageParams());
        ReleaseLogUtil.e("SectionActiveRecordThreeCircle", "popWindow:", Integer.valueOf(this.z.getWidth()), " screenWidth:", Integer.valueOf(h));
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
        PopupWindow popupWindow = this.z;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    private void a() {
        int i;
        View view = this.ab;
        if (view == null) {
            ReleaseLogUtil.c("SectionActiveRecordThreeCircle", "mRootView is null in adaptBigScaleAndWide");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.three_circle_linear_layout);
        this.am = linearLayout;
        if (this.g == null || !(linearLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ReleaseLogUtil.c("SectionActiveRecordThreeCircle", "mContext is null or LayoutParams error");
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.am.getLayoutParams();
        if (nsn.ag(this.g)) {
            i = R.dimen._2131363060_res_0x7f0a04f4;
        } else if (Build.BRAND.toLowerCase(Locale.ENGLISH).contains("vivo") && nsn.a(3.1f)) {
            LogUtil.a("SectionActiveRecordThreeCircle", "isLargeScaledDensityMode by vivo");
            i = R.dimen._2131362886_res_0x7f0a0446;
        } else if (efa.c() && nsn.a(3.1f)) {
            LogUtil.a("SectionActiveRecordThreeCircle", "isLargeScaledDensityMode");
            i = R.dimen._2131362922_res_0x7f0a046a;
        } else {
            i = R.dimen._2131363015_res_0x7f0a04c7;
        }
        marginLayoutParams.setMarginEnd((int) this.g.getResources().getDimension(i));
        this.am.setLayoutParams(marginLayoutParams);
    }

    private void a(eea eeaVar) {
        HealthTextView healthTextView;
        LinearLayout linearLayout = (LinearLayout) this.ab.findViewById(R.id.right_item_step);
        if (linearLayout == null) {
            return;
        }
        linearLayout.setOnClickListener(eeaVar.a(0).e);
        HealthTextView healthTextView2 = (HealthTextView) linearLayout.findViewById(R.id.htv_item_title);
        if (healthTextView2 == null) {
            return;
        }
        healthTextView2.setText(eeaVar.a(0).b);
        ((HealthImageView) linearLayout.findViewById(R.id.hiv_item_icon)).setBackground(ContextCompat.getDrawable(this.g, eeaVar.a(0).g));
        LinearLayout linearLayout2 = (LinearLayout) this.ab.findViewById(R.id.right_item_strength);
        if (linearLayout2 == null || (healthTextView = (HealthTextView) linearLayout2.findViewById(R.id.htv_item_title)) == null) {
            return;
        }
        linearLayout2.setOnClickListener(eeaVar.a(1).e);
        HealthImageView healthImageView = (HealthImageView) linearLayout2.findViewById(R.id.hiv_item_icon);
        if (healthImageView == null) {
            return;
        }
        healthImageView.setBackground(ContextCompat.getDrawable(this.g, eeaVar.a(1).g));
        healthTextView.setText(eeaVar.a(1).b);
        LinearLayout linearLayout3 = (LinearLayout) this.ab.findViewById(R.id.right_item_stand);
        if (linearLayout3 == null) {
            return;
        }
        HealthTextView healthTextView3 = (HealthTextView) linearLayout3.findViewById(R.id.htv_item_title);
        linearLayout3.setOnClickListener(eeaVar.a(2).e);
        if (healthTextView3 == null) {
            return;
        }
        healthTextView3.setText(eeaVar.a(2).b);
        ((HealthImageView) linearLayout3.findViewById(R.id.hiv_item_icon)).setBackground(ContextCompat.getDrawable(this.g, eeaVar.a(2).g));
    }

    private void e() {
        LinearLayout linearLayout = (LinearLayout) this.ab.findViewById(R.id.right_item_step);
        ((HealthTextView) linearLayout.findViewById(R.id.htv_item_title)).setText(this.g.getResources().getString(R$string.IDS_start_track_target_type_calorie));
        ((HealthImageView) linearLayout.findViewById(R.id.hiv_item_icon)).setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131430050_res_0x7f0b0aa2));
        this.ah = (HealthTextView) linearLayout.findViewById(R.id.htv_item_content);
        LinearLayout linearLayout2 = (LinearLayout) this.ab.findViewById(R.id.right_item_strength);
        ((HealthTextView) linearLayout2.findViewById(R.id.htv_item_title)).setText(this.g.getResources().getString(R$string.IDS_intensity_new));
        ((HealthImageView) linearLayout2.findViewById(R.id.hiv_item_icon)).setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131430042_res_0x7f0b0a9a));
        this.ag = (HealthTextView) linearLayout2.findViewById(R.id.htv_item_content);
        LinearLayout linearLayout3 = (LinearLayout) this.ab.findViewById(R.id.right_item_stand);
        ((HealthTextView) linearLayout3.findViewById(R.id.htv_item_title)).setText(this.g.getResources().getString(R$string.IDS_three_circle_card_activity_hours));
        ((HealthImageView) linearLayout3.findViewById(R.id.hiv_item_icon)).setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131429980_res_0x7f0b0a5c));
        this.af = (HealthTextView) linearLayout3.findViewById(R.id.htv_item_content);
    }

    private void setThreeCircleData(eea eeaVar) {
        this.ak.c("firstCircle", eeaVar.a(0).f, eeaVar.a(0).h);
        this.ak.c("secondCircle", eeaVar.a(1).f, eeaVar.a(1).h);
        this.ak.c("thirdCircle", eeaVar.a(2).f, eeaVar.a(2).h);
        setRightContent(eeaVar);
        setBottomContent(eeaVar);
        a(eeaVar);
    }

    private void d() {
        LinearLayout linearLayout = (LinearLayout) this.i.findViewById(R.id.distance_value_unit);
        LinearLayout linearLayout2 = (LinearLayout) this.i.findViewById(R.id.calories_value_unit);
        LinearLayout linearLayout3 = (LinearLayout) this.i.findViewById(R.id.floor_value_unit);
        aiv_(this.o, linearLayout);
        aiv_(this.d, linearLayout2);
        aiv_(this.j, linearLayout3);
    }

    private void aiv_(LinearLayout linearLayout, LinearLayout linearLayout2) {
        if (nsn.r()) {
            linearLayout.setOrientation(1);
            linearLayout.setGravity(1);
            linearLayout2.setOrientation(1);
            linearLayout2.setGravity(1);
            aiw_(this.ae, this.an, 0);
            b(this.k, 0);
            aiw_(this.ad, this.al, 0);
            b(this.b, 0);
            aiw_(this.ai, this.h, 0);
            b(this.r, 0);
            return;
        }
        if (nsn.t()) {
            linearLayout.setOrientation(1);
            linearLayout.setGravity(1);
            linearLayout2.setOrientation(0);
            aiw_(this.ae, this.an, 0);
            b(this.k, 2);
            this.al.setGravity(1);
            aiw_(this.ad, this.al, 0);
            b(this.b, 2);
            aiw_(this.ai, this.h, 0);
            b(this.r, 2);
            return;
        }
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout2.setOrientation(0);
        aiw_(this.ae, this.an, 2);
        b(this.k, 2);
        aiw_(this.ad, this.al, 2);
        b(this.b, 2);
        aiw_(this.ai, this.h, 4);
        b(this.r, 2);
    }

    private void b(HealthTextView healthTextView, int i) {
        if (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
            layoutParams.setMarginEnd(nrr.e(this.g, i));
            healthTextView.setLayoutParams(layoutParams);
        }
    }

    private void aiw_(ImageView imageView, HealthTextView healthTextView, int i) {
        if (imageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.setMarginEnd(nrr.e(this.g, i));
            imageView.setLayoutParams(layoutParams);
        }
        if (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
            layoutParams2.setMarginEnd(nrr.e(this.g, i));
            healthTextView.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        this.p = this.o.getWidth();
        this.f2663a = this.d.getWidth();
        this.s = this.j.getWidth();
        if (z) {
            int width = this.i.getWidth();
            int i = this.p;
            int i2 = (((width - i) - this.f2663a) - this.s) / 3;
            aiy_(this.m, i + i2);
            aiy_(this.f, this.f2663a + i2);
            aix_(this.t, this.s + i2);
            return;
        }
        int width2 = this.i.getWidth();
        int i3 = this.p;
        int i4 = ((width2 - i3) - this.f2663a) / 2;
        aiy_(this.m, i3 + i4);
        aiy_(this.f, this.f2663a + i4);
    }

    private void aiy_(RelativeLayout relativeLayout, int i) {
        if (relativeLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = i;
            relativeLayout.setLayoutParams(layoutParams);
        }
    }

    private void aix_(LinearLayout linearLayout, int i) {
        if (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.width = i;
            linearLayout.setLayoutParams(layoutParams);
        }
    }

    private void e(Context context) {
        if (LanguageUtil.m(context)) {
            this.an.setVisibility(0);
            this.ae.setVisibility(8);
            this.al.setVisibility(0);
            this.ad.setVisibility(8);
            this.h.setVisibility(0);
            this.ai.setVisibility(8);
            return;
        }
        this.an.setVisibility(8);
        this.ae.setVisibility(0);
        this.al.setVisibility(8);
        this.ad.setVisibility(0);
        this.h.setVisibility(8);
        this.ai.setVisibility(0);
    }

    private void e(eea eeaVar) {
        aiz_(this.ae, eeaVar.c().c);
        aiz_(this.ad, eeaVar.d().c);
        aiz_(this.ai, R.drawable._2131429985_res_0x7f0b0a61);
    }

    private void aiz_(ImageView imageView, int i) {
        if (LanguageUtil.bc(this.g)) {
            imageView.setBackground(nrz.cKn_(this.g, i));
        } else {
            imageView.setBackgroundResource(i);
        }
    }

    private void setRightContent(eea eeaVar) {
        this.ah.setText(eeaVar.a(0).f11976a);
        this.ag.setText(eeaVar.a(1).f11976a);
        this.af.setText(eeaVar.a(2).f11976a);
    }

    private void setBottomContent(eea eeaVar) {
        e(eeaVar);
        this.o.setVisibility(0);
        this.o.setOnClickListener(eeaVar.c().b);
        this.d.setVisibility(0);
        this.d.setOnClickListener(eeaVar.d().b);
        this.t.setVisibility(eeaVar.i() ? 0 : 8);
        this.y.setVisibility(eeaVar.i() ? 0 : 8);
        d(eeaVar.i());
        this.t.setOnClickListener(eeaVar.agJ_());
        g();
        if (UnitUtil.h()) {
            this.r.setText(UnitUtil.e(UnitUtil.e(eeaVar.e(), 1), 1, 2));
            this.q.setText(this.g.getResources().getString(R$string.IDS_ft));
        } else {
            this.r.setText(UnitUtil.e(eeaVar.e(), 1, 1));
        }
        this.an.setText(eeaVar.c().f11977a);
        this.al.setText(eeaVar.d().f11977a);
        this.k.setText(eeaVar.c().e);
        this.b.setText(eeaVar.d().e);
        this.l.setText(eeaVar.c().d);
        this.c.setText(eeaVar.d().d);
        if (this.o.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.o.getLayoutParams();
            if (!eeaVar.i()) {
                layoutParams.addRule(13, -1);
            } else {
                layoutParams.removeRule(13);
            }
            this.o.setLayoutParams(layoutParams);
        }
    }

    private void g() {
        this.i.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.SectionActiveRecordThreeCircle.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LogUtil.a("SectionActiveRecordThreeCircle", "update distance cal floor width");
                if (SectionActiveRecordThreeCircle.this.c()) {
                    SectionActiveRecordThreeCircle.this.i.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    SectionActiveRecordThreeCircle sectionActiveRecordThreeCircle = SectionActiveRecordThreeCircle.this;
                    sectionActiveRecordThreeCircle.d(sectionActiveRecordThreeCircle.x);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        return this.p == this.o.getWidth() && this.f2663a == this.d.getWidth() && this.s == this.j.getWidth();
    }

    /* loaded from: classes8.dex */
    static class d implements Observer {
        private final WeakReference<SectionActiveRecordThreeCircle> e;

        public d(SectionActiveRecordThreeCircle sectionActiveRecordThreeCircle) {
            this.e = new WeakReference<>(sectionActiveRecordThreeCircle);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            SectionActiveRecordThreeCircle sectionActiveRecordThreeCircle = this.e.get();
            if (sectionActiveRecordThreeCircle == null) {
                LogUtil.h("SectionActiveRecordThreeCircle", "section == null");
                return;
            }
            LogUtil.a("SectionActiveRecordThreeCircle", "mObserver label:", str);
            if (ObserveLabels.KNIT_FRAGMENT_LOADING_FINISH.equals(str) && sectionActiveRecordThreeCircle.n != null) {
                sectionActiveRecordThreeCircle.setPopWindowData(sectionActiveRecordThreeCircle.n);
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void clear() {
        super.clear();
        ObserverManagerUtil.d(this.w, ObserveLabels.KNIT_FRAGMENT_LOADING_FINISH);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionActiveRecordThreeCircle";
    }
}
