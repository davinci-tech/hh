package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes3.dex */
public class caa {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f577a = false;

    public static void a() {
        synchronized (caa.class) {
            if (f577a) {
                LogUtil.a("WalletConfigInit", "has done");
                return;
            }
            try {
                Class<?> cls = Class.forName("health.compact.a.DeviceConfigInit");
                Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                cls.getMethod("create", new Class[0]).invoke(declaredConstructor.newInstance(new Object[0]), new Object[0]);
                f577a = true;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
                LogUtil.h("WalletConfigInit", "WalletConfigInit create Exception");
            }
        }
    }
}
