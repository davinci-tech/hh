package com.autonavi.aps.amapapi.storage;

import android.database.sqlite.SQLiteDatabase;
import com.amap.api.col.p0003sl.iy;

/* loaded from: classes2.dex */
public class c implements iy {
    @Override // com.amap.api.col.p0003sl.iy
    public final void a(SQLiteDatabase sQLiteDatabase, int i) {
    }

    @Override // com.amap.api.col.p0003sl.iy
    public final int c() {
        return 1;
    }

    @Override // com.amap.api.col.p0003sl.iy
    public final void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS c (_id integer primary key autoincrement, a2 varchar(100), a4 varchar(2000), a3 LONG );");
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "SdCardDbCreator", "onCreate");
        }
    }

    @Override // com.amap.api.col.p0003sl.iy
    public final String b() {
        return "alsn20170807.db";
    }
}
