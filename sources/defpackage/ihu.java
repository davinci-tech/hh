package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ihu extends DBCommon {
    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int delete(String str, String[] strArr) {
        return super.delete(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ void execSQL(String str, Object[] objArr) {
        super.execSQL(str, objArr);
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
    public /* bridge */ /* synthetic */ Cursor rawQuery(String str, String[] strArr) {
        return super.rawQuery(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int update(ContentValues contentValues, String str, String[] strArr) {
        return super.update(contentValues, str, strArr);
    }

    private ihu() {
    }

    /* loaded from: classes7.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final ihu f13382a = new ihu();
    }

    public static ihu a() {
        return a.f13382a;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"cloud_code", "main_user_id", "sync_data_type", "sync_type_version", "sync_type_time", "modify_time"};
    }

    public static String b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS sync_anchor(_id integer primary key not null,cloud_code integer no null,main_user_id integer no null,sync_data_type integer no null,sync_type_version integer no null,sync_type_time integer no null,modify_time integer no null)");
        return sb.toString();
    }

    public static String c() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX SyncTypeIndex ON sync_anchor(cloud_code,main_user_id,sync_data_type)");
        return sb.toString();
    }

    public static igq byE_(Cursor cursor) {
        igq igqVar;
        SQLiteDatabaseCorruptException e;
        igq igqVar2 = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBSyncAnchor", "parseSyncAnchorTableCursor query is null!");
            return null;
        }
        try {
            try {
                if (cursor.moveToNext()) {
                    igqVar = new igq();
                    try {
                        igqVar.b(cursor.getInt(cursor.getColumnIndex("main_user_id")));
                        igqVar.d(cursor.getLong(cursor.getColumnIndex("cloud_code")));
                        igqVar.a(cursor.getInt(cursor.getColumnIndex("sync_data_type")));
                        igqVar.c(cursor.getLong(cursor.getColumnIndex("sync_type_version")));
                        igqVar.c(cursor.getInt(cursor.getColumnIndex("modify_time")));
                        igqVar2 = igqVar;
                    } catch (SQLiteDatabaseCorruptException e2) {
                        e = e2;
                        ReleaseLogUtil.c("Debug_DBSyncAnchor", "parseSyncAnchorTableCursor() SQLiteDatabaseCorruptException", LogAnonymous.b((Throwable) e));
                        cursor.close();
                        return igqVar;
                    }
                }
                return igqVar2;
            } catch (SQLiteDatabaseCorruptException e3) {
                igqVar = null;
                e = e3;
            }
        } finally {
            cursor.close();
        }
    }

    public static List<igq> byD_(Cursor cursor) {
        ArrayList arrayList = new ArrayList(10);
        if (cursor == null) {
            LogUtil.h("Debug_DBSyncAnchor", "parseSyncAnchorTableCursor query is null!");
            return arrayList;
        }
        while (cursor.moveToNext()) {
            try {
                try {
                    igq igqVar = new igq();
                    igqVar.b(cursor.getInt(cursor.getColumnIndex("main_user_id")));
                    igqVar.d(cursor.getLong(cursor.getColumnIndex("cloud_code")));
                    igqVar.a(cursor.getInt(cursor.getColumnIndex("sync_data_type")));
                    igqVar.c(cursor.getLong(cursor.getColumnIndex("sync_type_version")));
                    igqVar.c(cursor.getInt(cursor.getColumnIndex("modify_time")));
                    arrayList.add(igqVar);
                } catch (SQLiteDatabaseCorruptException e) {
                    ReleaseLogUtil.c("Debug_DBSyncAnchor", "parseLocalVerisonCursor() SQLiteDatabaseCorruptException", LogAnonymous.b((Throwable) e));
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static igq byF_(Cursor cursor) {
        igq igqVar = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBSyncAnchor", "parseSyncTimeAnchorTableCursor query is null!");
            return null;
        }
        try {
            if (cursor.moveToNext()) {
                igqVar = new igq();
                igqVar.b(cursor.getInt(cursor.getColumnIndex("main_user_id")));
                igqVar.d(cursor.getLong(cursor.getColumnIndex("cloud_code")));
                igqVar.a(cursor.getInt(cursor.getColumnIndex("sync_data_type")));
                igqVar.c(cursor.getLong(cursor.getColumnIndex("sync_type_version")));
                igqVar.c(cursor.getInt(cursor.getColumnIndex("sync_type_time")));
            }
            return igqVar;
        } finally {
            cursor.close();
        }
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "sync_anchor";
    }
}
