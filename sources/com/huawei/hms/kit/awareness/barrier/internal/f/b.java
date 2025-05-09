package com.huawei.hms.kit.awareness.barrier.internal.f;

import android.text.TextUtils;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes9.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4883a = "The parameter is empty or null";

    public static void b(Collection<String> collection) {
        a(collection);
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty(it.next())) {
                throw new IllegalArgumentException(f4883a);
            }
        }
    }

    public static void a(String... strArr) {
        a(strArr);
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException(f4883a);
            }
        }
    }

    public static <T> void a(T[] tArr) {
        if (c.a((Object[]) tArr)) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> void a(Collection<T> collection) {
        if (c.a((Collection) collection)) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(String str) {
        if (c.a(str)) {
            throw new IllegalArgumentException(f4883a);
        }
    }

    public static <T> void a(T t) {
        if (t == null) {
            throw new NullPointerException("The parameter is null");
        }
    }

    private b() {
        throw new AssertionError("Uninstantiable");
    }
}
