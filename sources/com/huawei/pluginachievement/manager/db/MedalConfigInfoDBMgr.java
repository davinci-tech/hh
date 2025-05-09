package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import defpackage.meg;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class MedalConfigInfoDBMgr implements IAchieveDBMgr {
    private Context e;

    private static long c(long j, long j2) {
        return j > j2 ? j : j2;
    }

    private static int e(int i, int i2) {
        return i == 0 ? i : i2;
    }

    public MedalConfigInfoDBMgr(Context context) {
        this.e = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return d(map.get("medalID"), map.get("huid"));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return c(map);
    }

    private mcz d(String str, String str2) {
        MedalConfigInfo medalConfigInfo = null;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_MedalConfigInfoDBMgr", "MedalConfigInfoDBMgr, query ,id is null!return");
            return null;
        }
        String str3 = "select *  from " + mea.b(this.e).getTableFullName("medal_config_info_record") + " where medalID =? and huid=?";
        LogUtil.c("PLGACHIEVE_MedalConfigInfoDBMgr", "query selection=", str3);
        try {
            Cursor rawQueryStorageData = mea.b(this.e).rawQueryStorageData(1, str3, new String[]{mef.e(str), mef.e(str2)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        if (medalConfigInfo == null) {
                            medalConfigInfo = new MedalConfigInfo();
                        }
                        cga_(rawQueryStorageData, medalConfigInfo);
                    } finally {
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_MedalConfigInfoDBMgr", e.getMessage());
        }
        return medalConfigInfo;
    }

    private List<mcz> c(Map<String, String> map) {
        String str = map.get("huid");
        if (str == null) {
            LogUtil.a("PLGACHIEVE_MedalConfigInfoDBMgr", "MedalLocationDBMgr, query ,id is null!return");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        String str2 = "select *  from " + mea.b(this.e).getTableFullName("medal_config_info_record") + " where huid=?";
        String[] strArr = {mef.e(str)};
        if (map.containsKey(ParsedFieldTag.MEDAL_TYPE)) {
            str2 = str2 + " and medalType=?";
            strArr = new String[]{mef.e(str), mef.e(map.get(ParsedFieldTag.MEDAL_TYPE))};
        }
        LogUtil.c("PLGACHIEVE_MedalConfigInfoDBMgr", "query selection=", str2);
        try {
            Cursor rawQueryStorageData = mea.b(this.e).rawQueryStorageData(1, str2, strArr);
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        MedalConfigInfo medalConfigInfo = new MedalConfigInfo();
                        cga_(rawQueryStorageData, medalConfigInfo);
                        arrayList.add(medalConfigInfo);
                    } catch (Throwable th) {
                        if (rawQueryStorageData != null) {
                            try {
                                rawQueryStorageData.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLiteDatabaseCorruptException unused) {
            ReleaseLogUtil.c("PLGACHIEVE_MedalConfigInfoDBMgr", "SQLiteDatabaseCorruptException");
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_MedalConfigInfoDBMgr", e.getMessage());
        }
        LogUtil.c("PLGACHIEVE_MedalConfigInfoDBMgr", "query medalConfigInfoList=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        MedalConfigInfo medalConfigInfo = mczVar instanceof MedalConfigInfo ? (MedalConfigInfo) mczVar : null;
        if (medalConfigInfo == null) {
            return -1L;
        }
        if (d(medalConfigInfo.acquireMedalID(), medalConfigInfo.getHuid()) != null) {
            return d(r0, mczVar);
        }
        return c(medalConfigInfo) - 1;
    }

    private long c(MedalConfigInfo medalConfigInfo) {
        ContentValues contentValues = new ContentValues();
        if (!cfZ_(contentValues, medalConfigInfo)) {
            return -1L;
        }
        long insertStorageData = mea.b(this.e).insertStorageData("medal_config_info_record", 1, contentValues);
        LogUtil.a("PLGACHIEVE_MedalConfigInfoDBMgr", "insert insertMedalInfoResult=", Long.valueOf(insertStorageData));
        return insertStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        MedalConfigInfo medalConfigInfo = mczVar instanceof MedalConfigInfo ? (MedalConfigInfo) mczVar : null;
        if (medalConfigInfo == null) {
            return -1;
        }
        String[] strArr = {mef.e(medalConfigInfo.acquireMedalID()), mef.e(medalConfigInfo.getHuid())};
        LogUtil.c("PLGACHIEVE_MedalConfigInfoDBMgr", "delete selection=", "medalID=? and huid=?");
        int deleteStorageData = mea.b(this.e).deleteStorageData("medal_config_info_record", 1, "medalID=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_MedalConfigInfoDBMgr", "delete deleteMedalInfoResult=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        mcz d;
        if (mczVar == null) {
            return -1;
        }
        MedalConfigInfo medalConfigInfo = mczVar instanceof MedalConfigInfo ? (MedalConfigInfo) mczVar : null;
        if (medalConfigInfo == null || (d = d(medalConfigInfo.acquireMedalID(), medalConfigInfo.getHuid())) == null) {
            return -1;
        }
        return d(d, mczVar);
    }

    public int d(mcz mczVar, mcz mczVar2) {
        if (mczVar2 == null) {
            return -1;
        }
        if ((mczVar2 instanceof MedalConfigInfo ? (MedalConfigInfo) mczVar2 : null) == null) {
            return -1;
        }
        return b(mczVar, r4) - 1;
    }

    private int b(mcz mczVar, MedalConfigInfo medalConfigInfo) {
        ContentValues contentValues = new ContentValues();
        if (!cgb_(contentValues, medalConfigInfo, mczVar)) {
            return -1;
        }
        String[] strArr = {mef.e(medalConfigInfo.acquireMedalID()), mef.e(medalConfigInfo.getHuid())};
        LogUtil.c("PLGACHIEVE_MedalConfigInfoDBMgr", "update selection=", "medalID=? and huid=?");
        int updateStorageData = mea.b(this.e).updateStorageData("medal_config_info_record", 1, contentValues, "medalID=? and huid=?", strArr);
        LogUtil.a("PLGACHIEVE_MedalConfigInfoDBMgr", "update result=", Integer.valueOf(updateStorageData));
        return updateStorageData;
    }

    private boolean cgb_(ContentValues contentValues, MedalConfigInfo medalConfigInfo, mcz mczVar) {
        if (medalConfigInfo == null) {
            return false;
        }
        MedalConfigInfo medalConfigInfo2 = mczVar instanceof MedalConfigInfo ? (MedalConfigInfo) mczVar : null;
        if (medalConfigInfo2 == null) {
            return false;
        }
        cfY_(contentValues, medalConfigInfo, medalConfigInfo2);
        contentValues.put("activityId", Integer.valueOf(mea.d(medalConfigInfo2.acquireActivityId(), medalConfigInfo.acquireActivityId())));
        contentValues.put(ParsedFieldTag.ACTION_TYPE, Integer.valueOf(mea.d(medalConfigInfo2.acquireActionType(), medalConfigInfo.acquireActionType())));
        contentValues.put(ParsedFieldTag.GOAL, Integer.valueOf(mea.d(medalConfigInfo2.acquireGoal(), medalConfigInfo.acquireGoal())));
        contentValues.put(ParsedFieldTag.MEDAL_LEVEL, Integer.valueOf(mea.d(medalConfigInfo2.acquireMedalLevel(), medalConfigInfo.acquireMedalLevel())));
        contentValues.put("medalLabel", Integer.valueOf(mea.d(medalConfigInfo2.acquireMedalLabel(), medalConfigInfo.acquireMedalLabel())));
        contentValues.put("medalUnit", Integer.valueOf(mea.d(medalConfigInfo2.acquireMedalUnit(), medalConfigInfo.acquireMedalUnit())));
        contentValues.put("isNewConfig", Integer.valueOf(e(medalConfigInfo2.acquireIsNewConfig(), medalConfigInfo.acquireIsNewConfig())));
        contentValues.put(ParsedFieldTag.REPEATABLE, Integer.valueOf(mea.d(medalConfigInfo2.acquireRepeatable(), medalConfigInfo.acquireRepeatable())));
        contentValues.put("timestamp", Long.valueOf(c(medalConfigInfo2.acquireTimestamp(), medalConfigInfo.acquireTimestamp())));
        contentValues.put("reachStatus", Integer.valueOf(mea.d(medalConfigInfo2.acquireReachStatus(), medalConfigInfo.acquireReachStatus())));
        contentValues.put("eventStatus", Integer.valueOf(meg.b(medalConfigInfo2.acquireEventStatus(), medalConfigInfo.acquireEventStatus())));
        return true;
    }

    private void cfY_(ContentValues contentValues, MedalConfigInfo medalConfigInfo, MedalConfigInfo medalConfigInfo2) {
        contentValues.put("huid", medalConfigInfo.getHuid());
        contentValues.put("medalName", mea.a(medalConfigInfo2.acquireMedalName(), medalConfigInfo.acquireMedalName()));
        contentValues.put("medalID", medalConfigInfo.acquireMedalID());
        contentValues.put(ParsedFieldTag.MEDAL_TYPE, mea.a(medalConfigInfo2.acquireMedalType(), medalConfigInfo.acquireMedalType()));
        contentValues.put("message", mea.a(medalConfigInfo2.acquireMessage(), medalConfigInfo.acquireMessage()));
        contentValues.put(ParsedFieldTag.GRAY_DESC, mea.a(medalConfigInfo2.acquireGrayDescription(), medalConfigInfo.acquireGrayDescription()));
        contentValues.put(ParsedFieldTag.LIGHT_DESC, mea.a(medalConfigInfo2.acquireLightDescription(), medalConfigInfo.acquireLightDescription()));
        contentValues.put(ParsedFieldTag.GRAY_DETAIL_STYLE, mea.a(medalConfigInfo2.acquireGrayDetailStyle(), medalConfigInfo.acquireGrayDetailStyle()));
        contentValues.put(ParsedFieldTag.LIGHT_DETAIL_STYLE, mea.a(medalConfigInfo2.acquireLightDetailStyle(), medalConfigInfo.acquireLightDetailStyle()));
        contentValues.put(ParsedFieldTag.GRAY_PRO_NAME, mea.a(medalConfigInfo2.acquireGrayPromotionName(), medalConfigInfo.acquireGrayPromotionName()));
        contentValues.put(ParsedFieldTag.LIGHT_PRO_NAME, mea.a(medalConfigInfo2.acquireLightPromotionName(), medalConfigInfo.acquireLightPromotionName()));
        contentValues.put(ParsedFieldTag.GARY_PRO_URL, mea.a(medalConfigInfo2.acquireGrayPromotionUrl(), medalConfigInfo.acquireGrayPromotionUrl()));
        contentValues.put(ParsedFieldTag.LIGHT_PRO_URL, mea.a(medalConfigInfo2.acquireLightPromotionUrl(), medalConfigInfo.acquireLightPromotionUrl()));
        contentValues.put(ParsedFieldTag.GRAY_LIST_STYLE, mea.a(medalConfigInfo2.acquireGrayListStyle(), medalConfigInfo.acquireGrayListStyle()));
        contentValues.put(ParsedFieldTag.LIGHT_LIST_STYLE, mea.a(medalConfigInfo2.acquireLightListStyle(), medalConfigInfo.acquireLightListStyle()));
        contentValues.put(ParsedFieldTag.SHARE_IMAGE_URL, mea.a(medalConfigInfo2.acquireShareImageUrl(), medalConfigInfo.acquireShareImageUrl()));
        contentValues.put("location", mea.a(medalConfigInfo2.acquireLocation(), medalConfigInfo.acquireLocation()));
        contentValues.put("startTime", mea.a(medalConfigInfo2.acquireStartTime(), medalConfigInfo.acquireStartTime()));
        contentValues.put("endTime", mea.a(medalConfigInfo2.acquireEndTime(), medalConfigInfo.acquireEndTime()));
        contentValues.put(ParsedFieldTag.TAKE_EFFECT_TIME, mea.a(medalConfigInfo2.acquireTakeEffectTime(), medalConfigInfo.acquireTakeEffectTime()));
        contentValues.put(ParsedFieldTag.CLIENT_TYPES, mea.a(medalConfigInfo2.getClientTypes(), medalConfigInfo.getClientTypes()));
        contentValues.put(ParsedFieldTag.PHONE_TYPES, mea.a(medalConfigInfo2.getPhoneTypes(), medalConfigInfo.getPhoneTypes()));
    }

    private boolean cfZ_(ContentValues contentValues, MedalConfigInfo medalConfigInfo) {
        if (medalConfigInfo == null || medalConfigInfo.acquireMedalID() == null) {
            return false;
        }
        contentValues.put("huid", medalConfigInfo.getHuid());
        contentValues.put("medalName", b(medalConfigInfo.acquireMedalName()));
        contentValues.put("medalID", medalConfigInfo.acquireMedalID());
        contentValues.put(ParsedFieldTag.MEDAL_TYPE, medalConfigInfo.acquireMedalType());
        contentValues.put(ParsedFieldTag.MEDAL_LEVEL, Integer.valueOf(medalConfigInfo.acquireMedalLevel()));
        contentValues.put("medalUnit", Integer.valueOf(medalConfigInfo.acquireMedalUnit()));
        contentValues.put("medalLabel", Integer.valueOf(medalConfigInfo.acquireMedalLabel()));
        contentValues.put("activityId", Integer.valueOf(medalConfigInfo.acquireActivityId()));
        contentValues.put("message", medalConfigInfo.acquireMessage());
        contentValues.put(ParsedFieldTag.GRAY_DESC, medalConfigInfo.acquireGrayDescription());
        contentValues.put(ParsedFieldTag.LIGHT_DESC, medalConfigInfo.acquireLightDescription());
        contentValues.put(ParsedFieldTag.GRAY_DETAIL_STYLE, medalConfigInfo.acquireGrayDetailStyle());
        contentValues.put(ParsedFieldTag.LIGHT_DETAIL_STYLE, medalConfigInfo.acquireLightDetailStyle());
        contentValues.put(ParsedFieldTag.GRAY_PRO_NAME, medalConfigInfo.acquireGrayPromotionName());
        contentValues.put(ParsedFieldTag.LIGHT_PRO_NAME, medalConfigInfo.acquireLightPromotionName());
        contentValues.put(ParsedFieldTag.GARY_PRO_URL, medalConfigInfo.acquireGrayPromotionUrl());
        contentValues.put(ParsedFieldTag.LIGHT_PRO_URL, medalConfigInfo.acquireLightPromotionUrl());
        contentValues.put(ParsedFieldTag.GRAY_LIST_STYLE, medalConfigInfo.acquireGrayListStyle());
        contentValues.put(ParsedFieldTag.LIGHT_LIST_STYLE, medalConfigInfo.acquireLightListStyle());
        contentValues.put(ParsedFieldTag.SHARE_IMAGE_URL, medalConfigInfo.acquireShareImageUrl());
        contentValues.put("location", medalConfigInfo.acquireLocation());
        contentValues.put(ParsedFieldTag.ACTION_TYPE, Integer.valueOf(medalConfigInfo.acquireActionType()));
        contentValues.put(ParsedFieldTag.GOAL, Integer.valueOf(medalConfigInfo.acquireGoal()));
        contentValues.put("startTime", medalConfigInfo.acquireStartTime());
        contentValues.put("endTime", medalConfigInfo.acquireEndTime());
        contentValues.put(ParsedFieldTag.TAKE_EFFECT_TIME, medalConfigInfo.acquireTakeEffectTime());
        contentValues.put(ParsedFieldTag.CLIENT_TYPES, medalConfigInfo.getClientTypes());
        contentValues.put(ParsedFieldTag.PHONE_TYPES, medalConfigInfo.getPhoneTypes());
        contentValues.put("isNewConfig", Integer.valueOf(medalConfigInfo.acquireIsNewConfig()));
        contentValues.put(ParsedFieldTag.REPEATABLE, Integer.valueOf(medalConfigInfo.acquireRepeatable()));
        contentValues.put("timestamp", Long.valueOf(medalConfigInfo.acquireTimestamp()));
        contentValues.put("reachStatus", Integer.valueOf(medalConfigInfo.acquireReachStatus()));
        contentValues.put("eventStatus", Integer.valueOf(medalConfigInfo.acquireEventStatus()));
        return true;
    }

    private void cga_(Cursor cursor, MedalConfigInfo medalConfigInfo) {
        if (cursor == null) {
            return;
        }
        try {
            medalConfigInfo.setHuid(cursor.getString(cursor.getColumnIndex("huid")));
            cfX_(cursor, medalConfigInfo);
            int i = cursor.getInt(cursor.getColumnIndex(ParsedFieldTag.GOAL));
            int i2 = cursor.getInt(cursor.getColumnIndex(ParsedFieldTag.ACTION_TYPE));
            String string = cursor.getString(cursor.getColumnIndex("location"));
            int i3 = cursor.getInt(cursor.getColumnIndex("activityId"));
            String string2 = cursor.getString(cursor.getColumnIndex("startTime"));
            String string3 = cursor.getString(cursor.getColumnIndex("endTime"));
            String string4 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.TAKE_EFFECT_TIME));
            String string5 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.CLIENT_TYPES));
            String string6 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.PHONE_TYPES));
            int i4 = cursor.getInt(cursor.getColumnIndex(ParsedFieldTag.REPEATABLE));
            int i5 = cursor.getInt(cursor.getColumnIndex("isNewConfig"));
            long j = cursor.getLong(cursor.getColumnIndex("timestamp"));
            int i6 = cursor.getInt(cursor.getColumnIndex(ParsedFieldTag.MEDAL_LEVEL));
            int i7 = cursor.getInt(cursor.getColumnIndex("medalLabel"));
            int i8 = cursor.getInt(cursor.getColumnIndex("medalUnit"));
            int i9 = cursor.getInt(cursor.getColumnIndex("reachStatus"));
            int i10 = cursor.getInt(cursor.getColumnIndex("eventStatus"));
            medalConfigInfo.saveActivityId(i3);
            medalConfigInfo.saveLocation(string);
            medalConfigInfo.saveActionType(i2);
            medalConfigInfo.saveGoal(i);
            medalConfigInfo.saveStartTime(string2);
            medalConfigInfo.saveEndTime(string3);
            medalConfigInfo.saveTakeEffectTime(string4);
            medalConfigInfo.setClientTypes(string5);
            medalConfigInfo.setPhoneTypes(string6);
            medalConfigInfo.saveIsNewConfig(i5);
            medalConfigInfo.saveRepeatable(i4);
            medalConfigInfo.saveTimestamp(j);
            medalConfigInfo.saveEventStatus(i9);
            medalConfigInfo.saveReachStatus(i10);
            medalConfigInfo.saveMedalLevel(i6);
            medalConfigInfo.saveMedalLabel(i7);
            medalConfigInfo.saveMedalUnit(i8);
        } catch (SQLiteException | IllegalStateException e) {
            LogUtil.b("PLGACHIEVE_MedalConfigInfoDBMgr", "structMedalInfoData: exception -> " + e.getMessage());
        }
    }

    private void cfX_(Cursor cursor, MedalConfigInfo medalConfigInfo) {
        String string = cursor.getString(cursor.getColumnIndex("medalName"));
        String string2 = cursor.getString(cursor.getColumnIndex("medalID"));
        String string3 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.MEDAL_TYPE));
        String string4 = cursor.getString(cursor.getColumnIndex("message"));
        String string5 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.GRAY_DESC));
        String string6 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.LIGHT_DESC));
        String string7 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.GRAY_PRO_NAME));
        String string8 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.GARY_PRO_URL));
        String string9 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.LIGHT_PRO_NAME));
        String string10 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.LIGHT_PRO_URL));
        String string11 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.GRAY_LIST_STYLE));
        String string12 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.LIGHT_LIST_STYLE));
        String string13 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.GRAY_DETAIL_STYLE));
        String string14 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.LIGHT_DETAIL_STYLE));
        String string15 = cursor.getString(cursor.getColumnIndex(ParsedFieldTag.SHARE_IMAGE_URL));
        medalConfigInfo.saveMedalType(string3);
        medalConfigInfo.saveMedalName(string);
        medalConfigInfo.saveMedalID(string2);
        medalConfigInfo.saveMessage(string4);
        medalConfigInfo.saveGrayDescription(string5);
        medalConfigInfo.saveLightDescription(string6);
        medalConfigInfo.saveGrayDetailStyle(string13);
        medalConfigInfo.saveLightDetailStyle(string14);
        medalConfigInfo.saveGrayPromotionName(string7);
        medalConfigInfo.saveLightPromotionName(string9);
        medalConfigInfo.saveGrayPromotionUrl(string8);
        medalConfigInfo.saveLightPromotionUrl(string10);
        medalConfigInfo.saveGrayListStyle(string11);
        medalConfigInfo.saveLightListStyle(string12);
        medalConfigInfo.saveShareImageUrl(string15);
    }

    private static String b(String str) {
        return str == null ? "" : str;
    }
}
