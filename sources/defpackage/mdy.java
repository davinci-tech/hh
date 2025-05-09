package defpackage;

import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;

/* loaded from: classes6.dex */
public class mdy {
    public static int cgl_(Cursor cursor, String str) {
        if (cursor == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        int columnIndex = cursor.getColumnIndex(str);
        if (-1 != columnIndex) {
            return cursor.getInt(columnIndex);
        }
        LogUtil.a("PLGACHIEVE_AnnualDBHelper", "getStringColumn wrong columnName = ", str);
        return 0;
    }

    public static boolean b(String str, String str2, String str3, String str4) {
        if ("0".equals(str4) && nsn.e(str3) < 2020) {
            return true;
        }
        if (mht.d(System.currentTimeMillis()).equals(str3)) {
            return false;
        }
        return String.valueOf(6002).equals(str2) || String.valueOf(5001).equals(str2) || EnumAnnualType.REPORT_ACTIVITY.value().equals(str);
    }
}
