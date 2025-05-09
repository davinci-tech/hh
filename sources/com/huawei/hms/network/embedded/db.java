package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public interface db extends zb, ReadableByteChannel {
    int a(pb pbVar) throws IOException;

    long a(byte b) throws IOException;

    long a(byte b, long j) throws IOException;

    long a(byte b, long j, long j2) throws IOException;

    long a(eb ebVar, long j) throws IOException;

    long a(yb ybVar) throws IOException;

    @Deprecated
    bb a();

    String a(long j, Charset charset) throws IOException;

    String a(Charset charset) throws IOException;

    void a(bb bbVar, long j) throws IOException;

    boolean a(long j, eb ebVar) throws IOException;

    boolean a(long j, eb ebVar, int i, int i2) throws IOException;

    long b(eb ebVar) throws IOException;

    long b(eb ebVar, long j) throws IOException;

    long c(eb ebVar) throws IOException;

    eb d(long j) throws IOException;

    int e() throws IOException;

    String e(long j) throws IOException;

    long f() throws IOException;

    String f(long j) throws IOException;

    bb g();

    byte[] g(long j) throws IOException;

    int h() throws IOException;

    boolean h(long j) throws IOException;

    void i(long j) throws IOException;

    boolean i() throws IOException;

    short j() throws IOException;

    long k() throws IOException;

    @Nullable
    String l() throws IOException;

    InputStream m();

    String n() throws IOException;

    String o() throws IOException;

    long p() throws IOException;

    db peek();

    byte[] q() throws IOException;

    eb r() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    byte readByte() throws IOException;

    void readFully(byte[] bArr) throws IOException;

    int readInt() throws IOException;

    long readLong() throws IOException;

    short readShort() throws IOException;

    void skip(long j) throws IOException;
}
