package defpackage;

import com.alipay.sdk.m.e.i;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.alipay.a;
import org.json.alipay.b;

/* loaded from: classes7.dex */
public final class jj {
    public static List<i> b;

    public static final Object b(String str, Type type) {
        Object bVar;
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("[") && trim.endsWith("]")) {
            bVar = new a(trim);
        } else {
            if (!trim.startsWith("{") || !trim.endsWith("}")) {
                return a(trim, type);
            }
            bVar = new b(trim);
        }
        return a(bVar, type);
    }

    public static final <T> T a(Object obj, Type type) {
        T t;
        for (i iVar : b) {
            if (iVar.a(jk.a(type)) && (t = (T) iVar.a(obj, type)) != null) {
                return t;
            }
        }
        return null;
    }

    static {
        ArrayList arrayList = new ArrayList();
        b = arrayList;
        arrayList.add(new jl());
        b.add(new jg());
        b.add(new ji());
        b.add(new jm());
        b.add(new jn());
        b.add(new jf());
        b.add(new jc());
        b.add(new jo());
    }
}
