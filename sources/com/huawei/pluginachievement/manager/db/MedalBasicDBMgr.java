package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import com.huawei.pluginachievement.manager.model.MedalBasic;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class MedalBasicDBMgr implements IAchieveDBMgr {
    private Context d;

    public MedalBasicDBMgr(Context context) {
        this.d = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return e(map.get("huid"));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return e(map.get("medalID"), map.get("huid"));
    }

    private mcz e(String str, String str2) {
        MedalBasic medalBasic = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_MedalBasicDBMgr", "MedalBasicDBMgr, query ,id is null!return");
            return null;
        }
        String str3 = "select *  from " + mea.b(this.d).getTableFullName("medal_basic_record") + " where medalID =? and huid=?";
        LogUtil.c("PLGACHIEVE_MedalBasicDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.d).rawQueryStorageData(1, str3, new String[]{mef.e(str), mef.e(str2)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (medalBasic == null) {
                            medalBasic = new MedalBasic();
                        }
                        medalBasic.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        medalBasic.saveMedalName(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalName")));
                        medalBasic.saveVeinUrl(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.MEDAL_URL)));
                        medalBasic.saveTimestamp(mea.cfR_(rawQueryStorageData, UserCloseRecord.TIME_STAMP));
                        medalBasic.saveMedalID(mea.cfS_(rawQueryStorageData, "medalID"));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_MedalBasicDBMgr", e.getMessage());
        }
        return medalBasic;
    }

    private List<mcz> e(String str) {
        if (str == null) {
            LogUtil.a("PLGACHIEVE_MedalBasicDBMgr", "MedalLocationDBMgr, query ,id is null!return");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        String str2 = "select *  from " + mea.b(this.d).getTableFullName("medal_basic_record") + " where huid=?";
        LogUtil.c("PLGACHIEVE_MedalBasicDBMgr", "query selection=", str2);
        try {
            Cursor rawQueryStorageData = mea.b(this.d).rawQueryStorageData(1, str2, new String[]{mef.e(str)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        MedalBasic medalBasic = new MedalBasic();
                        medalBasic.setHuid(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid")));
                        String string = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("medalName"));
                        String string2 = rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.MEDAL_URL));
                        long cfR_ = mea.cfR_(rawQueryStorageData, UserCloseRecord.TIME_STAMP);
                        String cfS_ = mea.cfS_(rawQueryStorageData, "medalID");
                        medalBasic.saveMedalName(string);
                        medalBasic.saveVeinUrl(string2);
                        medalBasic.saveTimestamp(cfR_);
                        medalBasic.saveMedalID(cfS_);
                        arrayList.add(medalBasic);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_MedalBasicDBMgr", e.getMessage());
        }
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        MedalBasic medalBasic = mczVar instanceof MedalBasic ? (MedalBasic) mczVar : null;
        if (medalBasic == null) {
            return -1L;
        }
        if (e(medalBasic.acquireMedalID(), medalBasic.getHuid()) != null) {
            return update(mczVar);
        }
        return c(medalBasic) - 1;
    }

    private long c(MedalBasic medalBasic) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", medalBasic.getHuid());
        contentValues.put("medalName", medalBasic.acquireMedalName());
        contentValues.put(ParsedFieldTag.MEDAL_URL, medalBasic.acquireVeinUrl());
        contentValues.put(UserCloseRecord.TIME_STAMP, Long.valueOf(medalBasic.acquireTimestamp()));
        contentValues.put("medalID", medalBasic.acquireMedalID());
        long insertStorageData = mea.b(this.d).insertStorageData("medal_basic_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_MedalBasicDBMgr", "insert insertMedalResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        MedalBasic medalBasic = mczVar instanceof MedalBasic ? (MedalBasic) mczVar : null;
        if (medalBasic == null) {
            return -1;
        }
        String[] strArr = {mef.e(medalBasic.acquireMedalName())};
        LogUtil.c("PLGACHIEVE_MedalBasicDBMgr", "delete selection=", "medalName=?");
        int deleteStorageData = mea.b(this.d).deleteStorageData("medal_basic_record", 1, "medalName=?", strArr);
        LogUtil.a("PLGACHIEVE_MedalBasicDBMgr", "delete deleteMedalResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        if ((mczVar instanceof MedalBasic ? (MedalBasic) mczVar : null) == null) {
            return -1;
        }
        return b(r3) - 1;
    }

    private int b(MedalBasic medalBasic) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", medalBasic.getHuid());
        contentValues.put("medalName", medalBasic.acquireMedalName());
        contentValues.put(ParsedFieldTag.MEDAL_URL, medalBasic.acquireVeinUrl());
        contentValues.put(UserCloseRecord.TIME_STAMP, Long.valueOf(medalBasic.acquireTimestamp()));
        contentValues.put("medalID", medalBasic.acquireMedalID());
        String[] strArr = {mef.e(medalBasic.acquireMedalID())};
        LogUtil.c("PLGACHIEVE_MedalBasicDBMgr", "update selection=", "medalID=?");
        int updateStorageData = mea.b(this.d).updateStorageData("medal_basic_record", 1, contentValues, "medalID=?", strArr);
        LogUtil.a("PLGACHIEVE_MedalBasicDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }
}
