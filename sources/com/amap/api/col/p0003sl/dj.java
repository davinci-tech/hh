package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.LatLng;
import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class dj {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f970a = false;
    private static volatile dj d;
    private Hashtable<String, String> b = new Hashtable<>();
    private WeakReference<Context> c = null;

    private dj() {
    }

    public static dj a() {
        if (d == null) {
            synchronized (dj.class) {
                if (d == null) {
                    d = new dj();
                }
            }
        }
        return d;
    }

    public static void b() {
        if (d != null) {
            if (d.b != null && d.b.size() > 0) {
                synchronized (d.b) {
                    d.d();
                    if (d.c != null) {
                        d.c.clear();
                    }
                }
            }
            d = null;
        }
        a(false);
    }

    public static void a(boolean z) {
        f970a = z;
    }

    public static boolean c() {
        return f970a;
    }

    public final void a(Context context) {
        if (context != null) {
            this.c = new WeakReference<>(context);
        }
    }

    public final void a(LatLng latLng, String str, String str2) {
        if (!f970a) {
            this.b.clear();
            return;
        }
        if (latLng == null || TextUtils.isEmpty(str)) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("{\"lon\":");
        stringBuffer.append(latLng.longitude).append(",\"lat\":");
        stringBuffer.append(latLng.latitude).append(",\"title\":\"");
        stringBuffer.append(str).append("\",\"snippet\":\"");
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        stringBuffer.append(str2).append("\"}");
        a(stringBuffer.toString());
    }

    private void a(String str) {
        Hashtable<String, String> hashtable;
        if (str == null || (hashtable = this.b) == null) {
            return;
        }
        synchronized (hashtable) {
            String b = hv.b(str);
            Hashtable<String, String> hashtable2 = this.b;
            if (hashtable2 != null && !hashtable2.contains(b)) {
                this.b.put(b, str);
            }
            if (e()) {
                d();
            }
        }
    }

    private void d() {
        WeakReference<Context> weakReference;
        if (!f970a) {
            this.b.clear();
            return;
        }
        if (this.b != null) {
            StringBuffer stringBuffer = new StringBuffer();
            int size = this.b.size();
            if (size > 0) {
                stringBuffer.append("[");
                Iterator<String> it = this.b.values().iterator();
                int i = 0;
                while (it.hasNext()) {
                    i++;
                    stringBuffer.append(it.next());
                    if (i < size) {
                        stringBuffer.append(",");
                    }
                }
                stringBuffer.append("]");
                String stringBuffer2 = stringBuffer.toString();
                if (!TextUtils.isEmpty(stringBuffer2) && (weakReference = this.c) != null && weakReference.get() != null) {
                    kf.a(stringBuffer2, this.c.get());
                }
            }
            this.b.clear();
        }
    }

    private boolean e() {
        Hashtable<String, String> hashtable = this.b;
        return hashtable != null && hashtable.size() > 20;
    }
}
