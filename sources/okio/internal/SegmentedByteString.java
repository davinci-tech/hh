package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hms.network.embedded.g4;
import defpackage.ueu;
import defpackage.uez;
import defpackage.uhy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import okio.Buffer;
import okio.ByteString;
import okio.C0338SegmentedByteString;
import okio.Segment;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a-\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0080\b\u001a\u0017\u0010\u000e\u001a\u00020\u000f*\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0080\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\bH\u0080\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\bH\u0080\b\u001a\u0015\u0010\u0014\u001a\u00020\u0015*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u0017\u001a\u00020\u000f*\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u0017\u001a\u00020\u000f*\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0080\b\u001a\u001d\u0010\u001a\u001a\u00020\u0019*\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u0001H\u0080\b\u001a\r\u0010\u001d\u001a\u00020\u000b*\u00020\bH\u0080\b\u001a%\u0010\u001e\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0080\b\u001a]\u0010!\u001a\u00020\u0007*\u00020\b2K\u0010\"\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070#H\u0080\bø\u0001\u0000\u001aj\u0010!\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00012K\u0010\"\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070#H\u0082\b\u001a\u0014\u0010'\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0001H\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006("}, d2 = {"binarySearch", "", "", "value", "fromIndex", "toIndex", "commonCopyInto", "", "Lokio/SegmentedByteString;", TypedValues.CycleType.S_WAVE_OFFSET, "target", "", "targetOffset", "byteCount", "commonEquals", "", "other", "", "commonGetSize", "commonHashCode", "commonInternalGet", "", "pos", "commonRangeEquals", "otherOffset", "Lokio/ByteString;", "commonSubstring", "beginIndex", "endIndex", "commonToByteArray", "commonWrite", "buffer", "Lokio/Buffer;", "forEachSegment", "action", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "data", "segment", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.internal.-SegmentedByteString, reason: invalid class name */
/* loaded from: classes7.dex */
public final class SegmentedByteString {
    public static final int binarySearch(int[] iArr, int i, int i2, int i3) {
        uhy.e((Object) iArr, "");
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i) {
                i2 = i5 + 1;
            } else {
                if (i6 <= i) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return (-i2) - 1;
    }

    public static final int segment(C0338SegmentedByteString c0338SegmentedByteString, int i) {
        uhy.e((Object) c0338SegmentedByteString, "");
        int binarySearch = binarySearch(c0338SegmentedByteString.getDirectory(), i + 1, 0, c0338SegmentedByteString.getSegments().length);
        return binarySearch >= 0 ? binarySearch : ~binarySearch;
    }

    public static final void forEachSegment(C0338SegmentedByteString c0338SegmentedByteString, Function3<? super byte[], ? super Integer, ? super Integer, ueu> function3) {
        uhy.e((Object) c0338SegmentedByteString, "");
        uhy.e((Object) function3, "");
        int length = c0338SegmentedByteString.getSegments().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = c0338SegmentedByteString.getDirectory()[length + i];
            int i4 = c0338SegmentedByteString.getDirectory()[i];
            function3.invoke(c0338SegmentedByteString.getSegments()[i], Integer.valueOf(i3), Integer.valueOf(i4 - i2));
            i++;
            i2 = i4;
        }
    }

    private static final void forEachSegment(C0338SegmentedByteString c0338SegmentedByteString, int i, int i2, Function3<? super byte[], ? super Integer, ? super Integer, ueu> function3) {
        int segment = segment(c0338SegmentedByteString, i);
        while (i < i2) {
            int i3 = segment == 0 ? 0 : c0338SegmentedByteString.getDirectory()[segment - 1];
            int i4 = c0338SegmentedByteString.getDirectory()[segment];
            int i5 = c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length + segment];
            int min = Math.min(i2, (i4 - i3) + i3) - i;
            function3.invoke(c0338SegmentedByteString.getSegments()[segment], Integer.valueOf(i5 + (i - i3)), Integer.valueOf(min));
            i += min;
            segment++;
        }
    }

    public static final ByteString commonSubstring(C0338SegmentedByteString c0338SegmentedByteString, int i, int i2) {
        uhy.e((Object) c0338SegmentedByteString, "");
        C0338SegmentedByteString c0338SegmentedByteString2 = c0338SegmentedByteString;
        int resolveDefaultParameter = okio.SegmentedByteString.resolveDefaultParameter(c0338SegmentedByteString2, i2);
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex=" + i + " < 0").toString());
        }
        if (resolveDefaultParameter > c0338SegmentedByteString.size()) {
            throw new IllegalArgumentException(("endIndex=" + resolveDefaultParameter + " > length(" + c0338SegmentedByteString.size() + g4.l).toString());
        }
        int i3 = resolveDefaultParameter - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(("endIndex=" + resolveDefaultParameter + " < beginIndex=" + i).toString());
        }
        if (i == 0 && resolveDefaultParameter == c0338SegmentedByteString.size()) {
            return c0338SegmentedByteString2;
        }
        if (i == resolveDefaultParameter) {
            return ByteString.EMPTY;
        }
        int segment = segment(c0338SegmentedByteString, i);
        int segment2 = segment(c0338SegmentedByteString, resolveDefaultParameter - 1);
        byte[][] bArr = (byte[][]) uez.a(c0338SegmentedByteString.getSegments(), segment, segment2 + 1);
        byte[][] bArr2 = bArr;
        int[] iArr = new int[bArr2.length * 2];
        if (segment <= segment2) {
            int i4 = segment;
            int i5 = 0;
            while (true) {
                iArr[i5] = Math.min(c0338SegmentedByteString.getDirectory()[i4] - i, i3);
                iArr[bArr2.length + i5] = c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length + i4];
                if (i4 == segment2) {
                    break;
                }
                i4++;
                i5++;
            }
        }
        int i6 = segment != 0 ? c0338SegmentedByteString.getDirectory()[segment - 1] : 0;
        int length = bArr2.length;
        iArr[length] = iArr[length] + (i - i6);
        return new C0338SegmentedByteString(bArr, iArr);
    }

    public static final byte commonInternalGet(C0338SegmentedByteString c0338SegmentedByteString, int i) {
        uhy.e((Object) c0338SegmentedByteString, "");
        okio.SegmentedByteString.checkOffsetAndCount(c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length - 1], i, 1L);
        int segment = segment(c0338SegmentedByteString, i);
        return c0338SegmentedByteString.getSegments()[segment][(i - (segment == 0 ? 0 : c0338SegmentedByteString.getDirectory()[segment - 1])) + c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length + segment]];
    }

    public static final int commonGetSize(C0338SegmentedByteString c0338SegmentedByteString) {
        uhy.e((Object) c0338SegmentedByteString, "");
        return c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length - 1];
    }

    public static final byte[] commonToByteArray(C0338SegmentedByteString c0338SegmentedByteString) {
        uhy.e((Object) c0338SegmentedByteString, "");
        byte[] bArr = new byte[c0338SegmentedByteString.size()];
        int length = c0338SegmentedByteString.getSegments().length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = c0338SegmentedByteString.getDirectory()[length + i];
            int i5 = c0338SegmentedByteString.getDirectory()[i];
            int i6 = i5 - i2;
            uez.d(c0338SegmentedByteString.getSegments()[i], bArr, i3, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    public static final boolean commonRangeEquals(C0338SegmentedByteString c0338SegmentedByteString, int i, ByteString byteString, int i2, int i3) {
        uhy.e((Object) c0338SegmentedByteString, "");
        uhy.e((Object) byteString, "");
        if (i < 0 || i > c0338SegmentedByteString.size() - i3) {
            return false;
        }
        int i4 = i3 + i;
        int segment = segment(c0338SegmentedByteString, i);
        while (i < i4) {
            int i5 = segment == 0 ? 0 : c0338SegmentedByteString.getDirectory()[segment - 1];
            int i6 = c0338SegmentedByteString.getDirectory()[segment];
            int i7 = c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length + segment];
            int min = Math.min(i4, (i6 - i5) + i5) - i;
            if (!byteString.rangeEquals(i2, c0338SegmentedByteString.getSegments()[segment], i7 + (i - i5), min)) {
                return false;
            }
            i2 += min;
            i += min;
            segment++;
        }
        return true;
    }

    public static final boolean commonRangeEquals(C0338SegmentedByteString c0338SegmentedByteString, int i, byte[] bArr, int i2, int i3) {
        uhy.e((Object) c0338SegmentedByteString, "");
        uhy.e((Object) bArr, "");
        if (i < 0 || i > c0338SegmentedByteString.size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i4 = i3 + i;
        int segment = segment(c0338SegmentedByteString, i);
        while (i < i4) {
            int i5 = segment == 0 ? 0 : c0338SegmentedByteString.getDirectory()[segment - 1];
            int i6 = c0338SegmentedByteString.getDirectory()[segment];
            int i7 = c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length + segment];
            int min = Math.min(i4, (i6 - i5) + i5) - i;
            if (!okio.SegmentedByteString.arrayRangeEquals(c0338SegmentedByteString.getSegments()[segment], i7 + (i - i5), bArr, i2, min)) {
                return false;
            }
            i2 += min;
            i += min;
            segment++;
        }
        return true;
    }

    public static final void commonCopyInto(C0338SegmentedByteString c0338SegmentedByteString, int i, byte[] bArr, int i2, int i3) {
        uhy.e((Object) c0338SegmentedByteString, "");
        uhy.e((Object) bArr, "");
        long j = i3;
        okio.SegmentedByteString.checkOffsetAndCount(c0338SegmentedByteString.size(), i, j);
        okio.SegmentedByteString.checkOffsetAndCount(bArr.length, i2, j);
        int i4 = i3 + i;
        int segment = segment(c0338SegmentedByteString, i);
        while (i < i4) {
            int i5 = segment == 0 ? 0 : c0338SegmentedByteString.getDirectory()[segment - 1];
            int i6 = c0338SegmentedByteString.getDirectory()[segment];
            int i7 = c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length + segment];
            int min = Math.min(i4, (i6 - i5) + i5) - i;
            int i8 = i7 + (i - i5);
            uez.d(c0338SegmentedByteString.getSegments()[segment], bArr, i2, i8, i8 + min);
            i2 += min;
            i += min;
            segment++;
        }
    }

    public static final boolean commonEquals(C0338SegmentedByteString c0338SegmentedByteString, Object obj) {
        uhy.e((Object) c0338SegmentedByteString, "");
        if (obj != c0338SegmentedByteString) {
            if (!(obj instanceof ByteString)) {
                return false;
            }
            ByteString byteString = (ByteString) obj;
            if (byteString.size() != c0338SegmentedByteString.size() || !c0338SegmentedByteString.rangeEquals(0, byteString, 0, c0338SegmentedByteString.size())) {
                return false;
            }
        }
        return true;
    }

    public static final int commonHashCode(C0338SegmentedByteString c0338SegmentedByteString) {
        uhy.e((Object) c0338SegmentedByteString, "");
        int hashCode$okio = c0338SegmentedByteString.getHashCode();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = c0338SegmentedByteString.getSegments().length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < length) {
            int i4 = c0338SegmentedByteString.getDirectory()[length + i];
            int i5 = c0338SegmentedByteString.getDirectory()[i];
            byte[] bArr = c0338SegmentedByteString.getSegments()[i];
            for (int i6 = i4; i6 < (i5 - i3) + i4; i6++) {
                i2 = (i2 * 31) + bArr[i6];
            }
            i++;
            i3 = i5;
        }
        c0338SegmentedByteString.setHashCode$okio(i2);
        return i2;
    }

    public static final void commonWrite(C0338SegmentedByteString c0338SegmentedByteString, Buffer buffer, int i, int i2) {
        uhy.e((Object) c0338SegmentedByteString, "");
        uhy.e((Object) buffer, "");
        int i3 = i + i2;
        int segment = segment(c0338SegmentedByteString, i);
        while (i < i3) {
            int i4 = segment == 0 ? 0 : c0338SegmentedByteString.getDirectory()[segment - 1];
            int i5 = c0338SegmentedByteString.getDirectory()[segment];
            int i6 = c0338SegmentedByteString.getDirectory()[c0338SegmentedByteString.getSegments().length + segment];
            int min = Math.min(i3, (i5 - i4) + i4) - i;
            int i7 = i6 + (i - i4);
            Segment segment2 = new Segment(c0338SegmentedByteString.getSegments()[segment], i7, i7 + min, true, false);
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
            i += min;
            segment++;
        }
        buffer.setSize$okio(buffer.size() + i2);
    }
}
