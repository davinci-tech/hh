package com.huawei.openalliance.ad.download.app;

import android.content.Context;
import com.huawei.openalliance.ad.inter.IAppDownloadManager;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSNativeView;
import com.huawei.openalliance.ad.views.PPSPlacementView;

/* loaded from: classes5.dex */
public class l implements com.huawei.openalliance.ad.download.app.interfaces.b, IAppDownloadManager {

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.openalliance.ad.download.app.interfaces.a f6791a = new m();

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public int startDownload(Context context, PPSPlacementView pPSPlacementView, IPlacementAd iPlacementAd) {
        int a2 = this.f6791a.a(context, pPSPlacementView, iPlacementAd);
        a(context, iPlacementAd, a2);
        return a2;
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public int startDownload(Context context, PPSNativeView pPSNativeView, INativeAd iNativeAd) {
        int a2 = this.f6791a.a(context, pPSNativeView, iNativeAd);
        a(context, iNativeAd, a2);
        return a2;
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public int startDownload(Context context, IPlacementAd iPlacementAd) {
        int a2 = this.f6791a.a(context, iPlacementAd);
        a(context, iPlacementAd, a2);
        return a2;
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public int startDownload(Context context, INativeAd iNativeAd) {
        int a2 = this.f6791a.a(context, iNativeAd);
        a(context, iNativeAd, a2);
        return a2;
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public void setAgdDownloadSource(int i) {
        this.f6791a.a(i);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public int resumeDownload(Context context, IPlacementAd iPlacementAd) {
        return this.f6791a.b(context, iPlacementAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public int resumeDownload(Context context, INativeAd iNativeAd) {
        return this.f6791a.b(context, iNativeAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public void removeOnResolutionRequiredListener() {
        this.f6791a.a();
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public void removeAllOnResolutionRequiredListener() {
        this.f6791a.b();
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public void pauseDownload(Context context, IPlacementAd iPlacementAd) {
        this.f6791a.c(context, iPlacementAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public void pauseDownload(Context context, INativeAd iNativeAd) {
        this.f6791a.c(context, iNativeAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public boolean openApp(Context context, IPlacementAd iPlacementAd) {
        return this.f6791a.a(context, (IAd) iPlacementAd, true);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public boolean openApp(Context context, INativeAd iNativeAd) {
        return this.f6791a.a(context, (IAd) iNativeAd, true);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public int getDownloadProgress(Context context, IPlacementAd iPlacementAd) {
        return this.f6791a.e(context, iPlacementAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public int getDownloadProgress(Context context, INativeAd iNativeAd) {
        return this.f6791a.e(context, iNativeAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public AppStatus getAppStatus(Context context, IPlacementAd iPlacementAd) {
        return this.f6791a.f(context, iPlacementAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public AppStatus getAppStatus(Context context, INativeAd iNativeAd) {
        return this.f6791a.f(context, iNativeAd);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.b
    public int f(Context context, IAd iAd) {
        return this.f6791a.e(context, iAd);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.b
    public AppStatus e(Context context, IAd iAd) {
        return this.f6791a.f(context, iAd);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.b
    public void d(Context context, IAd iAd) {
        this.f6791a.d(context, iAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public void cancelDownload(Context context, IPlacementAd iPlacementAd) {
        this.f6791a.d(context, iPlacementAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public void cancelDownload(Context context, INativeAd iNativeAd) {
        this.f6791a.d(context, iNativeAd);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.b
    public int c(Context context, IAd iAd) {
        return this.f6791a.c(context, iAd);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.b
    public int b(Context context, IAd iAd) {
        return this.f6791a.b(context, iAd);
    }

    @Override // com.huawei.openalliance.ad.inter.IAppDownloadManager
    public void addOnResolutionRequiredListener(AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener) {
        if (onResolutionRequiredListener == null) {
            return;
        }
        this.f6791a.a(onResolutionRequiredListener);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.b
    public void a(Integer num) {
        this.f6791a.a(num);
    }

    @Override // com.huawei.openalliance.ad.download.app.interfaces.b
    public int a(Context context, IAd iAd) {
        int a2 = this.f6791a.a(context, iAd);
        a(context, iAd, a2);
        return a2;
    }

    public static void b(Context context, AppInfo appInfo) {
        m.b(context, appInfo);
    }

    private void a(Context context, IAd iAd, int i) {
        this.f6791a.a(context, iAd, i);
    }

    public static void a(Context context, AppInfo appInfo) {
        m.a(context, appInfo);
    }
}
