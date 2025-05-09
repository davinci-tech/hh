package com.autonavi.aps.amapapi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.hs;
import com.amap.api.col.p0003sl.ms;
import com.amap.api.location.AMapLocation;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class f {
    private static f f;
    private static long i;
    private File d;
    private String e;
    private Context g;
    private boolean h;
    private LinkedHashMap<String, Long> c = new LinkedHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    String f1649a = "";
    String b = null;

    public static f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (f == null) {
                f = new f(context);
            }
            fVar = f;
        }
        return fVar;
    }

    private f(Context context) {
        this.e = null;
        Context applicationContext = context.getApplicationContext();
        this.g = applicationContext;
        String path = applicationContext.getFilesDir().getPath();
        if (this.e == null) {
            this.e = i.l(this.g);
        }
        try {
            this.d = new File(path, "reportRecorder");
        } catch (Throwable th) {
            ms.a(th);
        }
        c();
    }

    public final void a() {
        synchronized (this) {
            if (this.h) {
                d();
                this.h = false;
            }
        }
    }

    public final void a(AMapLocation aMapLocation) {
        synchronized (this) {
            try {
                if ((!this.c.containsKey(this.f1649a) && this.c.size() >= 8) || (this.c.containsKey(this.f1649a) && this.c.size() >= 9)) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<Map.Entry<String, Long>> it = this.c.entrySet().iterator();
                    while (it.hasNext()) {
                        try {
                            arrayList.add(it.next().getKey());
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        if (arrayList.size() == this.c.size() - 7) {
                            break;
                        }
                    }
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        this.c.remove((String) it2.next());
                    }
                }
                if (aMapLocation.getErrorCode() != 0) {
                    return;
                }
                if (aMapLocation.getLocationType() != 6 && aMapLocation.getLocationType() != 5) {
                    if (this.c.containsKey(this.f1649a)) {
                        long longValue = this.c.get(this.f1649a).longValue() + 1;
                        i = longValue;
                        this.c.put(this.f1649a, Long.valueOf(longValue));
                    } else {
                        this.c.put(this.f1649a, 1L);
                        i = 1L;
                    }
                    long j = i;
                    if (j != 0 && j % 100 == 0) {
                        a();
                    }
                    this.h = true;
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public final void b() {
        synchronized (this) {
            try {
                if (b(this.g)) {
                    for (Map.Entry<String, Long> entry : this.c.entrySet()) {
                        try {
                            if (!this.f1649a.equals(entry.getKey())) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("param_long_first", entry.getKey());
                                jSONObject.put("param_long_second", entry.getValue());
                                g.a(this.g, "O023", jSONObject);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    private boolean b(Context context) {
        if (this.b == null) {
            this.b = h.a(context, "pref", "lastavedate", "0");
        }
        if (this.b.equals(this.f1649a)) {
            return false;
        }
        SharedPreferences.Editor a2 = h.a(context, "pref");
        h.a(a2, "lastavedate", this.f1649a);
        h.a(a2);
        this.b = this.f1649a;
        return true;
    }

    private void c() {
        synchronized (this) {
            LinkedHashMap<String, Long> linkedHashMap = this.c;
            if (linkedHashMap == null || linkedHashMap.size() <= 0) {
                try {
                    this.f1649a = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
                    Iterator<String> it = i.a(this.d).iterator();
                    while (it.hasNext()) {
                        try {
                            try {
                                String[] split = new String(com.autonavi.aps.amapapi.security.a.b(hs.b(it.next()), this.e), "UTF-8").split(",");
                                if (split != null && split.length > 1) {
                                    this.c.put(split[0], Long.valueOf(Long.parseLong(split[1])));
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
    }

    private void d() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Long> entry : this.c.entrySet()) {
                try {
                    sb.append(hs.b(com.autonavi.aps.amapapi.security.a.a((entry.getKey() + "," + entry.getValue()).getBytes("UTF-8"), this.e)) + "\n");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            String sb2 = sb.toString();
            if (TextUtils.isEmpty(sb2)) {
                return;
            }
            i.a(this.d, sb2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
