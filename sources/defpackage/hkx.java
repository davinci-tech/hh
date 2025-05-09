package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class hkx {
    private int h;

    /* renamed from: a, reason: collision with root package name */
    private List<hjf> f13228a = new ArrayList();
    private List<hjd> i = new ArrayList();
    private List<hjd> e = new ArrayList();
    private List<hjd> c = new ArrayList();
    private List<hjd> b = new ArrayList();
    private List<hjg> d = new ArrayList();

    public int g() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public List<hjf> e() {
        return this.f13228a;
    }

    public void b(List<hjf> list) {
        if (list == null) {
            this.f13228a = new ArrayList();
        } else {
            this.f13228a = list;
        }
    }

    public List<hjd> d() {
        return this.i;
    }

    public void a(List<hjd> list) {
        if (list == null) {
            this.i = new ArrayList();
        } else {
            this.i = list;
        }
    }

    public List<hjd> b() {
        return this.e;
    }

    public void d(List<hjd> list) {
        if (list == null) {
            this.e = new ArrayList();
        } else {
            this.e = list;
        }
    }

    public List<hjd> a() {
        return this.c;
    }

    public void c(List<hjd> list) {
        if (this.c == null) {
            this.c = new ArrayList();
        } else {
            this.c = list;
        }
    }

    public List<hjg> c() {
        return this.d;
    }

    public void e(List<hjg> list) {
        this.d = list;
    }

    public String toString() {
        return "sportType:" + g() + " lineList size:" + e().size() + " markerDrawBefore size:" + d().size() + " markerDrawAfter size:" + b().size() + " markerNormal size:" + a() + " mHealthMarkerList size:" + c().size();
    }
}
