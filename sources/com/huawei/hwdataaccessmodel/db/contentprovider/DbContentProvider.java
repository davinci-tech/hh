package com.huawei.hwdataaccessmodel.db.contentprovider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class DbContentProvider extends SQLiteOpenHelper {
    private static DbContentProvider d;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    private DbContentProvider(Context context) {
        super(context, "HwCPDatas.db", null, 3, new b());
    }

    public static DbContentProvider c(Context context) {
        DbContentProvider dbContentProvider;
        synchronized (DbContentProvider.class) {
            if (d == null) {
                d = new DbContentProvider(context);
            }
            dbContentProvider = d;
        }
        return dbContentProvider;
    }

    public SQLiteDatabase bGL_() {
        return getWritableDatabase();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtil.a("DbContentProvider", "DbContentProvider_Version", "oldVersion = ", Integer.valueOf(i), " newVersion = ", Integer.valueOf(i2));
        while (i < i2) {
            if (i == 1 || i == 2) {
                try {
                    bGK_(sQLiteDatabase);
                } catch (SQLException e) {
                    LogUtil.b("DbContentProvider", "DbContentProvider_onUpgrade exception", e.getMessage());
                    return;
                }
            }
            i++;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtil.a("DbContentProvider", "DbContentProvider onDowngrade", "oldVersion = ", Integer.valueOf(i), " newVersion = ", Integer.valueOf(i2));
    }

    private void bGK_(SQLiteDatabase sQLiteDatabase) {
        bGJ_(sQLiteDatabase);
        sQLiteDatabase.execSQL("ALTER TABLE module_20009_smart_msg ADD showCount int default 0");
    }

    private void bGJ_(SQLiteDatabase sQLiteDatabase) {
        LogUtil.c("DbContentProvider", "DbContentProvider_createTable | create new table sql = id integer primary key autoincrement,msgType integer not null check(msgType >= 10000),msgSrc integer not null check(msgSrc > 0),msgContentType integer not null check(msgContentType > 0),msgContent text not null,showMethod text,createTime integer not null,updateTime integer,expireTime integer,status integer not null check(status > 0),huid text,showTime text,priority integer not null");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table IF NOT EXISTS module_20009_smart_msg(id integer primary key autoincrement,msgType integer not null check(msgType >= 10000),msgSrc integer not null check(msgSrc > 0),msgContentType integer not null check(msgContentType > 0),msgContent text not null,showMethod text,createTime integer not null,updateTime integer,expireTime integer,status integer not null check(status > 0),huid text,showTime text,priority integer not null)");
        sQLiteDatabase.execSQL(String.valueOf(stringBuffer));
    }

    public void c(String str) {
        synchronized (DbContentProvider.class) {
            SQLiteDatabase bGL_ = d.bGL_();
            if (bGL_ != null) {
                try {
                    bGL_.execSQL(str);
                } catch (SQLiteException e) {
                    ReleaseLogUtil.c("R_DbContentProvider", "execSql SQLiteException, e=", ExceptionUtils.d(e));
                } catch (Exception e2) {
                    ReleaseLogUtil.c("R_DbContentProvider", "execSql Exception=", ExceptionUtils.d(e2));
                }
            }
        }
    }

    static class b implements DatabaseErrorHandler {

        /* renamed from: a, reason: collision with root package name */
        private final DatabaseErrorHandler f6273a;

        private b() {
            this.f6273a = new DefaultDatabaseErrorHandler();
        }

        @Override // android.database.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            this.f6273a.onCorruption(sQLiteDatabase);
            ReleaseLogUtil.d("R_DbContentProvider", "db file corruption:", sQLiteDatabase.getPath());
        }
    }
}
