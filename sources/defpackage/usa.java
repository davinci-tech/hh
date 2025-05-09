package defpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;

/* loaded from: classes7.dex */
public class usa extends OutputStream implements OutputStreamWithSplitZipSupport {

    /* renamed from: a, reason: collision with root package name */
    private int f17525a;
    private long b;
    private long c;
    private utf d;
    private RandomAccessFile e;
    private File i;

    public usa(File file) throws FileNotFoundException, ZipException {
        this(file, -1L);
    }

    public usa(File file, long j) throws FileNotFoundException, ZipException {
        this.d = new utf();
        if (j >= 0 && j < 65536) {
            throw new ZipException("split length less than minimum allowed split length of 65536 Bytes");
        }
        this.e = new RandomAccessFile(file, RandomAccessFileMode.WRITE.getValue());
        this.b = j;
        this.i = file;
        this.f17525a = 0;
        this.c = 0L;
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
        if (i2 <= 0) {
            return;
        }
        long j = this.b;
        if (j == -1) {
            this.e.write(bArr, i, i2);
            this.c += i2;
            return;
        }
        long j2 = this.c;
        if (j2 >= j) {
            c();
            this.e.write(bArr, i, i2);
            this.c = i2;
            return;
        }
        long j3 = i2;
        if (j2 + j3 > j) {
            if (e(bArr)) {
                c();
                this.e.write(bArr, i, i2);
                this.c = j3;
                return;
            } else {
                this.e.write(bArr, i, (int) (this.b - this.c));
                c();
                RandomAccessFile randomAccessFile = this.e;
                long j4 = this.b - this.c;
                randomAccessFile.write(bArr, i + ((int) j4), (int) (j3 - j4));
                this.c = j3 - (this.b - this.c);
                return;
            }
        }
        this.e.write(bArr, i, i2);
        this.c += j3;
    }

    private void c() throws IOException {
        String str;
        String e = uta.e(this.i.getName());
        String absolutePath = this.i.getAbsolutePath();
        if (this.i.getParent() == null) {
            str = "";
        } else {
            str = this.i.getParent() + System.getProperty("file.separator");
        }
        String str2 = ".z0" + (this.f17525a + 1);
        if (this.f17525a >= 9) {
            str2 = ".z" + (this.f17525a + 1);
        }
        File file = new File(str + e + str2);
        this.e.close();
        if (file.exists()) {
            throw new IOException("split file: " + file.getName() + " already exists in the current directory, cannot rename this file");
        }
        if (!this.i.renameTo(file)) {
            throw new IOException("cannot rename newly created split file");
        }
        this.i = new File(absolutePath);
        this.e = new RandomAccessFile(this.i, RandomAccessFileMode.WRITE.getValue());
        this.f17525a++;
    }

    private boolean e(byte[] bArr) {
        int a2 = this.d.a(bArr);
        for (HeaderSignature headerSignature : HeaderSignature.values()) {
            if (headerSignature != HeaderSignature.SPLIT_ZIP && headerSignature.getValue() == a2) {
                return true;
            }
        }
        return false;
    }

    public boolean e(int i) throws ZipException {
        if (i < 0) {
            throw new ZipException("negative buffersize for checkBufferSizeAndStartNextSplitFile");
        }
        if (d(i)) {
            return false;
        }
        try {
            c();
            this.c = 0L;
            return true;
        } catch (IOException e) {
            throw new ZipException(e);
        }
    }

    private boolean d(int i) {
        long j = this.b;
        return j < 65536 || this.c + ((long) i) <= j;
    }

    public void e(long j) throws IOException {
        this.e.seek(j);
    }

    public int b(int i) throws IOException {
        return this.e.skipBytes(i);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.e.close();
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public long getFilePointer() throws IOException {
        return this.e.getFilePointer();
    }

    public boolean b() {
        return this.b != -1;
    }

    public long d() {
        return this.b;
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public int getCurrentSplitFileCounter() {
        return this.f17525a;
    }
}
