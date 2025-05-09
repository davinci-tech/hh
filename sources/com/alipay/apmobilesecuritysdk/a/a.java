package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.e.c;
import com.alipay.apmobilesecuritysdk.e.d;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.huawei.openalliance.ad.constant.OsType;
import defpackage.jd;
import defpackage.js;
import defpackage.jt;
import defpackage.mq;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* loaded from: classes7.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public Context f837a;
    public com.alipay.apmobilesecuritysdk.b.a b = com.alipay.apmobilesecuritysdk.b.a.a();
    public int c = 4;

    /* JADX WARN: Code restructure failed: missing block: B:82:0x00ba, code lost:
    
        if (defpackage.mq.e(b(r9.f837a)) != false) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01ff A[Catch: Exception -> 0x023b, TryCatch #0 {Exception -> 0x023b, blocks: (B:3:0x0007, B:5:0x0038, B:8:0x0041, B:12:0x00bf, B:15:0x01e5, B:17:0x01ff, B:19:0x0207, B:21:0x020d, B:23:0x0213, B:25:0x0219, B:31:0x00d7, B:33:0x00ef, B:38:0x00fc, B:39:0x010f, B:44:0x0124, B:46:0x0174, B:48:0x017e, B:49:0x0186, B:51:0x0193, B:53:0x019d, B:54:0x01a5, B:55:0x01a1, B:56:0x0182, B:58:0x0056, B:60:0x0064, B:63:0x006f, B:65:0x0075, B:68:0x0080, B:71:0x0089, B:74:0x0096, B:78:0x00a3, B:81:0x00b0), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0207 A[Catch: Exception -> 0x023b, TryCatch #0 {Exception -> 0x023b, blocks: (B:3:0x0007, B:5:0x0038, B:8:0x0041, B:12:0x00bf, B:15:0x01e5, B:17:0x01ff, B:19:0x0207, B:21:0x020d, B:23:0x0213, B:25:0x0219, B:31:0x00d7, B:33:0x00ef, B:38:0x00fc, B:39:0x010f, B:44:0x0124, B:46:0x0174, B:48:0x017e, B:49:0x0186, B:51:0x0193, B:53:0x019d, B:54:0x01a5, B:55:0x01a1, B:56:0x0182, B:58:0x0056, B:60:0x0064, B:63:0x006f, B:65:0x0075, B:68:0x0080, B:71:0x0089, B:74:0x0096, B:78:0x00a3, B:81:0x00b0), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d7 A[Catch: Exception -> 0x023b, TryCatch #0 {Exception -> 0x023b, blocks: (B:3:0x0007, B:5:0x0038, B:8:0x0041, B:12:0x00bf, B:15:0x01e5, B:17:0x01ff, B:19:0x0207, B:21:0x020d, B:23:0x0213, B:25:0x0219, B:31:0x00d7, B:33:0x00ef, B:38:0x00fc, B:39:0x010f, B:44:0x0124, B:46:0x0174, B:48:0x017e, B:49:0x0186, B:51:0x0193, B:53:0x019d, B:54:0x01a5, B:55:0x01a1, B:56:0x0182, B:58:0x0056, B:60:0x0064, B:63:0x006f, B:65:0x0075, B:68:0x0080, B:71:0x0089, B:74:0x0096, B:78:0x00a3, B:81:0x00b0), top: B:2:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r10) {
        /*
            Method dump skipped, instructions count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.a.a.a(java.util.Map):int");
    }

    public static void b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File file = new File(Environment.getExternalStorageDirectory(), ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static String b(Context context) {
        try {
            String b = i.b();
            if (!mq.e(b)) {
                return b;
            }
            c b2 = d.b(context);
            if (b2 != null) {
                i.a(b2);
                String str = b2.f841a;
                if (mq.b(str)) {
                    return str;
                }
            }
            b b3 = com.alipay.apmobilesecuritysdk.e.a.b(context);
            if (b3 == null) {
                return "";
            }
            i.a(b3);
            String str2 = b3.f840a;
            return mq.b(str2) ? str2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private jt b(Map<String, String> map) {
        String str;
        String str2;
        String str3;
        b b;
        b c;
        String str4 = "";
        try {
            Context context = this.f837a;
            js jsVar = new js();
            String b2 = mq.b(map, "appName", "");
            String b3 = mq.b(map, "sessionId", "");
            String b4 = mq.b(map, "rpcVersion", "");
            String a2 = a(context, b2);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String d = h.d(context);
            if (mq.b(b3)) {
                jsVar.c = b3;
            } else {
                jsVar.c = a2;
            }
            jsVar.e = securityToken;
            jsVar.f14043a = d;
            jsVar.d = OsType.ANDROID;
            c c2 = d.c(context);
            if (c2 != null) {
                str2 = c2.f841a;
                str = c2.c;
            } else {
                str = "";
                str2 = str;
            }
            if (mq.e(str2) && (c = com.alipay.apmobilesecuritysdk.e.a.c(context)) != null) {
                str2 = c.f840a;
                str = c.c;
            }
            c b5 = d.b();
            if (b5 != null) {
                str4 = b5.f841a;
                str3 = b5.c;
            } else {
                str3 = "";
            }
            if (mq.e(str4) && (b = com.alipay.apmobilesecuritysdk.e.a.b()) != null) {
                str4 = b.f840a;
                str3 = b.c;
            }
            jsVar.h = str2;
            jsVar.f = str4;
            jsVar.g = b4;
            if (mq.e(str2)) {
                jsVar.b = str4;
                str = str3;
            } else {
                jsVar.b = str2;
            }
            jsVar.i = str;
            jsVar.j = e.a(context, map);
            return jd.c(this.f837a, this.b.c()).a(jsVar);
        } catch (Throwable th) {
            th.printStackTrace();
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    public static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = (int) (Math.random() * 24.0d * 60.0d * 60.0d);
        for (int i = 0; i < 3; i++) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parse2);
                    calendar.add(13, random);
                    Date time = calendar.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static String a(Context context, String str) {
        try {
            b();
            String a2 = i.a(str);
            if (!mq.e(a2)) {
                return a2;
            }
            String a3 = g.a(context, str);
            i.a(str, a3);
            return !mq.e(a3) ? a3 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(Context context) {
        String b = b(context);
        return mq.e(b) ? h.f(context) : b;
    }

    public a(Context context) {
        this.f837a = context;
    }
}
