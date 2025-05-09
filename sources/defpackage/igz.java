package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hihealthservice.db.table.DBCommon;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes4.dex */
public class igz extends DBCommon {
    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int delete(String str, String[] strArr) {
        return super.delete(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ long insert(ContentValues contentValues) {
        return super.insert(contentValues);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor query(String str, String[] strArr, String str2, String str3, String str4) {
        return super.query(str, strArr, str2, str3, str4);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor queryEX(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        return super.queryEX(strArr, str, strArr2, str2, str3, str4);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int update(ContentValues contentValues, String str, String[] strArr) {
        return super.update(contentValues, str, strArr);
    }

    private igz() {
    }

    /* loaded from: classes7.dex */
    static class c {
        private static final igz e = new igz();
    }

    public static igz d() {
        return c.e;
    }

    public static String c() {
        return iiu.a("config_stat_day");
    }

    public static String a() {
        return iiu.d("config_stat_day");
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return iiu.e();
    }

    public Cursor bxu_(String str, String str2, String[] strArr, String str3, String str4, String str5) {
        SQLiteDatabase writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase.query(str, getColumns(), str2, strArr, str3, str4, str5);
        }
        return null;
    }

    public int bxv_(String str, ContentValues contentValues, String str2, String[] strArr) {
        SQLiteDatabase writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase.update(str, contentValues, str2, strArr);
        }
        return -1000;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public Cursor rawQuery(String str, String[] strArr) {
        SQLiteDatabase writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase.rawQuery(str, strArr);
        }
        return null;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public void execSQL(String str, Object[] objArr) {
        try {
            SQLiteDatabase writableDatabase = HiHealthDBHelper.a().getWritableDatabase();
            if (writableDatabase != null) {
                writableDatabase.execSQL(str, objArr);
            }
        } catch (SQLiteException e) {
            ReleaseLogUtil.c("HiH_DBConfigDataStat", "SQLiteException!", LogAnonymous.b((Throwable) e));
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_DBConfigDataStat", "Exception!", LogAnonymous.b((Throwable) e2));
        }
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "config_stat_day";
    }
}
