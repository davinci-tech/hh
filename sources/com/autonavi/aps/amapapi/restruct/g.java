package com.autonavi.aps.amapapi.restruct;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.hs;
import com.amap.api.col.p0003sl.mp;
import com.amap.api.col.p0003sl.ms;
import com.amap.api.services.geocoder.GeocodeSearch;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class g {
    private File b;
    private Handler d;
    private String e;
    private boolean f;

    /* renamed from: a, reason: collision with root package name */
    private LinkedList<f> f1632a = new LinkedList<>();
    private boolean c = false;
    private Runnable g = new Runnable() { // from class: com.autonavi.aps.amapapi.restruct.g.1
        @Override // java.lang.Runnable
        public final void run() {
            if (g.this.c) {
                return;
            }
            if (g.this.f) {
                g.this.b();
                g.d(g.this);
            }
            if (g.this.d != null) {
                g.this.d.postDelayed(g.this.g, 60000L);
            }
        }
    };

    static /* synthetic */ boolean d(g gVar) {
        gVar.f = false;
        return false;
    }

    public g(Context context, Handler handler) {
        this.e = null;
        this.d = handler;
        String path = context.getFilesDir().getPath();
        if (this.e == null) {
            this.e = com.autonavi.aps.amapapi.utils.i.l(context);
        }
        try {
            this.b = new File(path, "hisloc");
        } catch (Throwable th) {
            ms.a(th);
        }
        a();
        Handler handler2 = this.d;
        if (handler2 != null) {
            handler2.removeCallbacks(this.g);
            this.d.postDelayed(this.g, 60000L);
        }
    }

    public final void a(boolean z) {
        if (!z) {
            this.g.run();
        }
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacks(this.g);
        }
        this.c = true;
    }

    public final void a(f fVar) {
        Iterator<f> it = this.f1632a.iterator();
        f fVar2 = null;
        f fVar3 = null;
        int i = 0;
        while (it.hasNext()) {
            f next = it.next();
            if (next.f1631a == 1) {
                if (fVar3 == null) {
                    fVar3 = next;
                }
                i++;
                fVar2 = next;
            }
        }
        if (fVar2 != null) {
            new Location(GeocodeSearch.GPS);
            if (fVar.d - fVar2.d < 20000 && com.autonavi.aps.amapapi.utils.i.a(new double[]{fVar.b, fVar.c, fVar2.b, fVar2.c}) < 20.0f) {
                return;
            }
        }
        if (i >= 5) {
            this.f1632a.remove(fVar3);
        }
        if (this.f1632a.size() >= 10) {
            this.f1632a.removeFirst();
        }
        this.f1632a.add(fVar);
        this.f = true;
    }

    public final void b(f fVar) {
        if (this.f1632a.size() > 0) {
            if (fVar.f1631a == 6 || fVar.f1631a == 5) {
                f last = this.f1632a.getLast();
                if (last.c == fVar.c && last.b == fVar.b && last.e == fVar.e) {
                    return;
                }
                if (this.f1632a.size() >= 10) {
                    this.f1632a.removeFirst();
                }
                this.f1632a.add(fVar);
                this.f = true;
                return;
            }
            if (this.f1632a.contains(fVar)) {
                return;
            }
            if (this.f1632a.size() >= 10) {
                this.f1632a.removeFirst();
            }
            this.f1632a.add(fVar);
            this.f = true;
        }
    }

    public final List<f> a(ArrayList<d> arrayList, ArrayList<mp> arrayList2) {
        if (!b(arrayList, arrayList2)) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList3 = new ArrayList();
        Iterator<f> it = this.f1632a.iterator();
        int i = 0;
        while (it.hasNext()) {
            f next = it.next();
            if (currentTimeMillis - next.d < 21600000000L) {
                arrayList3.add(next);
                i++;
            }
            if (i == 10) {
                break;
            }
        }
        return arrayList3;
    }

    private static boolean b(ArrayList<d> arrayList, ArrayList<mp> arrayList2) {
        if (arrayList == null || arrayList.size() <= 0 || arrayList2 == null || arrayList2.size() <= 0) {
            return true;
        }
        return ((long) arrayList.size()) < 4 && ((long) arrayList2.size()) < 20;
    }

    private void a() {
        LinkedList<f> linkedList = this.f1632a;
        if (linkedList == null || linkedList.size() <= 0) {
            Iterator<String> it = com.autonavi.aps.amapapi.utils.i.a(this.b).iterator();
            while (it.hasNext()) {
                try {
                    String str = new String(com.autonavi.aps.amapapi.security.a.b(hs.b(it.next()), this.e), "UTF-8");
                    f fVar = new f();
                    fVar.a(new JSONObject(str));
                    this.f1632a.add(fVar);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        StringBuilder sb = new StringBuilder();
        Iterator<f> it = this.f1632a.iterator();
        while (it.hasNext()) {
            try {
                sb.append(hs.b(com.autonavi.aps.amapapi.security.a.a(it.next().a().getBytes("UTF-8"), this.e)) + "\n");
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
}
