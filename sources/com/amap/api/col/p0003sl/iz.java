package com.amap.api.col.p0003sl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class iz {
    private static Map<Class<? extends iy>, iy> d = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private jc f1208a;
    private SQLiteDatabase b;
    private iy c;

    public static iy a(Class<? extends iy> cls) throws IllegalAccessException, InstantiationException {
        iy iyVar;
        synchronized (iz.class) {
            if (d.get(cls) == null) {
                d.put(cls, cls.newInstance());
            }
            iyVar = d.get(cls);
        }
        return iyVar;
    }

    public iz(Context context, iy iyVar) {
        try {
            this.f1208a = new jc(context.getApplicationContext(), iyVar.b(), iyVar.c(), iyVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = iyVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0023, code lost:
    
        if (r4 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> void a(java.lang.String r4, java.lang.Class<T> r5) {
        /*
            r3 = this;
            com.amap.api.col.3sl.iy r0 = r3.c
            monitor-enter(r0)
            com.amap.api.col.3sl.ja r5 = b(r5)     // Catch: java.lang.Throwable -> L45
            java.lang.String r5 = a(r5)     // Catch: java.lang.Throwable -> L45
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L45
            if (r1 == 0) goto L13
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            return
        L13:
            android.database.sqlite.SQLiteDatabase r1 = r3.b()     // Catch: java.lang.Throwable -> L45
            r3.b = r1     // Catch: java.lang.Throwable -> L45
            if (r1 != 0) goto L1d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            return
        L1d:
            r2 = 0
            r1.delete(r5, r4, r2)     // Catch: java.lang.Throwable -> L29
            android.database.sqlite.SQLiteDatabase r4 = r3.b     // Catch: java.lang.Throwable -> L45
            if (r4 == 0) goto L38
        L25:
            r4.close()     // Catch: java.lang.Throwable -> L45
            goto L36
        L29:
            r4 = move-exception
            java.lang.String r5 = "dbs"
            java.lang.String r1 = "dld"
            com.amap.api.col.p0003sl.is.a(r4, r5, r1)     // Catch: java.lang.Throwable -> L3a
            android.database.sqlite.SQLiteDatabase r4 = r3.b     // Catch: java.lang.Throwable -> L45
            if (r4 == 0) goto L38
            goto L25
        L36:
            r3.b = r2     // Catch: java.lang.Throwable -> L45
        L38:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            return
        L3a:
            r4 = move-exception
            android.database.sqlite.SQLiteDatabase r5 = r3.b     // Catch: java.lang.Throwable -> L45
            if (r5 == 0) goto L44
            r5.close()     // Catch: java.lang.Throwable -> L45
            r3.b = r2     // Catch: java.lang.Throwable -> L45
        L44:
            throw r4     // Catch: java.lang.Throwable -> L45
        L45:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.iz.a(java.lang.String, java.lang.Class):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x002f, code lost:
    
        if (r5 != null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private <T> void a(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            com.amap.api.col.3sl.iy r0 = r4.c
            monitor-enter(r0)
            if (r6 != 0) goto L7
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L51
            return
        L7:
            java.lang.Class r1 = r6.getClass()     // Catch: java.lang.Throwable -> L51
            com.amap.api.col.3sl.ja r1 = b(r1)     // Catch: java.lang.Throwable -> L51
            java.lang.String r2 = a(r1)     // Catch: java.lang.Throwable -> L51
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto L1b
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L51
            return
        L1b:
            android.content.ContentValues r6 = a(r6, r1)     // Catch: java.lang.Throwable -> L51
            android.database.sqlite.SQLiteDatabase r1 = r4.b()     // Catch: java.lang.Throwable -> L51
            r4.b = r1     // Catch: java.lang.Throwable -> L51
            if (r1 != 0) goto L29
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L51
            return
        L29:
            r3 = 0
            r1.update(r2, r6, r5, r3)     // Catch: java.lang.Throwable -> L35
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch: java.lang.Throwable -> L51
            if (r5 == 0) goto L44
        L31:
            r5.close()     // Catch: java.lang.Throwable -> L51
            goto L42
        L35:
            r5 = move-exception
            java.lang.String r6 = "dbs"
            java.lang.String r1 = "udd"
            com.amap.api.col.p0003sl.is.a(r5, r6, r1)     // Catch: java.lang.Throwable -> L46
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch: java.lang.Throwable -> L51
            if (r5 == 0) goto L44
            goto L31
        L42:
            r4.b = r3     // Catch: java.lang.Throwable -> L51
        L44:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L51
            return
        L46:
            r5 = move-exception
            android.database.sqlite.SQLiteDatabase r6 = r4.b     // Catch: java.lang.Throwable -> L51
            if (r6 == 0) goto L50
            r6.close()     // Catch: java.lang.Throwable -> L51
            r4.b = r3     // Catch: java.lang.Throwable -> L51
        L50:
            throw r5     // Catch: java.lang.Throwable -> L51
        L51:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L51
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.iz.a(java.lang.String, java.lang.Object):void");
    }

    private <T> void b(String str, Object obj) {
        a(str, obj);
    }

    public final void a(Object obj, String str) {
        synchronized (this.c) {
            List b = b(str, (Class) obj.getClass());
            if (b != null && b.size() != 0) {
                b(str, obj);
            }
            a((iz) obj);
        }
    }

    private <T> void a(T t) {
        b((iz) t);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0013, code lost:
    
        if (r5 != null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private <T> void b(T r5) {
        /*
            r4 = this;
            com.amap.api.col.3sl.iy r0 = r4.c
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r4.b()     // Catch: java.lang.Throwable -> L35
            r4.b = r1     // Catch: java.lang.Throwable -> L35
            if (r1 != 0) goto Ld
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L35
            return
        Ld:
            r2 = 0
            a(r1, r5)     // Catch: java.lang.Throwable -> L19
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch: java.lang.Throwable -> L35
            if (r5 == 0) goto L28
        L15:
            r5.close()     // Catch: java.lang.Throwable -> L35
            goto L26
        L19:
            r5 = move-exception
            java.lang.String r1 = "dbs"
            java.lang.String r3 = "itd"
            com.amap.api.col.p0003sl.is.a(r5, r1, r3)     // Catch: java.lang.Throwable -> L2a
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch: java.lang.Throwable -> L35
            if (r5 == 0) goto L28
            goto L15
        L26:
            r4.b = r2     // Catch: java.lang.Throwable -> L35
        L28:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L35
            return
        L2a:
            r5 = move-exception
            android.database.sqlite.SQLiteDatabase r1 = r4.b     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L34
            r1.close()     // Catch: java.lang.Throwable -> L35
            r4.b = r2     // Catch: java.lang.Throwable -> L35
        L34:
            throw r5     // Catch: java.lang.Throwable -> L35
        L35:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L35
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.iz.b(java.lang.Object):void");
    }

    private static <T> void a(SQLiteDatabase sQLiteDatabase, T t) {
        ja b = b((Class) t.getClass());
        String a2 = a(b);
        if (TextUtils.isEmpty(a2) || t == null || sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.insert(a2, null, a(t, b));
    }

    public final <T> void a(List<T> list) {
        synchronized (this.c) {
            if (list.size() == 0) {
                return;
            }
            SQLiteDatabase b = b();
            this.b = b;
            if (b == null) {
                return;
            }
            try {
                try {
                    b.beginTransaction();
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        a(this.b, it.next());
                    }
                    this.b.setTransactionSuccessful();
                    try {
                        if (this.b.inTransaction()) {
                            this.b.endTransaction();
                        }
                    } catch (Throwable th) {
                        is.a(th, "dbs", "ild");
                    }
                    this.b.close();
                } catch (Throwable th2) {
                    is.a(th2, "dbs", "ild");
                }
            } catch (Throwable th3) {
                try {
                    is.a(th3, "dbs", "ild");
                    try {
                        if (this.b.inTransaction()) {
                            this.b.endTransaction();
                        }
                    } catch (Throwable th4) {
                        is.a(th4, "dbs", "ild");
                    }
                    this.b.close();
                } finally {
                }
            }
            this.b = null;
        }
    }

    private <T> List<T> c(String str, Class<T> cls) {
        Throwable th;
        Cursor cursor;
        synchronized (this.c) {
            ArrayList arrayList = new ArrayList();
            ja b = b((Class) cls);
            String a2 = a(b);
            if (this.b == null) {
                this.b = a();
            }
            if (this.b == null || TextUtils.isEmpty(a2) || str == null) {
                return arrayList;
            }
            try {
                try {
                    cursor = this.b.query(a2, null, str, null, null, null, null);
                } catch (Throwable th2) {
                    is.a(th2, "dbs", MapKeyNames.SLD);
                }
                try {
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        is.a(th, "dbs", MapKeyNames.SLD);
                        if (cursor != null) {
                            try {
                                cursor.close();
                            } catch (Throwable th4) {
                                is.a(th4, "dbs", MapKeyNames.SLD);
                            }
                        }
                        SQLiteDatabase sQLiteDatabase = this.b;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                            this.b = null;
                        }
                        return arrayList;
                    } finally {
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                cursor = null;
            }
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    arrayList.add(a(cursor, cls, b));
                }
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th6) {
                        is.a(th6, "dbs", MapKeyNames.SLD);
                    }
                }
                SQLiteDatabase sQLiteDatabase2 = this.b;
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                    this.b = null;
                }
                return arrayList;
            }
            this.b.close();
            this.b = null;
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Throwable th7) {
                    is.a(th7, "dbs", MapKeyNames.SLD);
                }
            }
            try {
                SQLiteDatabase sQLiteDatabase3 = this.b;
                if (sQLiteDatabase3 != null) {
                    sQLiteDatabase3.close();
                    this.b = null;
                }
            } catch (Throwable th8) {
                is.a(th8, "dbs", MapKeyNames.SLD);
            }
            return arrayList;
        }
    }

    public final <T> List<T> b(String str, Class<T> cls) {
        return c(str, cls);
    }

    private static <T> T a(Cursor cursor, Class<T> cls, ja jaVar) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] a2 = a((Class<?>) cls, jaVar.b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a2) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(jb.class);
            if (annotation != null) {
                jb jbVar = (jb) annotation;
                int b = jbVar.b();
                int columnIndex = cursor.getColumnIndex(jbVar.a());
                switch (b) {
                    case 1:
                        field.set(newInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        break;
                    case 2:
                        field.set(newInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        break;
                    case 3:
                        field.set(newInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        break;
                    case 4:
                        field.set(newInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        break;
                    case 5:
                        field.set(newInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        break;
                    case 6:
                        field.set(newInstance, cursor.getString(columnIndex));
                        break;
                    case 7:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        break;
                }
            }
        }
        return newInstance;
    }

    private static void a(Object obj, Field field, ContentValues contentValues) {
        Annotation annotation = field.getAnnotation(jb.class);
        if (annotation == null) {
        }
        jb jbVar = (jb) annotation;
        try {
            switch (jbVar.b()) {
                case 1:
                    contentValues.put(jbVar.a(), Short.valueOf(field.getShort(obj)));
                    break;
                case 2:
                    contentValues.put(jbVar.a(), Integer.valueOf(field.getInt(obj)));
                    break;
                case 3:
                    contentValues.put(jbVar.a(), Float.valueOf(field.getFloat(obj)));
                    break;
                case 4:
                    contentValues.put(jbVar.a(), Double.valueOf(field.getDouble(obj)));
                    break;
                case 5:
                    contentValues.put(jbVar.a(), Long.valueOf(field.getLong(obj)));
                    break;
                case 6:
                    contentValues.put(jbVar.a(), (String) field.get(obj));
                    break;
                case 7:
                    contentValues.put(jbVar.a(), (byte[]) field.get(obj));
                    break;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static ContentValues a(Object obj, ja jaVar) {
        ContentValues contentValues = new ContentValues();
        for (Field field : a(obj.getClass(), jaVar.b())) {
            field.setAccessible(true);
            a(obj, field, contentValues);
        }
        return contentValues;
    }

    private static Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        if (z) {
            return cls.getSuperclass().getDeclaredFields();
        }
        return cls.getDeclaredFields();
    }

    private SQLiteDatabase a() {
        try {
            if (this.b == null) {
                this.b = this.f1208a.getReadableDatabase();
            }
        } catch (Throwable th) {
            is.a(th, "dbs", "grd");
        }
        return this.b;
    }

    private SQLiteDatabase b() {
        try {
            SQLiteDatabase sQLiteDatabase = this.b;
            if (sQLiteDatabase == null || sQLiteDatabase.isReadOnly()) {
                SQLiteDatabase sQLiteDatabase2 = this.b;
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
                this.b = this.f1208a.getWritableDatabase();
            }
        } catch (Throwable th) {
            is.a(th, "dbs", "gwd");
        }
        return this.b;
    }

    private static <T> String a(ja jaVar) {
        if (jaVar == null) {
            return null;
        }
        return jaVar.a();
    }

    private static <T> ja b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(ja.class);
        if (annotation != null) {
            return (ja) annotation;
        }
        return null;
    }
}
