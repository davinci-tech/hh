package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.DropTakeSequence;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0096\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u0014\u0010\t\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlin/sequences/SubSequence;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", VastAttribute.SEQUENCE, "startIndex", "", "endIndex", "(Lkotlin/sequences/Sequence;II)V", "count", "getCount", "()I", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ujo<T> implements Sequence<T>, DropTakeSequence<T> {

    /* renamed from: a, reason: collision with root package name */
    private final int f17441a;
    private final Sequence<T> c;
    private final int e;

    /* JADX WARN: Multi-variable type inference failed */
    public ujo(Sequence<? extends T> sequence, int i, int i2) {
        uhy.e((Object) sequence, "");
        this.c = sequence;
        this.e = i;
        this.f17441a = i2;
        if (i < 0) {
            throw new IllegalArgumentException(("startIndex should be non-negative, but is " + i).toString());
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(("endIndex should be non-negative, but is " + i2).toString());
        }
        if (i2 >= i) {
            return;
        }
        throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was " + i2 + " < " + i).toString());
    }

    private final int d() {
        return this.f17441a - this.e;
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> drop(int n) {
        return n >= d() ? ujh.c() : new ujo(this.c, this.e + n, this.f17441a);
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> take(int n) {
        if (n >= d()) {
            return this;
        }
        Sequence<T> sequence = this.c;
        int i = this.e;
        return new ujo(sequence, i, n + i);
    }

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\u000e\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"kotlin/sequences/SubSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", "position", "", "getPosition", "()I", "setPosition", "(I)V", "drop", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class e implements Iterator<T>, KMappedMarker {

        /* renamed from: a, reason: collision with root package name */
        private final Iterator<T> f17442a;
        final /* synthetic */ ujo<T> b;
        private int d;

        e(ujo<T> ujoVar) {
            this.b = ujoVar;
            this.f17442a = ((ujo) ujoVar).c.iterator();
        }

        private final void c() {
            while (this.d < ((ujo) this.b).e && this.f17442a.hasNext()) {
                this.f17442a.next();
                this.d++;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            c();
            return this.d < ((ujo) this.b).f17441a && this.f17442a.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            c();
            if (this.d >= ((ujo) this.b).f17441a) {
                throw new NoSuchElementException();
            }
            this.d++;
            return this.f17442a.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new e(this);
    }
}
