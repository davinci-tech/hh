package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hms.scankit.b;
import com.huawei.operation.ble.BleConstants;
import defpackage.uhy;
import java.io.EOFException;
import kotlin.Metadata;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Timeout;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a%\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0080\b\u001a\u001d\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0006H\u0080\b\u001a\u001d\u0010\r\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0080\b\u001a-\u0010\u0011\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u0016\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u0010\u001a\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u001bH\u0080\b\u001a\r\u0010\u001c\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\u001d\u001a\u00020\u0018*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u001d\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u001e\u001a\u00020\f*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010\u001f\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0080\b\u001a\u001d\u0010 \u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010!\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010\"\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\r\u0010#\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\r\u0010$\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010%\u001a\u00020\u0006*\u00020\u0002H\u0080\b\u001a\r\u0010&\u001a\u00020'*\u00020\u0002H\u0080\b\u001a\r\u0010(\u001a\u00020'*\u00020\u0002H\u0080\b\u001a\r\u0010)\u001a\u00020**\u00020\u0002H\u0080\b\u001a\u0015\u0010)\u001a\u00020**\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u0010+\u001a\u00020\u0014*\u00020\u0002H\u0080\b\u001a\u000f\u0010,\u001a\u0004\u0018\u00010**\u00020\u0002H\u0080\b\u001a\u0015\u0010-\u001a\u00020**\u00020\u00022\u0006\u0010.\u001a\u00020\u0006H\u0080\b\u001a\u0015\u0010/\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u00100\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\u0015\u00101\u001a\u00020\u0014*\u00020\u00022\u0006\u00102\u001a\u000203H\u0080\b\u001a\u0015\u00104\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0080\b\u001a\r\u00105\u001a\u000206*\u00020\u0002H\u0080\b\u001a\r\u00107\u001a\u00020**\u00020\u0002H\u0080\b¨\u00068"}, d2 = {"commonClose", "", "Lokio/RealBufferedSource;", "commonExhausted", "", "commonIndexOf", "", b.H, "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonPeek", "Lokio/BufferedSource;", "commonRangeEquals", TypedValues.CycleType.S_WAVE_OFFSET, "bytesOffset", "", "byteCount", "commonRead", "sink", "", "Lokio/Buffer;", "commonReadAll", "Lokio/Sink;", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadIntLe", "commonReadLong", "commonReadLongLe", "commonReadShort", "", "commonReadShortLe", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", BleConstants.LIMIT, "commonRequest", "commonRequire", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonTimeout", "Lokio/Timeout;", "commonToString", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.internal.-RealBufferedSource, reason: invalid class name */
/* loaded from: classes10.dex */
public final class RealBufferedSource {
    public static final long commonRead(okio.RealBufferedSource realBufferedSource, Buffer buffer, long j) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) buffer, "");
        if (j < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        }
        if (!(!realBufferedSource.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (realBufferedSource.bufferField.size() == 0 && realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return realBufferedSource.bufferField.read(buffer, Math.min(j, realBufferedSource.bufferField.size()));
    }

    public static final boolean commonExhausted(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        if (!realBufferedSource.closed) {
            return realBufferedSource.bufferField.exhausted() && realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    public static final void commonRequire(okio.RealBufferedSource realBufferedSource, long j) {
        uhy.e((Object) realBufferedSource, "");
        if (!realBufferedSource.request(j)) {
            throw new EOFException();
        }
    }

    public static final boolean commonRequest(okio.RealBufferedSource realBufferedSource, long j) {
        uhy.e((Object) realBufferedSource, "");
        if (j < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        }
        if (!(!realBufferedSource.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (realBufferedSource.bufferField.size() < j) {
            if (realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return false;
            }
        }
        return true;
    }

    public static final byte commonReadByte(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(1L);
        return realBufferedSource.bufferField.readByte();
    }

    public static final ByteString commonReadByteString(okio.RealBufferedSource realBufferedSource, long j) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(j);
        return realBufferedSource.bufferField.readByteString(j);
    }

    public static final int commonSelect(okio.RealBufferedSource realBufferedSource, Options options) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) options, "");
        if (!(!realBufferedSource.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        do {
            int selectPrefix = Buffer.selectPrefix(realBufferedSource.bufferField, options, true);
            if (selectPrefix != -2) {
                if (selectPrefix == -1) {
                    return -1;
                }
                realBufferedSource.bufferField.skip(options.getByteStrings()[selectPrefix].size());
                return selectPrefix;
            }
        } while (realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return -1;
    }

    public static final byte[] commonReadByteArray(okio.RealBufferedSource realBufferedSource, long j) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(j);
        return realBufferedSource.bufferField.readByteArray(j);
    }

    public static final void commonReadFully(okio.RealBufferedSource realBufferedSource, byte[] bArr) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) bArr, "");
        try {
            realBufferedSource.require(bArr.length);
            realBufferedSource.bufferField.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (realBufferedSource.bufferField.size() > 0) {
                int read = realBufferedSource.bufferField.read(bArr, i, (int) realBufferedSource.bufferField.size());
                if (read == -1) {
                    throw new AssertionError();
                }
                i += read;
            }
            throw e;
        }
    }

    public static final int commonRead(okio.RealBufferedSource realBufferedSource, byte[] bArr, int i, int i2) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) bArr, "");
        long j = i2;
        SegmentedByteString.checkOffsetAndCount(bArr.length, i, j);
        if (realBufferedSource.bufferField.size() == 0 && realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return realBufferedSource.bufferField.read(bArr, i, (int) Math.min(j, realBufferedSource.bufferField.size()));
    }

    public static final void commonReadFully(okio.RealBufferedSource realBufferedSource, Buffer buffer, long j) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) buffer, "");
        try {
            realBufferedSource.require(j);
            realBufferedSource.bufferField.readFully(buffer, j);
        } catch (EOFException e) {
            buffer.writeAll(realBufferedSource.bufferField);
            throw e;
        }
    }

    public static final long commonReadAll(okio.RealBufferedSource realBufferedSource, Sink sink) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) sink, "");
        long j = 0;
        while (realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
            long completeSegmentByteCount = realBufferedSource.bufferField.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                j += completeSegmentByteCount;
                sink.write(realBufferedSource.bufferField, completeSegmentByteCount);
            }
        }
        if (realBufferedSource.bufferField.size() <= 0) {
            return j;
        }
        long size = j + realBufferedSource.bufferField.size();
        sink.write(realBufferedSource.bufferField, realBufferedSource.bufferField.size());
        return size;
    }

    public static final String commonReadUtf8(okio.RealBufferedSource realBufferedSource, long j) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(j);
        return realBufferedSource.bufferField.readUtf8(j);
    }

    public static final String commonReadUtf8Line(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        long indexOf = realBufferedSource.indexOf((byte) 10);
        if (indexOf != -1) {
            return Buffer.readUtf8Line(realBufferedSource.bufferField, indexOf);
        }
        if (realBufferedSource.bufferField.size() != 0) {
            return realBufferedSource.readUtf8(realBufferedSource.bufferField.size());
        }
        return null;
    }

    public static final String commonReadUtf8LineStrict(okio.RealBufferedSource realBufferedSource, long j) {
        uhy.e((Object) realBufferedSource, "");
        if (j < 0) {
            throw new IllegalArgumentException(("limit < 0: " + j).toString());
        }
        long j2 = j == LocationRequestCompat.PASSIVE_INTERVAL ? Long.MAX_VALUE : j + 1;
        long indexOf = realBufferedSource.indexOf((byte) 10, 0L, j2);
        if (indexOf != -1) {
            return Buffer.readUtf8Line(realBufferedSource.bufferField, indexOf);
        }
        if (j2 < LocationRequestCompat.PASSIVE_INTERVAL && realBufferedSource.request(j2) && realBufferedSource.bufferField.getByte(j2 - 1) == 13 && realBufferedSource.request(1 + j2) && realBufferedSource.bufferField.getByte(j2) == 10) {
            return Buffer.readUtf8Line(realBufferedSource.bufferField, j2);
        }
        Buffer buffer = new Buffer();
        realBufferedSource.bufferField.copyTo(buffer, 0L, Math.min(32, realBufferedSource.bufferField.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(realBufferedSource.bufferField.size(), j) + " content=" + buffer.readByteString().hex() + (char) 8230);
    }

    public static final int commonReadUtf8CodePoint(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(1L);
        byte b = realBufferedSource.bufferField.getByte(0L);
        if ((b & 224) == 192) {
            realBufferedSource.require(2L);
        } else if ((b & 240) == 224) {
            realBufferedSource.require(3L);
        } else if ((b & 248) == 240) {
            realBufferedSource.require(4L);
        }
        return realBufferedSource.bufferField.readUtf8CodePoint();
    }

    public static final short commonReadShort(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(2L);
        return realBufferedSource.bufferField.readShort();
    }

    public static final short commonReadShortLe(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(2L);
        return realBufferedSource.bufferField.readShortLe();
    }

    public static final int commonReadInt(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(4L);
        return realBufferedSource.bufferField.readInt();
    }

    public static final int commonReadIntLe(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(4L);
        return realBufferedSource.bufferField.readIntLe();
    }

    public static final long commonReadLong(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(8L);
        return realBufferedSource.bufferField.readLong();
    }

    public static final long commonReadLongLe(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.require(8L);
        return realBufferedSource.bufferField.readLongLe();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
    
        if (r5 == 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
    
        r11 = new java.lang.StringBuilder("Expected a digit or '-' but was 0x");
        r1 = java.lang.Integer.toString(r9, defpackage.ujm.e(defpackage.ujm.e(16)));
        defpackage.uhy.a(r1, "");
        r11.append(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
    
        throw new java.lang.NumberFormatException(r11.toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long commonReadDecimalLong(okio.RealBufferedSource r11) {
        /*
            java.lang.String r0 = ""
            defpackage.uhy.e(r11, r0)
            r1 = 1
            r11.require(r1)
            r3 = 0
            r5 = r3
        Ld:
            long r7 = r5 + r1
            boolean r9 = r11.request(r7)
            if (r9 == 0) goto L56
            okio.Buffer r9 = r11.bufferField
            byte r9 = r9.getByte(r5)
            r10 = 48
            if (r9 < r10) goto L23
            r10 = 57
            if (r9 <= r10) goto L2c
        L23:
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 != 0) goto L2e
            r6 = 45
            if (r9 == r6) goto L2c
            goto L2e
        L2c:
            r5 = r7
            goto Ld
        L2e:
            if (r5 == 0) goto L31
            goto L56
        L31:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r1 = "Expected a digit or '-' but was 0x"
            r11.<init>(r1)
            r1 = 16
            int r1 = defpackage.ujm.e(r1)
            int r1 = defpackage.ujm.e(r1)
            java.lang.String r1 = java.lang.Integer.toString(r9, r1)
            defpackage.uhy.a(r1, r0)
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            r11.append(r1)
            java.lang.String r11 = r11.toString()
            r0.<init>(r11)
            throw r0
        L56:
            okio.Buffer r11 = r11.bufferField
            long r0 = r11.readDecimalLong()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.RealBufferedSource.commonReadDecimalLong(okio.RealBufferedSource):long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0036, code lost:
    
        if (r1 == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0039, code lost:
    
        r6 = new java.lang.StringBuilder("Expected leading [0-9a-fA-F] character but was 0x");
        r1 = java.lang.Integer.toString(r3, defpackage.ujm.e(defpackage.ujm.e(16)));
        defpackage.uhy.a(r1, "");
        r6.append(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005d, code lost:
    
        throw new java.lang.NumberFormatException(r6.toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long commonReadHexadecimalUnsignedLong(okio.RealBufferedSource r6) {
        /*
            java.lang.String r0 = ""
            defpackage.uhy.e(r6, r0)
            r1 = 1
            r6.require(r1)
            r1 = 0
        Lb:
            int r2 = r1 + 1
            long r3 = (long) r2
            boolean r3 = r6.request(r3)
            if (r3 == 0) goto L5e
            okio.Buffer r3 = r6.bufferField
            long r4 = (long) r1
            byte r3 = r3.getByte(r4)
            r4 = 48
            if (r3 < r4) goto L23
            r4 = 57
            if (r3 <= r4) goto L34
        L23:
            r4 = 97
            if (r3 < r4) goto L2b
            r4 = 102(0x66, float:1.43E-43)
            if (r3 <= r4) goto L34
        L2b:
            r4 = 65
            if (r3 < r4) goto L36
            r4 = 70
            if (r3 <= r4) goto L34
            goto L36
        L34:
            r1 = r2
            goto Lb
        L36:
            if (r1 == 0) goto L39
            goto L5e
        L39:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r1 = "Expected leading [0-9a-fA-F] character but was 0x"
            r6.<init>(r1)
            r1 = 16
            int r1 = defpackage.ujm.e(r1)
            int r1 = defpackage.ujm.e(r1)
            java.lang.String r1 = java.lang.Integer.toString(r3, r1)
            defpackage.uhy.a(r1, r0)
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            r0.<init>(r6)
            throw r0
        L5e:
            okio.Buffer r6 = r6.bufferField
            long r0 = r6.readHexadecimalUnsignedLong()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.RealBufferedSource.commonReadHexadecimalUnsignedLong(okio.RealBufferedSource):long");
    }

    public static final void commonSkip(okio.RealBufferedSource realBufferedSource, long j) {
        uhy.e((Object) realBufferedSource, "");
        if (!(!realBufferedSource.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (j > 0) {
            if (realBufferedSource.bufferField.size() == 0 && realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, realBufferedSource.bufferField.size());
            realBufferedSource.bufferField.skip(min);
            j -= min;
        }
    }

    public static final long commonIndexOf(okio.RealBufferedSource realBufferedSource, byte b, long j, long j2) {
        uhy.e((Object) realBufferedSource, "");
        if (!(!realBufferedSource.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (0 > j || j > j2) {
            throw new IllegalArgumentException(("fromIndex=" + j + " toIndex=" + j2).toString());
        }
        while (j < j2) {
            long indexOf = realBufferedSource.bufferField.indexOf(b, j, j2);
            if (indexOf == -1) {
                long size = realBufferedSource.bufferField.size();
                if (size >= j2 || realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    break;
                }
                j = Math.max(j, size);
            } else {
                return indexOf;
            }
        }
        return -1L;
    }

    public static final long commonIndexOf(okio.RealBufferedSource realBufferedSource, ByteString byteString, long j) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) byteString, "");
        if (!(!realBufferedSource.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            long indexOf = realBufferedSource.bufferField.indexOf(byteString, j);
            if (indexOf != -1) {
                return indexOf;
            }
            long size = realBufferedSource.bufferField.size();
            if (realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, (size - byteString.size()) + 1);
        }
    }

    public static final long commonIndexOfElement(okio.RealBufferedSource realBufferedSource, ByteString byteString, long j) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) byteString, "");
        if (!(!realBufferedSource.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            long indexOfElement = realBufferedSource.bufferField.indexOfElement(byteString, j);
            if (indexOfElement != -1) {
                return indexOfElement;
            }
            long size = realBufferedSource.bufferField.size();
            if (realBufferedSource.source.read(realBufferedSource.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, size);
        }
    }

    public static final boolean commonRangeEquals(okio.RealBufferedSource realBufferedSource, long j, ByteString byteString, int i, int i2) {
        uhy.e((Object) realBufferedSource, "");
        uhy.e((Object) byteString, "");
        if (!(!realBufferedSource.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (j < 0 || i < 0 || i2 < 0 || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = i3 + j;
            if (!realBufferedSource.request(1 + j2) || realBufferedSource.bufferField.getByte(j2) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    public static final BufferedSource commonPeek(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        return Okio.buffer(new PeekSource(realBufferedSource));
    }

    public static final void commonClose(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        if (realBufferedSource.closed) {
            return;
        }
        realBufferedSource.closed = true;
        realBufferedSource.source.close();
        realBufferedSource.bufferField.clear();
    }

    public static final Timeout commonTimeout(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        return realBufferedSource.source.getTimeout();
    }

    public static final String commonToString(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        return "buffer(" + realBufferedSource.source + g4.l;
    }

    public static final ByteString commonReadByteString(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.bufferField.writeAll(realBufferedSource.source);
        return realBufferedSource.bufferField.readByteString();
    }

    public static final byte[] commonReadByteArray(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.bufferField.writeAll(realBufferedSource.source);
        return realBufferedSource.bufferField.readByteArray();
    }

    public static final String commonReadUtf8(okio.RealBufferedSource realBufferedSource) {
        uhy.e((Object) realBufferedSource, "");
        realBufferedSource.bufferField.writeAll(realBufferedSource.source);
        return realBufferedSource.bufferField.readUtf8();
    }
}
