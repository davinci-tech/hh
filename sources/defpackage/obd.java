package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class obd {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void a(String str, int i) {
        char c;
        LogUtil.a("HealthDataBiAnalyticsUtils", "dottingBiHealthDataAnalytics tag: ", str, " value: ", Integer.valueOf(i));
        str.hashCode();
        switch (str.hashCode()) {
            case -1604744017:
                if (str.equals("biContinueHeartRateModeSwitch")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -681505884:
                if (str.equals("biAutoHeartRateSwitch")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -437017607:
                if (str.equals("biBloodOxygenRemindValue")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -179713401:
                if (str.equals("biBloodOxygenSwitch")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1546791212:
                if (str.equals("biContinueHeartRateSwitch")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            e(i);
            return;
        }
        if (c == 1) {
            d(i);
            return;
        }
        if (c == 2) {
            c(i);
            return;
        }
        if (c == 3) {
            b(i);
        } else if (c == 4) {
            a(i);
        } else {
            LogUtil.h("HealthDataBiAnalyticsUtils", "dottingBiHealthDataAnalytics else");
        }
    }

    private void b(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("status", i + "");
        String value = AnalyticsValue.SETTING_2030068.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LogUtil.a("HealthDataBiAnalyticsUtils", "biBloodOxygenSwitch finish, value: ", value, " bloodOxygenMap: ", hashMap.toString());
    }

    private void c(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", i > 0 ? "1" : "0");
        hashMap.put("bloodOxygenNumber", Integer.valueOf(i));
        String value = AnalyticsValue.SETTING_2030069.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LogUtil.a("HealthDataBiAnalyticsUtils", "biBloodOxygenRemindValue finish, value: ", value, " biSwitchStatusMap: ", hashMap.toString());
    }

    private void a(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("status", i + "");
        String value = AnalyticsValue.SETTING_1090022.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LogUtil.a("HealthDataBiAnalyticsUtils", "biContinueHeartRateSwitch finish, value: ", value, " continueMap: ", hashMap.toString());
    }

    private void e(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", i + "");
        String value = AnalyticsValue.SETTING_1090036.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LogUtil.a("HealthDataBiAnalyticsUtils", "biContinueHeartRateModeSwitch finish, value: ", value, " typeMap: ", hashMap.toString());
    }

    private void d(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("status", i + "");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010038.value(), hashMap, 0);
        LogUtil.a("HealthDataBiAnalyticsUtils", "biAutoHeartRateSwitch finish, value: ", AnalyticsValue.HOME_1010038.value(), " typeMap: ", hashMap.toString());
    }
}
