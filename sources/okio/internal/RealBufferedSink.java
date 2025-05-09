package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.network.embedded.g4;
import com.huawei.hms.scankit.b;
import defpackage.uhy;
import java.io.EOFException;
import kotlin.Metadata;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0005\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\t\u001a\u00020\n*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u0016\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0015H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010!\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010#\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\nH\u0080\b\u001a%\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010(\u001a\u00020\u0004*\u00020\u00022\u0006\u0010)\u001a\u00020\u000fH\u0080\b¨\u0006*"}, d2 = {"commonClose", "", "Lokio/RealBufferedSink;", "commonEmit", "Lokio/BufferedSink;", "commonEmitCompleteSegments", "commonFlush", "commonTimeout", "Lokio/Timeout;", "commonToString", "", "commonWrite", "source", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "byteCount", "Lokio/Buffer;", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "commonWriteAll", "commonWriteByte", b.H, "commonWriteDecimalLong", FitRunPlayAudio.PLAY_TYPE_V, "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteIntLe", "commonWriteLong", "commonWriteLongLe", "commonWriteShort", "s", "commonWriteShortLe", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.internal.-RealBufferedSink, reason: invalid class name */
/* loaded from: classes10.dex */
public final class RealBufferedSink {
    public static final void commonWrite(okio.RealBufferedSink realBufferedSink, Buffer buffer, long j) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) buffer, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.write(buffer, j);
        realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink realBufferedSink, ByteString byteString) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) byteString, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.write(byteString);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink realBufferedSink, ByteString byteString, int i, int i2) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) byteString, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.write(byteString, i, i2);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(okio.RealBufferedSink realBufferedSink, String str) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) str, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeUtf8(str);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(okio.RealBufferedSink realBufferedSink, String str, int i, int i2) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) str, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeUtf8(str, i, i2);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8CodePoint(okio.RealBufferedSink realBufferedSink, int i) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeUtf8CodePoint(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink realBufferedSink, byte[] bArr) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) bArr, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.write(bArr);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink realBufferedSink, byte[] bArr, int i, int i2) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) bArr, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.write(bArr, i, i2);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteByte(okio.RealBufferedSink realBufferedSink, int i) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeByte(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShort(okio.RealBufferedSink realBufferedSink, int i) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeShort(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShortLe(okio.RealBufferedSink realBufferedSink, int i) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeShortLe(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteInt(okio.RealBufferedSink realBufferedSink, int i) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeInt(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteIntLe(okio.RealBufferedSink realBufferedSink, int i) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeIntLe(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLong(okio.RealBufferedSink realBufferedSink, long j) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeLong(j);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLongLe(okio.RealBufferedSink realBufferedSink, long j) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeLongLe(j);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteDecimalLong(okio.RealBufferedSink realBufferedSink, long j) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeDecimalLong(j);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteHexadecimalUnsignedLong(okio.RealBufferedSink realBufferedSink, long j) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        realBufferedSink.bufferField.writeHexadecimalUnsignedLong(j);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonEmitCompleteSegments(okio.RealBufferedSink realBufferedSink) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        long completeSegmentByteCount = realBufferedSink.bufferField.completeSegmentByteCount();
        if (completeSegmentByteCount > 0) {
            realBufferedSink.sink.write(realBufferedSink.bufferField, completeSegmentByteCount);
        }
        return realBufferedSink;
    }

    public static final BufferedSink commonEmit(okio.RealBufferedSink realBufferedSink) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        long size = realBufferedSink.bufferField.size();
        if (size > 0) {
            realBufferedSink.sink.write(realBufferedSink.bufferField, size);
        }
        return realBufferedSink;
    }

    public static final void commonFlush(okio.RealBufferedSink realBufferedSink) {
        uhy.e((Object) realBufferedSink, "");
        if (!(!realBufferedSink.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (realBufferedSink.bufferField.size() > 0) {
            realBufferedSink.sink.write(realBufferedSink.bufferField, realBufferedSink.bufferField.size());
        }
        realBufferedSink.sink.flush();
    }

    public static final void commonClose(okio.RealBufferedSink realBufferedSink) {
        uhy.e((Object) realBufferedSink, "");
        if (realBufferedSink.closed) {
            return;
        }
        try {
            if (realBufferedSink.bufferField.size() > 0) {
                realBufferedSink.sink.write(realBufferedSink.bufferField, realBufferedSink.bufferField.size());
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            realBufferedSink.sink.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        realBufferedSink.closed = true;
        if (th != null) {
            throw th;
        }
    }

    public static final Timeout commonTimeout(okio.RealBufferedSink realBufferedSink) {
        uhy.e((Object) realBufferedSink, "");
        return realBufferedSink.sink.getTimeout();
    }

    public static final String commonToString(okio.RealBufferedSink realBufferedSink) {
        uhy.e((Object) realBufferedSink, "");
        return "buffer(" + realBufferedSink.sink + g4.l;
    }

    public static final long commonWriteAll(okio.RealBufferedSink realBufferedSink, Source source) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) source, "");
        long j = 0;
        while (true) {
            long read = source.read(realBufferedSink.bufferField, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
            realBufferedSink.emitCompleteSegments();
        }
    }

    public static final BufferedSink commonWrite(okio.RealBufferedSink realBufferedSink, Source source, long j) {
        uhy.e((Object) realBufferedSink, "");
        uhy.e((Object) source, "");
        while (j > 0) {
            long read = source.read(realBufferedSink.bufferField, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
            realBufferedSink.emitCompleteSegments();
        }
        return realBufferedSink;
    }
}
