package defpackage;

import android.text.TextUtils;
import android.util.Pair;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.ui.main.R$string;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class scz {
    public static String d(String str, Object obj) {
        if (!koq.e(obj, HiSampleConfig.class)) {
            return "1";
        }
        List list = (List) obj;
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_PhysiologicalCycleUtil_" + str, "getOvulationFertilePeriodSwitch list ", list);
            return "1";
        }
        HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
        if (hiSampleConfig == null) {
            ReleaseLogUtil.a("R_PhysiologicalCycleUtil_" + str, "getOvulationFertilePeriodSwitch hiSampleConfig is null");
            return "1";
        }
        String configData = hiSampleConfig.getConfigData();
        if (TextUtils.isEmpty(configData)) {
            ReleaseLogUtil.a("R_PhysiologicalCycleUtil_" + str, "getOvulationFertilePeriodSwitch configData ", configData);
            return "1";
        }
        try {
            String optString = new JSONObject(configData).optString("ovuFerPerSwitch");
            jfa.a(String.valueOf(10000), "ovuFerPerSwitch", CommonUtils.h(optString) != 0);
            ReleaseLogUtil.b("R_PhysiologicalCycleUtil_" + str, "getOvulationFertilePeriodSwitch ovulationFertilePeriodSwitch ", optString, " isPullAllStatus ", Boolean.valueOf(jfa.a()));
            return optString;
        } catch (JSONException e) {
            ReleaseLogUtil.c("R_PhysiologicalCycleUtil_" + str, "getOvulationFertilePeriodSwitch exception ", ExceptionUtils.d(e));
            return "1";
        }
    }

    public static boolean e() {
        return jfa.d(String.valueOf(10000), "ovuFerPerSwitch", true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int c(String str, String str2, List<HiHealthData> list, int i) {
        char c;
        boolean z;
        int a2;
        int i2 = 1;
        i2 = 1;
        r2 = true;
        r2 = true;
        boolean e = true;
        if (TextUtils.isEmpty(str2) || koq.b(list)) {
            ReleaseLogUtil.a("R_PhysiologicalCycleUtil_" + str, "getTargetDays type ", str2, " currentIndex ", Integer.valueOf(i));
            return 1;
        }
        str2.hashCode();
        switch (str2.hashCode()) {
            case 47602:
                if (str2.equals("0.0")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 49524:
                if (str2.equals("2.0")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 46730099:
                if (str2.equals("100.0")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 46731060:
                if (str2.equals("101.0")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 46732021:
                if (str2.equals("102.0")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 48577141:
                if (str2.equals("300.0")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 48578102:
                if (str2.equals("301.0")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 48579063:
                if (str2.equals("302.0")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 49500662:
                if (str2.equals("400.0")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 49501623:
                if (str2.equals("401.0")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 49502584:
                if (str2.equals("402.0")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 49503545:
                if (str2.equals("403.0")) {
                    c = 11;
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
                a2 = a(list, i);
                z = e;
                i2 = a2;
                break;
            case 1:
            case '\b':
            case '\n':
                e = e();
                if (e) {
                    a2 = b("401.0", list, i);
                } else {
                    a2 = b(list, i);
                }
                z = e;
                i2 = a2;
                break;
            case 2:
            case 4:
                a2 = b("101.0", list, i);
                z = e;
                i2 = a2;
                break;
            case 3:
            case 6:
                z = true;
                break;
            case 5:
            case 7:
                a2 = b("301.0", list, i);
                z = e;
                i2 = a2;
                break;
            case '\t':
            case 11:
                z = e();
                if (!z) {
                    i2 = b(list, i);
                    break;
                }
                break;
            default:
                ReleaseLogUtil.a("R_PhysiologicalCycleUtil_" + str, "getTargetDays default type ", str2);
                z = true;
                break;
        }
        LogUtil.c("PhysiologicalCycleUtil_" + str, "getTargetDays targetDays ", Integer.valueOf(i2), " type ", str2, " currentIndex ", Integer.valueOf(i), " isSupportOvulationFertilePeriod ", Boolean.valueOf(z));
        return i2;
    }

    private static int b(String str, List<HiHealthData> list, int i) {
        for (int i2 = i; i2 < list.size() - 1; i2++) {
            if (str.equals(String.valueOf(list.get(i2).getDouble("point_value")))) {
                return 1 + (i2 - i);
            }
        }
        return 1;
    }

    private static int b(List<HiHealthData> list, int i) {
        int size = list.size();
        int i2 = i;
        while (i2 >= 0 && i2 < size - 1) {
            HiHealthData hiHealthData = list.get(i2);
            if (hiHealthData != null) {
                if ("301.0".equals(String.valueOf(hiHealthData.getDouble("point_value")))) {
                    return i - i2;
                }
                i2--;
            }
        }
        return 1;
    }

    private static int a(List<HiHealthData> list, int i) {
        boolean e = e();
        for (int i2 = i; i2 >= 0; i2--) {
            String valueOf = String.valueOf(list.get(i2).getDouble("point_value"));
            if ("101.0".equals(valueOf) || "301.0".equals(valueOf) || (e && "401.0".equals(valueOf))) {
                return i - i2;
            }
        }
        return 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d3, code lost:
    
        if (r10.equals("0.0") == false) goto L56;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.util.Pair<java.lang.Integer, java.lang.CharSequence> dWi_(java.lang.String r9, java.lang.String r10, java.util.List<com.huawei.hihealth.HiHealthData> r11, int r12) {
        /*
            Method dump skipped, instructions count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.scz.dWi_(java.lang.String, java.lang.String, java.util.List, int):android.util.Pair");
    }

    private static Pair<Integer, CharSequence> dWh_(String str, List<HiHealthData> list, int i, int i2) {
        int i3;
        boolean e = e();
        String e2 = UnitUtil.e(i2, 1, 0);
        if ("FunctionSetPhysiologicalCycleCardReader".equals(str) && LanguageUtil.m(BaseApplication.e())) {
            e2 = " " + e2 + " ";
        }
        String a2 = nsf.a(R.plurals._2130903331_res_0x7f030123, i2, e2);
        String b = nsf.b(R$string.IDS_physiology_distances_fore, a2);
        while (true) {
            i3 = 4;
            if (i < 0) {
                break;
            }
            String valueOf = String.valueOf(list.get(i).getDouble("point_value"));
            if ("101.0".equals(valueOf)) {
                b = nsf.b(R$string.IDS_physiology_distances, a2);
                break;
            }
            if ("301.0".equals(valueOf)) {
                b = nsf.b(R$string.IDS_physiology_distances_fore, a2);
                break;
            }
            if (e && "401.0".equals(valueOf)) {
                b = nsf.b(R$string.IDS_physiology_distances_pre, a2);
                i3 = 5;
                break;
            }
            i--;
        }
        return new Pair<>(Integer.valueOf(i3), b);
    }

    public static int d(String str, List<HiHealthData> list, int i, long j) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_PhysiologicalCycleUtil_" + str, "getLastIndex dataList ", list);
            return -1;
        }
        boolean e = e();
        LogUtil.c("PhysiologicalCycleUtil_" + str, "getLastIndex isSupportOvulationFertilePeriod ", Boolean.valueOf(e), " currentIndex ", Integer.valueOf(i), " lastStartTime ", Long.valueOf(j));
        if (e) {
            return -1;
        }
        int size = list.size();
        boolean z = true;
        int i2 = -1;
        for (int i3 = 0; i3 < size; i3++) {
            HiHealthData hiHealthData = list.get(i3);
            if (hiHealthData != null) {
                if (i3 <= i) {
                    String valueOf = String.valueOf(hiHealthData.getDouble("point_value"));
                    if (TextUtils.isEmpty(valueOf)) {
                        continue;
                    } else {
                        z = a(valueOf);
                    }
                }
                if (!z) {
                    return -1;
                }
                if (j == hiHealthData.getStartTime()) {
                    i2 = i3;
                }
            }
        }
        return i2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static boolean a(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 47602:
                if (str.equals("0.0")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 49524:
                if (str.equals("2.0")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 49500662:
                if (str.equals("400.0")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 49501623:
                if (str.equals("401.0")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 49502584:
                if (str.equals("402.0")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c == 0 || c == 1 || c == 2 || c == 3 || c == 4;
    }
}
