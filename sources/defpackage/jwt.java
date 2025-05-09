package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class jwt {
    private static final Object c = new Object();

    private String d() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("_id integer primary key autoincrement,User_ID NVARCHAR(300) not null,Device_ID NVARCHAR(300) not null,LastTotalSteps integer not null,LastTotalCalories integer not null,LastTotalDistance integer not null,Time_Stamp integer not null");
        return String.valueOf(sb);
    }

    public void d(HwBaseManager hwBaseManager) {
        synchronized (c) {
            if (hwBaseManager == null) {
                LogUtil.h("BasicLastTotalValueDb", "createDBTable, fitnessManager is null");
                return;
            }
            String e = e();
            if (hwBaseManager.createStorageDataTable(e, 1, d()) != 0) {
                LogUtil.h("BasicLastTotalValueDb", "database is bad.");
                if (!hwBaseManager.deleteDatabase()) {
                    LogUtil.h("BasicLastTotalValueDb", "data base error.");
                    return;
                }
                hwBaseManager.createStorageDataTable(e, 1, d());
            }
        }
    }

    public ContentValues bKF_(jxq jxqVar, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        if (jxqVar == null) {
            LogUtil.h("BasicLastTotalValueDb", "getContentValues lastTotalValue is null");
            return contentValues;
        }
        contentValues.put("User_ID", str);
        contentValues.put("Device_ID", str2);
        contentValues.put("LastTotalSteps", Integer.valueOf(jxqVar.a()));
        contentValues.put("LastTotalCalories", Integer.valueOf(jxqVar.c()));
        contentValues.put("LastTotalDistance", Integer.valueOf(jxqVar.b()));
        contentValues.put("Time_Stamp", Long.valueOf(jxqVar.e()));
        return contentValues;
    }

    private jxq bKE_(Cursor cursor) {
        jxq jxqVar = new jxq();
        jxqVar.i(cursor.getInt(cursor.getColumnIndex("LastTotalSteps")));
        jxqVar.b(cursor.getInt(cursor.getColumnIndex("LastTotalCalories")));
        jxqVar.d(cursor.getInt(cursor.getColumnIndex("LastTotalDistance")));
        jxqVar.a(cursor.getInt(cursor.getColumnIndex("Time_Stamp")));
        return jxqVar;
    }

    private String b() {
        String l = CommonUtil.l(keg.e());
        jwy.b();
        LogUtil.a("BasicLastTotalValueDb", "query condition first: ", l, " second: ", CommonUtil.l(jwy.e()));
        StringBuilder sb = new StringBuilder("( Device_ID='");
        sb.append(keg.e());
        sb.append("' AND User_ID='");
        jwy.b();
        sb.append(jwy.e());
        sb.append("' )");
        return sb.toString();
    }

    public jxq b(jwy jwyVar) {
        synchronized (c) {
            jxq jxqVar = new jxq();
            if (jwyVar == null) {
                LogUtil.h("BasicLastTotalValueDb", "getTotalValue, fitnessManager is null");
                return jxqVar;
            }
            Cursor cursor = null;
            try {
                try {
                    Cursor queryStorageData = jwyVar.queryStorageData(e(), 1, b());
                    if (queryStorageData == null) {
                        LogUtil.h("BasicLastTotalValueDb", "getLastTotalCalories cursor is null");
                        if (queryStorageData != null) {
                            queryStorageData.close();
                        }
                        return jxqVar;
                    }
                    if (queryStorageData.moveToNext()) {
                        jxqVar = bKE_(queryStorageData);
                    }
                    if (queryStorageData != null) {
                        queryStorageData.close();
                    }
                    LogUtil.a("BasicLastTotalValueDb", "getTotalValue: ", jxqVar);
                    return jxqVar;
                } catch (Exception e) {
                    LogUtil.b("BasicLastTotalValueDb", "queryStorageData Exception:", ExceptionUtils.d(e));
                    if (0 != 0) {
                        cursor.close();
                    }
                    return jxqVar;
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    public long e(jwy jwyVar) {
        synchronized (c) {
            if (jwyVar == null) {
                LogUtil.h("BasicLastTotalValueDb", "getLastTimestamp, fitnessManager is null");
                return 0L;
            }
            jxq b = b(jwyVar);
            long e = b != null ? b.e() : 0L;
            LogUtil.a("BasicLastTotalValueDb", "getLastTimestamp timeStamp: ", Long.valueOf(e));
            return e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0066, code lost:
    
        if (r10 != null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0084, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0080, code lost:
    
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007e, code lost:
    
        if (0 == 0) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(defpackage.jwy r9, defpackage.jxq r10) {
        /*
            r8 = this;
            java.lang.Object r0 = defpackage.jwt.c
            monitor-enter(r0)
            r1 = 0
            r2 = 1
            if (r9 != 0) goto L15
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L8b
            java.lang.String r10 = "setLastTotalValue, fitnessManager is null"
            r9[r1] = r10     // Catch: java.lang.Throwable -> L8b
            java.lang.String r10 = "BasicLastTotalValueDb"
            com.huawei.hwlogsmodel.LogUtil.h(r10, r9)     // Catch: java.lang.Throwable -> L8b
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8b
            return
        L15:
            defpackage.jwy.b()     // Catch: java.lang.Throwable -> L8b
            java.lang.String r3 = defpackage.jwy.e()     // Catch: java.lang.Throwable -> L8b
            java.lang.String r4 = defpackage.keg.e()     // Catch: java.lang.Throwable -> L8b
            android.content.ContentValues r3 = r8.bKF_(r10, r3, r4)     // Catch: java.lang.Throwable -> L8b
            java.lang.String r4 = r8.e()     // Catch: java.lang.Throwable -> L8b
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L8b
            java.lang.String r7 = "setLastTotalValue total: "
            r6[r1] = r7     // Catch: java.lang.Throwable -> L8b
            r6[r2] = r10     // Catch: java.lang.Throwable -> L8b
            java.lang.String r10 = "BasicLastTotalValueDb"
            com.huawei.hwlogsmodel.LogUtil.a(r10, r6)     // Catch: java.lang.Throwable -> L8b
            r10 = 0
            java.lang.String r6 = r8.b()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            android.database.Cursor r10 = r9.queryStorageData(r4, r2, r6)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            if (r10 != 0) goto L55
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            java.lang.String r3 = "setLastTotalValue cursor is null"
            r9[r1] = r3     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            java.lang.String r3 = "BasicLastTotalValueDb"
            com.huawei.hwlogsmodel.LogUtil.h(r3, r9)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            if (r10 == 0) goto L53
            r10.close()     // Catch: java.lang.Throwable -> L8b
        L53:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8b
            return
        L55:
            boolean r6 = r10.moveToNext()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            if (r6 == 0) goto L63
            java.lang.String r6 = r8.b()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r9.updateStorageData(r4, r2, r3, r6)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            goto L66
        L63:
            r9.insertStorageData(r4, r2, r3)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
        L66:
            if (r10 == 0) goto L83
            goto L80
        L69:
            r9 = move-exception
            goto L85
        L6b:
            r9 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L69
            java.lang.String r4 = "setLastTotalValue exception:"
            r3[r1] = r4     // Catch: java.lang.Throwable -> L69
            java.lang.String r9 = com.huawei.haf.common.exception.ExceptionUtils.d(r9)     // Catch: java.lang.Throwable -> L69
            r3[r2] = r9     // Catch: java.lang.Throwable -> L69
            java.lang.String r9 = "BasicLastTotalValueDb"
            com.huawei.hwlogsmodel.LogUtil.b(r9, r3)     // Catch: java.lang.Throwable -> L69
            if (r10 == 0) goto L83
        L80:
            r10.close()     // Catch: java.lang.Throwable -> L8b
        L83:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8b
            return
        L85:
            if (r10 == 0) goto L8a
            r10.close()     // Catch: java.lang.Throwable -> L8b
        L8a:
            throw r9     // Catch: java.lang.Throwable -> L8b
        L8b:
            r9 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8b
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jwt.b(jwy, jxq):void");
    }

    private String e() {
        return "LastTotalValueDB";
    }
}
