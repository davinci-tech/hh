package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.store.SharedStoreManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.DefaultDatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteConnection;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;
import net.zetetic.database.sqlcipher.SQLiteOpenHelper;

/* loaded from: classes2.dex */
public class tnr {
    private tnr() {
    }

    public static void b() {
        if (c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: tnr.4
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (tnr.class) {
                        if (tnr.c()) {
                            tnr.j();
                            tnr.g();
                            tnr.i();
                        }
                    }
                }
            });
        }
    }

    public static boolean c() {
        return !"ok".equals(SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").getString("sync_data", ""));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() {
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").edit();
        edit.putString("sync_data", "ok");
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void j() {
        /*
            java.lang.String r0 = "UdsDataToMmkvUtil"
            java.lang.String r1 = "sync Data From Database"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "R_UdsDataToMmkvUtil"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r1)
            r1 = 0
            r2 = 1
            r3 = 0
            tnr$a r4 = tnr.a.e()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            net.zetetic.database.sqlcipher.SQLiteDatabase r4 = tnr.a.e(r4)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            if (r4 != 0) goto L29
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r6 = "database is null."
            r5[r1] = r6     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            if (r4 == 0) goto L28
            r4.close()
        L28:
            return
        L29:
            java.lang.String r6 = "UdsDeviceTable"
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r5 = r4
            android.database.Cursor r3 = r5.query(r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            if (r3 != 0) goto L4c
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r6 = "cursor is null."
            r5[r1] = r6     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            if (r3 == 0) goto L46
            r3.close()
        L46:
            if (r4 == 0) goto L4b
            r4.close()
        L4b:
            return
        L4c:
            boolean r5 = r3.moveToFirst()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            if (r5 == 0) goto L88
            java.util.HashSet r5 = new java.util.HashSet     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            r5.<init>()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
        L57:
            java.lang.String r6 = "identity"
            int r6 = r3.getColumnIndex(r6)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r6 = r3.getString(r6)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r7 = "deviceInfo"
            int r7 = r3.getColumnIndex(r7)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r7 = r3.getString(r7)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r8 = "deviceStatus"
            int r8 = r3.getColumnIndex(r8)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r8 = r3.getString(r8)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r9 = "deviceCapability"
            int r9 = r3.getColumnIndex(r9)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            java.lang.String r9 = r3.getString(r9)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            c(r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            boolean r6 = r3.moveToNext()     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> Lb3
            if (r6 != 0) goto L57
        L88:
            if (r3 == 0) goto L8d
            r3.close()
        L8d:
            if (r4 == 0) goto Lb2
            goto Laf
        L90:
            r5 = move-exception
            goto L98
        L92:
            r0 = move-exception
            r4 = r3
            goto Lb4
        L95:
            r4 = move-exception
            r5 = r4
            r4 = r3
        L98:
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r7 = "syncDataFromDatabase Exception: "
            r6[r1] = r7     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r1 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)     // Catch: java.lang.Throwable -> Lb3
            r6[r2] = r1     // Catch: java.lang.Throwable -> Lb3
            com.huawei.hwlogsmodel.LogUtil.b(r0, r6)     // Catch: java.lang.Throwable -> Lb3
            if (r3 == 0) goto Lad
            r3.close()
        Lad:
            if (r4 == 0) goto Lb2
        Laf:
            r4.close()
        Lb2:
            return
        Lb3:
            r0 = move-exception
        Lb4:
            if (r3 == 0) goto Lb9
            r3.close()
        Lb9:
            if (r4 == 0) goto Lbe
            r4.close()
        Lbe:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tnr.j():void");
    }

    private static void c(Set<String> set, String str, String str2, String str3, String str4) {
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").edit();
        set.add(str);
        edit.putString("identitys", new Gson().toJson(set));
        edit.putString(str + "_deviceInfo", str2);
        edit.putString(str + "_deviceStatus", str3);
        edit.putString(str + "_deviceCapability", str4);
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i() {
        LogUtil.a("UdsDataToMmkvUtil", "deleteDatabase ", "com_huawei_health_UdsDevicesDatas.db", " ret=", Boolean.valueOf(BaseApplication.e().deleteDatabase("com_huawei_health_UdsDevicesDatas.db")));
    }

    /* loaded from: classes7.dex */
    static class a extends SQLiteOpenHelper {
        @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }

        private a(Context context, String str, String str2, SQLiteDatabaseHook sQLiteDatabaseHook) {
            super(context, str, str2, (SQLiteDatabase.CursorFactory) null, 1, 0, (DatabaseErrorHandler) new d(), sQLiteDatabaseHook, false);
            jev.e(context);
        }

        public static a e() {
            SQLiteDatabaseHook sQLiteDatabaseHook = new SQLiteDatabaseHook() { // from class: tnr.a.3
                @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
                public void preKey(SQLiteConnection sQLiteConnection) {
                }

                @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
                public void postKey(SQLiteConnection sQLiteConnection) {
                    if (sQLiteConnection == null) {
                        LogUtil.b("UdsDeviceDataBaseHelper", "getDataBaseHelper postKey database is null");
                        return;
                    }
                    if (SharedPreferenceManager.a("health_db_module", "ExecCipherMigrateKeycom_huawei_health_UdsDevicesDatas.db", false)) {
                        return;
                    }
                    try {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        sQLiteConnection.executeRaw("PRAGMA cipher_migrate;", null, null);
                        ReleaseLogUtil.e("UdsDeviceDataBaseHelper", " postKey migrateTime:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                        SharedPreferenceManager.e("health_db_module", "ExecCipherMigrateKeycom_huawei_health_UdsDevicesDatas.db", true);
                    } catch (SQLiteException e) {
                        LogUtil.b("UdsDeviceDataBaseHelper", "getDataBaseHelper hook sqliteexception: ", ExceptionUtils.d(e));
                    }
                }
            };
            byte[] a2 = KeyManager.a(14);
            String str = a2 != null ? new String(a2, StandardCharsets.UTF_8) : "";
            if (TextUtils.isEmpty(str)) {
                LogUtil.b("UdsDeviceDataBaseHelper", "getDatabase sDatabaseKey is null");
                return null;
            }
            return new a(BaseApplication.e(), "com_huawei_health_UdsDevicesDatas.db", str, sQLiteDatabaseHook);
        }

        static class d implements DatabaseErrorHandler {

            /* renamed from: a, reason: collision with root package name */
            private final DatabaseErrorHandler f17267a;

            private d() {
                this.f17267a = new DefaultDatabaseErrorHandler();
            }

            @Override // net.zetetic.database.DatabaseErrorHandler
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                this.f17267a.onCorruption(sQLiteDatabase);
                ReleaseLogUtil.d("UdsDeviceDataBaseHelper", "db file corruption:", sQLiteDatabase.getPath());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public SQLiteDatabase c() {
            SQLiteDatabase sQLiteDatabase;
            try {
                sQLiteDatabase = getWritableDatabase();
            } catch (SQLiteException e) {
                e = e;
                sQLiteDatabase = null;
            }
            try {
                LogUtil.a("UdsDeviceDataBaseHelper", "getDatabase and open database.");
                return sQLiteDatabase;
            } catch (SQLiteException e2) {
                e = e2;
                ReleaseLogUtil.c("R_UdsDeviceDataBaseHelper", "getDatabase Exception: ", ExceptionUtils.d(e));
                return sQLiteDatabase;
            }
        }

        @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            LogUtil.b("UdsDeviceDataBaseHelper", "create Storage Data Table");
            try {
                sQLiteDatabase.execSQL("create table if not exists UdsDeviceTable (id integer primary key autoincrement, identity text, deviceInfo text, deviceStatus text, deviceCapability text)");
            } catch (SQLiteException e) {
                LogUtil.b("UdsDeviceDataBaseHelper", "createStorageDataTable SQLiteException: ", ExceptionUtils.d(e));
            }
        }
    }
}
