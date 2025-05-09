package com.huawei.hwencryptmodel.rsa;

import health.compact.a.DecoderException;
import health.compact.a.EncoderException;
import health.compact.a.StringUtils;

/* loaded from: classes.dex */
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

    protected abstract void decode(byte[] bArr, int i, int i2, e eVar);

    protected abstract void encode(byte[] bArr, int i, int i2, e eVar);

    protected int getDefaultBufferSize() {
        return 8192;
    }

    protected abstract boolean isInAlphabet(byte b);

    public BaseCodec(int i, int i2, int i3, int i4) {
        this.unencodedBlockSize = i;
        this.encodedBlockSize = i2;
        this.lineLength = (i3 <= 0 || i4 <= 0) ? 0 : (i3 / i2) * i2;
        this.chunkSeparatorLength = i4;
    }

    int available(e eVar) {
        if (eVar.e != null) {
            return eVar.g - eVar.i;
        }
        return 0;
    }

    private byte[] resizeBuffer(e eVar) {
        if (eVar.e == null) {
            eVar.e = new byte[getDefaultBufferSize()];
            eVar.g = 0;
            eVar.i = 0;
        } else {
            byte[] bArr = new byte[eVar.e.length * 2];
            System.arraycopy(eVar.e, 0, bArr, 0, eVar.e.length);
            eVar.e = bArr;
        }
        return eVar.e;
    }

    protected byte[] ensureBufferSize(int i, e eVar) {
        if (eVar.e == null || eVar.e.length < eVar.g + i) {
            return resizeBuffer(eVar);
        }
        return eVar.e;
    }

    int readResults(byte[] bArr, int i, int i2, e eVar) {
        if (eVar.e == null) {
            return eVar.c ? -1 : 0;
        }
        int min = Math.min(available(eVar), i2);
        System.arraycopy(eVar.e, eVar.i, bArr, i, min);
        eVar.i += min;
        if (eVar.i >= eVar.g) {
            eVar.e = null;
        }
        return min;
    }

    @Override // com.huawei.hwencryptmodel.rsa.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        e eVar = new e();
        encode(bArr, 0, bArr.length, eVar);
        encode(bArr, 0, -1, eVar);
        int i = eVar.g - eVar.i;
        byte[] bArr2 = new byte[i];
        readResults(bArr2, 0, i, eVar);
        return bArr2;
    }

    @Override // com.huawei.hwencryptmodel.rsa.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof byte[])) {
            throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
        }
        return encode((byte[]) obj);
    }

    public String encodeToString(byte[] bArr) {
        return StringUtils.b(encode(bArr));
    }

    @Override // com.huawei.hwencryptmodel.rsa.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String str) {
        return decode(StringUtils.b(str));
    }

    @Override // com.huawei.hwencryptmodel.rsa.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        e eVar = new e();
        decode(bArr, 0, bArr.length, eVar);
        decode(bArr, 0, -1, eVar);
        int i = eVar.g;
        byte[] bArr2 = new byte[i];
        readResults(bArr2, 0, i, eVar);
        return bArr2;
    }

    protected boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (b == 61 || isInAlphabet(b)) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] bArr) {
        int length = bArr.length;
        int i = this.unencodedBlockSize;
        long j = (((length + i) - 1) / i) * this.encodedBlockSize;
        int i2 = this.lineLength;
        if (i2 <= 0) {
            return j;
        }
        long j2 = i2;
        return j + ((((j2 + j) - 1) / j2) * this.chunkSeparatorLength);
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        public int f6361a;
        public int b;
        public boolean c;
        public int d;
        byte[] e;
        public int g;
        int i;

        e() {
        }
    }
}
