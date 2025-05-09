package com.huawei.hwcloudjs.e.b;

import com.huawei.hwcloudjs.e.b.d;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class c<T extends d> implements a<T> {

    /* renamed from: a, reason: collision with root package name */
    private final List<b<T>> f6221a = new ArrayList();

    @Override // com.huawei.hwcloudjs.e.b.a
    public void b(b<T> bVar) {
        synchronized (this.f6221a) {
            if (bVar == null) {
                return;
            }
            if (!this.f6221a.contains(bVar)) {
                this.f6221a.add(bVar);
            }
        }
    }

    @Override // com.huawei.hwcloudjs.e.b.a
    public void a(T t) {
        synchronized (this.f6221a) {
            ArrayList arrayList = new ArrayList();
            for (b<T> bVar : this.f6221a) {
                if (!bVar.onReceive(t)) {
                    arrayList.add(bVar);
                }
            }
            if (arrayList.size() > 0) {
                this.f6221a.removeAll(arrayList);
            }
        }
    }

    @Override // com.huawei.hwcloudjs.e.b.a
    public void a(b<T> bVar) {
        synchronized (this.f6221a) {
            this.f6221a.remove(bVar);
        }
    }
}
