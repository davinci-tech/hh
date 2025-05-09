package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import health.compact.a.HiDateUtil;

/* loaded from: classes4.dex */
public class ihr extends DBSessionCommon {
    private ihr() {
    }

    /* loaded from: classes7.dex */
    static class a {
        private static final ihr b = new ihr();
    }

    public static String b() {
        return getSessionCreateTableSQL("sample_session_health");
    }

    public static String e() {
        return getSessionCommonIndexSQL("HealthSessionIndex", "sample_session_health");
    }

    public static ihr c() {
        return a.b;
    }

    public static ContentValues byu_(int i, int i2) {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("sync_status", Integer.valueOf(i2));
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static ContentValues bys_(HiHealthData hiHealthData, int i, int i2) {
        ContentValues contentValues = new ContentValues(9);
        contentValues.put("start_time", Long.valueOf(hiHealthData.getStartTime()));
        contentValues.put("end_time", Long.valueOf(hiHealthData.getEndTime()));
        contentValues.put("type_id", Integer.valueOf(hiHealthData.getType()));
        contentValues.put("metadata", hiHealthData.getMetaData());
        contentValues.put("client_id", Integer.valueOf(i));
        contentValues.put("merged", Integer.valueOf(i2));
        contentValues.put("sync_status", Integer.valueOf(hiHealthData.getSyncStatus()));
        contentValues.put(DBSessionCommon.COLUMN_TIME_ZONE, HiDateUtil.d(hiHealthData.getTimeZone()));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static ContentValues byt_(HiHealthData hiHealthData, int i) {
        ContentValues contentValues = new ContentValues(5);
        contentValues.put("type_id", Integer.valueOf(hiHealthData.getType()));
        contentValues.put("metadata", hiHealthData.getMetaData());
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("sync_status", Integer.valueOf(hiHealthData.getSyncStatus()));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "sample_session_health";
    }
}
