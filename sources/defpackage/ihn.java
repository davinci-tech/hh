package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import health.compact.a.HiDateUtil;

/* loaded from: classes4.dex */
public class ihn extends DBSessionCommon {
    private ihn() {
    }

    /* loaded from: classes7.dex */
    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final ihn f13381a = new ihn();
    }

    public static ihn a() {
        return e.f13381a;
    }

    public static String d() {
        return getSessionCreateTableSQL("sample_session");
    }

    public static String c() {
        return getSessionCommonIndexSQL("SessionIndex", "sample_session");
    }

    public static ContentValues byr_(int i, int i2) {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("sync_status", Integer.valueOf(i2));
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static ContentValues byp_(HiHealthData hiHealthData, int i, int i2) {
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

    public static ContentValues byq_(HiHealthData hiHealthData, int i) {
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
        return "sample_session";
    }
}
