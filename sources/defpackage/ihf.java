package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hihealthservice.db.table.DBPointCommon;

/* loaded from: classes4.dex */
public class ihf extends DBCommon {
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

    public static String a() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS hihealth_unit(_id integer primary key not null,user_id integer not null,type_id integer,unit_id integer not null)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "user_id", "type_id", DBPointCommon.COLUMN_UNIT_ID};
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "hihealth_unit";
    }
}
