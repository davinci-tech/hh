package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.t9;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes9.dex */
public final class x9 implements Closeable {
    public static final Logger e = Logger.getLogger(u9.class.getName());

    /* renamed from: a, reason: collision with root package name */
    public final db f5572a;
    public final a b;
    public final boolean c;
    public final t9.a d;

    public interface b {
        void a();

        void a(int i, int i2, int i3, boolean z);

        void a(int i, int i2, List<s9> list) throws IOException;

        void a(int i, long j);

        void a(int i, r9 r9Var);

        void a(int i, r9 r9Var, eb ebVar);

        void a(int i, String str, eb ebVar, String str2, int i2, long j);

        void a(boolean z, int i, int i2);

        void a(boolean z, int i, int i2, List<s9> list);

        void a(boolean z, int i, db dbVar, int i2) throws IOException;

        void a(boolean z, ca caVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5572a.close();
    }

    public boolean a(boolean z, b bVar) throws IOException {
        try {
            this.f5572a.i(9L);
            int a2 = a(this.f5572a);
            if (a2 < 0 || a2 > 16384) {
                throw u9.b("FRAME_SIZE_ERROR: %s", Integer.valueOf(a2));
            }
            byte readByte = (byte) (this.f5572a.readByte() & 255);
            if (z && readByte != 4) {
                throw u9.b("Expected a SETTINGS frame but was %s", Byte.valueOf(readByte));
            }
            byte readByte2 = (byte) (this.f5572a.readByte() & 255);
            int readInt = this.f5572a.readInt() & Integer.MAX_VALUE;
            Logger logger = e;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(u9.a(true, readInt, a2, readByte, readByte2));
            }
            switch (readByte) {
                case 0:
                    a(bVar, a2, readByte2, readInt);
                    return true;
                case 1:
                    c(bVar, a2, readByte2, readInt);
                    return true;
                case 2:
                    e(bVar, a2, readByte2, readInt);
                    return true;
                case 3:
                    g(bVar, a2, readByte2, readInt);
                    return true;
                case 4:
                    h(bVar, a2, readByte2, readInt);
                    return true;
                case 5:
                    f(bVar, a2, readByte2, readInt);
                    return true;
                case 6:
                    d(bVar, a2, readByte2, readInt);
                    return true;
                case 7:
                    b(bVar, a2, readByte2, readInt);
                    return true;
                case 8:
                    i(bVar, a2, readByte2, readInt);
                    return true;
                default:
                    this.f5572a.skip(a2);
                    return true;
            }
        } catch (EOFException unused) {
            return false;
        }
    }

