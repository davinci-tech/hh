package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ihe extends DBCommon {
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

    private ihe() {
    }

    /* loaded from: classes7.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final ihe f13378a = new ihe();
    }

    public static ihe b() {
        return a.f13378a;
    }

    public static String a() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" create table  IF NOT EXISTS real_time_health(_id integer primary key  not null,start_time integer not null,end_time integer not null,type_id integer not null,value double not null,unit_id integer,client_id integer not null)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "start_time", "end_time", "type_id", "value", DBPointCommon.COLUMN_UNIT_ID, "client_id"};
    }

    public static List<HiHealthData> bxM_(Cursor cursor, String str) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBRealtimeData", "parseRealTimeCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"start_time", "end_time", "type_id", "value", DBPointCommon.COLUMN_UNIT_ID};
                    int[] iArr2 = new int[5];
                    for (int i = 0; i < 5; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setStartTime(cursor.getLong(iArr[0]));
                hiHealthData.setEndTime(cursor.getLong(iArr[1]));
                hiHealthData.setType(cursor.getInt(iArr[2]));
                hiHealthData.setValue(cursor.getDouble(iArr[3]));
                hiHealthData.setPointUnit(cursor.getInt(iArr[4]));
                hiHealthData.setDeviceUuid(str);
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "real_time_health";
    }
}
