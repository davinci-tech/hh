package com.google.flatbuffers;

import java.util.Arrays;

/* loaded from: classes8.dex */
public class ArrayReadWriteBuf implements ReadWriteBuf {
    private byte[] buffer;
    private int writePos;

    public ArrayReadWriteBuf() {
        this(10);
    }

    public ArrayReadWriteBuf(int i) {
        this(new byte[i]);
    }

    public ArrayReadWriteBuf(byte[] bArr) {
        this.buffer = bArr;
        this.writePos = 0;
    }

    public ArrayReadWriteBuf(byte[] bArr, int i) {
        this.buffer = bArr;
        this.writePos = i;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void clear() {
        this.writePos = 0;
    }

    @Override // com.google.flatbuffers.ReadBuf
    public boolean getBoolean(int i) {
        return this.buffer[i] != 0;
    }

    @Override // com.google.flatbuffers.ReadBuf
    public byte get(int i) {
        return this.buffer[i];
    }

    @Override // com.google.flatbuffers.ReadBuf
    public short getShort(int i) {
        byte[] bArr = this.buffer;
        return (short) ((bArr[i] & 255) | (bArr[i + 1] << 8));
    }

    @Override // com.google.flatbuffers.ReadBuf
    public int getInt(int i) {
        byte[] bArr = this.buffer;
        return (bArr[i] & 255) | (bArr[i + 3] << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
    }

    @Override // com.google.flatbuffers.ReadBuf
    public long getLong(int i) {
        byte[] bArr = this.buffer;
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48) | (bArr[i + 7] << 56);
    }

    @Override // com.google.flatbuffers.ReadBuf
    public float getFloat(int i) {
        return Float.intBitsToFloat(getInt(i));
    }

    @Override // com.google.flatbuffers.ReadBuf
    public double getDouble(int i) {
        return Double.longBitsToDouble(getLong(i));
    }

    @Override // com.google.flatbuffers.ReadBuf
    public String getString(int i, int i2) {
        return Utf8Safe.decodeUtf8Array(this.buffer, i, i2);
    }

    @Override // com.google.flatbuffers.ReadBuf
    public byte[] data() {
        return this.buffer;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void putBoolean(boolean z) {
        setBoolean(this.writePos, z);
        this.writePos++;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void put(byte[] bArr, int i, int i2) {
        set(this.writePos, bArr, i, i2);
        this.writePos += i2;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void put(byte b) {
        set(this.writePos, b);
        this.writePos++;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void putShort(short s) {
        setShort(this.writePos, s);
        this.writePos += 2;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void putInt(int i) {
        setInt(this.writePos, i);
        this.writePos += 4;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void putLong(long j) {
        setLong(this.writePos, j);
        this.writePos += 8;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void putFloat(float f) {
        setFloat(this.writePos, f);
        this.writePos += 4;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void putDouble(double d) {
        setDouble(this.writePos, d);
        this.writePos += 8;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void setBoolean(int i, boolean z) {
        set(i, z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void set(int i, byte b) {
        requestCapacity(i + 1);
        this.buffer[i] = b;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void set(int i, byte[] bArr, int i2, int i3) {
        requestCapacity((i3 - i2) + i);
        System.arraycopy(bArr, i2, this.buffer, i, i3);
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void setShort(int i, short s) {
        requestCapacity(i + 2);
        byte[] bArr = this.buffer;
        bArr[i] = (byte) (s & 255);
        bArr[i + 1] = (byte) ((s >> 8) & 255);
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void setInt(int i, int i2) {
        requestCapacity(i + 4);
        byte[] bArr = this.buffer;
        bArr[i] = (byte) (i2 & 255);
        bArr[i + 1] = (byte) ((i2 >> 8) & 255);
        bArr[i + 2] = (byte) ((i2 >> 16) & 255);
        bArr[i + 3] = (byte) ((i2 >> 24) & 255);
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void setLong(int i, long j) {
        requestCapacity(i + 8);
        int i2 = (int) j;
        byte[] bArr = this.buffer;
        bArr[i] = (byte) (i2 & 255);
        bArr[i + 1] = (byte) ((i2 >> 8) & 255);
        bArr[i + 2] = (byte) ((i2 >> 16) & 255);
        bArr[i + 3] = (byte) ((i2 >> 24) & 255);
        int i3 = (int) (j >> 32);
        bArr[i + 4] = (byte) (i3 & 255);
        bArr[i + 5] = (byte) ((i3 >> 8) & 255);
        bArr[i + 6] = (byte) ((i3 >> 16) & 255);
        bArr[i + 7] = (byte) ((i3 >> 24) & 255);
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void setFloat(int i, float f) {
        requestCapacity(i + 4);
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        byte[] bArr = this.buffer;
        bArr[i] = (byte) (floatToRawIntBits & 255);
        bArr[i + 1] = (byte) ((floatToRawIntBits >> 8) & 255);
        bArr[i + 2] = (byte) ((floatToRawIntBits >> 16) & 255);
        bArr[i + 3] = (byte) ((floatToRawIntBits >> 24) & 255);
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public void setDouble(int i, double d) {
        requestCapacity(i + 8);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        int i2 = (int) doubleToRawLongBits;
        byte[] bArr = this.buffer;
        bArr[i] = (byte) (i2 & 255);
        bArr[i + 1] = (byte) ((i2 >> 8) & 255);
        bArr[i + 2] = (byte) ((i2 >> 16) & 255);
        bArr[i + 3] = (byte) ((i2 >> 24) & 255);
        int i3 = (int) (doubleToRawLongBits >> 32);
        bArr[i + 4] = (byte) (i3 & 255);
        bArr[i + 5] = (byte) ((i3 >> 8) & 255);
        bArr[i + 6] = (byte) ((i3 >> 16) & 255);
        bArr[i + 7] = (byte) ((i3 >> 24) & 255);
    }

    @Override // com.google.flatbuffers.ReadWriteBuf, com.google.flatbuffers.ReadBuf
    public int limit() {
        return this.writePos;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public int writePosition() {
        return this.writePos;
    }

    @Override // com.google.flatbuffers.ReadWriteBuf
    public boolean requestCapacity(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Capacity may not be negative (likely a previous int overflow)");
        }
        byte[] bArr = this.buffer;
        if (bArr.length >= i) {
            return true;
        }
        int length = bArr.length;
        int i2 = length + (length >> 1);
        if (i2 >= i) {
            i = i2;
        }
        this.buffer = Arrays.copyOf(bArr, i);
        return true;
    }
}
