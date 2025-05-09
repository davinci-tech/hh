package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.huawei.android.bundlecore.load.ModuleLoader;
import com.huawei.android.bundlecore.load.listener.OnModuleLoadListener;
import com.huawei.haf.bundle.extension.BundleExtensionException;
import com.huawei.haf.bundle.extension.BundleLoadMode;
import com.huawei.haf.bundle.extension.ContentProviderProxy;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes8.dex */
final class ze implements Runnable {
    private final zh b;
    private final OnModuleLoadListener c;
    private final Object d = new Object();
    private final List<Intent> e;

    ze(zh zhVar, List<Intent> list, OnModuleLoadListener onModuleLoadListener) {
        this.b = zhVar;
        this.e = list;
        this.c = onModuleLoadListener;
    }

    private Context e() {
        return this.b.b();
    }

    /* renamed from: ze$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f17771a;

        static {
            int[] iArr = new int[BundleLoadMode.values().length];
            f17771a = iArr;
            try {
                iArr[BundleLoadMode.MULTIPLE_CLASSLOADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17771a[BundleLoadMode.SINGLE_CLASSLOADER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private ModuleLoader b(yi yiVar) {
        int i = AnonymousClass5.f17771a[this.b.c().ordinal()];
        if (i == 1) {
            return new zk();
        }
        if (i != 2) {
            return yiVar.s() ? new zk() : new zl();
        }
        return new zl();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (CollectionUtils.d(this.e)) {
            OnModuleLoadListener onModuleLoadListener = this.c;
            if (onModuleLoadListener != null) {
                onModuleLoadListener.onFailed(-28);
            }
            LogUtil.c("Bundle_LoadTask", "reportLoadResult failed, moduleFileIntents is empty.");
            return;
        }
        if (HandlerExecutor.c()) {
            d(e());
            return;
        }
        synchronized (this.d) {
            b(e(), new CountDownLatch(1));
        }
    }

    private void b(final Context context, final CountDownLatch countDownLatch) {
        HandlerExecutor.a(new Runnable() { // from class: ze.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ze.this.d(context);
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            String stringExtra = this.e.get(0).getStringExtra("moduleName");
            yi e2 = yg.a().e(context, stringExtra);
            if (e2 != null) {
                e(Collections.EMPTY_LIST, Collections.singletonList(new zn(new zm(stringExtra, e2.i(), e2.k()), -26, e)), 0L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(0);
        ArrayList arrayList2 = new ArrayList(this.e.size());
        for (Intent intent : this.e) {
            String stringExtra = intent.getStringExtra("moduleName");
            if (this.b.checkModuleLoaded(stringExtra)) {
                LogUtil.c("Bundle_LoadTask", "Module ", stringExtra, " has been loaded!");
            } else {
                String stringExtra2 = intent.getStringExtra("apk");
                yi e = yg.a().e(context, stringExtra);
                if (e == null) {
                    LogUtil.a("Bundle_LoadTask", "loadModuleInternal info = null");
                } else {
                    zm zmVar = new zm(stringExtra, e.i(), e.k());
                    try {
                        if (Build.VERSION.SDK_INT >= 29) {
                            zg.c(e);
                        }
                        eH_(context, e, intent, stringExtra2);
                        if (!yw.a().b(e).setLastModified(System.currentTimeMillis())) {
                            LogUtil.a("Bundle_LoadTask", "Failed to set last modified time for ", e.f());
                        }
                        arrayList2.add(zmVar);
                        this.b.e(stringExtra, stringExtra2);
                    } catch (zf e2) {
                        zg.d(e);
                        arrayList.add(new zn(zmVar, e2.a(), e2.getCause()));
                    }
                }
            }
        }
        e(arrayList2, arrayList, System.currentTimeMillis() - currentTimeMillis);
    }

    private void eH_(Context context, yi yiVar, Intent intent, String str) throws zf {
        ModuleLoader b = b(yiVar);
        try {
            b.loadResources(context, str);
            try {
                ClassLoader loadCode = b.loadCode(yiVar.f(), intent.getStringArrayListExtra("added-dex"), yw.a().c(yiVar), yiVar.m() ? yw.a().d(yiVar) : null, yiVar.e());
                try {
                    e(loadCode, yiVar);
                } catch (zf e) {
                    LogUtil.e("Bundle_LoadTask", "Failed to activate ", yiVar.f());
                    b.unloadCode(yiVar.f(), loadCode);
                    throw e;
                }
            } catch (zf e2) {
                LogUtil.e("Bundle_LoadTask", "Failed to load module ", yiVar.f(), " code!");
                throw e2;
            }
        } catch (zf e3) {
            LogUtil.e("Bundle_LoadTask", "Failed to load module ", yiVar.f(), " resources!");
            throw e3;
        }
    }

    private void e(ClassLoader classLoader, yi yiVar) throws zf {
        try {
            ContentProviderProxy.createAndActivateModuleProviders(classLoader, yiVar.f());
        } catch (BundleExtensionException e) {
            throw new zf(-25, e);
        }
    }

    private void e(List<zm> list, List<zn> list2, long j) {
        if (!list2.isEmpty()) {
            if (this.c != null) {
                this.c.onFailed(list2.get(list2.size() - 1).e());
            }
            LogUtil.c("Bundle_LoadTask", "reportLoadResult failed, ", list2.toString(), ", cost time:", Long.valueOf(j), "ms");
            return;
        }
        OnModuleLoadListener onModuleLoadListener = this.c;
        if (onModuleLoadListener != null) {
            onModuleLoadListener.onCompleted();
        }
        if (list.isEmpty()) {
            return;
        }
        LogUtil.c("Bundle_LoadTask", "reportLoadResult completed, ", list.toString(), ", cost time:", Long.valueOf(j), "ms");
    }
}
