package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.DbManager;

/* loaded from: classes5.dex */
public class jil {
    private static final Object d = new Object();

    private String e() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("_id integer primary key autoincrement,User_ID NVARCHAR(300) not null,Device_ID NVARCHAR(300) not null,LastTotalSteps integer not null,LastTotalCalories integer not null,LastTotalDistance integer not null,Time_Stamp integer not null");
        return String.valueOf(stringBuffer);
    }

    public void b() {
        synchronized (d) {
            String h = h();
            if (DbManager.c(BaseApplication.getContext(), "100007", h, 1, e()) != 0) {
                LogUtil.h("LastTotalValueAw70Db", "database is bad.");
                if (!DbManager.b("100007")) {
                    LogUtil.h("LastTotalValueAw70Db", "data base error.");
                    return;
                }
                DbManager.c(BaseApplication.getContext(), "100007", h, 1, e());
            }
        }
    }

    public ContentValues bHq_(jib jibVar, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("User_ID", str);
        contentValues.put("Device_ID", str2);
        if (jibVar != null) {
            contentValues.put("LastTotalSteps", Integer.valueOf(jibVar.c()));
            contentValues.put("LastTotalCalories", Integer.valueOf(jibVar.b()));
            contentValues.put("LastTotalDistance", Integer.valueOf(jibVar.e()));
            contentValues.put("Time_Stamp", Long.valueOf(jibVar.d()));
        }
        return contentValues;
    }

    private jib bHp_(Cursor cursor) {
        jib jibVar = new jib();
        if (cursor != null) {
            jibVar.j(cursor.getInt(cursor.getColumnIndex("LastTotalSteps")));
            jibVar.a(cursor.getInt(cursor.getColumnIndex("LastTotalCalories")));
            jibVar.e(cursor.getInt(cursor.getColumnIndex("LastTotalDistance")));
            jibVar.e(cursor.getInt(cursor.getColumnIndex("Time_Stamp")));
        }
        return jibVar;
    }

    private String d() {
        String l = CommonUtil.l(jpp.b());
        jik.d(BaseApplication.getContext());
        LogUtil.a("LastTotalValueAw70Db", "query condition first: ", l, " second: ", CommonUtil.l(jik.b()));
        StringBuilder sb = new StringBuilder("( Device_ID='");
        sb.append(jpp.b());
        sb.append("' AND User_ID='");
        jik.d(BaseApplication.getContext());
        sb.append(jik.b());
        sb.append("' )");
        return sb.toString();
    }

    public jib a() {
        synchronized (d) {
            jib jibVar = new jib();
            Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), "100007", h(), 1, d());
            if (bGE_ == null) {
                LogUtil.h("LastTotalValueAw70Db", "getTotalValue: query error, cursor is null");
                return jibVar;
            }
            if (bGE_.moveToNext()) {
                jibVar = bHp_(bGE_);
            }
            bGE_.close();
            LogUtil.a("LastTotalValueAw70Db", "getTotalValue: lastTotalValue: ", jibVar);
            return jibVar;
        }
    }

    public long c() {
        long d2;
        synchronized (d) {
            jib a2 = a();
            d2 = a2 != null ? a2.d() : 0L;
            LogUtil.a("LastTotalValueAw70Db", "getLastTimestamp: timestamp: ", Long.valueOf(d2));
        }
        return d2;
    }

    public void a(jib jibVar) {
        synchronized (d) {
            jik.d(BaseApplication.getContext());
            ContentValues bHq_ = bHq_(jibVar, jik.b(), jpp.b());
            String h = h();
            LogUtil.a("LastTotalValueAw70Db", "setLastTotalValue: totalValue:", jibVar);
            Cursor bGE_ = DbManager.bGE_(BaseApplication.getContext(), "100007", h, 1, d());
            if (bGE_ == null) {
                LogUtil.h("LastTotalValueAw70Db", "setLastTotalValue: query error, cursor is null");
                return;
            }
            if (bGE_.moveToNext()) {
                DbManager.b bVar = new DbManager.b();
                bVar.b(BaseApplication.getContext());
                bVar.e("100007");
                bVar.c(h);
                bVar.a(1);
                DbManager.bGH_(bVar, bHq_, d());
            } else {
                DbManager.bGC_(BaseApplication.getContext(), "100007", h, 1, bHq_);
            }
            bGE_.close();
        }
    }

    private String h() {
        return "LastTotalValueDB";
    }
}
