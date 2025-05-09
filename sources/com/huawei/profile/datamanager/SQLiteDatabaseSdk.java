package com.huawei.profile.datamanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.profile.kv.DBEntity;
import com.huawei.profile.utils.AnonymousUtil;
import com.huawei.profile.utils.ProfileFileUtils;
import com.huawei.profile.utils.logger.DsLog;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class SQLiteDatabaseSdk extends AbstractDatabase {
    private static final int CORRECT_CODE = 1;
    private static final String DATABASES_PATH = "/databases/";
    private static final String DATABASE_CONDITION = "key = ?";
    private static final String DATABASE_CONDITION_OR = " OR ";
    private static final String DATABASE_ENTITY_KEY = "key";
    private static final String DATABASE_ENTITY_VALUE = "value";
    private static final String DATABASE_IS_EMPTY = "";
    private static final String DATABASE_NAME = "profileData";
    private static final String DATABASE_PATH_JOURNAL = "-journal";
    private static final String DATABASE_PATH_SHM = "-shm";
    private static final String DATABASE_PATH_WAL = "-wal";
    private static final String DELETE_DATA_CONDITION = "key like ?";
    private static final int ERROR_CODE = -1;
    private static final int FIRST_INDEX_OF_ARRAY = 0;
    private static final int INITIAL_LENGTH_OF_ARRAY = 1;
    private static final Object LOCK = new Object();
    private static final String TAG = "SQLiteDatabaseSdk";
    private static final String WILDCARD = "%";
    private static SQLiteDatabaseSdk singleton;
    private SQLiteDatabase database;
    private DatabaseHelperSdk databaseHelper;
    private boolean isWalModel = false;

    public SQLiteDatabaseSdk(Context context) {
        initDatabase(context);
    }

    public static SQLiteDatabaseSdk getInstance(Context context) {
        SQLiteDatabaseSdk sQLiteDatabaseSdk;
        synchronized (LOCK) {
            if (singleton == null) {
                singleton = new SQLiteDatabaseSdk(context);
            }
            sQLiteDatabaseSdk = singleton;
        }
        return sQLiteDatabaseSdk;
    }

    private void initDatabase(Context context) {
        try {
            DatabaseHelperSdk databaseHelperSdk = new DatabaseHelperSdk(context, getDatabasePath(context));
            this.databaseHelper = databaseHelperSdk;
            this.database = databaseHelperSdk.getWritableDatabase();
        } catch (SQLiteException unused) {
            if (databaseRecovery(context)) {
                initDatabase(context);
            }
        }
    }

    private boolean databaseRecovery(Context context) {
        String databasePath = getDatabasePath(context);
        try {
            ProfileFileUtils.deleteFile(databasePath);
            if (this.isWalModel) {
                ProfileFileUtils.deleteFile(databasePath + DATABASE_PATH_WAL);
                ProfileFileUtils.deleteFile(databasePath + DATABASE_PATH_SHM);
                return true;
            }
            ProfileFileUtils.deleteFile(databasePath + DATABASE_PATH_JOURNAL);
            return true;
        } catch (IOException unused) {
            DsLog.et(TAG, "Failed to delete database.", new Object[0]);
            return false;
        }
    }

    private String getDatabasePath(Context context) {
        try {
            return context.getFilesDir().getParentFile().getCanonicalPath() + "/databases/profileData";
        } catch (IOException unused) {
            DsLog.et(TAG, "failed to get database path", new Object[0]);
            throw new IllegalArgumentException("unable to get database directory");
        }
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public boolean put(String str, DBEntity dBEntity) {
        if (str == null || !dBEntity.verify()) {
            DsLog.et(TAG, "putInDatabase failed, reason is pkgName or dbEntity is invalid", new Object[0]);
            return false;
        }
        DsLog.dt(TAG, "putInDatabase , tableName is PUBLIC_CLOUD_TABLE", new Object[0]);
        return putString("PUBLIC_CLOUD_TABLE", dBEntity);
    }

    private boolean putString(String str, DBEntity dBEntity) {
        String[] strArr = {dBEntity.getEntityKey()};
        Cursor cursor = null;
        try {
            Cursor query = this.database.query(str, null, DATABASE_CONDITION, strArr, null, null, null, null);
            try {
                if (!query.moveToFirst()) {
                    DsLog.dt(TAG, "the key not exist, insert", new Object[0]);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("key", dBEntity.getEntityKey());
                    contentValues.put("value", dBEntity.getEntityValue());
                    if (this.database.insert(str, null, contentValues) != -1) {
                        DsLog.dt(TAG, " success to insert", new Object[0]);
                        if (query != null) {
                            query.close();
                        }
                        return true;
                    }
                    DsLog.et(TAG, " fail to insert", new Object[0]);
                    if (query != null) {
                        query.close();
                    }
                    return false;
                }
                DsLog.dt(TAG, "the key exist, update", new Object[0]);
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("value", dBEntity.getEntityValue());
                int update = this.database.update(str, contentValues2, DATABASE_CONDITION, strArr);
                if (update == 1) {
                    if (query != null) {
                        query.close();
                    }
                    return true;
                }
                if (update > 1) {
                    DsLog.wt(TAG, " update success, affect %s rows", Integer.valueOf(update));
                    if (query != null) {
                        query.close();
                    }
                    return true;
                }
                DsLog.et(TAG, " failed to update, affect %s rows", Integer.valueOf(update));
                if (query != null) {
                    query.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public String get(String str, String str2) {
        Cursor cursor = null;
        if (str == null || str2 == null) {
            DsLog.et(TAG, "getFromDatabase failed, reason is pkgName or dbKey is invalid", new Object[0]);
            return null;
        }
        try {
            try {
                cursor = this.database.query("PUBLIC_CLOUD_TABLE", null, DATABASE_CONDITION, new String[]{str2}, null, null, null, null);
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return "";
                }
                String string = cursor.getString(cursor.getColumnIndex("value"));
                if (cursor != null) {
                    cursor.close();
                }
                return string;
            } catch (SQLiteException unused) {
                DsLog.et(TAG, "get data from db failed.", new Object[0]);
                if (cursor != null) {
                    cursor.close();
                }
                return "";
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public List<KvEntity> get(String str) {
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public boolean remove(String str, String str2) {
        if (str == null || str2 == null) {
            DsLog.et(TAG, "removeFromDb failed, reason is pkgName or dbKey invalid", new Object[0]);
            return false;
        }
        int delete = this.database.delete("PUBLIC_CLOUD_TABLE", DATABASE_CONDITION, new String[]{str2});
        if (delete == 1) {
            return true;
        }
        if (delete > 1) {
            DsLog.wt(TAG, "removeFromDb success, affect %s rows, key = %s", Integer.valueOf(delete), AnonymousUtil.anonymousJson(str2));
            return true;
        }
        DsLog.et(TAG, "removeFromDb fail, affect %s rows, key = %s", Integer.valueOf(delete), AnonymousUtil.anonymousJson(str2));
        return false;
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public boolean removeList(String str, List<String> list) {
        if (str == null || list == null || list.size() == 0) {
            DsLog.wt(TAG, "removeFromDb failed, pkgName or dbKey invalid", new Object[0]);
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(DATABASE_CONDITION);
            if (i < list.size() - 1) {
                sb.append(DATABASE_CONDITION_OR);
            }
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            DsLog.wt(TAG, "dbConditionString is Empty", new Object[0]);
            return false;
        }
        int delete = this.database.delete("PUBLIC_CLOUD_TABLE", sb2, (String[]) list.toArray(new String[0]));
        if (delete == 1) {
            return true;
        }
        if (delete > 1) {
            DsLog.wt(TAG, "removeListFromDb success, affect %s rows", Integer.valueOf(delete));
            return true;
        }
        DsLog.et(TAG, "removeListFromDb fail, affect %s rows", Integer.valueOf(delete));
        return false;
    }

    @Override // com.huawei.profile.datamanager.AbstractDatabase
    public boolean removeStartWithKey(String str, String str2) {
        if (str == null || str2 == null) {
            DsLog.et(TAG, "removeFromDb failed, reason is pkgName or dbKey invalid", new Object[0]);
            return false;
        }
        try {
            DsLog.dt(TAG, "removeFromDb success, affect %s rows, key = %s", Integer.valueOf(this.database.delete("PUBLIC_CLOUD_TABLE", DELETE_DATA_CONDITION, new String[]{str2 + WILDCARD})), AnonymousUtil.anonymousJson(str2));
            return true;
        } catch (SQLException unused) {
            DsLog.et(TAG, "removeFromDb failed, reason is SQLiteException", new Object[0]);
            return false;
        }
    }
}
