package defpackage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;

/* loaded from: classes7.dex */
public class urw extends RandomAccessFile {

    /* renamed from: a, reason: collision with root package name */
    private String f17522a;
    private RandomAccessFile b;
    private int c;
    private File[] d;
    private byte[] e;
    private long i;

    public urw(File file, String str, File[] fileArr) throws IOException {
        super(file, str);
        this.e = new byte[1];
        this.c = 0;
        super.close();
        if (RandomAccessFileMode.WRITE.getValue().equals(str)) {
            throw new IllegalArgumentException("write mode is not allowed for NumberedSplitRandomAccessFile");
        }
        a(fileArr);
        this.b = new RandomAccessFile(file, str);
        this.d = fileArr;
        this.i = file.length();
        this.f17522a = str;
    }

    @Override // java.io.RandomAccessFile
    public int read() throws IOException {
        if (read(this.e) == -1) {
            return -1;
        }
        return this.e[0] & 255;
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.b.read(bArr, i, i2);
        if (read != -1) {
            return read;
        }
        int i3 = this.c;
        if (i3 == this.d.length - 1) {
            return -1;
        }
        a(i3 + 1);
        return read(bArr, i, i2);
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile
    public void seek(long j) throws IOException {
        int i = (int) (j / this.i);
        if (i != this.c) {
            a(i);
        }
        this.b.seek(j - (i * this.i));
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() throws IOException {
        return this.b.getFilePointer();
    }

    @Override // java.io.RandomAccessFile
    public long length() throws IOException {
        return this.b.length();
    }

    @Override // java.io.RandomAccessFile, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.b;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        super.close();
    }

    public void b(long j) throws IOException {
        this.b.seek(j);
    }

    public void c() throws IOException {
        a(this.d.length - 1);
    }

    private void a(int i) throws IOException {
        if (this.c == i) {
            return;
        }
        if (i > this.d.length - 1) {
            throw new IOException("split counter greater than number of split files");
        }
        RandomAccessFile randomAccessFile = this.b;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        this.b = new RandomAccessFile(this.d[i], this.f17522a);
        this.c = i;
    }

    private void a(File[] fileArr) throws IOException {
        int i = 1;
        for (File file : fileArr) {
            String c = uta.c(file);
            try {
                if (i != Integer.parseInt(c)) {
                    throw new IOException("Split file number " + i + " does not exist");
                }
                i++;
            } catch (NumberFormatException unused) {
                throw new IOException("Split file extension not in expected format. Found: " + c + " expected of format: .001, .002, etc");
            }
        }
    }
}
