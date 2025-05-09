package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class AchieveUserLevelDBMgr implements IAchieveDBMgr {
    private Context c;

    public AchieveUserLevelDBMgr(Context context) {
        this.c = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return Collections.emptyList();
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return d(map.get("huid"));
    }

    private mcz d(String str) {
        AchieveUserLevelInfo achieveUserLevelInfo = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_AchieveUserLevelDBMgr", "AchieveUserLevelDBMgr, query ,huid is null!return");
            return null;
        }
        String str2 = "select *  from " + mea.b(this.c).getTableFullName("level_info_record") + " where huid=?";
        LogUtil.c("PLGACHIEVE_AchieveUserLevelDBMgr", "query selection=", str2);
        try {
            Cursor rawQueryStorageData = mea.b(this.c).rawQueryStorageData(1, str2, new String[]{mef.e(str)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (achieveUserLevelInfo == null) {
                            achieveUserLevelInfo = new AchieveUserLevelInfo();
                        }
                        achieveUserLevelInfo.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        achieveUserLevelInfo.saveUserLevel(mea.cfQ_(rawQueryStorageData, ParsedFieldTag.USER_LEVEL));
                        achieveUserLevelInfo.saveUserExperience(mea.cfQ_(rawQueryStorageData, ParsedFieldTag.USER_EXPERIENCE));
                        achieveUserLevelInfo.saveUserReachDays(mef.d(mea.cfS_(rawQueryStorageData, "userReachDays")));
                        achieveUserLevelInfo.saveSyncTimestamp(mea.cfR_(rawQueryStorageData, "syncTimestamp"));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_AchieveUserLevelDBMgr", e.getMessage());
        }
        Object[] objArr = new Object[2];
        objArr[0] = "query AchieveUserLevelInfo=";
        objArr[1] = achieveUserLevelInfo == null ? Constants.NULL : achieveUserLevelInfo;
        LogUtil.a("PLGACHIEVE_AchieveUserLevelDBMgr", objArr);
        return achieveUserLevelInfo;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        AchieveUserLevelInfo achieveUserLevelInfo = mczVar instanceof AchieveUserLevelInfo ? (AchieveUserLevelInfo) mczVar : null;
        if (achieveUserLevelInfo == null) {
            return -1L;
        }
        if (d(achieveUserLevelInfo.getHuid()) != null) {
            return update(mczVar);
        }
        return Math.max(e(achieveUserLevelInfo) - 1, -1L);
    }

    private long e(AchieveUserLevelInfo achieveUserLevelInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", achieveUserLevelInfo.getHuid());
        contentValues.put(ParsedFieldTag.USER_LEVEL, Integer.valueOf(achieveUserLevelInfo.acquireUserLevel()));
        contentValues.put(ParsedFieldTag.USER_EXPERIENCE, Integer.valueOf(achieveUserLevelInfo.acquireUserExperience()));
        contentValues.put("syncTimestamp", Long.valueOf(achieveUserLevelInfo.acquireSyncTimestamp()));
        contentValues.put("userReachDays", String.valueOf(achieveUserLevelInfo.acquireUserReachDays()));
        long insertStorageData = mea.b(this.c).insertStorageData("level_info_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_AchieveUserLevelDBMgr", "insert insertUserLevelResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        AchieveUserLevelInfo achieveUserLevelInfo = mczVar instanceof AchieveUserLevelInfo ? (AchieveUserLevelInfo) mczVar : null;
        if (achieveUserLevelInfo == null) {
            return -1;
        }
        String[] strArr = {mef.e(Integer.valueOf(achieveUserLevelInfo.acquireUserLevel()))};
        LogUtil.c("PLGACHIEVE_AchieveUserLevelDBMgr", "delete selection=", "userLevel=?");
        int deleteStorageData = mea.b(this.c).deleteStorageData("level_info_record", 1, "userLevel=?", strArr);
        LogUtil.a("PLGACHIEVE_AchieveUserLevelDBMgr", "delete deleteUserResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        if ((mczVar instanceof AchieveUserLevelInfo ? (AchieveUserLevelInfo) mczVar : null) == null) {
            return -1;
        }
        return Math.max(d(r3) - 1, -1);
    }

    private int d(AchieveUserLevelInfo achieveUserLevelInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", achieveUserLevelInfo.getHuid());
        contentValues.put(ParsedFieldTag.USER_LEVEL, Integer.valueOf(achieveUserLevelInfo.acquireUserLevel()));
        contentValues.put(ParsedFieldTag.USER_EXPERIENCE, Integer.valueOf(achieveUserLevelInfo.acquireUserExperience()));
        contentValues.put("userReachDays", String.valueOf(achieveUserLevelInfo.acquireUserReachDays()));
        contentValues.put("syncTimestamp", Long.valueOf(achieveUserLevelInfo.acquireSyncTimestamp()));
        String[] strArr = {mef.e(achieveUserLevelInfo.getHuid())};
        LogUtil.c("PLGACHIEVE_AchieveUserLevelDBMgr", "update selection=", "huid=?");
        int updateStorageData = mea.b(this.c).updateStorageData("level_info_record", 1, contentValues, "huid=?", strArr);
        LogUtil.a("PLGACHIEVE_AchieveUserLevelDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }
}
