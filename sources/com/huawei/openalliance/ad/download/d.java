package com.huawei.openalliance.ad.download;

import android.text.TextUtils;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.ho;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

/* loaded from: classes5.dex */
public class d<T extends DownloadTask> {

    /* renamed from: a, reason: collision with root package name */
    private BlockingQueue<T> f6805a = new PriorityBlockingQueue();
    private Queue<T> b = new ConcurrentLinkedQueue();
    private Queue<T> c = new ConcurrentLinkedQueue();

    boolean g(T t) {
        if (t == null) {
            return false;
        }
        boolean remove = this.f6805a.remove(t);
        if (this.b.remove(t)) {
            remove = true;
        }
        if (!this.c.contains(t)) {
            return remove;
        }
        t.w();
        return true;
    }

    boolean f(T t) {
        if (t == null) {
            return false;
        }
        boolean a2 = a((d<T>) t);
        if (!ho.a()) {
            return a2;
        }
        ho.a("DownloadQueue", "resumeTask, succ:%s, taskId:%s", Boolean.valueOf(a2), t.n());
        return a2;
    }

    boolean e(T t) {
        if (t == null) {
            return false;
        }
        if (this.f6805a.contains(t)) {
            if (ho.a()) {
                ho.a("DownloadQueue", "pauseTask, from waitingQueue, taskId:%s", t.n());
            }
            this.f6805a.remove(t);
        } else {
            if (!this.c.contains(t)) {
                if (!this.b.contains(t)) {
                    return false;
                }
                if (ho.a()) {
                    ho.a("DownloadQueue", "pauseTask, from idleQueue, taskId:%s", t.n());
                }
                return true;
            }
            if (ho.a()) {
                ho.a("DownloadQueue", "pauseTask, from workingQueue, taskId:%s", t.n());
            }
            t.w();
        }
        b(t);
        return true;
    }

    boolean d(T t) {
        return this.c.remove(t);
    }

    public List<T> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f6805a);
        arrayList.addAll(this.c);
        return arrayList;
    }

    void c(T t) {
        this.c.remove(t);
    }

    public List<T> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.b);
        return arrayList;
    }

    boolean b(T t) {
        if (ho.a()) {
            ho.a("DownloadQueue", "addIdleTask, task:%s", t.toString());
        }
        if (t == null || this.b.contains(t)) {
            return false;
        }
        return this.b.offer(t);
    }

    T b() {
        String str;
        try {
            if (ho.a()) {
                ho.a("DownloadQueue", "takeTask, workingQueue.size:%d, waitingQueue.size:%d, idleQueue.size:%d", Integer.valueOf(this.c.size()), Integer.valueOf(this.f6805a.size()), Integer.valueOf(this.b.size()));
            }
            T take = this.f6805a.take();
            if (!this.c.offer(take)) {
                ho.b("DownloadQueue", "taskTask - workingQueue fail to offer ");
            }
            if (ho.a()) {
                ho.a("DownloadQueue", "takeTask, task:%s", take.toString());
            }
            return take;
        } catch (InterruptedException unused) {
            str = "takeTask InterruptedException";
            ho.d("DownloadQueue", str);
            return null;
        } catch (Exception unused2) {
            str = "takeTask Exception";
            ho.d("DownloadQueue", str);
            return null;
        }
    }

    boolean a(T t) {
        if (t == null) {
            return false;
        }
        boolean h = h(t);
        if (ho.a()) {
            ho.a("DownloadQueue", "addTask, succ:%s, taskId:%s, priority:%d, seqNum:%d", Boolean.valueOf(h), t.n(), Integer.valueOf(t.k()), Long.valueOf(t.m()));
        }
        return h;
    }

    T a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (ho.a()) {
            ho.a("DownloadQueue", "findTask, workingQueue.size:%d, waitingQueue.size:%d, idleQueue.size:%d", Integer.valueOf(this.c.size()), Integer.valueOf(this.f6805a.size()), Integer.valueOf(this.b.size()));
        }
        T a2 = a(this.c, str);
        if (a2 != null) {
            return a2;
        }
        T a3 = a(this.f6805a, str);
        return a3 == null ? a(this.b, str) : a3;
    }

    int a() {
        return this.f6805a.size();
    }

    private boolean h(T t) {
        if (t == null || this.c.contains(t)) {
            return false;
        }
        if (this.f6805a.contains(t)) {
            return true;
        }
        boolean offer = this.f6805a.offer(t);
        if (offer) {
            this.b.remove(t);
        }
        return offer;
    }

    private T a(Queue<T> queue, String str) {
        if (ho.a()) {
            ho.a("DownloadQueue", "findTaskFromQueue, taskId:%s", str);
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (T t : queue) {
            if (str.equals(t.n())) {
                return t;
            }
        }
        return null;
    }
}
