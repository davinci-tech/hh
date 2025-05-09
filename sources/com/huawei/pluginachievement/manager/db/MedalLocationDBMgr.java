package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class MedalLocationDBMgr implements IAchieveDBMgr {

    /* renamed from: a, reason: collision with root package name */
    private Context f8369a;

    public MedalLocationDBMgr(Context context) {
        this.f8369a = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return e(map.get("huid"));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return c(map.get("medalID"), map.get("huid"));
    }

    private mcz c(String str, String str2) {
        MedalLocation medalLocation = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_MedalLocationDBMgr", "MedalLocationDBMgr, query ,id is null!return");
            return null;
        }
        String str3 = "select *  from " + mea.b(this.f8369a).getTableFullName("medal_location_record") + " where medalID =? and huid=?";
        LogUtil.c("PLGACHIEVE_MedalLocationDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.f8369a).rawQueryStorageData(1, str3, new String[]{mef.e(str), mef.e(str2)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (medalLocation == null) {
                            medalLocation = new MedalLocation();
                        }
                        medalLocation.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        medalLocation.saveMedalName(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalName")));
                        medalLocation.saveMedalID(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalID")));
                        medalLocation.saveFirstTabDesc(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.FIRST_TAB_DESC)));
                        medalLocation.saveFirstTabPriority(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex(ParsedFieldTag.FIRST_TAB_PRIORITY)));
                        medalLocation.saveSecondTabDesc(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.SECOND_TAB_DESC)));
                        medalLocation.saveSecondTabPriority(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex(ParsedFieldTag.SECOND_TAB_PRIORITY)));
                        medalLocation.saveMedalWeight(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex(ParsedFieldTag.MEDAL_WEIGHT)));
                        medalLocation.saveTimestamp(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("timestamp")));
                        medalLocation.saveMedalGainedTime(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalGainedTime")));
                        medalLocation.saveGainedCount(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("gainedCount")));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.c("PLGACHIEVE_MedalLocationDBMgr", e.getMessage());
        }
        return medalLocation;
    }

    private List<mcz> e(String str) {
        if (str == null) {
            LogUtil.a("PLGACHIEVE_MedalLocationDBMgr", "MedalLocationDBMgr, query ,id is null!return");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        String str2 = "select *  from " + mea.b(this.f8369a).getTableFullName("medal_location_record") + " where huid=?";
        LogUtil.c("PLGACHIEVE_MedalLocationDBMgr", "query selection=", str2);
        try {
            Cursor rawQueryStorageData = mea.b(this.f8369a).rawQueryStorageData(1, str2, new String[]{mef.e(str)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        MedalLocation medalLocation = new MedalLocation();
                        medalLocation.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        String string = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalName"));
                        String string2 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalID"));
                        int i = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex(ParsedFieldTag.FIRST_TAB_PRIORITY));
                        String string3 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.FIRST_TAB_DESC));
                        int i2 = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex(ParsedFieldTag.SECOND_TAB_PRIORITY));
                        String string4 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.SECOND_TAB_DESC));
                        int i3 = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex(ParsedFieldTag.MEDAL_WEIGHT));
                        String string5 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalGainedTime"));
                        int i4 = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("gainedCount"));
                        long j = rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("timestamp"));
                        medalLocation.saveMedalName(string);
                        medalLocation.saveMedalID(string2);
                        medalLocation.saveFirstTabPriority(i);
                        medalLocation.saveFirstTabDesc(string3);
                        medalLocation.saveSecondTabDesc(string4);
                        medalLocation.saveSecondTabPriority(i2);
                        medalLocation.saveMedalWeight(i3);
                        medalLocation.saveMedalGainedTime(string5);
                        medalLocation.saveTimestamp(j);
                        medalLocation.saveGainedCount(i4);
                        arrayList.add(medalLocation);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException unused) {
            LogUtil.b("PLGACHIEVE_MedalLocationDBMgr", "query MedalLocationDBMgr SQLException");
        }
        LogUtil.c("PLGACHIEVE_MedalLocationDBMgr", "query MedalLocationDBMgr=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        MedalLocation medalLocation = mczVar instanceof MedalLocation ? (MedalLocation) mczVar : null;
        if (medalLocation == null) {
            return -1L;
        }
        if (c(medalLocation.acquireMedalID(), medalLocation.getHuid()) != null) {
            return d(r0, mczVar);
        }
        return d(medalLocation) - 1;
    }

    private long d(MedalLocation medalLocation) {
        ContentValues contentValues = new ContentValues();
        if (!cgd_(contentValues, medalLocation)) {
            return -1L;
        }
        long insertStorageData = mea.b(this.f8369a).insertStorageData("medal_location_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_MedalLocationDBMgr", "insert insertMedalLocationResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        MedalLocation medalLocation = mczVar instanceof MedalLocation ? (MedalLocation) mczVar : null;
        if (medalLocation == null) {
            return -1;
        }
        String[] strArr = {mef.e(medalLocation.acquireMedalID()), mef.e(medalLocation.getHuid())};
        LogUtil.c("PLGACHIEVE_MedalLocationDBMgr", "delete selection=", "medalID=? and huid=?");
        int deleteStorageData = mea.b(this.f8369a).deleteStorageData("medal_location_record", 1, "medalID=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_MedalLocationDBMgr", "delete deleteMedalLocationResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        mcz c;
        if (mczVar == null) {
            return -1;
        }
        MedalLocation medalLocation = mczVar instanceof MedalLocation ? (MedalLocation) mczVar : null;
        if (medalLocation == null || (c = c(medalLocation.acquireMedalID(), medalLocation.getHuid())) == null) {
            return -1;
        }
        return d(c, mczVar);
    }

    public int d(mcz mczVar, mcz mczVar2) {
        if (mczVar2 == null) {
            return -1;
        }
        if ((mczVar2 instanceof MedalLocation ? (MedalLocation) mczVar2 : null) == null) {
            return -1;
        }
        return c(mczVar, r4) - 1;
    }

    private int c(mcz mczVar, MedalLocation medalLocation) {
        ContentValues contentValues = new ContentValues();
        if (!cge_(contentValues, medalLocation, mczVar)) {
            return -1;
        }
        String[] strArr = {mef.e(medalLocation.acquireMedalID()), mef.e(medalLocation.getHuid())};
        LogUtil.c("PLGACHIEVE_MedalLocationDBMgr", "update selection=", "medalID=? and huid=?");
        int updateStorageData = mea.b(this.f8369a).updateStorageData("medal_location_record", 1, contentValues, "medalID=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_MedalLocationDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }

    private boolean cge_(ContentValues contentValues, MedalLocation medalLocation, mcz mczVar) {
        if (medalLocation == null) {
            return false;
        }
        MedalLocation medalLocation2 = mczVar instanceof MedalLocation ? (MedalLocation) mczVar : null;
        if (medalLocation2 == null) {
            return false;
        }
        String acquireFirstTabDesc = medalLocation.acquireFirstTabDesc();
        contentValues.put("huid", medalLocation2.getHuid());
        contentValues.put("medalName", c(medalLocation2.acquireMedalName()));
        contentValues.put("medalID", medalLocation2.acquireMedalID());
        contentValues.put(ParsedFieldTag.FIRST_TAB_PRIORITY, Integer.valueOf(mea.d(medalLocation2.acquireFirstTabPriority(), medalLocation.acquireFirstTabPriority())));
        contentValues.put(ParsedFieldTag.FIRST_TAB_DESC, mea.a(medalLocation2.acquireFirstTabDesc(), acquireFirstTabDesc));
        contentValues.put(ParsedFieldTag.SECOND_TAB_PRIORITY, Integer.valueOf(mea.d(medalLocation2.acquireSecondTabPriority(), medalLocation.acquireSecondTabPriority())));
        contentValues.put(ParsedFieldTag.SECOND_TAB_DESC, mea.a(medalLocation2.acquireSecondTabDesc(), medalLocation.acquireSecondTabDesc()));
        contentValues.put(ParsedFieldTag.MEDAL_WEIGHT, Integer.valueOf(mea.d(medalLocation2.acquireMedalWeight(), medalLocation.acquireMedalWeight())));
        contentValues.put("medalGainedTime", d(medalLocation2.acquireMedalGainedTime(), medalLocation.acquireMedalGainedTime()));
        contentValues.put("gainedCount", Integer.valueOf(mea.d(medalLocation2.acquireGainedCount(), medalLocation.acquireGainedCount())));
        if (!TextUtils.isEmpty(acquireFirstTabDesc)) {
            contentValues.put("timestamp", Long.valueOf(medalLocation.acquireTimestamp()));
            return true;
        }
        contentValues.put("timestamp", Long.valueOf(medalLocation2.acquireTimestamp()));
        return true;
    }

    private boolean cgd_(ContentValues contentValues, MedalLocation medalLocation) {
        if (medalLocation == null || medalLocation.acquireMedalID() == null || medalLocation.acquireFirstTabDesc() == null) {
            return false;
        }
        contentValues.put("huid", medalLocation.getHuid());
        contentValues.put("medalName", c(medalLocation.acquireMedalName()));
        contentValues.put("medalID", medalLocation.acquireMedalID());
        contentValues.put(ParsedFieldTag.FIRST_TAB_PRIORITY, Integer.valueOf(medalLocation.acquireFirstTabPriority()));
        contentValues.put(ParsedFieldTag.FIRST_TAB_DESC, c(medalLocation.acquireFirstTabDesc()));
        contentValues.put(ParsedFieldTag.SECOND_TAB_PRIORITY, Integer.valueOf(medalLocation.acquireSecondTabPriority()));
        contentValues.put(ParsedFieldTag.SECOND_TAB_DESC, c(medalLocation.acquireSecondTabDesc()));
        contentValues.put(ParsedFieldTag.MEDAL_WEIGHT, Integer.valueOf(medalLocation.acquireMedalWeight()));
        contentValues.put("medalGainedTime", c(medalLocation.acquireMedalGainedTime()));
        contentValues.put("gainedCount", Integer.valueOf(medalLocation.acquireGainedCount()));
        contentValues.put("timestamp", Long.valueOf(medalLocation.acquireTimestamp()));
        return true;
    }

    private static String d(String str, String str2) {
        return !mef.a(str2) ? str : str2;
    }

    private static String c(String str) {
        return str == null ? "" : str;
    }
}
