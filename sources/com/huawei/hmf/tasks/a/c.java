package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.CancellationToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class c extends CancellationToken {

    /* renamed from: a, reason: collision with root package name */
    public final List<Runnable> f4222a = new ArrayList();
    public final Object b = new Object();
    public boolean c = false;

    @Override // com.huawei.hmf.tasks.CancellationToken
    public CancellationToken register(Runnable runnable) {
        synchronized (this.b) {
            if (this.c) {
                runnable.run();
            } else {
                this.f4222a.add(runnable);
            }
        }
        return this;
    }

    @Override // com.huawei.hmf.tasks.CancellationToken
    public boolean isCancellationRequested() {
        return this.c;
    }

    public final void a() {
        if (this.c) {
            return;
        }
        synchronized (this.b) {
            this.c = true;
            Iterator<Runnable> it = this.f4222a.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }
}
