package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwcloudmodel.model.unite.ServiceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import defpackage.cen;
import defpackage.ctk;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class ctq {
    private static ArrayList<String> b;

    static {
        ArrayList<String> arrayList = new ArrayList<>(10);
        b = arrayList;
        arrayList.add(0, "dltId1");
        b.add(1, "dltId2");
        b.add(2, "dltId3");
        b.add(3, "dltId4");
        b.add(4, "dltId5");
        b.add(5, "dltId6");
        b.add(6, "dltId7");
        b.add(7, "dltId8");
        b.add(8, "dltId9");
        b.add(9, "dltId10");
    }

    public static ctk e(String str) {
        LogUtil.a("DataBaseApi", "getDeviceByProduct productID ", str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList<ctk> b2 = b("WiFiBindDevice", cen.d.e(), "productId=?", new String[]{str}, "_id DESC", "1");
        LogUtil.a("DataBaseApi", "getWiFiDeviceByProductId device size ", Integer.valueOf(b2.size()));
        if (koq.b(b2)) {
            LogUtil.h("DataBaseApi", "getWiFiDeviceByProductId has no wifi device");
            return null;
        }
        return b2.get(0);
    }

    public static ctk c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DataBaseApi", "getWiFiDeviceByDeviceId deviceId is null");
            return null;
        }
        ArrayList<ctk> b2 = b("WiFiBindDevice", cen.d.e(), "deviceId=?", new String[]{str}, "_id DESC", null);
        if (koq.b(b2)) {
            return null;
        }
        return b2.get(b2.size() - 1);
    }

    public static ctk h(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList<ctk> b2 = b("WiFiBindDevice", cen.d.e(), "sn=?", new String[]{str}, "_id DESC", "1");
        if (koq.b(b2)) {
            return null;
        }
        ctk ctkVar = b2.get(b2.size() - 1);
        if (ctkVar == null) {
            LogUtil.h("DataBaseApi", "get wifi device by productId fail!");
            return null;
        }
        ctkVar.setSerialNumber(str);
        return ctkVar;
    }

    public static ArrayList<ctk> e() {
        return b("WiFiBindDevice", cen.d.e(), null, null, null, null);
    }

    public static ArrayList<String> a() {
        try {
            ArrayList<ContentValues> b2 = b("WiFiBindDevice", new String[]{"deviceId"}, null, null);
            ArrayList<String> arrayList = new ArrayList<>(10);
            if (koq.b(b2)) {
                return arrayList;
            }
            Iterator<ContentValues> it = b2.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getAsString("deviceId"));
            }
            return arrayList;
        } catch (SQLiteException e) {
            LogUtil.b("DataBaseApi", "getBindWiFiProductID() Exception =", e.getMessage());
            return new ArrayList<>(0);
        }
    }

    public static long b(ctk ctkVar) {
        long j = -1;
        if (ctkVar == null) {
            return -1L;
        }
        try {
            if (ctkVar.f()) {
                return -1L;
            }
            LogUtil.a("DataBaseApi", "insert device ", ctkVar.toString());
            j = cen.b().DQ_("WiFiBindDevice", Md_(ctkVar));
            LogUtil.a("DataBaseApi", "insert count ", Long.valueOf(j));
            return j;
        } catch (SQLiteException e) {
            LogUtil.b("DataBaseApi", "insertWiFiDevice() Exception =", e.getMessage());
            return j;
        }
    }

    public static long c(ctk ctkVar) {
        if (ctkVar != null) {
            try {
                if (!TextUtils.isEmpty(ctkVar.getSerialNumber())) {
                    LogUtil.a("DataBaseApi", "update device ", ctkVar.toString());
                    return cen.b().DU_("WiFiBindDevice", Md_(ctkVar), "sn=?", new String[]{String.valueOf(ctkVar.getSerialNumber())});
                }
            } catch (SQLiteException e) {
                LogUtil.b("DataBaseApi", "updateWiFiDevice Exception =", e.getMessage());
                return -1L;
            }
        }
        LogUtil.h("DataBaseApi", "updateWiFiDevice illegal argument: device ");
        return -1L;
    }

    public static int b() {
        try {
            return cen.b().e("WiFiBindDevice", null, null);
        } catch (SQLiteException e) {
            cpw.e(false, "DataBaseApi", "deleteWiFiDevice() Exception =", e.getMessage());
            return -1;
        }
    }

    public static long b(String str) {
        int i = -1;
        try {
        } catch (SQLiteException e) {
            LogUtil.b("DataBaseApi", "deleteWiFiDeviceBySerialNumber Exception =", e.getMessage());
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DataBaseApi", "deleteWiFiDeviceBySerialNumber serialNumber = null");
            return -1;
        }
        LogUtil.a("DataBaseApi", "deleteWiFiDeviceBySerialNumber enter serialNumber ", cpw.d(str));
        i = cen.b().e("WiFiBindDevice", "sn=?", new String[]{str});
        LogUtil.a("DataBaseApi", "deleteWiFiDeviceBySerialNumber count = ", Integer.valueOf(i));
        return i;
    }

    /* JADX WARN: Not initialized variable reg: 6, insn: 0x00a9: MOVE (r5 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:39:0x00a9 */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String i(java.lang.String r17) {
        /*
            java.lang.String r0 = "productId"
            java.lang.String r1 = "DataBaseApi"
            r2 = 2
            r3 = 0
            r4 = 1
            r5 = 0
            boolean r6 = android.text.TextUtils.isEmpty(r17)     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            if (r6 == 0) goto L18
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            java.lang.String r6 = "getDeviceProductId deiviceId is null"
            r0[r3] = r6     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            return r5
        L18:
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            java.lang.String r7 = "getDeviceProductId deiviceId "
            r6[r3] = r7     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            java.lang.String r7 = defpackage.cpw.d(r17)     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            r6[r4] = r7     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            java.lang.String[] r10 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            r10[r3] = r0     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            java.lang.String[] r12 = new java.lang.String[]{r17}     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            cen r8 = defpackage.cen.b()     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            java.lang.String r9 = "WiFiBindDevice"
            java.lang.String r11 = "deviceId=?"
            r13 = 0
            r14 = 0
            java.lang.String r15 = "_id DESC"
            java.lang.String r16 = "1"
            android.database.Cursor r6 = r8.DT_(r9, r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> L8f android.database.sqlite.SQLiteException -> L91
            if (r6 != 0) goto L52
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            java.lang.String r7 = "getDeviceProductId cursor is null"
            r0[r3] = r7     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            if (r6 == 0) goto L51
            r6.close()
        L51:
            return r5
        L52:
            int r7 = r6.getCount()     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            if (r7 != 0) goto L67
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            java.lang.String r7 = "getDeviceProductId cursor size is 0"
            r0[r3] = r7     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            if (r6 == 0) goto L66
            r6.close()
        L66:
            return r5
        L67:
            r7 = r5
        L68:
            boolean r8 = r6.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            if (r8 == 0) goto L7c
            int r7 = r6.getColumnIndex(r0)     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            java.lang.String r7 = r6.getString(r7)     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            if (r8 != 0) goto L68
        L7c:
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            java.lang.String r8 = "getDeviceProductId product ,"
            r0[r3] = r8     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            r0[r4] = r7     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)     // Catch: android.database.sqlite.SQLiteException -> L8d java.lang.Throwable -> La8
            if (r6 == 0) goto L8c
            r6.close()
        L8c:
            return r7
        L8d:
            r0 = move-exception
            goto L93
        L8f:
            r0 = move-exception
            goto Laa
        L91:
            r0 = move-exception
            r6 = r5
        L93:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> La8
            java.lang.String r7 = "getWiFiDeviceProductId() Exception ="
            r2[r3] = r7     // Catch: java.lang.Throwable -> La8
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> La8
            r2[r4] = r0     // Catch: java.lang.Throwable -> La8
            com.huawei.hwlogsmodel.LogUtil.b(r1, r2)     // Catch: java.lang.Throwable -> La8
            if (r6 == 0) goto La7
            r6.close()
        La7:
            return r5
        La8:
            r0 = move-exception
            r5 = r6
        Laa:
            if (r5 == 0) goto Laf
            r5.close()
        Laf:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ctq.i(java.lang.String):java.lang.String");
    }

    public static crw a(String str) {
        Cursor cursor;
        SQLException e;
        crw crwVar;
        Cursor cursor2 = null;
        r1 = null;
        r1 = null;
        crw crwVar2 = null;
        cursor2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        LogUtil.a("DataBaseApi", "getDeviceByDeviceID deviceID ", cpw.d(str));
        try {
            try {
                cursor = cen.b().DT_("WiFiDeviceSubUser", cen.c.c(), "deviceId= ?", new String[]{str}, null, null, null, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToNext()) {
                            crwVar2 = Mf_(cursor);
                            LogUtil.a("DataBaseApi", crwVar2.toString());
                        }
                    } catch (SQLException e2) {
                        e = e2;
                        crwVar = crwVar2;
                        cursor2 = cursor;
                        LogUtil.b("DataBaseApi", "getSubUser catch sql exception ", e.getMessage());
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return crwVar;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursor == null) {
                    return crwVar2;
                }
                cursor.close();
                return crwVar2;
            } catch (SQLException e3) {
                e = e3;
                crwVar = null;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = cursor2;
        }
    }

    private static long a(crw crwVar) {
        if (crwVar == null) {
            LogUtil.a("DataBaseApi", "insert userInfo is null ");
            return -1L;
        }
        LogUtil.a("DataBaseApi", "insert SubUser ", crwVar.toString());
        long DQ_ = cen.b().DQ_("WiFiDeviceSubUser", Mc_(crwVar));
        LogUtil.a("DataBaseApi", "insert count ", Long.valueOf(DQ_));
        return DQ_;
    }

    public static long c(crw crwVar) {
        if (crwVar == null || crwVar.d()) {
            LogUtil.a("DataBaseApi", "setSubUser userInfo is null");
            return -1L;
        }
        if (a(crwVar.c()) == null) {
            return a(b(crwVar));
        }
        return e(b(crwVar));
    }

    private static crw b(crw crwVar) {
        ArrayList<crx> b2 = crwVar.b();
        if (b2 != null && b2.size() > 0) {
            Iterator<crx> it = b2.iterator();
            while (it.hasNext()) {
                crx next = it.next();
                if (TextUtils.isEmpty(next.b())) {
                    String d = d(b2);
                    if (!TextUtils.isEmpty(d)) {
                        next.c(d);
                    } else {
                        LogUtil.h("DataBaseApi", "setSubUserSid() sid is error:", " huid:", cpw.d(next.e()));
                    }
                }
            }
            crwVar.d(b2);
        }
        return crwVar;
    }

    public static long b(String str, crx crxVar) {
        if (TextUtils.isEmpty(str) || crxVar == null) {
            return -1L;
        }
        crw a2 = a(str);
        if (a2 == null) {
            return 0L;
        }
        ArrayList<crx> b2 = a2.b();
        if (b2 == null || b2.size() <= 0) {
            return -1L;
        }
        int i = 0;
        while (true) {
            if (i >= b2.size()) {
                break;
            }
            if (crxVar.e().equals(b2.get(i).e())) {
                b2.remove(i);
                break;
            }
            i++;
        }
        if (b2.size() > 0) {
            a2.d(b2);
            return e(a2);
        }
        return d(str);
    }

    public static long d(String str, crx crxVar) {
        ArrayList<crx> b2;
        int i = 0;
        if (TextUtils.isEmpty(str) || crxVar == null) {
            cpw.d(false, "DataBaseApi", "updateSubUser deviceid is null or user is null");
            return -1L;
        }
        crw a2 = a(str);
        if (a2 == null || (b2 = a2.b()) == null || b2.size() <= 0) {
            return -1L;
        }
        while (true) {
            if (i >= b2.size()) {
                break;
            }
            if (crxVar.e().equals(b2.get(i).e())) {
                crxVar.c(b2.get(i).b());
                b2.set(i, crxVar);
                break;
            }
            i++;
        }
        a2.d(b2);
        return e(a2);
    }

    public static long a(String str, crx crxVar) {
        if (TextUtils.isEmpty(str) || crxVar.h()) {
            cpw.a(false, "DataBaseApi", "updateSubUser DeviceUserInfo is null");
            return -1L;
        }
        crw a2 = a(str);
        if (a2 == null) {
            crw crwVar = new crw();
            crwVar.d(str);
            return a(d(crwVar, crxVar));
        }
        return e(d(a2, crxVar));
    }

    private static crw d(crw crwVar, crx crxVar) {
        ArrayList<crx> b2 = crwVar.b();
        if (TextUtils.isEmpty(crxVar.b())) {
            String d = d(b2);
            cpw.d(false, "DataBaseApi", "updataUser sid ", d);
            if (TextUtils.isEmpty(d)) {
                cpw.d(false, "DataBaseApi", "updataUser sid is error huid:", cpw.d(crxVar.e()));
            } else {
                crxVar.c(d);
            }
        }
        crwVar.e(crxVar);
        return crwVar;
    }

    private static String d(ArrayList<crx> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return b.get(0);
        }
        LogUtil.a("DataBaseApi", "getSid users ", arrayList.toString());
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            String next = it.next();
            Iterator<crx> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (next.equals(it2.next().b())) {
                    break;
                }
            }
            return next;
        }
        LogUtil.a("DataBaseApi", "getSid return null ");
        return null;
    }

    private static long e(crw crwVar) {
        if (crwVar == null) {
            LogUtil.h("DataBaseApi", "update userInfo is null ");
            return -1L;
        }
        LogUtil.a("DataBaseApi", "update SubUser ", crwVar.toString());
        long DU_ = cen.b().DU_("WiFiDeviceSubUser", Mc_(crwVar), "deviceId=?", new String[]{String.valueOf(crwVar.c())});
        LogUtil.a("DataBaseApi", "update count ", Long.valueOf(DU_));
        return DU_;
    }

    public static int d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DataBaseApi", "deleteByDeviceID() wifiDeviceID = null");
            return -1;
        }
        LogUtil.a("DataBaseApi", "deleteByDeviceID() enter wifiDeviceID ", cpw.d(str));
        int e = cen.b().e("WiFiDeviceSubUser", "deviceId=?", new String[]{str});
        LogUtil.a("DataBaseApi", "deleteByDeviceID() count = ", Integer.valueOf(e));
        return e;
    }

    public static int c() {
        LogUtil.a("DataBaseApi", "deleteAllSubUser enter");
        int e = cen.b().e("WiFiDeviceSubUser", null, null);
        LogUtil.a("DataBaseApi", "deleteAllSubUser count = ", Integer.valueOf(e));
        return e;
    }

    public static ArrayList<String> d() {
        return a(1);
    }

    public static ArrayList<String> c(boolean z) {
        return d(1, z);
    }

    public static ArrayList<String> j() {
        return a(2);
    }

    private static String e(ArrayList<crx> arrayList) {
        return arrayList != null ? new Gson().toJson(arrayList, new TypeToken<ArrayList<crx>>() { // from class: ctq.4
        }.getType()) : "";
    }

    private static ArrayList<crx> f(String str) {
        if (!TextUtils.isEmpty(str)) {
            return (ArrayList) new Gson().fromJson(CommonUtil.z(str), new TypeToken<ArrayList<crx>>() { // from class: ctq.2
            }.getType());
        }
        return new ArrayList<>(10);
    }

    private static crw Mf_(Cursor cursor) {
        crw crwVar = new crw();
        crwVar.d(cursor.getString(cursor.getColumnIndex("deviceId")));
        crwVar.d(f(cursor.getString(cursor.getColumnIndex("subUser"))));
        LogUtil.a("DataBaseApi", "getWiFiDeviceUser SubUser ", crwVar.toString());
        return crwVar;
    }

    private static ContentValues Mc_(crw crwVar) {
        LogUtil.a("DataBaseApi", "getContentValues deviceid ", cpw.d(crwVar.c()));
        ContentValues contentValues = new ContentValues();
        contentValues.put("deviceId", crwVar.c());
        if (crwVar.b() != null && crwVar.b().size() > 0) {
            contentValues.put("subUser", e(crwVar.b()));
        }
        return contentValues;
    }

    private static ContentValues Md_(ctk ctkVar) {
        LogUtil.a("DataBaseApi", "getContentValues deviceid ", cpw.d(ctkVar.b().a()));
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", ctkVar.getProductId());
        contentValues.put("deviceId", ctkVar.b().a());
        contentValues.put("deviceCode", String.valueOf(ctkVar.b().d()));
        contentValues.put("sn", ctkVar.b().m());
        contentValues.put("model", ctkVar.b().g());
        contentValues.put("deviceType", ctkVar.b().c());
        contentValues.put(ProfileRequestConstants.MANU, ctkVar.b().h());
        contentValues.put("prodId", ctkVar.b().f());
        contentValues.put(ProfileRequestConstants.HIV, ctkVar.b().b());
        contentValues.put(ProfileRequestConstants.FWV, ctkVar.b().e());
        contentValues.put(ProfileRequestConstants.HWV, ctkVar.b().i());
        contentValues.put(ProfileRequestConstants.SWV, ctkVar.b().o());
        contentValues.put("mac", ctkVar.b().j());
        contentValues.put(ProfileRequestConstants.PROT_TYPE, Integer.valueOf(ctkVar.b().n()));
        contentValues.put("source", Integer.valueOf(ctkVar.b().k()));
        LogUtil.a("DataBaseApi", "getContentValues source ", Integer.valueOf(ctkVar.b().k()));
        if (ctkVar.c() != null && ctkVar.c().size() > 0) {
            contentValues.put("serviceInfo", e(ctkVar.c()));
        }
        return contentValues;
    }

    private static String e(List<ctk.d> list) {
        return list != null ? new Gson().toJson(list, new TypeToken<List<ServiceInfo>>() { // from class: ctq.3
        }.getType()) : "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0023, code lost:
    
        if (r11.isClosed() == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0049, code lost:
    
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        if (r11.isClosed() == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.ArrayList<android.content.ContentValues> b(java.lang.String r11, java.lang.String[] r12, java.lang.String r13, java.lang.String[] r14) {
        /*
            if (r12 == 0) goto L4
            r0 = r12
            goto L8
        L4:
            java.lang.String[] r0 = g(r11)
        L8:
            r1 = 0
            cen r2 = defpackage.cen.b()     // Catch: java.lang.Throwable -> L28 android.database.SQLException -> L2a
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            android.database.Cursor r11 = r2.DT_(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L28 android.database.SQLException -> L2a
            java.util.ArrayList r1 = Mb_(r11, r0)     // Catch: android.database.SQLException -> L26 java.lang.Throwable -> L4d
            if (r11 == 0) goto L4c
            boolean r12 = r11.isClosed()
            if (r12 != 0) goto L4c
            goto L49
        L26:
            r12 = move-exception
            goto L2d
        L28:
            r11 = move-exception
            goto L50
        L2a:
            r11 = move-exception
            r12 = r11
            r11 = r1
        L2d:
            r13 = 2
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch: java.lang.Throwable -> L4d
            java.lang.String r14 = "queryRawData throw SQLException "
            r0 = 0
            r13[r0] = r14     // Catch: java.lang.Throwable -> L4d
            java.lang.String r12 = r12.getMessage()     // Catch: java.lang.Throwable -> L4d
            r14 = 1
            r13[r14] = r12     // Catch: java.lang.Throwable -> L4d
            java.lang.String r12 = "DataBaseApi"
            com.huawei.hwlogsmodel.LogUtil.h(r12, r13)     // Catch: java.lang.Throwable -> L4d
            if (r11 == 0) goto L4c
            boolean r12 = r11.isClosed()
            if (r12 != 0) goto L4c
        L49:
            r11.close()
        L4c:
            return r1
        L4d:
            r12 = move-exception
            r1 = r11
            r11 = r12
        L50:
            if (r1 == 0) goto L5b
            boolean r12 = r1.isClosed()
            if (r12 != 0) goto L5b
            r1.close()
        L5b:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ctq.b(java.lang.String, java.lang.String[], java.lang.String, java.lang.String[]):java.util.ArrayList");
    }

    private static String[] g(String str) {
        if ("WiFiBindDevice".equals(str)) {
            return cen.d.e();
        }
        if ("WiFiDeviceList".equals(str)) {
            return cen.a.a();
        }
        if ("WiFiDeviceSubUser".equals(str)) {
            return cen.c.c();
        }
        LogUtil.h("DataBaseApi", "getTableAllColumns unknow table: ", str);
        return new String[0];
    }

    private static ArrayList<ContentValues> Mb_(Cursor cursor, String[] strArr) {
        ArrayList<ContentValues> arrayList = new ArrayList<>(10);
        if (cursor == null || cursor.isClosed() || strArr == null) {
            LogUtil.h("DataBaseApi", "getColumnsFromCursor illegal value ");
            return arrayList;
        }
        StringBuffer stringBuffer = new StringBuffer(10);
        while (cursor.moveToNext()) {
            ContentValues contentValues = new ContentValues();
            boolean z = true;
            for (String str : strArr) {
                if (cursor.getColumnIndex(str) == -1) {
                    stringBuffer.append("missing " + str + ";");
                } else {
                    contentValues.put(str, cursor.getString(cursor.getColumnIndex(str)));
                    z = false;
                }
            }
            if (!z) {
                arrayList.add(contentValues);
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        if (!TextUtils.isEmpty(stringBuffer2)) {
            LogUtil.h("DataBaseApi", "getColumnsFromCursor some columns missing:", stringBuffer2);
        }
        return arrayList;
    }

    private static ctk Me_(ContentValues contentValues) {
        ctk ctkVar = new ctk();
        ctkVar.setProductId(contentValues.getAsString("productId"));
        ctkVar.b().b(contentValues.getAsString("deviceId"));
        ctkVar.b().e(CommonUtil.g(contentValues.getAsString("deviceCode")));
        ctkVar.b().h(contentValues.getAsString("sn"));
        ctkVar.b().g(contentValues.getAsString("model"));
        ctkVar.b().e(contentValues.getAsString("deviceType"));
        ctkVar.b().f(contentValues.getAsString(ProfileRequestConstants.MANU));
        ctkVar.b().j(contentValues.getAsString("prodId"));
        ctkVar.b().a(contentValues.getAsString(ProfileRequestConstants.HIV));
        ctkVar.b().c(contentValues.getAsString(ProfileRequestConstants.FWV));
        ctkVar.b().d(contentValues.getAsString(ProfileRequestConstants.HWV));
        ctkVar.b().l(contentValues.getAsString(ProfileRequestConstants.SWV));
        ctkVar.b().i(contentValues.getAsString("mac"));
        ctkVar.b().e(CommonUtil.h(contentValues.getAsString(ProfileRequestConstants.PROT_TYPE)));
        ctkVar.b().d(CommonUtil.h(contentValues.getAsString("source")));
        ctkVar.a(j(contentValues.getAsString("serviceInfo")));
        LogUtil.a("DataBaseApi", "getWiFiDevice device ", ctkVar.toString());
        return ctkVar;
    }

    private static List<ctk.d> j(String str) {
        if (!TextUtils.isEmpty(str)) {
            return (List) new Gson().fromJson(CommonUtil.z(str), new TypeToken<List<ctk.d>>() { // from class: ctq.5
            }.getType());
        }
        return new ArrayList(10);
    }

    private static ArrayList<String> a(int i) {
        Cursor DT_ = cen.b().DT_("WiFiBindDevice", cen.d.e(), "source = ?", new String[]{String.valueOf(i)}, null, null, null, null);
        ArrayList<String> arrayList = new ArrayList<>(16);
        if (DT_ == null) {
            LogUtil.h("DataBaseApi", " getAllCurrentUserDeviceID cursor is null");
            return arrayList;
        }
        if (DT_.getCount() <= 0) {
            LogUtil.h("DataBaseApi", " getAllCurrentUserDeviceID cursor size is 0");
            return arrayList;
        }
        while (DT_.moveToNext()) {
            try {
                try {
                    arrayList.add(DT_.getString(DT_.getColumnIndex("deviceId")));
                } catch (SQLException e) {
                    LogUtil.b("DataBaseApi", "getDeviceIdBySource catch sql exception ", e.getMessage());
                }
            } finally {
                DT_.close();
            }
        }
        DT_.close();
        LogUtil.a("DataBaseApi", " getAllCurrentUserDeviceID list ", arrayList.toString());
        return arrayList;
    }

    private static ArrayList<String> d(int i, boolean z) {
        Cursor DT_ = cen.b().DT_("WiFiBindDevice", cen.d.e(), "source = ?", new String[]{String.valueOf(i)}, null, null, null, null);
        ArrayList<String> arrayList = new ArrayList<>(16);
        if (DT_ == null) {
            LogUtil.h("DataBaseApi", " getAllCurrentUserDeviceID cursorQuery is null");
            return arrayList;
        }
        if (DT_.getCount() <= 0) {
            LogUtil.h("DataBaseApi", " getAllCurrentUserDeviceID cursorQuery size is 0");
            return arrayList;
        }
        while (DT_.moveToNext()) {
            try {
                try {
                    String string = DT_.getString(DT_.getColumnIndex("deviceId"));
                    ctk c = c(string);
                    if (z) {
                        if (c == null || c.b() == null || c.b().k() == 1) {
                            LogUtil.h("DataBaseApi", "getDeviceIdBySource deviceIdList not add : ", cpw.d(string));
                        } else {
                            arrayList.add(string);
                        }
                    } else {
                        arrayList.add(string);
                    }
                } catch (SQLException e) {
                    LogUtil.b("DataBaseApi", "getDeviceIdBySource isHagridDevice catch sql exception ", e.getMessage());
                }
            } finally {
                DT_.close();
            }
        }
        DT_.close();
        LogUtil.a("DataBaseApi", " getAllCurrentUserDeviceID isHagridDevice list ", arrayList.toString());
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.ArrayList<defpackage.ctk> b(java.lang.String r12, java.lang.String[] r13, java.lang.String r14, java.lang.String[] r15, java.lang.String r16, java.lang.String r17) {
        /*
            r1 = 0
            cen r2 = defpackage.cen.b()     // Catch: java.lang.Throwable -> L2a android.database.sqlite.SQLiteException -> L2c
            r7 = 0
            r8 = 0
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r9 = r16
            r10 = r17
            android.database.Cursor r2 = r2.DT_(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L2a android.database.sqlite.SQLiteException -> L2c
            r0 = r13
            java.util.ArrayList r1 = Mb_(r2, r13)     // Catch: java.lang.Throwable -> L23 android.database.sqlite.SQLiteException -> L25
            if (r2 == 0) goto L1d
            r2.close()     // Catch: java.lang.Throwable -> L23 android.database.sqlite.SQLiteException -> L25
        L1d:
            if (r2 == 0) goto L48
            r2.close()
            goto L48
        L23:
            r0 = move-exception
            goto L6d
        L25:
            r0 = move-exception
            r11 = r2
            r2 = r1
            r1 = r11
            goto L2e
        L2a:
            r0 = move-exception
            goto L6c
        L2c:
            r0 = move-exception
            r2 = r1
        L2e:
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L2a
            java.lang.String r4 = "getWiFiDeviceForDeviceID() Exception ="
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L2a
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L2a
            r4 = 1
            r3[r4] = r0     // Catch: java.lang.Throwable -> L2a
            java.lang.String r0 = "DataBaseApi"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)     // Catch: java.lang.Throwable -> L2a
            if (r1 == 0) goto L47
            r1.close()
        L47:
            r1 = r2
        L48:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r2 = defpackage.koq.b(r1)
            if (r2 != 0) goto L6b
            java.util.Iterator r1 = r1.iterator()
        L57:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L6b
            java.lang.Object r2 = r1.next()
            android.content.ContentValues r2 = (android.content.ContentValues) r2
            ctk r2 = Me_(r2)
            r0.add(r2)
            goto L57
        L6b:
            return r0
        L6c:
            r2 = r1
        L6d:
            if (r2 == 0) goto L72
            r2.close()
        L72:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ctq.b(java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String):java.util.ArrayList");
    }
}
