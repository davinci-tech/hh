package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018\u0000 P*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001PB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0007\b\u0016¢\u0006\u0002\u0010\u0006B\u0015\b\u0016\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0016\u0010\u001a\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0013\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u001cJ\b\u0010\u001e\u001a\u00020\u0017H\u0016J\u0016\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0016J\u001e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0004H\u0002J\u001d\u0010'\u001a\u00020\u00142\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140)H\u0082\bJ\u000b\u0010*\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010,\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0016\u0010-\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u00100\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\u0016\u00102\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0004H\u0083\b¢\u0006\u0002\u0010.J\u0011\u0010!\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0083\bJM\u00103\u001a\u00020\u00172>\u00104\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u000e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u001705H\u0000¢\u0006\u0002\b8J\b\u00109\u001a\u00020\u0014H\u0016J\u000b\u0010:\u001a\u00028\u0000¢\u0006\u0002\u0010+J\u0015\u0010;\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\r\u0010<\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0010\u0010=\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010>\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u0010?\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u0016\u0010@\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0015\u0010A\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010.J\u000b\u0010B\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010C\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u000b\u0010D\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010E\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0016\u0010F\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u001e\u0010G\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010HJ\u0017\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0000¢\u0006\u0004\bJ\u0010KJ)\u0010I\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0000¢\u0006\u0004\bJ\u0010NJ\u0015\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010KJ'\u0010O\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0016¢\u0006\u0002\u0010NR\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006Q"}, d2 = {"Lkotlin/collections/ArrayDeque;", ExifInterface.LONGITUDE_EAST, "Lkotlin/collections/AbstractMutableList;", "initialCapacity", "", "(I)V", "()V", "elements", "", "(Ljava/util/Collection;)V", "elementData", "", "", "[Ljava/lang/Object;", "head", "<set-?>", "size", "getSize", "()I", "add", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "addFirst", "(Ljava/lang/Object;)V", "addLast", "clear", "contains", "copyCollectionElements", "internalIndex", "copyElements", "newCapacity", "decremented", "ensureCapacity", "minCapacity", "filterInPlace", "predicate", "Lkotlin/Function1;", "first", "()Ljava/lang/Object;", "firstOrNull", "get", "(I)Ljava/lang/Object;", "incremented", "indexOf", "(Ljava/lang/Object;)I", "internalGet", "internalStructure", "structure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "internalStructure$kotlin_stdlib", "isEmpty", "last", "lastIndexOf", "lastOrNull", "negativeMod", "positiveMod", "remove", "removeAll", "removeAt", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "testToArray", "testToArray$kotlin_stdlib", "()[Ljava/lang/Object;", ExifInterface.GPS_DIRECTION_TRUE, "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toArray", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uew<E> extends AbstractMutableList<E> {

    /* renamed from: a, reason: collision with root package name */
    private int f17410a;
    private int b;
    private Object[] c = d;
    public static final b e = new b(null);
    private static final Object[] d = new Object[0];

    @Override // kotlin.collections.AbstractMutableList
    /* renamed from: getSize, reason: from getter */
    public int getB() {
        return this.b;
    }

    private final void d(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.c;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == d) {
            this.c = new Object[uja.a(i, 10)];
        } else {
            a(e.a(objArr.length, i));
        }
    }

    private final void a(int i) {
        Object[] objArr = new Object[i];
        Object[] objArr2 = this.c;
        uez.a(objArr2, objArr, 0, this.f17410a, objArr2.length);
        Object[] objArr3 = this.c;
        int length = objArr3.length;
        int i2 = this.f17410a;
        uez.a(objArr3, objArr, length - i2, 0, i2);
        this.f17410a = 0;
        this.c = objArr;
    }

    private final int g(int i) {
        Object[] objArr = this.c;
        return i >= objArr.length ? i - objArr.length : i;
    }

    private final int e(int i) {
        return i < 0 ? i + this.c.length : i;
    }

    private final int b(int i) {
        if (i == uez.b(this.c)) {
            return 0;
        }
        return i + 1;
    }

    private final int c(int i) {
        return i == 0 ? uez.b(this.c) : i - 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final void e(E e2) {
        d(size() + 1);
        int c = c(this.f17410a);
        this.f17410a = c;
        this.c[c] = e2;
        this.b = size() + 1;
    }

    public final void c(E e2) {
        d(size() + 1);
        this.c[g(this.f17410a + size())] = e2;
        this.b = size() + 1;
    }

    public final E d() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.c;
        int i = this.f17410a;
        E e2 = (E) objArr[i];
        objArr[i] = null;
        this.f17410a = b(i);
        this.b = size() - 1;
        return e2;
    }

    public final E e() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int g = g(this.f17410a + ufe.e((List) this));
        Object[] objArr = this.c;
        E e2 = (E) objArr[g];
        objArr[g] = null;
        this.b = size() - 1;
        return e2;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E element) {
        c((uew<E>) element);
        return true;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int index, E element) {
        AbstractList.INSTANCE.b(index, size());
        if (index == size()) {
            c((uew<E>) element);
            return;
        }
        if (index == 0) {
            e((uew<E>) element);
            return;
        }
        d(size() + 1);
        int g = g(this.f17410a + index);
        if (index < ((size() + 1) >> 1)) {
            int c = c(g);
            int c2 = c(this.f17410a);
            int i = this.f17410a;
            if (c >= i) {
                Object[] objArr = this.c;
                objArr[c2] = objArr[i];
                uez.a(objArr, objArr, i, i + 1, c + 1);
            } else {
                Object[] objArr2 = this.c;
                uez.a(objArr2, objArr2, i - 1, i, objArr2.length);
                Object[] objArr3 = this.c;
                objArr3[objArr3.length - 1] = objArr3[0];
                uez.a(objArr3, objArr3, 0, 1, c + 1);
            }
            this.c[c] = element;
            this.f17410a = c2;
        } else {
            int g2 = g(this.f17410a + size());
            if (g < g2) {
                Object[] objArr4 = this.c;
                uez.a(objArr4, objArr4, g + 1, g, g2);
            } else {
                Object[] objArr5 = this.c;
                uez.a(objArr5, objArr5, 1, 0, g2);
                Object[] objArr6 = this.c;
                objArr6[0] = objArr6[objArr6.length - 1];
                uez.a(objArr6, objArr6, g + 1, g, objArr6.length - 1);
            }
            this.c[g] = element;
        }
        this.b = size() + 1;
    }

    private final void e(int i, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.c.length;
        while (i < length && it.hasNext()) {
            this.c[i] = it.next();
            i++;
        }
        int i2 = this.f17410a;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.c[i3] = it.next();
        }
        this.b = size() + collection.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> elements) {
        uhy.e((Object) elements, "");
        if (elements.isEmpty()) {
            return false;
        }
        d(size() + elements.size());
        e(g(this.f17410a + size()), elements);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int index, Collection<? extends E> elements) {
        uhy.e((Object) elements, "");
        AbstractList.INSTANCE.b(index, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (index == size()) {
            return addAll(elements);
        }
        d(size() + elements.size());
        int g = g(this.f17410a + size());
        int g2 = g(this.f17410a + index);
        int size = elements.size();
        if (index < ((size() + 1) >> 1)) {
            int i = this.f17410a;
            int i2 = i - size;
            if (g2 < i) {
                Object[] objArr = this.c;
                uez.a(objArr, objArr, i2, i, objArr.length);
                if (size >= g2) {
                    Object[] objArr2 = this.c;
                    uez.a(objArr2, objArr2, objArr2.length - size, 0, g2);
                } else {
                    Object[] objArr3 = this.c;
                    uez.a(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.c;
                    uez.a(objArr4, objArr4, 0, size, g2);
                }
            } else if (i2 >= 0) {
                Object[] objArr5 = this.c;
                uez.a(objArr5, objArr5, i2, i, g2);
            } else {
                Object[] objArr6 = this.c;
                i2 += objArr6.length;
                int length = objArr6.length - i2;
                if (length >= g2 - i) {
                    uez.a(objArr6, objArr6, i2, i, g2);
                } else {
                    uez.a(objArr6, objArr6, i2, i, i + length);
                    Object[] objArr7 = this.c;
                    uez.a(objArr7, objArr7, 0, this.f17410a + length, g2);
                }
            }
            this.f17410a = i2;
            e(e(g2 - size), elements);
        } else {
            int i3 = g2 + size;
            if (g2 < g) {
                int i4 = size + g;
                Object[] objArr8 = this.c;
                if (i4 <= objArr8.length) {
                    uez.a(objArr8, objArr8, i3, g2, g);
                } else if (i3 >= objArr8.length) {
                    uez.a(objArr8, objArr8, i3 - objArr8.length, g2, g);
                } else {
                    int length2 = g - (i4 - objArr8.length);
                    uez.a(objArr8, objArr8, 0, length2, g);
                    Object[] objArr9 = this.c;
                    uez.a(objArr9, objArr9, i3, g2, length2);
                }
            } else {
                Object[] objArr10 = this.c;
                uez.a(objArr10, objArr10, size, 0, g);
                Object[] objArr11 = this.c;
                if (i3 >= objArr11.length) {
                    uez.a(objArr11, objArr11, i3 - objArr11.length, g2, objArr11.length);
                } else {
                    uez.a(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.c;
                    uez.a(objArr12, objArr12, i3, g2, objArr12.length - size);
                }
            }
            e(g2, elements);
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int index) {
        AbstractList.INSTANCE.e(index, size());
        return (E) this.c[g(this.f17410a + index)];
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int index, E element) {
        AbstractList.INSTANCE.e(index, size());
        int g = g(this.f17410a + index);
        Object[] objArr = this.c;
        E e2 = (E) objArr[g];
        objArr[g] = element;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object element) {
        int i;
        int g = g(this.f17410a + size());
        int i2 = this.f17410a;
        if (i2 < g) {
            while (i2 < g) {
                if (uhy.e(element, this.c[i2])) {
                    i = this.f17410a;
                } else {
                    i2++;
                }
            }
            return -1;
        }
        if (i2 < g) {
            return -1;
        }
        int length = this.c.length;
        while (true) {
            if (i2 >= length) {
                for (int i3 = 0; i3 < g; i3++) {
                    if (uhy.e(element, this.c[i3])) {
                        i2 = i3 + this.c.length;
                        i = this.f17410a;
                    }
                }
                return -1;
            }
            if (uhy.e(element, this.c[i2])) {
                i = this.f17410a;
                break;
            }
            i2++;
        }
        return i2 - i;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object element) {
        int b2;
        int i;
        int g = g(this.f17410a + size());
        int i2 = this.f17410a;
        if (i2 < g) {
            b2 = g - 1;
            if (i2 <= b2) {
                while (!uhy.e(element, this.c[b2])) {
                    if (b2 != i2) {
                        b2--;
                    }
                }
                i = this.f17410a;
                return b2 - i;
            }
            return -1;
        }
        if (i2 > g) {
            int i3 = g - 1;
            while (true) {
                if (-1 < i3) {
                    if (uhy.e(element, this.c[i3])) {
                        b2 = i3 + this.c.length;
                        i = this.f17410a;
                        break;
                    }
                    i3--;
                } else {
                    b2 = uez.b(this.c);
                    int i4 = this.f17410a;
                    if (i4 <= b2) {
                        while (!uhy.e(element, this.c[b2])) {
                            if (b2 != i4) {
                                b2--;
                            }
                        }
                        i = this.f17410a;
                    }
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object element) {
        int indexOf = indexOf(element);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int index) {
        AbstractList.INSTANCE.e(index, size());
        uew<E> uewVar = this;
        if (index == ufe.e((List) uewVar)) {
            return e();
        }
        if (index == 0) {
            return d();
        }
        int g = g(this.f17410a + index);
        E e2 = (E) this.c[g];
        if (index < (size() >> 1)) {
            int i = this.f17410a;
            if (g >= i) {
                Object[] objArr = this.c;
                uez.a(objArr, objArr, i + 1, i, g);
            } else {
                Object[] objArr2 = this.c;
                uez.a(objArr2, objArr2, 1, 0, g);
                Object[] objArr3 = this.c;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i2 = this.f17410a;
                uez.a(objArr3, objArr3, i2 + 1, i2, objArr3.length - 1);
            }
            Object[] objArr4 = this.c;
            int i3 = this.f17410a;
            objArr4[i3] = null;
            this.f17410a = b(i3);
        } else {
            int g2 = g(this.f17410a + ufe.e((List) uewVar));
            if (g <= g2) {
                Object[] objArr5 = this.c;
                uez.a(objArr5, objArr5, g, g + 1, g2 + 1);
            } else {
                Object[] objArr6 = this.c;
                uez.a(objArr6, objArr6, g, g + 1, objArr6.length);
                Object[] objArr7 = this.c;
                objArr7[objArr7.length - 1] = objArr7[0];
                uez.a(objArr7, objArr7, 0, 1, g2 + 1);
            }
            this.c[g2] = null;
        }
        this.b = size() - 1;
        return e2;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int g = g(this.f17410a + size());
        int i = this.f17410a;
        if (i < g) {
            uez.b(this.c, (Object) null, i, g);
        } else if (!isEmpty()) {
            Object[] objArr = this.c;
            uez.b(objArr, (Object) null, this.f17410a, objArr.length);
            uez.b(this.c, (Object) null, 0, g);
        }
        this.f17410a = 0;
        this.b = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] array) {
        uhy.e((Object) array, "");
        if (array.length < size()) {
            array = (T[]) uez.e(array, size());
        }
        int g = g(this.f17410a + size());
        int i = this.f17410a;
        if (i < g) {
            uez.d(this.c, array, 0, i, g, 2, null);
        } else if (!isEmpty()) {
            Object[] objArr = this.c;
            uez.a(objArr, array, 0, this.f17410a, objArr.length);
            Object[] objArr2 = this.c;
            uez.a(objArr2, array, objArr2.length - this.f17410a, 0, g);
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/collections/ArrayDeque$Companion;", "", "()V", "defaultMinCapacity", "", "emptyElementData", "", "[Ljava/lang/Object;", "maxArraySize", "newCapacity", "oldCapacity", "minCapacity", "newCapacity$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class b {
        public final int a(int i, int i2) {
            int i3 = i + (i >> 1);
            if (i3 - i2 < 0) {
                i3 = i2;
            }
            return i3 - 2147483639 > 0 ? i2 > 2147483639 ? Integer.MAX_VALUE : 2147483639 : i3;
        }

        private b() {
        }

        public /* synthetic */ b(uib uibVar) {
            this();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> elements) {
        int g;
        uhy.e((Object) elements, "");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.c.length != 0) {
            int g2 = g(this.f17410a + size());
            int i = this.f17410a;
            if (i < g2) {
                g = i;
                while (i < g2) {
                    Object obj = this.c[i];
                    if (!elements.contains(obj)) {
                        this.c[g] = obj;
                        g++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                uez.b(this.c, (Object) null, g, g2);
            } else {
                int length = this.c.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.c;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (!elements.contains(obj2)) {
                        this.c[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                g = g(i2);
                for (int i3 = 0; i3 < g2; i3++) {
                    Object[] objArr2 = this.c;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (!elements.contains(obj3)) {
                        this.c[g] = obj3;
                        g = b(g);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                this.b = e(g - this.f17410a);
            }
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<? extends Object> elements) {
        int g;
        uhy.e((Object) elements, "");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.c.length != 0) {
            int g2 = g(this.f17410a + size());
            int i = this.f17410a;
            if (i < g2) {
                g = i;
                while (i < g2) {
                    Object obj = this.c[i];
                    if (elements.contains(obj)) {
                        this.c[g] = obj;
                        g++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                uez.b(this.c, (Object) null, g, g2);
            } else {
                int length = this.c.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.c;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (elements.contains(obj2)) {
                        this.c[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                g = g(i2);
                for (int i3 = 0; i3 < g2; i3++) {
                    Object[] objArr2 = this.c;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (elements.contains(obj3)) {
                        this.c[g] = obj3;
                        g = b(g);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                this.b = e(g - this.f17410a);
            }
        }
        return z;
    }
}
