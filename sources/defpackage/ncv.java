package defpackage;

import android.view.View;
import com.huawei.skinner.theme.ThemeServiceInterceptor;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class ncv {

    /* renamed from: a, reason: collision with root package name */
    private static ncv f15259a = new ncv();
    private final Map<Class<? extends View>, ThemeServiceInterceptor> b = new HashMap();

    private ncv() {
    }

    public static ncv c() {
        return f15259a;
    }

    public ThemeServiceInterceptor c(Class<? extends View> cls) {
        ThemeServiceInterceptor themeServiceInterceptor = this.b.get(cls);
        if (themeServiceInterceptor != null) {
            return themeServiceInterceptor;
        }
        Class<? extends View> superclass = cls.getSuperclass();
        if (superclass != Object.class) {
            return c(superclass);
        }
        return null;
    }
}
