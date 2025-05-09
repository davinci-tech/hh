package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.FitnessCallback;
import com.huawei.basefitnessadvice.model.DayTotalRecord;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.plan.model.data.BaseDao;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.jsoperation.JsUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class etu extends BaseDao {
    public void a(final String str, final long j, final float f, final int i, final String str2, final boolean z) {
        ThreadPoolManager.d().c("Suggestion_DayTotalRecordDao", new Runnable() { // from class: etu.3
            @Override // java.lang.Runnable
            public void run() {
                etu.this.c(str, j, f, i);
                Bundle bundle = new Bundle();
                bundle.putLong(FitnessCallback.BUNDLE_FITNESS_DURATION, i);
                if (z) {
                    fhp.c().onChange(str2, 4, bundle);
                }
            }
        });
    }

    public List<Map<String, Object>> c(String str, int i, int i2) {
        ArrayList arrayList = new ArrayList(16);
        if (i2 < i) {
            LogUtil.b("Suggestion_DayTotalRecordDao", "getRecordsByDateRange, Data error");
            return arrayList;
        }
        for (DayTotalRecord dayTotalRecord : d(str, i, i2)) {
            if (dayTotalRecord != null) {
                HashMap hashMap = new HashMap(3);
                hashMap.put("date", String.valueOf(dayTotalRecord.getDate()));
                hashMap.put(JsUtil.SUGGESTION_TOTAL_CALORIE, String.valueOf(dayTotalRecord.getTotalCalorie()));
                hashMap.put("totalduration", String.valueOf(dayTotalRecord.getTotalDuration()));
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, long j, float f, int i) {
        if (a(j)) {
            LogUtil.a("Suggestion_DayTotalRecordDao", "record overtime!");
            return;
        }
        if (e(str, j)) {
            LogUtil.a("Suggestion_DayTotalRecordDao", "record exist!");
            return;
        }
        try {
            a(str, j, f, Integer.parseInt(moe.g(i)));
        } catch (NumberFormatException e) {
            LogUtil.b("Suggestion_DayTotalRecordDao", "parse exception", LogAnonymous.b((Throwable) e));
        }
        for (DayTotalRecord dayTotalRecord : c(str)) {
            e(dayTotalRecord.getTimeStamp(), dayTotalRecord.getTotalCalorie(), dayTotalRecord.getDuration(), str, dayTotalRecord.getDate());
        }
    }

    private void e(long j, float f, int i, final String str, final int i2) {
        LogUtil.c("Suggestion_DayTotalRecordDao", "updateCloud: date", Integer.valueOf(ggl.b(j)), " calorie", Float.valueOf(f), " duration", Integer.valueOf(i));
        eqa.a().setEvent(f, i, j, new DataCallback() { // from class: etu.2
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i3, String str2) {
                LogUtil.h("Suggestion_DayTotalRecordDao", "setEvent onFailure ", Integer.valueOf(i3));
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a("Suggestion_DayTotalRecordDao", "setEvent onSuccess");
                etu.this.c(str, i2);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0062, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0060, code lost:
    
        if (r1 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0048, code lost:
    
        if (r1 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0065, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e(java.lang.String r4, long r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 16
            r0.<init>(r1)
            java.lang.String r1 = "select * from "
            r0.append(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r2 = "fitness_total_record"
            java.lang.String r1 = r1.getTableFullName(r2)
            r0.append(r1)
            java.lang.String r1 = " where userId=? and recordTime=?"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r4 = health.compact.a.utils.StringUtils.c(r4)
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            java.lang.Long r5 = defpackage.gic.c(r5)
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String[] r4 = new java.lang.String[]{r4, r5}
            r5 = 1
            r6 = 0
            r1 = 0
            ett r2 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L4b android.database.sqlite.SQLiteException -> L4d
            android.database.Cursor r1 = r2.rawQueryStorageData(r5, r0, r4)     // Catch: java.lang.Throwable -> L4b android.database.sqlite.SQLiteException -> L4d
            if (r1 == 0) goto L48
            boolean r4 = r1.moveToNext()     // Catch: java.lang.Throwable -> L4b android.database.sqlite.SQLiteException -> L4d
            r6 = r4
        L48:
            if (r1 == 0) goto L65
            goto L62
        L4b:
            r4 = move-exception
            goto L66
        L4d:
            r4 = move-exception
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L4b
            java.lang.String r2 = "isRecordTimeExist "
            r0[r6] = r2     // Catch: java.lang.Throwable -> L4b
            java.lang.String r4 = health.compact.a.LogAnonymous.b(r4)     // Catch: java.lang.Throwable -> L4b
            r0[r5] = r4     // Catch: java.lang.Throwable -> L4b
            java.lang.String r4 = "Suggestion_DayTotalRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r4, r0)     // Catch: java.lang.Throwable -> L4b
            if (r1 == 0) goto L65
        L62:
            r1.close()
        L65:
            return r6
        L66:
            if (r1 == 0) goto L6b
            r1.close()
        L6b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.etu.e(java.lang.String, long):boolean");
    }

    private boolean a(String str, long j, float f, int i) {
        int b = ggl.b(j);
        b(str, b);
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsbMapKeyNames.H5_USER_ID, str);
        contentValues.put("recordDate", Integer.valueOf(b));
        contentValues.put("recordTime", Long.valueOf(j));
        contentValues.put("recordCalorie", Float.valueOf(f));
        contentValues.put("recordDuration", Integer.valueOf(i));
        contentValues.put("recordStatus", (Integer) 0);
        contentValues.put("recordTotalCalorie", Float.valueOf(d(str, b, f)));
        contentValues.put("recordTotalDuration", Integer.valueOf(e(str, b, i)));
        contentValues.put("dayLastRecord", (Integer) 1);
        long insertStorageData = ett.i().insertStorageData("fitness_total_record", 1, contentValues);
        LogUtil.c("Suggestion_DayTotalRecordDao", "insertRecord", LogAnonymous.b(contentValues));
        return insertStorageData > 0;
    }

    private int e(String str, int i, int i2) {
        int i3 = 0;
        for (DayTotalRecord dayTotalRecord : e(str, i)) {
            if (dayTotalRecord != null) {
                i3 += dayTotalRecord.getDuration();
            }
        }
        LogUtil.a("Suggestion_DayTotalRecordDao", "getDayTotalDuration ", Integer.valueOf(i3), " recordDate", Integer.valueOf(i), " duration", Integer.valueOf(i2));
        return i3 + i2;
    }

    private float d(String str, int i, float f) {
        float f2 = 0.0f;
        for (DayTotalRecord dayTotalRecord : e(str, i)) {
            if (dayTotalRecord != null) {
                f2 += dayTotalRecord.getCalorie();
            }
        }
        LogUtil.a("Suggestion_DayTotalRecordDao", "getDayTotalCalorie ", Float.valueOf(f2), " recordDate", Integer.valueOf(i), " calorie", Float.valueOf(f));
        return f2 + f;
    }

    private boolean a(long j) {
        LogUtil.a("Suggestion_DayTotalRecordDao", "timeGap :", Long.valueOf(System.currentTimeMillis() - j));
        return System.currentTimeMillis() - j > 2592000000L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x004d, code lost:
    
        if (r2 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x006b, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0068, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
    
        if (r2 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.basefitnessadvice.model.DayTotalRecord> e(java.lang.String r6, int r7) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 16
            r0.<init>(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "select * from "
            r2.append(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r3 = "fitness_total_record"
            java.lang.String r1 = r1.getTableFullName(r3)
            r2.append(r1)
            java.lang.String r1 = " where userId=? and recordDate=?"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r6 = health.compact.a.utils.StringUtils.c(r6)
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String[] r6 = new java.lang.String[]{r6, r7}
            r7 = 1
            r2 = 0
            ett r3 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L50 android.database.sqlite.SQLiteException -> L52
            android.database.Cursor r2 = r3.rawQueryStorageData(r7, r1, r6)     // Catch: java.lang.Throwable -> L50 android.database.sqlite.SQLiteException -> L52
            if (r2 == 0) goto L4d
        L3f:
            boolean r6 = r2.moveToNext()     // Catch: java.lang.Throwable -> L50 android.database.sqlite.SQLiteException -> L52
            if (r6 == 0) goto L4d
            com.huawei.basefitnessadvice.model.DayTotalRecord r6 = r5.asa_(r2)     // Catch: java.lang.Throwable -> L50 android.database.sqlite.SQLiteException -> L52
            r0.add(r6)     // Catch: java.lang.Throwable -> L50 android.database.sqlite.SQLiteException -> L52
            goto L3f
        L4d:
            if (r2 == 0) goto L6b
            goto L68
        L50:
            r6 = move-exception
            goto L6c
        L52:
            r6 = move-exception
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L50
            java.lang.String r3 = "getDayTotalRecords "
            r4 = 0
            r1[r4] = r3     // Catch: java.lang.Throwable -> L50
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> L50
            r1[r7] = r6     // Catch: java.lang.Throwable -> L50
            java.lang.String r6 = "Suggestion_DayTotalRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r6, r1)     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L6b
        L68:
            r2.close()
        L6b:
            return r0
        L6c:
            if (r2 == 0) goto L71
            r2.close()
        L71:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.etu.e(java.lang.String, int):java.util.List");
    }

    private DayTotalRecord asa_(Cursor cursor) {
        DayTotalRecord dayTotalRecord = new DayTotalRecord();
        dayTotalRecord.setId(cursor.getInt(cursor.getColumnIndex("recordId")));
        dayTotalRecord.setUserId(cursor.getString(cursor.getColumnIndex(JsbMapKeyNames.H5_USER_ID)));
        dayTotalRecord.setDate(cursor.getInt(cursor.getColumnIndex("recordDate")));
        dayTotalRecord.setTimeStamp(cursor.getLong(cursor.getColumnIndex("recordTime")));
        dayTotalRecord.setCalorie(cursor.getFloat(cursor.getColumnIndex("recordCalorie")));
        dayTotalRecord.setDuration(cursor.getInt(cursor.getColumnIndex("recordDuration")));
        dayTotalRecord.setStatus(cursor.getInt(cursor.getColumnIndex("recordStatus")));
        dayTotalRecord.setTotalCalorie(cursor.getFloat(cursor.getColumnIndex("recordTotalCalorie")));
        dayTotalRecord.setTotalDuration(cursor.getInt(cursor.getColumnIndex("recordTotalDuration")));
        dayTotalRecord.setIsLastRecord(cursor.getInt(cursor.getColumnIndex("dayLastRecord")));
        return dayTotalRecord;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0049, code lost:
    
        if (r3 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0067, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0064, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0062, code lost:
    
        if (r3 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.basefitnessadvice.model.DayTotalRecord> c(java.lang.String r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 16
            r0.<init>(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "select * from "
            r2.append(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r3 = "fitness_total_record"
            java.lang.String r1 = r1.getTableFullName(r3)
            r2.append(r1)
            java.lang.String r1 = " where userId=? and recordStatus=0 and dayLastRecord=1"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r7 = health.compact.a.utils.StringUtils.c(r7)
            java.lang.String[] r7 = new java.lang.String[]{r7}
            r2 = 1
            r3 = 0
            ett r4 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L4c android.database.sqlite.SQLiteException -> L4e
            android.database.Cursor r3 = r4.rawQueryStorageData(r2, r1, r7)     // Catch: java.lang.Throwable -> L4c android.database.sqlite.SQLiteException -> L4e
            if (r3 == 0) goto L49
        L3b:
            boolean r7 = r3.moveToNext()     // Catch: java.lang.Throwable -> L4c android.database.sqlite.SQLiteException -> L4e
            if (r7 == 0) goto L49
            com.huawei.basefitnessadvice.model.DayTotalRecord r7 = r6.asa_(r3)     // Catch: java.lang.Throwable -> L4c android.database.sqlite.SQLiteException -> L4e
            r0.add(r7)     // Catch: java.lang.Throwable -> L4c android.database.sqlite.SQLiteException -> L4e
            goto L3b
        L49:
            if (r3 == 0) goto L67
            goto L64
        L4c:
            r7 = move-exception
            goto L68
        L4e:
            r7 = move-exception
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L4c
            java.lang.String r4 = "getDayTotalRecords "
            r5 = 0
            r1[r5] = r4     // Catch: java.lang.Throwable -> L4c
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L4c
            r1[r2] = r7     // Catch: java.lang.Throwable -> L4c
            java.lang.String r7 = "Suggestion_DayTotalRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r7, r1)     // Catch: java.lang.Throwable -> L4c
            if (r3 == 0) goto L67
        L64:
            r3.close()
        L67:
            return r0
        L68:
            if (r3 == 0) goto L6d
            r3.close()
        L6d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.etu.c(java.lang.String):java.util.List");
    }

    private boolean b(String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("dayLastRecord", (Integer) 0);
        int updateStorageData = ett.i().updateStorageData("fitness_total_record", 1, contentValues, "userId=? and recordDate=?", new String[]{StringUtils.c((Object) str), StringUtils.c((Object) String.valueOf(i))});
        LogUtil.a("Suggestion_DayTotalRecordDao", "isUpdateDayRecordIsLast ", Integer.valueOf(updateStorageData));
        return updateStorageData > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("recordStatus", (Integer) 1);
        return ett.i().updateStorageData("fitness_total_record", 1, contentValues, "userId=? and recordDate=? and dayLastRecord=1", new String[]{StringUtils.c((Object) str), StringUtils.c((Object) String.valueOf(i))}) > 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0061, code lost:
    
        if (r7 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x007f, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x007c, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007a, code lost:
    
        if (r7 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.basefitnessadvice.model.DayTotalRecord> d(java.lang.String r5, int r6, int r7) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 16
            r0.<init>(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "select * from "
            r2.append(r1)
            ett r1 = defpackage.ett.i()
            java.lang.String r3 = "fitness_total_record"
            java.lang.String r1 = r1.getTableFullName(r3)
            r2.append(r1)
            java.lang.String r1 = " where userId=? and dayLastRecord=1 and recordDate>=? and recordDate<=?"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r5 = health.compact.a.utils.StringUtils.c(r5)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            int r6 = defpackage.gic.e(r6)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            int r7 = defpackage.gic.e(r7)
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r7}
            r6 = 1
            r7 = 0
            ett r2 = defpackage.ett.i()     // Catch: java.lang.Throwable -> L64 android.database.sqlite.SQLiteException -> L66
            android.database.Cursor r7 = r2.rawQueryStorageData(r6, r1, r5)     // Catch: java.lang.Throwable -> L64 android.database.sqlite.SQLiteException -> L66
            if (r7 == 0) goto L61
        L53:
            boolean r5 = r7.moveToNext()     // Catch: java.lang.Throwable -> L64 android.database.sqlite.SQLiteException -> L66
            if (r5 == 0) goto L61
            com.huawei.basefitnessadvice.model.DayTotalRecord r5 = r4.asa_(r7)     // Catch: java.lang.Throwable -> L64 android.database.sqlite.SQLiteException -> L66
            r0.add(r5)     // Catch: java.lang.Throwable -> L64 android.database.sqlite.SQLiteException -> L66
            goto L53
        L61:
            if (r7 == 0) goto L7f
            goto L7c
        L64:
            r5 = move-exception
            goto L80
        L66:
            r5 = move-exception
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = "getRecordsByRange "
            r3 = 0
            r1[r3] = r2     // Catch: java.lang.Throwable -> L64
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L64
            r1[r6] = r5     // Catch: java.lang.Throwable -> L64
            java.lang.String r5 = "Suggestion_DayTotalRecordDao"
            com.huawei.hwlogsmodel.LogUtil.b(r5, r1)     // Catch: java.lang.Throwable -> L64
            if (r7 == 0) goto L7f
        L7c:
            r7.close()
        L7f:
            return r0
        L80:
            if (r7 == 0) goto L85
            r7.close()
        L85:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.etu.d(java.lang.String, int, int):java.util.List");
    }
}
