package com.huawei.openalliance.ad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class ep {

    /* renamed from: a, reason: collision with root package name */
    protected Context f6846a;
    protected gc b;

    public void e() {
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> void c(List<de> list) {
        dd a2 = dd.a(this.f6846a);
        try {
            a2.a(list);
        } finally {
            a(a2);
        }
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> void a(Class<T> cls, ContentValues contentValues, fi fiVar, List<String> list) {
        if (this.b.aL()) {
            dd a2 = dd.a(this.f6846a);
            try {
                a2.a(cls.getSimpleName(), contentValues, fiVar.a(), list);
            } finally {
                a(a2);
            }
        }
    }

    protected void a(dd ddVar) {
        if (ddVar != null) {
            ddVar.a();
        }
    }

    protected void a(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        try {
            cursor.close();
        } catch (Throwable unused) {
            ho.d("BaseDao", "closeCursor exception");
        }
    }

    protected List<String> a(String str, String[] strArr, String str2) {
        dd ddVar;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            ddVar = dd.a(this.f6846a);
            try {
                cursor = ddVar.a(str, strArr);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            arrayList.add(cursor.getString(cursor.getColumnIndex(str2)));
                        } catch (Exception unused) {
                            ho.d("BaseDao", "query exception");
                        }
                    }
                }
            } catch (Throwable th) {
                th = th;
                try {
                    ho.d("BaseDao", "query db exception: %s", th.getClass().getSimpleName());
                    return arrayList;
                } finally {
                    a(cursor);
                    a(ddVar);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            ddVar = null;
        }
        return arrayList;
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> List<T> a(Class<T> cls, String[] strArr, fi fiVar, String[] strArr2, String str, String str2) {
        dd ddVar;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        String a2 = fiVar == null ? null : fiVar.a();
        try {
            ddVar = dd.a(this.f6846a);
            try {
                Cursor a3 = ddVar.a(cls.getSimpleName(), strArr, a2, strArr2, str, str2);
                try {
                    a(cls, arrayList, a3);
                    a(a3);
                    a(ddVar);
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    cursor = a3;
                    a(cursor);
                    a(ddVar);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            ddVar = null;
        }
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> List<T> a(Class<T> cls, String str, String[] strArr) {
        dd ddVar;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            ddVar = dd.a(this.f6846a);
            try {
                cursor = ddVar.a(str, strArr);
                a(cls, arrayList, cursor);
            } catch (Throwable th) {
                th = th;
                try {
                    ho.d("BaseDao", "query db exception: %s", th.getClass().getSimpleName());
                    return arrayList;
                } finally {
                    a(cursor);
                    a(ddVar);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            ddVar = null;
        }
        return arrayList;
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> long a(Class<T> cls, List<ContentValues> list) {
        dd a2 = dd.a(this.f6846a);
        try {
            return a2.a(cls.getSimpleName(), list);
        } finally {
            a(a2);
        }
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> long a(Class<T> cls, ContentValues contentValues) {
        if (!this.b.aL()) {
            return 0L;
        }
        dd a2 = dd.a(this.f6846a);
        try {
            return a2.a(cls.getSimpleName(), contentValues);
        } finally {
            a(a2);
        }
    }

    protected long a(Class cls) {
        dd ddVar = null;
        try {
            ddVar = dd.a(this.f6846a);
            return ddVar.g(cls.getSimpleName());
        } finally {
            a(ddVar);
        }
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> int a(Class<T> cls, fi fiVar, String[] strArr) {
        dd a2 = dd.a(this.f6846a);
        try {
            return a2.a(cls.getSimpleName(), fiVar == null ? null : fiVar.a(), strArr);
        } finally {
            a(a2);
        }
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> int a(Class<T> cls, fi fiVar, List<String> list) {
        dd a2 = dd.a(this.f6846a);
        try {
            return a2.a(cls.getSimpleName(), fiVar.a(), list);
        } finally {
            a(a2);
        }
    }

    protected <T extends com.huawei.openalliance.ad.db.bean.a> int a(Class<T> cls, ContentValues contentValues, fi fiVar, String[] strArr) {
        if (!this.b.aL()) {
            return 0;
        }
        dd a2 = dd.a(this.f6846a);
        try {
            return a2.a(cls.getSimpleName(), contentValues, fiVar.a(), strArr);
        } finally {
            a(a2);
        }
    }

    private <T extends com.huawei.openalliance.ad.db.bean.a> void a(Class<T> cls, List<T> list, Cursor cursor) {
        String str;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    T newInstance = cls.newInstance();
                    newInstance.a(cursor);
                    list.add(newInstance);
                } catch (IllegalAccessException unused) {
                    str = "query IllegalAccessException";
                    ho.d("BaseDao", str);
                } catch (InstantiationException unused2) {
                    str = "query InstantiationException";
                    ho.d("BaseDao", str);
                } catch (Exception unused3) {
                    str = "query exception";
                    ho.d("BaseDao", str);
                }
            }
        }
    }

    protected ep(Context context) {
        this.f6846a = context.getApplicationContext();
        this.b = fh.b(context);
    }
}
