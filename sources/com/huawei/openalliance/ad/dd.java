package com.huawei.openalliance.ad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.StaleDataException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public final class dd extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6694a = "dd";
    private static dd d;
    private SQLiteDatabase c;
    private final Context g;
    private static AtomicInteger b = new AtomicInteger();
    private static final AtomicInteger e = new AtomicInteger(3);
    private static final byte[] f = new byte[0];

    private boolean a(int i) {
        return i >= 93;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b.incrementAndGet();
        this.c = sQLiteDatabase;
        a(i2, i);
        b.decrementAndGet();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b.incrementAndGet();
        this.c = sQLiteDatabase;
        a(i2, i);
        b.decrementAndGet();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        b.incrementAndGet();
        this.c = sQLiteDatabase;
        a(97, 97);
        b.decrementAndGet();
    }

    public long g(String str) {
        synchronized (this) {
            if (!h(str)) {
                ho.c(f6694a, "queryCnt fail, table name is not right");
                return 0L;
            }
            try {
                return getReadableDatabase().compileStatement("SELECT COUNT(*) FROM \"" + str + '\"').simpleQueryForLong();
            } catch (Exception unused) {
                ho.c(f6694a, "queryCnt fail");
                return 0L;
            }
        }
    }

    void f(String str) {
        if (!h(str)) {
            throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "tableName: %s is invalid", str.trim()));
        }
        try {
            this.c.execSQL(" ALTER TABLE " + str + " RENAME TO _temp_" + str);
        } catch (SQLException unused) {
            ho.c(f6694a, "modifyTableName fail");
            throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "modify tableName: %s name failed", str.trim()));
        } catch (IllegalStateException unused2) {
            ho.c(f6694a, "modifyTableName fail");
            throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "modify tableName: %s name failed", str.trim()));
        }
    }

    boolean e(String str) {
        if (!h(str)) {
            throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "tableName: %s is invalid", str.trim()));
        }
        Cursor cursor = null;
        boolean z = false;
        try {
            try {
                try {
                    cursor = this.c.rawQuery("select count(1) as c from sqlite_master where type ='table' and name = ?", new String[]{str.trim()});
                    if (cursor.moveToNext()) {
                        if (cursor.getInt(0) > 0) {
                            z = true;
                        }
                    }
                    return z;
                } catch (StaleDataException unused) {
                    throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "query tableName: %s failed", str.trim()));
                } catch (IllegalArgumentException unused2) {
                    throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "query tableName: %s failed", str.trim()));
                }
            } catch (CursorIndexOutOfBoundsException unused3) {
                throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "query tableName: %s failed", str.trim()));
            } catch (IllegalStateException unused4) {
                throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "query tableName: %s failed", str.trim()));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    String[] d(String str) {
        if (!h(str)) {
            throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "tableName: %s is invalid", str.trim()));
        }
        Cursor cursor = null;
        try {
            try {
                cursor = this.c.rawQuery(" select * from " + str + " order by _id asc LIMIT 1", null);
                cursor.moveToNext();
                return cursor.getColumnNames();
            } catch (Exception unused) {
                ho.c(f6694a, "getColumnNames error");
                throw new com.huawei.openalliance.ad.exception.a(String.format(Locale.ENGLISH, "get new tableName: %s column names failed", str.trim()));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (b.decrementAndGet() <= 0) {
                super.close();
            }
        }
    }

    public void c(String str) {
        try {
            this.c.execSQL(str);
        } catch (Exception unused) {
            ho.c(f6694a, "executeSQL error");
            throw new com.huawei.openalliance.ad.exception.a("execute sql failed");
        }
    }

    void b(String str) {
        if (!h(str)) {
            ho.c(f6694a, "tableName: %s is invalid", str);
            return;
        }
        try {
            this.c.execSQL(" DROP TABLE " + str);
        } catch (Exception e2) {
            ho.c(f6694a, "delete table: %s fail, exception: %s", str, e2.getClass().getSimpleName());
        }
    }

    public void a(List<de> list) {
        synchronized (this) {
            if (list != null) {
                if (!list.isEmpty()) {
                    SQLiteDatabase sQLiteDatabase = null;
                    try {
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                        try {
                            writableDatabase.beginTransaction();
                            for (de deVar : list) {
                                if (a(writableDatabase, deVar)) {
                                    writableDatabase.update(deVar.a(), deVar.k(), deVar.i(), deVar.j());
                                } else {
                                    writableDatabase.insertOrThrow(deVar.a(), null, deVar.k());
                                }
                            }
                            writableDatabase.setTransactionSuccessful();
                            if (writableDatabase != null) {
                                writableDatabase.endTransaction();
                            }
                        } catch (Throwable th) {
                            th = th;
                            sQLiteDatabase = writableDatabase;
                            try {
                                ho.a(5, f6694a, "insertOrUpdate ", th);
                            } finally {
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.endTransaction();
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x002c, code lost:
    
        if (r0 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0043, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r3, android.content.ContentValues r4, java.lang.String r5, java.util.List<java.lang.String> r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r6 == 0) goto L4b
            boolean r0 = r6.isEmpty()     // Catch: java.lang.Throwable -> L54
            if (r0 == 0) goto La
            goto L4b
        La:
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()     // Catch: java.lang.Throwable -> L31
            r0.beginTransaction()     // Catch: java.lang.Throwable -> L2f
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> L2f
        L15:
            boolean r1 = r6.hasNext()     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L29
            java.lang.Object r1 = r6.next()     // Catch: java.lang.Throwable -> L2f
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L2f
            java.lang.String[] r1 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L2f
            r0.update(r3, r4, r5, r1)     // Catch: java.lang.Throwable -> L2f
            goto L15
        L29:
            r0.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L42
            goto L3f
        L2f:
            r3 = move-exception
            goto L34
        L31:
            r3 = move-exception
            r4 = 0
            r0 = r4
        L34:
            java.lang.String r4 = com.huawei.openalliance.ad.dd.f6694a     // Catch: java.lang.Throwable -> L44
            java.lang.String r5 = "update "
            r6 = 5
            com.huawei.openalliance.ad.ho.a(r6, r4, r5, r3)     // Catch: java.lang.Throwable -> L44
            if (r0 == 0) goto L42
        L3f:
            r0.endTransaction()     // Catch: java.lang.Throwable -> L54
        L42:
            monitor-exit(r2)
            return
        L44:
            r3 = move-exception
            if (r0 == 0) goto L4a
            r0.endTransaction()     // Catch: java.lang.Throwable -> L54
        L4a:
            throw r3     // Catch: java.lang.Throwable -> L54
        L4b:
            java.lang.String r3 = com.huawei.openalliance.ad.dd.f6694a     // Catch: java.lang.Throwable -> L54
            java.lang.String r4 = "nothing update, items is empty"
            com.huawei.openalliance.ad.ho.a(r3, r4)     // Catch: java.lang.Throwable -> L54
            monitor-exit(r2)
            return
        L54:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.dd.a(java.lang.String, android.content.ContentValues, java.lang.String, java.util.List):void");
    }

    void a(String str) {
        if (!h(str)) {
            ho.c(f6694a, "tableName: %s is invalid", str);
            return;
        }
        try {
            this.c.execSQL(" DROP TABLE _temp_" + str);
        } catch (Exception e2) {
            ho.c(f6694a, "delete temp tableName: %s failed, exception: %s", str, e2.getClass().getSimpleName());
        }
    }

    public void a() {
        synchronized (this) {
            b.decrementAndGet();
        }
    }

    public Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4) {
        Cursor query;
        synchronized (this) {
            query = getReadableDatabase().query(str, strArr, str2, strArr2, null, null, str3, str4);
        }
        return query;
    }

    public Cursor a(String str, String[] strArr) {
        Cursor rawQuery;
        synchronized (this) {
            rawQuery = getReadableDatabase().rawQuery(str, strArr);
        }
        return rawQuery;
    }

    public long a(String str, List<ContentValues> list) {
        SQLiteDatabase sQLiteDatabase;
        long j;
        synchronized (this) {
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                try {
                    sQLiteDatabase = getWritableDatabase();
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
                sQLiteDatabase = sQLiteDatabase2;
            }
            try {
                sQLiteDatabase.beginTransaction();
                Iterator<ContentValues> it = list.iterator();
                j = 0;
                while (it.hasNext()) {
                    j = sQLiteDatabase.insert(str, null, it.next());
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
            } catch (Exception e3) {
                e = e3;
                sQLiteDatabase2 = sQLiteDatabase;
                ho.a(3, f6694a, "batchInsert", e);
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.endTransaction();
                }
                j = -1;
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                throw th;
            }
        }
        return j;
    }

    public long a(String str, ContentValues contentValues) {
        long j;
        synchronized (this) {
            try {
                j = getWritableDatabase().insertOrThrow(str, null, contentValues);
            } catch (Throwable th) {
                String str2 = f6694a;
                ho.c(str2, "insert " + th.getClass().getSimpleName());
                ho.a(str2, "insert %s", th.getMessage());
                j = -1;
            }
        }
        return j;
    }

    public int a(String str, String str2, String[] strArr) {
        int i;
        synchronized (this) {
            try {
                i = getWritableDatabase().delete(str, str2, strArr);
            } catch (Exception e2) {
                String str3 = f6694a;
                ho.c(str3, "delete " + e2.getClass().getSimpleName());
                ho.a(str3, "delete %s", e2.getMessage());
                i = 0;
            }
        }
        return i;
    }

    public int a(String str, String str2, List<String> list) {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this) {
            int i = 0;
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        sQLiteDatabase = getWritableDatabase();
                        try {
                            sQLiteDatabase.beginTransaction();
                            Iterator<String> it = list.iterator();
                            while (it.hasNext()) {
                                i += sQLiteDatabase.delete(str, str2, new String[]{it.next()});
                            }
                            sQLiteDatabase.setTransactionSuccessful();
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.endTransaction();
                            }
                            return i;
                        } catch (Throwable th) {
                            th = th;
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.endTransaction();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        sQLiteDatabase = null;
                    }
                }
            }
            ho.a(f6694a, "noting delete, items is empty");
            return 0;
        }
    }

    public int a(String str, ContentValues contentValues, String str2, String[] strArr) {
        int i;
        synchronized (this) {
            try {
                i = getWritableDatabase().update(str, contentValues, str2, strArr);
            } catch (Throwable th) {
                String str3 = f6694a;
                ho.c(str3, "update " + th.getClass().getSimpleName());
                ho.a(str3, "update %s", th.getMessage());
                i = 0;
            }
        }
        return i;
    }

    private boolean h(String str) {
        return dg.a(str);
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, de deVar) {
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.query(deVar.a(), deVar.b(), deVar.c(), deVar.d(), deVar.e(), deVar.f(), deVar.g(), deVar.h());
                if (cursor != null) {
                    return cursor.getCount() > 0;
                }
            } catch (Exception unused) {
                ho.d(f6694a, "query exception");
            }
            return false;
        } finally {
            com.huawei.openalliance.ad.utils.cy.a(cursor);
        }
    }

    private void a(int i, int i2) {
        com.huawei.openalliance.ad.analysis.c cVar;
        String str;
        int i3;
        try {
            try {
                dg dgVar = new dg(this);
                this.c.beginTransaction();
                if (a(i2)) {
                    dgVar.a();
                } else {
                    dgVar.b();
                }
                this.c.setTransactionSuccessful();
                this.c.endTransaction();
                cVar = new com.huawei.openalliance.ad.analysis.c(this.g);
                str = "hiad.db";
                i3 = 0;
            } catch (Throwable th) {
                this.c.endTransaction();
                new com.huawei.openalliance.ad.analysis.c(this.g).a("hiad.db", i2, i, 1, "2100011");
                throw th;
            }
        } catch (Throwable unused) {
            ho.c(f6694a, "initTables error");
            this.c.endTransaction();
            cVar = new com.huawei.openalliance.ad.analysis.c(this.g);
            str = "hiad.db";
            i3 = 1;
        }
        cVar.a(str, i2, i, i3, "2100011");
    }

    public static dd a(Context context) {
        dd ddVar;
        synchronized (f) {
            if (d == null) {
                d = new dd(context.getApplicationContext());
            }
            b.incrementAndGet();
            ddVar = d;
        }
        return ddVar;
    }

    private dd(final Context context) {
        super(context, "hiad.db", null, 97, new DatabaseErrorHandler() { // from class: com.huawei.openalliance.ad.dd.1
            @Override // android.database.DatabaseErrorHandler
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                try {
                    if (dd.e.decrementAndGet() < 0) {
                        ho.a(dd.f6694a, "try time more three");
                        return;
                    }
                    ho.a(dd.f6694a, "reset time %s", dd.e);
                    com.huawei.openalliance.ad.utils.ae.e(context.getDatabasePath("hiad.db"));
                    ho.a(dd.f6694a, "delete dababases");
                    synchronized (dd.f) {
                        dd unused = dd.d = new dd(context);
                        ho.a(dd.f6694a, "rebuilding databases");
                    }
                    new com.huawei.openalliance.ad.analysis.c(context).a("hiad.db", 97, 97, 0, "2100012");
                } catch (Exception unused2) {
                    ho.c(dd.f6694a, "upgrade databases error");
                } finally {
                    new com.huawei.openalliance.ad.analysis.c(context).a("hiad.db", 97, 97, 1, "2100012");
                }
            }
        });
        this.c = null;
        this.g = context;
    }
}
