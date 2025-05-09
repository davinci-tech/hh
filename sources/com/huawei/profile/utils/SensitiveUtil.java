package com.huawei.profile.utils;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes6.dex */
public final class SensitiveUtil {
    private static final Set<String> SENSITIVE_EXCEPTION_SET;

    static {
        HashSet hashSet = new HashSet();
        SENSITIVE_EXCEPTION_SET = hashSet;
        hashSet.add("FileNotFoundException");
        hashSet.add("JarException");
        hashSet.add("MissingResourceException");
        hashSet.add("NotOwnerException");
        hashSet.add("ConcurrentModificationException");
        hashSet.add("InsufficientResourcesException");
        hashSet.add("BindException");
        hashSet.add("OutOfMemoryError");
        hashSet.add("SQLException");
    }

    private SensitiveUtil() {
    }

    public static String getMessage(Throwable th) {
        return (th == null || th.getClass() == null || SENSITIVE_EXCEPTION_SET.contains(th.getClass().getSimpleName())) ? "" : th.getMessage();
    }

    public static String getSimpleName(Throwable th) {
        if (th != null && th.getClass() != null) {
            String simpleName = th.getClass().getSimpleName();
            if (!SENSITIVE_EXCEPTION_SET.contains(simpleName)) {
                return simpleName;
            }
        }
        return "";
    }
}
