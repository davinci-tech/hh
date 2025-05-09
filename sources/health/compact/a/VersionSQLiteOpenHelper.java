package health.compact.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes.dex */
public class VersionSQLiteOpenHelper extends SQLiteOpenHelper {
    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public VersionSQLiteOpenHelper(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 2);
    }

    private String d() {
        com.huawei.hwlogsmodel.LogUtil.c("VersionSQLiteOpenHelper", "createTable, sql = ", "key text not null ,value text not null");
        com.huawei.hwlogsmodel.LogUtil.a("VersionSQLiteOpenHelper", "createTable end");
        return "create table IF NOT EXISTS module_200007_keyvaldb(key text not null ,value text not null)";
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            sQLiteDatabase.execSQL(d());
        }
    }
}
