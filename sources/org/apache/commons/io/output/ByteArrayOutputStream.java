package org.apache.commons.io.output;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.output.AbstractByteArrayOutputStream;

/* loaded from: classes10.dex */
public class ByteArrayOutputStream extends AbstractByteArrayOutputStream {
    public static InputStream toBufferedInputStream(InputStream inputStream) throws IOException {
        return toBufferedInputStream(inputStream, 1024);
    }

    public static InputStream toBufferedInputStream(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        try {
            byteArrayOutputStream.write(inputStream);
            InputStream inputStream2 = byteArrayOutputStream.toInputStream();
            byteArrayOutputStream.close();
            return inputStream2;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public ByteArrayOutputStream() {
        this(1024);
    }

    public ByteArrayOutputStream(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        synchronized (this) {
            needNewBuffer(i);
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public void reset() {
        synchronized (this) {
            resetImpl();
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public int size() {
        int i;
        synchronized (this) {
            i = this.count;
        }
        return i;
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public byte[] toByteArray() {
        byte[] byteArrayImpl;
        synchronized (this) {
            byteArrayImpl = toByteArrayImpl();
        }
        return byteArrayImpl;
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public InputStream toInputStream() {
        InputStream inputStream;
        synchronized (this) {
            inputStream = toInputStream(new AbstractByteArrayOutputStream.InputStreamConstructor() { // from class: org.apache.commons.io.output.ByteArrayOutputStream$$ExternalSyntheticLambda0
                @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream.InputStreamConstructor
                public final InputStream construct(byte[] bArr, int i, int i2) {
                    return ByteArrayOutputStream.$r8$lambda$b_x95vDRJTYVaIxBKUURRGxO_HQ(bArr, i, i2);
                }
            });
        }
        return inputStream;
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        synchronized (this) {
            writeImpl(bArr, i, i2);
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public int write(InputStream inputStream) throws IOException {
        int writeImpl;
        synchronized (this) {
            writeImpl = writeImpl(inputStream);
        }
        return writeImpl;
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public void write(int i) {
        synchronized (this) {
            writeImpl(i);
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public void writeTo(OutputStream outputStream) throws IOException {
        synchronized (this) {
            writeToImpl(outputStream);
        }
    }

    public static /* synthetic */ ByteArrayInputStream $r8$lambda$b_x95vDRJTYVaIxBKUURRGxO_HQ(byte[] bArr, int i, int i2) {
        return new ByteArrayInputStream(bArr, i, i2);
    }
}
