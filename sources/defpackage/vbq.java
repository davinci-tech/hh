package defpackage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes7.dex */
public class vbq {
    protected final InputStream b;
    protected int c;
    protected byte e;

    protected static class c extends ByteArrayInputStream {
        protected c(byte[] bArr) {
            super(bArr);
        }

        protected c(byte[] bArr, int i, int i2) {
            super(bArr, i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public c b(int i) {
            int i2 = this.pos;
            long j = i;
            long skip = skip(j);
            if (skip < j) {
                throw new IllegalArgumentException("requested " + i + " bytes exceeds available " + skip + " bytes.");
            }
            return new c(this.buf, i2, i);
        }
    }

    public vbq(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException("byte stream must not be null!");
        }
        this.b = inputStream;
        this.e = (byte) 0;
        this.c = -1;
    }

    public void b() {
        try {
            this.b.close();
        } catch (IOException unused) {
        }
        this.e = (byte) 0;
        this.c = -1;
    }

    public long e(long j) {
        int i;
        int i2 = this.c;
        int i3 = 0;
        if (i2 >= 0) {
            i = i2 + 1;
            j -= i;
            this.c = -1;
        } else {
            i = 0;
        }
        int i4 = (int) (7 & j);
        long j2 = j / 8;
        long c2 = c(j2);
        if (c2 < 0) {
            return i;
        }
        if (c2 >= j2) {
            try {
                e();
                this.c -= i4;
                i3 = i4;
            } catch (IllegalArgumentException unused) {
            }
        }
        return (c2 * 8) + i3 + i;
    }

    public long d(int i) {
        if (i < 0 || i > 64) {
            throw new IllegalArgumentException("bits must be in range 0 ... 64!");
        }
        long j = 0;
        if (this.c >= 0 || (i & 7) != 0) {
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                if (this.c < 0) {
                    e();
                }
                byte b = this.e;
                int i2 = this.c;
                if (((b >> i2) & 1) != 0) {
                    j |= 1 << i;
                }
                this.c = i2 - 1;
            }
        } else {
            for (int i3 = 0; i3 < i; i3 += 8) {
                j = (j << 8) | d();
            }
        }
        return j;
    }

    public int c(int i) {
        if (i < 0 || i > 32) {
            throw new IllegalArgumentException("bits must be in range 0 ... 32!");
        }
        int i2 = 0;
        if (this.c >= 0 || (i & 7) != 0) {
            for (int i3 = i - 1; i3 >= 0; i3--) {
                if (this.c < 0) {
                    e();
                }
                byte b = this.e;
                int i4 = this.c;
                if (((b >> i4) & 1) != 0) {
                    i2 |= 1 << i3;
                }
                this.c = i4 - 1;
            }
        } else {
            for (int i5 = 0; i5 < i; i5 += 8) {
                i2 = (i2 << 8) | d();
            }
        }
        return i2;
    }

    public byte[] a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Count " + i + " must not be negative!");
        }
        if (i == 0) {
            return vbj.c;
        }
        byte[] bArr = new byte[i];
        if (this.c >= 0) {
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) c(8);
            }
        } else {
            a(bArr, 0, i, true);
        }
        return bArr;
    }

    public byte c() {
        int d;
        if (this.c >= 0) {
            d = c(8);
        } else {
            d = d();
        }
        return (byte) d;
    }

    public byte[] h(int i) {
        int d = vbo.d(i);
        int a2 = vbo.a(d);
        int c2 = c(d);
        if (c2 == a2) {
            return null;
        }
        return a(c2);
    }

    public vbn b(int i) {
        return new vbn(e(i));
    }

    public ByteArrayInputStream e(int i) {
        if (this.c > 0) {
            throw new IllegalStateException(this.c + " bits unread!");
        }
        InputStream inputStream = this.b;
        if (inputStream instanceof c) {
            return ((c) inputStream).b(i);
        }
        byte[] bArr = new byte[i];
        a(bArr, 0, i, true);
        return new c(bArr);
    }

    private long c(long j) {
        try {
            return this.b.skip(j);
        } catch (IOException unused) {
            return -1L;
        }
    }

    private void e() {
        this.e = (byte) d();
        this.c = 7;
    }

    private int d() {
        try {
            int read = this.b.read();
            if (read >= 0) {
                return read;
            }
            throw new IllegalArgumentException("requested byte exceeds available bytes!");
        } catch (IOException e) {
            throw new IllegalArgumentException("request byte fails!", e);
        }
    }

    private int a(byte[] bArr, int i, int i2, boolean z) {
        int i3 = 0;
        int i4 = i2;
        while (i2 > 0) {
            try {
                int read = this.b.read(bArr, i + i3, i4);
                if (read <= 0) {
                    break;
                }
                i3 += read;
                i4 -= read;
                if (!z) {
                    break;
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("request bytes fails!", e);
            }
        }
        if (i3 >= i2) {
            return i3;
        }
        throw new IllegalArgumentException("requested " + i2 + " bytes exceeds available " + i3 + " bytes.");
    }
}
