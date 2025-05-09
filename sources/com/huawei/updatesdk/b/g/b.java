package com.huawei.updatesdk.b.g;

import android.os.AsyncTask;
import com.huawei.updatesdk.a.b.c.c.d;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final List<AsyncTask> f10842a = new CopyOnWriteArrayList();

    private static void a(com.huawei.updatesdk.a.b.c.b bVar) {
        com.huawei.updatesdk.a.a.c.a.a.a.a("StoreAgent", "executeTask, ActiveCount:" + c.f10843a.getActiveCount() + ", TaskCount:" + c.f10843a.getTaskCount());
        bVar.a(c.f10843a);
    }

    public static void a(AsyncTask asyncTask) {
        if (asyncTask == null) {
            return;
        }
        for (AsyncTask asyncTask2 : f10842a) {
            if (asyncTask2 != null && (asyncTask2.getStatus() == AsyncTask.Status.FINISHED || asyncTask2.isCancelled())) {
                f10842a.remove(asyncTask2);
            }
        }
        f10842a.add(asyncTask);
    }

    public static List<AsyncTask> a() {
        return f10842a;
    }

    public static d a(com.huawei.updatesdk.a.b.c.c.c cVar) {
        return new com.huawei.updatesdk.a.b.c.b(cVar, null).c();
    }

    public static com.huawei.updatesdk.a.b.c.b a(com.huawei.updatesdk.b.b.c cVar, com.huawei.updatesdk.a.b.c.c.a aVar) {
        com.huawei.updatesdk.a.b.c.b bVar = new com.huawei.updatesdk.a.b.c.b(cVar, aVar);
        a(bVar);
        return bVar;
    }
}
