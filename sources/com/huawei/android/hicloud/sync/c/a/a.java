package com.huawei.android.hicloud.sync.c.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import defpackage.abd;

/* loaded from: classes8.dex */
public final class a extends SQLiteOpenHelper {
    public a(Context context) {
        super(context, "sync.db", (SQLiteDatabase.CursorFactory) null, 4);
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE etag ADD COLUMN recycle_status INTEGER DEFAULT 2");
    }

    private void eJ_(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ctag (ctag_name TEXT PRIMARY KEY, ctag_value TEXT NOT NULL, sync_token TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS etag (luid TEXT NOT NULL,type TEXT NOT NULL,etag TEXT NOT NULL,uuid TEXT,guid TEXT NOT NULL,hash TEXT,recycle_status INTEGER,UNIQUE(luid,type));");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS idx_etag_guid ON etag(guid);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (itemid TEXT NOT NULL,type TEXT NOT NULL,hash TEXT NOT NULL,name TEXT NOT NULL,fileDir TEXT,UNIQUE(itemid,type,name));");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS idx_file_url ON file(name);");
        eK_(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS pre_records (syncType TEXT NOT NULL,prepareTraceId TEXT NOT NULL,scene INTEGER,startTime TEXT,endTime TEXT,errCode TEXT,times INTEGER,PRIMARY KEY (syncType,prepareTraceId,scene,errCode));");
    }

    private void eL_(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE ctag ADD COLUMN sync_token TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE file ADD COLUMN fileDir TEXT");
    }

    private void eM_(SQLiteDatabase sQLiteDatabase) {
        eK_(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS pre_records (syncType TEXT NOT NULL,prepareTraceId TEXT NOT NULL,scene INTEGER,startTime TEXT,endTime TEXT,errCode TEXT,times INTEGER,PRIMARY KEY (syncType,prepareTraceId,scene,errCode));");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        abd.c("SyncDBHelper", "onCreate");
        eJ_(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        super.onDowngrade(sQLiteDatabase, i, i2);
        abd.d("SyncDBHelper", "onDowngrade, oldVersion: " + i + ", newVersion: " + i2);
        sQLiteDatabase.execSQL("DELETE FROM ctag ");
        sQLiteDatabase.execSQL("DELETE FROM file ");
        if (i2 < 4) {
            eK_(sQLiteDatabase, "DELETE FROM pre_records");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        abd.c("SyncDBHelper", "onUpgrade, oldVersion: " + i + ", newVersion: " + i2);
        while (i < i2) {
            if (i == 1) {
                eL_(sQLiteDatabase);
            } else if (i == 2) {
                c(sQLiteDatabase);
            } else if (i == 3) {
                eM_(sQLiteDatabase);
            }
            i++;
        }
    }

    private void eK_(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (Exception e) {
            abd.d("SyncDBHelper", "upgradeTableNeedTryCatch exception: " + e.toString());
        }
    }
}
