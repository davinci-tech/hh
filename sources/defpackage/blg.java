package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LogUtil;

/* loaded from: classes8.dex */
public class blg {
    public static final String d = "content://" + BaseApplication.d() + ".data.access.provider/";

    public static void d(Context context, String str, String str2, String str3) {
        if (context == null) {
            LogUtil.a("ContentResolverUtils", "deleteStorageData context is null");
            return;
        }
        try {
            LogUtil.d("ContentResolverUtils", "deleteStorageData enter");
            context.getContentResolver().delete(Uri.parse(d + "module_" + str + "_" + str2), str3, null);
        } catch (Exception unused) {
            LogUtil.e("ContentResolverUtils", "deleteStorageData Exception");
        }
    }

    public static Cursor sV_(Context context, String str, String str2, String str3) {
        if (context == null) {
            LogUtil.a("ContentResolverUtils", "queryStorageData context is null");
            return null;
        }
        try {
            return context.getContentResolver().query(Uri.parse(d + "module_" + str + "_" + str2), null, str3, null, null);
        } catch (Exception unused) {
            LogUtil.e("ContentResolverUtils", "queryStorageData Exception");
            return null;
        }
    }
}
