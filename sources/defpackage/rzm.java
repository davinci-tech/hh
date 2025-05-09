package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.BaseView;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* loaded from: classes7.dex */
public class rzm {
    public static <T extends BasePresenter> T b(Class<T> cls, BaseView baseView) {
        Constructor<T> constructor;
        T t = null;
        try {
            constructor = cls.getConstructor(new Class[0]);
        } catch (NoSuchMethodException unused) {
            LogUtil.b("PowerUtil", "NoSuchMethodException");
            constructor = null;
        }
        if (constructor == null) {
            return null;
        }
        try {
            T t2 = (T) ((Constructor) Objects.requireNonNull(constructor)).newInstance(new Object[0]);
            try {
                t2.attachView(baseView);
                return t2;
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused2) {
                t = t2;
                LogUtil.b("PowerUtil", "IllegalAccessException or InstantiationException or InvocationTargetException");
                return t;
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused3) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0020 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T extends com.huawei.ui.main.stories.template.BaseComponent> T d(java.lang.Class<T> r5, android.content.Context r6) {
        /*
            java.lang.String r0 = "PowerUtil"
            r1 = 0
            if (r5 == 0) goto L1d
            if (r6 == 0) goto L1d
            r2 = 1
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L14
            java.lang.Class<android.content.Context> r3 = android.content.Context.class
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.NoSuchMethodException -> L14
            java.lang.reflect.Constructor r5 = r5.getConstructor(r2)     // Catch: java.lang.NoSuchMethodException -> L14
            goto L1e
        L14:
            java.lang.String r5 = "ClassNotFoundException or NoSuchMethodException:"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)
        L1d:
            r5 = r1
        L1e:
            if (r5 == 0) goto L35
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch: java.lang.Throwable -> L2c
            java.lang.Object r5 = r5.newInstance(r6)     // Catch: java.lang.Throwable -> L2c
            com.huawei.ui.main.stories.template.BaseComponent r5 = (com.huawei.ui.main.stories.template.BaseComponent) r5     // Catch: java.lang.Throwable -> L2c
            r1 = r5
            goto L35
        L2c:
            java.lang.String r5 = "IllegalAccessException or InstantiationException or InvocationTargetException"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)
        L35:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rzm.d(java.lang.Class, android.content.Context):com.huawei.ui.main.stories.template.BaseComponent");
    }
}
