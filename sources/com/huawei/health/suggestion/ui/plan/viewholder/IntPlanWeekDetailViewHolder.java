package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntPlanWeekDetailViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarView;
import com.huawei.ui.commonui.calendarview.HealthMark;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ase;
import defpackage.fys;
import defpackage.fyw;
import defpackage.fzr;
import defpackage.gib;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.Calendar;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class IntPlanWeekDetailViewHolder extends AbsFitnessViewHolder<IntPlan> {

    /* renamed from: a, reason: collision with root package name */
    private Context f3292a;
    private int b;
    private int c;
    private ConstraintLayout d;
    private HealthCalendarView e;
    private int f;
    private fzr g;
    private int h;
    private HealthTextView i;
    private HealthButton j;
    private LinearLayout k;
    private IntPlan l;
    private LinearLayout m;
    private int n;
    private HealthCardView o;
    private HealthTextView p;
    private int q;
    private HealthTextView r;
    private LinearLayout s;
    private int t;
    private int v;
    private int y;

    public IntPlanWeekDetailViewHolder(View view) {
        super(view);
        this.q = 1;
        this.y = -1;
        this.v = -1;
        this.n = -1;
        this.f3292a = view.getContext();
        aJJ_(view);
    }

    private void aJJ_(View view) {
        this.s = (LinearLayout) view.findViewById(R.id.view_pager_layout);
        this.m = (LinearLayout) view.findViewById(R.id.week_pre);
        this.k = (LinearLayout) view.findViewById(R.id.week_next);
        this.p = (HealthTextView) view.findViewById(R.id.week_number);
        this.i = (HealthTextView) view.findViewById(R.id.level_name);
        HealthCalendarView healthCalendarView = (HealthCalendarView) view.findViewById(R.id.week_calendarView);
        this.e = healthCalendarView;
        healthCalendarView.b(true);
        this.e.setWeekViewScrollable(false);
        this.e.setModeOnlyWeekView();
        if (LanguageUtil.bc(this.f3292a)) {
            ((ImageView) view.findViewById(R.id.arrow_left)).setImageDrawable(nrz.cKn_(this.f3292a, R.drawable._2131427841_res_0x7f0b0201));
            ((ImageView) view.findViewById(R.id.arrow_right)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        this.o = (HealthCardView) view.findViewById(R.id.member_expiration_card);
        this.r = (HealthTextView) view.findViewById(R.id.remind_follow_the_plan);
        HealthButton healthButton = (HealthButton) this.itemView.findViewById(R.id.immedate_renewal);
        this.j = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.IntPlanWeekDetailViewHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (view2.getId() == R.id.immedate_renewal) {
                    JumpUtil.h(IntPlanWeekDetailViewHolder.this.f3292a);
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        view.findViewById(R.id.fitness_sub_tab_layout).setVisibility(8);
        aJI_(view);
        LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "init viewHolder !!!");
        h();
    }

    private void aJI_(View view) {
        this.d = (ConstraintLayout) view.findViewById(R.id.circle_constraintLayout);
    }

    private void h() {
        this.m.setClickable(true);
        this.k.setClickable(true);
        this.m.setOnClickListener(new View.OnClickListener() { // from class: gbb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntPlanWeekDetailViewHolder.this.aJK_(view);
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() { // from class: gba
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntPlanWeekDetailViewHolder.this.aJL_(view);
            }
        });
    }

    public /* synthetic */ void aJK_(View view) {
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aJL_(View view) {
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void init(IntPlan intPlan) {
        if (intPlan != this.l) {
            this.l = intPlan;
            this.e.d(false);
            this.t = intPlan.getMetaInfo().getWeekCount();
            f();
            j();
            a();
            this.d.setVisibility(8);
            this.g = new fzr(this.f3292a, this.s);
        }
        f();
        k();
        i();
        m();
        if (fyw.t(intPlan)) {
            this.o.setVisibility(0);
            this.s.setVisibility(8);
        } else {
            this.o.setVisibility(8);
            this.s.setVisibility(0);
            b();
        }
    }

    public void b() {
        this.g.e(this.l);
        g();
    }

    private void f() {
        LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "startTime=", Long.valueOf(this.l.getPlanTimeInfo().getBeginDate() * 1000), "  currentTime=", Long.valueOf(System.currentTimeMillis()));
        int g = ase.g(this.l);
        this.h = g;
        if (g > 0) {
            g = 1;
        }
        this.n = g;
        this.f = gib.d(Calendar.getInstance().get(7));
        LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "mCurrentWeekOrder=", Integer.valueOf(this.h), "  mCurrentDayOrder=", Integer.valueOf(this.f));
    }

    private void j() {
        this.b = this.h;
        m();
        this.c = gib.d(Calendar.getInstance().get(7));
        LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "mCalenderWeekIndex=", Integer.valueOf(this.b), "  mCalenderDayIndex=", Integer.valueOf(this.c));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (!fys.a(this.l, this.b, this.c)) {
            this.g.f();
            if (this.b <= this.n) {
                this.g.g();
                return;
            }
            return;
        }
        this.r.setVisibility(8);
        int i = this.b;
        int i2 = this.h;
        if (i < i2) {
            this.q = 0;
        } else if (i > i2) {
            this.q = 3;
            if (this.l.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
                this.r.setVisibility(0);
                this.r.setText(R.string._2130848695_res_0x7f022bb7);
            } else {
                this.r.setVisibility(8);
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            int d = gib.d(calendar.get(7));
            calendar.setTimeInMillis(this.l.getPlanTimeInfo().getBeginDate() * 1000);
            int d2 = gib.d(calendar.get(7));
            LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "startDay=", Integer.valueOf(d2), "currentDay= ", Integer.valueOf(d));
            if (this.h == 1 && d2 > d) {
                this.q = 3;
            } else {
                this.q = 1;
            }
            l();
        }
        if (this.l.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
            this.q = 1;
        }
        this.g.e(this.q);
        this.g.e(this.b, this.c, null, 0);
    }

    private void l() {
        fys.c(this.l, this.b, this.c, new IBaseResponseCallback() { // from class: gbc
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                IntPlanWeekDetailViewHolder.this.a(i, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, Object obj) {
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            HandlerExecutor.e(new Runnable() { // from class: gbd
                @Override // java.lang.Runnable
                public final void run() {
                    IntPlanWeekDetailViewHolder.this.e();
                }
            });
        }
    }

    public /* synthetic */ void e() {
        this.r.setText(R.string._2130848704_res_0x7f022bc0);
        this.r.setVisibility(0);
    }

    private void a() {
        this.e.setOnCalendarSelectListener(new HealthCalendarView.OnCalendarSelectListener() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.IntPlanWeekDetailViewHolder.5
            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
            public void onCalendarOutOfRange(HealthCalendar healthCalendar) {
            }

            @Override // com.huawei.ui.commonui.calendarview.HealthCalendarView.OnCalendarSelectListener
            public void onCalendarSelect(HealthCalendar healthCalendar, boolean z) {
                int week = healthCalendar.getWeek();
                IntPlanWeekDetailViewHolder.this.c = gib.d(week);
                if (week != IntPlanWeekDetailViewHolder.this.y || IntPlanWeekDetailViewHolder.this.c != IntPlanWeekDetailViewHolder.this.v) {
                    IntPlanWeekDetailViewHolder.this.y = week;
                    IntPlanWeekDetailViewHolder intPlanWeekDetailViewHolder = IntPlanWeekDetailViewHolder.this;
                    intPlanWeekDetailViewHolder.v = intPlanWeekDetailViewHolder.c;
                    LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "onCalendarSelect calenderDayIndex ", Integer.valueOf(IntPlanWeekDetailViewHolder.this.c), " calenderWeekIndex", Integer.valueOf(IntPlanWeekDetailViewHolder.this.b));
                    if (!fyw.t(IntPlanWeekDetailViewHolder.this.l)) {
                        IntPlanWeekDetailViewHolder.this.g();
                    }
                    LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "onCalendarSelect end");
                    return;
                }
                LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "onCalendarSelect repeat");
            }
        });
    }

    private void k() {
        if (this.b > 0) {
            Resources resources = this.f3292a.getResources();
            int i = this.t;
            this.p.setText(this.f3292a.getResources().getString(R.string._2130844893_res_0x7f021cdd, Integer.valueOf(this.b), resources.getQuantityString(R.plurals._2130903311_res_0x7f03010f, i, Integer.valueOf(i))));
        } else {
            this.p.setText(this.f3292a.getResources().getString(R.string._2130848736_res_0x7f022be0, Integer.valueOf(1 - this.b)));
        }
        IntWeekPlan weekInfoByOrder = this.l.getWeekInfoByOrder(this.b);
        if (weekInfoByOrder != null) {
            this.i.setText(weekInfoByOrder.getWeekPeriod());
        } else {
            this.i.setText("");
        }
    }

    private void i() {
        this.e.a();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < this.l.getMetaInfo().getWeekCount(); i++) {
            IntWeekPlan weekInfoByIdx = this.l.getWeekInfoByIdx(i);
            if (weekInfoByIdx == null) {
                LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "weekInfo is null");
            } else {
                for (int i2 = 0; i2 < weekInfoByIdx.getDayCount(); i2++) {
                    IntDayPlan dayByIdx = weekInfoByIdx.getDayByIdx(i2);
                    if (dayByIdx != null && (fys.e(dayByIdx, IntAction.ActionType.WORKOUT) || fys.e(dayByIdx, IntAction.ActionType.RUN))) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(gib.a(this.l.getPlanTimeInfo().getBeginDate() * 1000, weekInfoByIdx.getWeekOrder() - 1, dayByIdx.getDayOrder()));
                        int i3 = calendar.get(1);
                        int i4 = calendar.get(2) + 1;
                        int i5 = calendar.get(5);
                        HealthCalendar b = b(i3, i4, i5);
                        HealthMark healthMark = new HealthMark(HealthMark.MarkType.COLOR);
                        healthMark.b(this.f3292a.getResources().getColor(R.color._2131296560_res_0x7f090130));
                        b.addMark(healthMark);
                        if (dayByIdx.getPunchFlag() == 1) {
                            HealthMark healthMark2 = new HealthMark(HealthMark.MarkType.DRAWABLE);
                            healthMark2.cxz_(this.f3292a.getDrawable(R.drawable._2131427734_res_0x7f0b0196));
                            healthMark2.d(nsn.c(this.f3292a, 7.0f));
                            b.addMark(healthMark2);
                            LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "mark checkIn month ", Integer.valueOf(i4), " day ", Integer.valueOf(i5));
                        }
                        hashMap.put(b.toString(), b);
                    }
                }
            }
        }
        this.e.b(hashMap);
    }

    private HealthCalendar b(int i, int i2, int i3) {
        HealthCalendar healthCalendar = new HealthCalendar();
        healthCalendar.setYear(i);
        healthCalendar.setMonth(i2);
        healthCalendar.setDay(i3);
        return healthCalendar;
    }

    private void m() {
        if (this.b <= this.n) {
            this.m.setVisibility(4);
        } else {
            this.m.setVisibility(0);
        }
        if (this.b >= this.t) {
            this.k.setVisibility(4);
        } else {
            this.k.setVisibility(0);
        }
    }

    private void a(int i) {
        m();
        k();
        Calendar calendar = Calendar.getInstance();
        long beginDate = this.l.getPlanTimeInfo().getBeginDate();
        int i2 = this.b;
        if (i2 == this.h) {
            i = this.f;
        }
        calendar.setTimeInMillis(gib.a(beginDate * 1000, i2 - 1, i));
        int i3 = calendar.get(1);
        int i4 = calendar.get(2);
        int i5 = calendar.get(5);
        this.e.e(b(i3, i4 + 1, i5), true, true);
        g();
        LogUtil.a("Suggestion_PlanWeekDetailViewHolder", "handlePreviousWeekClick day:", Integer.valueOf(i5));
    }

    private void d() {
        this.b--;
        a(7);
    }

    private void c() {
        this.b++;
        a(1);
    }
}
