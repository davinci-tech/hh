package defpackage;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.app.AuthTask;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class shs {
    public static shr dXP_(Activity activity, String str) {
        if (activity == null || TextUtils.isEmpty(str)) {
            LogUtil.a("AliSportManager", "aliAuth activity = null or authInfo empty");
            return null;
        }
        return new shr(new AuthTask(activity).authV2(str, true), true);
    }
}
