package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.location.LocationRequestCompat;
import com.huawei.android.bundlecore.download.DownloadCallback;
import com.huawei.android.bundlecore.download.DownloadRequest;
import com.huawei.android.bundlecore.download.Downloader;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.NetworkUtil;
import defpackage.yu;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes8.dex */
final class yq extends ModuleInstallSupervisor {

    /* renamed from: a, reason: collision with root package name */
    private final yu f17762a;
    private final Context b;
    private final long c;
    private final boolean d;
    private final Set<String> e;
    private final yp h;
    private final Class<?> i;
    private final Downloader j;

    yq(Context context, Downloader downloader, Class<? extends Activity> cls) {
        this.b = context;
        this.h = new yp(context);
        this.j = downloader;
        long downloadSizeThresholdWhenUsingMobileData = downloader.getDownloadSizeThresholdWhenUsingMobileData();
        this.c = downloadSizeThresholdWhenUsingMobileData < 0 ? LocationRequestCompat.PASSIVE_INTERVAL : downloadSizeThresholdWhenUsingMobileData;
        this.e = yb.d(context);
        this.i = cls;
        boolean k = AppBundleBuildConfig.k();
        this.d = k;
        this.f17762a = new yu(context, k);
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public void initialize(Context context) {
        NetworkUtil.xJ_(null);
        postTask(context, new yn(context, yn.d(context), yn.d("Bundle_SupervisorImpl", context, this.f17762a, true)));
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public void startInstall(List<Bundle> list, ModuleInstallSupervisor.Callback callback) {
        List<String> extractBundleModuleNames = extractBundleModuleNames(list);
        int j = j(extractBundleModuleNames);
        if (j != 0) {
            callback.onError(bundleErrorCode(j));
            return;
        }
        List<yi> d = d(extractBundleModuleNames);
        if (!b(d) && !NetworkUtil.j()) {
            callback.onError(bundleErrorCode(-6));
        } else {
            b(extractBundleModuleNames, d, callback);
        }
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public void deferredInstall(List<Bundle> list, ModuleInstallSupervisor.Callback callback) {
        List<String> extractBundleModuleNames = extractBundleModuleNames(list);
        int j = j(extractBundleModuleNames);
        if (j != 0) {
            callback.onError(bundleErrorCode(j));
        } else if (c().isEmpty()) {
            e(d(extractBundleModuleNames), callback);
        } else if (c().containsAll(extractBundleModuleNames)) {
            callback.onDeferredInstall(null);
        }
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public void deferredUninstall(List<Bundle> list, ModuleInstallSupervisor.Callback callback) {
        if (!c().isEmpty()) {
            callback.onError(bundleErrorCode(-98));
            return;
        }
        int d = d();
        if (d != 0) {
            callback.onError(bundleErrorCode(d));
            return;
        }
        List<String> extractBundleModuleNames = extractBundleModuleNames(list);
        if (g(extractBundleModuleNames)) {
            callback.onError(bundleErrorCode(-3));
        } else {
            b(extractBundleModuleNames, callback);
        }
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public void cancelInstall(int i, ModuleInstallSupervisor.Callback callback) {
        LogUtil.c("Bundle_SupervisorImpl", "start to cancel task id ", Integer.valueOf(i), " installation");
        yt a2 = this.h.a(i);
        if (a2 == null) {
            LogUtil.c("Bundle_SupervisorImpl", "Task id is not found!");
            callback.onError(bundleErrorCode(-4));
        } else {
            if (a2.b() == 1 || a2.b() == 2) {
                boolean cancelDownloadSync = this.j.cancelDownloadSync(i);
                LogUtil.d("Bundle_SupervisorImpl", "result of cancel request : ", Boolean.valueOf(cancelDownloadSync));
                if (cancelDownloadSync) {
                    callback.onCancelInstall(i, null);
                    return;
                } else {
                    callback.onError(bundleErrorCode(-3));
                    return;
                }
            }
            callback.onError(bundleErrorCode(-3));
        }
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public void getSessionState(int i, ModuleInstallSupervisor.Callback callback) {
        yt a2 = this.h.a(i);
        if (a2 == null) {
            callback.onError(bundleErrorCode(-4));
        } else {
            callback.onGetSession(i, yt.er_(a2));
        }
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public void getSessionStates(ModuleInstallSupervisor.Callback callback) {
        List<yt> a2 = this.h.a();
        if (a2.isEmpty()) {
            callback.onGetSessionStates(Collections.EMPTY_LIST);
            return;
        }
        ArrayList arrayList = new ArrayList(a2.size());
        Iterator<yt> it = a2.iterator();
        while (it.hasNext()) {
            arrayList.add(yt.er_(it.next()));
        }
        callback.onGetSessionStates(arrayList);
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public boolean continueInstallWithUserConfirmation(int i) {
        yt a2 = this.h.a(i);
        if (a2 == null) {
            return false;
        }
        c cVar = new c(this.f17762a, i, this.h, a2.b);
        this.h.a(i, 1);
        this.h.c(a2);
        this.j.startDownload(a2.d(), a2.a(), cVar);
        return true;
    }

    @Override // com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor
    public boolean cancelInstallWithoutUserConfirmation(int i) {
        yt a2 = this.h.a(i);
        if (a2 == null) {
            return false;
        }
        this.h.a(i, 7);
        this.h.c(a2);
        return true;
    }

    private boolean b(List<yi> list) {
        Iterator<yi> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().k()) {
                return false;
            }
        }
        return true;
    }

    private int j(List<String> list) {
        if (!c().isEmpty()) {
            return !c().containsAll(list) ? -3 : 0;
        }
        int d = d();
        return d == 0 ? a(list) : d;
    }

    private Set<String> c() {
        return this.e;
    }

    private int a(List<String> list) {
        if (g(list)) {
            return -3;
        }
        return !f(list) ? -2 : 0;
    }

    private int d() {
        yg a2 = yg.a();
        if (a2.a(this.b).isEmpty()) {
            LogUtil.a("Bundle_SupervisorImpl", "Failed to parse json file of module info!");
            return -100;
        }
        String d = a2.d(this.b);
        String l = AppBundleBuildConfig.l();
        if (TextUtils.isEmpty(d) || !d.equals(l)) {
            LogUtil.a("Bundle_SupervisorImpl", "Failed to match base app version-name excepted base app mVersion ", l, " but ", d);
            return -100;
        }
        String b = a2.b(this.b);
        String a3 = AppBundleBuildConfig.a();
        if (!TextUtils.isEmpty(b) && b.equals(a3)) {
            return 0;
        }
        LogUtil.a("Bundle_SupervisorImpl", "Failed to match base app bundle-version excepted ", a3, " but ", b);
        return -100;
    }

    private void e(List<yi> list, Collection<yi> collection, Collection<String> collection2) {
        for (yi yiVar : collection) {
            if (collection2.contains(yiVar.f())) {
                list.add(yiVar);
            }
        }
    }

    private Set<String> e(List<yi> list) {
        HashSet hashSet = new HashSet();
        Iterator<yi> it = list.iterator();
        while (it.hasNext()) {
            hashSet.addAll(it.next().e());
        }
        return hashSet;
    }

    private List<yi> d(List<String> list) {
        Collection<yi> a2 = yg.a().a(this.b);
        ArrayList arrayList = new ArrayList(list.size());
        e(arrayList, a2, list);
        Set<String> e = e(arrayList);
        if (!e.isEmpty()) {
            e.removeAll(list);
            LogUtil.c("Bundle_SupervisorImpl", "Automatically add dependencies ", e.toString(), " for install module ", list.toString());
            e(arrayList, a2, e);
        }
        return arrayList;
    }

    private void e(List<yi> list, ModuleInstallSupervisor.Callback callback) {
        try {
            ArrayList arrayList = new ArrayList(list.size());
            long d = d(list, arrayList);
            callback.onDeferredInstall(null);
            int createSessionId = createSessionId(list);
            boolean z = false;
            LogUtil.d("Bundle_SupervisorImpl", "DeferredInstall task id: ", Integer.valueOf(createSessionId));
            a aVar = new a(this.f17762a, list);
            if (d == 0) {
                LogUtil.d("Bundle_SupervisorImpl", "Deferred modules have been downloaded, install them directly!");
                aVar.onCompleted();
            } else {
                if (d < this.c && !this.j.isDeferredDownloadOnlyWhenUsingWifiData()) {
                    z = true;
                }
                this.j.deferredDownload(createSessionId, c(arrayList), aVar, z);
            }
        } catch (IOException unused) {
            callback.onError(bundleErrorCode(-99));
            LogUtil.e("Bundle_SupervisorImpl", "Failed to copy builtin module apks (onDeferredInstall)");
        }
    }

    private void b(List<String> list, List<yi> list2, ModuleInstallSupervisor.Callback callback) {
        if (this.h.b()) {
            LogUtil.a("Bundle_SupervisorImpl", "Start install request error code: ACTIVE_SESSIONS_LIMIT_EXCEEDED");
            callback.onError(bundleErrorCode(-1));
            return;
        }
        int createSessionId = createSessionId(list2);
        LogUtil.c("Bundle_SupervisorImpl", "startInstall task id: ", Integer.valueOf(createSessionId), ", moduleNames=", list);
        yt a2 = this.h.a(createSessionId);
        if ((a2 == null || a2.b() != 8) && this.h.d(list)) {
            LogUtil.a("Bundle_SupervisorImpl", "Start install request error code: INCOMPATIBLE_WITH_EXISTING_SESSION");
            callback.onError(bundleErrorCode(-8));
            return;
        }
        try {
            d(createSessionId, a2, list, list2, callback);
        } catch (IOException e) {
            LogUtil.a("Bundle_SupervisorImpl", "Failed to copy internal modules, ex=", LogUtil.a(e));
            callback.onError(bundleErrorCode(-99));
        }
    }

    private void d(int i, yt ytVar, List<String> list, List<yi> list2, ModuleInstallSupervisor.Callback callback) throws IOException {
        yt ytVar2;
        ArrayList arrayList = new ArrayList(list2.size());
        long d = d(list2, arrayList);
        callback.onStartInstall(i, null);
        List<DownloadRequest> c2 = c(arrayList);
        if (ytVar == null) {
            ytVar2 = new yt(i, list, list2, c2, d);
        } else {
            ytVar.e(c2, d);
            ytVar2 = ytVar;
        }
        this.h.a(i, ytVar2);
        if (d <= 0) {
            LogUtil.d("Bundle_SupervisorImpl", "Modules have been downloaded, install them directly!");
            new c(this.f17762a, i, this.h, list2).onCompleted();
        } else {
            d(ytVar2);
        }
    }

    private void d(yt ytVar) {
        Intent intent = new Intent();
        intent.putExtra("sessionId", ytVar.d());
        intent.putExtra("realDownloadTotalBytes", ytVar.f());
        intent.putStringArrayListExtra("moduleNames", (ArrayList) ytVar.c());
        intent.putStringArrayListExtra("downloadModuleNames", (ArrayList) ytVar.e());
        intent.setPackage(this.b.getPackageName());
        intent.setClass(this.b, this.i);
        ytVar.es_(PendingIntent.getActivity(this.b, ytVar.d(), intent, 167772160));
        this.h.a(ytVar.d(), 8);
        this.h.c(ytVar);
    }

    private void b(List<String> list, ModuleInstallSupervisor.Callback callback) {
        LogUtil.c("Bundle_SupervisorImpl", "start uninstall modules ", list.toString());
        for (String str : list) {
            yi e = yg.a().e(this.b, str);
            if (e != null) {
                try {
                    this.f17762a.a(e);
                    LogUtil.c("Bundle_SupervisorImpl", str, " record uninstall succeed.");
                } catch (yu.d e2) {
                    LogUtil.a("Bundle_SupervisorImpl", str, " record uninstall failed. ex=", LogUtil.a(e2));
                    callback.onError(bundleErrorCode(-100));
                    return;
                }
            }
        }
        callback.onDeferredUninstall(null);
    }

    private boolean g(List<String> list) {
        return list.isEmpty() || !AppBundleBuildConfig.d().containsAll(list);
    }

    private boolean f(List<String> list) {
        for (yi yiVar : yg.a().a(this.b)) {
            if (list.contains(yiVar.f()) && !a(yiVar)) {
                return false;
            }
        }
        return true;
    }

    private boolean a(yi yiVar) {
        return d(yiVar);
    }

    private boolean d(yi yiVar) {
        return yiVar.d() <= Build.VERSION.SDK_INT;
    }

    private List<DownloadRequest> c(List<yi> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (yi yiVar : list) {
            arrayList.add(DownloadRequest.newBuilder().c(yiVar.g()).e(yw.a().b(yiVar).getAbsolutePath()).b(yiVar.f() + ".tmp").d(yiVar.f()).c(yiVar.a()).e());
        }
        return arrayList;
    }

    private long d(List<yi> list, List<yi> list2) throws IOException {
        long j = 0;
        for (yi yiVar : list) {
            if (!c(yiVar)) {
                list2.add(yiVar);
                j += yiVar.o();
            }
        }
        return j;
    }

    private boolean c(yi yiVar) throws IOException {
        ys ysVar = new ys(yw.a().b(yiVar), yw.a().a(yiVar));
        try {
            boolean d = ysVar.d(this.b, yiVar, this.d);
            if (d) {
                FileUtils.i(yw.a().i(yiVar));
            }
            return d;
        } finally {
            FileUtils.d(ysVar);
        }
    }

    static class c implements DownloadCallback {
        private final List<yi> b;
        private final yu c;
        private final yp d;
        private final int e;

        c(yu yuVar, int i, yp ypVar, List<yi> list) {
            this.c = yuVar;
            this.e = i;
            this.d = ypVar;
            this.b = list;
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onStart() {
            yt a2 = this.d.a(this.e);
            if (a2 != null) {
                this.d.a(this.e, 2);
                e(a2);
            }
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onCanceled() {
            yt a2 = this.d.a(this.e);
            if (a2 != null) {
                this.d.a(this.e, 7);
                e(a2);
            }
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onCanceling() {
            yt a2 = this.d.a(this.e);
            if (a2 != null) {
                this.d.a(this.e, 9);
                e(a2);
            }
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onProgress(long j) {
            yt a2 = this.d.a(this.e);
            if (a2 != null) {
                a2.d(j);
                this.d.a(this.e, 2);
                e(a2);
            }
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onCompleted() {
            yt a2 = this.d.a(this.e);
            if (a2 != null) {
                this.d.a(this.e, 3);
                e(a2);
                yo.c(new yv(this.c, this.b, this.e, this.d));
            }
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onError(int i) {
            yt a2 = this.d.a(this.e);
            if (a2 != null) {
                a2.b(-10);
                this.d.a(this.e, 6);
                e(a2);
            }
        }

        private void e(yt ytVar) {
            this.d.c(ytVar);
        }
    }

    static class a implements DownloadCallback {

        /* renamed from: a, reason: collision with root package name */
        private final List<yi> f17763a;
        private final yu d;

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onCanceled() {
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onCanceling() {
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onError(int i) {
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onProgress(long j) {
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onStart() {
        }

        a(yu yuVar, List<yi> list) {
            this.f17763a = list;
            this.d = yuVar;
        }

        @Override // com.huawei.haf.bundle.AppBundleDownloader.DownloadCallback
        public void onCompleted() {
            yo.c(new yl(this.d, this.f17763a));
        }
    }
}
