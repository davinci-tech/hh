package com.autonavi.aps.amapapi.trans;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.ho;
import com.amap.api.col.p0003sl.jt;
import com.amap.api.col.p0003sl.ka;
import com.amap.api.col.p0003sl.la;
import com.amap.api.col.p0003sl.lb;
import com.autonavi.aps.amapapi.utils.g;
import com.autonavi.aps.amapapi.utils.h;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public static int f1639a = 1;
    public static int b = 2;
    private static a e;
    private Context j;
    private String k;
    private long c = 0;
    private boolean d = false;
    private ArrayList<String> f = new ArrayList<>();
    private com.autonavi.aps.amapapi.d g = new com.autonavi.aps.amapapi.d();
    private com.autonavi.aps.amapapi.d h = new com.autonavi.aps.amapapi.d();
    private long i = 120000;
    private boolean l = false;

    public static a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (e == null) {
                e = new a(context);
            }
            aVar = e;
        }
        return aVar;
    }

    private a(Context context) {
        this.j = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.autonavi.aps.amapapi.d b(int i) {
        if (i == b) {
            return this.h;
        }
        return this.g;
    }

    private static String c(int i) {
        return i == b ? "last_ip_6" : "last_ip_4";
    }

    public final String a(d dVar, int i) {
        try {
            if (com.autonavi.aps.amapapi.utils.a.p() && dVar != null) {
                String url = dVar.getURL();
                String host = new URL(url).getHost();
                if (!"http://abroad.apilocate.amap.com/mobile/binary".equals(url) && !"abroad.apilocate.amap.com".equals(host)) {
                    String str = "apilocate.amap.com".equalsIgnoreCase(host) ? "httpdns.apilocate.amap.com" : host;
                    if (!ho.d(str)) {
                        return null;
                    }
                    String e2 = e(i);
                    if (!TextUtils.isEmpty(e2)) {
                        dVar.c(url.replace(host, e2));
                        dVar.getRequestHead().put("host", str);
                        dVar.d(str);
                        dVar.setIPV6Request(i == b);
                        return e2;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public final void a(int i) {
        if (!b(i).e()) {
            this.f.add(b(i).b());
            d(i);
            b(true, i);
            return;
        }
        d(i);
    }

    public final void a(boolean z, int i) {
        b(i).b(z);
        if (z) {
            String c = b(i).c();
            String b2 = b(i).b();
            if (TextUtils.isEmpty(b2) || b2.equals(c)) {
                return;
            }
            SharedPreferences.Editor a2 = h.a(this.j, "cbG9jaXA");
            h.a(a2, c(i), b2);
            h.a(a2);
        }
    }

    private void d(int i) {
        if (b(i).d()) {
            SharedPreferences.Editor a2 = h.a(this.j, "cbG9jaXA");
            h.a(a2, c(i));
            h.a(a2);
            b(i).a(false);
        }
    }

    private String e(int i) {
        String str;
        int i2 = 0;
        b(false, i);
        String[] a2 = b(i).a();
        if (a2 != null && a2.length > 0) {
            int length = a2.length;
            while (true) {
                if (i2 >= length) {
                    str = null;
                    break;
                }
                str = a2[i2];
                if (!this.f.contains(str)) {
                    break;
                }
                i2++;
            }
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            b(i).a(str);
            return str;
        }
        g(i);
        return b(i).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        if (b(i).a() == null || b(i).a().length <= 0) {
            return;
        }
        String str = b(i).a()[0];
        if (str.equals(this.k) || this.f.contains(str)) {
            return;
        }
        this.k = str;
        SharedPreferences.Editor a2 = h.a(this.j, "cbG9jaXA");
        h.a(a2, c(i), str);
        h.a(a2);
    }

    private void g(int i) {
        String a2 = h.a(this.j, "cbG9jaXA", c(i), (String) null);
        if (TextUtils.isEmpty(a2) || this.f.contains(a2)) {
            return;
        }
        b(i).a(a2);
        b(i).b(a2);
        b(i).a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr.length == 0 || strArr2 == null || strArr2.length == 0 || strArr.length != strArr2.length) {
            return false;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (!strArr[i].equals(strArr2[i])) {
                return false;
            }
        }
        return true;
    }

    private void b(boolean z, final int i) {
        synchronized (this) {
            if (!z) {
                if (!com.autonavi.aps.amapapi.utils.a.o() && this.l) {
                    return;
                }
            }
            if (this.c != 0) {
                long currentTimeMillis = System.currentTimeMillis() - this.c;
                if (currentTimeMillis < this.i) {
                    return;
                }
                if (currentTimeMillis < 60000) {
                    return;
                }
            }
            this.c = System.currentTimeMillis();
            this.l = true;
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuffer stringBuffer = new StringBuffer();
            for (StackTraceElement stackTraceElement : stackTrace) {
                stringBuffer.append(stackTraceElement.getClassName() + Constants.LEFT_BRACKET_ONLY + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber() + "),");
            }
            la.a().a(new lb() { // from class: com.autonavi.aps.amapapi.trans.a.1
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    int i2;
                    StringBuilder sb = new StringBuilder("http://");
                    sb.append(com.autonavi.aps.amapapi.utils.a.q());
                    sb.append("?host=dualstack-a.apilocate.amap.com&query=");
                    sb.append(i == a.b ? 6 : 4);
                    String sb2 = sb.toString();
                    b bVar = new b();
                    bVar.a(sb2);
                    bVar.b(sb2);
                    bVar.setDegradeAbility(ka.a.SINGLE);
                    bVar.setHttpProtocol(ka.c.HTTP);
                    try {
                        jt.a();
                        JSONObject jSONObject = new JSONObject(new String(jt.a(bVar).f1250a));
                        String[] b2 = a.b(jSONObject.optJSONArray("ips"), a.f1639a);
                        if (b2 != null && b2.length > 0 && !a.b(b2, a.this.b(a.f1639a).a())) {
                            a.this.b(a.f1639a).a(b2);
                            a.this.f(a.f1639a);
                        }
                        String[] b3 = a.b(jSONObject.optJSONArray("ipsv6"), a.b);
                        if (b3 != null && b3.length > 0 && !a.b(b3, a.this.b(a.b).a())) {
                            a.this.b(a.b).a(b3);
                            a.this.f(a.b);
                        }
                        if ((jSONObject.has("ips") || jSONObject.has("ipsv6")) && jSONObject.has("ttl") && (i2 = jSONObject.getInt("ttl")) > 30) {
                            a.this.i = i2 * 1000;
                        }
                    } catch (Throwable th) {
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put(MedalConstants.EVENT_KEY, "dnsError");
                            jSONObject2.put("reason", th.getMessage());
                        } catch (Throwable unused) {
                        }
                        g.a(a.this.j, "O018", jSONObject2);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] b(JSONArray jSONArray, int i) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new String[0];
        }
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            String string = jSONArray.getString(i2);
            if (!TextUtils.isEmpty(string)) {
                if (i == b) {
                    string = "[" + string + "]";
                }
                strArr[i2] = string;
            }
        }
        return strArr;
    }
}
