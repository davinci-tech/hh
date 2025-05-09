package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.BaseChoreographyAction;
import com.huawei.pluginfitnessadvice.TargetConfig;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class fhq {
    public static Map<String, Double> d(TargetConfig targetConfig, ffg ffgVar, HeartZoneConf heartZoneConf, boolean z) {
        int b = CommonUtil.b(BaseApplication.getContext(), targetConfig.getId(), -1);
        LogUtil.a("CustomCourseActionIntensityUtil", "setIntensityTarget intensityConfigId ", Integer.valueOf(b));
        double d = b;
        if (d == -1.0d) {
            return c(-1.0d, -1.0d, -1.0d);
        }
        if (b == 14) {
            if (z) {
                return e(8, 1, heartZoneConf, targetConfig.getValueL());
            }
            return c(13, (int) targetConfig.getValueL(), ffgVar);
        }
        if (BaseChoreographyAction.c.b(b)) {
            return e(b, (b != 17 || heartZoneConf == null || heartZoneConf.getClassifyMethod() == 0) ? 0 : 1, heartZoneConf, targetConfig.getValueL());
        }
        if (b == 13) {
            return c(b, (int) targetConfig.getValueL(), ffgVar);
        }
        if (b == 15) {
            if (targetConfig.getValueL() > 0.0d && targetConfig.getValueH() > 0.0d) {
                return c(d, targetConfig.getValueL(), targetConfig.getValueH());
            }
            return c(d, -1.0d, -1.0d);
        }
        if (b == 16) {
            return d(b, targetConfig.getValueL(), targetConfig.getValueH());
        }
        if (b == 1) {
            return e(4, heartZoneConf, targetConfig);
        }
        return c(d, -1.0d, -1.0d);
    }

    private static Map<String, Double> c(double d, double d2, double d3) {
        LogUtil.a("CustomCourseActionIntensityUtil", "mIntensityType, ", Double.valueOf(d), " mIntensityLow ", Double.valueOf(d2), " mIntensityHeight ", Double.valueOf(d3));
        HashMap hashMap = new HashMap(3);
        hashMap.put("IntensityType", Double.valueOf(d));
        hashMap.put("IntensityLow", Double.valueOf(d2));
        hashMap.put("IntensityHeight", Double.valueOf(d3));
        return hashMap;
    }

    private static Map<String, Double> d(int i, double d, double d2) {
        if (d > 0.0d && d2 > 0.0d) {
            return c(i, d, d2);
        }
        return c(i, -1.0d, -1.0d);
    }

    private static String e(Context context, double d, double d2) {
        if (d <= 0.0d || d2 <= 0.0d) {
            return null;
        }
        double d3 = d / 1000.0d;
        if (UnitUtil.h()) {
            d3 = UnitUtil.d(d3, 3);
        }
        double d4 = d2 / 1000.0d;
        if (UnitUtil.h()) {
            d4 = UnitUtil.d(d4, 3);
        }
        return b(context, 1, a(context, (float) d3), a(context, (float) d4));
    }

    private static Map<String, Double> e(int i, HeartZoneConf heartZoneConf, TargetConfig targetConfig) {
        if (heartZoneConf == null) {
            return c(i, -1.0d, -1.0d);
        }
        return b(i, heartZoneConf.getMaxThreshold(), targetConfig.getValueL(), targetConfig.getValueH());
    }

    private static Map<String, Double> b(int i, int i2, double d, double d2) {
        double d3;
        double d4;
        if (d <= 0.0d || d2 <= 0.0d || i2 <= 0) {
            d3 = -1.0d;
            d4 = -1.0d;
        } else {
            double d5 = i2;
            d3 = (int) ((d * d5) / 100.0d);
            d4 = (int) ((d5 * d2) / 100.0d);
        }
        return c(i, d3, d4);
    }

    private static Map<String, Double> c(int i, int i2, ffg ffgVar) {
        if (ffgVar == null) {
            LogUtil.h("CustomCourseActionIntensityUtil", "setIntensityPaceZoneValue paceSectionConfig == null ");
            return c(i, -1.0d, -1.0d);
        }
        if (i2 <= 0) {
            LogUtil.h("CustomCourseActionIntensityUtil", "setIntensityPaceZoneValue index <= 0 ");
            return c(i, -1.0d, -1.0d);
        }
        return c(i, ffgVar.b(i2, true), ffgVar.b(i2, false));
    }

    public static String a(Context context, float f) {
        if (context == null) {
            return "";
        }
        int round = Math.round(f);
        int i = round / 60;
        if (f <= 0.001f) {
            return context.getResources().getString(R.string._2130849885_res_0x7f02305d);
        }
        if (LanguageUtil.r(context)) {
            return UnitUtil.d(round);
        }
        String string = context.getResources().getString(R.string._2130847575_res_0x7f022757, UnitUtil.e(i, 1, 0), Integer.valueOf(round % 60));
        if (TextUtils.isEmpty(string)) {
            return nsf.h(R.string._2130849885_res_0x7f02305d);
        }
        return (!LanguageUtil.b(context) || string.length() <= 2) ? string : string.substring(0, string.length() - 2).replace("'", ":");
    }

    private static Map<String, Double> e(int i, int i2, HeartZoneConf heartZoneConf, double d) {
        if (heartZoneConf == null) {
            return c(i, -1.0d, -1.0d);
        }
        return d(i, i2, heartZoneConf, d);
    }

    private static Map<String, Double> d(int i, int i2, HeartZoneConf heartZoneConf, double d) {
        double d2;
        double d3;
        if (d >= 0.0d) {
            int i3 = ((int) d) - 1;
            double zoneValue = heartZoneConf.getZoneValue(i3, true, i2);
            double zoneValue2 = heartZoneConf.getZoneValue(i3, false, i2);
            LogUtil.a("CustomCourseActionIntensityUtil", "setIntensityRelativeHeartRateZoneValue, valueL = ", Double.valueOf(d));
            d3 = zoneValue2;
            d2 = zoneValue;
        } else {
            d2 = -1.0d;
            d3 = -1.0d;
        }
        return c(i, d2, d3);
    }

    public static String b(Context context, int i, double d, double d2) {
        if (i == 13) {
            double d3 = UnitUtil.h() ? 1.609344d : 1.0d;
            return context.getResources().getString(R.string._2130845164_res_0x7f021dec, a(context, (float) (d * d3)), a(context, (float) (d2 * d3)));
        }
        if (BaseChoreographyAction.c.b(i)) {
            int i2 = (int) d;
            return context.getResources().getString(R.string._2130845163_res_0x7f021deb, context.getResources().getQuantityString(R.plurals._2130903151_res_0x7f03006f, i2, Integer.valueOf(i2), Integer.valueOf((int) d2)));
        }
        if (i == 16) {
            return c(context, d, d2);
        }
        if (i == 15) {
            return e(context, d, d2);
        }
        LogUtil.a("CustomCourseActionIntensityUtil", "setIntensityMsgTextView type exception", Integer.valueOf(i));
        return null;
    }

    private static String b(Context context, int i, String str, String str2) {
        String string = context.getResources().getString(R.string._2130845600_res_0x7f021fa0, str, str2);
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903281_res_0x7f0300f1, i, string);
        }
        return context.getResources().getQuantityString(R.plurals._2130903280_res_0x7f0300f0, i, string);
    }

    private static String c(Context context, double d, double d2) {
        if (d <= 0.0d || d2 <= 0.0d) {
            return null;
        }
        long j = (long) d;
        return context.getResources().getQuantityString(R.plurals._2130903316_res_0x7f030114, (int) j, Long.valueOf(j), Long.valueOf((long) d2));
    }

    public static fhx e(HeartZoneConf heartZoneConf) {
        fhx fhxVar = new fhx();
        if (heartZoneConf != null) {
            fhxVar.e(heartZoneConf.getMaxThreshold());
            fhxVar.a(heartZoneConf.getAnaerobicThreshold());
            fhxVar.c(heartZoneConf.getAerobicThreshold());
            fhxVar.b(heartZoneConf.getFatBurnThreshold());
            fhxVar.f(heartZoneConf.getWarmUpThreshold());
            fhxVar.d(heartZoneConf.getFitnessThreshold());
        } else {
            LogUtil.h("CustomCourseActionIntensityUtil", "getMaxHeartRateZone, heartZoneConf == null");
        }
        return fhxVar;
    }

    public static fhx d(HeartZoneConf heartZoneConf) {
        fhx fhxVar = new fhx();
        if (heartZoneConf != null) {
            fhxVar.e(heartZoneConf.getMaxThreshold());
            fhxVar.a(heartZoneConf.getAnaerobicAdvanceThreshold());
            fhxVar.c(heartZoneConf.getAnaerobicBaseThreshold());
            fhxVar.b(heartZoneConf.getLacticAcidThreshold());
            fhxVar.f(heartZoneConf.getAerobicAdvanceThreshold());
            fhxVar.d(heartZoneConf.getAerobicBaseThreshold());
        } else {
            LogUtil.h("CustomCourseActionIntensityUtil", "getReserveHeartRateZone, heartZoneConf == null");
        }
        return fhxVar;
    }

    public static ffg a() {
        return fgc.b();
    }

    public static void axN_(ffd ffdVar, Bundle bundle) {
        Map<String, Double> n = ffdVar.n();
        if (n == null || n.isEmpty() || c(n)) {
            bundle.putInt("IntensityType", -1);
            return;
        }
        int intValue = n.get("IntensityType").intValue();
        if (intValue == -1) {
            bundle.putInt("IntensityType", -1);
            return;
        }
        int intValue2 = n.get("IntensityLow").intValue();
        int intValue3 = n.get("IntensityHeight").intValue();
        if (intValue2 == -1 || intValue3 == -1) {
            bundle.putInt("IntensityType", -1);
            return;
        }
        bundle.putInt("IntensityType", intValue);
        bundle.putDouble("IntensityLow", n.get("IntensityLow").doubleValue());
        bundle.putDouble("IntensityHeight", n.get("IntensityHeight").doubleValue());
    }

    private static boolean c(Map<String, Double> map) {
        return map.get("IntensityType") == null || map.get("IntensityLow") == null || map.get("IntensityHeight") == null;
    }
}
