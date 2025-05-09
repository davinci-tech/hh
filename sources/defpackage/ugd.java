package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.network.embedded.q0;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;

@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 }*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u00060\u0004j\u0002`\u0005:\t}~\u007f\u0080\u0001\u0081\u0001\u0082\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tBE\b\u0002\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0002\u0010\u0012J\u0017\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00028\u0000H\u0000¢\u0006\u0004\b4\u00105J\u0013\u00106\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0002¢\u0006\u0002\u00107J\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000109J\r\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b<J\b\u0010=\u001a\u00020;H\u0016J\b\u0010>\u001a\u00020;H\u0002J\u0019\u0010?\u001a\u00020!2\n\u0010@\u001a\u0006\u0012\u0002\b\u00030AH\u0000¢\u0006\u0002\bBJ!\u0010C\u001a\u00020!2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0000¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020!2\u0006\u00103\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010HJ\u0015\u0010I\u001a\u00020!2\u0006\u0010J\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010HJ\u0018\u0010K\u001a\u00020!2\u000e\u0010L\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u000309H\u0002J\u0010\u0010M\u001a\u00020;2\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u0010\u0010N\u001a\u00020;2\u0006\u0010O\u001a\u00020\bH\u0002J\u0019\u0010P\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010QH\u0000¢\u0006\u0002\bRJ\u0013\u0010S\u001a\u00020!2\b\u0010L\u001a\u0004\u0018\u00010TH\u0096\u0002J\u0015\u0010U\u001a\u00020\b2\u0006\u00103\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00105J\u0015\u0010V\u001a\u00020\b2\u0006\u0010J\u001a\u00028\u0001H\u0002¢\u0006\u0002\u00105J\u0018\u0010W\u001a\u0004\u0018\u00018\u00012\u0006\u00103\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010XJ\u0015\u0010Y\u001a\u00020\b2\u0006\u00103\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00105J\b\u0010Z\u001a\u00020\bH\u0016J\b\u0010[\u001a\u00020!H\u0016J\u0019\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010]H\u0000¢\u0006\u0002\b^J\u001f\u0010_\u001a\u0004\u0018\u00018\u00012\u0006\u00103\u001a\u00028\u00002\u0006\u0010J\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010`J\u001e\u0010a\u001a\u00020;2\u0014\u0010b\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000109H\u0016J\"\u0010c\u001a\u00020!2\u0018\u0010b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010E0AH\u0002J\u001c\u0010d\u001a\u00020!2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0002J\u0010\u0010e\u001a\u00020!2\u0006\u0010f\u001a\u00020\bH\u0002J\u0010\u0010g\u001a\u00020;2\u0006\u0010h\u001a\u00020\bH\u0002J\u0017\u0010i\u001a\u0004\u0018\u00018\u00012\u0006\u00103\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010XJ!\u0010j\u001a\u00020!2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0000¢\u0006\u0002\bkJ\u0010\u0010l\u001a\u00020;2\u0006\u0010m\u001a\u00020\bH\u0002J\u0017\u0010n\u001a\u00020\b2\u0006\u00103\u001a\u00028\u0000H\u0000¢\u0006\u0004\bo\u00105J\u0010\u0010p\u001a\u00020;2\u0006\u0010q\u001a\u00020\bH\u0002J\u0017\u0010r\u001a\u00020!2\u0006\u0010s\u001a\u00028\u0001H\u0000¢\u0006\u0004\bt\u0010HJ\u0010\u0010u\u001a\u00020!2\u0006\u0010v\u001a\u00020\bH\u0002J\b\u0010w\u001a\u00020xH\u0016J\u0019\u0010y\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010zH\u0000¢\u0006\u0002\b{J\b\u0010|\u001a\u00020TH\u0002R\u0014\u0010\u0013\u001a\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R&\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00180\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0015R\u001e\u0010\"\u001a\u00020!2\u0006\u0010 \u001a\u00020!@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u001aR\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010'R\u0016\u0010(\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010*\u001a\u00020\b2\u0006\u0010 \u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0015R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010'R\u0016\u00100\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0083\u0001"}, d2 = {"Lkotlin/collections/builders/MapBuilder;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "initialCapacity", "", "(I)V", "keysArray", "", "valuesArray", "presenceArray", "", "hashArray", "maxProbeDistance", "length", "([Ljava/lang/Object;[Ljava/lang/Object;[I[III)V", "capacity", "getCapacity$kotlin_stdlib", "()I", "entries", "", "", "getEntries", "()Ljava/util/Set;", "entriesView", "Lkotlin/collections/builders/MapBuilderEntries;", "hashShift", "hashSize", "getHashSize", "<set-?>", "", "isReadOnly", "isReadOnly$kotlin_stdlib", "()Z", "keys", "getKeys", "[Ljava/lang/Object;", "keysView", "Lkotlin/collections/builders/MapBuilderKeys;", "size", "getSize", q0.j, "", "getValues", "()Ljava/util/Collection;", "valuesView", "Lkotlin/collections/builders/MapBuilderValues;", "addKey", MedalConstants.EVENT_KEY, "addKey$kotlin_stdlib", "(Ljava/lang/Object;)I", "allocateValuesArray", "()[Ljava/lang/Object;", "build", "", "checkIsMutable", "", "checkIsMutable$kotlin_stdlib", "clear", "compact", "containsAllEntries", FitRunPlayAudio.OPPORTUNITY_M, "", "containsAllEntries$kotlin_stdlib", "containsEntry", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", "(Ljava/lang/Object;)Z", "containsValue", "value", "contentEquals", "other", "ensureCapacity", "ensureExtraCapacity", "n", "entriesIterator", "Lkotlin/collections/builders/MapBuilder$EntriesItr;", "entriesIterator$kotlin_stdlib", "equals", "", "findKey", "findValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hash", WatchFaceConstant.HASHCODE, "isEmpty", "keysIterator", "Lkotlin/collections/builders/MapBuilder$KeysItr;", "keysIterator$kotlin_stdlib", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "putAllEntries", "putEntry", "putRehash", "i", "rehash", "newHashSize", "remove", "removeEntry", "removeEntry$kotlin_stdlib", "removeHashAt", "removedHash", "removeKey", "removeKey$kotlin_stdlib", "removeKeyAt", "index", "removeValue", FunctionSetBeanReader.BI_ELEMENT, "removeValue$kotlin_stdlib", "shouldCompact", "extraCapacity", "toString", "", "valuesIterator", "Lkotlin/collections/builders/MapBuilder$ValuesItr;", "valuesIterator$kotlin_stdlib", "writeReplace", "Companion", "EntriesItr", "EntryRef", "Itr", "KeysItr", "ValuesItr", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ugd<K, V> implements Map<K, V>, Serializable, KMutableMap {
    public static final d c = new d(null);
    private static final ugd e;

    /* renamed from: a, reason: collision with root package name */
    private int[] f17414a;
    private ugf<K, V> b;
    private int d;
    private ugg<K> f;
    private K[] g;
    private boolean h;
    private int i;
    private int j;
    private V[] k;
    private uge<V> l;
    private int[] m;
    private int n;

    private ugd(K[] kArr, V[] vArr, int[] iArr, int[] iArr2, int i, int i2) {
        this.g = kArr;
        this.k = vArr;
        this.m = iArr;
        this.f17414a = iArr2;
        this.j = i;
        this.i = i2;
        this.d = c.a(l());
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return j();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return i();
    }

    @Override // java.util.Map
    public final int size() {
        return getN();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return h();
    }

    /* renamed from: g, reason: from getter */
    public int getN() {
        return this.n;
    }

    /* renamed from: f, reason: from getter */
    public final boolean getH() {
        return this.h;
    }

    public ugd() {
        this(8);
    }

    public ugd(int i) {
        this(arrayOfUninitializedElements.d(i), null, new int[i], new int[c.e(i)], 2, 0);
    }

    public final Map<K, V> e() {
        b();
        this.h = true;
        if (size() > 0) {
            return this;
        }
        ugd ugdVar = e;
        uhy.b(ugdVar, "");
        return ugdVar;
    }

    private final Object writeReplace() {
        if (this.h) {
            return new ugi(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return d((ugd<K, V>) key) >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return c((ugd<K, V>) value) >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object key) {
        int d2 = d((ugd<K, V>) key);
        if (d2 < 0) {
            return null;
        }
        V[] vArr = this.k;
        uhy.d(vArr);
        return vArr[d2];
    }

    @Override // java.util.Map
    public V put(K key, V value) {
        b();
        int e2 = e((ugd<K, V>) key);
        V[] k = k();
        if (e2 < 0) {
            int i = (-e2) - 1;
            V v = k[i];
            k[i] = value;
            return v;
        }
        k[e2] = value;
        return null;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> from) {
        uhy.e((Object) from, "");
        b();
        d((Collection) from.entrySet());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V remove(Object key) {
        int a2 = a((ugd<K, V>) key);
        if (a2 < 0) {
            return null;
        }
        V[] vArr = this.k;
        uhy.d(vArr);
        V v = vArr[a2];
        arrayOfUninitializedElements.b(vArr, a2);
        return v;
    }

    @Override // java.util.Map
    public void clear() {
        b();
        IntIterator b2 = new uiv(0, this.i - 1).iterator();
        while (b2.hasNext()) {
            int nextInt = b2.nextInt();
            int[] iArr = this.m;
            int i = iArr[nextInt];
            if (i >= 0) {
                this.f17414a[i] = 0;
                iArr[nextInt] = -1;
            }
        }
        arrayOfUninitializedElements.d(this.g, 0, this.i);
        V[] vArr = this.k;
        if (vArr != null) {
            arrayOfUninitializedElements.d(vArr, 0, this.i);
        }
        this.n = 0;
        this.i = 0;
    }

    public Set<K> i() {
        ugg<K> uggVar = this.f;
        if (uggVar == null) {
            ugg<K> uggVar2 = new ugg<>(this);
            this.f = uggVar2;
            return uggVar2;
        }
        return uggVar;
    }

    public Collection<V> h() {
        uge<V> ugeVar = this.l;
        if (ugeVar == null) {
            uge<V> ugeVar2 = new uge<>(this);
            this.l = ugeVar2;
            return ugeVar2;
        }
        return ugeVar;
    }

    public Set<Map.Entry<K, V>> j() {
        ugf<K, V> ugfVar = this.b;
        if (ugfVar == null) {
            ugf<K, V> ugfVar2 = new ugf<>(this);
            this.b = ugfVar2;
            return ugfVar2;
        }
        return ugfVar;
    }

    @Override // java.util.Map
    public boolean equals(Object other) {
        return other == this || ((other instanceof Map) && d((Map<?, ?>) other));
    }

    @Override // java.util.Map
    public int hashCode() {
        c<K, V> d2 = d();
        int i = 0;
        while (d2.hasNext()) {
            i += d2.b();
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() * 3) + 2);
        sb.append("{");
        c<K, V> d2 = d();
        int i = 0;
        while (d2.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            d2.c(sb);
            i++;
        }
        sb.append("}");
        String sb2 = sb.toString();
        uhy.a(sb2, "");
        return sb2;
    }

    public final int a() {
        return this.g.length;
    }

    private final int l() {
        return this.f17414a.length;
    }

    public final void b() {
        if (this.h) {
            throw new UnsupportedOperationException();
        }
    }

    private final void d(int i) {
        if (g(i)) {
            c(l());
        } else {
            e(this.i + i);
        }
    }

    private final boolean g(int i) {
        int a2 = a();
        int i2 = this.i;
        int i3 = a2 - i2;
        int size = i2 - size();
        return i3 < i && i3 + size >= i && size >= a() / 4;
    }

    private final void e(int i) {
        if (i < 0) {
            throw new OutOfMemoryError();
        }
        if (i > a()) {
            int a2 = (a() * 3) / 2;
            if (i <= a2) {
                i = a2;
            }
            this.g = (K[]) arrayOfUninitializedElements.c(this.g, i);
            V[] vArr = this.k;
            this.k = vArr != null ? (V[]) arrayOfUninitializedElements.c(vArr, i) : null;
            int[] copyOf = Arrays.copyOf(this.m, i);
            uhy.a(copyOf, "");
            this.m = copyOf;
            int e2 = c.e(i);
            if (e2 > l()) {
                c(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V[] k() {
        V[] vArr = this.k;
        if (vArr != null) {
            return vArr;
        }
        V[] vArr2 = (V[]) arrayOfUninitializedElements.d(a());
        this.k = vArr2;
        return vArr2;
    }

    private final int j(K k) {
        return ((k != null ? k.hashCode() : 0) * (-1640531527)) >>> this.d;
    }

    private final void m() {
        int i;
        V[] vArr = this.k;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.i;
            if (i2 >= i) {
                break;
            }
            if (this.m[i2] >= 0) {
                K[] kArr = this.g;
                kArr[i3] = kArr[i2];
                if (vArr != null) {
                    vArr[i3] = vArr[i2];
                }
                i3++;
            }
            i2++;
        }
        arrayOfUninitializedElements.d(this.g, i3, i);
        if (vArr != null) {
            arrayOfUninitializedElements.d(vArr, i3, this.i);
        }
        this.i = i3;
    }

    private final void c(int i) {
        if (this.i > size()) {
            m();
        }
        if (i != l()) {
            this.f17414a = new int[i];
            this.d = c.a(i);
        } else {
            uez.b(this.f17414a, 0, 0, l());
        }
        for (int i2 = 0; i2 < this.i; i2++) {
            if (!b(i2)) {
                throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
            }
        }
    }

    private final boolean b(int i) {
        int j2 = j(this.g[i]);
        int i2 = this.j;
        while (true) {
            int[] iArr = this.f17414a;
            if (iArr[j2] == 0) {
                iArr[j2] = i + 1;
                this.m[i] = j2;
                return true;
            }
            i2--;
            if (i2 < 0) {
                return false;
            }
            j2 = j2 == 0 ? l() - 1 : j2 - 1;
        }
    }

    private final int d(K k) {
        int j2 = j(k);
        int i = this.j;
        while (true) {
            int i2 = this.f17414a[j2];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (uhy.e(this.g[i3], k)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            j2 = j2 == 0 ? l() - 1 : j2 - 1;
        }
    }

    private final int c(V v) {
        int i = this.i;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.m[i] >= 0) {
                V[] vArr = this.k;
                uhy.d(vArr);
                if (uhy.e(vArr[i], v)) {
                    return i;
                }
            }
        }
    }

    public final int e(K k) {
        b();
        while (true) {
            int j2 = j(k);
            int b2 = uja.b(this.j * 2, l() / 2);
            int i = 0;
            while (true) {
                int i2 = this.f17414a[j2];
                if (i2 <= 0) {
                    if (this.i >= a()) {
                        d(1);
                    } else {
                        int i3 = this.i;
                        int i4 = i3 + 1;
                        this.i = i4;
                        this.g[i3] = k;
                        this.m[i3] = j2;
                        this.f17414a[j2] = i4;
                        this.n = size() + 1;
                        if (i > this.j) {
                            this.j = i;
                        }
                        return i3;
                    }
                } else {
                    if (uhy.e(this.g[i2 - 1], k)) {
                        return -i2;
                    }
                    i++;
                    if (i > b2) {
                        c(l() * 2);
                        break;
                    }
                    j2 = j2 == 0 ? l() - 1 : j2 - 1;
                }
            }
        }
    }

    public final int a(K k) {
        b();
        int d2 = d((ugd<K, V>) k);
        if (d2 < 0) {
            return -1;
        }
        f(d2);
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void f(int i) {
        arrayOfUninitializedElements.b(this.g, i);
        a(this.m[i]);
        this.m[i] = -1;
        this.n = size() - 1;
    }

    private final void a(int i) {
        int b2 = uja.b(this.j * 2, l() / 2);
        int i2 = 0;
        int i3 = i;
        do {
            i = i == 0 ? l() - 1 : i - 1;
            i2++;
            if (i2 > this.j) {
                this.f17414a[i3] = 0;
                return;
            }
            int[] iArr = this.f17414a;
            int i4 = iArr[i];
            if (i4 == 0) {
                iArr[i3] = 0;
                return;
            }
            if (i4 < 0) {
                iArr[i3] = -1;
            } else {
                int i5 = i4 - 1;
                if (((j(this.g[i5]) - i) & (l() - 1)) >= i2) {
                    this.f17414a[i3] = i4;
                    this.m[i5] = i3;
                }
                b2--;
            }
            i3 = i;
            i2 = 0;
            b2--;
        } while (b2 >= 0);
        this.f17414a[i3] = -1;
    }

    public final boolean a(Map.Entry<? extends K, ? extends V> entry) {
        uhy.e((Object) entry, "");
        int d2 = d((ugd<K, V>) entry.getKey());
        if (d2 < 0) {
            return false;
        }
        V[] vArr = this.k;
        uhy.d(vArr);
        return uhy.e(vArr[d2], entry.getValue());
    }

    private final boolean d(Map<?, ?> map) {
        return size() == map.size() && c((Collection<?>) map.entrySet());
    }

    public final boolean c(Collection<?> collection) {
        uhy.e((Object) collection, "");
        for (Object obj : collection) {
            if (obj == null) {
                return false;
            }
            try {
                if (!a((Map.Entry) obj)) {
                    return false;
                }
            } catch (ClassCastException unused) {
                return false;
            }
        }
        return true;
    }

    private final boolean e(Map.Entry<? extends K, ? extends V> entry) {
        int e2 = e((ugd<K, V>) entry.getKey());
        V[] k = k();
        if (e2 >= 0) {
            k[e2] = entry.getValue();
            return true;
        }
        int i = (-e2) - 1;
        if (uhy.e(entry.getValue(), k[i])) {
            return false;
        }
        k[i] = entry.getValue();
        return true;
    }

    private final boolean d(Collection<? extends Map.Entry<? extends K, ? extends V>> collection) {
        boolean z = false;
        if (collection.isEmpty()) {
            return false;
        }
        d(collection.size());
        Iterator<? extends Map.Entry<? extends K, ? extends V>> it = collection.iterator();
        while (it.hasNext()) {
            if (e((Map.Entry) it.next())) {
                z = true;
            }
        }
        return z;
    }

    public final boolean b(Map.Entry<? extends K, ? extends V> entry) {
        uhy.e((Object) entry, "");
        b();
        int d2 = d((ugd<K, V>) entry.getKey());
        if (d2 < 0) {
            return false;
        }
        V[] vArr = this.k;
        uhy.d(vArr);
        if (!uhy.e(vArr[d2], entry.getValue())) {
            return false;
        }
        f(d2);
        return true;
    }

    public final boolean b(V v) {
        b();
        int c2 = c((ugd<K, V>) v);
        if (c2 < 0) {
            return false;
        }
        f(c2);
        return true;
    }

    public final b<K, V> n() {
        return new b<>(this);
    }

    public final j<K, V> o() {
        return new j<>(this);
    }

    public final c<K, V> d() {
        return new c<>(this);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0002J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0002R \u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlin/collections/builders/MapBuilder$Companion;", "", "()V", "Empty", "Lkotlin/collections/builders/MapBuilder;", "", "getEmpty$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "INITIAL_CAPACITY", "", "INITIAL_MAX_PROBE_DISTANCE", "MAGIC", "TOMBSTONE", "computeHashSize", "capacity", "computeShift", "hashSize", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class d {
        private d() {
        }

        public final ugd c() {
            return ugd.e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int e(int i) {
            return Integer.highestOneBit(uja.a(i, 1) * 3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int a(int i) {
            return Integer.numberOfLeadingZeros(i) + 1;
        }

        public /* synthetic */ d(uib uibVar) {
            this();
        }
    }

    static {
        ugd ugdVar = new ugd(0);
        ugdVar.h = true;
        e = ugdVar;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0006\u0010\u0017\u001a\u00020\u0015R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lkotlin/collections/builders/MapBuilder$Itr;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "index", "", "getIndex$kotlin_stdlib", "()I", "setIndex$kotlin_stdlib", "(I)V", "lastIndex", "getLastIndex$kotlin_stdlib", "setLastIndex$kotlin_stdlib", "getMap$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "hasNext", "", "initNext", "", "initNext$kotlin_stdlib", "remove", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static class e<K, V> {

        /* renamed from: a, reason: collision with root package name */
        private int f17416a;
        private int c;
        private final ugd<K, V> e;

        public e(ugd<K, V> ugdVar) {
            uhy.e((Object) ugdVar, "");
            this.e = ugdVar;
            this.c = -1;
            g();
        }

        public final ugd<K, V> d() {
            return this.e;
        }

        /* renamed from: c, reason: from getter */
        public final int getF17416a() {
            return this.f17416a;
        }

        public final void d(int i) {
            this.f17416a = i;
        }

        public final void a(int i) {
            this.c = i;
        }

        /* renamed from: e, reason: from getter */
        public final int getC() {
            return this.c;
        }

        public final void g() {
            while (this.f17416a < ((ugd) this.e).i) {
                int[] iArr = ((ugd) this.e).m;
                int i = this.f17416a;
                if (iArr[i] >= 0) {
                    return;
                } else {
                    this.f17416a = i + 1;
                }
            }
        }

        public final boolean hasNext() {
            return this.f17416a < ((ugd) this.e).i;
        }

        public final void remove() {
            if (this.c == -1) {
                throw new IllegalStateException("Call next() before removing element from the iterator.".toString());
            }
            this.e.b();
            this.e.f(this.c);
            this.c = -1;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00028\u0002H\u0096\u0002¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/collections/builders/MapBuilder$KeysItr;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/collections/builders/MapBuilder$Itr;", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class b<K, V> extends e<K, V> implements Iterator<K>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(ugd<K, V> ugdVar) {
            super(ugdVar);
            uhy.e((Object) ugdVar, "");
        }

        @Override // java.util.Iterator
        public K next() {
            if (getF17416a() >= ((ugd) d()).i) {
                throw new NoSuchElementException();
            }
            int c = getF17416a();
            d(c + 1);
            a(c);
            K k = (K) ((ugd) d()).g[getC()];
            g();
            return k;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00028\u0003H\u0096\u0002¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/collections/builders/MapBuilder$ValuesItr;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/collections/builders/MapBuilder$Itr;", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class j<K, V> extends e<K, V> implements Iterator<V>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(ugd<K, V> ugdVar) {
            super(ugdVar);
            uhy.e((Object) ugdVar, "");
        }

        @Override // java.util.Iterator
        public V next() {
            if (getF17416a() >= ((ugd) d()).i) {
                throw new NoSuchElementException();
            }
            int c = getF17416a();
            d(c + 1);
            a(c);
            Object[] objArr = ((ugd) d()).k;
            uhy.d(objArr);
            V v = (V) objArr[getC()];
            g();
            return v;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00050\u0004B\u0019\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\nH\u0096\u0002J\u0012\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lkotlin/collections/builders/MapBuilder$EntriesItr;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/collections/builders/MapBuilder$Itr;", "", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "Lkotlin/collections/builders/MapBuilder$EntryRef;", "nextAppendString", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "nextHashCode", "", "nextHashCode$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class c<K, V> extends e<K, V> implements Iterator<Map.Entry<K, V>>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(ugd<K, V> ugdVar) {
            super(ugdVar);
            uhy.e((Object) ugdVar, "");
        }

        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a<K, V> next() {
            if (getF17416a() >= ((ugd) d()).i) {
                throw new NoSuchElementException();
            }
            int c = getF17416a();
            d(c + 1);
            a(c);
            a<K, V> aVar = new a<>(d(), getC());
            g();
            return aVar;
        }

        public final int b() {
            if (getF17416a() >= ((ugd) d()).i) {
                throw new NoSuchElementException();
            }
            int c = getF17416a();
            d(c + 1);
            a(c);
            Object obj = ((ugd) d()).g[getC()];
            int hashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = ((ugd) d()).k;
            uhy.d(objArr);
            Object obj2 = objArr[getC()];
            int hashCode2 = obj2 != null ? obj2.hashCode() : 0;
            g();
            return hashCode ^ hashCode2;
        }

        public final void c(StringBuilder sb) {
            uhy.e((Object) sb, "");
            if (getF17416a() >= ((ugd) d()).i) {
                throw new NoSuchElementException();
            }
            int c = getF17416a();
            d(c + 1);
            a(c);
            Object obj = ((ugd) d()).g[getC()];
            if (uhy.e(obj, d())) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = ((ugd) d()).k;
            uhy.d(objArr);
            Object obj2 = objArr[getC()];
            if (uhy.e(obj2, d())) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            g();
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B!\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\u0015\u0010\u0013\u001a\u00028\u00032\u0006\u0010\u0014\u001a\u00028\u0003H\u0016¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00028\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00028\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0018"}, d2 = {"Lkotlin/collections/builders/MapBuilder$EntryRef;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "map", "Lkotlin/collections/builders/MapBuilder;", "index", "", "(Lkotlin/collections/builders/MapBuilder;I)V", MedalConstants.EVENT_KEY, "getKey", "()Ljava/lang/Object;", "value", "getValue", "equals", "", "other", "", WatchFaceConstant.HASHCODE, "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class a<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {

        /* renamed from: a, reason: collision with root package name */
        private final int f17415a;
        private final ugd<K, V> e;

        public a(ugd<K, V> ugdVar, int i) {
            uhy.e((Object) ugdVar, "");
            this.e = ugdVar;
            this.f17415a = i;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) ((ugd) this.e).g[this.f17415a];
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            Object[] objArr = ((ugd) this.e).k;
            uhy.d(objArr);
            return (V) objArr[this.f17415a];
        }

        @Override // java.util.Map.Entry
        public V setValue(V newValue) {
            this.e.b();
            Object[] k = this.e.k();
            int i = this.f17415a;
            V v = (V) k[i];
            k[i] = newValue;
            return v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object other) {
            if (other instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) other;
                if (uhy.e(entry.getKey(), getKey()) && uhy.e(entry.getValue(), getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K key = getKey();
            int hashCode = key != null ? key.hashCode() : 0;
            V value = getValue();
            return hashCode ^ (value != null ? value.hashCode() : 0);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }
}
