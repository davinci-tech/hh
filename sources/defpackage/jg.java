package defpackage;

import com.alipay.sdk.m.e.i;
import com.alipay.sdk.m.e.j;
import java.lang.reflect.Type;

/* loaded from: classes7.dex */
public final class jg implements i, j {
    @Override // com.alipay.sdk.m.e.i, com.alipay.sdk.m.e.j
    public final boolean a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }

    @Override // com.alipay.sdk.m.e.i
    public final Object a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    @Override // com.alipay.sdk.m.e.j
    public final Object a(Object obj) {
        return ((Enum) obj).name();
    }
}
