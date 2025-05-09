package defpackage;

import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class ttw {
    private static volatile ttw e;

    private ttw() throws ttn {
        d();
    }

    private void d() throws ttn {
        try {
            Method method = Class.forName("com.huawei.security.keystore.HwUniversalKeyStoreProvider").getMethod(JsbMapKeyNames.H5_TEXT_DOWNLOAD_INSTALL, new Class[0]);
            method.setAccessible(true);
            method.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            throw new ttn("install HwKeyStore fail:" + e2.getMessage());
        }
    }

    public static ttw a() throws ttn {
        if (e == null) {
            synchronized (ttw.class) {
                if (e == null) {
                    e = new ttw();
                }
            }
        }
        return e;
    }
}
