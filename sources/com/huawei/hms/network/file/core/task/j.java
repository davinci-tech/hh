package com.huawei.hms.network.file.core.task;

import com.huawei.hms.network.file.api.Progress;
import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.task.k;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class j<R extends Request, T extends k> implements d<R, T> {
    c e;
    public volatile long f;

    /* renamed from: a, reason: collision with root package name */
    volatile e.a f5635a = e.a.INIT;
    public volatile long b = 0;
    protected volatile long c = 0;
    volatile long d = 0;
    volatile long g = 0;
    volatile long h = 0;

    public void f(R r) {
        FLogger.i("RequestStatus", "onSuccess:" + r, new Object[0]);
        this.f5635a = e.a.INVALID;
        this.e.a((c) r, this.f5635a);
    }

    public void e(R r) {
        this.f5635a = e.a.PROCESS;
    }

    public void d(R r) {
        this.f5635a = e.a.PROCESS;
    }

    public void c(R r) {
        FLogger.i("RequestStatus", "onPaused:" + r, new Object[0]);
        this.g += this.d > 0 ? System.currentTimeMillis() - this.d : 0L;
        this.d = 0L;
        this.f5635a = e.a.PAUSE;
        this.e.a((c) r, this.f5635a);
    }

    public e.a c() {
        return this.f5635a;
    }

    public long c(List<T> list) {
        long j = 0;
        if (Utils.isEmpty(list)) {
            return 0L;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            j += it.next().c();
        }
        FLogger.d("RequestStatus", "getCurrentSize:" + j, new Object[0]);
        return j;
    }

    public void b(R r) {
        FLogger.i("RequestStatus", "onCanceled:" + r, new Object[0]);
        this.f5635a = e.a.CANCEL;
        this.e.a((c) r, this.f5635a);
    }

    @Override // com.huawei.hms.network.file.core.task.d
    public long b(List<T> list) {
        long j = 0;
        if (!Utils.isEmpty(list)) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                j += it.next().c();
            }
        }
        return j;
    }

    @Override // com.huawei.hms.network.file.core.task.d
    public long b() {
        return this.d > 0 ? (this.h - this.d) + this.g : this.g;
    }

    public void a(R r, List<T> list) {
        FLogger.i("RequestStatus", "onTaskStart:" + list, new Object[0]);
        if (this.d <= 0) {
            this.d = System.currentTimeMillis();
        }
        if (this.c <= 0) {
            this.c = System.currentTimeMillis();
        }
        this.f5635a = e.a.PROCESS;
        this.e.a((c) r, this.f5635a);
    }

    public void a(R r, FileManagerException fileManagerException) {
        FLogger.i("RequestStatus", "onException:" + r, new Object[0]);
        this.f5635a = e.a.INVALID;
    }

    public void a(R r) {
        FLogger.i("RequestStatus", "onAllTaskFinished:" + r, new Object[0]);
        this.h = System.currentTimeMillis();
    }

    public void a(long j) {
        this.f = j;
    }

    public Progress a(R r, String str, List<T> list) {
        long j;
        long j2;
        if (Utils.isEmpty(list)) {
            return null;
        }
        long j3 = 0;
        long j4 = 0;
        for (T t : list) {
            j4 += t.c();
            j3 += t.a();
        }
        FLogger.v("RequestStatus", "onProgress currentTotalFinished:" + j4 + ",totalFileSize:" + j3);
        long currentTimeMillis = System.currentTimeMillis() - this.c;
        if (currentTimeMillis > 0) {
            j2 = (long) (((j4 - this.b) / currentTimeMillis) * 1000.0d);
            j = 0;
        } else {
            j = 0;
            j2 = 0;
        }
        if (j2 < j || currentTimeMillis == 0) {
            FLogger.w("RequestStatus", "onProgress wait to notify -> timeSpan:" + currentTimeMillis + ",lastReportSize:" + this.b + ",currentTotalFinished:" + j4, new Object[0]);
            return null;
        }
        this.c = System.currentTimeMillis();
        this.b = j4;
        int i = (int) ((j4 / j3) * 100.0d);
        if (i > 100) {
            FLogger.w("RequestStatus", "onProgress for error percent, currentTotalFinished:" + j4 + ",totalFileSize:" + j3, new Object[0]);
            i = 99;
        }
        return new Progress(r, str, i, j3, j4, j2);
    }

    @Override // com.huawei.hms.network.file.core.task.d
    public long a(List<T> list) {
        long b = b(list);
        long a2 = a();
        if (a2 > 0) {
            return (long) ((b / a2) * 1000.0d);
        }
        return 0L;
    }

    @Override // com.huawei.hms.network.file.core.task.d
    public long a() {
        FLogger.i("RequestStatus", "currentTime:" + System.currentTimeMillis() + "; taskStartTime:" + this.d + "; lastUsedTime:" + this.g, new Object[0]);
        return this.d > 0 ? (System.currentTimeMillis() - this.d) + this.g : this.g;
    }

    public j(c cVar) {
        this.e = cVar;
    }
}
