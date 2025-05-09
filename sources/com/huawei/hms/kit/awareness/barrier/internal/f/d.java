package com.huawei.hms.kit.awareness.barrier.internal.f;

import android.util.ArraySet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes9.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4884a = "CollectionsUtils";

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(Set set, Object obj) {
        return !set.contains(obj);
    }

    public static <E> Set<E> a(Set<E> set, final Set<E> set2) {
        if (set != null && set2 != null) {
            return (Set) set.stream().filter(new Predicate() { // from class: com.huawei.hms.kit.awareness.barrier.internal.f.d$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean a2;
                    a2 = d.a(set2, obj);
                    return a2;
                }
            }).collect(Collectors.toSet());
        }
        com.huawei.hms.kit.awareness.b.a.c.b(f4884a, "s1 or s2 is null.", new Object[0]);
        return new ArraySet();
    }

    public static <E> Set<E> a(Set<E> set) {
        ArraySet arraySet = new ArraySet();
        arraySet.addAll(set);
        return arraySet;
    }
}
