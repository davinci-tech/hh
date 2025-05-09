package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class jey {
    private static final String c = "MigrateDb";

    public void b(jfb jfbVar) {
        jfbVar.createStorageDataTable("migrate_acc_tab_", 1, b());
    }

    private String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("_id integer,originalhuid  NVARCHAR(500) ,originalst  NVARCHAR(500) ,tohuid  NVARCHAR(500) ,time  NVARCHAR(500) ,sendcommondfinished  NVARCHAR(500) ,cloudfinished  NVARCHAR(500) ,localfinished  NVARCHAR(500) ,data1  NVARCHAR(500) ,data2  NVARCHAR(500) ,primary key(originalhuid ,tohuid)");
        return String.valueOf(sb);
    }

    public long c(jfb jfbVar, jfd jfdVar) {
        if (jfbVar == null || jfdVar == null) {
            LogUtil.h(c, "insert param is null");
            return -1L;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("originalhuid", jfdVar.d());
            contentValues.put("originalst", jfdVar.c());
            contentValues.put("tohuid", jfdVar.b());
            contentValues.put("time", jfdVar.e());
            contentValues.put("sendcommondfinished", jfdVar.f() ? "true" : "false");
            contentValues.put("localfinished", jfdVar.h() ? "true" : "false");
            contentValues.put("localfinished", jfdVar.h() ? "true" : "false");
            return jfbVar.insertStorageData("migrate_acc_tab_", 1, contentValues);
        } catch (Exception unused) {
            LogUtil.b(c, "insert Exception");
            return -1L;
        }
    }

    public int c(jfb jfbVar) {
        if (jfbVar == null) {
            LogUtil.h(c, "deleteAll manager is null");
            return -1;
        }
        try {
            int deleteStorageData = jfbVar.deleteStorageData("migrate_acc_tab_", 1, null);
            if (deleteStorageData == 0) {
                LogUtil.h(c, "deleteAll failed");
            }
            return deleteStorageData;
        } catch (Exception unused) {
            LogUtil.b(c, "deleteAll Exception");
            return -1;
        }
    }

    public int e(jfb jfbVar, String str, String str2) {
        if (jfbVar == null) {
            LogUtil.h(c, "updateLocalToFinished manager is null");
            return -1;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("localfinished", "true");
            return jfbVar.updateStorageData("migrate_acc_tab_", 1, contentValues, "originalhuid=" + str + " and tohuid=" + str2);
        } catch (Exception unused) {
            LogUtil.b(c, "updateLocalToFinished Exception");
            return -1;
        }
    }

    public int b(jfb jfbVar, String str, String str2) {
        if (jfbVar == null) {
            LogUtil.h(c, "updateLocalToNotFinished manager is null");
            return -1;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("localfinished", "false");
            return jfbVar.updateStorageData("migrate_acc_tab_", 1, contentValues, "originalhuid=" + str + " and tohuid=" + str2);
        } catch (Exception unused) {
            LogUtil.b(c, "updateLocalToNotFinished Exception");
            return -1;
        }
    }

    public int a(jfb jfbVar, String str, String str2) {
        if (jfbVar == null) {
            LogUtil.h(c, "updateCloudToFinished manager is null");
            return -1;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cloudfinished", "true");
            return jfbVar.updateStorageData("migrate_acc_tab_", 1, contentValues, "originalhuid=" + str + " and tohuid=" + str2);
        } catch (Exception unused) {
            LogUtil.b(c, "updateCloudToFinished Exception");
            return -1;
        }
    }

    public int c(jfb jfbVar, String str, String str2) {
        if (jfbVar == null) {
            LogUtil.h(c, "updateCloudToNotFinished manager is null");
            return -1;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cloudfinished", "false");
            return jfbVar.updateStorageData("migrate_acc_tab_", 1, contentValues, "originalhuid=" + str + " and tohuid=" + str2);
        } catch (Exception unused) {
            LogUtil.b(c, "updateCloudToNotFinished Exception");
            return -1;
        }
    }

    public int j(jfb jfbVar, String str, String str2) {
        if (jfbVar == null) {
            LogUtil.h(c, "updateSendCommandFinished manager is null");
            return -1;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("sendcommondfinished", "true");
            return jfbVar.updateStorageData("migrate_acc_tab_", 1, contentValues, "originalhuid=" + str + " and tohuid=" + str2);
        } catch (Exception unused) {
            LogUtil.b(c, "updateSendCommandFinished Exception");
            return -1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0065, code lost:
    
        if (r1 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(defpackage.jfb r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            r0 = 0
            if (r6 != 0) goto Lf
            java.lang.String r6 = defpackage.jey.c
            java.lang.String r7 = "isExist manager is null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r6, r7)
            return r0
        Lf:
            r1 = 0
            r2 = 1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.lang.String r4 = "SELECT  *  FROM "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.lang.String r4 = "migrate_acc_tab_"
            java.lang.String r4 = r6.getTableFullName(r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            r3.append(r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.lang.String r4 = " WHERE tohuid like ?  AND originalhuid like ? "
            r3.append(r4)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.lang.String[] r7 = new java.lang.String[]{r8, r7}     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            android.database.Cursor r1 = r6.rawQueryStorageData(r2, r3, r7)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            if (r1 != 0) goto L35
            goto L52
        L35:
            int r6 = r1.getCount()     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            if (r6 <= 0) goto L52
            java.lang.String r6 = defpackage.jey.c     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.lang.String r8 = "isExist cursor.getCount():"
            r7[r0] = r8     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            int r8 = r1.getCount()     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            r7[r2] = r8     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            com.huawei.hwlogsmodel.LogUtil.c(r6, r7)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            r0 = r2
        L52:
            if (r1 == 0) goto L68
        L54:
            r1.close()
            goto L68
        L58:
            r6 = move-exception
            goto L69
        L5a:
            java.lang.String r6 = defpackage.jey.c     // Catch: java.lang.Throwable -> L58
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L58
            java.lang.String r8 = "isExist Exception"
            r7[r0] = r8     // Catch: java.lang.Throwable -> L58
            com.huawei.hwlogsmodel.LogUtil.b(r6, r7)     // Catch: java.lang.Throwable -> L58
            if (r1 == 0) goto L68
            goto L54
        L68:
            return r0
        L69:
            if (r1 == 0) goto L6e
            r1.close()
        L6e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jey.d(jfb, java.lang.String, java.lang.String):boolean");
    }

    public ArrayList<jfd> b(jfb jfbVar, String str) {
        Throwable th;
        Cursor cursor;
        ArrayList<jfd> arrayList = new ArrayList<>();
        if (jfbVar == null) {
            LogUtil.h(c, "fetch manager is null");
            return arrayList;
        }
        Cursor cursor2 = null;
        try {
            try {
                cursor = jfbVar.rawQueryStorageData(1, "SELECT  *  FROM " + jfbVar.getTableFullName("migrate_acc_tab_") + " WHERE tohuid = ?", new String[]{str});
            } catch (Exception unused) {
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = cursor2;
        }
        try {
        } catch (Exception unused2) {
            cursor2 = cursor;
            LogUtil.b(c, "fetch Exception");
            if (cursor2 != null) {
                cursor2.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursor == null) {
            LogUtil.h(c, "fetch cursor is null");
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
        LogUtil.c(c, "fetch cursor.getCount():", Integer.valueOf(cursor.getCount()));
        while (cursor.moveToNext()) {
            jfd bGO_ = bGO_(cursor);
            LogUtil.c(c, "fetch migrateTable:", bGO_.toString());
            arrayList.add(bGO_);
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    private jfd bGO_(Cursor cursor) {
        jfd jfdVar;
        synchronized (this) {
            jfdVar = new jfd();
            jfdVar.b(cursor.getString(cursor.getColumnIndex("originalhuid")));
            jfdVar.a(cursor.getString(cursor.getColumnIndex("originalst")));
            jfdVar.d(cursor.getString(cursor.getColumnIndex("tohuid")));
            jfdVar.e(cursor.getString(cursor.getColumnIndex("time")));
            if ("true".equals(cursor.getString(cursor.getColumnIndex("sendcommondfinished")))) {
                jfdVar.b(true);
            } else {
                jfdVar.b(false);
            }
            if ("true".equals(cursor.getString(cursor.getColumnIndex("cloudfinished")))) {
                jfdVar.d(true);
            } else {
                jfdVar.d(false);
            }
            if ("true".equals(cursor.getString(cursor.getColumnIndex("localfinished")))) {
                jfdVar.a(true);
            } else {
                jfdVar.a(false);
            }
        }
        return jfdVar;
    }
}
