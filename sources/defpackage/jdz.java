package defpackage;

import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtils;

/* loaded from: classes.dex */
public class jdz extends CommonUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final String f13757a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final String g;
    public static final String j;

    static {
        String str = "content://" + BaseApplication.getAppPackage() + ".HwNotificationContentProvider/";
        e = str;
        g = str + "NotificationFlags";
        c = str + "NotificationCollaboration";
        j = str + "NotificationList";
        b = str + "ConnectDeviceInfo";
        d = str + "MidwareAuthority";
        f13757a = "content://" + BaseApplication.getAppPackage() + ".HwNotificationContentProvider/ClockState";
    }

    public static String a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(str);
        String buildQuery = sQLiteQueryBuilder.buildQuery(strArr, str2, null, null, str3, null);
        if (strArr2 != null && strArr2.length >= 1) {
            for (int i = 0; buildQuery.contains("?") && i < strArr2.length; i++) {
                buildQuery = buildQuery.replaceFirst("\\?", strArr2[i]);
            }
        }
        return buildQuery;
    }

    public static Integer bGi_(String str, Cursor cursor, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1) {
            LogUtil.b(str2, "ERROR: getColumnIndex return -1!");
            return null;
        }
        return Integer.valueOf(columnIndex);
    }
}
