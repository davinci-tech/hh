package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.embedded.g4;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.uez;
import defpackage.uhy;
import defpackage.uib;
import defpackage.uju;
import defpackage.ujy;
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
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 ]2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001]B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\u0011\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0000H\u0096\u0002J,\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0016J\u0015\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u0010H\u0010¢\u0006\u0002\b#J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0004J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0000J\u0013\u0010'\u001a\u00020%2\b\u0010\u001a\u001a\u0004\u0018\u00010(H\u0096\u0002J\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b,J\u0015\u0010,\u001a\u00020*2\u0006\u0010+\u001a\u00020\tH\u0007¢\u0006\u0002\b-J\r\u0010.\u001a\u00020\tH\u0010¢\u0006\u0002\b/J\b\u0010\b\u001a\u00020\tH\u0016J\b\u00100\u001a\u00020\u0010H\u0016J\u001d\u00101\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u0000H\u0010¢\u0006\u0002\b3J\u0010\u00104\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u0000H\u0016J\u0010\u00105\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u0000H\u0016J\u0010\u00106\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u0000H\u0016J\u001a\u00107\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u00108\u001a\u00020\tH\u0017J\u001a\u00107\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u00108\u001a\u00020\tH\u0007J\r\u00109\u001a\u00020\u0004H\u0010¢\u0006\u0002\b:J\u0015\u0010;\u001a\u00020*2\u0006\u0010<\u001a\u00020\tH\u0010¢\u0006\u0002\b=J\u001a\u0010>\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u00108\u001a\u00020\tH\u0017J\u001a\u0010>\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u00108\u001a\u00020\tH\u0007J\u0006\u0010?\u001a\u00020\u0000J(\u0010@\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0016J(\u0010@\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0016J\u0010\u0010B\u001a\u00020\u001c2\u0006\u0010C\u001a\u00020DH\u0002J\u0006\u0010E\u001a\u00020\u0000J\u0006\u0010F\u001a\u00020\u0000J\u0006\u0010G\u001a\u00020\u0000J\r\u0010\u000e\u001a\u00020\tH\u0007¢\u0006\u0002\bHJ\u000e\u0010I\u001a\u00020%2\u0006\u0010J\u001a\u00020\u0004J\u000e\u0010I\u001a\u00020%2\u0006\u0010J\u001a\u00020\u0000J\u0010\u0010K\u001a\u00020\u00102\u0006\u0010L\u001a\u00020MH\u0016J\u001c\u0010N\u001a\u00020\u00002\b\b\u0002\u0010O\u001a\u00020\t2\b\b\u0002\u0010P\u001a\u00020\tH\u0017J\b\u0010Q\u001a\u00020\u0000H\u0016J\b\u0010R\u001a\u00020\u0000H\u0016J\b\u0010S\u001a\u00020\u0004H\u0016J\b\u0010T\u001a\u00020\u0010H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010U\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020WH\u0016J%\u0010U\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020Y2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0010¢\u0006\u0002\bZJ\u0010\u0010[\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020\\H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006^"}, d2 = {"Lokio/ByteString;", "Ljava/io/Serializable;", "", "data", "", "([B)V", "getData$okio", "()[B", WatchFaceConstant.HASHCODE, "", "getHashCode$okio", "()I", "setHashCode$okio", "(I)V", "size", "utf8", "", "getUtf8$okio", "()Ljava/lang/String;", "setUtf8$okio", "(Ljava/lang/String;)V", "asByteBuffer", "Ljava/nio/ByteBuffer;", "base64", "base64Url", "compareTo", "other", "copyInto", "", TypedValues.CycleType.S_WAVE_OFFSET, "target", "targetOffset", "byteCount", "digest", "algorithm", "digest$okio", "endsWith", "", "suffix", "equals", "", "get", "", "index", "getByte", "-deprecated_getByte", "getSize", "getSize$okio", "hex", "hmac", MedalConstants.EVENT_KEY, "hmac$okio", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", "fromIndex", "internalArray", "internalArray$okio", "internalGet", "pos", "internalGet$okio", "lastIndexOf", "md5", "rangeEquals", "otherOffset", "readObject", "in", "Ljava/io/ObjectInputStream;", "sha1", "sha256", "sha512", "-deprecated_size", "startsWith", "prefix", "string", "charset", "Ljava/nio/charset/Charset;", "substring", "beginIndex", "endIndex", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toString", "write", "out", "Ljava/io/OutputStream;", "buffer", "Lokio/Buffer;", "write$okio", "writeObject", "Ljava/io/ObjectOutputStream;", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private static final long serialVersionUID = 1;
    private final byte[] data;
    private transient int hashCode;
    private transient String utf8;

    public ByteString(byte[] bArr) {
        uhy.e((Object) bArr, "");
        this.data = bArr;
    }

    /* renamed from: getData$okio, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }

    /* renamed from: getHashCode$okio, reason: from getter */
    public final int getHashCode() {
        return this.hashCode;
    }

    public final void setHashCode$okio(int i) {
        this.hashCode = i;
    }

    /* renamed from: getUtf8$okio, reason: from getter */
    public final String getUtf8() {
        return this.utf8;
    }

    public final void setUtf8$okio(String str) {
        this.utf8 = str;
    }

    public String string(Charset charset) {
        uhy.e((Object) charset, "");
        return new String(this.data, charset);
    }

    public final ByteString md5() {
        return digest$okio("MD5");
    }

    public final ByteString sha1() {
        return digest$okio("SHA-1");
    }

    public final ByteString sha256() {
        return digest$okio("SHA-256");
    }

    public final ByteString sha512() {
        return digest$okio("SHA-512");
    }

    public ByteString digest$okio(String algorithm) {
        uhy.e((Object) algorithm, "");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(this.data, 0, size());
        byte[] digest = messageDigest.digest();
        uhy.d(digest);
        return new ByteString(digest);
    }

    public ByteString hmacSha1(ByteString key) {
        uhy.e((Object) key, "");
        return hmac$okio("HmacSHA1", key);
    }

    public ByteString hmacSha256(ByteString key) {
        uhy.e((Object) key, "");
        return hmac$okio("HmacSHA256", key);
    }

    public ByteString hmacSha512(ByteString key) {
        uhy.e((Object) key, "");
        return hmac$okio("HmacSHA512", key);
    }

    public ByteString hmac$okio(String algorithm, ByteString key) {
        uhy.e((Object) algorithm, "");
        uhy.e((Object) key, "");
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            byte[] doFinal = mac.doFinal(this.data);
            uhy.a(doFinal, "");
            return new ByteString(doFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static /* synthetic */ ByteString substring$default(ByteString byteString, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: substring");
        }
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = SegmentedByteString.getDEFAULT__ByteString_size();
        }
        return byteString.substring(i, i2);
    }

    public final byte getByte(int index) {
        return internalGet$okio(index);
    }

    public final int size() {
        return getSize$okio();
    }

    public ByteBuffer asByteBuffer() {
        ByteBuffer asReadOnlyBuffer = ByteBuffer.wrap(this.data).asReadOnlyBuffer();
        uhy.a(asReadOnlyBuffer, "");
        return asReadOnlyBuffer;
    }

    public void write(OutputStream out) throws IOException {
        uhy.e((Object) out, "");
        out.write(this.data);
    }

    public void write$okio(Buffer buffer, int offset, int byteCount) {
        uhy.e((Object) buffer, "");
        okio.internal.ByteString.commonWrite(this, buffer, offset, byteCount);
    }

    public static /* synthetic */ void copyInto$default(ByteString byteString, int i, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copyInto");
        }
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        byteString.copyInto(i, bArr, i2, i3);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return byteString.indexOf(byteString2, i);
    }

    public final int indexOf(ByteString other, int fromIndex) {
        uhy.e((Object) other, "");
        return indexOf(other.internalArray$okio(), fromIndex);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return byteString.indexOf(bArr, i);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if ((i2 & 2) != 0) {
            i = SegmentedByteString.getDEFAULT__ByteString_size();
        }
        return byteString.lastIndexOf(byteString2, i);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if ((i2 & 2) != 0) {
            i = SegmentedByteString.getDEFAULT__ByteString_size();
        }
        return byteString.lastIndexOf(bArr, i);
    }

    private final void readObject(ObjectInputStream in) throws IOException {
        ByteString read = INSTANCE.read(in, in.readInt());
        Field declaredField = ByteString.class.getDeclaredField("data");
        declaredField.setAccessible(true);
        declaredField.set(this, read.data);
    }

    private final void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.data.length);
        out.write(this.data);
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\nJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\fJ\u001d\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\b\u0016J\u0014\u0010\u0013\u001a\u00020\u00042\n\u0010\u0017\u001a\u00020\u0018\"\u00020\u0019H\u0007J%\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0002\b\u0016J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0002\b!J\u000e\u0010\u0007\u001a\u0004\u0018\u00010\u0004*\u00020\tH\u0007J\f\u0010\u000b\u001a\u00020\u0004*\u00020\tH\u0007J\u001b\u0010\"\u001a\u00020\u0004*\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\b\rJ\f\u0010\u0011\u001a\u00020\u0004*\u00020\tH\u0007J\u0019\u0010#\u001a\u00020\u0004*\u00020 2\u0006\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0002\b\u001eJ\u0011\u0010$\u001a\u00020\u0004*\u00020\u0015H\u0007¢\u0006\u0002\b\u0013J%\u0010$\u001a\u00020\u0004*\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0002\b\u0013R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lokio/ByteString$Companion;", "", "()V", "EMPTY", "Lokio/ByteString;", "serialVersionUID", "", "decodeBase64", "string", "", "-deprecated_decodeBase64", "decodeHex", "-deprecated_decodeHex", "encodeString", "charset", "Ljava/nio/charset/Charset;", "-deprecated_encodeString", "encodeUtf8", "-deprecated_encodeUtf8", "of", "buffer", "Ljava/nio/ByteBuffer;", "-deprecated_of", "data", "", "", "array", TypedValues.CycleType.S_WAVE_OFFSET, "", "byteCount", "read", "inputstream", "Ljava/io/InputStream;", "-deprecated_read", "encode", "readByteString", "toByteString", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public static /* synthetic */ ByteString of$default(Companion companion, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = SegmentedByteString.getDEFAULT__ByteString_size();
            }
            return companion.of(bArr, i, i2);
        }

        private Companion() {
        }

        @JvmStatic
        public final ByteString of(ByteBuffer byteBuffer) {
            uhy.e((Object) byteBuffer, "");
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return new ByteString(bArr);
        }

        public static /* synthetic */ ByteString encodeString$default(Companion companion, String str, Charset charset, int i, Object obj) {
            if ((i & 1) != 0) {
                charset = uju.h;
            }
            return companion.encodeString(str, charset);
        }

        @JvmStatic
        public final ByteString encodeString(String str, Charset charset) {
            uhy.e((Object) str, "");
            uhy.e((Object) charset, "");
            byte[] bytes = str.getBytes(charset);
            uhy.a(bytes, "");
            return new ByteString(bytes);
        }

        @JvmStatic
        public final ByteString read(InputStream inputStream, int i) throws IOException {
            uhy.e((Object) inputStream, "");
            if (i < 0) {
                throw new IllegalArgumentException(("byteCount < 0: " + i).toString());
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
            return new ByteString(bArr);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.decodeBase64()", imports = {"okio.ByteString.Companion.decodeBase64"}))
        /* renamed from: -deprecated_decodeBase64, reason: not valid java name */
        public final ByteString m1121deprecated_decodeBase64(String string) {
            uhy.e((Object) string, "");
            return decodeBase64(string);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.decodeHex()", imports = {"okio.ByteString.Companion.decodeHex"}))
        /* renamed from: -deprecated_decodeHex, reason: not valid java name */
        public final ByteString m1122deprecated_decodeHex(String string) {
            uhy.e((Object) string, "");
            return decodeHex(string);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.encode(charset)", imports = {"okio.ByteString.Companion.encode"}))
        /* renamed from: -deprecated_encodeString, reason: not valid java name */
        public final ByteString m1123deprecated_encodeString(String string, Charset charset) {
            uhy.e((Object) string, "");
            uhy.e((Object) charset, "");
            return encodeString(string, charset);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.encodeUtf8()", imports = {"okio.ByteString.Companion.encodeUtf8"}))
        /* renamed from: -deprecated_encodeUtf8, reason: not valid java name */
        public final ByteString m1124deprecated_encodeUtf8(String string) {
            uhy.e((Object) string, "");
            return encodeUtf8(string);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "buffer.toByteString()", imports = {"okio.ByteString.Companion.toByteString"}))
        /* renamed from: -deprecated_of, reason: not valid java name */
        public final ByteString m1125deprecated_of(ByteBuffer buffer) {
            uhy.e((Object) buffer, "");
            return of(buffer);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "array.toByteString(offset, byteCount)", imports = {"okio.ByteString.Companion.toByteString"}))
        /* renamed from: -deprecated_of, reason: not valid java name */
        public final ByteString m1126deprecated_of(byte[] array, int offset, int byteCount) {
            uhy.e((Object) array, "");
            return of(array, offset, byteCount);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "inputstream.readByteString(byteCount)", imports = {"okio.ByteString.Companion.readByteString"}))
        /* renamed from: -deprecated_read, reason: not valid java name */
        public final ByteString m1127deprecated_read(InputStream inputstream, int byteCount) {
            uhy.e((Object) inputstream, "");
            return read(inputstream, byteCount);
        }

        @JvmStatic
        public final ByteString of(byte... data) {
            uhy.e((Object) data, "");
            byte[] copyOf = Arrays.copyOf(data, data.length);
            uhy.a(copyOf, "");
            return new ByteString(copyOf);
        }

        @JvmStatic
        public final ByteString of(byte[] bArr, int i, int i2) {
            uhy.e((Object) bArr, "");
            int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(bArr, i2);
            SegmentedByteString.checkOffsetAndCount(bArr.length, i, resolveDefaultParameter);
            return new ByteString(uez.a(bArr, i, resolveDefaultParameter + i));
        }

        @JvmStatic
        public final ByteString encodeUtf8(String str) {
            uhy.e((Object) str, "");
            ByteString byteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
            byteString.setUtf8$okio(str);
            return byteString;
        }

        @JvmStatic
        public final ByteString decodeBase64(String str) {
            uhy.e((Object) str, "");
            byte[] decodeBase64ToArray = Base64.decodeBase64ToArray(str);
            if (decodeBase64ToArray != null) {
                return new ByteString(decodeBase64ToArray);
            }
            return null;
        }

        @JvmStatic
        public final ByteString decodeHex(String str) {
            uhy.e((Object) str, "");
            if (str.length() % 2 != 0) {
                throw new IllegalArgumentException(("Unexpected hex string: " + str).toString());
            }
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = (byte) ((okio.internal.ByteString.decodeHexDigit(str.charAt(i2)) << 4) + okio.internal.ByteString.decodeHexDigit(str.charAt(i2 + 1)));
            }
            return new ByteString(bArr);
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}))
    /* renamed from: -deprecated_getByte, reason: not valid java name */
    public final byte m1119deprecated_getByte(int index) {
        return getByte(index);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    /* renamed from: -deprecated_size, reason: not valid java name */
    public final int m1120deprecated_size() {
        return size();
    }

    public String utf8() {
        String utf8 = getUtf8();
        if (utf8 != null) {
            return utf8;
        }
        String utf8String = _JvmPlatformKt.toUtf8String(internalArray$okio());
        setUtf8$okio(utf8String);
        return utf8String;
    }

    public String base64() {
        return Base64.encodeBase64$default(getData(), null, 1, null);
    }

    public String base64Url() {
        return Base64.encodeBase64(getData(), Base64.getBASE64_URL_SAFE());
    }

    public String hex() {
        char[] cArr = new char[getData().length * 2];
        int i = 0;
        for (byte b : getData()) {
            int i2 = i + 1;
            cArr[i] = okio.internal.ByteString.getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            i += 2;
            cArr[i2] = okio.internal.ByteString.getHEX_DIGIT_CHARS()[b & BaseType.Obj];
        }
        return ujy.d(cArr);
    }

    public ByteString toAsciiLowercase() {
        for (int i = 0; i < getData().length; i++) {
            byte b = getData()[i];
            if (b >= 65 && b <= 90) {
                byte[] data = getData();
                byte[] copyOf = Arrays.copyOf(data, data.length);
                uhy.a(copyOf, "");
                copyOf[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b2 = copyOf[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        copyOf[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return this;
    }

    public ByteString toAsciiUppercase() {
        for (int i = 0; i < getData().length; i++) {
            byte b = getData()[i];
            if (b >= 97 && b <= 122) {
                byte[] data = getData();
                byte[] copyOf = Arrays.copyOf(data, data.length);
                uhy.a(copyOf, "");
                copyOf[i] = (byte) (b - 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b2 = copyOf[i2];
                    if (b2 >= 97 && b2 <= 122) {
                        copyOf[i2] = (byte) (b2 - 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return this;
    }

    public ByteString substring(int beginIndex, int endIndex) {
        int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(this, endIndex);
        if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
        if (resolveDefaultParameter <= getData().length) {
            if (resolveDefaultParameter - beginIndex >= 0) {
                return (beginIndex == 0 && resolveDefaultParameter == getData().length) ? this : new ByteString(uez.a(getData(), beginIndex, resolveDefaultParameter));
            }
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        throw new IllegalArgumentException(("endIndex > length(" + getData().length + g4.l).toString());
    }

    public byte internalGet$okio(int pos) {
        return getData()[pos];
    }

    public int getSize$okio() {
        return getData().length;
    }

    public byte[] toByteArray() {
        byte[] data = getData();
        byte[] copyOf = Arrays.copyOf(data, data.length);
        uhy.a(copyOf, "");
        return copyOf;
    }

    public byte[] internalArray$okio() {
        return getData();
    }

    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        uhy.e((Object) other, "");
        return other.rangeEquals(otherOffset, getData(), offset, byteCount);
    }

    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        uhy.e((Object) other, "");
        return offset >= 0 && offset <= getData().length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && SegmentedByteString.arrayRangeEquals(getData(), offset, other, otherOffset, byteCount);
    }

    public void copyInto(int offset, byte[] target, int targetOffset, int byteCount) {
        uhy.e((Object) target, "");
        uez.d(getData(), target, targetOffset, offset, byteCount + offset);
    }

    public final boolean startsWith(ByteString prefix) {
        uhy.e((Object) prefix, "");
        return rangeEquals(0, prefix, 0, prefix.size());
    }

    public final boolean startsWith(byte[] prefix) {
        uhy.e((Object) prefix, "");
        return rangeEquals(0, prefix, 0, prefix.length);
    }

    public final boolean endsWith(ByteString suffix) {
        uhy.e((Object) suffix, "");
        return rangeEquals(size() - suffix.size(), suffix, 0, suffix.size());
    }

    public final boolean endsWith(byte[] suffix) {
        uhy.e((Object) suffix, "");
        return rangeEquals(size() - suffix.length, suffix, 0, suffix.length);
    }

    public int indexOf(byte[] other, int fromIndex) {
        uhy.e((Object) other, "");
        int length = getData().length - other.length;
        int max = Math.max(fromIndex, 0);
        if (max <= length) {
            while (!SegmentedByteString.arrayRangeEquals(getData(), max, other, 0, other.length)) {
                if (max != length) {
                    max++;
                }
            }
            return max;
        }
        return -1;
    }

    public final int lastIndexOf(ByteString other, int fromIndex) {
        uhy.e((Object) other, "");
        return lastIndexOf(other.internalArray$okio(), fromIndex);
    }

    public int lastIndexOf(byte[] other, int fromIndex) {
        uhy.e((Object) other, "");
        for (int min = Math.min(SegmentedByteString.resolveDefaultParameter(this, fromIndex), getData().length - other.length); -1 < min; min--) {
            if (SegmentedByteString.arrayRangeEquals(getData(), min, other, 0, other.length)) {
                return min;
            }
        }
        return -1;
    }

    public boolean equals(Object other) {
        if (other != this) {
            if (!(other instanceof ByteString)) {
                return false;
            }
            ByteString byteString = (ByteString) other;
            if (byteString.size() != getData().length || !byteString.rangeEquals(0, getData(), 0, getData().length)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int hashCode = getHashCode();
        if (hashCode != 0) {
            return hashCode;
        }
        int hashCode2 = Arrays.hashCode(getData());
        setHashCode$okio(hashCode2);
        return hashCode2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002e A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030 A[ORIG_RETURN, RETURN] */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int compareTo(okio.ByteString r8) {
        /*
            r7 = this;
            java.lang.String r0 = ""
            defpackage.uhy.e(r8, r0)
            int r0 = r7.size()
            int r1 = r8.size()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L13:
            if (r4 >= r2) goto L29
            byte r5 = r7.getByte(r4)
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r6 = r8.getByte(r4)
            r6 = r6 & 255(0xff, float:3.57E-43)
            if (r5 != r6) goto L26
            int r4 = r4 + 1
            goto L13
        L26:
            if (r5 >= r6) goto L30
            goto L2e
        L29:
            if (r0 != r1) goto L2c
            goto L31
        L2c:
            if (r0 >= r1) goto L30
        L2e:
            r3 = -1
            goto L31
        L30:
            r3 = 1
        L31:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.compareTo(okio.ByteString):int");
    }

    public String toString() {
        String str;
        if (getData().length == 0) {
            str = "[size=0]";
        } else {
            int codePointIndexToCharIndex = okio.internal.ByteString.codePointIndexToCharIndex(getData(), 64);
            if (codePointIndexToCharIndex == -1) {
                if (getData().length <= 64) {
                    str = "[hex=" + hex() + ']';
                } else {
                    StringBuilder sb = new StringBuilder("[size=");
                    sb.append(getData().length);
                    sb.append(" hex=");
                    int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(this, 64);
                    if (resolveDefaultParameter > getData().length) {
                        throw new IllegalArgumentException(("endIndex > length(" + getData().length + g4.l).toString());
                    }
                    if (resolveDefaultParameter >= 0) {
                        sb.append((resolveDefaultParameter == getData().length ? this : new ByteString(uez.a(getData(), 0, resolveDefaultParameter))).hex());
                        sb.append("…]");
                        return sb.toString();
                    }
                    throw new IllegalArgumentException("endIndex < beginIndex".toString());
                }
            } else {
                String utf8 = utf8();
                String substring = utf8.substring(0, codePointIndexToCharIndex);
                uhy.a(substring, "");
                String c = ujy.c(ujy.c(ujy.c(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
                if (codePointIndexToCharIndex < utf8.length()) {
                    return "[size=" + getData().length + " text=" + c + "…]";
                }
                return "[text=" + c + ']';
            }
        }
        return str;
    }

    public final ByteString substring(int i) {
        return substring$default(this, i, 0, 2, null);
    }

    public final ByteString substring() {
        return substring$default(this, 0, 0, 3, null);
    }

    public final int lastIndexOf(byte[] bArr) {
        uhy.e((Object) bArr, "");
        return lastIndexOf$default(this, bArr, 0, 2, (Object) null);
    }

    public final int lastIndexOf(ByteString byteString) {
        uhy.e((Object) byteString, "");
        return lastIndexOf$default(this, byteString, 0, 2, (Object) null);
    }

    public final int indexOf(byte[] bArr) {
        uhy.e((Object) bArr, "");
        return indexOf$default(this, bArr, 0, 2, (Object) null);
    }

    public final int indexOf(ByteString byteString) {
        uhy.e((Object) byteString, "");
        return indexOf$default(this, byteString, 0, 2, (Object) null);
    }

    @JvmStatic
    public static final ByteString read(InputStream inputStream, int i) throws IOException {
        return INSTANCE.read(inputStream, i);
    }

    @JvmStatic
    public static final ByteString of(byte[] bArr, int i, int i2) {
        return INSTANCE.of(bArr, i, i2);
    }

    @JvmStatic
    public static final ByteString of(byte... bArr) {
        return INSTANCE.of(bArr);
    }

    @JvmStatic
    public static final ByteString of(ByteBuffer byteBuffer) {
        return INSTANCE.of(byteBuffer);
    }

    @JvmStatic
    public static final ByteString encodeUtf8(String str) {
        return INSTANCE.encodeUtf8(str);
    }

    @JvmStatic
    public static final ByteString encodeString(String str, Charset charset) {
        return INSTANCE.encodeString(str, charset);
    }

    @JvmStatic
    public static final ByteString decodeHex(String str) {
        return INSTANCE.decodeHex(str);
    }

    @JvmStatic
    public static final ByteString decodeBase64(String str) {
        return INSTANCE.decodeBase64(str);
    }
}
