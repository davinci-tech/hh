package com.huawei.health.suggestion.ui.run.holder;

import android.content.Context;
import android.view.View;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.health.R;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.ffz;
import defpackage.gdr;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class ReportFootHolder extends BaseReportHolder<b> {
    private CustomTextAlertDialog b;
    private final HealthTextView d;
    private final HealthTextView e;

    public static class b {
        CharSequence d;
        CharSequence e;
    }

    public ReportFootHolder(View view) {
        super(view);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.sug_tv_improved);
        this.d = healthTextView;
        this.e = (HealthTextView) view.findViewById(R.id.sug_tv_recommended);
        b(view.getContext());
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.holder.ReportFootHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ReportFootHolder.this.b.show();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.run.holder.BaseReportHolder
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void bindDataToView(b bVar) {
        if (bVar.d == null) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
            this.d.setText(bVar.d);
        }
        this.e.setText(bVar.e);
    }

    public static b d(Context context, Plan plan, PlanStat planStat, PlanRecord planRecord) {
        if (context == null) {
            LogUtil.h("Suggestion_ReportFootHolder", "context == null");
            return null;
        }
        b bVar = new b();
        bVar.d = e(context, plan, planStat);
        bVar.e = d(context, plan, planRecord);
        return bVar;
    }

    private static CharSequence d(Context context, Plan plan, PlanRecord planRecord) {
        int a2;
        List<String> c;
        if (context == null || plan == null || planRecord == null) {
            LogUtil.h("Suggestion_ReportFootHolder", "context == null || plan == null || planRecord == null");
            return null;
        }
        if (plan.acquireType() == 0) {
            a2 = gdr.b(plan.acquireGoal());
            c = gdr.d(context);
        } else {
            a2 = gdr.a(plan.acquireType(), plan.getDifficulty());
            c = gdr.c(context);
        }
        if (gdr.b(planRecord.acquireFinishRate()) >= 4) {
            a2++;
        }
        String str = c.get(Math.min(c.size() - 1, Math.max(0, a2)));
        return ffy.awT_(context, str, ffy.d(context.getApplicationContext(), R.string._2130848426_res_0x7f022aaa, str), R.style.sug_report_smasize, R.style.sug_report_bigsize);
    }

    private static CharSequence e(Context context, Plan plan, PlanStat planStat) {
        if (context == null || plan == null || planStat == null) {
            LogUtil.h("Suggestion_ReportFootHolder", "context == null || plan == null || planStat == null");
            return null;
        }
        if (plan.acquireType() != 0) {
            return null;
        }
        int a2 = a(planStat);
        LogUtil.a("Suggestion_ReportFootHolder", "improved:", String.valueOf(a2));
        if (a2 <= 0) {
            return null;
        }
        return ffy.awR_(context, ffy.awT_(context, "\\d+%", ffy.d(context, R.string._2130848428_res_0x7f022aac, UnitUtil.e(a2, 1, 0), ffy.b(ffz.c(), 1, UnitUtil.e(1.0d, 1, 0))) + Constants.LINK, R.style.sug_report_key, R.style.sug_report_smasize), Constants.LINK, R.drawable._2131431669_res_0x7f0b10f5);
    }

    private static int a(PlanStat planStat) {
        if (planStat != null) {
            float bestRecordForFirstOneKm = planStat.getBestRecordForFirstOneKm();
            float bestRecordForOneKm = planStat.getBestRecordForOneKm();
            if (bestRecordForFirstOneKm > 1.0E-4f) {
                return (int) (100.0f - ((bestRecordForOneKm * 100.0f) / bestRecordForFirstOneKm));
            }
        }
        return 0;
    }

    private void b(Context context) {
        String b2 = ffy.b(ffz.c(), 1, UnitUtil.e(1.0d, 1, 0));
        this.b = new CustomTextAlertDialog.Builder(context).b(R.string._2130848429_res_0x7f022aad).e(ffy.d(context, R.string._2130848430_res_0x7f022aae, b2, b2, b2)).cyU_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.holder.ReportFootHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
    }
}
