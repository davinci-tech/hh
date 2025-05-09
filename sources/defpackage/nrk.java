package defpackage;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.provider.CalendarContract;
import health.compact.a.util.LogUtil;

/* loaded from: classes.dex */
public class nrk {
    public static boolean d(Context context) {
        return context != null && context.checkSelfPermission("android.permission.WRITE_CALENDAR") == 0 && context.checkSelfPermission("android.permission.READ_CALENDAR") == 0;
    }

    public static boolean cJX_(ContentResolver contentResolver) {
        if (contentResolver == null) {
            return false;
        }
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient("com.huawei.calendar");
        Object[] objArr = new Object[2];
        objArr[0] = "isSupportHWCalendarService:";
        objArr[1] = Boolean.valueOf(acquireUnstableContentProviderClient != null);
        LogUtil.d("AsyncCalendarUtils", objArr);
        return acquireUnstableContentProviderClient != null;
    }

    public static int b(Context context) {
        int c = c(context);
        if (c < 0) {
            return 1;
        }
        LogUtil.d("AsyncCalendarUtils", "current id of CalendarAccount：" + c);
        return c;
    }

    private static int c(Context context) {
        try {
            Cursor query = context.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, null, null, null, null);
            try {
                if (query == null) {
                    LogUtil.c("AsyncCalendarUtils", "userCursor null in checking CalendarAccount");
                    if (query != null) {
                        query.close();
                    }
                    return 1;
                }
                if (query.getCount() <= 0) {
                    if (query != null) {
                        query.close();
                    }
                    return 1;
                }
                query.moveToFirst();
                int i = query.getInt(query.getColumnIndex("_id"));
                if (query != null) {
                    query.close();
                }
                return i;
            } finally {
            }
        } catch (SQLException unused) {
            LogUtil.e("AsyncCalendarUtils", "checkCalendarAccount error");
            return 1;
        }
    }
}
