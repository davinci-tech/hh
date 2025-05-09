package com.huawei.hwnetworkmodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kuc;

/* loaded from: classes5.dex */
public class HttpDatabaseHelper extends SQLiteOpenHelper {
    public HttpDatabaseHelper(Context context) {
        super(context, "Traffic.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            LogUtil.h("HttpDatabaseHelper", "onCreate null");
        } else {
            sQLiteDatabase.execSQL("create table TrafficByDate (_id integer primary key autoincrement, received text not null , requested text not null, total text not null, date text not null);");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase == null) {
            LogUtil.h("HttpDatabaseHelper", "onUpgrade null");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXITS TrafficByDate");
            onCreate(sQLiteDatabase);
        }
    }

    public long d(kuc kucVar) {
        SQLiteDatabase sQLiteDatabase;
        if (kucVar == null) {
            LogUtil.h("HttpDatabaseHelper", "insert null");
            return 0L;
        }
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            try {
                sQLiteDatabase = getWritableDatabase();
            } catch (Throwable th) {
                th = th;
                sQLiteDatabase = null;
            }
        } catch (SQLiteException unused) {
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("received", Long.valueOf(kucVar.d()));
            contentValues.put("requested", Long.valueOf(kucVar.c()));
            contentValues.put("date", kucVar.a());
            contentValues.put("total", Long.valueOf(kucVar.e()));
            long insert = sQLiteDatabase.insert("TrafficByDate", null, contentValues);
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            return insert;
        } catch (SQLiteException unused2) {
            sQLiteDatabase2 = sQLiteDatabase;
            LogUtil.b("HttpDatabaseHelper", "insert SQLiteException");
            if (sQLiteDatabase2 == null) {
                return -1L;
            }
            sQLiteDatabase2.close();
            return -1L;
        } catch (Throwable th2) {
            th = th2;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    public int e(kuc kucVar) {
        if (kucVar == null) {
            LogUtil.h("HttpDatabaseHelper", "update null");
            return 0;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = getWritableDatabase();
                String[] strArr = {kucVar.a()};
                ContentValues contentValues = new ContentValues();
                contentValues.put("received", Long.valueOf(kucVar.d()));
                contentValues.put("requested", Long.valueOf(kucVar.c()));
                contentValues.put("total", Long.valueOf(kucVar.e()));
                contentValues.put("date", kucVar.a());
                int update = sQLiteDatabase.update("TrafficByDate", contentValues, "date = ?", strArr);
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return update;
            } catch (SQLiteException unused) {
                LogUtil.b("HttpDatabaseHelper", "update SQLiteException");
                if (sQLiteDatabase == null) {
                    return -1;
                }
                sQLiteDatabase.close();
                return -1;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.kuc d(java.lang.String r14) {
        /*
            r13 = this;
            java.lang.String r0 = "HttpDatabaseHelper"
            r1 = 0
            if (r14 != 0) goto Lf
            java.lang.String r14 = "query date is null"
            java.lang.Object[] r14 = new java.lang.Object[]{r14}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r14)
            return r1
        Lf:
            r2 = 0
            r3 = 1
            android.database.sqlite.SQLiteDatabase r12 = r13.getReadableDatabase()     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L92
            java.lang.String r5 = "TrafficByDate"
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r4 = r12
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L89 android.database.sqlite.SQLiteException -> L8d
            if (r4 == 0) goto L75
        L24:
            boolean r5 = r4.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            if (r5 == 0) goto L7e
            java.lang.String r5 = "date"
            int r5 = r4.getColumnIndex(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            java.lang.String r5 = r4.getString(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            boolean r6 = r14.equals(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            if (r6 == 0) goto L24
            kuc r14 = new kuc     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            r14.<init>(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            java.lang.String r5 = "received"
            int r5 = r4.getColumnIndex(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            long r5 = r4.getLong(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            r14.c(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            java.lang.String r5 = "requested"
            int r5 = r4.getColumnIndex(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            long r5 = r4.getLong(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            r14.b(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            java.lang.String r5 = "total"
            int r5 = r4.getColumnIndex(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            long r5 = r4.getLong(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            r14.e(r5)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            r4.close()     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            if (r12 == 0) goto L6f
            r12.close()
        L6f:
            if (r4 == 0) goto L74
            r4.close()
        L74:
            return r14
        L75:
            java.lang.Object[] r14 = new java.lang.Object[r3]     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            java.lang.String r5 = "query cursor is null"
            r14[r2] = r5     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
            com.huawei.hwlogsmodel.LogUtil.h(r0, r14)     // Catch: android.database.sqlite.SQLiteException -> L94 java.lang.Throwable -> La8
        L7e:
            if (r12 == 0) goto L83
            r12.close()
        L83:
            if (r4 == 0) goto L88
            r4.close()
        L88:
            return r1
        L89:
            r14 = move-exception
            r4 = r1
        L8b:
            r1 = r12
            goto Laa
        L8d:
            r4 = r1
            goto L94
        L8f:
            r14 = move-exception
            r4 = r1
            goto Laa
        L92:
            r4 = r1
            r12 = r4
        L94:
            java.lang.Object[] r14 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La8
            java.lang.String r3 = "query SQLiteException"
            r14[r2] = r3     // Catch: java.lang.Throwable -> La8
            com.huawei.hwlogsmodel.LogUtil.b(r0, r14)     // Catch: java.lang.Throwable -> La8
            if (r12 == 0) goto La2
            r12.close()
        La2:
            if (r4 == 0) goto La7
            r4.close()
        La7:
            return r1
        La8:
            r14 = move-exception
            goto L8b
        Laa:
            if (r1 == 0) goto Laf
            r1.close()
        Laf:
            if (r4 == 0) goto Lb4
            r4.close()
        Lb4:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwnetworkmodel.HttpDatabaseHelper.d(java.lang.String):kuc");
    }
}
