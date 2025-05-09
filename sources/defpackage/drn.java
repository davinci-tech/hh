package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes3.dex */
public class drn {
    public static boolean a(String str, Map<String, String> map) {
        String e = SharedPreferenceManager.e(Integer.toString(3041), "health_cloud_grayscale_switch", "");
        String e2 = e();
        if ("".equals(e2)) {
            return Boolean.parseBoolean(e);
        }
        if (Arrays.asList(e2.split(";")).contains(b(str, map))) {
            return Boolean.parseBoolean(e);
        }
        return false;
    }

    private static String b(String str, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return str;
        }
        String str2 = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str2 = entry.getKey() + ":" + entry.getValue();
        }
        if (str2 == null) {
            return str;
        }
        return str + "," + str2;
    }

    private static String e() {
        if (!CommonUtil.bv() && "1".equals(SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch"))) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), "app_enter_module", "app_health_cloud_gray_configs");
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
        }
        return "";
    }
}
