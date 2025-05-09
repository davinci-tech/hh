package com.huawei.android.hicloud.sync.c.a.b;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import defpackage.abd;
import defpackage.zz;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes8.dex */
public abstract class e<V> {

    /* renamed from: a, reason: collision with root package name */
    private final SQLiteDatabase f1830a = zz.eN_();

    protected abstract V a(Cursor cursor);

    public void a(String str, String[] strArr) throws Exception {
        if (strArr != null) {
            this.f1830a.execSQL(str, strArr);
        } else {
            this.f1830a.execSQL(str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0058, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x002e, code lost:
    
        if (r1 != null) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.ArrayList<V> b(java.lang.String r5, java.lang.String[] r6) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r5 != 0) goto L8
            return r0
        L8:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.f1830a     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            android.database.Cursor r1 = r2.rawQuery(r5, r6)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            if (r1 == 0) goto L2e
            boolean r5 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            if (r5 == 0) goto L2e
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            r5.<init>()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
        L1c:
            java.lang.Object r6 = r4.a(r1)     // Catch: java.lang.Exception -> L2b java.lang.Throwable -> L31
            r5.add(r6)     // Catch: java.lang.Exception -> L2b java.lang.Throwable -> L31
            boolean r6 = r1.moveToNext()     // Catch: java.lang.Exception -> L2b java.lang.Throwable -> L31
            if (r6 != 0) goto L1c
            r0 = r5
            goto L2e
        L2b:
            r6 = move-exception
            r0 = r5
            goto L35
        L2e:
            if (r1 == 0) goto L58
            goto L55
        L31:
            r5 = move-exception
            goto L59
        L33:
            r5 = move-exception
            r6 = r5
        L35:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L31
            r5.<init>()     // Catch: java.lang.Throwable -> L31
            java.lang.String r2 = "SyncOperator"
            java.lang.String r3 = "queryResult4Vo exception: "
            r5.append(r3)     // Catch: java.lang.Throwable -> L31
            java.lang.Class r6 = r6.getClass()     // Catch: java.lang.Throwable -> L31
            java.lang.String r6 = r6.getName()     // Catch: java.lang.Throwable -> L31
            r5.append(r6)     // Catch: java.lang.Throwable -> L31
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L31
            defpackage.abd.b(r2, r5)     // Catch: java.lang.Throwable -> L31
            if (r1 == 0) goto L58
        L55:
            r1.close()
        L58:
            return r0
        L59:
            if (r1 == 0) goto L5e
            r1.close()
        L5e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.android.hicloud.sync.c.a.b.e.b(java.lang.String, java.lang.String[]):java.util.ArrayList");
    }

    public Cursor c(String str, String[] strArr) {
        SQLiteDatabase sQLiteDatabase = this.f1830a;
        if (sQLiteDatabase == null || str == null) {
            abd.d("SyncOperator", "rawQuery database or sql is null.");
            return null;
        }
        try {
            return sQLiteDatabase.rawQuery(str, strArr);
        } catch (Exception e) {
            abd.b("SyncOperator", "rawQuery exception: " + e.getClass().getName());
            return null;
        }
    }

    public int a(String str, ArrayList<String[]> arrayList) {
        int i;
        if (str == null) {
            return -2;
        }
        try {
            if (arrayList == null) {
                return -2;
            }
            try {
                this.f1830a.beginTransaction();
                SQLiteStatement compileStatement = this.f1830a.compileStatement(str);
                Iterator<String[]> it = arrayList.iterator();
                while (it.hasNext()) {
                    String[] next = it.next();
                    compileStatement.clearBindings();
                    compileStatement.bindAllArgsAsStrings(next);
                    compileStatement.execute();
                }
                this.f1830a.setTransactionSuccessful();
                i = 0;
            } catch (Exception e) {
                abd.b("SyncOperator", "execSQL4Batch exception: " + e.getClass().getName());
                i = -1;
            }
            return i;
        } finally {
            this.f1830a.endTransaction();
        }
    }
}
