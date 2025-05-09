package com.huawei.openalliance.ad.download.ag;

import android.content.Context;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.ho;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class a<T extends DownloadTask> {

    /* renamed from: a, reason: collision with root package name */
    protected Context f6715a;
    protected com.huawei.openalliance.ad.download.a<T> b;
    protected b<T> c;

    public boolean c(T t) {
        if (t == null) {
            return false;
        }
        boolean b = this.c.b(t);
        ho.b("AdapterDownloadManager", "removeTask, succ:" + b);
        if (b) {
            a(t, true);
        }
        return true;
    }

    public void b(T t) {
        if (t == null) {
            return;
        }
        ho.b("AdapterDownloadManager", "deleteTask, succ:" + this.c.b(t));
    }

    public void b() {
        ho.b("AdapterDownloadManager", "cancelAllDownload");
        Iterator<T> it = this.c.a().iterator();
        while (it.hasNext()) {
            a(it.next(), true);
        }
        this.c.b();
    }

    public void a(com.huawei.openalliance.ad.download.a<T> aVar) {
        this.b = aVar;
    }

    protected void a(T t, boolean z) {
        if (t == null) {
            return;
        }
        if (ho.a()) {
            ho.a("AdapterDownloadManager", "onDownloadDeleted, taskId:%s", t.n());
        }
        com.huawei.openalliance.ad.download.a<T> aVar = this.b;
        if (aVar != null) {
            aVar.a(t, z);
        }
    }

    public void a(T t) {
        this.c.a((b<T>) t);
        if (ho.a()) {
            ho.a("AdapterDownloadManager", "addTask, task:%s, priority:%d", t.n(), Integer.valueOf(t.k()));
        }
    }

    public void a() {
        if (this.c == null) {
            this.c = new b<>();
        }
    }

    protected T a(String str) {
        return this.c.a(str);
    }

    public a(Context context) {
        this.f6715a = context.getApplicationContext();
    }
}
