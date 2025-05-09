package defpackage;

import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes7.dex */
public final class rtv {
    private static final String e = "BiUtil";

    private static int h(int i) {
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 2 : -1;
    }

    private static int j(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    i2 = 4;
                    if (i != 4) {
                        i2 = 5;
                        if (i != 5) {
                            return -1;
                        }
                    }
                }
            }
        }
        return i2;
    }

    public static void d() {
        HashMap hashMap = new HashMap();
        hashMap.put("pageId", "BloodPressure_0002");
        hashMap.put("sessionId", "");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("event", 1);
        hashMap2.put(FunctionSetBeanReader.BI_ELEMENT, "m63");
        hashMap2.put("moduleName", "main_page");
        hashMap2.put("value", "all_data");
        iyb iybVar = new iyb();
        iybVar.e(hashMap);
        iybVar.d(hashMap2);
        iybVar.d(new ArrayList());
        ixx.d().a(BaseApplication.getContext(), AnalyticsValue.STATISTICS_BLOOD_PRESSURE_ALL_DATA.value(), iybVar, 0);
    }

    public static void e(int i, boolean z) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("isAllData", Boolean.valueOf(z));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.DELETE_SLEEP_DATA.value(), hashMap, 0);
    }

    public static void c(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("dataType", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SWITCH_SLEEP_DATA_SOURCE.value(), hashMap, 0);
    }

    public static void f() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.TEMPERATURE_SWITCH_TYPE.value(), hashMap, 0);
    }

    public static void a() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.TEMPERATURE_ENTER_DAY_LIST.value(), hashMap, 0);
    }

    public static void c() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.TEMPERATURE_ENTER_DATA_DETAIL.value(), hashMap, 0);
    }

    public static void e() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.TEMPERATURE_DELETE_DAY_LIST.value(), hashMap, 0);
    }

    public static void b() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.TEMPERATURE_DELETE_DATA_DETAIL.value(), hashMap, 0);
    }

    public static void e(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BLOOD_OXYGEN_SWITCHING_TYPE.value(), hashMap, 0);
    }

    public static void c(int i, int i2, int i3) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        hashMap.put("type", Integer.valueOf(i2));
        hashMap.put("level", Integer.valueOf(i3));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.ABNORMAL_BLOOD_OXYGEN_DATA_VIEW.value(), hashMap, 0);
    }

    public static void a(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("level", Integer.valueOf(i2));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BLOOD_OXYGEN_ABNORMAL_DATA.value(), hashMap, 0);
    }

    public static void d(int i) {
        int a2 = scg.a(i);
        if (a2 != 0) {
            a(1, a2);
        }
    }

    public static void b(int i) {
        int intValue = scg.e.get(Integer.valueOf(i)).intValue();
        if (intValue != 0) {
            a(1, intValue);
        }
    }

    public static int a(int i) {
        if (i == 1001) {
            return 1;
        }
        if (i == 1002) {
            return 2;
        }
        LogUtil.c(e, "getSpO2AbnormalType cardType is invalid");
        return Integer.MIN_VALUE;
    }

    public static int b(int i, int i2) {
        if (i == 1001) {
            return scg.e.get(Integer.valueOf(i2)).intValue();
        }
        if (i == 1002) {
            return scg.a(i2);
        }
        LogUtil.c(e, "getSpO2AbnormalLevel cardType is invalid");
        return Integer.MIN_VALUE;
    }

    public static void e(int i, int i2) {
        if (h(i) == -1 || j(i2) == -1) {
            LogUtil.c(e, "pageType or dataType is invalid");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(CommonUtil.PAGE_TYPE, Integer.valueOf(h(i)));
        hashMap.put("dataType", Integer.valueOf(j(i2)));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BLOOD_PRESSURE_ALL_DATA_DISPLAY_TYPE.value(), hashMap, 0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int b(SourceInfo sourceInfo) {
        char c;
        if (sourceInfo == null) {
            return 1;
        }
        if (rrb.c(sourceInfo.getPackageName(), sourceInfo.getDeviceUniqueCode())) {
            return 4;
        }
        String e2 = rrf.e(String.valueOf(sourceInfo.getDeviceType()));
        e2.hashCode();
        switch (e2.hashCode()) {
            case 47685:
                if (e2.equals("00E")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 47696:
                if (e2.equals("011")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 47870:
                if (e2.equals("06D")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 47871:
                if (e2.equals("06E")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1) {
            return 3;
        }
        return (c == 2 || c == 3) ? 2 : 5;
    }
}
