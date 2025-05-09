package com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jev;
import defpackage.sqc;
import health.compact.a.CommonUtil;
import health.compact.a.KeyManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.DefaultDatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteConnection;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;
import net.zetetic.database.sqlcipher.SQLiteOpenHelper;

/* loaded from: classes5.dex */
public class SecurityCalendarDbHelper extends SQLiteOpenHelper {
    private static volatile SecurityCalendarDbHelper c;
    private static volatile SQLiteDatabase e;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f6335a = new Object();
    private static final SQLiteDatabaseHook d = new SQLiteDatabaseHook() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SecurityCalendarDbHelper.3
        @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
        public void preKey(SQLiteConnection sQLiteConnection) {
        }

        @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
        public void postKey(SQLiteConnection sQLiteConnection) {
            if (sQLiteConnection == null) {
                LogUtil.h("SecurityCalendarDbHelper", "DB_HOOK#postKey(SQLiteDatabase): database is null");
                return;
            }
            if (SharedPreferenceManager.a("health_db_module", "ExecCipherMigrateKeycalendar_sync_data.db", false)) {
                return;
            }
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                sQLiteConnection.executeRaw("PRAGMA cipher_migrate;", null, null);
                ReleaseLogUtil.e("R_SecurityCalendarDbHelper", " postKey migrateTime:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                SharedPreferenceManager.e("health_db_module", "ExecCipherMigrateKeycalendar_sync_data.db", true);
            } catch (SQLiteException e2) {
                LogUtil.b("SecurityCalendarDbHelper", "DB_HOOK#postKey(SQLiteDatabase): SQLiteException occurred.", e2.getMessage());
            } catch (Exception unused) {
                LogUtil.b("SecurityCalendarDbHelper", "DB_HOOK#postKey(SQLiteDatabase): third lib unknown exception");
            }
        }
    };
    private static volatile String b = "ydt";

    private SecurityCalendarDbHelper(Context context) {
        super(context, "calendar_sync_data.db", b, (SQLiteDatabase.CursorFactory) null, 1, 0, (DatabaseErrorHandler) new c(), d, false);
        LogUtil.a("SecurityCalendarDbHelper", "SecurityContactsDbHelper: ");
    }

    public static SecurityCalendarDbHelper d(Context context) {
        SecurityCalendarDbHelper securityCalendarDbHelper;
        if (context == null) {
            context = BaseApplication.getContext();
        }
        synchronized (f6335a) {
            if (c == null) {
                jev.e(context);
                c(context);
                e();
                c = new SecurityCalendarDbHelper(context);
            }
            securityCalendarDbHelper = c;
        }
        return securityCalendarDbHelper;
    }

    private static void c(Context context) {
        if (!CommonUtil.av()) {
            LogUtil.a("SecurityCalendarDbHelper", "setDbFileFlag: is not emui11+.");
            return;
        }
        File databasePath = context.getDatabasePath("calendar_sync_data.db");
        if (databasePath.exists()) {
            LogUtil.a("SecurityCalendarDbHelper", "setDbFileFlag: database file existed.");
            return;
        }
        try {
            if (databasePath.createNewFile()) {
                sqc.c(databasePath.getPath(), "S2", 0);
            } else {
                LogUtil.a("SecurityCalendarDbHelper", "setDbFileFlag: failed to create db file.");
            }
        } catch (IOException unused) {
            LogUtil.b("SecurityCalendarDbHelper", "setDbFileFlag: create db file error.");
        }
    }

    public void d() {
        LogUtil.a("SecurityCalendarDbHelper", "createSyncStateTable: ", "calendar_sync_state");
        if (!a("calendar_sync_state")) {
            c("CREATE TABLE calendar_sync_state (_id INTEGER PRIMARY KEY AUTOINCREMENT,device_id VARCHAR(36), device_table_name VARCHAR(36), device_sync_flag INTEGER, device_sync_state INTEGER,minor INTEGER)");
        } else {
            LogUtil.a("SecurityCalendarDbHelper", "createSyncStateTable: table existed.");
        }
    }

    public void b(String str) {
        LogUtil.a("SecurityCalendarDbHelper", "createSyncStateTable: " + str);
        if (!a(str)) {
            c("CREATE TABLE " + str + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,event_uuid TEXT, operation TEXT, event_id INTEGER, dtstart INTEGER, dtend INTEGER, all_day INTEGER, calendarFeature TEXT)");
            return;
        }
        LogUtil.a("SecurityCalendarDbHelper", "createSyncStateTable: table existed.");
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SecurityCalendarDbHelper", "executeSql: sql is null or empty.");
            return;
        }
        if (e == null) {
            LogUtil.h("SecurityCalendarDbHelper", "executeSql: sDatabase is null");
        } else {
            if (!e.isOpen()) {
                LogUtil.h("SecurityCalendarDbHelper", "executeSql: sDatabase is not open.");
                return;
            }
            try {
                e.execSQL(str);
            } catch (SQLiteException unused) {
                LogUtil.b("SecurityCalendarDbHelper", "executeSql: occurred SQLException");
            }
        }
    }

    public SQLiteDatabase c() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (f6335a) {
            if (e == null) {
                e(c, b);
            }
            sQLiteDatabase = e;
        }
        return sQLiteDatabase;
    }

    public void d(String str) {
        LogUtil.a("SecurityCalendarDbHelper", "clearTable: tableName= " + str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SecurityCalendarDbHelper", "clearTable: tableName is null");
            return;
        }
        if (e == null) {
            LogUtil.h("SecurityCalendarDbHelper", "clearTable: sDb is null");
            return;
        }
        if (!a(str)) {
            LogUtil.a("SecurityCalendarDbHelper", "clearTable: do not have this table: ", str);
            return;
        }
        try {
            e.execSQL("delete from " + str);
        } catch (SQLiteException unused) {
            LogUtil.b("SecurityCalendarDbHelper", "clearTable: occurred SQLException");
        }
    }

    public boolean a(String str) {
        return a(e, str);
    }

    public boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        boolean z = false;
        if (sQLiteDatabase == null) {
            LogUtil.h("SecurityCalendarDbHelper", "isExist: db is null");
            return false;
        }
        if (str == null || TextUtils.isEmpty(str.trim())) {
            LogUtil.h("SecurityCalendarDbHelper", "isExist: tableName is null");
            return false;
        }
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery("select count(*) as tableCount from sqlite_master where type ='table' and name = '" + str.trim() + "'", (String[]) null);
            try {
                if (rawQuery.moveToNext()) {
                    if (rawQuery.getInt(0) > 0) {
                        z = true;
                    }
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } finally {
            }
        } catch (SQLiteException unused) {
            LogUtil.b("SecurityCalendarDbHelper", "isExist: ", str, "occurred SQLiteException");
        } catch (IllegalStateException unused2) {
            LogUtil.b("SecurityCalendarDbHelper", "isExist: ", str, "occurred IllegalStateException");
        }
        return z;
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("SecurityCalendarDbHelper", "onCreate: ");
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtil.a("SecurityCalendarDbHelper", "onUpgrade: oldVersion=", Integer.valueOf(i), "newVersion=", Integer.valueOf(i2));
    }

    private static void e() {
        byte[] a2;
        LogUtil.a("SecurityCalendarDbHelper", "initDbKey: ");
        if (!TextUtils.isEmpty(b) || (a2 = KeyManager.a(14)) == null) {
            return;
        }
        b = new String(a2, StandardCharsets.UTF_8);
    }

    private static void e(SQLiteOpenHelper sQLiteOpenHelper, String str) {
        LogUtil.a("SecurityCalendarDbHelper", "initDb: ");
        try {
            e = sQLiteOpenHelper.getWritableDatabase();
        } catch (SQLiteException e2) {
            LogUtil.b("SecurityCalendarDbHelper", "initDb: net.sqlcipher.database.SQLiteException occurred." + e2.getMessage());
            bKT_(e2);
        } catch (Exception unused) {
            LogUtil.b("SecurityCalendarDbHelper", "initDb: third lib unknown exception");
        }
    }

    private static void bKT_(SQLiteException sQLiteException) {
        int a2 = SharedPreferenceManager.a("SecurityCalendarDbHelper", "sqliteExceptionNum", 0);
        SharedPreferenceManager.b("SecurityCalendarDbHelper", "sqliteExceptionNum", a2 + 1);
        ReleaseLogUtil.c("R_SecurityCalendarDbHelper", "processInitDbException getSyncContactState: ", Integer.valueOf(a2));
        if (a2 >= 2) {
            SharedPreferenceManager.b("SecurityCalendarDbHelper", "sqliteExceptionNum", 0);
            String message = sQLiteException.getMessage();
            if (!TextUtils.isEmpty(message) && message.contains("SQL logic error") && message.contains("select count(*) from sqlite_master")) {
                int a3 = SharedPreferenceManager.a("SecurityCalendarDbHelper", "exceptionDeleteNum", 0);
                SharedPreferenceManager.b("SecurityCalendarDbHelper", "exceptionDeleteNum", a3 + 1);
                ReleaseLogUtil.c("R_SecurityCalendarDbHelper", "initDb SQL logic error deleteDatabase", Integer.valueOf(a3));
                BaseApplication.getContext().deleteDatabase("calendar_sync_data.db");
                Process.killProcess(Process.myPid());
            }
        }
    }

    static class c implements DatabaseErrorHandler {
        private final DatabaseErrorHandler b;

        private c() {
            this.b = new DefaultDatabaseErrorHandler();
        }

        @Override // net.zetetic.database.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            this.b.onCorruption(sQLiteDatabase);
            ReleaseLogUtil.d("SecurityCalendarDbHelper", "db file corruption:", sQLiteDatabase.getPath());
        }
    }
}
