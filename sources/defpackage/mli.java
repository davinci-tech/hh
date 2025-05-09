package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.JsonSyntaxException;
import com.huawei.agconnect.apms.Agent;
import com.huawei.health.R;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.RecentMonthRecordFromDB;
import com.huawei.pluginachievement.manager.model.RecentWeekRecordFromDB;
import com.huawei.pluginachievement.manager.model.WeekAndMonthRecord;
import com.huawei.pluginachievement.report.bean.ReportDataBean;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.reportchart.HwHealthReportBarChart;
import com.huawei.ui.commonui.reporthorizontalpercentageview.ReportHorizontalBarChart;
import com.huawei.ui.commonui.reportrectview.ReportProportionView;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes8.dex */
public class mli {
    public static void e(Context context, WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, mkn mknVar) {
        int i;
        int i2;
        if (e(context, weekAndMonthRecord, mknVar)) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "refreshMonthStepViewLayout params is invalid.");
            return;
        }
        RecentMonthRecordFromDB a2 = a(weekAndMonthRecord);
        if (a2 == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "refreshMonthStepViewLayout currentMonthRecord is null.");
            return;
        }
        int acquireAvgSteps = a2.acquireAvgSteps();
        a(context, mknVar, acquireAvgSteps);
        RecentMonthRecordFromDB a3 = a(weekAndMonthRecord2);
        if (a3 != null) {
            i2 = a3.acquireAvgSteps();
            i = d(mknVar.q(), a3.acquireOneMonthSteps());
        } else {
            i = 0;
            i2 = 0;
        }
        d(context, mknVar, acquireAvgSteps);
        b(context, mknVar, acquireAvgSteps, i2);
        int d = d(mknVar.q(), a2.acquireOneMonthSteps());
        HealthTextView g = mknVar.g();
        if (g != null) {
            g.setText(UnitUtil.e(d, 1, 0));
        }
        if (b(d, a2.acquireFirstday())) {
            a(context, mknVar);
        } else {
            c(context, mknVar, i, d, 0);
        }
    }

    private static boolean b(int i, long j) {
        boolean z = i == mkx.e(j);
        LogUtil.a("PLGACHIEVE_ReportViewUtil", "isComplianceDaysFullMoon currentMonthComplianceDays ", Integer.valueOf(i), "firstDay ", Long.valueOf(j));
        return z;
    }

    private static void a(Context context, mkn mknVar) {
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setStepComplianceDayDesc() context or parameter is null.");
            return;
        }
        ImageView cjD_ = mknVar.cjD_();
        if (cjD_ != null) {
            cjD_.setVisibility(8);
        }
        HealthTextView e = mknVar.e();
        if (e != null) {
            e.setText(context.getResources().getString(R.string._2130841012_res_0x7f020db4));
        }
    }

    private static void c(Context context, mkn mknVar, int i, int i2, int i3) {
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setStepComplianceDayDesc() context or parameter is null.");
            return;
        }
        Resources resources = context.getResources();
        ImageView cjD_ = mknVar.cjD_();
        if (cjD_ != null) {
            if (i2 > i) {
                cjD_.setVisibility(0);
                cjD_.setImageDrawable(resources.getDrawable(R.drawable._2131431190_res_0x7f0b0f16));
            } else if (i2 < i) {
                cjD_.setVisibility(0);
                cjD_.setImageDrawable(resources.getDrawable(R.drawable._2131431189_res_0x7f0b0f15));
            } else {
                cjD_.setVisibility(8);
            }
        }
        HealthTextView e = mknVar.e();
        if (e != null) {
            e.setText(mlh.e(context, i3, i2, i));
        }
    }

    public static void a(Context context, WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, mkn mknVar) {
        int i;
        if (e(context, weekAndMonthRecord, mknVar)) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "refreshMonthExerciseViewLayout isParamsInvalid is true.");
            return;
        }
        RecentMonthRecordFromDB a2 = a(weekAndMonthRecord);
        if (a2 == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "refreshMonthExerciseViewLayout currentMonthRecord is null.");
            return;
        }
        e(context, mknVar, 0, a2.acquireSumAllCount());
        int a3 = a(context, a2, mknVar);
        a(context, mknVar, 0, a3);
        float acquireSumCalorie = a2.acquireSumCalorie();
        a(context, mknVar, 0, acquireSumCalorie);
        e(context, mknVar, a2);
        b(context, mknVar, a2);
        float acquireDayAvgCalorie = a2.acquireDayAvgCalorie();
        a(context, mknVar, acquireDayAvgCalorie);
        RecentMonthRecordFromDB a4 = a(weekAndMonthRecord2);
        float f = 0.0f;
        float acquireDayAvgCalorie2 = a4 != null ? a4.acquireDayAvgCalorie() : 0.0f;
        if (a4 != null) {
            f = a4.acquireSumCalorie();
            i = d(a4.acquireWeekTime());
        } else {
            i = 0;
        }
        b(mknVar, a3, i);
        c(context, mknVar, acquireSumCalorie, f, 0);
        b(context, mknVar, acquireDayAvgCalorie, acquireDayAvgCalorie2);
    }

    private static int e(Map<Long, Long> map) {
        if (map == null || map.size() <= 0) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "getExerciseTotalTime timeMap is null.");
            return 0;
        }
        float f = 0.0f;
        while (map.entrySet().iterator().hasNext()) {
            f += r3.next().getValue().longValue() / 60.0f;
        }
        return (int) Math.ceil(f);
    }

    private static int d(Map<Long, Long> map) {
        int i = 0;
        if (map == null || map.size() <= 0) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "getExerciseTotalTime timeMap is null.");
            return 0;
        }
        Iterator<Map.Entry<Long, Long>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            i += Math.round(it.next().getValue().longValue() / 60.0f);
        }
        return i;
    }

    private static void b(Context context, mkn mknVar, RecentMonthRecordFromDB recentMonthRecordFromDB) {
        if (context == null || mknVar == null || recentMonthRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setCaloriesExtensionLayout context or parameter or currentMonthRecord is null.");
            return;
        }
        ArrayList<ReportProportionView.ProportionItem> d = d(context, recentMonthRecordFromDB);
        LinearLayout cjC_ = mknVar.cjC_();
        HealthTextView a2 = mknVar.a();
        if (koq.b(d)) {
            if (cjC_ != null) {
                cjC_.removeAllViews();
                cjC_.setVisibility(8);
            }
            if (a2 != null) {
                a2.setVisibility(8);
                return;
            }
            return;
        }
        ReportProportionView reportProportionView = new ReportProportionView(context);
        reportProportionView.d(d);
        if (cjC_ != null) {
            cjC_.removeAllViews();
            cjC_.addView(reportProportionView);
            cjC_.setVisibility(0);
        }
        if (a2 != null) {
            a2.setVisibility(0);
        }
    }

    private static ArrayList<ReportProportionView.ProportionItem> d(Context context, RecentMonthRecordFromDB recentMonthRecordFromDB) {
        if (context == null || recentMonthRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "getProportionItemList context or currentMonthRecord is null.");
            return new ArrayList<>();
        }
        int[] iArr = recentMonthRecordFromDB.mPercentCalorie;
        int length = iArr.length;
        ArrayList<ReportProportionView.ProportionItem> arrayList = new ArrayList<>(length);
        if (length >= 1 && iArr[0] > 0) {
            ReportProportionView.b bVar = new ReportProportionView.b(context, context.getResources().getColor(R.color._2131296396_res_0x7f09008c));
            bVar.setDataValue(iArr[0]);
            arrayList.add(bVar);
        }
        if (length >= 2 && iArr[1] > 0) {
            ReportProportionView.d dVar = new ReportProportionView.d(context, context.getResources().getColor(R.color._2131296397_res_0x7f09008d));
            dVar.setDataValue(iArr[1]);
            arrayList.add(dVar);
        }
        if (length >= 3 && iArr[2] > 0) {
            ReportProportionView.e eVar = new ReportProportionView.e(context, context.getResources().getColor(R.color._2131296393_res_0x7f090089));
            eVar.setDataValue(iArr[2]);
            arrayList.add(eVar);
        }
        if (length >= 4 && iArr[3] > 0) {
            ReportProportionView.a aVar = new ReportProportionView.a(context, context.getResources().getColor(R.color._2131296394_res_0x7f09008a));
            aVar.setDataValue(iArr[3]);
            arrayList.add(aVar);
        }
        if (length >= 5 && iArr[4] > 0) {
            ReportProportionView.c cVar = new ReportProportionView.c(context, context.getResources().getColor(R.color._2131296395_res_0x7f09008b));
            cVar.setDataValue(iArr[4]);
            arrayList.add(cVar);
        }
        return arrayList;
    }

    private static void e(Context context, mkn mknVar, RecentMonthRecordFromDB recentMonthRecordFromDB) {
        if (context == null || mknVar == null || recentMonthRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setWeeklyCaloriesBarChart context or parameter or currentMonthRecord is null.");
            return;
        }
        HwHealthReportBarChart x = mknVar.x();
        HealthTextView p = mknVar.p();
        if (x == null || p == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setWeeklyCaloriesBarChart weeklyCaloriesBarChart is null.");
            return;
        }
        List<BarEntry> c = mkz.c(new ArrayList(8), recentMonthRecordFromDB, LanguageUtil.bc(context));
        if (koq.b(c)) {
            x.setVisibility(8);
            p.setVisibility(8);
            return;
        }
        x.setVisibility(0);
        p.setVisibility(0);
        BarDataSet barDataSet = new BarDataSet(c, Agent.OS_NAME);
        barDataSet.setColor(context.getResources().getColor(R.color._2131296485_res_0x7f0900e5));
        barDataSet.setDrawValues(false);
        barDataSet.setBarBorderWidth(12.0f);
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(barDataSet);
        ArrayList<String> b = mkx.b(recentMonthRecordFromDB);
        if (koq.b(b)) {
            return;
        }
        float d = mkx.d(recentMonthRecordFromDB);
        x.setData(new BarData(arrayList));
        x.setCustomValue(context, b, context.getResources().getColor(R.color._2131296486_res_0x7f0900e6), 0.0f, d, 10.0f, false);
        x.invalidate();
    }

    private static void c(Context context, mkn mknVar, float f, float f2, int i) {
        String string;
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setComparedTotalCaloriesText context or parameter is null.");
            return;
        }
        float f3 = (f - f2) / 1000.0f;
        String string2 = context.getResources().getString(R.string._2130840712_res_0x7f020c88, UnitUtil.e(Math.abs(f3), 1, 0));
        if (f3 > 0.0f) {
            if (i == 0) {
                string = context.getResources().getString(R.string._2130840947_res_0x7f020d73, string2);
            } else {
                string = context.getResources().getString(R.string._2130840949_res_0x7f020d75, string2);
            }
        } else if (f3 < 0.0f) {
            if (i == 0) {
                string = context.getResources().getString(R.string._2130840948_res_0x7f020d74, string2);
            } else {
                string = context.getResources().getString(R.string._2130840950_res_0x7f020d76, string2);
            }
        } else if (i == 0) {
            string = context.getResources().getString(R.string._2130840951_res_0x7f020d77);
        } else {
            string = context.getResources().getString(R.string._2130840952_res_0x7f020d78);
        }
        HealthTextView h = mknVar.h();
        if (h != null) {
            h.setText(string);
        }
    }

    private static void b(mkn mknVar, int i, int i2) {
        String str;
        if (mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseComparedTimeText parameter is null.");
            return;
        }
        int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131365074_res_0x7f0a0cd2);
        int color = BaseApplication.getContext().getResources().getColor(R.color._2131299236_res_0x7f090ba4);
        String string = BaseApplication.getContext().getResources().getString(R.string._2130851581_res_0x7f0236fd);
        int i3 = i - i2;
        if (i3 > 0) {
            str = Marker.ANY_NON_NULL_MARKER + UnitUtil.e(i3, 1, 0);
        } else if (i3 < 0) {
            str = UnitUtil.e(i3, 1, 0);
        } else {
            str = "±" + i3;
        }
        CharSequence e = mlh.e(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903305_res_0x7f030109, i3, str), str, color, dimensionPixelSize, string);
        HealthTextView f = mknVar.f();
        if (f != null) {
            f.setText(e);
        }
    }

    private static void b(Context context, mkn mknVar, float f, float f2) {
        String string;
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseComparedAvgCaloriesText context or parameter is null.");
            return;
        }
        float f3 = (f - f2) / 1000.0f;
        String string2 = context.getResources().getString(R.string._2130840712_res_0x7f020c88, UnitUtil.e(Math.abs(f3), 1, 0));
        if (f3 > 0.0f) {
            string = context.getResources().getString(R.string._2130840947_res_0x7f020d73, string2);
        } else if (f3 < 0.0f) {
            string = context.getResources().getString(R.string._2130840948_res_0x7f020d74, string2);
        } else {
            string = context.getResources().getString(R.string._2130840951_res_0x7f020d77);
        }
        HealthTextView b = mknVar.b();
        if (b != null) {
            b.setText(string);
        }
    }

    private static void a(Context context, mkn mknVar, float f) {
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseAvgCaloriesText context or parameter is null.");
            return;
        }
        int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131365074_res_0x7f0a0cd2);
        int color = BaseApplication.getContext().getResources().getColor(R.color._2131299236_res_0x7f090ba4);
        String string = BaseApplication.getContext().getResources().getString(R.string._2130851581_res_0x7f0236fd);
        String e = UnitUtil.e(f / 1000.0f, 1, 0);
        CharSequence e2 = mlh.e(context.getResources().getString(R.string._2130840712_res_0x7f020c88, e), e, color, dimensionPixelSize, string);
        HealthTextView j = mknVar.j();
        if (j != null) {
            j.setText(e2);
        }
    }

    private static void a(Context context, mkn mknVar, int i, float f) {
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseTotalCalTextAndImage context or parameter is null.");
            return;
        }
        int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131365074_res_0x7f0a0cd2);
        int color = BaseApplication.getContext().getResources().getColor(R.color._2131299236_res_0x7f090ba4);
        String string = BaseApplication.getContext().getResources().getString(R.string._2130851581_res_0x7f0236fd);
        float f2 = f / 1000.0f;
        String e = UnitUtil.e(f2, 1, 0);
        CharSequence e2 = mlh.e(context.getResources().getString(R.string._2130840712_res_0x7f020c88, e), e, color, dimensionPixelSize, string);
        HealthTextView n = mknVar.n();
        if (n != null) {
            n.setText(e2);
        }
        ImageView cjE_ = mknVar.cjE_();
        if (cjE_ != null) {
            cjE_.setImageDrawable(mlh.ckr_(context, i, (int) f2));
        }
    }

    private static void a(Context context, mkn mknVar, int i, int i2) {
        String valueOf;
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseTotalTimeTextAndImage context or parameter is null.");
            return;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131365074_res_0x7f0a0cd2);
        int color = context.getResources().getColor(R.color._2131299236_res_0x7f090ba4);
        String string = context.getResources().getString(R.string._2130851581_res_0x7f0236fd);
        if (i2 >= 0) {
            valueOf = UnitUtil.e(i2, 1, 0);
        } else {
            valueOf = String.valueOf(0);
        }
        CharSequence e = mlh.e(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903305_res_0x7f030109, i2, valueOf), valueOf, color, dimensionPixelSize, string);
        HealthTextView o = mknVar.o();
        if (o != null) {
            o.setText(e);
        }
        ImageView cjF_ = mknVar.cjF_();
        if (cjF_ != null) {
            cjF_.setImageDrawable(mlh.cks_(context, i, i2));
        }
    }

    private static void e(Context context, mkn mknVar, int i, int i2) {
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseDescText context or parameter or currentMonthRecord is null.");
            return;
        }
        HealthTextView i3 = mknVar.i();
        if (i3 != null) {
            i3.setText(mlh.a(context, i, i2));
        }
    }

    private static boolean e(Context context, WeekAndMonthRecord weekAndMonthRecord, mkn mknVar) {
        if (context != null && weekAndMonthRecord != null && mknVar != null) {
            return false;
        }
        LogUtil.h("PLGACHIEVE_ReportViewUtil", "isParamsInvalid listData is empty or context | parameter is null.");
        return true;
    }

    private static int a(Context context, RecentMonthRecordFromDB recentMonthRecordFromDB, mkn mknVar) {
        if (recentMonthRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setWeeklyDistributionTotalTimeView currentMonthRecord is null.");
            return 0;
        }
        Map<Long, Long> acquireWeekTime = recentMonthRecordFromDB.acquireWeekTime();
        if (acquireWeekTime == null || acquireWeekTime.size() <= 0) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setWeeklyDistributionTotalTimeView weekTimeMap is null.");
            return 0;
        }
        Map<Long, Number> b = mkx.b();
        b.putAll(acquireWeekTime);
        LinearLayout cjG_ = mknVar.cjG_();
        if (cjG_ != null) {
            cjG_.removeAllViews();
        }
        long a2 = a(acquireWeekTime);
        int i = 0;
        for (Map.Entry<Long, Number> entry : b.entrySet()) {
            long longValue = entry.getKey().longValue();
            long longValue2 = entry.getValue().longValue();
            long b2 = mkx.b(longValue);
            long c = mkx.c(longValue);
            ReportHorizontalBarChart reportHorizontalBarChart = new ReportHorizontalBarChart(context);
            if (Math.abs(b2 - c) <= 86400000) {
                reportHorizontalBarChart.setWeekDate(mlg.a(b2, 3));
            } else {
                reportHorizontalBarChart.setWeekDate(mlg.a(b2, 3) + Constants.LINK + mlg.a(c, 3));
            }
            int round = Math.round(longValue2 / 60.0f);
            reportHorizontalBarChart.setExerciseTime(UnitUtil.e(round, 1, 0));
            reportHorizontalBarChart.setMaxValue(round, Math.round(a2 / 60.0f));
            if (cjG_ != null) {
                cjG_.addView(reportHorizontalBarChart);
                i += round;
            }
        }
        return i;
    }

    private static long a(Map<Long, Long> map) {
        long j = 0;
        if (map == null || map.size() <= 0) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "getDistributionMaxExerciseTime weekTimeMap is null.");
            return 0L;
        }
        Iterator<Map.Entry<Long, Long>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            j = Math.max(j, it.next().getValue().longValue());
        }
        return j;
    }

    private static int d(double d, Map<Long, Integer> map) {
        LogUtil.a("PLGACHIEVE_ReportViewUtil", "getComplianceDays() stepTarget = ", Double.valueOf(d));
        Iterator<Map.Entry<Long, Integer>> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getValue().intValue() >= d) {
                i++;
            }
        }
        LogUtil.a("PLGACHIEVE_ReportViewUtil", "getComplianceDays() currentMonthComplianceDays = ", Integer.valueOf(i));
        return i;
    }

    private static ArrayList<ReportDataBean> d(String str) {
        JSONObject jSONObject;
        int i;
        ArrayList<ReportDataBean> arrayList = new ArrayList<>();
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportViewUtil", "parseFromChildClass JsonSyntaxException, exception is ", e.getMessage());
        }
        if (!jSONObject.has("mReportDataDetail")) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "parseFromChildClass mReportDataDetail is not exist.");
            return arrayList;
        }
        JSONArray jSONArray = jSONObject.getJSONArray("mReportDataDetail");
        if (jSONArray.length() != 0 && mlm.c().size() != 0) {
            for (i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                arrayList.add(mlm.e(jSONObject2.toString(), jSONObject2.getInt("mDataType")));
            }
            return arrayList;
        }
        LogUtil.h("PLGACHIEVE_ReportViewUtil", "parseFromChildClass jsonArray.length() == 0 || SportTypeMap().size == 0.");
        return arrayList;
    }

    public static void c(Context context, WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, mkn mknVar) {
        int i;
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "refreshWeekStepViewLayout params is invalid.");
            return;
        }
        RecentWeekRecordFromDB c = c(weekAndMonthRecord);
        if (c == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "refreshWeekStepViewLayout() currentWeekRecord is null.");
            return;
        }
        int acquireAvgSteps = c.acquireAvgSteps();
        a(context, mknVar, acquireAvgSteps);
        d(context, mknVar, acquireAvgSteps);
        int d = d(mknVar.q(), c.acquireSevenDaySteps());
        HealthTextView g = mknVar.g();
        if (g != null) {
            g.setText(UnitUtil.e(d, 1, 0));
        }
        RecentWeekRecordFromDB c2 = c(weekAndMonthRecord2);
        if (c2 != null) {
            int acquireAvgSteps2 = c2.acquireAvgSteps();
            Map<Long, Integer> acquireSevenDaySteps = c2.acquireSevenDaySteps();
            i = acquireSevenDaySteps != null ? d(mknVar.q(), acquireSevenDaySteps) : 0;
            r3 = acquireAvgSteps2;
        } else {
            i = 0;
        }
        b(context, mknVar, acquireAvgSteps, r3);
        c(context, mknVar, i, d, 1);
    }

    private static void a(Context context, mkn mknVar, int i) {
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setStepDescText context or parameter is null.");
            return;
        }
        HealthTextView t = mknVar.t();
        if (t == null) {
            return;
        }
        if (!LanguageUtil.m(context)) {
            t.setAutoTextSize(1, 20.0f);
            t.setAutoTextInfo(15, 2, 1);
            t.setSingleLine(false);
            t.setMaxLines(3);
        }
        t.setText(mlh.c(context, i));
    }

    private static void b(Context context, mkn mknVar, int i, int i2) {
        String str;
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setAvgStepValueView context or parameter is null.");
            return;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131365074_res_0x7f0a0cd2);
        int color = context.getResources().getColor(R.color._2131299236_res_0x7f090ba4);
        String string = context.getResources().getString(R.string._2130851581_res_0x7f0236fd);
        int i3 = i - i2;
        if (i3 == 0) {
            str = "±" + UnitUtil.e(i3, 1, 0);
        } else if (i3 > 0) {
            str = Marker.ANY_NON_NULL_MARKER + UnitUtil.e(i3, 1, 0);
        } else {
            str = Constants.LINK + UnitUtil.e(-i3, 1, 0);
        }
        CharSequence e = mlh.e(context.getResources().getQuantityString(R.plurals._2130903189_res_0x7f030095, i3, str), str, color, dimensionPixelSize, string);
        HealthTextView r = mknVar.r();
        if (r != null) {
            r.setText(e);
        }
    }

    private static void d(Context context, mkn mknVar, int i) {
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setAvgStepValueView context or parameter is null.");
            return;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131365074_res_0x7f0a0cd2);
        int color = context.getResources().getColor(R.color._2131299236_res_0x7f090ba4);
        String string = context.getResources().getString(R.string._2130851581_res_0x7f0236fd);
        String e = UnitUtil.e(i, 1, 0);
        CharSequence e2 = mlh.e(context.getResources().getQuantityString(R.plurals._2130903189_res_0x7f030095, i, e), e, color, dimensionPixelSize, string);
        HealthTextView s = mknVar.s();
        if (s != null) {
            s.setText(e2);
        }
    }

    public static void d(Context context, WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, mkn mknVar) {
        long j;
        float f;
        if (e(context, weekAndMonthRecord, mknVar)) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "refreshWeekExerciseViewLayout isParamsInvalid is true.");
            return;
        }
        RecentWeekRecordFromDB c = c(weekAndMonthRecord);
        if (c == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "refreshWeekExerciseViewLayout currentWeekRecord is null.");
            return;
        }
        e(context, mknVar, 1, c.acquireSumAllCount());
        int e = e(c.acquireSevenDayTime());
        a(context, mknVar, 1, e);
        a(context, mknVar, c);
        a(context, mknVar, 1, c.acquireSumCalorie());
        e(context, mknVar, c.acquireAvgCalorie());
        RecentWeekRecordFromDB c2 = c(weekAndMonthRecord2);
        if (c2 != null) {
            j = e(c2.acquireSevenDayTime());
            f = c2.acquireSumCalorie();
        } else {
            j = 0;
            f = 0.0f;
        }
        c(mknVar, e, j);
        c(context, mknVar, c.acquireSumCalorie(), f, 1);
    }

    private static void e(Context context, mkn mknVar, float f) {
        if (context == null || mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseWeekAvgCaloriesText parameter is null.");
            return;
        }
        double d = (f / 1000.0f) / 300.0f;
        String e = UnitUtil.e(d, 1, 1);
        CharSequence e2 = mlh.e(context.getResources().getQuantityString(R.plurals._2130903184_res_0x7f030090, UnitUtil.e(d, Locale.getDefault()), e), e, context.getResources().getColor(R.color._2131299236_res_0x7f090ba4), context.getResources().getDimensionPixelSize(R.dimen._2131365074_res_0x7f0a0cd2), context.getResources().getString(R.string._2130851581_res_0x7f0236fd));
        HealthTextView j = mknVar.j();
        if (j != null) {
            j.setText(e2);
        }
    }

    private static void c(mkn mknVar, long j, long j2) {
        String string;
        if (mknVar == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseWeekComparedTotalTimeText parameter is null.");
            return;
        }
        long j3 = j - j2;
        String string2 = BaseApplication.getContext().getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(Math.abs(j3), 1, 0));
        if (j3 > 0) {
            string = BaseApplication.getContext().getResources().getString(R.string._2130840949_res_0x7f020d75, string2);
        } else if (j3 < 0) {
            string = BaseApplication.getContext().getResources().getString(R.string._2130840950_res_0x7f020d76, string2);
        } else {
            string = BaseApplication.getContext().getResources().getString(R.string._2130840952_res_0x7f020d78);
        }
        HealthTextView f = mknVar.f();
        if (f != null) {
            f.setText(string);
        }
    }

    private static void a(Context context, mkn mknVar, RecentWeekRecordFromDB recentWeekRecordFromDB) {
        if (context == null || mknVar == null || recentWeekRecordFromDB == null) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseTotalTimeBarChart currentMonthRecord is null.");
            return;
        }
        Map<Long, Long> acquireSevenDayTime = recentWeekRecordFromDB.acquireSevenDayTime();
        if (acquireSevenDayTime == null || acquireSevenDayTime.size() <= 0) {
            LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseTotalTimeBarChart weekTimeMap is null.");
            return;
        }
        LogUtil.h("PLGACHIEVE_ReportViewUtil", "setExerciseTotalTimeBarChart weekTimeMap = ", acquireSevenDayTime.toString());
        List<BarEntry> d = mkz.d(new ArrayList(8), recentWeekRecordFromDB, LanguageUtil.bc(context));
        if (koq.b(d)) {
            return;
        }
        BarDataSet barDataSet = new BarDataSet(d, Agent.OS_NAME);
        barDataSet.setColor(context.getResources().getColor(R.color._2131296485_res_0x7f0900e5));
        barDataSet.setDrawValues(false);
        barDataSet.setBarBorderWidth(2.0f);
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(barDataSet);
        ArrayList<String> e = mkx.e(recentWeekRecordFromDB);
        if (koq.b(e)) {
            return;
        }
        float a2 = mkx.a(recentWeekRecordFromDB);
        BarData barData = new BarData(arrayList);
        barData.setBarWidth(0.95f);
        HwHealthReportBarChart l = mknVar.l();
        if (l != null) {
            l.setData(barData);
            l.setCustomValue(context, e, context.getResources().getColor(R.color._2131296486_res_0x7f0900e6), 0.0f, (float) Math.ceil(a2), 10.0f, false);
            l.invalidate();
        }
    }

    public static ArrayList<ReportDataBean> a(WeekAndMonthRecord weekAndMonthRecord, int i) {
        ArrayList<ReportDataBean> arrayList = new ArrayList<>(8);
        if (i == 1) {
            RecentWeekRecordFromDB c = c(weekAndMonthRecord);
            return c != null ? c.acquireReportDataDetail() : arrayList;
        }
        RecentMonthRecordFromDB a2 = a(weekAndMonthRecord);
        return a2 != null ? a2.acquireReportDataDetail() : arrayList;
    }

    public static RecentMonthRecordFromDB a(WeekAndMonthRecord weekAndMonthRecord) {
        String value;
        RecentMonthRecordFromDB recentMonthRecordFromDB;
        RecentMonthRecordFromDB recentMonthRecordFromDB2 = null;
        if (weekAndMonthRecord != null) {
            try {
                value = weekAndMonthRecord.getValue();
                recentMonthRecordFromDB = (RecentMonthRecordFromDB) HiJsonUtil.e(value, RecentMonthRecordFromDB.class);
            } catch (JsonSyntaxException e) {
                e = e;
            }
            try {
                recentMonthRecordFromDB.setReportDataDetail(d(value));
                return recentMonthRecordFromDB;
            } catch (JsonSyntaxException e2) {
                e = e2;
                recentMonthRecordFromDB2 = recentMonthRecordFromDB;
                LogUtil.b("PLGACHIEVE_ReportViewUtil", "getMonthRecordFromBaseRecord JsonSyntaxException, exception is ", e.getMessage());
                return recentMonthRecordFromDB2;
            }
        }
        LogUtil.h("PLGACHIEVE_ReportViewUtil", "getMonthRecordFromBaseRecord record is null.");
        return null;
    }

    public static RecentWeekRecordFromDB c(WeekAndMonthRecord weekAndMonthRecord) {
        String value;
        RecentWeekRecordFromDB recentWeekRecordFromDB;
        RecentWeekRecordFromDB recentWeekRecordFromDB2 = null;
        if (weekAndMonthRecord != null) {
            try {
                value = weekAndMonthRecord.getValue();
                recentWeekRecordFromDB = (RecentWeekRecordFromDB) HiJsonUtil.e(value, RecentWeekRecordFromDB.class);
            } catch (JsonSyntaxException e) {
                e = e;
            }
            try {
                recentWeekRecordFromDB.setReportDataDetail(d(value));
                return recentWeekRecordFromDB;
            } catch (JsonSyntaxException e2) {
                e = e2;
                recentWeekRecordFromDB2 = recentWeekRecordFromDB;
                LogUtil.b("PLGACHIEVE_ReportViewUtil", "getWeekRecordFromBaseRecord JsonSyntaxException, exception is ", e.getMessage());
                return recentWeekRecordFromDB2;
            }
        }
        LogUtil.h("PLGACHIEVE_ReportViewUtil", "getWeekRecordFromBaseRecord record is null.");
        return null;
    }
}
