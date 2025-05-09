package defpackage;

import com.alipay.sdk.m.e.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.alipay.a;
import org.json.alipay.b;

/* loaded from: classes7.dex */
public final class jh {
    public static List<j> d;

    public static Object c(Object obj) {
        Object a2;
        if (obj == null) {
            return null;
        }
        for (j jVar : d) {
            if (jVar.a(obj.getClass()) && (a2 = jVar.a(obj)) != null) {
                return a2;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }

    public static String b(Object obj) {
        if (obj == null) {
            return null;
        }
        Object c = c(obj);
        if (jk.e(c.getClass())) {
            return b.c(c.toString());
        }
        if (Collection.class.isAssignableFrom(c.getClass())) {
            return new a((Collection) c).toString();
        }
        if (Map.class.isAssignableFrom(c.getClass())) {
            return new b((Map) c).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + c.getClass());
    }

    static {
        ArrayList arrayList = new ArrayList();
        d = arrayList;
        arrayList.add(new jl());
        d.add(new jg());
        d.add(new ji());
        d.add(new jm());
        d.add(new jf());
        d.add(new jc());
        d.add(new jo());
    }
}
