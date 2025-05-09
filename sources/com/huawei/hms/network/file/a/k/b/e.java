package com.huawei.hms.network.file.a.k.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.download.api.GetRequest;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class e implements com.huawei.hms.network.file.a.k.a<GetRequest, com.huawei.hms.network.file.a.d> {
    private static volatile e d;

    /* renamed from: a, reason: collision with root package name */
    private g f5612a;
    private i b;
    private volatile boolean c;

    public List<com.huawei.hms.network.file.a.d> d(long j, String str) {
        return this.b.b(j, str);
    }

    public com.huawei.hms.network.file.core.e<GetRequest> c(long j, String str) {
        return this.f5612a.a(j);
    }

    public void b(GetRequest getRequest, e.a aVar, String str) {
        this.f5612a.a(getRequest.getId(), aVar, str);
    }

    public void b(long j, List<com.huawei.hms.network.file.a.d> list, String str) {
        this.b.b((List) list, str);
    }

    public void b(long j, String str) {
        this.b.a(j, str);
    }

    public boolean a() {
        return this.c;
    }

    public void a(List<com.huawei.hms.network.file.a.d> list, String str) {
        this.b.d(list, str);
    }

    public void a(GetRequest getRequest, String str) {
        this.f5612a.b((g) new com.huawei.hms.network.file.core.e(getRequest, e.a.INIT.ordinal()), str);
    }

    public void a(GetRequest getRequest, e.a aVar, String str) {
        this.f5612a.a(getRequest, aVar, str);
    }

    public void a(long j, List<com.huawei.hms.network.file.a.d> list, String str) {
        this.b.c(list, str);
    }

    public Set<Long> a(String str, int i) {
        return this.f5612a.a(str, i);
    }

    public List<com.huawei.hms.network.file.core.e<GetRequest>> a(String str) {
        return this.f5612a.c(str);
    }

    public String a(long j, String str) {
        return this.f5612a.a(j, str);
    }

    public static e a(Context context) {
        if (d == null) {
            synchronized (e.class) {
                if (d == null) {
                    d = new e(context);
                }
            }
        }
        return d;
    }

    private e(Context context) {
        this.c = false;
        if (context == null) {
            FLogger.e("DownloadDataDaoImpl", "context is null, must call init method to set context");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("download_move2DE_records", 0);
        if (sharedPreferences.getBoolean("DownloadData.db", false)) {
            FLogger.i("DownloadDataDaoImpl", "the db has moved!", new Object[0]);
        } else {
            Context applicationContext = context.getApplicationContext();
            if (context.moveDatabaseFrom(applicationContext, "DownloadData.db")) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("DownloadData.db", true);
                edit.apply();
                FLogger.i("DownloadDataDaoImpl", "the package file has moved to user_de", new Object[0]);
            } else {
                FLogger.w("DownloadDataDaoImpl", "moveDatabaseFrom failed! use sysContext continue", new Object[0]);
                context = applicationContext;
            }
        }
        try {
            SQLiteDatabase writableDatabase = new d(context).getWritableDatabase();
            this.f5612a = new g(writableDatabase);
            this.b = new i(writableDatabase);
            this.c = true;
        } catch (SQLiteException e) {
            FLogger.w("DownloadDataDaoImpl", "the sqlite is not available!", e);
            FLogger.i("DownloadDataDaoImpl", "this time for dbWriting, dbWrite == null", new Object[0]);
        } catch (RuntimeException e2) {
            FLogger.w("DownloadDataDaoImpl", "create DB RuntimeException", e2);
        }
    }
}
