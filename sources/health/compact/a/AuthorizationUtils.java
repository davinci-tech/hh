package health.compact.a;

import android.content.Context;
import android.content.Intent;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes.dex */
public class AuthorizationUtils {
    private AuthorizationUtils() {
    }

    public static boolean a(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        boolean equals = "true".equals(KeyValDbManager.b(context).e("key_wether_to_auth"));
        if (!equals) {
            com.huawei.hwlogsmodel.LogUtil.a("AuthorizationUtils", "getAuthorizationStatus key_wether_to_auth is false");
        }
        return equals;
    }

    public static void d() {
        com.huawei.hwlogsmodel.LogUtil.a("AuthorizationUtils", "sendAuthChangedBroadcast enter");
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.AUTHORIZATION_CHANGED");
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), intent);
    }
}
