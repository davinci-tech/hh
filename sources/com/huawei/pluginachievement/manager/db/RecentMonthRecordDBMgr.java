package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.RecentMonthRecord;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class RecentMonthRecordDBMgr implements IAchieveDBMgr {
    private Context e;

    public RecentMonthRecordDBMgr(Context context) {
        this.e = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return Collections.emptyList();
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return b(map.get("huid"), map.get("reportNo"));
    }

    private mcz b(String str, String str2) {
        RecentMonthRecord recentMonthRecord = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_RecentMonthRecordDBMgr", "RecentMonthRecordDBMgr, query ,id is null!return null.");
            return null;
        }
        String tableFullName = mea.b(this.e).getTableFullName("recent");
        String str3 = "select *  from " + tableFullName + " where huid = ? and recentType = 2 and reportNo = ? ";
        String[] strArr = {mef.e(str), mef.e(str2)};
        if ("0".equals(str2) || TextUtils.isEmpty(str2)) {
            strArr = new String[]{mef.e(str)};
            str3 = "select *  from " + tableFullName + " where huid = ? and recentType = 2 and reportNo = (select reportNo from " + tableFullName + " where recentType = 2 order by  reportNo desc limit 1)";
        }
        LogUtil.c("PLGACHIEVE_RecentMonthRecordDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.e).rawQueryStorageData(1, str3, strArr);
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (recentMonthRecord == null) {
                            recentMonthRecord = new RecentMonthRecord();
                        }
                        recentMonthRecord.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        String string = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.FIRST_DATE));
                        String string2 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("endDate"));
                        recentMonthRecord.saveFirstDate(mef.e(string));
                        recentMonthRecord.saveEndDate(mef.e(string2));
                        cgf_(rawQueryStorageData, recentMonthRecord, rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("dataType")), mef.d(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("value"))));
                        String string3 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("stepsRanking"));
                        String string4 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("distanceRanking"));
                        recentMonthRecord.setStepsRanking(mef.d(string3));
                        recentMonthRecord.setDistanceRanking(mef.d(string4));
                        recentMonthRecord.setMinReportNo(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex(ParsedFieldTag.MIN_REPORT_NO)));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_RecentMonthRecordDBMgr", e.getMessage());
        }
        return recentMonthRecord;
    }

    private void cgf_(Cursor cursor, RecentMonthRecord recentMonthRecord, int i, double d) {
        if (i == 1) {
            recentMonthRecord.setSteps(d);
        } else if (i == 2) {
            recentMonthRecord.setDistance(d);
        } else {
            LogUtil.h("PLGACHIEVE_RecentMonthRecordDBMgr", "setMonthRecord type error:", Integer.valueOf(i));
        }
        recentMonthRecord.setReportNo(cursor.getInt(cursor.getColumnIndex("reportNo")));
        recentMonthRecord.setKakaNum(cursor.getInt(cursor.getColumnIndex("kakaNum")));
        recentMonthRecord.setPrice(cursor.getInt(cursor.getColumnIndex(ParsedFieldTag.PRICE)));
        recentMonthRecord.setMedals(cursor.getString(cursor.getColumnIndex("medal_id")));
        recentMonthRecord.setComments1(cursor.getString(cursor.getColumnIndex("comments1_id")));
        recentMonthRecord.setComments2(cursor.getString(cursor.getColumnIndex("commentS2_id")));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        RecentMonthRecord recentMonthRecord = mczVar instanceof RecentMonthRecord ? (RecentMonthRecord) mczVar : null;
        if (recentMonthRecord == null) {
            return -1L;
        }
        if (b(recentMonthRecord.getHuid(), String.valueOf(recentMonthRecord.acquireReportNo())) != null) {
            return update(mczVar);
        }
        if (a(recentMonthRecord) == -1) {
            LogUtil.a("PLGACHIEVE_RecentMonthRecordDBMgr", "insert Column check not pass");
            return -1L;
        }
        return (b(recentMonthRecord, 1, recentMonthRecord.acquireSteps()) - 1) + b(recentMonthRecord, 2, recentMonthRecord.acquireDistance());
    }

    private long b(RecentMonthRecord recentMonthRecord, int i, double d) {
        ContentValues contentValues = new ContentValues();
        cgg_(contentValues, recentMonthRecord);
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", Double.valueOf(d));
        long insertStorageData = mea.b(this.e).insertStorageData("recent", 1, contentValues);
        LogUtil.a("PLGACHIEVE_RecentMonthRecordDBMgr", "insert insertMonthRecordResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        RecentMonthRecord recentMonthRecord = mczVar instanceof RecentMonthRecord ? (RecentMonthRecord) mczVar : null;
        if (recentMonthRecord == null) {
            return -1;
        }
        String[] strArr = {mef.e(recentMonthRecord.getHuid())};
        LogUtil.c("PLGACHIEVE_RecentMonthRecordDBMgr", "delete selection=", "huid=? and recentType='2'");
        int deleteStorageData = mea.b(this.e).deleteStorageData("recent", 1, "huid=? and recentType='2'", strArr);
        LogUtil.a("PLGACHIEVE_RecentMonthRecordDBMgr", "delete deleteMonthRecordResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        RecentMonthRecord recentMonthRecord = mczVar instanceof RecentMonthRecord ? (RecentMonthRecord) mczVar : null;
        if (recentMonthRecord == null) {
            return -1;
        }
        if (a(recentMonthRecord) == -1) {
            LogUtil.a("PLGACHIEVE_RecentMonthRecordDBMgr", "update Column check not pass");
            return -1;
        }
        return (e(recentMonthRecord, 1, recentMonthRecord.acquireSteps()) - 1) + e(recentMonthRecord, 2, recentMonthRecord.acquireDistance());
    }

    private int e(RecentMonthRecord recentMonthRecord, int i, double d) {
        ContentValues contentValues = new ContentValues();
        cgg_(contentValues, recentMonthRecord);
        contentValues.put("dataType", Integer.valueOf(i));
        contentValues.put("value", Double.valueOf(d));
        String[] strArr = {mef.e(recentMonthRecord.getHuid()), mef.e(Integer.valueOf(i)), mef.e(Double.valueOf(d))};
        LogUtil.c("PLGACHIEVE_RecentMonthRecordDBMgr", "update selection=", "huid=? and recentType='2' and dataType=? and reportNo=?");
        int updateStorageData = mea.b(this.e).updateStorageData("recent", 1, contentValues, "huid=? and recentType='2' and dataType=? and reportNo=?", strArr);
        LogUtil.a("PLGACHIEVE_RecentMonthRecordDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }

    private int a(RecentMonthRecord recentMonthRecord) {
        return (TextUtils.isEmpty(recentMonthRecord.getHuid()) || recentMonthRecord.acquireDataType() == -1 || recentMonthRecord.acquireSteps() == -1.0d || recentMonthRecord.acquireDistance() == -1.0d || recentMonthRecord.acquireReportNo() == -1 || recentMonthRecord.getKakaNum() == -1 || recentMonthRecord.acquirePrice() == -1 || recentMonthRecord.acquireFirstDate() == -1 || recentMonthRecord.acquireEndDate() == -1) ? -1 : 0;
    }

    private void cgg_(ContentValues contentValues, RecentMonthRecord recentMonthRecord) {
        if (recentMonthRecord == null) {
            return;
        }
        contentValues.put("huid", recentMonthRecord.getHuid());
        contentValues.put("recentType", (Integer) 2);
        contentValues.put(ParsedFieldTag.FIRST_DATE, Long.valueOf(recentMonthRecord.acquireFirstDate()));
        contentValues.put("endDate", Long.valueOf(recentMonthRecord.acquireEndDate()));
        contentValues.put("reportNo", Integer.valueOf(recentMonthRecord.acquireReportNo()));
        contentValues.put("kakaNum", Integer.valueOf(recentMonthRecord.getKakaNum()));
        contentValues.put(ParsedFieldTag.PRICE, Integer.valueOf(recentMonthRecord.acquirePrice()));
        contentValues.put("medal_id", recentMonthRecord.acquireMedals());
        contentValues.put("comments1_id", recentMonthRecord.getComments1());
        contentValues.put("commentS2_id", recentMonthRecord.getComments2());
        contentValues.put("stepsRanking", Double.valueOf(recentMonthRecord.acquireStepsRanking()));
        contentValues.put("distanceRanking", Double.valueOf(recentMonthRecord.acquireDistanceRanking()));
        contentValues.put(ParsedFieldTag.MIN_REPORT_NO, Integer.valueOf(recentMonthRecord.acquireMinReportNo()));
    }
}
