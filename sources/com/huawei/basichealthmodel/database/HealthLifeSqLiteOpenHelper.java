package com.huawei.basichealthmodel.database;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.auw;
import health.compact.a.KeyManager;
import health.compact.a.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.DefaultDatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteConnection;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;
import net.zetetic.database.sqlcipher.SQLiteOpenHelper;

/* loaded from: classes3.dex */
public class HealthLifeSqLiteOpenHelper extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private static volatile HealthLifeSqLiteOpenHelper f1898a;
    private static volatile String e;
    private SQLiteDatabase i;
    private static final String[] c = {"challengeId Integer", "taskType Integer", "dataSource Integer"};
    private static final byte[] d = new byte[0];
    private static final SQLiteDatabaseHook b = new SQLiteDatabaseHook() { // from class: com.huawei.basichealthmodel.database.HealthLifeSqLiteOpenHelper.2
        @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
        public void preKey(SQLiteConnection sQLiteConnection) {
        }

        @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
        public void postKey(SQLiteConnection sQLiteConnection) {
            try {
                sQLiteConnection.executeRaw("PRAGMA cipher_migrate;", null, null);
                sQLiteConnection.executeRaw("PRAGMA journal_mode = WAL;", null, null);
            } catch (Exception e2) {
                ReleaseLogUtil.c("R_HealthLife_HealthLifeSqLiteOpenHelper", "HealthLifeSqLiteOpenHelper postKey exception ", ExceptionUtils.d(e2));
            }
        }
    };

    public static HealthLifeSqLiteOpenHelper d() {
        if (f1898a == null) {
            synchronized (d) {
                if (f1898a == null) {
                    i();
                    f1898a = new HealthLifeSqLiteOpenHelper();
                }
            }
        }
        return f1898a;
    }

    private HealthLifeSqLiteOpenHelper() {
        super(BaseApplication.e(), "com_huawei_health10031.db", e, (SQLiteDatabase.CursorFactory) null, 113, 0, (DatabaseErrorHandler) new a(), b, true);
        c();
    }

    private void c() {
        if (this.i != null) {
            return;
        }
        synchronized (d) {
            if (this.i != null) {
                return;
            }
            try {
                this.i = getWritableDatabase();
            } catch (SQLiteException | UnsatisfiedLinkError e2) {
                ReleaseLogUtil.c("R_HealthLife_HealthLifeSqLiteOpenHelper", "initDatabase exception ", ExceptionUtils.d(e2));
            }
        }
    }

    private static void i() {
        if (TextUtils.isEmpty(e)) {
            synchronized (d) {
                if (TextUtils.isEmpty(e)) {
                    byte[] a2 = KeyManager.a(14);
                    if (a2 != null) {
                        e = new String(a2, StandardCharsets.UTF_8);
                    }
                }
            }
        }
    }

    public SQLiteDatabase a() {
        return this.i;
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("HealthLife_HealthLifeSqLiteOpenHelper", "onCreate database ", sQLiteDatabase);
        if (sQLiteDatabase == null) {
            return;
        }
        a(sQLiteDatabase);
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtil.a("HealthLife_HealthLifeSqLiteOpenHelper", "onUpgrade database ", sQLiteDatabase, " oldVersion ", Integer.valueOf(i), " newVersion ", Integer.valueOf(i2));
        if (sQLiteDatabase != null && i < 113) {
            a(sQLiteDatabase);
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        e(sQLiteDatabase);
        b(sQLiteDatabase);
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        if (auw.c(sQLiteDatabase, "module_10031_health_task_config")) {
            if (auw.a(sQLiteDatabase, "module_10031_health_task_config", ParsedFieldTag.TASK_TYPE)) {
                return;
            }
            for (String str : c) {
                sQLiteDatabase.execSQL("ALTER TABLE module_10031_health_task_config ADD " + str);
            }
            return;
        }
        sQLiteDatabase.execSQL(b());
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        if (auw.c(sQLiteDatabase, "module_10031_health_task_records")) {
            if (auw.a(sQLiteDatabase, "module_10031_health_task_records", ParsedFieldTag.TASK_TYPE)) {
                return;
            }
            for (String str : c) {
                sQLiteDatabase.execSQL("ALTER TABLE module_10031_health_task_records ADD " + str);
            }
            return;
        }
        sQLiteDatabase.execSQL(e());
    }

    static class a implements DatabaseErrorHandler {
        private final DatabaseErrorHandler e;

        private a() {
            this.e = new DefaultDatabaseErrorHandler();
        }

        @Override // net.zetetic.database.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            this.e.onCorruption(sQLiteDatabase);
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("HealthLife_HealthLifeSqLiteOpenHelper", "db file corruption:", sQLiteDatabase.getPath());
        }
    }

    private String e() {
        return "create table IF NOT EXISTS module_10031_health_task_records(huid text not null,timezone text,timestamp Long not null,recordDay Integer not null,id Integer not null,dataSource Integer,challengeId Integer,taskType Integer,syncStatus Integer,condition text,reserve text,target text not null,result text not null,type Integer not null,status Integer not null,restStatus Integer,primary key (huid, recordDay, id))";
    }

    private String b() {
        return "create table IF NOT EXISTS module_10031_health_task_config(huid text not null,timezone text,timestamp Long not null,recordDay Integer not null,id Integer not null,dataSource Integer,challengeId Integer,taskType Integer,syncStatus Integer,condition text,reserve text,target text not null,lastTarget text not null,primary key (huid, recordDay, id))";
    }
}
