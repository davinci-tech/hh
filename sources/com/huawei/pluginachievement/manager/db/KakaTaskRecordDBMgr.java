package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.jec;
import defpackage.koq;
import defpackage.mcz;
import defpackage.mdf;
import defpackage.mea;
import defpackage.mef;
import defpackage.mle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public class KakaTaskRecordDBMgr implements IAchieveDBMgr {
    private Context c;

    public KakaTaskRecordDBMgr(Context context) {
        this.c = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        if (map == null || TextUtils.isEmpty(map.get("huid"))) {
            LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "queryAllKakaInfo, query, user id is null!return");
            return Collections.emptyList();
        }
        StringBuilder sb = new StringBuilder("select *  from ");
        sb.append(mea.b(this.c).getTableFullName("kaka_task_record"));
        sb.append(" where ");
        String[] strArr = new String[map.size() + 3];
        int i = 0;
        long j = 0;
        long j2 = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (!TextUtils.isEmpty(value)) {
                String key = entry.getKey();
                try {
                    if ("taskStartTime".equals(key)) {
                        j = Long.parseLong(value);
                    } else if ("taskEndTime".equals(key)) {
                        j2 = Long.parseLong(value);
                    } else {
                        if ("taskRules".equals(key)) {
                            sb.append("taskRule in ? and ");
                        } else {
                            sb.append(key);
                            sb.append(CommonUtil.EQUAL_END);
                        }
                        strArr[i] = mef.e(value);
                        i++;
                    }
                } catch (NumberFormatException e) {
                    LogUtil.b("PLGACHIEVE_KakaTaskRecordDBMgr", "queryAll: exception -> " + e.getMessage());
                }
            }
        }
        sb.append("timeZoneOffset =? and ");
        int i2 = i + 1;
        strArr[i] = mef.e(Integer.valueOf(TimeZone.getDefault().getRawOffset()));
        if (j <= 0) {
            j = jec.n(new Date());
        }
        if (j2 <= 0) {
            j2 = jec.k(new Date());
        }
        sb.append("taskSyncTimeStamp between ? and ? order by taskPriority desc");
        strArr[i2] = mef.e(Long.valueOf(j * 1000));
        strArr[i + 2] = mef.e(Long.valueOf(j2 * 1000));
        return c(sb.toString(), strArr);
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        List<mcz> queryAll = queryAll(map);
        if (koq.b(queryAll)) {
            return null;
        }
        return queryAll.get(0);
    }

    private mcz e(String str, String str2) {
        mdf mdfVar = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "KakaTaskRecordDBMgr, query ,id is null!return");
            return null;
        }
        String str3 = "select *  from " + mea.b(this.c).getTableFullName("kaka_task_record") + " where taskId =? and huid=?";
        LogUtil.c("PLGACHIEVE_KakaTaskRecordDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.c).rawQueryStorageData(1, str3, new String[]{mef.e(str), mef.e(str2)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (mdfVar == null) {
                            mdfVar = new mdf();
                        }
                        mdfVar.e(str);
                        cfV_(rawQueryStorageData, mdfVar);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_KakaTaskRecordDBMgr", e.getMessage());
        }
        return mdfVar;
    }

    private List<mcz> c(String str, String[] strArr) {
        if (strArr == null) {
            LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "queryKakaInfoList, query ,param is null!return");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        LogUtil.c("PLGACHIEVE_KakaTaskRecordDBMgr", "query selection=", str);
        try {
            Cursor rawQueryStorageData = mea.b(this.c).rawQueryStorageData(1, str, strArr);
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        mdf mdfVar = new mdf();
                        mdfVar.e(mea.cfS_(rawQueryStorageData, "taskId"));
                        cfV_(rawQueryStorageData, mdfVar);
                        arrayList.add(mdfVar);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (Exception e) {
            LogUtil.h("PLGACHIEVE_KakaTaskRecordDBMgr", e.getMessage());
        }
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        mdf mdfVar = mczVar instanceof mdf ? (mdf) mczVar : null;
        if (mdfVar == null) {
            return -1L;
        }
        mcz e = e(mdfVar.h(), mdfVar.getHuid());
        if (e == null) {
            LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "queryAchieveData is null");
        }
        if (e instanceof mdf) {
            return e(mdfVar, (mdf) e);
        }
        return e(mdfVar) - 1;
    }

    private long e(mdf mdfVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", mdfVar.getHuid());
        if (!cfU_(contentValues, mdfVar)) {
            LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "insert result is Invalid=", -1);
            return -1L;
        }
        long insertStorageData = mea.b(this.c).insertStorageData("kaka_task_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "insert insertKakaResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        mdf mdfVar = mczVar instanceof mdf ? (mdf) mczVar : null;
        if (mdfVar == null) {
            return -1;
        }
        String[] strArr = {mef.e(mdfVar.h())};
        LogUtil.c("PLGACHIEVE_KakaTaskRecordDBMgr", "delete selection=", "taskId=?");
        int deleteStorageData = mea.b(this.c).deleteStorageData("kaka_task_record", 1, "taskId=?", strArr);
        LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "delete deleteKakaResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        mdf mdfVar = mczVar instanceof mdf ? (mdf) mczVar : null;
        if (mdfVar == null) {
            return -1;
        }
        mcz e = e(mdfVar.h(), mdfVar.getHuid());
        if (e instanceof mdf) {
            return (-1) + e(mdfVar, (mdf) e);
        }
        return -1;
    }

    private int e(mdf mdfVar, mdf mdfVar2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", mdfVar.getHuid());
        if (!cfW_(contentValues, mdfVar, mdfVar2)) {
            LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "update status error");
            return -1;
        }
        String[] strArr = {mef.e(mdfVar.h()), mef.e(mdfVar.getHuid())};
        LogUtil.c("PLGACHIEVE_KakaTaskRecordDBMgr", "update selection=", "taskId=? and huid=?");
        int updateStorageData = mea.b(this.c).updateStorageData("kaka_task_record", 1, contentValues, "taskId=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "KakaTaskInfo update result=", Integer.valueOf(updateStorageData), " sc ", Integer.valueOf(mdfVar.ac()), " name ", mdfVar.f(), " status ", mdfVar.p(), " modifyTime ", Long.valueOf(mdfVar.c()));
        return updateStorageData;
    }

    private mdf cfV_(Cursor cursor, mdf mdfVar) {
        mdfVar.setHuid(cursor.getString(cursor.getColumnIndex("huid")));
        mdfVar.i(mea.cfS_(cursor, "taskStatus"));
        mdfVar.g(mea.cfS_(cursor, ParsedFieldTag.TASK_NAME));
        mdfVar.d(mea.cfS_(cursor, "taskDes"));
        mdfVar.f(mea.cfS_(cursor, "taskSpecification"));
        mdfVar.c(mea.cfS_(cursor, "taskDetailUrl"));
        mdfVar.b(mea.cfQ_(cursor, "taskFrequency"));
        mdfVar.a(mea.cfQ_(cursor, "taskRewardKaKa"));
        mdfVar.c(mea.cfQ_(cursor, "taskRewardExperience"));
        mdfVar.j(mea.cfQ_(cursor, "taskTypes"));
        mdfVar.b(mea.cfS_(cursor, "taskConditions"));
        mdfVar.c(mea.cfR_(cursor, "eventTimeStamp"));
        mdfVar.a(mea.cfR_(cursor, "taskSyncTimeStamp"));
        mdfVar.e(mea.cfR_(cursor, "lastTimeStamp"));
        mdfVar.b(mea.cfR_(cursor, "taskStartTime"));
        mdfVar.d(mea.cfR_(cursor, "taskEndTime"));
        mdfVar.e(mea.cfQ_(cursor, "taskPriority"));
        mdfVar.a(mea.cfS_(cursor, ParsedFieldTag.TASK_ICON));
        mdfVar.h(mea.cfQ_(cursor, "taskSyncStatus"));
        mdfVar.d(mea.cfQ_(cursor, "taskIsShow"));
        mdfVar.o(mea.cfS_(cursor, "preButton"));
        mdfVar.n(mea.cfS_(cursor, "postButton"));
        mdfVar.g(mea.cfQ_(cursor, "category"));
        mdfVar.q(mea.cfQ_(cursor, ParsedFieldTag.KAKA_TASK_RULE));
        mdfVar.m(mea.cfQ_(cursor, "level"));
        mdfVar.i(mea.cfQ_(cursor, ParsedFieldTag.KAKA_TASK_BONUS_TYPE));
        mdfVar.i(mea.cfR_(cursor, "updateTime"));
        mdfVar.f(mea.cfR_(cursor, "timeZoneOffset"));
        mdfVar.n(mea.cfQ_(cursor, ParsedFieldTag.KAKA_TASK_SCENARIO));
        mdfVar.r(mea.cfQ_(cursor, ParsedFieldTag.KAKA_TASK_VIP_MATCH));
        mdfVar.j(mea.cfS_(cursor, "pictureUrl"));
        mdfVar.h(mea.cfS_(cursor, ParsedFieldTag.KAKA_TASK_BTN_COLOR));
        mdfVar.l(mea.cfQ_(cursor, ParsedFieldTag.KAKA_TASK_LABLE_MATCH));
        return mdfVar;
    }

    private boolean cfU_(ContentValues contentValues, mdf mdfVar) {
        if (mdfVar == null || TextUtils.isEmpty(mdfVar.h())) {
            return false;
        }
        contentValues.put("taskId", mdfVar.h());
        contentValues.put(ParsedFieldTag.TASK_NAME, mdfVar.f());
        contentValues.put("taskStatus", mdfVar.p());
        contentValues.put("taskDes", mdfVar.b());
        contentValues.put("taskSpecification", mdfVar.o());
        contentValues.put("taskDetailUrl", mdfVar.e());
        contentValues.put("taskStartTime", Long.valueOf(mdfVar.k()));
        contentValues.put("taskEndTime", Long.valueOf(mdfVar.d()));
        contentValues.put("lastTimeStamp", Long.valueOf(mdfVar.c()));
        contentValues.put("taskConditions", mdfVar.a());
        contentValues.put("taskFrequency", Integer.valueOf(mdfVar.j()));
        contentValues.put("taskRewardKaKa", Integer.valueOf(mdfVar.n()));
        contentValues.put("taskRewardExperience", Integer.valueOf(mdfVar.l()));
        contentValues.put("taskTypes", Integer.valueOf(mdfVar.s()));
        contentValues.put("eventTimeStamp", Long.valueOf(mdfVar.d()));
        contentValues.put("taskSyncTimeStamp", Long.valueOf(mdfVar.q()));
        contentValues.put("taskPriority", Integer.valueOf(mdfVar.m()));
        contentValues.put(ParsedFieldTag.TASK_ICON, mdfVar.g());
        contentValues.put("taskSyncStatus", Integer.valueOf(mdfVar.t()));
        contentValues.put("taskIsShow", Integer.valueOf(mdfVar.i()));
        contentValues.put("preButton", mdfVar.z());
        contentValues.put("postButton", mdfVar.aa());
        contentValues.put("category", Integer.valueOf(mdfVar.x()));
        contentValues.put(ParsedFieldTag.KAKA_TASK_RULE, Integer.valueOf(mdfVar.ag()));
        contentValues.put("level", Integer.valueOf(mdfVar.ad()));
        contentValues.put(ParsedFieldTag.KAKA_TASK_BONUS_TYPE, Integer.valueOf(mdfVar.u()));
        contentValues.put("updateTime", Long.valueOf(mdfVar.ah()));
        contentValues.put("timeZoneOffset", Integer.valueOf(TimeZone.getDefault().getRawOffset()));
        contentValues.put(ParsedFieldTag.KAKA_TASK_SCENARIO, Integer.valueOf(mdfVar.ac()));
        contentValues.put(ParsedFieldTag.KAKA_TASK_VIP_MATCH, Integer.valueOf(mdfVar.ae()));
        contentValues.put("pictureUrl", mdfVar.ab());
        contentValues.put(ParsedFieldTag.KAKA_TASK_BTN_COLOR, mdfVar.v());
        contentValues.put(ParsedFieldTag.KAKA_TASK_LABLE_MATCH, Integer.valueOf(mdfVar.w()));
        return true;
    }

    private boolean cfW_(ContentValues contentValues, mdf mdfVar, mdf mdfVar2) {
        if (mdfVar2.d() >= mdfVar.d()) {
            mdfVar.c(mdfVar2.d());
        }
        if (mdfVar.r() == 1) {
            if (mdfVar.ag() == 30008 && mdfVar.j() > 0) {
                cfU_(contentValues, mdfVar);
                return true;
            }
            if (mdfVar.p().compareTo(mdfVar2.p()) < 0 && TimeZone.getDefault().getRawOffset() == mdfVar2.ai() && d(mdfVar)) {
                LogUtil.a("PLGACHIEVE_KakaTaskRecordDBMgr", "The cloud state is smaller than app, modifyTime ", Long.valueOf(mdfVar.c()));
                mdfVar.i(mdfVar2.p());
            }
        }
        if (!mle.a(mdfVar.c(), System.currentTimeMillis())) {
            mdfVar.g(mdfVar2.f());
        }
        cfU_(contentValues, mdfVar);
        return true;
    }

    private boolean d(mdf mdfVar) {
        return (mle.a(mdfVar.c(), System.currentTimeMillis()) && mle.a(mdfVar.d(), System.currentTimeMillis())) ? false : true;
    }
}
