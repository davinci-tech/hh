package com.huawei.ads.fund.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.wk;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseDao implements IDao {
    public Context context;

    @Override // com.huawei.ads.fund.db.IDao
    public void emptyTables(String str) {
    }

    @Override // com.huawei.ads.fund.db.IDao
    public <T extends RecordBean> void update(Class<T> cls, ContentValues contentValues, String str, List<String> list) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            dbHelper.update(cls.getSimpleName(), contentValues, str, list);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public <T extends RecordBean> int update(Class<T> cls, ContentValues contentValues, String str, String[] strArr) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            return dbHelper.update(cls.getSimpleName(), contentValues, str, strArr);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public void releaseDbHelper(BaseDbHelper baseDbHelper) {
        if (baseDbHelper != null) {
            baseDbHelper.a();
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public List<String> rawQuerySpecColumn(String str, String[] strArr, String str2) {
        BaseDbHelper baseDbHelper;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            baseDbHelper = getDbHelper();
            try {
                cursor = baseDbHelper.rawQuery(str, strArr);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            arrayList.add(cursor.getString(cursor.getColumnIndex(str2)));
                        } catch (Exception unused) {
                            HiAdLog.e(getTag(), "query exception");
                        }
                    }
                }
            } catch (Throwable th) {
                th = th;
                try {
                    HiAdLog.e(getTag(), "query db exception: %s", th.getClass().getSimpleName());
                    return arrayList;
                } finally {
                    closeCursor(cursor);
                    releaseDbHelper(baseDbHelper);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            baseDbHelper = null;
        }
        return arrayList;
    }

    @Override // com.huawei.ads.fund.db.IDao
    public <T extends RecordBean> List<T> rawQuery(Class<T> cls, String str, String[] strArr) {
        BaseDbHelper baseDbHelper;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            baseDbHelper = getDbHelper();
            try {
                cursor = baseDbHelper.rawQuery(str, strArr);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            T newInstance = cls.newInstance();
                            newInstance.toBean(cursor);
                            arrayList.add(newInstance);
                        } catch (Throwable th) {
                            HiAdLog.e(getTag(), "query:" + th.getClass().getSimpleName());
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    HiAdLog.e(getTag(), "query db exception: %s", th.getClass().getSimpleName());
                    return arrayList;
                } finally {
                    closeCursor(cursor);
                    releaseDbHelper(baseDbHelper);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            baseDbHelper = null;
        }
        return arrayList;
    }

    @Override // com.huawei.ads.fund.db.IDao
    public long queryCount(String str) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            try {
                return dbHelper.queryCount(str);
            } catch (Exception unused) {
                HiAdLog.e(getTag(), "queryCount exception");
                releaseDbHelper(dbHelper);
                return 0L;
            }
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public <T extends RecordBean> List<T> query(Class<T> cls, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        BaseDbHelper baseDbHelper;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            baseDbHelper = getDbHelper();
            try {
                cursor = baseDbHelper.query(cls.getSimpleName(), strArr, str, strArr2, str2, str3);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            T newInstance = cls.newInstance();
                            newInstance.toBean(cursor);
                            arrayList.add(newInstance);
                        } catch (Throwable th) {
                            HiAdLog.e(getTag(), "query:" + th.getClass().getSimpleName());
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    HiAdLog.e(getTag(), "query db exception: %s", th.getClass().getSimpleName());
                    return arrayList;
                } finally {
                    closeCursor(cursor);
                    releaseDbHelper(baseDbHelper);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            baseDbHelper = null;
        }
        return arrayList;
    }

    @Override // com.huawei.ads.fund.db.IDao
    public void insertOrUpdate(List<wk> list) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            dbHelper.insertOrUpdate(list);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public void insert(List<wk> list) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            dbHelper.insert(list);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public <T extends RecordBean> long insert(Class<T> cls, ContentValues contentValues) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            return dbHelper.insert(cls.getSimpleName(), contentValues);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public void executeSql(String str, Object[] objArr) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            dbHelper.executeSql(str, objArr);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public <T extends RecordBean> void deleteSet(Class<T> cls, String str, List<String> list) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            dbHelper.delete(cls.getSimpleName(), str, list);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public <T extends RecordBean> int delete(Class<T> cls, String str, String[] strArr) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            return dbHelper.delete(cls.getSimpleName(), str, strArr);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public void closeCursor(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        try {
            cursor.close();
        } catch (Throwable unused) {
            HiAdLog.e(getTag(), "closeCursor exception");
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public void clearExpiredData() {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            dbHelper.clearExpiredData();
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    @Override // com.huawei.ads.fund.db.IDao
    public void batchExecute(String str, List<String[]> list) {
        BaseDbHelper dbHelper = getDbHelper();
        try {
            dbHelper.batchExecute(str, list);
        } finally {
            releaseDbHelper(dbHelper);
        }
    }

    public BaseDao(Context context) {
        this.context = context.getApplicationContext();
    }
}
