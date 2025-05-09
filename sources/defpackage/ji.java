package defpackage;

import com.alipay.sdk.m.e.i;
import com.alipay.sdk.m.e.j;
import java.lang.reflect.Type;
import java.util.Date;

/* loaded from: classes7.dex */
public final class ji implements i, j {
    @Override // com.alipay.sdk.m.e.i, com.alipay.sdk.m.e.j
    public final boolean a(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }

    @Override // com.alipay.sdk.m.e.i
    public final Object a(Object obj, Type type) {
        return new Date(((Long) obj).longValue());
    }

    @Override // com.alipay.sdk.m.e.j
    public final Object a(Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }
}
