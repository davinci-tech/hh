package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.basichealthmodel.database.HealthLifeSqLiteOpenHelper;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes3.dex */
public class auw {
    public static Cursor jV_(String str, String[] strArr) throws SQLiteException, IllegalStateException {
        SQLiteDatabase a2 = HealthLifeSqLiteOpenHelper.d().a();
        if (TextUtils.isEmpty(str) || strArr == null || a2 == null) {
            LogUtil.h("HealthLife_DatabaseHelper", "rawQuery sql ", str, " selectionArgs ", strArr, " database ", a2);
            return null;
        }
        try {
            return a2.rawQuery(str, strArr);
        } catch (SQLiteException | IllegalStateException | OutOfMemoryError e) {
            LogUtil.h("HealthLife_DatabaseHelper", "rawQuery exception ", LogAnonymous.b(e));
            return null;
        }
    }

    public static int e(String str, String str2, String[] strArr) {
        SQLiteDatabase a2 = HealthLifeSqLiteOpenHelper.d().a();
        if (a2 == null) {
            LogUtil.h("HealthLife_DatabaseHelper", "delete database is null");
            return -1;
        }
        try {
            return a2.delete(str, str2, strArr);
        } catch (SQLiteException | IllegalStateException | OutOfMemoryError e) {
            LogUtil.b("HealthLife_DatabaseHelper", "delete exception ", LogAnonymous.b(e));
            return -1;
        }
    }

    public static int jW_(String str, ContentValues contentValues, String str2, String[] strArr) {
        SQLiteDatabase a2 = HealthLifeSqLiteOpenHelper.d().a();
        if (a2 == null) {
            LogUtil.h("HealthLife_DatabaseHelper", "update database is null");
            return -1;
        }
        try {
            return a2.update(str, contentValues, str2, strArr);
        } catch (SQLiteException | IllegalStateException | OutOfMemoryError e) {
            LogUtil.b("HealthLife_DatabaseHelper", "update exception ", LogAnonymous.b(e));
            return -1;
        }
    }

    public static long jU_(String str, ContentValues contentValues) {
        SQLiteDatabase a2 = HealthLifeSqLiteOpenHelper.d().a();
        if (a2 == null) {
            LogUtil.h("HealthLife_DatabaseHelper", "insert database is null");
            return -1L;
        }
        try {
            return a2.insert(str, (String) null, contentValues);
        } catch (SQLiteException | IllegalStateException | OutOfMemoryError e) {
            LogUtil.h("HealthLife_DatabaseHelper", "insert exception ", LogAnonymous.b(e));
            return -1L;
        }
    }

    public static boolean c(SQLiteDatabase sQLiteDatabase, String str) {
        if (sQLiteDatabase == null || TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_DatabaseHelper", "isTableExist database ", sQLiteDatabase, " tableName ", str);
            return false;
        }
        List<String> b = b(sQLiteDatabase);
        if (koq.b(b)) {
            return false;
        }
        for (String str2 : b) {
            if (!TextUtils.isEmpty(str2) && str2.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> b(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        ArrayList arrayList = new ArrayList();
        try {
            rawQuery = sQLiteDatabase.rawQuery("select name from sqlite_master where type='table' order by name", (String[]) null);
            try {
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("HealthLife_DatabaseHelper", "getAllTableNameList exception ", LogAnonymous.b((Throwable) e));
        }
        if (rawQuery == null) {
            LogUtil.h("HealthLife_DatabaseHelper", "getAllTableNameList cursor is null database ", sQLiteDatabase);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        }
        while (rawQuery.moveToNext()) {
            String string = rawQuery.getString(0);
            if (!TextUtils.isEmpty(string)) {
                arrayList.add(string);
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return arrayList;
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        if (sQLiteDatabase == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("HealthLife_DatabaseHelper", "isFieldExist database ", sQLiteDatabase, " tableName ", str, " fieldName ", str2);
            return false;
        }
        String a2 = a(sQLiteDatabase, str);
        return !TextUtils.isEmpty(a2) && a2.contains(str2);
    }

    private static String a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery(String.format(Locale.ENGLISH, "select sql from sqlite_master where type = 'table' and name = '%s'", str), (String[]) null);
            try {
            } finally {
            }
        } catch (Exception e) {
            LogUtil.b("HealthLife_DatabaseHelper", "getAllFieldName exception ", LogAnonymous.b((Throwable) e));
        }
        if (rawQuery == null) {
            LogUtil.h("HealthLife_DatabaseHelper", "getAllFieldName cursor is null database ", sQLiteDatabase, " tableName ", str);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return "";
        }
        if (!rawQuery.moveToFirst()) {
            if (rawQuery != null) {
                rawQuery.close();
            }
            return "";
        }
        String string = rawQuery.getString(rawQuery.getColumnIndex("sql"));
        if (rawQuery != null) {
            rawQuery.close();
        }
        return string;
    }
}
