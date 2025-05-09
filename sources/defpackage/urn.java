package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.inputstream.CipherInputStream;

/* loaded from: classes10.dex */
class urn extends CipherInputStream<uqy> {

    /* renamed from: a, reason: collision with root package name */
    private int f17517a;
    private int b;
    private byte[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private byte[] h;
    private int i;

    public urn(urt urtVar, usq usqVar, char[] cArr, int i, boolean z) throws IOException {
        super(urtVar, usqVar, cArr, i, z);
        this.h = new byte[1];
        this.c = new byte[16];
        this.e = 0;
        this.i = 0;
        this.f = 0;
        this.g = 0;
        this.f17517a = 0;
        this.d = 0;
        this.b = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public uqy initializeDecrypter(usq usqVar, char[] cArr, boolean z) throws IOException {
        return new uqy(usqVar.getAesExtraDataRecord(), cArr, b(usqVar), a(), z);
    }

    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream, java.io.InputStream
    public int read() throws IOException {
        if (read(this.h) == -1) {
            return -1;
        }
        return this.h[0];
    }

    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        this.f = i2;
        this.g = i;
        this.f17517a = 0;
        if (this.i != 0) {
            a(bArr, i);
            int i3 = this.f17517a;
            if (i3 == i2) {
                return i3;
            }
        }
        if (this.f < 16) {
            byte[] bArr2 = this.c;
            int read = super.read(bArr2, 0, bArr2.length);
            this.b = read;
            this.e = 0;
            if (read == -1) {
                this.i = 0;
                int i4 = this.f17517a;
                if (i4 > 0) {
                    return i4;
                }
                return -1;
            }
            this.i = read;
            a(bArr, this.g);
            int i5 = this.f17517a;
            if (i5 == i2) {
                return i5;
            }
        }
        int i6 = this.g;
        int i7 = this.f;
        int read2 = super.read(bArr, i6, i7 - (i7 % 16));
        if (read2 == -1) {
            int i8 = this.f17517a;
            if (i8 > 0) {
                return i8;
            }
            return -1;
        }
        return read2 + this.f17517a;
    }

    private void a(byte[] bArr, int i) {
        int i2 = this.f;
        int i3 = this.i;
        if (i2 >= i3) {
            i2 = i3;
        }
        this.d = i2;
        System.arraycopy(this.c, this.e, bArr, i, i2);
        d(this.d);
        e(this.d);
        int i4 = this.f17517a;
        int i5 = this.d;
        this.f17517a = i4 + i5;
        this.f -= i5;
        this.g += i5;
    }

    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    public void endOfEntryReached(InputStream inputStream, int i) throws IOException {
        b(c(inputStream), i);
    }

    private void b(byte[] bArr, int i) throws IOException {
        byte[] bArr2 = new byte[10];
        System.arraycopy(getDecrypter().c(i), 0, bArr2, 0, 10);
        if (!Arrays.equals(bArr, bArr2)) {
            throw new IOException("Reached end of data for this entry, but aes verification failed");
        }
    }

    protected byte[] c(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[10];
        if (utd.c(inputStream, bArr) == 10) {
            return bArr;
        }
        throw new ZipException("Invalid AES Mac bytes. Could not read sufficient data");
    }

    private byte[] b(usq usqVar) throws IOException {
        if (usqVar.getAesExtraDataRecord() == null) {
            throw new IOException("invalid aes extra data record");
        }
        use aesExtraDataRecord = usqVar.getAesExtraDataRecord();
        if (aesExtraDataRecord.d() == null) {
            throw new IOException("Invalid aes key strength in aes extra data record");
        }
        byte[] bArr = new byte[aesExtraDataRecord.d().getSaltLength()];
        readRaw(bArr);
        return bArr;
    }

    private byte[] a() throws IOException {
        byte[] bArr = new byte[2];
        readRaw(bArr);
        return bArr;
    }

    private void d(int i) {
        int i2 = this.e + i;
        this.e = i2;
        if (i2 >= 15) {
            this.e = 15;
        }
    }

    private void e(int i) {
        int i2 = this.i - i;
        this.i = i2;
        if (i2 <= 0) {
            this.i = 0;
        }
    }
}
