package com.amap.api.col.p0003sl;

import android.os.SystemClock;
import androidx.core.location.LocationRequestCompat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class li {

    /* renamed from: a, reason: collision with root package name */
    private mi f1323a;
    private mi b;
    private mo c;
    private a d = new a();
    private final List<mi> e = new ArrayList(3);

    final a a(mo moVar, boolean z, byte b, String str, List<mi> list) {
        if (z) {
            this.d.a();
            return null;
        }
        this.d.a(b, str, list);
        if (this.d.c == null) {
            return null;
        }
        if (this.c != null && !a(moVar) && a.a(this.d.d, this.f1323a) && a.a(this.d.e, this.b)) {
            return null;
        }
        this.f1323a = this.d.d;
        this.b = this.d.e;
        this.c = moVar;
        me.a(this.d.f);
        a(this.d);
        return this.d;
    }

    private boolean a(mo moVar) {
        return moVar.a(this.c) > ((double) ((moVar.g > 10.0f ? 1 : (moVar.g == 10.0f ? 0 : -1)) > 0 ? 2000.0f : (moVar.g > 2.0f ? 1 : (moVar.g == 2.0f ? 0 : -1)) > 0 ? 500.0f : 100.0f));
    }

    private void a(a aVar) {
        synchronized (this.e) {
            for (mi miVar : aVar.f) {
                if (miVar != null && miVar.h) {
                    mi clone = miVar.clone();
                    clone.e = SystemClock.elapsedRealtime();
                    a(clone);
                }
            }
            this.d.g.clear();
            this.d.g.addAll(this.e);
        }
    }

    private void a(mi miVar) {
        if (miVar == null) {
            return;
        }
        int size = this.e.size();
        if (size == 0) {
            this.e.add(miVar);
            return;
        }
        int i = -1;
        long j = LocationRequestCompat.PASSIVE_INTERVAL;
        int i2 = 0;
        int i3 = -1;
        while (true) {
            if (i2 >= size) {
                i = i3;
                break;
            }
            mi miVar2 = this.e.get(i2);
            if (miVar.equals(miVar2)) {
                if (miVar.c != miVar2.c) {
                    miVar2.e = miVar.c;
                    miVar2.c = miVar.c;
                }
            } else {
                j = Math.min(j, miVar2.e);
                if (j == miVar2.e) {
                    i3 = i2;
                }
                i2++;
            }
        }
        if (i >= 0) {
            if (size < 3) {
                this.e.add(miVar);
            } else {
                if (miVar.e <= j || i >= size) {
                    return;
                }
                this.e.remove(i);
                this.e.add(miVar);
            }
        }
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public byte f1324a;
        public String b;
        public mi c;
        public mi d;
        public mi e;
        public List<mi> f = new ArrayList();
        public List<mi> g = new ArrayList();

        public final void a() {
            this.f1324a = (byte) 0;
            this.b = "";
            this.c = null;
            this.d = null;
            this.e = null;
            this.f.clear();
            this.g.clear();
        }

        public final void a(byte b, String str, List<mi> list) {
            a();
            this.f1324a = b;
            this.b = str;
            if (list != null) {
                this.f.addAll(list);
                for (mi miVar : this.f) {
                    if (!miVar.i && miVar.h) {
                        this.d = miVar;
                    } else if (miVar.i && miVar.h) {
                        this.e = miVar;
                    }
                }
            }
            mi miVar2 = this.d;
            if (miVar2 == null) {
                miVar2 = this.e;
            }
            this.c = miVar2;
        }

        public static boolean a(mi miVar, mi miVar2) {
            if (miVar == null || miVar2 == null) {
                return (miVar == null) == (miVar2 == null);
            }
            if ((miVar instanceof mk) && (miVar2 instanceof mk)) {
                mk mkVar = (mk) miVar;
                mk mkVar2 = (mk) miVar2;
                return mkVar.j == mkVar2.j && mkVar.k == mkVar2.k;
            }
            if ((miVar instanceof mj) && (miVar2 instanceof mj)) {
                mj mjVar = (mj) miVar;
                mj mjVar2 = (mj) miVar2;
                return mjVar.l == mjVar2.l && mjVar.k == mjVar2.k && mjVar.j == mjVar2.j;
            }
            if ((miVar instanceof ml) && (miVar2 instanceof ml)) {
                ml mlVar = (ml) miVar;
                ml mlVar2 = (ml) miVar2;
                return mlVar.j == mlVar2.j && mlVar.k == mlVar2.k;
            }
            if ((miVar instanceof mm) && (miVar2 instanceof mm)) {
                mm mmVar = (mm) miVar;
                mm mmVar2 = (mm) miVar2;
                if (mmVar.j == mmVar2.j && mmVar.k == mmVar2.k) {
                    return true;
                }
            }
            return false;
        }

        public final String toString() {
            return "CellInfo{radio=" + ((int) this.f1324a) + ", operator='" + this.b + "', mainCell=" + this.c + ", mainOldInterCell=" + this.d + ", mainNewInterCell=" + this.e + ", cells=" + this.f + ", historyMainCellList=" + this.g + '}';
        }
    }
}
