package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.io.inputstream.CipherInputStream;
import net.lingala.zip4j.io.inputstream.DecompressedInputStream;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.util.PasswordCallback;

/* loaded from: classes10.dex */
public class uru extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private boolean f17521a;
    private DecompressedInputStream b;
    private CRC32 c;
    private boolean d;
    private byte[] e;
    private usq f;
    private PushbackInputStream g;
    private PasswordCallback h;
    private url i;
    private char[] j;
    private usn m;
    private boolean o;

    public usq a(usm usmVar, boolean z) throws IOException {
        PasswordCallback passwordCallback;
        if (this.f != null && z) {
            d();
        }
        usq c = this.i.c(this.g, this.m.b());
        this.f = c;
        if (c == null) {
            return null;
        }
        if (c.isEncrypted() && this.j == null && (passwordCallback = this.h) != null) {
            a(passwordCallback.getPassword());
        }
        d(this.f);
        this.c.reset();
        if (usmVar != null) {
            this.f.setCrc(usmVar.getCrc());
            this.f.setCompressedSize(usmVar.getCompressedSize());
            this.f.setUncompressedSize(usmVar.getUncompressedSize());
            this.f.setDirectory(usmVar.isDirectory());
            this.d = true;
        } else {
            this.d = false;
        }
        this.b = e(this.f);
        this.f17521a = false;
        return this.f;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.o) {
            throw new IOException("Stream closed");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Negative read length");
        }
        if (i2 == 0) {
            return 0;
        }
        if (this.f == null) {
            return -1;
        }
        try {
            int read = this.b.read(bArr, i, i2);
            if (read == -1) {
                a();
            } else {
                this.c.update(bArr, i, read);
            }
            return read;
        } catch (IOException e) {
            if (b(this.f)) {
                throw new ZipException(e.getMessage(), e.getCause(), ZipException.Type.WRONG_PASSWORD);
            }
            throw e;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.o) {
            return;
        }
        DecompressedInputStream decompressedInputStream = this.b;
        if (decompressedInputStream != null) {
            decompressedInputStream.close();
        }
        this.o = true;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        c();
        return !this.f17521a ? 1 : 0;
    }

    public void a(char[] cArr) {
        this.j = cArr;
    }

    private void a() throws IOException {
        this.b.endOfEntryReached(this.g, this.b.pushBackInputStreamIfNecessary(this.g));
        e();
        i();
        b();
        this.f17521a = true;
    }

    private DecompressedInputStream e(usq usqVar) throws IOException {
        return e(e(new urt(this.g, a(usqVar)), usqVar), usqVar);
    }

    private CipherInputStream<?> e(urt urtVar, usq usqVar) throws IOException {
        if (!usqVar.isEncrypted()) {
            return new urp(urtVar, usqVar, this.j, this.m.a());
        }
        if (usqVar.getEncryptionMethod() == EncryptionMethod.AES) {
            return new urn(urtVar, usqVar, this.j, this.m.a(), this.m.c());
        }
        if (usqVar.getEncryptionMethod() == EncryptionMethod.ZIP_STANDARD) {
            return new urv(urtVar, usqVar, this.j, this.m.a(), this.m.c());
        }
        throw new ZipException(String.format("Entry [%s] Strong Encryption not supported", usqVar.getFileName()), ZipException.Type.UNSUPPORTED_ENCRYPTION);
    }

    private DecompressedInputStream e(CipherInputStream<?> cipherInputStream, usq usqVar) throws ZipException {
        if (utd.b(usqVar) == CompressionMethod.DEFLATE) {
            return new uro(cipherInputStream, this.m.a());
        }
        return new urs(cipherInputStream);
    }

    private void e() throws IOException {
        if (!this.f.isDataDescriptorExists() || this.d) {
            return;
        }
        usj c = this.i.c(this.g, e(this.f.getExtraDataRecords()));
        this.f.setCompressedSize(c.c());
        this.f.setUncompressedSize(c.a());
        this.f.setCrc(c.b());
    }

    private void d(usq usqVar) throws IOException {
        if (a(usqVar.getFileName()) || usqVar.getCompressionMethod() != CompressionMethod.STORE || usqVar.getUncompressedSize() >= 0) {
            return;
        }
        throw new IOException("Invalid local file header for: " + usqVar.getFileName() + ". Uncompressed size has to be set for entry of compression type store which is not a directory");
    }

    private boolean e(List<usp> list) {
        if (list == null) {
            return false;
        }
        Iterator<usp> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().b() == HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue()) {
                return true;
            }
        }
        return false;
    }

    private void i() throws IOException {
        if ((this.f.getEncryptionMethod() == EncryptionMethod.AES && this.f.getAesExtraDataRecord().a().equals(AesVersion.TWO)) || this.f.getCrc() == this.c.getValue()) {
            return;
        }
        ZipException.Type type = ZipException.Type.CHECKSUM_MISMATCH;
        if (b(this.f)) {
            type = ZipException.Type.WRONG_PASSWORD;
        }
        throw new ZipException("Reached end of entry, but crc verification failed for " + this.f.getFileName(), type);
    }

    private void b() {
        this.f = null;
        this.c.reset();
    }

    private boolean a(String str) {
        return str.endsWith("/") || str.endsWith("\\");
    }

    private long a(usq usqVar) throws ZipException {
        if (utd.b(usqVar).equals(CompressionMethod.STORE)) {
            return usqVar.getUncompressedSize();
        }
        if (!usqVar.isDataDescriptorExists() || this.d) {
            return usqVar.getCompressedSize() - c(usqVar);
        }
        return -1L;
    }

    private int c(usq usqVar) throws ZipException {
        if (!usqVar.isEncrypted()) {
            return 0;
        }
        if (usqVar.getEncryptionMethod().equals(EncryptionMethod.AES)) {
            return a(usqVar.getAesExtraDataRecord());
        }
        return usqVar.getEncryptionMethod().equals(EncryptionMethod.ZIP_STANDARD) ? 12 : 0;
    }

    private void d() throws IOException {
        if (this.e == null) {
            this.e = new byte[512];
        }
        while (read(this.e) != -1) {
        }
        this.f17521a = true;
    }

    private int a(use useVar) throws ZipException {
        if (useVar == null || useVar.d() == null) {
            throw new ZipException("AesExtraDataRecord not found or invalid for Aes encrypted entry");
        }
        return useVar.d().getSaltLength() + 12;
    }

    private boolean b(usq usqVar) {
        return usqVar.isEncrypted() && EncryptionMethod.ZIP_STANDARD.equals(usqVar.getEncryptionMethod());
    }

    private void c() throws IOException {
        if (this.o) {
            throw new IOException("Stream closed");
        }
    }
}
