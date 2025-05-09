package com.huawei.hianalytics;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.DaoManager;

/* loaded from: classes4.dex */
public class o extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    public static volatile o f3888a;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        HiLog.si("HaOpenHelper", "upgrade run");
        try {
            j.a(sQLiteDatabase);
            HiLog.si("HaOpenHelper", "upgrade run success");
        } catch (Exception unused) {
            HiLog.e("HaOpenHelper", "onUpgrade error");
            DaoManager.getInstance().dropAllTable(sQLiteDatabase);
            DaoManager.getInstance().createEventTable(sQLiteDatabase);
            DaoManager.getInstance().createHeaderTable(sQLiteDatabase);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        DaoManager.getInstance().createEventTable(sQLiteDatabase);
        DaoManager.getInstance().createHeaderTable(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        super.onConfigure(sQLiteDatabase);
        try {
            sQLiteDatabase.execSQL("PRAGMA synchronous = OFF");
        } catch (SQLException unused) {
            HiLog.e("HaOpenHelper", "execSQL synchronous OFF SQLException");
        }
    }

    public static o a(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        if (f3888a == null) {
            synchronized (o.class) {
                if (f3888a == null) {
                    f3888a = new o(context, str, null, i);
                }
            }
        }
        return f3888a;
    }

    public o(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }
}
