package com.huawei.hwcloudjs.f;

import com.huawei.hwcloudjs.annotation.JSMethod;

/* loaded from: classes9.dex */
public class e {
    public static boolean c(com.huawei.hwcloudjs.core.a.a aVar) {
        return !"".equals(a(aVar));
    }

    public static boolean b(com.huawei.hwcloudjs.core.a.a aVar) {
        return ((JSMethod) aVar.a().getAnnotation(JSMethod.class)).isOpen();
    }

    public static String a(com.huawei.hwcloudjs.core.a.a aVar) {
        return ((JSMethod) aVar.a().getAnnotation(JSMethod.class)).permission();
    }
}
