package com.huawei.openalliance.ad.download.ag;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.download.app.interfaces.AutoOpenListener;
import com.huawei.openalliance.ad.download.l;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.views.AppDownloadButton;

/* loaded from: classes5.dex */
public class d extends a<AppDownloadTask> {
    private static final byte[] e = new byte[0];
    private static d f;
    c d;

    public void d() {
        c cVar = this.d;
        if (cVar != null) {
            cVar.b();
        }
    }

    public void c(final AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null) {
            return;
        }
        f.a(this.f6715a, appDownloadTask, new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.download.ag.d.3
            @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
            public void onRemoteCallResult(String str, CallResult<String> callResult) {
                if (callResult.getCode() != -1) {
                    appDownloadTask.e(true);
                    d.super.a((d) appDownloadTask);
                    ho.b("AgLocalDownloadMgr", " resume task is success:" + appDownloadTask.n());
                }
            }
        }, String.class);
    }

    public void c() {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a();
        }
    }

    public void b(String str, AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.b(str, onResolutionRequiredListener);
        }
    }

    public void b(String str, l lVar) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.b(str, lVar);
        }
    }

    public void b(final AppDownloadTask appDownloadTask) {
        if (appDownloadTask == null) {
            return;
        }
        f.b(this.f6715a, appDownloadTask, new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.download.ag.d.2
            @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
            public void onRemoteCallResult(String str, CallResult<String> callResult) {
                if (callResult.getCode() != -1) {
                    ho.b("AgLocalDownloadMgr", " pause task is success:" + appDownloadTask.n());
                }
            }
        }, String.class);
    }

    @Override // com.huawei.openalliance.ad.download.ag.a
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public AppDownloadTask a(String str) {
        DownloadTask a2 = super.a(str);
        if (a2 instanceof AppDownloadTask) {
            return (AppDownloadTask) a2;
        }
        if (a2 == null) {
            AppInfo appInfo = new AppInfo();
            appInfo.a(str);
            appInfo.o("5");
            RemoteAppDownloadTask remoteAppDownloadTask = (RemoteAppDownloadTask) f.a(this.f6715a, appInfo, RemoteAppDownloadTask.class);
            if (remoteAppDownloadTask != null) {
                ho.b("AgLocalDownloadMgr", " remote task is exist, create proxy task");
                AppDownloadTask a3 = remoteAppDownloadTask.a(appInfo);
                super.a((d) a3);
                return a3;
            }
        }
        return null;
    }

    public AppDownloadTask b(AppInfo appInfo) {
        RemoteAppDownloadTask remoteAppDownloadTask;
        if (c(appInfo)) {
            return null;
        }
        AppDownloadTask appDownloadTask = (AppDownloadTask) super.a(appInfo.getPackageName());
        if (appDownloadTask != null || (remoteAppDownloadTask = (RemoteAppDownloadTask) f.a(this.f6715a, appInfo, RemoteAppDownloadTask.class)) == null) {
            return appDownloadTask;
        }
        ho.b("AgLocalDownloadMgr", " remote task is exist, create proxy task");
        AppDownloadTask a2 = remoteAppDownloadTask.a(appInfo);
        super.a((d) a2);
        return a2;
    }

    public void a(String str, AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(str, onResolutionRequiredListener);
        }
    }

    public void a(String str, l lVar) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(str, lVar);
        }
    }

    public void a(AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(onResolutionRequiredListener);
        }
    }

    public void a(AppDownloadListenerV1 appDownloadListenerV1) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(appDownloadListenerV1);
        }
    }

    public void a(AppDownloadListener appDownloadListener) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(appDownloadListener);
        }
    }

    public void a(AppInfo appInfo) {
        if (c(appInfo)) {
            return;
        }
        final AppDownloadTask b = b(appInfo);
        if (b != null) {
            f.c(this.f6715a, b, new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.download.ag.d.4
                @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                public void onRemoteCallResult(String str, CallResult<String> callResult) {
                    if (callResult.getCode() == 200 && String.valueOf(Boolean.TRUE).equals(callResult.getData())) {
                        d.super.c((d) b);
                        ho.b("AgLocalDownloadMgr", " removeTask task is success:" + b.n());
                    }
                }
            }, String.class);
            return;
        }
        ho.b("AgLocalDownloadMgr", " removeTask failed:" + appInfo.getPackageName());
    }

    public void a(AutoOpenListener autoOpenListener) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(autoOpenListener);
        }
    }

    @Override // com.huawei.openalliance.ad.download.ag.a
    public void a(final AppDownloadTask appDownloadTask) {
        f.a(this.f6715a, appDownloadTask, new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.download.ag.d.1
            @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
            public void onRemoteCallResult(String str, CallResult<String> callResult) {
                if (callResult.getCode() != -1) {
                    AppDownloadTask appDownloadTask2 = appDownloadTask;
                    if (appDownloadTask2 != null) {
                        appDownloadTask2.e(true);
                    }
                    d.super.a((d) appDownloadTask);
                }
            }
        }, String.class);
    }

    private static boolean c(AppInfo appInfo) {
        return appInfo == null || TextUtils.isEmpty(appInfo.getPackageName());
    }

    public static d a(Context context) {
        synchronized (e) {
            if (f == null) {
                f = new d(context);
            }
        }
        return f;
    }

    private d(Context context) {
        super(context);
        super.a();
        c cVar = new c(context);
        this.d = cVar;
        a(cVar);
    }
}
