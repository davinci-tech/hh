package defpackage;

import android.content.ContentValues;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import health.compact.a.HiDateUtil;

/* loaded from: classes4.dex */
public class ihk extends DBPointCommon {
    private ihk() {
    }

    public static ihk d() {
        return c.f13379a;
    }

    /* loaded from: classes7.dex */
    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final ihk f13379a = new ihk();
    }

    public static String a() {
        return getPointCreateTableSQL("sample_point");
    }

    public static String c() {
        return getPointCommonIndexSQL("PointIndex", "sample_point");
    }

    public static String g() {
        return getStartTimeIndexSQL("PointStartTimeIndex", "sample_point");
    }

    public static String e() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX IF NOT EXISTS PointSyncStatusIndex ON sample_point(sync_status)");
        return sb.toString();
    }

    public static ContentValues bxS_(HiHealthData hiHealthData, int i, int i2) {
        ContentValues contentValues = new ContentValues(11);
        contentValues.put("start_time", Long.valueOf(hiHealthData.getStartTime()));
        contentValues.put("end_time", Long.valueOf(hiHealthData.getEndTime()));
        contentValues.put("type_id", Integer.valueOf(hiHealthData.getType()));
        contentValues.put("value", Double.valueOf(hiHealthData.getValue()));
        contentValues.put("metadata", hiHealthData.getMetaData());
        contentValues.put(DBPointCommon.COLUMN_UNIT_ID, Integer.valueOf(hiHealthData.getPointUnit()));
        contentValues.put("client_id", Integer.valueOf(i));
        contentValues.put("merged", Integer.valueOf(i2));
        contentValues.put("sync_status", Integer.valueOf(hiHealthData.getSyncStatus()));
        contentValues.put("timeZone", HiDateUtil.d(hiHealthData.getTimeZone()));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static ContentValues bxU_(HiHealthData hiHealthData, int i) {
        ContentValues contentValues = new ContentValues(6);
        contentValues.put("value", Double.valueOf(hiHealthData.getValue()));
        contentValues.put("metadata", hiHealthData.getMetaData());
        contentValues.put(DBPointCommon.COLUMN_UNIT_ID, Integer.valueOf(hiHealthData.getPointUnit()));
        contentValues.put("sync_status", Integer.valueOf(hiHealthData.getSyncStatus()));
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static ContentValues bxV_(int i, int i2) {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("sync_status", Integer.valueOf(i2));
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static ContentValues bxT_(int i) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "sample_point";
    }

    public static String b() {
        return " DROP INDEX IF EXISTS PointIndex";
    }
}
