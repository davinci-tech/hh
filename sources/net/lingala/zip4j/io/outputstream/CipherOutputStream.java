package net.lingala.zip4j.io.outputstream;

import defpackage.usf;
import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.crypto.Encrypter;
import net.lingala.zip4j.model.ZipParameters;

/* loaded from: classes7.dex */
public abstract class CipherOutputStream<T extends Encrypter> extends OutputStream {
    private T encrypter;
    private usf zipEntryOutputStream;

    protected abstract T initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z) throws IOException;

    public CipherOutputStream(usf usfVar, ZipParameters zipParameters, char[] cArr, boolean z) throws IOException {
        this.zipEntryOutputStream = usfVar;
        this.encrypter = initializeEncrypter(usfVar, zipParameters, cArr, z);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.zipEntryOutputStream.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.zipEntryOutputStream.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.encrypter.encryptData(bArr, i, i2);
        this.zipEntryOutputStream.write(bArr, i, i2);
    }

    public void writeHeaders(byte[] bArr) throws IOException {
        this.zipEntryOutputStream.write(bArr);
    }

    public void closeEntry() throws IOException {
        this.zipEntryOutputStream.c();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.zipEntryOutputStream.close();
    }

    public long getNumberOfBytesWrittenForThisEntry() {
        return this.zipEntryOutputStream.b();
    }

    protected T getEncrypter() {
        return this.encrypter;
    }
}
