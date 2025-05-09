package com.huawei.devicesdk.util.rsa;

import defpackage.bmh;
import defpackage.bna;
import defpackage.bnb;

/* loaded from: classes3.dex */
public abstract class BaseCodec implements BinaryEncoder, BinaryDecoder {
    public static final String BASE_ROOT_INFO = "67CeEsCdM8UbcYpdKUEGhxRdwBmol2/q";
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final int EOF = -1;
    protected static final int MASK_8BITS = 255;
    public static final int MIME_CHUNK_SIZE = 76;
    protected static final byte PAD = 61;
    private final int chunkSeparatorLength;
    private final int encodedBlockSize;
    protected final int lineLength;
    private final int unencodedBlockSize;

    protected abstract void decode(byte[] bArr, int i, int i2, b bVar);

    protected abstract void encode(byte[] bArr, int i, int i2, b bVar);

    protected int getDefaultBufferSize() {
        return 8192;
    }

    protected abstract boolean isInAlphabet(byte b2);

    public BaseCodec(int i, int i2, int i3, int i4) {
        this.unencodedBlockSize = i;
        this.encodedBlockSize = i2;
        this.lineLength = (i3 <= 0 || i4 <= 0 || i2 == 0) ? 0 : (i3 / i2) * i2;
        this.chunkSeparatorLength = i4;
    }

    int available(b bVar) {
        if (bVar.e != null) {
            return bVar.j - bVar.g;
        }
        return 0;
    }

    private byte[] resizeBuffer(b bVar) {
        if (bVar.e == null) {
            bVar.e = new byte[getDefaultBufferSize()];
            bVar.j = 0;
            bVar.g = 0;
        } else {
            byte[] bArr = new byte[bVar.e.length * 2];
            System.arraycopy(bVar.e, 0, bArr, 0, bVar.e.length);
            bVar.e = bArr;
        }
        return bVar.e;
    }

    protected byte[] ensureBufferSize(int i, b bVar) {
        if (bVar.e == null || bVar.e.length < bVar.j + i) {
            return resizeBuffer(bVar);
        }
        return bVar.e;
    }

    int readResults(byte[] bArr, int i, int i2, b bVar) {
        if (bVar.e == null) {
            return bVar.c ? -1 : 0;
        }
        int min = Math.min(available(bVar), i2);
        System.arraycopy(bVar.e, bVar.g, bArr, i, min);
        bVar.g += min;
        if (bVar.g >= bVar.j) {
            bVar.e = null;
        }
        return min;
    }

    @Override // com.huawei.devicesdk.util.rsa.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        b bVar = new b();
        encode(bArr, 0, bArr.length, bVar);
        encode(bArr, 0, -1, bVar);
        int i = bVar.j - bVar.g;
        byte[] bArr2 = new byte[i];
        readResults(bArr2, 0, i, bVar);
        return bArr2;
    }

    @Override // com.huawei.devicesdk.util.rsa.Encoder
    public Object encode(Object obj) throws bnb {
        if (!(obj instanceof byte[])) {
            throw new bnb("Parameter supplied to Base-N encode is not a byte[]");
        }
        return encode((byte[]) obj);
    }

    public String encodeToString(byte[] bArr) {
        return bmh.e(encode(bArr));
    }

    @Override // com.huawei.devicesdk.util.rsa.Decoder
    public Object decode(Object obj) throws bna {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new bna("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String str) {
        return decode(bmh.d(str));
    }

    @Override // com.huawei.devicesdk.util.rsa.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        b bVar = new b();
        decode(bArr, 0, bArr.length, bVar);
        decode(bArr, 0, -1, bVar);
        int i = bVar.j;
        byte[] bArr2 = new byte[i];
        readResults(bArr2, 0, i, bVar);
        return bArr2;
    }

    protected boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b2 : bArr) {
            if (b2 == 61 || isInAlphabet(b2)) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] bArr) {
        int i;
        if (bArr == null || (i = this.unencodedBlockSize) == 0) {
            return 0L;
        }
        long length = (((bArr.length + i) - 1) / i) * this.encodedBlockSize;
        int i2 = this.lineLength;
        if (i2 <= 0) {
            return length;
        }
        long j = i2;
        return length + ((((j + length) - 1) / j) * this.chunkSeparatorLength);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public int f1950a;
        public int b;
        public boolean c;
        public int d;
        byte[] e;
        int g;
        public int j;

        b() {
        }
    }
}
