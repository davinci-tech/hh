package com.google.flatbuffers;

/* loaded from: classes8.dex */
public interface ReadBuf {
    byte[] data();

    byte get(int i);

    boolean getBoolean(int i);

    double getDouble(int i);

    float getFloat(int i);

    int getInt(int i);

    long getLong(int i);

    short getShort(int i);

    String getString(int i, int i2);

    int limit();
}
