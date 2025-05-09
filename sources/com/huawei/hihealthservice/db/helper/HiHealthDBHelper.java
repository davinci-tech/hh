package com.huawei.hihealthservice.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.type.HiDeviceType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.OperationKey;
import com.huawei.utils.FoundationCommonUtil;
import com.huawei.watchface.mvp.ui.activity.ScanImageActivity;
import defpackage.igj;
import defpackage.igu;
import defpackage.igv;
import defpackage.igw;
import defpackage.igx;
import defpackage.igy;
import defpackage.igz;
import defpackage.iha;
import defpackage.ihb;
import defpackage.ihc;
import defpackage.ihd;
import defpackage.ihe;
import defpackage.ihf;
import defpackage.ihg;
import defpackage.ihi;
import defpackage.ihj;
import defpackage.ihk;
import defpackage.ihl;
import defpackage.ihm;
import defpackage.ihn;
import defpackage.iho;
import defpackage.ihp;
import defpackage.ihq;
import defpackage.ihr;
import defpackage.ihs;
import defpackage.iht;
import defpackage.ihu;
import defpackage.ihv;
import defpackage.ihw;
import defpackage.ihx;
import defpackage.ihy;
import defpackage.ihz;
import defpackage.iia;
import defpackage.iib;
import defpackage.iie;
import defpackage.iif;
import defpackage.iig;
import defpackage.iik;
import defpackage.iuz;
import defpackage.ivz;
import defpackage.iwe;
import defpackage.jev;
import health.compact.a.CommonUtil;
import health.compact.a.FileSensitiveProtectUtil;
import health.compact.a.KeyManager;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.DefaultDatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteConnection;
import net.zetetic.database.sqlcipher.SQLiteCursor;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;
import net.zetetic.database.sqlcipher.SQLiteOpenHelper;

/* loaded from: classes4.dex */
public class HiHealthDBHelper extends SQLiteOpenHelper {
    private static volatile String e;
    private volatile SQLiteDatabase i;
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, HiHealthDBHelper> f4149a = new ConcurrentHashMap(5);
    private static final Map<String, Object> c = new ConcurrentHashMap(5);
    private static volatile int d = 0;

    static {
        SQLiteCursor.setCursorWindowSize(ScanImageActivity.SCAN_IMAGE_LENGTH_LIMIT);
    }

    private HiHealthDBHelper(Context context, String str, SQLiteDatabaseHook sQLiteDatabaseHook) {
        super(context, str, e, (SQLiteDatabase.CursorFactory) null, 16, 0, new e(), sQLiteDatabaseHook, SystemInfo.a());
    }

    public static HiHealthDBHelper a() {
        return b("hihealth_003.db");
    }

    public static HiHealthDBHelper e(String str) {
        if ("hihealth_003_export.db".equals(str) || "hihealth_sensitive_export.db".equals(str)) {
            if (TextUtils.isEmpty(str) || !CommonUtil.av()) {
                str = "hihealth_003_export.db";
            }
            return b(str);
        }
        if (TextUtils.isEmpty(str) || !CommonUtil.av()) {
            str = "hihealth_003.db";
        }
        return b(str);
    }

    private static void a(String str) {
        e = str;
    }

    public void c() {
        if (SystemInfo.a()) {
            ReleaseLogUtil.e("HiH_HiHealthDBHelper", "start to execWalCheckPoint");
            try {
                for (Map.Entry<String, HiHealthDBHelper> entry : f4149a.entrySet()) {
                    if (entry.getValue() == null || entry.getValue().getWritableDatabase() == null) {
                        ReleaseLogUtil.e("HiH_HiHealthDBHelper", "fail to execWalCheckPoint, dbName:", entry.getKey());
                    } else {
                        entry.getValue().getWritableDatabase().rawExecSQL("PRAGMA wal_checkpoint(FULL);", new Object[0]);
                        ReleaseLogUtil.e("HiH_HiHealthDBHelper", "Success to execWalCheckPoint, dbName:", entry.getKey());
                    }
                }
                ReleaseLogUtil.e("HiH_HiHealthDBHelper", "Success to execWalCheckPoint");
            } catch (Exception e2) {
                ReleaseLogUtil.c("HiH_HiHealthDBHelper", "failed to execWalCheckPoint ", ExceptionUtils.d(e2));
            }
        }
    }

