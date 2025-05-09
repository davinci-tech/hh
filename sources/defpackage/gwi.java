package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.VastTag;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class gwi {
    public static void c(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, List<gxs> list) {
        if (nreVar == null) {
            LogUtil.h("Track_PaceIntervalUtils", "tableDataSet == null");
            return;
        }
        if (list == null) {
            LogUtil.h("Track_PaceIntervalUtils", "zoneStatisticList == null");
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_PaceIntervalUtils", "addRunningPaceTableTitle with context is null, pls check");
            nreVar.putRowColumnHeaderData(0, 0, null);
            return;
        }
        LogUtil.a("Track_PaceIntervalUtils", "title count", Integer.valueOf(list.size()));
        nreVar.putRowColumnHeaderData(0, 0, new hjc(context.getResources().getString(R.string._2130843120_res_0x7f0215f0)));
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            nreVar.putColumnHeaderData(0, i2, d(list.get(i).g(), context));
            i = i2;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static hjj d(String str, Context context) {
        char c;
        hjj hjjVar;
        str.hashCode();
        switch (str.hashCode()) {
            case -2058984604:
                if (str.equals("GROUND_CONTACT_TIME")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1964372974:
                if (str.equals("SWING_ANGLE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1927368268:
                if (str.equals(VastTag.DURATION)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1849873063:
                if (str.equals(ObserveLabels.HEART_RATE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -133865869:
                if (str.equals("STEP_RATE")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1131061880:
                if (str.equals("ACTIVE_PEAK")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1708340310:
                if (str.equals("EVERSION_EXCURSION")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1790478369:
                if (str.equals("GROUND_IMPACT_ACCELERATION")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return new hjj(context.getResources().getString(R.string._2130842710_res_0x7f021456), b(context));
            case 1:
                hjjVar = new hjj(context.getResources().getString(R.string._2130842758_res_0x7f021486), null);
                break;
            case 2:
                hjjVar = new hjj(context.getResources().getString(R.string._2130843686_res_0x7f021826), null);
                break;
            case 3:
                return new hjj(context.getResources().getString(R.string._2130841430_res_0x7f020f56), a(context));
            case 4:
                return new hjj(context.getResources().getString(R.string._2130844075_res_0x7f0219ab), d(context));
            case 5:
                return new hjj(context.getResources().getString(R.string._2130845176_res_0x7f021df8), context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130845180_res_0x7f021dfc)));
            case 6:
                hjjVar = new hjj(context.getResources().getString(R.string._2130842760_res_0x7f021488), null);
                break;
            case 7:
                return new hjj(context.getResources().getString(R.string._2130842712_res_0x7f021458), context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130842716_res_0x7f02145c)));
            default:
                LogUtil.h("Track_PaceIntervalUtils", "type exception", str);
                return null;
        }
        return hjjVar;
    }

    public static void c(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, List<gxs> list) {
        if (nreVar == null || koq.b(list)) {
            LogUtil.h("Track_PaceIntervalUtils", "addRunningPaceToTableSet with error parameter tableDataSet:", nreVar, " index:", Integer.valueOf(i));
        } else {
            e(nreVar, nreVar.getRowsCount(), i, list);
        }
    }

    public static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, int i2, List<gxs> list) {
        if (nreVar == null || i < 0 || koq.b(list)) {
            LogUtil.h("Track_PaceIntervalUtils", "putRunningPaceToTableSet with error parameter tableDataSet:", nreVar, " trackRunningSpeed:", Integer.valueOf(i2), " rowIndex:", Integer.valueOf(i));
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_PaceIntervalUtils", "putRunningPaceToTableSet with context is null");
            return;
        }
        hjc hjcVar = new hjc(context.getResources().getString(R.string._2130844130_res_0x7f0219e2, Integer.valueOf(i2)));
        nreVar.putRowHeaderData(i, 0, hjcVar);
        LogUtil.a("Track_PaceIntervalUtils", "column size= ", Integer.valueOf(list.size()));
        for (int i3 = 0; i3 < list.size(); i3++) {
            e(nreVar, i, list, i3, i2);
        }
    }

    private static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, List<gxs> list, int i2, int i3) {
        int i4 = i2 + 1;
        if (i3 == 1) {
            nreVar.putContentData(i, i4, new hjc(b(list.get(i2).a(), list.get(i2).g())));
            return;
        }
        if (i3 == 2) {
            nreVar.putContentData(i, i4, new hjc(b(list.get(i2).b(), list.get(i2).g())));
            return;
        }
        if (i3 == 3) {
            nreVar.putContentData(i, i4, new hjc(b(list.get(i2).e(), list.get(i2).g())));
            return;
        }
        if (i3 == 4) {
            nreVar.putContentData(i, i4, new hjc(b(list.get(i2).d(), list.get(i2).g())));
        } else if (i3 == 5) {
            nreVar.putContentData(i, i4, new hjc(b(list.get(i2).c(), list.get(i2).g())));
        } else {
            LogUtil.h("Track_PaceIntervalUtils", "exception index ", Integer.valueOf(i3));
        }
    }

    private static String b(float f, String str) {
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_PaceIntervalUtils", "checkDataValidity context == null");
            return UnitUtil.e(f, 1, 0);
        }
        if (f == 0.0f) {
            return context.getString(R.string._2130850262_res_0x7f0231d6);
        }
        if ("EVERSION_EXCURSION".equals(str) || "SWING_ANGLE".equals(str)) {
            int i = (int) f;
            return context.getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, i, Integer.valueOf(i));
        }
        if (VastTag.DURATION.equals(str)) {
            return hji.b((long) (f * 1000.0f));
        }
        return UnitUtil.e(f, 1, (f * 10.0f) % 10.0f > 0.0f ? 1 : 0);
    }

    private static String a(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string.IDS_main_watch_heart_rate_unit_string));
    }

    private static String d(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130839766_res_0x7f0208d6));
    }

    private static String b(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130842713_res_0x7f021459));
    }
}
