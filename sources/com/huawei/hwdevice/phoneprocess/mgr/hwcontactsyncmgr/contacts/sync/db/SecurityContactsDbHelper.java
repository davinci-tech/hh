package com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db;

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
public class SecurityContactsDbHelper extends SQLiteOpenHelper {
    private static volatile String c;
    private static volatile SecurityContactsDbHelper d;
    private static volatile SQLiteDatabase e;
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final SQLiteDatabaseHook f6338a = new SQLiteDatabaseHook() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.SecurityContactsDbHelper.3
        @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
        public void preKey(SQLiteConnection sQLiteConnection) {
        }

        @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
        public void postKey(SQLiteConnection sQLiteConnection) {
            if (sQLiteConnection == null) {
                LogUtil.h("SecurityContactsDbHelper", "DB_HOOK#postKey(SQLiteDatabase): database is null");
                return;
            }
            if (SharedPreferenceManager.a("health_db_module", "ExecCipherMigrateKeycontact_sync_data.db", false)) {
                return;
            }
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                sQLiteConnection.executeRaw("PRAGMA cipher_migrate;", null, null);
                ReleaseLogUtil.e("R_SecurityContactsDbHelper", "postKey migrateTime:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                SharedPreferenceManager.e("health_db_module", "ExecCipherMigrateKeycontact_sync_data.db", true);
            } catch (SQLiteException e2) {
                LogUtil.b("SecurityContactsDbHelper", "DB_HOOK#postKey(SQLiteDatabase): SQLiteException occurred.", e2.getMessage());
            } catch (Exception unused) {
                LogUtil.b("SecurityContactsDbHelper", "DB_HOOK#postKey(SQLiteDatabase): third lib unknown exception");
            }
        }
    };

    private SecurityContactsDbHelper(Context context) {
        super(context, "contact_sync_data.db", c, (SQLiteDatabase.CursorFactory) null, 1, 0, (DatabaseErrorHandler) new c(), f6338a, false);
        LogUtil.a("SecurityContactsDbHelper", "SecurityContactsDbHelper: ");
    }

    public static SecurityContactsDbHelper c(Context context) {
        SecurityContactsDbHelper securityContactsDbHelper;
        if (context == null) {
            context = BaseApplication.getContext();
        }
        synchronized (b) {
            if (d == null) {
                jev.e(context);
                e(context);
                b();
                d = new SecurityContactsDbHelper(context);
            }
            securityContactsDbHelper = d;
        }
        return securityContactsDbHelper;
    }

    private static void e(Context context) {
        if (!CommonUtil.av()) {
            LogUtil.a("SecurityContactsDbHelper", "setDbFileFlag: is not emui11+.");
            return;
        }
        File databasePath = context.getDatabasePath("contact_sync_data.db");
        if (databasePath.exists()) {
            LogUtil.a("SecurityContactsDbHelper", "setDbFileFlag: database file existed.");
            return;
        }
        try {
            if (databasePath.createNewFile()) {
                sqc.c(databasePath.getPath(), "S2", 0);
            } else {
                LogUtil.a("SecurityContactsDbHelper", "setDbFileFlag: failed to create db file.");
            }
        } catch (IOException unused) {
            LogUtil.b("SecurityContactsDbHelper", "setDbFileFlag: create db file error.");
        }
    }

    public void c(String str) {
        LogUtil.a("SecurityContactsDbHelper", "createSyncDataTable: ", str);
        if (!b(str)) {
            d("CREATE TABLE " + str + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,raw_contact_id VARCHAR(10), raw_contact_uid VARCHAR(32), raw_contact_feature VARCHAR(25))");
            return;
        }
        LogUtil.a("SecurityContactsDbHelper", "createSyncDataTable: table existed.");
    }

    public void c() {
        LogUtil.a("SecurityContactsDbHelper", "createSyncStateTable: ", "contact_sync_state");
        if (!b("contact_sync_state")) {
            d("CREATE TABLE contact_sync_state (_id INTEGER PRIMARY KEY AUTOINCREMENT,device_id VARCHAR(36), device_table_name VARCHAR(36), device_sync_flag INTEGER, device_sync_state INTEGER, device_last_sync_time INTEGER)");
        } else {
            LogUtil.a("SecurityContactsDbHelper", "createSyncStateTable: table existed.");
        }
    }

    private void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SecurityContactsDbHelper", "executeSql: sql is null or empty.");
            return;
        }
        if (e == null) {
            LogUtil.h("SecurityContactsDbHelper", "executeSql: sDatabase is null");
        } else {
            if (!e.isOpen()) {
                LogUtil.h("SecurityContactsDbHelper", "executeSql: sDatabase is not open.");
                return;
            }
            try {
                e.execSQL(str);
            } catch (SQLiteException unused) {
                LogUtil.b("SecurityContactsDbHelper", "executeSql: occurred SQLException");
            }
        }
    }

    public SQLiteDatabase e() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (b) {
            if (e == null) {
                e(d, c);
            }
            sQLiteDatabase = e;
        }
        return sQLiteDatabase;
    }

    public void e(String str) {
        LogUtil.a("SecurityContactsDbHelper", "clearTable: tableName=", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SecurityContactsDbHelper", "clearTable: tableName is null");
            return;
        }
        if (e == null) {
            LogUtil.h("SecurityContactsDbHelper", "clearTable: sDb is null");
            return;
        }
        if (!b(str)) {
            LogUtil.a("SecurityContactsDbHelper", "clearTable: do not have this table: ", str);
            return;
        }
        try {
            e.execSQL("delete from " + str);
        } catch (SQLiteException unused) {
            LogUtil.b("SecurityContactsDbHelper", "clearTable: occurred SQLException");
        }
    }

    public boolean b(String str) {
        return a(e, str);
    }

    public boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        boolean z = false;
        if (sQLiteDatabase == null) {
            LogUtil.h("SecurityContactsDbHelper", "isExist: db is null");
            return false;
        }
        if (str == null || TextUtils.isEmpty(str.trim())) {
            LogUtil.h("SecurityContactsDbHelper", "isExist: tableName is null");
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
            LogUtil.b("SecurityContactsDbHelper", "isExist: ", str, "occurred SQLiteException");
        } catch (IllegalStateException unused2) {
            LogUtil.b("SecurityContactsDbHelper", "isExist: ", str, "occurred IllegalStateException");
        }
        return z;
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("SecurityContactsDbHelper", "onCreate: ");
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtil.a("SecurityContactsDbHelper", "onUpgrade: oldVersion=", Integer.valueOf(i), "newVersion=", Integer.valueOf(i2));
    }

    private static void b() {
        byte[] a2;
        LogUtil.a("SecurityContactsDbHelper", "initDbKey: ");
        if (!TextUtils.isEmpty(c) || (a2 = KeyManager.a(14)) == null) {
            return;
        }
        c = new String(a2, StandardCharsets.UTF_8);
    }

    private static void e(SQLiteOpenHelper sQLiteOpenHelper, String str) {
        LogUtil.a("SecurityContactsDbHelper", "initDb: ");
        try {
            e = sQLiteOpenHelper.getWritableDatabase();
        } catch (SQLiteException e2) {
            LogUtil.b("SecurityContactsDbHelper", "initDb: net.sqlcipher.database.SQLiteException occurred.", e2.getMessage());
            bMf_(e2);
            return;
        } catch (Exception unused) {
            LogUtil.b("SecurityContactsDbHelper", "initDb: third lib unknown exception");
        }
        SharedPreferenceManager.b("SecurityContactsDbHelper", "sqliteExceptionNum", 0);
    }

    private static void bMf_(SQLiteException sQLiteException) {
        int a2 = SharedPreferenceManager.a("SecurityContactsDbHelper", "sqliteExceptionNum", 0);
        SharedPreferenceManager.b("SecurityContactsDbHelper", "sqliteExceptionNum", a2 + 1);
        ReleaseLogUtil.c("R_SecurityContactsDbHelper", "processInitDbException getSyncContactState: ", Integer.valueOf(a2));
        if (a2 >= 2) {
            SharedPreferenceManager.b("SecurityContactsDbHelper", "sqliteExceptionNum", 0);
            String message = sQLiteException.getMessage();
            if (!TextUtils.isEmpty(message) && message.contains("SQL logic error") && message.contains("select count(*) from sqlite_master")) {
                int a3 = SharedPreferenceManager.a("SecurityContactsDbHelper", "exceptionDeleteNum", 0);
                SharedPreferenceManager.b("SecurityContactsDbHelper", "exceptionDeleteNum", a3 + 1);
                ReleaseLogUtil.c("R_SecurityContactsDbHelper", "initDb SQL logic error deleteDatabase", Integer.valueOf(a3));
                BaseApplication.getContext().deleteDatabase("contact_sync_data.db");
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
            ReleaseLogUtil.d("SecurityContactsDbHelper", "db file corruption:", sQLiteDatabase.getPath());
        }
    }
}
