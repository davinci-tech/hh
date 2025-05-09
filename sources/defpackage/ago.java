package defpackage;

import com.huawei.appgallery.marketinstallerservice.a.c;
import com.huawei.appgallery.marketinstallerservice.b.a.a.a;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class ago {
    private static Map<Class, Object> e;

    public static boolean e(Class cls, a aVar) {
        String str;
        if (cls == null) {
            str = "class is null.";
        } else if (aVar == null) {
            str = "Impl is null.";
        } else {
            if (cls.isAssignableFrom(aVar.getClass())) {
                e.put(cls, aVar);
                return true;
            }
            str = "Impl is not extends right class:" + cls + Constants.LINK + aVar.getClass();
        }
        agr.e("MarketInstallApiRegiste", str);
        return false;
    }

    public static Object d(Class cls) {
        if (cls == null) {
            agr.e("MarketInstallApiRegiste", "apiClass can not be null");
            return null;
        }
        Object obj = e.get(cls);
        if (obj == null || !cls.isAssignableFrom(obj.getClass())) {
            return null;
        }
        return obj;
    }

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        e = concurrentHashMap;
        if (concurrentHashMap.size() <= 0) {
            e(c.class, new agi());
        }
    }
}
