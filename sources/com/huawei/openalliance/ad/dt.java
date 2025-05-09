package com.huawei.openalliance.ad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.du;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public class dt extends com.huawei.openalliance.ad.download.b<du> {
    private static dt f;
    private static final byte[] g = new byte[0];
    private ds h;
    private dk i;
    private a j;
    private BroadcastReceiver k;

    public void j() {
        List<du> c = this.e.c();
        if (ho.a()) {
            ho.a("VideoDownloadManager", "resumeAllTask, task.size: %d", Integer.valueOf(c.size()));
        }
        if (c.size() <= 0) {
            return;
        }
        a(c);
    }

    public void i() {
        a(DownloadTask.c.HW_VIDEO);
    }

    public void b(DownloadTask.c cVar) {
        List<du> c = this.e.c();
        if (ho.a()) {
            ho.a("VideoDownloadManager", "resumeAllTask, task.size: %d", Integer.valueOf(c.size()));
        }
        if (c.size() <= 0) {
            return;
        }
        Collections.sort(c);
        for (du duVar : c) {
            if (duVar.p() == cVar) {
                a((dt) duVar, false);
            }
        }
    }

    public String b(String str) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = (String) com.huawei.openalliance.ad.utils.dc.b(new Callable<String>() { // from class: com.huawei.openalliance.ad.dt.2
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String call() {
                    return dt.c(dt.this.f6803a);
                }
            });
        }
        return this.b + File.separator + "tmp" + File.separator + str + ".tmp";
    }

    public boolean a(du duVar, boolean z) {
        return a(duVar, false, z, true);
    }

    @Override // com.huawei.openalliance.ad.download.b
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean c(du duVar) {
        if (duVar == null) {
            ho.c("VideoDownloadManager", "cannot add task, task is null");
            return false;
        }
        if (ho.a()) {
            ho.a("VideoDownloadManager", "addTask, taskid: %s", com.huawei.openalliance.ad.utils.dl.a(duVar.n()));
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.dt.3
            @Override // java.lang.Runnable
            public void run() {
                dt.this.k();
            }
        });
        return super.c((dt) duVar);
    }

    void a(DownloadTask.c cVar) {
        List d = this.e.d();
        if (ho.a()) {
            ho.a("VideoDownloadManager", "pauseAllTask.begin, task.size: %d", Integer.valueOf(d.size()));
        }
        Iterator it = d.iterator();
        while (it.hasNext()) {
            a((dt) it.next(), cVar);
        }
        if (ho.a()) {
            ho.a("VideoDownloadManager", "pauseAllTask.end, task.size: %d", Integer.valueOf(d.size()));
        }
    }

    public du a(dr drVar) {
        if (TextUtils.isEmpty(drVar.a())) {
            ho.c("VideoDownloadManager", "downloadVideo - empty video url");
            return null;
        }
        a(drVar.e());
        du a2 = a(com.huawei.openalliance.ad.utils.cu.a(drVar.a()));
        du duVar = a2 instanceof du ? a2 : null;
        if (duVar == null) {
            duVar = b(drVar.a(), drVar.b(), drVar.c(), drVar.d(), drVar.h(), drVar.i());
            if (duVar == null) {
                return null;
            }
            b(drVar, duVar);
            if (duVar.l() >= 100) {
                eu.a(this.f6803a).a(a(drVar, duVar), "normal");
                Integer m = drVar.m();
                if (m == null) {
                    m = Integer.valueOf(dj.a(duVar.G()));
                }
                this.i.a(duVar.d(), m.intValue());
                h(duVar);
            } else {
                c(duVar);
            }
        } else {
            ho.b("VideoDownloadManager", "downloadVideo - task %s is already in queue, resume it", com.huawei.openalliance.ad.utils.dl.a(a2.n()));
            b(drVar, duVar);
            a((dt) duVar, false);
        }
        duVar.a(drVar.g());
        duVar.e(drVar.l());
        return duVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        String str;
        try {
            String str2 = this.b + File.separator + "tmp" + File.separator;
            String[] list = new File(str2).list();
            if (list != null && list.length > 0) {
                a(str2, list);
            }
        } catch (IllegalStateException unused) {
            str = "deleteTimeoutFile IllegalStateException";
            ho.c("VideoDownloadManager", str);
        } catch (Exception unused2) {
            str = "deleteTimeoutFile exception";
            ho.c("VideoDownloadManager", str);
        }
    }

    public static dt h() {
        dt dtVar;
        synchronized (g) {
            dtVar = f;
            if (dtVar == null) {
                throw new com.huawei.openalliance.ad.exception.b("VideoDownloadManager inst is not initialize!");
            }
        }
        return dtVar;
    }

    private static String c(String str) {
        int indexOf = str.indexOf(".tmp");
        if (indexOf > 0) {
            return str.substring(0, indexOf);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(Context context) {
        File cacheDir;
        if (context == null || (cacheDir = context.getCacheDir()) == null) {
            return null;
        }
        return com.huawei.openalliance.ad.utils.ae.h(cacheDir) + File.separator + Constants.PPS_ROOT_PATH + File.separator + "placement";
    }

    private void b(dr drVar, du duVar) {
        duVar.a(drVar.f());
        duVar.d(drVar.j());
        duVar.a(a(drVar, duVar));
    }

    private du b(String str, int i, boolean z, String str2, String str3, String str4) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        du a2 = a(str, i, z, str2, str3, str4);
        String c = this.i.c(a2.d());
        File file2 = c != null ? new File(c) : null;
        if (file2 == null || !file2.exists()) {
            File file3 = new File(a2.e());
            if (!file3.exists()) {
                return a2;
            }
            file = file3;
        } else {
            file = file2;
        }
        return a(a2, i, z, str2, file);
    }

    private void a(List<du> list) {
        Collections.sort(list);
        for (du duVar : list) {
            DownloadTask.c p = duVar.p();
            if (p == DownloadTask.c.NETWORK_CHANGED || p == DownloadTask.c.HW_VIDEO) {
                a((dt) duVar, false);
            }
        }
    }

    private void a(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str2.endsWith(".tmp")) {
                File file = new File(str + str2);
                if (!file.isDirectory() && System.currentTimeMillis() - file.lastModified() > Constants.ANALYSIS_EVENT_KEEP_TIME) {
                    ho.b("VideoDownloadManager", "remove timeout file");
                    du a2 = a(c(str2));
                    if (a2 == null || !(a2 instanceof du)) {
                        com.huawei.openalliance.ad.utils.ae.e(file);
                    } else {
                        a(a2, true);
                    }
                }
            }
        }
    }

    public static void a(Context context) {
        synchronized (g) {
            if (f == null) {
                f = new dt(context);
            }
        }
    }

    public static du a(String str, int i, boolean z, String str2, String str3, String str4) {
        du a2 = new du.a().a(true).a(str).a(i).b(str2).c(str3).d(str4).a();
        a2.d(z);
        return a2;
    }

    private du a(du duVar, int i, boolean z, String str, File file) {
        long length = file.length();
        duVar.b(length);
        long j = i;
        if (length == j || i == 0) {
            if (!z || com.huawei.openalliance.ad.utils.ae.a(str, file)) {
                duVar.b(100);
                duVar.a(com.huawei.openalliance.ad.download.e.DOWNLOADED);
            } else {
                duVar.b(0L);
                duVar.b(0);
                com.huawei.openalliance.ad.utils.ae.e(file);
            }
        } else if (length < j) {
            duVar.b((int) ((length * 100) / j));
        } else {
            com.huawei.openalliance.ad.utils.ae.e(file);
            duVar.b(0);
            duVar.b(0L);
        }
        return duVar;
    }

    class a {
        private final Context b;
        private ConnectivityManager.NetworkCallback c = new ConnectivityManager.NetworkCallback() { // from class: com.huawei.openalliance.ad.dt.a.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                super.onLost(network);
                final Context applicationContext = a.this.b.getApplicationContext();
                if (ho.a()) {
                    ho.a("VideoDownloadManager", "net onLost, active netType: %s, [0:UNKNOWN, 1:ETHERNET, 2:WIFI, 4/5/6/7:2G/3G/4G/5G]", Integer.valueOf(com.huawei.openalliance.ad.utils.bv.d(applicationContext)));
                }
                if (fh.b(applicationContext).aL()) {
                    com.huawei.openalliance.ad.utils.m.g(new Runnable() { // from class: com.huawei.openalliance.ad.dt.a.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (com.huawei.openalliance.ad.utils.bv.e(applicationContext) && com.huawei.openalliance.ad.utils.bv.c(applicationContext)) {
                                dt.this.j();
                            } else {
                                if (com.huawei.openalliance.ad.utils.bv.e(applicationContext) && com.huawei.openalliance.ad.utils.bv.c(applicationContext)) {
                                    return;
                                }
                                dt.this.a(DownloadTask.c.NETWORK_CHANGED);
                            }
                        }
                    });
                } else {
                    ho.b("VideoDownloadManager", "user info is not enabled");
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                super.onAvailable(network);
                final Context applicationContext = a.this.b.getApplicationContext();
                if (ho.a()) {
                    ho.a("VideoDownloadManager", "net onAvailable, active netType: %s, [0:UNKNOWN, 1:ETHERNET, 2:WIFI, 4/5/6/7:2G/3G/4G/5G]", Integer.valueOf(com.huawei.openalliance.ad.utils.bv.d(applicationContext)));
                }
                if (fh.b(applicationContext).aL()) {
                    com.huawei.openalliance.ad.utils.m.g(new Runnable() { // from class: com.huawei.openalliance.ad.dt.a.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (com.huawei.openalliance.ad.utils.bv.e(applicationContext) && com.huawei.openalliance.ad.utils.bv.c(applicationContext)) {
                                dt.this.j();
                            } else {
                                if (com.huawei.openalliance.ad.utils.bv.e(applicationContext) && com.huawei.openalliance.ad.utils.bv.c(applicationContext)) {
                                    return;
                                }
                                dt.this.a(DownloadTask.c.NETWORK_CHANGED);
                            }
                        }
                    });
                } else {
                    ho.b("VideoDownloadManager", "user info is not enabled");
                }
            }
        };

        public void a() {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) this.b.getSystemService("connectivity");
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addTransportType(0).addTransportType(3).addTransportType(1);
                connectivityManager.registerNetworkCallback(builder.build(), this.c);
            } catch (Throwable unused) {
                ho.c("VideoDownloadManager", "register all network callback exception.");
            }
        }

        public a(Context context) {
            this.b = context;
        }
    }

    private ContentResource a(dr drVar, du duVar) {
        if (duVar == null || TextUtils.isEmpty(duVar.B())) {
            return null;
        }
        ContentResource contentResource = new ContentResource();
        contentResource.a(duVar.G());
        Integer m = drVar.m();
        if (m == null) {
            m = Integer.valueOf(dj.a(duVar.G()));
        }
        contentResource.b(m.intValue());
        contentResource.c(duVar.B());
        contentResource.b(duVar.C());
        contentResource.a(duVar.n());
        contentResource.c(!drVar.k() ? 1 : 0);
        contentResource.d(drVar.k() ? 3 : 2);
        return contentResource;
    }

    private dt(final Context context) {
        super(context);
        String str;
        this.k = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.dt.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (ho.a()) {
                    ho.a("VideoDownloadManager", "networkReceiver.onReceive, action: %s", intent.getAction());
                }
                final Context applicationContext = context2.getApplicationContext();
                if (fh.b(applicationContext).aL()) {
                    com.huawei.openalliance.ad.utils.m.g(new Runnable() { // from class: com.huawei.openalliance.ad.dt.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!com.huawei.openalliance.ad.utils.bv.e(applicationContext) || !com.huawei.openalliance.ad.utils.bv.c(applicationContext)) {
                                dt.this.a(DownloadTask.c.NETWORK_CHANGED);
                            } else if (com.huawei.openalliance.ad.utils.bv.c(applicationContext)) {
                                dt.this.b(DownloadTask.c.NETWORK_CHANGED);
                            }
                        }
                    });
                } else {
                    ho.b("VideoDownloadManager", "user info is not enabled");
                }
            }
        };
        try {
            super.a();
            ds dsVar = new ds(context);
            this.h = dsVar;
            super.a(dsVar);
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.dt.1
                @Override // java.lang.Runnable
                public void run() {
                    dt.this.b = dt.c(context);
                }
            });
            a aVar = new a(this.f6803a);
            this.j = aVar;
            aVar.a();
            this.i = dh.a(context, "normal");
        } catch (IllegalStateException unused) {
            str = "initialize IllegalStateException";
            ho.c("VideoDownloadManager", str);
        } catch (Exception unused2) {
            str = "initialize exception";
            ho.c("VideoDownloadManager", str);
        }
    }
}
