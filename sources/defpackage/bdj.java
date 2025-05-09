package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.data.Entry;
import com.huawei.basichealthmodel.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.reportchart.HwHealthReportLineChart;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class bdj {

    /* renamed from: a, reason: collision with root package name */
    private static final int f336a = Color.parseColor("#FFA3E5A3");
    private static final int e = Color.parseColor("#FF47CC47");

    public static void b(Context context, HealthTextView healthTextView, HwHealthReportLineChart hwHealthReportLineChart, List<ayi> list) {
        if (hwHealthReportLineChart == null || healthTextView == null) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "refreshWeekLineChart, horizontalAxisValues or lineChartTips is null");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "refreshWeekLineChart, recentWeeklyData is null");
            return;
        }
        hwHealthReportLineChart.setChartNumberWithInt(true);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        ArrayList arrayList4 = new ArrayList(16);
        ArrayList[] arrayListArr = {arrayList3, arrayList4};
        for (int size = list.size() - 1; size >= 0; size--) {
            if (!koq.b(list, size)) {
                ayi ayiVar = list.get(size);
                arrayList.add(ayiVar.c());
                arrayList2.add(Integer.valueOf(ayiVar.e()));
            }
        }
        e(context, healthTextView, arrayList2);
        for (int i = 0; i < arrayList2.size(); i++) {
            e(context, i, arrayListArr, arrayList4, arrayList2);
        }
        b(context, (ArrayList<Entry>[]) arrayListArr, (ArrayList<String>) arrayList, hwHealthReportLineChart);
    }

    private static void e(Context context, int i, ArrayList<Entry>[] arrayListArr, ArrayList<Entry> arrayList, ArrayList<Integer> arrayList2) {
        if (!koq.d(arrayList2, i)) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "initCompleteNumberPoint, completeNumbers is null or outOfBounds");
            return;
        }
        int intValue = arrayList2.get(i).intValue();
        int size = arrayList2.size();
        if (size == 1) {
            a(context, i, intValue, arrayList);
            return;
        }
        int i2 = size - 1;
        if (i < i2) {
            a(context, i, arrayListArr, intValue);
        }
        if (i == size - 2) {
            d(context, i, arrayListArr, intValue);
        }
        if (i == i2) {
            a(context, i, intValue, arrayList);
        }
    }

    private static void e(Context context, HealthTextView healthTextView, ArrayList<Integer> arrayList) {
        int i;
        int i2;
        int size = arrayList.size();
        if (size > 1) {
            i = arrayList.get(size - 1).intValue();
            i2 = arrayList.get(size - 2).intValue();
        } else {
            i = 0;
            i2 = 0;
        }
        String d = d(context, i, i2);
        if (TextUtils.isEmpty(d)) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setText(d);
        }
    }

    private static void b(Context context, ArrayList<Entry>[] arrayListArr, ArrayList<String> arrayList, HwHealthReportLineChart hwHealthReportLineChart) {
        if (koq.b(arrayList) || hwHealthReportLineChart == null) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "refreshWeekLineChartLayout, horizontalAxisValues or historyLineChart is null");
            return;
        }
        if (arrayListArr == null || arrayListArr.length < 2) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "refreshWeekLineChartLayout, arrayLists error");
            return;
        }
        hwHealthReportLineChart.setExtraOffsets(nsn.c(context, 10.0f), 0.0f, nsn.c(context, 11.0f), nsn.c(context, 2.0f));
        int i = f336a;
        int i2 = e;
        hwHealthReportLineChart.d(i, i2, i2, R$drawable.report_line_chart_shadow_run);
        hwHealthReportLineChart.setChartData(context, arrayList, arrayListArr[0], arrayListArr[1], new ArrayList<>(16));
        hwHealthReportLineChart.invalidate();
    }

    private static void a(Context context, int i, ArrayList<Entry>[] arrayListArr, int i2) {
        if (arrayListArr == null || arrayListArr.length < 2) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "initDataOnHollowPoint, arrayLists error");
        } else {
            arrayListArr[0].add(nV_(i, i2, ContextCompat.getDrawable(context, R$drawable.report_line_chart_hollow_run), "LINE_PATH"));
        }
    }

    private static void d(Context context, int i, ArrayList<Entry>[] arrayListArr, int i2) {
        if (arrayListArr == null || arrayListArr.length < 2) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "initDataOnThickLine, arrayLists error");
        } else {
            arrayListArr[1].add(nV_(i, i2, ContextCompat.getDrawable(context, R$drawable.report_line_chart_solid_run), "LINE_PATH_BOLD_START"));
        }
    }

    private static void a(Context context, int i, int i2, ArrayList<Entry> arrayList) {
        if (arrayList == null) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "initDataOnSolidPoint, completeNumberBold is null");
        } else {
            arrayList.add(nV_(i, i2, ContextCompat.getDrawable(context, R$drawable.report_line_chart_solid_run), "LINE_PATH"));
        }
    }

    private static String d(Context context, int i, int i2) {
        float f = i2 != 0 ? ((i - i2) * 100.0f) / i2 : 0.0f;
        if ("0".equals(azi.c(Math.abs(f), 1, 0))) {
            LogUtil.h("HealthLife_WeeklyReportLineChartUtil", "getWeekLineChartTips percent is 0");
            return "";
        }
        return c(context, f);
    }

    private static String c(Context context, float f) {
        return f < 0.0f ? context.getString(R$string.IDS_health_model_line_chart_down_tips, azi.c(-f, 2, 0)) : f > 0.0f ? context.getString(R$string.IDS_health_model_line_chart_up_tips, azi.c(f, 2, 0)) : "";
    }

    private static Entry nV_(int i, float f, Drawable drawable, Object obj) {
        Entry entry = new Entry(i, f);
        entry.setIcon(drawable);
        entry.setData(obj);
        return entry;
    }
}
