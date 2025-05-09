package com.amap.api.col.p0003sl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* loaded from: classes2.dex */
public class bk implements iy {

    /* renamed from: a, reason: collision with root package name */
    private static volatile bk f922a;

    @Override // com.amap.api.col.p0003sl.iy
    public final int c() {
        return 2;
    }

    public static bk a() {
        if (f922a == null) {
            synchronized (bk.class) {
                if (f922a == null) {
                    f922a = new bk();
                }
            }
        }
        return f922a;
    }

    private bk() {
    }

    @Override // com.amap.api.col.p0003sl.iy
    public final void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return;
        }
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item (_id integer primary key autoincrement, title  TEXT, url TEXT,mAdcode TEXT,fileName TEXT,version TEXT,lLocalLength INTEGER,lRemoteLength INTEGER,localPath TEXT,mIndex INTEGER,isProvince INTEGER NOT NULL,mCompleteCode INTEGER,mCityCode TEXT,mState INTEGER,mPinyin TEXT, UNIQUE(mAdcode));");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item_file (_id integer primary key autoincrement,mAdcode TTEXT, file TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item_download_info (_id integer primary key autoincrement,mAdcode TEXT,fileLength integer,splitter integer,startPos integer,endPos integer, UNIQUE(mAdcode));");
        } catch (Throwable th) {
            iv.c(th, "DB", "onCreate");
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.col.p0003sl.iy
    public final void a(SQLiteDatabase sQLiteDatabase, int i) {
        if (sQLiteDatabase != null && i == 1) {
            sQLiteDatabase.execSQL("ALTER TABLE update_item ADD COLUMN mPinyin TEXT;");
            Cursor query = sQLiteDatabase.query("update_item", null, null, null, null, null, null);
            if (query == null) {
                sQLiteDatabase.close();
                sQLiteDatabase = null;
            }
            if (query != null) {
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex("url"));
                    String substring = string.substring(string.lastIndexOf("/") + 1);
                    sQLiteDatabase.execSQL("update update_item set mPinyin=? where url =?", new String[]{substring.substring(0, substring.lastIndexOf(".")), string});
                }
                query.close();
            }
        }
    }

    @Override // com.amap.api.col.p0003sl.iy
    public final String b() {
        return "offlineDbV4.db";
    }
}
