package com.huawei.health.knit.section.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.knit.section.view.MemberPlanSection;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.ruler.circlescaleruler.HealthCircleScaleRuler;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.dpe;
import defpackage.dqj;
import defpackage.eiv;
import defpackage.ffy;
import defpackage.gge;
import defpackage.gib;
import defpackage.koq;
import defpackage.moj;
import defpackage.nqg;
import defpackage.nru;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qts;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class MemberPlanSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2632a;
    private c ab;
    private HealthBubbleLayout b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private Context h;
    private HealthImageView i;
    private HealthImageView j;
    private HealthProgressBar k;
    private HealthButton l;
    private LinearLayout m;
    private HealthCardView n;
    private boolean o;
    private HealthButton p;
    private boolean q;
    private HealthTextView r;
    private HealthTextView s;
    private Map<String, Object> t;
    private ImageView u;
    private HealthCardView v;
    private ImageView w;
    private HealthCircleScaleRuler x;
    private View y;

    public MemberPlanSection(Context context) {
        super(context);
        this.t = new HashMap();
        this.h = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Suggestion_MemberPlanSection", "MemberPlanSection onCreateView.");
        View inflate = LayoutInflater.from(context).inflate(R.layout.member_plan_layout, (ViewGroup) this, false);
        this.y = inflate;
        ViewTreeVisibilityListener.Zy_(inflate, new ViewTreeVisibilityListener(inflate, this));
        ahT_(this.y);
        this.y.setOnTouchListener(new View.OnTouchListener() { // from class: efk
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return MemberPlanSection.this.ahV_(view, motionEvent);
            }
        });
        return this.y;
    }

    public /* synthetic */ boolean ahV_(View view, MotionEvent motionEvent) {
        LogUtil.a("Suggestion_MemberPlanSection", "onCreateView onTouch tempView ", view, " motionEvent ", motionEvent, " performClick ", Boolean.valueOf(view != null ? view.performClick() : false));
        HealthBubbleLayout healthBubbleLayout = this.b;
        if (healthBubbleLayout != null && healthBubbleLayout.getVisibility() == 0) {
            this.b.setVisibility(8);
        }
        return false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
    public void checkVisibilityAndSetBiEvent() {
        if (ViewTreeVisibilityListener.Zx_(this)) {
            if (this.o) {
                return;
            }
            this.o = e();
            return;
        }
        this.o = false;
    }

    private boolean e() {
        LogUtil.a("Suggestion_MemberPlanSection", "doMemberBi into page.", Boolean.valueOf(this.q), Boolean.valueOf(this.o), Integer.valueOf(this.t.size()));
        if (!this.q) {
            return false;
        }
        gge.d(nru.d((Map) this.t, "planStatus", 0), nru.b(this.t, "planName", ""), 0, nru.d((Map) this.t, "finishRate", 0));
        return true;
    }

    private void ahT_(View view) {
        this.v = (HealthCardView) view.findViewById(R.id.recommend_plan_member_card);
        this.n = (HealthCardView) view.findViewById(R.id.doing_plan_member_card);
        this.f2632a = (ImageView) view.findViewById(R.id.close_button);
        if (nsn.v(this.h)) {
            this.f2632a.setBackgroundResource(R.drawable._2131429867_res_0x7f0b09eb);
        }
        this.u = (ImageView) view.findViewById(R.id.recommend_plan_image);
        this.l = (HealthButton) view.findViewById(R.id.create_plan_button);
        this.s = (HealthTextView) view.findViewById(R.id.plan_name);
        this.p = (HealthButton) view.findViewById(R.id.plan_report_button);
        this.w = (ImageView) view.findViewById(R.id.report_dot);
        this.d = (HealthTextView) view.findViewById(R.id.plan_completed_days_and_rate);
        this.k = (HealthProgressBar) view.findViewById(R.id.current_plan_progress_item_horizontal);
        this.x = (HealthCircleScaleRuler) view.findViewById(R.id.sug_layout_plan_week_detail_health_circle_scale_ruler);
        this.m = (LinearLayout) view.findViewById(R.id.sug_layout_plan_week_detail_layout);
        this.i = (HealthImageView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_icon);
        this.g = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_title);
        this.j = (HealthImageView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_icon_tip);
        this.f = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_value);
        this.b = (HealthBubbleLayout) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_bubble);
        this.e = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_bubble_sport);
        this.c = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_bubble_resting);
        this.r = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_intake_value);
        ((HealthSubHeader) view.findViewById(R.id.plan_subheader)).setSubHeaderBackgroundColor(ContextCompat.getColor(this.h, R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.today_suggestion_list);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this.h, 1, false));
        c cVar = new c(this.h);
        this.ab = cVar;
        healthRecycleView.setAdapter(cVar);
        healthRecycleView.setNestedScrollingEnabled(false);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        healthRecycleView.setItemAnimator(defaultItemAnimator);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (!nru.d((Map) hashMap, "DOING_PLAN_FLAG", false)) {
            String b2 = SharedPreferenceManager.b(this.h, Integer.toString(10000), "KEY_CLOSED_PLAN_CARD");
            LogUtil.a("Suggestion_MemberPlanSection", "no doing plan.", b2);
            if (Boolean.parseBoolean(b2)) {
                this.v.setVisibility(8);
                this.t.put("planStatus", 2);
                c(2, "", 0);
            } else {
                this.v.setVisibility(0);
                c(0, "", 0);
            }
            if (nsn.ag(this.h)) {
                this.u.setBackgroundResource(R.drawable.pic_activity_plan_daxidi);
            }
            this.n.setVisibility(8);
            d();
            setRecommendCardClickListener(hashMap);
            return;
        }
        LogUtil.a("Suggestion_MemberPlanSection", "bindParamsToView.", hashMap.toString());
        this.v.setVisibility(8);
        this.n.setVisibility(0);
        this.s.setText(nru.b(hashMap, "PLAN_NAME", ""));
        setPlanReportButtion(hashMap);
        if (nru.d((Map) hashMap, "INT_PLAN_TYPE", IntPlan.PlanType.NA_PLAN.getType()) == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
            setAiFitnessPlanLayout(hashMap);
        } else {
            setCommonPlanLayout(hashMap);
        }
        this.ab.c(a(hashMap));
    }

    private void c(int i, String str, int i2) {
        this.t.put("planStatus", Integer.valueOf(i));
        this.t.put("planName", str);
        this.t.put("finishRate", Integer.valueOf(i2));
        this.q = true;
    }

    private void setPlanReportButtion(HashMap<String, Object> hashMap) {
        String b2 = nru.b(hashMap, "REPORT_PLAN_TEXT", "");
        if (TextUtils.isEmpty(b2)) {
            this.p.setVisibility(8);
        } else {
            this.p.setVisibility(0);
            this.p.setText(b2);
            View.OnClickListener ahR_ = ahR_(hashMap, "GO_TO_REPORT_CLICK");
            if (ahR_ != null) {
                this.p.setOnClickListener(ahR_);
            } else {
                LogUtil.b("Suggestion_MemberPlanSection", "reportClick null.");
            }
        }
        if (nru.d((Map) hashMap, "IS_SHOW_RED_DOT", false)) {
            this.w.setVisibility(0);
        } else {
            this.w.setVisibility(8);
        }
    }

    private void setAiFitnessPlanLayout(HashMap<String, Object> hashMap) {
        this.d.setVisibility(8);
        this.k.setVisibility(8);
        nqg nqgVar = new nqg(false);
        dpe.c(nqgVar, false);
        this.x.setVisibility(0);
        View.OnClickListener ahR_ = ahR_(hashMap, "GO_TO_DIETARY_INSTRUCT");
        if (ahR_ != null) {
            this.x.setIconOnClickListener(ahR_);
        }
        qts qtsVar = (qts) nru.c(hashMap, "DIET_CALORIE_OVERVIEW", qts.class, null);
        if (qtsVar == null) {
            ReleaseLogUtil.a("Suggestion_MemberPlanSection", "setAiFitnessPlanLayout overview is null currentParams ", hashMap);
            return;
        }
        dpe.c(nqgVar, qtsVar, false);
        this.x.setCircleScaleRulerData(nqgVar);
        dpe.d(this.x, qtsVar, false);
        this.m.setVisibility(0);
        dpe.a(this.f, qtsVar, false);
        dpe.b(this.r, qtsVar, false);
        ahS_(this.i, qtsVar);
        ahS_(this.g, qtsVar);
        ahS_(this.j, qtsVar);
        ahS_(this.f, qtsVar);
        ahS_(this.b, qtsVar);
    }

    private void ahS_(View view, final qts qtsVar) {
        view.setOnClickListener(new View.OnClickListener() { // from class: efl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MemberPlanSection.this.ahU_(qtsVar, view2);
            }
        });
    }

    public /* synthetic */ void ahU_(qts qtsVar, View view) {
        dpe.a(this.b, this.g.getMeasuredWidth(), this.e, this.c, qtsVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        int a2 = gib.a();
        LogUtil.a("Suggestion_MemberPlanSection", "today of week:", Integer.valueOf(a2));
        FrameLayout frameLayout = (FrameLayout) this.y.findViewById(R.id.week_monday_layout);
        HealthTextView healthTextView = (HealthTextView) this.y.findViewById(R.id.week_monday);
        FrameLayout frameLayout2 = (FrameLayout) this.y.findViewById(R.id.week_tuesday_layout);
        HealthTextView healthTextView2 = (HealthTextView) this.y.findViewById(R.id.week_tuesday);
        FrameLayout frameLayout3 = (FrameLayout) this.y.findViewById(R.id.week_wed_layout);
        HealthTextView healthTextView3 = (HealthTextView) this.y.findViewById(R.id.week_wed);
        FrameLayout frameLayout4 = (FrameLayout) this.y.findViewById(R.id.week_thur_layout);
        HealthTextView healthTextView4 = (HealthTextView) this.y.findViewById(R.id.week_thur);
        FrameLayout frameLayout5 = (FrameLayout) this.y.findViewById(R.id.week_fri_layout);
        HealthTextView healthTextView5 = (HealthTextView) this.y.findViewById(R.id.week_friday);
        FrameLayout frameLayout6 = (FrameLayout) this.y.findViewById(R.id.week_sat_layout);
        HealthTextView healthTextView6 = (HealthTextView) this.y.findViewById(R.id.week_sat);
        FrameLayout[] frameLayoutArr = {frameLayout, frameLayout2, frameLayout3, frameLayout4, frameLayout5, frameLayout6, (FrameLayout) this.y.findViewById(R.id.week_sun_layout)};
        HealthTextView[] healthTextViewArr = {healthTextView, healthTextView2, healthTextView3, healthTextView4, healthTextView5, healthTextView6, (HealthTextView) this.y.findViewById(R.id.week_sun)};
        if (a2 > 0 && a2 <= 7) {
            int i = a2 - 1;
            FrameLayout frameLayout7 = frameLayoutArr[i];
            if (frameLayout7 != null) {
                frameLayout7.setBackground(nsf.cKq_(R.drawable._2131427776_res_0x7f0b01c0));
            }
            HealthTextView healthTextView7 = healthTextViewArr[i];
            if (healthTextView7 != null) {
                healthTextView7.setTextColor(nsf.c(R.color._2131299238_res_0x7f090ba6));
            }
        }
        int i2 = 0;
        if (!LanguageUtil.h(this.h)) {
            while (i2 < 7) {
                healthTextViewArr[i2].setTextSize(1, 10.0f);
                i2++;
            }
        } else if (nsn.t()) {
            while (i2 < 7) {
                healthTextViewArr[i2].setTextSize(1, 12.0f);
                i2++;
            }
        }
    }

    private void setRecommendCardClickListener(HashMap<String, Object> hashMap) {
        ImageView imageView = this.f2632a;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.MemberPlanSection.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dqj.o();
                    MemberPlanSection.this.v.setVisibility(8);
                    SharedPreferenceManager.e(MemberPlanSection.this.h, Integer.toString(10000), "KEY_CLOSED_PLAN_CARD", "true", (StorageParams) null);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        if (this.l != null) {
            View.OnClickListener ahR_ = ahR_(hashMap, "CREATE_AI_FITNESS_CLICK");
            if (ahR_ != null) {
                this.l.setOnClickListener(ahR_);
            } else {
                LogUtil.b("Suggestion_MemberPlanSection", "create plan click null.");
            }
        }
    }

    private void setCommonPlanLayout(HashMap<String, Object> hashMap) {
        this.x.setVisibility(8);
        this.m.setVisibility(8);
        this.d.setVisibility(0);
        this.k.setVisibility(0);
        View.OnClickListener ahR_ = ahR_(hashMap, "GO_TO_SPORT_CLICK");
        if (ahR_ != null) {
            this.k.setOnClickListener(ahR_);
        }
        float e2 = nru.e(hashMap, "FINISH_RATE", 0.0f);
        String d2 = ffy.d(this.h, R$string.IDS_plan_finish_percent, UnitUtil.e(e2, 2, 1));
        if (LanguageUtil.e(this.h)) {
            d2 = d2.replace("%", " %");
        }
        c(nru.d((Map) hashMap, "BEGIN_DATE", 0L), nru.d((Map) hashMap, "TOTAL_DAY", 0), d2);
        if (e2 > 0.0f && e2 <= 6.0f) {
            this.k.setProgress(6);
        } else {
            this.k.setProgress(Math.round(e2));
        }
    }

    private void c(long j, int i, String str) {
        String c2;
        long j2 = j * 1000;
        int b2 = (int) (((gib.b(System.currentTimeMillis()) / 86400000) - (j2 / 86400000)) + 1);
        if (b2 > i) {
            b2 = i;
        }
        if (b2 <= 0) {
            c2 = BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_plan_start_date, DateUtils.formatDateTime(BaseApplication.getContext(), j2, 24));
        } else {
            c2 = ffy.c(this.h, b2, i, 0);
        }
        this.d.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_run_plan_calendar_title, c2, str));
    }

    private List<d> a(HashMap<String, Object> hashMap) {
        boolean z;
        String b2 = nru.b(hashMap, "GO_TO_SPORT_TIP", "");
        boolean d2 = nru.d((Map) hashMap, "IS_VIP_EXPIRED", false);
        int d3 = nru.d((Map) hashMap, "INT_PLAN_TYPE", IntPlan.PlanType.NA_PLAN.getType());
        String b3 = nru.b(hashMap, "PLAN_NAME", "");
        float e2 = nru.e(hashMap, "FINISH_RATE", 0.0f);
        if (d2 && d3 == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
            c(3, b3, (int) e2);
            z = true;
        } else {
            c(1, b3, (int) e2);
            z = false;
        }
        d dVar = new d(R.drawable._2131430585_res_0x7f0b0cb9, this.h.getString(R$string.IDS_health_model_card_dialog_go_exercise), b2.equals(this.h.getString(R$string.IDS_sport_task_finished)), b2, z, ahR_(hashMap, "GO_TO_SPORT_CLICK"), true);
        d dVar2 = new d(R.drawable._2131430419_res_0x7f0b0c13, this.h.getString(R$string.IDS_record_diet), false, nru.b(hashMap, "DIET_TIP", ""), false, ahR_(hashMap, "DIET_CLICK"), true);
        String b4 = nru.b(hashMap, "RECIPE_TIP", "");
        View.OnClickListener ahR_ = ahR_(hashMap, "RECIPE_CLICK");
        d b5 = b(hashMap);
        d dVar3 = new d(R.drawable._2131430173_res_0x7f0b0b1d, this.h.getString(R$string.IDS_look_recipe), false, b4, d2, ahR_, b5 != null);
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(dVar);
        arrayList.add(dVar2);
        arrayList.add(dVar3);
        if (b5 != null) {
            arrayList.add(b5);
        }
        return arrayList;
    }

    private d b(HashMap<String, Object> hashMap) {
        String string;
        int d2 = nru.d((Map) hashMap, "FASTING_LITE_TYPE", 0);
        long d3 = nru.d((Map) hashMap, "FASTING_LITE_VALUE", 0L);
        if (d2 == 0) {
            return null;
        }
        View.OnClickListener ahR_ = ahR_(hashMap, "FASTING_LITE_CLICK");
        if (d3 == 0) {
            string = BaseApplication.getContext().getString(d2);
        } else {
            string = BaseApplication.getContext().getString(d2, UnitUtil.d((int) (d3 / 1000)));
        }
        d dVar = new d(R.drawable._2131430151_res_0x7f0b0b07, this.h.getString(R$string.IDS_wl_food_entrance_light_f), false, string, false, ahR_, false);
        dVar.g = d2;
        dVar.d = d3;
        return dVar;
    }

    private View.OnClickListener ahR_(HashMap<String, Object> hashMap, String str) {
        Object d2 = nru.d(hashMap, str, (Object) null);
        if (d2 instanceof View.OnClickListener) {
            return (View.OnClickListener) d2;
        }
        return null;
    }

    static class b extends CountDownTimer {
        private int b;
        private WeakReference<HealthTextView> d;

        b(HealthTextView healthTextView, int i, long j, long j2) {
            super(j, j2);
            LogUtil.a("Suggestion_MemberPlanSection", "FastingCountDownTimer create.", Long.valueOf(j));
            this.d = new WeakReference<>(healthTextView);
            this.b = i;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            HealthTextView healthTextView = this.d.get();
            if (healthTextView == null) {
                LogUtil.h("Suggestion_MemberPlanSection", "healthTextView == null");
            } else {
                healthTextView.setText(BaseApplication.getContext().getString(this.b, UnitUtil.d((int) (j / 1000))));
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            LogUtil.a("Suggestion_MemberPlanSection", "finish.");
        }
    }

    static class c extends RecyclerView.Adapter<e> {

        /* renamed from: a, reason: collision with root package name */
        private Context f2634a;
        private List<d> b = new ArrayList();

        c(Context context) {
            this.f2634a = context;
        }

        void c(List<d> list) {
            DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new a(this.b, list));
            this.b = list;
            calculateDiff.dispatchUpdatesTo(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: ahW_, reason: merged with bridge method [inline-methods] */
        public e onCreateViewHolder(ViewGroup viewGroup, int i) {
            LogUtil.a("Suggestion_MemberPlanSection", "onCreateViewHolder.", Integer.valueOf(i));
            return new e(LayoutInflater.from(this.f2634a).inflate(R.layout.today_suggestion_item_layout, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(e eVar, int i) {
            if (koq.b(this.b, i)) {
                LogUtil.b("Suggestion_MemberPlanSection", "onBindViewHolder error,", Integer.valueOf(i));
            } else {
                LogUtil.a("Suggestion_MemberPlanSection", "onBindViewHolder.", Integer.valueOf(i), eVar.f.getText());
                eVar.d(this.b.get(i));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.b.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onViewRecycled(e eVar) {
            super.onViewRecycled(eVar);
            Object[] objArr = new Object[4];
            objArr[0] = "onViewRecycled.";
            objArr[1] = eVar.f.getText();
            objArr[2] = " ";
            objArr[3] = Boolean.valueOf(eVar.e == null);
            LogUtil.a("Suggestion_MemberPlanSection", objArr);
            eVar.c();
        }
    }

    static class a extends DiffUtil.Callback {

        /* renamed from: a, reason: collision with root package name */
        private List<d> f2633a;
        private List<d> c;

        a(List<d> list, List<d> list2) {
            this.f2633a = list;
            this.c = list2;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            List<d> list = this.f2633a;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            List<d> list = this.c;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int i, int i2) {
            String str;
            return (koq.b(this.f2633a, i) || koq.b(this.c, i) || (str = this.f2633a.get(i).h) == null || !str.equals(this.c.get(i2).h)) ? false : true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int i, int i2) {
            if (koq.b(this.f2633a, i) || koq.b(this.c, i)) {
                return false;
            }
            d dVar = this.f2633a.get(i);
            d dVar2 = this.c.get(i2);
            if (dVar == null || dVar2 == null) {
                return false;
            }
            return moj.e(dVar).equals(moj.e(dVar2));
        }
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f2636a;
        private ConstraintLayout b;
        private HealthDivider c;
        private ImageView d;
        private CountDownTimer e;
        private HealthTextView f;
        private ImageView g;
        private ImageView h;
        private HealthTextView i;

        e(View view) {
            super(view);
            this.b = (ConstraintLayout) view.findViewById(R.id.suggestion_item);
            this.d = (ImageView) view.findViewById(R.id.suggestion_icon);
            this.f = (HealthTextView) view.findViewById(R.id.suggestion_text);
            this.f2636a = (ImageView) view.findViewById(R.id.finish_icon);
            this.i = (HealthTextView) view.findViewById(R.id.suggestion_value);
            this.g = (ImageView) view.findViewById(R.id.right_icon);
            this.h = (ImageView) view.findViewById(R.id.locked_icon);
            this.c = (HealthDivider) view.findViewById(R.id.suggestion_item_divider);
        }

        void d(d dVar) {
            this.d.setImageResource(dVar.b);
            this.f.setText(dVar.h);
            if (dVar.i == null) {
                this.b.setVisibility(8);
            } else {
                this.i.setText(dVar.i);
            }
            if (dVar.e) {
                this.f2636a.setVisibility(0);
            } else {
                this.f2636a.setVisibility(8);
            }
            d(dVar.c);
            if (dVar.f != null) {
                this.b.setOnClickListener(dVar.f);
            }
            if (dVar.f2635a) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
            if (dVar.g != 0) {
                if (dVar.d != 0) {
                    d(dVar.g, dVar.d);
                } else {
                    this.i.setText(BaseApplication.getContext().getString(dVar.g));
                }
            }
        }

        private void d(boolean z) {
            if (z) {
                this.h.setVisibility(0);
                this.g.setVisibility(8);
            } else {
                this.g.setImageDrawable(eiv.alY_());
                this.h.setVisibility(8);
                this.g.setVisibility(0);
            }
        }

        private void d(int i, long j) {
            CountDownTimer countDownTimer = this.e;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.e = null;
            }
            this.e = new b(this.i, i, j, 1000L).start();
        }

        public void c() {
            CountDownTimer countDownTimer = this.e;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.e = null;
            }
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2635a;
        private int b;
        private boolean c;
        private long d;
        private boolean e;
        private View.OnClickListener f;
        private int g;
        private String h;
        private String i;

        d(int i, String str, boolean z, String str2, boolean z2, View.OnClickListener onClickListener, boolean z3) {
            this.b = i;
            this.h = str;
            this.e = z;
            this.i = str2;
            this.c = z2;
            this.f = onClickListener;
            this.f2635a = z3;
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Suggestion_MemberPlanSection";
    }
}
