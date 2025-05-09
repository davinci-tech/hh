package health.compact.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.db.backup.SubstituteDataBaseHelper;
import defpackage.jev;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.DefaultDatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteConnection;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;
import net.zetetic.database.sqlcipher.SQLiteOpenHelper;

/* loaded from: classes.dex */
public class DataBaseHelper extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private static volatile String f13110a;
    private volatile SQLiteDatabase b;
    private final String e;
    private int h;
    private final String i;
    private static final Map<String, DataBaseHelper> d = new ConcurrentHashMap(5);
    private static final Map<String, Object> c = new ConcurrentHashMap(5);

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    private DataBaseHelper(Context context, String str, String str2, SQLiteDatabaseHook sQLiteDatabaseHook, String str3) {
        super(context, str, str2, (SQLiteDatabase.CursorFactory) null, 112, 0, (DatabaseErrorHandler) new b(), sQLiteDatabaseHook, false);
        this.i = str3;
        this.e = str;
    }

    private static void d() {
        if (TextUtils.isEmpty(f13110a)) {
            synchronized (DataBaseHelper.class) {
                if (TextUtils.isEmpty(f13110a)) {
                    byte[] a2 = KeyManager.a(14);
                    if (a2 != null) {
                        f13110a = new String(a2, StandardCharsets.UTF_8);
                    }
                }
            }
        }
    }

    private static Object d(String str) {
        Object obj;
        Map<String, Object> map = c;
        Object obj2 = map.get(str);
        if (obj2 != null) {
            return obj2;
        }
        synchronized (DataBaseHelper.class) {
            obj = map.get(str);
            if (obj == null) {
                obj = new Object();
                map.put(str, obj);
            }
        }
        return obj;
    }

    private static DataBaseHelper e(final String str) {
        String str2;
        Map<String, DataBaseHelper> map = d;
        DataBaseHelper dataBaseHelper = map.get(str);
        if (dataBaseHelper != null) {
            return dataBaseHelper;
        }
        synchronized (d(str)) {
            DataBaseHelper dataBaseHelper2 = map.get(str);
            if (dataBaseHelper2 != null) {
                c.remove(str);
                return dataBaseHelper2;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Context context = BaseApplication.getContext();
            jev.e(context);
            if (TextUtils.isEmpty("com.huawei.health")) {
                str2 = "SportDatas.db";
            } else {
                str2 = "com.huawei.health".replaceAll("\\.", "_") + str + ".db";
            }
            String str3 = str2;
            SQLiteDatabaseHook sQLiteDatabaseHook = new SQLiteDatabaseHook() { // from class: health.compact.a.DataBaseHelper.5
                @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
                public void preKey(SQLiteConnection sQLiteConnection) {
                }

                @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
                public void postKey(SQLiteConnection sQLiteConnection) {
                    if (sQLiteConnection == null) {
                        com.huawei.hwlogsmodel.LogUtil.h("DataBaseHelper", "getDataBaseHelper postKey database is null");
                        return;
                    }
                    if (SharedPreferenceManager.a("health_db_module", "ExecCipherMigrateKey" + str, false)) {
                        return;
                    }
                    try {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        sQLiteConnection.executeRaw("PRAGMA cipher_migrate;", null, null);
                        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("R_DataBaseHelper", "moduleId:", str, " postKey migrateTime:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime2));
                        SharedPreferenceManager.e("health_db_module", "ExecCipherMigrateKey" + str, true);
                    } catch (SQLiteException e) {
                        com.huawei.hwlogsmodel.LogUtil.b("DataBaseHelper", "getDataBaseHelper hook sqliteexception e=", e.getMessage());
                    } catch (Exception unused) {
                        com.huawei.hwlogsmodel.LogUtil.b("DataBaseHelper", "getDataBaseHelper hook Exception");
                    }
                }
            };
            d();
            DataBaseHelper dataBaseHelper3 = new DataBaseHelper(context, str3, f13110a, sQLiteDatabaseHook, str);
            map.put(str, dataBaseHelper3);
            c.remove(str);
            if (!"SportDatas.db".equals(str3) && !KeyManager.c(str3)) {
                com.huawei.hwlogsmodel.LogUtil.b("R_DataBaseHelper", "getDataBaseHelper error,databaseName:", str3);
            }
            com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "end to getDataBaseHelper for the first time. the moduleId: ", str, "cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            return dataBaseHelper3;
        }
    }

    public static DataBaseHelper c(String str) {
        return e(str);
    }

    public int b() {
        int i;
        synchronized (this) {
            i = this.h;
        }
        return i;
    }

    private void b(int i) {
        this.h = i;
    }

    public boolean e() {
        boolean deleteDatabase = BaseApplication.getContext().deleteDatabase(this.e);
        health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_DataBaseHelper", "deleteDatabase ", this.e, " ret=", Boolean.valueOf(deleteDatabase));
        return deleteDatabase;
    }

    public SQLiteDatabase c() {
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b != null) {
                return this.b;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                this.b = getWritableDatabase();
                health.compact.a.hwlogsmodel.ReleaseLogUtil.e("DataBaseHelper", "getDatabase for the first time. name= ", this.e, " costTime:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                return this.b;
            } catch (SQLiteException e) {
                health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_DataBaseHelper", "getDatabase Exception=", ExceptionUtils.d(e));
                try {
                    this.b = SubstituteDataBaseHelper.d(this.i).b();
                    com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "end to getDatabase for the first time. name=", this.e);
                    return this.b;
                } catch (Exception e2) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_DataBaseHelper", "getDatabase Exception2=", ExceptionUtils.d(e2));
                    return null;
                }
            } catch (Exception e3) {
                health.compact.a.hwlogsmodel.ReleaseLogUtil.c("R_DataBaseHelper", "getDatabase Exception3=", ExceptionUtils.d(e3));
                return null;
            }
        }
    }

    public void a() {
        c();
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase == null) {
            com.huawei.hwlogsmodel.LogUtil.h("DataBaseHelper", "onUpgrade db is null");
            return;
        }
        synchronized (this) {
            com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "onUpgrade enter oldVersion=", Integer.valueOf(i), ",newVersion", Integer.valueOf(i2), ",databaseName=", this.e);
            b(i);
            if (i == 101 && "com_huawei_bone8.db".equals(this.e)) {
                b(sQLiteDatabase);
            }
            if ("com_huawei_health10033.db".equals(this.e)) {
                if (i == 108) {
                    e(sQLiteDatabase);
                    i = 109;
                }
                if (i == 109 || i == 110) {
                    d(sQLiteDatabase);
                }
            }
            c(sQLiteDatabase);
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.rawQuery("select name from sqlite_master where type='table' order by name", (String[]) null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "onUpgradeExecSql table name = ", cursor.getString(0));
                    }
                }
                sQLiteDatabase.execSQL("ALTER TABLE module_8_event_alarm RENAME TO module_8_event_alarm_temp");
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS module_8_event_alarm(event_alarm_index integer,event_alarm_enable integer,event_alarm_time varchar(50),event_alarm_cycle integer,event_alarm_name varchar(50),User_ID varchar(50))");
                sQLiteDatabase.execSQL("insert into module_8_event_alarm(event_alarm_index,event_alarm_enable,event_alarm_time,event_alarm_cycle,event_alarm_name,User_ID) select event_alarm_index,event_alarm_enable,event_alarm_time,event_alarm_cycle,event_alarm_name,User_ID from module_8_event_alarm_temp");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS module_8_event_alarm_temp");
                sQLiteDatabase.execSQL("ALTER TABLE module_8_smart_alarm RENAME TO module_8_smart_alarm_temp");
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS module_8_smart_alarm(smart_alarm_index integer,smart_alarm_enable integer,smart_alarm_time varchar(50),smart_alarm_cycle integer,smart_alarm_ahead_time integer,User_ID varchar(50))");
                sQLiteDatabase.execSQL("insert into module_8_smart_alarm(smart_alarm_index,smart_alarm_enable,smart_alarm_time,smart_alarm_cycle,smart_alarm_ahead_time,User_ID) select smart_alarm_index,smart_alarm_enable,smart_alarm_time,smart_alarm_cycle,smart_alarm_ahead_time,User_ID from module_8_smart_alarm_temp");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS module_8_smart_alarm_temp");
                com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "alarm db upgrade");
                if (cursor == null) {
                    return;
                }
            } catch (SQLiteException e) {
                com.huawei.hwlogsmodel.LogUtil.b("DataBaseHelper", "onUpgradeExecSql SQLiteException e=", e.getMessage());
                if (cursor == null) {
                    return;
                }
            } catch (Exception unused) {
                com.huawei.hwlogsmodel.LogUtil.b("DataBaseHelper", "onUpgradeExecSql Exception");
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            e(sQLiteDatabase, "resistanceFreq");
            e(sQLiteDatabase, "resistanceLeftArmRightArmHf");
            e(sQLiteDatabase, "resistanceLeftArmLeftLegHf");
            e(sQLiteDatabase, "resistanceLeftArmRightLegHf");
            e(sQLiteDatabase, "resistanceRightArmLeftLegHf");
            e(sQLiteDatabase, "resistanceRightArmRightLegHf");
            e(sQLiteDatabase, "resistanceLeftLegRightLegHf");
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DataBaseHelper", "upgradeVersion111to112 weightdata_db Exception");
        }
    }

    private void e(SQLiteDatabase sQLiteDatabase, String str) {
        boolean e = e(sQLiteDatabase, "module_10031_hihealth_weightData", str);
        com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "upgradeVersion111to112 onUpgrade module_10031_hihealth_weightData isHas;", Boolean.valueOf(e));
        if (e) {
            sQLiteDatabase.execSQL("ALTER TABLE module_10031_hihealth_weightData ADD COLUMN " + str + "real default null");
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        try {
            boolean e = e(sQLiteDatabase, "module_10033_WiFiBindDevice", "source");
            com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "upgradeVersion109to110 device_db WiFi device source isHas:", Boolean.valueOf(e));
            if (e) {
                return;
            }
            sQLiteDatabase.execSQL("alter table module_10033_WiFiBindDevice add source integer end");
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DataBaseHelper", "upgradeVersion109to110 device_db Exception");
        }
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        try {
            boolean e = e(sQLiteDatabase, "module_10033_WiFiBindDevice", "mac");
            com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "upgradeVersion108to109 onUpgrade device_db WiFi device isHas;", Boolean.valueOf(e));
            if (e) {
                return;
            }
            sQLiteDatabase.execSQL("alter table module_10033_WiFiBindDevice add mac TEXT end");
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("DataBaseHelper", "upgradeVersion108to109 device_db Exception");
        }
    }

    private boolean e(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor rawQuery = sQLiteDatabase.rawQuery(String.format(Locale.ENGLISH, "select sql from sqlite_master where type = 'table' and name = '%s'", str), (String[]) null);
        try {
            String string = rawQuery.moveToFirst() ? rawQuery.getString(rawQuery.getColumnIndex("sql")) : null;
            return string != null && string.contains(str2);
        } finally {
            rawQuery.close();
        }
    }

    public static class a implements Runnable {
        private String d;

        public a(String str) {
            this.d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TextUtils.isEmpty(this.d)) {
                return;
            }
            com.huawei.hwlogsmodel.LogUtil.a("DataBaseHelper", "pre-getInstance DB:", this.d);
            DataBaseHelper.c(this.d).a();
        }
    }

    static class b implements DatabaseErrorHandler {
        private final DatabaseErrorHandler b;

        private b() {
            this.b = new DefaultDatabaseErrorHandler();
        }

        @Override // net.zetetic.database.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            this.b.onCorruption(sQLiteDatabase);
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("R_DataBaseHelper", "db file corruption:", sQLiteDatabase.getPath());
        }
    }
}
