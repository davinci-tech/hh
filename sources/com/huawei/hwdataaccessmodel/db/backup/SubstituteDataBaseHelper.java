package com.huawei.hwdataaccessmodel.db.backup;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jev;
import health.compact.a.KeyManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.DefaultDatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteConnection;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;
import net.zetetic.database.sqlcipher.SQLiteOpenHelper;

/* loaded from: classes.dex */
public class SubstituteDataBaseHelper extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, SubstituteDataBaseHelper> f6272a = new ConcurrentHashMap(5);
    private static final Map<String, Object> c = new ConcurrentHashMap(5);
    private static volatile String d;
    private volatile SQLiteDatabase e;

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    private SubstituteDataBaseHelper(Context context, String str, String str2, SQLiteDatabaseHook sQLiteDatabaseHook) {
        super(context, str, str2, (SQLiteDatabase.CursorFactory) null, 102, 0, (DatabaseErrorHandler) new d(), sQLiteDatabaseHook, false);
    }

    private static void a() {
        if (TextUtils.isEmpty(d)) {
            synchronized (SubstituteDataBaseHelper.class) {
                if (TextUtils.isEmpty(d)) {
                    byte[] a2 = KeyManager.a(14);
                    if (a2 != null) {
                        d = new String(a2, StandardCharsets.UTF_8);
                    }
                }
            }
        }
    }

    private static Object b(String str) {
        Object obj;
        Map<String, Object> map = c;
        Object obj2 = map.get(str);
        if (obj2 != null) {
            return obj2;
        }
        synchronized (SubstituteDataBaseHelper.class) {
            obj = map.get(str);
            if (obj == null) {
                obj = new Object();
                map.put(str, obj);
            }
        }
        return obj;
    }

    private static SubstituteDataBaseHelper e(final String str) {
        String str2;
        Map<String, SubstituteDataBaseHelper> map = f6272a;
        SubstituteDataBaseHelper substituteDataBaseHelper = map.get(str);
        if (substituteDataBaseHelper != null) {
            return substituteDataBaseHelper;
        }
        synchronized (b(str)) {
            SubstituteDataBaseHelper substituteDataBaseHelper2 = map.get(str);
            if (substituteDataBaseHelper2 != null) {
                c.remove(str);
                return substituteDataBaseHelper2;
            }
            Context context = BaseApplication.getContext();
            jev.e(context);
            String packageName = context.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                str2 = "SubstituteSportDatas.db";
            } else {
                str2 = packageName.replaceAll("\\.", "_") + "Sub" + str + ".db";
            }
            SQLiteDatabaseHook sQLiteDatabaseHook = new SQLiteDatabaseHook() { // from class: com.huawei.hwdataaccessmodel.db.backup.SubstituteDataBaseHelper.3
                @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
                public void preKey(SQLiteConnection sQLiteConnection) {
                }

                @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
                public void postKey(SQLiteConnection sQLiteConnection) {
                    if (sQLiteConnection == null) {
                        LogUtil.h("SubstituteDataBaseHelper", "postKey database is null");
                        return;
                    }
                    if (SharedPreferenceManager.a("health_db_module", "ExecCipherMigrateKey" + str, false)) {
                        return;
                    }
                    try {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        sQLiteConnection.executeRaw("PRAGMA cipher_migrate;", null, null);
                        ReleaseLogUtil.e("SubstituteDataBaseHelper", " postKey migrateTime:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                        SharedPreferenceManager.e("health_db_module", "ExecCipherMigrateKey" + str, true);
                    } catch (SQLiteException e) {
                        LogUtil.b("SubstituteDataBaseHelper", "hook sqliteexception e=", e.getMessage());
                    } catch (Exception unused) {
                        LogUtil.b("SubstituteDataBaseHelper", "hook Exception");
                    }
                }
            };
            a();
            SubstituteDataBaseHelper substituteDataBaseHelper3 = new SubstituteDataBaseHelper(context, str2, d, sQLiteDatabaseHook);
            map.put(str, substituteDataBaseHelper3);
            c.remove(str);
            return substituteDataBaseHelper3;
        }
    }

    public static SubstituteDataBaseHelper d(String str) {
        return e(str);
    }

    public SQLiteDatabase b() {
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (this.e != null) {
                return this.e;
            }
            this.e = getWritableDatabase();
            return this.e;
        }
    }

    /* loaded from: classes5.dex */
    static class d implements DatabaseErrorHandler {
        private final DatabaseErrorHandler c;

        private d() {
            this.c = new DefaultDatabaseErrorHandler();
        }

        @Override // net.zetetic.database.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            this.c.onCorruption(sQLiteDatabase);
            ReleaseLogUtil.d("SubstituteDataBaseHelper", "db file corruption:", sQLiteDatabase.getPath());
        }
    }
}
