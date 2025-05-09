package defpackage;

import android.content.ContentValues;
import com.huawei.hihealthservice.db.table.DBPointCommon;

/* loaded from: classes4.dex */
public class ihl extends DBPointCommon {
    private ihl() {
    }

    public static ihl a() {
        return c.d;
    }

    /* loaded from: classes7.dex */
    static class c {
        private static final ihl d = new ihl();
    }

    public static String c() {
        return getPointCreateTableSQL("sample_point_health_stress");
    }

    public static String b() {
        return getStartTimeIndexSQL("HealthStressPointIndex", "sample_point_health_stress");
    }

    public static ContentValues bxZ_(int i) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "sample_point_health_stress";
    }
}
