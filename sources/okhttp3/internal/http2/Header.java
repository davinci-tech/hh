package okhttp3.internal.http2;

import com.huawei.hms.network.embedded.y;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.uhy;
import kotlin.Metadata;
import okio.ByteString;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0004\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000b\u001a\u00020\u0006HÆ\u0003J\t\u0010\f\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00062\b\b\u0002\u0010\u0004\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\nHÖ\u0001J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0010\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/internal/http2/Header;", "", "name", "", "value", "(Ljava/lang/String;Ljava/lang/String;)V", "Lokio/ByteString;", "(Lokio/ByteString;Ljava/lang/String;)V", "(Lokio/ByteString;Lokio/ByteString;)V", "hpackSize", "", "component1", "component2", "copy", "equals", "", "other", WatchFaceConstant.HASHCODE, "toString", "Companion", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class Header {
    public static final String RESPONSE_STATUS_UTF8 = ":status";
    public static final String TARGET_AUTHORITY_UTF8 = ":authority";
    public static final String TARGET_METHOD_UTF8 = ":method";
    public static final String TARGET_PATH_UTF8 = ":path";
    public static final String TARGET_SCHEME_UTF8 = ":scheme";
    public final int hpackSize;
    public final ByteString name;
    public final ByteString value;
    public static final ByteString PSEUDO_PREFIX = ByteString.INSTANCE.encodeUtf8(":");
    public static final ByteString RESPONSE_STATUS = ByteString.INSTANCE.encodeUtf8(":status");
    public static final ByteString TARGET_METHOD = ByteString.INSTANCE.encodeUtf8(":method");
    public static final ByteString TARGET_PATH = ByteString.INSTANCE.encodeUtf8(":path");
    public static final ByteString TARGET_SCHEME = ByteString.INSTANCE.encodeUtf8(":scheme");
    public static final ByteString TARGET_AUTHORITY = ByteString.INSTANCE.encodeUtf8(":authority");

    public Header(ByteString byteString, ByteString byteString2) {
        uhy.e((Object) byteString, "");
        uhy.e((Object) byteString2, "");
        this.name = byteString;
        this.value = byteString2;
        this.hpackSize = byteString.size() + 32 + byteString2.size();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Header(String str, String str2) {
        this(ByteString.INSTANCE.encodeUtf8(str), ByteString.INSTANCE.encodeUtf8(str2));
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Header(ByteString byteString, String str) {
        this(byteString, ByteString.INSTANCE.encodeUtf8(str));
        uhy.e((Object) byteString, "");
        uhy.e((Object) str, "");
    }

    public String toString() {
        return this.name.utf8() + ": " + this.value.utf8();
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.value.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Header)) {
            return false;
        }
        Header header = (Header) other;
        return uhy.e(this.name, header.name) && uhy.e(this.value, header.value);
    }

    public final Header copy(ByteString name, ByteString value) {
        uhy.e((Object) name, "");
        uhy.e((Object) value, "");
        return new Header(name, value);
    }

    /* renamed from: component2, reason: from getter */
    public final ByteString getValue() {
        return this.value;
    }

    /* renamed from: component1, reason: from getter */
    public final ByteString getName() {
        return this.name;
    }

    public static /* synthetic */ Header copy$default(Header header, ByteString byteString, ByteString byteString2, int i, Object obj) {
        if ((i & 1) != 0) {
            byteString = header.name;
        }
        if ((i & 2) != 0) {
            byteString2 = header.value;
        }
        return header.copy(byteString, byteString2);
    }
}
