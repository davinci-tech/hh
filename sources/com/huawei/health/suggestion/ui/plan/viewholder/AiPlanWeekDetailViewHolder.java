package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amap.api.maps.model.MyLocationStyle;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.plan.fragment.AiFatReductionPlanFragment;
import com.huawei.health.suggestion.ui.plan.fragment.DietDetailsFragment;
import com.huawei.health.suggestion.ui.plan.fragment.DietRecordFragment;
import com.huawei.health.suggestion.ui.plan.fragment.WorkoutDetailsFragment;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.calendarview.HealthMark;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.ruler.circlescaleruler.CircleScaleRuler;
import com.huawei.ui.commonui.ruler.circlescaleruler.HealthCircleScaleRuler;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ase;
import defpackage.dpe;
import defpackage.ffy;
import defpackage.fit;
import defpackage.fiu;
import defpackage.fiy;
import defpackage.fiz;
import defpackage.fjf;
import defpackage.fuo;
import defpackage.fys;
import defpackage.fyv;
import defpackage.fyw;
import defpackage.fzr;
import defpackage.gib;
import defpackage.grz;
import defpackage.gsd;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nkw;
import defpackage.nqg;
import defpackage.nqx;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.qts;
import defpackage.quh;
import defpackage.smy;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class AiPlanWeekDetailViewHolder extends AbsFitnessViewHolder<IntPlan> {

    /* renamed from: a, reason: collision with root package name */
    private DietDetailsFragment f3282a;
    private HealthTextView aa;
    private boolean ab;
    private HealthButton ac;
    private HealthButton ad;
    private long ae;
    private qts af;
    private HealthCardView ag;
    private LinearLayout ah;
    private int ai;
    private LinearLayout aj;
    private LinearLayout ak;
    private final HealthRecycleView al;
    private IntPlan am;
    private HealthCircleScaleRuler an;
    private HealthSubTabWidget ao;
    private Map<Integer, Integer> ap;
    private int aq;
    private boolean ar;
    private HealthViewPager as;
    private qts at;
    private int au;
    private HealthTextView av;
    private WorkoutDetailsFragment aw;
    private final Handler b;
    private ConstraintLayout c;
    private LinearLayout d;
    private AiFatReductionPlanFragment e;
    private LinearLayout f;
    private int g;
    private final nqg h;
    private int i;
    private HealthCalendarView j;
    private HealthImageView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthBubbleLayout n;
    private HealthImageView o;
    private int p;
    private HealthTextView q;
    private HealthTextView r;
    private int s;
    private Context t;
    private FragmentManager u;
    private DietRecordFragment v;
    private fuo w;
    private nqx x;
    private LinearLayout y;
    private boolean z;

    public AiPlanWeekDetailViewHolder(View view, FragmentManager fragmentManager, HealthRecycleView healthRecycleView, fuo fuoVar) {
        super(view);
        this.b = new Handler(Looper.getMainLooper());
        this.h = new nqg(false);
        this.ap = new HashMap(16);
        this.au = -1;
        this.ai = 1;
        this.t = view.getContext();
        this.u = fragmentManager;
        this.al = healthRecycleView;
        this.w = fuoVar;
        aJy_(view);
    }

    private void aJA_(View view) {
        ViewGroup.LayoutParams layoutParams = this.ak.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(20);
            layoutParams2.setMarginEnd(0);
            this.ak.setLayoutParams(layoutParams2);
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.week_text);
        linearLayout.setGravity(1);
        ViewGroup.LayoutParams layoutParams3 = linearLayout.getLayoutParams();
        if (layoutParams3 instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
            layoutParams4.addRule(14);
            layoutParams4.setMarginEnd(0);
            linearLayout.setLayoutParams(layoutParams4);
        }
    }

    private void aJy_(View view) {
        this.ak = (LinearLayout) view.findViewById(R.id.week_pre);
        this.ah = (LinearLayout) view.findViewById(R.id.week_next);
        aJA_(view);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.week_number);
        this.av = healthTextView;
        healthTextView.setAlpha(0.6f);
        this.av.setTextSize(14.0f);
        view.findViewById(R.id.level_name).setVisibility(8);
        this.d = (LinearLayout) view.findViewById(R.id.fitness_sub_tab_layout);
        this.ao = (HealthSubTabWidget) view.findViewById(R.id.work_diet_detail_tab);
        this.as = (HealthViewPager) view.findViewById(R.id.work_diet_detail_vp);
        aJv_(view);
        this.f = (LinearLayout) view.findViewById(R.id.ai_fat_reduction_program);
        this.ag = (HealthCardView) view.findViewById(R.id.member_expiration_card);
        this.aj = (LinearLayout) view.findViewById(R.id.view_pager_layout);
        this.ac = (HealthButton) this.itemView.findViewById(R.id.immedate_renewal);
        this.ad = (HealthButton) this.itemView.findViewById(R.id.immedate_renewal_older);
        Drawable cKl_ = nrz.cKl_(BaseApplication.e(), R.drawable._2131427841_res_0x7f0b0201);
        if (cKl_ == null) {
            ReleaseLogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "initViewHolder drawable is null");
        } else {
            ((ImageView) view.findViewById(R.id.arrow_left)).setImageDrawable(cKl_);
        }
        if (nsn.r()) {
            this.ac.setVisibility(8);
            this.ad.setVisibility(0);
            this.ad.setOnClickListener(new View.OnClickListener() { // from class: gah
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AiPlanWeekDetailViewHolder.this.aJE_(view2);
                }
            });
        } else {
            this.ad.setVisibility(8);
            this.ac.setVisibility(0);
            this.ac.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (view2.getId() == R.id.immedate_renewal) {
                        JumpUtil.e(AiPlanWeekDetailViewHolder.this.am);
                    }
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
        this.ab = ase.f();
        p();
        aJx_(view);
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "init viewHolder !!!");
        k();
        view.setOnTouchListener(new View.OnTouchListener() { // from class: gaj
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return AiPlanWeekDetailViewHolder.this.aJF_(view2, motionEvent);
            }
        });
    }

    public /* synthetic */ void aJE_(View view) {
        if (view.getId() == R.id.immedate_renewal_older) {
            JumpUtil.e(this.am);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ boolean aJF_(View view, MotionEvent motionEvent) {
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "initViewHolder onTouch tempView ", view, " motionEvent ", motionEvent, " performClick ", Boolean.valueOf(view != null ? view.performClick() : false));
        HealthBubbleLayout healthBubbleLayout = this.n;
        if (healthBubbleLayout != null && healthBubbleLayout.getVisibility() == 0) {
            this.n.setVisibility(8);
        }
        return false;
    }

    private void aJv_(View view) {
        this.j = (HealthCalendarView) view.findViewById(R.id.week_calendarView);
        Context e = BaseApplication.e();
        Resources resources = e.getResources();
        if (!LanguageUtil.h(e)) {
            this.j.setDayTextSize(resources.getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446));
        }
        this.j.b(true);
        this.j.a(true);
        this.j.setWeekStart(2);
        this.j.setIsSelectFutureDate(true);
        this.j.setIsSetGrayFutureDate(true);
        this.j.setSelectGrayDate(false);
        this.j.setMarkViewSize(nsn.c(this.t, 24.0f));
        this.j.setMarkTopMargin(nsn.c(this.t, 4.0f));
        this.j.setIsWeekDayReplaceDate(true);
        this.j.setModeOnlyWeekView();
        ViewGroup.LayoutParams layoutParams = this.j.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.topMargin = -resources.getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
            layoutParams2.bottomMargin = 0;
            this.j.setLayoutParams(layoutParams2);
        }
    }

    private void aJx_(View view) {
        this.an = (HealthCircleScaleRuler) view.findViewById(R.id.sug_layout_plan_week_detail_health_circle_scale_ruler);
        dpe.c(this.h, false);
        this.an.setCircleScaleRulerData(this.h);
        this.y = (LinearLayout) view.findViewById(R.id.sug_layout_plan_week_detail_layout);
        this.o = (HealthImageView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_icon);
        this.q = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_title);
        this.k = (HealthImageView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_icon_tip);
        this.r = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_value);
        this.n = (HealthBubbleLayout) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_bubble);
        this.l = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_bubble_sport);
        this.m = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_consumption_bubble_resting);
        this.aa = (HealthTextView) view.findViewById(R.id.sug_layout_plan_week_detail_total_intake_value);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.circle_constraintLayout);
        this.c = constraintLayout;
        constraintLayout.setVisibility(8);
    }

    private void k() {
        this.ak.setClickable(true);
        this.ah.setClickable(true);
        this.ak.setOnClickListener(new View.OnClickListener() { // from class: gak
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiPlanWeekDetailViewHolder.this.aJB_(view);
            }
        });
        this.ah.setOnClickListener(new View.OnClickListener() { // from class: gal
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiPlanWeekDetailViewHolder.this.aJC_(view);
            }
        });
        this.an.setIconOnClickListener(new View.OnClickListener() { // from class: gai
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiPlanWeekDetailViewHolder.aJz_(view);
            }
        });
        aJw_(this.o);
        aJw_(this.q);
        aJw_(this.k);
        aJw_(this.r);
        aJw_(this.n);
    }

    public /* synthetic */ void aJB_(View view) {
        g();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aJC_(View view) {
        i();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aJz_(View view) {
        JumpUtil.e();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void aJw_(View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: gat
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AiPlanWeekDetailViewHolder.this.aJD_(view2);
            }
        });
    }

    public /* synthetic */ void aJD_(View view) {
        boolean z = (this.g * 7) + this.i > (this.p * 7) + this.s;
        ReleaseLogUtil.b("Suggestion_AiPlanWeekDetailViewHolder", "initConsumptionClick isFuture ", Boolean.valueOf(z), " mCalenderWeekIndex ", Integer.valueOf(this.g), " mCalenderDayIndex ", Integer.valueOf(this.i), " mCurrentWeekOrder ", Integer.valueOf(this.p), " mCurrentDayOrder ", Integer.valueOf(this.s));
        if (z) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            dpe.a(this.n, this.q.getMeasuredWidth(), this.l, this.m, this.af);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public HealthCalendarView d() {
        return this.j;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: c, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void init(final IntPlan intPlan) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: gas
                @Override // java.lang.Runnable
                public final void run() {
                    AiPlanWeekDetailViewHolder.this.a(intPlan);
                }
            });
            return;
        }
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "init plan is null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (jdl.ac(this.ae) && Math.abs(currentTimeMillis - this.ae) <= 1000) {
            LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "init mLastInitTimeMillis ", Long.valueOf(this.ae), " dumpStackTraceInfo ", DfxUtils.d(Thread.currentThread(), null));
            return;
        }
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "init mLastInitTimeMillis ", Long.valueOf(this.ae), " mPlan ", this.am, " plan ", intPlan);
        this.ae = currentTimeMillis;
        if (intPlan != this.am) {
            this.am = intPlan;
            this.j.d(false);
            this.aq = intPlan.getMetaInfo().getWeekCount();
            n();
            o();
            l();
            e();
        }
        n();
        s();
        m();
        y();
        r();
        c(true, false);
        t();
        ReleaseLogUtil.b("Suggestion_AiPlanWeekDetailViewHolder", "init mCalenderWeekIndex ", Integer.valueOf(this.g), " mCurrentWeekOrder ", Integer.valueOf(this.p), " mCalenderDayIndex ", Integer.valueOf(this.i), " mCurrentDayOrder ", Integer.valueOf(this.s));
        if (this.g == this.p) {
            List<quh> d = gsd.d();
            LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "init dietRecordCacheList ", d);
            a(d, true);
        }
        if (this.i == this.s) {
            quh e = gsd.e();
            LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "init dayDietRecord ", e);
            if (e != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(e);
                a((List<quh>) arrayList, true);
                e(e.d());
            }
        }
    }

    private void n() {
        long beginDate = this.am.getPlanTimeInfo().getBeginDate() * 1000;
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "startTime=", Long.valueOf(beginDate), "  currentTime=", Long.valueOf(currentTimeMillis));
        int e = gib.e(beginDate, currentTimeMillis) + 1;
        this.p = e;
        this.ai = e <= 0 ? e : 1;
        this.s = gib.d(Calendar.getInstance().get(7));
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "mCurrentWeekOrder=", Integer.valueOf(this.p), "  mCurrentDayOrder=", Integer.valueOf(this.s));
    }

    private void o() {
        this.g = this.p;
        y();
        this.i = gib.d(Calendar.getInstance().get(7));
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "mCalenderWeekIndex=", Integer.valueOf(this.g), " mCalenderDayIndex=", Integer.valueOf(this.i));
    }

    private void l() {
        this.j.setOnCalendarSelectListener(new HealthCalendarView.OnCalendarSelectListener() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder.4
            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
            public void onCalendarOutOfRange(HealthCalendar healthCalendar) {
            }

            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
            public void onCalendarSelect(HealthCalendar healthCalendar, boolean z) {
                AiPlanWeekDetailViewHolder.this.i = gib.d(healthCalendar.getWeek());
                int e = gib.e(AiPlanWeekDetailViewHolder.this.am.getPlanTimeInfo().getBeginDate() * 1000, healthCalendar.transformCalendar().getTimeInMillis());
                AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder = AiPlanWeekDetailViewHolder.this;
                aiPlanWeekDetailViewHolder.ar = (z || e + 1 == aiPlanWeekDetailViewHolder.g) ? false : true;
                if (AiPlanWeekDetailViewHolder.this.i == AiPlanWeekDetailViewHolder.this.au && AiPlanWeekDetailViewHolder.this.g == e + 1) {
                    LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "onCalendarSelect repeat");
                    return;
                }
                AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder2 = AiPlanWeekDetailViewHolder.this;
                aiPlanWeekDetailViewHolder2.au = aiPlanWeekDetailViewHolder2.i;
                AiPlanWeekDetailViewHolder.this.g = e + 1;
                LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "onCalendarSelect calenderDayIndex ", Integer.valueOf(AiPlanWeekDetailViewHolder.this.i), " calenderWeekIndex", Integer.valueOf(AiPlanWeekDetailViewHolder.this.g));
                if (AiPlanWeekDetailViewHolder.this.ar) {
                    AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder3 = AiPlanWeekDetailViewHolder.this;
                    aiPlanWeekDetailViewHolder3.e(aiPlanWeekDetailViewHolder3.i);
                }
                AiPlanWeekDetailViewHolder.this.s();
                AiPlanWeekDetailViewHolder.this.c(true, false);
                LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "onCalendarSelect end");
            }
        });
        this.j.setOnWeekChangeListener(new HealthCalendarView.OnWeekChangeListener() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder.1
            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnWeekChangeListener
            public void onWeekChange(List<HealthCalendar> list) {
                Object[] objArr = new Object[2];
                objArr[0] = "onWeekChange enter ";
                objArr[1] = list == null ? null : Integer.valueOf(list.size());
                LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", objArr);
                AiPlanWeekDetailViewHolder.this.e(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HealthCalendar> list) {
        if (koq.b(list)) {
            return;
        }
        HealthCalendar healthCalendar = list.get(0);
        HealthCalendar healthCalendar2 = list.get(list.size() - 1);
        int b = DateFormatUtil.b(nkw.d(healthCalendar));
        int b2 = DateFormatUtil.b(nkw.c(healthCalendar2));
        int d = ase.d(this.am);
        if (d >= b && d <= b2) {
            this.ah.setVisibility(4);
            this.j.setForbiddenSwipeNextWeek(true);
        } else {
            this.ah.setVisibility(0);
            this.j.setForbiddenSwipeNextWeek(false);
        }
        int h = ase.h(this.am);
        if (h >= b && h <= b2) {
            this.ak.setVisibility(4);
            this.j.setForbiddenSwipePreWeek(true);
        } else {
            this.ak.setVisibility(0);
            this.j.setForbiddenSwipePreWeek(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        nsy.cMs_(this.av, UnitUtil.a(new Date(gib.e(this.am.getPlanTimeInfo().getBeginDate() * 1000, this.g, this.i)), 22), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        IntPlan intPlan = this.am;
        if (intPlan == null || intPlan.getPlanTimeInfo() == null) {
            ReleaseLogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "queryPlanDietRecord mPlan ", this.am);
            return;
        }
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gam
                @Override // java.lang.Runnable
                public final void run() {
                    AiPlanWeekDetailViewHolder.this.t();
                }
            });
            return;
        }
        long c = jdl.c(gib.e(this.am.getPlanTimeInfo().getBeginDate() * 1000, this.p, this.s), -1);
        final int h = ase.h(this.am);
        int b = DateFormatUtil.b(c);
        if (h > b) {
            ReleaseLogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "queryPlanDietRecord planStartDate ", Integer.valueOf(h), " endDate ", Integer.valueOf(b));
            return;
        }
        if (fyw.a()) {
            LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "queryPlanDietRecord isInSyncDataProgress");
            return;
        }
        int b2 = DateFormatUtil.b(jdl.c(c, 2, 0));
        final int max = Math.max(h, b2);
        ReleaseLogUtil.b("Suggestion_AiPlanWeekDetailViewHolder", "queryPlanDietRecord planStartDate ", Integer.valueOf(h), " startDate ", Integer.valueOf(max), " endDate ", Integer.valueOf(b), " weekStartDate ", Integer.valueOf(b2), " mCalenderWeekIndex ", Integer.valueOf(this.g), " mCalenderDayIndex ", Integer.valueOf(this.i));
        grz.b(max, b, new ResponseCallback() { // from class: gap
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                AiPlanWeekDetailViewHolder.this.b(i, (List) obj);
            }
        });
        if (h >= max) {
            return;
        }
        HandlerCenter.d().e(new Runnable() { // from class: gao
            @Override // java.lang.Runnable
            public final void run() {
                AiPlanWeekDetailViewHolder.this.c(h, max);
            }
        }, 3000L);
    }

    public /* synthetic */ void b(int i, List list) {
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "queryPlanDietRecord errorCode ", Integer.valueOf(i), " list ", list);
        a((List<quh>) list, false);
        fyv.a((List<quh>) list);
    }

    public /* synthetic */ void c(int i, int i2) {
        grz.b(i, i2, new ResponseCallback() { // from class: gaq
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i3, Object obj) {
                AiPlanWeekDetailViewHolder.this.d(i3, (List) obj);
            }
        });
    }

    public /* synthetic */ void d(int i, List list) {
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "queryPlanDietRecord errorCode ", Integer.valueOf(i), " list ", list);
        a((List<quh>) list, false);
        fyv.a((List<quh>) list);
    }

    private void m() {
        this.j.a();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < this.am.getMetaInfo().getWeekCount(); i++) {
            IntWeekPlan weekInfoByIdx = this.am.getWeekInfoByIdx(i);
            if (weekInfoByIdx == null) {
                LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "weekInfo is null");
            } else {
                for (int i2 = 1; i2 <= 7; i2++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(gib.a(this.am.getPlanTimeInfo().getBeginDate() * 1000, weekInfoByIdx.getWeekOrder() - 1, i2));
                    HealthMark healthMark = new HealthMark();
                    HealthCalendar e = e(calendar);
                    View cKs_ = nsf.cKs_(this.t, R.layout.ai_plan_calendar_circle_scale_ruler_layout, this.j, false);
                    if (cKs_ != null) {
                        CircleScaleRuler circleScaleRuler = (CircleScaleRuler) cKs_.findViewById(R.id.ai_plan_calendar_layout_circle_scale_ruler);
                        nqg nqgVar = new nqg(true);
                        nqgVar.b(R.color._2131296670_res_0x7f09019e);
                        nqgVar.c(R.color._2131296670_res_0x7f09019e);
                        circleScaleRuler.setCircleScaleRulerData(nqgVar);
                    }
                    healthMark.cxA_(cKs_);
                    healthMark.d(nsf.b(R.dimen._2131363063_res_0x7f0a04f7));
                    e.addMark(healthMark);
                    hashMap.put(e.toString(), e);
                }
            }
        }
        this.j.b(hashMap);
    }

    private void r() {
        HealthCalendarView healthCalendarView = this.j;
        if (healthCalendarView != null) {
            e(healthCalendarView.getCurrentWeekCalendars());
        }
    }

    private HealthCalendar e(Calendar calendar) {
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        HealthCalendar healthCalendar = new HealthCalendar();
        healthCalendar.setYear(i);
        healthCalendar.setMonth(i2 + 1);
        healthCalendar.setDay(i3);
        return healthCalendar;
    }

    private void a(final List<quh> list, final boolean z) {
        if (koq.b(list)) {
            return;
        }
        HandlerExecutor.e(new Runnable() { // from class: gag
            @Override // java.lang.Runnable
            public final void run() {
                AiPlanWeekDetailViewHolder.this.d(list, z);
            }
        });
    }

    public /* synthetic */ void d(List list, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            quh quhVar = (quh) it.next();
            if (quhVar != null) {
                long c = DateFormatUtil.c(quhVar.c());
                if (!z || jdl.f(currentTimeMillis, c)) {
                    calendar.setTimeInMillis(c);
                    b(e(calendar), quhVar);
                }
            }
        }
    }

    private void b(HealthCalendar healthCalendar, quh quhVar) {
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "updateCalendarMarkProgress calendarItem ", healthCalendar, " dietRecord ", quhVar);
        if (healthCalendar == null || quhVar == null) {
            return;
        }
        HealthMark healthMark = new HealthMark();
        View cKs_ = nsf.cKs_(this.t, R.layout.ai_plan_calendar_circle_scale_ruler_layout, this.j, false);
        if (cKs_ != null) {
            HealthTextView healthTextView = (HealthTextView) cKs_.findViewById(R.id.ai_plan_calendar_layout_text);
            CircleScaleRuler circleScaleRuler = (CircleScaleRuler) cKs_.findViewById(R.id.ai_plan_calendar_layout_circle_scale_ruler);
            nqg nqgVar = new nqg(true);
            int b = DateFormatUtil.b(healthCalendar.transformCalendar().getTimeInMillis());
            int b2 = DateFormatUtil.b(System.currentTimeMillis());
            int h = ase.h(this.am);
            LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "updateCalendarMarkProgress date ", Integer.valueOf(b), " todayDate ", Integer.valueOf(b2), " planStartDate ", Integer.valueOf(h));
            if (b > b2 || b < h) {
                healthTextView.setText("");
                nqgVar.c(0.0f);
                nqgVar.b(R.color._2131296670_res_0x7f09019e);
                nqgVar.c(R.color._2131296670_res_0x7f09019e);
            } else {
                qts d = quhVar.d();
                healthTextView.setText(UnitUtil.e(dpe.b(d), 1, 0));
                if (LanguageUtil.b(BaseApplication.e())) {
                    healthTextView.setTextDirection(4);
                }
                nqgVar.c(dpe.a(d));
            }
            circleScaleRuler.setCircleScaleRulerData(nqgVar);
        }
        healthMark.cxA_(cKs_);
        healthMark.d(nsf.b(R.dimen._2131363063_res_0x7f0a04f7));
        healthCalendar.addMark(healthMark);
        HashMap hashMap = new HashMap();
        hashMap.put(healthCalendar.toString(), healthCalendar);
        this.j.b(hashMap);
    }

    private HealthCalendar d(int i, int i2, int i3) {
        HealthCalendar healthCalendar = new HealthCalendar();
        healthCalendar.setYear(i);
        healthCalendar.setMonth(i2);
        healthCalendar.setDay(i3);
        return healthCalendar;
    }

    private void y() {
        if (this.g <= this.ai) {
            this.ak.setVisibility(4);
        } else {
            this.ak.setVisibility(0);
        }
        if (this.g >= this.aq) {
            this.ah.setVisibility(4);
        } else {
            this.ah.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        y();
        s();
        Calendar calendar = Calendar.getInstance();
        long beginDate = this.am.getPlanTimeInfo().getBeginDate();
        int i2 = this.g;
        if (i2 == this.p) {
            i = this.s;
        }
        calendar.setTimeInMillis(gib.a(beginDate * 1000, i2 - 1, i));
        int i3 = calendar.get(1);
        int i4 = calendar.get(2);
        int i5 = calendar.get(5);
        HealthCalendar d = d(i3, i4 + 1, i5);
        if (!this.ar) {
            this.j.e(d, true, true);
        }
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "handlePreviousWeekClick day:", Integer.valueOf(i5));
        c(true, false);
        this.ar = false;
    }

    private void g() {
        if (nsn.a(500)) {
            return;
        }
        this.g--;
        e(7);
    }

    private void i() {
        if (nsn.a(500)) {
            return;
        }
        this.g++;
        e(1);
    }

    private void p() {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.as.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder.5
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (i <= 2) {
                    if (AiPlanWeekDetailViewHolder.this.ap.size() > i) {
                        layoutParams.height = ((Integer) AiPlanWeekDetailViewHolder.this.ap.get(Integer.valueOf(i))).intValue();
                        LogUtil.c("Suggestion_AiPlanWeekDetailViewHolder", "position " + i + " " + AiPlanWeekDetailViewHolder.this.ap.get(Integer.valueOf(i)));
                        AiPlanWeekDetailViewHolder.this.as.setLayoutParams(layoutParams);
                        return;
                    }
                    return;
                }
                LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "onPageSelected position error:", Integer.valueOf(i));
            }
        });
        this.ap.put(0, 0);
        this.ap.put(1, 0);
        if (this.ab) {
            this.e = new AiFatReductionPlanFragment(this.w);
        } else {
            this.x = new nqx(this.u, this.as, this.ao);
            b();
        }
    }

    public void e(int i, int i2) {
        this.ap.put(Integer.valueOf(i), Integer.valueOf(i2));
        if (this.ao.getSelectedSubTabPostion() == i) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.height = i2;
            LogUtil.c("Suggestion_AiPlanWeekDetailViewHolder", "position " + i + " height " + i2);
            this.as.setLayoutParams(layoutParams);
        }
    }

    public void c(boolean z, boolean z2) {
        AiFatReductionPlanFragment aiFatReductionPlanFragment;
        if (fyw.t(this.am)) {
            this.an.setVisibility(8);
            this.y.setVisibility(8);
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            this.f.setVisibility(8);
            this.ag.setVisibility(0);
            this.aj.setVisibility(8);
            return;
        }
        if (!fys.a(this.am, this.g, this.i)) {
            this.d.setVisibility(8);
            this.f.setVisibility(8);
            this.an.setVisibility(8);
            this.y.setVisibility(8);
            this.c.setVisibility(8);
            this.ag.setVisibility(8);
            if (this.g <= this.ai) {
                this.aj.setVisibility(0);
                e();
                return;
            } else {
                this.aj.setVisibility(8);
                return;
            }
        }
        this.d.setVisibility(0);
        this.f.setVisibility(0);
        this.an.setVisibility(0);
        this.y.setVisibility(0);
        this.c.setVisibility(8);
        this.ag.setVisibility(8);
        this.aj.setVisibility(8);
        List<Integer> asList = Arrays.asList(Integer.valueOf(this.p), Integer.valueOf(this.g), Integer.valueOf(this.s), Integer.valueOf(this.i));
        if (this.ab && (aiFatReductionPlanFragment = this.e) != null) {
            aiFatReductionPlanFragment.c(this.t, this.am, this, asList, this.al);
            this.f.setVisibility(0);
            this.d.setVisibility(8);
        } else {
            WorkoutDetailsFragment workoutDetailsFragment = this.aw;
            if (workoutDetailsFragment != null) {
                workoutDetailsFragment.b(this.t, this, this.am, asList);
            }
        }
        if (fyw.a()) {
            return;
        }
        c();
        if (this.z) {
            q();
        }
        fys.e();
    }

    private void q() {
        boolean z = (this.g * 7) + this.i > (this.p * 7) + this.s;
        long e = gib.e(this.am.getPlanTimeInfo().getBeginDate() * 1000, this.g, this.i);
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "DietRecordFragment isFutureTime: ", Boolean.valueOf(z), ", currentSelectTime: ", Long.valueOf(e));
        DietRecordFragment dietRecordFragment = this.v;
        if (dietRecordFragment != null) {
            dietRecordFragment.d(z, e);
        }
    }

    public void e() {
        fzr fzrVar = new fzr(this.t, this.aj);
        fzrVar.e(this.am);
        fzrVar.g();
    }

    public void c() {
        boolean z = (this.g * 7) + this.i > (this.p * 7) + this.s;
        final long e = gib.e(this.am.getPlanTimeInfo().getBeginDate() * 1000, this.g, this.i);
        long e2 = gib.e(this.am.getPlanTimeInfo().getBeginDate() * 1000, this.p, this.s);
        if (!z) {
            e2 = e;
        }
        int b = DateFormatUtil.b(e2);
        ReleaseLogUtil.b("Suggestion_AiPlanWeekDetailViewHolder", "getDietRecord dayFormat ", Integer.valueOf(b), " mCalenderWeekIndex ", Integer.valueOf(this.g), " mCalenderDayIndex ", Integer.valueOf(this.i));
        grz.b(b, b, new ResponseCallback() { // from class: gan
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                AiPlanWeekDetailViewHolder.this.a(e, i, (List) obj);
            }
        });
    }

    public /* synthetic */ void a(final long j, int i, final List list) {
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "getDietRecord errorCode ", Integer.valueOf(i), " list ", list);
        HandlerExecutor.e(new Runnable() { // from class: gau
            @Override // java.lang.Runnable
            public final void run() {
                AiPlanWeekDetailViewHolder.this.c(list, j);
            }
        });
    }

    public /* synthetic */ void c(List list, long j) {
        if (koq.b(list)) {
            e((quh) null, j);
            e(this.at);
            return;
        }
        quh quhVar = (quh) list.get(0);
        if (quhVar == null) {
            e((quh) null, j);
            e(this.at);
            return;
        }
        this.at = quhVar.d();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        b(e(calendar), quhVar);
        e(quhVar, j);
        e(this.at);
        this.af = this.at;
        fyv.a((List<quh>) list);
    }

    private void e(qts qtsVar) {
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "refreshCaloricDeficit overview ", qtsVar);
        if (qtsVar == null) {
            return;
        }
        this.n.setVisibility(8);
        int i = (this.g * 7) + this.i;
        int i2 = (this.p * 7) + this.s;
        boolean z = i > i2;
        if (i == i2) {
            dpe.f(qtsVar);
        }
        dpe.c(this.h, qtsVar, z);
        this.an.setCircleScaleRulerData(this.h);
        dpe.d(this.an, qtsVar, z);
        dpe.a(this.r, qtsVar, z);
        dpe.b(this.aa, qtsVar, z);
    }

    private void c(long j, UiCallback<fiy> uiCallback) {
        if (this.z) {
            return;
        }
        qts qtsVar = this.at;
        int min = qtsVar != null ? Math.min(Math.max(1000, Math.round(qtsVar.c())), 10000) : 1000;
        fit fitVar = new fit();
        fitVar.e(String.valueOf(min));
        fitVar.c("1");
        new fjf().getFoodRecommendDetail(this.am.getPlanId(), DateFormatUtil.a(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD), fitVar, uiCallback);
    }

    /* renamed from: com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder$2, reason: invalid class name */
    public class AnonymousClass2 extends UiCallback<fiy> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f3284a;
        final /* synthetic */ quh c;
        final /* synthetic */ long e;

        AnonymousClass2(List list, quh quhVar, long j) {
            this.f3284a = list;
            this.c = quhVar;
            this.e = j;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "getRecommendFoodForTomorrow errorCode ", Integer.valueOf(i), " errorInfo ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(fiy fiyVar) {
            if (fiyVar != null) {
                AiPlanWeekDetailViewHolder.this.c(0, fiyVar.e(), new ArrayList(), this.f3284a);
                AiPlanWeekDetailViewHolder.this.c(1, fiyVar.c(), new ArrayList(), this.f3284a);
                AiPlanWeekDetailViewHolder.this.c(2, fiyVar.a(), new ArrayList(), this.f3284a);
                final List list = this.f3284a;
                final quh quhVar = this.c;
                final long j = this.e;
                HandlerExecutor.e(new Runnable() { // from class: gar
                    @Override // java.lang.Runnable
                    public final void run() {
                        AiPlanWeekDetailViewHolder.AnonymousClass2.this.b(list, quhVar, j);
                    }
                });
                return;
            }
            LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "getRecommendFoodForTomorrow data is null");
        }

        public /* synthetic */ void b(List list, quh quhVar, long j) {
            if (!AiPlanWeekDetailViewHolder.this.ab || AiPlanWeekDetailViewHolder.this.e == null) {
                return;
            }
            AiPlanWeekDetailViewHolder.this.e.a((List<List<fiu>>) list, quhVar, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(quh quhVar, List<List<fiu>> list, long j) {
        c(jdl.y(j), new AnonymousClass2(list, quhVar, j));
    }

    private List<List<fiu>> f() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new fiu());
        arrayList.add(arrayList2);
        arrayList.add(arrayList2);
        arrayList.add(arrayList2);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void e(final quh quhVar, final long j) {
        if (!ase.e()) {
            AiFatReductionPlanFragment aiFatReductionPlanFragment = this.e;
            if (aiFatReductionPlanFragment != null) {
                aiFatReductionPlanFragment.a(f(), quhVar, j);
                return;
            }
            return;
        }
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gae
                @Override // java.lang.Runnable
                public final void run() {
                    AiPlanWeekDetailViewHolder.this.e(quhVar, j);
                }
            });
        } else {
            c(j, new AnonymousClass10(quhVar, j));
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder$10, reason: invalid class name */
    public class AnonymousClass10 extends UiCallback<fiy> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ quh f3283a;
        final /* synthetic */ long c;

        AnonymousClass10(quh quhVar, long j) {
            this.f3283a = quhVar;
            this.c = j;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "getRecommendFoods errorCode ", Integer.valueOf(i), " errorInfo ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(fiy fiyVar) {
            if (fiyVar == null) {
                LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "getRecommendFoods data is null");
                return;
            }
            final ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            AiPlanWeekDetailViewHolder.this.c(0, fiyVar.e(), arrayList2, arrayList);
            AiPlanWeekDetailViewHolder.this.c(1, fiyVar.c(), arrayList2, arrayList);
            AiPlanWeekDetailViewHolder.this.c(2, fiyVar.a(), arrayList2, arrayList);
            final quh quhVar = this.f3283a;
            final long j = this.c;
            HandlerExecutor.e(new Runnable() { // from class: gav
                @Override // java.lang.Runnable
                public final void run() {
                    AiPlanWeekDetailViewHolder.AnonymousClass10.this.a(arrayList2, arrayList, quhVar, j);
                }
            });
        }

        public /* synthetic */ void a(List list, List list2, quh quhVar, long j) {
            if (AiPlanWeekDetailViewHolder.this.f3282a != null) {
                AiPlanWeekDetailViewHolder.this.f3282a.d(AiPlanWeekDetailViewHolder.this.am, list, list2, quhVar, DateFormatUtil.a(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD));
            }
            if (!AiPlanWeekDetailViewHolder.this.ab || AiPlanWeekDetailViewHolder.this.e == null) {
                return;
            }
            if (fyv.b(list2, quhVar, j)) {
                AiPlanWeekDetailViewHolder.this.a(quhVar, (List<List<fiu>>) list2, j);
            } else {
                AiPlanWeekDetailViewHolder.this.e.a((List<List<fiu>>) list2, quhVar, j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, List<fiu> list, List<String> list2, List<List<fiu>> list3) {
        if (koq.b(list)) {
            return;
        }
        list3.add(list);
        StringBuilder sb = new StringBuilder();
        Iterator<fiu> it = list.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            f += it.next().g();
        }
        if (i == 0) {
            sb.append(ffy.d(this.t, R.string._2130848649_res_0x7f022b89, ((int) f) + " "));
        } else if (i == 1) {
            sb.append(ffy.d(this.t, R.string._2130848650_res_0x7f022b8a, ((int) f) + " "));
        } else if (i == 2) {
            sb.append(ffy.d(this.t, R.string._2130848651_res_0x7f022b8b, ((int) f) + " "));
        }
        sb.append(BaseApplication.e().getResources().getString(R.string._2130848385_res_0x7f022a81));
        list2.add(sb.toString());
    }

    public void h() {
        FragmentManager fragmentManager;
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "replaceFragment mIsSupportNewAiFitnessPlan ", Boolean.valueOf(this.ab), " mFragmentManager ", this.u, " mAiFatReductionProgram ", this.f);
        if (!this.ab || (fragmentManager = this.u) == null || this.f == null) {
            return;
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.ai_fat_reduction_program, this.e);
        beginTransaction.commitAllowingStateLoss();
    }

    public void a() {
        AiFatReductionPlanFragment aiFatReductionPlanFragment = this.e;
        if (aiFatReductionPlanFragment != null) {
            aiFatReductionPlanFragment.e();
        }
    }

    public void b() {
        if (this.x == null) {
            LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "addSubTabsOnPlanVisible mFitnessTabsAdapter is null");
            return;
        }
        boolean o = Utils.o();
        this.z = o;
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "addSubTabsOnPlanVisible() mIsOversea: ", Boolean.valueOf(o));
        this.aw = new WorkoutDetailsFragment();
        this.x.c(this.ao.c(this.t.getResources().getString(R.string._2130848652_res_0x7f022b8c)), this.aw, true);
        if (!this.z) {
            smy c = this.ao.c(this.t.getResources().getString(R.string._2130848653_res_0x7f022b8d));
            DietDetailsFragment c2 = DietDetailsFragment.c();
            this.f3282a = c2;
            this.x.c(c, c2, false);
            this.f3282a.c(this);
        } else {
            smy c3 = this.ao.c(this.t.getResources().getString(R.string._2130845505_res_0x7f021f41));
            DietRecordFragment c4 = DietRecordFragment.c();
            this.v = c4;
            this.x.c(c3, c4, false);
            this.v.d(this);
        }
        this.as.setOffscreenPageLimit(this.ao.getSubTabCount());
    }

    public void j() {
        nqx nqxVar = this.x;
        if (nqxVar == null) {
            LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "removeSubTabsOnPlanFinish mFitnessTabsAdapter is null");
            return;
        }
        for (int count = nqxVar.getCount() - 1; count >= 0; count--) {
            this.x.a(count);
        }
        this.x.notifyDataSetChanged();
    }

    public void c(final List<fiu> list, final int i, final String str) {
        final fit fitVar = new fit();
        String valueOf = String.valueOf(1000);
        qts qtsVar = this.at;
        if (qtsVar != null) {
            valueOf = String.valueOf(Math.min(Math.max(1000, Math.round(qtsVar.c())), 10000));
        }
        LogUtil.c("Suggestion_AiPlanWeekDetailViewHolder", "mEnergy", valueOf);
        fitVar.e(valueOf);
        fitVar.c("1");
        LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "getRecommendFoods time recipesDate" + str);
        new fjf().getFoodRecommendDetail(this.am.getPlanId(), str, fitVar, new UiCallback<fiy>() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder.7
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                LogUtil.h("Suggestion_AiPlanWeekDetailViewHolder", "errorCode:" + i2 + MyLocationStyle.ERROR_INFO + str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(fiy fiyVar) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                int i2 = i;
                if (i2 == 0) {
                    fiyVar.d(list);
                } else if (i2 == 1) {
                    fiyVar.b(list);
                } else if (i2 == 2) {
                    fiyVar.c(list);
                }
                AiPlanWeekDetailViewHolder.this.c(0, fiyVar.e(), arrayList2, arrayList);
                AiPlanWeekDetailViewHolder.this.c(1, fiyVar.c(), arrayList2, arrayList);
                AiPlanWeekDetailViewHolder.this.c(2, fiyVar.a(), arrayList2, arrayList);
                AiPlanWeekDetailViewHolder.this.e(fiyVar, str, fitVar, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(fiy fiyVar, String str, fit fitVar, int i) {
        fjf fjfVar = new fjf();
        fiz fizVar = new fiz();
        fizVar.d(this.am.getPlanId());
        fizVar.a(fitVar);
        fizVar.c(i + 1);
        fizVar.e(2);
        fizVar.b(str);
        fizVar.d(fiyVar);
        fjfVar.saveReplaceFood(fizVar, new AnonymousClass9());
    }

    /* renamed from: com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder$9, reason: invalid class name */
    public class AnonymousClass9 extends UiCallback<String> {
        AnonymousClass9() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_AiPlanWeekDetailViewHolder", "saveReplaceFood errorCode:", Integer.valueOf(i), CloudParamKeys.INFO, str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            LogUtil.a("Suggestion_AiPlanWeekDetailViewHolder", "saveReplaceFood onSuccess:" + str);
            AiPlanWeekDetailViewHolder.this.b.post(new Runnable() { // from class: gaw
                @Override // java.lang.Runnable
                public final void run() {
                    AiPlanWeekDetailViewHolder.AnonymousClass9.this.b();
                }
            });
        }

        public /* synthetic */ void b() {
            AiPlanWeekDetailViewHolder.this.c(true, true);
        }
    }
}
