package com.autonavi.aps.amapapi.restruct;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.hs;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public abstract class a<T> {

    /* renamed from: a, reason: collision with root package name */
    public String f1622a;
    private File b;
    private Handler e;
    private String f;
    private boolean g;
    private boolean c = false;
    private Map<String, C0026a> d = new ConcurrentHashMap();
    private Runnable h = new Runnable() { // from class: com.autonavi.aps.amapapi.restruct.a.2
        @Override // java.lang.Runnable
        public final void run() {
            if (a.this.c) {
                if (a.this.g) {
                    a.this.e();
                    a.e(a.this);
                }
                if (a.this.e != null) {
                    a.this.e.postDelayed(a.this.h, 60000L);
                }
            }
        }
    };

    public static int a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    abstract void a(T t, long j);

    abstract long b();

    public abstract String b(T t);

    abstract int c(T t);

    abstract long c();

    abstract long d(T t);

    static /* synthetic */ boolean e(a aVar) {
        aVar.g = false;
        return false;
    }

    public a(Context context, String str, Handler handler) {
        this.f = null;
        if (context == null) {
            return;
        }
        this.e = handler;
        this.f1622a = TextUtils.isEmpty(str) ? "unknow" : str;
        this.f = com.autonavi.aps.amapapi.utils.i.l(context);
        try {
            this.b = new File(context.getFilesDir().getPath(), this.f1622a);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        d();
    }

    public final void a() {
        Handler handler;
        if (!this.c && (handler = this.e) != null) {
            handler.removeCallbacks(this.h);
            this.e.postDelayed(this.h, 60000L);
        }
        this.c = true;
    }

    public final void a(boolean z) {
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacks(this.h);
        }
        if (!z) {
            this.h.run();
        }
        this.c = false;
    }

    public final void a(T t) {
        b(t, com.autonavi.aps.amapapi.utils.i.b());
    }

    public final void a(List<T> list) {
        long b = com.autonavi.aps.amapapi.utils.i.b();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            b(it.next(), b);
        }
        if (this.d.size() >= list.size()) {
            this.g = true;
        }
        if (this.d.size() > 16384 || c() <= 0) {
            this.d.clear();
            for (T t : list) {
                this.d.put(b((a<T>) t), new C0026a(c((a<T>) t), d((a<T>) t), b));
            }
        }
    }

    private void b(T t, long j) {
        if (t == null || d((a<T>) t) < 0) {
            return;
        }
        String b = b((a<T>) t);
        C0026a c0026a = this.d.get(b);
        if (c0026a == null) {
            a((a<T>) t, j);
            this.d.put(b, new C0026a(c((a<T>) t), d((a<T>) t), j));
            this.g = true;
            return;
        }
        c0026a.c = j;
        if (c0026a.f1625a != c((a<T>) t)) {
            a((a<T>) t, j);
            c0026a.f1625a = c((a<T>) t);
            c0026a.b = d((a<T>) t);
            this.g = true;
            return;
        }
        a((a<T>) t, c0026a.b);
    }

    private void d() {
        long b;
        try {
            Iterator<String> it = com.autonavi.aps.amapapi.utils.i.a(this.b).iterator();
            while (it.hasNext()) {
                try {
                    String[] split = new String(com.autonavi.aps.amapapi.security.a.b(hs.b(it.next()), this.f), "UTF-8").split(",");
                    if (split.length >= 4) {
                        b = Long.parseLong(split[3]);
                    } else {
                        b = com.autonavi.aps.amapapi.utils.i.b();
                    }
                    this.d.put(split[0], new C0026a(Integer.parseInt(split[1]), Long.parseLong(split[2]), b));
                } catch (Throwable th) {
                    if (this.b.exists()) {
                        this.b.delete();
                    }
                    th.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (c() > 0) {
            this.d.size();
            if (b() > 0) {
                long b = com.autonavi.aps.amapapi.utils.i.b();
                Iterator<Map.Entry<String, C0026a>> it = this.d.entrySet().iterator();
                while (it.hasNext()) {
                    if (b - this.d.get(it.next().getKey()).c > b()) {
                        it.remove();
                    }
                }
            }
            if (this.d.size() > c()) {
                ArrayList arrayList = new ArrayList(this.d.keySet());
                Collections.sort(arrayList, new Comparator<String>() { // from class: com.autonavi.aps.amapapi.restruct.a.1
                    /* JADX INFO: Access modifiers changed from: private */
                    @Override // java.util.Comparator
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(String str, String str2) {
                        return a.a(((C0026a) a.this.d.get(str2)).c, ((C0026a) a.this.d.get(str)).c);
                    }
                });
                for (int c = (int) c(); c < arrayList.size(); c++) {
                    this.d.remove(arrayList.get(c));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, C0026a> entry : this.d.entrySet()) {
            try {
                sb.append(hs.b(com.autonavi.aps.amapapi.security.a.a((entry.getKey() + "," + entry.getValue().f1625a + "," + entry.getValue().b + "," + entry.getValue().c).getBytes("UTF-8"), this.f)) + "\n");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return;
        }
        com.autonavi.aps.amapapi.utils.i.a(this.b, sb2);
    }

    /* renamed from: com.autonavi.aps.amapapi.restruct.a$a, reason: collision with other inner class name */
    static final class C0026a {

        /* renamed from: a, reason: collision with root package name */
        int f1625a;
        long b;
        long c;

        public C0026a(int i, long j, long j2) {
            this.f1625a = i;
            this.b = j;
            this.c = j2;
        }
    }

    public final long e(T t) {
        return (com.autonavi.aps.amapapi.utils.i.b() - d((a<T>) t)) / 1000;
    }
}
