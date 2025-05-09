package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.watchface.videoedit.gles.transition.SquareChangeAnimation;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.DropTakeSequence;
import kotlin.sequences.Sequence;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/sequences/TakeSequence;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", VastAttribute.SEQUENCE, "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ujl<T> implements Sequence<T>, DropTakeSequence<T> {
    private final Sequence<T> c;
    private final int d;

    /* JADX WARN: Multi-variable type inference failed */
    public ujl(Sequence<? extends T> sequence, int i) {
        uhy.e((Object) sequence, "");
        this.c = sequence;
        this.d = i;
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i + FilenameUtils.EXTENSION_SEPARATOR).toString());
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> drop(int n) {
        int i = this.d;
        return n >= i ? ujh.c() : new ujo(this.c, n, i);
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> take(int n) {
        return n >= this.d ? this : new ujl(this.c, n);
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\u000e\u0010\r\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"kotlin/sequences/TakeSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", SquareChangeAnimation.LEFT, "", "getLeft", "()I", "setLeft", "(I)V", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class c implements Iterator<T>, KMappedMarker {

        /* renamed from: a, reason: collision with root package name */
        private int f17439a;
        private final Iterator<T> b;

        c(ujl<T> ujlVar) {
            this.f17439a = ((ujl) ujlVar).d;
            this.b = ((ujl) ujlVar).c.iterator();
        }

        @Override // java.util.Iterator
        public T next() {
            int i = this.f17439a;
            if (i == 0) {
                throw new NoSuchElementException();
            }
            this.f17439a = i - 1;
            return this.b.next();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f17439a > 0 && this.b.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new c(this);
    }
}
