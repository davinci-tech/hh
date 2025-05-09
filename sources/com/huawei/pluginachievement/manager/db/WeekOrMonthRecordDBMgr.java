package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.WeekAndMonthRecord;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class WeekOrMonthRecordDBMgr implements IAchieveDBMgr {

    /* renamed from: a, reason: collision with root package name */
    private Context f8371a;

    public WeekOrMonthRecordDBMgr(Context context) {
        this.f8371a = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        return c(map.get("startTimestamp"), map.get("huid"), map.get("recentType"));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return Collections.emptyList();
        }
        return e(map.get("startTimestamp"), map.get("endTimeStamp"), map.get("huid"), map.get("recentType"), map.get("dataSource"));
    }

    private mcz c(String str, String str2, String str3) {
        WeekAndMonthRecord weekAndMonthRecord;
        WeekAndMonthRecord weekAndMonthRecord2 = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            LogUtil.d("PLGACHIEVE_WeekOrMonthRecordDBMgr", "WeekOrMonthRecordDBMgr, query ,id is null!return");
            return null;
        }
        String str4 = "select *  from " + mea.b(this.f8371a).getTableFullName("week_and_month_record") + " where startTimestamp =? and huid =? and recentType=?";
        LogUtil.b("PLGACHIEVE_WeekOrMonthRecordDBMgr", "query selection=", str4);
        try {
            Cursor rawQueryStorageData = mea.b(this.f8371a).rawQueryStorageData(1, str4, new String[]{mef.e(str), mef.e(str2), mef.e(str3)});
            if (rawQueryStorageData != null) {
                weekAndMonthRecord = null;
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        weekAndMonthRecord = cgj_(null, rawQueryStorageData);
                    } catch (Throwable th) {
                        if (rawQueryStorageData != null) {
                            try {
                                try {
                                    rawQueryStorageData.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } catch (SQLException e) {
                                e = e;
                                com.huawei.hwlogsmodel.LogUtil.h("PLGACHIEVE_WeekOrMonthRecordDBMgr", e.getMessage());
                                return weekAndMonthRecord;
                            }
                        }
                        throw th;
                    }
                }
                weekAndMonthRecord2 = weekAndMonthRecord;
            }
            if (rawQueryStorageData == null) {
                return weekAndMonthRecord2;
            }
            rawQueryStorageData.close();
            return weekAndMonthRecord2;
        } catch (SQLException e2) {
            e = e2;
            weekAndMonthRecord = weekAndMonthRecord2;
        }
    }

    private WeekAndMonthRecord cgj_(String str, Cursor cursor) {
        WeekAndMonthRecord weekAndMonthRecord = new WeekAndMonthRecord();
        weekAndMonthRecord.setHuid(cursor.getString(cursor.getColumnIndex("huid")));
        long j = cursor.getLong(cursor.getColumnIndex("startTimestamp"));
        long j2 = cursor.getLong(cursor.getColumnIndex("endTimeStamp"));
        String string = cursor.getString(cursor.getColumnIndex("britishValue"));
        int i = cursor.getInt(cursor.getColumnIndex("recentType"));
        int i2 = cursor.getInt(cursor.getColumnIndex("dataSource"));
        int i3 = cursor.getInt(cursor.getColumnIndex("maxReportNo"));
        int i4 = cursor.getInt(cursor.getColumnIndex(ParsedFieldTag.MIN_REPORT_NO));
        weekAndMonthRecord.setReportNo(cursor.getInt(cursor.getColumnIndex("reportNo")));
        weekAndMonthRecord.setBritishValue(string);
        weekAndMonthRecord.setDataSource(i2);
        weekAndMonthRecord.setMaxReportNo(i3);
        weekAndMonthRecord.setMinReportNo(i4);
        weekAndMonthRecord.setRecentType(i);
        weekAndMonthRecord.setStartTimestamp(Long.valueOf(j));
        weekAndMonthRecord.setEndTimestamp(Long.valueOf(j2));
        String string2 = cursor.getString(cursor.getColumnIndex("value"));
        if (str != null) {
            if (String.valueOf(2).equals(str)) {
                weekAndMonthRecord.setValue(string2);
            } else if (String.valueOf(1).equals(str)) {
                weekAndMonthRecord.setValueFromCloud(string2);
            } else {
                LogUtil.e("PLGACHIEVE_WeekOrMonthRecordDBMgr", "getWeekAndMonthRecord = null");
            }
        } else if (i2 == 2) {
            weekAndMonthRecord.setValue(string2);
        } else if (i2 == 1) {
            weekAndMonthRecord.setValueFromCloud(string2);
        } else {
            LogUtil.e("PLGACHIEVE_WeekOrMonthRecordDBMgr", "getWeekAndMonthRecord = null");
        }
        return weekAndMonthRecord;
    }

    private List<mcz> e(String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str4)) {
            LogUtil.d("PLGACHIEVE_WeekOrMonthRecordDBMgr", "WeekOrMonthRecordDBMgr, queryList ,id is null!return");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        String str6 = "select *  from " + mea.b(this.f8371a).getTableFullName("week_and_month_record") + " where startTimestamp >=? and endTimeStamp <=? and huid =? and recentType=? and dataSource=? order by startTimestamp ASC";
        LogUtil.b("PLGACHIEVE_WeekOrMonthRecordDBMgr", "query selection=", str6);
        try {
            Cursor rawQueryStorageData = mea.b(this.f8371a).rawQueryStorageData(1, str6, new String[]{mef.e(str), mef.e(str2), mef.e(str3), mef.e(str4), mef.e(str5)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        arrayList.add(cgj_(null, rawQueryStorageData));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            com.huawei.hwlogsmodel.LogUtil.h("PLGACHIEVE_WeekOrMonthRecordDBMgr", e.getMessage());
        }
        return arrayList;
    }

    private mcz b(String str, String str2, String str3, String str4) {
        WeekAndMonthRecord weekAndMonthRecord = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            LogUtil.d("PLGACHIEVE_WeekOrMonthRecordDBMgr", "WeekOrMonthRecordDBMgr, query ,id is null!return");
            return null;
        }
        String str5 = "select *  from " + mea.b(this.f8371a).getTableFullName("week_and_month_record") + " where startTimestamp =? and huid =? and recentType =? and dataSource=?";
        LogUtil.b("PLGACHIEVE_WeekOrMonthRecordDBMgr", "query selection=", str5);
        try {
            Cursor rawQueryStorageData = mea.b(this.f8371a).rawQueryStorageData(1, str5, new String[]{mef.e(str), mef.e(str2), mef.e(str3), mef.e(str4)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        weekAndMonthRecord = cgj_(str4, rawQueryStorageData);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            com.huawei.hwlogsmodel.LogUtil.b("PLGACHIEVE_WeekOrMonthRecordDBMgr", e.getMessage());
        }
        return weekAndMonthRecord;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        int a2;
        long j = -1;
        if (mczVar == null) {
            LogUtil.c("PLGACHIEVE_WeekOrMonthRecordDBMgr", "insert achieveData is null");
            return -1L;
        }
        WeekAndMonthRecord weekAndMonthRecord = mczVar instanceof WeekAndMonthRecord ? (WeekAndMonthRecord) mczVar : null;
        if (weekAndMonthRecord == null) {
            LogUtil.c("PLGACHIEVE_WeekOrMonthRecordDBMgr", "insert weekAndMonthRecord is null");
            return -1L;
        }
        if (!TextUtils.isEmpty(weekAndMonthRecord.getValueFromCloud())) {
            if (b(String.valueOf(weekAndMonthRecord.getStartTimestamp()), weekAndMonthRecord.getHuid(), String.valueOf(weekAndMonthRecord.getRecentType()), String.valueOf(1)) != null) {
                a2 = a(weekAndMonthRecord);
                return a2;
            }
            j = (-1) + e(weekAndMonthRecord);
        }
        if (TextUtils.isEmpty(weekAndMonthRecord.getValue())) {
            return j;
        }
        if (b(String.valueOf(weekAndMonthRecord.getStartTimestamp()), weekAndMonthRecord.getHuid(), String.valueOf(weekAndMonthRecord.getRecentType()), String.valueOf(2)) != null) {
            a2 = a(weekAndMonthRecord);
            return a2;
        }
        return j + e(weekAndMonthRecord);
    }

    private long e(WeekAndMonthRecord weekAndMonthRecord) {
        ContentValues contentValues = new ContentValues();
        if (!cgk_(contentValues, weekAndMonthRecord)) {
            return -1L;
        }
        long insertStorageData = mea.b(this.f8371a).insertStorageData("week_and_month_record", 1, contentValues);
        LogUtil.d("PLGACHIEVE_WeekOrMonthRecordDBMgr", "insert result=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            LogUtil.c("PLGACHIEVE_WeekOrMonthRecordDBMgr", "delete achieveData is null");
            return -1;
        }
        WeekAndMonthRecord weekAndMonthRecord = mczVar instanceof WeekAndMonthRecord ? (WeekAndMonthRecord) mczVar : null;
        if (weekAndMonthRecord == null) {
            LogUtil.c("PLGACHIEVE_WeekOrMonthRecordDBMgr", "delete weekAndMonthRecord is null");
            return -1;
        }
        String[] strArr = {mef.e(weekAndMonthRecord.getHuid())};
        LogUtil.b("PLGACHIEVE_WeekOrMonthRecordDBMgr", "delete selection=", "huid=?");
        int deleteStorageData = mea.b(this.f8371a).deleteStorageData("week_and_month_record", 1, "huid=?", strArr);
        LogUtil.d("PLGACHIEVE_WeekOrMonthRecordDBMgr", "delete result=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            LogUtil.c("PLGACHIEVE_WeekOrMonthRecordDBMgr", "update achieveData is null");
            return -1;
        }
        WeekAndMonthRecord weekAndMonthRecord = mczVar instanceof WeekAndMonthRecord ? (WeekAndMonthRecord) mczVar : null;
        if (weekAndMonthRecord == null) {
            LogUtil.c("PLGACHIEVE_WeekOrMonthRecordDBMgr", "update weekAndMonthRecord is null");
            return -1;
        }
        if (b(String.valueOf(weekAndMonthRecord.getStartTimestamp()), weekAndMonthRecord.getHuid(), String.valueOf(weekAndMonthRecord.getRecentType()), String.valueOf(weekAndMonthRecord.getDataSource())) != null) {
            return a(weekAndMonthRecord);
        }
        return -1;
    }

    private int a(WeekAndMonthRecord weekAndMonthRecord) {
        ContentValues contentValues = new ContentValues();
        if (!cgk_(contentValues, weekAndMonthRecord)) {
            return -1;
        }
        String[] strArr = {mef.e(Long.valueOf(weekAndMonthRecord.getStartTimestamp())), mef.e(weekAndMonthRecord.getHuid()), mef.e(Integer.valueOf(weekAndMonthRecord.getRecentType())), mef.e(Integer.valueOf(weekAndMonthRecord.getDataSource()))};
        LogUtil.b("PLGACHIEVE_WeekOrMonthRecordDBMgr", "update selection=", "startTimestamp=? and huid=? and recentType=? and dataSource=?");
        int updateStorageData = mea.b(this.f8371a).updateStorageData("week_and_month_record", 1, contentValues, "startTimestamp=? and huid=? and recentType=? and dataSource=?", strArr);
        LogUtil.d("PLGACHIEVE_WeekOrMonthRecordDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }

    private boolean cgk_(ContentValues contentValues, WeekAndMonthRecord weekAndMonthRecord) {
        if (weekAndMonthRecord == null || weekAndMonthRecord.getStartTimestamp() == 0) {
            return false;
        }
        contentValues.put("huid", weekAndMonthRecord.getHuid());
        contentValues.put("startTimestamp", Long.valueOf(weekAndMonthRecord.getStartTimestamp()));
        contentValues.put("endTimeStamp", Long.valueOf(weekAndMonthRecord.getEndTimestamp()));
        if (!TextUtils.isEmpty(weekAndMonthRecord.getValue())) {
            contentValues.put("value", weekAndMonthRecord.getValue());
        } else if (!TextUtils.isEmpty(weekAndMonthRecord.getValueFromCloud())) {
            contentValues.put("value", weekAndMonthRecord.getValueFromCloud());
        } else {
            LogUtil.c("PLGACHIEVE_WeekOrMonthRecordDBMgr", "structContentData weekAndMonthRecord getValue/getValueFromCloud is empty");
        }
        contentValues.put("britishValue", weekAndMonthRecord.getBritishValue());
        contentValues.put("recentType", Integer.valueOf(weekAndMonthRecord.getRecentType()));
        contentValues.put("dataSource", Integer.valueOf(weekAndMonthRecord.getDataSource()));
        contentValues.put("maxReportNo", Integer.valueOf(weekAndMonthRecord.getMinReportNo()));
        contentValues.put(ParsedFieldTag.MIN_REPORT_NO, Integer.valueOf(weekAndMonthRecord.getMinReportNo()));
        contentValues.put("reportNo", Integer.valueOf(weekAndMonthRecord.getReportNo()));
        return true;
    }
}
