package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.InsightRankRecord;
import defpackage.mcz;
import defpackage.mea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes6.dex */
public class InsightRankRecordDBMgr implements IAchieveDBMgr {
    private Context b;

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        return 0;
    }

    public InsightRankRecordDBMgr(Context context) {
        this.b = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        String str = map.get("ageSegment");
        String str2 = map.get(CommonConstant.KEY_GENDER);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("PLGACHIEVE_InsightRankRecordDBMgr", "params is null");
            return Collections.emptyList();
        }
        String str3 = "select *  from " + mea.b(this.b).getTableFullName("insight_rank") + " where ageSegment =? and gender =? ";
        LogUtil.c("PLGACHIEVE_InsightRankRecordDBMgr", "query selection=", str3);
        String[] strArr = {str, str2};
        ArrayList arrayList = new ArrayList(8);
        try {
            Cursor rawQueryStorageData = mea.b(this.b).rawQueryStorageData(1, str3, strArr);
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        InsightRankRecord insightRankRecord = new InsightRankRecord();
                        insightRankRecord.setRankType(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("rankType")));
                        insightRankRecord.setRankVersion(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("rankVersion")));
                        insightRankRecord.setAgeSegment(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("ageSegment")));
                        insightRankRecord.setGender(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(CommonConstant.KEY_GENDER)));
                        insightRankRecord.setInsightRankItemJsonArray(c(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("insightRankItemList"))));
                        arrayList.add(insightRankRecord);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_InsightRankRecordDBMgr", e.getMessage());
        }
        LogUtil.a("PLGACHIEVE_InsightRankRecordDBMgr", "query insightRankList =", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        String str = map.get("ageSegment");
        String str2 = map.get(CommonConstant.KEY_GENDER);
        String str3 = map.get("rankType");
        InsightRankRecord insightRankRecord = null;
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("PLGACHIEVE_InsightRankRecordDBMgr", "params is null");
            return null;
        }
        LogUtil.a("PLGACHIEVE_InsightRankRecordDBMgr", "query inSightRankRecord =", str3);
        String str4 = "select *  from " + mea.b(this.b).getTableFullName("insight_rank") + " where rankType =? and ageSegment =? and gender =? ";
        LogUtil.c("PLGACHIEVE_InsightRankRecordDBMgr", "query selection=", str4);
        try {
            Cursor rawQueryStorageData = mea.b(this.b).rawQueryStorageData(1, str4, new String[]{str3, str, str2});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (insightRankRecord == null) {
                            insightRankRecord = new InsightRankRecord();
                        }
                        insightRankRecord.setRankType(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("rankType")));
                        insightRankRecord.setRankVersion(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("rankVersion")));
                        insightRankRecord.setAgeSegment(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("ageSegment")));
                        insightRankRecord.setGender(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(CommonConstant.KEY_GENDER)));
                        insightRankRecord.setInsightRankItemJsonArray(c(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("insightRankItemList"))));
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_InsightRankRecordDBMgr", e.getMessage());
        }
        return insightRankRecord;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        InsightRankRecord insightRankRecord = mczVar instanceof InsightRankRecord ? (InsightRankRecord) mczVar : null;
        if (insightRankRecord == null) {
            return -1L;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("rankType", insightRankRecord.getRankType());
        hashMap.put("ageSegment", insightRankRecord.getAgeSegment());
        hashMap.put(CommonConstant.KEY_GENDER, insightRankRecord.getGender());
        if (query(hashMap) != null) {
            return update(insightRankRecord);
        }
        return mea.b(this.b).insertStorageData("insight_rank", 1, cfT_(insightRankRecord));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        InsightRankRecord insightRankRecord = mczVar instanceof InsightRankRecord ? (InsightRankRecord) mczVar : null;
        if (insightRankRecord == null) {
            return -1;
        }
        StringBuffer stringBuffer = new StringBuffer(10);
        stringBuffer.append("rankType =? and ageSegment =? and gender =? ");
        int updateStorageData = mea.b(this.b).updateStorageData("insight_rank", 1, cfT_(insightRankRecord), stringBuffer.toString(), new String[]{insightRankRecord.getRankType(), insightRankRecord.getAgeSegment(), insightRankRecord.getGender()});
        LogUtil.c("PLGACHIEVE_InsightRankRecordDBMgr", "update result = ", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }

    private ContentValues cfT_(InsightRankRecord insightRankRecord) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("rankType", insightRankRecord.getRankType());
        contentValues.put("rankVersion", Long.valueOf(insightRankRecord.getRankVersion()));
        contentValues.put("ageSegment", insightRankRecord.getAgeSegment());
        contentValues.put(CommonConstant.KEY_GENDER, insightRankRecord.getGender());
        contentValues.put("insightRankItemList", insightRankRecord.getInsightRankItemJsonArray().toString());
        return contentValues;
    }

    private static JSONArray c(String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            return !TextUtils.isEmpty(str) ? new JSONArray(str) : jSONArray;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_InsightRankRecordDBMgr", e.getMessage());
            return jSONArray;
        }
    }
}
