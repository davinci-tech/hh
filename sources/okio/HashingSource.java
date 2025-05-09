package okio;

import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.uhy;
import defpackage.uib;
import java.io.IOException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u00012\u00020\u0002:\u0001\u0019B\u0017\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u0017\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fB\u001f\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000eH\u0007¢\u0006\u0002\b\u0013J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0016R\u0011\u0010\u0010\u001a\u00020\u000e8G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lokio/HashingSource;", "Lokio/ForwardingSource;", "Lokio/Source;", "source", "digest", "Ljava/security/MessageDigest;", "(Lokio/Source;Ljava/security/MessageDigest;)V", "algorithm", "", "(Lokio/Source;Ljava/lang/String;)V", "mac", "Ljavax/crypto/Mac;", "(Lokio/Source;Ljavax/crypto/Mac;)V", MedalConstants.EVENT_KEY, "Lokio/ByteString;", "(Lokio/Source;Lokio/ByteString;Ljava/lang/String;)V", "hash", "()Lokio/ByteString;", "messageDigest", "-deprecated_hash", "read", "", "sink", "Lokio/Buffer;", "byteCount", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HashingSource extends ForwardingSource implements Source {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Mac mac;
    private final MessageDigest messageDigest;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingSource(Source source, MessageDigest messageDigest) {
        super(source);
        uhy.e((Object) source, "");
        uhy.e((Object) messageDigest, "");
        this.messageDigest = messageDigest;
        this.mac = null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public HashingSource(okio.Source r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = ""
            defpackage.uhy.e(r2, r0)
            defpackage.uhy.e(r3, r0)
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)
            defpackage.uhy.a(r3, r0)
            r1.<init>(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.HashingSource.<init>(okio.Source, java.lang.String):void");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingSource(Source source, Mac mac) {
        super(source);
        uhy.e((Object) source, "");
        uhy.e((Object) mac, "");
        this.mac = mac;
        this.messageDigest = null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public HashingSource(okio.Source r3, okio.ByteString r4, java.lang.String r5) {
        /*
            r2 = this;
            java.lang.String r0 = ""
            defpackage.uhy.e(r3, r0)
            defpackage.uhy.e(r4, r0)
            defpackage.uhy.e(r5, r0)
            javax.crypto.Mac r0 = javax.crypto.Mac.getInstance(r5)     // Catch: java.security.InvalidKeyException -> L26
            javax.crypto.spec.SecretKeySpec r1 = new javax.crypto.spec.SecretKeySpec     // Catch: java.security.InvalidKeyException -> L26
            byte[] r4 = r4.toByteArray()     // Catch: java.security.InvalidKeyException -> L26
            r1.<init>(r4, r5)     // Catch: java.security.InvalidKeyException -> L26
            java.security.Key r1 = (java.security.Key) r1     // Catch: java.security.InvalidKeyException -> L26
            r0.init(r1)     // Catch: java.security.InvalidKeyException -> L26
            ueu r4 = defpackage.ueu.d     // Catch: java.security.InvalidKeyException -> L26
            defpackage.uhy.d(r0)
            r2.<init>(r3, r0)
            return
        L26:
            r3 = move-exception
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r4.<init>(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.HashingSource.<init>(okio.Source, okio.ByteString, java.lang.String):void");
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(Buffer sink, long byteCount) throws IOException {
        uhy.e((Object) sink, "");
        long read = super.read(sink, byteCount);
        if (read != -1) {
            long size = sink.size() - read;
            long size2 = sink.size();
            Segment segment = sink.head;
            uhy.d(segment);
            while (size2 > size) {
                segment = segment.prev;
                uhy.d(segment);
                size2 -= segment.limit - segment.pos;
            }
            while (size2 < sink.size()) {
                int i = (int) ((segment.pos + size) - size2);
                MessageDigest messageDigest = this.messageDigest;
                if (messageDigest != null) {
                    messageDigest.update(segment.data, i, segment.limit - i);
                } else {
                    Mac mac = this.mac;
                    uhy.d(mac);
                    mac.update(segment.data, i, segment.limit - i);
                }
                size2 += segment.limit - segment.pos;
                segment = segment.next;
                uhy.d(segment);
                size = size2;
            }
        }
        return read;
    }

    public final ByteString hash() {
        byte[] doFinal;
        MessageDigest messageDigest = this.messageDigest;
        if (messageDigest != null) {
            doFinal = messageDigest.digest();
        } else {
            Mac mac = this.mac;
            uhy.d(mac);
            doFinal = mac.doFinal();
        }
        uhy.d(doFinal);
        return new ByteString(doFinal);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hash", imports = {}))
    /* renamed from: -deprecated_hash, reason: not valid java name */
    public final ByteString m1136deprecated_hash() {
        return hash();
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000f"}, d2 = {"Lokio/HashingSource$Companion;", "", "()V", "hmacSha1", "Lokio/HashingSource;", "source", "Lokio/Source;", MedalConstants.EVENT_KEY, "Lokio/ByteString;", "hmacSha256", "hmacSha512", "md5", "sha1", "sha256", "sha512", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final HashingSource md5(Source source) {
            uhy.e((Object) source, "");
            return new HashingSource(source, "MD5");
        }

        @JvmStatic
        public final HashingSource sha1(Source source) {
            uhy.e((Object) source, "");
            return new HashingSource(source, "SHA-1");
        }

        @JvmStatic
        public final HashingSource sha256(Source source) {
            uhy.e((Object) source, "");
            return new HashingSource(source, "SHA-256");
        }

        @JvmStatic
        public final HashingSource sha512(Source source) {
            uhy.e((Object) source, "");
            return new HashingSource(source, "SHA-512");
        }

        @JvmStatic
        public final HashingSource hmacSha1(Source source, ByteString key) {
            uhy.e((Object) source, "");
            uhy.e((Object) key, "");
            return new HashingSource(source, key, "HmacSHA1");
        }

        @JvmStatic
        public final HashingSource hmacSha256(Source source, ByteString key) {
            uhy.e((Object) source, "");
            uhy.e((Object) key, "");
            return new HashingSource(source, key, "HmacSHA256");
        }

        @JvmStatic
        public final HashingSource hmacSha512(Source source, ByteString key) {
            uhy.e((Object) source, "");
            uhy.e((Object) key, "");
            return new HashingSource(source, key, "HmacSHA512");
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }

    @JvmStatic
    public static final HashingSource sha512(Source source) {
        return INSTANCE.sha512(source);
    }

    @JvmStatic
    public static final HashingSource sha256(Source source) {
        return INSTANCE.sha256(source);
    }

    @JvmStatic
    public static final HashingSource sha1(Source source) {
        return INSTANCE.sha1(source);
    }

    @JvmStatic
    public static final HashingSource md5(Source source) {
        return INSTANCE.md5(source);
    }

    @JvmStatic
    public static final HashingSource hmacSha512(Source source, ByteString byteString) {
        return INSTANCE.hmacSha512(source, byteString);
    }

    @JvmStatic
    public static final HashingSource hmacSha256(Source source, ByteString byteString) {
        return INSTANCE.hmacSha256(source, byteString);
    }

    @JvmStatic
    public static final HashingSource hmacSha1(Source source, ByteString byteString) {
        return INSTANCE.hmacSha1(source, byteString);
    }
}
