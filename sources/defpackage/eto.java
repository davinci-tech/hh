package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.google.gson.Gson;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.health.plan.model.model.DataSync;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class eto extends BaseDao {
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0043, code lost:
    
        if (r2 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0060, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x005d, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005b, code lost:
    
        if (r2 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int e(java.lang.String r8) {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            java.lang.String r5 = "SELECT COUNT(*) AS recordCount FROM "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            java.lang.String r6 = "data_sync"
            java.lang.String r5 = r5.getTableFullName(r6)     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            r4.append(r5)     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            java.lang.String r5 = " WHERE userId=?"
            r4.append(r5)     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            java.lang.String r8 = health.compact.a.utils.StringUtils.c(r8)     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            r5[r1] = r8     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            android.database.Cursor r2 = r3.rawQueryStorageData(r0, r4, r5)     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            if (r2 == 0) goto L43
            boolean r8 = r2.moveToNext()     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            if (r8 == 0) goto L43
            java.lang.String r8 = "recordCount"
            int r8 = r2.getColumnIndex(r8)     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            int r8 = r2.getInt(r8)     // Catch: java.lang.Throwable -> L46 android.database.sqlite.SQLiteException -> L48
            r1 = r8
        L43:
            if (r2 == 0) goto L60
            goto L5d
        L46:
            r8 = move-exception
            goto L61
        L48:
            r8 = move-exception
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L46
            java.lang.String r4 = "getSyncRecordCount:"
            r3[r1] = r4     // Catch: java.lang.Throwable -> L46
            java.lang.String r8 = health.compact.a.LogAnonymous.b(r8)     // Catch: java.lang.Throwable -> L46
            r3[r0] = r8     // Catch: java.lang.Throwable -> L46
            java.lang.String r8 = "Suggestion_DataSyncDao"
            com.huawei.hwlogsmodel.LogUtil.b(r8, r3)     // Catch: java.lang.Throwable -> L46
            if (r2 == 0) goto L60
        L5d:
            r2.close()
        L60:
            return r1
        L61:
            if (r2 == 0) goto L66
            r2.close()
        L66:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eto.e(java.lang.String):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0044, code lost:
    
        if (r2 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0061, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x005e, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005c, code lost:
    
        if (r2 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int b(long r8) {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            java.lang.String r5 = "SELECT syncTimes FROM "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            ett r5 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            java.lang.String r6 = "data_sync"
            java.lang.String r5 = r5.getTableFullName(r6)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            r4.append(r5)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            java.lang.String r5 = " WHERE recordId=?"
            r4.append(r5)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            r5[r1] = r8     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            android.database.Cursor r2 = r3.rawQueryStorageData(r0, r4, r5)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            if (r2 == 0) goto L44
            boolean r8 = r2.moveToNext()     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            if (r8 == 0) goto L44
            java.lang.String r8 = "syncTimes"
            int r8 = r2.getColumnIndex(r8)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            int r8 = r2.getInt(r8)     // Catch: java.lang.Throwable -> L47 android.database.sqlite.SQLiteException -> L49
            r1 = r8
        L44:
            if (r2 == 0) goto L61
            goto L5e
        L47:
            r8 = move-exception
            goto L62
        L49:
            r8 = move-exception
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L47
            java.lang.String r3 = "getSyncRecordTimes:"
            r9[r1] = r3     // Catch: java.lang.Throwable -> L47
            java.lang.String r8 = health.compact.a.LogAnonymous.b(r8)     // Catch: java.lang.Throwable -> L47
            r9[r0] = r8     // Catch: java.lang.Throwable -> L47
            java.lang.String r8 = "Suggestion_DataSyncDao"
            com.huawei.hwlogsmodel.LogUtil.b(r8, r9)     // Catch: java.lang.Throwable -> L47
            if (r2 == 0) goto L61
        L5e:
            r2.close()
        L61:
            return r1
        L62:
            if (r2 == 0) goto L67
            r2.close()
        L67:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eto.b(long):int");
    }

    public void b(String str) {
        ett.i().deleteStorageData("data_sync", 1, "planId=?", new String[]{StringUtils.c((Object) str)});
    }

    public void e(DataSync dataSync) {
        ett.i().deleteStorageData("data_sync", 1, "recordId=" + dataSync.getRecordId());
    }

    public void a(String str, String str2, int i) {
        ett.i().deleteStorageData("data_sync", 1, "userId=? and type=" + i + " and planId=?", new String[]{StringUtils.c((Object) str), StringUtils.c((Object) str2)});
    }

    public DataSync c(String str, long j) {
        SQLiteException e;
        DataSync dataSync;
        Throwable th;
        Cursor cursor;
        Cursor cursor2 = null;
        r3 = null;
        DataSync dataSync2 = null;
        DataSync dataSync3 = null;
        cursor2 = null;
        try {
            try {
                cursor = ett.i().rawQueryStorageData(1, "SELECT * FROM " + ett.i().getTableFullName("data_sync") + " WHERE userId=? AND recordId > ? LIMIT 0,1 ", new String[]{str, String.valueOf(j)});
                if (cursor != null) {
                    try {
                        try {
                            if (cursor.moveToNext()) {
                                DataSync dataSync4 = new DataSync();
                                try {
                                    dataSync4.setRecordId(cursor.getLong(cursor.getColumnIndex("recordId")));
                                    dataSync4.setUserId(cursor.getString(cursor.getColumnIndex(JsbMapKeyNames.H5_USER_ID)));
                                    dataSync4.setType(cursor.getInt(cursor.getColumnIndex("type")));
                                    dataSync4.setValue(cursor.getString(cursor.getColumnIndex("value")));
                                    dataSync3 = dataSync4;
                                } catch (SQLiteException e2) {
                                    e = e2;
                                    dataSync2 = dataSync4;
                                    DataSync dataSync5 = dataSync2;
                                    cursor2 = cursor;
                                    dataSync = dataSync5;
                                    LogUtil.b("Suggestion_DataSyncDao", "getSyncRecord:", LogAnonymous.b((Throwable) e));
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    return dataSync;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    } catch (SQLiteException e3) {
                        e = e3;
                    }
                }
                if (cursor == null) {
                    return dataSync3;
                }
                cursor.close();
                return dataSync3;
            } catch (Throwable th3) {
                th = th3;
                cursor = cursor2;
            }
        } catch (SQLiteException e4) {
            e = e4;
            dataSync = null;
        }
    }

    public List<DataSync> c(String str, int i) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQueryStorageData = ett.i().rawQueryStorageData(1, "SELECT * FROM " + ett.i().getTableFullName("data_sync") + " WHERE userId=? AND type=?", new String[]{str, String.valueOf(i)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        DataSync dataSync = new DataSync();
                        dataSync.setRecordId(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("recordId")));
                        dataSync.setUserId(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(JsbMapKeyNames.H5_USER_ID)));
                        dataSync.setType(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("type")));
                        dataSync.setValue(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("value")));
                        arrayList.add(dataSync);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLiteException e) {
            LogUtil.b("Suggestion_DataSyncDao", "getSyncRecordByType:", LogAnonymous.b((Throwable) e));
        }
        return arrayList;
    }

    public boolean e(String str, String str2, int i, String str3) {
        LogUtil.a("Suggestion_DataSyncDao", "addSyncRecord() enter");
        if (i == 1 || i == 4 || i == 5 || i == 7 || i == 8 || i == 6) {
            a(str, str2, i);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("planId", StringUtils.c((Object) str2));
        contentValues.put(JsbMapKeyNames.H5_USER_ID, str);
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("value", str3);
        long insertStorageData = ett.i().insertStorageData("data_sync", 1, contentValues);
        LogUtil.c("Suggestion_DataSyncDao", "addSyncRecord：", contentValues.toString());
        etn.b();
        return insertStorageData > 0;
    }

    public boolean a(DataSync dataSync) {
        if (dataSync == null) {
            return false;
        }
        LogUtil.c("Suggestion_DataSyncDao", "isBeyondSyncTimes：", dataSync);
        int b = b(dataSync.getRecordId());
        if (b >= 5) {
            return true;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("syncTimes", Integer.valueOf(b + 1));
        ett.i().updateStorageData("data_sync", 1, contentValues, "recordId=" + dataSync.getRecordId());
        return false;
    }

    public boolean b(String str, String str2, String str3) {
        return e(str, str2, 1, gic.a(new String[]{str2, gic.a(str3)}));
    }

    public boolean d(String str, String str2, long j) {
        return e(str, str2, 2, String.valueOf(j));
    }

    public boolean c(String str, WorkoutRecord workoutRecord, String str2) {
        return e(str, "", 3, gic.a(new String[]{workoutRecord.acquireWorkoutId(), workoutRecord.acquireVersion(), workoutRecord.acquireWorkoutPackageId(), Integer.toString(workoutRecord.acquireCourseBelongType()), str2}));
    }

    public boolean c(String str, String str2, String str3) {
        return e(str, str2, 4, gic.a(new String[]{str2, str3}));
    }

    public boolean c(String str, String str2) {
        return e(str, str2, 5, str2);
    }

    public boolean e(String str, String str2) {
        return e(str, str2, 7, str2);
    }

    public boolean b(String str, int i, String str2) {
        return e(str, str2, 8, gic.a(new String[]{str2, Integer.toString(i)}));
    }

    public boolean b(String str, String str2) {
        return e(str, str2, 6, str2);
    }

    public boolean d(String str, mof mofVar) {
        return e(str, String.valueOf(mofVar.c()), 10, new Gson().toJson(mofVar));
    }

    public boolean b(String str, String str2, long j) {
        ReleaseLogUtil.e("Suggestion_DataSyncDao", "addUpdatePlanClock:", Long.valueOf(j));
        return e(str, str2, 11, Long.toString(j));
    }
}
