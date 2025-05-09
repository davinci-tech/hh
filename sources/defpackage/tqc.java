package defpackage;

import com.huawei.wearengine.device.Device;

/* loaded from: classes9.dex */
public class tqc {

    /* renamed from: a, reason: collision with root package name */
    private Device f17343a;
    private String d;
    private String e;

    protected tqc(b bVar) {
        this.f17343a = bVar.e;
        this.d = bVar.b;
        this.e = bVar.c;
    }

    public static class b {
        private String b;
        private String c;
        private Device e;

        public b b(Device device) {
            this.e = device;
            return this;
        }

        public b d(String str) {
            this.b = str;
            return this;
        }

        public b a(String str) {
            this.c = str;
            return this;
        }

        public tqc c() {
            return new tqc(this);
        }
    }

    public Device e() {
        return this.f17343a;
    }

    public String b() {
        return this.d;
    }

    public String a() {
        return this.e;
    }
}
