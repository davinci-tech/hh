package defpackage;

import com.alipay.sdk.m.e.i;
import com.alipay.sdk.m.e.j;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.json.alipay.a;

/* loaded from: classes7.dex */
public final class jc implements i, j {
    @Override // com.alipay.sdk.m.e.i, com.alipay.sdk.m.e.j
    public final boolean a(Class<?> cls) {
        return cls.isArray();
    }

    @Override // com.alipay.sdk.m.e.i
    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(a.class)) {
            return null;
        }
        a aVar = (a) obj;
        if (type instanceof GenericArrayType) {
            throw new IllegalArgumentException("Does not support generic array!");
        }
        Class<?> componentType = ((Class) type).getComponentType();
        int a2 = aVar.a();
        Object newInstance = Array.newInstance(componentType, a2);
        for (int i = 0; i < a2; i++) {
            Array.set(newInstance, i, jj.a(aVar.a(i), componentType));
        }
        return newInstance;
    }

    @Override // com.alipay.sdk.m.e.j
    public final Object a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (Object[]) obj) {
            arrayList.add(jh.c(obj2));
        }
        return arrayList;
    }
}
