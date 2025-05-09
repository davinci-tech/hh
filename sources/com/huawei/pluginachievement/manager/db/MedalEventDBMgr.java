package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.EventRecord;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class MedalEventDBMgr implements IAchieveDBMgr {
    private Context d;

    public MedalEventDBMgr(Context context) {
        this.d = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return e(map.get("huid"), map.get("eventStatus"));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return c(map.get("medalID"), map.get("huid"));
    }

    private mcz c(String str, String str2) {
        EventRecord eventRecord = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_MedalEventDBMgr", "MedalEventDBMgr, query ,id is null!return");
            return null;
        }
        String str3 = "select *  from " + mea.b(this.d).getTableFullName("medal_event_record") + " where medalID =? and huid=?";
        LogUtil.c("PLGACHIEVE_MedalEventDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.d).rawQueryStorageData(1, str3, new String[]{mef.e(str), mef.e(str2)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (eventRecord == null) {
                            eventRecord = new EventRecord();
                        }
                        eventRecord.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        eventRecord.saveEventType(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("eventType")));
                        eventRecord.saveMedalID(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalID")));
                        eventRecord.saveTimestamp(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("timestamp")));
                        eventRecord.saveKey(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(MedalConstants.EVENT_KEY)));
                        eventRecord.saveKeyType(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(MedalConstants.EVENT_KEYTYPE)));
                        eventRecord.saveTimezone(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("timeZone")));
                        eventRecord.saveValue(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("value")));
                        eventRecord.saveMedalName(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalName")));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_MedalEventDBMgr", e.getMessage());
        }
        return eventRecord;
    }

    private List<mcz> e(String str, String str2) {
        if (str == null) {
            LogUtil.a("PLGACHIEVE_MedalEventDBMgr", "MedalEventDBMgr, query ,id is null!return");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        String str3 = "select *  from " + mea.b(this.d).getTableFullName("medal_event_record") + " where eventStatus =? and huid=?";
        LogUtil.c("PLGACHIEVE_MedalEventDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.d).rawQueryStorageData(1, str3, new String[]{mef.e(str2), mef.e(str)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        EventRecord eventRecord = new EventRecord();
                        eventRecord.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        int i = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("eventType"));
                        String string = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalID"));
                        String string2 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalName"));
                        String string3 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("timeZone"));
                        String string4 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(MedalConstants.EVENT_KEY));
                        String string5 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("value"));
                        String string6 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(MedalConstants.EVENT_KEYTYPE));
                        long j = rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("timestamp"));
                        eventRecord.saveEventType(i);
                        eventRecord.saveMedalID(string);
                        eventRecord.saveTimestamp(j);
                        eventRecord.saveKey(string4);
                        eventRecord.saveValue(string5);
                        eventRecord.saveKeyType(string6);
                        eventRecord.saveTimezone(string3);
                        eventRecord.saveTimestamp(j);
                        eventRecord.saveMedalName(string2);
                        eventRecord.saveStartTime(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("startTime")));
                        eventRecord.saveEndTime(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("endTime")));
                        eventRecord.saveEventStatus(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("eventStatus")));
                        arrayList.add(eventRecord);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_MedalEventDBMgr", e.getMessage());
        }
        LogUtil.c("PLGACHIEVE_MedalEventDBMgr", "query MedalEventDBMgr list=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        EventRecord eventRecord = mczVar instanceof EventRecord ? (EventRecord) mczVar : null;
        if (eventRecord == null) {
            return -1L;
        }
        if (c(eventRecord.acquireMedalID(), eventRecord.getHuid()) != null) {
            return update(mczVar);
        }
        return b(eventRecord) - 1;
    }

    private long b(EventRecord eventRecord) {
        ContentValues contentValues = new ContentValues();
        cgc_(contentValues, eventRecord);
        long insertStorageData = mea.b(this.d).insertStorageData("medal_event_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_MedalEventDBMgr", "insert insertRecordResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        EventRecord eventRecord = mczVar instanceof EventRecord ? (EventRecord) mczVar : null;
        if (eventRecord == null) {
            return -1;
        }
        String[] strArr = {mef.e(eventRecord.acquireMedalID()), mef.e(eventRecord.getHuid())};
        LogUtil.c("PLGACHIEVE_MedalEventDBMgr", "delete selection=", "medalID=? and huid=?");
        int deleteStorageData = mea.b(this.d).deleteStorageData("medal_event_record", 1, "medalID=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_MedalEventDBMgr", "delete deleteRecordResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        if ((mczVar instanceof EventRecord ? (EventRecord) mczVar : null) == null) {
            return -1;
        }
        return a(r3) - 1;
    }

    private int a(EventRecord eventRecord) {
        ContentValues contentValues = new ContentValues();
        cgc_(contentValues, eventRecord);
        String[] strArr = {mef.e(eventRecord.acquireMedalID()), mef.e(eventRecord.getHuid())};
        LogUtil.c("PLGACHIEVE_MedalEventDBMgr", "update selection=", "medalID=? and huid=?");
        int updateStorageData = mea.b(this.d).updateStorageData("medal_event_record", 1, contentValues, "medalID=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_MedalEventDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }

    private void cgc_(ContentValues contentValues, EventRecord eventRecord) {
        if (eventRecord == null) {
            return;
        }
        contentValues.put("huid", eventRecord.getHuid());
        contentValues.put("eventType", Integer.valueOf(eventRecord.acquireEventType()));
        contentValues.put("medalID", eventRecord.acquireMedalID());
        contentValues.put("medalName", eventRecord.acquireMedalName());
        contentValues.put("timeZone", eventRecord.acquireTimezone());
        contentValues.put(MedalConstants.EVENT_KEY, eventRecord.acquireKey());
        contentValues.put(MedalConstants.EVENT_KEYTYPE, eventRecord.acquireKeyType());
        contentValues.put("value", eventRecord.acquireValue());
        contentValues.put("startTime", eventRecord.acquireStartTime());
        contentValues.put("endTime", eventRecord.acquireEndTime());
        contentValues.put("eventStatus", eventRecord.acquireEventStatus());
        contentValues.put("timestamp", Long.valueOf(eventRecord.acquireTimestamp()));
    }
}
