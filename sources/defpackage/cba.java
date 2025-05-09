package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class cba {
    private static d c = new d();

    /* renamed from: a, reason: collision with root package name */
    private int f588a;
    private int b;
    private int d;
    private int e;
    private int f;

    public static int c() {
        return 36;
    }

    private cba() {
    }

    public cba(byte[] bArr) {
        if (bArr == null || bArr.length != c()) {
            return;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        this.e = wrap.getInt();
        this.f = wrap.getInt();
        this.f588a = wrap.getInt();
        this.b = wrap.getInt();
        this.d = wrap.getInt();
    }

    public int d() {
        return this.e;
    }

    public int j() {
        return this.f588a;
    }

    public int a() {
        return this.d;
    }

    public static int b() {
        return c.d();
    }

    public byte[] e() {
        ByteBuffer allocate = ByteBuffer.allocate(c());
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(this.e);
        allocate.putInt(this.f);
        allocate.putInt(this.f588a);
        allocate.putInt(this.b);
        allocate.putInt(this.d);
        allocate.flip();
        return allocate.array();
    }

    public String toString() {
        return "MsgHeader{mBusinessType=" + this.e + ", mVersion=" + this.f + ", mTotalLength=" + this.f588a + ", mBusinessHeadLength=" + this.b + ", mMessageId=" + this.d + '}';
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private int f589a;
        private int b;
        private int c;
        private int d;
        private int e;

        public b e(int i) {
            this.d = i;
            return this;
        }

        public b b(int i) {
            this.e = i;
            return this;
        }

        public b d(int i) {
            this.f589a = i;
            return this;
        }

        public b a(int i) {
            this.b = i;
            return this;
        }

        public b c(int i) {
            this.c = i;
            return this;
        }

        public cba a() {
            cba cbaVar = new cba();
            cbaVar.e = this.d;
            cbaVar.f = this.e;
            cbaVar.f588a = cba.c() + this.f589a + this.b;
            cbaVar.b = this.f589a;
            cbaVar.d = this.c;
            return cbaVar;
        }
    }

    static class d {
        int c;

        private d() {
            this.c = 0;
        }

        protected int d() {
            int i;
            synchronized (this) {
                int i2 = this.c + 1;
                this.c = i2;
                i = i2 % 1000000;
                this.c = i;
            }
            return i;
        }
    }
}
