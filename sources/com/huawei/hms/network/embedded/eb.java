package com.huawei.hms.network.embedded;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.operation.utils.Constants;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes9.dex */
public class eb implements Serializable, Comparable<eb> {
    public static final long e = 1;

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f5235a;
    public transient int b;
    public transient String c;
    public static final char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final eb f = e(new byte[0]);

    public String toString() {
        StringBuilder sb;
        StringBuilder sb2;
        if (this.f5235a.length == 0) {
            return "[size=0]";
        }
        String n = n();
        int a2 = a(n, 64);
        if (a2 == -1) {
            if (this.f5235a.length <= 64) {
                sb2 = new StringBuilder("[hex=");
                sb2.append(d());
                sb2.append("]");
            } else {
                sb2 = new StringBuilder("[size=");
                sb2.append(this.f5235a.length);
                sb2.append(" hex=");
                sb2.append(a(0, 64).d());
                sb2.append("…]");
            }
            return sb2.toString();
        }
        String replace = n.substring(0, a2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        if (a2 < n.length()) {
            sb = new StringBuilder("[size=");
            sb.append(this.f5235a.length);
            sb.append(" text=");
            sb.append(replace);
            sb.append("…]");
        } else {
            sb = new StringBuilder("[text=");
            sb.append(replace);
            sb.append("]");
        }
        return sb.toString();
    }

    public String n() {
        String str = this.c;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f5235a, cc.f5207a);
        this.c = str2;
        return str2;
    }

    public byte[] m() {
        return (byte[]) this.f5235a.clone();
    }

