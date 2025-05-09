package defpackage;

import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import health.compact.a.HEXUtils;
import health.compact.a.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;

/* loaded from: classes.dex */
public class hpw {
    public static byte[] e(HeartRateZoneMgr heartRateZoneMgr) {
        if (heartRateZoneMgr == null || b(heartRateZoneMgr)) {
            LogUtil.a("Track_HeartDataParses", "checkHeartObject() == null ");
            return new byte[0];
        }
        if (d(heartRateZoneMgr)) {
            ReleaseLogUtil.d("Track_HeartDataParses", "heart data exception");
            hpz.b().d();
            return new byte[0];
        }
        HeartRateThresholdConfig postureType = heartRateZoneMgr.getPostureType(1);
        String str = (e(postureType) ? e(postureType, "") : "") + HEXUtils.e(12) + HEXUtils.e(1) + HEXUtils.e(postureType.getRestHeartRate());
        if (b(postureType)) {
            str = b(postureType, str);
        }
        String d = d(postureType, str);
        HeartRateThresholdConfig postureType2 = heartRateZoneMgr.getPostureType(2);
        if (e(postureType2)) {
            d = e(18, postureType2, d);
        }
        if (b(postureType2)) {
            d = b(27, postureType2, d);
        }
        HeartRateThresholdConfig postureType3 = heartRateZoneMgr.getPostureType(3);
        if (e(postureType3)) {
            d = e(33, postureType3, d);
        }
        if (b(postureType3)) {
            d = b(42, postureType3, d);
        }
        HeartRateThresholdConfig postureType4 = heartRateZoneMgr.getPostureType(4);
        if (e(postureType4)) {
            d = e(48, postureType4, d);
        }
        if (b(postureType4)) {
            d = b(57, postureType4, d);
        }
        LogUtil.c("Track_HeartDataParses", "getHeartRateZoneConfigure command= ", d);
        return HEXUtils.c(d);
    }

    public static boolean d(HeartRateZoneMgr heartRateZoneMgr) {
        HeartRateThresholdConfig postureType = heartRateZoneMgr.getPostureType(1);
        if (a(postureType) || d(postureType)) {
            LogUtil.a("Track_HeartDataParses", "standing exception:", Arrays.toString(postureType.getHeartData()));
            return true;
        }
        HeartRateThresholdConfig postureType2 = heartRateZoneMgr.getPostureType(2);
        if (a(postureType2) || d(postureType2)) {
            LogUtil.a("Track_HeartDataParses", "riding exception:", Arrays.toString(postureType2.getHeartData()));
            return true;
        }
        HeartRateThresholdConfig postureType3 = heartRateZoneMgr.getPostureType(3);
        if (a(postureType3) || d(postureType3)) {
            LogUtil.a("Track_HeartDataParses", "strokes exception:", Arrays.toString(postureType3.getHeartData()));
            return true;
        }
        HeartRateThresholdConfig postureType4 = heartRateZoneMgr.getPostureType(4);
        if (!a(postureType4) && !d(postureType4)) {
            return false;
        }
        LogUtil.a("Track_HeartDataParses", "otherHeart exception:", Arrays.toString(postureType4.getHeartData()));
        return true;
    }

    private static boolean d(HeartRateThresholdConfig heartRateThresholdConfig) {
        return heartRateThresholdConfig.getWarningLimit() == 0;
    }

