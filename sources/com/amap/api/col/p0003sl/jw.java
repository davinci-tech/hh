package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class jw {

    /* renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<String, c> f1233a = new ConcurrentHashMap<>(8);
    public static volatile List<String> b = Collections.synchronizedList(new ArrayList(8));
    private static volatile ConcurrentHashMap<String, b> c = new ConcurrentHashMap<>(8);
    private static Random d = new Random();
    private static ConcurrentHashMap<String, String> e = new ConcurrentHashMap<>(8);
    private static List<ki> f = Collections.synchronizedList(new ArrayList(16));

    public static void a(hz hzVar, JSONObject jSONObject) {
        synchronized (jw.class) {
            if (hzVar == null) {
                return;
            }
            try {
                String a2 = hzVar.a();
                if (TextUtils.isEmpty(a2)) {
                    return;
                }
                if (jSONObject == null) {
                    a(a2);
                }
                if (!ho.a(jSONObject.optString("able", null), false)) {
                    a(a2);
                } else {
                    ji.a(ho.c, "Yb3Blbl9odHRwX2NvbnRyb2w", a2, jSONObject.toString());
                    a(a2, jSONObject);
                }
            } catch (Throwable th) {
                is.a(th, "hlUtil", "par");
            }
        }
    }

    private static void a(String str, JSONObject jSONObject) {
        try {
            c cVar = new c((byte) 0);
            a(cVar, jSONObject);
            b(cVar, jSONObject);
            if (cVar.b == null && cVar.f1236a == null) {
                a(str);
            } else {
                a(str, cVar);
            }
        } catch (Throwable unused) {
        }
    }

    public static String a(String str, String str2) throws hm {
        synchronized (jw.class) {
            try {
                try {
                    System.currentTimeMillis();
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                        Context context = ho.c;
                        try {
                            if (b == null) {
                                b = Collections.synchronizedList(new ArrayList(8));
                            }
                            if (context != null && !b.contains(str2)) {
                                b.add(str2);
                                String a2 = ji.a(context, "Yb3Blbl9odHRwX2NvbnRyb2w", str2);
                                if (!TextUtils.isEmpty(a2)) {
                                    a(str2, new JSONObject(a2));
                                }
                            }
                        } catch (Throwable th) {
                            is.a(th, "hlUtil", "llhl");
                        }
                        if (f1233a != null && f1233a.size() > 0) {
                            if (!f1233a.containsKey(str2)) {
                                return str;
                            }
                            c cVar = f1233a.get(str2);
                            if (cVar == null) {
                                return str;
                            }
                            if (a(str, cVar, str2)) {
                                throw new hm("服务QPS超限");
                            }
                            return b(str, cVar, str2);
                        }
                        return str;
                    }
                    return str;
                } finally {
                }
            } catch (hm e2) {
                throw e2;
            } catch (Throwable th2) {
                is.a(th2, "hlUtil", "pcr");
                return str;
            }
        }
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        kb f1235a;
        long b;

        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }
    }

    public static void a(URL url, kb kbVar) {
        List<String> list;
        try {
            if (c == null) {
                c = new ConcurrentHashMap<>(8);
            }
            if (kbVar.b != null && kbVar.b.containsKey("nb") && (list = kbVar.b.get("nb")) != null && list.size() > 0) {
                byte b2 = 0;
                String[] split = list.get(0).split("#");
                if (split.length < 2) {
                    return;
                }
                int parseInt = Integer.parseInt(split[0]);
                long parseInt2 = Integer.parseInt(split[1]);
                b bVar = new b(b2);
                bVar.f1235a = kbVar;
                if (parseInt2 <= 0) {
                    parseInt2 = 30;
                }
                bVar.b = SystemClock.elapsedRealtime() + (parseInt2 * 1000);
                if (parseInt == 1) {
                    c.put("app", bVar);
                } else {
                    if (parseInt != 2 || url == null) {
                        return;
                    }
                    c.put(url.getPath(), bVar);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static kb b(String str, String str2) {
        Uri parse;
        if (c == null) {
            return null;
        }
        if (c.containsKey("app")) {
            b bVar = c.get("app");
            if (SystemClock.elapsedRealtime() > bVar.b) {
                c.remove("app");
            } else {
                kb kbVar = bVar.f1235a;
                if (kbVar != null) {
                    kbVar.e = false;
                }
                a(true, str2, str, 1);
                return kbVar;
            }
        } else if (!TextUtils.isEmpty(str) && (parse = Uri.parse(str)) != null) {
            String path = parse.getPath();
            if (c.containsKey(path)) {
                b bVar2 = c.get(path);
                if (SystemClock.elapsedRealtime() <= bVar2.b) {
                    kb kbVar2 = bVar2.f1235a;
                    if (kbVar2 != null) {
                        kbVar2.e = false;
                    }
                    a(true, str2, str, 2);
                    return kbVar2;
                }
                c.remove(path);
            }
        }
        return null;
    }

    private static void a(String str, c cVar) {
        try {
            if (f1233a == null) {
                f1233a = new ConcurrentHashMap<>(8);
            }
            f1233a.put(str, cVar);
        } catch (Throwable th) {
            is.a(th, "hlUtil", "ucr");
        }
    }

    private static void a(c cVar, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("block");
            if (optJSONArray == null) {
                return;
            }
            HashMap hashMap = new HashMap(8);
            byte b2 = 0;
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString(TrackConstants$Events.API);
                    if (!TextUtils.isEmpty(optString)) {
                        if (!optString.startsWith("/")) {
                            optString = "/".concat(String.valueOf(optString));
                        }
                        if (optString.endsWith("/")) {
                            optString = optString.substring(0, optString.length() - 1);
                        }
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("periods");
                        if (optJSONArray != null) {
                            ArrayList arrayList = new ArrayList();
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                                if (optJSONObject2 != null) {
                                    a aVar = new a(b2);
                                    aVar.f1234a = optJSONObject2.optString("begin");
                                    aVar.b = optJSONObject2.optInt("duration");
                                    aVar.c = optJSONObject2.optDouble("percent");
                                    arrayList.add(aVar);
                                }
                            }
                            hashMap.put(optString, arrayList);
                        }
                    }
                }
            }
            cVar.f1236a = hashMap;
        } catch (Throwable th) {
            is.a(th, "hlUtil", "pbr");
        }
    }

    private static void b(c cVar, JSONObject jSONObject) {
        JSONArray names;
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("domainMap");
            if (optJSONObject == null || (names = optJSONObject.names()) == null) {
                return;
            }
            HashMap hashMap = new HashMap(8);
            int length = names.length();
            for (int i = 0; i < length; i++) {
                String optString = names.optString(i);
                hashMap.put(optString, optJSONObject.optString(optString));
            }
            cVar.b = hashMap;
        } catch (Throwable th) {
            is.a(th, "hlUtil", "pdr");
        }
    }

    private static void a(String str) {
        synchronized (jw.class) {
            try {
                if (f1233a.containsKey(str)) {
                    f1233a.remove(str);
                }
                SharedPreferences.Editor a2 = ji.a(ho.c, "Yb3Blbl9odHRwX2NvbnRyb2w");
                ji.a(a2, str);
                ji.a(a2);
            } catch (Throwable th) {
                is.a(th, "hlUtil", "rc");
            }
        }
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        Map<String, List<a>> f1236a;
        Map<String, String> b;

        private c() {
            this.f1236a = new HashMap(8);
            this.b = new HashMap(8);
        }

        /* synthetic */ c(byte b) {
            this();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            c cVar = (c) obj;
            return this.f1236a.equals(cVar.f1236a) && this.b.equals(cVar.b);
        }

        public final int hashCode() {
            Map<String, List<a>> map = this.f1236a;
            int hashCode = map != null ? map.hashCode() : 0;
            Map<String, String> map2 = this.b;
            return hashCode + (map2 != null ? map2.hashCode() : 0);
        }
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        String f1234a;
        int b;
        double c;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    private static boolean a(String str, c cVar, String str2) {
        Map<String, List<a>> map;
        try {
            map = cVar.f1236a;
        } catch (Throwable th) {
            is.a(th, "hlUtil", "inb");
        }
        if (map != null && map.size() > 0) {
            if (map.containsKey("*")) {
                Iterator<Map.Entry<String, List<a>>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    if (a(it.next().getValue())) {
                        a(false, str2, str, 1);
                        return true;
                    }
                }
            } else {
                String path = Uri.parse(str).getPath();
                if (map.containsKey(path) && a(map.get(path))) {
                    a(false, str2, str, 2);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static boolean a(List<a> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        Iterator<a> it = list.iterator();
        while (it.hasNext()) {
            if (a(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(a aVar) {
        if (aVar == null || aVar.c == 1.0d) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(aVar.f1234a) && aVar.b > 0) {
            long timeInMillis = currentTimeMillis - ia.a(aVar.f1234a, Constants.TIME_FORMAT_WITHOUT_MILLS).getTimeInMillis();
            if (timeInMillis > 0 && timeInMillis < aVar.b * 1000) {
                if (aVar.c == 0.0d) {
                    return true;
                }
                if (d == null) {
                    d = new Random();
                }
                d.setSeed(UUID.randomUUID().hashCode() + currentTimeMillis);
                if (d.nextDouble() > aVar.c) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String b(String str, c cVar, String str2) {
        try {
            Map<String, String> map = cVar.b;
            if (map != null && map.size() > 0) {
                Uri parse = Uri.parse(str);
                String authority = parse.getAuthority();
                if (!map.containsKey(authority)) {
                    return str;
                }
                String str3 = map.get(authority);
                str = parse.buildUpon().authority(str3).toString();
                a(str2, authority, str3);
                return str;
            }
            return str;
        } catch (Throwable th) {
            is.a(th, "hlUtil", "pdr");
            return str;
        }
    }

    public static void a(boolean z, String str) {
        try {
            Context context = ho.c;
            if (context != null && !TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                if (z) {
                    jSONObject.put("type", io.g);
                } else {
                    jSONObject.put("type", io.f);
                }
                jSONObject.put("name", str);
                jSONObject.put("version", io.a(str));
                String jSONObject2 = jSONObject.toString();
                ki kiVar = new ki(context, "core", "2.0", "O005");
                kiVar.a(jSONObject2);
                kj.a(kiVar, context);
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(String str, String str2, String str3) {
        try {
            Context context = ho.c;
            if (context != null && !TextUtils.isEmpty(str)) {
                if (e == null) {
                    e = new ConcurrentHashMap<>(8);
                }
                synchronized (e) {
                    if (e.containsKey(str2)) {
                        return;
                    }
                    e.put(str2, str3);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("timestamp", System.currentTimeMillis());
                    jSONObject.put("type", io.j);
                    jSONObject.put("name", str);
                    jSONObject.put("version", io.a(str));
                    jSONObject.put("hostname", str2 + "#" + str3);
                    String jSONObject2 = jSONObject.toString();
                    if (TextUtils.isEmpty(jSONObject2)) {
                        return;
                    }
                    ki kiVar = new ki(context, "core", "2.0", "O005");
                    kiVar.a(jSONObject2);
                    kj.a(kiVar, context);
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(boolean z, String str, String str2, int i) {
        try {
            Context context = ho.c;
            if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", System.currentTimeMillis());
                String a2 = io.a(str);
                if (z) {
                    jSONObject.put("type", io.i);
                } else {
                    jSONObject.put("type", io.h);
                }
                jSONObject.put("name", str);
                jSONObject.put("version", a2);
                jSONObject.put("uri", Uri.parse(str2).getPath());
                jSONObject.put("blockLevel", i);
                String jSONObject2 = jSONObject.toString();
                if (TextUtils.isEmpty(jSONObject2)) {
                    return;
                }
                ki kiVar = new ki(context, "core", "2.0", "O005");
                kiVar.a(jSONObject2);
                if (f == null) {
                    f = Collections.synchronizedList(new ArrayList(16));
                }
                synchronized (f) {
                    f.add(kiVar);
                    if (f.size() >= 15) {
                        a();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void a() {
        try {
            Context context = ho.c;
            if (context == null) {
                return;
            }
            kj.a(b(), context);
        } catch (Throwable unused) {
        }
    }

    public static List<ki> b() {
        ArrayList arrayList = null;
        try {
        } catch (Throwable unused) {
        }
        synchronized (f) {
            try {
                List<ki> list = f;
                if (list != null && list.size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    try {
                        arrayList2.addAll(f);
                        f.clear();
                        arrayList = arrayList2;
                    } catch (Throwable th) {
                        th = th;
                        arrayList = arrayList2;
                        throw th;
                    }
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
