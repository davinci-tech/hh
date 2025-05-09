package defpackage;

import com.alipay.sdk.m.e.i;
import com.alipay.sdk.m.e.j;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import org.json.alipay.a;

/* loaded from: classes7.dex */
public final class jf implements i, j {
    @Override // com.alipay.sdk.m.e.i, com.alipay.sdk.m.e.j
    public final boolean a(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }

    @Override // com.alipay.sdk.m.e.i
    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(a.class)) {
            return null;
        }
        a aVar = (a) obj;
        Collection<Object> d = d(jk.a(type), type);
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Does not support the implement for generics.");
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        for (int i = 0; i < aVar.a(); i++) {
            d.add(jj.a(aVar.a(i), type2));
        }
        return d;
    }

    @Override // com.alipay.sdk.m.e.j
    public final Object a(Object obj) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((Iterable) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(jh.c(it.next()));
        }
        return arrayList;
    }

    public static Collection<Object> d(Class<?> cls, Type type) {
        if (cls == AbstractCollection.class) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(EnumSet.class)) {
            return EnumSet.noneOf((Class) (type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class));
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception unused) {
            throw new IllegalArgumentException("create instane error, class " + cls.getName());
        }
    }
}
