package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import defpackage.mlg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class TotalRecordDBMgr implements IAchieveDBMgr {
    private Context c;

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return null;
    }

    public TotalRecordDBMgr(Context context) {
        this.c = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return a(map.get("huid"));
    }

    private mcz a(String str) {
        TotalRecord totalRecord = null;
        try {
            Cursor queryStorageData = mea.b(this.c).queryStorageData("total_record", 1, "huid='" + str + "'");
            if (queryStorageData != null) {
                while (queryStorageData.moveToNext()) {
                    try {
                        if (totalRecord == null) {
                            totalRecord = new TotalRecord();
                        }
                        totalRecord.setHuid(queryStorageData.getString(queryStorageData.getColumnIndex("huid")));
                        totalRecord.setStartDate(mef.e(queryStorageData.getString(queryStorageData.getColumnIndex(Constants.START_DATE))));
                        totalRecord.setEndDate(mef.e(queryStorageData.getString(queryStorageData.getColumnIndex("endDate"))));
                        d(totalRecord, queryStorageData.getInt(queryStorageData.getColumnIndex("dataType")), queryStorageData.getString(queryStorageData.getColumnIndex("value")));
                        totalRecord.setStepsRanking(mef.d(queryStorageData.getString(queryStorageData.getColumnIndex("stepsRanking"))));
                    } finally {
                    }
                }
                queryStorageData.close();
            }
            if (queryStorageData != null) {
                queryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_TotalRecordDBMgr", e.getMessage());
        }
        return totalRecord;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
    
        r1 = new com.huawei.pluginachievement.manager.model.TotalRecord();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
    
        r1.setHuid(r5.getString(r5.getColumnIndex("huid")));
        r1.setStartDate(defpackage.mef.e(r5.getString(r5.getColumnIndex(com.huawei.operation.utils.Constants.START_DATE))));
        r1.setEndDate(defpackage.mef.e(r5.getString(r5.getColumnIndex("endDate"))));
        d(r1, r6, r5.getString(r5.getColumnIndex("value")));
        r1.setStepsRanking(defpackage.mef.d(r5.getString(r5.getColumnIndex("stepsRanking"))));
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0087, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0089, code lost:
    
        r6 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008a, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008d, code lost:
    
        if (r5 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0097, code lost:
    
        throw r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008f, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0093, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0094, code lost:
    
        r6.addSuppressed(r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private defpackage.mcz a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "huid='"
            r0.<init>(r1)
            r0.append(r5)
            java.lang.String r5 = "'"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r0 = 0
            android.content.Context r1 = r4.c     // Catch: android.database.SQLException -> L9e
            mea r1 = defpackage.mea.b(r1)     // Catch: android.database.SQLException -> L9e
            java.lang.String r2 = "total_record"
            r3 = 1
            android.database.Cursor r5 = r1.queryStorageData(r2, r3, r5)     // Catch: android.database.SQLException -> L9e
            if (r5 == 0) goto L98
        L23:
            boolean r1 = r5.moveToNext()     // Catch: java.lang.Throwable -> L8c
            if (r1 == 0) goto L98
            java.lang.String r1 = "dataType"
            int r1 = r5.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L8c
            int r1 = r5.getInt(r1)     // Catch: java.lang.Throwable -> L8c
            if (r6 != r1) goto L23
            com.huawei.pluginachievement.manager.model.TotalRecord r1 = new com.huawei.pluginachievement.manager.model.TotalRecord     // Catch: java.lang.Throwable -> L8c
            r1.<init>()     // Catch: java.lang.Throwable -> L8c
            java.lang.String r0 = "huid"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L89
            java.lang.String r0 = r5.getString(r0)     // Catch: java.lang.Throwable -> L89
            r1.setHuid(r0)     // Catch: java.lang.Throwable -> L89
            java.lang.String r0 = "startDate"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L89
            java.lang.String r0 = r5.getString(r0)     // Catch: java.lang.Throwable -> L89
            long r2 = defpackage.mef.e(r0)     // Catch: java.lang.Throwable -> L89
            r1.setStartDate(r2)     // Catch: java.lang.Throwable -> L89
            java.lang.String r0 = "endDate"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L89
            java.lang.String r0 = r5.getString(r0)     // Catch: java.lang.Throwable -> L89
            long r2 = defpackage.mef.e(r0)     // Catch: java.lang.Throwable -> L89
            r1.setEndDate(r2)     // Catch: java.lang.Throwable -> L89
            java.lang.String r0 = "value"
            int r0 = r5.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L89
            java.lang.String r0 = r5.getString(r0)     // Catch: java.lang.Throwable -> L89
            r4.d(r1, r6, r0)     // Catch: java.lang.Throwable -> L89
            java.lang.String r6 = "stepsRanking"
            int r6 = r5.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L89
            java.lang.String r6 = r5.getString(r6)     // Catch: java.lang.Throwable -> L89
            double r2 = defpackage.mef.d(r6)     // Catch: java.lang.Throwable -> L89
            r1.setStepsRanking(r2)     // Catch: java.lang.Throwable -> L89
            r0 = r1
            goto L98
        L89:
            r6 = move-exception
            r0 = r1
            goto L8d
        L8c:
            r6 = move-exception
        L8d:
            if (r5 == 0) goto L97
            r5.close()     // Catch: java.lang.Throwable -> L93
            goto L97
        L93:
            r5 = move-exception
            r6.addSuppressed(r5)     // Catch: android.database.SQLException -> L9e
        L97:
            throw r6     // Catch: android.database.SQLException -> L9e
        L98:
            if (r5 == 0) goto Lac
            r5.close()     // Catch: android.database.SQLException -> L9e
            goto Lac
        L9e:
            r5 = move-exception
            java.lang.String r5 = r5.getMessage()
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r6 = "PLGACHIEVE_TotalRecordDBMgr"
            com.huawei.hwlogsmodel.LogUtil.h(r6, r5)
        Lac:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginachievement.manager.db.TotalRecordDBMgr.a(java.lang.String, int):mcz");
    }

    private void d(TotalRecord totalRecord, int i, String str) {
        switch (i) {
            case 1:
                totalRecord.setDays((int) mef.d(str));
                break;
            case 2:
                totalRecord.setDistance(mef.d(str));
                break;
            case 3:
                totalRecord.setSteps(mef.d(str));
                break;
            case 4:
                totalRecord.saveCalorie(mef.d(str));
                break;
            case 5:
                totalRecord.saveAccumulatedRunDistance(str);
                break;
            case 6:
                totalRecord.saveAccumulatedCycleDistance(str);
                break;
            case 7:
                totalRecord.saveAccumulatedWalkDistance(str);
                break;
            case 8:
                totalRecord.saveAccumulatedHealthTime(str);
                break;
        }
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        TotalRecord totalRecord = mczVar instanceof TotalRecord ? (TotalRecord) mczVar : null;
        if (totalRecord == null) {
            return -1L;
        }
        if (a(totalRecord.getHuid()) != null) {
            return d(mczVar, r5);
        }
        if (e(totalRecord) == -1) {
            LogUtil.a("PLGACHIEVE_TotalRecordDBMgr", "insert Column check not pass");
            return -1L;
        }
        long c = c(totalRecord, 1, totalRecord.getDays());
        long c2 = c(totalRecord, 2, totalRecord.getDistance());
        long c3 = c(totalRecord, 3, totalRecord.getSteps());
        long c4 = c(totalRecord, 4, totalRecord.acquireCalorie());
        return Math.max((c - 1) + c2 + c3 + c4 + a(totalRecord, 5, totalRecord.acquireAccumulatedRunDistance()) + a(totalRecord, 6, totalRecord.acquireAccumulatedCycleDistance()) + a(totalRecord, 7, totalRecord.acquireAccumulatedWalkDistance()) + a(totalRecord, 8, totalRecord.acquireAccumulatedHealthTime()), -1L);
    }

    private long c(TotalRecord totalRecord, int i, double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", totalRecord.getHuid());
        contentValues.put(Constants.START_DATE, Long.valueOf(totalRecord.getStartDate()));
        contentValues.put("endDate", Long.valueOf(totalRecord.getEndDate()));
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", Double.valueOf(d));
        contentValues.put("stepsRanking", Double.valueOf(totalRecord.getStepsRanking()));
        long insertStorageData = mea.b(this.c).insertStorageData("total_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_TotalRecordDBMgr", "insert result=", Long.valueOf(insertStorageData), " dataType ", Integer.valueOf(i), " value ", Double.valueOf(d));
        return insertStorageData;
    }

    private long a(TotalRecord totalRecord, int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", totalRecord.getHuid());
        contentValues.put(Constants.START_DATE, Long.valueOf(totalRecord.getStartDate()));
        contentValues.put("endDate", Long.valueOf(totalRecord.getEndDate()));
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", str);
        contentValues.put("stepsRanking", Double.valueOf(totalRecord.getStepsRanking()));
        long insertStorageData = mea.b(this.c).insertStorageData("total_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_TotalRecordDBMgr", "insert insertTotalRecordResult=", Long.valueOf(insertStorageData), " dataType ", Integer.valueOf(i), " value ", str);
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        TotalRecord totalRecord = mczVar instanceof TotalRecord ? (TotalRecord) mczVar : null;
        if (totalRecord == null) {
            return -1;
        }
        String[] strArr = {mef.e(totalRecord.getHuid())};
        LogUtil.c("PLGACHIEVE_TotalRecordDBMgr", "delete selection=", "huid =?");
        int deleteStorageData = mea.b(this.c).deleteStorageData("total_record", 1, "huid =?", strArr);
        LogUtil.a("PLGACHIEVE_TotalRecordDBMgr", "delete deleteTotalRecordResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        TotalRecord totalRecord = mczVar instanceof TotalRecord ? (TotalRecord) mczVar : null;
        if (totalRecord == null) {
            return -1;
        }
        if (e(totalRecord) == -1) {
            LogUtil.h("PLGACHIEVE_TotalRecordDBMgr", "update Column check not pass");
            return -1;
        }
        mcz a2 = a(totalRecord.getHuid());
        if (a2 != null) {
            return d(mczVar, a2);
        }
        return -1;
    }

    public int d(mcz mczVar, mcz mczVar2) {
        if (mczVar == null) {
            return -1;
        }
        TotalRecord totalRecord = mczVar instanceof TotalRecord ? (TotalRecord) mczVar : null;
        if (totalRecord == null) {
            return -1;
        }
        if (e(totalRecord) == -1) {
            LogUtil.h("PLGACHIEVE_TotalRecordDBMgr", "update Column check not pass");
            return -1;
        }
        TotalRecord totalRecord2 = mczVar2 instanceof TotalRecord ? (TotalRecord) mczVar2 : null;
        if (totalRecord2 == null) {
            return -1;
        }
        LogUtil.a("PLGACHIEVE_TotalRecordDBMgr", "myAchieveDb oldTotalRecord ", totalRecord2.toString());
        TotalRecord totalRecord3 = totalRecord;
        TotalRecord totalRecord4 = totalRecord2;
        int a2 = a(totalRecord3, totalRecord4, 1, totalRecord.getDays(), totalRecord2.getDays());
        int a3 = a(totalRecord3, totalRecord4, 2, totalRecord.getDistance(), totalRecord2.getDistance());
        int a4 = a(totalRecord3, totalRecord4, 3, totalRecord.getSteps(), totalRecord2.getSteps());
        int a5 = a(totalRecord3, totalRecord4, 4, totalRecord.acquireCalorie(), totalRecord2.acquireCalorie());
        return Math.max((a2 - 1) + a3 + a4 + a5 + e(totalRecord3, totalRecord4, 5, totalRecord.acquireAccumulatedRunDistance(), totalRecord2.acquireAccumulatedRunDistance()) + e(totalRecord3, totalRecord4, 6, totalRecord.acquireAccumulatedCycleDistance(), totalRecord2.acquireAccumulatedCycleDistance()) + e(totalRecord3, totalRecord4, 7, totalRecord.acquireAccumulatedWalkDistance(), totalRecord2.acquireAccumulatedWalkDistance()) + e(totalRecord3, totalRecord4, 8, totalRecord.acquireAccumulatedHealthTime(), totalRecord2.acquireAccumulatedHealthTime()), -1);
    }

    private int a(TotalRecord totalRecord, TotalRecord totalRecord2, int i, double d, double d2) {
        boolean z = BigDecimal.valueOf(d).equals(BigDecimal.valueOf(d2)) && totalRecord.getStartDate() == totalRecord2.getStartDate();
        if (d < 0.0d || z) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", totalRecord.getHuid());
        contentValues.put(Constants.START_DATE, Long.valueOf(totalRecord.getStartDate()));
        contentValues.put("endDate", Long.valueOf(totalRecord.getEndDate()));
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", Double.valueOf(d));
        contentValues.put("stepsRanking", Double.valueOf(totalRecord.getStepsRanking()));
        int updateStorageData = mea.b(this.c).updateStorageData("total_record", 1, contentValues, "huid=? and dataType=?", new String[]{mef.e(totalRecord.getHuid()), mef.e(Integer.valueOf(i))});
        LogUtil.a("PLGACHIEVE_TotalRecordDBMgr", "update result=", Integer.valueOf(updateStorageData), " dataType ", Integer.valueOf(i), " value ", Double.valueOf(d));
        if (updateStorageData == 0) {
            e(totalRecord, i, d);
        }
        return updateStorageData;
    }

    private void e(TotalRecord totalRecord, int i, double d) {
        if (a(totalRecord.getHuid(), i) == null) {
            LogUtil.h("PLGACHIEVE_TotalRecordDBMgr", "dealUpdateFailItem no record！");
            c(totalRecord, i, d);
        }
    }

    private int e(TotalRecord totalRecord, TotalRecord totalRecord2, int i, String str, String str2) {
        String str3 = str == null ? "" : str;
        if (str3.equals(str2) && totalRecord.getStartDate() == totalRecord2.getStartDate() && mlg.e(totalRecord.getStepsRanking(), totalRecord2.getStepsRanking()) == 0) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", totalRecord.getHuid());
        contentValues.put(Constants.START_DATE, Long.valueOf(totalRecord.getStartDate()));
        contentValues.put("endDate", Long.valueOf(totalRecord.getEndDate()));
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", str3);
        contentValues.put("stepsRanking", Double.valueOf(totalRecord.getStepsRanking()));
        int updateStorageData = mea.b(this.c).updateStorageData("total_record", 1, contentValues, "huid=? and dataType=?", new String[]{mef.e(totalRecord.getHuid()), mef.e(Integer.valueOf(i))});
        LogUtil.a("PLGACHIEVE_TotalRecordDBMgr", "update result=", Integer.valueOf(updateStorageData), " dataType ", Integer.valueOf(i), " value ", str3);
        return updateStorageData;
    }

    private int e(TotalRecord totalRecord) {
        return (TextUtils.isEmpty(totalRecord.getHuid()) || totalRecord.acquireDataType() == -1 || totalRecord.getStartDate() == -1 || totalRecord.getEndDate() == -1 || totalRecord.getDistance() == -1.0d || totalRecord.getSteps() == -1.0d || totalRecord.getDays() == -1 || totalRecord.acquireCalorie() == -1.0d) ? -1 : 0;
    }
}
