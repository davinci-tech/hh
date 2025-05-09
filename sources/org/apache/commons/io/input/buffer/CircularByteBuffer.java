package org.apache.commons.io.input.buffer;

import java.util.Objects;
import org.apache.commons.io.IOUtils;

/* loaded from: classes10.dex */
public class CircularByteBuffer {
    private final byte[] buffer;
    private int currentNumberOfBytes;
    private int endOffset;
    private int startOffset;

    public CircularByteBuffer() {
        this(8192);
    }

    public CircularByteBuffer(int i) {
        this.buffer = IOUtils.byteArray(i);
        this.startOffset = 0;
        this.endOffset = 0;
        this.currentNumberOfBytes = 0;
    }

    public void add(byte b) {
        int i = this.currentNumberOfBytes;
        byte[] bArr = this.buffer;
        if (i >= bArr.length) {
            throw new IllegalStateException("No space available");
        }
        int i2 = this.endOffset;
        bArr[i2] = b;
        this.currentNumberOfBytes = i + 1;
        int i3 = i2 + 1;
        this.endOffset = i3;
        if (i3 == bArr.length) {
            this.endOffset = 0;
        }
    }

    public void add(byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr, "Buffer");
        if (i < 0 || i >= bArr.length) {
            throw new IllegalArgumentException("Illegal offset: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Illegal length: " + i2);
        }
        if (this.currentNumberOfBytes + i2 > this.buffer.length) {
            throw new IllegalStateException("No space available");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr2 = this.buffer;
            int i4 = this.endOffset;
            bArr2[i4] = bArr[i + i3];
            int i5 = i4 + 1;
            this.endOffset = i5;
            if (i5 == bArr2.length) {
                this.endOffset = 0;
            }
        }
        this.currentNumberOfBytes += i2;
    }

    public void clear() {
        this.startOffset = 0;
        this.endOffset = 0;
        this.currentNumberOfBytes = 0;
    }

    public int getCurrentNumberOfBytes() {
        return this.currentNumberOfBytes;
    }

    public int getSpace() {
        return this.buffer.length - this.currentNumberOfBytes;
    }

    public boolean hasBytes() {
        return this.currentNumberOfBytes > 0;
    }

    public boolean hasSpace() {
        return this.currentNumberOfBytes < this.buffer.length;
    }

    public boolean hasSpace(int i) {
        return this.currentNumberOfBytes + i <= this.buffer.length;
    }

    public boolean peek(byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr, "Buffer");
        if (i < 0 || i >= bArr.length) {
            throw new IllegalArgumentException("Illegal offset: " + i);
        }
        if (i2 < 0 || i2 > this.buffer.length) {
            throw new IllegalArgumentException("Illegal length: " + i2);
        }
        if (i2 < this.currentNumberOfBytes) {
            return false;
        }
        int i3 = this.startOffset;
        for (int i4 = 0; i4 < i2; i4++) {
            byte[] bArr2 = this.buffer;
            if (bArr2[i3] != bArr[i4 + i]) {
                return false;
            }
            i3++;
            if (i3 == bArr2.length) {
                i3 = 0;
            }
        }
        return true;
    }

    public byte read() {
        int i = this.currentNumberOfBytes;
        if (i <= 0) {
            throw new IllegalStateException("No bytes available.");
        }
        byte[] bArr = this.buffer;
        int i2 = this.startOffset;
        byte b = bArr[i2];
        this.currentNumberOfBytes = i - 1;
        int i3 = i2 + 1;
        this.startOffset = i3;
        if (i3 == bArr.length) {
            this.startOffset = 0;
        }
        return b;
    }

    public void read(byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr, "targetBuffer");
        if (i < 0 || i >= bArr.length) {
            throw new IllegalArgumentException("Illegal offset: " + i);
        }
        if (i2 < 0 || i2 > this.buffer.length) {
            throw new IllegalArgumentException("Illegal length: " + i2);
        }
        int i3 = i + i2;
        if (i3 > bArr.length) {
            StringBuilder sb = new StringBuilder("The supplied byte array contains only ");
            sb.append(bArr.length);
            sb.append(" bytes, but offset, and length would require ");
            sb.append(i3 - 1);
            throw new IllegalArgumentException(sb.toString());
        }
        if (this.currentNumberOfBytes < i2) {
            throw new IllegalStateException("Currently, there are only " + this.currentNumberOfBytes + "in the buffer, not " + i2);
        }
        int i4 = 0;
        while (i4 < i2) {
            byte[] bArr2 = this.buffer;
            int i5 = this.startOffset;
            bArr[i] = bArr2[i5];
            this.currentNumberOfBytes--;
            int i6 = i5 + 1;
            this.startOffset = i6;
            if (i6 == bArr2.length) {
                this.startOffset = 0;
            }
            i4++;
            i++;
        }
    }
}