    private static boolean a(HeartRateThresholdConfig heartRateThresholdConfig) {
        boolean z;
        for (int i : heartRateThresholdConfig.getHeartData()) {
            if (i < 0 || i > 220) {
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            return z;
        }
        return heartRateThresholdConfig.getWarningLimit() == 0;
    }

    private static String e(int i, HeartRateThresholdConfig heartRateThresholdConfig, String str) {
        return ((((((((str + HEXUtils.e(i) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getWarningEnable() ? 1 : 0)) + HEXUtils.e(i + 1) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getClassifyMethod())) + HEXUtils.e(i + 2) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getWarningLimit())) + HEXUtils.e(i + 3) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getFitnessThreshold())) + HEXUtils.e(i + 4) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getWarmUpThreshold())) + HEXUtils.e(i + 5) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getFatBurnThreshold())) + HEXUtils.e(i + 6) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAerobicThreshold())) + HEXUtils.e(i + 7) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAnaerobicThreshold())) + HEXUtils.e(i + 8) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getMaxThreshold());
    }

    private static String b(int i, HeartRateThresholdConfig heartRateThresholdConfig, String str) {
        return (((((str + HEXUtils.e(i) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getRestHeartRate())) + HEXUtils.e(i + 1) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAerobicBaseThreshold())) + HEXUtils.e(i + 2) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAerobicAdvanceThreshold())) + HEXUtils.e(i + 3) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getLacticAcidThreshold())) + HEXUtils.e(i + 4) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAnaerobicBaseThreshold())) + HEXUtils.e(i + 5) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAnaerobicAdvanceThreshold());
    }

    private static boolean b(HeartRateZoneMgr heartRateZoneMgr) {
        return heartRateZoneMgr.getPostureType(1) == null || heartRateZoneMgr.getPostureType(2) == null || heartRateZoneMgr.getPostureType(3) == null || heartRateZoneMgr.getPostureType(4) == null;
    }

    private static boolean e(HeartRateThresholdConfig heartRateThresholdConfig) {
        return (heartRateThresholdConfig.getFitnessThreshold() > 0) && (heartRateThresholdConfig.getWarmUpThreshold() > 0 && heartRateThresholdConfig.getFatBurnThreshold() > 0) && (heartRateThresholdConfig.getAerobicThreshold() > 0 && heartRateThresholdConfig.getAnaerobicThreshold() > 0 && heartRateThresholdConfig.getMaxThreshold() > 0);
    }

    private static boolean b(HeartRateThresholdConfig heartRateThresholdConfig) {
        return (heartRateThresholdConfig.getRestHeartRate() > 0 && heartRateThresholdConfig.getAerobicBaseThreshold() > 0 && heartRateThresholdConfig.getAerobicAdvanceThreshold() > 0) && (heartRateThresholdConfig.getLacticAcidThreshold() > 0 && heartRateThresholdConfig.getAnaerobicBaseThreshold() > 0 && heartRateThresholdConfig.getAnaerobicAdvanceThreshold() > 0);
    }

    private static String d(HeartRateThresholdConfig heartRateThresholdConfig, String str) {
        if (c(heartRateThresholdConfig)) {
            return str;
        }
        return (((((str + HEXUtils.e(63) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getLactateThresholdHeartRate())) + HEXUtils.e(64) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getLthrAnaerobicInterval())) + HEXUtils.e(65) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getLthrLactateThreshold())) + HEXUtils.e(66) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getLthrAerobicHighZone())) + HEXUtils.e(67) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getLthrAerobicLowZone())) + HEXUtils.e(68) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getLthrRecoveryInterval());
    }

    private static boolean c(HeartRateThresholdConfig heartRateThresholdConfig) {
        return heartRateThresholdConfig.getLactateThresholdHeartRate() == 0 || heartRateThresholdConfig.getLthrAnaerobicInterval() == 0 || heartRateThresholdConfig.getLthrLactateThreshold() == 0 || heartRateThresholdConfig.getLthrAerobicHighZone() == 0 || heartRateThresholdConfig.getLthrAerobicLowZone() == 0 || heartRateThresholdConfig.getLthrRecoveryInterval() == 0;
    }

    private static String b(HeartRateThresholdConfig heartRateThresholdConfig, String str) {
        return ((((str + HEXUtils.e(13) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAerobicBaseThreshold())) + HEXUtils.e(14) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAerobicAdvanceThreshold())) + HEXUtils.e(15) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getLacticAcidThreshold())) + HEXUtils.e(16) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAnaerobicBaseThreshold())) + HEXUtils.e(17) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAnaerobicAdvanceThreshold());
    }

    private static String e(HeartRateThresholdConfig heartRateThresholdConfig, String str) {
        return (((((((((str + HEXUtils.e(2) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getFitnessThreshold())) + HEXUtils.e(3) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getWarmUpThreshold())) + HEXUtils.e(4) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getFatBurnThreshold())) + HEXUtils.e(5) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAerobicThreshold())) + HEXUtils.e(6) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getAnaerobicThreshold())) + HEXUtils.e(7) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getMaxThreshold())) + HEXUtils.e(8) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getWarningEnable() ? 1 : 0)) + HEXUtils.e(9) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getWarningLimit())) + HEXUtils.e(10) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getClassifyMethod())) + HEXUtils.e(11) + HEXUtils.e(1) + HEXUtils.e(heartRateThresholdConfig.getMaxThreshold());
    }
}
