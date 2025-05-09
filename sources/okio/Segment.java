package okio;

import com.huawei.operation.ble.BleConstants;
import defpackage.uez;
import defpackage.uhy;
import java.util.Arrays;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B/\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000J\u0006\u0010\u0013\u001a\u00020\u0000J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006J\u0006\u0010\u0016\u001a\u00020\u0000J\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lokio/Segment;", "", "()V", "data", "", "pos", "", BleConstants.LIMIT, "shared", "", "owner", "([BIIZZ)V", "next", "prev", "compact", "", "pop", "push", "segment", "sharedCopy", "split", "byteCount", "unsharedCopy", "writeTo", "sink", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Segment {
    public static final int SHARE_MINIMUM = 1024;
    public static final int SIZE = 8192;
    public final byte[] data;
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public Segment(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        uhy.e((Object) bArr, "");
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.shared = z;
        this.owner = z2;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    public final Segment unsharedCopy() {
        byte[] bArr = this.data;
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        uhy.a(copyOf, "");
        return new Segment(copyOf, this.pos, this.limit, false, true);
    }

    public final Segment pop() {
        Segment segment = this.next;
        if (segment == this) {
            segment = null;
        }
        Segment segment2 = this.prev;
        uhy.d(segment2);
        segment2.next = this.next;
        Segment segment3 = this.next;
        uhy.d(segment3);
        segment3.prev = this.prev;
        this.next = null;
        this.prev = null;
        return segment;
    }

    public final Segment push(Segment segment) {
        uhy.e((Object) segment, "");
        segment.prev = this;
        segment.next = this.next;
        Segment segment2 = this.next;
        uhy.d(segment2);
        segment2.prev = segment;
        this.next = segment;
        return segment;
    }

    public final Segment split(int byteCount) {
        Segment take;
        if (byteCount <= 0 || byteCount > this.limit - this.pos) {
            throw new IllegalArgumentException("byteCount out of range".toString());
        }
        if (byteCount >= 1024) {
            take = sharedCopy();
        } else {
            take = SegmentPool.take();
            byte[] bArr = this.data;
            byte[] bArr2 = take.data;
            int i = this.pos;
            uez.b(bArr, bArr2, 0, i, i + byteCount, 2, null);
        }
        take.limit = take.pos + byteCount;
        this.pos += byteCount;
        Segment segment = this.prev;
        uhy.d(segment);
        segment.push(take);
        return take;
    }

    public final void compact() {
        int i;
        Segment segment = this.prev;
        if (segment == this) {
            throw new IllegalStateException("cannot compact".toString());
        }
        uhy.d(segment);
        if (segment.owner) {
            int i2 = this.limit - this.pos;
            Segment segment2 = this.prev;
            uhy.d(segment2);
            int i3 = segment2.limit;
            Segment segment3 = this.prev;
            uhy.d(segment3);
            if (segment3.shared) {
                i = 0;
            } else {
                Segment segment4 = this.prev;
                uhy.d(segment4);
                i = segment4.pos;
            }
            if (i2 > (8192 - i3) + i) {
                return;
            }
            Segment segment5 = this.prev;
            uhy.d(segment5);
            writeTo(segment5, i2);
            pop();
            SegmentPool.recycle(this);
        }
    }

    public final void writeTo(Segment sink, int byteCount) {
        uhy.e((Object) sink, "");
        if (!sink.owner) {
            throw new IllegalStateException("only owner can write".toString());
        }
        int i = sink.limit;
        int i2 = i + byteCount;
        if (i2 > 8192) {
            if (sink.shared) {
                throw new IllegalArgumentException();
            }
            int i3 = sink.pos;
            if (i2 - i3 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = sink.data;
            uez.b(bArr, bArr, 0, i3, i, 2, null);
            sink.limit -= sink.pos;
            sink.pos = 0;
        }
        byte[] bArr2 = this.data;
        byte[] bArr3 = sink.data;
        int i4 = sink.limit;
        int i5 = this.pos;
        uez.d(bArr2, bArr3, i4, i5, i5 + byteCount);
        sink.limit += byteCount;
        this.pos += byteCount;
    }
}
