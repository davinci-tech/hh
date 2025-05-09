package com.huawei.hms.kit.awareness.barrier.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.huawei.hms.kit.awareness.barrier.AwarenessBarrier;
import com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes4.dex */
public final class f extends BarrierUpdateRequest implements g {

    /* renamed from: a, reason: collision with root package name */
    public static final int f4880a = 0;
    public static final int b = 1;
    public static final int c = 3;
    private static final String d = "BarrierUpdateRequestImpl";
    private final List<com.huawei.hms.kit.awareness.barrier.internal.a> e;
    private com.huawei.hms.kit.awareness.b.e f;

    @Override // com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.e);
        parcel.writeParcelable(this.f, i);
    }

    public boolean l() {
        Iterator<com.huawei.hms.kit.awareness.barrier.internal.a> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next().b() instanceof com.huawei.hms.kit.awareness.barrier.internal.a.e.a) {
                return true;
            }
        }
        return false;
    }

    public int k() {
        int o;
        Iterator<com.huawei.hms.kit.awareness.barrier.internal.a> it = this.e.iterator();
        int i = 0;
        while (it.hasNext()) {
            AwarenessBarrier b2 = it.next().b();
            if ((b2 instanceof com.huawei.hms.kit.awareness.barrier.internal.a.c) && i < (o = ((com.huawei.hms.kit.awareness.barrier.internal.a.c) b2).o())) {
                i = o;
            }
        }
        return i;
    }

    public Set<Integer> j() {
        final ArraySet arraySet = new ArraySet();
        a(new a() { // from class: com.huawei.hms.kit.awareness.barrier.internal.f.1
            @Override // com.huawei.hms.kit.awareness.barrier.internal.f.a
            protected boolean a(com.huawei.hms.kit.awareness.barrier.internal.a aVar) {
                AwarenessBarrier b2 = aVar.b();
                if (!(b2 instanceof com.huawei.hms.kit.awareness.barrier.internal.a.c)) {
                    return false;
                }
                arraySet.addAll(((com.huawei.hms.kit.awareness.barrier.internal.a.c) b2).n().m());
                return false;
            }
        });
        return arraySet;
    }

    public com.huawei.hms.kit.awareness.barrier.internal.a i() {
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.e)) {
            return null;
        }
        com.huawei.hms.kit.awareness.barrier.internal.a aVar = this.e.get(0);
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a(aVar)) {
            return null;
        }
        return aVar;
    }

    public boolean h() {
        boolean z = false;
        for (com.huawei.hms.kit.awareness.barrier.internal.a aVar : this.e) {
            if (aVar.c() == 1) {
                AwarenessBarrier b2 = aVar.b();
                if (b2 instanceof com.huawei.hms.kit.awareness.barrier.internal.a.c) {
                    com.huawei.hms.kit.awareness.barrier.internal.a.c cVar = (com.huawei.hms.kit.awareness.barrier.internal.a.c) b2;
                    cVar.B();
                    cVar.q();
                    z = true;
                }
            }
        }
        return z;
    }

    public void g() {
        Iterator<com.huawei.hms.kit.awareness.barrier.internal.a> it = this.e.iterator();
        while (it.hasNext()) {
            AwarenessBarrier b2 = it.next().b();
            if (b2 instanceof com.huawei.hms.kit.awareness.barrier.internal.a.c) {
                ((com.huawei.hms.kit.awareness.barrier.internal.a.c) b2).n().j();
            }
        }
    }

    public List<com.huawei.hms.kit.awareness.barrier.internal.a> f() {
        return (List) this.e.stream().filter(new Predicate() { // from class: com.huawei.hms.kit.awareness.barrier.internal.f$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean a2;
                a2 = f.a((a) obj);
                return a2;
            }
        }).collect(Collectors.toList());
    }

    public int e() {
        Iterator<com.huawei.hms.kit.awareness.barrier.internal.a> it = this.e.iterator();
        int i = 0;
        while (it.hasNext()) {
            AwarenessBarrier b2 = it.next().b();
            if (b2 instanceof com.huawei.hms.kit.awareness.barrier.internal.a.c) {
                i += ((com.huawei.hms.kit.awareness.barrier.internal.a.c) b2).p();
            }
        }
        return i;
    }

    public com.huawei.hms.kit.awareness.b.e d() {
        return this.f;
    }

    public void c() {
        this.e.clear();
        this.e.add(com.huawei.hms.kit.awareness.barrier.internal.a.a(null, 3, null, null));
    }

    public boolean b() {
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.e)) {
            com.huawei.hms.kit.awareness.b.a.c.a(d, "mItems is empty", new Object[0]);
            return false;
        }
        Iterator<com.huawei.hms.kit.awareness.barrier.internal.a> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                com.huawei.hms.kit.awareness.b.a.c.a(d, "one item is null", new Object[0]);
                return false;
            }
        }
        return true;
    }

    public boolean a(a aVar) {
        Iterator<com.huawei.hms.kit.awareness.barrier.internal.a> it = this.e.iterator();
        while (it.hasNext()) {
            if (aVar.a(it.next())) {
                return false;
            }
        }
        return true;
    }

    public void a(final String str, AwarenessBarrier awarenessBarrier, PendingIntent pendingIntent) {
        if (str == null || awarenessBarrier == null || pendingIntent == null) {
            com.huawei.hms.kit.awareness.b.a.c.d(d, "addBarrier parameters are invalid", new Object[0]);
        } else {
            this.e.removeIf(new Predicate() { // from class: com.huawei.hms.kit.awareness.barrier.internal.f$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean b2;
                    b2 = f.b(str, (a) obj);
                    return b2;
                }
            });
            this.e.add(com.huawei.hms.kit.awareness.barrier.internal.a.a(str, 1, awarenessBarrier, pendingIntent));
        }
    }

    public void a(final String str) {
        if (str == null) {
            return;
        }
        this.e.removeIf(new Predicate() { // from class: com.huawei.hms.kit.awareness.barrier.internal.f$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean a2;
                a2 = f.a(str, (a) obj);
                return a2;
            }
        });
        this.e.add(com.huawei.hms.kit.awareness.barrier.internal.a.a(str, 0, null, null));
    }

    public void a(com.huawei.hms.kit.awareness.b.e eVar) {
        this.f = eVar;
    }

    public void a(final PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            return;
        }
        this.e.removeIf(new Predicate() { // from class: com.huawei.hms.kit.awareness.barrier.internal.f$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean a2;
                a2 = f.a(pendingIntent, (a) obj);
                return a2;
            }
        });
        this.e.add(com.huawei.hms.kit.awareness.barrier.internal.a.a(null, 0, null, pendingIntent));
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.g
    public int a(Context context) {
        Iterator<com.huawei.hms.kit.awareness.barrier.internal.a> it = this.e.iterator();
        while (it.hasNext()) {
            int a2 = it.next().a(context);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }

    public int a() {
        return this.e.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean b(String str, com.huawei.hms.kit.awareness.barrier.internal.a aVar) {
        return str.equals(aVar.d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(String str, com.huawei.hms.kit.awareness.barrier.internal.a aVar) {
        return str.equals(aVar.d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(com.huawei.hms.kit.awareness.barrier.internal.a aVar) {
        return aVar.c() == 1;
    }

    public static boolean a(Parcelable parcelable) {
        return (parcelable instanceof f) && ((f) parcelable).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(PendingIntent pendingIntent, com.huawei.hms.kit.awareness.barrier.internal.a aVar) {
        return pendingIntent.equals(aVar.e());
    }

    public f(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.e = arrayList;
        parcel.readList(arrayList, com.huawei.hms.kit.awareness.barrier.internal.a.class.getClassLoader());
        this.f = (com.huawei.hms.kit.awareness.b.e) parcel.readParcelable(com.huawei.hms.kit.awareness.b.e.class.getClassLoader());
    }

    public f() {
        this.e = new ArrayList();
    }

    /* loaded from: classes9.dex */
    public static abstract class a {

        /* renamed from: a, reason: collision with root package name */
        int f4882a;

        protected abstract boolean a(com.huawei.hms.kit.awareness.barrier.internal.a aVar);

        public final void a(int i) {
            this.f4882a = i;
        }

        public final int a() {
            return this.f4882a;
        }
    }
}
