package com.huawei.hihealthservice.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class DBSessionCommon extends DBCommon {
    public static final String COLUMN_CLIENT_ID = "client_id";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MERGED = "merged";
    public static final String COLUMN_META_DATA = "metadata";
    public static final String COLUMN_MODIFIED_TIME = "modified_time";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_SYNC_STATUS = "sync_status";
    public static final String COLUMN_TIME_ZONE = "timezone";
    public static final String COLUMN_TYPE_ID = "type_id";
    private static final String LOG_TAG = "Debug_DBSessionCommon";

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

    public static String getSessionCreateTableSQL(String str) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS " + str + Constants.LEFT_BRACKET_ONLY);
        sb.append("_id integer primary key not null,start_time integer not null,end_time integer not null,type_id integer not null,metadata text,client_id integer not null,merged integer not null,sync_status integer not null,timezone text not null,modified_time integer not null)");
        return sb.toString();
    }

    public static String getSessionCommonIndexSQL(String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX  IF NOT EXISTS ");
        sb.append(str + " ON " + str2 + Constants.LEFT_BRACKET_ONLY);
        sb.append("start_time,end_time,client_id)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "start_time", "end_time", "type_id", "metadata", "client_id", "merged", "sync_status", COLUMN_TIME_ZONE, "modified_time"};
    }

    public static List<HiHealthData> parseSessionCursor(Cursor cursor) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseSessionCursor cursor == null");
            return null;
        }
        int i = 10;
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = new String[i];
                    strArr[0] = "_id";
                    strArr[1] = "start_time";
                    strArr[2] = "end_time";
                    strArr[3] = "type_id";
                    strArr[4] = "metadata";
                    strArr[5] = "merged";
                    strArr[6] = "sync_status";
                    strArr[7] = COLUMN_TIME_ZONE;
                    strArr[8] = "client_id";
                    strArr[9] = "modified_time";
                    int[] iArr2 = new int[i];
                    int i2 = 0;
                    while (i2 < i) {
                        iArr2[i2] = cursor.getColumnIndex(strArr[i2]);
                        i2++;
                        i = 10;
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setDataId(cursor.getLong(iArr[0]));
                hiHealthData.setStartTime(cursor.getLong(iArr[1]));
                hiHealthData.setEndTime(cursor.getLong(iArr[2]));
                hiHealthData.setType(cursor.getInt(iArr[3]));
                hiHealthData.setMetaData(cursor.getString(iArr[4]));
                hiHealthData.putInt("merged", cursor.getInt(iArr[5]));
                hiHealthData.setSyncStatus(cursor.getInt(iArr[6]));
                hiHealthData.setTimeZone(cursor.getString(iArr[7]));
                hiHealthData.setClientId(cursor.getInt(iArr[8]));
                hiHealthData.putLong("modified_time", cursor.getLong(iArr[9]));
                arrayList.add(hiHealthData);
                i = 10;
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseHealthSessionCursor(Cursor cursor) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, " parseHealthSessionCursor() cursor is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(16);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"_id", "end_time", "start_time", "type_id", "metadata", "sync_status", "merged", "modified_time", "client_id", COLUMN_TIME_ZONE, "modified_time"};
                    int[] iArr2 = new int[11];
                    int i = 0;
                    for (int i2 = 11; i < i2; i2 = 11) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                        i++;
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setDataId(cursor.getLong(iArr[0]));
                hiHealthData.setEndTime(cursor.getLong(iArr[1]));
                hiHealthData.setStartTime(cursor.getLong(iArr[2]));
                hiHealthData.setType(cursor.getInt(iArr[3]));
                hiHealthData.setMetaData(cursor.getString(iArr[4]));
                hiHealthData.setSyncStatus(cursor.getInt(iArr[5]));
                hiHealthData.putInt("merged", cursor.getInt(iArr[6]));
                hiHealthData.putLong("modified_time", cursor.getLong(iArr[7]));
                hiHealthData.setClientId(cursor.getInt(iArr[8]));
                hiHealthData.setTimeZone(cursor.getString(iArr[9]));
                hiHealthData.putLong("modified_time", cursor.getLong(iArr[10]));
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseAggregateRawSessionCursor(Cursor cursor, String str) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, " parseAggregateRawSessionCursor() cursor == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"end_time", "start_time", "type_id"};
                    int[] iArr2 = new int[4];
                    for (int i = 0; i < 3; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                    }
                    iArr2[3] = cursor.getColumnIndex(str);
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setEndTime(cursor.getLong(iArr[0]));
                hiHealthData.setStartTime(cursor.getLong(iArr[1]));
                hiHealthData.setType(cursor.getInt(iArr[2]));
                hiHealthData.putDouble(str, cursor.getDouble(iArr[3]));
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseAggregateRawHealthSessionCursor(Cursor cursor, String str) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseAggregateRawHealthSessionCursor() Cursor cursor == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"start_time", "end_time", "type_id"};
                    int[] iArr2 = new int[4];
                    for (int i = 0; i < 3; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                    }
                    iArr2[3] = cursor.getColumnIndex(str);
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setStartTime(cursor.getLong(iArr[0]));
                hiHealthData.setEndTime(cursor.getLong(iArr[1]));
                hiHealthData.setType(cursor.getInt(iArr[2]));
                hiHealthData.putDouble(str, cursor.getDouble(iArr[3]));
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseAggregateHealthSessionCursor(Cursor cursor, String[] strArr) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseAggregateHealthSessionCursor() Cursor cursor is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                int i = 0;
                if (iArr == null) {
                    String[] strArr2 = {"type_id", "start_time", "end_time", "client_id", "modified_time"};
                    int[] iArr2 = new int[strArr.length + 5];
                    for (int i2 = 0; i2 < 5; i2++) {
                        iArr2[i2] = cursor.getColumnIndex(strArr2[i2]);
                    }
                    for (int i3 = 0; i3 < strArr.length; i3++) {
                        iArr2[i3 + 5] = cursor.getColumnIndex(strArr[i3]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setType(cursor.getInt(iArr[0]));
                hiHealthData.setStartTime(cursor.getLong(iArr[1]));
                hiHealthData.setEndTime(cursor.getLong(iArr[2]));
                int i4 = iArr[3];
                if (i4 != -1) {
                    hiHealthData.setClientId(cursor.getInt(i4));
                }
                if (iArr[4] >= 0) {
                    hiHealthData.setModifiedTime(cursor.getInt(r2));
                }
                int length = iArr.length - strArr.length;
                int length2 = strArr.length;
                while (i < length2) {
                    hiHealthData.putDouble(strArr[i], cursor.getDouble(iArr[length]));
                    i++;
                    length++;
                }
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseAggregateRawMixCursor(Cursor cursor, String[] strArr) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseAggregateRawMixCursor Cursor cursor == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                int i = 0;
                if (iArr == null) {
                    String[] strArr2 = {"start_time", "type_id", "end_time", "modified_time"};
                    int[] iArr2 = new int[strArr.length + 4];
                    for (int i2 = 0; i2 < 4; i2++) {
                        iArr2[i2] = cursor.getColumnIndex(strArr2[i2]);
                    }
                    for (int i3 = 0; i3 < strArr.length; i3++) {
                        iArr2[i3 + 4] = cursor.getColumnIndex(strArr[i3]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setStartTime(cursor.getLong(iArr[0]));
                hiHealthData.setType(cursor.getInt(iArr[1]));
                hiHealthData.setEndTime(cursor.getLong(iArr[2]));
                int i4 = iArr[3];
                if (i4 >= 0) {
                    hiHealthData.setModifiedTime(cursor.getLong(i4));
                }
                int length = iArr.length - strArr.length;
                int length2 = strArr.length;
                while (i < length2) {
                    hiHealthData.putDouble(strArr[i], cursor.getDouble(iArr[length]));
                    i++;
                    length++;
                }
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseNoSyncRawSportCursor(Cursor cursor, String[] strArr) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, " parseNoSyncRawSportCursor() cursor is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                int i = 0;
                if (iArr == null) {
                    String[] strArr2 = {"_id", "end_time", "start_time", "type_id", "client_id", "metadata", "modified_time", COLUMN_TIME_ZONE};
                    int[] iArr2 = new int[(strArr.length * 2) + 8];
                    int i2 = 0;
                    for (int i3 = 8; i2 < i3; i3 = 8) {
                        iArr2[i2] = cursor.getColumnIndex(strArr2[i2]);
                        i2++;
                    }
                    for (int i4 = 0; i4 < strArr.length; i4++) {
                        iArr2[i4 + 8] = cursor.getColumnIndex(strArr[i4]);
                    }
                    for (int i5 = 0; i5 < strArr.length; i5++) {
                        iArr2[i5 + 8 + strArr.length] = cursor.getColumnIndex(strArr[i5] + "Merged");
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setDataId(cursor.getLong(iArr[0]));
                hiHealthData.setEndTime(cursor.getLong(iArr[1]));
                hiHealthData.setStartTime(cursor.getLong(iArr[2]));
                hiHealthData.setType(cursor.getInt(iArr[3]));
                hiHealthData.setClientId(cursor.getInt(iArr[4]));
                hiHealthData.setMetaData(cursor.getString(iArr[5]));
                hiHealthData.putLong("modified_time", cursor.getLong(iArr[6]));
                hiHealthData.setTimeZone(cursor.getString(iArr[7]));
                int length = iArr.length - (strArr.length * 2);
                int length2 = strArr.length;
                int i6 = 0;
                while (i6 < length2) {
                    hiHealthData.putDouble(strArr[i6], cursor.getDouble(iArr[length]));
                    i6++;
                    length++;
                }
                int length3 = iArr.length - strArr.length;
                int length4 = strArr.length;
                while (i < length4) {
                    hiHealthData.putDouble(strArr[i] + "Merged", cursor.getDouble(iArr[length3]));
                    i++;
                    length3++;
                }
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }
}
