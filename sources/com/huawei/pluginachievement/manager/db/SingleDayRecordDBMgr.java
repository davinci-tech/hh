package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import defpackage.mke;
import defpackage.mlg;
import health.compact.a.Utils;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class SingleDayRecordDBMgr implements IAchieveDBMgr {
    private Context b;

    public SingleDayRecordDBMgr(Context context) {
        this.b = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return d(map.get("huid"));
    }

    private mcz d(String str) {
        SingleDayRecord singleDayRecord = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "SingleDayRecordDBMgr, query ,id is null!return");
            return null;
        }
        try {
            Cursor queryStorageData = mea.b(this.b).queryStorageData("single_day_record", 1, "huid='" + str + "'");
            if (queryStorageData != null) {
                while (queryStorageData.moveToNext()) {
                    try {
                        if (singleDayRecord == null) {
                            singleDayRecord = new SingleDayRecord();
                        }
                        singleDayRecord.setHuid(queryStorageData.getString(queryStorageData.getColumnIndex("huid")));
                        d(singleDayRecord, queryStorageData.getInt(queryStorageData.getColumnIndex("dataType")), queryStorageData.getString(queryStorageData.getColumnIndex("value")), queryStorageData.getString(queryStorageData.getColumnIndex("date")));
                    } finally {
                    }
                }
            }
            if (queryStorageData != null) {
                queryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_SingleDayRecordDBMgr", e.getMessage());
        }
        return singleDayRecord;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return Collections.emptyList();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        r8 = new com.huawei.pluginachievement.manager.model.SingleDayRecord();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
    
        r8.setHuid(r7.getString(r7.getColumnIndex("huid")));
        d(r8, r7.getInt(r7.getColumnIndex("dataType")), r7.getString(r7.getColumnIndex("value")), r7.getString(r7.getColumnIndex("date")));
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0074, code lost:
    
        r2 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0076, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0077, code lost:
    
        r2 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007b, code lost:
    
        if (r7 != null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007d, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0081, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0082, code lost:
    
        r0.addSuppressed(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0085, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private defpackage.mcz c(java.lang.String r7, int r8) {
        /*
            r6 = this;
            java.lang.String r0 = "dataType"
            java.lang.String r1 = "PLGACHIEVE_SingleDayRecordDBMgr"
            r2 = 0
            if (r7 != 0) goto L11
            java.lang.String r7 = "SingleDayRecordDBMgr, query ,id is null!return"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
            return r2
        L11:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "huid='"
            r3.<init>(r4)
            r3.append(r7)
            java.lang.String r7 = "'"
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            android.content.Context r3 = r6.b     // Catch: android.database.SQLException -> L8c
            mea r3 = defpackage.mea.b(r3)     // Catch: android.database.SQLException -> L8c
            java.lang.String r4 = "single_day_record"
            r5 = 1
            android.database.Cursor r7 = r3.queryStorageData(r4, r5, r7)     // Catch: android.database.SQLException -> L8c
            if (r7 == 0) goto L86
        L33:
            boolean r3 = r7.moveToNext()     // Catch: java.lang.Throwable -> L79
            if (r3 == 0) goto L86
            int r3 = r7.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L79
            int r3 = r7.getInt(r3)     // Catch: java.lang.Throwable -> L79
            if (r8 != r3) goto L33
            com.huawei.pluginachievement.manager.model.SingleDayRecord r8 = new com.huawei.pluginachievement.manager.model.SingleDayRecord     // Catch: java.lang.Throwable -> L79
            r8.<init>()     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = "huid"
            int r2 = r7.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L76
            java.lang.String r2 = r7.getString(r2)     // Catch: java.lang.Throwable -> L76
            r8.setHuid(r2)     // Catch: java.lang.Throwable -> L76
            int r0 = r7.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L76
            int r0 = r7.getInt(r0)     // Catch: java.lang.Throwable -> L76
            java.lang.String r2 = "value"
            int r2 = r7.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L76
            java.lang.String r2 = r7.getString(r2)     // Catch: java.lang.Throwable -> L76
            java.lang.String r3 = "date"
            int r3 = r7.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L76
            java.lang.String r3 = r7.getString(r3)     // Catch: java.lang.Throwable -> L76
            r6.d(r8, r0, r2, r3)     // Catch: java.lang.Throwable -> L76
            r2 = r8
            goto L86
        L76:
            r0 = move-exception
            r2 = r8
            goto L7b
        L79:
            r8 = move-exception
            r0 = r8
        L7b:
            if (r7 == 0) goto L85
            r7.close()     // Catch: java.lang.Throwable -> L81
            goto L85
        L81:
            r7 = move-exception
            r0.addSuppressed(r7)     // Catch: android.database.SQLException -> L8c
        L85:
            throw r0     // Catch: android.database.SQLException -> L8c
        L86:
            if (r7 == 0) goto L98
            r7.close()     // Catch: android.database.SQLException -> L8c
            goto L98
        L8c:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
        L98:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginachievement.manager.db.SingleDayRecordDBMgr.c(java.lang.String, int):mcz");
    }

    private void d(SingleDayRecord singleDayRecord, int i, String str, String str2) {
        switch (i) {
            case 1:
                e(singleDayRecord, str);
                break;
            case 2:
                singleDayRecord.saveBestRunDistance(str);
                break;
            case 3:
                singleDayRecord.saveBestRunPace(str);
                break;
            case 4:
                singleDayRecord.saveBestWalkDistance(str);
                break;
            case 5:
                singleDayRecord.saveBestCycleDistance(str);
                break;
            case 6:
                singleDayRecord.saveBestCycleSpeed(str);
                break;
            case 7:
                singleDayRecord.saveBestRun3KMPace(str);
                break;
            case 8:
                singleDayRecord.saveBestRun5KMPace(str);
                break;
            case 9:
                singleDayRecord.saveBestRun10KMPace(str);
                break;
            case 10:
                singleDayRecord.saveBestRunHMPace(str);
                break;
            case 11:
                singleDayRecord.saveBestRunFMPace(str);
                break;
            case 12:
                a(singleDayRecord, str, str2);
                break;
            case 13:
                e(singleDayRecord, str, str2);
                break;
            case 14:
                d(singleDayRecord, str, str2);
                break;
            default:
                a(singleDayRecord, i, str, str2);
                break;
        }
    }

    private void a(SingleDayRecord singleDayRecord, int i, String str, String str2) {
        switch (i) {
            case 15:
                singleDayRecord.saveBestRopeSingCount(str);
                break;
            case 16:
                singleDayRecord.saveBestRopeContinuousCount(str);
                break;
            case 17:
                singleDayRecord.saveBestRopeSpeed(str);
                break;
            case 18:
                singleDayRecord.saveBestRopeDuration(str);
                break;
            case 19:
                singleDayRecord.setNpeBestResult(str);
                break;
        }
    }

    private void d(SingleDayRecord singleDayRecord, String str, String str2) {
        singleDayRecord.setMatchSpeedDate(mef.e(str2));
        singleDayRecord.saveMatchSpeed((int) mef.d(str));
    }

    private void e(SingleDayRecord singleDayRecord, String str, String str2) {
        singleDayRecord.setDistanceDate(mef.e(str2));
        singleDayRecord.saveDistance(mef.d(str));
    }

    private void a(SingleDayRecord singleDayRecord, String str, String str2) {
        singleDayRecord.setStepsDate(mef.e(str2));
        singleDayRecord.setSteps((int) mef.d(str));
    }

    private void e(SingleDayRecord singleDayRecord, String str) {
        singleDayRecord.saveBestStepDay(str);
        mke h = mlg.h(str);
        if (h == null || Utils.o()) {
            return;
        }
        singleDayRecord.setSteps(h.a());
        singleDayRecord.setStepsDate(h.d());
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        SingleDayRecord singleDayRecord = mczVar instanceof SingleDayRecord ? (SingleDayRecord) mczVar : null;
        if (singleDayRecord == null) {
            return -1L;
        }
        if (d(singleDayRecord.getHuid()) != null) {
            return a(mczVar, r1);
        }
        long b = b(singleDayRecord, 12, singleDayRecord.getSteps(), singleDayRecord.getStepsDate());
        long b2 = b(singleDayRecord, 13, singleDayRecord.acquireDistance(), singleDayRecord.getDistanceDate());
        long b3 = b(singleDayRecord, 14, singleDayRecord.acquireMatchSpeed(), singleDayRecord.getMatchSpeedDate());
        long b4 = b(singleDayRecord, 1, singleDayRecord.acquireBestStepDay());
        long b5 = b(singleDayRecord, 2, singleDayRecord.acquireBestRunDistance());
        long b6 = b(singleDayRecord, 3, singleDayRecord.acquireBestRunPace());
        long b7 = b(singleDayRecord, 4, singleDayRecord.acquireBestWalkDistance());
        long b8 = b(singleDayRecord, 5, singleDayRecord.acquireBestCycleDistance());
        long b9 = b(singleDayRecord, 6, singleDayRecord.acquireBestCycleSpeed());
        long b10 = b(singleDayRecord, 7, singleDayRecord.acquireBestRun3KMPace());
        long b11 = b(singleDayRecord, 8, singleDayRecord.acquireBestRun5KMPace());
        long b12 = b(singleDayRecord, 9, singleDayRecord.acquireBestRun10KMPace());
        long b13 = b(singleDayRecord, 10, singleDayRecord.acquireBestRunHMPace());
        long b14 = b(singleDayRecord, 11, singleDayRecord.acquireBestRunFMPace());
        return (b - 1) + b2 + b3 + b4 + b5 + b6 + b7 + b8 + b9 + b10 + b11 + b12 + b13 + b14 + b(singleDayRecord, 15, singleDayRecord.acquireBestRopeSingCount()) + b(singleDayRecord, 16, singleDayRecord.acquireBestRopeContinuousCount()) + b(singleDayRecord, 17, singleDayRecord.acquireBestRopeSpeed()) + b(singleDayRecord, 18, singleDayRecord.acquireBestRopeDuration());
    }

    private long b(SingleDayRecord singleDayRecord, int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", singleDayRecord.getHuid());
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", str);
        contentValues.put("date", "");
        long insertStorageData = mea.b(this.b).insertStorageData("single_day_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "insertSingle result=", Long.valueOf(insertStorageData), " dataType ", Integer.valueOf(i), " value ", str);
        return insertStorageData;
    }

    private long b(SingleDayRecord singleDayRecord, int i, double d, long j) {
        if (c(singleDayRecord, j) == -1) {
            LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "insert Column check not pass");
            return -1L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", singleDayRecord.getHuid());
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", Double.valueOf(d));
        contentValues.put("date", Long.valueOf(j));
        long insertStorageData = mea.b(this.b).insertStorageData("single_day_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "insertSingle insertSingleRecordResult=", Long.valueOf(insertStorageData), " dataType ", Integer.valueOf(i), " value ", Double.valueOf(d));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        SingleDayRecord singleDayRecord = mczVar instanceof SingleDayRecord ? (SingleDayRecord) mczVar : null;
        if (singleDayRecord == null) {
            return -1;
        }
        String[] strArr = {mef.e(singleDayRecord.getHuid())};
        LogUtil.c("PLGACHIEVE_SingleDayRecordDBMgr", "delete selection=", "huid=?");
        int deleteStorageData = mea.b(this.b).deleteStorageData("single_day_record", 1, "huid=?", strArr);
        LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "delete deleteSingleRecordResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        mcz d;
        if (mczVar == null) {
            return -1;
        }
        SingleDayRecord singleDayRecord = mczVar instanceof SingleDayRecord ? (SingleDayRecord) mczVar : null;
        if (singleDayRecord == null || (d = d(singleDayRecord.getHuid())) == null) {
            return -1;
        }
        return a(mczVar, d);
    }

    private int a(SingleDayRecord singleDayRecord, int i, double d, long j) {
        if (c(singleDayRecord, j) == -1) {
            LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "update Column check not pass");
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", singleDayRecord.getHuid());
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", Double.valueOf(d));
        contentValues.put("date", Long.valueOf(j));
        int updateStorageData = mea.b(this.b).updateStorageData("single_day_record", 1, contentValues, "huid=? and dataType=?", new String[]{mef.e(singleDayRecord.getHuid()), mef.e(Integer.valueOf(i))});
        LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "updateSingle result=", Integer.valueOf(updateStorageData), " dataType ", Integer.valueOf(i), " value ", Double.valueOf(d), " date ", Long.valueOf(j));
        if (updateStorageData == 0) {
            c(singleDayRecord, i, d, j);
        }
        return updateStorageData;
    }

    private void c(SingleDayRecord singleDayRecord, int i, double d, long j) {
        if (c(singleDayRecord.getHuid(), i) == null) {
            LogUtil.h("PLGACHIEVE_SingleDayRecordDBMgr", "dealUpdateFailItem no record！dataType ", Integer.valueOf(i));
            b(singleDayRecord, i, d, j);
        }
    }

    private void a(SingleDayRecord singleDayRecord, int i, String str) {
        if (c(singleDayRecord.getHuid(), i) == null) {
            LogUtil.h("PLGACHIEVE_SingleDayRecordDBMgr", "dealUpdateFailItem no record！dataType ", Integer.valueOf(i));
            b(singleDayRecord, i, str);
        }
    }

    private int a(mcz mczVar, mcz mczVar2) {
        if (mczVar == null) {
            return -1;
        }
        SingleDayRecord singleDayRecord = mczVar instanceof SingleDayRecord ? (SingleDayRecord) mczVar : null;
        if (singleDayRecord == null) {
            return -1;
        }
        SingleDayRecord singleDayRecord2 = mczVar2 instanceof SingleDayRecord ? (SingleDayRecord) mczVar2 : null;
        if (singleDayRecord2 == null) {
            return -1;
        }
        LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "myAchieveDb oldSingleRecord ", singleDayRecord2.toString());
        return b(singleDayRecord, singleDayRecord2);
    }

    private int b(SingleDayRecord singleDayRecord, SingleDayRecord singleDayRecord2) {
        int a2 = a(singleDayRecord, singleDayRecord2, 12);
        int a3 = a(singleDayRecord, singleDayRecord2, 13);
        int a4 = a(singleDayRecord, singleDayRecord2, 14);
        int a5 = a(singleDayRecord, singleDayRecord2, 1);
        int a6 = a(singleDayRecord, singleDayRecord2, 19);
        int a7 = a(singleDayRecord, 2, singleDayRecord2.acquireBestRunDistance(), singleDayRecord.acquireBestRunDistance(), 3);
        int a8 = a(singleDayRecord, 3, singleDayRecord2.acquireBestRunPace(), singleDayRecord.acquireBestRunPace(), 4);
        int a9 = a(singleDayRecord, 4, singleDayRecord2.acquireBestWalkDistance(), singleDayRecord.acquireBestWalkDistance(), 1);
        int a10 = a(singleDayRecord, 5, singleDayRecord2.acquireBestCycleDistance(), singleDayRecord.acquireBestCycleDistance(), 10);
        int a11 = a(singleDayRecord, 6, singleDayRecord2.acquireBestCycleSpeed(), singleDayRecord.acquireBestCycleSpeed(), 11);
        int a12 = a(singleDayRecord, 7, singleDayRecord2.acquireBestRun3KMPace(), singleDayRecord.acquireBestRun3KMPace(), 5);
        int a13 = a(singleDayRecord, 8, singleDayRecord2.acquireBestRun5KMPace(), singleDayRecord.acquireBestRun5KMPace(), 6);
        int a14 = a(singleDayRecord, 9, singleDayRecord2.acquireBestRun10KMPace(), singleDayRecord.acquireBestRun10KMPace(), 7);
        int a15 = a(singleDayRecord, 10, singleDayRecord2.acquireBestRunHMPace(), singleDayRecord.acquireBestRunHMPace(), 8);
        int a16 = a(singleDayRecord, 11, singleDayRecord2.acquireBestRunFMPace(), singleDayRecord.acquireBestRunFMPace(), 9);
        int a17 = a(singleDayRecord, 15, singleDayRecord2.acquireBestRopeSingCount(), singleDayRecord.acquireBestRopeSingCount(), 12);
        int a18 = a(singleDayRecord, 16, singleDayRecord2.acquireBestRopeContinuousCount(), singleDayRecord.acquireBestRopeContinuousCount(), 13);
        return (a2 - 1) + a3 + a4 + a5 + a6 + a7 + a8 + a9 + a10 + a11 + a12 + a13 + a14 + a15 + a16 + a17 + a18 + a(singleDayRecord, 17, singleDayRecord2.acquireBestRopeSpeed(), singleDayRecord.acquireBestRopeSpeed(), 14) + a(singleDayRecord, 18, singleDayRecord2.acquireBestRopeDuration(), singleDayRecord.acquireBestRopeDuration(), 15);
    }

    private int a(SingleDayRecord singleDayRecord, SingleDayRecord singleDayRecord2, int i) {
        if (i == 1) {
            if (singleDayRecord.acquireBestStepDay() == null || singleDayRecord.acquireBestStepDay().equals(singleDayRecord2.acquireBestStepDay())) {
                return 0;
            }
            return d(singleDayRecord, 1, singleDayRecord.acquireBestStepDay());
        }
        if (i != 19) {
            switch (i) {
                case 12:
                    if (singleDayRecord.getSteps() == singleDayRecord2.getSteps()) {
                        return 0;
                    }
                    return a(singleDayRecord, i, singleDayRecord.getSteps(), singleDayRecord.getStepsDate());
                case 13:
                    if (mlg.d(singleDayRecord.acquireDistance(), singleDayRecord2.acquireDistance()) == 0 && singleDayRecord.getDistanceDate() == singleDayRecord2.getDistanceDate()) {
                        return 0;
                    }
                    return a(singleDayRecord, i, singleDayRecord.acquireDistance(), singleDayRecord.getDistanceDate());
                case 14:
                    if (singleDayRecord.acquireMatchSpeed() == singleDayRecord2.acquireMatchSpeed() && singleDayRecord.getMatchSpeedDate() == singleDayRecord2.getMatchSpeedDate()) {
                        return 0;
                    }
                    return a(singleDayRecord, i, singleDayRecord.acquireMatchSpeed(), singleDayRecord.getMatchSpeedDate());
                default:
                    LogUtil.h("PLGACHIEVE_SingleDayRecordDBMgr", "updateRecord no branch.");
                    return 0;
            }
        }
        if (singleDayRecord.getNpeBestResult() == null || singleDayRecord.getNpeBestResult().equals(singleDayRecord2.getNpeBestResult())) {
            return 0;
        }
        return d(singleDayRecord, i, singleDayRecord.getNpeBestResult());
    }

    private int d(SingleDayRecord singleDayRecord, int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", singleDayRecord.getHuid());
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", str);
        contentValues.put("date", "");
        int updateStorageData = mea.b(this.b).updateStorageData("single_day_record", 1, contentValues, "huid=? and dataType=?", new String[]{mef.e(singleDayRecord.getHuid()), mef.e(Integer.valueOf(i))});
        LogUtil.a("PLGACHIEVE_SingleDayRecordDBMgr", "updateSingle result=", Integer.valueOf(updateStorageData), " dataType ", Integer.valueOf(i), " value ", str);
        if (updateStorageData == 0) {
            a(singleDayRecord, i, str);
        }
        return updateStorageData;
    }

    private int c(SingleDayRecord singleDayRecord, long j) {
        return (TextUtils.isEmpty(singleDayRecord.getHuid()) || singleDayRecord.acquireDataType() == -1 || j == -1 || singleDayRecord.getSteps() == -1 || singleDayRecord.acquireDistance() == -1.0d || singleDayRecord.acquireMatchSpeed() == -1) ? -1 : 0;
    }

    private int a(SingleDayRecord singleDayRecord, int i, String str, String str2, int i2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return 0;
        }
        if (!TextUtils.isEmpty(str) && str.equals(str2)) {
            return 0;
        }
        if (singleDayRecord.isDelete()) {
            return d(singleDayRecord, i, str2);
        }
        return d(singleDayRecord, i, mef.d(str, str2, i2));
    }
}