    public eb l() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f5235a;
            if (i >= bArr.length) {
                return this;
            }
            byte b = bArr[i];
            if (b >= 97 && b <= 122) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b - 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b2 = bArr2[i2];
                    if (b2 >= 97 && b2 <= 122) {
                        bArr2[i2] = (byte) (b2 - 32);
                    }
                }
                return new eb(bArr2);
            }
            i++;
        }
    }

    public eb k() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f5235a;
            if (i >= bArr.length) {
                return this;
            }
            byte b = bArr[i];
            if (b >= 65 && b <= 90) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b2 = bArr2[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArr2[i2] = (byte) (b2 + 32);
                    }
                }
                return new eb(bArr2);
            }
            i++;
        }
    }

    public int j() {
        return this.f5235a.length;
    }

    public eb i() {
        return c("SHA-512");
    }

    public int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int hashCode = Arrays.hashCode(this.f5235a);
        this.b = hashCode;
        return hashCode;
    }

    public final boolean h(eb ebVar) {
        return a(0, ebVar, 0, ebVar.j());
    }

    public eb h() {
        return c("SHA-256");
    }

    public eb g() {
        return c("SHA-1");
    }

    public final int g(eb ebVar) {
        return b(ebVar.e(), j());
    }

    public eb f() {
        return c("MD5");
    }

    public final int f(eb ebVar) {
        return a(ebVar.e(), 0);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof eb) {
            eb ebVar = (eb) obj;
            int j = ebVar.j();
            byte[] bArr = this.f5235a;
            if (j == bArr.length && ebVar.a(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public byte[] e() {
        return this.f5235a;
    }

    public eb e(eb ebVar) {
        return a("HmacSHA512", ebVar);
    }

    public final boolean d(byte[] bArr) {
        return a(0, bArr, 0, bArr.length);
    }

    public String d() {
        byte[] bArr = this.f5235a;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = d;
            cArr[i] = cArr2[(b >> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & BaseType.Obj];
        }
        return new String(cArr);
    }

    public eb d(eb ebVar) {
        return a("HmacSHA256", ebVar);
    }

    public String c() {
        return ab.b(this.f5235a);
    }

    public eb c(eb ebVar) {
        return a("HmacSHA1", ebVar);
    }

    public final int c(byte[] bArr) {
        return b(bArr, j());
    }

    public final boolean b(eb ebVar) {
        return a(j() - ebVar.j(), ebVar, 0, ebVar.j());
    }

    public String b() {
        return ab.a(this.f5235a);
    }

    public eb b(int i) {
        return a(i, this.f5235a.length);
    }

    public int b(byte[] bArr, int i) {
        for (int min = Math.min(i, this.f5235a.length - bArr.length); min >= 0; min--) {
            if (cc.a(this.f5235a, min, bArr, 0, bArr.length)) {
                return min;
            }
        }
        return -1;
    }

    public final int b(byte[] bArr) {
        return a(bArr, 0);
    }

    public final int b(eb ebVar, int i) {
        return b(ebVar.e(), i);
    }

    public final boolean a(byte[] bArr) {
        return a(j() - bArr.length, bArr, 0, bArr.length);
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i >= 0) {
            byte[] bArr2 = this.f5235a;
            if (i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && cc.a(bArr2, i, bArr, i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(int i, eb ebVar, int i2, int i3) {
        return ebVar.a(i2, this.f5235a, i, i3);
    }

    public void a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.f5235a);
    }

    public void a(bb bbVar) {
        byte[] bArr = this.f5235a;
        bbVar.write(bArr, 0, bArr.length);
    }

    public ByteBuffer a() {
        return ByteBuffer.wrap(this.f5235a).asReadOnlyBuffer();
    }

    public String a(Charset charset) {
        if (charset != null) {
            return new String(this.f5235a, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    public eb a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.f5235a;
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.f5235a.length + Constants.RIGHT_BRACKET_ONLY);
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i == 0 && i2 == bArr.length) {
            return this;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return new eb(bArr2);
    }

    public int a(byte[] bArr, int i) {
        int length = this.f5235a.length;
        int length2 = bArr.length;
        for (int max = Math.max(i, 0); max <= length - length2; max++) {
            if (cc.a(this.f5235a, max, bArr, 0, bArr.length)) {
                return max;
            }
        }
        return -1;
    }

    public final int a(eb ebVar, int i) {
        return a(ebVar.e(), i);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(eb ebVar) {
        int j = j();
        int j2 = ebVar.j();
        int min = Math.min(j, j2);
        for (int i = 0; i < min; i++) {
            int a2 = a(i) & 255;
            int a3 = ebVar.a(i) & 255;
            if (a2 != a3) {
                return a2 < a3 ? -1 : 1;
            }
        }
        if (j == j2) {
            return 0;
        }
        return j < j2 ? -1 : 1;
    }

    public byte a(int i) {
        return this.f5235a[i];
    }

    public static eb e(byte... bArr) {
        if (bArr != null) {
            return new eb((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static eb d(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        eb ebVar = new eb(str.getBytes(cc.f5207a));
        ebVar.c = str;
        return ebVar;
    }

    private eb c(String str) {
        try {
            return e(MessageDigest.getInstance(str).digest(this.f5235a));
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    public static eb b(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((a(str.charAt(i2)) << 4) + a(str.charAt(i2 + 1)));
        }
        return e(bArr);
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.f5235a.length);
        objectOutputStream.write(this.f5235a);
    }

    private void a(ObjectInputStream objectInputStream) throws IOException {
        eb a2 = a(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = eb.class.getDeclaredField(VideoPlayFlag.PLAY_IN_ALL);
            declaredField.setAccessible(true);
            declaredField.set(this, a2.f5235a);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (NoSuchFieldException unused2) {
            throw new AssertionError();
        }
    }

    public static eb a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        cc.a(bArr.length, i, i2);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new eb(bArr2);
    }

    public static eb a(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("data == null");
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return new eb(bArr);
    }

    public static eb a(String str, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        if (charset != null) {
            return new eb(str.getBytes(charset));
        }
        throw new IllegalArgumentException("charset == null");
    }

    private eb a(String str, eb ebVar) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(ebVar.m(), str));
            return e(mac.doFinal(this.f5235a));
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new AssertionError(e3);
        }
    }

    @Nullable
    public static eb a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] a2 = ab.a(str);
        if (a2 != null) {
            return new eb(a2);
        }
        return null;
    }

    public static eb a(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i);
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 += read;
        }
        return new eb(bArr);
    }

    public static int a(String str, int i) {
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            if (i3 == i) {
                return i2;
            }
            int codePointAt = str.codePointAt(i2);
            if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                return -1;
            }
            i3++;
            i2 += Character.charCount(codePointAt);
        }
        return str.length();
    }

    public static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c);
            }
        }
        return (c - c2) + 10;
    }

    public eb(byte[] bArr) {
        this.f5235a = bArr;
    }
}
