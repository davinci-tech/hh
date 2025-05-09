package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hms.network.embedded.g4;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.uez;
import defpackage.uhy;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\u0005\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J(\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0015\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\r\u0010 \u001a\u00020\u0015H\u0010¢\u0006\u0002\b!J\b\u0010\"\u001a\u00020\u0015H\u0016J\b\u0010#\u001a\u00020\u0010H\u0016J\u001d\u0010$\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u0001H\u0010¢\u0006\u0002\b&J\u0018\u0010'\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0015H\u0016J\r\u0010)\u001a\u00020\u0004H\u0010¢\u0006\u0002\b*J\u0015\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0015H\u0010¢\u0006\u0002\b.J\u0018\u0010/\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0015H\u0016J(\u00100\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J(\u00100\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\u0010\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u000204H\u0016J\u0018\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u0015H\u0016J\b\u00108\u001a\u00020\u0001H\u0016J\b\u00109\u001a\u00020\u0001H\u0016J\b\u0010:\u001a\u00020\u0004H\u0016J\b\u0010;\u001a\u00020\u0001H\u0002J\b\u0010<\u001a\u00020\u0010H\u0016J\u0010\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020?H\u0016J%\u0010=\u001a\u00020\u00132\u0006\u0010@\u001a\u00020A2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0010¢\u0006\u0002\bBJ\b\u0010C\u001a\u00020DH\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006E"}, d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", "segments", "", "", "directory", "", "([[B[I)V", "getDirectory$okio", "()[I", "getSegments$okio", "()[[B", "[[B", "asByteBuffer", "Ljava/nio/ByteBuffer;", "base64", "", "base64Url", "copyInto", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "target", "targetOffset", "byteCount", "digest", "algorithm", "digest$okio", "equals", "", "other", "", "getSize", "getSize$okio", WatchFaceConstant.HASHCODE, "hex", "hmac", MedalConstants.EVENT_KEY, "hmac$okio", "indexOf", "fromIndex", "internalArray", "internalArray$okio", "internalGet", "", "pos", "internalGet$okio", "lastIndexOf", "rangeEquals", "otherOffset", "string", "charset", "Ljava/nio/charset/Charset;", "substring", "beginIndex", "endIndex", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toByteString", "toString", "write", "out", "Ljava/io/OutputStream;", "buffer", "Lokio/Buffer;", "write$okio", "writeReplace", "Ljava/lang/Object;", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.SegmentedByteString, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0338SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    /* renamed from: getSegments$okio, reason: from getter */
    public final byte[][] getSegments() {
        return this.segments;
    }

    /* renamed from: getDirectory$okio, reason: from getter */
    public final int[] getDirectory() {
        return this.directory;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0338SegmentedByteString(byte[][] bArr, int[] iArr) {
        super(ByteString.EMPTY.getData());
        uhy.e((Object) bArr, "");
        uhy.e((Object) iArr, "");
        this.segments = bArr;
        this.directory = iArr;
    }

    @Override // okio.ByteString
    public String string(Charset charset) {
        uhy.e((Object) charset, "");
        return toByteString().string(charset);
    }

    @Override // okio.ByteString
    public String base64() {
        return toByteString().base64();
    }

    @Override // okio.ByteString
    public String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    @Override // okio.ByteString
    public ByteString digest$okio(String algorithm) {
        uhy.e((Object) algorithm, "");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        int length = getSegments().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = getDirectory()[length + i];
            int i4 = getDirectory()[i];
            messageDigest.update(getSegments()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
        byte[] digest = messageDigest.digest();
        uhy.d(digest);
        return new ByteString(digest);
    }

    @Override // okio.ByteString
    public ByteString hmac$okio(String algorithm, ByteString key) {
        uhy.e((Object) algorithm, "");
        uhy.e((Object) key, "");
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            int length = getSegments().length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = getDirectory()[length + i];
                int i4 = getDirectory()[i];
                mac.update(getSegments()[i], i3, i4 - i2);
                i++;
                i2 = i4;
            }
            byte[] doFinal = mac.doFinal();
            uhy.a(doFinal, "");
            return new ByteString(doFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // okio.ByteString
    public String base64Url() {
        return toByteString().base64Url();
    }

    @Override // okio.ByteString
    public ByteBuffer asByteBuffer() {
        ByteBuffer asReadOnlyBuffer = ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
        uhy.a(asReadOnlyBuffer, "");
        return asReadOnlyBuffer;
    }

    @Override // okio.ByteString
    public int indexOf(byte[] other, int fromIndex) {
        uhy.e((Object) other, "");
        return toByteString().indexOf(other, fromIndex);
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] other, int fromIndex) {
        uhy.e((Object) other, "");
        return toByteString().lastIndexOf(other, fromIndex);
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    public byte[] internalArray$okio() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public String toString() {
        return toByteString().toString();
    }

    private final Object writeReplace() {
        ByteString byteString = toByteString();
        uhy.b(byteString, "");
        return byteString;
    }

    @Override // okio.ByteString
    public ByteString substring(int beginIndex, int endIndex) {
        C0338SegmentedByteString c0338SegmentedByteString = this;
        int resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(c0338SegmentedByteString, endIndex);
        if (beginIndex < 0) {
            throw new IllegalArgumentException(("beginIndex=" + beginIndex + " < 0").toString());
        }
        if (resolveDefaultParameter > size()) {
            throw new IllegalArgumentException(("endIndex=" + resolveDefaultParameter + " > length(" + size() + g4.l).toString());
        }
        int i = resolveDefaultParameter - beginIndex;
        if (i < 0) {
            throw new IllegalArgumentException(("endIndex=" + resolveDefaultParameter + " < beginIndex=" + beginIndex).toString());
        }
        if (beginIndex == 0 && resolveDefaultParameter == size()) {
            return c0338SegmentedByteString;
        }
        if (beginIndex == resolveDefaultParameter) {
            return ByteString.EMPTY;
        }
        int segment = okio.internal.SegmentedByteString.segment(this, beginIndex);
        int segment2 = okio.internal.SegmentedByteString.segment(this, resolveDefaultParameter - 1);
        byte[][] bArr = (byte[][]) uez.a(getSegments(), segment, segment2 + 1);
        byte[][] bArr2 = bArr;
        int[] iArr = new int[bArr2.length * 2];
        if (segment <= segment2) {
            int i2 = segment;
            int i3 = 0;
            while (true) {
                iArr[i3] = Math.min(getDirectory()[i2] - beginIndex, i);
                iArr[bArr2.length + i3] = getDirectory()[getSegments().length + i2];
                if (i2 == segment2) {
                    break;
                }
                i2++;
                i3++;
            }
        }
        int i4 = segment != 0 ? getDirectory()[segment - 1] : 0;
        int length = bArr2.length;
        iArr[length] = iArr[length] + (beginIndex - i4);
        return new C0338SegmentedByteString(bArr, iArr);
    }

    @Override // okio.ByteString
    public byte internalGet$okio(int pos) {
        SegmentedByteString.checkOffsetAndCount(getDirectory()[getSegments().length - 1], pos, 1L);
        int segment = okio.internal.SegmentedByteString.segment(this, pos);
        return getSegments()[segment][(pos - (segment == 0 ? 0 : getDirectory()[segment - 1])) + getDirectory()[getSegments().length + segment]];
    }

    @Override // okio.ByteString
    public int getSize$okio() {
        return getDirectory()[getSegments().length - 1];
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        byte[] bArr = new byte[size()];
        int length = getSegments().length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory()[length + i];
            int i5 = getDirectory()[i];
            int i6 = i5 - i2;
            uez.d(getSegments()[i], bArr, i3, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    @Override // okio.ByteString
    public void write(OutputStream out) throws IOException {
        uhy.e((Object) out, "");
        int length = getSegments().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = getDirectory()[length + i];
            int i4 = getDirectory()[i];
            out.write(getSegments()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    @Override // okio.ByteString
    public void write$okio(Buffer buffer, int offset, int byteCount) {
        uhy.e((Object) buffer, "");
        int i = offset + byteCount;
        int segment = okio.internal.SegmentedByteString.segment(this, offset);
        while (offset < i) {
            int i2 = segment == 0 ? 0 : getDirectory()[segment - 1];
            int i3 = getDirectory()[segment];
            int i4 = getDirectory()[getSegments().length + segment];
            int min = Math.min(i, (i3 - i2) + i2) - offset;
            int i5 = i4 + (offset - i2);
            Segment segment2 = new Segment(getSegments()[segment], i5, i5 + min, true, false);
            if (buffer.head == null) {
                segment2.prev = segment2;
                segment2.next = segment2.prev;
                buffer.head = segment2.next;
            } else {
                Segment segment3 = buffer.head;
                uhy.d(segment3);
                Segment segment4 = segment3.prev;
                uhy.d(segment4);
                segment4.push(segment2);
            }
            offset += min;
            segment++;
        }
        buffer.setSize$okio(buffer.size() + byteCount);
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        uhy.e((Object) other, "");
        if (offset < 0 || offset > size() - byteCount) {
            return false;
        }
        int i = byteCount + offset;
        int segment = okio.internal.SegmentedByteString.segment(this, offset);
        while (offset < i) {
            int i2 = segment == 0 ? 0 : getDirectory()[segment - 1];
            int i3 = getDirectory()[segment];
            int i4 = getDirectory()[getSegments().length + segment];
            int min = Math.min(i, (i3 - i2) + i2) - offset;
            if (!other.rangeEquals(otherOffset, getSegments()[segment], i4 + (offset - i2), min)) {
                return false;
            }
            otherOffset += min;
            offset += min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        uhy.e((Object) other, "");
        if (offset < 0 || offset > size() - byteCount || otherOffset < 0 || otherOffset > other.length - byteCount) {
            return false;
        }
        int i = byteCount + offset;
        int segment = okio.internal.SegmentedByteString.segment(this, offset);
        while (offset < i) {
            int i2 = segment == 0 ? 0 : getDirectory()[segment - 1];
            int i3 = getDirectory()[segment];
            int i4 = getDirectory()[getSegments().length + segment];
            int min = Math.min(i, (i3 - i2) + i2) - offset;
            if (!SegmentedByteString.arrayRangeEquals(getSegments()[segment], i4 + (offset - i2), other, otherOffset, min)) {
                return false;
            }
            otherOffset += min;
            offset += min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public void copyInto(int offset, byte[] target, int targetOffset, int byteCount) {
        uhy.e((Object) target, "");
        long j = byteCount;
        SegmentedByteString.checkOffsetAndCount(size(), offset, j);
        SegmentedByteString.checkOffsetAndCount(target.length, targetOffset, j);
        int i = byteCount + offset;
        int segment = okio.internal.SegmentedByteString.segment(this, offset);
        while (offset < i) {
            int i2 = segment == 0 ? 0 : getDirectory()[segment - 1];
            int i3 = getDirectory()[segment];
            int i4 = getDirectory()[getSegments().length + segment];
            int min = Math.min(i, (i3 - i2) + i2) - offset;
            int i5 = i4 + (offset - i2);
            uez.d(getSegments()[segment], target, targetOffset, i5, i5 + min);
            targetOffset += min;
            offset += min;
            segment++;
        }
    }

    @Override // okio.ByteString
    public boolean equals(Object other) {
        if (other != this) {
            if (!(other instanceof ByteString)) {
                return false;
            }
            ByteString byteString = (ByteString) other;
            if (byteString.size() != size() || !rangeEquals(0, byteString, 0, size())) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.ByteString
    public int hashCode() {
        int hashCode$okio = getHashCode();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = getSegments().length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory()[length + i];
            int i5 = getDirectory()[i];
            byte[] bArr = getSegments()[i];
            for (int i6 = i4; i6 < (i5 - i3) + i4; i6++) {
                i2 = (i2 * 31) + bArr[i6];
            }
            i++;
            i3 = i5;
        }
        setHashCode$okio(i2);
        return i2;
    }
}
