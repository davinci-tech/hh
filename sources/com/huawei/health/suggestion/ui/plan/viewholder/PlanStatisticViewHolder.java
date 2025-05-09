package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ffy;
import defpackage.ffz;
import defpackage.gge;
import defpackage.moe;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes4.dex */
public class PlanStatisticViewHolder extends AbsFitnessViewHolder<PlanStatistics> {

    /* renamed from: a, reason: collision with root package name */
    private static float f3299a = 20.3f;
    private static int b = 9;
    private static int c = 29;
    private static int d = 1;
    private static float e = 17.4f;
    private Context f;
    private HealthCardView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private LinearLayout l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private String p;
    private HealthSubHeader q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private RelativeLayout u;
    private HealthTextView x;
    private HealthTextView y;

    public PlanStatisticViewHolder(View view) {
        super(view);
        this.f = view.getContext();
        this.q = (HealthSubHeader) view.findViewById(R.id.plan_statistic_layout_title);
        this.l = (LinearLayout) view.findViewById(R.id.sug_plan_statistic_layout);
        this.s = (HealthTextView) view.findViewById(R.id.tv_plan_total_statistic);
        this.k = (HealthTextView) view.findViewById(R.id.tv_plan_total_statistic_unit);
        this.o = (HealthTextView) view.findViewById(R.id.tv_plan_total_history);
        if (LanguageUtil.bc(this.f)) {
            ImageView imageView = (ImageView) view.findViewById(R.id.img_plan_total_history_arrow);
            BitmapDrawable cKn_ = nrz.cKn_(this.f, R.drawable._2131429721_res_0x7f0b0959);
            cKn_.setBounds(0, 0, cKn_.getMinimumWidth(), cKn_.getMinimumHeight());
            imageView.setImageDrawable(cKn_);
        }
        this.u = (RelativeLayout) view.findViewById(R.id.sug_plan_statistic_time_layout);
        this.t = (HealthTextView) view.findViewById(R.id.sug_fragment_plan_statistic_time);
        this.r = (HealthTextView) view.findViewById(R.id.sug_fragment_plan_statistic_calories);
        this.x = (HealthTextView) view.findViewById(R.id.sug_fragment_plan_statistic_num);
        this.y = (HealthTextView) view.findViewById(R.id.tv_plan_total_num_unit);
        this.q.setMoreLayoutVisibility(8);
        this.q.setBackgroundColor(this.f.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        this.i = (HealthTextView) view.findViewById(R.id.duration_item);
        this.j = (HealthTextView) view.findViewById(R.id.calories_item);
        this.m = (HealthTextView) view.findViewById(R.id.plans_item);
        this.n = (HealthTextView) view.findViewById(R.id.tv_plan_total_time_unit);
        this.h = (HealthTextView) view.findViewById(R.id.tv_plan_total_calories_unit);
        this.g = (HealthCardView) view.findViewById(R.id.recycle_item);
        c();
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.PlanStatisticViewHolder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!nsn.o()) {
                    JumpUtil.e(PlanStatisticViewHolder.this.f, 2);
                    HashMap hashMap = new HashMap();
                    hashMap.put("click", "1");
                    gge.e("1120015", hashMap);
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                LogUtil.b("Suggestion_PlanStatisticViewHolder", "mStatisticLayout click too fast");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    private void c() {
        if (nsn.t()) {
            this.s.setTextSize(1, 43.5f);
            this.k.setTextSize(1, f3299a);
            this.o.setTextSize(1, f3299a);
            this.i.setTextSize(1, e);
            this.j.setTextSize(1, e);
            this.m.setTextSize(1, e);
            this.t.setTextSize(1, c);
            this.r.setTextSize(1, c);
            this.x.setTextSize(1, c);
            this.n.setTextSize(1, e);
            this.h.setTextSize(1, e);
            this.y.setTextSize(1, e);
            if (this.g.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.g.getLayoutParams();
                layoutParams.height = -2;
                layoutParams.width = -1;
                this.g.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        e();
    }

    private void e() {
        this.i.setAutoTextInfo(b, d, 2);
        this.j.setAutoTextInfo(b, d, 2);
        this.i.setAutoTextInfo(b, d, 2);
        this.m.setAutoTextInfo(b, d, 2);
        this.t.setAutoTextInfo(b, d, 2);
        this.r.setAutoTextInfo(b, d, 2);
        this.x.setAutoTextInfo(b, d, 2);
        this.n.setAutoTextInfo(b, d, 2);
        this.h.setAutoTextInfo(b, d, 2);
        this.y.setAutoTextInfo(b, d, 2);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void init(PlanStatistics planStatistics) {
        if (planStatistics == null) {
            LogUtil.h("Suggestion_PlanStatisticViewHolder", "PlanStatisticViewHolder data = null");
            return;
        }
        if (planStatistics.acquireType() == 2) {
            this.u.setVisibility(0);
            this.o.setText(this.f.getResources().getString(R.string._2130844187_res_0x7f021a1b));
            c(planStatistics);
        } else {
            this.u.setVisibility(8);
            this.o.setText(this.f.getResources().getString(R.string._2130845994_res_0x7f02212a));
            d(planStatistics);
        }
    }

    private void c(PlanStatistics planStatistics) {
        if (planStatistics.acquireTotalPlan() == 0) {
            return;
        }
        double totalDistance = planStatistics.getTotalDistance() / 1000.0d;
        if (UnitUtil.h()) {
            totalDistance = UnitUtil.e(totalDistance, 3);
        }
        this.s.setText(UnitUtil.e(totalDistance, 1, 2));
        this.r.setText(moe.i(moe.b(planStatistics.acquireCalorie())));
        this.x.setText(UnitUtil.e(planStatistics.acquireTotalPlan(), 1, 0));
        this.t.setText(moe.g(planStatistics.acquireDuration()));
        this.n.setText(ffy.b(R.plurals._2130903305_res_0x7f030109, (int) (planStatistics.acquireDuration() / 60000), ""));
        this.k.setText(ffy.b(ffz.c(), (int) totalDistance, ""));
        this.y.setText(ffy.b(R.plurals._2130903470_res_0x7f0301ae, planStatistics.acquireTotalPlan(), ""));
        this.h.setText(ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) moe.b(planStatistics.acquireCalorie()), ""));
    }

    private void d(PlanStatistics planStatistics) {
        String g = moe.g(planStatistics.acquireDuration());
        int acquireTotalPlan = planStatistics.acquireTotalPlan();
        this.s.setText(g);
        String i = moe.i(moe.b(planStatistics.acquireCalorie()));
        this.p = i;
        this.r.setText(i);
        this.x.setText(String.valueOf(acquireTotalPlan));
        this.k.setText(this.f.getResources().getString(R.string._2130841436_res_0x7f020f5c));
        this.y.setText(String.format(Locale.ENGLISH, this.f.getResources().getString(R.string._2130851537_res_0x7f0236d1), ""));
    }
}
