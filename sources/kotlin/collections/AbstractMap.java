package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hms.network.embedded.q0;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.ufe;
import defpackage.uhy;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b'\u0018\u0000 )*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0001)B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0016H\u0000¢\u0006\u0002\b\u0017J\u0015\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001d\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\u0018\u0010 \u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0019\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\rH\u0016J#\u0010#\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u0014H\u0016J\b\u0010&\u001a\u00020'H\u0016J\u0012\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u001fH\u0002J\u001c\u0010&\u001a\u00020'2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016H\bR\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bX\u0088\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006*"}, d2 = {"Lkotlin/collections/AbstractMap;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "()V", "_keys", "", "_values", "", "keys", "getKeys", "()Ljava/util/Set;", "size", "", "getSize", "()I", q0.j, "getValues", "()Ljava/util/Collection;", "containsEntry", "", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", MedalConstants.EVENT_KEY, "(Ljava/lang/Object;)Z", "containsValue", "value", "equals", "other", "", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", WatchFaceConstant.HASHCODE, "implFindEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;", "isEmpty", "toString", "", "o", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public abstract class AbstractMap<K, V> implements Map<K, V>, KMappedMarker {
    private volatile Set<? extends K> _keys;
    private volatile Collection<? extends V> _values;

    public abstract Set getEntries();

    protected AbstractMap() {
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public final int size() {
        return getSize();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return getValues();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return implFindEntry(key) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        Set<Map.Entry<K, V>> entrySet = entrySet();
        if (!(entrySet instanceof Collection) || !entrySet.isEmpty()) {
            Iterator<T> it = entrySet.iterator();
            while (it.hasNext()) {
                if (uhy.e(((Map.Entry) it.next()).getValue(), value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry<?, ?> entry) {
        if (entry == null) {
            return false;
        }
        Object key = entry.getKey();
        Object value = entry.getValue();
        AbstractMap<K, V> abstractMap = this;
        uhy.b(abstractMap, "");
        V v = abstractMap.get(key);
        if (!uhy.e(value, v)) {
            return false;
        }
        if (v != null) {
            return true;
        }
        uhy.b(abstractMap, "");
        return abstractMap.containsKey(key);
    }

    @Override // java.util.Map
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Map)) {
            return false;
        }
        Map map = (Map) other;
        if (size() != map.size()) {
            return false;
        }
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        if ((entrySet instanceof Collection) && entrySet.isEmpty()) {
            return true;
        }
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            if (!containsEntry$kotlin_stdlib((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object key) {
        Map.Entry<K, V> implFindEntry = implFindEntry(key);
        if (implFindEntry != null) {
            return implFindEntry.getValue();
        }
        return null;
    }

    @Override // java.util.Map
    public int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public int getSize() {
        return entrySet().size();
    }

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\tJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"kotlin/collections/AbstractMap$keys$1", "Lkotlin/collections/AbstractSet;", "size", "", "getSize", "()I", "contains", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)Z", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class b extends AbstractSet<K> {
        final /* synthetic */ AbstractMap<K, V> b;

        /* JADX WARN: Multi-variable type inference failed */
        b(AbstractMap<K, ? extends V> abstractMap) {
            this.b = abstractMap;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean contains(Object element) {
            return this.b.containsKey(element);
        }

        @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0002\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"kotlin/collections/AbstractMap$keys$1$iterator$1", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class c implements Iterator<K>, KMappedMarker {
            final /* synthetic */ Iterator<Map.Entry<K, V>> e;

            /* JADX WARN: Multi-variable type inference failed */
            c(Iterator<? extends Map.Entry<? extends K, ? extends V>> it) {
                this.e = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.e.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                return this.e.next().getKey();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        }

        @Override // kotlin.collections.AbstractSet, kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<K> iterator() {
            return new c(this.b.entrySet().iterator());
        }

        @Override // kotlin.collections.AbstractCollection
        /* renamed from: getSize */
        public int getB() {
            return this.b.size();
        }
    }

    public Set<K> getKeys() {
        if (this._keys == null) {
            this._keys = new b(this);
        }
        Set<? extends K> set = this._keys;
        uhy.d(set);
        return set;
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010&\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0006\b\u0001\u0010\u0003 \u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "it", "", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class c extends Lambda implements Function1<Map.Entry<? extends K, ? extends V>, CharSequence> {
        final /* synthetic */ AbstractMap<K, V> d;

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final CharSequence invoke(Map.Entry<? extends K, ? extends V> entry) {
            uhy.e((Object) entry, "");
            return this.d.toString((Map.Entry) entry);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        c(AbstractMap<K, ? extends V> abstractMap) {
            super(1);
            this.d = abstractMap;
        }
    }

    public String toString() {
        return ufe.b(entrySet(), ", ", "{", "}", 0, null, new c(this), 24, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String toString(Map.Entry<? extends K, ? extends V> entry) {
        return toString(entry.getKey()) + '=' + toString(entry.getValue());
    }

    private final String toString(Object o) {
        return o == this ? "(this Map)" : String.valueOf(o);
    }

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\tJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"kotlin/collections/AbstractMap$values$1", "Lkotlin/collections/AbstractCollection;", "size", "", "getSize", "()I", "contains", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Object;)Z", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class e extends AbstractCollection<V> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AbstractMap<K, V> f14476a;

        /* JADX WARN: Multi-variable type inference failed */
        e(AbstractMap<K, ? extends V> abstractMap) {
            this.f14476a = abstractMap;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean contains(Object element) {
            return this.f14476a.containsValue(element);
        }

        @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0002\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"kotlin/collections/AbstractMap$values$1$iterator$1", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* renamed from: kotlin.collections.AbstractMap$e$e, reason: collision with other inner class name */
        public static final class C0315e implements Iterator<V>, KMappedMarker {
            final /* synthetic */ Iterator<Map.Entry<K, V>> e;

            /* JADX WARN: Multi-variable type inference failed */
            C0315e(Iterator<? extends Map.Entry<? extends K, ? extends V>> it) {
                this.e = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.e.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                return this.e.next().getValue();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new C0315e(this.f14476a.entrySet().iterator());
        }

        @Override // kotlin.collections.AbstractCollection
        /* renamed from: getSize */
        public int getB() {
            return this.f14476a.size();
        }
    }

    public Collection<V> getValues() {
        if (this._values == null) {
            this._values = new e(this);
        }
        Collection<? extends V> collection = this._values;
        uhy.d(collection);
        return collection;
    }

    private final Map.Entry<K, V> implFindEntry(K key) {
        Object obj;
        Iterator<T> it = entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (uhy.e(((Map.Entry) obj).getKey(), key)) {
                break;
            }
        }
        return (Map.Entry) obj;
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
