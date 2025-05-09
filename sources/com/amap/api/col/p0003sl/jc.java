package com.amap.api.col.p0003sl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes2.dex */
public final class jc extends SQLiteOpenHelper {
    private static boolean b = true;
    private static boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private iy f1210a;

    public jc(Context context, String str, int i, iy iyVar) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        this.f1210a = iyVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f1210a.a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f1210a.a(sQLiteDatabase, i);
    }
}
