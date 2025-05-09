package health.compact.a;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import com.huawei.hwencryptmodel.rsa.BaseCodec;
import okio.Utf8;

/* loaded from: classes.dex */
public class Base64 extends BaseCodec {
    private final byte[] f;
    private final byte[] g;
    private final int h;
    private final byte[] i;
    private final int j;
    static final byte[] d = {13, 10};
    private static final char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f13103a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] e = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, BaseType.Float, BaseType.Double, 13, BaseType.Vector, BaseType.Obj, BaseType.Union, BaseType.Array, BaseType.Vector64, BaseType.MaxBaseType, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, Utf8.REPLACEMENT_BYTE, -1, 26, 27, 28, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    public Base64() {
        this(0);
    }

    public Base64(boolean z) {
        this(76, d, z);
    }

    public Base64(int i) {
        this(i, d);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public Base64(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.g = e;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                throw new IllegalArgumentException("myLineSeparator must not contain base64 characters: [" + StringUtils.b(bArr) + "]");
            }
            if (i > 0) {
                this.h = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.f = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.h = 4;
                this.f = null;
            }
        } else {
            this.h = 4;
            this.f = null;
        }
        this.j = this.h - 1;
        this.i = z ? b : f13103a;
    }

    public static String a(byte[] bArr) {
        return StringUtils.b(d(bArr, false));
    }

    public static byte[] d(byte[] bArr, boolean z) {
        return a(bArr, z, false);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return a(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, d, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength > i) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i);
        }
        return base64.encode(bArr);
    }

    public static byte[] e(String str) {
        return new Base64().decode(str);
    }

    private static void c(byte[] bArr, int i, int i2, char[] cArr, int i3) {
        byte b2 = bArr[i];
        char[] cArr2 = c;
        cArr[i3] = cArr2[(b2 >>> 2) & 63];
        if (i2 > 2) {
            byte b3 = bArr[i + 1];
            byte b4 = bArr[i + 2];
            cArr[i3 + 1] = cArr2[((b2 << 4) & 48) + ((b3 >>> 4) & 15)];
            cArr[i3 + 2] = cArr2[((b3 << 2) & 60) + ((b4 >>> 6) & 3)];
            cArr[i3 + 3] = cArr2[b4 & Utf8.REPLACEMENT_BYTE];
            return;
        }
        if (i2 > 1) {
            byte b5 = bArr[i + 1];
            cArr[i3 + 1] = cArr2[((b2 << 4) & 48) + ((b5 >>> 4) & 15)];
            cArr[i3 + 2] = cArr2[(b5 << 2) & 60];
            cArr[i3 + 3] = '=';
            return;
        }
        cArr[i3 + 1] = cArr2[(b2 << 4) & 48];
        cArr[i3 + 2] = '=';
        cArr[i3 + 3] = '=';
    }

    public static String c(byte[] bArr, int i, int i2) {
        int i3 = ((i2 + 2) / 3) * 4;
        char[] cArr = new char[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5 += 4) {
            c(bArr, i + i4, i2 - i4, cArr, i5);
            i4 += 3;
        }
        return new String(cArr);
    }

    @Override // com.huawei.hwencryptmodel.rsa.BaseCodec
    public void encode(byte[] bArr, int i, int i2, BaseCodec.e eVar) {
        if (eVar == null || eVar.c) {
            return;
        }
        if (i2 < 0) {
            a(eVar);
            return;
        }
        int i3 = 0;
        while (i3 < i2) {
            byte[] ensureBufferSize = ensureBufferSize(this.h, eVar);
            eVar.b = (eVar.b + 1) % 3;
            int i4 = bArr[i];
            if (i4 < 0) {
                i4 += 256;
            }
            eVar.f6361a = (eVar.f6361a << 8) + i4;
            if (eVar.b == 0) {
                int i5 = eVar.g;
                eVar.g = i5 + 1;
                ensureBufferSize[i5] = this.i[(eVar.f6361a >> 18) & 63];
                int i6 = eVar.g;
                eVar.g = i6 + 1;
                ensureBufferSize[i6] = this.i[(eVar.f6361a >> 12) & 63];
                int i7 = eVar.g;
                eVar.g = i7 + 1;
                ensureBufferSize[i7] = this.i[(eVar.f6361a >> 6) & 63];
                int i8 = eVar.g;
                eVar.g = i8 + 1;
                ensureBufferSize[i8] = this.i[eVar.f6361a & 63];
                eVar.d += 4;
                if (this.lineLength > 0 && this.lineLength <= eVar.d) {
                    System.arraycopy(this.f, 0, ensureBufferSize, eVar.g, this.f.length);
                    eVar.g += this.f.length;
                    eVar.d = 0;
                }
            }
            i3++;
            i++;
        }
    }

    private void a(BaseCodec.e eVar) {
        eVar.c = true;
        if (eVar.b == 0 && this.lineLength == 0) {
            return;
        }
        int i = eVar.g;
        byte[] ensureBufferSize = ensureBufferSize(this.h, eVar);
        int i2 = eVar.b;
        if (i2 != 0) {
            if (i2 == 1) {
                int i3 = eVar.g;
                eVar.g = i3 + 1;
                ensureBufferSize[i3] = this.i[(eVar.f6361a >> 2) & 63];
                int i4 = eVar.g;
                eVar.g = i4 + 1;
                ensureBufferSize[i4] = this.i[(eVar.f6361a << 4) & 63];
                if (this.i == f13103a) {
                    int i5 = eVar.g;
                    eVar.g = i5 + 1;
                    ensureBufferSize[i5] = 61;
                    int i6 = eVar.g;
                    eVar.g = i6 + 1;
                    ensureBufferSize[i6] = 61;
                }
            } else if (i2 == 2) {
                int i7 = eVar.g;
                eVar.g = i7 + 1;
                ensureBufferSize[i7] = this.i[(eVar.f6361a >> 10) & 63];
                int i8 = eVar.g;
                eVar.g = i8 + 1;
                ensureBufferSize[i8] = this.i[(eVar.f6361a >> 4) & 63];
                int i9 = eVar.g;
                eVar.g = i9 + 1;
                ensureBufferSize[i9] = this.i[(eVar.f6361a << 2) & 63];
                if (this.i == f13103a) {
                    int i10 = eVar.g;
                    eVar.g = i10 + 1;
                    ensureBufferSize[i10] = 61;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + eVar.b);
            }
        }
        eVar.d += eVar.g - i;
        if (this.lineLength <= 0 || eVar.d <= 0) {
            return;
        }
        System.arraycopy(this.f, 0, ensureBufferSize, eVar.g, this.f.length);
        eVar.g += this.f.length;
    }

    @Override // com.huawei.hwencryptmodel.rsa.BaseCodec
    public void decode(byte[] bArr, int i, int i2, BaseCodec.e eVar) {
        byte b2;
        if (eVar.c) {
            return;
        }
        if (i2 < 0) {
            eVar.c = true;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            byte b3 = bArr[i];
            if (b3 == 61) {
                eVar.c = true;
                break;
            }
            if (b3 >= 0) {
                byte[] bArr2 = e;
                if (b3 < bArr2.length && (b2 = bArr2[b3]) >= 0) {
                    eVar.b = (eVar.b + 1) % 4;
                    eVar.f6361a = (eVar.f6361a << 6) + b2;
                    if (eVar.b == 0) {
                        byte[] ensureBufferSize = ensureBufferSize(this.j, eVar);
                        int i4 = eVar.g;
                        eVar.g = i4 + 1;
                        ensureBufferSize[i4] = (byte) ((eVar.f6361a >> 16) & 255);
                        int i5 = eVar.g;
                        eVar.g = i5 + 1;
                        ensureBufferSize[i5] = (byte) ((eVar.f6361a >> 8) & 255);
                        int i6 = eVar.g;
                        eVar.g = i6 + 1;
                        ensureBufferSize[i6] = (byte) (eVar.f6361a & 255);
                    }
                }
            }
            i3++;
            i++;
        }
        c(eVar);
    }

    private void c(BaseCodec.e eVar) {
        if (!eVar.c || eVar.b == 0) {
            return;
        }
        byte[] ensureBufferSize = ensureBufferSize(this.j, eVar);
        int i = eVar.b;
        if (i != 1) {
            if (i == 2) {
                eVar.f6361a >>= 4;
                int i2 = eVar.g;
                eVar.g = i2 + 1;
                ensureBufferSize[i2] = (byte) (eVar.f6361a & 255);
                return;
            }
            if (i == 3) {
                eVar.f6361a >>= 2;
                int i3 = eVar.g;
                eVar.g = i3 + 1;
                ensureBufferSize[i3] = (byte) ((eVar.f6361a >> 8) & 255);
                int i4 = eVar.g;
                eVar.g = i4 + 1;
                ensureBufferSize[i4] = (byte) (eVar.f6361a & 255);
                return;
            }
            throw new IllegalStateException("Impossible modulus " + eVar.b);
        }
    }

    @Override // com.huawei.hwencryptmodel.rsa.BaseCodec
    public boolean isInAlphabet(byte b2) {
        if (b2 >= 0) {
            byte[] bArr = this.g;
            if (b2 < bArr.length && bArr[b2] != -1) {
                return true;
            }
        }
        return false;
    }
}
