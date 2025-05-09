package com.huawei.hms.framework.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class NetworkKitSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME_DYNAMIC = "networkkit_dynamic.db";
    private static final String DB_NAME_LOCAL = "networkkit.db";
    private static final String DB_NAME_SUFFIX = "-journal";
    public static final int DB_VERSION = 1;
    private static final String TAG = "PLSQLiteOpenHelper";
    private static String dbName = "networkkit.db";
    private static volatile NetworkKitSQLiteOpenHelper instance;
    private ConcurrentHashMap<String, SQLiteDatabase> oldDbMaps;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    static {
        if (ContextHolder.getKitContext() != null) {
            dbName = DB_NAME_DYNAMIC;
        }
    }

    private NetworkKitSQLiteOpenHelper(Context context, String str, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        this.oldDbMaps = new ConcurrentHashMap<>();
    }

    public static NetworkKitSQLiteOpenHelper getInstance() {
        if (instance == null) {
            synchronized (NetworkKitSQLiteOpenHelper.class) {
                if (instance == null) {
                    instance = new NetworkKitSQLiteOpenHelper(ContextHolder.getAppContext().createDeviceProtectedStorageContext(), dbName, 1);
                }
            }
        }
        return instance;
    }

    public SQLiteDatabase getNetworkKitWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = getWritableDatabase();
        } catch (Throwable th) {
            Logger.e(TAG, "getWritableDatabase db error:" + th.getMessage());
            sQLiteDatabase = null;
        }
        if (sQLiteDatabase == null) {
            Logger.e(TAG, "getWritableDatabase db is null");
        }
        return sQLiteDatabase;
    }

    public SQLiteDatabase getNetworkKitReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = getReadableDatabase();
        } catch (Throwable th) {
            Logger.e(TAG, "getReadableDatabase db error:" + th.getMessage());
            sQLiteDatabase = null;
        }
        if (sQLiteDatabase == null) {
            Logger.e(TAG, "getReadableDatabase db is null");
        }
        return sQLiteDatabase;
    }

    public SQLiteDatabase getNetworkkitUnusedDbDB() {
        String str = dbName;
        String str2 = DB_NAME_DYNAMIC;
        if (str.equals(DB_NAME_DYNAMIC)) {
            str2 = DB_NAME_LOCAL;
        }
        return getDbByName(str2);
    }

    public SQLiteDatabase getDbByName(String str) {
        SQLiteDatabase sQLiteDatabase = this.oldDbMaps.get(str);
        if (sQLiteDatabase != null) {
            Logger.d(TAG, "get old db from oldDbMaps, dbname = " + str);
            return sQLiteDatabase;
        }
        SQLiteDatabase networkKitReadableDatabase = getNetworkKitReadableDatabase();
        if (networkKitReadableDatabase == null) {
            return networkKitReadableDatabase;
        }
        String replace = networkKitReadableDatabase.getPath().replace(dbName, str);
        if (new File(replace).exists()) {
            SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(replace, null, 0);
            this.oldDbMaps.put(str, openDatabase);
            Logger.d(TAG, "create and put old db to oldDbMaps, dbname = " + str);
            return openDatabase;
        }
        Logger.i(TAG, "old db is not exists");
        return networkKitReadableDatabase;
    }

    public void deleteDbByName(String str) {
        Logger.d(TAG, "close old db from oldDbMaps, dbname = " + str);
        SQLiteDatabase sQLiteDatabase = this.oldDbMaps.get(str);
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
            this.oldDbMaps.remove(str);
        }
    }

    public boolean deleteDbFile() {
        return deleteDbFileByPath(getNetworkKitReadableDatabase().getPath());
    }

    public boolean deleteUnusedDbFile() {
        String str = dbName;
        String str2 = DB_NAME_DYNAMIC;
        if (str.equals(DB_NAME_DYNAMIC)) {
            str2 = DB_NAME_LOCAL;
        }
        return deleteDbFileByPath(getNetworkKitReadableDatabase().getPath().replace(dbName, str2));
    }

    public boolean deleteDbFileByPath(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        Logger.i(TAG, "db is not exists");
        return false;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbNameSuffix() {
        return DB_NAME_SUFFIX;
    }
}
