package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.core.view.PointerIconCompat;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dxf extends HwBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f11876a;
    private final Object c;

    static class e {

        /* renamed from: a, reason: collision with root package name */
        public static final dxf f11880a = new dxf(BaseApplication.getContext());
    }

    private dxf(Context context) {
        super(context);
        this.c = new Object();
        this.f11876a = false;
    }

    public static dxf d() {
        return e.f11880a;
    }

    private static ContentValues aao_(dxi dxiVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", dxiVar.e());
        contentValues.put("type", Integer.valueOf(dxiVar.b()));
        contentValues.put("timestamp", Long.valueOf(dxiVar.d()));
        contentValues.put("frequency", Integer.valueOf(dxiVar.a()));
        return contentValues;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return Integer.valueOf(PointerIconCompat.TYPE_GRAB);
    }

    public void a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ClickRecordMgr", "clickRecord isEmpty");
        } else {
            final dxi dxiVar = new dxi(jec.g(System.currentTimeMillis()), i, 1, str);
            jdx.b(new Runnable() { // from class: dxf.4
                @Override // java.lang.Runnable
                public void run() {
                    dxf.this.c();
                    dxf.this.c(dxiVar);
                }
            });
        }
    }

    public void c(final long j, final long j2, final String str, final int i, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("ClickRecordMgr", "queryClickRecordSum callback == null");
            throw new IllegalArgumentException("queryClickRecordSum callback == null");
        }
        if (TextUtils.isEmpty(str) || j > j2) {
            LogUtil.h("ClickRecordMgr", "queryClickRecordSum illegal input");
            iBaseResponseCallback.d(AwarenessConstants.ERROR_UNKNOWN_CODE, 0);
        } else {
            jdx.b(new Runnable() { // from class: dxf.5
                @Override // java.lang.Runnable
                public void run() {
                    dxf.this.c();
                    String f = dxf.this.f();
                    LogUtil.c("ClickRecordMgr", "queryClickRecordSum getSqlSumString sqlString = ", f);
                    dxf.this.a(f, new String[]{String.valueOf(j), String.valueOf(j2), str, String.valueOf(i)}, iBaseResponseCallback);
                }
            });
        }
    }

    public int c(dxn dxnVar) {
        int i = 0;
        if (dxnVar == null) {
            LogUtil.h("ClickRecordMgr", "queryClickRecordCount readOption is null");
            return 0;
        }
        String d = dxnVar.d();
        if (TextUtils.isEmpty(d) || TextUtils.isEmpty(dxnVar.h()) || dxnVar.c() > dxnVar.a()) {
            LogUtil.h("ClickRecordMgr", "queryClickRecordCount illegal input");
            return 0;
        }
        c();
        Cursor rawQueryStorageData = rawQueryStorageData(1, a(), new String[]{String.valueOf(dxnVar.c()), String.valueOf(dxnVar.a()), d, dxnVar.h()});
        if (rawQueryStorageData != null) {
            int i2 = 0;
            while (rawQueryStorageData.moveToNext()) {
                try {
                    try {
                        i2 = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("total"));
                        LogUtil.c("ClickRecordMgr", "queryClickRecordCount counter = ", Integer.valueOf(i2));
                    } catch (SQLiteException e2) {
                        LogUtil.b("ClickRecordMgr", "queryClickRecordCount exception is", ExceptionUtils.d(e2));
                    }
                } finally {
                    rawQueryStorageData.close();
                }
            }
            i = i2;
        }
        return i;
    }

    public void d(final long j, final long j2, final String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("ClickRecordMgr", "queryAllRecord callback is null");
            throw new IllegalArgumentException("queryAllRecord callback == null");
        }
        if (TextUtils.isEmpty(str) || j > j2) {
            LogUtil.h("ClickRecordMgr", "queryAllRecord illegal input");
            iBaseResponseCallback.d(AwarenessConstants.ERROR_UNKNOWN_CODE, null);
        } else {
            jdx.b(new Runnable() { // from class: dxf.2
                @Override // java.lang.Runnable
                public void run() {
                    dxf.this.c();
                    String e2 = dxf.this.e();
                    LogUtil.c("ClickRecordMgr", "getSqlAllString sqlString = ", e2);
                    dxf.this.b(e2, new String[]{String.valueOf(j), String.valueOf(j2), str}, iBaseResponseCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String[] strArr, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("ClickRecordMgr", "queryAllRecord callback null");
            throw new IllegalArgumentException("queryAllRecord callback == null");
        }
        if (str == null || strArr == null) {
            LogUtil.h("ClickRecordMgr", "queryAllRecord null");
            iBaseResponseCallback.d(AwarenessConstants.ERROR_UNKNOWN_CODE, null);
            return;
        }
        Cursor rawQueryStorageData = rawQueryStorageData(1, str, strArr);
        if (rawQueryStorageData != null) {
            ArrayList arrayList = new ArrayList(10);
            while (rawQueryStorageData.moveToNext()) {
                try {
                    try {
                        arrayList.add(new dxi(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("timestamp")), rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("type")), rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("frequency")), rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid"))));
                    } catch (SQLiteException e2) {
                        LogUtil.b("ClickRecordMgr", "queryAllRecord exception is", ExceptionUtils.d(e2));
                    }
                } catch (Throwable th) {
                    rawQueryStorageData.close();
                    throw th;
                }
            }
            rawQueryStorageData.close();
            iBaseResponseCallback.d(0, arrayList);
            return;
        }
        iBaseResponseCallback.d(AwarenessConstants.ERROR_UNKNOWN_CODE, null);
    }

    public List<dxi> d(dxn dxnVar) {
        String[] strArr;
        if (dxnVar == null) {
            LogUtil.h("ClickRecordMgr", "queryClickTypeRecord readOption is null");
            return new ArrayList(0);
        }
        String d = dxnVar.d();
        if (TextUtils.isEmpty(d) || TextUtils.isEmpty(dxnVar.h()) || dxnVar.c() > dxnVar.a()) {
            LogUtil.h("ClickRecordMgr", "queryClickTypeRecord huid or type is null");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(10);
        c();
        String c = c(dxnVar.b(), dxnVar.e());
        if (dxnVar.e() <= 0) {
            strArr = new String[]{String.valueOf(dxnVar.c()), String.valueOf(dxnVar.a()), d, dxnVar.h()};
        } else {
            strArr = new String[]{String.valueOf(dxnVar.c()), String.valueOf(dxnVar.a()), d, dxnVar.h(), String.valueOf(dxnVar.e())};
        }
        Cursor rawQueryStorageData = rawQueryStorageData(1, c, strArr);
        if (rawQueryStorageData != null) {
            while (rawQueryStorageData.moveToNext()) {
                try {
                    try {
                        arrayList.add(new dxi(rawQueryStorageData.getLong(rawQueryStorageData.getColumnIndex("timestamp")), rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("type")), rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("frequency")), rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("huid"))));
                    } catch (SQLiteException e2) {
                        LogUtil.b("ClickRecordMgr", "queryClickTypeRecord Exception is ", ExceptionUtils.d(e2));
                    }
                } finally {
                    rawQueryStorageData.close();
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String[] strArr, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("ClickRecordMgr", "queryRecordSumOrCount callback null");
            throw new IllegalArgumentException("queryRecordSumOrCount callback == null");
        }
        if (str == null || strArr == null) {
            LogUtil.h("ClickRecordMgr", "queryRecordSumOrCount null");
            iBaseResponseCallback.d(AwarenessConstants.ERROR_UNKNOWN_CODE, 0);
            return;
        }
        Cursor rawQueryStorageData = rawQueryStorageData(1, str, strArr);
        if (rawQueryStorageData != null) {
            int i = 0;
            while (rawQueryStorageData.moveToNext()) {
                try {
                    try {
                        i = rawQueryStorageData.getInt(rawQueryStorageData.getColumnIndex("total"));
                        LogUtil.c("ClickRecordMgr", "queryRecordSumOrCount counter = ", Integer.valueOf(i));
                    } catch (SQLiteException e2) {
                        LogUtil.b("ClickRecordMgr", "queryRecordSumOrCount Exception is", ExceptionUtils.d(e2));
                    }
                } catch (Throwable th) {
                    rawQueryStorageData.close();
                    throw th;
                }
            }
            rawQueryStorageData.close();
            iBaseResponseCallback.d(0, Integer.valueOf(i));
            return;
        }
        iBaseResponseCallback.d(AwarenessConstants.ERROR_UNKNOWN_CODE, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(dxi dxiVar) {
        if (dxiVar == null) {
            LogUtil.h("ClickRecordMgr", "updateOrInsertRecord null");
        } else if (d(dxiVar)) {
            LogUtil.c("ClickRecordMgr", "updateOrInsertRecord update result = ", Long.valueOf(a(dxiVar)));
        } else {
            LogUtil.c("ClickRecordMgr", "updateOrInsertRecord insert result = ", Long.valueOf(b(dxiVar)));
        }
    }

    private String b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("_id integer primary key autoincrement,timestamp integer not null,type integer not null,frequency integer not null,huid text");
        return sb.toString();
    }

    private long a(dxi dxiVar) {
        if (dxiVar == null) {
            LogUtil.h("ClickRecordMgr", "update null");
            return 200004L;
        }
        int a2 = dxiVar.a();
        new ContentValues().put("frequency", Integer.valueOf(a2 + 1));
        return updateStorageData("click_record", 1, r1, d(dxiVar.d(), dxiVar.b(), dxiVar.e()));
    }

    private long b(dxi dxiVar) {
        if (dxiVar == null) {
            LogUtil.h("ClickRecordMgr", "insert clickRecordInfo null");
            return 200004L;
        }
        return insertStorageData("click_record", 1, aao_(dxiVar));
    }

    private boolean d(dxi dxiVar) {
        int count;
        if (dxiVar == null) {
            LogUtil.h("ClickRecordMgr", "queryExist null");
            return false;
        }
        Cursor queryStorageData = queryStorageData("click_record", 1, d(dxiVar.d(), dxiVar.b(), dxiVar.e()));
        if (queryStorageData != null) {
            try {
                count = queryStorageData.getCount();
                while (queryStorageData.moveToNext()) {
                    dxiVar.b(queryStorageData.getInt(queryStorageData.getColumnIndex("frequency")));
                }
            } catch (Throwable th) {
                if (queryStorageData != null) {
                    try {
                        queryStorageData.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } else {
            count = 0;
        }
        if (queryStorageData != null) {
            queryStorageData.close();
        }
        return count != 0;
    }

    private String d(long j, int i, String str) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("huid='").append(str).append("' AND type='").append(i).append("' AND timestamp='").append(j).append("'");
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.f11876a) {
            return;
        }
        synchronized (this.c) {
            if (this.f11876a) {
                return;
            }
            LogUtil.c("ClickRecordMgr", "create table error code =", Integer.valueOf(createStorageDataTable("click_record", 1, b())));
            this.f11876a = true;
        }
    }

    private String a() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("SELECT COUNT(*) AS total FROM ");
        sb.append(getTableFullName("click_record"));
        sb.append(" WHERE timestamp >=? and timestamp <=? and huid =? and type =? ");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String f() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("SELECT SUM(frequency) AS total FROM ");
        sb.append(getTableFullName("click_record"));
        sb.append(" WHERE timestamp >=? and timestamp <=? and huid =? and type =? ");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("SELECT * FROM ");
        sb.append(getTableFullName("click_record"));
        sb.append(" WHERE timestamp >=? and timestamp <=? and huid =? ");
        return sb.toString();
    }

    private String c(String str, int i) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("SELECT * FROM ");
        sb.append(getTableFullName("click_record"));
        sb.append(" WHERE timestamp >=? and timestamp <=? and huid =? and type =? ");
        if (" asc ".equals(str)) {
            sb.append(" order by timestamp asc ");
        } else {
            sb.append(" order by timestamp desc ");
        }
        if (i > 0) {
            sb.append(" limit ? ");
        }
        return sb.toString();
    }
}
