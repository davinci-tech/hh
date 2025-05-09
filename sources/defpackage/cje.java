package defpackage;

import com.huawei.health.device.util.EventBus;
import defpackage.cgt;

/* loaded from: classes3.dex */
public class cje {

    /* renamed from: a, reason: collision with root package name */
    private cxh f739a;
    private EventBus.b b;
    private cgp c;
    private cgt.d d;
    private String e;
    private String g;
    private int i;
    private int j;

    public cje() {
        this.i = -1;
    }

    public EventBus.b e() {
        return this.b;
    }

    public int g() {
        return this.j;
    }

    public cxh b() {
        return this.f739a;
    }

    public cgt.d c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String j() {
        return this.g;
    }

    public cgp a() {
        return this.c;
    }

    public int f() {
        return this.i;
    }

    public String toString() {
        return "EventBusBean {mEvent = " + this.b + ", mType = " + this.j + ", mDevice = " + this.f739a + ", mDeviceSsid = " + this.e + ", mProductId = " + this.g + ", mDeviceVersion = " + this.c + ", mUnitType = " + this.i + '}';
    }

    private cje(a aVar) {
        this.i = -1;
        this.b = aVar.d;
        this.j = aVar.f;
        this.f739a = aVar.e;
        this.d = aVar.c;
        this.e = aVar.b;
        this.g = aVar.h;
        this.c = aVar.f740a;
        this.i = aVar.j;
    }

    public a i() {
        return new a().a(this.b).e(this.j).d(this.f739a).d(this.d).e(this.e).a(this.g).d(this.c).d(this.i);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private cgp f740a;
        private String b;
        private cgt.d c;
        private EventBus.b d;
        private cxh e;
        private int f;
        private String h;
        private int j;

        public a a(EventBus.b bVar) {
            this.d = bVar;
            return this;
        }

        public a e(int i) {
            this.f = i;
            return this;
        }

        public a d(cxh cxhVar) {
            this.e = cxhVar;
            return this;
        }

        public a d(cgt.d dVar) {
            this.c = dVar;
            return this;
        }

        public a e(String str) {
            this.b = str;
            return this;
        }

        public a a(String str) {
            this.h = str;
            return this;
        }

        public a d(cgp cgpVar) {
            this.f740a = cgpVar;
            return this;
        }

        public a d(int i) {
            this.j = i;
            return this;
        }

        public cje e() {
            return new cje(this);
        }
    }
}
