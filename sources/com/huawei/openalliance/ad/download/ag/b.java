package com.huawei.openalliance.ad.download.ag;

import android.text.TextUtils;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.ho;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes5.dex */
public class b<T extends DownloadTask> {

    /* renamed from: a, reason: collision with root package name */
    private Queue<T> f6716a = new ConcurrentLinkedQueue();

    public boolean b(T t) {
        if (t == null || !this.f6716a.contains(t)) {
            return false;
        }
        this.f6716a.remove(t);
        return true;
    }

    public void b() {
        this.f6716a.clear();
    }

    public void a(T t) {
        if (t == null || this.f6716a.contains(t)) {
            return;
        }
        this.f6716a.offer(t);
    }

    public Queue<T> a() {
        return this.f6716a;
    }

    public T a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (ho.a()) {
            ho.a("AdapterDownloadQueue", "findTask, workingQueue.size:%d", Integer.valueOf(this.f6716a.size()));
        }
        return a(this.f6716a, str);
    }

    private T a(Queue<T> queue, String str) {
        if (ho.a()) {
            ho.a("AdapterDownloadQueue", "findTaskFromQueue, taskId:%s", str);
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
