package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.DbManager;

/* loaded from: classes5.dex */
public class jxa {
    private static final Object d = new Object();

    private String d() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("_id integer primary key autoincrement,User_ID NVARCHAR(300) not null,Device_ID NVARCHAR(300) not null,LastTotalSteps integer not null,LastTotalCalories integer not null,LastTotalDistance integer not null,Time_Stamp integer not null");
        return String.valueOf(stringBuffer);
    }

    public void b() {
        synchronized (d) {
            String j = j();
            if (DbManager.c(BaseApplication.getContext(), "100007", j, 1, d()) != 0) {
                LogUtil.h("BasicLastTotalValueAw70Db", "database is bad.");
                if (!DbManager.b("100007")) {
                    LogUtil.h("BasicLastTotalValueAw70Db", "data base error.");
                    return;
                }
                DbManager.c(BaseApplication.getContext(), "100007", j, 1, d());
            }
        }
    }

    public ContentValues bKN_(jxq jxqVar, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("User_ID", str);
        contentValues.put("Device_ID", str2);
        if (jxqVar != null) {
            contentValues.put("LastTotalSteps", Integer.valueOf(jxqVar.a()));
            contentValues.put("LastTotalCalories", Integer.valueOf(jxqVar.c()));
            contentValues.put("LastTotalDistance", Integer.valueOf(jxqVar.b()));
            contentValues.put("Time_Stamp", Long.valueOf(jxqVar.e()));
        }
        return contentValues;
    }

    private jxq bKM_(Cursor cursor) {
        jxq jxqVar = new jxq();
        if (cursor != null) {
            jxqVar.i(cursor.getInt(cursor.getColumnIndex("LastTotalSteps")));
            jxqVar.b(cursor.getInt(cursor.getColumnIndex("LastTotalCalories")));
            jxqVar.d(cursor.getInt(cursor.getColumnIndex("LastTotalDistance")));
            jxqVar.a(cursor.getInt(cursor.getColumnIndex("Time_Stamp")));
        }
        return jxqVar;
    }

    private String e() {
        String l = CommonUtil.l(jxi.d());
        jxc.b();
        LogUtil.a("BasicLastTotalValueAw70Db", "query condition first: ", l, " second: ", CommonUtil.l(jxc.c()));
        StringBuilder sb = new StringBuilder("( Device_ID='");
        sb.append(jxi.d());
        sb.append("' AND User_ID='");
        jxc.b();
        sb.append(jxc.c());
        sb.append("' )");
        return sb.toString();
    }

    public jxq c() {
        synchronized (d) {
            jxq jxqVar = new jxq();
            Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), "100007", j(), 1, e());
            if (bGE_ == null) {
                LogUtil.h("BasicLastTotalValueAw70Db", "getTotalValue: query error, cursor is null");
                return jxqVar;
            }
            if (bGE_.moveToNext()) {
                jxqVar = bKM_(bGE_);
            }
            bGE_.close();
            LogUtil.a("BasicLastTotalValueAw70Db", "getTotalValue: lastTotalValue: ", jxqVar);
            return jxqVar;
        }
    }

    public long a() {
        long e;
        synchronized (d) {
            jxq c = c();
            e = c != null ? c.e() : 0L;
            LogUtil.a("BasicLastTotalValueAw70Db", "getLastTimestamp: timestamp: ", Long.valueOf(e));
        }
        return e;
    }

    public void a(jxq jxqVar) {
        synchronized (d) {
            jxc.b();
            ContentValues bKN_ = bKN_(jxqVar, jxc.c(), jxi.d());
            String j = j();
            LogUtil.a("BasicLastTotalValueAw70Db", "setLastTotalValue: totalValue:", jxqVar);
            Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), "100007", j, 1, e());
            if (bGE_ == null) {
                LogUtil.h("BasicLastTotalValueAw70Db", "setLastTotalValue: query error, cursor is null");
                return;
            }
            if (bGE_.moveToNext()) {
                DbManager.b bVar = new DbManager.b();
                bVar.b(BaseApplication.getContext());
                bVar.e("100007");
                bVar.c(j);
                bVar.a(1);
                DbManager.bGH_(bVar, bKN_, e());
            } else {
                DbManager.bGC_(BaseApplication.getContext(), "100007", j, 1, bKN_);
            }
            bGE_.close();
        }
    }

    private String j() {
        return "LastTotalValueDB";
    }
}
