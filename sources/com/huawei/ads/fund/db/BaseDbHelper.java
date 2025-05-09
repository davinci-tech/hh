package com.huawei.ads.fund.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.wj;
import defpackage.wk;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public abstract class BaseDbHelper extends SQLiteOpenHelper {
    public static final AtomicInteger OPEN_COUNTER = new AtomicInteger();

    /* renamed from: a, reason: collision with root package name */
    private SQLiteDatabase f1689a;

    protected abstract BaseDbUpdateHelper getDbUpdateHelper();

    protected abstract String getTag();

    protected boolean needKeepData() {
        return false;
    }

    public void update(String str, ContentValues contentValues, String str2, List<String> list) {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this) {
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        sQLiteDatabase = getWritableDatabase();
                        try {
                            sQLiteDatabase.beginTransaction();
                            Iterator<String> it = list.iterator();
                            while (it.hasNext()) {
                                sQLiteDatabase.update(str, contentValues, str2, new String[]{it.next()});
                            }
                            sQLiteDatabase.setTransactionSuccessful();
                        } catch (Throwable th) {
                            th = th;
                            try {
                                HiAdLog.printSafeExceptionMessage(5, getTag(), "update ", th);
                            } finally {
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.endTransaction();
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        sQLiteDatabase = null;
                    }
                    return;
                }
            }
            getTag();
        }
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        int i;
        synchronized (this) {
            try {
                i = getWritableDatabase().update(str, contentValues, str2, strArr);
            } catch (Throwable th) {
                HiAdLog.w(getTag(), "update %s", th.getClass().getSimpleName());
                getTag();
                th.getMessage();
                i = 0;
            }
        }
        return i;
    }

    public Cursor rawQuery(String str, String[] strArr) {
        Cursor rawQuery;
        synchronized (this) {
            rawQuery = getReadableDatabase().rawQuery(str, strArr);
        }
        return rawQuery;
    }

    public long queryCount(String str) {
        synchronized (this) {
            if (!a(str)) {
                return 0L;
            }
            try {
                return getReadableDatabase().compileStatement("SELECT COUNT(*) FROM \"" + str + '\"').simpleQueryForLong();
            } catch (Exception e) {
                HiAdLog.printSafeStackTrace(3, e);
                return 0L;
            }
        }
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4) {
        Cursor query;
        synchronized (this) {
            query = getReadableDatabase().query(str, strArr, str2, strArr2, null, null, str3, str4);
        }
        return query;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        AtomicInteger atomicInteger = OPEN_COUNTER;
        atomicInteger.incrementAndGet();
        this.f1689a = sQLiteDatabase;
        a(needKeepData());
        atomicInteger.decrementAndGet();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        AtomicInteger atomicInteger = OPEN_COUNTER;
        atomicInteger.incrementAndGet();
        this.f1689a = sQLiteDatabase;
        a(needKeepData());
        atomicInteger.decrementAndGet();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        AtomicInteger atomicInteger = OPEN_COUNTER;
        atomicInteger.incrementAndGet();
        this.f1689a = sQLiteDatabase;
        a(false);
        atomicInteger.decrementAndGet();
    }

    public void insertOrUpdate(List<wk> list) {
        synchronized (this) {
            if (list != null) {
                if (!list.isEmpty()) {
                    SQLiteDatabase sQLiteDatabase = null;
                    try {
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                        try {
                            writableDatabase.beginTransaction();
                            for (wk wkVar : list) {
                                if (writableDatabase.update(wkVar.d(), wkVar.do_(), wkVar.f(), wkVar.c()) <= 0) {
                                    writableDatabase.insertOrThrow(wkVar.d(), null, wkVar.do_());
                                }
                            }
                            writableDatabase.setTransactionSuccessful();
                            writableDatabase.endTransaction();
                        } catch (Throwable th) {
                            th = th;
                            sQLiteDatabase = writableDatabase;
                            try {
                                HiAdLog.printSafeExceptionMessage(5, getTag(), "insertOrUpdate ", th);
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

    public void insert(List<wk> list) {
        synchronized (this) {
            if (ListUtil.isEmpty(list)) {
                return;
            }
            SQLiteDatabase sQLiteDatabase = null;
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    for (wk wkVar : list) {
                        writableDatabase.insertOrThrow(wkVar.d(), null, wkVar.do_());
                    }
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = writableDatabase;
                    try {
                        getTag();
                        th.getMessage();
                        HiAdLog.w(getTag(), "insert ex " + th.getClass().getSimpleName());
                        HiAdLog.printSafeStackTrace(3, th);
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

    public long insert(String str, ContentValues contentValues) {
        long j;
        synchronized (this) {
            try {
                j = getWritableDatabase().insertOrThrow(str, null, contentValues);
            } catch (Throwable th) {
                getTag();
                th.getMessage();
                HiAdLog.w(getTag(), "insert ex " + th.getClass().getSimpleName());
                HiAdLog.printSafeStackTrace(3, th);
                j = -1;
            }
        }
        return j;
    }

    void f(String str) {
        if (!a(str)) {
            throw new wj(String.format(Locale.ENGLISH, "tableName: %s is invalid", str.trim()));
        }
        this.f1689a.execSQL(" ALTER TABLE " + str + " RENAME TO _temp_" + str);
    }

    public void executeSql(String str, Object[] objArr) {
        synchronized (this) {
            try {
                getWritableDatabase().execSQL(str, objArr);
            } catch (Throwable th) {
                HiAdLog.w(getTag(), "update %s", th.getClass().getSimpleName());
            }
        }
    }

    public void executeSQL(String str) {
        try {
            this.f1689a.execSQL(str);
        } catch (Exception unused) {
            HiAdLog.w(getTag(), "executeSQL error");
            throw new wj("execute sql failed");
        }
    }

    boolean e(String str) {
        Cursor cursor;
        if (!a(str)) {
            throw new wj(String.format(Locale.ENGLISH, "tableName: %s is invalid", str.trim()));
        }
        boolean z = false;
        try {
            cursor = this.f1689a.rawQuery("select count(1) as c from sqlite_master where type ='table' and name = ?", new String[]{str.trim()});
        } catch (Throwable unused) {
            cursor = null;
        }
        try {
            if (cursor.moveToNext()) {
                if (cursor.getInt(0) > 0) {
                    z = true;
                }
            }
            cursor.close();
            return z;
        } catch (Throwable unused2) {
            try {
                throw new wj(String.format(Locale.ENGLISH, "query tableName: %s failed", str.trim()));
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    public void delete(String str, String str2, List<String> list) {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this) {
            if (list != null) {
                if (!list.isEmpty()) {
                    try {
                        sQLiteDatabase = getWritableDatabase();
                        try {
                            sQLiteDatabase.beginTransaction();
                            Iterator<String> it = list.iterator();
                            while (it.hasNext()) {
                                sQLiteDatabase.delete(str, str2, new String[]{it.next()});
                            }
                            sQLiteDatabase.setTransactionSuccessful();
                            sQLiteDatabase.endTransaction();
                            return;
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
            getTag();
        }
    }

    public int delete(String str, String str2, String[] strArr) {
        int i;
        synchronized (this) {
            i = 0;
            try {
                i = getWritableDatabase().delete(str, str2, strArr);
                getTag();
                Arrays.toString(strArr);
            } catch (Throwable th) {
                HiAdLog.w(getTag(), "delete " + th.getClass().getSimpleName());
            }
        }
        return i;
    }

    String[] d(String str) {
        if (!a(str)) {
            throw new wj(String.format(Locale.ENGLISH, "tableName: %s is invalid", str.trim()));
        }
        Cursor cursor = null;
        try {
            try {
                cursor = this.f1689a.rawQuery(" select * from " + str + " order by _id asc LIMIT 1", null);
                cursor.moveToNext();
                String[] columnNames = cursor.getColumnNames();
                cursor.close();
                return columnNames;
            } catch (Exception unused) {
                HiAdLog.w(getTag(), "getColumnNames error");
                throw new wj(String.format(Locale.ENGLISH, "get new tableName: %s column names failed", str.trim()));
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (OPEN_COUNTER.decrementAndGet() <= 0) {
                super.close();
            }
        }
    }

    public void clearExpiredData() {
        getDbUpdateHelper().a();
    }

    void c(String str) {
        if (!a(str)) {
            HiAdLog.w(getTag(), "tableName: %s is invalid", str);
            return;
        }
        try {
            this.f1689a.execSQL(" DROP TABLE _temp_" + str);
        } catch (Exception e) {
            HiAdLog.w(getTag(), "delete temp tableName: %s failed, exception: %s", str, e.getClass().getSimpleName());
        }
    }

    public void batchExecute(String str, List<String[]> list) {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this) {
            if (TextUtils.isEmpty(str) || ListUtil.isEmpty(list)) {
                getTag();
                return;
            }
            try {
                sQLiteDatabase = getWritableDatabase();
                try {
                    sQLiteDatabase.beginTransaction();
                    SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(str);
                    Iterator<String[]> it = list.iterator();
                    while (it.hasNext()) {
                        compileStatement.bindAllArgsAsStrings(it.next());
                        compileStatement.execute();
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (Throwable th) {
                    th = th;
                    try {
                        getTag();
                        th.getMessage();
                        HiAdLog.w(getTag(), "execute ex " + th.getClass().getSimpleName());
                        HiAdLog.printSafeStackTrace(3, th);
                    } finally {
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabase = null;
            }
        }
    }

    void b(String str) {
        if (!a(str)) {
            HiAdLog.w(getTag(), "tableName: %s is invalid", str);
            return;
        }
        try {
            this.f1689a.execSQL(" DROP TABLE " + str);
        } catch (Exception e) {
            HiAdLog.w(getTag(), "delete table: %s fail, exception: %s", str, e.getClass().getSimpleName());
        }
    }

    public void a() {
        synchronized (this) {
            close();
        }
    }

    private boolean a(String str) {
        return getDbUpdateHelper().b(str);
    }

    private void a(boolean z) {
        try {
            try {
                BaseDbUpdateHelper dbUpdateHelper = getDbUpdateHelper();
                this.f1689a.beginTransaction();
                if (z) {
                    dbUpdateHelper.c();
                } else {
                    dbUpdateHelper.b();
                }
                dbUpdateHelper.createTriggers();
                this.f1689a.setTransactionSuccessful();
            } catch (wj unused) {
                HiAdLog.w(getTag(), "initTables error");
            }
        } finally {
            this.f1689a.endTransaction();
        }
    }

    public BaseDbHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.f1689a = null;
    }
}
