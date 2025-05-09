package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.provider.Settings;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kdo {
    private static ContentResolver c;
    private static ContentObserver e;

    public static void bNg_(ContentResolver contentResolver, ContentObserver contentObserver, String str) {
        if (contentResolver == null || contentObserver == null) {
            LogUtil.h("StorePutBackUtils", "startListen: failure, parameter is null");
            return;
        }
        LogUtil.a("StorePutBackUtils", "startListen: start");
        c = contentResolver;
        e = contentObserver;
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(str), true, contentObserver);
    }

    public static void c(Context context) {
        ContentObserver contentObserver;
        LogUtil.a("StorePutBackUtils", "stopListen: start");
        if (context == null) {
            LogUtil.h("StorePutBackUtils", "stopListen: context is null.");
            return;
        }
        ContentResolver contentResolver = c;
        if (contentResolver == null || (contentObserver = e) == null) {
            LogUtil.h("StorePutBackUtils", "stopListen: sCalendarResolver or sCalendarObserver is null.");
        } else {
            contentResolver.unregisterContentObserver(contentObserver);
        }
    }
}
