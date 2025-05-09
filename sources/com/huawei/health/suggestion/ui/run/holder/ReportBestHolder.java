package com.huawei.health.suggestion.ui.run.holder;

import android.content.Context;
import android.view.View;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.R;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.ffz;
import defpackage.ggl;
import defpackage.gib;
import defpackage.moe;
import defpackage.moi;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class ReportBestHolder extends BaseReportHolder<a> {
    private final HealthTextView b;
    private final HealthTextView c;
    private final View e;

    public ReportBestHolder(View view) {
        super(view);
        this.c = (HealthTextView) view.findViewById(R.id.sug_tv_name);
        this.b = (HealthTextView) view.findViewById(R.id.sug_tv_value);
        this.e = view.findViewById(R.id.sug_divider);
    }

    @Override // com.huawei.health.suggestion.ui.run.holder.BaseReportHolder
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void bindDataToView(a aVar) {
        this.c.setText(aVar.d);
        this.b.setText(aVar.f3369a);
        this.e.setVisibility(aVar.b ? 8 : 0);
    }

    public static List<a> d(Context context, Plan plan, PlanStat planStat) {
        ArrayList arrayList = new ArrayList(10);
        if (plan.acquireType() == 0) {
            c(context, planStat, arrayList);
        } else {
            e(context, planStat, arrayList);
        }
        if (arrayList.size() != 0) {
            ((a) arrayList.get(arrayList.size() - 1)).b = true;
        }
        return arrayList;
    }

    private static void c(Context context, PlanStat planStat, List<a> list) {
        list.add(new a(R.string._2130848416_res_0x7f022aa0, c(planStat)));
        list.add(new a(R.string._2130848417_res_0x7f022aa1, b(planStat)));
        a(R.string._2130848418_res_0x7f022aa2, planStat.getBestRecordForOneKm(), list);
        a(R.string._2130848419_res_0x7f022aa3, planStat.getBestRecordForFiveKm(), list);
        a(R.string._2130848420_res_0x7f022aa4, planStat.getBestRecordForTenKm(), list);
        a(R.string._2130848421_res_0x7f022aa5, planStat.getBestRecordForHalfMarathon(), list);
        a(R.string._2130848422_res_0x7f022aa6, planStat.getBestRecordForMarathon(), list);
    }

    private static void e(Context context, PlanStat planStat, List<a> list) {
        list.add(new a(R.string._2130848423_res_0x7f022aa7, b(context, planStat)));
        list.add(new a(R.string._2130848424_res_0x7f022aa8, d(context, planStat)));
        list.add(new a(R.string._2130848425_res_0x7f022aa9, a(planStat)));
    }

    private static String c(PlanStat planStat) {
        float farthestRunning = planStat.getFarthestRunning();
        return ffy.b(ffz.c(), (int) farthestRunning, moe.d(moe.g(farthestRunning)));
    }

    private static String b(PlanStat planStat) {
        return ggl.a(new Date(gib.g(planStat.getLongestRunning())), Constants.TIME_FORMAT_WITHOUT_MILLS);
    }

    private static String c(int i) {
        return ggl.a(new Date(gib.g(i)), Constants.TIME_FORMAT_WITHOUT_MILLS);
    }

    private static void a(int i, int i2, List<a> list) {
        if (i2 != 0) {
            list.add(new a(i, c(i2)));
        }
    }

    private static String b(Context context, PlanStat planStat) {
        return moi.e(context, R.string._2130837534_res_0x7f02001e, moe.g(planStat.getLongestTimePerWeek()));
    }

    private static String d(Context context, PlanStat planStat) {
        return moi.e(context, R.string._2130837535_res_0x7f02001f, moe.d(planStat.getMostCaloriePerWeek()));
    }

    private static String a(PlanStat planStat) {
        return UnitUtil.e(planStat.getHighestCompleteRate(), 2, 1);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        String f3369a;
        boolean b;
        int d;

        public a(int i, String str) {
            this.d = i;
            this.f3369a = str;
        }
    }
}
