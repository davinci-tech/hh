package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.AchieveLevelTotalInfo;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class LevelNumbersDBMgr implements IAchieveDBMgr {
    private Context b;

    public LevelNumbersDBMgr(Context context) {
        this.b = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return d(map.get("huid"));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return a(map.get("level"), map.get("huid"));
    }

    private mcz a(String str, String str2) {
        AchieveLevelTotalInfo achieveLevelTotalInfo = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_LevelNumbersDBMgr", "MedalBasicDBMgr, query ,id is null!return");
            return null;
        }
        String str3 = "select *  from " + mea.b(this.b).getTableFullName("level_number_record") + " where level =? and huid=?";
        LogUtil.c("PLGACHIEVE_LevelNumbersDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.b).rawQueryStorageData(1, str3, new String[]{mef.e(str), mef.e(str2)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (achieveLevelTotalInfo == null) {
                            achieveLevelTotalInfo = new AchieveLevelTotalInfo();
                        }
                        achieveLevelTotalInfo.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        achieveLevelTotalInfo.saveLevel(mea.cfQ_(rawQueryStorageData, "level"));
                        achieveLevelTotalInfo.saveUserNumber(mea.cfQ_(rawQueryStorageData, "levelUserNumber"));
                        achieveLevelTotalInfo.saveSyncTime(mea.cfR_(rawQueryStorageData, UserCloseRecord.TIME_STAMP));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (Exception e) {
            LogUtil.h("PLGACHIEVE_LevelNumbersDBMgr", e.getMessage());
        }
        Object[] objArr = new Object[2];
        objArr[0] = "query achieveLevelTotalInfo=";
        objArr[1] = achieveLevelTotalInfo == null ? Constants.NULL : achieveLevelTotalInfo;
        LogUtil.c("PLGACHIEVE_LevelNumbersDBMgr", objArr);
        return achieveLevelTotalInfo;
    }

    private List<mcz> d(String str) {
        if (str == null) {
            LogUtil.a("PLGACHIEVE_LevelNumbersDBMgr", "AchieveLevelTotalInfo, query ,id is null!return");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        String str2 = "select *  from " + mea.b(this.b).getTableFullName("level_number_record") + " where huid=?";
        LogUtil.c("PLGACHIEVE_LevelNumbersDBMgr", "query selection=", str2);
        try {
            Cursor rawQueryStorageData = mea.b(this.b).rawQueryStorageData(1, str2, new String[]{mef.e(str)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        AchieveLevelTotalInfo achieveLevelTotalInfo = new AchieveLevelTotalInfo();
                        achieveLevelTotalInfo.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        achieveLevelTotalInfo.saveLevel(mea.cfQ_(rawQueryStorageData, "level"));
                        achieveLevelTotalInfo.saveUserNumber(mea.cfQ_(rawQueryStorageData, "levelUserNumber"));
                        achieveLevelTotalInfo.saveSyncTime(mea.cfR_(rawQueryStorageData, UserCloseRecord.TIME_STAMP));
                        arrayList.add(achieveLevelTotalInfo);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_LevelNumbersDBMgr", e.getMessage());
        }
        LogUtil.c("PLGACHIEVE_LevelNumbersDBMgr", "query medalBasic medalBasicList=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        AchieveLevelTotalInfo achieveLevelTotalInfo = mczVar instanceof AchieveLevelTotalInfo ? (AchieveLevelTotalInfo) mczVar : null;
        if (achieveLevelTotalInfo == null) {
            return -1L;
        }
        if (a(String.valueOf(achieveLevelTotalInfo.acquireLevel()), achieveLevelTotalInfo.getHuid()) != null) {
            return update(mczVar);
        }
        return b(achieveLevelTotalInfo) - 1;
    }

    private long b(AchieveLevelTotalInfo achieveLevelTotalInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", achieveLevelTotalInfo.getHuid());
        contentValues.put("level", Integer.valueOf(achieveLevelTotalInfo.acquireLevel()));
        contentValues.put("levelUserNumber", Integer.valueOf(achieveLevelTotalInfo.acquireUserNumber()));
        contentValues.put(UserCloseRecord.TIME_STAMP, Long.valueOf(achieveLevelTotalInfo.acquireSyncTime()));
        long insertStorageData = mea.b(this.b).insertStorageData("level_number_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_LevelNumbersDBMgr", "insert totalLevelResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        AchieveLevelTotalInfo achieveLevelTotalInfo = mczVar instanceof AchieveLevelTotalInfo ? (AchieveLevelTotalInfo) mczVar : null;
        if (achieveLevelTotalInfo == null) {
            return -1;
        }
        String[] strArr = {mef.e(Integer.valueOf(achieveLevelTotalInfo.acquireLevel()))};
        LogUtil.c("PLGACHIEVE_LevelNumbersDBMgr", "delete selection=", "level=?");
        int deleteStorageData = mea.b(this.b).deleteStorageData("level_number_record", 1, "level=?", strArr);
        LogUtil.a("PLGACHIEVE_LevelNumbersDBMgr", "delete deleteTotalLevelResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        if ((mczVar instanceof AchieveLevelTotalInfo ? (AchieveLevelTotalInfo) mczVar : null) == null) {
            return -1;
        }
        return a(r3) - 1;
    }

    private int a(AchieveLevelTotalInfo achieveLevelTotalInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", achieveLevelTotalInfo.getHuid());
        contentValues.put("level", Integer.valueOf(achieveLevelTotalInfo.acquireLevel()));
        contentValues.put("levelUserNumber", Integer.valueOf(achieveLevelTotalInfo.acquireUserNumber()));
        contentValues.put(UserCloseRecord.TIME_STAMP, Long.valueOf(achieveLevelTotalInfo.acquireSyncTime()));
        String[] strArr = {mef.e(Integer.valueOf(achieveLevelTotalInfo.acquireLevel())), mef.e(achieveLevelTotalInfo.getHuid())};
        LogUtil.c("PLGACHIEVE_LevelNumbersDBMgr", "update selection=", "level=? and huid=?");
        int updateStorageData = mea.b(this.b).updateStorageData("level_number_record", 1, contentValues, "level=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_LevelNumbersDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }
}
