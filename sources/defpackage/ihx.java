package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ihx extends DBCommon {
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

    private ihx() {
    }

    /* loaded from: classes7.dex */
    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final ihx f13384a = new ihx();
    }

    public static ihx e() {
        return e.f13384a;
    }

    public static String c() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS user_preference(_id integer primary key not null,key text not null,value text not null,user_id integer not null,sync_status integer not null,create_time integer not null,modified_time integer not null)");
        return sb.toString();
    }

    public static String a() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX UserPreferenceIndex ON user_preference(user_id,key)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", MedalConstants.EVENT_KEY, "value", "user_id", "sync_status", "create_time", "modified_time"};
    }

    public static ContentValues byR_(HiUserPreference hiUserPreference) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedalConstants.EVENT_KEY, hiUserPreference.getKey());
        contentValues.put("value", hiUserPreference.getValue());
        contentValues.put("user_id", Integer.valueOf(hiUserPreference.getUserId()));
        contentValues.put("sync_status", Integer.valueOf(hiUserPreference.getSyncStatus()));
        contentValues.put("create_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("modified_time", Long.valueOf(hiUserPreference.getModifiedTime()));
        return contentValues;
    }

    public static ContentValues byQ_(HiUserPreference hiUserPreference) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", hiUserPreference.getValue());
        contentValues.put("sync_status", Integer.valueOf(hiUserPreference.getSyncStatus()));
        contentValues.put("modified_time", Long.valueOf(hiUserPreference.getModifiedTime()));
        return contentValues;
    }

    public static List<HiUserPreference> byU_(Cursor cursor) {
        if (cursor == null) {
            LogUtil.h("Debug_DBUserPreference", "parseUserPreferencesCursor cursor is null!");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                try {
                    arrayList.add(byS_(cursor));
                } catch (SQLiteDatabaseCorruptException e2) {
                    ReleaseLogUtil.c("HiH_DBUserPreference", "parseUserPreferencesCursor SQLiteDatabaseCorruptException e: ", e2.getMessage());
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static HiUserPreference byT_(Cursor cursor) {
        HiUserPreference hiUserPreference = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBUserPreference", "parseUserPreferenceCursor cursor is null!");
            return null;
        }
        try {
            try {
                if (cursor.moveToNext()) {
                    hiUserPreference = byS_(cursor);
                }
            } catch (SQLiteDatabaseCorruptException e2) {
                ReleaseLogUtil.c("HiH_DBUserPreference", "parseUserPreferenceCursor SQLiteDatabaseCorruptException e: ", e2.getMessage());
            }
            return hiUserPreference;
        } finally {
            cursor.close();
        }
    }

    private static HiUserPreference byS_(Cursor cursor) {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        hiUserPreference.setKey(cursor.getString(cursor.getColumnIndex(MedalConstants.EVENT_KEY)));
        hiUserPreference.setValue(cursor.getString(cursor.getColumnIndex("value")));
        hiUserPreference.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
        hiUserPreference.setSyncStatus(cursor.getInt(cursor.getColumnIndex("sync_status")));
        hiUserPreference.setCreateTime(cursor.getLong(cursor.getColumnIndex("create_time")));
        hiUserPreference.setModifiedTime(cursor.getLong(cursor.getColumnIndex("modified_time")));
        return hiUserPreference;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "user_preference";
    }
}
