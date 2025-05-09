package com.huawei.hianalytics.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.DBUtil;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class a<T> {

    /* renamed from: a, reason: collision with root package name */
    public String f3840a;
    public SQLiteDatabase b;
    public final ContentValues c = new ContentValues();
    public SQLiteStatement d;

    public abstract T a(Cursor cursor);

    public abstract void a(ContentValues contentValues, T t);

    public void c() {
        if (a()) {
            return;
        }
        DBUtil.deleteAll(this.b, this.f3840a);
        HiLog.i("AbstractDao", "deleteAll: " + this.f3840a);
    }

    public final long b(List<T> list, boolean z) {
        long j;
        long j2 = -1;
        try {
            synchronized (this) {
                j = -1;
                for (int i = 0; i < list.size(); i++) {
                    try {
                        a(this.c, (ContentValues) list.get(i));
                        j = z ? this.b.insertWithOnConflict(this.f3840a, null, this.c, 5) : this.b.insert(this.f3840a, null, this.c);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Exception e) {
                            e = e;
                            j2 = j;
                            HiLog.e("AbstractDao", "insertOrReplace() error: " + e.getMessage());
                            return j2;
                        }
                    }
                }
            }
            return j;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public long b() {
        if (this.d == null) {
            this.d = this.b.compileStatement("SELECT COUNT(*) FROM \"" + this.f3840a + '\"');
        }
        return this.d.simpleQueryForLong();
    }

    public int b(String str, String[] strArr) {
        int i = -1;
        if (a()) {
            return -1;
        }
        if (this.b.isDbLockedByCurrentThread()) {
            return a(str, strArr);
        }
        this.b.beginTransaction();
        try {
            try {
                i = a(str, strArr);
                this.b.setTransactionSuccessful();
            } catch (Exception e) {
                HiLog.e("AbstractDao", e.getMessage());
            }
            return i;
        } finally {
            this.b.endTransaction();
        }
    }

    public boolean a() {
        return this.b == null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0033, code lost:
    
        if (r3 == null) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<T> a(java.lang.String[] r14, java.lang.String r15, java.lang.String[] r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            r13 = this;
            r1 = r13
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r1.b     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            java.lang.String r5 = r1.f3840a     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            r9 = 0
            r10 = 0
            r6 = r14
            r7 = r15
            r8 = r16
            r11 = r19
            r12 = r20
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
        L19:
            boolean r0 = r3.moveToNext()     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            if (r0 == 0) goto L35
            java.lang.Object r0 = r13.a(r3)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            r2.add(r0)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            goto L19
        L27:
            r0 = move-exception
            goto L39
        L29:
            r0 = move-exception
            java.lang.String r4 = "AbstractDao"
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L27
            com.huawei.hianalytics.core.log.HiLog.e(r4, r0)     // Catch: java.lang.Throwable -> L27
            if (r3 == 0) goto L38
        L35:
            r3.close()
        L38:
            return r2
        L39:
            if (r3 == 0) goto L3e
            r3.close()
        L3e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.core.a.a(java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public long a(List<T> list, boolean z) {
        long j = -1;
        if (a()) {
            return -1L;
        }
        if (this.b.isDbLockedByCurrentThread()) {
            return b(list, z);
        }
        this.b.beginTransaction();
        try {
            try {
                j = b(list, z);
                this.b.setTransactionSuccessful();
            } catch (Exception e) {
                HiLog.e("AbstractDao", e.getMessage());
            }
            return j;
        } finally {
            this.b.endTransaction();
        }
    }

    public final int a(String str, String[] strArr) {
        int i = -1;
        try {
            synchronized (this) {
                i = this.b.delete(this.f3840a, str, strArr);
            }
        } catch (Exception e) {
            HiLog.e("AbstractDao", "deleteSynchronized() error: " + e.getMessage());
        }
        return i;
    }

    public a(SQLiteDatabase sQLiteDatabase, String str) {
        this.b = sQLiteDatabase;
        this.f3840a = str;
    }
}
