package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.Date;

/* loaded from: classes9.dex */
public class mll {
    public static String c(Context context, TotalRecord totalRecord) {
        if (totalRecord == null || context == null) {
            return "";
        }
        int days = totalRecord.getDays();
        long startDate = totalRecord.getStartDate();
        String a2 = UnitUtil.a(new Date(startDate), 20);
        if (days <= 0 || startDate <= 0) {
            return context.getResources().getString(R.string._2130840718_res_0x7f020c8e);
        }
        return context.getString(R.string._2130840717_res_0x7f020c8d, a2);
    }

    public static String d(Context context, TotalRecord totalRecord) {
        if (totalRecord == null || context == null) {
            return "";
        }
        int days = totalRecord.getDays();
        return context.getResources().getQuantityString(R.plurals._2130903164_res_0x7f03007c, days, UnitUtil.e(days, 1, 0));
    }

    public static String g(Context context, TotalRecord totalRecord) {
        if (totalRecord == null || context == null) {
            return "";
        }
        double distance = totalRecord.getDistance();
        if (UnitUtil.h()) {
            return context.getString(R.string._2130841422_res_0x7f020f4e, UnitUtil.e(UnitUtil.e(distance, 3), 1, 0));
        }
        return context.getString(R.string._2130840711_res_0x7f020c87, UnitUtil.e(distance, 1, 0));
    }

    public static String i(Context context, TotalRecord totalRecord) {
        if (totalRecord == null || context == null) {
            return "";
        }
        return context.getResources().getString(R.string._2130840706_res_0x7f020c82, mln.d(totalRecord.getDistance()));
    }

    public static String j(Context context, TotalRecord totalRecord) {
        if (totalRecord == null || context == null) {
            return "";
        }
        double steps = totalRecord.getSteps();
        return context.getResources().getQuantityString(R.plurals._2130903167_res_0x7f03007f, (int) steps, UnitUtil.e(steps, 1, 0));
    }

    public static String h(Context context, TotalRecord totalRecord) {
        return (Utils.o() || totalRecord == null || context == null) ? "" : context.getString(R.string._2130840716_res_0x7f020c8c, UnitUtil.e(totalRecord.getStepsRanking(), 2, 2));
    }

    public static String a(Context context, TotalRecord totalRecord) {
        return (totalRecord == null || context == null) ? "" : context.getString(R.string._2130840712_res_0x7f020c88, UnitUtil.e(totalRecord.acquireCalorie() / 1000.0d, 1, 0));
    }

    public static String b(Context context, TotalRecord totalRecord) {
        if (totalRecord == null || context == null) {
            return "";
        }
        double acquireCalorie = (totalRecord.acquireCalorie() / 1000.0d) / 300.0d;
        return context.getResources().getQuantityString(R.plurals._2130903165_res_0x7f03007d, (int) acquireCalorie, UnitUtil.e(acquireCalorie, 1, 1));
    }

    public static TotalRecord a() {
        TotalRecord totalRecord = new TotalRecord();
        totalRecord.setDays(0);
        totalRecord.setDistance(0.0d);
        totalRecord.setStartDate(System.currentTimeMillis());
        totalRecord.setSteps(0.0d);
        totalRecord.setStepsRanking(0.0d);
        totalRecord.saveCalorie(0.0d);
        return totalRecord;
    }

    public static String e(Context context, TotalRecord totalRecord) {
        if (totalRecord == null || context == null) {
            return "";
        }
        double distance = totalRecord.getDistance() / 42.195d;
        return context.getResources().getQuantityString(R.plurals._2130903177_res_0x7f030089, (int) distance, UnitUtil.e(distance, 1, 1));
    }
}
