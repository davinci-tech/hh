package defpackage;

import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class kie {

    /* renamed from: a, reason: collision with root package name */
    private static kie f14385a;
    private static int b;
    private static final Object c = new Object();
    private ByteBuffer d;
    private kie e;

    private kie(int i) {
        this.d = ByteBuffer.allocate(i);
    }

    public ByteBuffer c() {
        return this.d;
    }

    public static kie b(int i) {
        synchronized (c) {
            kie kieVar = f14385a;
            if (kieVar != null) {
                f14385a = kieVar.e;
                kieVar.e = null;
                b--;
                if (kieVar.d.capacity() != i) {
                    kieVar.d = ByteBuffer.allocate(i);
                }
                return kieVar;
            }
            return new kie(i);
        }
    }

    public void b() {
        this.d.clear();
        synchronized (c) {
            int i = b;
            if (i < 1) {
                this.e = f14385a;
                f14385a = this;
                b = i + 1;
            }
        }
    }
}
