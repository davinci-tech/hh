package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ClosedInputStream;

/* loaded from: classes10.dex */
public abstract class AbstractByteArrayOutputStream extends OutputStream {
    static final int DEFAULT_SIZE = 1024;
    protected int count;
    private byte[] currentBuffer;
    private int currentBufferIndex;
    private int filledBufferSum;
    private final List<byte[]> buffers = new ArrayList();
    private boolean reuseBuffers = true;

    @FunctionalInterface
    protected interface InputStreamConstructor<T extends InputStream> {
        T construct(byte[] bArr, int i, int i2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public abstract void reset();

    public abstract int size();

    public abstract byte[] toByteArray();

    public abstract InputStream toInputStream();

    public abstract int write(InputStream inputStream) throws IOException;

    @Override // java.io.OutputStream
    public abstract void write(int i);

    @Override // java.io.OutputStream
    public abstract void write(byte[] bArr, int i, int i2);

    public abstract void writeTo(OutputStream outputStream) throws IOException;

    protected void needNewBuffer(int i) {
        if (this.currentBufferIndex < this.buffers.size() - 1) {
            this.filledBufferSum += this.currentBuffer.length;
            int i2 = this.currentBufferIndex + 1;
            this.currentBufferIndex = i2;
            this.currentBuffer = this.buffers.get(i2);
            return;
        }
        byte[] bArr = this.currentBuffer;
        if (bArr == null) {
            this.filledBufferSum = 0;
        } else {
            i = Math.max(bArr.length << 1, i - this.filledBufferSum);
            this.filledBufferSum += this.currentBuffer.length;
        }
        this.currentBufferIndex++;
        byte[] byteArray = IOUtils.byteArray(i);
        this.currentBuffer = byteArray;
        this.buffers.add(byteArray);
    }

    protected void resetImpl() {
        this.count = 0;
        this.filledBufferSum = 0;
        this.currentBufferIndex = 0;
        if (this.reuseBuffers) {
            this.currentBuffer = this.buffers.get(0);
            return;
        }
        this.currentBuffer = null;
        int length = this.buffers.get(0).length;
        this.buffers.clear();
        needNewBuffer(length);
        this.reuseBuffers = true;
    }

    protected byte[] toByteArrayImpl() {
        int i = this.count;
        if (i == 0) {
            return IOUtils.EMPTY_BYTE_ARRAY;
        }
        byte[] byteArray = IOUtils.byteArray(i);
        int i2 = 0;
        for (byte[] bArr : this.buffers) {
            int min = Math.min(bArr.length, i);
            System.arraycopy(bArr, 0, byteArray, i2, min);
            i2 += min;
            i -= min;
            if (i == 0) {
                break;
            }
        }
        return byteArray;
    }

    protected <T extends InputStream> InputStream toInputStream(InputStreamConstructor<T> inputStreamConstructor) {
        int i = this.count;
        if (i == 0) {
            return ClosedInputStream.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(this.buffers.size());
        for (byte[] bArr : this.buffers) {
            int min = Math.min(bArr.length, i);
            arrayList.add(inputStreamConstructor.construct(bArr, 0, min));
            i -= min;
            if (i == 0) {
                break;
            }
        }
        this.reuseBuffers = false;
        return new SequenceInputStream(Collections.enumeration(arrayList));
    }

    @Deprecated
    public String toString() {
        return new String(toByteArray(), Charset.defaultCharset());
    }

    public String toString(Charset charset) {
        return new String(toByteArray(), charset);
    }

    public String toString(String str) throws UnsupportedEncodingException {
        return new String(toByteArray(), str);
    }

    protected void writeImpl(byte[] bArr, int i, int i2) {
        int i3 = this.count;
        int i4 = i3 + i2;
        int i5 = i3 - this.filledBufferSum;
        int i6 = i2;
        while (i6 > 0) {
            int min = Math.min(i6, this.currentBuffer.length - i5);
            System.arraycopy(bArr, (i + i2) - i6, this.currentBuffer, i5, min);
            i6 -= min;
            if (i6 > 0) {
                needNewBuffer(i4);
                i5 = 0;
            }
        }
        this.count = i4;
    }

    protected int writeImpl(InputStream inputStream) throws IOException {
        int i = this.count - this.filledBufferSum;
        byte[] bArr = this.currentBuffer;
        int read = inputStream.read(bArr, i, bArr.length - i);
        int i2 = 0;
        while (read != -1) {
            i2 += read;
            i += read;
            this.count += read;
            byte[] bArr2 = this.currentBuffer;
            if (i == bArr2.length) {
                needNewBuffer(bArr2.length);
                i = 0;
            }
            byte[] bArr3 = this.currentBuffer;
            read = inputStream.read(bArr3, i, bArr3.length - i);
        }
        return i2;
    }

    protected void writeImpl(int i) {
        int i2 = this.count;
        int i3 = i2 - this.filledBufferSum;
        if (i3 == this.currentBuffer.length) {
            needNewBuffer(i2 + 1);
            i3 = 0;
        }
        this.currentBuffer[i3] = (byte) i;
        this.count++;
    }

    protected void writeToImpl(OutputStream outputStream) throws IOException {
        int i = this.count;
        for (byte[] bArr : this.buffers) {
            int min = Math.min(bArr.length, i);
            outputStream.write(bArr, 0, min);
            i -= min;
            if (i == 0) {
                return;
            }
        }
    }
}
