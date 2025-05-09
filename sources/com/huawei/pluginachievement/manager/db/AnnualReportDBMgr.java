package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.mcz;
import defpackage.mdx;
import defpackage.mdy;
import defpackage.mea;
import defpackage.mef;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class AnnualReportDBMgr implements IAchieveDBMgr {
    private Context e;

    public AnnualReportDBMgr(Context context) {
        this.e = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return d(map.get("huid"), map.get("type"), map.get("year"));
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        return c(map.get("type"), map.get("huid"), map.get(MedalConstants.EVENT_KEY), map.get("year"));
    }

    public mcz c(String str, String str2, String str3, String str4) {
        String str5 = "select *  from " + mea.b(this.e).getTableFullName("annual_info") + " where type =? and huid=? and key=? and year=?";
        LogUtil.c("PLGACHIEVE_AnnualReportDBMgr", "query selection=", str5);
        mcz mczVar = null;
        try {
            Cursor rawQueryStorageData = mea.b(this.e).rawQueryStorageData(1, str5, new String[]{mef.e(str2), mef.e(str), mef.e(str3), mef.e(str4)});
            if (rawQueryStorageData != null) {
                while (rawQueryStorageData.moveToNext()) {
                    try {
                        mcz mczVar2 = new mcz(19);
                        try {
                            mczVar2.setHuid(str);
                            mczVar2.setType(str2);
                            mczVar2.setYear(mdy.cgl_(rawQueryStorageData, "year"));
                            mczVar2.setKey(mdy.cgl_(rawQueryStorageData, MedalConstants.EVENT_KEY));
                            mczVar2.setValues(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("value")));
                            mczVar = mczVar2;
                        } catch (Throwable th) {
                            th = th;
                            mczVar = mczVar2;
                            if (rawQueryStorageData != null) {
                                try {
                                    rawQueryStorageData.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_AnnualReportDBMgr", e.getMessage());
        }
        return mczVar;
    }

    public List<mcz> d(String str, String str2, String str3) {
        List<Integer> c = mdx.c(str2, str3);
        if (c == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(c.size());
        Iterator<Integer> it = c.iterator();
        while (it.hasNext()) {
            mcz c2 = c(str, str2, String.valueOf(it.next().intValue()), str3);
            if (c2 != null) {
                arrayList.add(c2);
            }
        }
        LogUtil.a("PLGACHIEVE_AnnualReportDBMgr", "querySetData size=", Integer.valueOf(arrayList.size()), " setType=", str2);
        return arrayList;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        if (mczVar == null) {
            return -1L;
        }
        String huid = mczVar.getHuid();
        String type = mczVar.getType();
        int key = mczVar.getKey();
        int year = mczVar.getYear();
        return d(huid, type, String.valueOf(key), String.valueOf(year), mczVar.getValues()) - 1;
    }

    public long d(String str, String str2, String str3, String str4, String str5) {
        if (c(str, str2, str3, str4) != null) {
            return a(str, str2, str3, str4, str5);
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("huid", str);
            contentValues.put("type", str2);
            contentValues.put("value", str5);
            contentValues.put(MedalConstants.EVENT_KEY, Integer.valueOf(str3));
            contentValues.put("year", Integer.valueOf(str4));
            return mea.b(this.e).insertStorageData("annual_info", 1, contentValues) - 1;
        } catch (NumberFormatException e) {
            LogUtil.b("PLGACHIEVE_AnnualReportDBMgr", "insert error: ", e.getMessage());
            return 201000L;
        }
    }

    private int a(String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str5) || mdy.b(str2, str3, str4, str5)) {
            LogUtil.a("PLGACHIEVE_AnnualReportDBMgr", "update value null");
            return 1;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("huid", str);
            contentValues.put("type", str2);
            contentValues.put(MedalConstants.EVENT_KEY, Integer.valueOf(str3));
            contentValues.put("year", Integer.valueOf(str4));
            contentValues.put("value", str5);
            int updateStorageData = mea.b(this.e).updateStorageData("annual_info", 1, contentValues, "type=? and huid=? and key=? and year=?", new String[]{mef.e(str2), mef.e(str), mef.e(str3), mef.e(str4)});
            LogUtil.a("PLGACHIEVE_AnnualReportDBMgr", "update result=", Integer.valueOf(updateStorageData));
            return updateStorageData;
        } catch (NumberFormatException e) {
            LogUtil.b("PLGACHIEVE_AnnualReportDBMgr", "update error: ", e.getMessage());
            return 201000;
        }
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        String huid = mczVar.getHuid();
        String[] strArr = {mef.e(mczVar.getType()), mef.e(huid), mef.e(String.valueOf(mczVar.getKey())), mef.e(String.valueOf(mczVar.getYear()))};
        LogUtil.c("PLGACHIEVE_AnnualReportDBMgr", "delete selection=", "type=? and huid=? and key=? and year=?");
        int deleteStorageData = mea.b(this.e).deleteStorageData("annual_info", 1, "type=? and huid=? and key=? and year=?", strArr);
        LogUtil.a("PLGACHIEVE_AnnualReportDBMgr", "delete result=", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        if (mczVar == null) {
            return -1;
        }
        String huid = mczVar.getHuid();
        String type = mczVar.getType();
        int key = mczVar.getKey();
        int year = mczVar.getYear();
        return a(huid, type, String.valueOf(key), String.valueOf(year), mczVar.getValues());
    }
}
