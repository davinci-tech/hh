package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.datatype.RunPaceZoneConfig;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.fitnessdatatype.ActivityReminder;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes5.dex */
public class jhn {
    public static byte[] c(List<MotionGoal> list) {
        return jhm.a(list);
    }

    public static byte[] d(UserInfomation userInfomation, DeviceInfo deviceInfo) {
        int i;
        int i2;
        String str;
        LogUtil.a("FitnessPackage", "setDataUserInfo userInfomation");
        if (userInfomation.getMetricHeight() > 0) {
            i = userInfomation.getMetricHeight();
        } else {
            LogUtil.h("FitnessPackage", "setDataUserInfo invalid height");
            i = 170;
        }
        String e = e(userInfomation, i);
        if (userInfomation.getMetricWeight() > 0.0f) {
            i2 = (int) userInfomation.getMetricWeight();
        } else {
            LogUtil.h("FitnessPackage", "setDataUserInfo invalid weight");
            i2 = 60;
        }
        String d = d(userInfomation, i2);
        String str2 = cvx.e(3) + cvx.e(1) + cvx.e(userInfomation.getAge());
        String c = c(userInfomation, deviceInfo);
        float f = i;
        String str3 = cvx.e(6) + cvx.e(1) + cvx.e(Math.round(0.42f * f));
        String str4 = cvx.e(7) + cvx.e(1) + cvx.e(Math.round(f * 0.83f));
        String c2 = c(userInfomation);
        String str5 = cvx.e(8) + cvx.e(4) + cvx.b(userInfomation.getMaxVo2());
        String str6 = cvx.e(9) + cvx.e(4) + cvx.b(userInfomation.getVo2Time());
        if (e()) {
            str = e + d + str2 + c + str3 + str4 + str5 + str6 + c2;
        } else {
            str = e + d + str2 + c + str3 + str4 + c2;
        }
        if (c(deviceInfo)) {
            String str7 = cvx.e(11) + cvx.e(4) + cvx.b(Math.round(userInfomation.getWeight() * 100.0f));
            LogUtil.a("FitnessPackage", "isSupportSendHighWeight and dataUserInfo.getWeight() is", Float.valueOf(userInfomation.getWeight()));
            str = str + str7;
        }
        LogUtil.a("FitnessPackage", "tlvs is ", str);
        return cvx.a(str);
    }

