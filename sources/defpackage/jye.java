package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.provider.CalendarContract;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jye {

    /* renamed from: a, reason: collision with root package name */
    private static String f14190a = "content://com.coloros.calendar/calendars";
    private static ContentObserver b = null;
    private static ContentResolver c = null;
    private static String e = "content://com.coloros.calendar/events";

    public static void bKQ_(ContentResolver contentResolver, ContentObserver contentObserver) {
        if (contentResolver == null || contentObserver == null) {
            LogUtil.h("CalendarListenUtils", "startListen: failure, parameter is null");
        } else if (jyo.d()) {
            bKP_(contentResolver, contentObserver);
        } else {
            LogUtil.h("CalendarListenUtils", "startListen: no contact permission..");
        }
    }

    public static void e(Context context) {
        ContentObserver contentObserver;
        LogUtil.a("CalendarListenUtils", "stopListen: start");
        if (context == null) {
            LogUtil.h("CalendarListenUtils", "stopListen: context is null.");
            return;
        }
        ContentResolver contentResolver = c;
        if (contentResolver == null || (contentObserver = b) == null) {
            LogUtil.h("CalendarListenUtils", "stopListen: sCalendarResolver or sCalendarObserver is null.");
        } else {
            contentResolver.unregisterContentObserver(contentObserver);
        }
    }

    private static void bKP_(ContentResolver contentResolver, ContentObserver contentObserver) {
        LogUtil.a("CalendarListenUtils", "listenCalendarEarlierApi24: start");
        c = contentResolver;
        b = contentObserver;
        if (jyo.c(BaseApplication.getContext())) {
            c.registerContentObserver(Uri.parse(e), true, b);
            c.registerContentObserver(Uri.parse(f14190a), true, b);
        }
        c.registerContentObserver(CalendarContract.Events.CONTENT_URI, true, b);
        c.registerContentObserver(CalendarContract.Calendars.CONTENT_URI, true, b);
    }
}
