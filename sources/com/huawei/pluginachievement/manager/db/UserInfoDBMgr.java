package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveAMedalsObserver;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import defpackage.meg;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class UserInfoDBMgr implements IAchieveDBMgr {
    private static final Object c = new Object();
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private AchieveAMedalsObserver f8370a;
    private Context b;
    private String e = "";

    public UserInfoDBMgr(Context context) {
        this.b = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return d(map.get("huid"));
    }

    private mcz d(String str) {
        Throwable th;
        synchronized (c) {
            Cursor cursor = null;
            r3 = null;
            AchieveInfo achieveInfo = null;
            if (str == null) {
                LogUtil.a("PLGACHIEVE_UserInfoDBMgr", "UserInfoDBMgr, query ,id is null!return null.");
                return null;
            }
            mea b = mea.b(this.b);
            if (b == null) {
                LogUtil.c("PLGACHIEVE_UserInfoDBMgr", "achieveDBManager is null");
                return null;
            }
            String str2 = "select *  from " + b.getTableFullName("achieve_info") + " where huid =? ";
            LogUtil.c("PLGACHIEVE_UserInfoDBMgr", "query selection=", str2);
            try {
                Cursor rawQueryStorageData = b.rawQueryStorageData(1, str2, new String[]{mef.e(str)});
                if (rawQueryStorageData != null) {
                    while (rawQueryStorageData.moveToNext()) {
                        try {
                            achieveInfo = new AchieveInfo();
                            achieveInfo.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                            achieveInfo.setUserLevel(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("reach_days")));
                            achieveInfo.setUserPoint(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("points")));
                            achieveInfo.saveMedals(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medal_id")));
                            achieveInfo.setUserReachStandardDays(mef.d(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("userReachStandardDays"))));
                            achieveInfo.setSyncTimestamp(mef.e(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("syncTimestamp"))));
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = rawQueryStorageData;
                            if (cursor != null) {
                                cursor.close();
                                throw th;
                            }
                            throw th;
                        }
                    }
                }
                if (rawQueryStorageData != null) {
                    rawQueryStorageData.close();
                }
                return achieveInfo;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return Collections.emptyList();
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        synchronized (c) {
            if (mczVar == null) {
                LogUtil.h("PLGACHIEVE_UserInfoDBMgr", "insert achieveData is null");
                return -1L;
            }
            AchieveInfo achieveInfo = mczVar instanceof AchieveInfo ? (AchieveInfo) mczVar : null;
            if (achieveInfo == null) {
                LogUtil.h("PLGACHIEVE_UserInfoDBMgr", "insert achieveInfo is null");
                return -1L;
            }
            mcz d2 = d(achieveInfo.getHuid());
            if (d2 instanceof AchieveInfo) {
                this.e = ((AchieveInfo) d2).acquireMedals();
                return update(mczVar);
            }
            if (meg.a(achieveInfo) == -1) {
                LogUtil.a("PLGACHIEVE_UserInfoDBMgr", "Column check not pass");
                return -1L;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("huid", achieveInfo.getHuid());
            contentValues.put("reach_days", Integer.valueOf(achieveInfo.getUserLevel()));
            contentValues.put("points", Integer.valueOf(achieveInfo.getUserPoint()));
            contentValues.put("medal_id", achieveInfo.acquireMedals());
            contentValues.put("userReachStandardDays", Double.valueOf(achieveInfo.getUserReachStandardDays()));
            contentValues.put("syncTimestamp", Long.valueOf(achieveInfo.getSyncTimestamp()));
            long insertStorageData = mea.b(this.b).insertStorageData("achieve_info", 1, contentValues);
            LogUtil.a("PLGACHIEVE_UserInfoDBMgr", "insert result=", Long.valueOf(insertStorageData));
            if (0 == insertStorageData) {
                c(achieveInfo.acquireMedals());
            }
            return insertStorageData;
        }
    }

    private void c(String str) {
        synchronized (d) {
            if (this.f8370a != null) {
                LogUtil.a("PLGACHIEVE_UserInfoDBMgr", "notifyAMRefresh!", str);
                this.f8370a.onMedalsChange(str);
            }
        }
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        synchronized (c) {
            if (mczVar == null) {
                LogUtil.h("PLGACHIEVE_UserInfoDBMgr", "delete achieveData is null");
                return -1;
            }
            AchieveInfo achieveInfo = mczVar instanceof AchieveInfo ? (AchieveInfo) mczVar : null;
            if (achieveInfo == null) {
                LogUtil.h("PLGACHIEVE_UserInfoDBMgr", "delete achieveInfo is null");
                return -1;
            }
            String[] strArr = {mef.e(achieveInfo.getHuid())};
            LogUtil.c("PLGACHIEVE_UserInfoDBMgr", "delete selection=", "huid=?");
            return mea.b(this.b).deleteStorageData("achieve_info", 1, "huid=?", strArr);
        }
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            LogUtil.h("PLGACHIEVE_UserInfoDBMgr", "update achieveData is null");
            return -1;
        }
        AchieveInfo achieveInfo = mczVar instanceof AchieveInfo ? (AchieveInfo) mczVar : null;
        if (achieveInfo == null) {
            return -1;
        }
        if (meg.a(achieveInfo) == -1) {
            LogUtil.a("PLGACHIEVE_UserInfoDBMgr", "update Column check not pass");
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", achieveInfo.getHuid());
        contentValues.put("reach_days", Integer.valueOf(achieveInfo.getUserLevel()));
        contentValues.put("points", Integer.valueOf(achieveInfo.getUserPoint()));
        String c2 = meg.c(achieveInfo.acquireMedals(), this.e);
        contentValues.put("medal_id", c2);
        contentValues.put("userReachStandardDays", Double.valueOf(achieveInfo.getUserReachStandardDays()));
        contentValues.put("syncTimestamp", Long.valueOf(achieveInfo.getSyncTimestamp()));
        int updateStorageData = mea.b(this.b).updateStorageData("achieve_info", 1, contentValues, "huid=?", new String[]{mef.e(achieveInfo.getHuid())});
        LogUtil.a("PLGACHIEVE_UserInfoDBMgr", "update allMds ", "result :", c2);
        if (updateStorageData == 0) {
            c(c2);
        }
        return updateStorageData;
    }
}
