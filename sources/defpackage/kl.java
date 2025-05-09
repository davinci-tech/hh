package defpackage;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;

/* loaded from: classes7.dex */
public class kl {

    public static final class a {
        public static long e(Context context) {
            long d;
            synchronized (a.class) {
                d = d.d(context, "alipay_cashier_statistic_v");
            }
            return d;
        }
    }

    public static final class b {
        public static String b(Context context) {
            synchronized (b.class) {
                ma.a("RecordPref", "stat peek");
                if (context == null) {
                    return null;
                }
                e a2 = a(context);
                if (a2.d.isEmpty()) {
                    return null;
                }
                try {
                    return a2.d.entrySet().iterator().next().getValue();
                } catch (Throwable th) {
                    ma.c(th);
                    return null;
                }
            }
        }

        public static String c(Context context, String str, String str2) {
            synchronized (b.class) {
                ma.a("RecordPref", "stat append " + str2 + " , " + str);
                if (context != null && !TextUtils.isEmpty(str)) {
                    if (TextUtils.isEmpty(str2)) {
                        str2 = UUID.randomUUID().toString();
                    }
                    e a2 = a(context);
                    if (a2.d.size() > 20) {
                        a2.d.clear();
                    }
                    a2.d.put(str2, str);
                    a(context, a2);
                    return str2;
                }
                return null;
            }
        }

        public static final class e {
            public final LinkedHashMap<String, String> d = new LinkedHashMap<>();

            public e() {
            }

