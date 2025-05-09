package com.huawei.hihealthservice.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.ihh;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class DBPointCommon extends DBCommon {
    public static final String COLUMN_CLIENT_ID = "client_id";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MERGED = "merged";
    public static final String COLUMN_META_DATA = "metadata";
    public static final String COLUMN_MODIFIED_TIME = "modified_time";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_SYNC_STATUS = "sync_status";
    public static final String COLUMN_TIME_ZONE = "timeZone";
    public static final String COLUMN_TYPE_ID = "type_id";
    public static final String COLUMN_UNIT_ID = "unit_id";
    public static final String COLUMN_VALUE = "value";
    private static final String LOG_TAG = "Debug_DBPointCommon";
    private static final String RELEASE_LOG_TAG = "HiH_DBPointCommon";

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

    public DBPointCommon() {
    }

    public DBPointCommon(String str) {
        super(str);
    }

    public static String getPointCreateTableSQL(String str) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" create table  IF NOT EXISTS " + str + Constants.LEFT_BRACKET_ONLY);
        sb.append("_id integer primary key  not null,start_time integer not null,end_time integer not null,type_id integer not null,value double not null,metadata text,unit_id integer not null,client_id integer not null,merged integer not null,sync_status integer not null,timeZone text not null,modified_time integer not null)");
        return sb.toString();
    }

    public static String getStartTimeIndexSQL(String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX  IF NOT EXISTS ");
        sb.append(str + " ON " + str2 + Constants.LEFT_BRACKET_ONLY);
        sb.append("start_time,end_time,type_id,client_id)");
        return sb.toString();
    }

    public static String getPointCommonIndexSQL(String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX  IF NOT EXISTS ");
        sb.append(str + " ON " + str2 + Constants.LEFT_BRACKET_ONLY);
        sb.append("type_id,start_time,end_time,client_id)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "start_time", "end_time", "type_id", "value", "metadata", COLUMN_UNIT_ID, "client_id", "merged", "sync_status", "timeZone", "modified_time"};
    }

    public static List<HiHealthData> parsePointListWithDeviceUUID(Cursor cursor, String str) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parsePointListWithDeviceUUID Cursor query == null");
            return null;
        }
        char c = '\n';
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = new String[12];
                    strArr[0] = "_id";
                    strArr[1] = "type_id";
                    strArr[2] = "start_time";
                    strArr[3] = "end_time";
                    strArr[4] = "metadata";
                    strArr[5] = "value";
                    strArr[6] = COLUMN_UNIT_ID;
                    strArr[7] = "sync_status";
                    strArr[8] = "merged";
                    strArr[9] = "timeZone";
                    strArr[c] = "client_id";
                    strArr[11] = "modified_time";
                    int[] iArr2 = new int[12];
                    int i = 0;
                    for (int i2 = 12; i < i2; i2 = 12) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                        i++;
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData(iArr.length + 1);
                hiHealthData.setDataId(cursor.getLong(iArr[0]));
                hiHealthData.setType(cursor.getInt(iArr[1]));
                hiHealthData.setStartTime(cursor.getLong(iArr[2]));
                hiHealthData.setEndTime(cursor.getLong(iArr[3]));
                hiHealthData.setMetaData(cursor.getString(iArr[4]));
                hiHealthData.setValue(cursor.getDouble(iArr[5]));
                hiHealthData.setPointUnit(cursor.getInt(iArr[6]));
                hiHealthData.setSyncStatus(cursor.getInt(iArr[7]));
                hiHealthData.setDeviceUuid(str);
                hiHealthData.putInt("merged", cursor.getInt(iArr[8]));
                hiHealthData.setTimeZone(cursor.getString(iArr[9]));
                hiHealthData.setClientId(cursor.getInt(iArr[10]));
                hiHealthData.putLong("modified_time", cursor.getLong(iArr[11]));
                arrayList.add(hiHealthData);
                c = '\n';
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseMergePointListWithClientIdAndMerged(Cursor cursor) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseMergePointListWithClientIdAndMerged() Cursor query == null");
            return null;
        }
        char c = '\n';
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                try {
                    if (iArr == null) {
                        String[] strArr = new String[12];
                        strArr[0] = "_id";
                        strArr[1] = "start_time";
                        strArr[2] = "end_time";
                        strArr[3] = "timeZone";
                        strArr[4] = "type_id";
                        strArr[5] = "client_id";
                        strArr[6] = "value";
                        strArr[7] = "metadata";
                        strArr[8] = COLUMN_UNIT_ID;
                        strArr[9] = "sync_status";
                        strArr[c] = "merged";
                        strArr[11] = "modified_time";
                        int[] iArr2 = new int[12];
                        int i = 0;
                        for (int i2 = 12; i < i2; i2 = 12) {
                            iArr2[i] = cursor.getColumnIndex(strArr[i]);
                            i++;
                        }
                        iArr = iArr2;
                    }
                    HiHealthData hiHealthData = new HiHealthData(iArr.length + 1);
                    hiHealthData.setDataId(cursor.getLong(iArr[0]));
                    hiHealthData.setStartTime(cursor.getLong(iArr[1]));
                    hiHealthData.setEndTime(cursor.getLong(iArr[2]));
                    hiHealthData.setTimeZone(cursor.getString(iArr[3]));
                    hiHealthData.setType(cursor.getInt(iArr[4]));
                    hiHealthData.setClientId(cursor.getInt(iArr[5]));
                    hiHealthData.setValue(cursor.getDouble(iArr[6]));
                    hiHealthData.setMetaData(cursor.getString(iArr[7]));
                    hiHealthData.setPointUnit(cursor.getInt(iArr[8]));
                    hiHealthData.setSyncStatus(cursor.getInt(iArr[9]));
                    hiHealthData.putInt("merged", cursor.getInt(iArr[10]));
                    hiHealthData.setModifiedTime(cursor.getLong(iArr[11]));
                    arrayList.add(hiHealthData);
                    c = '\n';
                } catch (SQLiteDatabaseCorruptException e) {
                    ReleaseLogUtil.c(RELEASE_LOG_TAG, "parseMergePointListWithClientIdAndMerged SQLiteDatabaseCorruptException", LogAnonymous.b((Throwable) e));
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseNoSyncPointList(Cursor cursor) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseNoSyncPointList() Cursor query == null");
            return null;
        }
        char c = '\n';
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = new String[12];
                    strArr[0] = "_id";
                    strArr[1] = "start_time";
                    strArr[2] = "end_time";
                    strArr[3] = "timeZone";
                    strArr[4] = "type_id";
                    strArr[5] = COLUMN_UNIT_ID;
                    strArr[6] = "value";
                    strArr[7] = "modified_time";
                    strArr[8] = "metadata";
                    strArr[9] = "sync_status";
                    strArr[c] = "client_id";
                    strArr[11] = "merged";
                    int[] iArr2 = new int[12];
                    int i = 0;
                    for (int i2 = 12; i < i2; i2 = 12) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                        i++;
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setDataId(cursor.getLong(iArr[0]));
                hiHealthData.setStartTime(cursor.getLong(iArr[1]));
                hiHealthData.setEndTime(cursor.getLong(iArr[2]));
                hiHealthData.setTimeZone(cursor.getString(iArr[3]));
                hiHealthData.setType(cursor.getInt(iArr[4]));
                hiHealthData.setPointUnit(cursor.getInt(iArr[5]));
                hiHealthData.setValue(cursor.getDouble(iArr[6]));
                hiHealthData.putLong("modified_time", cursor.getLong(iArr[7]));
                hiHealthData.setMetaData(cursor.getString(iArr[8]));
                hiHealthData.setSyncStatus(cursor.getInt(iArr[9]));
                hiHealthData.setClientId(cursor.getInt(iArr[10]));
                hiHealthData.setMergedStatus(cursor.getInt(iArr[11]));
                arrayList.add(hiHealthData);
                c = '\n';
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseNoSyncHealthPointCursor(Cursor cursor, String[] strArr) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseNoSyncHealthPointCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                int i = 0;
                if (iArr == null) {
                    String[] strArr2 = {"start_time", "end_time", "type_id", "timeZone", "client_id"};
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
                hiHealthData.setStartTime(cursor.getLong(iArr[0]));
                hiHealthData.setEndTime(cursor.getLong(iArr[1]));
                hiHealthData.setType(cursor.getInt(iArr[2]));
                hiHealthData.setTimeZone(cursor.getString(iArr[3]));
                hiHealthData.setClientId(cursor.getInt(iArr[4]));
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

    public static List<HiHealthData> parseAggregateHealthPointCursor(Cursor cursor, String[] strArr) {
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseAggregateHealthPointCursor() query == null");
            return null;
        }
        return getAggregateHiHealthDatas(cursor, strArr);
    }

    public static List<HiHealthData> parseAggregatePointCursor(Cursor cursor, String[] strArr) {
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseAggregatePointCursor() Cursor query == null");
            return null;
        }
        return getAggregateHiHealthDatas(cursor, strArr);
    }

    public static List<HiHealthData> parseAggregateWeightPointCursor(Cursor cursor, String[] strArr) {
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseAggregateWeightPointCursor() Cursor query == null");
            return null;
        }
        return ihh.bxL_(cursor, strArr);
    }

    public static List<HiHealthData> parseStateDeviceStepData(Cursor cursor) {
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseStateDeviceStepData() Cursor query == null");
            return null;
        }
        return ihh.bxK_(cursor);
    }

    private static List<HiHealthData> getAggregateHiHealthDatas(Cursor cursor, String[] strArr) {
        ArrayList arrayList = new ArrayList(10);
        int[] iArr = null;
        while (cursor.moveToNext()) {
            try {
                try {
                    if (iArr == null) {
                        String[] strArr2 = {"start_time", "end_time", "type_id", "metadata", "client_id", "timeZone", "modified_time"};
                        int[] iArr2 = new int[strArr.length + 7];
                        for (int i = 0; i < 7; i++) {
                            iArr2[i] = cursor.getColumnIndex(strArr2[i]);
                        }
                        for (int i2 = 0; i2 < strArr.length; i2++) {
                            iArr2[i2 + 7] = cursor.getColumnIndex(strArr[i2]);
                        }
                        iArr = iArr2;
                    }
                    HiHealthData hiHealthData = new HiHealthData();
                    hiHealthData.setStartTime(cursor.getLong(iArr[0]));
                    hiHealthData.setEndTime(cursor.getLong(iArr[1]));
                    hiHealthData.setType(cursor.getInt(iArr[2]));
                    int i3 = iArr[3];
                    if (i3 >= 0) {
                        hiHealthData.setMetaData(cursor.getString(i3));
                    }
                    int i4 = iArr[4];
                    if (i4 >= 0) {
                        hiHealthData.setClientId(cursor.getInt(i4));
                    }
                    int i5 = iArr[5];
                    if (i5 >= 0) {
                        hiHealthData.setTimeZone(cursor.getString(i5));
                    }
                    int i6 = iArr[6];
                    if (i6 >= 0) {
                        hiHealthData.setModifiedTime(cursor.getLong(i6));
                    }
                    int length = iArr.length - strArr.length;
                    for (String str : strArr) {
                        if (!"dysmenorrhea".equals(str) && !"quantity".equals(str)) {
                            hiHealthData.putDouble(str, cursor.getDouble(iArr[length]));
                            length++;
                        }
                        if (!cursor.isNull(iArr[length])) {
                            hiHealthData.putDouble(str, cursor.getDouble(iArr[length]));
                        }
                        length++;
                    }
                    arrayList.add(hiHealthData);
                } catch (SQLiteDatabaseCorruptException e) {
                    ReleaseLogUtil.c(RELEASE_LOG_TAG, "getAggregateHiHealthDatas() SQLiteDatabaseCorruptException", LogAnonymous.b((Throwable) e));
                }
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> parseNoSyncDeviceStatCursor(Cursor cursor, String[] strArr) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h(LOG_TAG, "parseRawSportCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                int i = 0;
                if (iArr == null) {
                    String[] strArr2 = {"_id", "start_time", "end_time", "type_id", "client_id", "metadata", "timeZone", "modified_time"};
                    int[] iArr2 = new int[strArr.length + 8];
                    int i2 = 0;
                    for (int i3 = 8; i2 < i3; i3 = 8) {
                        iArr2[i2] = cursor.getColumnIndex(strArr2[i2]);
                        i2++;
                    }
                    for (int i4 = 0; i4 < strArr.length; i4++) {
                        iArr2[i4 + 8] = cursor.getColumnIndex(strArr[i4]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setDataId(cursor.getLong(iArr[0]));
                hiHealthData.setStartTime(cursor.getLong(iArr[1]));
                hiHealthData.setEndTime(cursor.getLong(iArr[2]));
                hiHealthData.setType(cursor.getInt(iArr[3]));
                hiHealthData.setClientId(cursor.getInt(iArr[4]));
                hiHealthData.setMetaData(cursor.getString(iArr[5]));
                hiHealthData.setTimeZone(cursor.getString(iArr[6]));
                hiHealthData.putLong("modified_time", cursor.getLong(iArr[7]));
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
}
