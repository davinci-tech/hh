package com.huawei.hms.kit.awareness.barrier.internal.a;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.AwarenessBarrier;
import com.huawei.hms.kit.awareness.barrier.internal.b.f;
import com.huawei.hms.kit.awareness.barrier.internal.type.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class b extends c {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.b.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }
    };
    private static final String k = "ComplexCondition";
    public static final int p = 200;
    private static final int u = -1;
    private final int l;
    private final List<c> m;
    private int n;
    private int o;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(f fVar) {
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b() {
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int d() {
        return 50;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    final boolean f() {
        return false;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public final com.huawei.hms.kit.awareness.barrier.internal.d.f i() {
        return null;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.m.size());
        Iterator<c> it = this.m.iterator();
        while (it.hasNext()) {
            parcel.writeParcelable(it.next(), i);
        }
        parcel.writeInt(this.l);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int p() {
        return this.n;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int o() {
        Iterator<c> it = this.m.iterator();
        int i = 0;
        while (it.hasNext()) {
            int o = it.next().o();
            if (i < o) {
                i = o;
            }
        }
        return i;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean j() {
        return d.b(this);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    final int h() {
        return this.l;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    final List<c> g() {
        return this.m;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public final int e() {
        return this.o;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean c() {
        if (f()) {
            return false;
        }
        Iterator<c> it = this.m.iterator();
        while (it.hasNext()) {
            if (!it.next().c()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int b(Context context) {
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.m)) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermissionByContext()->list is empty, AWARENESS_REQUEST_ERROR", new Object[0]);
            return 10007;
        }
        Iterator<c> it = this.m.iterator();
        while (it.hasNext()) {
            int b = it.next().b(context);
            if (b != 0) {
                return b;
            }
        }
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.m)) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermission()->list is empty, AWARENESS_REQUEST_ERROR", new Object[0]);
            return 10007;
        }
        Iterator<c> it = this.m.iterator();
        while (it.hasNext()) {
            int a2 = it.next().a(context);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.m)) {
            com.huawei.hms.kit.awareness.b.a.c.d(k, "Invalid barrier", new Object[0]);
            return 2;
        }
        int a2 = d.a(this.m.get(0));
        int i = this.l;
        int i2 = 1;
        if (i == 1) {
            while (a2 != 2 && i2 < this.m.size()) {
                a2 = State.and(a2, d.a(this.m.get(i2)));
                i2++;
            }
            return a2;
        }
        if (i != 2) {
            if (i != 3) {
                return 2;
            }
            return State.not(a2);
        }
        while (a2 != 2 && i2 < this.m.size()) {
            a2 = State.or(a2, d.a(this.m.get(i2)));
            i2++;
        }
        return a2;
    }

    public static AwarenessBarrier a(int i, AwarenessBarrier... awarenessBarrierArr) {
        b bVar = new b(i, awarenessBarrierArr);
        if (bVar.j()) {
            return bVar;
        }
        throw new IllegalArgumentException("Circular reference in complex condition");
    }

    public static AwarenessBarrier a(int i, Collection<AwarenessBarrier> collection) {
        b bVar = new b(i, collection);
        if (bVar.j()) {
            return bVar;
        }
        throw new IllegalArgumentException("Circular reference in complex condition");
    }

    private b(Parcel parcel) {
        super(parcel);
        int readInt;
        this.n = 1;
        this.o = 0;
        this.m = new ArrayList();
        int readInt2 = parcel.readInt();
        if (readInt2 >= 200) {
            readInt = -1;
        } else {
            while (true) {
                readInt2--;
                if (readInt2 < 0) {
                    break;
                }
                c createFromParcel = c.CREATOR.createFromParcel(parcel);
                if (createFromParcel != null) {
                    this.m.add(createFromParcel);
                    this.n += createFromParcel.p();
                    this.o += createFromParcel.e();
                }
            }
            readInt = parcel.readInt();
        }
        this.l = readInt;
    }

    private b(int i, AwarenessBarrier... awarenessBarrierArr) {
        this.n = 1;
        this.o = 0;
        this.l = i;
        this.m = new ArrayList();
        for (AwarenessBarrier awarenessBarrier : awarenessBarrierArr) {
            if (awarenessBarrier instanceof c) {
                c cVar = (c) awarenessBarrier;
                this.m.add(cVar);
                this.n += cVar.p();
                this.o += cVar.e();
            }
        }
    }

    private b(int i, Collection<AwarenessBarrier> collection) {
        this.n = 1;
        this.o = 0;
        this.l = i;
        this.m = new ArrayList();
        for (AwarenessBarrier awarenessBarrier : collection) {
            if (awarenessBarrier instanceof c) {
                c cVar = (c) awarenessBarrier;
                this.m.add(cVar);
                this.n += cVar.p();
            }
        }
    }
}
