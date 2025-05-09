package com.huawei.pluginachievement.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.KakaLineRecord;
import com.huawei.pluginachievement.manager.model.KakaRecord;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.mcz;
import defpackage.mea;
import defpackage.mef;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class KakaLineRecordDBMgr implements IAchieveDBMgr {
    private Context b;

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int update(mcz mczVar) {
        return 0;
    }

    public KakaLineRecordDBMgr(Context context) {
        this.b = context;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public List<mcz> queryAll(Map<String, String> map) {
        return Collections.emptyList();
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public mcz query(Map<String, String> map) {
        String str = map.get("page");
        String str2 = map.get(IAchieveDBMgr.PARAM_PAGE_SIZE);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return e(map.get("huid"), mef.b(str), mef.b(str2));
        }
        LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "query page is null || pageSize is null");
        return null;
    }

    private mcz e(String str, int i, int i2) {
        LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "query page=", Integer.valueOf(i));
        KakaLineRecord kakaLineRecord = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "query huid is null");
            return null;
        }
        String str2 = " select *  from " + mea.b(this.b).getTableFullName("kaka_line") + " where huid =?  order by  occurDate desc limit ?,? ";
        String[] strArr = {mef.e(str), mef.e(String.valueOf((i - 1) * i2)), mef.e(String.valueOf(i2))};
        LogUtil.c("PLGACHIEVE_KakaLineRecordDBMgr", "query sql=", str2);
        try {
            Cursor rawQueryStorageData = mea.b(this.b).rawQueryStorageData(1, str2, strArr);
            if (rawQueryStorageData != null) {
                try {
                    int count = rawQueryStorageData.getCount();
                    LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "query count=", Integer.valueOf(count));
                    if (count <= 0) {
                        if (rawQueryStorageData != null) {
                            rawQueryStorageData.close();
                        }
                        return null;
                    }
                    KakaLineRecord kakaLineRecord2 = new KakaLineRecord();
                    try {
                        ArrayList arrayList = new ArrayList(8);
                        while (rawQueryStorageData.moveToNext()) {
                            KakaRecord kakaRecord = new KakaRecord();
                            kakaRecord.setKakaNum(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("kaka_num")));
                            kakaRecord.setDate(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("date_timestamp")));
                            kakaRecord.setDescription(rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("description")));
                            kakaRecord.setOccurDate(c(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex(ParsedFieldTag.OCCUR_DATE))));
                            arrayList.add(kakaRecord);
                        }
                        kakaLineRecord2.setHuid(str);
                        kakaLineRecord2.setTotalNum(a(str));
                        LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "query count=", Integer.valueOf(kakaLineRecord2.getTotalNum()));
                        kakaLineRecord2.setKakaLineRecords(arrayList);
                        kakaLineRecord = kakaLineRecord2;
                    } catch (Throwable th) {
                        th = th;
                        kakaLineRecord = kakaLineRecord2;
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
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_KakaLineRecordDBMgr", e.getMessage());
        }
        LogUtil.c("PLGACHIEVE_KakaLineRecordDBMgr", "query kakaLineRecord=", kakaLineRecord);
        return kakaLineRecord;
    }

    private int a(String str) {
        int i = 0;
        if (str == null) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "getTotalCount(),id is null!return 0");
            return 0;
        }
        try {
            Cursor rawQueryStorageData = mea.b(this.b).rawQueryStorageData(1, "select *  from " + mea.b(this.b).getTableFullName("kaka_line") + " where huid =? ", new String[]{mef.e(str)});
            if (rawQueryStorageData != null) {
                try {
                    i = rawQueryStorageData.getCount();
                } finally {
                }
            }
            if (rawQueryStorageData != null) {
                rawQueryStorageData.close();
            }
        } catch (SQLException e) {
            LogUtil.h("PLGACHIEVE_KakaLineRecordDBMgr", e.getMessage());
        }
        return i;
    }

    private long c(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Timestamp.valueOf(str).getTime();
        } catch (IllegalArgumentException e) {
            LogUtil.b("PLGACHIEVE_KakaLineRecordDBMgr", "getTime() e=", e.getMessage());
            return 0L;
        }
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public long insert(mcz mczVar) {
        long j = -1;
        if (mczVar == null) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "insert achieveData is null");
            return -1L;
        }
        KakaLineRecord kakaLineRecord = mczVar instanceof KakaLineRecord ? (KakaLineRecord) mczVar : null;
        if (kakaLineRecord == null) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "insert kakaLineRecord is null");
            return -1L;
        }
        String huid = kakaLineRecord.getHuid();
        if (TextUtils.isEmpty(huid)) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "insert huid is null");
            return -1L;
        }
        List<KakaRecord> kakaLineRecords = kakaLineRecord.getKakaLineRecords();
        if (kakaLineRecords == null) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "insert kakaRecords is null");
            return -1L;
        }
        Iterator<KakaRecord> it = kakaLineRecords.iterator();
        while (it.hasNext()) {
            long b = b(it.next(), huid);
            if (b > 0) {
                j += b;
            }
        }
        LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "insert to KakaLineRecord result=", Long.valueOf(j));
        return j;
    }

    @Override // com.huawei.pluginachievement.manager.db.IAchieveDBMgr
    public int delete(mcz mczVar) {
        if (mczVar == null) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "delete achieveData is null");
            return -1;
        }
        KakaLineRecord kakaLineRecord = mczVar instanceof KakaLineRecord ? (KakaLineRecord) mczVar : null;
        if (kakaLineRecord == null) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "delete kakaLineRecord is null");
            return -1;
        }
        return mea.b(this.b).deleteStorageData("kaka_line", 1, "huid=?", new String[]{mef.e(kakaLineRecord.getHuid())});
    }

    private boolean d(KakaRecord kakaRecord) {
        if (kakaRecord.getDate() == -1 || TextUtils.isEmpty(String.valueOf(kakaRecord.getDate()))) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "columCheck kakaRecord date can't be null");
            return false;
        }
        if (TextUtils.isEmpty(String.valueOf(kakaRecord.getKakaNum()))) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "columCheck kakaRecord kakaNum can't be null");
            return false;
        }
        if (!TextUtils.isEmpty(String.valueOf(kakaRecord.getDescription()))) {
            return true;
        }
        LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "columCheck kakaRecord description can't be null");
        return false;
    }

    private long b(KakaRecord kakaRecord, String str) {
        if (kakaRecord == null) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "insertKakaRecord kakaRecord is null");
            return -1L;
        }
        if (!d(kakaRecord)) {
            LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "insertKakaRecord kakaRecord colums have null data");
            return -1L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", str);
        contentValues.put("date_timestamp", Long.valueOf(kakaRecord.getDate()));
        contentValues.put("description", Integer.valueOf(kakaRecord.getDescription()));
        contentValues.put("kaka_num", Integer.valueOf(kakaRecord.getKakaNum()));
        contentValues.put(ParsedFieldTag.OCCUR_DATE, String.valueOf(new Timestamp(kakaRecord.getOccurDate())));
        long j = mea.b(this.b).insertStorageData("kaka_line", 1, contentValues) > 0 ? 1L : -1L;
        LogUtil.a("PLGACHIEVE_KakaLineRecordDBMgr", "insertKakaRecord into db successs result = ", Long.valueOf(j));
        return j;
    }
}
