package com.huawei.hihealthservice.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes4.dex */
public abstract class DBCommon {
    private static final int DB_EXCEPTION = -1001;
    public static final int DB_NULL_ERROR = -1000;
    private static final int DURATION_THRESHOLD = 1000;
    private static final int DURATION_THRESHOLD_LONG_TIME = 3000;
    private static final String LOG_TAG = "HiH_DBCommon";
    private final String mDbName;

    protected abstract String[] getColumns();

    protected abstract String getTableName();

    public DBCommon() {
        this.mDbName = null;
    }

    public DBCommon(String str) {
        this.mDbName = str;
    }

    public long insert(ContentValues contentValues) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            SQLiteDatabase writableDatabase = HiHealthDBHelper.e(this.mDbName).getWritableDatabase();
            if (writableDatabase == null) {
                return -1000L;
            }
            long insert = writableDatabase.insert(getTableName(), (String) null, contentValues);
            printDurationLog(elapsedRealtime, getTableName(), "insert", null);
            return insert;
        } catch (SQLiteException e) {
            ReleaseLogUtil.c(LOG_TAG, "insert SQLiteException!", LogAnonymous.b((Throwable) e));
            return -1001L;
        } catch (Exception e2) {
            ReleaseLogUtil.c(LOG_TAG, "insert Exception!", LogAnonymous.b((Throwable) e2));
            return -1001L;
        }
    }

    public int delete(String str, String[] strArr) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            SQLiteDatabase writableDatabase = HiHealthDBHelper.e(this.mDbName).getWritableDatabase();
            if (writableDatabase == null) {
                return -1000;
            }
            int delete = writableDatabase.delete(getTableName(), str, strArr);
            printDurationLog(elapsedRealtime, getTableName(), "delete:" + str, strArr);
            return delete;
        } catch (SQLiteException e) {
            ReleaseLogUtil.c(LOG_TAG, "delete SQLiteException!", LogAnonymous.b((Throwable) e));
            return -1001;
        } catch (Exception e2) {
            ReleaseLogUtil.c(LOG_TAG, "delete Exception!", LogAnonymous.b((Throwable) e2));
            return -1001;
        }
    }

    public int update(ContentValues contentValues, String str, String[] strArr) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            SQLiteDatabase writableDatabase = HiHealthDBHelper.e(this.mDbName).getWritableDatabase();
            if (writableDatabase == null) {
                return -1000;
            }
            int update = writableDatabase.update(getTableName(), contentValues, str, strArr);
            printDurationLog(elapsedRealtime, getTableName(), "update:" + str, strArr);
            return update;
        } catch (SQLiteException e) {
            ReleaseLogUtil.c(LOG_TAG, "update SQLiteException!", LogAnonymous.b((Throwable) e));
            return -1001;
        } catch (Exception e2) {
            ReleaseLogUtil.c(LOG_TAG, "update Exception!", LogAnonymous.b((Throwable) e2));
            return -1001;
        }
    }

    public Cursor query(String str, String[] strArr, String str2, String str3, String str4) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                SQLiteDatabase writableDatabase = HiHealthDBHelper.e(this.mDbName).getWritableDatabase();
                if (writableDatabase == null) {
                    return null;
                }
                Cursor query = writableDatabase.query(getTableName(), getColumns(), str, strArr, str2, str3, str4);
                printDurationLog(elapsedRealtime, getTableName(), "query:" + str, strArr);
                return query;
            } catch (SQLiteException e) {
                e = e;
                ReleaseLogUtil.c(LOG_TAG, "query SQLiteException!", LogAnonymous.b((Throwable) e));
                return null;
            } catch (Exception e2) {
                e = e2;
                ReleaseLogUtil.c(LOG_TAG, "query Exception!", LogAnonymous.b((Throwable) e));
                return null;
            }
        } catch (SQLiteException e3) {
            e = e3;
        } catch (Exception e4) {
            e = e4;
        }
    }

    public Cursor queryEX(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                SQLiteDatabase writableDatabase = HiHealthDBHelper.e(this.mDbName).getWritableDatabase();
                if (writableDatabase == null) {
                    return null;
                }
                Cursor query = writableDatabase.query(getTableName(), strArr, str, strArr2, str2, str3, str4);
                printDurationLog(elapsedRealtime, getTableName(), "query:" + str, strArr2);
                return query;
            } catch (SQLiteException e) {
                e = e;
                ReleaseLogUtil.c(LOG_TAG, "queryEX SQLiteException!", LogAnonymous.b((Throwable) e));
                return null;
            } catch (Exception e2) {
                e = e2;
                ReleaseLogUtil.c(LOG_TAG, "queryEX Exception!", LogAnonymous.b((Throwable) e));
                return null;
            }
        } catch (SQLiteException e3) {
            e = e3;
        } catch (Exception e4) {
            e = e4;
        }
    }

    public Cursor rawQuery(String str, String[] strArr) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            SQLiteDatabase writableDatabase = HiHealthDBHelper.e(this.mDbName).getWritableDatabase();
            if (writableDatabase == null) {
                return null;
            }
            Cursor rawQuery = writableDatabase.rawQuery(str, strArr);
            printDurationLog(elapsedRealtime, getTableName(), "rawQuery:" + str, strArr);
            return rawQuery;
        } catch (SQLiteException e) {
            ReleaseLogUtil.c(LOG_TAG, "rawQuery SQLiteException!", LogAnonymous.b((Throwable) e));
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.c(LOG_TAG, "rawQuery Exception!", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    public void execSQL(String str, Object[] objArr) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            SQLiteDatabase writableDatabase = HiHealthDBHelper.e(this.mDbName).getWritableDatabase();
            if (writableDatabase != null) {
                writableDatabase.execSQL(str, objArr);
                printDurationLog(elapsedRealtime, null, "execSQL:" + str, null);
            }
        } catch (SQLiteException e) {
            ReleaseLogUtil.c(LOG_TAG, "execSQL ClearAllInfo SQLiteException!", LogAnonymous.b((Throwable) e));
        } catch (Exception e2) {
            ReleaseLogUtil.c(LOG_TAG, "execSQL ClearAllInfo Exception!", LogAnonymous.b((Throwable) e2));
        }
    }

    private void printDurationLog(long j, String str, String str2, String[] strArr) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        if (elapsedRealtime > 3000) {
            LogUtil.a(LOG_TAG, "duration:", Long.valueOf(elapsedRealtime), "ms, tableName:" + str, ", sql:" + str2);
            LogUtil.c(LOG_TAG, "param:" + HiJsonUtil.e(strArr));
        }
    }
}
