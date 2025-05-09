package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes3.dex */
public class cau {
    private static String e(String str) {
        return "SportTabDataCache_" + knl.b(SharedPreferenceUtil.getInstance(BaseApplication.e()).getUserID()) + "_" + str;
    }

    public static void d(String str, String str2) {
        if (LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            LogUtil.h("Track_SportTabDataCacheUtils", "setData() is not login");
            return;
        }
        String e = e(str);
        LogUtil.a("Track_SportTabDataCacheUtils", "setData() key: ", e, ", data: ", str2);
        SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(20002), e, str2, (StorageParams) null);
    }

    public static String a(String str) {
        if (LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            LogUtil.h("Track_SportTabDataCacheUtils", "getData() is not login");
            return "";
        }
        String e = e(str);
        LogUtil.a("Track_SportTabDataCacheUtils", "getData() key: ", e);
        return SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(20002), e);
    }
}
