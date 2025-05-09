package com.huawei.health.suggestion.ui.run.holder;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.fitness.module.SugChart;
import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.ffz;
import defpackage.fqv;
import defpackage.ggl;
import defpackage.koq;
import defpackage.moe;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class ReportChartHolder extends BaseReportHolder<c> {
    private final SugChart c;
    private final HealthTextView d;
    private final HealthTextView e;

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        float f3370a;
        int b;
        int c;
        int d;
        int e;
        int[] f;
        int g;
        Calendar h;
        boolean i;
        int[] j;
        int k;
        int l;
        Date m;
        float n;
        float o;
        List<fqv> q;
        CharSequence r;
    }

    public ReportChartHolder(View view) {
        super(view);
        this.d = (HealthTextView) view.findViewById(R.id.sug_tv_title);
        this.e = (HealthTextView) view.findViewById(R.id.sug_tv_sub_title);
        this.c = (SugChart) view.findViewById(R.id.sug_chart);
    }

    @Override // com.huawei.health.suggestion.ui.run.holder.BaseReportHolder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void bindDataToView(c cVar) {
        if (cVar == null) {
            LogUtil.h("Suggestion_ReportChartHolder", "data == null");
            return;
        }
        this.d.setVisibility(cVar.i ? 0 : 8);
        this.e.setText(cVar.r);
        this.c.d(cVar.e);
        this.c.h(cVar.k);
        this.c.d(cVar.f3370a);
        this.c.i(cVar.l);
        this.c.b(cVar.g);
        this.c.a(cVar.c);
        this.c.e(cVar.b);
        this.c.c(cVar.d);
        this.c.b(cVar.f);
        this.c.d(cVar.q);
        this.c.b(cVar.o);
        this.c.c(cVar.n);
        this.c.d(cVar.j);
    }

    public static List<c> b(Context context, Plan plan, List<WorkoutRecord> list) {
        ArrayList arrayList = new ArrayList();
        c(context, arrayList, plan, list);
        if (arrayList.size() != 0) {
            ((c) arrayList.get(0)).i = true;
        }
        return arrayList;
    }

    public static void c(Context context, List<c> list, Plan plan, List<WorkoutRecord> list2) {
        try {
            a b = b(context, plan);
            if (plan.acquireType() == 0) {
                b(context, list, list2, b);
            } else {
                c(context, list, list2, b);
            }
        } catch (ParseException e) {
            LogUtil.b("Suggestion_ReportChartHolder", LogAnonymous.b((Throwable) e));
        }
    }

    private static void b(Context context, List<c> list, List<WorkoutRecord> list2, a aVar) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        b(context, aVar.d, arrayList, arrayList2);
        b(list, c(context, list2, aVar, arrayList));
        b(list, d(context, list2, aVar, arrayList2));
    }

    private static void c(Context context, List<c> list, List<WorkoutRecord> list2, a aVar) {
        b(list, a(context, list2, aVar));
    }

    private static c c(Context context, List<WorkoutRecord> list, a aVar, List<fqv> list2) {
        c cVar = new c();
        cVar.r = context.getString(R.string._2130848437_res_0x7f022ab5);
        cVar.e = 1;
        cVar.g = 30;
        cVar.k = context.getResources().getColor(R.color._2131299194_res_0x7f090b7a);
        cVar.c = context.getResources().getColor(R$color.textColorSecondary);
        cVar.d = context.getResources().getColor(R$color.textColorTertiary);
        cVar.l = context.getResources().getColor(R.color._2131299195_res_0x7f090b7b);
        cVar.b = context.getResources().getColor(R$color.textColorTertiary);
        cVar.f3370a = (int) context.getResources().getDimension(R.dimen._2131364966_res_0x7f0a0c66);
        cVar.f = new int[]{context.getResources().getColor(R.color._2131299195_res_0x7f090b7b), context.getResources().getColor(R.color._2131299196_res_0x7f090b7c)};
        c cVar2 = new c();
        cVar2.m = aVar.b;
        cVar2.h = null;
        List<fqv> b = b(context, list2, list, cVar2, 0);
        d(context, b);
        cVar.q = b;
        return cVar;
    }

    private static c d(Context context, List<WorkoutRecord> list, a aVar, List<fqv> list2) {
        c cVar = new c();
        cVar.m = aVar.b;
        cVar.h = null;
        List<fqv> b = b(context, list2, list, cVar, 2);
        if (!a(b)) {
            return null;
        }
        d(context, b);
        SpannableString awT_ = ffy.awT_(context, context.getString(R.string._2130848432_res_0x7f022ab0), context.getString(R.string._2130848431_res_0x7f022aaf), R.style.sug_report_vo2, R.style.sug_report_normal);
        c cVar2 = new c();
        cVar2.r = awT_;
        cVar2.e = 2;
        cVar2.g = 30;
        cVar2.o = (int) context.getResources().getDimension(R.dimen._2131365013_res_0x7f0a0c95);
        cVar2.n = (int) context.getResources().getDimension(R.dimen._2131365012_res_0x7f0a0c94);
        cVar2.c = context.getResources().getColor(R$color.textColorSecondary);
        cVar2.d = context.getResources().getColor(R$color.textColorSecondary);
        cVar2.l = context.getResources().getColor(R.color._2131299197_res_0x7f090b7d);
        cVar2.b = context.getResources().getColor(R$color.textColorTertiary);
        cVar2.f = new int[]{context.getResources().getColor(R.color._2131299198_res_0x7f090b7e), context.getResources().getColor(R.color._2131299199_res_0x7f090b7f)};
        cVar2.j = new int[]{context.getResources().getColor(R.color._2131299197_res_0x7f090b7d), context.getResources().getColor(R.color._2131299200_res_0x7f090b80)};
        cVar2.q = b;
        return cVar2;
    }

    private static c a(Context context, List<WorkoutRecord> list, a aVar) {
        c cVar = new c();
        cVar.r = context.getString(R.string._2130847440_res_0x7f0226d0);
        cVar.g = 30;
        cVar.k = context.getResources().getColor(R.color._2131299168_res_0x7f090b60);
        cVar.c = context.getResources().getColor(R$color.textColorSecondary);
        cVar.e = 1;
        cVar.d = context.getResources().getColor(R$color.textColorTertiary);
        cVar.l = context.getResources().getColor(R.color._2131299181_res_0x7f090b6d);
        cVar.b = context.getResources().getColor(R$color.textColorTertiary);
        cVar.f3370a = (int) context.getResources().getDimension(R.dimen._2131364966_res_0x7f0a0c66);
        cVar.f = new int[]{context.getResources().getColor(R.color._2131299171_res_0x7f090b63), context.getResources().getColor(R.color._2131299178_res_0x7f090b6a)};
        ArrayList arrayList = new ArrayList();
        d(aVar.b, aVar.c, aVar.e, aVar.d, arrayList);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aVar.b);
        c cVar2 = new c();
        cVar2.m = aVar.b;
        cVar2.h = calendar;
        cVar.q = b(context, arrayList, list, cVar2, 1);
        return cVar;
    }

    private static a b(Context context, Plan plan) throws ParseException {
        a aVar = new a();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!TextUtils.isEmpty(plan.acquireStartDate()) && !TextUtils.isEmpty(plan.getEndDate())) {
            aVar.b = simpleDateFormat.parse(plan.acquireStartDate());
            aVar.c = simpleDateFormat.parse(plan.getEndDate());
            aVar.d = ggl.a(aVar.b, aVar.c);
            aVar.e = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), context.getString(R.string._2130851567_res_0x7f0236ef)));
        } else {
            LogUtil.h("Suggestion_ReportChartHolder", "plan date is null");
        }
        return aVar;
    }

    private static void b(List<c> list, c cVar) {
        if (cVar != null) {
            list.add(cVar);
        }
    }

    private static boolean a(List<fqv> list) {
        for (fqv fqvVar : list) {
            if (fqvVar == null) {
                LogUtil.h("Suggestion_ReportChartHolder", "sugExcel == null");
            } else if (fqvVar.y() > 0.0f) {
                return true;
            }
        }
        return false;
    }

    private static void d(Context context, List<fqv> list) {
        if (LanguageUtil.bc(context)) {
            Collections.reverse(list);
        }
    }

    private static void d(Date date, Date date2, SimpleDateFormat simpleDateFormat, int i, List<fqv> list) {
        fqv fqvVar;
        for (int i2 = 0; i2 < i + 1; i2++) {
            if (i2 == 0) {
                fqvVar = new fqv(0.0f, simpleDateFormat.format(date), i2);
            } else if (i2 == i) {
                fqvVar = new fqv(0.0f, simpleDateFormat.format(date2), i2);
            } else {
                fqvVar = new fqv(0.0f, "", i2);
            }
            list.add(fqvVar);
        }
    }

    private static void b(Context context, int i, List<fqv> list, List<fqv> list2) {
        fqv fqvVar;
        fqv fqvVar2;
        int i2 = (i + 1) / 7;
        if (i2 <= 8) {
            int i3 = 0;
            while (i3 < i2) {
                int i4 = i3 + 1;
                String d = ffy.d(context, R.string._2130848387_res_0x7f022a83, UnitUtil.e(i4, 1, 0));
                fqv fqvVar3 = new fqv(0.0f, d, i3);
                list2.add(new fqv(0.0f, d, i3));
                list.add(fqvVar3);
                i3 = i4;
            }
            return;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            if (i5 == 0 || i5 == 9 || i5 == 19) {
                String d2 = ffy.d(context, R.string._2130848387_res_0x7f022a83, UnitUtil.e(i5 + 1, 1, 0));
                fqv fqvVar4 = new fqv(0.0f, d2, i5);
                fqvVar = new fqv(0.0f, d2, i5);
                fqvVar2 = fqvVar4;
            } else {
                fqvVar2 = new fqv(0.0f, "", i5);
                fqvVar = new fqv(0.0f, "", i5);
            }
            list2.add(fqvVar);
            list.add(fqvVar2);
        }
    }

    private static List<fqv> b(Context context, List<fqv> list, List<WorkoutRecord> list2, c cVar, int i) {
        if (koq.b(list2)) {
            LogUtils.w("Suggestion_ReportChartHolder", "recordList is empty");
            return list;
        }
        for (WorkoutRecord workoutRecord : list2) {
            if (workoutRecord != null) {
                int d = d(cVar.m, cVar.h, workoutRecord);
                if (d(list, d)) {
                    c(context, list, i, workoutRecord, d);
                }
            }
        }
        return list;
    }

    private static boolean d(List<fqv> list, int i) {
        return i >= 0 && i < list.size();
    }

    private static void c(Context context, List<fqv> list, int i, WorkoutRecord workoutRecord, int i2) {
        if (koq.b(list, i2)) {
            return;
        }
        fqv fqvVar = list.get(i2);
        if (i == 0) {
            fqvVar.a(3);
            fqvVar.h(fqvVar.f() + workoutRecord.acquireDistance());
            try {
                fqvVar.d(fqvVar.o() + Float.parseFloat(moe.d(workoutRecord.acquireActualDistance())));
            } catch (NumberFormatException e) {
                LogUtil.b("Suggestion_ReportChartHolder", "recordToExcel NumberFormatException", e.getMessage());
            }
            fqvVar.c(ffy.b(ffz.c(), (int) fqvVar.o(), UnitUtil.e(moe.g(fqvVar.o()), 1, 2)));
            return;
        }
        if (i == 1) {
            fqvVar.a(1);
            fqvVar.h(fqvVar.f() + workoutRecord.acquireCalorie());
            fqvVar.d(fqvVar.o() + workoutRecord.acquireActualCalorie());
            fqvVar.i(moe.e(fqvVar.o()));
            fqvVar.c(ffy.d(context, R.string._2130837535_res_0x7f02001f, UnitUtil.e(Math.round(fqvVar.o() / 1000.0f), 1, 0)));
            return;
        }
        if (i == 2) {
            float a2 = a((int) workoutRecord.acquireOxygen());
            if (fqvVar.y() > a2) {
                a2 = fqvVar.y();
            }
            fqvVar.i(a2);
            fqvVar.a(3);
        }
    }

    private static int a(int i) {
        return ((float) i) > 18724.572f ? d(i) : i;
    }

    private static int d(Date date, Calendar calendar, WorkoutRecord workoutRecord) {
        int i;
        try {
            if (calendar == null) {
                i = ggl.a(date, new SimpleDateFormat("yyyy-MM-dd").parse(workoutRecord.acquireWorkoutDate())) / 7;
            } else {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(workoutRecord.acquireWorkoutDate()));
                i = calendar2.get(6) - calendar.get(6);
            }
            return i;
        } catch (ParseException unused) {
            LogUtil.b("Suggestion_ReportChartHolder", "refreshExcels");
            return 0;
        }
    }

    private static int d(double d) {
        return ((int) (d * 3.5d)) / 65536;
    }

    static class a {
        Date b;
        Date c;
        int d;
        SimpleDateFormat e;

        private a() {
        }
    }
}
