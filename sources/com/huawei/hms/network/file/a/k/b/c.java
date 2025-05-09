package com.huawei.hms.network.file.a.k.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.hms.network.file.core.util.FLogger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class c<T> {

    /* renamed from: a, reason: collision with root package name */
    private SQLiteDatabase f5610a;

    public abstract ContentValues a(T t, String str);

    public abstract T a(Cursor cursor);

    public abstract String a();

    public T c(String str, String[] strArr) {
        List<T> a2 = a(null, str, strArr, null, null, null, "1");
        if (a2.size() > 0) {
            return a2.get(0);
        }
        return null;
    }

    public boolean b(List<T> list, String str) {
        try {
            if (list == null) {
                return false;
            }
            try {
                this.f5610a.beginTransaction();
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (this.f5610a.replace(a(), null, a((c<T>) it.next(), str)) == -1) {
                        FLogger.w("DBDao", "for replace error", new Object[0]);
                    }
                }
                this.f5610a.setTransactionSuccessful();
                f.a(this.f5610a, "when endTransaction has error!,this time is replace");
                return true;
            } catch (Exception e) {
                FLogger.logException(e);
                f.a(this.f5610a, "when endTransaction has error!,this time is replace");
                return false;
            }
        } catch (Throwable th) {
            f.a(this.f5610a, "when endTransaction has error!,this time is replace");
            throw th;
        }
    }

    public boolean b(T t, String str) {
        try {
            if (t == null) {
                return false;
            }
            try {
                this.f5610a.beginTransaction();
                if (this.f5610a.insert(a(), null, a((c<T>) t, str)) == -1) {
                    FLogger.w("DBDao", "insert error", new Object[0]);
                }
                this.f5610a.setTransactionSuccessful();
                f.a(this.f5610a, "when endTransaction has error!,this time is insert");
                return true;
            } catch (Exception e) {
                FLogger.logException(e);
                f.a(this.f5610a, "when endTransaction has error!,this time is insert");
                return false;
            }
        } catch (Throwable th) {
            f.a(this.f5610a, "when endTransaction has error!,this time is insert");
            throw th;
        }
    }

    public List<T> b(String str, String[] strArr) {
        return a(null, str, strArr, null, null, null, null);
    }

    protected byte[] a(Cursor cursor, String str) {
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(str));
            return blob != null ? com.huawei.hms.network.file.a.k.b.j.b.a(blob) : blob;
        } catch (Exception e) {
            FLogger.w("DBDao", "getBytesBoException exception for:" + str, new Object[0]);
            FLogger.logException(e);
            return null;
        }
    }

    public boolean a(List<T> list, String str) {
        try {
            if (list == null) {
                return false;
            }
            try {
                this.f5610a.beginTransaction();
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (this.f5610a.insert(a(), null, a((c<T>) it.next(), str)) == -1) {
                        FLogger.w("DBDao", "for insert error", new Object[0]);
                    }
                }
                this.f5610a.setTransactionSuccessful();
                f.a(this.f5610a, "when endTransaction has error!,this time is insert list");
                return true;
            } catch (Exception e) {
                FLogger.logException(e);
                f.a(this.f5610a, "when endTransaction has error!,this time is insert list");
                return false;
            }
        } catch (Throwable th) {
            f.a(this.f5610a, "when endTransaction has error!,this time is insert list");
            throw th;
        }
    }

    public boolean a(String str, String[] strArr) {
        try {
            this.f5610a.beginTransaction();
            if (this.f5610a.delete(a(), str, strArr) < 1) {
                FLogger.w("DBDao", "delete error", new Object[0]);
            }
            this.f5610a.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            FLogger.logException(e);
            return false;
        } finally {
            f.a(this.f5610a, "when endTransaction has error!,this time is delete");
        }
    }

    public boolean a(ContentValues contentValues, String str, String[] strArr) {
        try {
            FLogger.v("DBDao", "update " + str + "," + contentValues);
            this.f5610a.beginTransaction();
            int update = this.f5610a.update(a(), contentValues, str, strArr);
            if (update < 1) {
                FLogger.w("DBDao", "update error:" + update + ",tableName:" + a(), new Object[0]);
            }
            this.f5610a.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            FLogger.logException(e);
            return false;
        } finally {
            f.a(this.f5610a, "when endTransaction has error!,this time is update");
        }
    }

    public Set<Long> a(String str, String str2, String[] strArr, String str3) {
        HashSet hashSet = new HashSet();
        Cursor cursor = null;
        try {
            try {
                this.f5610a.beginTransaction();
                cursor = this.f5610a.query(a(), new String[]{str}, str2, strArr, null, null, null, str3);
                while (!cursor.isClosed() && cursor.moveToNext()) {
                    hashSet.add(Long.valueOf(cursor.getLong(cursor.getColumnIndex(str))));
                }
                this.f5610a.setTransactionSuccessful();
            } catch (Exception e) {
                FLogger.logException(e);
            }
            return hashSet;
        } finally {
            f.a(cursor);
            f.a(this.f5610a, "when endTransaction has error!,this time is queryRequuestIds");
        }
    }

    public List<T> a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                this.f5610a.beginTransaction();
                cursor = this.f5610a.query(a(), strArr, str, strArr2, str2, str3, str4, str5);
                while (!cursor.isClosed() && cursor.moveToNext()) {
                    arrayList.add(a(cursor));
                }
                this.f5610a.setTransactionSuccessful();
            } catch (Exception e) {
                FLogger.logException(e);
            }
            return arrayList;
        } finally {
            f.a(cursor);
            f.a(this.f5610a, "when endTransaction has error!,this time is query");
        }
    }

    protected static void a(ContentValues contentValues, String str, byte[] bArr) {
        try {
            contentValues.put(str, com.huawei.hms.network.file.a.k.b.j.b.b(bArr));
        } catch (Exception unused) {
            FLogger.e("DBDao", "putBlobWithEncrypt exception");
        }
    }

    public c(SQLiteDatabase sQLiteDatabase) {
        this.f5610a = sQLiteDatabase;
    }
}
