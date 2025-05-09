package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hihealthservice.db.table.DBPointCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class iht extends DBCommon {
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

    private iht() {
    }

    protected iht(String str) {
        super(str);
    }

    /* loaded from: classes7.dex */
    static class b {
        private static final iht d = new iht();
    }

    public static iht c() {
        return b.d;
    }

    public static String e() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS hihealth_stat_day(_id integer primary key not null,date integer not null,hihealth_type integer not null,stat_type integer not null,value double not null,user_id integer,unit_id integer not null,client_id integer,timeZone text not null,sync_status integer not null,modified_time integer not null)");
        return sb.toString();
    }

    public static String d() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX StatIndex ON hihealth_stat_day(date,stat_type,client_id)");
        return sb.toString();
    }

    public static String b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX IF NOT EXISTS StatDaySyncStatusIndex ON hihealth_stat_day(sync_status)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "date", "hihealth_type", "stat_type", "value", "user_id", DBPointCommon.COLUMN_UNIT_ID, "client_id", "timeZone", "sync_status", "modified_time"};
    }

    public static List<HiHealthData> byA_(Cursor cursor) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBStatDay", "parseStatCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr = {"date", "hihealth_type", "stat_type", "value", DBPointCommon.COLUMN_UNIT_ID, "timeZone", "modified_time"};
                    int[] iArr2 = new int[7];
                    for (int i = 0; i < 7; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr[i]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                long a2 = HiDateUtil.a(cursor.getInt(iArr[0]));
                hiHealthData.setStartTime(a2);
                hiHealthData.setEndTime(a2);
                hiHealthData.putInt("hihealth_type", cursor.getInt(iArr[1]));
                hiHealthData.setType(cursor.getInt(iArr[2]));
                hiHealthData.setValue(cursor.getDouble(iArr[3]));
                hiHealthData.setPointUnit(cursor.getInt(iArr[4]));
                hiHealthData.setTimeZone(cursor.getString(iArr[5]));
                hiHealthData.setModifiedTime(cursor.getLong(iArr[6]));
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<igo> byC_(Cursor cursor) {
        if (cursor == null) {
            LogUtil.h("Debug_DBStatDay", "parseStatTablesCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                arrayList.add(byv_(cursor));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static igo byz_(Cursor cursor) {
        igo igoVar = null;
        try {
            if (cursor == null) {
                LogUtil.h("Debug_DBStatDay", "parseOneStatCursor() Cursor query == null");
                return null;
            }
            try {
                if (cursor.moveToNext()) {
                    igoVar = byv_(cursor);
                }
            } catch (SQLiteException e) {
                ReleaseLogUtil.c("Debug_DBStatDay", "parseOneIntCursor sqLiteException message: " + e.getMessage());
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
                linkedHashMap.put("errorCode", Integer.toString(-1));
                ivz.d(BaseApplication.e()).c(OperationKey.HEALTH_APP_SQL_ERROR_2129011.value(), linkedHashMap, false);
            }
            return igoVar;
        } finally {
            cursor.close();
        }
    }

    private static igo byv_(Cursor cursor) {
        igo igoVar = new igo();
        igoVar.a(cursor.getInt(cursor.getColumnIndex("_id")));
        igoVar.b(cursor.getInt(cursor.getColumnIndex("client_id")));
        igoVar.e(cursor.getInt(cursor.getColumnIndex("date")));
        igoVar.c(cursor.getInt(cursor.getColumnIndex("hihealth_type")));
        igoVar.d(cursor.getInt(cursor.getColumnIndex("stat_type")));
        igoVar.a(cursor.getDouble(cursor.getColumnIndex("value")));
        igoVar.h(cursor.getInt(cursor.getColumnIndex(DBPointCommon.COLUMN_UNIT_ID)));
        igoVar.b(cursor.getString(cursor.getColumnIndex("timeZone")));
        igoVar.a(cursor.getLong(cursor.getColumnIndex("modified_time")));
        igoVar.g(cursor.getInt(cursor.getColumnIndex("sync_status")));
        return igoVar;
    }

    public static List<Integer> byB_(Cursor cursor) {
        if (cursor == null) {
            LogUtil.h("Debug_DBStatDay", "parseStatCursorDays() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        boolean z = true;
        int i = 0;
        while (cursor.moveToNext()) {
            try {
                if (z) {
                    i = cursor.getColumnIndex("date");
                    z = false;
                }
                arrayList.add(Integer.valueOf(cursor.getInt(i)));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> byy_(Cursor cursor, String[] strArr, boolean z) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBStatDay", "parseNoSyncStatCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr2 = {"_id", "date", "stat_type", "timeZone", "modified_time"};
                    int[] iArr2 = new int[strArr.length + 5];
                    for (int i = 0; i < 5; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr2[i]);
                    }
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        iArr2[i2 + 5] = cursor.getColumnIndex(strArr[i2]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setDataId(cursor.getInt(iArr[0]));
                hiHealthData.setStartTime(HiDateUtil.a(cursor.getInt(iArr[1])));
                hiHealthData.setType(cursor.getInt(iArr[2]));
                hiHealthData.setTimeZone(cursor.getString(iArr[3]));
                hiHealthData.putLong("modified_time", cursor.getLong(iArr[4]));
                int length = iArr.length - strArr.length;
                for (String str : strArr) {
                    if (!z || !cursor.isNull(iArr[length])) {
                        hiHealthData.putDouble(str, cursor.getDouble(iArr[length]));
                    }
                    length++;
                }
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> byx_(Cursor cursor, String[] strArr, boolean z) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBStatDay", "parseAggregateStatCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                if (iArr == null) {
                    String[] strArr2 = {"date", "stat_type", "timeZone", "modified_time"};
                    int[] iArr2 = new int[strArr.length + 4];
                    for (int i = 0; i < 4; i++) {
                        iArr2[i] = cursor.getColumnIndex(strArr2[i]);
                    }
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        iArr2[i2 + 4] = cursor.getColumnIndex(strArr[i2]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                long a2 = HiDateUtil.a(cursor.getInt(iArr[0]));
                hiHealthData.setStartTime(a2);
                hiHealthData.setEndTime(a2);
                hiHealthData.setType(cursor.getInt(iArr[1]));
                hiHealthData.setTimeZone(cursor.getString(iArr[2]));
                if (strArr == HiHealthDataKey.b(10010)) {
                    hiHealthData.putLong("modified_time", cursor.getLong(iArr[3]));
                }
                int length = iArr.length - strArr.length;
                for (String str : strArr) {
                    if (!z || !cursor.isNull(iArr[length])) {
                        hiHealthData.putDouble(str, cursor.getDouble(iArr[length]));
                    }
                    length++;
                }
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> byw_(int i, Cursor cursor, String[] strArr) {
        int[] iArr = null;
        if (cursor == null) {
            LogUtil.h("Debug_DBStatDay", "parseAggregateStatCursor() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                int i2 = 0;
                if (iArr == null) {
                    String[] strArr2 = {"date", "hihealth_type", "stat_type", "timeZone", "modified_time"};
                    int[] iArr2 = new int[strArr.length + 5];
                    for (int i3 = 0; i3 < 5; i3++) {
                        iArr2[i3] = cursor.getColumnIndex(strArr2[i3]);
                    }
                    for (int i4 = 0; i4 < strArr.length; i4++) {
                        iArr2[i4 + 5] = cursor.getColumnIndex(strArr[i4]);
                    }
                    iArr = iArr2;
                }
                HiHealthData hiHealthData = new HiHealthData();
                long a2 = HiDateUtil.a(cursor.getInt(iArr[0]));
                hiHealthData.setStartTime(a2);
                hiHealthData.setEndTime(a2);
                if (i > 0) {
                    hiHealthData.putInt("hihealth_type", i);
                } else {
                    hiHealthData.putInt("hihealth_type", cursor.getInt(iArr[1]));
                }
                hiHealthData.setType(cursor.getInt(iArr[2]));
                hiHealthData.setTimeZone(cursor.getString(iArr[3]));
                if (strArr == HiHealthDataKey.b(10010)) {
                    hiHealthData.putLong("modified_time", cursor.getLong(iArr[4]));
                }
                int length = iArr.length - strArr.length;
                int length2 = strArr.length;
                while (i2 < length2) {
                    hiHealthData.putDouble(strArr[i2], cursor.getDouble(iArr[length]));
                    i2++;
                    length++;
                }
                arrayList.add(hiHealthData);
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "hihealth_stat_day";
    }
}
