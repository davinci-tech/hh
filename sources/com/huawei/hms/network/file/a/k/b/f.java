package com.huawei.hms.network.file.a.k.b;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.network.file.api.RequestConfig;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public class f {
    public static byte[] a(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            a((Closeable) objectOutputStream);
            a((Closeable) byteArrayOutputStream);
            return byteArray;
        } catch (IOException e3) {
            e = e3;
            objectOutputStream2 = objectOutputStream;
            FLogger.logException(e);
            a((Closeable) objectOutputStream2);
            a((Closeable) byteArrayOutputStream);
            return new byte[0];
        } catch (Throwable th3) {
            th = th3;
            objectOutputStream2 = objectOutputStream;
            a((Closeable) objectOutputStream2);
            a((Closeable) byteArrayOutputStream);
            throw th;
        }
    }

    static final class a extends ObjectInputStream {

        /* renamed from: a, reason: collision with root package name */
        static final Set<String> f5613a = new C0145a();

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
            if (f5613a.contains(objectStreamClass.getName())) {
                return super.resolveClass(objectStreamClass);
            }
            String str = "resolveClass forbidden for:" + objectStreamClass.getName();
            FLogger.e("IOUtils", str);
            throw new ClassNotFoundException(str);
        }

        /* renamed from: com.huawei.hms.network.file.a.k.b.f$a$a, reason: collision with other inner class name */
        class C0145a extends HashSet<String> {
            C0145a() {
                add(Collections.unmodifiableMap(Collections.emptyMap()).getClass().getName());
                add(HashMap.class.getName());
                add(RequestConfig.class.getName());
            }
        }

        public a(InputStream inputStream) {
            super(inputStream);
        }
    }

    public static void a(Closeable closeable) {
        Utils.close(closeable);
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
                return;
            }
            FLogger.w("IOUtils", "db is null for:" + str, new Object[0]);
        } catch (SQLiteException e) {
            FLogger.w("IOUtils", "SqliteException for:" + str, e);
        } catch (RuntimeException e2) {
            FLogger.w("IOUtils", "RuntimeException for:" + str, e2);
        }
    }

    public static void a(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        try {
            IoUtils.close(cursor);
        } catch (RuntimeException | Exception e) {
            FLogger.logException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.Closeable] */
    public static Object a(byte[] bArr) {
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        Exception e;
        a aVar;
        Closeable closeable = null;
        Object obj = null;
        closeable = null;
        try {
            if (bArr == 0) {
                return null;
            }
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
            } catch (Exception e2) {
                e = e2;
                aVar = null;
                byteArrayInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayInputStream = null;
            }
            try {
                aVar = new a(byteArrayInputStream);
                try {
                    obj = aVar.readObject();
                    bArr = aVar;
                } catch (Exception e3) {
                    e = e3;
                    FLogger.logException(e);
                    bArr = aVar;
                    a((Closeable) bArr);
                    a((Closeable) byteArrayInputStream);
                    return obj;
                }
            } catch (Exception e4) {
                e = e4;
                aVar = null;
            } catch (Throwable th3) {
                th = th3;
                a(closeable);
                a((Closeable) byteArrayInputStream);
                throw th;
            }
            a((Closeable) bArr);
            a((Closeable) byteArrayInputStream);
            return obj;
        } catch (Throwable th4) {
            closeable = bArr;
            th = th4;
        }
    }
}
