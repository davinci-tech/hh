package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableCollection;
import kotlin.jvm.internal.markers.KMutableCollection;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010)\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0019\b\u0000\u0012\u0010\u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096\u0002J\u0015\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010J\u0016\u0010\u001b\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016J\u0016\u0010\u001c\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016R\u001b\u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Lkotlin/collections/builders/MapBuilderValues;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "Lkotlin/collections/AbstractMutableCollection;", "backing", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "getBacking", "()Lkotlin/collections/builders/MapBuilder;", "size", "", "getSize", "()I", "add", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)Z", "addAll", "elements", "", "clear", "", "contains", "isEmpty", "iterator", "", "remove", "removeAll", "retainAll", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uge<V> extends AbstractMutableCollection<V> implements Collection<V>, KMutableCollection {
    private final ugd<?, V> b;

    public uge(ugd<?, V> ugdVar) {
        uhy.e((Object) ugdVar, "");
        this.b = ugdVar;
    }

    @Override // kotlin.collections.AbstractMutableCollection
    public int getSize() {
        return this.b.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object element) {
        return this.b.containsValue(element);
    }

    @Override // kotlin.collections.AbstractMutableCollection, java.util.AbstractCollection, java.util.Collection
    public boolean add(V element) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends V> elements) {
        uhy.e((Object) elements, "");
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<V> iterator() {
        return this.b.o();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object element) {
        return this.b.b((ugd<?, V>) element);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        uhy.e((Object) elements, "");
        this.b.b();
        return super.removeAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        uhy.e((Object) elements, "");
        this.b.b();
        return super.retainAll(elements);
    }
}
