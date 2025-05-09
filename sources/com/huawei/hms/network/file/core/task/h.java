package com.huawei.hms.network.file.core.task;

import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private final i f5633a;
    private List<k> b;
    private ExecutorService c;
    private Future d;
    private volatile boolean e = false;
    private volatile boolean f = false;

    public boolean e() {
        return this.f;
    }

    public boolean d() {
        Future future = this.d;
        if (future != null) {
            return future.isCancelled();
        }
        return false;
    }

    public void c() {
        this.d = this.c.submit(new Runnable() { // from class: com.huawei.hms.network.file.core.task.h$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                h.this.f();
            }
        });
    }

    public void b() {
        this.e = true;
    }

    boolean a(List<k> list) {
        FLogger.i("RequestProcessor", "processTaskFinished all task finished start", new Object[0]);
        this.f = true;
        List<ITaskResult> b = b(list);
        if (this.f5633a.e()) {
            FLogger.w("RequestProcessor", "processTaskFinished exceptionTask not null!", new Object[0]);
            if (!this.f5633a.t) {
                return this.f5633a.a((Throwable) null);
            }
            this.f5633a.q = false;
            return false;
        }
        FLogger.i("RequestProcessor", "1.check results:" + this.f5633a.c().getId() + ",isResuleEmpty:" + Utils.isEmpty(b), new Object[0]);
        for (ITaskResult iTaskResult : b) {
            if (!Constants.a(iTaskResult.getErrorCode())) {
                FLogger.e("RequestProcessor", "task finish failed for " + iTaskResult.getErrorMessage());
                this.f5633a.a(iTaskResult.getException(), b);
                return true;
            }
        }
        return this.f5633a.b(b);
    }

    public void a() {
        Future future = this.d;
        if (future != null) {
            future.cancel(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        boolean a2;
        try {
            if (this.e) {
                FLogger.w("RequestProcessor", "allFuture.thenAcceptAsync executeSession invalid", new Object[0]);
                return;
            }
            synchronized (this.f5633a) {
                a2 = a(this.b);
            }
            if (a2) {
                this.f5633a.g();
            }
        } catch (Throwable th) {
            if (this.e) {
                FLogger.w("RequestProcessor", "allFuture.exceptionally executeSession invalid", new Object[0]);
            }
            FLogger.w("RequestProcessor", "allFuture.exceptionally, exceptionTask", new Object[0]);
            this.f5633a.a(th);
        }
    }

    private List<ITaskResult> b(List<k> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<k> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().u());
        }
        return arrayList;
    }

    public h(i iVar, List<k> list, ExecutorService executorService) {
        this.f5633a = iVar;
        this.b = list;
        this.c = executorService;
    }
}
