package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.watchface.videoedit.gles.transition.SquareChangeAnimation;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.DropTakeSequence;
import kotlin.sequences.Sequence;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/sequences/DropSequence;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", VastAttribute.SEQUENCE, "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ujf<T> implements Sequence<T>, DropTakeSequence<T> {
    private final Sequence<T> b;
    private final int c;

    /* JADX WARN: Multi-variable type inference failed */
    public ujf(Sequence<? extends T> sequence, int i) {
        uhy.e((Object) sequence, "");
        this.b = sequence;
        this.c = i;
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i + FilenameUtils.EXTENSION_SEPARATOR).toString());
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> drop(int n) {
        int i = this.c + n;
        return i < 0 ? new ujf(this, n) : new ujf(this.b, i);
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> take(int n) {
        int i = this.c + n;
        return i < 0 ? new ujl(this, n) : new ujo(this.b, this.c, i);
    }

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\u000e\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"kotlin/sequences/DropSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", SquareChangeAnimation.LEFT, "", "getLeft", "()I", "setLeft", "(I)V", "drop", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class e implements Iterator<T>, KMappedMarker {
        private int c;
        private final Iterator<T> e;

        e(ujf<T> ujfVar) {
            this.e = ((ujf) ujfVar).b.iterator();
            this.c = ((ujf) ujfVar).c;
        }

        private final void a() {
            while (this.c > 0 && this.e.hasNext()) {
                this.e.next();
                this.c--;
            }
        }

        @Override // java.util.Iterator
        public T next() {
            a();
            return this.e.next();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            a();
            return this.e.hasNext();
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
