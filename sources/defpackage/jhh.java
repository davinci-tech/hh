package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class jhh {
    private static final Object b = new Object();

    private String a() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("_id integer primary key autoincrement,User_ID NVARCHAR(300) not null,Device_ID NVARCHAR(300) not null,LastTotalSteps integer not null,LastTotalCalories integer not null,LastTotalDistance integer not null,Time_Stamp integer not null");
        return String.valueOf(sb);
    }

    public void a(jhg jhgVar) {
        synchronized (b) {
            if (jhgVar == null) {
                LogUtil.h("LastTotalValueDb", "createDBTable, fitnessManager is null");
                return;
            }
            String c = c();
            if (jhgVar.createStorageDataTable(c, 1, a()) != 0) {
                LogUtil.h("LastTotalValueDb", "database is bad.");
                if (!jhgVar.deleteDatabase()) {
                    LogUtil.h("LastTotalValueDb", "data base error.");
                    return;
                }
                jhgVar.createStorageDataTable(c, 1, a());
            }
        }
    }

    public ContentValues bHl_(jib jibVar, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        if (jibVar == null) {
            LogUtil.h("LastTotalValueDb", "getContentValues lastTotalValue is null");
            return contentValues;
        }
        contentValues.put("User_ID", str);
        contentValues.put("Device_ID", str2);
        contentValues.put("LastTotalSteps", Integer.valueOf(jibVar.c()));
        contentValues.put("LastTotalCalories", Integer.valueOf(jibVar.b()));
        contentValues.put("LastTotalDistance", Integer.valueOf(jibVar.e()));
        contentValues.put("Time_Stamp", Long.valueOf(jibVar.d()));
        return contentValues;
    }

    private jib bHk_(Cursor cursor) {
        jib jibVar = new jib();
        jibVar.j(cursor.getInt(cursor.getColumnIndex("LastTotalSteps")));
        jibVar.a(cursor.getInt(cursor.getColumnIndex("LastTotalCalories")));
        jibVar.e(cursor.getInt(cursor.getColumnIndex("LastTotalDistance")));
        jibVar.e(cursor.getInt(cursor.getColumnIndex("Time_Stamp")));
        return jibVar;
    }

    private String e() {
        String l = CommonUtil.l(jpp.d());
        jhg.c(BaseApplication.getContext());
        LogUtil.a("LastTotalValueDb", "query condition first: ", l, " second: ", CommonUtil.l(jhg.b()));
        StringBuilder sb = new StringBuilder("( Device_ID='");
        sb.append(jpp.d());
        sb.append("' AND User_ID='");
        jhg.c(BaseApplication.getContext());
        sb.append(jhg.b());
        sb.append("' )");
        return sb.toString();
    }

    public jib d(jhg jhgVar) {
        synchronized (b) {
            jib jibVar = new jib();
            if (jhgVar == null) {
                LogUtil.h("LastTotalValueDb", "getTotalValue, fitnessManager is null");
                return jibVar;
            }
            Cursor queryStorageData = jhgVar.queryStorageData(c(), 1, e());
            if (queryStorageData == null) {
                LogUtil.h("LastTotalValueDb", "getLastTotalCalories cursor is null");
                return jibVar;
            }
            if (queryStorageData.moveToNext()) {
                jibVar = bHk_(queryStorageData);
            }
            queryStorageData.close();
            LogUtil.a("05", 1, "LastTotalValueDb", "getTotalValue: ", jibVar);
            return jibVar;
        }
    }

    public long b(jhg jhgVar) {
        synchronized (b) {
            if (jhgVar == null) {
                LogUtil.h("LastTotalValueDb", "getLastTimestamp, fitnessManager is null");
                return 0L;
            }
            jib d = d(jhgVar);
            long d2 = d != null ? d.d() : 0L;
            LogUtil.a("05", 1, "LastTotalValueDb", "getLastTimestamp timeStamp: ", Long.valueOf(d2));
            return d2;
        }
    }

    public void b(jhg jhgVar, jib jibVar) {
        synchronized (b) {
            if (jhgVar == null) {
                LogUtil.h("LastTotalValueDb", "setLastTotalValue, fitnessManager is null");
                return;
            }
            jhg.c(BaseApplication.getContext());
            ContentValues bHl_ = bHl_(jibVar, jhg.b(), jpp.d());
            String c = c();
            LogUtil.a("05", 1, "LastTotalValueDb", "setLastTotalValue total: ", jibVar);
            Cursor queryStorageData = jhgVar.queryStorageData(c, 1, e());
            if (queryStorageData == null) {
                LogUtil.h("LastTotalValueDb", "setLastTotalValue cursor is null");
                return;
            }
            if (queryStorageData.moveToNext()) {
                jhgVar.updateStorageData(c, 1, bHl_, e());
            } else {
                jhgVar.insertStorageData(c, 1, bHl_);
            }
            queryStorageData.close();
        }
    }

    private String c() {
        return "LastTotalValueDB";
    }
}
