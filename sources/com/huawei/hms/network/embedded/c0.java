package com.huawei.hms.network.embedded;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkKitSQLiteOpenHelper;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class c0 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5196a = "FileCacheManager";
    public static final String b = "restclient_dns.db";
    public static final String c = "restclient_dynamic_dns.db";
    public static String d = "restclient_dns.db";
    public static final String e = "createTime";
    public static final String f = "dns_domain";
    public static final String g = "domain";
    public static final String i = "ttl";
    public static final String j = "source";
    public static volatile SQLiteDatabase l;
    public static final String h = "ip";
    public static final String[] k = {"domain", h, "ttl", "source", "createTime"};

    public static boolean e() {
        Logger.v(f5196a, "file cache removeAll operation");
        try {
            d().execSQL("delete from dns_domain");
            SQLiteDatabase networkkitUnusedDbDB = NetworkKitSQLiteOpenHelper.getInstance().getNetworkkitUnusedDbDB();
            if (networkkitUnusedDbDB != null) {
                networkkitUnusedDbDB.execSQL("delete from dns_domain");
            }
            return true;
        } catch (Exception e2) {
            Logger.i(f5196a, "remove exception:" + e2.getClass().getSimpleName());
            return false;
        }
    }

    public static SQLiteDatabase d() throws SQLiteException {
        if (t.m().b() == null) {
            throw new SQLiteException("Can't acce ss database");
        }
        synchronized (c0.class) {
            if (l == null) {
                l = NetworkKitSQLiteOpenHelper.getInstance().getNetworkKitWritableDatabase();
                if (l != null) {
                    l.execSQL("Create table if not exists dns_domain( _id INTEGER PRIMARY KEY AUTOINCREMENT, domain TEXT UNIQUE, ip TEXT, ttl TEXT, source INTEGER, createTime INTEGER);");
                    a();
                }
            }
        }
        if (l != null) {
            return l;
        }
        throw new SQLiteException("Can't access database");
    }

    public static HashMap<String, m0> c() {
        return a(NetworkKitSQLiteOpenHelper.getInstance().getDbByName(d));
    }

    public static boolean b(String str, m0 m0Var) {
        if (TextUtils.isEmpty(str)) {
            Logger.w(f5196a, "invalid parameter: domain is null");
            return false;
        }
        Logger.v(f5196a, "insertOrUpdateAddress: %s", str);
        b0 a2 = a(str, m0Var);
        if (a2 == null) {
            Logger.w(f5196a, "convert address failed, domain:", str);
            return false;
        }
        try {
            SQLiteDatabase d2 = d();
            d2.delete(f, "domain=?", new String[]{str});
            return a(d2, a2);
        } catch (Exception e2) {
            Logger.w(f5196a, "insert exception:" + e2.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.w(f5196a, "invalid parameter: domain is null");
            return false;
        }
        Logger.v(f5196a, "file cache remove operation:" + str);
        try {
            return d().delete(f, "domain=?", new String[]{str}) >= 0;
        } catch (Exception e2) {
            Logger.i(f5196a, "remove exception:" + e2.getClass().getSimpleName());
            return false;
        }
    }

    public static HashMap<String, m0> b() {
        return a(d());
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, b0 b0Var) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("domain", b0Var.f5167a);
        contentValues.put(h, b0Var.b);
        contentValues.put("ttl", Long.valueOf(b0Var.c));
        contentValues.put("source", Integer.valueOf(b0Var.e));
        contentValues.put("createTime", Long.valueOf(b0Var.d));
        return sQLiteDatabase.replace(f, "", contentValues) > 0;
    }

    public static void a() {
        if (l != null) {
            String replace = l.getPath().replace(NetworkKitSQLiteOpenHelper.getInstance().getDbName(), d);
            if (new File(replace).exists()) {
                HashMap<String, m0> c2 = c();
                if (!c2.isEmpty()) {
                    for (Map.Entry<String, m0> entry : c2.entrySet()) {
                        b(entry.getKey(), entry.getValue());
                    }
                }
                NetworkKitSQLiteOpenHelper.getInstance().deleteDbByName(d);
                NetworkKitSQLiteOpenHelper.getInstance().deleteDbFileByPath(replace);
                NetworkKitSQLiteOpenHelper.getInstance().deleteDbFileByPath(replace + NetworkKitSQLiteOpenHelper.getInstance().getDbNameSuffix());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x00bb, code lost:
    
        if (r4.a() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x00bd, code lost:
    
        b((java.lang.String) r0.getKey());
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00d0, code lost:
    
        if (com.huawei.hms.network.embedded.y.b(r4) != false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00d2, code lost:
    
        r1.put(r0.getKey(), r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00c8, code lost:
    
        r4 = a(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00da, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0082, code lost:
    
        if (r12 == null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x008f, code lost:
    
        if (r12 != null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0091, code lost:
    
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0094, code lost:
    
        r12 = r0.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00a0, code lost:
    
        if (r12.hasNext() == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00a2, code lost:
    
        r0 = (java.util.Map.Entry) r12.next();
        r4 = (com.huawei.hms.network.embedded.b0) r0.getValue();
        com.huawei.hms.framework.common.Logger.v(com.huawei.hms.network.embedded.c0.f5196a, "Address: %s", r4);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.HashMap<java.lang.String, com.huawei.hms.network.embedded.m0> a(android.database.sqlite.SQLiteDatabase r12) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.lang.String r2 = "FileCacheManager"
            r3 = 0
            if (r12 == 0) goto L8e
            java.lang.String r5 = "dns_domain"
            java.lang.String[] r6 = com.huawei.hms.network.embedded.c0.k     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r4 = r12
            android.database.Cursor r12 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            int r4 = r12.getCount()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            if (r4 <= 0) goto L8f
        L23:
            boolean r4 = r12.moveToNext()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            if (r4 == 0) goto L8f
            com.huawei.hms.network.embedded.b0 r4 = new com.huawei.hms.network.embedded.b0     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r4.<init>()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r5 = 0
            java.lang.String r5 = r12.getString(r5)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r4.f5167a = r5     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r5 = 1
            java.lang.String r5 = r12.getString(r5)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r4.b = r5     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r5 = 2
            long r5 = r12.getLong(r5)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r4.c = r5     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r5 = 3
            int r5 = r12.getInt(r5)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r4.e = r5     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r5 = 4
            long r5 = r12.getLong(r5)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r4.d = r5     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            java.lang.String r5 = r4.f5167a     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            if (r5 != 0) goto L23
            java.lang.String r5 = r4.f5167a     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            r0.put(r5, r4)     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L85
            goto L23
        L5f:
            r4 = move-exception
            goto L66
        L61:
            r12 = move-exception
            goto L88
        L63:
            r12 = move-exception
            r4 = r12
            r12 = r3
        L66:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L85
            r5.<init>()     // Catch: java.lang.Throwable -> L85
            java.lang.String r6 = "getAll exception:"
            r5.append(r6)     // Catch: java.lang.Throwable -> L85
            java.lang.Class r4 = r4.getClass()     // Catch: java.lang.Throwable -> L85
            java.lang.String r4 = r4.getSimpleName()     // Catch: java.lang.Throwable -> L85
            r5.append(r4)     // Catch: java.lang.Throwable -> L85
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> L85
            com.huawei.hms.framework.common.Logger.w(r2, r4)     // Catch: java.lang.Throwable -> L85
            if (r12 == 0) goto L94
            goto L91
        L85:
            r0 = move-exception
            r3 = r12
            r12 = r0
        L88:
            if (r3 == 0) goto L8d
            r3.close()
        L8d:
            throw r12
        L8e:
            r12 = r3
        L8f:
            if (r12 == 0) goto L94
        L91:
            r12.close()
        L94:
            java.util.Set r12 = r0.entrySet()
            java.util.Iterator r12 = r12.iterator()
        L9c:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto Lda
            java.lang.Object r0 = r12.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r4 = r0.getValue()
            com.huawei.hms.network.embedded.b0 r4 = (com.huawei.hms.network.embedded.b0) r4
            java.lang.Object[] r5 = new java.lang.Object[]{r4}
            java.lang.String r6 = "Address: %s"
            com.huawei.hms.framework.common.Logger.v(r2, r6, r5)
            boolean r5 = r4.a()
            if (r5 == 0) goto Lc8
            java.lang.Object r4 = r0.getKey()
            java.lang.String r4 = (java.lang.String) r4
            b(r4)
            r4 = r3
            goto Lcc
        Lc8:
            com.huawei.hms.network.embedded.m0 r4 = a(r4)
        Lcc:
            boolean r5 = com.huawei.hms.network.embedded.y.b(r4)
            if (r5 != 0) goto L9c
            java.lang.Object r0 = r0.getKey()
            r1.put(r0, r4)
            goto L9c
        Lda:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.c0.a(android.database.sqlite.SQLiteDatabase):java.util.HashMap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0066, code lost:
    
        if (r3 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008d, code lost:
    
        r1 = r1.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0095, code lost:
    
        if (r1.hasNext() == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0097, code lost:
    
        r2 = (com.huawei.hms.network.embedded.b0) r1.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a1, code lost:
    
        if (r2.a() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a7, code lost:
    
        r0 = a(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a3, code lost:
    
        b(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ac, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008a, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0088, code lost:
    
        if (r3 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.network.embedded.m0 a(java.lang.String r12) {
        /*
            com.huawei.hms.network.embedded.m0 r0 = new com.huawei.hms.network.embedded.m0
            r0.<init>()
            boolean r1 = android.text.TextUtils.isEmpty(r12)
            java.lang.String r2 = "FileCacheManager"
            if (r1 == 0) goto L13
            java.lang.String r12 = "invalid parameter: domain is null"
            com.huawei.hms.framework.common.Logger.w(r2, r12)
            return r0
        L13:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = d()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            java.lang.String[] r6 = com.huawei.hms.network.embedded.c0.k     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            java.lang.String[] r8 = new java.lang.String[]{r12}     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            java.lang.String r5 = "dns_domain"
            java.lang.String r7 = "domain=?"
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            int r4 = r3.getCount()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            if (r4 <= 0) goto L66
        L34:
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            if (r4 == 0) goto L66
            com.huawei.hms.network.embedded.b0 r4 = new com.huawei.hms.network.embedded.b0     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r4.<init>()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r5 = 0
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r4.f5167a = r5     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r5 = 1
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r4.b = r5     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r5 = 2
            long r5 = r3.getLong(r5)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r4.c = r5     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r5 = 3
            int r5 = r3.getInt(r5)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r4.e = r5     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r5 = 4
            long r5 = r3.getLong(r5)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r4.d = r5     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r1.add(r4)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            goto L34
        L66:
            if (r3 == 0) goto L8d
            goto L8a
        L69:
            r12 = move-exception
            goto Lad
        L6b:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L69
            r5.<init>()     // Catch: java.lang.Throwable -> L69
            java.lang.String r6 = "query exception:"
            r5.append(r6)     // Catch: java.lang.Throwable -> L69
            java.lang.Class r4 = r4.getClass()     // Catch: java.lang.Throwable -> L69
            java.lang.String r4 = r4.getSimpleName()     // Catch: java.lang.Throwable -> L69
            r5.append(r4)     // Catch: java.lang.Throwable -> L69
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> L69
            com.huawei.hms.framework.common.Logger.w(r2, r4)     // Catch: java.lang.Throwable -> L69
            if (r3 == 0) goto L8d
        L8a:
            r3.close()
        L8d:
            java.util.Iterator r1 = r1.iterator()
        L91:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto Lac
            java.lang.Object r2 = r1.next()
            com.huawei.hms.network.embedded.b0 r2 = (com.huawei.hms.network.embedded.b0) r2
            boolean r3 = r2.a()
            if (r3 == 0) goto La7
            b(r12)
            goto L91
        La7:
            com.huawei.hms.network.embedded.m0 r0 = a(r2)
            goto L91
        Lac:
            return r0
        Lad:
            if (r3 == 0) goto Lb2
            r3.close()
        Lb2:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.c0.a(java.lang.String):com.huawei.hms.network.embedded.m0");
    }

    public static m0 a(b0 b0Var) {
        m0 m0Var = new m0();
        try {
            JSONArray jSONArray = new JSONObject(b0Var.b).getJSONArray("address");
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                m0Var.a(jSONArray.getString(i2));
            }
        } catch (JSONException unused) {
            Logger.w(f5196a, "ConvertAddress Failed:" + b0Var.toString());
        }
        m0Var.b(b0Var.c);
        m0Var.a(b0Var.d);
        m0Var.b(b0Var.e);
        m0Var.a(1);
        y.c(m0Var);
        return m0Var;
    }

    public static b0 a(String str, m0 m0Var) {
        b0 b0Var = null;
        if (y.b(m0Var)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = m0Var.d().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put("address", jSONArray);
            b0 b0Var2 = new b0();
            try {
                b0Var2.c = m0Var.h();
                b0Var2.e = m0Var.f();
                b0Var2.f5167a = str;
                b0Var2.b = jSONObject.toString();
                b0Var2.d = m0Var.b();
                return b0Var2;
            } catch (JSONException unused) {
                b0Var = b0Var2;
                Logger.w(f5196a, "ConvertAddress Failed:" + m0Var.toString());
                return b0Var;
            }
        } catch (JSONException unused2) {
        }
    }

    static {
        if (ContextHolder.getKitContext() != null) {
            d = c;
        }
    }
}