    private static HiHealthDBHelper b(final String str) {
        String str2;
        Map<String, HiHealthDBHelper> map = f4149a;
        HiHealthDBHelper hiHealthDBHelper = map.get(str);
        if (hiHealthDBHelper != null) {
            return hiHealthDBHelper;
        }
        synchronized (d(str)) {
            HiHealthDBHelper hiHealthDBHelper2 = map.get(str);
            if (hiHealthDBHelper2 != null) {
                c.remove(str);
                return hiHealthDBHelper2;
            }
            final Context e2 = BaseApplication.e();
            jev.e(e2);
            SQLiteDatabaseHook sQLiteDatabaseHook = new SQLiteDatabaseHook() { // from class: com.huawei.hihealthservice.db.helper.HiHealthDBHelper.2
                @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
                public void preKey(SQLiteConnection sQLiteConnection) {
                }

                @Override // net.zetetic.database.sqlcipher.SQLiteDatabaseHook
                public void postKey(SQLiteConnection sQLiteConnection) {
                    if (iwe.d(e2, "ExecCipherMigrateKey" + str, 0, false)) {
                        return;
                    }
                    try {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        sQLiteConnection.executeRaw("PRAGMA cipher_migrate;", null, null);
                        ReleaseLogUtil.e("HiH_HiHealthDBHelper", "DB:", str, " postKey migrateTime:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                        iwe.b(e2, "ExecCipherMigrateKey" + str, true, 0);
                    } catch (SQLiteException e3) {
                        ReleaseLogUtil.c("HiH_HiHealthDBHelper", "hook sqliteexception e=", e3.getMessage());
                        String message = e3.getMessage();
                        if (TextUtils.isEmpty(message) || !message.contains("file is not a database")) {
                            return;
                        }
                        ReleaseLogUtil.c("HiH_HiHealthDBHelper", "postKey file is not a database, need to clean clone data");
                        KeyManager.a(false);
                    } catch (Exception e4) {
                        ReleaseLogUtil.c("HiH_HiHealthDBHelper", "hook Exception", LogAnonymous.b((Throwable) e4));
                    }
                }
            };
            if ("hihealth_sensitive.db".equals(str)) {
                str2 = FileSensitiveProtectUtil.e(str);
                FileSensitiveProtectUtil.c();
            } else {
                str2 = str;
            }
            d();
            HiHealthDBHelper hiHealthDBHelper3 = new HiHealthDBHelper(e2, str2, sQLiteDatabaseHook);
            if (!iwe.d(e2, "CopySensitiveDataKey", 0, false)) {
                ReleaseLogUtil.e("Debug_HiHealthDBHelper", "start to copySensitiveData");
                hiHealthDBHelper3.d(hiHealthDBHelper3.getWritableDatabase());
                iwe.b(e2, "CopySensitiveDataKey", true, 0);
            }
            map.put(str, hiHealthDBHelper3);
            c.remove(str);
            return hiHealthDBHelper3;
        }
    }

    private static void d() {
        if (TextUtils.isEmpty(e)) {
            synchronized (b) {
                if (TextUtils.isEmpty(e)) {
                    byte[] a2 = KeyManager.a(13);
                    if (a2 != null) {
                        LogUtil.c("Debug_HiHealthDBHelper", "initDbKey keyByte != null");
                        a(new String(a2, StandardCharsets.UTF_8));
                    } else {
                        LogUtil.c("Debug_HiHealthDBHelper", "initDbKey keyByte = null");
                        a("");
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
        synchronized (b) {
            obj = map.get(str);
            if (obj == null) {
                obj = new Object();
                map.put(str, obj);
            }
        }
        return obj;
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        String databaseName = getDatabaseName();
        ReleaseLogUtil.e("HiH_HiHealthDBHelper", "onCreate VERSION = ", 16, ", NAME = ", databaseName);
        if ("hihealth_003.db".equals(databaseName)) {
            a(sQLiteDatabase);
        } else if (FileSensitiveProtectUtil.e("hihealth_sensitive.db").equals(databaseName)) {
            sQLiteDatabase.execSQL(ihi.e());
            sQLiteDatabase.execSQL(ihi.d());
            sQLiteDatabase.execSQL(ihi.a());
            sQLiteDatabase.execSQL(ihi.j());
            sQLiteDatabase.execSQL(ihw.e());
            sQLiteDatabase.execSQL(ihw.d());
        } else {
            LogUtil.h("Debug_HiHealthDBHelper", "onCreate is other condition");
        }
        LogUtil.a("HiH_HiHealthDBHelper", "onCreate end");
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(igv.d());
        sQLiteDatabase.execSQL(iia.e());
        sQLiteDatabase.execSQL(iia.c());
        sQLiteDatabase.execSQL(igu.a());
        sQLiteDatabase.execSQL(igu.e());
        sQLiteDatabase.execSQL(ihz.a());
        sQLiteDatabase.execSQL(ihz.e());
        sQLiteDatabase.execSQL(iig.b());
        sQLiteDatabase.execSQL(igy.e());
        sQLiteDatabase.execSQL(igy.c());
        sQLiteDatabase.execSQL(ihc.b());
        sQLiteDatabase.execSQL(ihc.d());
        sQLiteDatabase.execSQL(ihb.b());
        sQLiteDatabase.execSQL(iha.e());
        sQLiteDatabase.execSQL(ihk.a());
        sQLiteDatabase.execSQL(ihk.c());
        sQLiteDatabase.execSQL(ihk.e());
        sQLiteDatabase.execSQL(ihk.g());
        sQLiteDatabase.execSQL(ihm.e());
        sQLiteDatabase.execSQL(ihm.d());
        sQLiteDatabase.execSQL(ihm.a());
        sQLiteDatabase.execSQL(ihm.j());
        sQLiteDatabase.execSQL(ihn.d());
        sQLiteDatabase.execSQL(ihn.c());
        sQLiteDatabase.execSQL(ihr.b());
        sQLiteDatabase.execSQL(ihr.e());
        sQLiteDatabase.execSQL(ihp.a());
        sQLiteDatabase.execSQL(ihp.b());
        sQLiteDatabase.execSQL(ihq.d());
        sQLiteDatabase.execSQL(ihq.c());
        sQLiteDatabase.execSQL(ihq.e());
        sQLiteDatabase.execSQL(iib.d(false));
        sQLiteDatabase.execSQL(iib.c());
        sQLiteDatabase.execSQL(ihy.b());
        sQLiteDatabase.execSQL(ihy.a());
        sQLiteDatabase.execSQL(ihe.a());
        sQLiteDatabase.execSQL(iht.e());
        sQLiteDatabase.execSQL(iht.d());
        sQLiteDatabase.execSQL(iht.b());
        sQLiteDatabase.execSQL(ihx.c());
        sQLiteDatabase.execSQL(ihx.a());
        sQLiteDatabase.execSQL(iho.d());
        sQLiteDatabase.execSQL(iie.d());
        sQLiteDatabase.execSQL(igx.b());
        sQLiteDatabase.execSQL(igx.e());
        sQLiteDatabase.execSQL(ihg.e());
        sQLiteDatabase.execSQL(iif.b());
        sQLiteDatabase.execSQL(ihf.a());
        sQLiteDatabase.execSQL(ihu.b());
        sQLiteDatabase.execSQL(ihu.c());
        sQLiteDatabase.execSQL(ihv.b());
        sQLiteDatabase.execSQL(ihs.a());
        sQLiteDatabase.execSQL(ihl.c());
        sQLiteDatabase.execSQL(ihl.b());
        sQLiteDatabase.execSQL(igw.a());
        sQLiteDatabase.execSQL(igw.b());
        sQLiteDatabase.execSQL(igz.c());
        sQLiteDatabase.execSQL(igz.a());
        sQLiteDatabase.execSQL(ihd.d());
        sQLiteDatabase.execSQL(ihj.c());
        sQLiteDatabase.execSQL(ihj.d());
        e(sQLiteDatabase);
        c(sQLiteDatabase);
        b();
        iuz.c();
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtil.a("HiH_HiHealthDBHelper", "onUpgrade oldVersion = ", Integer.valueOf(i), ",newVersion =  ", Integer.valueOf(i2));
        int i3 = i;
        while (i3 < i2) {
            if (i3 >= 8) {
                d(sQLiteDatabase);
            }
            switch (i3) {
                case 1:
                    k(sQLiteDatabase);
                    break;
                case 2:
                    t(sQLiteDatabase);
                    break;
                case 3:
                    p(sQLiteDatabase);
                    break;
                case 4:
                    f(sQLiteDatabase);
                    break;
                case 5:
                    j(sQLiteDatabase);
                    break;
                case 6:
                    o(sQLiteDatabase);
                    break;
                case 7:
                    n(sQLiteDatabase);
                    break;
                case 8:
                    b(sQLiteDatabase);
                    break;
                case 9:
                    l(sQLiteDatabase);
                    break;
                case 10:
                    m(sQLiteDatabase);
                    break;
                case 11:
                    i(sQLiteDatabase);
                    break;
                case 12:
                    s(sQLiteDatabase);
                    break;
                case 13:
                    q(sQLiteDatabase);
                    break;
                case 14:
                    h(sQLiteDatabase);
                    break;
                case 15:
                    g(sQLiteDatabase);
                    break;
            }
            i3++;
        }
        LogUtil.a("HiH_HiHealthDBHelper", "onUpgrade end oldVersion = ", Integer.valueOf(i), ",newVersion =  ", Integer.valueOf(i2), ",upgradeTo = ", Integer.valueOf(i3));
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_unique_code", "-1");
        contentValues.put("deviceName", HiDeviceType.e(1));
        contentValues.put(DeviceCategoryFragment.DEVICE_TYPE, (Integer) 1);
        contentValues.put("firmwareVersion", Build.VERSION.RELEASE);
        contentValues.put("manufacturer", Build.PRODUCT);
        contentValues.put("model", Build.MODEL);
        contentValues.put("createTime", Long.valueOf(System.currentTimeMillis()));
        if (sQLiteDatabase.insert("hihealth_device", (String) null, contentValues) <= 0) {
            LogUtil.h("Debug_HiHealthDBHelper", "initManualInputDeviceInfo insert fail");
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        String androidId = FoundationCommonUtil.getAndroidId(BaseApplication.e());
        if (TextUtils.isEmpty(androidId)) {
            LogUtil.h("Debug_HiHealthDBHelper", "initPhoneDeviceInfo insert fail: Unauthorized to get android ID");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_unique_code", androidId);
        contentValues.put("deviceName", Build.MANUFACTURER);
        contentValues.put(DeviceCategoryFragment.DEVICE_TYPE, (Integer) 32);
        contentValues.put("firmwareVersion", Build.VERSION.RELEASE);
        contentValues.put("hardwareVersion", Build.VERSION.RELEASE);
        contentValues.put("softwareVersion", Build.VERSION.RELEASE);
        contentValues.put("manufacturer", Build.PRODUCT);
        contentValues.put("model", iik.a());
        contentValues.put(PluginPayAdapter.KEY_DEVICE_INFO_SN, Build.SERIAL);
        contentValues.put("device_mac", Build.USER);
        contentValues.put("createTime", Long.valueOf(System.currentTimeMillis()));
        LogUtil.c("Debug_HiHealthDBHelper", "initPhoneDeviceInfo values = ", contentValues);
        if (sQLiteDatabase.insert("hihealth_device", (String) null, contentValues) <= 0) {
            LogUtil.h("Debug_HiHealthDBHelper", "initPhoneDeviceInfo insert fail");
        }
    }

    private void b() {
        LogUtil.a("Debug_HiHealthDBHelper", "enter initUserAccount");
        Context e2 = BaseApplication.e();
        final LoginInit loginInit = LoginInit.getInstance(e2);
        if (!loginInit.getIsLogined() || Utils.l()) {
            ReleaseLogUtil.e("HiH_HiHealthDBHelper", "initUserData not login");
            return;
        }
        final String accountInfo = loginInit.getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.d("HiH_HiHealthDBHelper", "initUserData userId is empty");
            return;
        }
        HiAccountInfo hiAccountInfo = new HiAccountInfo();
        String accountInfo2 = loginInit.getAccountInfo(1015);
        hiAccountInfo.setAccessToken(accountInfo2);
        hiAccountInfo.setHuid(accountInfo);
        hiAccountInfo.setServiceToken(accountInfo2);
        hiAccountInfo.setSiteId(CommonUtil.m(e2, loginInit.getAccountInfo(1009)));
        HiHealthNativeApi.a(e2).hiLogin(hiAccountInfo, new HiCommonListener() { // from class: com.huawei.hihealthservice.db.helper.HiHealthDBHelper.5
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("HiH_HiHealthDBHelper", "hiLogin onSuccess.");
                HiHealthDBHelper.b(accountInfo, loginInit);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("HiH_HiHealthDBHelper", "hiLogin failed errCode:", Integer.valueOf(i), "errorMsg:", obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, LoginInit loginInit) {
        HiUserInfo hiUserInfo = new HiUserInfo();
        hiUserInfo.setUser(1073741824);
        hiUserInfo.setHuid(str);
        hiUserInfo.setBirthday(CommonUtil.h(loginInit.getAccountInfo(1006)));
        int h = CommonUtil.h(loginInit.getAccountInfo(1005));
        if (h == 0) {
            hiUserInfo.setGender(1);
        } else if (h == 1) {
            hiUserInfo.setGender(0);
        } else {
            hiUserInfo.setGender(h);
        }
        hiUserInfo.setHeadImgUrl(loginInit.getAccountInfo(1003));
        String accountInfo = loginInit.getAccountInfo(1002);
        if (TextUtils.isEmpty(accountInfo)) {
            accountInfo = loginInit.getAccountInfo(1001);
        }
        hiUserInfo.setName(accountInfo);
        hiUserInfo.setModifiedIntent(268435456);
        HiHealthNativeApi.a(BaseApplication.e()).setUserData(hiUserInfo, new HiCommonListener() { // from class: com.huawei.hihealthservice.db.helper.HiHealthDBHelper.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                ReleaseLogUtil.e("HiH_HiHealthDBHelper", "initUserInfo success");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                ReleaseLogUtil.d("HiH_HiHealthDBHelper", "initUserInfo fail:", Integer.valueOf(i), ",msg:", obj.toString());
            }
        });
    }

    @Override // net.zetetic.database.sqlcipher.SQLiteOpenHelper, androidx.sqlite.db.SupportSQLiteOpenHelper
    public SQLiteDatabase getWritableDatabase() {
        if (this.i != null) {
            return this.i;
        }
        d();
        if (TextUtils.isEmpty(e)) {
            ReleaseLogUtil.d("HiH_HiHealthDBHelper", "dbKey is also null return");
            return null;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        synchronized (this) {
            if (this.i != null) {
                return this.i;
            }
            boolean z = true;
            try {
                this.i = super.getWritableDatabase();
            } catch (SQLiteException e2) {
                ReleaseLogUtil.c("HiH_HiHealthDBHelper", "getWritableDatabase e=", ExceptionUtils.d(e2));
                if (!TextUtils.isEmpty(e)) {
                    this.i = e();
                }
            } catch (Exception e3) {
                ReleaseLogUtil.c("HiH_HiHealthDBHelper", "getWritableDatabase Exception2=", ExceptionUtils.d(e3));
            }
            Object[] objArr = new Object[6];
            objArr[0] = "db:";
            objArr[1] = getDatabaseName();
            objArr[2] = " WAL:";
            if (this.i == null || !this.i.isWriteAheadLoggingEnabled()) {
                z = false;
            }
            objArr[3] = Boolean.valueOf(z);
            objArr[4] = " costTime:";
            objArr[5] = Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime);
            ReleaseLogUtil.e("Debug_HiHealthDBHelper", objArr);
            return this.i;
        }
    }

    private SQLiteDatabase e() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(5);
        SQLiteDatabase sQLiteDatabase = null;
        try {
            LogUtil.c("Debug_HiHealthDBHelper", "try open database again!");
            sQLiteDatabase = super.getWritableDatabase();
            linkedHashMap.put("errorCode", Integer.toString(0));
            ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_DATABASE_UNAVAILABLE_85070025.value(), linkedHashMap, false);
            return sQLiteDatabase;
        } catch (SQLiteException e2) {
            ReleaseLogUtil.c("HiH_HiHealthDBHelper", "getWritableDatabase sqliteexcepiont e2=", e2.getMessage());
            String message = e2.getMessage();
            if (TextUtils.isEmpty(message)) {
                return sQLiteDatabase;
            }
            if (message.contains("SQL logic error: , while compiling: select count(*) from sqlite_master")) {
                d++;
            }
            if (!message.contains("file is not a database") && d < 10) {
                return sQLiteDatabase;
            }
            LogUtil.b("Debug_HiHealthDBHelper", "getWritableDatabaseSync need to clean clone data");
            d = 0;
            linkedHashMap.put("errorCode", Integer.toString(message.contains("file is not a database") ? -1 : -2));
            ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_DATABASE_UNAVAILABLE_85070025.value(), linkedHashMap, false);
            KeyManager.a(false);
            return sQLiteDatabase;
        } catch (Exception e3) {
            ReleaseLogUtil.c("HiH_HiHealthDBHelper", "getWritableDatabase Exception1=", ExceptionUtils.d(e3));
            return sQLiteDatabase;
        }
    }

    private void k(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionOne create table sample_session_core and user_preference");
        sQLiteDatabase.execSQL(ihp.a());
        sQLiteDatabase.execSQL(ihp.b());
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS user_preference");
        sQLiteDatabase.execSQL(ihx.c());
        sQLiteDatabase.execSQL(ihx.a());
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionOne end");
    }

    private void t(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionTwo create hihealth_authorization and hihealth_permission and point_health_stress");
        sQLiteDatabase.execSQL(ihl.c());
        sQLiteDatabase.execSQL(ihl.b());
        sQLiteDatabase.execSQL(igx.b());
        sQLiteDatabase.execSQL(igx.e());
        sQLiteDatabase.execSQL(ihg.e());
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionTwo end");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x011b A[LOOP:1: B:17:0x0115->B:19:0x011b, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void p(net.zetetic.database.sqlcipher.SQLiteDatabase r27) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.db.helper.HiHealthDBHelper.p(net.zetetic.database.sqlcipher.SQLiteDatabase):void");
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionFour create config model data tables");
        sQLiteDatabase.execSQL(igw.a());
        sQLiteDatabase.execSQL(igw.b());
        sQLiteDatabase.execSQL(igz.c());
        sQLiteDatabase.execSQL(igz.a());
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionFour end");
    }

    private void j(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionFive create kit data tables");
        sQLiteDatabase.execSQL(ihz.a());
        sQLiteDatabase.execSQL(ihz.e());
        sQLiteDatabase.execSQL(ihd.d());
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionFive end");
    }

    private void o(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(iig.b());
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionSix end");
    }

    private void n(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionSeven start：copy sensitive data to SECE database");
        d(sQLiteDatabase);
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionSeven end");
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionEight start");
        if ("hihealth_003.db".equals(getDatabaseName())) {
            sQLiteDatabase.execSQL("ALTER TABLE sample_sequence ADD simple_data TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE sample_sequence ADD sub_type_id INTEGER");
            sQLiteDatabase.execSQL("ALTER TABLE sample_sequence ADD reserve TEXT");
            igj.e(sQLiteDatabase);
            LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionEight alter table end");
        }
    }

    private void l(SQLiteDatabase sQLiteDatabase) {
        if ("hihealth_003.db".equals(getDatabaseName())) {
            sQLiteDatabase.execSQL(iib.d(true));
            sQLiteDatabase.execSQL(iib.c());
            sQLiteDatabase.execSQL(ihy.b());
            sQLiteDatabase.execSQL(ihy.a());
            LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionNine end");
        }
    }

    private void m(SQLiteDatabase sQLiteDatabase) {
        if ("hihealth_003.db".equals(getDatabaseName())) {
            sQLiteDatabase.execSQL("ALTER TABLE business_data ADD data_source TEXT");
            LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionTen end");
        }
    }

    private void i(SQLiteDatabase sQLiteDatabase) {
        if ("hihealth_003.db".equals(getDatabaseName())) {
            sQLiteDatabase.execSQL(ihj.c());
            sQLiteDatabase.execSQL(ihj.d());
            LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionEleven end");
        }
    }

    private void s(SQLiteDatabase sQLiteDatabase) {
        if ("hihealth_003.db".equals(getDatabaseName())) {
            sQLiteDatabase.execSQL("ALTER TABLE hihealth_device ADD prod_id TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE hihealth_device ADD device_udid TEXT");
            LogUtil.a("Debug_HiHealthDBHelper", "upgradeVersionTwelve end");
        }
    }

    private void q(SQLiteDatabase sQLiteDatabase) {
        if ("hihealth_003.db".equals(getDatabaseName())) {
            sQLiteDatabase.execSQL("ALTER TABLE hihealth_device ADD sub_prod_id TEXT");
            LogUtil.a("HiH_HiHealthDBHelper", "upgradeVersionThirteen end");
        }
    }

    private void h(SQLiteDatabase sQLiteDatabase) {
        if (!"hihealth_003.db".equals(getDatabaseName())) {
            sQLiteDatabase.execSQL(ihm.c());
            sQLiteDatabase.execSQL(ihm.d());
            sQLiteDatabase.execSQL(ihm.a());
        } else {
            sQLiteDatabase.execSQL(ihm.c());
            sQLiteDatabase.execSQL(ihm.d());
            sQLiteDatabase.execSQL(ihk.b());
            sQLiteDatabase.execSQL(ihk.c());
            sQLiteDatabase.execSQL(ihm.a());
            sQLiteDatabase.execSQL(ihk.e());
            sQLiteDatabase.execSQL(iht.b());
            sQLiteDatabase.execSQL(ihq.b());
            sQLiteDatabase.execSQL(ihq.e());
        }
        LogUtil.a("HiH_HiHealthDBHelper", "upgradeVersionFourteen end");
    }

    private void g(SQLiteDatabase sQLiteDatabase) {
        if (!"hihealth_003.db".equals(getDatabaseName())) {
            sQLiteDatabase.execSQL(ihm.a());
            sQLiteDatabase.execSQL(ihm.j());
        } else {
            sQLiteDatabase.execSQL(ihm.j());
            sQLiteDatabase.execSQL(ihk.g());
            sQLiteDatabase.execSQL(ihq.c());
        }
        LogUtil.a("HiH_HiHealthDBHelper", "upgradeVersionFifteen end");
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        if (CommonUtil.av() && "hihealth_003.db".equals(getDatabaseName())) {
            SQLiteDatabase writableDatabase = e("hihealth_sensitive.db").getWritableDatabase();
            if (writableDatabase == null) {
                LogUtil.h("Debug_HiHealthDBHelper", "Create new dataBase failed");
                return;
            }
            igj.c(sQLiteDatabase, writableDatabase);
            igj.b(sQLiteDatabase, writableDatabase);
            LogUtil.a("Debug_HiHealthDBHelper", "copy sensitive data to SECE database end");
        }
    }

    static class e implements DatabaseErrorHandler {
        private final DatabaseErrorHandler d;

        private e() {
            this.d = new DefaultDatabaseErrorHandler();
        }

        @Override // net.zetetic.database.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            this.d.onCorruption(sQLiteDatabase);
            ReleaseLogUtil.d("HiH_HiHealthDBHelper", "db file corruption:", sQLiteDatabase.getPath());
        }
    }
}