    public void a(b bVar) throws IOException {
        if (this.c) {
            if (!a(true, bVar)) {
                throw u9.b("Required SETTINGS preface not received", new Object[0]);
            }
            return;
        }
        eb d = this.f5572a.d(u9.f5523a.j());
        Logger logger = e;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(f8.a("<< CONNECTION %s", d.d()));
        }
        if (!u9.f5523a.equals(d)) {
            throw u9.b("Expected a connection header but was %s", d.n());
        }
    }

    private void i(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            throw u9.b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
        }
        long readInt = this.f5572a.readInt() & 2147483647L;
        if (readInt == 0) {
            throw u9.b("windowSizeIncrement was 0", Long.valueOf(readInt));
        }
        bVar.a(i2, readInt);
    }

    private void h(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 != 0) {
            throw u9.b("TYPE_SETTINGS streamId != 0", new Object[0]);
        }
        if ((b2 & 1) != 0) {
            if (i != 0) {
                throw u9.b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            bVar.a();
            return;
        }
        if (i % 6 != 0) {
            throw u9.b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
        }
        ca caVar = new ca();
        for (int i3 = 0; i3 < i; i3 += 6) {
            int readShort = this.f5572a.readShort() & 65535;
            int readInt = this.f5572a.readInt();
            if (readShort != 2) {
                if (readShort == 3) {
                    readShort = 4;
                } else if (readShort == 4) {
                    if (readInt < 0) {
                        throw u9.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                    }
                    readShort = 7;
                } else if (readShort == 5 && (readInt < 16384 || readInt > 16777215)) {
                    throw u9.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(readInt));
                }
            } else if (readInt != 0 && readInt != 1) {
                throw u9.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
            }
            caVar.a(readShort, readInt);
        }
        bVar.a(false, caVar);
    }

    private void g(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            throw u9.b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
        }
        if (i2 == 0) {
            throw u9.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        int readInt = this.f5572a.readInt();
        r9 a2 = r9.a(readInt);
        if (a2 == null) {
            throw u9.b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(readInt));
        }
        bVar.a(i2, a2);
    }

    private void f(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            throw u9.b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        short readByte = (b2 & 8) != 0 ? (short) (this.f5572a.readByte() & 255) : (short) 0;
        bVar.a(i2, this.f5572a.readInt() & Integer.MAX_VALUE, a(a(i - 4, b2, readByte), readByte, b2, i2));
    }

    private void e(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 5) {
            throw u9.b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
        }
        if (i2 == 0) {
            throw u9.b("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        a(bVar, i2);
    }

    private void d(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 8) {
            throw u9.b("TYPE_PING length != 8: %s", Integer.valueOf(i));
        }
        if (i2 != 0) {
            throw u9.b("TYPE_PING streamId != 0", new Object[0]);
        }
        bVar.a((b2 & 1) != 0, this.f5572a.readInt(), this.f5572a.readInt());
    }

    private void c(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            throw u9.b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        short readByte = (b2 & 8) != 0 ? (short) (this.f5572a.readByte() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            a(bVar, i2);
            i -= 5;
        }
        bVar.a(z, i2, -1, a(a(i, b2, readByte), readByte, b2, i2));
    }

    private void b(b bVar, int i, byte b2, int i2) throws IOException {
        if (i < 8) {
            throw u9.b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
        }
        if (i2 != 0) {
            throw u9.b("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        int readInt = this.f5572a.readInt();
        int readInt2 = this.f5572a.readInt();
        int i3 = i - 8;
        r9 a2 = r9.a(readInt2);
        if (a2 == null) {
            throw u9.b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(readInt2));
        }
        eb ebVar = eb.f;
        if (i3 > 0) {
            ebVar = this.f5572a.d(i3);
        }
        bVar.a(readInt, a2, ebVar);
    }

    private void a(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            throw u9.b("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        if ((b2 & 32) != 0) {
            throw u9.b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        short readByte = (b2 & 8) != 0 ? (short) (this.f5572a.readByte() & 255) : (short) 0;
        bVar.a(z, i2, this.f5572a, a(i, b2, readByte));
        this.f5572a.skip(readByte);
    }

    public static final class a implements zb {

        /* renamed from: a, reason: collision with root package name */
        public final db f5573a;
        public int b;
        public byte c;
        public int d;
        public int e;
        public short f;

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
        }

        @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5573a.timeout();
        }

        @Override // com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            while (true) {
                int i = this.e;
                if (i != 0) {
                    long read = this.f5573a.read(bbVar, Math.min(j, i));
                    if (read == -1) {
                        return -1L;
                    }
                    this.e = (int) (this.e - read);
                    return read;
                }
                this.f5573a.skip(this.f);
                this.f = (short) 0;
                if ((this.c & 4) != 0) {
                    return -1L;
                }
                b();
            }
        }

        private void b() throws IOException {
            int i = this.d;
            int a2 = x9.a(this.f5573a);
            this.e = a2;
            this.b = a2;
            byte readByte = (byte) (this.f5573a.readByte() & 255);
            this.c = (byte) (this.f5573a.readByte() & 255);
            if (x9.e.isLoggable(Level.FINE)) {
                x9.e.fine(u9.a(true, this.d, this.b, readByte, this.c));
            }
            int readInt = this.f5573a.readInt() & Integer.MAX_VALUE;
            this.d = readInt;
            if (readByte != 9) {
                throw u9.b("%s != TYPE_CONTINUATION", Byte.valueOf(readByte));
            }
            if (readInt != i) {
                throw u9.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }

        public a(db dbVar) {
            this.f5573a = dbVar;
        }
    }

    private void a(b bVar, int i) throws IOException {
        int readInt = this.f5572a.readInt();
        bVar.a(i, readInt & Integer.MAX_VALUE, (this.f5572a.readByte() & 255) + 1, (Integer.MIN_VALUE & readInt) != 0);
    }

    private List<s9> a(int i, short s, byte b2, int i2) throws IOException {
        a aVar = this.b;
        aVar.e = i;
        aVar.b = i;
        aVar.f = s;
        aVar.c = b2;
        aVar.d = i2;
        this.d.d();
        return this.d.a();
    }

    public static int a(db dbVar) throws IOException {
        return (dbVar.readByte() & 255) | ((dbVar.readByte() & 255) << 16) | ((dbVar.readByte() & 255) << 8);
    }

    public static int a(int i, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        throw u9.b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
    }

    public x9(db dbVar, boolean z) {
        this.f5572a = dbVar;
        this.c = z;
        a aVar = new a(dbVar);
        this.b = aVar;
        this.d = new t9.a(4096, aVar);
    }
}
