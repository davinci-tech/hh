package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.EMPTY;
import defpackage.ufe;
import defpackage.uhy;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007\b\u0004¢\u0006\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016J\b\u0010\u000e\u001a\u00020\tH\u0016J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H¦\u0002J\u0015\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012H\u0015¢\u0006\u0002\u0010\u0014J'\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0012\"\u0004\b\u0001\u0010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0012H\u0014¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001a"}, d2 = {"Lkotlin/collections/AbstractCollection;", ExifInterface.LONGITUDE_EAST, "", "()V", "size", "", "getSize", "()I", "contains", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)Z", "containsAll", "elements", "isEmpty", "iterator", "", "toArray", "", "", "()[Ljava/lang/Object;", ExifInterface.GPS_DIRECTION_TRUE, "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes7.dex */
public abstract class AbstractCollection<E> implements Collection<E>, KMappedMarker {
    public abstract int getSize();

    @Override // java.util.Collection, java.lang.Iterable
    public abstract Iterator<E> iterator();

    protected AbstractCollection() {
    }

    @Override // java.util.Collection
    public final int size() {
        return getSize();
    }

    @Override // java.util.Collection
    public boolean contains(E element) {
        AbstractCollection<E> abstractCollection = this;
        if (!(abstractCollection instanceof Collection) || !abstractCollection.isEmpty()) {
            Iterator<E> it = abstractCollection.iterator();
            while (it.hasNext()) {
                if (uhy.e(it.next(), element)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        uhy.e((Object) elements, "");
        Collection<? extends Object> collection = elements;
        if (!collection.isEmpty()) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002 \u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "it", TrackConstants$Opers.INVOKE, "(Ljava/lang/Object;)Ljava/lang/CharSequence;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class d extends Lambda implements Function1<E, CharSequence> {
        final /* synthetic */ AbstractCollection<E> c;

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final CharSequence invoke(E e) {
            return e == this.c ? "(this Collection)" : String.valueOf(e);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        d(AbstractCollection<? extends E> abstractCollection) {
            super(1);
            this.c = abstractCollection;
        }
    }

    public String toString() {
        return ufe.b(this, ", ", "[", "]", 0, null, new d(this), 24, null);
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return EMPTY.e(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] array) {
        uhy.e((Object) array, "");
        return (T[]) EMPTY.b(this, array);
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean add(E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
