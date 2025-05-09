package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.AchieveLevelEventInfo;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class AchieveLevelEventDBMgr implements IAchieveDBMgr {
    private Context e;

    public AchieveLevelEventDBMgr(Context context) {
        this.e = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return a(map.get("huid"));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return e(map.get("taskId"), map.get("huid"));
    }

    private mcz e(String str, String str2) {
        AchieveLevelEventInfo achieveLevelEventInfo = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelEventDBMgr", "AchieveLevelEventDBMgr, query ,taskId is null!return");
            return null;
        }
        String str3 = "select *  from " + mea.b(this.e).getTableFullName("level_event_record") + " where taskId =? and huid=?";
        LogUtil.c("PLGACHIEVE_AchieveLevelEventDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.e).rawQueryStorageData(1, str3, new String[]{mef.e(str), mef.e(str2)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (achieveLevelEventInfo == null) {
                            achieveLevelEventInfo = new AchieveLevelEventInfo();
                        }
                        achieveLevelEventInfo.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        achieveLevelEventInfo.saveTaskId(mea.cfS_(rawQueryStorageData, "taskId"));
                        achieveLevelEventInfo.saveRewardExperience(mea.cfQ_(rawQueryStorageData, ParsedFieldTag.TASK_REWARD_EXPERIENCE));
                        achieveLevelEventInfo.saveTaskCount(mea.cfQ_(rawQueryStorageData, "taskCount"));
                        achieveLevelEventInfo.saveLastModifyTime(mea.cfR_(rawQueryStorageData, "lastModifyTime"));
                        achieveLevelEventInfo.saveTaskSumCount(mea.cfQ_(rawQueryStorageData, "taskSumCount"));
                        achieveLevelEventInfo.saveSyncTime(mea.cfR_(rawQueryStorageData, "SyncTime"));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_AchieveLevelEventDBMgr", e.getMessage());
        }
        Object[] objArr = new Object[2];
        objArr[0] = "query achieveLevelEventInfo=";
        objArr[1] = achieveLevelEventInfo == null ? Constants.NULL : achieveLevelEventInfo;
        LogUtil.c("PLGACHIEVE_AchieveLevelEventDBMgr", objArr);
        return achieveLevelEventInfo;
    }

    private List<mcz> a(String str) {
        if (str == null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelEventDBMgr", "achieveLevelEventInfo, query ,id is null!return");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        String str2 = "select *  from " + mea.b(this.e).getTableFullName("level_event_record") + " where huid=?";
        LogUtil.c("PLGACHIEVE_AchieveLevelEventDBMgr", "query selection=", str2);
        try {
            Cursor rawQueryStorageData = mea.b(this.e).rawQueryStorageData(1, str2, new String[]{mef.e(str)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        AchieveLevelEventInfo achieveLevelEventInfo = new AchieveLevelEventInfo();
                        achieveLevelEventInfo.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        achieveLevelEventInfo.saveTaskId(mea.cfS_(rawQueryStorageData, "taskId"));
                        achieveLevelEventInfo.saveRewardExperience(mea.cfQ_(rawQueryStorageData, ParsedFieldTag.TASK_REWARD_EXPERIENCE));
                        achieveLevelEventInfo.saveTaskCount(mea.cfQ_(rawQueryStorageData, "taskCount"));
                        achieveLevelEventInfo.saveLastModifyTime(mea.cfR_(rawQueryStorageData, "lastModifyTime"));
                        achieveLevelEventInfo.saveTaskSumCount(mea.cfQ_(rawQueryStorageData, "taskSumCount"));
                        achieveLevelEventInfo.saveSyncTime(mea.cfR_(rawQueryStorageData, "SyncTime"));
                        arrayList.add(achieveLevelEventInfo);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_AchieveLevelEventDBMgr", e.getMessage());
        }
        LogUtil.c("PLGACHIEVE_AchieveLevelEventDBMgr", "query achieveLevelEventInfo List=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        AchieveLevelEventInfo achieveLevelEventInfo = mczVar instanceof AchieveLevelEventInfo ? (AchieveLevelEventInfo) mczVar : null;
        if (achieveLevelEventInfo == null) {
            return -1L;
        }
        if (e(String.valueOf(achieveLevelEventInfo.acquireTaskId()), achieveLevelEventInfo.getHuid()) != null) {
            return update(mczVar);
        }
        return d(achieveLevelEventInfo) - 1;
    }

    private long d(AchieveLevelEventInfo achieveLevelEventInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", achieveLevelEventInfo.getHuid());
        contentValues.put("taskId", String.valueOf(achieveLevelEventInfo.acquireTaskId()));
        contentValues.put(ParsedFieldTag.TASK_REWARD_EXPERIENCE, Integer.valueOf(achieveLevelEventInfo.acquireRewardExperience()));
        contentValues.put("taskSumCount", String.valueOf(achieveLevelEventInfo.acquireTaskSumCount()));
        contentValues.put("taskCount", Integer.valueOf(achieveLevelEventInfo.acquireTaskCount()));
        contentValues.put("SyncTime", Long.valueOf(achieveLevelEventInfo.acquireSyncTime()));
        contentValues.put("lastModifyTime", Long.valueOf(achieveLevelEventInfo.acquireLastModifyTime()));
        long insertStorageData = mea.b(this.e).insertStorageData("level_event_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_AchieveLevelEventDBMgr", "insert insertLevelResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        AchieveLevelEventInfo achieveLevelEventInfo = mczVar instanceof AchieveLevelEventInfo ? (AchieveLevelEventInfo) mczVar : null;
        if (achieveLevelEventInfo == null) {
            return -1;
        }
        String[] strArr = {mef.e(achieveLevelEventInfo.acquireTaskId())};
        LogUtil.c("PLGACHIEVE_AchieveLevelEventDBMgr", "delete selection=", "taskId=?");
        int deleteStorageData = mea.b(this.e).deleteStorageData("level_event_record", 1, "taskId=?", strArr);
        LogUtil.a("PLGACHIEVE_AchieveLevelEventDBMgr", "delete deleteLevelResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        if ((mczVar instanceof AchieveLevelEventInfo ? (AchieveLevelEventInfo) mczVar : null) == null) {
            return -1;
        }
        return b(r3) - 1;
    }

    private int b(AchieveLevelEventInfo achieveLevelEventInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", achieveLevelEventInfo.getHuid());
        contentValues.put("taskId", String.valueOf(achieveLevelEventInfo.acquireTaskId()));
        contentValues.put(ParsedFieldTag.TASK_REWARD_EXPERIENCE, Integer.valueOf(achieveLevelEventInfo.acquireRewardExperience()));
        contentValues.put("taskCount", Integer.valueOf(achieveLevelEventInfo.acquireTaskCount()));
        contentValues.put("lastModifyTime", Long.valueOf(achieveLevelEventInfo.acquireLastModifyTime()));
        contentValues.put("taskSumCount", String.valueOf(achieveLevelEventInfo.acquireTaskSumCount()));
        contentValues.put("SyncTime", Long.valueOf(achieveLevelEventInfo.acquireSyncTime()));
        String[] strArr = {mef.e(achieveLevelEventInfo.acquireTaskId()), mef.e(achieveLevelEventInfo.getHuid())};
        LogUtil.c("PLGACHIEVE_AchieveLevelEventDBMgr", "update selection=", "taskId=? and huid=?");
        int updateStorageData = mea.b(this.e).updateStorageData("level_event_record", 1, contentValues, "taskId=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_AchieveLevelEventDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }
}
