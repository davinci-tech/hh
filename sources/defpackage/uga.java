package defpackage;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 V*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00060\u0006j\u0002`\u0007:\u0002VWB\u0007\b\u0016¢\u0006\u0002\u0010\bB\u000f\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bBM\b\u0002\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000¢\u0006\u0002\u0010\u0014J\u0015\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u001d\u0010\u001b\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010 J\u001e\u0010!\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J\u0016\u0010!\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J&\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010&\u001a\u00020\nH\u0002J\u001d\u0010'\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010 J\f\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)J\b\u0010*\u001a\u00020\u001eH\u0002J\b\u0010+\u001a\u00020\u001eH\u0016J\u0014\u0010,\u001a\u00020\u00112\n\u0010-\u001a\u0006\u0012\u0002\b\u00030)H\u0002J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\nH\u0002J\u0010\u00100\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\nH\u0002J\u0013\u00101\u001a\u00020\u00112\b\u0010-\u001a\u0004\u0018\u000102H\u0096\u0002J\u0016\u00103\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\nH\u0096\u0002¢\u0006\u0002\u00104J\b\u00105\u001a\u00020\nH\u0016J\u0015\u00106\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00107J\u0018\u00108\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nH\u0002J\b\u00109\u001a\u00020\u0011H\u0016J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;H\u0096\u0002J\u0015\u0010<\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00107J\u000e\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000>H\u0016J\u0016\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000>2\u0006\u0010\u001f\u001a\u00020\nH\u0016J\u0015\u0010?\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u0016\u0010@\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J\u0015\u0010A\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\nH\u0016¢\u0006\u0002\u00104J\u0015\u0010B\u001a\u00028\u00002\u0006\u0010%\u001a\u00020\nH\u0002¢\u0006\u0002\u00104J\u0018\u0010C\u001a\u00020\u001e2\u0006\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020\nH\u0002J\u0016\u0010F\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J.\u0010G\u001a\u00020\n2\u0006\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010H\u001a\u00020\u0011H\u0002J\u001e\u0010I\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010JJ\u001e\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u00020\nH\u0016J\u0015\u0010N\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\rH\u0016¢\u0006\u0002\u0010OJ'\u0010N\u001a\b\u0012\u0004\u0012\u0002HP0\r\"\u0004\b\u0001\u0010P2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002HP0\rH\u0016¢\u0006\u0002\u0010RJ\b\u0010S\u001a\u00020TH\u0016J\b\u0010U\u001a\u000202H\u0002R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006X"}, d2 = {"Lkotlin/collections/builders/ListBuilder;", ExifInterface.LONGITUDE_EAST, "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Lkotlin/collections/AbstractMutableList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "initialCapacity", "", "(I)V", "array", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "isReadOnly", "", "backing", "root", "([Ljava/lang/Object;IIZLkotlin/collections/builders/ListBuilder;Lkotlin/collections/builders/ListBuilder;)V", "[Ljava/lang/Object;", "isEffectivelyReadOnly", "()Z", "size", "getSize", "()I", "add", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "elements", "", "addAllInternal", "i", "n", "addAtInternal", "build", "", "checkIsMutable", "clear", "contentEquals", "other", "ensureCapacity", "minCapacity", "ensureExtraCapacity", "equals", "", "get", "(I)Ljava/lang/Object;", WatchFaceConstant.HASHCODE, "indexOf", "(Ljava/lang/Object;)I", "insertAtInternal", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "remove", "removeAll", "removeAt", "removeAtInternal", "removeRangeInternal", "rangeOffset", "rangeLength", "retainAll", "retainOrRemoveAllInternal", "retain", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "subList", "fromIndex", "toIndex", "toArray", "()[Ljava/lang/Object;", ExifInterface.GPS_DIRECTION_TRUE, "destination", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "writeReplace", "Companion", "Itr", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uga<E> extends AbstractMutableList<E> implements List<E>, RandomAccess, Serializable, KMutableList {

    /* renamed from: a, reason: collision with root package name */
    private static final c f17412a = new c(null);
    private static final uga e;
    private E[] b;
    private boolean c;
    private final uga<E> d;
    private int g;
    private int i;
    private final uga<E> j;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/collections/builders/ListBuilder$Companion;", "", "()V", "Empty", "Lkotlin/collections/builders/ListBuilder;", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class c {
        private c() {
        }

        public /* synthetic */ c(uib uibVar) {
            this();
        }
    }

    private uga(E[] eArr, int i, int i2, boolean z, uga<E> ugaVar, uga<E> ugaVar2) {
        this.b = eArr;
        this.i = i;
        this.g = i2;
        this.c = z;
        this.d = ugaVar;
        this.j = ugaVar2;
    }

    static {
        uga ugaVar = new uga(0);
        ugaVar.c = true;
        e = ugaVar;
    }

    public uga() {
        this(10);
    }

    public uga(int i) {
        this(arrayOfUninitializedElements.d(i), 0, 0, false, null, null);
    }

    public final List<E> a() {
        if (this.d != null) {
            throw new IllegalStateException();
        }
        b();
        this.c = true;
        return this.g > 0 ? this : e;
    }

    private final Object writeReplace() {
        if (d()) {
            return new ugh(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // kotlin.collections.AbstractMutableList
    /* renamed from: getSize, reason: from getter */
    public int getB() {
        return this.g;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.g == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int index) {
        AbstractList.INSTANCE.e(index, this.g);
        return this.b[this.i + index];
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int index, E element) {
        b();
        AbstractList.INSTANCE.e(index, this.g);
        E[] eArr = this.b;
        int i = this.i + index;
        E e2 = eArr[i];
        eArr[i] = element;
        return e2;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object element) {
        for (int i = 0; i < this.g; i++) {
            if (uhy.e(this.b[this.i + i], element)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object element) {
        for (int i = this.g - 1; i >= 0; i--) {
            if (uhy.e(this.b[this.i + i], element)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new d(this, 0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return new d(this, 0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int index) {
        AbstractList.INSTANCE.b(index, this.g);
        return new d(this, index);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E element) {
        b();
        e(this.i + this.g, element);
        return true;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int index, E element) {
        b();
        AbstractList.INSTANCE.b(index, this.g);
        e(this.i + index, element);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> elements) {
        uhy.e((Object) elements, "");
        b();
        int size = elements.size();
        a(this.i + this.g, elements, size);
        return size > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int index, Collection<? extends E> elements) {
        uhy.e((Object) elements, "");
        b();
        AbstractList.INSTANCE.b(index, this.g);
        int size = elements.size();
        a(this.i + index, elements, size);
        return size > 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        b();
        a(this.i, this.g);
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int index) {
        b();
        AbstractList.INSTANCE.e(index, this.g);
        return c(this.i + index);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object element) {
        b();
        int indexOf = indexOf(element);
        if (indexOf >= 0) {
            remove(indexOf);
        }
        return indexOf >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> elements) {
        uhy.e((Object) elements, "");
        b();
        return d(this.i, this.g, elements, false) > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<? extends Object> elements) {
        uhy.e((Object) elements, "");
        b();
        return d(this.i, this.g, elements, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public List<E> subList(int fromIndex, int toIndex) {
        AbstractList.INSTANCE.c(fromIndex, toIndex, this.g);
        E[] eArr = this.b;
        int i = this.i;
        boolean z = this.c;
        uga<E> ugaVar = this.j;
        return new uga(eArr, i + fromIndex, toIndex - fromIndex, z, this, ugaVar == null ? this : ugaVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] destination) {
        uhy.e((Object) destination, "");
        int length = destination.length;
        int i = this.g;
        if (length < i) {
            E[] eArr = this.b;
            int i2 = this.i;
            T[] tArr = (T[]) Arrays.copyOfRange(eArr, i2, i + i2, destination.getClass());
            uhy.a(tArr, "");
            return tArr;
        }
        E[] eArr2 = this.b;
        int i3 = this.i;
        uez.a(eArr2, destination, 0, i3, i + i3);
        int length2 = destination.length;
        int i4 = this.g;
        if (length2 > i4) {
            destination[i4] = null;
        }
        return destination;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        E[] eArr = this.b;
        int i = this.i;
        return uez.a(eArr, i, this.g + i);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object other) {
        return other == this || ((other instanceof List) && a((List<?>) other));
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int b;
        b = arrayOfUninitializedElements.b(this.b, this.i, this.g);
        return b;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        String c2;
        c2 = arrayOfUninitializedElements.c(this.b, this.i, this.g);
        return c2;
    }

    private final void e(int i) {
        if (this.d != null) {
            throw new IllegalStateException();
        }
        if (i < 0) {
            throw new OutOfMemoryError();
        }
        if (i > this.b.length) {
            this.b = (E[]) arrayOfUninitializedElements.c(this.b, uew.e.a(this.b.length, i));
        }
    }

    private final void b() {
        if (d()) {
            throw new UnsupportedOperationException();
        }
    }

    private final boolean d() {
        uga<E> ugaVar;
        return this.c || ((ugaVar = this.j) != null && ugaVar.c);
    }

    private final void d(int i) {
        e(this.g + i);
    }

    private final boolean a(List<?> list) {
        boolean c2;
        c2 = arrayOfUninitializedElements.c(this.b, this.i, this.g, list);
        return c2;
    }

    private final void c(int i, int i2) {
        d(i2);
        E[] eArr = this.b;
        uez.a(eArr, eArr, i + i2, i, this.i + this.g);
        this.g += i2;
    }

    private final void e(int i, E e2) {
        uga<E> ugaVar = this.d;
        if (ugaVar != null) {
            ugaVar.e(i, e2);
            this.b = this.d.b;
            this.g++;
        } else {
            c(i, 1);
            this.b[i] = e2;
        }
    }

    private final void a(int i, Collection<? extends E> collection, int i2) {
        uga<E> ugaVar = this.d;
        if (ugaVar != null) {
            ugaVar.a(i, collection, i2);
            this.b = this.d.b;
            this.g += i2;
        } else {
            c(i, i2);
            Iterator<? extends E> it = collection.iterator();
            for (int i3 = 0; i3 < i2; i3++) {
                this.b[i + i3] = it.next();
            }
        }
    }

    private final E c(int i) {
        uga<E> ugaVar = this.d;
        if (ugaVar != null) {
            this.g--;
            return ugaVar.c(i);
        }
        E[] eArr = this.b;
        E e2 = eArr[i];
        uez.a(eArr, eArr, i, i + 1, this.i + this.g);
        arrayOfUninitializedElements.b(this.b, (this.i + this.g) - 1);
        this.g--;
        return e2;
    }

    private final void a(int i, int i2) {
        uga<E> ugaVar = this.d;
        if (ugaVar != null) {
            ugaVar.a(i, i2);
        } else {
            E[] eArr = this.b;
            uez.a(eArr, eArr, i, i + i2, this.g);
            E[] eArr2 = this.b;
            int i3 = this.g;
            arrayOfUninitializedElements.d(eArr2, i3 - i2, i3);
        }
        this.g -= i2;
    }

    private final int d(int i, int i2, Collection<? extends E> collection, boolean z) {
        uga<E> ugaVar = this.d;
        if (ugaVar != null) {
            int d2 = ugaVar.d(i, i2, collection, z);
            this.g -= d2;
            return d2;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i + i3;
            if (collection.contains(this.b[i5]) == z) {
                E[] eArr = this.b;
                i3++;
                eArr[i4 + i] = eArr[i5];
                i4++;
            } else {
                i3++;
            }
        }
        int i6 = i2 - i4;
        E[] eArr2 = this.b;
        uez.a(eArr2, eArr2, i4 + i, i2 + i, this.g);
        E[] eArr3 = this.b;
        int i7 = this.g;
        arrayOfUninitializedElements.d(eArr3, i7 - i6, i7);
        this.g -= i6;
        return i6;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u000e\u0010\u0010\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\r\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0011J\b\u0010\u0014\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u0015\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlin/collections/builders/ListBuilder$Itr;", ExifInterface.LONGITUDE_EAST, "", "list", "Lkotlin/collections/builders/ListBuilder;", "index", "", "(Lkotlin/collections/builders/ListBuilder;I)V", "lastIndex", "add", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)V", "hasNext", "", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "previous", "previousIndex", "remove", "set", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class d<E> implements ListIterator<E>, KMutableListIterator {

        /* renamed from: a, reason: collision with root package name */
        private int f17413a;
        private int b;
        private final uga<E> c;

        public d(uga<E> ugaVar, int i) {
            uhy.e((Object) ugaVar, "");
            this.c = ugaVar;
            this.b = i;
            this.f17413a = -1;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.b > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.b < ((uga) this.c).g;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.b - 1;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.b;
        }

        @Override // java.util.ListIterator
        public E previous() {
            int i = this.b;
            if (i <= 0) {
                throw new NoSuchElementException();
            }
            int i2 = i - 1;
            this.b = i2;
            this.f17413a = i2;
            return (E) ((uga) this.c).b[((uga) this.c).i + this.f17413a];
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            if (this.b >= ((uga) this.c).g) {
                throw new NoSuchElementException();
            }
            int i = this.b;
            this.b = i + 1;
            this.f17413a = i;
            return (E) ((uga) this.c).b[((uga) this.c).i + this.f17413a];
        }

        @Override // java.util.ListIterator
        public void set(E element) {
            int i = this.f17413a;
            if (i == -1) {
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
            }
            this.c.set(i, element);
        }

        @Override // java.util.ListIterator
        public void add(E element) {
            uga<E> ugaVar = this.c;
            int i = this.b;
            this.b = i + 1;
            ugaVar.add(i, element);
            this.f17413a = -1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            int i = this.f17413a;
            if (i == -1) {
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
            }
            this.c.remove(i);
            this.b = this.f17413a;
            this.f17413a = -1;
        }
    }
}
