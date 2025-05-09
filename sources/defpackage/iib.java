package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.openservice.db.model.ChildServiceTable;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class iib extends DBCommon {
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

    private iib() {
    }

    /* loaded from: classes7.dex */
    static class c {
        private static final iib e = new iib();
    }

    public static iib b() {
        return c.e;
    }

    public static String d(boolean z) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS business_data(_id integer primary key not null,start_time integer not null,end_time integer not null,type  integer not null,sub_type  integer,value double not null,meta_data text,");
        if (!z) {
            sb.append("data_source text,");
        }
        sb.append("client_id integer not null,merged integer not null,sync_status integer not null,reserve text,time_zone text not null,modified_time integer not null)");
        return sb.toString();
    }

    public static String c() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX BusinessIndex ON business_data(start_time,end_time,type,client_id)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "start_time", "end_time", ChildServiceTable.COLUMN_SUB_TYPE, "type", "value", "meta_data", "data_source", "client_id", "merged", "sync_status", "reserve", "time_zone", "modified_time"};
    }

    public Cursor bza_(String str, String[] strArr, String str2, String str3, String str4) {
        return query(str, strArr, str2, str3, str4);
    }

    public static ContentValues byV_(HiHealthData hiHealthData, int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("start_time", Long.valueOf(hiHealthData.getStartTime()));
        contentValues.put("end_time", Long.valueOf(hiHealthData.getEndTime()));
        contentValues.put("type", Integer.valueOf(hiHealthData.getType()));
        contentValues.put(ChildServiceTable.COLUMN_SUB_TYPE, Integer.valueOf(hiHealthData.getSubType()));
        contentValues.put("value", Double.valueOf(hiHealthData.getValue()));
        contentValues.put("meta_data", hiHealthData.getMetaData());
        contentValues.put("client_id", Integer.valueOf(i));
        contentValues.put("merged", Integer.valueOf(i2));
        contentValues.put("sync_status", Integer.valueOf(hiHealthData.getSyncStatus()));
        contentValues.put("reserve", hiHealthData.getReserve());
        contentValues.put("time_zone", HiDateUtil.d(hiHealthData.getTimeZone()));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("data_source", hiHealthData.getDataSource());
        return contentValues;
    }

    public static ContentValues byW_(HiHealthData hiHealthData, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", Double.valueOf(hiHealthData.getValue()));
        contentValues.put("meta_data", hiHealthData.getMetaData());
        contentValues.put("sync_status", Integer.valueOf(hiHealthData.getSyncStatus()));
        contentValues.put("merged", Integer.valueOf(i));
        contentValues.put("modified_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static List<HiHealthData> byY_(Cursor cursor, String str) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("HiH_DbBusinessData", "parseBusinessListWithDeviceUuid Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"_id", "start_time", "end_time", "type", ChildServiceTable.COLUMN_SUB_TYPE, "value", "meta_data", "sync_status", "merged", "reserve", "time_zone", "client_id", "modified_time", "data_source"};
                    int[] iArr2 = new int[14];
                    int i = 0;
                    for (int i2 = 14; i < i2; i2 = 14) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                        i++;
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setDataId(cursor.getLong(iArr[0]));
                hiHealthData.setStartTime(cursor.getLong(iArr[1]));
                hiHealthData.setEndTime(cursor.getLong(iArr[2]));
                hiHealthData.setType(cursor.getInt(iArr[3]));
                hiHealthData.setSubType(cursor.getInt(iArr[4]));
                hiHealthData.setValue(cursor.getDouble(iArr[5]));
                hiHealthData.setMetaData(cursor.getString(iArr[6]));
                hiHealthData.setSyncStatus(cursor.getInt(iArr[7]));
                hiHealthData.setDeviceUuid(str);
                hiHealthData.putInt("merged", cursor.getInt(iArr[8]));
                hiHealthData.setReserve(cursor.getString(iArr[9]));
                hiHealthData.setTimeZone(cursor.getString(iArr[10]));
                hiHealthData.setClientId(cursor.getInt(iArr[11]));
                hiHealthData.setModifiedTime(cursor.getLong(iArr[12]));
                hiHealthData.setDataSource(cursor.getString(iArr[13]));
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> byZ_(Cursor cursor) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("HiH_DbBusinessData", "parseMergeBusinessListWithClientIdAndMerged() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"_id", "start_time", "end_time", "type", ChildServiceTable.COLUMN_SUB_TYPE, "value", "meta_data", "sync_status", "merged", "reserve", "time_zone", "client_id", "modified_time", "data_source"};
                    int[] iArr2 = new int[14];
                    int i = 0;
                    for (int i2 = 14; i < i2; i2 = 14) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                        i++;
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setDataId(cursor.getLong(iArr[0]));
                hiHealthData.setStartTime(cursor.getLong(iArr[1]));
                hiHealthData.setEndTime(cursor.getLong(iArr[2]));
                hiHealthData.setType(cursor.getInt(iArr[3]));
                hiHealthData.setSubType(cursor.getInt(iArr[4]));
                hiHealthData.setValue(cursor.getDouble(iArr[5]));
                hiHealthData.setMetaData(cursor.getString(iArr[6]));
                hiHealthData.setSyncStatus(cursor.getInt(iArr[7]));
                hiHealthData.putInt("merged", cursor.getInt(iArr[8]));
                hiHealthData.setReserve(cursor.getString(iArr[9]));
                hiHealthData.setTimeZone(cursor.getString(iArr[10]));
                hiHealthData.setClientId(cursor.getInt(iArr[11]));
                hiHealthData.setModifiedTime(cursor.getLong(iArr[12]));
                hiHealthData.setDataSource(cursor.getString(iArr[13]));
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static long byX_(Cursor cursor) {
        int columnIndex;
        if (cursor == null) {
            LogUtil.h("HiH_DbBusinessData", "parseBusinessDataId() Cursor query == null");
            return -1L;
        }
        try {
            if (!cursor.moveToNext() || (columnIndex = cursor.getColumnIndex("_id")) < 0) {
                return -1L;
            }
            return cursor.getLong(columnIndex);
        } finally {
            cursor.close();
        }
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "business_data";
    }
}