            public String e() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry<String, String> entry : this.d.entrySet()) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(entry.getKey()).put(entry.getValue());
                        jSONArray.put(jSONArray2);
                    }
                    return jSONArray.toString();
                } catch (Throwable th) {
                    ma.c(th);
                    return new JSONArray().toString();
                }
            }

            public e(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        this.d.put(jSONArray2.getString(0), jSONArray2.getString(1));
                    }
                } catch (Throwable th) {
                    ma.c(th);
                }
            }
        }

        public static int d(Context context, String str) {
            synchronized (b.class) {
                ma.a("RecordPref", "stat remove " + str);
                if (context != null && !TextUtils.isEmpty(str)) {
                    e a2 = a(context);
                    if (a2.d.isEmpty()) {
                        return 0;
                    }
                    try {
                        ArrayList arrayList = new ArrayList();
                        for (Map.Entry<String, String> entry : a2.d.entrySet()) {
                            if (str.equals(entry.getValue())) {
                                arrayList.add(entry.getKey());
                            }
                        }
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            a2.d.remove((String) it.next());
                        }
                        a(context, a2);
                        return arrayList.size();
                    } catch (Throwable th) {
                        ma.c(th);
                        int size = a2.d.size();
                        a(context, new e());
                        return size;
                    }
                }
                return 0;
            }
        }

        public static e a(Context context) {
            synchronized (b.class) {
                try {
                    String d = mb.d(null, context, "alipay_cashier_statistic_record", null);
                    if (TextUtils.isEmpty(d)) {
                        return new e();
                    }
                    return new e(d);
                } catch (Throwable th) {
                    ma.c(th);
                    return new e();
                }
            }
        }

        public static void a(Context context, e eVar) {
            synchronized (b.class) {
                if (eVar == null) {
                    try {
                        eVar = new e();
                    } catch (Throwable th) {
                        ma.c(th);
                    }
                }
                mb.c(null, context, "alipay_cashier_statistic_record", eVar.e());
            }
        }
    }

    public static final class c {
        public static long d(Context context) {
            long d;
            synchronized (c.class) {
                d = d.d(context, "alipay_cashier_ap_seq_v");
            }
            return d;
        }
    }

    public static final class d {
        /* JADX WARN: Can't wrap try/catch for region: R(9:3|4|5|(5:7|8|9|10|11)|17|8|9|10|11) */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static long d(android.content.Context r6, java.lang.String r7) {
            /*
                java.lang.Class<kl$d> r0 = kl.d.class
                monitor-enter(r0)
                r1 = 0
                java.lang.String r2 = defpackage.mb.d(r1, r6, r7, r1)     // Catch: java.lang.Throwable -> L13
                boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L13
                if (r3 != 0) goto L13
                long r2 = java.lang.Long.parseLong(r2)     // Catch: java.lang.Throwable -> L13
                goto L15
            L13:
                r2 = 0
            L15:
                r4 = 1
                long r2 = r2 + r4
                java.lang.String r4 = java.lang.Long.toString(r2)     // Catch: java.lang.Throwable -> L1f
                defpackage.mb.c(r1, r6, r7, r4)     // Catch: java.lang.Throwable -> L1f
            L1f:
                monitor-exit(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: kl.d.d(android.content.Context, java.lang.String):long");
        }
    }

    public static final class e {

        public static final class d implements Runnable {

            /* renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Context f14436a;
            public final /* synthetic */ String e;

            public d(String str, Context context) {
                this.e = str;
                this.f14436a = context;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(this.e) || e.a(this.f14436a, this.e)) {
                    for (int i = 0; i < 4; i++) {
                        String b = b.b(this.f14436a);
                        if (TextUtils.isEmpty(b) || !e.a(this.f14436a, b)) {
                            return;
                        }
                    }
                }
            }
        }

        public static boolean a(Context context, String str) {
            synchronized (e.class) {
                ma.a("mspl", "stat sub " + str);
                try {
                    if ((kr.a().g() ? new lr() : new lo()).a((lv) null, context, str) == null) {
                        return false;
                    }
                    b.d(context, str);
                    return true;
                } catch (Throwable th) {
                    ma.c(th);
                    return false;
                }
            }
        }

        public static void d(Context context, kn knVar, String str, String str2) {
            synchronized (e.class) {
                if (context == null || knVar == null || str == null) {
                    return;
                }
                a(context, knVar.d(str), str2);
            }
        }

        public static void a(Context context, String str, String str2) {
            synchronized (e.class) {
                if (context == null) {
                    return;
                }
                if (!TextUtils.isEmpty(str)) {
                    b.c(context, str, str2);
                }
                new Thread(new d(str, context)).start();
            }
        }
    }

    public static void c(Context context, lv lvVar, String str, String str2) {
        synchronized (kl.class) {
            if (context == null || lvVar == null) {
                return;
            }
            try {
                b.c(context, lvVar.n.d(str), str2);
            } catch (Throwable th) {
                ma.c(th);
            }
        }
    }

    public static void e(Context context, lv lvVar, String str, String str2) {
        synchronized (kl.class) {
            if (context == null || lvVar == null) {
                return;
            }
            e.d(context, lvVar.n, str, str2);
        }
    }

    public static void c(lv lvVar, String str, String str2, String str3) {
        if (lvVar == null) {
            return;
        }
        lvVar.n.d(str, str2, str3);
    }

    public static void e(lv lvVar, String str, Throwable th) {
        if (lvVar == null || th == null || th.getClass() == null) {
            return;
        }
        lvVar.n.b(str, th.getClass().getSimpleName(), th);
    }

    public static void a(lv lvVar, String str, String str2, Throwable th, String str3) {
        if (lvVar == null) {
            return;
        }
        lvVar.n.c(str, str2, th, str3);
    }

    public static void e(lv lvVar, String str, String str2, Throwable th) {
        if (lvVar == null) {
            return;
        }
        lvVar.n.b(str, str2, th);
    }

    public static void a(lv lvVar, String str, String str2, String str3) {
        if (lvVar == null) {
            return;
        }
        lvVar.n.c(str, str2, str3);
    }

    public static void b(lv lvVar, String str, String str2) {
        if (lvVar == null) {
            return;
        }
        lvVar.n.e(str, str2);
    }
}
