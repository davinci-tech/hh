package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.location.LocationRequestCompat;
import androidx.exifinterface.media.ExifInterface;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hms.scankit.b;
import com.huawei.operation.ble.BleConstants;
import defpackage.uez;
import defpackage.uhy;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.connection.RealConnection;
import okio.Buffer;
import okio.ByteString;
import okio.C0338SegmentedByteString;
import okio.Options;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio.Utf8;
import okio._JvmPlatformKt;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0000\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\u0015H\u0080\b\u001a\r\u0010\u0016\u001a\u00020\u0014*\u00020\u0017H\u0080\b\u001a\r\u0010\u0018\u001a\u00020\u0007*\u00020\u0015H\u0080\b\u001a\r\u0010\u0019\u001a\u00020\u0015*\u00020\u0015H\u0080\b\u001a%\u0010\u001a\u001a\u00020\u0015*\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a\u0017\u0010\u001e\u001a\u00020\f*\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0080\b\u001a\u0015\u0010!\u001a\u00020\u0007*\u00020\u00172\u0006\u0010\"\u001a\u00020\nH\u0080\b\u001a\u0015\u0010#\u001a\u00020$*\u00020\u00152\u0006\u0010%\u001a\u00020\u0007H\u0080\b\u001a\r\u0010&\u001a\u00020\n*\u00020\u0015H\u0080\b\u001a%\u0010'\u001a\u00020\u0007*\u00020\u00152\u0006\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0080\b\u001a\u001d\u0010'\u001a\u00020\u0007*\u00020\u00152\u0006\u0010\u0010\u001a\u00020+2\u0006\u0010)\u001a\u00020\u0007H\u0080\b\u001a\u001d\u0010,\u001a\u00020\u0007*\u00020\u00152\u0006\u0010-\u001a\u00020+2\u0006\u0010)\u001a\u00020\u0007H\u0080\b\u001a\r\u0010.\u001a\u00020\n*\u00020\u0017H\u0080\b\u001a-\u0010/\u001a\u00020\f*\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0015\u00100\u001a\u00020\n*\u00020\u00152\u0006\u00101\u001a\u00020\u0001H\u0080\b\u001a%\u00100\u001a\u00020\n*\u00020\u00152\u0006\u00101\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u001d\u00100\u001a\u00020\u0007*\u00020\u00152\u0006\u00101\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a\u0015\u00102\u001a\u00020\u0007*\u00020\u00152\u0006\u00101\u001a\u000203H\u0080\b\u001a\u0014\u00104\u001a\u00020\u0017*\u00020\u00152\u0006\u00105\u001a\u00020\u0017H\u0000\u001a\r\u00106\u001a\u00020$*\u00020\u0015H\u0080\b\u001a\r\u00107\u001a\u00020\u0001*\u00020\u0015H\u0080\b\u001a\u0015\u00107\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a\r\u00108\u001a\u00020+*\u00020\u0015H\u0080\b\u001a\u0015\u00108\u001a\u00020+*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a\r\u00109\u001a\u00020\u0007*\u00020\u0015H\u0080\b\u001a\u0015\u0010:\u001a\u00020\u0014*\u00020\u00152\u0006\u00101\u001a\u00020\u0001H\u0080\b\u001a\u001d\u0010:\u001a\u00020\u0014*\u00020\u00152\u0006\u00101\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a\r\u0010;\u001a\u00020\u0007*\u00020\u0015H\u0080\b\u001a\r\u0010<\u001a\u00020\n*\u00020\u0015H\u0080\b\u001a\r\u0010=\u001a\u00020\u0007*\u00020\u0015H\u0080\b\u001a\r\u0010>\u001a\u00020?*\u00020\u0015H\u0080\b\u001a\u0014\u0010@\u001a\u00020\u0017*\u00020\u00152\u0006\u00105\u001a\u00020\u0017H\u0000\u001a\u0015\u0010A\u001a\u00020B*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a\r\u0010C\u001a\u00020\n*\u00020\u0015H\u0080\b\u001a\u000f\u0010D\u001a\u0004\u0018\u00010B*\u00020\u0015H\u0080\b\u001a\u0015\u0010E\u001a\u00020B*\u00020\u00152\u0006\u0010F\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010G\u001a\u00020\u0007*\u00020\u00172\u0006\u0010H\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010I\u001a\u00020\n*\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010J\u001a\u00020\n*\u00020\u00152\u0006\u0010K\u001a\u00020LH\u0080\b\u001a\u0015\u0010M\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a\r\u0010N\u001a\u00020+*\u00020\u0015H\u0080\b\u001a\u0015\u0010N\u001a\u00020+*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u0015\u0010O\u001a\u00020\u000e*\u00020\u00152\u0006\u0010P\u001a\u00020\nH\u0080\b\u001a\u0015\u0010Q\u001a\u00020\u0015*\u00020\u00152\u0006\u0010R\u001a\u00020\u0001H\u0080\b\u001a%\u0010Q\u001a\u00020\u0015*\u00020\u00152\u0006\u0010R\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u001d\u0010Q\u001a\u00020\u0014*\u00020\u00152\u0006\u0010R\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a)\u0010Q\u001a\u00020\u0015*\u00020\u00152\u0006\u0010S\u001a\u00020+2\b\b\u0002\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010\u001d\u001a\u00020\nH\u0080\b\u001a\u001d\u0010Q\u001a\u00020\u0015*\u00020\u00152\u0006\u0010R\u001a\u00020T2\u0006\u0010\u001d\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010U\u001a\u00020\u0007*\u00020\u00152\u0006\u0010R\u001a\u00020TH\u0080\b\u001a\u0015\u0010V\u001a\u00020\u0015*\u00020\u00152\u0006\u0010(\u001a\u00020\nH\u0080\b\u001a\u0015\u0010W\u001a\u00020\u0015*\u00020\u00152\u0006\u0010X\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010Y\u001a\u00020\u0015*\u00020\u00152\u0006\u0010X\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010Z\u001a\u00020\u0015*\u00020\u00152\u0006\u0010[\u001a\u00020\nH\u0080\b\u001a\u0015\u0010\\\u001a\u00020\u0015*\u00020\u00152\u0006\u0010X\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010]\u001a\u00020\u0015*\u00020\u00152\u0006\u0010^\u001a\u00020\nH\u0080\b\u001a%\u0010_\u001a\u00020\u0015*\u00020\u00152\u0006\u0010`\u001a\u00020B2\u0006\u0010a\u001a\u00020\n2\u0006\u0010b\u001a\u00020\nH\u0080\b\u001a\u0015\u0010c\u001a\u00020\u0015*\u00020\u00152\u0006\u0010d\u001a\u00020\nH\u0080\b\u001a\u0014\u0010e\u001a\u00020B*\u00020\u00152\u0006\u0010f\u001a\u00020\u0007H\u0000\u001a?\u0010g\u001a\u0002Hh\"\u0004\b\u0000\u0010h*\u00020\u00152\u0006\u0010)\u001a\u00020\u00072\u001a\u0010i\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002Hh0jH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010k\u001a\u001e\u0010l\u001a\u00020\n*\u00020\u00152\u0006\u0010K\u001a\u00020L2\b\b\u0002\u0010m\u001a\u00020\fH\u0000\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006n"}, d2 = {"HEX_DIGIT_BYTES", "", "getHEX_DIGIT_BYTES$annotations", "()V", "getHEX_DIGIT_BYTES", "()[B", "OVERFLOW_DIGIT_START", "", "OVERFLOW_ZONE", "SEGMENTING_THRESHOLD", "", "rangeEquals", "", "segment", "Lokio/Segment;", "segmentPos", "bytes", "bytesOffset", "bytesLimit", "commonClear", "", "Lokio/Buffer;", "commonClose", "Lokio/Buffer$UnsafeCursor;", "commonCompleteSegmentByteCount", "commonCopy", "commonCopyTo", "out", TypedValues.CycleType.S_WAVE_OFFSET, "byteCount", "commonEquals", "other", "", "commonExpandBuffer", "minByteCount", "commonGet", "", "pos", "commonHashCode", "commonIndexOf", b.H, "fromIndex", "toIndex", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonNext", "commonRangeEquals", "commonRead", "sink", "commonReadAll", "Lokio/Sink;", "commonReadAndWriteUnsafe", "unsafeCursor", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadLong", "commonReadShort", "", "commonReadUnsafe", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", BleConstants.LIMIT, "commonResizeBuffer", "newSize", "commonSeek", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonSnapshot", "commonWritableSegment", "minimumCapacity", "commonWrite", "source", "byteString", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "commonWriteDecimalLong", FitRunPlayAudio.PLAY_TYPE_V, "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteLong", "commonWriteShort", "s", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "readUtf8Line", "newline", "seek", ExifInterface.GPS_DIRECTION_TRUE, "lambda", "Lkotlin/Function2;", "(Lokio/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "selectPrefix", "selectTruncated", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.internal.-Buffer, reason: invalid class name */
/* loaded from: classes.dex */
public final class Buffer {
    private static final byte[] HEX_DIGIT_BYTES = _JvmPlatformKt.asUtf8ToByteArray("0123456789abcdef");
    public static final long OVERFLOW_DIGIT_START = -7;
    public static final long OVERFLOW_ZONE = -922337203685477580L;
    public static final int SEGMENTING_THRESHOLD = 4096;

    public static /* synthetic */ void getHEX_DIGIT_BYTES$annotations() {
    }

    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static final boolean rangeEquals(Segment segment, int i, byte[] bArr, int i2, int i3) {
        uhy.e((Object) segment, "");
        uhy.e((Object) bArr, "");
        int i4 = segment.limit;
        byte[] bArr2 = segment.data;
        while (i2 < i3) {
            if (i == i4) {
                segment = segment.next;
                uhy.d(segment);
                byte[] bArr3 = segment.data;
                bArr2 = bArr3;
                i = segment.pos;
                i4 = segment.limit;
            }
            if (bArr2[i] != bArr[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public static final String readUtf8Line(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        if (j > 0) {
            long j2 = j - 1;
            if (buffer.getByte(j2) == 13) {
                String readUtf8 = buffer.readUtf8(j2);
                buffer.skip(2L);
                return readUtf8;
            }
        }
        String readUtf82 = buffer.readUtf8(j);
        buffer.skip(1L);
        return readUtf82;
    }

    public static final <T> T seek(okio.Buffer buffer, long j, Function2<? super Segment, ? super Long, ? extends T> function2) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) function2, "");
        Segment segment = buffer.head;
        if (segment == null) {
            return function2.invoke(null, -1L);
        }
        if (buffer.size() - j < j) {
            long size = buffer.size();
            while (size > j) {
                segment = segment.prev;
                uhy.d(segment);
                size -= segment.limit - segment.pos;
            }
            return function2.invoke(segment, Long.valueOf(size));
        }
        long j2 = 0;
        while (true) {
            long j3 = (segment.limit - segment.pos) + j2;
            if (j3 <= j) {
                segment = segment.next;
                uhy.d(segment);
                j2 = j3;
            } else {
                return function2.invoke(segment, Long.valueOf(j2));
            }
        }
    }

    public static /* synthetic */ int selectPrefix$default(okio.Buffer buffer, Options options, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return selectPrefix(buffer, options, z);
    }

    public static final int selectPrefix(okio.Buffer buffer, Options options, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        uhy.e((Object) buffer, "");
        uhy.e((Object) options, "");
        Segment segment = buffer.head;
        int i5 = -1;
        if (segment == null) {
            return z ? -2 : -1;
        }
        byte[] bArr = segment.data;
        int i6 = segment.pos;
        int i7 = segment.limit;
        int[] trie = options.getTrie();
        Segment segment2 = segment;
        int i8 = -1;
        int i9 = 0;
        loop0: while (true) {
            int i10 = i9 + 1;
            int i11 = trie[i9];
            int i12 = i9 + 2;
            int i13 = trie[i10];
            if (i13 != i5) {
                i8 = i13;
            }
            if (segment2 == null) {
                break;
            }
            if (i11 >= 0) {
                int i14 = i6 + 1;
                byte b = bArr[i6];
                for (int i15 = i12; i15 != i12 + i11; i15++) {
                    if ((b & 255) == trie[i15]) {
                        i = trie[i15 + i11];
                        if (i14 == i7) {
                            segment2 = segment2.next;
                            uhy.d(segment2);
                            i2 = segment2.pos;
                            bArr = segment2.data;
                            i7 = segment2.limit;
                            if (segment2 == segment) {
                                segment2 = null;
                            }
                        } else {
                            i2 = i14;
                        }
                    }
                }
                return i8;
            }
            int i16 = i12;
            while (true) {
                int i17 = i6 + 1;
                int i18 = i16 + 1;
                if ((bArr[i6] & 255) != trie[i16]) {
                    return i8;
                }
                boolean z2 = i18 == (i11 * (-1)) + i12;
                if (i17 == i7) {
                    uhy.d(segment2);
                    Segment segment3 = segment2.next;
                    uhy.d(segment3);
                    i4 = segment3.pos;
                    byte[] bArr2 = segment3.data;
                    i3 = segment3.limit;
                    if (segment3 != segment) {
                        segment2 = segment3;
                        bArr = bArr2;
                    } else {
                        if (!z2) {
                            break loop0;
                        }
                        bArr = bArr2;
                        segment2 = null;
                    }
                } else {
                    i3 = i7;
                    i4 = i17;
                }
                if (z2) {
                    i = trie[i18];
                    i2 = i4;
                    i7 = i3;
                    break;
                }
                i6 = i4;
                i7 = i3;
                i16 = i18;
            }
            if (i >= 0) {
                return i;
            }
            i9 = -i;
            i6 = i2;
            i5 = -1;
        }
        if (z) {
            return -2;
        }
        return i8;
    }

    public static final okio.Buffer commonCopyTo(okio.Buffer buffer, okio.Buffer buffer2, long j, long j2) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) buffer2, "");
        SegmentedByteString.checkOffsetAndCount(buffer.size(), j, j2);
        if (j2 == 0) {
            return buffer;
        }
        buffer2.setSize$okio(buffer2.size() + j2);
        Segment segment = buffer.head;
        while (true) {
            uhy.d(segment);
            if (j < segment.limit - segment.pos) {
                break;
            }
            j -= segment.limit - segment.pos;
            segment = segment.next;
        }
        while (j2 > 0) {
            uhy.d(segment);
            Segment sharedCopy = segment.sharedCopy();
            sharedCopy.pos += (int) j;
            sharedCopy.limit = Math.min(sharedCopy.pos + ((int) j2), sharedCopy.limit);
            if (buffer2.head == null) {
                sharedCopy.prev = sharedCopy;
                sharedCopy.next = sharedCopy.prev;
                buffer2.head = sharedCopy.next;
            } else {
                Segment segment2 = buffer2.head;
                uhy.d(segment2);
                Segment segment3 = segment2.prev;
                uhy.d(segment3);
                segment3.push(sharedCopy);
            }
            j2 -= sharedCopy.limit - sharedCopy.pos;
            segment = segment.next;
            j = 0;
        }
        return buffer;
    }

    public static final long commonCompleteSegmentByteCount(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        long size = buffer.size();
        if (size == 0) {
            return 0L;
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        Segment segment2 = segment.prev;
        uhy.d(segment2);
        return (segment2.limit >= 8192 || !segment2.owner) ? size : size - (segment2.limit - segment2.pos);
    }

    public static final byte commonReadByte(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        if (buffer.size() == 0) {
            throw new EOFException();
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        int i = segment.pos;
        int i2 = segment.limit;
        int i3 = i + 1;
        byte b = segment.data[i];
        buffer.setSize$okio(buffer.size() - 1);
        if (i3 == i2) {
            buffer.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i3;
        }
        return b;
    }

    public static final short commonReadShort(okio.Buffer buffer) {
        int i;
        int i2;
        uhy.e((Object) buffer, "");
        if (buffer.size() < 2) {
            throw new EOFException();
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        int i3 = segment.pos;
        int i4 = segment.limit;
        if (i4 - i3 < 2) {
            byte readByte = buffer.readByte();
            i = buffer.readByte() & 255;
            i2 = (readByte & 255) << 8;
        } else {
            byte[] bArr = segment.data;
            int i5 = i3 + 1;
            byte b = bArr[i3];
            int i6 = i3 + 2;
            byte b2 = bArr[i5];
            buffer.setSize$okio(buffer.size() - 2);
            if (i6 == i4) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i6;
            }
            i = (b & 255) << 8;
            i2 = b2 & 255;
        }
        return (short) (i | i2);
    }

    public static final int commonReadInt(okio.Buffer buffer) {
        int i;
        int i2;
        uhy.e((Object) buffer, "");
        if (buffer.size() < 4) {
            throw new EOFException();
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        int i3 = segment.pos;
        int i4 = segment.limit;
        if (i4 - i3 < 4) {
            byte readByte = buffer.readByte();
            byte readByte2 = buffer.readByte();
            byte readByte3 = buffer.readByte();
            i = buffer.readByte() & 255;
            i2 = ((readByte & 255) << 24) | ((readByte2 & 255) << 16) | ((readByte3 & 255) << 8);
        } else {
            byte[] bArr = segment.data;
            byte b = bArr[i3];
            byte b2 = bArr[i3 + 1];
            int i5 = i3 + 3;
            byte b3 = bArr[i3 + 2];
            int i6 = i3 + 4;
            byte b4 = bArr[i5];
            buffer.setSize$okio(buffer.size() - 4);
            if (i6 == i4) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i6;
            }
            i = ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8);
            i2 = b4 & 255;
        }
        return i | i2;
    }

    public static final long commonReadLong(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        if (buffer.size() < 8) {
            throw new EOFException();
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        int i = segment.pos;
        int i2 = segment.limit;
        if (i2 - i < 8) {
            return ((buffer.readInt() & 4294967295L) << 32) | (4294967295L & buffer.readInt());
        }
        byte[] bArr = segment.data;
        long j = bArr[i];
        long j2 = bArr[i + 1];
        long j3 = bArr[i + 2];
        long j4 = bArr[i + 3];
        long j5 = bArr[i + 4];
        long j6 = bArr[i + 5];
        int i3 = i + 7;
        long j7 = bArr[i + 6];
        int i4 = i + 8;
        long j8 = bArr[i3];
        buffer.setSize$okio(buffer.size() - 8);
        if (i4 == i2) {
            buffer.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i4;
        }
        return (255 & j8) | ((j7 & 255) << 8) | ((j4 & 255) << 32) | ((j & 255) << 56) | ((j2 & 255) << 48) | ((j3 & 255) << 40) | ((j5 & 255) << 24) | ((j6 & 255) << 16);
    }

    public static final byte commonGet(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        SegmentedByteString.checkOffsetAndCount(buffer.size(), j, 1L);
        Segment segment = buffer.head;
        if (segment == null) {
            Segment segment2 = null;
            uhy.d((Object) null);
            byte[] bArr = segment2.data;
            throw null;
        }
        if (buffer.size() - j < j) {
            long size = buffer.size();
            while (size > j) {
                segment = segment.prev;
                uhy.d(segment);
                size -= segment.limit - segment.pos;
            }
            uhy.d(segment);
            return segment.data[(int) ((segment.pos + j) - size)];
        }
        long j2 = 0;
        while (true) {
            long j3 = (segment.limit - segment.pos) + j2;
            if (j3 > j) {
                uhy.d(segment);
                return segment.data[(int) ((segment.pos + j) - j2)];
            }
            segment = segment.next;
            uhy.d(segment);
            j2 = j3;
        }
    }

    public static final void commonClear(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        buffer.skip(buffer.size());
    }

    public static final void commonSkip(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        while (j > 0) {
            Segment segment = buffer.head;
            if (segment == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, segment.limit - segment.pos);
            long j2 = min;
            buffer.setSize$okio(buffer.size() - j2);
            j -= j2;
            segment.pos += min;
            if (segment.pos == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    public static /* synthetic */ okio.Buffer commonWrite$default(okio.Buffer buffer, ByteString byteString, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = byteString.size();
        }
        uhy.e((Object) buffer, "");
        uhy.e((Object) byteString, "");
        byteString.write$okio(buffer, i, i2);
        return buffer;
    }

    public static final okio.Buffer commonWrite(okio.Buffer buffer, ByteString byteString, int i, int i2) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) byteString, "");
        byteString.write$okio(buffer, i, i2);
        return buffer;
    }

    public static final okio.Buffer commonWriteDecimalLong(okio.Buffer buffer, long j) {
        boolean z;
        uhy.e((Object) buffer, "");
        if (j == 0) {
            return buffer.writeByte(48);
        }
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return buffer.writeUtf8("-9223372036854775808");
            }
            z = true;
        } else {
            z = false;
        }
        int i = j < 100000000 ? j < PreConnectManager.CONNECT_INTERNAL ? j < 100 ? j < 10 ? 1 : 2 : j < 1000 ? 3 : 4 : j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8 : j < 1000000000000L ? j < RealConnection.IDLE_CONNECTION_HEALTHY_NS ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        if (z) {
            i++;
        }
        Segment writableSegment$okio = buffer.writableSegment$okio(i);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit + i;
        while (j != 0) {
            long j2 = 10;
            i2--;
            bArr[i2] = getHEX_DIGIT_BYTES()[(int) (j % j2)];
            j /= j2;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        writableSegment$okio.limit += i;
        buffer.setSize$okio(buffer.size() + i);
        return buffer;
    }

    public static final okio.Buffer commonWriteHexadecimalUnsignedLong(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        if (j == 0) {
            return buffer.writeByte(48);
        }
        long j2 = (j >>> 1) | j;
        long j3 = j2 | (j2 >>> 2);
        long j4 = j3 | (j3 >>> 4);
        long j5 = j4 | (j4 >>> 8);
        long j6 = j5 | (j5 >>> 16);
        long j7 = j6 | (j6 >>> 32);
        long j8 = j7 - ((j7 >>> 1) & 6148914691236517205L);
        long j9 = ((j8 >>> 2) & 3689348814741910323L) + (j8 & 3689348814741910323L);
        long j10 = ((j9 >>> 4) + j9) & 1085102592571150095L;
        long j11 = j10 + (j10 >>> 8);
        long j12 = j11 + (j11 >>> 16);
        int i = (int) ((((j12 & 63) + ((j12 >>> 32) & 63)) + 3) / 4);
        Segment writableSegment$okio = buffer.writableSegment$okio(i);
        byte[] bArr = writableSegment$okio.data;
        int i2 = (writableSegment$okio.limit + i) - 1;
        int i3 = writableSegment$okio.limit;
        long j13 = j;
        for (int i4 = i2; i4 >= i3; i4--) {
            bArr[i4] = getHEX_DIGIT_BYTES()[(int) (15 & j13)];
            j13 >>>= 4;
        }
        writableSegment$okio.limit += i;
        buffer.setSize$okio(buffer.size() + i);
        return buffer;
    }

    public static final Segment commonWritableSegment(okio.Buffer buffer, int i) {
        uhy.e((Object) buffer, "");
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        if (buffer.head == null) {
            Segment take = SegmentPool.take();
            buffer.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        Segment segment2 = segment.prev;
        uhy.d(segment2);
        return (segment2.limit + i > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
    }

    public static final okio.Buffer commonWrite(okio.Buffer buffer, byte[] bArr) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) bArr, "");
        return buffer.write(bArr, 0, bArr.length);
    }

    public static final okio.Buffer commonWrite(okio.Buffer buffer, byte[] bArr, int i, int i2) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) bArr, "");
        long j = i2;
        SegmentedByteString.checkOffsetAndCount(bArr.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            Segment writableSegment$okio = buffer.writableSegment$okio(1);
            int min = Math.min(i3 - i, 8192 - writableSegment$okio.limit);
            int i4 = i + min;
            uez.d(bArr, writableSegment$okio.data, writableSegment$okio.limit, i, i4);
            writableSegment$okio.limit += min;
            i = i4;
        }
        buffer.setSize$okio(buffer.size() + j);
        return buffer;
    }

    public static final byte[] commonReadByteArray(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        return buffer.readByteArray(buffer.size());
    }

    public static final byte[] commonReadByteArray(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        }
        if (buffer.size() < j) {
            throw new EOFException();
        }
        byte[] bArr = new byte[(int) j];
        buffer.readFully(bArr);
        return bArr;
    }

    public static final int commonRead(okio.Buffer buffer, byte[] bArr) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) bArr, "");
        return buffer.read(bArr, 0, bArr.length);
    }

    public static final void commonReadFully(okio.Buffer buffer, byte[] bArr) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) bArr, "");
        int i = 0;
        while (i < bArr.length) {
            int read = buffer.read(bArr, i, bArr.length - i);
            if (read == -1) {
                throw new EOFException();
            }
            i += read;
        }
    }

    public static final int commonRead(okio.Buffer buffer, byte[] bArr, int i, int i2) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) bArr, "");
        SegmentedByteString.checkOffsetAndCount(bArr.length, i, i2);
        Segment segment = buffer.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i2, segment.limit - segment.pos);
        uez.d(segment.data, bArr, i, segment.pos, segment.pos + min);
        segment.pos += min;
        buffer.setSize$okio(buffer.size() - min);
        if (segment.pos == segment.limit) {
            buffer.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public static final long commonReadDecimalLong(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        if (buffer.size() == 0) {
            throw new EOFException();
        }
        boolean z = false;
        int i = 0;
        long j = 0;
        long j2 = -7;
        boolean z2 = false;
        do {
            Segment segment = buffer.head;
            uhy.d(segment);
            byte[] bArr = segment.data;
            int i2 = segment.pos;
            int i3 = segment.limit;
            while (i2 < i3) {
                byte b = bArr[i2];
                if (b >= 48 && b <= 57) {
                    int i4 = 48 - b;
                    if (j < OVERFLOW_ZONE || (j == OVERFLOW_ZONE && i4 < j2)) {
                        okio.Buffer writeByte = new okio.Buffer().writeDecimalLong(j).writeByte((int) b);
                        if (!z2) {
                            writeByte.readByte();
                        }
                        throw new NumberFormatException("Number too large: " + writeByte.readUtf8());
                    }
                    j = (j * 10) + i4;
                } else {
                    if (b != 45 || i != 0) {
                        z = true;
                        break;
                    }
                    j2--;
                    z2 = true;
                }
                i2++;
                i++;
            }
            if (i2 == i3) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i2;
            }
            if (z) {
                break;
            }
        } while (buffer.head != null);
        buffer.setSize$okio(buffer.size() - i);
        if (i >= (z2 ? 2 : 1)) {
            return z2 ? j : -j;
        }
        if (buffer.size() == 0) {
            throw new EOFException();
        }
        throw new NumberFormatException((z2 ? "Expected a digit" : "Expected a digit or '-'") + " but was 0x" + SegmentedByteString.toHexString(buffer.getByte(0L)));
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a8 A[EDGE_INSN: B:41:0x00a8->B:38:0x00a8 BREAK  A[LOOP:0: B:4:0x0012->B:40:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long commonReadHexadecimalUnsignedLong(okio.Buffer r14) {
        /*
            java.lang.String r0 = ""
            defpackage.uhy.e(r14, r0)
            long r0 = r14.size()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lb2
            r0 = 0
            r1 = r0
            r4 = r2
        L12:
            okio.Segment r6 = r14.head
            defpackage.uhy.d(r6)
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L1d:
            if (r8 >= r9) goto L94
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L2c
            r11 = 57
            if (r10 > r11) goto L2c
            int r11 = r10 + (-48)
            goto L43
        L2c:
            r11 = 97
            if (r10 < r11) goto L37
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L37
            int r11 = r10 + (-97)
            goto L41
        L37:
            r11 = 65
            if (r10 < r11) goto L78
            r11 = 70
            if (r10 > r11) goto L78
            int r11 = r10 + (-65)
        L41:
            int r11 = r11 + 10
        L43:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L53
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L1d
        L53:
            okio.Buffer r14 = new okio.Buffer
            r14.<init>()
            okio.Buffer r14 = r14.writeHexadecimalUnsignedLong(r4)
            okio.Buffer r14 = r14.writeByte(r10)
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Number too large: "
            r1.<init>(r2)
            java.lang.String r14 = r14.readUtf8()
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            r0.<init>(r14)
            throw r0
        L78:
            if (r0 == 0) goto L7c
            r1 = 1
            goto L94
        L7c:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r0 = "Expected leading [0-9a-fA-F] character but was 0x"
            r14.<init>(r0)
            java.lang.String r0 = okio.SegmentedByteString.toHexString(r10)
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            r0.<init>(r14)
            throw r0
        L94:
            if (r8 != r9) goto La0
            okio.Segment r7 = r6.pop()
            r14.head = r7
            okio.SegmentPool.recycle(r6)
            goto La2
        La0:
            r6.pos = r8
        La2:
            if (r1 != 0) goto La8
            okio.Segment r6 = r14.head
            if (r6 != 0) goto L12
        La8:
            long r1 = r14.size()
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.setSize$okio(r1)
            return r4
        Lb2:
            java.io.EOFException r14 = new java.io.EOFException
            r14.<init>()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.Buffer.commonReadHexadecimalUnsignedLong(okio.Buffer):long");
    }

    public static final ByteString commonReadByteString(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        return buffer.readByteString(buffer.size());
    }

    public static final ByteString commonReadByteString(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        }
        if (buffer.size() < j) {
            throw new EOFException();
        }
        if (j >= 4096) {
            ByteString snapshot = buffer.snapshot((int) j);
            buffer.skip(j);
            return snapshot;
        }
        return new ByteString(buffer.readByteArray(j));
    }

    public static final int commonSelect(okio.Buffer buffer, Options options) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) options, "");
        int selectPrefix$default = selectPrefix$default(buffer, options, false, 2, null);
        if (selectPrefix$default == -1) {
            return -1;
        }
        buffer.skip(options.getByteStrings()[selectPrefix$default].size());
        return selectPrefix$default;
    }

    public static final void commonReadFully(okio.Buffer buffer, okio.Buffer buffer2, long j) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) buffer2, "");
        if (buffer.size() < j) {
            buffer2.write(buffer, buffer.size());
            throw new EOFException();
        }
        buffer2.write(buffer, j);
    }

    public static final long commonReadAll(okio.Buffer buffer, Sink sink) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) sink, "");
        long size = buffer.size();
        if (size > 0) {
            sink.write(buffer, size);
        }
        return size;
    }

    public static final String commonReadUtf8(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        }
        if (buffer.size() < j) {
            throw new EOFException();
        }
        if (j == 0) {
            return "";
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        if (segment.pos + j > segment.limit) {
            return _Utf8Kt.commonToUtf8String$default(buffer.readByteArray(j), 0, 0, 3, null);
        }
        int i = (int) j;
        String commonToUtf8String = _Utf8Kt.commonToUtf8String(segment.data, segment.pos, segment.pos + i);
        segment.pos += i;
        buffer.setSize$okio(buffer.size() - j);
        if (segment.pos == segment.limit) {
            buffer.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return commonToUtf8String;
    }

    public static final String commonReadUtf8Line(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        long indexOf = buffer.indexOf((byte) 10);
        if (indexOf != -1) {
            return readUtf8Line(buffer, indexOf);
        }
        if (buffer.size() != 0) {
            return buffer.readUtf8(buffer.size());
        }
        return null;
    }

    public static final String commonReadUtf8LineStrict(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        if (j < 0) {
            throw new IllegalArgumentException(("limit < 0: " + j).toString());
        }
        long j2 = LocationRequestCompat.PASSIVE_INTERVAL;
        if (j != LocationRequestCompat.PASSIVE_INTERVAL) {
            j2 = j + 1;
        }
        long indexOf = buffer.indexOf((byte) 10, 0L, j2);
        if (indexOf != -1) {
            return readUtf8Line(buffer, indexOf);
        }
        if (j2 < buffer.size() && buffer.getByte(j2 - 1) == 13 && buffer.getByte(j2) == 10) {
            return readUtf8Line(buffer, j2);
        }
        okio.Buffer buffer2 = new okio.Buffer();
        buffer.copyTo(buffer2, 0L, Math.min(32, buffer.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(buffer.size(), j) + " content=" + buffer2.readByteString().hex() + (char) 8230);
    }

    public static final int commonReadUtf8CodePoint(okio.Buffer buffer) {
        int i;
        int i2;
        int i3;
        uhy.e((Object) buffer, "");
        if (buffer.size() == 0) {
            throw new EOFException();
        }
        byte b = buffer.getByte(0L);
        if ((b & 128) == 0) {
            i = b & Byte.MAX_VALUE;
            i3 = 0;
            i2 = 1;
        } else if ((b & 224) == 192) {
            i = b & 31;
            i2 = 2;
            i3 = 128;
        } else if ((b & 240) == 224) {
            i = b & BaseType.Obj;
            i2 = 3;
            i3 = 2048;
        } else {
            if ((b & 248) != 240) {
                buffer.skip(1L);
                return 65533;
            }
            i = b & 7;
            i2 = 4;
            i3 = 65536;
        }
        long j = i2;
        if (buffer.size() < j) {
            throw new EOFException("size < " + i2 + ": " + buffer.size() + " (to read code point prefixed 0x" + SegmentedByteString.toHexString(b) + g4.l);
        }
        for (int i4 = 1; i4 < i2; i4++) {
            long j2 = i4;
            byte b2 = buffer.getByte(j2);
            if ((b2 & 192) != 128) {
                buffer.skip(j2);
                return 65533;
            }
            i = (i << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
        }
        buffer.skip(j);
        if (i > 1114111) {
            return 65533;
        }
        if ((55296 > i || i >= 57344) && i >= i3) {
            return i;
        }
        return 65533;
    }

    public static final okio.Buffer commonWriteUtf8(okio.Buffer buffer, String str, int i, int i2) {
        char charAt;
        uhy.e((Object) buffer, "");
        uhy.e((Object) str, "");
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
        }
        while (i < i2) {
            char charAt2 = str.charAt(i);
            if (charAt2 < 128) {
                Segment writableSegment$okio = buffer.writableSegment$okio(1);
                byte[] bArr = writableSegment$okio.data;
                int i3 = writableSegment$okio.limit - i;
                int min = Math.min(i2, 8192 - i3);
                int i4 = i + 1;
                bArr[i + i3] = (byte) charAt2;
                while (true) {
                    i = i4;
                    if (i >= min || (charAt = str.charAt(i)) >= 128) {
                        break;
                    }
                    i4 = i + 1;
                    bArr[i + i3] = (byte) charAt;
                }
                int i5 = (i3 + i) - writableSegment$okio.limit;
                writableSegment$okio.limit += i5;
                buffer.setSize$okio(buffer.size() + i5);
            } else {
                if (charAt2 < 2048) {
                    Segment writableSegment$okio2 = buffer.writableSegment$okio(2);
                    writableSegment$okio2.data[writableSegment$okio2.limit] = (byte) ((charAt2 >> 6) | 192);
                    writableSegment$okio2.data[writableSegment$okio2.limit + 1] = (byte) ((charAt2 & '?') | 128);
                    writableSegment$okio2.limit += 2;
                    buffer.setSize$okio(buffer.size() + 2);
                } else if (charAt2 < 55296 || charAt2 > 57343) {
                    Segment writableSegment$okio3 = buffer.writableSegment$okio(3);
                    writableSegment$okio3.data[writableSegment$okio3.limit] = (byte) ((charAt2 >> '\f') | 224);
                    writableSegment$okio3.data[writableSegment$okio3.limit + 1] = (byte) (((charAt2 >> 6) & 63) | 128);
                    writableSegment$okio3.data[writableSegment$okio3.limit + 2] = (byte) ((charAt2 & '?') | 128);
                    writableSegment$okio3.limit += 3;
                    buffer.setSize$okio(buffer.size() + 3);
                } else {
                    int i6 = i + 1;
                    char charAt3 = i6 < i2 ? str.charAt(i6) : (char) 0;
                    if (charAt2 > 56319 || 56320 > charAt3 || charAt3 >= 57344) {
                        buffer.writeByte(63);
                        i = i6;
                    } else {
                        int i7 = (((charAt2 & 1023) << 10) | (charAt3 & 1023)) + 65536;
                        Segment writableSegment$okio4 = buffer.writableSegment$okio(4);
                        writableSegment$okio4.data[writableSegment$okio4.limit] = (byte) ((i7 >> 18) | GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
                        writableSegment$okio4.data[writableSegment$okio4.limit + 1] = (byte) (((i7 >> 12) & 63) | 128);
                        writableSegment$okio4.data[writableSegment$okio4.limit + 2] = (byte) (((i7 >> 6) & 63) | 128);
                        writableSegment$okio4.data[writableSegment$okio4.limit + 3] = (byte) ((i7 & 63) | 128);
                        writableSegment$okio4.limit += 4;
                        buffer.setSize$okio(buffer.size() + 4);
                        i += 2;
                    }
                }
                i++;
            }
        }
        return buffer;
    }

    public static final okio.Buffer commonWriteUtf8CodePoint(okio.Buffer buffer, int i) {
        uhy.e((Object) buffer, "");
        if (i < 128) {
            buffer.writeByte(i);
        } else if (i < 2048) {
            Segment writableSegment$okio = buffer.writableSegment$okio(2);
            writableSegment$okio.data[writableSegment$okio.limit] = (byte) ((i >> 6) | 192);
            writableSegment$okio.data[writableSegment$okio.limit + 1] = (byte) ((i & 63) | 128);
            writableSegment$okio.limit += 2;
            buffer.setSize$okio(buffer.size() + 2);
        } else if (55296 <= i && i < 57344) {
            buffer.writeByte(63);
        } else if (i < 65536) {
            Segment writableSegment$okio2 = buffer.writableSegment$okio(3);
            writableSegment$okio2.data[writableSegment$okio2.limit] = (byte) ((i >> 12) | 224);
            writableSegment$okio2.data[writableSegment$okio2.limit + 1] = (byte) (((i >> 6) & 63) | 128);
            writableSegment$okio2.data[writableSegment$okio2.limit + 2] = (byte) ((i & 63) | 128);
            writableSegment$okio2.limit += 3;
            buffer.setSize$okio(buffer.size() + 3);
        } else if (i <= 1114111) {
            Segment writableSegment$okio3 = buffer.writableSegment$okio(4);
            writableSegment$okio3.data[writableSegment$okio3.limit] = (byte) ((i >> 18) | GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
            writableSegment$okio3.data[writableSegment$okio3.limit + 1] = (byte) (((i >> 12) & 63) | 128);
            writableSegment$okio3.data[writableSegment$okio3.limit + 2] = (byte) (((i >> 6) & 63) | 128);
            writableSegment$okio3.data[writableSegment$okio3.limit + 3] = (byte) ((i & 63) | 128);
            writableSegment$okio3.limit += 4;
            buffer.setSize$okio(buffer.size() + 4);
        } else {
            throw new IllegalArgumentException("Unexpected code point: 0x" + SegmentedByteString.toHexString(i));
        }
        return buffer;
    }

    public static final long commonWriteAll(okio.Buffer buffer, Source source) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) source, "");
        long j = 0;
        while (true) {
            long read = source.read(buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public static final okio.Buffer commonWrite(okio.Buffer buffer, Source source, long j) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) source, "");
        while (j > 0) {
            long read = source.read(buffer, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
        }
        return buffer;
    }

    public static final okio.Buffer commonWriteByte(okio.Buffer buffer, int i) {
        uhy.e((Object) buffer, "");
        Segment writableSegment$okio = buffer.writableSegment$okio(1);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        writableSegment$okio.limit = i2 + 1;
        bArr[i2] = (byte) i;
        buffer.setSize$okio(buffer.size() + 1);
        return buffer;
    }

    public static final okio.Buffer commonWriteShort(okio.Buffer buffer, int i) {
        uhy.e((Object) buffer, "");
        Segment writableSegment$okio = buffer.writableSegment$okio(2);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 1] = (byte) (i & 255);
        writableSegment$okio.limit = i2 + 2;
        buffer.setSize$okio(buffer.size() + 2);
        return buffer;
    }

    public static final okio.Buffer commonWriteInt(okio.Buffer buffer, int i) {
        uhy.e((Object) buffer, "");
        Segment writableSegment$okio = buffer.writableSegment$okio(4);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >>> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
        writableSegment$okio.limit = i2 + 4;
        buffer.setSize$okio(buffer.size() + 4);
        return buffer;
    }

    public static final okio.Buffer commonWriteLong(okio.Buffer buffer, long j) {
        uhy.e((Object) buffer, "");
        Segment writableSegment$okio = buffer.writableSegment$okio(8);
        byte[] bArr = writableSegment$okio.data;
        int i = writableSegment$okio.limit;
        bArr[i] = (byte) ((j >>> 56) & 255);
        bArr[i + 1] = (byte) ((j >>> 48) & 255);
        bArr[i + 2] = (byte) ((j >>> 40) & 255);
        bArr[i + 3] = (byte) ((j >>> 32) & 255);
        bArr[i + 4] = (byte) ((j >>> 24) & 255);
        bArr[i + 5] = (byte) ((j >>> 16) & 255);
        bArr[i + 6] = (byte) ((j >>> 8) & 255);
        bArr[i + 7] = (byte) (j & 255);
        writableSegment$okio.limit = i + 8;
        buffer.setSize$okio(buffer.size() + 8);
        return buffer;
    }

    public static final void commonWrite(okio.Buffer buffer, okio.Buffer buffer2, long j) {
        Segment segment;
        uhy.e((Object) buffer, "");
        uhy.e((Object) buffer2, "");
        if (buffer2 == buffer) {
            throw new IllegalArgumentException("source == this".toString());
        }
        SegmentedByteString.checkOffsetAndCount(buffer2.size(), 0L, j);
        while (j > 0) {
            Segment segment2 = buffer2.head;
            uhy.d(segment2);
            int i = segment2.limit;
            uhy.d(buffer2.head);
            if (j < i - r1.pos) {
                if (buffer.head != null) {
                    Segment segment3 = buffer.head;
                    uhy.d(segment3);
                    segment = segment3.prev;
                } else {
                    segment = null;
                }
                if (segment != null && segment.owner) {
                    if ((segment.limit + j) - (segment.shared ? 0 : segment.pos) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        Segment segment4 = buffer2.head;
                        uhy.d(segment4);
                        segment4.writeTo(segment, (int) j);
                        buffer2.setSize$okio(buffer2.size() - j);
                        buffer.setSize$okio(buffer.size() + j);
                        return;
                    }
                }
                Segment segment5 = buffer2.head;
                uhy.d(segment5);
                buffer2.head = segment5.split((int) j);
            }
            Segment segment6 = buffer2.head;
            uhy.d(segment6);
            long j2 = segment6.limit - segment6.pos;
            buffer2.head = segment6.pop();
            if (buffer.head == null) {
                buffer.head = segment6;
                segment6.prev = segment6;
                segment6.next = segment6.prev;
            } else {
                Segment segment7 = buffer.head;
                uhy.d(segment7);
                Segment segment8 = segment7.prev;
                uhy.d(segment8);
                segment8.push(segment6).compact();
            }
            buffer2.setSize$okio(buffer2.size() - j2);
            buffer.setSize$okio(buffer.size() + j2);
            j -= j2;
        }
    }

    public static final long commonRead(okio.Buffer buffer, okio.Buffer buffer2, long j) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) buffer2, "");
        if (j < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        }
        if (buffer.size() == 0) {
            return -1L;
        }
        if (j > buffer.size()) {
            j = buffer.size();
        }
        buffer2.write(buffer, j);
        return j;
    }

    public static final long commonIndexOf(okio.Buffer buffer, byte b, long j, long j2) {
        Segment segment;
        int i;
        uhy.e((Object) buffer, "");
        long j3 = 0;
        if (0 > j || j > j2) {
            throw new IllegalArgumentException(("size=" + buffer.size() + " fromIndex=" + j + " toIndex=" + j2).toString());
        }
        if (j2 > buffer.size()) {
            j2 = buffer.size();
        }
        if (j == j2 || (segment = buffer.head) == null) {
            return -1L;
        }
        if (buffer.size() - j < j) {
            j3 = buffer.size();
            while (j3 > j) {
                segment = segment.prev;
                uhy.d(segment);
                j3 -= segment.limit - segment.pos;
            }
            if (segment == null) {
                return -1L;
            }
            while (j3 < j2) {
                byte[] bArr = segment.data;
                int min = (int) Math.min(segment.limit, (segment.pos + j2) - j3);
                i = (int) ((segment.pos + j) - j3);
                while (i < min) {
                    if (bArr[i] != b) {
                        i++;
                    }
                }
                j3 += segment.limit - segment.pos;
                segment = segment.next;
                uhy.d(segment);
                j = j3;
            }
            return -1L;
        }
        while (true) {
            long j4 = (segment.limit - segment.pos) + j3;
            if (j4 > j) {
                break;
            }
            segment = segment.next;
            uhy.d(segment);
            j3 = j4;
        }
        if (segment == null) {
            return -1L;
        }
        while (j3 < j2) {
            byte[] bArr2 = segment.data;
            int min2 = (int) Math.min(segment.limit, (segment.pos + j2) - j3);
            i = (int) ((segment.pos + j) - j3);
            while (i < min2) {
                if (bArr2[i] != b) {
                    i++;
                }
            }
            j3 += segment.limit - segment.pos;
            segment = segment.next;
            uhy.d(segment);
            j = j3;
        }
        return -1L;
        return (i - segment.pos) + j3;
    }

    public static final long commonIndexOf(okio.Buffer buffer, ByteString byteString, long j) {
        long j2;
        int i;
        long j3 = j;
        uhy.e((Object) buffer, "");
        uhy.e((Object) byteString, "");
        if (byteString.size() <= 0) {
            throw new IllegalArgumentException("bytes is empty".toString());
        }
        long j4 = 0;
        if (j3 < 0) {
            throw new IllegalArgumentException(("fromIndex < 0: " + j3).toString());
        }
        Segment segment = buffer.head;
        if (segment == null) {
            return -1L;
        }
        if (buffer.size() - j3 < j3) {
            j2 = buffer.size();
            while (j2 > j3) {
                segment = segment.prev;
                uhy.d(segment);
                j2 -= segment.limit - segment.pos;
            }
            if (segment == null) {
                return -1L;
            }
            byte[] internalArray$okio = byteString.internalArray$okio();
            byte b = internalArray$okio[0];
            int size = byteString.size();
            long size2 = (buffer.size() - size) + 1;
            while (j2 < size2) {
                byte[] bArr = segment.data;
                int min = (int) Math.min(segment.limit, (segment.pos + size2) - j2);
                i = (int) ((segment.pos + j3) - j2);
                while (i < min) {
                    if (bArr[i] != b || !rangeEquals(segment, i + 1, internalArray$okio, 1, size)) {
                        i++;
                    }
                }
                j2 += segment.limit - segment.pos;
                segment = segment.next;
                uhy.d(segment);
                j3 = j2;
            }
            return -1L;
        }
        while (true) {
            long j5 = (segment.limit - segment.pos) + j4;
            if (j5 > j3) {
                break;
            }
            segment = segment.next;
            uhy.d(segment);
            j4 = j5;
        }
        if (segment == null) {
            return -1L;
        }
        byte[] internalArray$okio2 = byteString.internalArray$okio();
        byte b2 = internalArray$okio2[0];
        int size3 = byteString.size();
        long size4 = (buffer.size() - size3) + 1;
        j2 = j4;
        while (j2 < size4) {
            byte[] bArr2 = segment.data;
            int min2 = (int) Math.min(segment.limit, (segment.pos + size4) - j2);
            i = (int) ((segment.pos + j3) - j2);
            while (i < min2) {
                if (bArr2[i] == b2 && rangeEquals(segment, i + 1, internalArray$okio2, 1, size3)) {
                }
                i++;
            }
            j2 += segment.limit - segment.pos;
            segment = segment.next;
            uhy.d(segment);
            j3 = j2;
        }
        return -1L;
        return (i - segment.pos) + j2;
    }

    public static final boolean commonRangeEquals(okio.Buffer buffer, long j, ByteString byteString, int i, int i2) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) byteString, "");
        if (j < 0 || i < 0 || i2 < 0 || buffer.size() - j < i2 || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (buffer.getByte(i3 + j) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean commonEquals(okio.Buffer buffer, Object obj) {
        uhy.e((Object) buffer, "");
        if (buffer == obj) {
            return true;
        }
        if (!(obj instanceof okio.Buffer)) {
            return false;
        }
        okio.Buffer buffer2 = (okio.Buffer) obj;
        if (buffer.size() != buffer2.size()) {
            return false;
        }
        long j = 0;
        if (buffer.size() == 0) {
            return true;
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        Segment segment2 = buffer2.head;
        uhy.d(segment2);
        int i = segment.pos;
        int i2 = segment2.pos;
        long j2 = 0;
        while (j2 < buffer.size()) {
            long min = Math.min(segment.limit - i, segment2.limit - i2);
            long j3 = j;
            while (j3 < min) {
                if (segment.data[i] != segment2.data[i2]) {
                    return false;
                }
                j3++;
                i++;
                i2++;
            }
            if (i == segment.limit) {
                segment = segment.next;
                uhy.d(segment);
                i = segment.pos;
            }
            if (i2 == segment2.limit) {
                segment2 = segment2.next;
                uhy.d(segment2);
                i2 = segment2.pos;
            }
            j2 += min;
            j = 0;
        }
        return true;
    }

    public static final int commonHashCode(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        Segment segment = buffer.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
            uhy.d(segment);
        } while (segment != buffer.head);
        return i;
    }

    public static final okio.Buffer commonCopy(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        okio.Buffer buffer2 = new okio.Buffer();
        if (buffer.size() == 0) {
            return buffer2;
        }
        Segment segment = buffer.head;
        uhy.d(segment);
        Segment sharedCopy = segment.sharedCopy();
        buffer2.head = sharedCopy;
        sharedCopy.prev = buffer2.head;
        sharedCopy.next = sharedCopy.prev;
        for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
            Segment segment3 = sharedCopy.prev;
            uhy.d(segment3);
            uhy.d(segment2);
            segment3.push(segment2.sharedCopy());
        }
        buffer2.setSize$okio(buffer.size());
        return buffer2;
    }

    public static final ByteString commonSnapshot(okio.Buffer buffer) {
        uhy.e((Object) buffer, "");
        if (buffer.size() > 2147483647L) {
            throw new IllegalStateException(("size > Int.MAX_VALUE: " + buffer.size()).toString());
        }
        return buffer.snapshot((int) buffer.size());
    }

    public static final ByteString commonSnapshot(okio.Buffer buffer, int i) {
        uhy.e((Object) buffer, "");
        if (i == 0) {
            return ByteString.EMPTY;
        }
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0L, i);
        Segment segment = buffer.head;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            uhy.d(segment);
            if (segment.limit == segment.pos) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += segment.limit - segment.pos;
            i4++;
            segment = segment.next;
        }
        byte[][] bArr = new byte[i4][];
        int[] iArr = new int[i4 * 2];
        Segment segment2 = buffer.head;
        int i5 = 0;
        while (i2 < i) {
            uhy.d(segment2);
            bArr[i5] = segment2.data;
            i2 += segment2.limit - segment2.pos;
            iArr[i5] = Math.min(i2, i);
            iArr[bArr.length + i5] = segment2.pos;
            segment2.shared = true;
            i5++;
            segment2 = segment2.next;
        }
        return new C0338SegmentedByteString(bArr, iArr);
    }

    public static final Buffer.UnsafeCursor commonReadUnsafe(okio.Buffer buffer, Buffer.UnsafeCursor unsafeCursor) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) unsafeCursor, "");
        Buffer.UnsafeCursor resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(unsafeCursor);
        if (resolveDefaultParameter.buffer != null) {
            throw new IllegalStateException("already attached to a buffer".toString());
        }
        resolveDefaultParameter.buffer = buffer;
        resolveDefaultParameter.readWrite = false;
        return resolveDefaultParameter;
    }

    public static final Buffer.UnsafeCursor commonReadAndWriteUnsafe(okio.Buffer buffer, Buffer.UnsafeCursor unsafeCursor) {
        uhy.e((Object) buffer, "");
        uhy.e((Object) unsafeCursor, "");
        Buffer.UnsafeCursor resolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(unsafeCursor);
        if (resolveDefaultParameter.buffer != null) {
            throw new IllegalStateException("already attached to a buffer".toString());
        }
        resolveDefaultParameter.buffer = buffer;
        resolveDefaultParameter.readWrite = true;
        return resolveDefaultParameter;
    }

    public static final int commonNext(Buffer.UnsafeCursor unsafeCursor) {
        uhy.e((Object) unsafeCursor, "");
        long j = unsafeCursor.offset;
        okio.Buffer buffer = unsafeCursor.buffer;
        uhy.d(buffer);
        if (j != buffer.size()) {
            return unsafeCursor.seek(unsafeCursor.offset == -1 ? 0L : unsafeCursor.offset + (unsafeCursor.end - unsafeCursor.start));
        }
        throw new IllegalStateException("no more bytes".toString());
    }

    public static final int commonSeek(Buffer.UnsafeCursor unsafeCursor, long j) {
        uhy.e((Object) unsafeCursor, "");
        okio.Buffer buffer = unsafeCursor.buffer;
        if (buffer == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
        }
        if (j < -1 || j > buffer.size()) {
            throw new ArrayIndexOutOfBoundsException("offset=" + j + " > size=" + buffer.size());
        }
        if (j == -1 || j == buffer.size()) {
            unsafeCursor.setSegment$okio(null);
            unsafeCursor.offset = j;
            unsafeCursor.data = null;
            unsafeCursor.start = -1;
            unsafeCursor.end = -1;
            return -1;
        }
        long size = buffer.size();
        Segment segment = buffer.head;
        Segment segment2 = buffer.head;
        long j2 = 0;
        if (unsafeCursor.getSegment() != null) {
            long j3 = unsafeCursor.offset;
            int i = unsafeCursor.start;
            uhy.d(unsafeCursor.getSegment());
            long j4 = j3 - (i - r10.pos);
            if (j4 > j) {
                segment2 = unsafeCursor.getSegment();
                size = j4;
            } else {
                segment = unsafeCursor.getSegment();
                j2 = j4;
            }
        }
        if (size - j > j - j2) {
            while (true) {
                uhy.d(segment);
                if (j < (segment.limit - segment.pos) + j2) {
                    break;
                }
                j2 += segment.limit - segment.pos;
                segment = segment.next;
            }
        } else {
            while (size > j) {
                uhy.d(segment2);
                segment2 = segment2.prev;
                uhy.d(segment2);
                size -= segment2.limit - segment2.pos;
            }
            j2 = size;
            segment = segment2;
        }
        if (unsafeCursor.readWrite) {
            uhy.d(segment);
            if (segment.shared) {
                Segment unsharedCopy = segment.unsharedCopy();
                if (buffer.head == segment) {
                    buffer.head = unsharedCopy;
                }
                segment = segment.push(unsharedCopy);
                Segment segment3 = segment.prev;
                uhy.d(segment3);
                segment3.pop();
            }
        }
        unsafeCursor.setSegment$okio(segment);
        unsafeCursor.offset = j;
        uhy.d(segment);
        unsafeCursor.data = segment.data;
        unsafeCursor.start = segment.pos + ((int) (j - j2));
        unsafeCursor.end = segment.limit;
        return unsafeCursor.end - unsafeCursor.start;
    }

    public static final long commonResizeBuffer(Buffer.UnsafeCursor unsafeCursor, long j) {
        uhy.e((Object) unsafeCursor, "");
        okio.Buffer buffer = unsafeCursor.buffer;
        if (buffer == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
        }
        if (!unsafeCursor.readWrite) {
            throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
        }
        long size = buffer.size();
        if (j <= size) {
            if (j < 0) {
                throw new IllegalArgumentException(("newSize < 0: " + j).toString());
            }
            long j2 = size - j;
            while (true) {
                if (j2 <= 0) {
                    break;
                }
                Segment segment = buffer.head;
                uhy.d(segment);
                Segment segment2 = segment.prev;
                uhy.d(segment2);
                long j3 = segment2.limit - segment2.pos;
                if (j3 <= j2) {
                    buffer.head = segment2.pop();
                    SegmentPool.recycle(segment2);
                    j2 -= j3;
                } else {
                    segment2.limit -= (int) j2;
                    break;
                }
            }
            unsafeCursor.setSegment$okio(null);
            unsafeCursor.offset = j;
            unsafeCursor.data = null;
            unsafeCursor.start = -1;
            unsafeCursor.end = -1;
        } else if (j > size) {
            long j4 = j - size;
            boolean z = true;
            while (j4 > 0) {
                Segment writableSegment$okio = buffer.writableSegment$okio(1);
                int min = (int) Math.min(j4, 8192 - writableSegment$okio.limit);
                writableSegment$okio.limit += min;
                j4 -= min;
                if (z) {
                    unsafeCursor.setSegment$okio(writableSegment$okio);
                    unsafeCursor.offset = size;
                    unsafeCursor.data = writableSegment$okio.data;
                    unsafeCursor.start = writableSegment$okio.limit - min;
                    unsafeCursor.end = writableSegment$okio.limit;
                    z = false;
                }
            }
        }
        buffer.setSize$okio(j);
        return size;
    }

    public static final long commonExpandBuffer(Buffer.UnsafeCursor unsafeCursor, int i) {
        uhy.e((Object) unsafeCursor, "");
        if (i <= 0) {
            throw new IllegalArgumentException(("minByteCount <= 0: " + i).toString());
        }
        if (i > 8192) {
            throw new IllegalArgumentException(("minByteCount > Segment.SIZE: " + i).toString());
        }
        okio.Buffer buffer = unsafeCursor.buffer;
        if (buffer == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
        }
        if (!unsafeCursor.readWrite) {
            throw new IllegalStateException("expandBuffer() only permitted for read/write buffers".toString());
        }
        long size = buffer.size();
        Segment writableSegment$okio = buffer.writableSegment$okio(i);
        int i2 = 8192 - writableSegment$okio.limit;
        writableSegment$okio.limit = 8192;
        long j = i2;
        buffer.setSize$okio(size + j);
        unsafeCursor.setSegment$okio(writableSegment$okio);
        unsafeCursor.offset = size;
        unsafeCursor.data = writableSegment$okio.data;
        unsafeCursor.start = 8192 - i2;
        unsafeCursor.end = 8192;
        return j;
    }

    public static final void commonClose(Buffer.UnsafeCursor unsafeCursor) {
        uhy.e((Object) unsafeCursor, "");
        if (unsafeCursor.buffer == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
        }
        unsafeCursor.buffer = null;
        unsafeCursor.setSegment$okio(null);
        unsafeCursor.offset = -1L;
        unsafeCursor.data = null;
        unsafeCursor.start = -1;
        unsafeCursor.end = -1;
    }

    public static final long commonIndexOfElement(okio.Buffer buffer, ByteString byteString, long j) {
        int i;
        int i2;
        uhy.e((Object) buffer, "");
        uhy.e((Object) byteString, "");
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException(("fromIndex < 0: " + j).toString());
        }
        Segment segment = buffer.head;
        if (segment == null) {
            return -1L;
        }
        if (buffer.size() - j < j) {
            j2 = buffer.size();
            while (j2 > j) {
                segment = segment.prev;
                uhy.d(segment);
                j2 -= segment.limit - segment.pos;
            }
            if (segment == null) {
                return -1L;
            }
            if (byteString.size() == 2) {
                byte b = byteString.getByte(0);
                byte b2 = byteString.getByte(1);
                while (j2 < buffer.size()) {
                    byte[] bArr = segment.data;
                    i = (int) ((segment.pos + j) - j2);
                    int i3 = segment.limit;
                    while (i < i3) {
                        byte b3 = bArr[i];
                        if (b3 != b && b3 != b2) {
                            i++;
                        }
                        i2 = segment.pos;
                    }
                    j2 += segment.limit - segment.pos;
                    segment = segment.next;
                    uhy.d(segment);
                    j = j2;
                }
            } else {
                byte[] internalArray$okio = byteString.internalArray$okio();
                while (j2 < buffer.size()) {
                    byte[] bArr2 = segment.data;
                    i = (int) ((segment.pos + j) - j2);
                    int i4 = segment.limit;
                    while (i < i4) {
                        byte b4 = bArr2[i];
                        for (byte b5 : internalArray$okio) {
                            if (b4 == b5) {
                                i2 = segment.pos;
                            }
                        }
                        i++;
                    }
                    j2 += segment.limit - segment.pos;
                    segment = segment.next;
                    uhy.d(segment);
                    j = j2;
                }
            }
            return -1L;
        }
        while (true) {
            long j3 = (segment.limit - segment.pos) + j2;
            if (j3 > j) {
                break;
            }
            segment = segment.next;
            uhy.d(segment);
            j2 = j3;
        }
        if (segment == null) {
            return -1L;
        }
        if (byteString.size() == 2) {
            byte b6 = byteString.getByte(0);
            byte b7 = byteString.getByte(1);
            while (j2 < buffer.size()) {
                byte[] bArr3 = segment.data;
                i = (int) ((segment.pos + j) - j2);
                int i5 = segment.limit;
                while (i < i5) {
                    byte b8 = bArr3[i];
                    if (b8 != b6 && b8 != b7) {
                        i++;
                    }
                    i2 = segment.pos;
                }
                j2 += segment.limit - segment.pos;
                segment = segment.next;
                uhy.d(segment);
                j = j2;
            }
        } else {
            byte[] internalArray$okio2 = byteString.internalArray$okio();
            while (j2 < buffer.size()) {
                byte[] bArr4 = segment.data;
                i = (int) ((segment.pos + j) - j2);
                int i6 = segment.limit;
                while (i < i6) {
                    byte b9 = bArr4[i];
                    for (byte b10 : internalArray$okio2) {
                        if (b9 == b10) {
                            i2 = segment.pos;
                        }
                    }
                    i++;
                }
                j2 += segment.limit - segment.pos;
                segment = segment.next;
                uhy.d(segment);
                j = j2;
            }
        }
        return -1L;
        return (i - i2) + j2;
    }
}
