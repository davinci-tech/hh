package defpackage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.io.outputstream.CipherOutputStream;
import net.lingala.zip4j.io.outputstream.CompressedOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* loaded from: classes7.dex */
public class usd extends OutputStream {
    private usm b;
    private urx d;
    private CompressedOutputStream e;
    private char[] g;
    private usq i;
    private usn l;
    private boolean m;
    private usu n;
    private urk f = new urk();
    private urr j = new urr();
    private CRC32 c = new CRC32();
    private utf h = new utf();
    private long o = 0;

    /* renamed from: a, reason: collision with root package name */
    private boolean f17526a = true;

    public usd(OutputStream outputStream, char[] cArr, usn usnVar, usu usuVar) throws IOException {
        if (usnVar.a() < 512) {
            throw new IllegalArgumentException("Buffer size cannot be less than 512 bytes");
        }
        urx urxVar = new urx(outputStream);
        this.d = urxVar;
        this.g = cArr;
        this.l = usnVar;
        this.n = e(usuVar, urxVar);
        this.m = false;
        a();
    }

    public void a(ZipParameters zipParameters) throws IOException {
        e(zipParameters);
        ZipParameters c = c(zipParameters);
        d(c);
        this.e = b(c);
        this.f17526a = false;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        b();
        this.c.update(bArr, i, i2);
        this.e.write(bArr, i, i2);
        this.o += i2;
    }

    public usm d() throws IOException {
        this.e.closeEntry();
        long compressedSize = this.e.getCompressedSize();
        this.b.setCompressedSize(compressedSize);
        this.i.setCompressedSize(compressedSize);
        this.b.setUncompressedSize(this.o);
        this.i.setUncompressedSize(this.o);
        if (e(this.b)) {
            this.b.setCrc(this.c.getValue());
            this.i.setCrc(this.c.getValue());
        }
        this.n.c().add(this.i);
        this.n.e().d().add(this.b);
        if (this.i.isDataDescriptorExists()) {
            this.j.c(this.i, this.d);
        }
        c();
        this.f17526a = true;
        return this.b;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.f17526a) {
            d();
        }
        this.n.d().c(this.d.c());
        this.j.a(this.n, this.d, this.l.b());
        this.d.close();
        this.m = true;
    }

    private void b() throws IOException {
        if (this.m) {
            throw new IOException("Stream is closed");
        }
    }

    private usu e(usu usuVar, urx urxVar) {
        if (usuVar == null) {
            usuVar = new usu();
        }
        if (urxVar.e()) {
            usuVar.c(true);
            usuVar.d(urxVar.d());
        }
        return usuVar;
    }

    private void d(ZipParameters zipParameters) throws IOException {
        usm b = this.f.b(zipParameters, this.d.e(), this.d.getCurrentSplitFileCounter(), this.l.b(), this.h);
        this.b = b;
        b.c(this.d.a());
        usq e = this.f.e(this.b);
        this.i = e;
        this.j.b(this.n, e, this.d, this.l.b());
    }

    private void c() throws IOException {
        this.o = 0L;
        this.c.reset();
        this.e.close();
    }

    private void a() throws IOException {
        if (this.d.e()) {
            this.h.d((OutputStream) this.d, (int) HeaderSignature.SPLIT_ZIP.getValue());
        }
    }

    private CompressedOutputStream b(ZipParameters zipParameters) throws IOException {
        return d(b(new usf(this.d), zipParameters), zipParameters);
    }

    private CipherOutputStream<?> b(usf usfVar, ZipParameters zipParameters) throws IOException {
        if (!zipParameters.l()) {
            return new ury(usfVar, zipParameters, null);
        }
        char[] cArr = this.g;
        if (cArr == null || cArr.length == 0) {
            throw new ZipException("password not set");
        }
        if (zipParameters.h() == EncryptionMethod.AES) {
            return new usb(usfVar, zipParameters, this.g, this.l.c());
        }
        if (zipParameters.h() == EncryptionMethod.ZIP_STANDARD) {
            return new usg(usfVar, zipParameters, this.g, this.l.c());
        }
        if (zipParameters.h() == EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG) {
            throw new ZipException(EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG + " encryption method is not supported");
        }
        throw new ZipException("Invalid encryption method");
    }

    private CompressedOutputStream d(CipherOutputStream<?> cipherOutputStream, ZipParameters zipParameters) {
        if (zipParameters.a() == CompressionMethod.DEFLATE) {
            return new urz(cipherOutputStream, zipParameters.d(), this.l.a());
        }
        return new usc(cipherOutputStream);
    }

    private void e(ZipParameters zipParameters) {
        if (utd.d(zipParameters.o())) {
            throw new IllegalArgumentException("fileNameInZip is null or empty");
        }
        if (zipParameters.a() == CompressionMethod.STORE && zipParameters.j() < 0 && !uta.d(zipParameters.o()) && zipParameters.u()) {
            throw new IllegalArgumentException("uncompressed size should be set for zip entries of compression type store");
        }
    }

    private boolean e(usm usmVar) {
        if (usmVar.isEncrypted() && usmVar.getEncryptionMethod().equals(EncryptionMethod.AES)) {
            return usmVar.getAesExtraDataRecord().a().equals(AesVersion.ONE);
        }
        return true;
    }

    private ZipParameters c(ZipParameters zipParameters) {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        if (uta.d(zipParameters.o())) {
            zipParameters2.c(false);
            zipParameters2.e(CompressionMethod.STORE);
            zipParameters2.b(false);
            zipParameters2.c(0L);
        }
        if (zipParameters.n() <= 0) {
            zipParameters2.e(System.currentTimeMillis());
        }
        return zipParameters2;
    }
}
