package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

/* loaded from: classes9.dex */
public interface cb extends yb, WritableByteChannel {
    long a(zb zbVar) throws IOException;

    bb a();

    cb a(int i) throws IOException;

    cb a(long j) throws IOException;

    cb a(eb ebVar) throws IOException;

    cb a(zb zbVar, long j) throws IOException;

    cb a(String str) throws IOException;

    cb a(String str, int i, int i2) throws IOException;

    cb a(String str, int i, int i2, Charset charset) throws IOException;

    cb a(String str, Charset charset) throws IOException;

    cb b(int i) throws IOException;

    cb b(long j) throws IOException;

    OutputStream b();

    cb c() throws IOException;

    cb c(int i) throws IOException;

    cb c(long j) throws IOException;

    cb d() throws IOException;

    @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
    void flush() throws IOException;

    cb write(byte[] bArr) throws IOException;

    cb write(byte[] bArr, int i, int i2) throws IOException;

    cb writeByte(int i) throws IOException;

    cb writeInt(int i) throws IOException;

    cb writeLong(long j) throws IOException;

    cb writeShort(int i) throws IOException;
}