    private static boolean c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("FitnessPackage", "isSupportSendMaxVos deviceInfo is null.");
            return false;
        }
        return cwi.c(deviceInfo, 179);
    }

    private static boolean e() {
        DeviceInfo d = jpt.d("FitnessPackage");
        if (d == null) {
            LogUtil.h("FitnessPackage", "isSupportSendMaxVos deviceInfo is null.");
            return false;
        }
        int productType = d.getProductType();
        if (productType == 10 || productType == 23 || productType == 24) {
            return false;
        }
        return (jhb.a() && jhb.d(d.getDeviceIdentify())) ? false : true;
    }

    private static String e(UserInfomation userInfomation, int i) {
        String str = cvx.e(1) + cvx.e(1) + cvx.e(i);
        if (userInfomation.isHeightValid()) {
            return str;
        }
        LogUtil.a("FitnessPackage", "height data is invalid");
        return "";
    }

    private static String d(UserInfomation userInfomation, int i) {
        String str = cvx.e(2) + cvx.e(1) + cvx.e(i);
        if (userInfomation.isWeightValid()) {
            return str;
        }
        LogUtil.a("FitnessPackage", "weight data is invalid");
        return "";
    }

    private static String c(UserInfomation userInfomation, DeviceInfo deviceInfo) {
        String e = e(userInfomation, deviceInfo);
        if (userInfomation.isGenderValid()) {
            return e;
        }
        LogUtil.a("FitnessPackage", "gender data is invalid");
        return "";
    }

    private static String c(UserInfomation userInfomation) {
        String a2 = a(userInfomation);
        if (userInfomation.isBirthdayValid()) {
            return a2;
        }
        LogUtil.a("FitnessPackage", "birthday data is invalid");
        return "";
    }

    private static String e(UserInfomation userInfomation, DeviceInfo deviceInfo) {
        String str;
        int gender = userInfomation.getGender();
        if (gender == 1) {
            str = cvx.e(5) + cvx.e(1) + cvx.e(2);
        } else {
            str = cvx.e(5) + cvx.e(1) + cvx.e(1);
        }
        if (!cwi.c(deviceInfo, 87) || gender != 2) {
            return str;
        }
        return cvx.e(5) + cvx.e(1) + cvx.e(3);
    }

    private static String a(UserInfomation userInfomation) {
        int i;
        try {
            i = Integer.parseInt(userInfomation.getBirthday());
        } catch (NumberFormatException e) {
            LogUtil.b("FitnessPackage", "setDataUserInfo NumberFormatException:", e);
            i = 0;
        }
        if (i != 0) {
            String str = i + "";
            if (str.length() != 8) {
                return "";
            }
            try {
                int parseInt = Integer.parseInt(str.substring(0, 4));
                int parseInt2 = Integer.parseInt(str.substring(4, 6));
                int parseInt3 = Integer.parseInt(str.substring(6));
                return cvx.e(4) + cvx.e(4) + cvx.a(parseInt) + cvx.e(parseInt2) + cvx.e(parseInt3);
            } catch (NumberFormatException unused) {
                LogUtil.b("FitnessPackage", "setDataUserInfo NumberFormatException");
                return "";
            }
        }
        LogUtil.h("FitnessPackage", "birthday is 0");
        return "";
    }

    public static byte[] b() {
        return cvx.a(cvx.e(10) + cvx.e(1) + cvx.e(1));
    }

    public static byte[] a() {
        return cvx.a(cvx.e(1) + cvx.e(0));
    }

    public static byte[] a(long j, long j2) {
        String str = ("" + cvx.e(1)) + cvx.e(0);
        if (j != 0) {
            String str2 = (str + cvx.e(3) + cvx.e(4)) + cvx.b(j);
            if (j2 != 0) {
                str = (str2 + cvx.e(4) + cvx.e(4)) + cvx.b(j2);
            } else {
                str = str2;
            }
        }
        return cvx.a(str);
    }

    public static byte[] d(int i) {
        return cvx.a(((("" + cvx.e(129)) + cvx.e(4)) + cvx.e(2)) + cvx.e(2) + cvx.a(i));
    }

    public static byte[] a(ActivityReminder activityReminder) {
        String str = "";
        if (activityReminder != null) {
            String str2 = ((("" + cvx.e(129)) + cvx.e(17)) + cvx.e(2) + cvx.e(1) + cvx.e(activityReminder.isEnabled() ? 1 : 0)) + cvx.e(3) + cvx.e(1) + cvx.e(activityReminder.getInterval());
            int startTime = activityReminder.getStartTime();
            String str3 = str2 + cvx.e(4) + cvx.e(2) + cvx.e((startTime >> 8) & 255) + cvx.e(startTime & 255);
            int endTime = activityReminder.getEndTime();
            str = (str3 + cvx.e(5) + cvx.e(2) + cvx.e((endTime >> 8) & 255) + cvx.e(endTime & 255)) + cvx.e(6) + cvx.e(1) + cvx.e(activityReminder.getCycle());
        }
        return cvx.a(str);
    }

    public static byte[] e(int i, int i2) {
        return cvx.a(((((("" + cvx.e(129)) + cvx.e(8)) + cvx.e(2)) + cvx.e(2) + cvx.a(i)) + cvx.e(3)) + cvx.e(2) + cvx.a(i2));
    }

    public static byte[] e(long j, long j2) {
        ByteBuffer byteBuffer;
        int i = 2;
        byte[] bArr = {-127, 0};
        ByteBuffer byteBuffer2 = null;
        if (j != 0) {
            ByteBuffer a2 = a(3, (int) j);
            int capacity = a2.capacity() + 2;
            if (j2 != 0) {
                byteBuffer2 = a(4, (int) j2);
                capacity += byteBuffer2.capacity();
            }
            i = capacity;
            ByteBuffer byteBuffer3 = byteBuffer2;
            byteBuffer2 = a2;
            byteBuffer = byteBuffer3;
        } else {
            byteBuffer = null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.put(bArr);
        if (byteBuffer2 != null) {
            allocate.put(byteBuffer2.array());
        }
        if (byteBuffer != null) {
            allocate.put(byteBuffer.array());
        }
        return allocate.array();
    }

    public static ByteBuffer a(int i, int i2) {
        byte[] g = cvx.g(i2);
        byte[] c = cvx.c(g.length);
        byte[] f = cvx.f(i);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + c.length + g.length);
        allocate.put(f).put(c).put(g);
        return allocate;
    }

    public static ByteBuffer c(int i, int i2) {
        byte[] b = cvx.b(i2);
        byte[] c = cvx.c(b.length);
        byte[] f = cvx.f(i);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + c.length + b.length);
        allocate.put(f).put(c).put(b);
        return allocate;
    }

    public static byte[] e(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put((byte) -127).put((byte) 4).put(c(2, i).array());
        return allocate.array();
    }

    public static byte[] c(long j, long j2) {
        ByteBuffer byteBuffer;
        int i = 2;
        byte[] bArr = {-127, 0};
        ByteBuffer byteBuffer2 = null;
        if (j != 0) {
            ByteBuffer a2 = a(3, (int) j);
            int capacity = a2.capacity() + 2;
            if (j2 != 0) {
                byteBuffer2 = a(4, (int) j2);
                capacity += byteBuffer2.capacity();
            }
            i = capacity;
            ByteBuffer byteBuffer3 = byteBuffer2;
            byteBuffer2 = a2;
            byteBuffer = byteBuffer3;
        } else {
            byteBuffer = null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.put(bArr);
        if (byteBuffer2 != null) {
            allocate.put(byteBuffer2.array());
        }
        if (byteBuffer != null) {
            allocate.put(byteBuffer.array());
        }
        return allocate.array();
    }

    public static byte[] b(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put((byte) -127).put((byte) 4).put(c(2, i).array());
        return allocate.array();
    }

    public static byte[] a(List<jid> list) {
        StringBuffer stringBuffer = new StringBuffer(16);
        int i = 0;
        for (jid jidVar : list) {
            stringBuffer.append(cvx.e(2));
            stringBuffer.append(cvx.e(5));
            stringBuffer.append(cvx.e(jidVar.c()));
            stringBuffer.append(cvx.e(jidVar.b()));
            stringBuffer.append(cvx.a(jidVar.a()));
            stringBuffer.append(cvx.e(jidVar.e()));
            i += 7;
        }
        return cvx.a(cvx.e(129) + cvx.e(i) + stringBuffer.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v7 */
    public static byte[] b(HeartZoneConf heartZoneConf, int i, boolean z) {
        String str;
        boolean z2;
        int i2 = 3;
        if (i == 0) {
            str = ("" + cvx.e(8)) + cvx.e(1) + cvx.e(heartZoneConf.isWarningEnble() ? 1 : 0);
            ?? r1 = heartZoneConf.getWarningLimitHR() > 0 && heartZoneConf.getFitnessThreshold() > 0;
            ?? r2 = heartZoneConf.getWarmUpThreshold() > 0 && heartZoneConf.getFatBurnThreshold() > 0;
            ?? r6 = heartZoneConf.getAerobicThreshold() > 0 && heartZoneConf.getAnaerobicThreshold() > 0 && heartZoneConf.getMaxThreshold() > 0;
            if (r1 != false && r2 != false && r6 != false) {
                str = a(heartZoneConf, str);
                i2 = 27;
            }
            ?? r12 = heartZoneConf.getAerobicBaseThreshold() > 0 && heartZoneConf.getAerobicAdvanceThreshold() > 0;
            z2 = heartZoneConf.getLacticAcidThreshold() > 0 && heartZoneConf.getAnaerobicBaseThreshold() > 0 && heartZoneConf.getAnaerobicAdvanceThreshold() > 0;
            if (z && r12 != false && z2) {
                str = b(heartZoneConf, str);
                i2 += 15;
            }
        } else {
            str = ("" + cvx.e(8)) + cvx.e(1) + cvx.e(heartZoneConf.isWarningEnbleHRR() ? 1 : 0);
            ?? r9 = heartZoneConf.getWarningLimitHRHRR() > 0 && heartZoneConf.getAerobicBaseThreshold() > 0;
            ?? r13 = heartZoneConf.getAerobicAdvanceThreshold() > 0 && heartZoneConf.getLacticAcidThreshold() > 0;
            z2 = heartZoneConf.getAnaerobicBaseThreshold() > 0 && heartZoneConf.getAnaerobicAdvanceThreshold() > 0 && heartZoneConf.getHrrMaxThreshold() > 0;
            if (r9 != false && r13 != false && z2) {
                str = d(heartZoneConf, str);
                i2 = 27;
            }
        }
        return cvx.a(cvx.e(129) + cvx.e(i2 + 6) + c(heartZoneConf, str));
    }

    public static byte[] d() {
        return cvx.a(cvx.e(129) + cvx.e(3) + (cvx.e(18) + cvx.e(1) + cvx.e(1)));
    }

    private static String c(HeartZoneConf heartZoneConf, String str) {
        return (((str + cvx.e(10)) + cvx.e(1) + cvx.e(heartZoneConf.getClassifyMethod())) + cvx.e(12)) + cvx.e(1) + cvx.e(heartZoneConf.getRestHeartRate());
    }

    private static String a(HeartZoneConf heartZoneConf, String str) {
        return (((((((((((((((str + cvx.e(9)) + cvx.e(1) + cvx.e(heartZoneConf.getWarningLimitHR())) + cvx.e(2)) + cvx.e(1) + cvx.e(heartZoneConf.getFitnessThreshold())) + cvx.e(3)) + cvx.e(1) + cvx.e(heartZoneConf.getWarmUpThreshold())) + cvx.e(4)) + cvx.e(1) + cvx.e(heartZoneConf.getFatBurnThreshold())) + cvx.e(5)) + cvx.e(1) + cvx.e(heartZoneConf.getAerobicThreshold())) + cvx.e(6)) + cvx.e(1) + cvx.e(heartZoneConf.getAnaerobicThreshold())) + cvx.e(7)) + cvx.e(1) + cvx.e(heartZoneConf.getMaxThreshold())) + cvx.e(11)) + cvx.e(1) + cvx.e(heartZoneConf.getMaxThreshold());
    }

    private static String b(HeartZoneConf heartZoneConf, String str) {
        return (((((((((str + cvx.e(13)) + cvx.e(1) + cvx.e(heartZoneConf.getAerobicBaseThreshold())) + cvx.e(14)) + cvx.e(1) + cvx.e(heartZoneConf.getAerobicAdvanceThreshold())) + cvx.e(15)) + cvx.e(1) + cvx.e(heartZoneConf.getLacticAcidThreshold())) + cvx.e(16)) + cvx.e(1) + cvx.e(heartZoneConf.getAnaerobicBaseThreshold())) + cvx.e(17)) + cvx.e(1) + cvx.e(heartZoneConf.getAnaerobicAdvanceThreshold());
    }

    private static String d(HeartZoneConf heartZoneConf, String str) {
        boolean z = false;
        boolean z2 = heartZoneConf.getWarningLimitHRHRR() > 0 && heartZoneConf.getAerobicBaseThreshold() > 0;
        boolean z3 = heartZoneConf.getAerobicAdvanceThreshold() > 0 && heartZoneConf.getLacticAcidThreshold() > 0;
        if (heartZoneConf.getAnaerobicBaseThreshold() > 0 && heartZoneConf.getAnaerobicAdvanceThreshold() > 0 && heartZoneConf.getHrrMaxThreshold() > 0) {
            z = true;
        }
        if (!z2 || !z3 || !z) {
            return str;
        }
        return (((((((((((((((str + cvx.e(9)) + cvx.e(1) + cvx.e(heartZoneConf.getWarningLimitHRHRR())) + cvx.e(2)) + cvx.e(1) + cvx.e(heartZoneConf.getAerobicBaseThreshold())) + cvx.e(3)) + cvx.e(1) + cvx.e(heartZoneConf.getAerobicAdvanceThreshold())) + cvx.e(4)) + cvx.e(1) + cvx.e(heartZoneConf.getLacticAcidThreshold())) + cvx.e(5)) + cvx.e(1) + cvx.e(heartZoneConf.getAnaerobicBaseThreshold())) + cvx.e(6)) + cvx.e(1) + cvx.e(heartZoneConf.getAnaerobicAdvanceThreshold())) + cvx.e(7)) + cvx.e(1) + cvx.e(heartZoneConf.getHrrMaxThreshold())) + cvx.e(11)) + cvx.e(1) + cvx.e(heartZoneConf.getHrrMaxThreshold());
    }

    public static byte[] a(int i) {
        return cvx.a("" + cvx.e(1) + cvx.e(1) + cvx.e(i));
    }

    public static byte[] f(int i) {
        return cvx.a("" + cvx.e(1) + cvx.e(1) + cvx.e(i));
    }

    public static byte[] c(int i) {
        return cvx.a("" + cvx.e(1) + cvx.e(1) + cvx.e(i));
    }

    public static byte[] c() {
        return cvx.a(("" + cvx.e(1)) + cvx.e(0));
    }

    public static byte[] e(RunPaceZoneConfig runPaceZoneConfig) {
        return cvx.a(((((((((((("" + cvx.e(1)) + cvx.e(2) + cvx.a(runPaceZoneConfig.getEasyPaceZoneMinValue())) + cvx.e(2)) + cvx.e(2) + cvx.a(runPaceZoneConfig.getMarathonPaceZoneMinValue())) + cvx.e(3)) + cvx.e(2) + cvx.a(runPaceZoneConfig.getLactatePaceZoneMinValue())) + cvx.e(4)) + cvx.e(2) + cvx.a(runPaceZoneConfig.getAnaerobicPaceZoneMinValue())) + cvx.e(5)) + cvx.e(2) + cvx.a(runPaceZoneConfig.getMaxOxygenPaceZoneMinValue())) + cvx.e(6)) + cvx.e(2) + cvx.a(runPaceZoneConfig.getMaxOxygenPaceZoneMaxValue()));
    }

    public static byte[] c(UserInfomation userInfomation, int i) {
        byte b = (byte) 0;
        byte b2 = (byte) 1;
        return new byte[]{5, BaseType.Vector, (byte) userInfomation.getMetricHeight(), (byte) userInfomation.getMetricWeight(), (byte) userInfomation.getAge(), (byte) userInfomation.getGender(), (byte) userInfomation.getWalkStepLen(), (byte) userInfomation.getRunStepLen(), 0, b, b2, (byte) ((i >> 8) & 255), (byte) (i & 255), b2, b, b};
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v8 */
    public static byte[] c(HeartRateThresholdConfig heartRateThresholdConfig) {
        int i;
        boolean z = false;
        if (heartRateThresholdConfig == null) {
            LogUtil.h("FitnessPackage", "getHeartRateZoneConfigure heartData is null");
            return new byte[0];
        }
        String str = ("" + cvx.e(8)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getWarningEnable() ? 1 : 0);
        ?? r3 = heartRateThresholdConfig.getWarningLimit() > 0 && heartRateThresholdConfig.getFitnessThreshold() > 0;
        ?? r4 = heartRateThresholdConfig.getWarmUpThreshold() > 0 && heartRateThresholdConfig.getFatBurnThreshold() > 0;
        ?? r5 = heartRateThresholdConfig.getAerobicThreshold() > 0 && heartRateThresholdConfig.getAnaerobicThreshold() > 0 && heartRateThresholdConfig.getMaxThreshold() > 0;
        if (r3 == true && r4 == true && r5 == true) {
            str = c(heartRateThresholdConfig, str);
            i = 27;
        } else {
            i = 3;
        }
        ?? r42 = heartRateThresholdConfig.getAerobicBaseThreshold() > 0 && heartRateThresholdConfig.getAerobicAdvanceThreshold() > 0;
        if (heartRateThresholdConfig.getLacticAcidThreshold() > 0 && heartRateThresholdConfig.getAnaerobicBaseThreshold() > 0 && heartRateThresholdConfig.getAnaerobicAdvanceThreshold() > 0) {
            z = true;
        }
        if (r42 != false && z) {
            str = b(heartRateThresholdConfig, str);
            i += 15;
        }
        return cvx.a(cvx.e(129) + cvx.e(i + 6) + e(heartRateThresholdConfig, str));
    }

    private static String c(HeartRateThresholdConfig heartRateThresholdConfig, String str) {
        return (((((((((((((((str + cvx.e(9)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getWarningLimit())) + cvx.e(2)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getFitnessThreshold())) + cvx.e(3)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getWarmUpThreshold())) + cvx.e(4)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getFatBurnThreshold())) + cvx.e(5)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getAerobicThreshold())) + cvx.e(6)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getAnaerobicThreshold())) + cvx.e(7)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getMaxThreshold())) + cvx.e(11)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getMaxThreshold());
    }

    private static String b(HeartRateThresholdConfig heartRateThresholdConfig, String str) {
        return (((((((((str + cvx.e(13)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getAerobicBaseThreshold())) + cvx.e(14)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getAerobicAdvanceThreshold())) + cvx.e(15)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getLacticAcidThreshold())) + cvx.e(16)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getAnaerobicBaseThreshold())) + cvx.e(17)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getAnaerobicAdvanceThreshold());
    }

    private static String e(HeartRateThresholdConfig heartRateThresholdConfig, String str) {
        return (((str + cvx.e(10)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getClassifyMethod())) + cvx.e(12)) + cvx.e(1) + cvx.e(heartRateThresholdConfig.getRestHeartRate());
    }
}
