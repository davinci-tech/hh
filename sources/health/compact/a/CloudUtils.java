package health.compact.a;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes.dex */
public class CloudUtils {
    private CloudUtils() {
    }

    public static boolean d() {
        if (CommonUtil.cg()) {
            return false;
        }
        String w = CommonUtil.w();
        if (TextUtils.isEmpty(w)) {
            com.huawei.hwlogsmodel.LogUtil.h("CloudUtils", "isNoCloudVersion, isNoCloud = ", w);
            w = VersionDbManager.e(BaseApplication.getContext()).e("have_cloud_or_not");
        }
        if ("1".equalsIgnoreCase(w)) {
            return false;
        }
        com.huawei.hwlogsmodel.LogUtil.a("CloudUtils", "Health APP isNoCloudVersion = true");
        return true;
    }

    public static boolean a() {
        String v = EnvironmentUtils.c() ? CommonUtil.v() : "";
        com.huawei.hwlogsmodel.LogUtil.c("CloudUtils", "CommonUtil.getIsAllowedLogin() ifAllowLogin = ", v);
        if (TextUtils.isEmpty(v)) {
            v = VersionDbManager.e(BaseApplication.getContext()).e("allow_login_or_not");
            CommonUtil.ad(v);
            com.huawei.hwlogsmodel.LogUtil.c("CloudUtils", "ifAllowLogin() = ", v);
        }
        if ("1".equalsIgnoreCase(v)) {
            com.huawei.hwlogsmodel.LogUtil.c("CloudUtils", "Health APP ifAllowLogin = true");
            return true;
        }
        com.huawei.hwlogsmodel.LogUtil.c("CloudUtils", "Health APP ifAllowLogin = false");
        return false;
    }
}
