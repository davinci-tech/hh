package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.RecentWeekRecord;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class RecentWeekRecordDBMgr implements IAchieveDBMgr {
    private Context b;

    public RecentWeekRecordDBMgr(Context context) {
        this.b = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return b(map.get("huid"), map.get("reportNo"));
    }

    private mcz b(String str, String str2) {
        RecentWeekRecord recentWeekRecord = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_RecentWeekRecordDBMgr", "RecentWeekRecordDBMgr, query ,id is null!return null.");
            return null;
        }
        String tableFullName = mea.b(this.b).getTableFullName("recent");
        String str3 = "select *  from " + tableFullName + " where huid = ? and recentType = 1 and reportNo = ? ";
        String[] strArr = {mef.e(str), mef.e(str2)};
        if ("0".equals(str2) || TextUtils.isEmpty(str2)) {
            strArr = new String[]{mef.e(str)};
            str3 = "select *  from " + tableFullName + " where huid = ? and recentType = 1 and reportNo = (select reportNo from " + tableFullName + " where recentType = 1 order by  reportNo desc limit 1)";
        }
        LogUtil.c("PLGACHIEVE_RecentWeekRecordDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.b).rawQueryStorageData(1, str3, strArr);
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (recentWeekRecord == null) {
                            recentWeekRecord = new RecentWeekRecord();
                        }
                        recentWeekRecord.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        String string = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.FIRST_DATE));
                        String string2 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("endDate"));
                        recentWeekRecord.saveFirstDate(mef.e(string));
                        recentWeekRecord.saveEndDate(mef.e(string2));
                        cgh_(rawQueryStorageData, recentWeekRecord, rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("dataType")), mef.d(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("value"))));
                        String string3 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("stepsRanking"));
                        String string4 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("distanceRanking"));
                        recentWeekRecord.setStepsRanking(mef.d(string3));
                        recentWeekRecord.setDistanceRanking(mef.d(string4));
                        recentWeekRecord.setMinReportNo(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex(ParsedFieldTag.MIN_REPORT_NO)));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_RecentWeekRecordDBMgr", e.getMessage());
        }
        return recentWeekRecord;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return Collections.emptyList();
    }

    private void cgh_(Cursor cursor, RecentWeekRecord recentWeekRecord, int i, double d) {
        if (i == 1) {
            recentWeekRecord.setSteps(d);
        } else if (i == 2) {
            recentWeekRecord.setDistance(d);
        } else {
            LogUtil.h("PLGACHIEVE_RecentWeekRecordDBMgr", "setWeekRecord type error:", Integer.valueOf(i));
        }
        recentWeekRecord.setReportNo(cursor.getInt(cursor.getColumnIndex("reportNo")));
        recentWeekRecord.setKakaNum(cursor.getInt(cursor.getColumnIndex("kakaNum")));
        recentWeekRecord.setPrice(cursor.getInt(cursor.getColumnIndex(ParsedFieldTag.PRICE)));
        recentWeekRecord.setUserMedals(cursor.getString(cursor.getColumnIndex("medal_id")));
        recentWeekRecord.setComments1(cursor.getString(cursor.getColumnIndex("comments1_id")));
        recentWeekRecord.setComments2(cursor.getString(cursor.getColumnIndex("commentS2_id")));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        RecentWeekRecord recentWeekRecord = mczVar instanceof RecentWeekRecord ? (RecentWeekRecord) mczVar : null;
        if (recentWeekRecord == null) {
            return -1L;
        }
        if (b(recentWeekRecord.getHuid(), String.valueOf(recentWeekRecord.acquireReportNo())) != null) {
            return update(mczVar);
        }
        if (c(recentWeekRecord) == -1) {
            LogUtil.a("PLGACHIEVE_RecentWeekRecordDBMgr", "insert Column check not pass");
            return -1L;
        }
        return (a(recentWeekRecord, 1, recentWeekRecord.acquireSteps()) - 1) + a(recentWeekRecord, 2, recentWeekRecord.acquireDistance());
    }

    private long a(RecentWeekRecord recentWeekRecord, int i, double d) {
        ContentValues contentValues = new ContentValues();
        cgi_(contentValues, recentWeekRecord);
        contentValues.put("value", Double.valueOf(d));
        contentValues.put("dataType", Integer.valueOf(i));
        long insertStorageData = mea.b(this.b).insertStorageData("recent", 1, contentValues);
        LogUtil.a("PLGACHIEVE_RecentWeekRecordDBMgr", "insert insertWeekResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        RecentWeekRecord recentWeekRecord = mczVar instanceof RecentWeekRecord ? (RecentWeekRecord) mczVar : null;
        if (recentWeekRecord == null) {
            return -1;
        }
        String[] strArr = {mef.e(recentWeekRecord.getHuid())};
        LogUtil.c("PLGACHIEVE_RecentWeekRecordDBMgr", "delete selection=", "huid=? and recentType='1'");
        int deleteStorageData = mea.b(this.b).deleteStorageData("recent", 1, "huid=? and recentType='1'", strArr);
        LogUtil.a("PLGACHIEVE_RecentWeekRecordDBMgr", "delete deleteWeekResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        RecentWeekRecord recentWeekRecord = mczVar instanceof RecentWeekRecord ? (RecentWeekRecord) mczVar : null;
        if (recentWeekRecord == null) {
            return -1;
        }
        if (c(recentWeekRecord) == -1) {
            LogUtil.a("PLGACHIEVE_RecentWeekRecordDBMgr", "update Column check not pass");
            return -1;
        }
        return (c(recentWeekRecord, 1, recentWeekRecord.acquireSteps()) - 1) + c(recentWeekRecord, 2, recentWeekRecord.acquireDistance());
    }

    private int c(RecentWeekRecord recentWeekRecord, int i, double d) {
        ContentValues contentValues = new ContentValues();
        cgi_(contentValues, recentWeekRecord);
        contentValues.put("value", Double.valueOf(d));
        contentValues.put("dataType", Integer.valueOf(i));
        String[] strArr = {mef.e(recentWeekRecord.getHuid()), mef.e(Integer.valueOf(i)), mef.e(Double.valueOf(d))};
        LogUtil.c("PLGACHIEVE_RecentWeekRecordDBMgr", "update selection=", "huid=? and recentType='1' and dataType=? and reportNo=?");
        int updateStorageData = mea.b(this.b).updateStorageData("recent", 1, contentValues, "huid=? and recentType='1' and dataType=? and reportNo=?", strArr);
        LogUtil.a("PLGACHIEVE_RecentWeekRecordDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }

    private int c(RecentWeekRecord recentWeekRecord) {
        return (TextUtils.isEmpty(recentWeekRecord.getHuid()) || recentWeekRecord.acquireDataType() == -1 || recentWeekRecord.acquireSteps() == -1.0d || recentWeekRecord.acquireDistance() == -1.0d || recentWeekRecord.acquireReportNo() == -1 || recentWeekRecord.acquireKakaNum() == -1 || recentWeekRecord.acquirePrice() == -1 || recentWeekRecord.acquireFirstDate() == -1 || recentWeekRecord.acquireEndDate() == -1) ? -1 : 0;
    }

    private void cgi_(ContentValues contentValues, RecentWeekRecord recentWeekRecord) {
        if (recentWeekRecord == null) {
            return;
        }
        contentValues.put("huid", recentWeekRecord.getHuid());
        contentValues.put("recentType", (Integer) 1);
        contentValues.put(ParsedFieldTag.FIRST_DATE, Long.valueOf(recentWeekRecord.acquireFirstDate()));
        contentValues.put("endDate", Long.valueOf(recentWeekRecord.acquireEndDate()));
        contentValues.put("reportNo", Integer.valueOf(recentWeekRecord.acquireReportNo()));
        contentValues.put("kakaNum", Integer.valueOf(recentWeekRecord.acquireKakaNum()));
        contentValues.put(ParsedFieldTag.PRICE, Integer.valueOf(recentWeekRecord.acquirePrice()));
        contentValues.put("medal_id", recentWeekRecord.acquireUserMedals());
        contentValues.put("comments1_id", recentWeekRecord.acquireComments1());
        contentValues.put("commentS2_id", recentWeekRecord.getComments2());
        contentValues.put("stepsRanking", Double.valueOf(recentWeekRecord.acquireStepsRanking()));
        contentValues.put("distanceRanking", Double.valueOf(recentWeekRecord.acquireDistanceRanking()));
        contentValues.put(ParsedFieldTag.MIN_REPORT_NO, Integer.valueOf(recentWeekRecord.acquireMinReportNo()));
    }
}
