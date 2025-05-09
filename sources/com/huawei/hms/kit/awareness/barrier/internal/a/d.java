package com.huawei.hms.kit.awareness.barrier.internal.a;

import com.huawei.hms.kit.awareness.barrier.BarrierStatus;
import com.huawei.hms.kit.awareness.barrier.internal.type.State;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/* loaded from: classes9.dex */
final class d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4850a = "Traversal";

    private static int f(c cVar) {
        if (cVar.f()) {
            return d(cVar);
        }
        int h = cVar.h();
        int i = 1;
        if (h != 1) {
            i = 2;
            if (h == 2) {
                Iterator<c> it = cVar.g().iterator();
                i = 0;
                while (it.hasNext()) {
                    i = State.or(i, f(it.next()));
                }
            } else if (h == 3) {
                i = State.not(f(cVar.g().get(0)));
            }
        } else {
            Iterator<c> it2 = cVar.g().iterator();
            while (it2.hasNext()) {
                i = State.and(i, f(it2.next()));
            }
        }
        if (cVar.n().k()) {
            a(cVar, i);
        }
        return i;
    }

    private static int e(c cVar) {
        cVar.n().a(cVar.h);
        return d(cVar);
    }

    private static int d(c cVar) {
        BarrierStatus m = cVar.m();
        if (m != null) {
            return m.getPresentStatus();
        }
        return 2;
    }

    static int c(c cVar) {
        com.huawei.hms.kit.awareness.b.a.c.b(f4850a, "the current root result: " + f(cVar), new Object[0]);
        return e(cVar);
    }

    static boolean b(b bVar) {
        if (bVar.p() > 200) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4850a, "amount of ComplexCondition is more than MAX_SUB_BARRIER_NUM", new Object[0]);
            return false;
        }
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) bVar.g())) {
            return false;
        }
        Stack stack = new Stack();
        Stack stack2 = new Stack();
        stack.push(bVar);
        while (true) {
            stack2.push(0);
            while (!stack.isEmpty()) {
                c cVar = (c) stack.pop();
                Integer num = (Integer) stack2.pop();
                if (cVar.f()) {
                    if (!cVar.j()) {
                        return false;
                    }
                } else if (num.intValue() < cVar.g().size()) {
                    c cVar2 = cVar.g().get(num.intValue());
                    stack.push(cVar);
                    stack2.push(Integer.valueOf(num.intValue() + 1));
                    if (stack.contains(cVar2)) {
                        return false;
                    }
                    stack.push(cVar2);
                }
            }
            return true;
        }
    }

    static void b(c cVar) {
        cVar.h = com.huawei.hms.kit.awareness.barrier.internal.d.a(cVar.n().c());
        if (cVar.f()) {
            cVar.v();
            return;
        }
        Iterator<c> it = cVar.g().iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    static void a(c cVar, a aVar) {
        cVar.i = aVar;
        if (cVar.f()) {
            aVar.a(cVar);
            return;
        }
        Iterator<c> it = cVar.g().iterator();
        while (it.hasNext()) {
            a(it.next(), aVar);
        }
    }

    static void a(c cVar, int i) {
        BarrierStatus m = cVar.m();
        if (m instanceof com.huawei.hms.kit.awareness.barrier.internal.d) {
            com.huawei.hms.kit.awareness.barrier.internal.d dVar = (com.huawei.hms.kit.awareness.barrier.internal.d) m;
            dVar.a(i);
            dVar.a(System.currentTimeMillis());
        }
    }

    static int a(c cVar) {
        if (cVar == null) {
            return 2;
        }
        return cVar.a();
    }

    d() {
    }
}
