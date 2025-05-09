package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import java.io.Serializable;
import java.lang.Enum;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.enums.EnumEntries;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\b\u0003\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00060\u0005j\u0002`\u0006B\u0013\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\fH\u0096\u0002¢\u0006\u0002\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0019\u001a\u00020\u001aH\u0002R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lkotlin/enums/EnumEntriesList;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/enums/EnumEntries;", "Lkotlin/collections/AbstractList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "entries", "", "([Ljava/lang/Enum;)V", "[Ljava/lang/Enum;", "size", "", "getSize", "()I", "contains", "", FunctionSetBeanReader.BI_ELEMENT, "(Ljava/lang/Enum;)Z", "get", "index", "(I)Ljava/lang/Enum;", "indexOf", "(Ljava/lang/Enum;)I", "lastIndexOf", "writeReplace", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
final class uhb<T extends Enum<T>> extends AbstractList<T> implements EnumEntries<T>, Serializable {

    /* renamed from: a, reason: collision with root package name */
    private final T[] f17428a;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Enum) {
            return c((Enum) obj);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Enum) {
            return b((Enum) obj);
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof Enum) {
            return d((Enum) obj);
        }
        return -1;
    }

    public uhb(T[] tArr) {
        uhy.e((Object) tArr, "");
        this.f17428a = tArr;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    /* renamed from: getSize */
    public int getB() {
        return this.f17428a.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public T get(int i) {
        AbstractList.INSTANCE.e(i, this.f17428a.length);
        return this.f17428a[i];
    }

    public boolean c(T t) {
        uhy.e((Object) t, "");
        return ((Enum) uez.b(this.f17428a, t.ordinal())) == t;
    }

    public int b(T t) {
        uhy.e((Object) t, "");
        int ordinal = t.ordinal();
        if (((Enum) uez.b(this.f17428a, ordinal)) == t) {
            return ordinal;
        }
        return -1;
    }

    public int d(T t) {
        uhy.e((Object) t, "");
        return indexOf(t);
    }

    private final Object writeReplace() {
        return new uhd(this.f17428a);
    }
}
