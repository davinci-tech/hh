package health.compact.a;

/* loaded from: classes.dex */
public class ThirdLoginFreshAccessToken {
    private ThirdLoginFreshAccessToken() {
    }

    public static void d() {
        if ("0".equalsIgnoreCase(CommonUtil.v()) || CommonUtil.bh()) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("ThirdLoginFreshAccessToken", "enter reflectionFreshAccessToken");
        Class<?> b = ReflectionUtils.b("com.huawei.login.huaweilogin.ThirdPartyLoginManager");
        if (b == null) {
            return;
        }
        Object c = ReflectionUtils.c(b, "getInstance");
        if (c == null) {
            com.huawei.hwlogsmodel.LogUtil.b("ThirdLoginFreshAccessToken", "reflectionFreshAccessToken instance is null!");
        } else {
            ReflectionUtils.a(c, "immediatelyFreshAt");
        }
    }

    public static String c() {
        com.huawei.hwlogsmodel.LogUtil.c("ThirdLoginFreshAccessToken", "enter reflectionLogoutByLite");
        Class<?> b = ReflectionUtils.b("com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil");
        if (b == null) {
            com.huawei.hwlogsmodel.LogUtil.b("ThirdLoginFreshAccessToken", "reflectionLogoutByLite myClass is null!");
            return "";
        }
        Object c = ReflectionUtils.c(b, "getLogoutFlag");
        return c instanceof String ? (String) c : "";
    }

    public static void a() {
        com.huawei.hwlogsmodel.LogUtil.c("ThirdLoginFreshAccessToken", "enter reflectionLoginStatusByHms");
        Class<?> b = ReflectionUtils.b("com.huawei.login.ui.login.LoginInit");
        if (b == null) {
            com.huawei.hwlogsmodel.LogUtil.b("ThirdLoginFreshAccessToken", "reflectionLoginStatusByHms myClass is null!");
        } else {
            ReflectionUtils.c(b, "checkHmsHasLogin");
        }
    }
}
