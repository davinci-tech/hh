package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes4.dex */
public class ihc extends DBCommon {
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

    private ihc() {
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final ihc f13377a = new ihc();
    }

    public static ihc a() {
        return c.f13377a;
    }

    public static String b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS hihealth_dataclient(_id integer primary key not null,client_uuid text not null,user_id integer not null,device_id integer not null,app_id integer not null,sync_status integer not null,cloud_device integer,create_time integer not null,need_uploaddata integer not null)");
        return sb.toString();
    }

    public static String d() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX ClientIndex ON hihealth_dataclient(_id,user_id,device_id,app_id)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "client_uuid", "user_id", "device_id", "app_id", "cloud_device", "sync_status", "create_time", "need_uploaddata"};
    }

    public static ContentValues bxw_(ikv ikvVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("client_uuid", UUID.randomUUID().toString());
        contentValues.put("user_id", Integer.valueOf(ikvVar.g()));
        contentValues.put("device_id", Integer.valueOf(ikvVar.d()));
        contentValues.put("app_id", Integer.valueOf(ikvVar.e()));
        contentValues.put("sync_status", Integer.valueOf(ikvVar.i()));
        contentValues.put("cloud_device", Long.valueOf(ikvVar.a()));
        contentValues.put("create_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("need_uploaddata", Integer.valueOf(ikvVar.h()));
        return contentValues;
    }

    public static ContentValues bxx_(ikv ikvVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(ikvVar.i()));
        contentValues.put("cloud_device", Long.valueOf(ikvVar.a()));
        contentValues.put("need_uploaddata", Integer.valueOf(ikvVar.h()));
        return contentValues;
    }

    public static ContentValues bxy_(ikv ikvVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("need_uploaddata", Integer.valueOf(ikvVar.h()));
        return contentValues;
    }

    public static ikv bxA_(Cursor cursor) {
        ikv ikvVar = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBDataClient", "parseClientCursor query is null ");
            return null;
        }
        try {
            try {
                if (cursor.moveToNext()) {
                    ikvVar = bxz_(cursor);
                }
            } catch (SQLiteDatabaseCorruptException e) {
                ReleaseLogUtil.c("HiH_DBDataClient", "parseClientCursor SQLiteDatabaseCorruptException e: ", e.getMessage());
            }
            return ikvVar;
        } finally {
            cursor.close();
        }
    }

    public static List<ikv> bxC_(Cursor cursor) {
        if (cursor == null) {
            LogUtil.h("Debug_DBDataClient", "getHiHealthClientList query is null ");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                try {
                    arrayList.add(bxz_(cursor));
                } catch (SQLiteDatabaseCorruptException e) {
                    ReleaseLogUtil.c("HiH_DBDataClient", "parseClientsListCursor SQLiteDatabaseCorruptException e: ", e.getMessage());
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private static ikv bxz_(Cursor cursor) {
        ikv ikvVar = new ikv();
        ikvVar.e(cursor.getInt(cursor.getColumnIndex("_id")));
        ikvVar.f(cursor.getInt(cursor.getColumnIndex("user_id")));
        ikvVar.a(cursor.getInt(cursor.getColumnIndex("app_id")));
        ikvVar.d(cursor.getInt(cursor.getColumnIndex("device_id")));
        ikvVar.i(cursor.getInt(cursor.getColumnIndex("sync_status")));
        ikvVar.c(cursor.getLong(cursor.getColumnIndex("cloud_device")));
        ikvVar.a(cursor.getLong(cursor.getColumnIndex("create_time")));
        return ikvVar;
    }

    public static List<Integer> bxB_(Cursor cursor) {
        ArrayList arrayList = new ArrayList(10);
        if (cursor == null) {
            LogUtil.h("Debug_DBDataClient", "getHiHealthClientList query is null ");
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                try {
                    arrayList2.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))));
                } catch (SQLiteDatabaseCorruptException e) {
                    ReleaseLogUtil.c("HiH_DBDataClient", "parseClientIDListCursor SQLiteDatabaseCorruptException e: ", e.getMessage());
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList2;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "hihealth_dataclient";
    }
}
