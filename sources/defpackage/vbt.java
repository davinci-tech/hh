package defpackage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import org.eclipse.californium.elements.util.StandardCharsets;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vbt {
    private static final Logger d = vha.a((Class<?>) vbt.class);

    public static void a(OutputStream outputStream) throws IOException {
        outputStream.write(0);
    }

    public static void d(vbo vboVar) {
        vboVar.d((byte) 0);
    }

    public static int c(vbo vboVar, int i, int i2) {
        if (i == 0) {
            throw new IllegalArgumentException("version must not be 0!");
        }
        vboVar.d((byte) i);
        return vboVar.c(i2);
    }

    public static void d(vbo vboVar, int i, int i2) {
        vboVar.d(i, i2);
    }

    public static int a(vbq vbqVar, int i, int i2) {
        if (i == 0) {
            throw new IllegalArgumentException("Version must not be 0!");
        }
        int c = vbqVar.c() & 255;
        if (c == 0) {
            return -1;
        }
        if (c != i) {
            throw new vbl("Version mismatch! " + i + " is required, not " + c + "!", c);
        }
        return vbqVar.c(i2);
    }

    public static int a(vbq vbqVar, e eVar, int i) {
        if (eVar == null) {
            throw new NullPointerException("Version must not be null!");
        }
        int c = vbqVar.c() & 255;
        if (c == 0) {
            return -1;
        }
        if (!eVar.a(c)) {
            throw new vbl("Version mismatch! " + eVar + " are required, not " + c + "!", c);
        }
        return vbqVar.c(i);
    }

    public static void d(vbo vboVar, String str, int i) {
        vboVar.d(str == null ? null : str.getBytes(StandardCharsets.UTF_8), i);
    }

    public static String c(vbq vbqVar, int i) {
        byte[] h = vbqVar.h(i);
        if (h != null) {
            return new String(h, StandardCharsets.UTF_8);
        }
        return null;
    }

    public static void b(vbo vboVar, InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress == null) {
            d(vboVar);
            return;
        }
        int c = c(vboVar, 1, 8);
        vboVar.b(inetSocketAddress.getPort(), 16);
        if (inetSocketAddress.isUnresolved()) {
            vboVar.d((byte) 2);
            vboVar.d(inetSocketAddress.getHostName().getBytes(StandardCharsets.US_ASCII));
        } else {
            vboVar.d((byte) 1);
            vboVar.d(inetSocketAddress.getAddress().getAddress());
        }
        d(vboVar, c, 8);
    }

    public static InetSocketAddress a(vbq vbqVar) {
        int a2 = a(vbqVar, 1, 8);
        if (a2 <= 0) {
            return null;
        }
        vbn b = vbqVar.b(a2);
        int c = b.c(16);
        int c2 = b.c() & 255;
        byte[] i = b.i();
        if (c2 == 1) {
            try {
                return new InetSocketAddress(InetAddress.getByAddress(i), c);
            } catch (UnknownHostException unused) {
                return null;
            }
        }
        if (c2 != 2) {
            return null;
        }
        return new InetSocketAddress(new String(i, StandardCharsets.US_ASCII), c);
    }

    public static class a {
        private final int[] d;

        public a(int... iArr) {
            this(true, iArr);
        }

        protected a(boolean z, int... iArr) {
            if (iArr == null) {
                throw new NullPointerException("Versions must not be null!");
            }
            if (iArr.length == 0) {
                throw new IllegalArgumentException("Versions must not be empty!");
            }
            this.d = z ? Arrays.copyOf(iArr, iArr.length) : iArr;
            if (a(0)) {
                throw new IllegalArgumentException("Versions must not contain NO_VERSION!");
            }
        }

        public boolean a(int i) {
            for (int i2 : this.d) {
                if (i == i2) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return Arrays.toString(this.d);
        }

        public e d() {
            return new e(this.d);
        }
    }

    /* loaded from: classes10.dex */
    public static class e extends a {

        /* renamed from: a, reason: collision with root package name */
        private int f17652a;

        private e(int... iArr) {
            super(false, iArr);
            this.f17652a = 0;
        }

        @Override // vbt.a
        public boolean a(int i) {
            if (super.a(i)) {
                this.f17652a = i;
                return true;
            }
            this.f17652a = 0;
            return false;
        }

        public int a() {
            return this.f17652a;
        }
    }
}
