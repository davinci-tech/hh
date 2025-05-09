package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gxz {
    public static int b(String str, Context context) {
        return a(str, context, "string");
    }

    public static int d(String str, Context context) {
        return a(str, context, "drawable");
    }

    public static int a(String str, Context context, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("HwResourceUtil", "getResourceId context == null || TextUtils.isEmpty(stringName) || TextUtils.isEmpty(type)");
            return 0;
        }
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }
}
