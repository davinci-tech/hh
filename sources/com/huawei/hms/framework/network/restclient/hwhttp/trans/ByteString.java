package com.huawei.hms.framework.network.restclient.hwhttp.trans;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ByteString implements Comparable<ByteString> {
    private com.huawei.hms.network.base.common.trans.ByteString proxyByteString;

    ByteString(byte[] bArr) {
        this.proxyByteString = new com.huawei.hms.network.base.common.trans.ByteString(bArr);
    }

    public static ByteString of(byte... bArr) {
        return new ByteString(com.huawei.hms.network.base.common.trans.ByteString.of(bArr).toByteArray());
    }

    public static ByteString of(byte[] bArr, int i, int i2) {
        return new ByteString(com.huawei.hms.network.base.common.trans.ByteString.of(bArr, i, i2).toByteArray());
    }

    public static ByteString of(ByteBuffer byteBuffer) {
        return new ByteString(com.huawei.hms.network.base.common.trans.ByteString.of(byteBuffer).toByteArray());
    }

    public static ByteString encodeUtf8(String str) {
        return new ByteString(com.huawei.hms.network.base.common.trans.ByteString.encodeUtf8(str).toByteArray());
    }

    public static ByteString encodeString(String str, Charset charset) {
        return new ByteString(com.huawei.hms.network.base.common.trans.ByteString.encodeString(str, charset).toByteArray());
    }

    public String utf8() {
        return this.proxyByteString.utf8();
    }

    public String string(Charset charset) {
        return this.proxyByteString.string(charset);
    }

    public String base64() {
        return this.proxyByteString.base64();
    }

    public ByteString md5() {
        return new ByteString(this.proxyByteString.md5().toByteArray());
    }

    public ByteString sha1() {
        return new ByteString(this.proxyByteString.sha1().toByteArray());
    }

    public ByteString sha256() {
        return new ByteString(this.proxyByteString.sha256().toByteArray());
    }

    public ByteString sha512() {
        return new ByteString(this.proxyByteString.sha512().toByteArray());
    }

    public ByteString hmacSha1(ByteString byteString) {
        return new ByteString(this.proxyByteString.hmacSha1(byteString.proxyByteString).toByteArray());
    }

    public ByteString hmacSha256(ByteString byteString) {
        return new ByteString(this.proxyByteString.hmacSha256(byteString.proxyByteString).toByteArray());
    }

    public ByteString hmacSha512(ByteString byteString) {
        return new ByteString(this.proxyByteString.hmacSha512(byteString.proxyByteString).toByteArray());
    }

    public String base64Url() {
        return this.proxyByteString.base64Url();
    }

    @Nullable
    public static ByteString decodeBase64(String str) {
        com.huawei.hms.network.base.common.trans.ByteString decodeBase64 = com.huawei.hms.network.base.common.trans.ByteString.decodeBase64(str);
        if (decodeBase64 != null) {
            return new ByteString(decodeBase64.toByteArray());
        }
        return null;
    }

    public String hex() {
        return this.proxyByteString.hex();
    }

    public static ByteString decodeHex(String str) {
        return new ByteString(com.huawei.hms.network.base.common.trans.ByteString.decodeHex(str).toByteArray());
    }

    public static ByteString read(InputStream inputStream, int i) throws IOException {
        return new ByteString(com.huawei.hms.network.base.common.trans.ByteString.read(inputStream, i).toByteArray());
    }

    public ByteString toAsciiLowercase() {
        return new ByteString(this.proxyByteString.toAsciiLowercase().toByteArray());
    }

    public ByteString toAsciiUppercase() {
        return new ByteString(this.proxyByteString.toAsciiUppercase().toByteArray());
    }

    public ByteString substring(int i) {
        return new ByteString(this.proxyByteString.substring(i).toByteArray());
    }

    public ByteString substring(int i, int i2) {
        return new ByteString(this.proxyByteString.substring(i, i2).toByteArray());
    }

    public byte getByte(int i) {
        return this.proxyByteString.getByte(i);
    }

    public int size() {
        return this.proxyByteString.size();
    }

    public byte[] toByteArray() {
        return this.proxyByteString.toByteArray();
    }

    public ByteBuffer asByteBuffer() {
        return this.proxyByteString.asByteBuffer();
    }

    public void write(OutputStream outputStream) throws IOException {
        this.proxyByteString.write(outputStream);
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        return this.proxyByteString.rangeEquals(i, byteString.proxyByteString, i2, i3);
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        return this.proxyByteString.rangeEquals(i, bArr, i2, i3);
    }

    public final boolean startsWith(ByteString byteString) {
        return this.proxyByteString.startsWith(byteString.proxyByteString);
    }

    public final boolean startsWith(byte[] bArr) {
        return this.proxyByteString.startsWith(bArr);
    }

    public final boolean endsWith(ByteString byteString) {
        return this.proxyByteString.endsWith(byteString.proxyByteString);
    }

    public final boolean endsWith(byte[] bArr) {
        return this.proxyByteString.endsWith(bArr);
    }

    public final int indexOf(ByteString byteString) {
        return this.proxyByteString.indexOf(byteString.proxyByteString);
    }

    public final int indexOf(ByteString byteString, int i) {
        return this.proxyByteString.indexOf(byteString.proxyByteString, i);
    }

    public final int indexOf(byte[] bArr) {
        return this.proxyByteString.indexOf(bArr);
    }

    public int indexOf(byte[] bArr, int i) {
        return this.proxyByteString.indexOf(bArr, i);
    }

    public final int lastIndexOf(ByteString byteString) {
        return this.proxyByteString.lastIndexOf(byteString.proxyByteString);
    }

    public final int lastIndexOf(ByteString byteString, int i) {
        return this.proxyByteString.lastIndexOf(byteString.proxyByteString, i);
    }

    public final int lastIndexOf(byte[] bArr) {
        return this.proxyByteString.lastIndexOf(bArr);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        return this.proxyByteString.lastIndexOf(bArr, i);
    }

    public boolean equals(Object obj) {
        return this.proxyByteString.equals(obj);
    }

    public int hashCode() {
        return this.proxyByteString.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(ByteString byteString) {
        return this.proxyByteString.compareTo(byteString.proxyByteString);
    }

    public String toString() {
        return this.proxyByteString.toString();
    }
}
