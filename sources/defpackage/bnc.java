package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.util.rsa.BaseCodec;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import okio.Utf8;

/* loaded from: classes3.dex */
public class bnc extends BaseCodec {
    private final int f;
    private final byte[] g;
    private final byte[] h;
    private final byte[] i;
    private final int j;
    static final byte[] e = {13, 10};
    private static final char[] d = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f441a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, BaseType.Float, BaseType.Double, 13, BaseType.Vector, BaseType.Obj, BaseType.Union, BaseType.Array, BaseType.Vector64, BaseType.MaxBaseType, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, Utf8.REPLACEMENT_BYTE, -1, 26, 27, 28, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    public bnc() {
        this(0);
    }

    public bnc(boolean z) {
        this(76, e, z);
    }

    public bnc(int i) {
        this(i, e);
    }

    public bnc(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public bnc(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.h = b;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                throw new IllegalArgumentException("myLineSeparator must not contain base64 characters: [" + bmh.e(bArr) + "]");
            }
            if (i > 0) {
                this.f = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.i = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.f = 4;
                this.i = null;
            }
        } else {
            this.f = 4;
            this.i = null;
        }
        this.j = this.f - 1;
        this.g = z ? c : f441a;
    }

    public static String a(byte[] bArr) {
        return bmh.e(c(bArr, false));
    }

    public static byte[] c(byte[] bArr, boolean z) {
        return a(bArr, z, false);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return c(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] c(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        bnc bncVar = z ? new bnc(z2) : new bnc(0, e, z2);
        long encodedLength = bncVar.getEncodedLength(bArr);
        if (encodedLength > i) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i);
        }
        return bncVar.encode(bArr);
    }

    public static byte[] e(String str) {
        return new bnc().decode(str);
    }

    @Override // com.huawei.devicesdk.util.rsa.BaseCodec
    public void encode(byte[] bArr, int i, int i2, BaseCodec.b bVar) {
        if (bVar == null || bVar.c) {
            return;
        }
        if (i2 < 0) {
            d(bVar);
            return;
        }
        int i3 = 0;
        while (i3 < i2) {
            byte[] ensureBufferSize = ensureBufferSize(this.f, bVar);
            bVar.f1950a = (bVar.f1950a + 1) % 3;
            int i4 = bArr[i];
            if (i4 < 0) {
                i4 += 256;
            }
            bVar.b = (bVar.b << 8) + i4;
            if (bVar.f1950a == 0) {
                int i5 = bVar.j;
                bVar.j = i5 + 1;
                ensureBufferSize[i5] = this.g[(bVar.b >> 18) & 63];
                int i6 = bVar.j;
                bVar.j = i6 + 1;
                ensureBufferSize[i6] = this.g[(bVar.b >> 12) & 63];
                int i7 = bVar.j;
                bVar.j = i7 + 1;
                ensureBufferSize[i7] = this.g[(bVar.b >> 6) & 63];
                int i8 = bVar.j;
                bVar.j = i8 + 1;
                ensureBufferSize[i8] = this.g[bVar.b & 63];
                bVar.d += 4;
                if (this.lineLength > 0 && this.lineLength <= bVar.d) {
                    System.arraycopy(this.i, 0, ensureBufferSize, bVar.j, this.i.length);
                    bVar.j += this.i.length;
                    bVar.d = 0;
                }
            }
            i3++;
            i++;
        }
    }

    private void d(BaseCodec.b bVar) {
        bVar.c = true;
        if (bVar.f1950a == 0 && this.lineLength == 0) {
            return;
        }
        int i = bVar.j;
        byte[] ensureBufferSize = ensureBufferSize(this.f, bVar);
        int i2 = bVar.f1950a;
        if (i2 != 0) {
            if (i2 == 1) {
                int i3 = bVar.j;
                bVar.j = i3 + 1;
                ensureBufferSize[i3] = this.g[(bVar.b >> 2) & 63];
                int i4 = bVar.j;
                bVar.j = i4 + 1;
                ensureBufferSize[i4] = this.g[(bVar.b << 4) & 63];
                if (this.g == f441a) {
                    int i5 = bVar.j;
                    bVar.j = i5 + 1;
                    ensureBufferSize[i5] = 61;
                    int i6 = bVar.j;
                    bVar.j = i6 + 1;
                    ensureBufferSize[i6] = 61;
                }
            } else if (i2 == 2) {
                int i7 = bVar.j;
                bVar.j = i7 + 1;
                ensureBufferSize[i7] = this.g[(bVar.b >> 10) & 63];
                int i8 = bVar.j;
                bVar.j = i8 + 1;
                ensureBufferSize[i8] = this.g[(bVar.b >> 4) & 63];
                int i9 = bVar.j;
                bVar.j = i9 + 1;
                ensureBufferSize[i9] = this.g[(bVar.b << 2) & 63];
                if (this.g == f441a) {
                    int i10 = bVar.j;
                    bVar.j = i10 + 1;
                    ensureBufferSize[i10] = 61;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + bVar.f1950a);
            }
        }
        bVar.d += bVar.j - i;
        if (this.lineLength <= 0 || bVar.d <= 0) {
            return;
        }
        System.arraycopy(this.i, 0, ensureBufferSize, bVar.j, this.i.length);
        bVar.j += this.i.length;
    }

    @Override // com.huawei.devicesdk.util.rsa.BaseCodec
    public void decode(byte[] bArr, int i, int i2, BaseCodec.b bVar) {
        byte b2;
        if (bVar.c) {
            return;
        }
        if (i2 < 0) {
            bVar.c = true;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            byte b3 = bArr[i];
            if (b3 == 61) {
                bVar.c = true;
                break;
            }
            if (b3 >= 0) {
                byte[] bArr2 = b;
                if (b3 < bArr2.length && (b2 = bArr2[b3]) >= 0) {
                    bVar.f1950a = (bVar.f1950a + 1) % 4;
                    bVar.b = (bVar.b << 6) + b2;
                    if (bVar.f1950a == 0) {
                        byte[] ensureBufferSize = ensureBufferSize(this.j, bVar);
                        int i4 = bVar.j;
                        bVar.j = i4 + 1;
                        ensureBufferSize[i4] = (byte) ((bVar.b >> 16) & 255);
                        int i5 = bVar.j;
                        bVar.j = i5 + 1;
                        ensureBufferSize[i5] = (byte) ((bVar.b >> 8) & 255);
                        int i6 = bVar.j;
                        bVar.j = i6 + 1;
                        ensureBufferSize[i6] = (byte) (bVar.b & 255);
                    }
                }
            }
            i3++;
            i++;
        }
        e(bVar);
    }

    private void e(BaseCodec.b bVar) {
        if (!bVar.c || bVar.f1950a == 0) {
            return;
        }
        byte[] ensureBufferSize = ensureBufferSize(this.j, bVar);
        int i = bVar.f1950a;
        if (i != 1) {
            if (i == 2) {
                bVar.b >>= 4;
                int i2 = bVar.j;
                bVar.j = i2 + 1;
                ensureBufferSize[i2] = (byte) (bVar.b & 255);
                return;
            }
            if (i == 3) {
                bVar.b >>= 2;
                int i3 = bVar.j;
                bVar.j = i3 + 1;
                ensureBufferSize[i3] = (byte) ((bVar.b >> 8) & 255);
                int i4 = bVar.j;
                bVar.j = i4 + 1;
                ensureBufferSize[i4] = (byte) (bVar.b & 255);
                return;
            }
            throw new IllegalStateException("Impossible modulus " + bVar.f1950a);
        }
    }

    @Override // com.huawei.devicesdk.util.rsa.BaseCodec
    public boolean isInAlphabet(byte b2) {
        if (b2 >= 0) {
            byte[] bArr = this.h;
            if (b2 < bArr.length && bArr[b2] != -1) {
                return true;
            }
        }
        return false;
    }
}
