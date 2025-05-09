package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import health.compact.a.CommonUtil;
import health.compact.a.DataBaseHelper;
import health.compact.a.DbManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class cen extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private static cen f663a;
    private static final Object b = new Object();
    private static final Object c = new Object();
    private static final Map<String, Integer> d = new HashMap(10);

    public static class d {
        private static final String[] e = {"productId", "deviceId", "deviceCode", "sn", "model", "deviceType", ProfileRequestConstants.MANU, "prodId", ProfileRequestConstants.HIV, ProfileRequestConstants.FWV, ProfileRequestConstants.HWV, ProfileRequestConstants.SWV, "mac", ProfileRequestConstants.PROT_TYPE, "serviceInfo", "source"};

        public static final String[] e() {
            return (String[]) e.clone();
        }
    }

    public static class a {
        private static final String[] c = {"productId", "deviceId", "deviceModel", "deviceTypeId"};

        public static String[] a() {
            return (String[]) c.clone();
        }
    }

    public static class c {
        private static final String[] e = {"deviceId", "subUser"};

        public static String[] c() {
            return (String[]) e.clone();
        }
    }

    private cen(Context context) {
        super(context, "device.db", (SQLiteDatabase.CursorFactory) null, 4);
    }

    public static cen b() {
        synchronized (b) {
            if (f663a == null) {
                LogUtil.h("DeviceDataBaseHelper", "Enter getInstance");
                f663a = new cen(cpp.a());
            }
        }
        return f663a;
    }

    public Cursor DT_(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceDataBaseHelper", "query table is null");
            return null;
        }
        SQLiteDatabase readableDatabase = getReadableDatabase();
        LogUtil.c("DeviceDataBaseHelper", "raw query data from device.db ", str);
        return readableDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    private String d(String str) {
        return ("WiFiBindDevice".equals(str) || "WiFiDeviceSubUser".equals(str)) ? "10033" : "WiFiDeviceList".equals(str) ? "10034" : "";
    }

    public long DQ_(String str, ContentValues contentValues) {
        long j;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            LogUtil.h("DeviceDataBaseHelper", "do not writedate base on main thread");
        }
        long j2 = -1;
        if (contentValues == null) {
            LogUtil.h("DeviceDataBaseHelper", "cant not insert empty value");
            return -1L;
        }
        LogUtil.a("DeviceDataBaseHelper", "insert to table ", str);
        LogUtil.c("DeviceDataBaseHelper", "insert values", contentValues.toString());
        synchronized (c) {
            try {
                j = getWritableDatabase().insertOrThrow(str, null, contentValues);
                try {
                    LogUtil.a("DeviceDataBaseHelper", "after insert to rowId ", Long.valueOf(j));
                    if (j != -1) {
                        DF_(str, contentValues);
                    }
                } catch (SQLException unused) {
                    j2 = j;
                    LogUtil.b("DeviceDataBaseHelper", "getRowId SQLException!");
                    j = j2;
                    return j;
                }
            } catch (SQLException unused2) {
            }
        }
        return j;
    }

    private void DF_(String str, ContentValues contentValues) {
        String asString;
        if (!"device".equals(str) || contentValues == null || (asString = contentValues.getAsString("sn")) == null || !asString.endsWith("#ANDROID21")) {
            return;
        }
        String asString2 = contentValues.getAsString("name");
        ReleaseLogUtil.d("R_DeviceDataBaseHelper", "checkAndroid21Device deviceInfo:", "Name:" + asString2 + " Sn:" + cpw.d(asString.substring(0, asString.length() - 10)));
        if (CommonUtil.ag(BaseApplication.getContext())) {
            return;
        }
        String d2 = DfxUtils.d(Thread.currentThread(), null);
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("checkAndroid21Device", "deviceInfo:" + asString2 + " traceInfo:" + d2);
    }

    public long DU_(String str, ContentValues contentValues, String str2, String[] strArr) {
        long j = -1;
        if (TextUtils.isEmpty(str) || contentValues == null) {
            LogUtil.h("DeviceDataBaseHelper", "update data illegal argments");
            return -1L;
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            LogUtil.h("DeviceDataBaseHelper", "do not write datebase on main thread");
        }
        LogUtil.a("DeviceDataBaseHelper", "update data in table ", str);
        LogUtil.c("DeviceDataBaseHelper", "update data, ", contentValues.toString());
        synchronized (c) {
            try {
                j = getWritableDatabase().update(str, contentValues, str2, strArr);
            } catch (SQLException unused) {
                LogUtil.b("DeviceDataBaseHelper", "getUpdateRowId SQLException!");
            }
        }
        return j;
    }

    public int e(String str, String str2, String[] strArr) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            LogUtil.h("DeviceDataBaseHelper", "do not write datebase on main thread");
        }
        int i = -1;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceDataBaseHelper", "delete fail, table name is null");
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("DeviceDataBaseHelper", "delete all data in table ，are you serious ?");
        }
        LogUtil.c("DeviceDataBaseHelper", "delete data in table ", str, " whereClause ", str2);
        synchronized (c) {
            try {
                i = getWritableDatabase().delete(str, str2, strArr);
            } catch (SQLException unused) {
                LogUtil.b("DeviceDataBaseHelper", "getUpdateRowId SQLException!");
            }
        }
        return i;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        super.onDowngrade(sQLiteDatabase, i, i2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        LogUtil.h("DeviceDataBaseHelper", "on Open currentVersion,", Integer.valueOf(sQLiteDatabase.getVersion()));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtil.h("DeviceDataBaseHelper", "onUpgrade oldVersion:", Integer.valueOf(i), " newVersion:", Integer.valueOf(i2));
        if (i == 0 || i == 1 || i == 2) {
            DD_(sQLiteDatabase);
            DN_(sQLiteDatabase);
        } else if (i != 3) {
            return;
        }
        DE_(sQLiteDatabase);
    }

    private void DD_(SQLiteDatabase sQLiteDatabase) {
        synchronized (c) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE device ADD COLUMN mDeviceId varchar(64) ");
                LogUtil.a("DeviceDataBaseHelper", "add column success");
            } catch (SQLException unused) {
                LogUtil.b("DeviceDataBaseHelper", "exception throw when add column");
            }
        }
    }

    private void DE_(SQLiteDatabase sQLiteDatabase) {
        synchronized (c) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE device ADD COLUMN showInList INTEGER DEFAULT 1");
                sQLiteDatabase.execSQL("ALTER TABLE device ADD COLUMN extra TEXT");
                LogUtil.a("DeviceDataBaseHelper", "addFittingColumn success");
            } catch (SQLException unused) {
                LogUtil.b("DeviceDataBaseHelper", "exception throw when addFittingColumn");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: DC_, reason: merged with bridge method [inline-methods] */
    public void DS_(SQLiteDatabase sQLiteDatabase) {
        Map<String, String> DI_ = DI_(sQLiteDatabase);
        if (DI_.size() != 0) {
            synchronized (c) {
                try {
                    try {
                        sQLiteDatabase.beginTransactionWithListenerNonExclusive(new SQLiteTransactionListener() { // from class: cen.2
                            @Override // android.database.sqlite.SQLiteTransactionListener
                            public void onBegin() {
                                LogUtil.a("DeviceDataBaseHelper", "insert mDeviceId value begin");
                            }

                            @Override // android.database.sqlite.SQLiteTransactionListener
                            public void onCommit() {
                                LogUtil.a("DeviceDataBaseHelper", "insert mDeviceId value success");
                            }

                            @Override // android.database.sqlite.SQLiteTransactionListener
                            public void onRollback() {
                                LogUtil.a("DeviceDataBaseHelper", "insert mDeviceId fail");
                            }
                        });
                        ContentValues contentValues = new ContentValues();
                        int i = 0;
                        for (Map.Entry<String, String> entry : DI_.entrySet()) {
                            contentValues.put("mDeviceId", entry.getValue());
                            if (sQLiteDatabase.update("device", contentValues, "uniqueId=?", new String[]{entry.getKey()}) > 0) {
                                i++;
                            }
                        }
                        LogUtil.a("DeviceDataBaseHelper", "update old version database,update table ", "device", "by adding wiseDeviceId column,and insert value ", Integer.valueOf(i), " successes");
                        sQLiteDatabase.setTransactionSuccessful();
                    } catch (SQLException unused) {
                        LogUtil.h("DeviceDataBaseHelper", "exception throw when add values to old version database");
                    }
                } finally {
                    sQLiteDatabase.endTransaction();
                }
            }
            return;
        }
        LogUtil.a("DeviceDataBaseHelper", "no data needs to add value");
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x007a, code lost:
    
        if (r3 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0095, code lost:
    
        return r0.b(r2, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008e, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008c, code lost:
    
        if (r3 == null) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.Map<java.lang.String, java.lang.String> DI_(android.database.sqlite.SQLiteDatabase r8) {
        /*
            r7 = this;
            cpo r0 = new cpo
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            android.content.Context r3 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            r4 = 2
            defpackage.jbw.d(r3, r4)
            java.util.ArrayList r3 = com.huawei.hihealth.dictionary.constants.ProductMap.d()
            java.util.Iterator r3 = r3.iterator()
        L1f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L43
            java.lang.Object r4 = r3.next()
            com.huawei.hihealth.dictionary.constants.ProductMapInfo r4 = (com.huawei.hihealth.dictionary.constants.ProductMapInfo) r4
            java.lang.String r5 = r4.h()
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L1f
            java.util.Map<java.lang.String, java.lang.Integer> r6 = defpackage.cen.d
            int r4 = r4.c()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r6.put(r5, r4)
            goto L1f
        L43:
            r3 = 0
            java.lang.String r4 = "select uniqueId, productId from device group by uniqueId having addTime=MAX(addTime)"
            android.database.Cursor r3 = r8.rawQuery(r4, r3)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
        L4a:
            boolean r8 = r3.moveToNext()     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            if (r8 == 0) goto L7a
            java.lang.String r8 = "productId"
            int r8 = r3.getColumnIndex(r8)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            java.lang.String r8 = r3.getString(r8)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            boolean r4 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            if (r4 != 0) goto L4a
            java.util.Map<java.lang.String, java.lang.Integer> r4 = defpackage.cen.d     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            boolean r4 = r4.containsKey(r8)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            if (r4 == 0) goto L4a
            java.lang.String r4 = "uniqueId"
            int r4 = r3.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            java.lang.String r4 = r3.getString(r4)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            r1.add(r4)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            r2.add(r8)     // Catch: java.lang.Throwable -> L7d android.database.SQLException -> L7f
            goto L4a
        L7a:
            if (r3 == 0) goto L91
            goto L8e
        L7d:
            r8 = move-exception
            goto L96
        L7f:
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L7d
            java.lang.String r4 = "getCloudWiseDeviceIdByUniqueIds has exception"
            r5 = 0
            r8[r5] = r4     // Catch: java.lang.Throwable -> L7d
            java.lang.String r4 = "DeviceDataBaseHelper"
            com.huawei.hwlogsmodel.LogUtil.b(r4, r8)     // Catch: java.lang.Throwable -> L7d
            if (r3 == 0) goto L91
        L8e:
            r3.close()
        L91:
            java.util.Map r8 = r0.b(r2, r1)
            return r8
        L96:
            if (r3 == 0) goto L9b
            r3.close()
        L9b:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cen.DI_(android.database.sqlite.SQLiteDatabase):java.util.Map");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtil.a("DeviceDataBaseHelper", "onCreate dataBase version ", Integer.valueOf(sQLiteDatabase.getVersion()));
        if (sQLiteDatabase.getVersion() > 0) {
            LogUtil.h("DeviceDataBaseHelper", "data base hase been initialized");
            return;
        }
        DH_(sQLiteDatabase);
        DP_(sQLiteDatabase);
        DL_(sQLiteDatabase);
    }

    private void DH_(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(a("device"));
        sQLiteDatabase.execSQL(a("WiFiBindDevice"));
        sQLiteDatabase.execSQL(a("WiFiDeviceSubUser"));
        sQLiteDatabase.execSQL(a("WiFiDeviceList"));
    }

    private void DP_(SQLiteDatabase sQLiteDatabase) {
        synchronized (c) {
            try {
                try {
                    sQLiteDatabase.beginTransactionWithListenerNonExclusive(new SQLiteTransactionListener() { // from class: cen.4
                        @Override // android.database.sqlite.SQLiteTransactionListener
                        public void onBegin() {
                            LogUtil.a("DeviceDataBaseHelper", "migrate all device data begin");
                        }

                        @Override // android.database.sqlite.SQLiteTransactionListener
                        public void onCommit() {
                            LogUtil.a("DeviceDataBaseHelper", "migrate all device data success");
                        }

                        @Override // android.database.sqlite.SQLiteTransactionListener
                        public void onRollback() {
                            LogUtil.a("DeviceDataBaseHelper", "migrate all device data fail");
                        }
                    });
                    sQLiteDatabase.execSQL(a("device_tmp"));
                    sQLiteDatabase.execSQL("INSERT INTO device_tmp(uniqueId, productId, name, mode, kind, kitUuid, auto, addTime) SELECT uniqueId, productId, name, mode, kind, kitUuid, auto, addTime  FROM device GROUP BY uniqueId HAVING addTime=MAX(addTime)");
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device");
                    sQLiteDatabase.execSQL("ALTER TABLE device_tmp RENAME TO device");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (SQLException unused) {
                    LogUtil.h("DeviceDataBaseHelper", "exception throw when upgrade table device");
                }
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }
    }

    private void DN_(final SQLiteDatabase sQLiteDatabase) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: cep
            @Override // java.lang.Runnable
            public final void run() {
                cen.this.DS_(sQLiteDatabase);
            }
        });
    }

    private void DL_(final SQLiteDatabase sQLiteDatabase) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: cer
            @Override // java.lang.Runnable
            public final void run() {
                cen.this.DR_(sQLiteDatabase);
            }
        });
    }

    /* synthetic */ void DR_(SQLiteDatabase sQLiteDatabase) {
        ArrayList<ContentValues> b2 = b("WiFiBindDevice");
        ArrayList<ContentValues> b3 = b("WiFiDeviceList");
        ArrayList<ContentValues> b4 = b("WiFiDeviceSubUser");
        ArrayList<ContentValues> DJ_ = DJ_(sQLiteDatabase, b2);
        if (koq.b(b2) && koq.b(b3) && koq.b(b4) && koq.b(DJ_)) {
            LogUtil.h("DeviceDataBaseHelper", "no data need to migrate");
            return;
        }
        synchronized (c) {
            try {
                try {
                    sQLiteDatabase.beginTransactionWithListenerNonExclusive(new SQLiteTransactionListener() { // from class: cen.5
                        @Override // android.database.sqlite.SQLiteTransactionListener
                        public void onBegin() {
                            LogUtil.h("DeviceDataBaseHelper", "migrate data begin !");
                        }

                        @Override // android.database.sqlite.SQLiteTransactionListener
                        public void onCommit() {
                            LogUtil.h("DeviceDataBaseHelper", "migrate data suucess !");
                            cen.this.c();
                        }

                        @Override // android.database.sqlite.SQLiteTransactionListener
                        public void onRollback() {
                            LogUtil.h("DeviceDataBaseHelper", "migrate wifi data fail !");
                            cen.this.c();
                        }
                    });
                    DG_(sQLiteDatabase, "WiFiBindDevice", b2);
                    DG_(sQLiteDatabase, "WiFiDeviceSubUser", b4);
                    DG_(sQLiteDatabase, "WiFiDeviceList", b3);
                    DO_(sQLiteDatabase, DJ_);
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (SQLException e) {
                    LogUtil.b("DeviceDataBaseHelper", "migrate data catch exception ", e.getMessage());
                }
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }
    }

    private ArrayList<ContentValues> b(String str) {
        return e(d(str), str) ? c(str) : new ArrayList<>(10);
    }

    private void DO_(SQLiteDatabase sQLiteDatabase, ArrayList<ContentValues> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h("DeviceDataBaseHelper", "migrate data updateDeviceSn no data, tableName", "device");
            return;
        }
        try {
            Iterator<ContentValues> it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                ContentValues next = it.next();
                ContentValues contentValues = new ContentValues();
                contentValues.put("sn", next.getAsString("sn"));
                if (sQLiteDatabase.update("device", contentValues, "uniqueId=?", new String[]{next.getAsString("uniqueId")}) > 0) {
                    i++;
                }
            }
            LogUtil.a("DeviceDataBaseHelper", "migrate data in table ", "device", " data'size ", Integer.valueOf(arrayList.size()), " success ", Integer.valueOf(i));
        } catch (SQLException e) {
            LogUtil.b("DeviceDataBaseHelper", "catch exception while copy data from table:", "device", " error msg ", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        String packageName = BaseApplication.getContext().getPackageName();
        BaseApplication.getContext().deleteDatabase(packageName.replaceAll("\\.", "_") + "10033.db");
        BaseApplication.getContext().deleteDatabase(packageName.replaceAll("\\.", "_") + "10034.db");
    }

    private void DG_(SQLiteDatabase sQLiteDatabase, String str, ArrayList<ContentValues> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h("DeviceDataBaseHelper", "migrate data wifiDeviceData no data, tableName", str);
            return;
        }
        try {
            Iterator<ContentValues> it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (sQLiteDatabase.insert(str, null, it.next()) > 0) {
                    i++;
                }
            }
            LogUtil.a("DeviceDataBaseHelper", "migrate data in table ", str, " data'size ", Integer.valueOf(arrayList.size()), " success ", Integer.valueOf(i));
        } catch (SQLException e) {
            LogUtil.h("DeviceDataBaseHelper", "catch exception while copy data from table:", str, " error msg ", e.getMessage());
        }
    }

    private String a(String str) {
        if ("device".equals(str) || "device_tmp".equals(str)) {
            return "CREATE TABLE IF NOT EXISTS " + str + "(uniqueId VARCHAR(64) UNIQUE NOT NULL,productId VARCHAR(64) NOT NULL,name VARCHAR(64) NOT NULL,mode VARCHAR(16) NOT NULL,kind VARCHAR(16) NOT NULL, kitUuid VARCHAR(64) NOT NULL,auto INTEGER NOT NULL,sn VARCHAR(64),displayName VARCHAR(64),addTime LONG,mDeviceId VARCHAR(64),showInList INTEGER DEFAULT 1,extra TEXT)";
        }
        if ("WiFiBindDevice".equals(str)) {
            return "CREATE TABLE IF NOT EXISTS WiFiBindDevice(_id integer primary key autoincrement, productId text not null, deviceId text not null, deviceCode text not null, sn text not null, model text not null,deviceType text not null,manu text not null,prodId text not null,hiv text not null,fwv text,hwv text,swv text,mac text,protType integer,serviceInfo text,source integer,addTime long)";
        }
        if ("WiFiDeviceList".equals(str)) {
            return "CREATE TABLE IF NOT EXISTS WiFiDeviceList(_id integer PRIMARY KEY AUTOINCREMENT,productId text NOT NULL, deviceId text NOT NULL, deviceModel TEXT,deviceTypeId TEXT)";
        }
        if ("WiFiDeviceSubUser".equals(str)) {
            return "CREATE TABLE IF NOT EXISTS WiFiDeviceSubUser(_id integer PRIMARY KEY AUTOINCREMENT,deviceId text UNIQUE not null,subUser text)";
        }
        LogUtil.h("DeviceDataBaseHelper", "unkow table ", str);
        return null;
    }

    private Cursor DM_(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        String d2 = d(str);
        DataBaseHelper.c(d2).a();
        String c2 = DbManager.c(d2, str);
        if (DataBaseHelper.c(d2).c() != null) {
            return DataBaseHelper.c(d2).c().query(c2, strArr, str2, strArr2, str3, str4, str5, str6);
        }
        return null;
    }

    private ArrayList<ContentValues> DJ_(SQLiteDatabase sQLiteDatabase, ArrayList<ContentValues> arrayList) {
        Cursor query;
        ArrayList<ContentValues> arrayList2 = new ArrayList<>(10);
        String[] strArr = {"productId", "uniqueId", "sn", Wpt.MODE};
        try {
            query = sQLiteDatabase.query("device", strArr, "productId = ? or productId = ? or productId = ? or productId = ? or productId = ? or productId = ?", new String[]{"8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", "e835d102-af95-48a6-ae13-2983bc06f5c0", "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", "e4b0b1d5-2003-4d88-8b5f-c4f64542040b", "a8ba095d-4123-43c4-a30a-0240011c58de", "c943c933-442e-4c34-bcd0-66597f24aaed"}, null, null, null, null);
        } catch (SQLException unused) {
        }
        try {
            try {
            } catch (SQLException unused2) {
                LogUtil.b("DeviceDataBaseHelper", "getDataFromDevice has exception");
                return arrayList2;
            }
            if (query == null) {
                LogUtil.h("DeviceDataBaseHelper", "getDataFromDevice not device data");
                if (query != null) {
                    query.close();
                }
                return arrayList2;
            }
            while (query.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i < 4; i++) {
                    String str = strArr[i];
                    int columnIndex = query.getColumnIndex(str);
                    if (columnIndex != -1) {
                        contentValues.put(str, query.getString(columnIndex));
                    } else {
                        contentValues.put(str, "");
                    }
                }
                try {
                    String DK_ = DK_(contentValues, arrayList);
                    if (!TextUtils.isEmpty(DK_)) {
                        contentValues.put("sn", DK_);
                        arrayList2.add(contentValues);
                    }
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    if (query == null) {
                        throw th2;
                    }
                    try {
                        query.close();
                        throw th2;
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                        throw th2;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
            return arrayList2;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private String DK_(ContentValues contentValues, ArrayList<ContentValues> arrayList) {
        if (contentValues == null) {
            LogUtil.c("DeviceDataBaseHelper", "hasUpdateSn contentValues is null");
            return "";
        }
        if (!TextUtils.isEmpty(contentValues.getAsString("sn"))) {
            LogUtil.a("DeviceDataBaseHelper", "sn is not null");
            return "";
        }
        String b2 = new DeviceCloudSharePreferencesManager(cpp.a()).b(contentValues.getAsString("productId") + "sn");
        if (TextUtils.isEmpty(b2)) {
            LogUtil.c("DeviceDataBaseHelper", "serialNumber is null");
            return "";
        }
        if ("0".equals(b2) && !koq.b(arrayList)) {
            LogUtil.a("DeviceDataBaseHelper", "wifiDevice size:", Integer.valueOf(arrayList.size()));
            LogUtil.a("DeviceDataBaseHelper", "serialNumber is 0");
            Iterator<ContentValues> it = arrayList.iterator();
            while (it.hasNext()) {
                ContentValues next = it.next();
                if (contentValues.getAsString("productId").equals(next.getAsString("productId"))) {
                    return next.getAsString("sn");
                }
            }
        }
        return b2;
    }

    private ArrayList<ContentValues> c(String str) {
        String[] c2;
        Cursor DM_;
        ArrayList<ContentValues> arrayList = new ArrayList<>(10);
        if ("WiFiBindDevice".equals(str)) {
            c2 = d.e();
        } else if ("WiFiDeviceList".equals(str)) {
            c2 = a.a();
        } else if ("WiFiDeviceSubUser".equals(str)) {
            c2 = c.c();
        } else {
            LogUtil.h("DeviceDataBaseHelper", "getAllWifiDeviceData know table, name ", str);
            return arrayList;
        }
        try {
            DM_ = DM_(str, null, null, null, null, null, null, null);
            try {
            } finally {
            }
        } catch (SQLException unused) {
            LogUtil.b("DeviceDataBaseHelper", "getDataFromWifiDataBase has exception");
        }
        if (DM_ == null) {
            LogUtil.h("DeviceDataBaseHelper", "copyWifiDeviceData not wifi device data");
            if (DM_ != null) {
                DM_.close();
            }
            return arrayList;
        }
        while (DM_.moveToNext()) {
            ContentValues contentValues = new ContentValues();
            for (String str2 : c2) {
                int columnIndex = DM_.getColumnIndex(str2);
                if (columnIndex != -1) {
                    contentValues.put(str2, DM_.getString(columnIndex));
                } else {
                    contentValues.put(str2, "");
                }
            }
            arrayList.add(contentValues);
        }
        if (DM_ != null) {
            DM_.close();
        }
        return arrayList;
    }

    private boolean e(String str, String str2) {
        LogUtil.a("DeviceDataBaseHelper", "moduleId:", str, ",tableName:", str2);
        String str3 = BaseApplication.getContext().getPackageName().replaceAll("\\.", "_") + str + ".db";
        String[] databaseList = BaseApplication.getContext().databaseList();
        LogUtil.a("DeviceDataBaseHelper", "dataBaseName:", str3);
        if (databaseList == null) {
            return false;
        }
        for (String str4 : databaseList) {
            if (str3.equals(str4)) {
                List<String> a2 = DbManager.a(String.valueOf(str));
                return !koq.b(a2) && a2.contains(DbManager.c(str, str2));
            }
        }
        return false;
    }
}
