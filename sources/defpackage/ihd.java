package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.HiThirdIdentity;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;

/* loaded from: classes4.dex */
public class ihd extends DBCommon {
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

    private ihd() {
    }

    /* loaded from: classes7.dex */
    static class c {
        private static final ihd d = new ihd();
    }

    public static ihd c() {
        return c.d;
    }

    public static String d() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS hihealth_identity(_id integer primary key not null,package_name text not null,fingerprint text not null,level integer not null,identity integer not null,create_time text ,modified_time text)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "package_name", HiAnalyticsConstant.HaKey.BI_KEY_FINGERPRINT, "level", "identity", "create_time", "modified_time"};
    }

    public static ContentValues bxJ_(HiThirdIdentity hiThirdIdentity, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("package_name", hiThirdIdentity.getPackageName());
        contentValues.put(HiAnalyticsConstant.HaKey.BI_KEY_FINGERPRINT, hiThirdIdentity.getFingerprint());
        contentValues.put("level", Integer.valueOf(hiThirdIdentity.getLevel()));
        contentValues.put("identity", Integer.valueOf(hiThirdIdentity.getIdentity()));
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            contentValues.put("create_time", Long.valueOf(currentTimeMillis));
        }
        contentValues.put("modified_time", Long.valueOf(currentTimeMillis));
        return contentValues;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "hihealth_identity";
    }
}
