package defpackage;

import android.content.Context;
import android.database.sqlite.SQLiteException;

/* loaded from: classes8.dex */
public class aaa {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f126a = new Object();

    public static int a(Context context) {
        abd.c("ClearAllDBInfo", "clearAllPrepareInfo");
        synchronized (f126a) {
            try {
                try {
                    try {
                        if (zz.e() == null) {
                            abd.c("ClearAllDBInfo", "clearAllPrepareInfo getContext is null: set context");
                            if (context == null) {
                                abd.b("ClearAllDBInfo", "clearAllPrepareInfo context is null");
                                return -1;
                            }
                            zz.d(context);
                        }
                        zr.a(context).b();
                        return 0;
                    } catch (SQLiteException unused) {
                        abd.b("ClearAllDBInfo", "clearAllPrepareInfo SQLiteException");
                        return -2;
                    }
                } catch (Exception e) {
                    abd.b("ClearAllDBInfo", "clearAllPrepareInfo Exception = " + e.toString());
                    return -1;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static int b(Context context) {
        abd.c("ClearAllDBInfo", "clearTag");
        synchronized (f126a) {
            try {
                try {
                    try {
                        if (zz.e() == null) {
                            abd.c("ClearAllDBInfo", "getContext is null: set context");
                            if (context == null) {
                                abd.b("ClearAllDBInfo", "clearAllInfo context is null");
                                return -1;
                            }
                            zz.d(context);
                        }
                        new aab().c();
                        new zy().a();
                        new aad().c();
                        return 0;
                    } catch (Exception e) {
                        abd.b("ClearAllDBInfo", "clearAllInfo Exception = " + e.toString());
                        return -1;
                    }
                } catch (SQLiteException unused) {
                    abd.b("ClearAllDBInfo", "clearAllInfo SQLiteException");
                    return -2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
